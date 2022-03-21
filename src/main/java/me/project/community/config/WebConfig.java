package me.project.community.config;

import me.project.community.interceptor.LoginInterceptor;
import me.project.community.session.UserInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    final
    UserInfo userInfo;

    public WebConfig(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor(userInfo);

        registry
                .addInterceptor(loginInterceptor)
                //LoginInterceptor 적용 URL
                .addPathPatterns("/*")
                //LoginInterceptor 제외 URL
                .excludePathPatterns("/boards")
                .excludePathPatterns("/users")
                .excludePathPatterns("/users/create")
                .excludePathPatterns("/users/login");

    }
}
