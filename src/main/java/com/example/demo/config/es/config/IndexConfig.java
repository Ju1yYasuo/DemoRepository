package com.example.demo.config.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.example.demo.util.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 索引配置
 *
 * @author luox
 * @date 2022/6/9
 */
@Component
@Slf4j
public class IndexConfig {

    @Autowired
    private ElasticsearchClient client;

    /**
     * 初始化索引
     *
     * @throws IOException ioexception
     * @author luox
     * @date 2022/06/09
     */
    @PostConstruct
    public void initIndex() throws IOException {
        // 判断索引是否存在
        if(!client.indices().exists(builder -> builder.index(Constant.ES_INDEX_PRODUCTS)).value()) {
            Map<String, Property> documentMap = new HashMap<>();
            documentMap.put("id",Property.of(p -> p.long_(xp -> xp.index(true))));

            documentMap.put("userId",Property.of(p -> p.keyword(xp -> xp.index(false))));
            documentMap.put("userName",Property.of(p -> p.keyword(xp -> xp.index(false))));
            documentMap.put("orgId",Property.of(p -> p.keyword(xp -> xp.index(false))));
            documentMap.put("orgName",Property.of(p -> p.keyword(xp -> xp.index(false))));

            documentMap.put("kType",Property.of(p -> p.keyword(xp -> xp.index(true))));

            documentMap.put("title",Property.of(p -> p.text(xp -> xp.analyzer(FieldAnalyzer.IK_MAX_WORD)
                    .fields("suggest",sp -> sp.completion(cp -> cp.analyzer(FieldAnalyzer.IK_MAX_WORD))))));
            documentMap.put("content",Property.of(p -> p.text(xp -> xp.analyzer(FieldAnalyzer.IK_SMART))));

            documentMap.put("createTime",Property.of(p -> p.date(xp -> xp.index(true).format("date_optional_time||epoch_millis"))));
            documentMap.put("updateTime",Property.of(p -> p.date(xp -> xp.index(true).format("date_optional_time||epoch_millis"))));

            documentMap.put("attachment",Property.of(p -> p.keyword(xp -> xp.index(false))));
            documentMap.put("attachmentType",Property.of(p -> p.keyword(xp -> xp.index(true))));

            CreateIndexResponse createIndexResponse = client.indices().create(builder -> builder.index(Constant.ES_INDEX_PRODUCTS)
                    .settings(s -> s.numberOfShards("1").numberOfReplicas("0"))
                    .mappings(m -> m.properties(documentMap)));
            if(Boolean.TRUE.equals(createIndexResponse.acknowledged())){
                log.info(Constant.ES_INDEX_PRODUCTS + "索引创建成功。");
            }else{
                log.error(Constant.ES_INDEX_PRODUCTS + "索引创建失败。");
            }
        }
    }

}
