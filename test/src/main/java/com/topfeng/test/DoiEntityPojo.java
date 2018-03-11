package com.topfeng.test;

public class DoiEntityPojo {
	private String entityId;
	private String entityName;
	private String longPos;
	private String shortPos;
	
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DoiEntityPojo){
			DoiEntityPojo temp = (DoiEntityPojo)obj;
			if(temp.getEntityId().equals(entityId)){
				return true;
			}
		}
		return false;
	}
}
