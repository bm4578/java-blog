package com.codetype.web.blog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.codetype.web.blog.entity.SysImg;

/**
 * (SysImg)表数据库访问层
 *
 * @author 白也
 * @since 2023-11-04 16:50:14
 */

@Mapper
public interface SysImgMapper extends BaseMapper<SysImg> {

}

