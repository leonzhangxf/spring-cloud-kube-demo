#!/usr/bin/env bash

# docker
REGISTRY=registry.cn-hangzhou.aliyuncs.com/leonzhangxf
TAG=`date "+%Y%m%d%H%M%S"`
# kube
NAMESPACE=leonzhangxf

# 构建项目
echo "Maven build start..."
mvn -U clean package
echo "Maven build end..."

# 构建并上传模块镜像
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

    # kube部署文件替换
    KUBE_FILE=$(find ./${MODULES[$i]}/ -name kube.yaml)
    sed -i.bak "s|{{ TAG }}|${TAG}|g" ${KUBE_FILE}
    sed -i.bak "s|{{ REGISTRY }}|${REGISTRY}|g" ${KUBE_FILE}
    sed -i.bak "s|{{ NAMESPACE }}|${NAMESPACE}|g" ${KUBE_FILE}

done

# kube部署文件替换
KUBE_FILE=$(find ./ -name kube.yaml)
sed -i.bak "s|{{ TAG }}|${TAG}|g" ${KUBE_FILE}
sed -i.bak "s|{{ REGISTRY }}|${REGISTRY}|g" ${KUBE_FILE}
sed -i.bak "s|{{ NAMESPACE }}|${NAMESPACE}|g" ${KUBE_FILE}