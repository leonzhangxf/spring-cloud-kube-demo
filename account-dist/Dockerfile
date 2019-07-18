FROM openjdk:8-jdk-alpine

# 时区调整
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

VOLUME /tmp/logs

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-cp","app:/app/lib/*","com.leonzhangxf.cloud.kube.account.AccountDistApplication"]

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
