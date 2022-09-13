# ddns
阿里云动态域名解析DDNS

## 简介
本项目基于Spring Boot和阿里云SDK实现，使用jib插件打包docker镜像

实质为阿里云SDK配置域名解析的一层包装，实现功能

- 查询所有（其实只查1次，1次100,）DNS配置

> http://ip:port/ddns/info

- 新增DNS配置

> http://ip:port/ddns/add

- 修改DNS配置

> http://ip:port/ddns/update

- 删除DNS配置

> http://ip:port/ddns/delete

## 运行

### 服务端(docker部署)

导入镜像

 `docker load < jib-image.tar`
 
 修改 start.sh 脚本参数
 
 `endpoint: 阿里云sdk服务地址，比如 alidns.cn-chengdu.aliyuncs.com`
 
 `accessKeyId: 阿里云accessKeyId`
 
 `accessKeySecret: 阿里云accessKeySecret`
 
 `domainName: 要管理的域名`
 
 执行 start.sh 脚本创建并启动一个容器
 
 ### 客户端（在需要ddns的机器上执行）
 
 实质为curl模拟http请求
 
 修改ddns.sh（linux系统）/ddns.bat（windows系统）
 
 `ipv6RR: 子域名`
 
 `ipv6Url: 获取本机公网ipv6地址，可修改，建议不动`
 
 `ipv6ConfIP: docker服务端ip端口（端口80可省略，其他端口格式ip:port）`
 
 然后执行ddns脚本即可
 
## 额外说明

- 默认操作ipv6映射，毕竟ipv4公网难搞，ipv6公网已经普及

- ipv6公网检测地址

> http://test6.ustc.edu.cn/backend/getIP.php

> http://speed.neu6.edu.cn/getIP.php

- 本服务也可以操作ipv4，需要修改ddns脚本，把type=AAAA换为type=A即可
