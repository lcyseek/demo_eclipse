package com.seek.map;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * 有序!!!!
 * @author lcy
 *
 */
public class LinkedHashMapDemo {

	public static void main(String[] args) {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("1", "a");
		map.put("2","b");
		map.put("3", "c");
		map.put("4", "d");
		map.put("5", "e");
		map.put("6", "f");
		map.put(null, "");
		
		showData(map);
	}

	private static void showData(LinkedHashMap<String, String> map) {
		// TODO Auto-generated method stub
		Set<String> keys = map.keySet();
		for(String key:keys){
			System.out.println(key+"="+map.get(key));
		}
	}

}
