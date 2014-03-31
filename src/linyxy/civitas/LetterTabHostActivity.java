package linyxy.civitas;

import java.util.ArrayList;
import java.util.List;

import linyxy.civitas.model.Notification;
import linyxy.civitas.util.DataRequestUtil;
import linyxy.civitas.util.UpdateData;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 这是一个包含上导航栏的activity类，
 * 导航栏按钮将生成不同的activity容器用于盛放activity.
 * @author duanxin
 *
 */
public class LetterTabHostActivity extends Activity {  
	  
	private ViewPager viewPager;//页卡内容  
    private ImageView imageView;// 动画图片  
    private TextView textView1,textView2;  
   // private List<View> views;// Tab页面列表  
    private int offset = 0;// 动画图片偏移量  
    private int currIndex = 0;// 当前页卡编号  
    private int bmpW;// 动画图片宽度  
    private View view1,view2;//各个页卡  
    LocalActivityManager manager = null;
    Context context=null;
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.viewpager); 
        
        //生成manager
        context = LetterTabHostActivity.this;
        manager = new LocalActivityManager(this , true);
        manager.dispatchCreate(savedInstanceState);
       
        /*
         * 初始化各个view
         */
        InitImageView();  
        InitTextView();  
        InitViewPager();  
    }  
    
    
    @Override
	protected void onResume() {
		UpdateData updata = new UpdateData(LetterTabHostActivity.this,NotifHandler);
		updata.execute("get_notifications");
		super.onResume();
	}
    
    
    public static int  notificationTrue = 0x1200;
    
    /*
     * 用来更新UI
     */
	public Handler NotifHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == notificationTrue )
			{
				Log.d("H", "Handler is in use");
				ArrayList<Notification> notifs = (ArrayList<Notification>) DataRequestUtil.get_notifications(LetterTabHostActivity.this);
				Log.d(DataRequestUtil.dataR,"length of the notif---->"+notifs.size());
				for(Notification notif:notifs)
					Log.i(DataRequestUtil.dataR,notif.toString());
			}
			super.handleMessage(msg);
		}
		
	};


	/** 
     * 初始化步骤
     * 当页卡滑动时，下面的横线也滑动的效果，
     * 在这里需要计算一些数据 
     * 
     */  
  
    private void InitImageView() {  
        imageView= (ImageView) findViewById(R.id.cursor);  
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.tab_host).getWidth();// 获取图片宽度  
        
        DisplayMetrics dm = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        int screenW = dm.widthPixels;// 获取分辨率宽度  
        
        offset = (screenW / 2 - bmpW)/2 ;// 计算偏移量
        
        
        Matrix matrix = new Matrix();  
        matrix.postTranslate(offset, 0);  
        imageView.setImageMatrix(matrix);// 设置动画初始位置  
    }  
    
    
    /** 
     *  初始化页面上方标签
     */  
   private void InitTextView() {  
       textView1 = (TextView) findViewById(R.id.text1);  
       textView2 = (TextView) findViewById(R.id.text2);  
        
       textView1.setOnClickListener(new MyOnClickListener(0));  //为按钮绑定监听器
       textView2.setOnClickListener(new MyOnClickListener(1));         
   }  
   
    
   /**
    * 初始化下方为vPager的显示区域
    * 为下方屏幕下放view设置填充内容的adapter
    */
    private void InitViewPager() {  
    	
        viewPager=(ViewPager) findViewById(R.id.vPager);  
       
        final ArrayList<View> views = new ArrayList<View>();
        Intent intent1 = new Intent(context, LetterActivity.class);
        views.add(getView("A", intent1));//使用getView函数获取view
        
        Intent intent2 = new Intent(context, MessageActivity.class);
        views.add(getView("B", intent2));
       
        
        viewPager.setAdapter(new MyViewPagerAdapter(views));  
        viewPager.setCurrentItem(0);  
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());  
    }  
    
    
    /**
     * 通过activity获取视图
     * @param id
     * @param intent
     * @return
     */
    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }
    
    

  


    /**  
     * 导航栏按钮监听器
     */  
    private class MyOnClickListener implements OnClickListener{  
        private int index=0;  
        public MyOnClickListener(int i){  
            index=i;  
        }  
        public void onClick(View v) {  
            viewPager.setCurrentItem(index);              
        }  
          
    }  
      
    public class MyViewPagerAdapter extends PagerAdapter{  
        private List<View> mListViews;  
          
        public MyViewPagerAdapter(List<View> mListViews) {  
            this.mListViews = mListViews;  
        }  
  
        @Override  
        public void destroyItem(ViewGroup container, int position, Object object)   {     
            container.removeView(mListViews.get(position));  
        }  
  
  
        @Override  
        public Object instantiateItem(ViewGroup container, int position) {            
             container.addView(mListViews.get(position), 0);  
             return mListViews.get(position);  
        }  
  
        @Override  
        public int getCount() {           
            return  mListViews.size();  
        }  
          
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {             
            return arg0==arg1;  
        }  
    }  

    
    public class MyOnPageChangeListener implements OnPageChangeListener{  
  
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量  
       // int two = one * 2;// 页卡1 -> 页卡3 偏移量  
        
        public void onPageScrollStateChanged(int arg0) {  
              
              
        }  
  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
              
              
        }  
  
        public void onPageSelected(int arg0) {  
            
            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);  
            currIndex = arg0;  
            animation.setFillAfter(true);// True:图片停在动画结束位置  
            animation.setDuration(300);  
            imageView.startAnimation(animation);  
            //Toast.makeText(LetterTabHostActivity.this, "您选择了"+ viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();  
        }  
          
    }  
} 