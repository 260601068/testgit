package com.wjl.bbs;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CommentAction extends ActionSupport{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public String subComment(){
		Map<String,Object> map=new HashMap<String,Object>();
		Integer parent_com_id=null;
		String _parent_com_id=ServletActionContext.getRequest().getParameter("parent_com_id");
		if(_parent_com_id!=null && _parent_com_id!="") parent_com_id=Integer.valueOf(_parent_com_id);
		map.put("parent_com_id", parent_com_id);
		map.put("sub_id", Integer.valueOf(ServletActionContext.getRequest().getParameter("sub_id")));
		map.put("com_content", ServletActionContext.getRequest().getParameter("com_content"));
		map.put("com_by", Integer.valueOf(ServletActionContext.getRequest().getParameter("com_by")));
		map.put("com_by_name", ServletActionContext.getRequest().getParameter("com_by_name"));
		String sql="INSERT INTO comment(parent_com_id,sub_id,com_content,com_time,com_by,com_by_name) VALUES(:parent_com_id,:sub_id,:com_content,CURRENT_TIMESTAMP,:com_by,:com_by_name)";
		namedParameterJdbcTemplate.update(sql, map);
		return "reload";
	}
	
	public String deleteComment(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("com_id", Integer.valueOf(ServletActionContext.getRequest().getParameter("com_id")));
		namedParameterJdbcTemplate.update("delete from comment where com_id=:com_id", map);
		return "reload";
	}
}
