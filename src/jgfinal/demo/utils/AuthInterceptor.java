package jgfinal.demo.utils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

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
