package com.yl.myrobotfriend;

import android.R.integer;
import android.R.string;

public class ChatBean {
	public string text;//���ֵ�����
	public boolean isAsker;//�Ƿ�Ϊ������
	public int imageId=-1;//����ͼƬid����Ϊ-1����ʵͼƬ
	
	public ChatBean(string text, boolean isAsker, int imageId) {
		this.text = text;
		this.isAsker = isAsker;
		this.imageId = imageId;
	}

}
