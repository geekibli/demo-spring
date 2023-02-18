package com.example.testspring.a20;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <a href="https://www.bilibili.com/video/BV1P44y1N7QG?p=65">...</a>
 */
@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({WebMvcProperties.class, ServerProperties.class})
public class WebConfig {


    /**
     * 内嵌web容器工厂
     *
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    /**
     * 创建  dispatcherServlet
     * <p>
     * Spring创建 有 tomcat来完成初始化
     *
     * @return
     */
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    /**
     * 、注册 dispatcherServlet SpringMVC的入口 dispatcherServlet注册到tomcat容器才可以运行
     *
     * @param dispatcherServlet
     * @return
     */
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet, WebMvcProperties webMvcProperties) {
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");

        int loadOnStartup = webMvcProperties.getServlet().getLoadOnStartup();
        System.out.printf(">>>>>>>>>>>>>>> loadOnStartup : %s", loadOnStartup);
        System.out.println("");
        // 在tomcat启动时，初始化DispatcherServlet
        registrationBean.setLoadOnStartup(loadOnStartup);
        return registrationBean;
    }

}
