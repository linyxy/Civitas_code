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
 * ����һ�������ϵ�������activity�࣬
 * ��������ť�����ɲ�ͬ��activity��������ʢ��activity.
 * @author duanxin
 *
 */
public class LetterTabHostActivity extends Activity {  
	  
	private ViewPager viewPager;//ҳ������  
    private ImageView imageView;// ����ͼƬ  
    private TextView textView1,textView2;  
   // private List<View> views;// Tabҳ���б�  
    private int offset = 0;// ����ͼƬƫ����  
    private int currIndex = 0;// ��ǰҳ�����  
    private int bmpW;// ����ͼƬ���  
    private View view1,view2;//����ҳ��  
    LocalActivityManager manager = null;
    Context context=null;
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.viewpager); 
        
        //����manager
        context = LetterTabHostActivity.this;
        manager = new LocalActivityManager(this , true);
        manager.dispatchCreate(savedInstanceState);
       
        /*
         * ��ʼ������view
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
     * ��������UI
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
     * ��ʼ������
     * ��ҳ������ʱ������ĺ���Ҳ������Ч����
     * ��������Ҫ����һЩ���� 
     * 
     */  
  
    private void InitImageView() {  
        imageView= (ImageView) findViewById(R.id.cursor);  
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.tab_host).getWidth();// ��ȡͼƬ���  
        
        DisplayMetrics dm = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��  
        
        offset = (screenW / 2 - bmpW)/2 ;// ����ƫ����
        
        
        Matrix matrix = new Matrix();  
        matrix.postTranslate(offset, 0);  
        imageView.setImageMatrix(matrix);// ���ö�����ʼλ��  
    }  
    
    
    /** 
     *  ��ʼ��ҳ���Ϸ���ǩ
     */  
   private void InitTextView() {  
       textView1 = (TextView) findViewById(R.id.text1);  
       textView2 = (TextView) findViewById(R.id.text2);  
        
       textView1.setOnClickListener(new MyOnClickListener(0));  //Ϊ��ť�󶨼�����
       textView2.setOnClickListener(new MyOnClickListener(1));         
   }  
   
    
   /**
    * ��ʼ���·�ΪvPager����ʾ����
    * Ϊ�·���Ļ�·�view����������ݵ�adapter
    */
    private void InitViewPager() {  
    	
        viewPager=(ViewPager) findViewById(R.id.vPager);  
       
        final ArrayList<View> views = new ArrayList<View>();
        Intent intent1 = new Intent(context, LetterActivity.class);
        views.add(getView("A", intent1));//ʹ��getView������ȡview
        
        Intent intent2 = new Intent(context, MessageActivity.class);
        views.add(getView("B", intent2));
       
        
        viewPager.setAdapter(new MyViewPagerAdapter(views));  
        viewPager.setCurrentItem(0);  
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());  
    }  
    
    
    /**
     * ͨ��activity��ȡ��ͼ
     * @param id
     * @param intent
     * @return
     */
    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }
    
    

  


    /**  
     * ��������ť������
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
  
        int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����  
       // int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����  
        
        public void onPageScrollStateChanged(int arg0) {  
              
              
        }  
  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
              
              
        }  
  
        public void onPageSelected(int arg0) {  
            
            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);  
            currIndex = arg0;  
            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��  
            animation.setDuration(300);  
            imageView.startAnimation(animation);  
            //Toast.makeText(LetterTabHostActivity.this, "��ѡ����"+ viewPager.getCurrentItem()+"ҳ��", Toast.LENGTH_SHORT).show();  
        }  
          
    }  
} 