package com.example.demo.util.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * minio 返回对象vo
 *
 * @author luox
 * @date 2022/5/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutObjectRespVo {

    /**
     * 桶
     */
    private String bucket;

    /**
     * 对象名称
     */
    private String objectName;

    /**
     * url
     */
    private String url;
}
