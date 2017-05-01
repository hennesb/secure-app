package ie.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "ie.*" })
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter{
	
	public static final String UI_PATH = "/public/html/**";
	public static final String ANGULAR_UI_PATH = "/angular/**";
    public static final String SCRIPTS_URI_PATH = "/scripts/**";
	public static final String CSS_URI_PATH = "/css/**";
	public static final String NODE_MODULES = "/node_modules/**";
	public static final String CONTEXT_PATH = "/**";
		
	public static final String UI_RESOURCES = "file:/Users/hennesb/apps/static-content/html/";
	public static final String SCRIPT_RESOURCES = "file:/Users/hennesb/apps/static-content/scripts/";
	public static final String CSS_RESOURCES = "file:/Users/hennesb/apps/static-content/css/";
	public static final String HTML_ANGULAR_PATH = "file:/Users/hennesb/apps/playground/quickstart/src/";
	public static final String NODE_MODULE_PATH = "file:/Users/hennesb/apps/playground/quickstart/node_modules/";

	public static final int CACHE_EXPIRED_AFTER = 3000000;

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver
                          = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(UI_PATH).addResourceLocations(UI_RESOURCES);	
        registry.addResourceHandler(ANGULAR_UI_PATH).addResourceLocations(HTML_ANGULAR_PATH);
        registry.addResourceHandler(NODE_MODULES).addResourceLocations(NODE_MODULE_PATH);
        registry.addResourceHandler(CONTEXT_PATH).addResourceLocations(HTML_ANGULAR_PATH);
        registry.addResourceHandler(SCRIPTS_URI_PATH ).addResourceLocations(SCRIPT_RESOURCES);
        registry.addResourceHandler(CSS_URI_PATH).addResourceLocations(CSS_RESOURCES);
	}
	


}