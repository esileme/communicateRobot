package com.yl.myrobotfriend;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lvList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvList=	 (ListView) findViewById(R.id.lv_list);
	}

	/**
	 * 开始监听事件
	 * 
	 * @param v
	 */
	public void startListen(View v) {

	}
	/**
	 * chatAdapter
	 */
	class ChatAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			return null;
		}
		
	}

}
