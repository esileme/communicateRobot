package com.itheima.voicedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化引擎
		SpeechUtility.createUtility(this, SpeechConstant.APPID + "=54b8bca3");
	}

	/**
	 * 寮�濮嬪惉鍐�
	 * 
	 * @param view
	 */
	public void listen(View view) {
		// 1.鍒涘缓SpeechRecognizer瀵硅薄锛岀浜屼釜鍙傛暟锛氭湰鍦板惉鍐欐椂浼營nitListener
		SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(this, null);

		// 2.璁剧疆鍚啓鍙傛暟锛岃瑙併�婄澶ц椋濵SC API鎵嬪唽(Android)銆婼peechConstant绫�
		mIat.setParameter(SpeechConstant.DOMAIN, "iat");
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		mIat.setParameter(SpeechConstant.ACCENT, "mandarin");

		// 寮�濮嬪惉鍐�
		mIat.startListening(mRecoListener);

	}

	/**
	 * 浜や簰鍔ㄧ敾
	 * 
	 * @param view
	 */
	public void listenUI(View view) {
		RecognizerDialog iatDialog = new RecognizerDialog(this, mInitListener);

		// 2.璁剧疆鍚啓鍙傛暟锛岃瑙併�婄澶ц椋濵SC API鎵嬪唽(Android)銆婼peechConstant绫�
		iatDialog.setParameter(SpeechConstant.DOMAIN, "iat");
		iatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		iatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");

		iatDialog.setListener(recognizerDialogListener);

		iatDialog.show();
	}

	/**
	 * 璇煶鏈楄
	 */
	public void read(View view) {
		SpeechSynthesizer mTts = SpeechSynthesizer
				.createSynthesizer(this, null);

		mTts.setParameter(SpeechConstant.VOICE_NAME, "vixm");
		mTts.setParameter(SpeechConstant.SPEED, "50");
		mTts.setParameter(SpeechConstant.VOLUME, "80");
		mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);

		mTts.startSpeaking("搴婂墠鏄庢湀鍏�,鍦颁笂闉嬩袱鍙�,搴婁笂鐙楃敺濂�,鍏朵腑灏辨湁浣�! 浣犲ソ鍟�?",
				mSynthesizerListener);
	}

	private SynthesizerListener mSynthesizerListener = new SynthesizerListener() {

		@Override
		public void onSpeakResumed() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSpeakProgress(int arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSpeakPaused() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSpeakBegin() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onCompleted(SpeechError arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onBufferProgress(int arg0, int arg1, int arg2, String arg3) {
			// TODO Auto-generated method stub

		}
	};

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

	private InitListener mInitListener = new InitListener() {

		@Override
		public void onInit(int arg0) {
		}
	};

	private RecognizerListener mRecoListener = new RecognizerListener() {

		/**
		 * 璇煶璇嗗埆缁撴灉 isLast=true琛ㄧず浼氳瘽缁撴潫
		 */
		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			System.out.println(results.getResultString());
		}

		@Override
		public void onBeginOfSpeech() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onEndOfSpeech() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onError(SpeechError arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onVolumeChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};

}
