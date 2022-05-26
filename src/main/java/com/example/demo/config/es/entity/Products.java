package com.example.demo.config.es.entity;

import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.example.demo.config.es.config.FieldAnalyzer;
import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.core.completion.Completion;

import java.util.Date;

/**
 * 产品
 *
 * @author luox
 * @date 2022/5/26
 */
@Document(indexName = "products")
@Setting(
        shards = 1, // 默认索引分区数
        replicas = 0, // 每个分区的备份数
        refreshInterval = "-1" // 刷新间隔
)
@Data
public class Products {

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Keyword)
    private String title;

    /**
     * 价格
     */
    private Double price;

    /**
     * 创建在
     */
    private Date create_at;

    /**
     * 描述
     */
    @Field(analyzer = FieldAnalyzer.IK_SMART, type = FieldType.Text)
    private String description;

    /**
     * 建议
     */
    private Completion suggest;

    /**
     * 位置
     */
    private GeoPoint location;
}
