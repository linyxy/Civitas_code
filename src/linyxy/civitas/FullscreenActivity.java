package linyxy.civitas;

import java.util.HashMap;
import java.util.Map;

import linyxy.civitas.util.DataRequestUtil;
import linyxy.civitas.util.SystemUiHider;
import linyxy.civitas.util.UpdateData;

import org.json.JSONObject;

import projectTesting.HttpUtilX;
import structure.DatabaseHelper;
import structure.DialogUtil;
import structure.HttpUtil;
import structure.SharedPreferenceUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
@SuppressLint("HandlerLeak")
public class FullscreenActivity extends Activity {
//	/**
//	 * Whether or not the system UI should be auto-hidden after
//	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
//	 */
//	private static final boolean AUTO_HIDE = true;
//
//	/**
//	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
//	 * user interaction before hiding the system UI.
//	 */
//	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
//
//	/**
//	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
//	 * will show the system UI visibility upon interaction.
//	 */
//	private static final boolean TOGGLE_ON_CLICK = true;
//
//	/**
//	 * The flags to pass to {@link SystemUiHider#getInstance}.
//	 */
//	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
//
//	/**
//	 * The instance of the {@link SystemUiHider} for this activity.
//	 */
//	private SystemUiHider mSystemUiHider;

	private Button log;
	private EditText userName;
	private EditText userPassword;
	public Handler FSAHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x1111)
			{
				Log.d("H", "Handler is in use");
				DialogUtil.showDialog(FullscreenActivity.this, "大概帐号密码写错了", false);
				
				Intent intent = new Intent();  
	            intent.setClass(FullscreenActivity.this,MainActivity.class);  
	            startActivity(intent);  
	            FullscreenActivity.this.onDestroy();
			}
			super.handleMessage(msg);
		}
		
	};
	;
	
	public static String Login = "logining";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		

		
		Log.d(Login,"The application is started normally");
		log = (Button)findViewById(R.id.login_button);
		userName = (EditText)findViewById(R.id.user_name);
		userPassword = (EditText)findViewById(R.id.user_password);
		log.setOnClickListener(new handlerTestListener());
		
	}
	
//	class myHandler extends Handler
//	{
//
//		/* (non-Javadoc)
//		 * @see android.os.Handler#handleMessage(android.os.Message)
//		 */
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			super.handleMessage(msg);
//			
//			if(msg.what == 0x1357)
//			{
//				Log.d(Login, "Jump to my_second");
//				Intent startMySecond = new Intent();
//				startMySecond.setClass(FullscreenActivity.this, my_second.class);
//				FullscreenActivity.this.startActivity(startMySecond);
//				finish();
//			}
//			else if(msg.what == 0x1358)
//			{
//				DialogUtil.showDialog(FullscreenActivity.this, "大概帐号密码写错了", false);
//			}
//		}
//		
//	}
	
	class handlerTestListener implements View.OnClickListener
	{

		@Override
		public void onClick(View arg0) {
			UpdateData update = new UpdateData(FullscreenActivity.this,FSAHandler);
//			update.execute("UrlTest");
//			服务器相应测试
//			update.execute("SharedPTest");
//			SharedPreference测试
//			update.execute("SQLiteTest");
//			SQLite Test
			
			update.execute("handlerTest");
			
		}
		
	}
	
	//按钮监听器
	//用于后台登陆
	class loginButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			
			//String name = userName.getText().toString();
			//Log.d(Login, "start to login");
			//System.out.println("start the program");
			
			Intent intent = new Intent();  
            intent.setClass(FullscreenActivity.this,MainActivity.class);  
            startActivity(intent);  
            FullscreenActivity.this.onDestroy();

			
			//if(validate())
			//{
			//	Toast.makeText(getApplicationContext(),"正在登陆中", Toast.LENGTH_SHORT).show();
				//进入后台验证帐号密码
				//update.execute("login",userName.getText().toString(),userPassword.getText().toString());
			//}

		
			
			// TODO Auto-generated method stub
		}
		
	}
	


	// 对用户输入的用户名、密码进行校验
	private boolean validate()
	{
		String username = userName.getText().toString().trim();
		if (username.equals(""))
		{
			DialogUtil.showDialog(this, "你填写的用户是个啥！", false);
			return false;
		}
		String pwd = userPassword.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "你填的密码是个啥！", false);
			return false;
		}
		Log.d(Login,"pass validation");
		return true;
	}
	

