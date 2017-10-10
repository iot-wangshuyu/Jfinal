package jgfinal.demo.utils;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

import jfinal.demo.dao.BlogDao;
import jfinal.demo.dao.RoleDao;
import jfinal.demo.dao.UserDao;
import jfinal.demo.dao.UserRoleDao;

/**
 * @ClassName: DbMappingKit 
 * @Description: 数据库映射的第二种方式：映射表的文件
 * @author shuyu.wang
 * @date 2017年10月10日 下午2:26:45 
 * @version V1.0
 */
public class DbMappingKit {
	public static void mapping(ActiveRecordPlugin arp) {  
		arp.addMapping("t_user", UserDao.class);
		arp.addMapping("t_blog", BlogDao.class);
		arp.addMapping("t_role", RoleDao.class);
		arp.addMapping("t_user_role", "user_id, role_id", UserRoleDao.class);
    }  

}
