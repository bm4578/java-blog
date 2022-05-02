package com.codetype.config.web;

import com.codetype.web.file.controller.FileController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class InitProject implements ApplicationRunner{

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Value("${server.port}")
    private String port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        String url = "http://"+localHost.getHostAddress()+":"+port;
        log.warn("服务器端口:  " +url);
        log.warn("接口文档地址:  " +url+"/doc.html");
    }
}
