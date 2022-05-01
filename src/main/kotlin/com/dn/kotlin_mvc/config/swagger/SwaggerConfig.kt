package com.dn.kotlin_mvc.config.swagger


import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.SwaggerUiConfigProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun springShopOpenAPI(): OpenAPI? {
        return OpenAPI()
            .info(
                Info().title("kotlin mvc")
                    .description("kotlin mvc application")
                    .version("v0.0.1")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
//            .components(Components().addSecuritySchemes("bearerAuth", securityScheme()))
//            .security(securityRequirement())
    }

//    https://localcoder.org/enable-authorize-button-in-springdoc-openapi-ui-for-bearer-token-authentication
//    fun securityScheme(): SecurityScheme? {
//        return SecurityScheme()
//            .type(SecurityScheme.Type.HTTP)
//            .scheme("bearer")
//            .bearerFormat("JWT")
//            .`in`(SecurityScheme.In.HEADER)
//            .name("Authorization")
//    }
//
//    fun securityRequirement(): List<SecurityRequirement> {
//        return listOf(SecurityRequirement().addList("bearerAuth"))
//    }

    // https://springdoc.org/faq.html
    @Bean
    fun swaggerUiConfig(config: SwaggerUiConfigProperties): SwaggerUiConfigProperties {
//        config.showCommonExtensions = true
//        config.queryConfigEnabled = true
        // 하단 schemas 제거
        config.defaultModelsExpandDepth = -1
        config.isDisableSwaggerDefaultUrl = true
        config.url = "/v3/api-docs"
        config.configUrl = "/v3/api-docs/swagger-config"

        return config
    }
}