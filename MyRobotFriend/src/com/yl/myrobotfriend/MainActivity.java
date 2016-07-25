package com.yl.myrobotfriend;

import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.yl.myrobotfriend.VoiceBean.WSBean;

public class MainActivity extends Activity {
	private ListView lvList;
	private ArrayList<ChatBean> mChatList = new ArrayList<ChatBean>();// 定义聊天数组内容集合
	private ChatAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvList = (ListView) findViewById(R.id.lv_list);
		mAdapter = new ChatAdapter();
		lvList.setAdapter(mAdapter);

		// 初始化引擎
		SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5794d902");

	}

	/**
	 * 开始监听事件
	 * 
	 * @param v
	 */
	public void startListen(View v) {

		RecognizerDialog iatDialog = new RecognizerDialog(this, null);

		// 2.设置听写参数，详见speechconstant类
		iatDialog.setParameter(SpeechConstant.DOMAIN, "iat");
		iatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		iatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");

		iatDialog.setListener(recognizerDialogListener);

		iatDialog.show();
	}

	/**
	 * chatAdapter
	 */
	class ChatAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mChatList.size();
		}

		@Override
		public ChatBean getItem(int position) {
			return mChatList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View coverView, ViewGroup parent) {
			viewHolder holder;
			if (coverView == null) {
				holder = new viewHolder();// 初始化vierHolder
				// 将界面显示出来，没有添加inflate代码导致崩溃。。。
				coverView = View.inflate(MainActivity.this, R.layout.list_item,
						null);
				holder.tvAsk = (TextView) coverView.findViewById(R.id.tv_ask);
				holder.llAnswer = (LinearLayout) coverView
						.findViewById(R.id.ll_answer);
				holder.tvAnswer = (TextView) coverView
						.findViewById(R.id.tv_answer);
				holder.ivPic = (ImageView) coverView.findViewById(R.id.iv_pic);
				coverView = View.inflate(MainActivity.this, R.layout.list_item,
						null);
				coverView.setTag(holder);
			} else {
				holder = (viewHolder) coverView.getTag();
			}

			ChatBean item = getItem(position);// 强转

			if (item.isAsker) {// 判断是不是提问者
				holder.tvAsk.setVisibility(View.VISIBLE);
				holder.llAnswer.setVisibility(View.GONE);
				holder.tvAnswer.setText(item.text);
			} else {
				holder.tvAnswer.setVisibility(View.GONE);
				holder.llAnswer.setVisibility(View.VISIBLE);
				holder.tvAnswer.setText(item.text);
				if (item.imageId != -1) {
					holder.ivPic.setVisibility(View.VISIBLE);
					holder.ivPic.setImageResource(item.imageId);

				} else {
					holder.ivPic.setVisibility(View.GONE);
				}
			}
			return coverView;
		}

	}

	/**
	 * 定义一个viewHolder容器，将list_item里面的所有属性都填进去
	 * 
	 * @author Administrator
	 * 
	 */
	static class viewHolder {
		public TextView tvAsk;
		public TextView tvAnswer;
		public LinearLayout llAnswer;
		public ImageView ivPic;

	}

	/**
	 * set recognizerDialogListener listen
	 */
	StringBuffer mTextBuffer = new StringBuffer();
	private RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() {

		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			System.out.println(results.getResultString());
			System.out.println("isLast=" + isLast);

			String text = parseData(results.getResultString());
			mTextBuffer.append(text);

			if (isLast) {// 会话结束
				String finalText = mTextBuffer.toString();
				mTextBuffer = new StringBuffer();// 清楚buffer
				System.out.println("最终结果" + finalText);
				mChatList.add(new ChatBean(finalText, true, -1));// 将回话内容添加进去
				mAdapter.notifyDataSetChanged();// 刷新listview
			}
		}

		@Override
		public void onError(SpeechError arg0) {

		}
	};

	/**
	 * 解析语音字符串
	 * 
	 * @param resultString
	 */

	private String parseData(String resultString) {
		Gson gson = new Gson();// 在不可用的情况下，在网上下载一个gson的jar包导入
		VoiceBean bean = gson.fromJson(resultString, VoiceBean.class);
		ArrayList<WSBean> ws = bean.ws;
		StringBuffer sb = new StringBuffer();
		for (WSBean wsBean : ws) {
			String text = wsBean.cw.get(0).w;
			sb.append(text);
		}
		return sb.toString();
	}
}
