spring-cloud-kubernetes-demo
============================

## 介绍

spring cloud kubernetes实现的使用Demo

官方文档参见[Spring Cloud Kubernetes](https://cloud.spring.io/spring-cloud-static/spring-cloud-kubernetes/1.0.2.RELEASE/single/spring-cloud-kubernetes.html)

## 软件架构

软件架构说明

使用spring cloud框架，服务组件实现采用kubernetes


## 安装教程

### 1. kubernetes安装

根据官方文档安装docker和kubernetes。可以参见docker或kubernetes官方网站相关SETUPS，本文档不再详述。

#### 1.1 管理界面&管理员用户

##### 1.1.1 管理员用户

[参见官方文档](https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/)

```kubernetes
kubectl apply -f dashboard-adminuser.yaml
```

##### 1.1.2 管理界面

```bash
kubectl proxy --address=0.0.0.0
```

> 注意此命令需要挂载到后台，不然会被销毁

本地web-ui启动后访问地址：

http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

#### 1.2 CoreDNS & ingress-nginx controller

##### 1.2.1 CoreDNS

To install CoreDNS refer to 
[Use CoreDNS in kubernetes](https://github.com/coredns/deployment/tree/master/kubernetes)

##### 1.2.2 ingress-nginx controller

参见文档 [ingress-nginx](https://kubernetes.github.io/ingress-nginx/deploy/)

Deploy ingress-nginx controller

```bash
kubectl apply -f deploy_ing_nginx.yaml
```

Attach service to the deploy

```bash
kubectl apply -f svc_nginx.yaml
```

Test the config

1. add a ingress (startup a account service at first)

```bash
kubectl apply -f ing_test.yaml
```

2. add a hosts record to local `hosts` file

```text
127.0.0.1 account.leonzhangxf.com
```

3. use curl to testify the config 

```bash
curl http://account.leonzhangxf.com
```

### 2. 本地启动查看Demo

#### 2.1 构建相关Demo模块以及镜像

在项目根目录下，执行以下脚本。Mac请执行`build-max.sh`脚本。

```bash
./build.sh
```

#### 2.2 部署相关服务

1. 通过一个统一的编排文件部署响应的服务:

```bash
kubectl apply -f kube.yaml
```

> 注：过程中可能出现namespace无法创建的情况，请根据以下脚本，手动创建。`leonzhangxf`为示例的命名空间

```bash
kubectl create ns leonzhangxf
```

2. 部署ingress反向代理，提供部署服务的入口

```bash
kubectl apply -f ing_kube.yaml
```

#### 2.3 外部配置并访问

1. 本地配置hosts

> 此步骤可以跳过，直接访问localhost

```text
127.0.0.1 account.leonzhangxf.com
127.0.0.1 gateway.leonzhangxf.com
```

2. 访问网关服务

访问`gateway.leonzhangxf.com`或`localhost`都将代理到网关服务。

点击页面中的`API Docs`链接，跳转到swagger文档页面，分别测试服务注册与发现、负载均衡功能，中心化配置功能。

> 配置更新基于kubernetes的ConfigMap Resource，可以通过命令、API、Admin WebUI进行服务配置的更新。

---

## Appendix

### 1. 本地调试指南

需要本地调试某个服务时，为了保证对依赖服务调用的正常。请启动`spring.profiles.active=local`的配置。
在对应的配置文件中，禁用掉Spring Cloud Kubernetes（以下简称 SCKube）ribbon组件适配器。
并手动配置对应的依赖服务的`ribbon.listOfServers`。

> 注：`ribbon.listOfServers`中的域名配置需要结合kube ingress提供服务的外部暴露。
> 可以参考[`1.2.2 ingress-nginx controller`](#1.2.2 ingress-nginx controller)以及`ing_kube.yaml`编排文件。

### 2 Spring Cloud Kubernetes 的服务注册与发现、负载均衡、中心化配置

Spring Cloud Kubernetes（以下简称 SCKube）的服务注册与发现与负载均衡本质上都是基于kubernetes的endpoint去实现的。

在服务启动后，当前服务会使用serviceId通过kubernetes API查询对应的endpoint，进而获取对应的SCHEMA、IP和PORT，
从而作为每个instance内部调用的URI。

类似的，在服务调用时如果使用了SCKube ribbon适配组件，则也将使用serviceId通过kubernetes API查询对应的endpoint，最后提供以供
负载均衡器进行选择调用的server list。因此，同样也是endpoint的IP和PORT。

而endpoint的IP和PORT都是在kube集群内部的DN（Doamin Name）地址。

在这种情况下，使用


### 2. 删除本地调试产生的多余的镜像

docker images | grep 镜像名称 | awk "{print $3}" | xargs docker rmi



## 参与贡献

1. Fork 本仓库
2. 新建 Feature_xxx 分支
3. 提交代码
4. 新建 Pull Request

## Author

leonzhangxf@gmail.com