//		final View controlsView = findViewById(R.id.fullscreen_content_controls);
//		final View contentView = findViewById(R.id.fullscreen_content);
//
//		// Set up an instance of SystemUiHider to control the system UI for
//		// this activity.
//		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
//				HIDER_FLAGS);
//		mSystemUiHider.setup();
//		mSystemUiHider
//				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
//					// Cached values.
//					int mControlsHeight;
//					int mShortAnimTime;
//
//					@Override
//					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//					public void onVisibilityChange(boolean visible) {
//						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//							// If the ViewPropertyAnimator API is available
//							// (Honeycomb MR2 and later), use it to animate the
//							// in-layout UI controls at the bottom of the
//							// screen.
//							if (mControlsHeight == 0) {
//								mControlsHeight = controlsView.getHeight();
//							}
//							if (mShortAnimTime == 0) {
//								mShortAnimTime = getResources().getInteger(
//										android.R.integer.config_shortAnimTime);
//							}
//							controlsView
//									.animate()
//									.translationY(visible ? 0 : mControlsHeight)
//									.setDuration(mShortAnimTime);
//						} else {
//							// If the ViewPropertyAnimator APIs aren't
//							// available, simply show or hide the in-layout UI
//							// controls.
//							controlsView.setVisibility(visible ? View.VISIBLE
//									: View.GONE);
//						}
//
//						if (visible && AUTO_HIDE) {
//							// Schedule a hide().
//							delayedHide(AUTO_HIDE_DELAY_MILLIS);
//						}
//					}
//				});
//
//		// Set up the user interaction to manually show or hide the system UI.
//		contentView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				if (TOGGLE_ON_CLICK) {
//					mSystemUiHider.toggle();
//				} else {
//					mSystemUiHider.show();
//				}
//			}
//		});
//
//		// Upon interacting with UI controls, delay any scheduled hide()
//		// operations to prevent the jarring behavior of controls going away
//		// while interacting with the UI.
//		findViewById(R.id.dummy_button).setOnTouchListener(
//				mDelayHideTouchListener);
		
		
//
//	@Override
//	protected void onPostCreate(Bundle savedInstanceState) {
//		super.onPostCreate(savedInstanceState);
//
//		// Trigger the initial hide() shortly after the activity has been
//		// created, to briefly hint to the user that UI controls
//		// are available.
//		delayedHide(100);
//	}
//
//	/**
//	 * Touch listener to use for in-layout UI controls to delay hiding the
//	 * system UI. This is to prevent the jarring behavior of controls going away
//	 * while interacting with activity UI.
//	 */
//	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
//		@Override
//		public boolean onTouch(View view, MotionEvent motionEvent) {
//			if (AUTO_HIDE) {
//				delayedHide(AUTO_HIDE_DELAY_MILLIS);
//			}
//			return false;
//		}
//	};
//
//	Handler mHideHandler = new Handler();
//	Runnable mHideRunnable = new Runnable() {
//		@Override
//		public void run() {
//			mSystemUiHider.hide();
//		}
//	};
//
//	/**
//	 * Schedules a call to hide() in [delay] milliseconds, canceling any
//	 * previously scheduled calls.
//	 */
//	private void delayedHide(int delayMillis) {
//		mHideHandler.removeCallbacks(mHideRunnable);
//		mHideHandler.postDelayed(mHideRunnable, delayMillis);
//	}


}
