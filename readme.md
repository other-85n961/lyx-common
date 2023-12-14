# 概述
我的项目通用的东西.  
这个项目会被打成jar包供其他项目引用

# 这个项目引入其他依赖
```xml
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.21</version>
</dependency>
```

# jar包部署
此项目打成的jar部署到[阿里云云效制品仓库](https://packages.aliyun.com/repos/2442054-release-Zn6jzO/packages)  
部署命令`mvn clean install org.apache.maven.plugins:maven-deploy-plugin:2.8:deploy -DskipTests --settings /Users/leeyx/my/software/maven/apache-maven-3.9.5/conf/settings_aliyun_package.xml`