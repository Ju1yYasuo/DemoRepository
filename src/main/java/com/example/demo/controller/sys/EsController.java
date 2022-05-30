package com.example.demo.controller.sys;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo.config.annotation.ResponseEntity;
import com.example.demo.config.es.entity.Products;
import com.example.demo.config.es.repository.ProductsRepository;
import com.example.demo.dto.es.ProductsQueryDto;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * es测试
 *
 * @author luox
 * @date 2022/5/26
 */
@RestController
@RequestMapping("/es")
@ResponseEntity
public class EsController {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ProductsRepository repository;

    public static final String INDEX_NAME = "products3";

    @PostMapping("/saveProducts")
    public IndexResponse saveProducts(@RequestBody List<Products> productsList) throws IOException {

        IndexRequest request = new IndexRequest(INDEX_NAME)
                .source(JSON.toJSONString(productsList), XContentType.JSON);

        return client.index(request, RequestOptions.DEFAULT);
    }

    //@PostMapping("/saveAll")
    //public Boolean saveAll(@RequestBody List<Products> productsList) {
    //    repository.saveAll(productsList);
    //    return true;
    //}

    @GetMapping("/searchSuggest/{prefix}")
    public List<String> searchSuggest(@PathVariable("prefix") String prefix) throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder ssb = new SearchSourceBuilder();

        //建议搜索默认10条
        CompletionSuggestionBuilder suggest = SuggestBuilders
                .completionSuggestion("suggest").size(10)
                .skipDuplicates(true)
                .prefix(prefix);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("s-test",suggest);

        ssb.suggest(suggestBuilder);

        searchRequest.source(ssb);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        List<String> result = new ArrayList<>();
        CompletionSuggestion suggestion = response.getSuggest().getSuggestion("s-test");
        for (CompletionSuggestion.Entry.Option option : suggestion.getOptions()) {
            result.add(option.getText().string());
        }

        return result;
    }

    //@GetMapping("/find")
    //public Page<Products> find(ProductsQueryDto dto) {
    //    PageRequest pageRequest = PageRequest.of(dto.getCurrent() - 1,dto.getSize(), Sort.by("id").ascending());
    //    if(StrUtil.isNotBlank(dto.getTitle())){
    //        return repository.findByTitle(dto.getTitle(),pageRequest);
    //    }
    //    return repository.findAll(pageRequest);
    //}

    @GetMapping("/getProducts")
    public List<Products> getProducts(ProductsQueryDto dto) throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.from(dto.getCurrent() - 1);
        ssb.size(dto.getSize());
        if(StrUtil.isNotBlank(dto.getTitle())){
            QueryBuilder queryBuilder = new MatchQueryBuilder("title",dto.getTitle());
            ssb.query(queryBuilder);
        }
        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        //highlightBuilder.preTags("<b>");
        //highlightBuilder.postTags("</b>");
        ssb.highlighter(highlightBuilder);

        searchRequest.source(ssb);

        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        System.out.println(searchResponse);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Products> list = new ArrayList<>();
        for(SearchHit hit : searchHits){
            list.add(JSON.parseObject(hit.getSourceAsString(),Products.class));

            //HighlightField highlightField = hit.getHighlightFields().get("title");
            //for (Text fragment : highlightField.getFragments()) {
            //    System.out.println(fragment.string());
            //}
            //System.out.println("----------------");
        }

        return list;
    }

    @GetMapping("/getProducts0")
    public List<Products> getProducts0(ProductsQueryDto dto) throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        QueryBuilder queryBuilder = new MatchQueryBuilder("title",dto.getTitle());
        ssb.query(queryBuilder);

        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        //highlightBuilder.preTags("<b>");
        //highlightBuilder.postTags("</b>");
        ssb.highlighter(highlightBuilder);

        searchRequest.source(ssb);

        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Products> list = new ArrayList<>();
        for(SearchHit hit : searchHits){
            list.add(JSON.parseObject(hit.getSourceAsString(),Products.class));

            HighlightField highlightField = hit.getHighlightFields().get("title");
            for (Text fragment : highlightField.getFragments()) {
                System.out.println(fragment.string());
            }
            System.out.println("----------------");


        }

        return list;
    }

    @GetMapping("/searchAggregation")
    public void searchAggregation() throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder ssb = new SearchSourceBuilder();

        TermsAggregationBuilder category = AggregationBuilders.terms("category").field("category.keyword");
        ssb.aggregation(category);

        searchRequest.source(ssb);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        Terms terms = response.getAggregations().get("category");

        for (Terms.Bucket bucket : terms.getBuckets()) {
            System.out.println(bucket.getKey());
            System.out.println(bucket.getDocCount());
        }
    }

    public void searchGeo() throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder ssb = new SearchSourceBuilder();

        //工体的坐标
        GeoPoint geoPoint = new GeoPoint(39.93367367974064d,116.47845257733152d);
        //geo距离查询  name=geo字段
        QueryBuilder qb = QueryBuilders.geoDistanceQuery("location")
                //距离 3KM
                .distance(3d, DistanceUnit.KILOMETERS)
                //坐标工体
                .point(geoPoint);

        ssb.query(qb);
        searchRequest.source(ssb);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }


    }

    @GetMapping("/searchGeoSort")
    public void searchGeoSort() throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder ssb = new SearchSourceBuilder();

        //工体的坐标
        GeoPoint geoPoint = new GeoPoint(39.93367367974064d,116.47845257733152d);

        GeoDistanceSortBuilder sortBuilder = SortBuilders
                .geoDistanceSort("location", geoPoint)
                .order(SortOrder.ASC);

        ssb.sort(sortBuilder);
        searchRequest.source(ssb);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }



}
