package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.demo.config.annotation.ResponseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.util.common.Constant;
import com.example.demo.util.json.JsonUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.example.demo.service.BiProductsService;
import com.example.demo.entity.BiProducts;
import com.example.demo.util.vo.BaseBodyVo;
import com.example.demo.util.vo.BaseQueryVo;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * es-产品信息 前端控制器
 *
 * @author luox
 * @since 2022-05-31
 */
@RestController
@RequestMapping("/bi_products")
@ResponseEntity
public class BiProductsController {

    @Autowired
    private BiProductsService biProductsService;

    @Autowired
    private RestHighLevelClient client;//仅在8.x版本中被废弃
    //@Autowired
    //private ProductsRepository productsRepository;

    @GetMapping("/searchSuggest/{prefix}")
    public List<String> searchSuggest(@PathVariable("prefix") String prefix) throws IOException {
        SearchRequest searchRequest = new SearchRequest(Constant.ES_INDEX_PRODUCTS);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //建议搜索默认10条
        CompletionSuggestionBuilder suggest = SuggestBuilders
                .completionSuggestion("title.suggest").size(10).skipDuplicates(true)
                .prefix(prefix);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("s-title",suggest);
        sourceBuilder.suggest(suggestBuilder);
        //筛选指定字段
        sourceBuilder.fetchSource(new String[]{"id","title"},new String[]{"description"});

        searchRequest.source(sourceBuilder);

        List<String> result = new ArrayList<>();
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        CompletionSuggestion suggestion = response.getSuggest().getSuggestion("s-title");
        for (CompletionSuggestion.Entry.Option option : suggestion.getOptions()) {
            result.add(option.getText().string());
            //result.add(option.getHit().field("title").getValue());
        }

        return result;
    }

    /**
     * 根据id查询es-产品信息详情
     *
     * @param id id
     * @return {@link BiProducts }
     * @author luox
     * @date 2022-05-31
     */
    @GetMapping("/get/{id}")
    public BiProducts getById(@PathVariable("id") Long id) throws IOException {
        GetRequest getRequest = new GetRequest().index(Constant.ES_INDEX_PRODUCTS).id(id.toString());
        //Jackson无法反序列化无默认无参构造方法
        //FetchSourceContext sourceContext = new FetchSourceContext(true,null,new String[]{"suggest"});
        //getRequest.fetchSourceContext(sourceContext);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);

        return JsonUtil.parse(response.getSourceAsString(),BiProducts.class);
    }

    @GetMapping("/page")
    public Page<BiProducts> page(BaseQueryVo<BiProducts> queryVo,BiProducts biProducts) throws IOException {
        SearchRequest searchRequest = new SearchRequest(Constant.ES_INDEX_PRODUCTS);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //from,size分页，数据量大，深度分页不适用
        sourceBuilder.from((int) ((queryVo.getPageNum() - 1L) * queryVo.getPageSize()));
        sourceBuilder.size((int) queryVo.getPageSize());

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(StrUtil.isNotBlank(biProducts.getTitle())){
            boolQueryBuilder.should(QueryBuilders.matchQuery("title",biProducts.getTitle()));
        }
        if(StrUtil.isNotBlank(biProducts.getDescription())){
            boolQueryBuilder.should(QueryBuilders.matchQuery("description",biProducts.getDescription()));
        }
        sourceBuilder.query(boolQueryBuilder);
        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        sourceBuilder.highlighter(highlightBuilder);
        //指定和排除搜索字段
        sourceBuilder.fetchSource(null,new String[]{"description"});
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        //total
        Page<BiProducts> page = queryVo.toPage();
        page.setTotal(searchHits.getTotalHits().value);

        SearchHit[] searchHitArray = searchResponse.getHits().getHits();
        List<BiProducts> list = new ArrayList<>();
        for(SearchHit hit : searchHitArray){
            list.add(JsonUtil.parse(hit.getSourceAsString(),BiProducts.class));
        }
        page.setRecords(list);
        return page;
    }

    /**
     * 保存es-产品信息
     *
     * @param biProducts es-产品信息
     * @return {@link Boolean }
     * @author luox
     * @date 2022-05-31
     */
    @PostMapping("/save")
    @Transactional
    public Boolean save(@RequestBody BiProducts biProducts) throws IOException {
        biProductsService.save(biProducts);
        //Products products = new Products();
        //BeanUtils.copyProperties(biProducts,products);
        //products.setPrice(biProducts.getPrice().doubleValue());
        //products.setSuggest(new Completion(new String[]{biProducts.getSuggest()}));
        //products.setSuggest(new Completion(biProducts.getSuggest().split(",")));
        //productsRepository.save(products);

        IndexRequest request = new IndexRequest(Constant.ES_INDEX_PRODUCTS);
        request.id(biProducts.getId().toString()).source(JsonUtil.toJsonString(biProducts), XContentType.JSON);
        int status = client.index(request, RequestOptions.DEFAULT).status().getStatus();

        return status == RestStatus.CREATED.getStatus();
    }

    /**
     * 更新es-产品信息
     *
     * @param biProducts es-产品信息
     * @return {@link Boolean }
     * @author luox
     * @date 2022-05-31
     */
    @PostMapping("/update")
    @Transactional
    public Boolean update(@RequestBody BiProducts biProducts) throws IOException {
        //Products products = new Products();
        //BeanUtils.copyProperties(biProducts,products);
        UpdateRequest updateRequest = new UpdateRequest(Constant.ES_INDEX_PRODUCTS,biProducts.getId().toString());

        updateRequest.doc(JsonUtil.toJsonString(biProducts), XContentType.JSON);

        client.update(updateRequest,RequestOptions.DEFAULT);
        return biProductsService.updateById(biProducts);
    }

    /**
     * 删除es-产品信息
     *
     * @param baseBodyVo 请求body基础vo
     * @return {@link Boolean }
     * @author luox
     * @date 2022-05-31
     */
    @PostMapping("/delete")
    @Transactional
    public Boolean delete(@RequestBody @Validated BaseBodyVo baseBodyVo) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for(Long id : baseBodyVo.getIds()){
            bulkRequest.add(new DeleteRequest(Constant.ES_INDEX_PRODUCTS,id.toString()));
            //client.delete(deleteRequest, RequestOptions.DEFAULT);
        }
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
        //List<String> ids = baseBodyVo.getIds().stream().map(Object::toString).collect(Collectors.toList());
        //productsRepository.deleteAllById(ids);

        return biProductsService.removeByIds(baseBodyVo.getIds());
    }

}
