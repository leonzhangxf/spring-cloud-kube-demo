#!/usr/bin/env bash

# profiles
SPRING_PROFILES_ACTIVE=$1
if [[ SPRING_PROFILES_ACTIVE || -z SPRING_PROFILES_ACTIVE ]]; then
    # 默认dev环境
	SPRING_PROFILES_ACTIVE=dev
fi

# kube
NAMESPACE=$2
if [[ NAMESPACE || -z NAMESPACE ]]; then
    # 默认命名空间
	NAMESPACE=leonzhangxf
fi

# docker
REGISTRY=$3
if [[ REGISTRY || -z REGISTRY ]]; then
    # 默认仓库
	REGISTRY=registry.cn-hangzhou.aliyuncs.com/leonzhangxf
fi

TAG=$4
if [[ TAG || -z TAG ]]; then
    # 默认tag
	TAG=`date "+%Y%m%d%H%M%S"`
fi

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
    if [[ -e ${KUBE_FILE} ]]; then
        sed -i.bak "s|{{ TAG }}|${TAG}|g" ${KUBE_FILE}
        sed -i.bak "s|{{ REGISTRY }}|${REGISTRY}|g" ${KUBE_FILE}
        sed -i.bak "s|{{ NAMESPACE }}|${NAMESPACE}|g" ${KUBE_FILE}
        sed -i.bak "s|{{ SPRING_PROFILES_ACTIVE }}|${SPRING_PROFILES_ACTIVE}|g" ${SPRING_PROFILES_ACTIVE}
    fi

done

# kube部署文件替换
KUBE_FILE=$(find ./ -name kube.yaml)
if [[ -e ${KUBE_FILE} ]]; then
    sed -i.bak "s|{{ TAG }}|${TAG}|g" ${KUBE_FILE}
    sed -i.bak "s|{{ REGISTRY }}|${REGISTRY}|g" ${KUBE_FILE}
    sed -i.bak "s|{{ NAMESPACE }}|${NAMESPACE}|g" ${KUBE_FILE}
    sed -i.bak "s|{{ SPRING_PROFILES_ACTIVE }}|${SPRING_PROFILES_ACTIVE}|g" ${SPRING_PROFILES_ACTIVE}
fi