package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.util.entity.IdEntity;
import lombok.*;

import java.io.Serializable;

/**
 * 
 *
 * @author luox
 * @since 2023-03-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("cl_detail")
@ToString(callSuper = true)
public class ClDetail extends IdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String 公司名称;

    private String 单据编号;

    private String 单据日期;

    private String 经办人编号;

    private String 经办人;

    private String 审批状态描述;

    private String 利润中心;

    private String 利润中心名称;

    private String 组织单位;

    private String 部门名称;

    private String 费用类型编码;

    private String 费用类型名称;

    private String 收付标识描述;

    private String 是否关联商旅;

    private String 出差目的费用事由;

    private String 特殊说明;

    private String 是否超标;

    private String 制证时间;

    private String 凭证编号;

    private String 冲销反记账凭证过账日期;

    private String 冲销反记账凭证号;

    private String 冲销反记账业务单据号;

    private String 单据行项目;

    private String 费用项目编码;

    private String 费用项目名称;

    private String 报销人编号;

    private String 报销人;

    private String 说明;

    private String 成本中心;

    private String 成本中心描述;

    private String 工作分解结构元素;

    @TableField("WBS描述")
    private String wbs描述;

    private String 住宿城市编码;

    private String 住宿城市名;

    private String 出发地点;

    private String 到达地点;

    private String 出发日期;

    private String 到达日期;

    private String 交通工具;

    private String 交通工具名称;

    private String 仓位席别;

    private String 出差天数;

    private String 按本位币计的金额;

    private String 不含税金额;

    private String 发票税额;

    private String 发票金额;

    private String 是否抵扣;

    private String 扣个税;

    private String 是否首次报销;

    private String 不是首次报销说明;

    private String 销售购买税代码;

    private String 税率;

    private String 文本;

    private String 民航发展基金;

    private String 保险费;

    private String 燃油附加费;

    private String 手续费;

    private String 其他税费;

    private String 城市类别;

    private String 费用标准;

}
