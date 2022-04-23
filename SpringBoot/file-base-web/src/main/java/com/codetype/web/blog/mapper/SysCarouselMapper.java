package com.codetype.web.blog.mapper;

import com.codetype.web.blog.entity.SysCarousel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_carousel】的数据库操作Mapper
* @createDate 2022-04-07 22:02:01
* @Entity com.codetype.web.blog.entity.SysCarousel
*/
@Mapper
public interface SysCarouselMapper {
    /**
     * 查询轮播图
     * @return
     */
    List<SysCarousel> selectBySysUrl();

    /**
     * 新增
     * @param sysCarousel
     * @return
     */
    Integer addBySysUrl(SysCarousel sysCarousel);

    /**
     * 修改
     * @param sysCarousel
     * @return
     */
    Integer updateSysUrl(SysCarousel sysCarousel);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteBySysUrl(Integer id);

    /**
     * 按id查询
     * @param id
     * @return
     */
    List<SysCarousel> selectBySysId(Integer id);
}
