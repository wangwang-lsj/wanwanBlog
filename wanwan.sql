/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : wanwan

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 01/05/2024 22:21:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文章标题',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '文章内容',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签',
  `author_id` int(11) NULL DEFAULT NULL COMMENT '作者ID',
  `public_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布日期',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `read_count` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `likes` int(11) NULL DEFAULT 0 COMMENT '喜欢数',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `home_show` tinyint(4) NULL DEFAULT 0 COMMENT '是否主页展示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章(博客）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '跨域问题', '前后端分离项目，跨域问题是跨不过去的。', '<h2 style=\"text-align: start;\">一.为什么会产生跨域问题？</h2><p style=\"text-align: start;\"> &nbsp; &nbsp; 出于浏览器的同源策略限制。同源策略（Sameoriginpolicy）是一种约定，它是浏览器最核心也最基本的安全功能，如果缺少了同源策略，则浏览器的正常功能可能都会受到影响。所谓同源（即指在同一个域）就是两个页面具有相同的协议（protocol），主机（host）和端口号（port）。</p><p style=\"text-align: start;\"> &nbsp; &nbsp;也就是当一个请求的url的协议、域名、端口三者只要有一个与当前页面的url不同则会出现跨域问题</p><h2 style=\"text-align: start;\">二.解决方案</h2><p style=\"text-align: start;\"> &nbsp; &nbsp;我就聊一下我的解决方案吧，非常简单，只需要在服务端新建一个config文件夹在创建一个CorsConfig的配置类，内容如下：</p><pre><code class=\"language-java\">package com.wanwan.springboot.config;\n\nimport org.springframework.context.annotation.Bean;\nimport org.springframework.context.annotation.Configuration;\nimport org.springframework.web.cors.CorsConfiguration;\nimport org.springframework.web.cors.UrlBasedCorsConfigurationSource;\nimport org.springframework.web.filter.CorsFilter;\n\n/**\n * @Author：玩玩\n * @Date：2024/1/18 15:27\n * @Description:\n */\n@Configuration\npublic class CorsConfig {\n    private static final long MAX_AGE = 24 * 60 *60;\n    @Bean\n    public CorsFilter corsFilter() {\n        CorsConfiguration corsConfiguration = new CorsConfiguration();\n        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();\n        corsConfiguration.addAllowedOrigin(\"*\");\n        corsConfiguration.addAllowedHeader(\"*\");\n        corsConfiguration.addAllowedMethod(\"*\");\n        corsConfiguration.setMaxAge(MAX_AGE);\n        // corsConfiguration.setAllowCredentials(true);\n\n        source.registerCorsConfiguration(\"/**\", corsConfiguration);\n        return new CorsFilter(source);\n    }\n}\n</code></pre><p><br></p>', 'http://localhost:9090/api/file/8e4bbc71f3ec484fa974c1911f2b67fb.jpg', '[\"后端\",\"前端\"]', 2, '2024-03-09 20:27:05', '2024-05-01 20:17:59', 1375, 103, 1, 1);
INSERT INTO `article` VALUES (7, 'Hutool介绍', 'Hutool是一个小而全的Java工具类库，通过静态方法封装，降低相关API的学习成本，提高工作效率，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。', '<h3>简介</h3><p>中文官网：<a href=\"https://plus.hutool.cn/docs/\" target=\"_blank\"><span style=\"color: rgb(54, 88, 226);\"><u>https://plus.hutool.cn/docs/</u></span></a></p><p>Hutool是一个小而<u>全的Java工具类库，通过静态方法封装，降低相关API的学习成本，提高工作效率，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。</u></p><p>Hutool的目标是使用一个工具方法代替一段复杂代码，从而最大限度的避免“复制粘贴”代码的问题，彻底改变我们写代码的方式。</p><h3>包含组件</h3><table style=\"width: auto;\"><tbody><tr><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\">模块	</th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\">介绍</th></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-aop</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">JDK动态代理封装，提供非IOC下的切面支持</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-bloomFilter</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">布隆过滤，提供一些Hash算法的布隆过滤</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-cache</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">简单缓存实现</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-core</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">核心，包括Bean操作、日期、各种Util等</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-cron</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">定时任务模块，提供类Crontab表达式的定时任务</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-crypto</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">加密解密模块，提供对称、非对称和摘要算法封装</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-db</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">JDBC封装后的数据操作，基于ActiveRecord思想</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-dfa</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">基于DFA模型的多关键字查找</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-extra</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">扩展模块，对第三方封装（模板引擎、邮件、Servlet、二维码、Emoji、FTP、分词等）</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-http	</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">基于HttpUrlConnection的Http客户端封装</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-log</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">自动识别日志实现的日志门面</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-script</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">脚本执行封装，例如Javascript</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-setting	</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">功能更强大的Setting配置文件和Properties封装</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-system</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">系统参数调用封装（JVM信息等）</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-json</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">JSON实现</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-captcha</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">图片验证码实现</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-poi	</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">针对POI中Excel和Word的封装</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-socket</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">基于Java的NIO和AIO的Socket封装</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">hutool-jwt</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">JSON Web Token (JWT)封装实现</td></tr></tbody></table><h3>使用</h3><pre><code >&lt;dependency&gt;\n        &lt;groupId&gt;cn.hutool&lt;/groupId&gt;\n        &lt;artifactId&gt;hutool-all&lt;/artifactId&gt;\n        &lt;version&gt;5.8.0.M4&lt;/version&gt;\n&lt;/dependency&gt;\n</code></pre><p><br></p>', 'http://localhost:9090/api/file/825430ac8105443ab50b6cf5bd6be8b7.jpg', '[\"后端开发工具\"]', 2, '2024-03-10 15:32:45', '2024-04-24 23:24:04', 111, 1, 1, 1);
INSERT INTO `article` VALUES (27, 'Springboot介绍', 'SpringBoot是一个开发基于Spring框架的应用的快速开发框架，它也是SpringCloud构建微服务分布式系统的基础设施。', '<h2 style=\"text-align: start;\">1、什么是SpringBoot?</h2><p style=\"text-align: start;\">SpringBoot是一个开发基于Spring框架的应用的快速开发框架，它也是SpringCloud构建微服务分布式系统的基础设施。</p><h2 style=\"text-align: start;\">2、SpringBoot有哪些主要特性？</h2><p style=\"text-align: start;\"> &nbsp; &nbsp;SpringBoot的主要特色包括构建独立的Spring应用、嵌入式的Web容器、固化的starter依赖、自动装配Spring模块或第三方库、产品就绪特性（日志、指标、健康检查、外部化配置等）、避免或简化XML配置等特性。</p><h3 style=\"text-align: start;\">2.1可独立运行的Spring应用</h3><p style=\"text-align: start;\"> &nbsp; &nbsp;相对于普通的Spring应用，使用SpringBoot构建的Spring应用可以直接打包为一个独立可执行的jar或war包，使用java -jar命令即可运行，不需要管理依赖的第三方库，也不需要依赖外部容器来启动应用。之前使用Spring开发的Java Web应用，一般都会在第三方的Web容器中启动，比如Tomcat等，而使用SpringBoot开发的Java Web应用，虽然基于Spring，但它提供了内嵌的Web容器（基于Servlet或Reactive的Web容器，如Tomcat、Jetty、Undertow、NettyWebServer），通过SpringBoot插件，把所有依赖的第三方库、Web容器和应用本身一起重新打包（repackage）为一个Fat Jar或Fat War，然后直接使用java -jar命令运行即可。</p><h3 style=\"text-align: start;\">2.2嵌入式Web容器</h3><p style=\"text-align: start;\"> &nbsp; &nbsp;SpringBoot内置了多种嵌入式Web容器，包括Tomcat、Jetty、Undertow、NettyWebServer等，用于运行基于Servlet或Reactive的Web应用，无需再打包部署WAR文件，即不需要依赖外部的Web容器部署。</p><h3 style=\"text-align: start;\">2.3固化的starter依赖</h3><p style=\"text-align: start;\"> &nbsp; &nbsp;SpringBoot提供了一系列的starter依赖，用于快速的引入应用依赖的Spring模块及相关的第三方库。不同版本的SpringBoot，与其依赖的Spring模块及其相关的第三方库的版本关系是固定的，一一对应的。开发人员不需要关注依赖的Spring模块和第三方库的版本，只需要关心SpringBoot的版本即可，SpringBoot会正确引入依赖的Spring模块和第三方库的版本。固化依赖，可以降低SpringBoot应用管理依赖的成本。</p><h3 style=\"text-align: start;\">2.4自动装配Spring模块或第三方库</h3><p style=\"text-align: start;\"> &nbsp; &nbsp;SpringBoot启动应用时，会推断应用类型，并检测引入的Spring模块或第三方库，当条件满足时自动加载Spring模块或第三方库的组件到容器中，以提供给应用使用。</p><h3 style=\"text-align: start;\">2.5产品就绪特性</h3><p style=\"text-align: start;\"> &nbsp; &nbsp;SpringBoot提供了应用部署产品环境运行所必须的日志、指标、健康检查、外部化配置等特性，为部署后的运维提供工具支持，支撑应用尽可能快的部署到产品环境。</p><h3 style=\"text-align: start;\">2.6避免或简化配置</h3><p style=\"text-align: start;\"> &nbsp; &nbsp;不会额外生成代码，并且可以简化甚至不需要xml或properties文件配置，即可快速开发Spring应用。</p><h2 style=\"text-align: start;\">3、Spring、SpringBoot、SpringCloud有什么区别？</h2><p style=\"text-align: start;\"> &nbsp; &nbsp;以前说到Spring，一般指Spring框架（SpringFramework），它是一个开源、轻量级的Java应用开发框架。其核心是控制 &nbsp; &nbsp;反转IOC和面向切面编程AOP。Spring提供了很多包括ORM、事务管理、WebMVC等非常有用的模块构建Java应用。</p><p style=\"text-align: start;\"> &nbsp; &nbsp;SpringBoot则是在Spring基础之上，用于快速构建Spring应用的一个框架，它并不是要取代Spring，而是基于Spring的。</p><p style=\"text-align: start;\"> &nbsp; &nbsp;SpringCloud是一个分布式微服务系统的开发框架，SpringBoot则是SpringCloud的基础设施。三者之间都不是取代的关系，而是一种倒三角的依赖关系，顶层是SpringCloud，中间层是SpringBoot，底层是Spring。</p>', 'http://localhost:9090/api/file/aba92a0df1ee4da6b4d47632c262ffc8.jpg', '[\"后端开发\"]', 2, '2024-03-10 21:52:05', '2024-04-30 16:25:47', 339, 1, 1, 1);
INSERT INTO `article` VALUES (28, 'Vue简介', 'Vue是一套用于构建用户界面的前端框架。', '<h3 style=\"text-align: start;\">一. 由来：</h3><p style=\"text-align: start;\">引入vue作者尤雨溪的话：</p><p style=\"text-align: start;\"> &nbsp; &nbsp; &nbsp; &nbsp;“Vue 一开始完全是一个个人兴趣项目。2013 年的时候我还在 Google Creative Lab，那时候前端框架还处于比较草莽的阶段，React 刚刚发布还没几个人知道，最成熟的是 AngularJS (Angular 1)。我当时一方面是想自己实现一个简单的框架练练手，另一方面是想尝试一下用 ES5 的 Object.defineProperty 实现数据变动侦测。众所周知 AngularJS 使用的是脏检查，而当时大部分的应用还需要支持 IE8，所以不能全面使用 ES5，而个人项目则不需要考虑这些。Vue 就是这样作为一个实验性质的项目开始的。\"</p><p style=\"text-align: start;\">发展历程：</p><p style=\"text-align: start;\"> &nbsp; &nbsp; &nbsp; &nbsp;从最初的实验阶段（2013 年中到 2014 年 2 月），0.x 阶段 (2014 年 2 月到 2015 年 10 月)，1.x 阶段 (2015 年 10 月到 2016 年 9 月)，到现在的2.x 阶段 (2016 年 9 月至今)，加上正式对外发布之前的时间，到今天已经有 足足5 年多的时间了。 0.x - 1.0 的改动主要集中在模版语法上，在 1.0 之后，模版语法就相对稳定，没有再经历过特别大的改动了。2.0 的改动则专注于内部的渲染机制变化，引入了 Virtual DOM，从而获得了服务端渲染、原生渲染、手写渲染函数等能力。目前 3.0 正在竭力的开发过程中，主要集中于利用 ES2015 的新特性、改进内部架构和性能优化上。</p><h3 style=\"text-align: start;\">二. vue特性：</h3><h4 style=\"text-align: start;\"><strong>2.1vue四大特点：</strong></h4><blockquote style=\"text-align: start;\"><span style=\"background-color: rgb(255, 255, 255); font-size: 16px;\">jsvaScript渐进式、易用、灵活、高效 &nbsp;官方文档：</span><a href=\"https://v2.cn.vuejs.org/\" target=\"\" style=\"text-align: start;\"><span style=\"color: rgb(54, 88, 226); background-color: rgb(255, 255, 255);\">Vue.js</span></a></blockquote><p style=\"text-align: start;\"><img src=\"http://localhost:9090/api/file/1e100d73592142bb906731a4c28810cd.png\" alt=\"null\" data-href=\"http://localhost:9090/api/file/1e100d73592142bb906731a4c28810cd.png\" style=\"\"></p><h4 style=\"text-align: start;\"><strong>2.2javaScript渐进式框架介绍：</strong></h4><ul><li style=\"text-align: start;\">a. Vue.js提供了很多的功能，但是这些功能并不是集成在一起的，而是分开的</li><ul><li style=\"text-align: start;\">类似于Nodejs,拆分成很多个小模块</li></ul><li style=\"text-align: start;\">b. 在项目中我们用到什么，就导入什么</li><ul><li style=\"text-align: start;\">这样可以保证我们用到的最少</li></ul><li style=\"text-align: start;\">c. 说人话：Vue相当于是一个五星级自助餐，我们想要用什么就拿什么，这样可以最大限度避免浪费资源</li></ul><h4 style=\"text-align: start;\"><strong>2.3vue设计模式：</strong></h4><p style=\"text-align: start;\">vue是MVVM设计模式的框架。</p><ul><li style=\"text-align: start;\">MVVM设计模式：一种软件架构模式，决定了写代码的方式。</li><ul><li style=\"text-align: start;\">M：model数据模型(ajax获取到的数据)</li><li style=\"text-align: start;\">V：view视图（页面）</li><li style=\"text-align: start;\">VM：ViewModel 视图模型(vue实例)</li></ul><li style=\"text-align: start;\">MVVM通过数据双向绑定让数据自动地双向同步 不在需要操作DOM</li><ul><li style=\"text-align: start;\">V（修改视图） -&gt; M（数据自动同步）</li><li style=\"text-align: start;\">M（修改数据） -&gt; V（视图自动同步）</li></ul></ul><h3 style=\"text-align: start;\">三.vue全家桶</h3><h4 style=\"text-align: start;\">3.1 vue-cli</h4><p style=\"text-align: start;\">1.官方介绍：</p><p style=\"text-align: start;\">Vue CLI 是一个用于快速 Vue.js 开发的完整系统，提供：</p><ul><li style=\"text-align: start;\">交互式项目脚手架通过 .@vue/cli</li><li style=\"text-align: start;\">运行时依赖项 （），即：@vue/cli-service</li><ul><li style=\"text-align: start;\">可升级;</li><li style=\"text-align: start;\">构建在 webpack 之上，具有合理的默认值;</li><li style=\"text-align: start;\">可通过项目内配置文件进行配置;</li><li style=\"text-align: start;\">可通过插件扩展</li></ul><li style=\"text-align: start;\">丰富的官方插件集合，集成了前端生态系统中最好的工具。</li><li style=\"text-align: start;\">一个完整的图形用户界面，用于创建和管理 Vue.js 项目。</li></ul><p style=\"text-align: start;\">Vue CLI 旨在成为 Vue 生态系统的标准工具基线。它确保各种构建工具与合理的默认值一起顺利运行，因此您可以专注于编写应用程序，而不是花费数天时间争论配置。同时，它仍然提供了调整每个工具配置的灵活性，而无需弹出。</p><blockquote style=\"text-align: start;\">大白话：我们专注于框架中的业务与数据的处理即可，用cli会自动帮我们处理默认的配置与用Cli还可以通过插件可扩展我们的项目中功能</blockquote><h4 style=\"text-align: start;\"><strong>3.2 vue-router</strong></h4><p>1.路由是什么？</p><p><span style=\"color: rgb(77, 77, 77); background-color: rgb(255, 255, 255); font-size: 16px;\">路由中的页面：主要指vue的组件（在vue中 页面 和 盒子 都是 组件）</span></p><p style=\"text-align: start;\"><strong>2.为什么要学习vue中的路由</strong></p><p style=\"text-align: start;\">vue是一个渐进式框架，一个网页由很多个页面组成，那我页面之间如何跳转呢？</p>', 'http://localhost:9090/api/file/1257494427f0435da4eca81f9698354e.png', '[\"Vue\"]', 2, '2024-03-11 21:28:39', '2024-04-30 16:25:42', 57, 1, 2, 1);
INSERT INTO `article` VALUES (29, 'IDEA快捷键', '我的IDEA常用快捷键', '<h1 style=\"text-align: start;\">IDEA快捷键</h1><table style=\"width: auto; text-align: left;\"><tbody><tr><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">ctrl+enter</th><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">到下一行</th></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">ctrl+左键</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">跳转到方法</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">ctrl+p</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">查看方法可以用哪些参数</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">ctrl+alt+L</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">让代码整齐</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">ctrl+shift+alt+L</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">让代码整齐</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">shift+enter</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">让光标到下一行</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">ctrl+w</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">选中这个单词</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">shift+alt+鼠标去选中</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">选中的地方可以同时更改</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">ctrl+alt+o</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">去除无用的包</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">tab+选中代码</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">向右</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">shift+tab+选中代码</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">向左</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">alt+enter</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">快速生成接收对象</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">ctrl+alt+t</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: left;\">生成模板</td></tr></tbody></table><p><br></p>', 'http://localhost:9090/api/file/c2dec9dc00c14e6dbbe3f218e5dc5ea7.jpg', '[\"便捷\"]', 2, '2024-03-11 21:28:52', '2024-04-24 17:51:32', 32, 1, 5, 1);
INSERT INTO `article` VALUES (30, '阿里云服务器部署', '将springboot项目和vue项目分开打包部署在linux服务器上', '<h3>一、前后端不分离SpringBoot项目部署</h3><h4>1、配置阿里云服务器</h4><h5>1.1、购买服务器</h5><p>阿里云官网 https://www.aliyun.com/</p><p>注册阿里云账号（自行注册）</p><p>阿里云购买教程 https://yq.aliyun.com/articles/699313</p><p>公网IP 私网IP</p><h5>1.2、购买域名</h5><p>进入阿里云官网 https://www.aliyun.com/，点击 “域名”</p><p>搜索你想要的域名进行购买；</p><h5>1.3、备案</h5><p>进入阿里云官网 https://www.aliyun.com/&gt;</p><p>进入“控制台”</p><p>进入备案 阿里云域名首次备案教程 https://www.yunqikan.cn/2255.html</p><h5>1.4、域名解析</h5><p>域名已经备案完成</p><p>进入阿里云官网 https://www.aliyun.com/</p><p>进入控制台</p><p>点击左侧弹出栏中域名</p><p>点击解析添加记录按照步骤进行解析</p><p><br></p><h4 style=\"text-align: start;\">2、宝塔面板相关</h4><h5 style=\"text-align: start;\">2.1、安装CentOS宝塔面板</h5><p style=\"text-align: start;\">远程连接个人阿里服务器</p><p style=\"text-align: start;\">进入宝塔官网 www.bt.cn，进行下载：</p><pre><code class=\"language-bash\">yum install -y wget && wget -O install.sh http://download.bt.cn/install/install_6.0.sh && sh install.sh\n</code></pre><p><span style=\"color: rgb(77, 77, 77); background-color: rgb(255, 255, 255); font-size: 16px;\">运行命令后，宝塔面板会自动安装，期间你需要输入一个y，如下所示：</span></p><pre><code class=\"language-bash\">Do you want to install Bt-Panel to the /www directory now?(y/n):y</code></pre><p style=\"text-align: start;\">输入y，然后回车即可。<br>等待，大概2分钟左右，显示“Complete!”，即安装完毕！</p><p style=\"text-align: start;\">注意:下载完成会出现有下面这样一段内容:\"(一定要先复制保存下来)\"</p><pre><code class=\"language-bash\">Complete!\nCreated symlink /etc/systemd/system/dbus-org.fedoraproject.FirewallD1.service → /usr/lib/systemd/system/firewalld.service.\nCreated symlink /etc/systemd/system/multi-user.target.wants/firewalld.service → /usr/lib/systemd/system/firewalld.service.\nsuccess\n==================================================================\nCongratulations! Installed successfully!\n==================================================================\n外网面板地址: http://公网IP:8888/335fc27a\n内网面板地址: http://172.27.86.45:8888/335fc27a\nusername: oild6mqk\npassword: 54922e3f\nIf you cannot access the panel,\nrelease the following panel port [8888] in the security group\n若无法访问面板，请检查防火墙/安全组是否有放行面板[8888]端口\n==================================================================\nTime consumed: 1 Minute!\n</code></pre><pre><code class=\"language-bash\">Bt-Panel: http://公网ip:8888/随机安全入口\nusername: 随机用户名\npassword: 随机登录密码\n</code></pre><p><br></p>', 'http://localhost:9090/api/file/38492c20405f488fba08a82f20366ffe.jpg', '[\"linux\",\"宝塔\"]', 2, '2024-03-11 21:29:05', '2024-04-24 23:23:51', 41, 0, 3, 1);
INSERT INTO `article` VALUES (31, 'test5', 'test1', '<p>test1</p>', NULL, '[\"Element\"]', 2, '2024-03-11 21:29:15', '2024-04-24 13:09:56', 14, 0, 5, 0);
INSERT INTO `article` VALUES (32, 'test6', 'test1', '<p>test1</p>', NULL, '[\"Element\"]', 2, '2024-03-11 21:29:25', '2024-04-24 13:09:50', 18, 0, 5, 0);
INSERT INTO `article` VALUES (33, 'test7', 'test1', '<p>test1</p>', NULL, '[\"Axios\"]', 2, '2024-03-11 21:29:38', '2024-04-19 20:08:42', 18, 0, 6, 0);
INSERT INTO `article` VALUES (34, 'test8', 'test1', '<p>test1</p>', NULL, '[\"Vue\"]', 2, '2024-03-11 21:29:46', '2024-04-22 00:18:42', 11, 0, 6, 0);
INSERT INTO `article` VALUES (35, 'test9', 'test1', '<p>test1</p>', NULL, '[\"Element\"]', 2, '2024-03-11 21:29:54', '2024-04-24 16:48:07', 15, 0, 6, 0);
INSERT INTO `article` VALUES (36, 'test10', 'test1', '<p>test1</p>', NULL, '[\"Axios\"]', 2, '2024-03-11 21:30:00', '2024-04-24 13:33:34', 43, 1, 6, 0);
INSERT INTO `article` VALUES (37, 'test11', 'test1', '<p>test1</p>', NULL, '[\"Element\"]', 2, '2024-03-11 21:30:08', '2024-04-24 18:51:50', 21, 0, 6, 0);
INSERT INTO `article` VALUES (38, 'test12', 'test1', '<p>test1</p>', NULL, '[\"Element\"]', 2, '2024-03-11 21:30:16', '2024-04-19 20:10:23', 27, 0, 5, 0);
INSERT INTO `article` VALUES (39, 'test13', 'test1', '<p>test1</p>', NULL, '[\"Element\"]', 2, '2024-03-11 21:30:23', '2024-04-17 23:31:41', 21, 0, 6, 0);
INSERT INTO `article` VALUES (40, 'test14', 'test1', '<p>test1</p>', NULL, '[\"Element\"]', 2, '2024-03-11 21:30:29', '2024-04-22 00:18:53', 13, 0, 6, 0);
INSERT INTO `article` VALUES (41, 'test15', 'test1', '<p>test1</p>', NULL, '[\"Element\"]', 2, '2024-03-11 21:30:36', '2024-04-24 14:04:05', 39, 1, 5, 0);
INSERT INTO `article` VALUES (42, 'test19', 'test1', '<p>test1</p>', NULL, '[\"Axios\"]', 2, '2024-03-11 21:30:43', '2024-04-17 23:32:07', 43, 1, 6, 0);
INSERT INTO `article` VALUES (43, 'test16', 'test1', '<p>test111111111111</p>', 'http://localhost:9090/api/file/8b38cffce1f142e193476317796d9f7d.gif', '[\"Element\"]', 2, '2024-03-11 21:30:50', '2024-04-24 18:55:55', 177, 1, 6, 0);
INSERT INTO `article` VALUES (44, 'test18', 'test1', '<pre><code class=\"language-java\">@DeleteMapping(\"/del/batch\")\npublic Result deleteBatch(@RequestBody List&lt;Integer&gt; ids){\n       stringRedisTemplate.delete(Constants.USER_KEY);\n       return Result.success(userService.removeBatchByIds(ids));dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd\n}</code></pre><p><br></p>', 'http://localhost:9090/api/file/13d823f8a3524bf282bb4ec49859afb0.jpg', '[\"Vue\"]', 2, '2024-03-11 21:30:59', '2024-05-01 20:17:53', 1007, 37, 6, 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '后端开发');
INSERT INTO `category` VALUES (2, '前端开发');
INSERT INTO `category` VALUES (3, '服务器部署');
INSERT INTO `category` VALUES (5, '开发工具');
INSERT INTO `category` VALUES (6, '生活琐事');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级评论id',
  `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '回复用户id',
  `reply_comment_id` int(11) NULL DEFAULT NULL COMMENT '回复的评论的id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '评论用户id',
  `comment_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评论内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `like_num` int(11) NULL DEFAULT 0 COMMENT '点赞量',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 307 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (69, NULL, NULL, NULL, 2, '123', '2024-03-15 17:09:58', 1, 44);
INSERT INTO `comment` VALUES (70, NULL, NULL, NULL, 2, '123', '2024-03-15 17:10:08', 1, 44);
INSERT INTO `comment` VALUES (71, NULL, NULL, NULL, 2, '123', '2024-03-15 17:10:09', 1, 44);
INSERT INTO `comment` VALUES (72, NULL, NULL, NULL, 2, '1133333', '2024-03-15 17:12:39', 1, 44);
INSERT INTO `comment` VALUES (73, NULL, NULL, NULL, 2, '6564565', '2024-03-15 17:15:21', 1, 44);
INSERT INTO `comment` VALUES (74, NULL, NULL, NULL, 2, '56516', '2024-03-15 17:16:20', 1, 44);
INSERT INTO `comment` VALUES (75, NULL, NULL, NULL, 2, '666\n', '2024-03-15 17:18:56', 1, 44);
INSERT INTO `comment` VALUES (76, NULL, NULL, NULL, 2, '', '2024-03-15 17:19:32', 1, 44);
INSERT INTO `comment` VALUES (77, NULL, NULL, NULL, 2, '123\n', '2024-03-15 17:21:19', 1, 44);
INSERT INTO `comment` VALUES (86, NULL, NULL, NULL, 2, '111111111111111111111111111111', '2024-03-15 23:36:54', 1, 44);
INSERT INTO `comment` VALUES (87, NULL, NULL, NULL, 2, '测试unshift', '2024-03-15 23:39:30', 1, 44);
INSERT INTO `comment` VALUES (88, NULL, NULL, NULL, 2, '怎么卡了一下', '2024-03-15 23:39:59', 1, 44);
INSERT INTO `comment` VALUES (89, NULL, NULL, NULL, 2, 'unshift用错了，解决', '2024-03-15 23:42:54', 1, 44);
INSERT INTO `comment` VALUES (90, NULL, NULL, NULL, 2, '71key更新错误？\n', '2024-03-15 23:43:32', 1, 44);
INSERT INTO `comment` VALUES (91, NULL, NULL, NULL, 2, '又没了\n', '2024-03-15 23:43:38', 1, 44);
INSERT INTO `comment` VALUES (92, NULL, NULL, NULL, 2, '好像可以关机了', '2024-03-15 23:43:48', 1, 44);
INSERT INTO `comment` VALUES (93, NULL, NULL, NULL, 2, '1', '2024-03-15 23:50:36', 1, 44);
INSERT INTO `comment` VALUES (94, NULL, NULL, NULL, 2, '（评论后再滚动加载出错，原因：插入后，再查询数据然后concat会有重复的一条，也就导致了v-for的key重复了）原来是+1再-1的问题啊，收工', '2024-03-16 00:07:05', 1, 44);
INSERT INTO `comment` VALUES (95, NULL, NULL, NULL, 2, '解决方法commentlist.pop()\n', '2024-03-16 00:07:49', 1, 44);
INSERT INTO `comment` VALUES (96, NULL, NULL, NULL, 2, '我又来啦\n', '2024-03-16 16:51:00', 0, 44);
INSERT INTO `comment` VALUES (97, NULL, NULL, NULL, 2, '测试\n', '2024-03-16 18:01:21', 0, 27);
INSERT INTO `comment` VALUES (98, NULL, NULL, NULL, 2, '啊\n', '2024-03-16 18:01:30', 0, 27);
INSERT INTO `comment` VALUES (99, NULL, NULL, NULL, 2, '？\n', '2024-03-16 18:04:28', 0, 27);
INSERT INTO `comment` VALUES (100, NULL, NULL, NULL, 2, 'what', '2024-03-16 18:04:39', 0, 27);
INSERT INTO `comment` VALUES (101, NULL, NULL, NULL, 2, 'ok\n', '2024-03-16 18:12:00', 0, 27);
INSERT INTO `comment` VALUES (102, NULL, NULL, NULL, 2, '测试\n', '2024-03-16 18:12:27', 0, 27);
INSERT INTO `comment` VALUES (103, NULL, NULL, NULL, 2, '测试', '2024-03-16 18:12:33', 0, 27);
INSERT INTO `comment` VALUES (104, NULL, NULL, NULL, 2, '测试\n', '2024-03-16 18:12:37', 0, 27);
INSERT INTO `comment` VALUES (105, NULL, NULL, NULL, 2, '测试', '2024-03-16 18:12:40', 1, 27);
INSERT INTO `comment` VALUES (106, NULL, NULL, NULL, 2, '66666666666', '2024-03-16 18:12:45', 1, 27);
INSERT INTO `comment` VALUES (107, NULL, NULL, NULL, 2, '测试', '2024-03-16 18:13:56', 0, 42);
INSERT INTO `comment` VALUES (108, NULL, NULL, NULL, 2, 'hello', '2024-03-16 18:19:41', 0, 40);
INSERT INTO `comment` VALUES (109, NULL, NULL, NULL, 2, '有bug是以后的事，跟现在的我无关\n', '2024-03-16 18:20:21', 0, 28);
INSERT INTO `comment` VALUES (110, NULL, NULL, NULL, 2, '我为什么想要点赞功能呢？\n', '2024-03-16 20:18:06', 1, 44);
INSERT INTO `comment` VALUES (111, NULL, NULL, NULL, 2, '成功拿到islike的数据\n', '2024-03-16 21:13:35', 1, 44);
INSERT INTO `comment` VALUES (112, NULL, NULL, NULL, 2, '为什么每次都是这么晚\n', '2024-03-16 23:23:44', 1, 44);
INSERT INTO `comment` VALUES (113, NULL, NULL, NULL, 2, '明天二级评论\n', '2024-03-16 23:23:51', 1, 44);
INSERT INTO `comment` VALUES (114, NULL, NULL, NULL, 2, '为什么这么久，因为我写mapper.xml语句前把这个mapper备份到了同级的backup目录下，结果编译的时候一直编译那个旧的xml，找了几个小时问题，结果就这', '2024-03-16 23:35:59', 0, 44);
INSERT INTO `comment` VALUES (115, NULL, NULL, NULL, 2, '输入框的字体好垃圾，但我懒得换了\n...', '2024-03-16 23:36:33', 1, 44);
INSERT INTO `comment` VALUES (116, NULL, NULL, NULL, 2, '还不支持换行输入', '2024-03-16 23:36:50', 0, 44);
INSERT INTO `comment` VALUES (128, 30, 1, 57, 2, '回复玩玩', '2024-03-17 22:55:26', 0, 44);
INSERT INTO `comment` VALUES (129, 60, 1, 68, 2, '真的行了\n', '2024-03-17 22:59:06', 0, 44);
INSERT INTO `comment` VALUES (130, 60, 1, 68, 2, 'nice', '2024-03-17 23:01:53', 0, 44);
INSERT INTO `comment` VALUES (131, 60, 1, 68, 2, 'ok', '2024-03-17 23:03:10', 0, 44);
INSERT INTO `comment` VALUES (132, 59, 1, 63, 2, '666666', '2024-03-17 23:03:20', 0, 44);
INSERT INTO `comment` VALUES (133, 41, 3, 53, 2, '哈哈哈', '2024-03-17 23:03:27', 0, 44);
INSERT INTO `comment` VALUES (134, 41, 1, 54, 2, '有点小问题，不碍事', '2024-03-17 23:03:45', 0, 44);
INSERT INTO `comment` VALUES (135, NULL, NULL, NULL, 2, '终于成功了，为什么又是晚上\n', '2024-03-17 23:04:07', 0, 44);
INSERT INTO `comment` VALUES (136, 135, 2, 135, 2, '二级评论就是好', '2024-03-17 23:04:16', 0, 44);
INSERT INTO `comment` VALUES (137, 135, 2, 136, 2, '有bug', '2024-03-17 23:04:44', 0, 44);
INSERT INTO `comment` VALUES (138, 135, 2, 136, 2, '离谱', '2024-03-17 23:04:52', 0, 44);
INSERT INTO `comment` VALUES (139, NULL, NULL, NULL, 2, '再试一下', '2024-03-17 23:05:14', 0, 44);
INSERT INTO `comment` VALUES (140, 139, 2, 139, 2, '试一下', '2024-03-17 23:05:19', 0, 44);
INSERT INTO `comment` VALUES (141, NULL, NULL, NULL, 2, 'test', '2024-03-17 23:08:17', 0, 44);
INSERT INTO `comment` VALUES (142, 141, 2, 141, 2, 'tset', '2024-03-17 23:08:20', 0, 44);
INSERT INTO `comment` VALUES (143, NULL, NULL, NULL, 2, 'testtttt', '2024-03-17 23:08:58', 0, 44);
INSERT INTO `comment` VALUES (144, 143, 2, 143, 2, 'testtttt', '2024-03-17 23:09:02', 0, 44);
INSERT INTO `comment` VALUES (145, NULL, NULL, NULL, 2, 'tetttttt', '2024-03-17 23:11:07', 0, 44);
INSERT INTO `comment` VALUES (146, 145, 2, 145, 2, 'tttttt', '2024-03-17 23:11:10', 0, 44);
INSERT INTO `comment` VALUES (147, NULL, NULL, NULL, 2, 'ttttttttttttttttttttttt', '2024-03-17 23:11:36', 0, 44);
INSERT INTO `comment` VALUES (148, 147, 2, 147, 2, 'tttttttttttttttttttttttttttt', '2024-03-17 23:11:40', 0, 44);
INSERT INTO `comment` VALUES (149, NULL, NULL, NULL, 2, 'ggggggggggggggggg', '2024-03-17 23:14:01', 0, 44);
INSERT INTO `comment` VALUES (150, 149, 2, 149, 2, 'gggggggggggggg', '2024-03-17 23:14:04', 0, 44);
INSERT INTO `comment` VALUES (151, NULL, NULL, NULL, 2, 'cccccccccccc', '2024-03-17 23:16:55', 0, 44);
INSERT INTO `comment` VALUES (152, 151, 2, 151, 2, 'ccccccc', '2024-03-17 23:16:57', 0, 44);
INSERT INTO `comment` VALUES (153, NULL, NULL, NULL, 2, 'ddddddddddddd', '2024-03-17 23:17:25', 0, 44);
INSERT INTO `comment` VALUES (154, 153, 2, 153, 2, 'ddddddddddd', '2024-03-17 23:17:28', 0, 44);
INSERT INTO `comment` VALUES (155, NULL, NULL, NULL, 2, 'dddddddddddddd', '2024-03-17 23:17:44', 0, 44);
INSERT INTO `comment` VALUES (156, 155, 2, 155, 2, 'ddddddddddddddddd', '2024-03-17 23:17:49', 0, 44);
INSERT INTO `comment` VALUES (157, NULL, NULL, NULL, 2, 'dddddddddd', '2024-03-17 23:18:29', 0, 44);
INSERT INTO `comment` VALUES (158, 157, 2, 157, 2, 'ddddddddd', '2024-03-17 23:18:31', 0, 44);
INSERT INTO `comment` VALUES (159, NULL, NULL, NULL, 2, '成功成功', '2024-03-17 23:18:48', 0, 44);
INSERT INTO `comment` VALUES (160, 159, 2, 159, 2, '啊啊啊啊', '2024-03-17 23:18:53', 0, 44);
INSERT INTO `comment` VALUES (161, 159, 2, 160, 2, '的点点滴滴多多多所所所所所所所无无大无无无无无无无无大大多大奥奥大奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥', '2024-03-17 23:18:59', 0, 44);
INSERT INTO `comment` VALUES (162, 159, 2, 160, 2, '213444444444444444444444', '2024-03-17 23:19:04', 0, 44);
INSERT INTO `comment` VALUES (163, NULL, NULL, NULL, 2, '加个hover效果', '2024-03-17 23:19:44', 0, 44);
INSERT INTO `comment` VALUES (164, NULL, NULL, NULL, 2, '算了，不加了\n不重要', '2024-03-17 23:20:01', 0, 44);
INSERT INTO `comment` VALUES (165, NULL, NULL, NULL, 2, '是打发斯蒂芬', '2024-03-17 23:20:44', 1, 44);
INSERT INTO `comment` VALUES (166, NULL, NULL, NULL, 2, '阿斯顿发斯蒂芬', '2024-03-17 23:20:46', 1, 44);
INSERT INTO `comment` VALUES (167, 166, 2, 166, 2, '阿斯顿发斯蒂芬', '2024-03-17 23:20:49', 1, 44);
INSERT INTO `comment` VALUES (168, 165, 2, 165, 2, '阿斯顿发斯蒂芬', '2024-03-17 23:20:54', 1, 44);
INSERT INTO `comment` VALUES (169, 165, 2, 168, 2, '阿斯顿发斯蒂芬', '2024-03-17 23:20:57', 0, 44);
INSERT INTO `comment` VALUES (170, NULL, NULL, NULL, 2, '多人情况下会出问题吧', '2024-03-17 23:21:25', 1, 44);
INSERT INTO `comment` VALUES (171, NULL, NULL, NULL, 2, '唉', '2024-03-17 23:21:36', 0, 44);
INSERT INTO `comment` VALUES (172, NULL, NULL, NULL, 2, '目前没点bug了\n', '2024-03-17 23:22:16', 0, 44);
INSERT INTO `comment` VALUES (173, 166, 2, 167, 2, '啥啥啥', '2024-03-17 23:23:09', 1, 44);
INSERT INTO `comment` VALUES (174, 166, 2, 167, 2, '啥啥啥', '2024-03-17 23:23:09', 1, 44);
INSERT INTO `comment` VALUES (175, 166, 2, 167, 2, '啥啥啥', '2024-03-17 23:23:09', 1, 44);
INSERT INTO `comment` VALUES (176, 166, 2, 167, 2, '啥啥啥', '2024-03-17 23:23:09', 1, 44);
INSERT INTO `comment` VALUES (177, 166, 2, 167, 2, '啥啥啥', '2024-03-17 23:23:09', 0, 44);
INSERT INTO `comment` VALUES (178, 166, 2, 167, 2, '啥啥啥', '2024-03-17 23:23:09', 0, 44);
INSERT INTO `comment` VALUES (179, NULL, NULL, NULL, 2, '我靠，一次发四条消息', '2024-03-17 23:23:41', 1, 44);
INSERT INTO `comment` VALUES (180, 172, 2, 172, 2, '什么情况', '2024-03-17 23:24:39', 0, 44);
INSERT INTO `comment` VALUES (181, 179, 2, 179, 2, '怎么复现', '2024-03-17 23:24:47', 0, 44);
INSERT INTO `comment` VALUES (182, 179, 2, 179, 2, '不管了', '2024-03-17 23:25:00', 0, 44);
INSERT INTO `comment` VALUES (183, 165, 2, 168, 2, 'test', '2024-03-17 23:25:26', 0, 44);
INSERT INTO `comment` VALUES (184, 59, 3, 59, 2, 'test', '2024-03-17 23:26:38', 0, 44);
INSERT INTO `comment` VALUES (185, 166, 2, 176, 2, 'test', '2024-03-17 23:30:13', 0, 44);
INSERT INTO `comment` VALUES (186, 166, 2, 185, 2, '测试', '2024-03-18 13:44:08', 0, 44);
INSERT INTO `comment` VALUES (187, 179, 2, 182, 2, '1232', '2024-03-18 13:51:51', 0, 44);
INSERT INTO `comment` VALUES (188, 179, 2, 181, 2, '21546', '2024-03-18 13:51:58', 0, 44);
INSERT INTO `comment` VALUES (189, 179, 2, 181, 2, '5466546', '2024-03-18 13:52:06', 0, 44);
INSERT INTO `comment` VALUES (190, 179, 2, 181, 2, '唉\n', '2024-03-18 14:23:44', 0, 44);
INSERT INTO `comment` VALUES (191, 179, 2, 181, 2, '1', '2024-03-18 14:27:27', 0, 44);
INSERT INTO `comment` VALUES (192, 179, 2, 181, 2, '1', '2024-03-18 14:27:54', 0, 44);
INSERT INTO `comment` VALUES (193, 179, 2, 181, 2, '1', '2024-03-18 14:30:14', 0, 44);
INSERT INTO `comment` VALUES (194, 179, 2, 181, 2, '1\n', '2024-03-18 14:33:57', 0, 44);
INSERT INTO `comment` VALUES (195, 179, 2, 181, 2, '1', '2024-03-18 14:40:16', 0, 44);
INSERT INTO `comment` VALUES (196, 179, 2, 181, 2, '终于', '2024-03-18 14:40:25', 0, 44);
INSERT INTO `comment` VALUES (197, 179, 2, 181, 2, '成', '2024-03-18 14:41:21', 0, 44);
INSERT INTO `comment` VALUES (198, 170, 2, 170, 2, '没问题了', '2024-03-18 14:41:56', 0, 44);
INSERT INTO `comment` VALUES (199, 170, 2, 198, 2, '哈哈哈', '2024-03-18 14:42:01', 0, 44);
INSERT INTO `comment` VALUES (200, 170, 2, 198, 2, '哈哈哈', '2024-03-18 14:42:09', 0, 44);
INSERT INTO `comment` VALUES (201, 170, 2, 198, 2, '哈哈哈', '2024-03-18 14:42:13', 0, 44);
INSERT INTO `comment` VALUES (202, 170, 2, 198, 2, '哈哈哈', '2024-03-18 14:42:20', 0, 44);
INSERT INTO `comment` VALUES (203, 170, 2, 170, 2, '哈哈哈', '2024-03-18 14:42:26', 0, 44);
INSERT INTO `comment` VALUES (204, 179, 2, 181, 2, '哈哈哈', '2024-03-18 14:42:52', 0, 44);
INSERT INTO `comment` VALUES (205, NULL, NULL, NULL, 2, '移动测试', '2024-03-18 14:46:49', 0, 43);
INSERT INTO `comment` VALUES (206, NULL, NULL, NULL, 2, '没问题', '2024-03-18 14:46:56', 0, 43);
INSERT INTO `comment` VALUES (207, NULL, NULL, NULL, 2, '时间能看就行\n', '2024-03-18 14:47:05', 1, 43);
INSERT INTO `comment` VALUES (208, 207, 2, 207, 2, '哈哈哈', '2024-03-18 14:47:13', 1, 43);
INSERT INTO `comment` VALUES (209, 207, 2, 208, 2, '。。。。。', '2024-03-18 14:47:20', 1, 43);
INSERT INTO `comment` VALUES (210, 207, 2, 208, 2, 'test', '2024-03-18 14:47:27', 0, 43);
INSERT INTO `comment` VALUES (211, 207, 2, 208, 2, 'test', '2024-03-18 14:47:30', 0, 43);
INSERT INTO `comment` VALUES (212, NULL, NULL, NULL, 2, '写留言板', '2024-03-18 14:48:26', 0, 28);
INSERT INTO `comment` VALUES (213, 106, 2, 106, 2, '再次回复', '2024-04-07 21:09:47', 0, 27);
INSERT INTO `comment` VALUES (214, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:20px;height:20\"/><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:20px;height:20\"/><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:20px;height:20\"/>表情输入', '2024-04-10 11:21:14', 0, 44);
INSERT INTO `comment` VALUES (215, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:21px;height:21\"/>测试', '2024-04-10 11:24:49', 0, 44);
INSERT INTO `comment` VALUES (216, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:16px;height:16\"/><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:16px;height:16\"/><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:16px;height:16\"/><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:16px;height:16\"/>测试', '2024-04-10 11:25:15', 0, 44);
INSERT INTO `comment` VALUES (217, 216, 2, 216, 2, '测试二级评论表情输入<img class=\"emoji-pic\" src=\"/public/emoji/tvxyx.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 11:54:29', 0, 44);
INSERT INTO `comment` VALUES (218, 165, 2, 168, 2, '测试二级评论表情输入<img class=\"emoji-pic\" src=\"/public/emoji/tvxyx.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 11:54:29', 0, 44);
INSERT INTO `comment` VALUES (219, 216, 2, 217, 2, '再次测试<img class=\"emoji-pic\" src=\"/public/emoji/tvwx.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 11:54:48', 0, 44);
INSERT INTO `comment` VALUES (220, 216, 2, 217, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/ohu.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 11:55:56', 0, 44);
INSERT INTO `comment` VALUES (221, 216, 2, 217, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 11:56:04', 0, 44);
INSERT INTO `comment` VALUES (222, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 12:13:35', 0, 44);
INSERT INTO `comment` VALUES (223, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 12:13:38', 0, 44);
INSERT INTO `comment` VALUES (224, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 12:13:39', 0, 44);
INSERT INTO `comment` VALUES (225, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 12:13:40', 0, 44);
INSERT INTO `comment` VALUES (226, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 12:13:41', 0, 44);
INSERT INTO `comment` VALUES (227, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 12:13:50', 0, 44);
INSERT INTO `comment` VALUES (228, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/xiao.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/xiao.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/piezui.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/ganbei.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/xxy.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/yongbao.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/yongbao.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/dianzan.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 12:13:59', 0, 44);
INSERT INTO `comment` VALUES (229, 228, 2, 228, 2, '回复啥呢<img class=\"emoji-pic\" src=\"/public/emoji/ciya.png\"  style=\"width:21px;height:21\"/>', '2024-04-10 13:20:12', 0, 44);
INSERT INTO `comment` VALUES (230, 228, 2, 229, 2, 'dffasdfsadf<br/>asdfasd<br/>asdfasdf<br/>asdfasdf<br/>asdfasdf<br/><br/>a<br/>sdfasdfasdf', '2024-04-10 13:20:43', 0, 44);
INSERT INTO `comment` VALUES (231, NULL, NULL, NULL, 2, 'sdfasd1<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-11 23:01:25', 0, 44);
INSERT INTO `comment` VALUES (232, NULL, NULL, NULL, 2, '测试<br/>测试', '2024-04-11 23:03:31', 0, 44);
INSERT INTO `comment` VALUES (233, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\"  style=\"width:21px;height:21\"/><img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\"  style=\"width:21px;height:21\"/>啥啥啥', '2024-04-11 23:07:12', 0, 44);
INSERT INTO `comment` VALUES (234, NULL, NULL, NULL, 2, 'sdfgsdfg<br/>asdfasdf', '2024-04-11 23:16:03', 0, 44);
INSERT INTO `comment` VALUES (235, NULL, NULL, NULL, 2, '1234<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-11 23:19:13', 0, 44);
INSERT INTO `comment` VALUES (236, 59, 1, 63, 2, '你好', '2024-04-13 21:16:31', 0, 44);
INSERT INTO `comment` VALUES (237, 228, 2, 230, 2, '你好', '2024-04-13 21:16:31', 0, 44);
INSERT INTO `comment` VALUES (238, NULL, NULL, NULL, 2, 'sdd', '2024-04-14 21:29:54', 0, 44);
INSERT INTO `comment` VALUES (239, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" />', '2024-04-15 09:43:35', 0, 44);
INSERT INTO `comment` VALUES (240, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" />', '2024-04-15 09:44:24', 0, 44);
INSERT INTO `comment` VALUES (241, NULL, NULL, NULL, 2, '目录快完成了<br/>', '2024-04-16 22:25:10', 0, 1);
INSERT INTO `comment` VALUES (242, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-17 00:19:41', 0, 27);
INSERT INTO `comment` VALUES (243, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-17 00:19:55', 0, 27);
INSERT INTO `comment` VALUES (244, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" />', '2024-04-17 00:20:07', 0, 27);
INSERT INTO `comment` VALUES (245, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-17 17:53:32', 0, 44);
INSERT INTO `comment` VALUES (246, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/touxiao.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/touxiao.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/touxiao.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/touxiao.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/touxiao.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/touxiao.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/touxiao.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/touxiao.png\" />', '2024-04-17 19:19:41', 0, 1);
INSERT INTO `comment` VALUES (247, 246, 2, 246, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" />', '2024-04-17 19:19:49', 0, 1);
INSERT INTO `comment` VALUES (248, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/tvxyx.png\" />', '2024-04-17 23:54:47', 0, 1);
INSERT INTO `comment` VALUES (249, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" />', '2024-04-17 23:54:51', 0, 1);
INSERT INTO `comment` VALUES (250, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" />', '2024-04-17 23:55:05', 0, 1);
INSERT INTO `comment` VALUES (251, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/shengqi.png\" />', '2024-04-18 00:07:49', 0, 7);
INSERT INTO `comment` VALUES (252, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:51:43', 0, 1);
INSERT INTO `comment` VALUES (253, 252, 2, 252, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/tu.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/tu.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/tu.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/tu.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/tu.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/tu.png\" />', '2024-04-19 17:51:54', 0, 1);
INSERT INTO `comment` VALUES (254, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" />', '2024-04-19 17:55:27', 0, 1);
INSERT INTO `comment` VALUES (255, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/wulian.png\" />', '2024-04-19 17:55:33', 0, 1);
INSERT INTO `comment` VALUES (256, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:55:42', 0, 1);
INSERT INTO `comment` VALUES (257, 256, 2, 256, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:55:48', 0, 1);
INSERT INTO `comment` VALUES (258, 256, 2, 257, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:56:24', 0, 1);
INSERT INTO `comment` VALUES (259, 256, 2, 257, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:56:35', 0, 1);
INSERT INTO `comment` VALUES (260, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:56:41', 0, 1);
INSERT INTO `comment` VALUES (261, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:57:31', 0, 1);
INSERT INTO `comment` VALUES (262, 261, 2, 261, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:57:37', 0, 1);
INSERT INTO `comment` VALUES (263, 261, 2, 262, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:57:49', 0, 1);
INSERT INTO `comment` VALUES (264, 261, 2, 262, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:57:55', 0, 1);
INSERT INTO `comment` VALUES (265, 261, 2, 261, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 17:58:04', 0, 1);
INSERT INTO `comment` VALUES (266, 251, 2, 251, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 18:10:29', 0, 7);
INSERT INTO `comment` VALUES (267, NULL, NULL, NULL, 2, '1111', '2024-04-19 18:10:36', 0, 7);
INSERT INTO `comment` VALUES (268, 267, 2, 267, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 18:10:54', 0, 7);
INSERT INTO `comment` VALUES (269, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 20:36:29', 0, 1);
INSERT INTO `comment` VALUES (270, 269, 2, 269, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 20:36:33', 0, 1);
INSERT INTO `comment` VALUES (271, NULL, NULL, NULL, 2, '不改了，v1版本就这样吧，全增删改查<br/>', '2024-04-19 20:52:03', 0, 7);
INSERT INTO `comment` VALUES (272, NULL, NULL, NULL, 2, '啊<br/>', '2024-04-19 20:52:23', 0, 7);
INSERT INTO `comment` VALUES (273, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 20:53:07', 0, 7);
INSERT INTO `comment` VALUES (274, 273, 2, 273, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 20:53:16', 0, 7);
INSERT INTO `comment` VALUES (275, 273, 2, 274, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-19 20:53:21', 0, 7);
INSERT INTO `comment` VALUES (276, NULL, NULL, NULL, 2, '哈哈，改完<br/>', '2024-04-19 21:58:03', 0, 1);
INSERT INTO `comment` VALUES (277, NULL, NULL, NULL, 2, 'this.load()<br/>console.log(this.article)<br/>下面的居然比上面的先执行，6到我了<br/>', '2024-04-19 21:59:00', 0, 7);
INSERT INTO `comment` VALUES (279, NULL, NULL, NULL, 2, '11111', '2024-04-21 00:52:01', 1, 44);
INSERT INTO `comment` VALUES (280, 279, 2, 279, 2, '11111111111', '2024-04-21 00:52:06', 0, 44);
INSERT INTO `comment` VALUES (281, 279, 2, 280, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-21 00:52:12', 0, 44);
INSERT INTO `comment` VALUES (282, 279, 2, 281, 2, '1111111111', '2024-04-21 00:52:16', 0, 44);
INSERT INTO `comment` VALUES (283, 279, 2, 280, 2, '111111111', '2024-04-21 00:52:22', 0, 44);
INSERT INTO `comment` VALUES (284, 279, 2, 280, 2, '111111111', '2024-04-21 00:52:26', 0, 44);
INSERT INTO `comment` VALUES (285, 279, 2, 280, 2, '11111111111', '2024-04-21 00:52:32', 0, 44);
INSERT INTO `comment` VALUES (286, NULL, NULL, NULL, 2, '1111111111111111', '2024-04-21 00:52:39', 0, 44);
INSERT INTO `comment` VALUES (287, NULL, NULL, NULL, 2, '1111111111111111', '2024-04-21 00:52:42', 0, 44);
INSERT INTO `comment` VALUES (288, NULL, NULL, NULL, 2, '1111111111', '2024-04-21 00:53:04', 0, 44);
INSERT INTO `comment` VALUES (289, NULL, NULL, NULL, 2, '1111111', '2024-04-21 00:53:06', 0, 44);
INSERT INTO `comment` VALUES (290, 289, 2, 289, 2, '1111111', '2024-04-21 00:53:10', 0, 44);
INSERT INTO `comment` VALUES (291, NULL, NULL, NULL, 2, '11111111111', '2024-04-21 00:53:12', 0, 44);
INSERT INTO `comment` VALUES (292, NULL, NULL, NULL, 2, '11111', '2024-04-21 00:56:48', 0, 44);
INSERT INTO `comment` VALUES (293, 292, 2, 292, 2, '1111', '2024-04-21 00:56:51', 0, 44);
INSERT INTO `comment` VALUES (294, NULL, NULL, NULL, 2, '1111', '2024-04-21 00:56:53', 0, 44);
INSERT INTO `comment` VALUES (295, NULL, NULL, NULL, 2, '1111111111', '2024-04-21 00:56:59', 0, 44);
INSERT INTO `comment` VALUES (296, 295, 2, 295, 2, '1111111111111', '2024-04-21 00:57:02', 0, 44);
INSERT INTO `comment` VALUES (297, 295, 2, 296, 2, '111111111', '2024-04-21 00:57:05', 0, 44);
INSERT INTO `comment` VALUES (298, NULL, NULL, NULL, 2, '11111111111', '2024-04-21 00:57:07', 0, 44);
INSERT INTO `comment` VALUES (299, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-21 20:58:40', 1, 44);
INSERT INTO `comment` VALUES (300, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/kgz.png\" />', '2024-04-21 20:58:45', 0, 44);
INSERT INTO `comment` VALUES (301, 300, 2, 300, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-21 20:58:50', 0, 44);
INSERT INTO `comment` VALUES (302, NULL, NULL, NULL, 2, '性能测试', '2024-04-22 22:04:56', 1, 27);
INSERT INTO `comment` VALUES (303, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/zhichi.png\" />性能测试', '2024-04-22 22:05:03', 1, 27);
INSERT INTO `comment` VALUES (304, NULL, NULL, NULL, 2, '<img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" /><img class=\"emoji-pic\" src=\"/public/emoji/suanle.png\" />', '2024-04-23 17:34:40', 0, 1);
INSERT INTO `comment` VALUES (305, NULL, NULL, NULL, 2, '1212121', '2024-04-23 17:34:59', 0, 1);
INSERT INTO `comment` VALUES (306, NULL, NULL, NULL, 2, '自己给自己点赞<br/>', '2024-04-24 17:48:46', 1, 27);

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字体图标表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('user', 'el-icon-user', 'icon');
INSERT INTO `dict` VALUES ('house', 'el-icon-house', 'icon');
INSERT INTO `dict` VALUES ('menu', 'el-icon-menu', 'icon');
INSERT INTO `dict` VALUES ('file', 'el-icon-file', 'icon');
INSERT INTO `dict` VALUES ('s-customs', 'el-icon-s-custom', 'icon');
INSERT INTO `dict` VALUES ('s-grid', 'el-icon-s-grid', 'icon');
INSERT INTO `dict` VALUES ('document', 'el-icon-document', 'icon');
INSERT INTO `dict` VALUES ('chart', 'el-icon-pie-chart', 'icon');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小(kb)',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下载地址',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件md5',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用链接',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (2, 'Battlefield_V_Screenshot_2021.09.09_-_18.59.54.94.png', 'png', 27, 'http://localhost:9090/api/file/55d39a81430e4cc1b3df736ec31ba1b3.png', 'a91d6cbfe1a2989bfb1cef88856ee6d9', 0, 1, NULL);
INSERT INTO `file` VALUES (3, 'Battlefield_V_Screenshot_2021.09.09_-_18.59.54.94.png', 'png', 27, 'http://localhost:9090/api/file/55d39a81430e4cc1b3df736ec31ba1b3.png', 'a91d6cbfe1a2989bfb1cef88856ee6d9', 1, 1, NULL);
INSERT INTO `file` VALUES (5, '2.jpg', 'jpg', 31, 'http://localhost:9090/api/file/2dbea15ca8884ad3aba1b53e9124865b.jpg', 'e082427b6254fd88b1cc45242c89e75a', 0, 1, NULL);
INSERT INTO `file` VALUES (6, 'export.xlsx', 'xlsx', 8, 'http://localhost:9090/api/file/9031866a7fdc4326ac1f272a4dd25702.xlsx', '6db8b18ffb811904e72375a85f8448df', 0, 1, NULL);
INSERT INTO `file` VALUES (7, '1.png', 'png', 5, 'http://localhost:9090/api/file/3d3a24b6c2454b25ad9a0cc08ce97b65.png', '712a572705b34ef7a44ffc8c476171c7', 0, 1, NULL);
INSERT INTO `file` VALUES (8, '640.png', 'png', 216, 'http://localhost:9090/api/file/7d924cd22c9441858ff4ee45c60a16a8.png', 'e7d58cd5a90f5b9012424f32edab617f', 0, 1, NULL);
INSERT INTO `file` VALUES (9, '1.jpg', 'jpg', 22, 'http://localhost:9090/api/file/ee5dd5b1d88a4564864d1eb39dc47c20.jpg', 'a00501ff54c11f56c083cde0db7c1bc2', 0, 1, NULL);
INSERT INTO `file` VALUES (10, '3.jpg', 'jpg', 17, 'http://localhost:9090/api/file/aec4d588dfbd4b8e9c0b9714ab0405ed.jpg', '3b4e907e4aa33229b21c77d93ea67b8e', 0, 1, NULL);
INSERT INTO `file` VALUES (11, '5.jpg', 'jpg', 17, 'http://localhost:9090/api/file/cb935c477d9f4accacb95c18feeedd87.jpg', '6eaccff3e07f64c60ae0dac71c0a61ca', 0, 1, NULL);
INSERT INTO `file` VALUES (12, 'u=1846922134,1220596769&fm=26&gp=0.png', 'png', 425, 'http://localhost:9090/api/file/dbf21f06f21b4e67b5f2c8c172353a25.png', '697584afbebe3ad4057f216b0854e5d8', 0, 1, NULL);
INSERT INTO `file` VALUES (13, '2.jpg', 'jpg', 15, 'http://localhost:9090/api/file/a6caea9d461a4966915ccf16e7a782ad.jpg', '419beef76bb5d59fc5e4bab1b7018f25', 0, 1, NULL);
INSERT INTO `file` VALUES (14, '2.jpg', 'jpg', 15, 'http://localhost:9090/api/file/a6caea9d461a4966915ccf16e7a782ad.jpg', '419beef76bb5d59fc5e4bab1b7018f25', 0, 1, NULL);
INSERT INTO `file` VALUES (15, 'u=225722119,3665517301&fm=26&gp=0.jpg', 'jpg', 26, 'http://localhost:9090/api/file/4e7631c264f1427d9ba5e5678d26cfb1.jpgs', 'b4bf50087569c3578efa0dee93a80112', 0, 1, NULL);
INSERT INTO `file` VALUES (16, 'u=731740168,33347063&fm=26&gp=0.jpg', 'jpg', 21, 'http://localhost:9090/api/file/53379075eec6454b8fb3cc54db4153df.jpg', '2f4f7af28c5a30fc2364a097b8fcba48', 0, 1, NULL);
INSERT INTO `file` VALUES (17, 'u=1830354045,4066059328&fm=26&gp=0.jpg', 'jpg', 8, 'http://localhost:9090/api/file/13a7c485983f4e39acacfe0319f69d21.jpg', '5f65da684d3e20ccb09cb6189fd13d5f', 0, 1, NULL);
INSERT INTO `file` VALUES (18, 'u=4269629040,1011336882&fm=26&gp=0.jpg', 'jpg', 37, 'http://localhost:9090/api/file/917dc34e8daa43e3a509bd209e158e9c.jpg', 'e47b2a8cb7c2f2b8e2d519ef014b652c', 0, 1, NULL);
INSERT INTO `file` VALUES (19, 'u=2005077375,1536908048&fm=26&gp=0.jpg', 'jpg', 25, 'http://localhost:9090/api/file/8eaaaa13abca46b1923932d60b8d219e.jpg', '3c975c0f11c002385c70c7e4896853c7', 0, 1, NULL);
INSERT INTO `file` VALUES (20, 'u=330243628,483352131&fm=26&gp=0.jpg', 'jpg', 50, 'http://localhost:9090/api/file/efecef72406d49e5bda7b9b6fc04fa9f.jpg', '89eb01033fd31375216ec22fa28785b0', 0, 1, NULL);
INSERT INTO `file` VALUES (21, 'u=1882348261,2524884328&fm=26&gp=0.jpg', 'jpg', 46, 'http://localhost:9090/api/file/a2b4bf70966b4923b3a090294bd17f44.jpg', '54617d030f658556938c4cc3ab37c088', 0, 1, NULL);
INSERT INTO `file` VALUES (22, 'u=4269629040,1011336882&fm=26&gp=0.jpg', 'jpg', 37, 'http://localhost:9090/api/file/917dc34e8daa43e3a509bd209e158e9c.jpg', 'e47b2a8cb7c2f2b8e2d519ef014b652c', 0, 1, NULL);
INSERT INTO `file` VALUES (23, 'c75d63ea-5d42-4b1e-a452-994706fd3712.jpg', 'jpg', 5, 'http://localhost:9090/api/file/204a1a1231e34aeaba0ffecb5f0f201f.jpg', 'db5204f13b0edaf2ee3362ee461c903d', 0, 1, NULL);
INSERT INTO `file` VALUES (24, 'Inked1655899914968_LI.jpg', 'jpg', 704, 'http://localhost:9090/api/file/237c3a2dac8a4ec0a0193e2a2c78833d.jpg', '9d4bbdde6eb6ce8b6be34d6803d73504', 0, 1, NULL);
INSERT INTO `file` VALUES (25, 'test.jpg', 'jpg', 704, 'http://localhost:9090/api/file/237c3a2dac8a4ec0a0193e2a2c78833d.jpg', '9d4bbdde6eb6ce8b6be34d6803d73504', 0, 1, NULL);
INSERT INTO `file` VALUES (26, 'test.jpg', 'jpg', 704, 'http://localhost:9090/api/file/237c3a2dac8a4ec0a0193e2a2c78833d.jpg', '9d4bbdde6eb6ce8b6be34d6803d73504', 0, 1, NULL);
INSERT INTO `file` VALUES (30, '5.jpg', 'jpg', 31, 'http://localhost:9090/api/file/5ee4f63c4e3540cf9dc3c7d3ee66566b.jpg', 'a0d848eb76ffd580be041f4f433076b3', 0, 1, NULL);
INSERT INTO `file` VALUES (31, 'share_c0bc6a62c44973b420861e30135b3fc9.mp4', 'mp4', 24962, 'http://localhost:9090/api/file/c176820f4cd043699bca54e6902bdb3e.mp4', '2e14546038b052ffe749b993646a7f68', 0, 1, NULL);
INSERT INTO `file` VALUES (32, '0d2c1d7c5c2ee7b0ecf76f88e7b1c481.mp4', 'mp4', 54528, 'http://localhost:9090/api/file/337a0dab93f848e0a9d7a1245ada9b4a.mp4', '0d2c1d7c5c2ee7b0ecf76f88e7b1c481', 0, 1, NULL);
INSERT INTO `file` VALUES (33, '8d5494eef01f3a292cb013ca4b256c365c607c46.jpeg', 'jpeg', 52, 'http://localhost:9090/api/file/3a29d2b42cc04b7fbb16c201b702a0e6.jpeg', 'b4ba2339c941dc71ebe4880e79fd9565', 0, 1, NULL);
INSERT INTO `file` VALUES (34, '1658495094690.png', 'png', 408, 'http://localhost:9090/api/file/27c52e6494d246449d7d318bcd1810fa.png', 'c3a816b13fe5247bc45279b37978eec4', 0, 1, NULL);
INSERT INTO `file` VALUES (35, '计算机导论复习.docx', 'docx', 120, 'http://localhost:9090/api/file/21c9755f92fc44c8928ae9807cc2d40f.docx', '474af7c93aeb8e42418bf27994c1a708', 0, 1, NULL);
INSERT INTO `file` VALUES (36, '关系.xmind', 'xmind', 55, 'http://localhost:9090/api/file/5411b0235e804118b1fc4492cee99d26.xmind', '80949de3e89d8c9aa4d6ce3b5a68b81e', 0, 1, NULL);
INSERT INTO `file` VALUES (37, '在线医疗系统.pptx', 'pptx', 6357, 'http://localhost:9090/api/file/4b425fa3cd0a47caab3881df91c1303f.pptx', '4f2049037ff95b7465f029689ce06ea6', 0, 1, NULL);
INSERT INTO `file` VALUES (38, '5.jpg', 'jpg', 41, 'http://localhost:9090/api/file/c6003ddf31ab4623b6034120a1ce0b15.jpg', '7de9a0469df7d06941e0adc4ffe3c4db', 0, 1, NULL);
INSERT INTO `file` VALUES (39, '3.jpg', 'jpg', 37, 'http://localhost:9090/api/file/d98fc2241c9641a7bbf4b944844b1c9e.jpg', '589b4c8992e68eba0ac6cc473072bb68', 0, 1, NULL);
INSERT INTO `file` VALUES (40, 'u=1657918883,713689183&fm=26&gp=0.jpg', 'jpg', 28, 'http://localhost:9090/api/file/72a139b7968b4c11ba8808e4c30aca5d.jpg', 'c6221423324c40cbfab5af8f0de00c8a', 0, 1, NULL);
INSERT INTO `file` VALUES (41, 'u=225722119,3665517301&fm=26&gp=0.jpg', 'jpg', 26, 'http://localhost:9090/api/file/4e7631c264f1427d9ba5e5678d26cfb1.jpgs', 'b4bf50087569c3578efa0dee93a80112', 0, 1, NULL);
INSERT INTO `file` VALUES (42, '1.jpg', 'jpg', 27, 'http://localhost:9090/api/file/f263a2751c7146fb9a1316d2369616fc.jpg', 'd26d9ab351956432a6a597dd7d2641d4', 0, 1, NULL);
INSERT INTO `file` VALUES (43, 'u=580693191,1864949988&fm=26&gp=0.jpg', 'jpg', 33, 'http://localhost:9090/api/file/385d6fa3c71f4aa09ee29752b60cfdc2.jpg', '8a5013cc9f1317797041548ffe9893fc', 0, 1, NULL);
INSERT INTO `file` VALUES (44, 'u=3092246790,3051084387&fm=26&gp=0.jpg', 'jpg', 16, 'http://localhost:9090/api/file/1407f110dfbe40a0b29b9ee692710064.jpg', '0dacc92456e8226c4972bdc0833e2d10', 0, 1, NULL);
INSERT INTO `file` VALUES (45, 'u=3419131030,653308971&fm=26&gp=0.jpg', 'jpg', 42, 'http://localhost:9090/api/file/fbe77385204541d5ab73976f8410624c.jpg', '5c1e91dbe0f532edcf5b46dfed987c84', 0, 1, NULL);
INSERT INTO `file` VALUES (46, 'F8D93D695C94B9420C47A1A22A86038E.jpg', 'jpg', 243, 'http://localhost:9090/api/file/fa27b7946e9b47f68cbbe577bc7b2173.jpg', 'f8d93d695c94b9420c47a1a22a86038e', 0, 1, NULL);
INSERT INTO `file` VALUES (47, 'F8D93D695C94B9420C47A1A22A86038E.jpg', 'jpg', 243, 'http://localhost:9090/api/file/fa27b7946e9b47f68cbbe577bc7b2173.jpg', 'f8d93d695c94b9420c47a1a22a86038e', 0, 1, NULL);
INSERT INTO `file` VALUES (48, 'F8D93D695C94B9420C47A1A22A86038E.jpg', 'jpg', 243, 'http://localhost:9090/api/file/fa27b7946e9b47f68cbbe577bc7b2173.jpg', 'f8d93d695c94b9420c47a1a22a86038e', 0, 1, NULL);
INSERT INTO `file` VALUES (49, 'F8D93D695C94B9420C47A1A22A86038E.jpg', 'jpg', 243, 'http://localhost:9090/api/file/fa27b7946e9b47f68cbbe577bc7b2173.jpg', 'f8d93d695c94b9420c47a1a22a86038e', 0, 1, NULL);
INSERT INTO `file` VALUES (50, '08DAB44533F8DF94C06F33C2733165E4.jpg', 'jpg', 198, 'http://localhost:9090/api/file/f41b81d0820d4df79273494d9e240641.jpg', '08dab44533f8df94c06f33c2733165e4', 0, 1, NULL);
INSERT INTO `file` VALUES (51, '1_200318220526_1.jpg', 'jpg', 31, 'http://localhost:9090/api/file/3edec282350842e5b276c22f1109a88e.jpg', 'b74cc60a4db3a36eb1c60740827fe24b', 0, 1, NULL);
INSERT INTO `file` VALUES (52, 'play.png', 'png', 1, 'http://localhost:9090/api/file/5b399a849ea8428992a940a2440f9569.png', 'ff440a6699d1c11ea584dab624942b8a', 0, 1, NULL);
INSERT INTO `file` VALUES (53, '08DAB44533F8DF94C06F33C2733165E4.jpg', 'jpg', 198, 'http://localhost:9090/api/file/f41b81d0820d4df79273494d9e240641.jpg', '08dab44533f8df94c06f33c2733165e4', 0, 1, NULL);
INSERT INTO `file` VALUES (54, 'F8D93D695C94B9420C47A1A22A86038E.jpg', 'jpg', 243, 'http://localhost:9090/api/file/fa27b7946e9b47f68cbbe577bc7b2173.jpg', 'f8d93d695c94b9420c47a1a22a86038e', 0, 1, NULL);
INSERT INTO `file` VALUES (55, '2021-5-21 (2).jpg', 'jpg', 279, 'http://localhost:9090/api/file/d22004c995274d22842e906378b486c5.jpg', '2a40b41d8761fca52f6c8d207ea1a99a', 0, 1, NULL);
INSERT INTO `file` VALUES (56, '2021-5-21 (2).jpg', 'jpg', 279, 'http://localhost:9090/api/file/d22004c995274d22842e906378b486c5.jpg', '2a40b41d8761fca52f6c8d207ea1a99a', 0, 1, NULL);
INSERT INTO `file` VALUES (57, '08DAB44533F8DF94C06F33C2733165E4.jpg', 'jpg', 198, 'http://localhost:9090/api/file/f41b81d0820d4df79273494d9e240641.jpg', '08dab44533f8df94c06f33c2733165e4', 0, 1, NULL);
INSERT INTO `file` VALUES (58, 'YMES03W@OBEMHWAZKDO18W0.png', 'png', 68, 'http://localhost:9090/api/file/7f6c3d5197514f91a3a047541685ad20.png', 'f9a2e415d06f01ef5494343858d44d2f', 0, 1, NULL);
INSERT INTO `file` VALUES (59, 'YMES03W@OBEMHWAZKDO18W0.png', 'png', 68, 'http://localhost:9090/api/file/7f6c3d5197514f91a3a047541685ad20.png', 'f9a2e415d06f01ef5494343858d44d2f', 0, 1, NULL);
INSERT INTO `file` VALUES (60, '{J_0I)2MB3ZK43)Q9SB)J4B.png', 'png', 68, 'http://localhost:9090/api/file/bc44d640c70c4c94ba83dfe5422f015f.png', 'bbaa9355426be6616c71d1f760090b51', 0, 1, NULL);
INSERT INTO `file` VALUES (61, 'YMES03W@OBEMHWAZKDO18W0.png', 'png', 68, 'http://localhost:9090/api/file/7f6c3d5197514f91a3a047541685ad20.png', 'f9a2e415d06f01ef5494343858d44d2f', 0, 1, NULL);
INSERT INTO `file` VALUES (62, '936D092D772AADE3F9297917622006F5.png', 'png', 257, 'http://localhost:9090/api/file/f620a0bf9c614c219d1a8b9b5fc7d458.png', '936d092d772aade3f9297917622006f5', 0, 1, NULL);
INSERT INTO `file` VALUES (63, '936D092D772AADE3F9297917622006F5.png', 'png', 257, 'http://localhost:9090/api/file/f620a0bf9c614c219d1a8b9b5fc7d458.png', '936d092d772aade3f9297917622006f5', 0, 1, NULL);
INSERT INTO `file` VALUES (64, '1_200318220526_1.jpg', 'jpg', 31, 'http://localhost:9090/api/file/3edec282350842e5b276c22f1109a88e.jpg', 'b74cc60a4db3a36eb1c60740827fe24b', 0, 1, NULL);
INSERT INTO `file` VALUES (65, 'c4ba1ade02a8b9e23228a65daba373c9.jpg', 'jpg', 57, 'http://localhost:9090/api/file/aa908e31297d46f0ab5c770b653617e4.jpg', 'c4ba1ade02a8b9e23228a65daba373c9', 0, 1, NULL);
INSERT INTO `file` VALUES (66, 'YMES03W@OBEMHWAZKDO18W0.png', 'png', 68, 'http://localhost:9090/api/file/7f6c3d5197514f91a3a047541685ad20.png', 'f9a2e415d06f01ef5494343858d44d2f', 0, 1, NULL);
INSERT INTO `file` VALUES (67, 'c4ba1ade02a8b9e23228a65daba373c9.jpg', 'jpg', 57, 'http://localhost:9090/api/file/aa908e31297d46f0ab5c770b653617e4.jpg', 'c4ba1ade02a8b9e23228a65daba373c9', 0, 1, NULL);
INSERT INTO `file` VALUES (68, '936D092D772AADE3F9297917622006F5.png', 'png', 257, 'http://localhost:9090/api/file/f620a0bf9c614c219d1a8b9b5fc7d458.png', '936d092d772aade3f9297917622006f5', 0, 1, NULL);
INSERT INTO `file` VALUES (69, '936D092D772AADE3F9297917622006F5.png', 'png', 257, 'http://localhost:9090/api/file/f620a0bf9c614c219d1a8b9b5fc7d458.png', '936d092d772aade3f9297917622006f5', 0, 1, NULL);
INSERT INTO `file` VALUES (70, '1_200318220526_1.jpg', 'jpg', 31, 'http://localhost:9090/api/file/3edec282350842e5b276c22f1109a88e.jpg', 'b74cc60a4db3a36eb1c60740827fe24b', 0, 1, NULL);
INSERT INTO `file` VALUES (71, 'c75d63ea-5d42-4b1e-a452-994706fd3712.jpg', 'jpg', 5, 'http://localhost:9090/api/file/204a1a1231e34aeaba0ffecb5f0f201f.jpg', 'db5204f13b0edaf2ee3362ee461c903d', 0, 1, NULL);
INSERT INTO `file` VALUES (72, '3.png', 'png', 56, 'http://localhost:9090/api/file/f3db9b36785f4d56afd1c32da6b4cf4b.png', '8f548711e02f365e349da93f6b6db2d2', 0, 1, NULL);
INSERT INTO `file` VALUES (73, '2.png', 'png', 55, 'http://localhost:9090/api/file/d22f46b6b33447b198b67572525cd74f.png', '5b4f57109c424b1c898950f9ec1ee557', 0, 1, NULL);
INSERT INTO `file` VALUES (74, '4.png', 'png', 62, 'http://localhost:9090/api/file/12cf607526b0490b9b3c48d964331348.png', '2b087679c8222b58914b970b444eb403', 0, 1, NULL);
INSERT INTO `file` VALUES (75, '3.png', 'png', 56, 'http://localhost:9090/api/file/f3db9b36785f4d56afd1c32da6b4cf4b.png', '8f548711e02f365e349da93f6b6db2d2', 0, 1, NULL);
INSERT INTO `file` VALUES (76, '羊村.jpg', 'jpg', 42, 'http://localhost:9090/api/file/c36dab6bbed041fdb6d5d8c179e06368.jpg', '16d7c0f11f9c75bdefb27499d2248ad4', 0, 1, NULL);
INSERT INTO `file` VALUES (77, '羊村.jpg', 'jpg', 42, 'http://localhost:9090/api/file/c36dab6bbed041fdb6d5d8c179e06368.jpg', '16d7c0f11f9c75bdefb27499d2248ad4', 0, 1, NULL);
INSERT INTO `file` VALUES (78, '羊村.jpg', 'jpg', 42, 'http://localhost:9090/api/file/c36dab6bbed041fdb6d5d8c179e06368.jpg', '16d7c0f11f9c75bdefb27499d2248ad4', 0, 1, NULL);
INSERT INTO `file` VALUES (79, 'photomode_24092021_122725.png', 'png', 2699, 'http://localhost:9090/api/file/3e7ef0c8d5ba4f36b067727a3c1ac016.png', 'e8db69831ef07db905b65a03e13768da', 0, 1, NULL);
INSERT INTO `file` VALUES (80, 'cbc932bb6cc1d734a3ac1f7dce95cf6f.mp4', 'mp4', 89694, 'http://localhost:9090/api/file/79ba08385f314123b7a5a161e25d9d75.mp4', 'cbc932bb6cc1d734a3ac1f7dce95cf6f', 0, 1, NULL);
INSERT INTO `file` VALUES (81, '118399115058269.gif', 'gif', 8, 'http://localhost:9090/api/file/8b38cffce1f142e193476317796d9f7d.gif', '51f4b1af072d7989ba54cb92c964ca77', 0, 1, NULL);
INSERT INTO `file` VALUES (82, '005J4OU5ly1h4cig1wumyj30o30o3dgm.jpg', 'jpg', 38, 'http://localhost:9090/api/file/13d823f8a3524bf282bb4ec49859afb0.jpg', 'e46d5d83023a2e8891352541455756c6', 0, 1, NULL);
INSERT INTO `file` VALUES (83, '118399115058269.gif', 'gif', 8, 'http://localhost:9090/api/file/8b38cffce1f142e193476317796d9f7d.gif', '51f4b1af072d7989ba54cb92c964ca77', 0, 1, NULL);
INSERT INTO `file` VALUES (84, '005J4OU5ly1h4cig1wumyj30o30o3dgm.jpg', 'jpg', 38, 'http://localhost:9090/api/file/13d823f8a3524bf282bb4ec49859afb0.jpg', 'e46d5d83023a2e8891352541455756c6', 0, 1, NULL);
INSERT INTO `file` VALUES (85, '118399075271863.jpg', 'jpg', 7, 'http://localhost:9090/api/file/a67209504c124bfb80b50932d9146d4a.jpg', '196e1da337f0db21b20e9e026d2fd00b', 0, 1, NULL);
INSERT INTO `file` VALUES (86, '118399104125977.jpg', 'jpg', 95, 'http://localhost:9090/api/file/387b69ed2c7b412cb6aab09433b830d1.jpg', '36fdc49bc7c4eb325fd99bfe556b850e', 0, 1, NULL);
INSERT INTO `file` VALUES (87, '118399368842383.gif', 'gif', 78, 'http://localhost:9090/api/file/42b03965c6bf482ebbfbb24ac638b0b3.gif', '02425a77cc7f31b978ca4dbb3823e681', 0, 1, '2024-03-12 20:13:06');
INSERT INTO `file` VALUES (88, '118399313788842.gif', 'gif', 87, 'http://localhost:9090/api/file/91e6faff7eb94cfbaab0f4d7780d5fbc.gif', '51afe4eb7fd8cc52907f4a681c429af9', 0, 1, '2024-03-12 20:13:06');
INSERT INTO `file` VALUES (89, '118400639682747.gif', 'gif', 13, 'http://localhost:9090/api/file/d39d6ac26c2e46f0abcc81cc0461a13f.gif', 'ef6cad32cc79b2cb61c5526bdf28f8fd', 0, 1, '2024-03-12 20:17:47');
INSERT INTO `file` VALUES (90, '118400595455508.jpg', 'jpg', 55, 'http://localhost:9090/api/file/73a9322f6faa423fbf8699b6f2a3b8a8.jpg', 'ac4e14096257b35a6f2ada9369b15120', 0, 1, '2024-03-12 20:17:47');
INSERT INTO `file` VALUES (91, 'photo-1643180109978-8b88bd13f568.webp', 'webp', 42, 'http://localhost:9090/api/file/c90708352b7d4037bd302f8958d4e4a1.webp', '41dbc9afc1dd64c22611dcb900d12c97', 0, 1, '2024-03-12 20:45:16');
INSERT INTO `file` VALUES (92, 'photo-1661349794818-a2282abd5460.webp', 'webp', 158, 'http://localhost:9090/api/file/0a0bd4eabe664a33876965e99729307f.webp', '4ea3ce52e2da3b6ecab9dc5c9a0a1970', 0, 1, '2024-03-12 20:45:16');
INSERT INTO `file` VALUES (93, 'photo-1673825496543-b9609b997baf.webp', 'webp', 620, 'http://localhost:9090/api/file/7342a76b1ab6446f8618ff36ea6dc9b3.webp', '54f5b7bccff7ced1993c10a30eab334a', 0, 1, '2024-03-12 20:45:16');
INSERT INTO `file` VALUES (94, 'photo-1682463927638-47b4ec887b19.webp', 'webp', 246, 'http://localhost:9090/api/file/a33123b4258c4717941c82aaf20cd2b3.webp', '1dda31d20cd7d78b0dc42bfe1bed3668', 0, 1, '2024-03-12 20:45:16');
INSERT INTO `file` VALUES (95, 'photo-1664911978738-edcc6fb3cf3d.webp', 'webp', 767, 'http://localhost:9090/api/file/75dbede56ba14db8bdca30ac191c19dd.webp', 'd8e119cf25318fb54eaa0dd4ce56df81', 0, 1, '2024-03-12 20:45:16');
INSERT INTO `file` VALUES (96, 'photo-1665598765894-e0c0321aeaca.webp', 'webp', 1121, 'http://localhost:9090/api/file/7af378c8bdcd42499db82fa47a9183a3.webp', 'b8418bd8134dda0d68163c78ddca6d6b', 0, 1, '2024-03-12 20:45:16');
INSERT INTO `file` VALUES (97, '118399792628477.gif', 'gif', 21, 'http://localhost:9090/api/file/1175e94a07ed4e449e4c4681f83506e7.gif', 'aa35530db88878d1d03c2d3ac25639c0', 0, 1, '2024-03-15 11:37:44');
INSERT INTO `file` VALUES (98, '118403921899152.jpg', 'jpg', 53, 'http://localhost:9090/api/file/f15dfaa77fce48a2a3e6700c1fe94e70.jpg', '67486a88c51308361f0f08f48b94e839', 0, 1, '2024-03-15 11:38:05');
INSERT INTO `file` VALUES (99, '118399675172800.jpg', 'jpg', 5, 'http://localhost:9090/api/file/d210e96c7e5b423697cdf02b541519fb.jpg', '2825d6ef19775e0e1418614369611aa7', 0, 1, '2024-03-15 11:45:38');
INSERT INTO `file` VALUES (100, '118402012638789.gif', 'gif', 10, 'http://localhost:9090/api/file/cbc99ac1987841bda7f71c1545345fba.gif', 'b8952441380c0ca730291517d24bdb30', 0, 1, '2024-03-16 16:58:45');
INSERT INTO `file` VALUES (101, '1184010721848test.gif', 'gif', 256, 'http://localhost:9090/api/file/0d9109cfd0e0429cb34e8c292bebb1ec.gif', '52a6cc89098a3592a679895c5a324947', 0, 1, '2024-04-07 19:36:32');
INSERT INTO `file` VALUES (102, '118400135185560.jpg', 'jpg', 9, 'http://localhost:9090/api/file/13767713297b4101bd92f087a1f81470.jpg', 'a747b0a417a8f94fbb76fdf515f4166e', 0, 1, '2024-04-21 21:29:47');
INSERT INTO `file` VALUES (103, '118400135185560.jpg', 'jpg', 9, 'http://localhost:9090/api/file/13767713297b4101bd92f087a1f81470.jpg', 'a747b0a417a8f94fbb76fdf515f4166e', 0, 1, '2024-04-21 21:30:02');
INSERT INTO `file` VALUES (104, '118400135185560.jpg', 'jpg', 9, 'http://localhost:9090/api/file/13767713297b4101bd92f087a1f81470.jpg', 'a747b0a417a8f94fbb76fdf515f4166e', 0, 1, '2024-04-21 21:32:46');
INSERT INTO `file` VALUES (105, '118400135185560.jpg', 'jpg', 9, 'http://localhost:9090/api/file/13767713297b4101bd92f087a1f81470.jpg', 'a747b0a417a8f94fbb76fdf515f4166e', 0, 1, '2024-04-21 21:33:26');
INSERT INTO `file` VALUES (106, '118400135185560.jpg', 'jpg', 9, 'http://localhost:9090/api/file/13767713297b4101bd92f087a1f81470.jpg', 'a747b0a417a8f94fbb76fdf515f4166e', 0, 1, '2024-04-21 21:34:03');
INSERT INTO `file` VALUES (107, '118400135185560.jpg', 'jpg', 9, 'http://localhost:9090/api/file/13767713297b4101bd92f087a1f81470.jpg', 'a747b0a417a8f94fbb76fdf515f4166e', 0, 1, '2024-04-21 21:36:30');
INSERT INTO `file` VALUES (108, '118400135185560.jpg', 'jpg', 9, 'http://localhost:9090/api/file/13767713297b4101bd92f087a1f81470.jpg', 'a747b0a417a8f94fbb76fdf515f4166e', 0, 1, '2024-04-21 21:39:36');
INSERT INTO `file` VALUES (109, '118400135185560.jpg', 'jpg', 9, 'http://localhost:9090/api/file/13767713297b4101bd92f087a1f81470.jpg', 'a747b0a417a8f94fbb76fdf515f4166e', 0, 1, '2024-04-21 21:40:11');
INSERT INTO `file` VALUES (110, '118403889851913.gif', 'gif', 2, 'http://localhost:9090/api/file/a07247a613844629897a7e6caee7335f.gif', '63af70b209fb55d26c9d99afb7381070', 0, 1, '2024-04-21 21:40:39');
INSERT INTO `file` VALUES (111, '跨域问题.jpg', 'jpg', 34, 'http://localhost:9090/api/file/8e4bbc71f3ec484fa974c1911f2b67fb.jpg', 'f82b69139b5b7e526343503af2594ef2', 0, 1, '2024-04-24 15:41:57');
INSERT INTO `file` VALUES (112, 'Hutool.jpg', 'jpg', 30, 'http://localhost:9090/api/file/825430ac8105443ab50b6cf5bd6be8b7.jpg', '46530b2f2a2530d92a0450b250b48220', 0, 1, '2024-04-24 16:09:21');
INSERT INTO `file` VALUES (113, 'spring-boot.jpg', 'jpg', 11, 'http://localhost:9090/api/file/aba92a0df1ee4da6b4d47632c262ffc8.jpg', 'b8aeccb565dfb7255599b7dad0d5b737', 0, 1, '2024-04-24 16:23:47');
INSERT INTO `file` VALUES (114, 'QQ截图20240424163430.png', 'png', 50, 'http://localhost:9090/api/file/1e100d73592142bb906731a4c28810cd.png', '7342f90bd8d9c2e43ac9f75c4fcec0c9', 0, 1, '2024-04-24 16:34:50');
INSERT INTO `file` VALUES (115, '5cce43bbac2b27cd0d1e7839e91d115b.png', 'png', 230, 'http://localhost:9090/api/file/1257494427f0435da4eca81f9698354e.png', 'b874bfe982acd2aafb2009b7fbc6f286', 0, 1, '2024-04-24 16:45:56');
INSERT INTO `file` VALUES (116, '2318345f0c7b4a61fbb.jpg', 'jpg', 94, 'http://localhost:9090/api/file/c2dec9dc00c14e6dbbe3f218e5dc5ea7.jpg', '2c3d6fa6767964a39bffd525a30b8884', 0, 1, '2024-04-24 17:34:41');
INSERT INTO `file` VALUES (117, '411ab55be7b63d3ee76a8cd954c36aac.jpg', 'jpg', 83, 'http://localhost:9090/api/file/38492c20405f488fba08a82f20366ffe.jpg', 'c4944307094fb93708478b8fd4fb7b1a', 0, 1, '2024-04-24 18:16:52');

-- ----------------------------
-- Table structure for leave_word
-- ----------------------------
DROP TABLE IF EXISTS `leave_word`;
CREATE TABLE `leave_word`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '留言人名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '留言内容',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '回复人ID',
  `replied` tinyint(1) NULL DEFAULT 0 COMMENT '是否回复',
  `reply_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '回复内容',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否展示',
  `reply_time` datetime NULL DEFAULT NULL COMMENT '回复时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '留言表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave_word
