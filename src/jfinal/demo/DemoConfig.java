package jfinal.demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

import jfinal.demo.bean.User;
import jfinal.demo.dao.UserDao;

public class DemoConfig extends JFinalConfig {
	// 配置常量值
	public void configConstant(Constants me) {
		// 运行在开发模式下
		me.setDevMode(true);
	}

	// 配置访问路由
	public void configRoute(Routes me) {
		me.add("/hello", HelloController.class);
	}

	public void configEngine(Engine me) {
	}

	// 插件配置
	public void configPlugin(Plugins me) {
//		DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost/jfinal", "root", "123456");
//		me.add(dp);
//		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
//		me.add(arp);
//		arp.addMapping("user", User.class);
//		arp.addMapping("t_user", "id",User.class);
		 C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/jfinal",
	                "root", "123456");
	        me.add(cp);
	        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
	        me.add(arp);
	        arp.addMapping("t_user", UserDao.class);

	}

	// 全局拦截器配置
	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
	}

}
