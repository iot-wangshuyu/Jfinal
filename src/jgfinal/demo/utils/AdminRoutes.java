package jgfinal.demo.utils;

import com.jfinal.config.Routes;

import jfinal.demo.HelloController;

/**
 * @ClassName: AdminRoutes 
 * @Description: 路由的第二种配置方式，中间路由
 * @author shuyu.wang
 * @date 2017年10月10日 下午2:40:43 
 * @version V1.0
 */
public class AdminRoutes extends Routes{

	@Override
	public void config() {
		add("/hello", HelloController.class);
		
	}

}
