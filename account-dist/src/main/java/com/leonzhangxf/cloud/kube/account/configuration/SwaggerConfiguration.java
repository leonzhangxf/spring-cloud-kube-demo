package com.leonzhangxf.cloud.kube.account.configuration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger集成配置。
 * 通过主程序引入该类，或者实例化来生效。
 * 注：需要配置相关配置。
 * <p>
 * 需要生成swagger文档的接口，需要在其类上声明 {@link Api} 注解，
 * 并在其接口方法上声明 {@link ApiOperation} 注解。
 *
 * @author leonzhangxf 20190718
 */
@Configuration
@ConfigurationProperties(prefix = "swagger.doc")
public class SwaggerConfiguration {

    private Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);

    /**
     * 是否开启swagger接口文档生成，默认为true
     * 项目上线之后一般不暴露swagger文档，设定为false
     */
    private Boolean enabled = true;

    private String title = "接口文档";

    private String desc = "接口文档";

    private String url = "https://leonzhangxf.com";

    private String host = null;

    private Principal contact = new Principal();

    private String version = "1.0.0";

    /**
     * @return swagger摘要bean
     */
    @Bean
    public Docket restApi() {
        Docket docket;
        if (enabled) {
            // 根据指定配置生成指定Controller的接口文档
            // 接口类上要声明 Api 注解
            // 接口方法上要声明 ApiOperation 注解
            // 所有的路径都进行扫描
            docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host(host)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
            logger.info("##### SWAGGER: choose to generate Swagger API User Interface Doc.");
        } else {
            // 所有的接口都不生成
            docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.none())
                .paths(PathSelectors.none())
                .build();
            logger.info("##### SWAGGER: choose [ not ] to generate Swagger API User Interface Doc.");
        }
        return docket;
    }

    /**
     * @return API文档主信息对象
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(title)
            .description(desc)
            // 服务地址
            .termsOfServiceUrl(url)
            .contact(new Contact(contact.getName(), contact.getUrl(), contact.getEmail()))
            .version(version)
            .build();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Principal getContact() {
        return contact;
    }

    public void setContact(Principal contact) {
        this.contact = contact;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 联系人实体
     */
    private static class Principal {

        /**
         * 联系人姓名
         */
        private String name = "leonzhangxf";

        /**
         * 联系网址
         */
        private String url = "https://leonzhangxf.com";

        /**
         * 联系邮箱
         */
        private String email = "leon_zhangxf@qq.com";

        Principal() {
        }

        public Principal(String name, String url, String email) {
            this.name = name;
            this.url = url;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
