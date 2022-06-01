package com.example.demo.config.es.config;

import com.example.demo.util.common.Constant;
import com.example.demo.util.json.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
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
 * es client 配置类已被yml配置代替
 *
 * @author luox
 * @date 2022/5/26
 */
@Configuration
public class RestClientConfig {

    //@Value("es.url")
    //private String esUrl;

    //@Autowired
    //Environment environment;
    @Autowired
    private RestHighLevelClient client;

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

    @PostConstruct
    public void initEsMetadata() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(Constant.ES_INDEX_PRODUCTS);
        // 判断索引是否存在
        if(!client.indices().exists(getIndexRequest, RequestOptions.DEFAULT)) {
            File file = new ClassPathResource("es/mapping/products.json").getFile();
            // 读取mapping配置
            String jsonStr = IOUtils.toString(file.toURI(), StandardCharsets.UTF_8);

            CreateIndexRequest request = new CreateIndexRequest(Constant.ES_INDEX_PRODUCTS);
            request.settings(Settings.builder()
                    .put("index.number_of_shards", 1)
                    .put("index.number_of_replicas", 0)
            );
            XContentBuilder builder = XContentFactory.jsonBuilder().map(JsonUtil.parse(jsonStr, HashMap.class));

            request.mapping(builder);

            client.indices().create(request, RequestOptions.DEFAULT);
        }
    }

}
