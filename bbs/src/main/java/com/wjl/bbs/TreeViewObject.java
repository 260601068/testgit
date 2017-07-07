package com.wjl.bbs;

import java.util.List;

public class TreeViewObject {
	private Integer id;
	private String text;
	private List<TreeViewObject> nodes;
	public TreeViewObject() {
		super();
	}
	public TreeViewObject(Integer id, String text, List<TreeViewObject> nodes) {
		super();
		this.id = id;
		this.text = text;
		this.nodes = nodes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<TreeViewObject> getNodes() {
		return nodes;
	}
	public void setNodes(List<TreeViewObject> nodes) {
		this.nodes = nodes;
	}
	@Override
	public String toString() {
		return "TreeViewObject [id=" + id + ", text=" + text + ", nodes=" + nodes + "]";
	}
	
}
