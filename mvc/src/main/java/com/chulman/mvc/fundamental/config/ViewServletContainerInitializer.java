package com.chulman.mvc.fundamental.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

// 웹 배포 서술자의 역할 (ServletContainerInitialzer or web.xml)
//여기서 정의한 dispatcher servlet은 "/" 경로 하위의 모든 url을 매핑한다.
public class ViewServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ViewResolverConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic courtRegistration = ctx.addServlet("court", dispatcherServlet);
        courtRegistration.setLoadOnStartup(1);
        courtRegistration.addMapping("/");
    }

}
