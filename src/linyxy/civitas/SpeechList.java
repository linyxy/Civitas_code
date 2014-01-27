package linyxy.civitas;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.widget.SimpleAdapter;

public class SpeechList {

	public SpeechList() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	public static SimpleAdapter getSpeechAdapter(Context ctx,Activity act,
			JSONArray data,String property,boolean hasIcon)
	{
		SimpleAdapter adp;
		adp = new SimpleAdapter(ctx,(List<? extends Map<String, ?>>) data,R.layout.speech,
				ctx.getResources().getStringArray(R.array.speech),ctx.getResources().getIntArray(R.array.speech));
		return adp;
	}
}
