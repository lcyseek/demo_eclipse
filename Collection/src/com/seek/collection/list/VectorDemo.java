package com.seek.collection.list;

import java.util.Stack;

/**
 * 
 * Stack<E> extends Vector<E>
 */

public class VectorDemo {

	public static void main(String[] args) {

		Stack<String> stack = new Stack<>();
		stack.push("第一层");
		stack.push("第二层");
		stack.push("第三层");
		stack.push("第四层");
		stack.push("第五层");
		
		showData(stack);
		
		System.out.println("pop:"+stack.pop());
		System.out.println("pop:"+stack.pop());
		System.out.println("pop:"+stack.pop());
		System.out.println("pop:"+stack.pop());
		System.out.println("pop:"+stack.pop());

		
	}

	private static void showData(Stack<String> stack) {
		for (int i = 0; i < stack.size(); i++) {
			System.out.println(stack.get(i));
		}
	}

}
