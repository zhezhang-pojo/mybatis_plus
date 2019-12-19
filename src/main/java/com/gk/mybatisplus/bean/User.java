package com.gk.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2019-12-18 12:26
 */
@Data
public class User {
    //@TableId(type = IdType.AUTO)设置主键自增策略
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //自动填充时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Version//添加版本控制
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
