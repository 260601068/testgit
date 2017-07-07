package com.wjl.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login(){
		Map<String,String> userMap=new HashMap<String,String>();
		userMap.put("username", username);
		userMap.put("password", password);		
		Map<String,Object> db_user=null;
		try {
			db_user = namedParameterJdbcTemplate.queryForMap("select * from user where user_name=:username and password=:password", userMap);
		} catch (DataAccessException e) {
		}
		if(db_user!=null && db_user.size()>0){
			ActionContext.getContext().getSession().put("user", db_user);
			return SUCCESS;
		}
		ActionContext.getContext().put("logininfo", "用户名密码错误！");
		return "login";
	}
	
	public String regist(){
		Map<String,String> userMap=new HashMap<String,String>();
		userMap.put("username", username);
		userMap.put("password", password);	
		namedParameterJdbcTemplate.update("insert into user(user_name,password) values (:username,:password)", userMap);
		ActionContext.getContext().put("logininfo", "注册成功，请重新登录！");
		return "login";
	}
}
