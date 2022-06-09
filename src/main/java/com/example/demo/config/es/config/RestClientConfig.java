package com.example.demo.config.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.example.demo.util.common.BeanUtil;
import com.example.demo.util.common.Constant;
import com.example.demo.util.json.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.xcontent.XContentBuilder;
//import org.elasticsearch.xcontent.XContentFactory;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;

/**
 * es client 配置类
 *
 * @author luox
 * @date 2022/5/26
 */
@Configuration
public class RestClientConfig {

    @Value("${spring.elasticsearch.rest.url}")
    private String url;
    @Value("${spring.elasticsearch.rest.port}")
    private Integer port;
    @Value("${spring.elasticsearch.rest.username}")
    private String username;
    @Value("${spring.elasticsearch.rest.password}")
    private String password;

    //@Autowired
    //Environment environment;
    //@Autowired
    //private RestHighLevelClient client;
    @Autowired
    private ElasticsearchClient client;

    //@Bean
    //public RestHighLevelClient restHighLevelClient(){
    //    //ClientConfiguration clientConfiguration = ClientConfiguration.builder()
    //    //        .connectedTo("localhost:9200")
    //    //        .build();
    //    //RestClients.create(clientConfiguration).rest();
    //    //String esUrl = environment.getProperty("es.url", "localhost");
    //
    //    RestHighLevelClient client = new RestHighLevelClient(
    //            RestClient.builder(
    //                    new HttpHost("localhost", 9200, "http")));
    //    return client;
    //}

    @Bean
    public ElasticsearchClient elasticsearchClient(){
        // 创建低版本client
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(username, password));
        RestClientBuilder builder = RestClient.builder(new HttpHost(url, port)).setHttpClientConfigCallback(
                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        RestClient restClient = builder.build();

        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
        //new ElasticsearchAsyncClient(transport);
    }

    @PostConstruct
    public void initIndex() throws IOException {
        ExistsRequest existsRequest = ExistsRequest.of(builder -> builder.index(Constant.ES_INDEX_PRODUCTS));

        // 判断索引是否存在
        if(!client.indices().exists(existsRequest).value()) {
            Map<String, Property> documentMap = new HashMap<>();
            documentMap.put("id",Property.of(p -> p.long_(xp -> xp.index(true))));

            documentMap.put("title",Property.of(p -> p.text(
                    xp -> xp.analyzer(FieldAnalyzer.IK_MAX_WORD)
                            .fields("suggest",sp -> sp.completion(cp -> cp.analyzer(FieldAnalyzer.IK_MAX_WORD))))));
            
            documentMap.put("price",Property.of(p -> p.double_(xp -> xp.index(true))));
            documentMap.put("createTime",Property.of(p -> p.date(
                    xp -> xp.index(true).format("epoch_millis"))));

            documentMap.put("description",Property.of(p -> p.text(xp -> xp.analyzer(FieldAnalyzer.IK_SMART))));

            // 读取mapping配置
            //File file = new ClassPathResource("es/mapping/products.json").getFile();
            //String jsonStr = IOUtils.toString(file.toURI(), StandardCharsets.UTF_8);
            //Map<String, Property> documentMap = JsonUtil.parse(jsonStr, HashMap.class);
            CreateIndexRequest request = CreateIndexRequest.of(builder ->
                builder.index(Constant.ES_INDEX_PRODUCTS)
                        .settings(s -> s.numberOfShards("1").numberOfReplicas("1"))
                        .mappings(m -> m.properties(documentMap))
            );

            CreateIndexResponse createIndexResponse = client.indices().create(request);
            System.out.println(createIndexResponse.acknowledged());
        }

    }

    //@PostConstruct
    //public void initEsMetadata() throws IOException {
    //    GetIndexRequest getIndexRequest = new GetIndexRequest(Constant.ES_INDEX_PRODUCTS);
    //    // 判断索引是否存在
    //    if(!client.indices().exists(getIndexRequest, RequestOptions.DEFAULT)) {
    //        File file = new ClassPathResource("es/mapping/products.json").getFile();
    //        // 读取mapping配置
    //        String jsonStr = IOUtils.toString(file.toURI(), StandardCharsets.UTF_8);
    //
    //        CreateIndexRequest request = new CreateIndexRequest(Constant.ES_INDEX_PRODUCTS);
    //        request.settings(Settings.builder()
    //                .put("index.number_of_shards", 1)
    //                .put("index.number_of_replicas", 1)
    //        );
    //        XContentBuilder builder = XContentFactory.jsonBuilder().map(JsonUtil.parse(jsonStr, HashMap.class));
    //
    //        request.mapping(builder);
    //
    //        client.indices().create(request, RequestOptions.DEFAULT);
    //    }
    //}

}
