package com.example.demo.config.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;

/**
 * es client 配置类
 *
 * @author luox
 * @date 2022/5/26
 */
@ConditionalOnProperty(value = "spring.elasticsearch.enabled",havingValue = "true")
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
        //配置JacksonJson
        JacksonJsonpMapper jacksonJsonpMapper = new JacksonJsonpMapper();
        ObjectMapper mapper = jacksonJsonpMapper.objectMapper();
        //logstash同步数据会产生的type关键字字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //日期不转为时间戳
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        ElasticsearchTransport transport = new RestClientTransport(restClient,jacksonJsonpMapper);

        return new ElasticsearchClient(transport);
        //new ElasticsearchAsyncClient(transport);
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
