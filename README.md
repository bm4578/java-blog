# 博客项目

#### 介绍
本项目后端基于springboot+springsecurity+jwt +mysql8.0+redis ，前端基于vue2脚手架+elementUI+vuetify+wangEditor5富文本编辑器 。本项目采用后端聚合工程，前后端分离。灵感参考与国内各个博客论坛，目前已经实现普通用户对文章的编辑与修改，管理员对普通用户的提权与对文章的编辑与删除。

### 测试账号
管理员：
1. 账号：admin 
2. 密码：1234

普通账号:
1. 账号：codetype 
2. 密码：1234
3. 可以自己注册
### SpringBoot
1. 首先导入数据库
2. 分别启动Redis和MySQL服务
   <img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jued4tn3jqe6l3gkryorqqkgeioa/demo-bucket/redis.png?wrap=0" style="display: block;">

3. 然后添加maven工程，并进入SpringBoot/file-base-web/src/main/resources/application-test.yml 中修改自己的数据库名和redis服务
   <img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jwmy3tzstbgnzncuqgvndixqgh5q/demo-bucket/maven.png?wrap=0" style="display: block;">
4. 然后启动springboot项目 ，在浏览器输入http://localhost:8089/doc.html
   <img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jxz3tp2v77dng3uexa2mzmvdbeea/demo-bucket/%E5%90%8E%E7%AB%AF.png?wrap=0" style="display: block;">
### Vue
1. 进入terminal输入npm install
   <img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jxb527ay5bkm3bduwpwrxcsaradq/demo-bucket/%E5%89%8D%E7%AB%AF%E8%BF%90%E8%A1%8C.png?wrap=0" style="display: block;">
2. 然后浏览器输入http://localhost:3002
   <img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jvyhcmq4xauagehwwabaamjggoqq/demo-bucket/%E5%89%8D%E7%AB%AF%E6%95%88%E6%9E%9C%E5%9B%BE%201.png?wrap=0" style="display: block;">
后端效果图  <br/>
<img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jxz3tp2v77dng3uexa2mzmvdbeea/demo-bucket/%E5%90%8E%E7%AB%AF.png?wrap=0" style="display: block;">
前端效果图  <br/>
<img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jvyhcmq4xauagehwwabaamjggoqq/demo-bucket/%E5%89%8D%E7%AB%AF%E6%95%88%E6%9E%9C%E5%9B%BE%201.png?wrap=0" style="display: block;">
<img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jul7ghfqpojfokn4wxuofbnhd7qa/demo-bucket/%E5%89%8D%E7%AB%AF%E6%95%88%E6%9E%9C%E5%9B%BE2.png?wrap=0" style="display: block;">
<img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/jxhugydvjgvtynpnyrfa7s4hexoq/demo-bucket/%E6%95%88%E6%9E%9C%E5%9B%BE3.png?wrap=0" style="display: block;">

<h2>有问题可以联系作者一起学习(在校大学生一枚)</h2>

<img class="embed-responsive embed-responsive-4by3" id="imgTag" alt="预览图像" src="https://link.ap1.storjshare.io/s/juwy4mzbcirai2jmektu3x6dlzwq/demo-bucket/T.png?wrap=0" style="display: block;">


 

