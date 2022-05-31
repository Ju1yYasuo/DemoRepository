package com.example.demo.config.es.entity;

import com.example.demo.config.es.config.FieldAnalyzer;
import com.example.demo.controller.EsController;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.completion.Completion;

import java.util.Date;

/**
 * 产品
 *
 * @author luox
 * @date 2022/5/26
 */
@Document(indexName = EsController.INDEX_NAME)
@Setting(
        shards = 1, // 默认索引分区数
        replicas = 0, // 每个分区的备份数
        refreshInterval = "-1" // 刷新间隔
)
@Mapping(mappingPath = "/es/mapping/products.json")
@Data
public class Products {

    /**
     * id
     * settingMapper如果没有走外部文件配置
     * 那么只要fieldName为id或document就判定为是id属性，然后将type设置为keyword并且可被索引
     */
    @Field(type = FieldType.Long)
    private Long id;

    /**
     * 标题
     * 只有text类型才可以被分词
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Text)
    private String title;

    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    private Double price;

    /**
     * 创建日期
     * 转Date类型仅支持默认格式，否则使用LocalDate类型,而毫秒值不支持该类型
     * name = "create_time"
     */
    @Field(type = FieldType.Date)
    private Date createTime;

    /**
     * 描述
     */
    @Field(analyzer = FieldAnalyzer.IK_SMART, type = FieldType.Text)
    private String description;

    /**
     * 搜索建议
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD)
    private Completion suggest;

    ///**
    // * 位置,GEO类型的字段是不能使用动态映射自动生成
    // */
    //private GeoPoint location;
}
