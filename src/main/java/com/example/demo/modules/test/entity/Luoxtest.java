package com.example.demo.modules.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author luox
 * @since 2021-06-15
 */
@Data
//Data默认包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true)
public class Luoxtest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;


//    /**
//     * 创建时间
//     */
//    @TableField(fill = FieldFill.INSERT)
//    private Date createTime;
//
//    /**
//     * 最后修改时间
//     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Date updateTime;


    /**
     * 逻辑删除（0 未删除、1 删除）
     * 去除 TableLogic 注解，再执行 testDelete 时进行物理删除，直接删除这条数据
     */
//    @TableLogic(value = "0", delval = "1")
//    @TableField(fill = FieldFill.INSERT)
//    private Integer deleteFlag;

    /**
     * 版本号（用于乐观锁， 默认为 1）
     */
//    @Version
//    @TableField(fill = FieldFill.INSERT)
//    private Integer version;

}
