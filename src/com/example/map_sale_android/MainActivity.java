package com.example.map_sale_android;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(
		   WindowManager.LayoutParams.FLAG_FULLSCREEN);
		   requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		LinearLayout all_screen = (LinearLayout) findViewById(R.id.all_screen);
		all_screen.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v){
    	int id = v.getId();
    	if (id == R.id.all_screen){
    		Intent intent = new Intent(this, SelectMenuActivity.class);
    		startActivity(intent);
    		finish();
    	}
    	
    }

	


}
