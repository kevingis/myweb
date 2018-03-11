package com.topfeng.test;

public class DoiEntityPojoNode {
	private String entityId;
	private String entityName;
	private String longPos;
	private String shortPos;
	private DoiEntityPojoNode parent;
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getLongPos() {
		return longPos;
	}
	public void setLongPos(String longPos) {
		this.longPos = longPos;
	}
	public String getShortPos() {
		return shortPos;
	}
	public void setShortPos(String shortPos) {
		this.shortPos = shortPos;
	}
	public DoiEntityPojoNode getParent() {
		return parent;
	}
	public void setParent(DoiEntityPojoNode parent) {
		this.parent = parent;
	}
}
