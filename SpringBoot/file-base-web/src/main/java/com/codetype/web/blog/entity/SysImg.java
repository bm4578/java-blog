package com.codetype.web.blog.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * (SysImg)表实体类
 *
 * @author 白也
 * @since 2023-11-04 16:50:14
 */
@SuppressWarnings("serial")
@Data
public class SysImg extends Model<SysImg> {
    //配置自增
    @TableId(type = IdType.AUTO)
    //图片ID
    private Integer imgid;
    //图片URL
    private String imgurl;


    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.imgid;
    }
}

