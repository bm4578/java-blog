package com.codetype.web.blog.service.impl;

import com.codetype.web.blog.entity.SysCarousel;
import com.codetype.web.blog.mapper.SysArticleMapper;
import com.codetype.web.blog.mapper.SysCarouselMapper;
import com.codetype.web.blog.service.SysCarouselService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_carousel】的数据库操作Service实现
* @createDate 2022-04-07 22:02:01
*/
@Service
public class SysCarouselServiceImpl
implements SysCarouselService{
        @Autowired
        private SysCarouselMapper sysCarouselMapper;
    /**
     * 查询轮播图
     *
     * @return
     */
    @Override
    public List<SysCarousel> selectBySysUrl() {
        return sysCarouselMapper.selectBySysUrl();
    }

    /**
     * 新增
     *
     * @param sysCarousel
     * @return
     */
    @Override
    public Boolean addBySysUrl(SysCarousel sysCarousel) {
        return sysCarouselMapper.addBySysUrl(sysCarousel) > 0;
    }

    /**
     * 修改
     *
     * @param sysCarousel
     * @return
     */
    @Override
    public Boolean updateSysUrl(SysCarousel sysCarousel) {
        return sysCarouselMapper.updateSysUrl(sysCarousel) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteBySysUrl(Integer id) {
        return sysCarouselMapper.deleteBySysUrl(id) > 0;
    }

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    @Override
    public List<SysCarousel> selectBySysId(Integer id) {
        return sysCarouselMapper.selectBySysId(id);
    }
}
