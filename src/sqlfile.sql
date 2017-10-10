#namespace("jfinal")
  #sql("queryBySql")
   SELECT tu.name,tr.role_name FROM t_role tr INNER JOIN t_user_role tur ON tr.id=tur.role_id INNER JOIN t_user tu ON tur.user_id=tu.id
  #end
  
  #sql("queryUser")
   SELECT tu.name,tr.role_name FROM t_role tr INNER JOIN t_user_role tur ON tr.id=tur.role_id INNER JOIN t_user tu ON tur.user_id=tu.id AND tr.id= #para(0)
  #end
#end