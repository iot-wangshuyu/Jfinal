package jgfinal.demo.utils;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * @ClassName: LoginValidator 
 * @Description: 校验器
 * @author shuyu.wang
 * @date 2017年10月10日 下午4:58:57 
 * @version V1.0
 */
public class LoginValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("username", "nameMsg", "请输入用户名");
		validateRequiredString("password", "passMsg", "请输入密码");
		
	}

	@Override
	protected void handleError(Controller c) {
		//用于将页面表单中的数据保持住并传递回页
		c.keepPara("name");
		c.render("/WEB-INF/jsp/index.jsp");
		
	}

}
