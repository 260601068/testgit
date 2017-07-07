package com.wjl.bbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Comment {
	private List<Comment> comments;
	private Integer comId;
	private String comContent;
	private String comTime;
	private String comBy;
	private String comByName;
	
	public Comment() {
		super();
	}

	public static List<TreeViewObject> getTreeViewObjectList(List<Comment> data){
		Comment comment = new Comment();
		comment.setComId(0);comment.setComContent("root");
		comment.setComments(data);
		TreeViewObject treeViewObject=getTreeViewObject(comment);
		return treeViewObject.getNodes();
	}
	
	public static TreeViewObject getTreeViewObject(Comment comment){
		TreeViewObject treeViewObject=new TreeViewObject(comment.getComId(),comment.getComContent(),null);
		List<Comment> childComments=comment.getComments();
		if(childComments!=null && childComments.size()>0){
			for(Comment chiledComment : childComments){
				TreeViewObject childTreeViewObject=getTreeViewObject(chiledComment);
				if(childTreeViewObject!=null){
					List<TreeViewObject> childTreeViewObjects=treeViewObject.getNodes();
					if(childTreeViewObjects==null) {
						childTreeViewObjects=new ArrayList<TreeViewObject>();
						treeViewObject.setNodes(childTreeViewObjects);
					}
					childTreeViewObjects.add(childTreeViewObject);
				}
			}
		}
		return treeViewObject;
	}
	
	public static List<Comment> getCommentTreeList(List<Map<String,Object>> commentData,List<Map<String,Object>> userData){
		List<Comment> res=new ArrayList<Comment>();
		for (Map<String, Object> map : commentData) {
			Integer comId = map.get("com_id") == null ? null : Integer.valueOf(map.get("com_id").toString());
			Integer parentComId = map.get("parent_com_id") == null ? null : Integer.valueOf(map.get("parent_com_id").toString());
			if(parentComId==null){
				res.add(new Comment(comId, commentData, userData));
			}
		}
		return res;
	}
	
	public Comment(Integer comId,List<Map<String,Object>> commentData,List<Map<String,Object>> userData){
		for(Map<String,Object> commentMap : commentData){
			
			Integer comId_db=new Integer(commentMap.get("com_id").toString());
			String comContent=commentMap.get("com_content").toString();
			String comTime=commentMap.get("com_time")==null ? null : commentMap.get("com_time").toString();
			String comBy=commentMap.get("com_by").toString();
			String comByName=null;
			
			if(!comId_db.equals(comId)) continue;
			for(Map<String,Object> user: userData){
				if(comBy.equals(user.get("user_id").toString()))
					comByName=(String)commentMap.get("com_by_name");
			}
			this.comId=comId;
			this.comContent=comContent;
			this.comTime=comTime;
			this.comBy=comBy;
			this.comByName=comByName;
			for(Map<String,Object> _commentMap : commentData){
				Integer _comId=new Integer(_commentMap.get("com_id").toString());
				if(_commentMap.get("parent_com_id")==null) continue;
				Integer _parentComId=new Integer(_commentMap.get("parent_com_id").toString());
				if(this.comId.equals(_parentComId)){
					if(comments==null)
						comments=new ArrayList<Comment>();
					this.comments.add(new Comment(_comId,commentData, userData));
				}
			}
		}
	}

	
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public String getComTime() {
		return comTime;
	}

	public void setComTime(String comTime) {
		this.comTime = comTime;
	}

	public String getComBy() {
		return comBy;
	}

	public void setComBy(String comBy) {
		this.comBy = comBy;
	}

	public String getComByName() {
		return comByName;
	}

	public void setComByName(String comByName) {
		this.comByName = comByName;
	}

	@Override
	public String toString() {
		return "Comment [comments=" + comments + ", comId=" + comId + ", comContent=" + comContent + ", comTime="
				+ comTime + ", comBy=" + comBy + ", comByName=" + comByName + "]";
	}
	
}
