package com.topfeng.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final String[] group1 = {"7","6","5","4","3","2","1"};
	public static final String[] group2 = {"24","23","22","5","3","2","1"};
	public static final String[] group3 = {"34","33","32","6","3","2","1"};
    public static void main( String[] args )
    {
    	//direct trade: 7, 24, 34
        App app = new App();
        
        DoiEntityPojoNode pojo1 = app.initData(group1, 0, "10", "5");
        DoiEntityPojoNode pojo2 = app.initData(group2, 0, "10", "5");
        DoiEntityPojoNode pojo3 = app.initData(group3, 0, "10", "5");
        Gson gson = new Gson();
//		System.out.println(gson.toJson(pojo1));
//		System.out.println(gson.toJson(pojo2));
//		System.out.println(gson.toJson(pojo3));
		List<DoiEntityPojo> list1 = new ArrayList<>();
		List<DoiEntityPojo> list2 = new ArrayList<>();
		List<DoiEntityPojo> list3 = new ArrayList<>();
		app.toArray(pojo1, list1);
		app.toArray(pojo2, list2);
		app.toArray(pojo3, list3);
		Map<String, DoiEntityPojo> map = new HashMap<String, DoiEntityPojo>();
		app.merge(list1, map);
		app.merge(list2, map);
		app.merge(list3, map);
//		System.out.println(gson.toJson(list1));
//		System.out.println(gson.toJson(list2));
//		System.out.println(gson.toJson(list3));
		System.out.println(gson.toJson(map));
    }
    
    public DoiEntityPojoNode initData(String[] pair, int index, String longPos, String shortPos){
    	DoiEntityPojoNode pojo = new DoiEntityPojoNode();
    	String id = pair[index];
    	pojo.setEntityId(id);    		
		pojo.setEntityName("Entity - "+id);
		
		if(pojo.getLongPos()!=null&&!"".equals(pojo.getLongPos())){
			BigDecimal intLongPos = new BigDecimal(pojo.getLongPos()).add(new BigDecimal(longPos));
			pojo.setLongPos(intLongPos.toString());
		}else{
			pojo.setLongPos(longPos);
		}
		if(pojo.getShortPos()!=null&&!"".equals(pojo.getShortPos())){
			BigDecimal intShortPos = new BigDecimal(pojo.getShortPos()).add(new BigDecimal(shortPos));
			pojo.setShortPos(intShortPos.toString());
		}else{
			pojo.setShortPos(shortPos);
		}
		
		if(index<pair.length-1){
			pojo.setParent(initData(pair, ++index, longPos, shortPos));			
		}
		return pojo;
    }
    
    public List<DoiEntityPojo> toArray(DoiEntityPojoNode node, List<DoiEntityPojo> list){
    	if(node!=null){   
    		DoiEntityPojo pojo = convert(node);
    		list.add(pojo);
    		if(node.getParent()!=null){
    			toArray(node.getParent(), list);
    		}
    	}
    	Collections.reverse(list);
    	return list;
    }
    public DoiEntityPojo convert(DoiEntityPojoNode node){
    	DoiEntityPojo pojo = new DoiEntityPojo();
    	pojo.setEntityId(node.getEntityId());
    	pojo.setEntityName(node.getEntityName());
    	pojo.setLongPos(node.getLongPos());
    	pojo.setShortPos(node.getShortPos());
    	return pojo;
    }
    public void merge(List<DoiEntityPojo> list, final Map<String, DoiEntityPojo> map){
    	if(map.size()<=0){
    		for(DoiEntityPojo pojo:list){
    			map.put(pojo.getEntityId(), pojo);
    		}
    		return;
    	}
    	for(String key: map.keySet()){
    		for(int i=0;i<list.size();i++){
    			DoiEntityPojo temp1 = map.get(key);
				DoiEntityPojo temp2 = list.get(i);
    			if(temp1.equals(temp2)){
    				BigDecimal longPos = new BigDecimal(temp1.getLongPos()).add(new BigDecimal(temp2.getLongPos()));
    				BigDecimal shortPos = new BigDecimal(temp1.getShortPos()).add(new BigDecimal(temp2.getShortPos()));
    				temp1.setLongPos(longPos.toString());
    				temp1.setShortPos(shortPos.toString());
    				break;
    			}
    		}
    	}
    	//Scan the rest of list2
    	for(DoiEntityPojo pojo:list){
    		if(!map.keySet().contains(pojo.getEntityId())){
    			map.put(pojo.getEntityId(), pojo);
    		}
    	}
    }
}
