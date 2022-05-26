package com.example.demo.config.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @author luox
 * @date 2022/5/26
 */
//@Configuration
public class RestClientConfig {

    //@Value("es.url")
    //private String esUrl;

    //@Autowired
    //Environment environment;

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

}
