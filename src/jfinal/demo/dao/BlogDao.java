package jfinal.demo.dao;

import com.jfinal.plugin.activerecord.Model;

public class BlogDao extends Model<BlogDao> {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	public static final BlogDao dao = new BlogDao();
}
