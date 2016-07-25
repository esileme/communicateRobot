package com.yl.myrobotfriend;

import java.util.ArrayList;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView lvList;
	private ArrayList<ChatBean> mChatList = new ArrayList<ChatBean>();// ���������������ݼ���
	private ChatAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvList = (ListView) findViewById(R.id.lv_list);
		mAdapter = new ChatAdapter();
		lvList.setAdapter(mAdapter);

		// ��ʼ������
		SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5794d902");

	}

	/**
	 * ��ʼ�����¼�
	 * 
	 * @param v
	 */
	public void startListen(View v) {

		RecognizerDialog iatDialog = new RecognizerDialog(this, null);

		// 2.������д���������speechconstant��
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
		public Object getItem(int position) {
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
				holder = new viewHolder();// ��ʼ��vierHolder
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

			ChatBean item = (ChatBean) getItem(position);// ǿת

			if (item.isAsker) {// �ж��ǲ���������
				holder.tvAnswer.setVisibility(View.VISIBLE);
				holder.llAnswer.setVisibility(View.GONE);

			} else {
				holder.tvAnswer.setVisibility(View.GONE);
				holder.llAnswer.setVisibility(View.VISIBLE);
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
	 * ����һ��viewHolder��������list_item������������Զ����ȥ
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
	private RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() {

		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			System.out.println(results.getResultString());
			System.out.println("isLast=" + isLast);
		}

		@Override
		public void onError(SpeechError arg0) {

		}
	};
}
