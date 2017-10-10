package jgfinal.demo.utils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * @ClassName: AuthInterceptor 
 * @Description: 拦截器
 * @author shuyu.wang
 * @date 2017年10月10日 下午4:59:11 
 * @version V1.0
 */
public class AuthInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {

		Controller controller = inv.getController();
		String username = controller.getPara("username");
		if (username==null||username.equals("")) {
			controller.renderText("需要输入用户名");
		}else {
			 inv.invoke();
		}

	}

}
