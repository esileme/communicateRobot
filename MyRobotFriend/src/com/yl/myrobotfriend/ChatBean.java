package com.yl.myrobotfriend;

public class ChatBean {
	public String text;//文字的内容
	public boolean isAsker;//是否为提问者
	public int imageId=-1;//定义图片id，若为-1则不现实图片
	
	public ChatBean(String text, boolean isAsker, int imageId) {
		this.text = text;
		this.isAsker = isAsker;
		this.imageId = imageId;
	}

}
