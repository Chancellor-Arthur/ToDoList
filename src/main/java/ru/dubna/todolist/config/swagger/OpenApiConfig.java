package ru.dubna.todolist.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "ToDo List API", version = "1.0.0"))
@SecurityScheme(name = "jsessionid", in = SecuritySchemeIn.COOKIE, type = SecuritySchemeType.APIKEY, paramName = "JSESSIONID")
public class OpenApiConfig {
}
