package com.ch1ppy.springboot_back_exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket desertsApi(){
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ch1ppy.springboot_back_exam.controller"))
				.paths(PathSelectors.any())
				.build()
				.groupName("desertsGroup")
				.enable(true);
	}

	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("Swagger3测试文档")
				.description("文档描述信息")
				.contact(new Contact("deserts", "#", "2569509245@qq.com"))
				.version("1.0")
				.build();
	}
}
