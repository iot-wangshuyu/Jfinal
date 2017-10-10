package jfinal.demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.template.Engine;

import jgfinal.demo.utils.AdminRoutes;
import jgfinal.demo.utils.DbMappingKit;

public class DemoConfig extends JFinalConfig {
	// 配置常量值
	public void configConstant(Constants me) {
		// 运行在开发模式下
		me.setDevMode(true);
		PropKit.use("config.properties"); // 数据库配置文件，发觉不管放到哪里都可以，没有路径  
	}

	// 配置访问路由
	public void configRoute(Routes me) {
		//方式一：直接配置文件
		//http://localhost:8080/jfinal_demo/hello 将 访 问 HelloController.index() 方 法  
		//me.add(,,); // 第三个参数为该Controller的视图存放路径  
//		me.add("/hello", HelloController.class);
		// http://localhost:8080/jfinal_demo/hello/queryUserRole 将 访 问 UserController.queryUserRole() 方 法 
//		me.add("/hello/queryUserRole", HelloController.class);
		
		
		 // 方式二：使用中间路由  
        //me.add(new FrontRoutes()); // 前端路由  
        me.add(new AdminRoutes()); // 后端路由 
	}

	public void configEngine(Engine me) {
	}

	// 插件配置
	public void configPlugin(Plugins me) {
		// DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost/jfinal", "root",
		// "123456");
		// me.add(dp);
		C3p0Plugin cp = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit  
                .get("username"), PropKit.get("password").trim());
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		//sql文件存放的基础路径
		arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());
		//外部sql文件模板
		arp.addSqlTemplate("sqlfile.sql");
		arp.setShowSql(true);
		me.add(arp);
		//方式一： 添加类和表的映射关系
//		arp.addMapping("t_user", UserDao.class);
//		arp.addMapping("t_blog", BlogDao.class);
//		arp.addMapping("t_role", RoleDao.class);
//		arp.addMapping("t_user_role", "user_id, role_id", UserRoleDao.class);
		// 方式二：配置数据表映射写到一个文件中  
         DbMappingKit.mapping(arp);  

	}

	// 全局拦截器配置
	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
	}

}
