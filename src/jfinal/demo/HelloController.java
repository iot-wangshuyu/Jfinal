package jfinal.demo;

import java.sql.SQLException;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.json.FastJson;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

import jfinal.demo.bean.User;
import jfinal.demo.dao.BlogDao;
import jfinal.demo.dao.UserDao;
import jfinal.demo.dao.UserRoleDao;

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
	
	//基于Model模式
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
	
	//基于Db + Record模式
	
	public void drSave() {
		Record set = new Record().set("name", "test1").set("pass_word", "123456");
		Db.save("t_user", set);
		renderText("执行结果："+set);
	}
	
	public void drQuery() {
		List<Record> find = Db.find("select name,pass_word from t_user");
		renderJson(find);
	}
	
	/** 
	* @Title: update 
	* @Description: 事务操作
	* @param  
	* @return void 
	* @throws 
	*/
	public void update() {
		boolean tx = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				int update = Db.update("UPDATE t_user SET pass_word=? WHERE NAME=?", "123","a");
                System.out.println(update);
				//				int a=1/0;
				int update2 = Db.update("UPDATE t_user SET pass_word=? WHERE NAME=?", "123","b");
				 System.out.println(update2);
				return update==1&&update2==1;
			}
		});
		renderText("执行结果"+tx);
	}
	
	/** 
	* @Title: relation 
	* @Description: 关联表查询
	* @param  
	* @return void 
	* @throws 
	*/
	public void relation() {
		String sql="SELECT tu.name,tb.blog_name,tb.blog_body FROM t_user tu INNER JOIN t_blog tb ON tu.id=tb.user_id";
		List<BlogDao> find = new BlogDao().dao().find(sql);
		renderJson(find);
	}
	/** 
	* @Title: saveRPK 
	* @Description:联合主键的使用
	* @param  
	* @return void 
	* @throws 
	*/
	public void saveRPK() {
		boolean save = UserRoleDao.dao.set("user_id","2").set("role_id", "1").save();
		renderText("执行结果"+save);
	}
	
	public void queryUserRole() {
		String sql="SELECT tu.name,tr.role_name FROM t_role tr INNER JOIN t_user_role tur ON tr.id=tur.role_id INNER JOIN t_user tu ON tur.user_id=tu.id";
		List<UserRoleDao> find = UserRoleDao.dao.find(sql);
		renderJson(find);
	}
	
	/** 
	* @Title: queryBySql 
	* @Description: 根据文件中的sql语句执行相关指令
	* @param  
	* @return void 
	* @throws 
	*/
	public void queryBySql() {
		String sql=Db.getSql("jfinal.queryBySql");
		List<Record> find = Db.find(sql);
		renderJson(find);
	}
	
	/** 
	* @Title: queryBySqlPage 
	* @Description:分页查询权限是管理员的全部用户
	* @param  
	* @return void 
	* @throws 
	*/
	public void queryBySqlPage() {
		SqlPara sqlPara=Db.getSqlPara("jfinal.queryUser",1);
		Page<Record> paginate = Db.paginate(1, 2, sqlPara);
		
		renderJson(paginate);
	}
	
//	public static void main(String[] args) {
//		JFinal.start("src/main/webapp",80,"/");
//		JFinal.start("WebRoot", 80, "/", 5);
//	}

}
