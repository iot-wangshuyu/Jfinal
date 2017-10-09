package jfinal.demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {
	// 配置常量值
	public void configConstant(Constants me) {
		// 运行在开发模式下
		me.setDevMode(true);
	}
    //配置访问路由
	public void configRoute(Routes me) {
		me.add("/hello", HelloController.class);
	}
   
	public void configEngine(Engine me) {
	}
	//插件配置
	public void configPlugin(Plugins me) {
	}
    //全局拦截器配置
	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
	}

}