-- ----------------------------
INSERT INTO `leave_word` VALUES (1, '玩玩', '好难啊', '127.0.0.1', NULL, 0, NULL, 1, NULL, '2024-03-19 15:29:07');
INSERT INTO `leave_word` VALUES (2, '玩玩', '好难啊', '127.0.0.1', NULL, 0, NULL, 1, NULL, '2024-03-19 15:29:07');
INSERT INTO `leave_word` VALUES (7, 'dfasdf', 'asdfasdf', '127.0.0.1', 2, 1, '44444444444', 1, '2024-03-30 15:25:29', '2024-03-19 17:49:29');
INSERT INTO `leave_word` VALUES (8, 'dsfa', 'asdf', '127.0.0.1', 2, 1, '111111', 1, '2024-03-30 15:25:12', '2024-03-19 17:49:58');
INSERT INTO `leave_word` VALUES (10, 'asdf', 'asdf', '127.0.0.1', 2, 1, 'sdfasdf', 1, '2024-03-19 22:44:46', '2024-03-19 17:53:47');
INSERT INTO `leave_word` VALUES (11, 'asdfasdf', 'asdfasdfasdf', '127.0.0.1', 2, 1, 'sdfsdfasdfasdf', 1, '2024-03-19 22:44:27', '2024-03-19 17:53:50');
INSERT INTO `leave_word` VALUES (12, 'asdfasdf', 'asdfasdf', '127.0.0.1', 2, 1, 'sdfasdfasdfasdf', 1, '2024-03-19 22:43:40', '2024-03-19 17:53:53');
INSERT INTO `leave_word` VALUES (13, 'asdfsadfas', 'asdfasdfasdf', '127.0.0.1', 2, 1, 'asdfasdfdfgsd11111', 1, '2024-04-21 22:41:13', '2024-03-19 17:53:57');
INSERT INTO `leave_word` VALUES (14, 'asdfasdfas', 'asdfasdfasdf', '127.0.0.1', 2, 1, '是打发斯蒂芬', 1, '2024-03-19 22:42:02', '2024-03-19 17:54:00');
INSERT INTO `leave_word` VALUES (20, 'tttttttt', 'ttttttttttttttttt', '127.0.0.1', NULL, 0, NULL, 1, NULL, '2024-03-30 20:21:25');
INSERT INTO `leave_word` VALUES (21, 'ww', '不登录测试', '127.0.0.1', NULL, 0, NULL, 1, NULL, '2024-04-14 21:24:20');
INSERT INTO `leave_word` VALUES (22, '我啊', 'restful测试', '127.0.0.1', 2, 1, '111111111', 1, '2024-04-21 22:40:43', '2024-04-18 23:01:51');
INSERT INTO `leave_word` VALUES (23, 'kkk', 'jjjjjjj', '127.0.0.1', NULL, 0, NULL, 0, NULL, '2024-04-19 12:36:57');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `sort_num` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '路由菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '后台主页', '/manage/backhome', 'el-icon-house', '后台主页', 1, NULL, 'BackHome');
INSERT INTO `menu` VALUES (2, '数据报表', '/manage/dashbord', 'el-icon-pie-chart', '统计数据', 2, NULL, 'DashBord');
INSERT INTO `menu` VALUES (9, '系统管理', '', 'el-icon-menu', '系统管理', 100, NULL, NULL);
INSERT INTO `menu` VALUES (10, '用户管理', '/manage/user', 'el-icon-user', '用户管理', 101, 9, 'User');
INSERT INTO `menu` VALUES (11, '角色管理', '/manage/role', 'el-icon-s-custom', '角色管理', 102, 9, 'Role');
INSERT INTO `menu` VALUES (12, '菜单管理', '/manage/menu', 'el-icon-s-grid', '菜单管理', 103, 9, 'Menu');
INSERT INTO `menu` VALUES (13, '文件管理', '/manage/file', 'el-icon-file', '文件管理', 104, 9, 'File');
INSERT INTO `menu` VALUES (15, '小站管理', '', 'el-icon-user', '管理网站内容', 50, NULL, NULL);
INSERT INTO `menu` VALUES (16, '主页管理', '/manage/home', 'el-icon-house', '小站主页', 51, 15, 'Home');
INSERT INTO `menu` VALUES (17, '文章管理', '/manage/article', 'el-icon-house', '管理文章', 52, 15, 'Article');
INSERT INTO `menu` VALUES (19, '留言板管理', '/manage/guestbook', 'el-icon-house', '管理留言', 54, 15, 'GuestBook');
INSERT INTO `menu` VALUES (20, '高德地图', '/manage/map', 'el-icon-user', '高德地图', 4, NULL, 'Map');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `flag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '管理员', 'ADMIN');
INSERT INTO `role` VALUES (2, '游客', '游客', 'VISTOR');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 9);
INSERT INTO `role_menu` VALUES (1, 10);
INSERT INTO `role_menu` VALUES (1, 11);
INSERT INTO `role_menu` VALUES (1, 12);
INSERT INTO `role_menu` VALUES (1, 13);
INSERT INTO `role_menu` VALUES (1, 14);
INSERT INTO `role_menu` VALUES (1, 15);
INSERT INTO `role_menu` VALUES (1, 16);
INSERT INTO `role_menu` VALUES (1, 17);
INSERT INTO `role_menu` VALUES (1, 18);
INSERT INTO `role_menu` VALUES (1, 19);
INSERT INTO `role_menu` VALUES (1, 20);
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (2, 20);
INSERT INTO `role_menu` VALUES (3, 1);
INSERT INTO `role_menu` VALUES (3, 2);
INSERT INTO `role_menu` VALUES (3, 14);
INSERT INTO `role_menu` VALUES (4, 1);
INSERT INTO `role_menu` VALUES (4, 2);
INSERT INTO `role_menu` VALUES (4, 14);
INSERT INTO `role_menu` VALUES (5, 1);
INSERT INTO `role_menu` VALUES (5, 2);
INSERT INTO `role_menu` VALUES (5, 20);

-- ----------------------------
-- Table structure for slideshow
-- ----------------------------
DROP TABLE IF EXISTS `slideshow`;
CREATE TABLE `slideshow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '轮播图名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '轮播图地址',
  `sort_num` int(11) NULL DEFAULT NULL COMMENT '序号',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '启用',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '轮播图表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of slideshow
