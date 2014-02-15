package linyxy.civitas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LetterActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);					
		
		Toast.makeText(getApplicationContext(),"Õ¾ÄÚÐÅ",Toast.LENGTH_SHORT).show();	
		setContentView(R.layout.sub);
		
	}
}

