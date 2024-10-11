package com.asiainfotest.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author oyd
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_param_config")
public class TdParamConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数编码
     */
    @TableField("param_id")
    private Integer paramId;

    /**
     * 参数名称
     */
    @TableField("param_name")
    private String paramName;

    /**
     * 参数值
     */
    @TableField("param_value")
    private String paramValue;

    /**
     * 参数值描述
     */
    @TableField("param_desc")
    private String paramDesc;

    /**
     * 状态
     */
    @TableField("state")
    private String state;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;


    public static final String ID = "id";

    public static final String PARAM_ID = "param_id";

    public static final String PARAM_NAME = "param_name";

    public static final String PARAM_VALUE = "param_value";

    public static final String PARAM_DESC = "param_desc";

    public static final String STATE = "state";

    public static final String REMARK = "remark";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

}
