package com.example.demo.util.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 请求body基础vo
 *
 * @author luox
 * @date 2022/01/26
 */
@Data
public class BaseBodyVo {

    /**
     * ids id集合
     */
    @NotEmpty(message = "ids不能为空")
    private List<Long> ids;
}
