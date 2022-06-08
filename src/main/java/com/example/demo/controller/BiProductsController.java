package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.*;
import co.elastic.clients.elasticsearch.indices.AnalyzeRequest;
import co.elastic.clients.elasticsearch.indices.AnalyzeResponse;
import co.elastic.clients.util.ObjectBuilder;
import com.example.demo.config.annotation.ResponseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.es.config.FieldAnalyzer;
import com.example.demo.util.common.Constant;
import com.example.demo.util.json.JsonUtil;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.AnalyzeRequest;
//import org.elasticsearch.client.indices.AnalyzeResponse;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.rest.RestStatus;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.suggest.SuggestBuilder;
//import org.elasticsearch.search.suggest.SuggestBuilders;
//import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
//import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
//import org.elasticsearch.xcontent.XContentType;
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

    //@Autowired
    //private RestHighLevelClient client;
    //@Autowired
    //private ProductsRepository productsRepository;
    @Autowired
    private ElasticsearchClient client;

    @GetMapping("/ikAnalyze/{searchKey}")
    public List<String> ikAnalyze(@PathVariable("searchKey") String searchKey) throws IOException {
        List<String> keywordList = new ArrayList<>();
        AnalyzeResponse analyzeResponse = client.indices().analyze(
                request -> request.analyzer(FieldAnalyzer.IK_SMART).text(searchKey));
        analyzeResponse.tokens().forEach(analyzeToken -> {
            keywordList.add(analyzeToken.token());
        });
        //AnalyzeRequest analyzeRequest = AnalyzeRequest.withGlobalAnalyzer(FieldAnalyzer.IK_SMART,searchKey);
        //AnalyzeResponse analyzeResponse = client.indices().analyze(analyzeRequest, RequestOptions.DEFAULT);
        //analyzeResponse.getTokens().forEach(analyzeToken -> {
        //    keywordList.add(analyzeToken.getTerm());
        //});
        return keywordList;
    }

    @GetMapping("/searchSuggest/{prefix}")
    public List<String> searchSuggest(@PathVariable("prefix") String prefix) throws IOException {
        //SearchRequest searchRequest = new SearchRequest(Constant.ES_INDEX_PRODUCTS);
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //建议搜索默认10条
        //CompletionSuggestionBuilder suggest = SuggestBuilders
        //        .completionSuggestion("title.suggest").size(10).skipDuplicates(true)
        //        .prefix(prefix);
        //
        //SuggestBuilder suggestBuilder = new SuggestBuilder();
        //suggestBuilder.addSuggestion("s-title",suggest);
        //sourceBuilder.suggest(suggestBuilder);
        //筛选指定字段
        //sourceBuilder.fetchSource(new String[]{"id","title"},new String[]{"description"});

        //searchRequest.source(sourceBuilder);


        //FieldSuggester fieldSuggester = FieldSuggester.of(s -> (ObjectBuilder<FieldSuggester>) s.prefix(""));

        SearchRequest searchRequest = SearchRequest.of(request -> request.index(Constant.ES_INDEX_PRODUCTS)
                .source(s -> s.filter(sf -> sf.includes("id","title").excludes("description")))
                .suggest(s -> s.text("s-title").suggesters(prefix,
                        fs -> fs.completion(c -> c.field("title.suggest").skipDuplicates(true).size(10)))));
                        //fs -> fs.prefix(""))));
        //field("title.suggest")
        //fs -> fs.completion(c -> c.field("title.suggest").skipDuplicates(true).size(10))

        List<String> result = new ArrayList<>();
        SearchResponse<Suggester> response = client.search(searchRequest,Suggester.class);
        response.suggest().get("s-title").forEach(s -> {
            result.add(s.term().text());
        });
        //SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //CompletionSuggestion suggestion = response.getSuggest().getSuggestion("s-title");
        //for (CompletionSuggestion.Entry.Option option : suggestion.getOptions()) {
        //    result.add(option.getText().string());
            //result.add(option.getHit().field("title").getValue());
        //}

        return result;
    }

    @GetMapping("/get/{id}")
    public BiProducts getById(@PathVariable("id") Long id) throws IOException {
        //GetRequest getRequest = new GetRequest().index(Constant.ES_INDEX_PRODUCTS).id(id.toString());
        //Jackson无法反序列化无默认无参构造方法
        //FetchSourceContext sourceContext = new FetchSourceContext(true,null,new String[]{"suggest"});
        //getRequest.fetchSourceContext(sourceContext);
        //GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        //return JsonUtil.parse(response.getSourceAsString(),BiProducts.class);
        GetResponse<BiProducts> response = client.get(request -> request.index(Constant.ES_INDEX_PRODUCTS)
                .id(id.toString()),BiProducts.class);

        return response.source();
    }

    @GetMapping("/page")
    public Page<BiProducts> page(BaseQueryVo<BiProducts> queryVo,BiProducts biProducts) throws IOException {

        BoolQuery.Builder builder = new BoolQuery.Builder();
        if(StrUtil.isNotBlank(biProducts.getTitle())){
            builder.should(bq -> bq.match(mq -> mq.field("title").query(biProducts.getTitle())));
        }
        if(StrUtil.isNotBlank(biProducts.getDescription())){
            builder.should(bq -> bq.match(mq -> mq.field("description").query(biProducts.getDescription())));
        }
        SearchResponse<BiProducts> searchResponse = client.search(s -> s.index(Constant.ES_INDEX_PRODUCTS)
                        .source(c -> c.filter(f -> f.excludes("description")))
                        .query(q -> q.bool(builder.build()))
                        .from((int) ((queryVo.getPageNum() - 1L) * queryVo.getPageSize()))
                        .size((int) queryVo.getPageSize())
                        .highlight(h -> h.fields("title",hf -> hf))
                        //.highlight(h -> h.fields("h-test",hf -> hf.field("title")))
                ,BiProducts.class);

        //total
        Page<BiProducts> page = queryVo.toPage();
        page.setTotal(searchResponse.hits().total().value());

        List<Hit<BiProducts>> hitList = searchResponse.hits().hits();
        List<BiProducts> list = new ArrayList<>();
        for(Hit<BiProducts> hit : hitList){
            list.add(hit.source());
        }
        page.setRecords(list);
        return page;

        //SearchRequest searchRequest = new SearchRequest(Constant.ES_INDEX_PRODUCTS);
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        ////from,size分页，数据量大，深度分页不适用
        //sourceBuilder.from((int) ((queryVo.getPageNum() - 1L) * queryVo.getPageSize()));
        //sourceBuilder.size((int) queryVo.getPageSize());
        //
        //BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //if(StrUtil.isNotBlank(biProducts.getTitle())){
        //    boolQueryBuilder.should(QueryBuilders.matchQuery("title",biProducts.getTitle()));
        //}
        //if(StrUtil.isNotBlank(biProducts.getDescription())){
        //    boolQueryBuilder.should(QueryBuilders.matchQuery("description",biProducts.getDescription()));
        //}
        //sourceBuilder.query(boolQueryBuilder);
        ////高亮设置
        //HighlightBuilder highlightBuilder = new HighlightBuilder();
        //highlightBuilder.field("title");
        //sourceBuilder.highlighter(highlightBuilder);
        ////指定和排除搜索字段
        //sourceBuilder.fetchSource(null,new String[]{"description"});
        //searchRequest.source(sourceBuilder);
        //
        //SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        //SearchHits searchHits = searchResponse.getHits();
        ////total
        //Page<BiProducts> page = queryVo.toPage();
        //page.setTotal(searchHits.getTotalHits().value);
        //
        //SearchHit[] searchHitArray = searchResponse.getHits().getHits();
        //List<BiProducts> list = new ArrayList<>();
        //for(SearchHit hit : searchHitArray){
        //    list.add(JsonUtil.parse(hit.getSourceAsString(),BiProducts.class));
        //}
        //page.setRecords(list);
        //return page;
    }

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

        //IndexRequest request = new IndexRequest(Constant.ES_INDEX_PRODUCTS);
        //request.id(biProducts.getId().toString()).source(JsonUtil.toJsonString(biProducts), XContentType.JSON);
        //int status = client.index(request, RequestOptions.DEFAULT).status().getStatus();
        //
        //return status == RestStatus.CREATED.getStatus();
        IndexResponse indexResponse = client.index(request -> request.index(Constant.ES_INDEX_PRODUCTS)
                .id(biProducts.getId().toString()).document(biProducts));

        return indexResponse.result() == Result.Created;
    }

    @PostMapping("/update")
    @Transactional
    public Boolean update(@RequestBody BiProducts biProducts) throws IOException {
        biProductsService.updateById(biProducts);
        //Products products = new Products();
        //BeanUtils.copyProperties(biProducts,products);
        //UpdateRequest updateRequest = new UpdateRequest(Constant.ES_INDEX_PRODUCTS,biProducts.getId().toString());
        //
        //updateRequest.doc(JsonUtil.toJsonString(biProducts), XContentType.JSON);
        //
        //client.update(updateRequest,RequestOptions.DEFAULT);

        UpdateResponse<BiProducts> updateResponse = client.update(request ->
                        request.index(Constant.ES_INDEX_PRODUCTS).id(biProducts.getId().toString()).doc(biProducts)
                ,BiProducts.class);

        return updateResponse.result() == Result.Updated;
    }

    @PostMapping("/delete")
    @Transactional
    public Boolean delete(@RequestBody @Validated BaseBodyVo baseBodyVo) throws IOException {
        biProductsService.removeByIds(baseBodyVo.getIds());
        //BulkRequest bulkRequest = new BulkRequest();
        //for(Long id : baseBodyVo.getIds()){
            //bulkRequest.add(new DeleteRequest(Constant.ES_INDEX_PRODUCTS,id.toString()));
            //client.delete(deleteRequest, RequestOptions.DEFAULT);
        //}
        //client.bulk(bulkRequest, RequestOptions.DEFAULT);
        //List<String> ids = baseBodyVo.getIds().stream().map(Object::toString).collect(Collectors.toList());
        //productsRepository.deleteAllById(ids);

        List<BulkOperation> bulkOperationList = new ArrayList<>();
        for(Long id : baseBodyVo.getIds()){
            bulkOperationList.add(new BulkOperation.Builder().delete(d ->
                    d.index(Constant.ES_INDEX_PRODUCTS).id(id.toString())).build());
        }
        BulkResponse bulkResponse = client.bulk(request -> request.operations(bulkOperationList));
        return !bulkResponse.errors();
    }

}
