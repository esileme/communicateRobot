package com.yl.myrobotfriend;

public class ChatBean {
	public String text;//���ֵ�����
	public boolean isAsker;//�Ƿ�Ϊ������
	public int imageId=-1;//����ͼƬid����Ϊ-1����ʵͼƬ
	
	public ChatBean(String text, boolean isAsker, int imageId) {
		this.text = text;
		this.isAsker = isAsker;
		this.imageId = imageId;
	}

}
