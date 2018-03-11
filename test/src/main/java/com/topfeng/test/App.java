package com.topfeng.test;

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
    public static void main( String[] args )
    {
        App app = new App();
        String[] group1 = {"7","6","5","4","3","2","1"};
        String[] group2 = {"24","23","22","5","3","2","1"};
        String[] group3 = {"34","33","32","6","3","2","1"};
        DoiEntityPojoNode pojo1 = app.initData(group1, 0, "10", "10");
        DoiEntityPojoNode pojo2 = app.initData(group2, 0, "10", "10");
        DoiEntityPojoNode pojo3 = app.initData(group3, 0, "10", "10");
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
		Collections.reverse(list1);
		Collections.reverse(list2);
		Collections.reverse(list3);
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
    	pojo.setEntityId(String.valueOf(id));    		
		pojo.setEntityName("Entity - "+id);
		
		if(pojo.getLongPos()!=null&&!"".equals(pojo.getLongPos())){
			int intLongPos = Integer.parseInt(pojo.getLongPos())+Integer.parseInt(longPos);
			pojo.setLongPos(String.valueOf(intLongPos));
		}
		if(pojo.getShortPos()!=null&&!"".equals(pojo.getShortPos())){
			int intShortPos = Integer.parseInt(pojo.getShortPos());
			pojo.setShortPos(String.valueOf(intShortPos));
		}
		pojo.setLongPos(longPos);
		pojo.setShortPos(shortPos);
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
    				int longPos = Integer.parseInt(temp1.getLongPos())+Integer.parseInt(temp2.getLongPos());
    				int shortPos = Integer.parseInt(temp1.getShortPos())+Integer.parseInt(temp2.getShortPos());
    				temp1.setLongPos(String.valueOf(longPos));
    				temp1.setShortPos(String.valueOf(shortPos));
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
