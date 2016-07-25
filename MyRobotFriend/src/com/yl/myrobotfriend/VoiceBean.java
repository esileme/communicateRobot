package com.yl.myrobotfriend;

import java.util.ArrayList;
/**
 * 提供对字符串数据的存放解析
 * @author Administrator
 *
 */
public class VoiceBean {
	public ArrayList<WSBean>ws;
	public class WSBean{
		public ArrayList<CWBean>cw;
		
	}
	public class CWBean {
		public String w;
	}

}
