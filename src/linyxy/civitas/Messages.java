package linyxy.civitas;

import linyxy.civitas.R.layout;
import android.app.Activity;
import android.os.Bundle;

/*
 * 站内信的总集页面，显示显示所有coversations
 */
public class Messages extends Activity {

	public Messages() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(layout.messages);
	}

}
