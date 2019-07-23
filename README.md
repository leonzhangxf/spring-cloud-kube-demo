spring-cloud-kube-demo
============================

## 介绍

spring cloud kube实现

## 软件架构

软件架构说明

使用spring cloud框架，服务组件实现采用kubernetes


## 安装教程

### 1. kube安装 

根据官方文档安装docker和kubernetes

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

## 使用说明

1. xxxx
2. xxxx
3. xxxx

docker images | grep none | awk "{print $3}" | xargs docker rmi

## 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request