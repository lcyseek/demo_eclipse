package com.seek.hal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Entry {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		JPanel jpPanel = new JPanel();
		JTextArea jTextArea = new JTextArea();
		jTextArea.setText("Hello android");
		jFrame.add(jTextArea);
		jFrame.setSize(400, 400);
		jFrame.show();
	}

}
