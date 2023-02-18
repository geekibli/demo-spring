package com.example.testspring.a20;

public class A20_11 {



    /**
     *
     DispatcherServlet 初始化时做了什么事情？

     protected void initStrategies(ApplicationContext context) {
         this.initMultipartResolver(context);
         this.initLocaleResolver(context);
         this.initThemeResolver(context);

        // 请求路径的映射器
         this.initHandlerMappings(context);

        // 执行具体请求处理，请求的形式有很多种，需要适配不同形式的适配器方法，然后调用它
         this.initHandlerAdapters(context);

        // 解析请求过程中的异常
         this.initHandlerExceptionResolvers(context);
         this.initRequestToViewNameTranslator(context);
         this.initViewResolvers(context);
         this.initFlashMapManager(context);
     }



     */
}
