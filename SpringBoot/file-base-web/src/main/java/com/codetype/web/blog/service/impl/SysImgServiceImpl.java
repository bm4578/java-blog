package com.codetype.web.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codetype.web.blog.mapper.SysImgMapper;
import com.codetype.web.blog.entity.SysImg;
import com.codetype.web.blog.service.SysImgService;
import org.springframework.stereotype.Service;

/**
 * (SysImg)表服务实现类
 *
 * @author 白也
 * @since 2023-11-04 16:50:14
 */
@Service("sysImgService")
public class SysImgServiceImpl extends ServiceImpl<SysImgMapper, SysImg> implements SysImgService {

}