-- ----------------------------
INSERT INTO `slideshow` VALUES (1, '图一', 'http://localhost:9090/api/file/c90708352b7d4037bd302f8958d4e4a1.webp', 1, 0, '2024-04-23 22:06:12', '2024-03-12 19:03:15');
INSERT INTO `slideshow` VALUES (2, 'springboot', 'https://cdn.educba.com/academy/wp-content/uploads/2019/04/what-is-spring-boot.jpg', 2, 1, '2024-04-23 22:06:29', '2024-03-12 19:40:13');
INSERT INTO `slideshow` VALUES (3, '图三', 'http://localhost:9090/api/file/7af378c8bdcd42499db82fa47a9183a3.webp', 3, 0, '2024-04-23 22:05:25', '2024-03-12 20:47:45');
INSERT INTO `slideshow` VALUES (4, '图四', 'https://swansoftwaresolutions.com/wp-content/uploads/2020/01/Benefits-of-Using-VUE.JS.jpeg', 4, 1, '2024-04-23 22:06:27', '2024-03-12 20:48:09');
INSERT INTO `slideshow` VALUES (5, '图五', 'http://localhost:9090/api/file/a33123b4258c4717941c82aaf20cd2b3.webp', 5, 0, '2024-04-23 22:05:26', '2024-03-12 20:49:53');
INSERT INTO `slideshow` VALUES (6, '图六', 'http://localhost:9090/api/file/0a0bd4eabe664a33876965e99729307f.webp', 6, 0, '2024-04-23 22:05:27', '2024-03-12 20:50:06');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名字',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (-1812287487, '罗书江', 22, '2024-03-09 20:20:35', '2024-03-09 20:20:35');
INSERT INTO `test` VALUES (-1308971006, '罗书江', 22, '2024-03-09 20:21:57', '2024-03-09 20:21:57');
INSERT INTO `test` VALUES (-797261823, '罗书江', 22, '2024-03-09 20:47:40', '2024-03-09 20:47:40');
INSERT INTO `test` VALUES (-621072382, '罗书江', 22, '2024-03-09 20:25:56', '2024-03-09 20:25:56');
INSERT INTO `test` VALUES (1, '罗书江', 22, '2024-03-09 20:24:40', '2024-03-09 20:24:40');
INSERT INTO `test` VALUES (1014640641, '罗书江', 22, '2024-03-09 20:26:40', '2024-03-09 20:26:40');
INSERT INTO `test` VALUES (1140482050, '罗书江', 22, '2024-03-09 20:27:05', '2024-03-09 20:27:05');
INSERT INTO `test` VALUES (1706754049, '罗书江', 12, '2024-03-09 20:28:28', '2024-03-09 21:12:55');
INSERT INTO `test` VALUES (1991942146, '罗书江', 22, '2024-03-09 20:27:58', '2024-03-09 20:27:58');
INSERT INTO `test` VALUES (2021314562, '罗书江', 22, '2024-03-09 20:25:23', '2024-03-09 20:25:23');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `role_id` int(11) NOT NULL DEFAULT 2 COMMENT '角色',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像路径',
  `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生日',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `recently_landed` timestamp NULL DEFAULT NULL COMMENT '最近登陆时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wanwan', 1, 'admin', '玩玩', '3491454639@qq.com', '19918627905', '湖南', 'http://localhost:9090/api/file/237c3a2dac8a4ec0a0193e2a2c78833d.jpg', NULL, NULL, NULL, '2024-08-20 21:38:45', NULL);
INSERT INTO `user` VALUES (2, 'admin', 1, 'admin', '管理员', 'admin@qq.com', '110', 'admin', 'http://localhost:9090/api/file/8b38cffce1f142e193476317796d9f7d.gif', NULL, NULL, NULL, '2024-10-24 14:53:00', '2024-04-30 16:26:04');
INSERT INTO `user` VALUES (3, 'admin1', 1, 'admin', '管理员1', 'admin1@qq.com', '19918627905', 'admin1', 'http://localhost:9090/api/file/d210e96c7e5b423697cdf02b541519fb.jpg', NULL, NULL, NULL, '2024-03-18 16:19:10', NULL);
INSERT INTO `user` VALUES (4, 'sha', 2, '123456', '沙悟净', 'sha@qq.com', '19918627905', '流沙河', NULL, NULL, NULL, NULL, '2024-04-18 16:19:21', NULL);
INSERT INTO `user` VALUES (10, '马芳', 2, '123456', '戴霞', 'z.iqpiwt@qq.com', '13423838448', '广西壮族自治区澳门半岛万州区', NULL, NULL, NULL, NULL, '2024-05-18 16:20:08', NULL);
INSERT INTO `user` VALUES (11, '孟静', 2, '123456', '萧秀兰', 'y.jrambc@qq.com', '13471683851', '贵州省林芝地区惠农区', NULL, NULL, NULL, NULL, '2024-06-19 21:45:23', NULL);
INSERT INTO `user` VALUES (12, 'tang', 2, '123456', '唐僧', 'tang@qq.com', '110', '大唐', 'http://localhost:9090/api/file/204a1a1231e34aeaba0ffecb5f0f201f.jpg', NULL, NULL, NULL, '2024-07-19 21:49:28', NULL);
INSERT INTO `user` VALUES (19, 'sun', 2, '123456', '孙悟空', 'sun@qq.com', '19918627905', '花果山', NULL, NULL, NULL, NULL, '2024-01-15 21:22:07', NULL);

-- ----------------------------
-- Table structure for user_article_like
-- ----------------------------
DROP TABLE IF EXISTS `user_article_like`;
CREATE TABLE `user_article_like`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `article_id` int(11) NOT NULL COMMENT '文章ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`, `article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户点赞关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_article_like
-- ----------------------------
INSERT INTO `user_article_like` VALUES (2, 1, '2024-04-19 18:08:54');
INSERT INTO `user_article_like` VALUES (2, 7, '2024-04-19 18:10:32');
INSERT INTO `user_article_like` VALUES (2, 27, '2024-04-24 17:17:34');
INSERT INTO `user_article_like` VALUES (2, 28, '2024-04-24 17:48:27');
INSERT INTO `user_article_like` VALUES (2, 29, '2024-04-24 17:48:22');
INSERT INTO `user_article_like` VALUES (2, 36, '2024-03-15 20:07:22');
INSERT INTO `user_article_like` VALUES (2, 41, '2024-03-13 23:24:29');
INSERT INTO `user_article_like` VALUES (2, 42, '2024-03-14 09:34:50');
INSERT INTO `user_article_like` VALUES (2, 43, '2024-04-08 21:48:28');
INSERT INTO `user_article_like` VALUES (2, 44, '2024-04-09 19:58:44');

-- ----------------------------
-- Table structure for user_comment_like
-- ----------------------------
DROP TABLE IF EXISTS `user_comment_like`;
CREATE TABLE `user_comment_like`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `comment_id` int(11) NOT NULL COMMENT '评论ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`, `comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户评论关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_comment_like
-- ----------------------------
INSERT INTO `user_comment_like` VALUES (2, 28, '2024-03-16 23:22:44');
INSERT INTO `user_comment_like` VALUES (2, 30, '2024-03-16 23:22:40');
INSERT INTO `user_comment_like` VALUES (2, 36, '2024-03-16 23:22:44');
INSERT INTO `user_comment_like` VALUES (2, 37, '2024-03-16 23:22:47');
INSERT INTO `user_comment_like` VALUES (2, 38, '2024-03-16 23:22:46');
INSERT INTO `user_comment_like` VALUES (2, 39, '2024-03-16 23:22:37');
INSERT INTO `user_comment_like` VALUES (2, 40, '2024-03-16 23:22:39');
INSERT INTO `user_comment_like` VALUES (2, 41, '2024-03-16 23:22:34');
INSERT INTO `user_comment_like` VALUES (2, 53, '2024-03-16 23:22:37');
INSERT INTO `user_comment_like` VALUES (2, 54, '2024-03-16 23:22:36');
INSERT INTO `user_comment_like` VALUES (2, 55, '2024-03-16 23:22:35');
INSERT INTO `user_comment_like` VALUES (2, 56, '2024-03-16 23:22:38');
INSERT INTO `user_comment_like` VALUES (2, 57, '2024-03-16 23:22:43');
INSERT INTO `user_comment_like` VALUES (2, 58, '2024-03-16 23:22:42');
INSERT INTO `user_comment_like` VALUES (2, 59, '2024-03-16 23:22:32');
INSERT INTO `user_comment_like` VALUES (2, 60, '2024-03-16 23:22:30');
INSERT INTO `user_comment_like` VALUES (2, 61, '2024-03-16 23:22:29');
INSERT INTO `user_comment_like` VALUES (2, 62, '2024-03-16 23:22:28');
INSERT INTO `user_comment_like` VALUES (2, 63, '2024-03-17 22:46:48');
INSERT INTO `user_comment_like` VALUES (2, 68, '2024-03-16 20:08:50');
INSERT INTO `user_comment_like` VALUES (2, 69, '2024-03-16 23:22:27');
INSERT INTO `user_comment_like` VALUES (2, 70, '2024-03-16 23:22:26');
INSERT INTO `user_comment_like` VALUES (2, 71, '2024-03-16 23:22:26');
INSERT INTO `user_comment_like` VALUES (2, 72, '2024-03-16 23:22:24');
INSERT INTO `user_comment_like` VALUES (2, 73, '2024-03-16 23:22:24');
INSERT INTO `user_comment_like` VALUES (2, 74, '2024-03-16 23:22:23');
INSERT INTO `user_comment_like` VALUES (2, 75, '2024-03-16 23:22:22');
INSERT INTO `user_comment_like` VALUES (2, 76, '2024-03-16 23:22:22');
INSERT INTO `user_comment_like` VALUES (2, 77, '2024-03-16 23:22:21');
INSERT INTO `user_comment_like` VALUES (2, 86, '2024-03-16 23:22:20');
INSERT INTO `user_comment_like` VALUES (2, 87, '2024-03-16 23:22:19');
INSERT INTO `user_comment_like` VALUES (2, 88, '2024-03-16 23:22:19');
INSERT INTO `user_comment_like` VALUES (2, 89, '2024-03-16 23:22:18');
INSERT INTO `user_comment_like` VALUES (2, 90, '2024-03-16 23:22:17');
INSERT INTO `user_comment_like` VALUES (2, 91, '2024-03-16 23:22:16');
INSERT INTO `user_comment_like` VALUES (2, 92, '2024-03-16 23:22:15');
INSERT INTO `user_comment_like` VALUES (2, 93, '2024-03-16 23:22:14');
INSERT INTO `user_comment_like` VALUES (2, 94, '2024-03-16 23:17:51');
INSERT INTO `user_comment_like` VALUES (2, 95, '2024-03-16 23:14:30');
INSERT INTO `user_comment_like` VALUES (2, 105, '2024-03-16 23:23:27');
INSERT INTO `user_comment_like` VALUES (2, 106, '2024-03-16 23:23:26');
INSERT INTO `user_comment_like` VALUES (2, 110, '2024-03-17 20:15:28');
INSERT INTO `user_comment_like` VALUES (2, 111, '2024-03-16 23:22:13');
INSERT INTO `user_comment_like` VALUES (2, 112, '2024-03-16 23:25:16');
INSERT INTO `user_comment_like` VALUES (2, 113, '2024-03-16 23:26:49');
INSERT INTO `user_comment_like` VALUES (2, 115, '2024-03-17 20:19:59');
INSERT INTO `user_comment_like` VALUES (2, 165, '2024-03-17 23:23:06');
INSERT INTO `user_comment_like` VALUES (2, 166, '2024-03-17 23:23:04');
INSERT INTO `user_comment_like` VALUES (2, 167, '2024-03-17 23:23:05');
INSERT INTO `user_comment_like` VALUES (2, 168, '2024-03-17 23:25:22');
INSERT INTO `user_comment_like` VALUES (2, 170, '2024-03-17 23:23:04');
INSERT INTO `user_comment_like` VALUES (2, 173, '2024-03-17 23:30:10');
INSERT INTO `user_comment_like` VALUES (2, 174, '2024-03-17 23:30:10');
INSERT INTO `user_comment_like` VALUES (2, 175, '2024-03-17 23:30:09');
INSERT INTO `user_comment_like` VALUES (2, 176, '2024-03-17 23:30:08');
INSERT INTO `user_comment_like` VALUES (2, 179, '2024-04-08 21:41:16');
INSERT INTO `user_comment_like` VALUES (2, 207, '2024-04-08 21:50:04');
INSERT INTO `user_comment_like` VALUES (2, 208, '2024-03-18 14:47:32');
INSERT INTO `user_comment_like` VALUES (2, 209, '2024-03-18 14:47:30');
INSERT INTO `user_comment_like` VALUES (2, 279, '2024-04-21 00:52:03');
INSERT INTO `user_comment_like` VALUES (2, 299, '2024-04-22 21:35:15');
INSERT INTO `user_comment_like` VALUES (2, 302, '2024-04-22 22:05:07');
INSERT INTO `user_comment_like` VALUES (2, 303, '2024-04-22 22:05:05');
INSERT INTO `user_comment_like` VALUES (2, 306, '2024-04-24 17:48:48');

SET FOREIGN_KEY_CHECKS = 1;
