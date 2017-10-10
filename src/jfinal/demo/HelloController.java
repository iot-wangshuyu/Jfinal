package jfinal.demo;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.json.FastJson;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Page;

import jfinal.demo.bean.User;
import jfinal.demo.dao.UserDao;

/**
 * @ClassName: HelloController 
 * @Description: TODO
 * @author shuyu.wang
 * @date 2017年10月9日 下午5:20:08 
 * @version V1.0
 */
public class HelloController extends Controller {
	public void index() {
		render("/WEB-INF/jsp/index.jsp");  
	}
	
	public void hello() {
		renderText("Hello JFinal World.");
	}
	
	public void getPara1() {
		String name = getPara("name");
		renderText("输入参数："+name);
	}
	@Before(POST.class)
	public void getJson() {
		String jsonString = HttpKit.readData(getRequest());
		User user = FastJson.getJson().parse(jsonString, User.class);
		
//		User user =getModel(User.class);
		renderJson(user);
	}
	
	public void save() {
		boolean save = new UserDao().set("name", "wang").save();
		List<UserDao> find = new UserDao().find("select name,pass_word from t_user");
		renderText("执行结果："+find);
	}
	
	public void queryOne() {
		UserDao user=UserDao.dao.findFirst("select name,pass_word from t_user");
		renderJson(user);
	}
	
	public void queryList() {
		List<UserDao> find = UserDao.dao.find("select name,pass_word from t_user");
		renderJson(find);
	}
	
	
	public void queryPage() {
		Integer pageNumber = getParaToInt("pageNumber");
		Integer pageSize = getParaToInt("pageSize");
		Page<UserDao> paginate = UserDao.dao.paginate(pageNumber, pageSize, "select name,pass_word"," from t_user");
		renderJson(paginate);
	}
	
	public static void main(String[] args) {
//		JFinal.start("src/main/webapp",80,"/");
		JFinal.start("WebRoot", 80, "/", 5);
	}

}
