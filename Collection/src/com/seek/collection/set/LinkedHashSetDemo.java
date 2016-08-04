package com.seek.collection.set;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * 有序！
 * @author lcy
 *
 */
public class LinkedHashSetDemo {

	public static void main(String[] args) {
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
		
		linkedHashSet.add("hello");
		linkedHashSet.add("android");
		linkedHashSet.add("tom");
		linkedHashSet.add("jim");
		linkedHashSet.add("kity");
		linkedHashSet.add("james");
		linkedHashSet.add(null);
		
		showData(linkedHashSet);
	}

	private static void showData(LinkedHashSet<String> linkedHashSet) {

		Iterator<String> iterator = linkedHashSet.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.print(string+" ");
		}
	}

}
