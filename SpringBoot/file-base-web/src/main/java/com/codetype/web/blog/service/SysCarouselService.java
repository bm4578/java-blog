package com.codetype.web.blog.service;

import com.codetype.web.blog.entity.SysCarousel;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_carousel】的数据库操作Service
* @createDate 2022-04-07 22:02:01
*/
public interface SysCarouselService {
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
    Boolean addBySysUrl(SysCarousel sysCarousel);
    /**
     * 修改
     * @param sysCarousel
     * @return
     */
    Boolean updateSysUrl(SysCarousel sysCarousel);
    /**
     * 删除
     * @param id
     * @return
     */
    Boolean deleteBySysUrl(Integer id);
    /**
     * 按id查询
     * @param id
     * @return
     */
    List<SysCarousel> selectBySysId(Integer id);

}
