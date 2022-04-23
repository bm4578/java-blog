package com.codetype.web.blog.mapper;

import com.codetype.web.blog.entity.SysCarousel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SysCarouselMapperTest {
    SysCarousel sysCarousel = new SysCarousel();
@Autowired
private SysCarouselMapper sysCarouselMapper;
    @Test
    void selectBySysUrl() {
        List<SysCarousel> sysCarousels = sysCarouselMapper.selectBySysUrl();
        System.out.println(sysCarousels);
    }

    @Test
    void addBySysUrl() {
        SysCarousel sysCarousel = new SysCarousel();
        sysCarousel.setSysUrl("slkfjldkfjlkdsjflkd");
        Integer integer = sysCarouselMapper.addBySysUrl(sysCarousel);
        System.out.println(integer);
    }

    @Test
    void updateSysUrl() {
        sysCarousel.setSysId(1);
        sysCarousel.setSysUrl("代理费独立思考");
        Integer integer = sysCarouselMapper.updateSysUrl(sysCarousel);
        System.out.println(integer);
    }

    @Test
    void deleteBySysUrl() {
        Integer integer = sysCarouselMapper.deleteBySysUrl(2);
        System.out.println(integer);
    }

    @Test
    void selectBySysId() {
        List<SysCarousel> sysCarousels = sysCarouselMapper.selectBySysId(1);
        System.out.println(sysCarousels);
    }
}