#!/usr/bin/env bash

# docker
REGISTRY=registry.cn-hangzhou.aliyuncs.com/leonzhangxf
TAG=`date "+%Y%m%d%H%M%S"`

# 构建项目
echo "Maven build start..."
mvn -U clean package
echo "Maven build end..."

# 构建模块
MODULES=(
account-dist
gateway-dist
)

for ((i=0;i<${#MODULES[*]};i++)); do
    echo "Docker build start..."
    docker build -t ${REGISTRY}/${MODULES[$i]}:${TAG} ./${MODULES[$i]}
    echo "Docker build end..."

    echo "Docker push start..."
    # docker push ${REGISTRY}/${MODULES[$i]}:${TAG}
    echo "Docker push end..."
done

# kube部署文件替换
DEPLOY_FILE=$(find ./ -name kube.yaml)
sed -i "s|{{ TAG }}|${TAG}|g" ${DEPLOY_FILE}
sed -i "s|${REGISTRY}|${REGISTRY}|g" ${DEPLOY_FILE}