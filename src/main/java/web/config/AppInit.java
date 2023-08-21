package web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }


    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }


    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    // Ниже два метода добавлены д/того, чтобы на стороне Спринга обрабатывалось скрытое hidden-поле "_method",
    // где находится реальный http-метод, который мы хотим использовать. В нашем случае - PATCH.
    //Это сделано д/того, чтобы корректно работал метод update в контроллере, у которого аннотация @PatchMapping ("/{id}")
    //Д/его корректной работы мы ниже создаем фильтр, который будет читать скрытое hidden-поле "_method",
    // значение которого будет PATCH
    //В Спринг Boot эти методы можно будет заменить одной строкой

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        super.onStartup(context);
        registerHiddenFilter(context);
    }

    public void registerHiddenFilter(ServletContext context) {
        context.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter())
                .addMappingForUrlPatterns(null, true, "/*");

        context.addFilter("characterEncodingFilter",
                        new CharacterEncodingFilter("UTF-8", true, true))
                .addMappingForUrlPatterns(null, false, "/*");
    }
}