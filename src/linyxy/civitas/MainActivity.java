package linyxy.civitas;

import linyxy.fragment.MarketFragment;
import linyxy.fragment.My2Fragment;
import linyxy.fragment.SquareFragment;
import linyxy.fragment.StreetFragment;
import linyxy.slidingmenu.SlidingMenuView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class MainActivity extends FragmentActivity {
	SlidingMenuView slidingMenuView;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_activity);
        
        slidingMenuView = (SlidingMenuView) findViewById(R.id.sliding_menu_view);
        
        ///////////////////
        if(findViewById(R.id.sliding_body_mysecond)!=null)
        {
        	if(savedInstanceState!=null)
        	{
        		return;
        	}
        My2Fragment my2Fragment=new My2Fragment();
        my2Fragment.setArguments(getIntent().getExtras());
        
        getSupportFragmentManager().beginTransaction()
        .add(R.id.sliding_body_mysecond, my2Fragment).commit();
        }
        
        /////////////////////
        if(findViewById(R.id.sliding_body_square)!=null)
        {
        	if(savedInstanceState!=null)
        	{
        		return;
        	}       	
        SquareFragment squareFragment=new SquareFragment();
        squareFragment.setArguments(getIntent().getExtras());      
        getSupportFragmentManager().beginTransaction()
        .add(R.id.sliding_body_square, squareFragment).commit();
        }
        
        ///////////////////////////
        if(findViewById(R.id.sliding_body_street)!=null)
        {
        	if(savedInstanceState!=null)
        	{
        		return;
        	}
        StreetFragment streetFragment=new StreetFragment();
        streetFragment.setArguments(getIntent().getExtras());
        
        getSupportFragmentManager().beginTransaction()
        .add(R.id.sliding_body_street, streetFragment).commit();
        }
        
        ////////////////////////////
        if(findViewById(R.id.sliding_body_market)!=null)
        {
        	if(savedInstanceState!=null)
        	{
        		return;
        	}
        MarketFragment marketFragment=new MarketFragment();
        marketFragment.setArguments(getIntent().getExtras());
        
        getSupportFragmentManager().beginTransaction()
        .add(R.id.sliding_body_market, marketFragment).commit();
        }
    }
    
    public static void logout(Context ctx)
    {
    	Intent intent = new Intent();
    	intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    	intent.setClass(ctx,MainActivity.class);
    	
    	ctx.startActivity(intent);
    	
    }
    
}
        