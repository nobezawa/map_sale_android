package com.example.map_sale_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectMenuActivity extends Activity implements OnClickListener{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_select_menu);
	    
	    Button now_where_button  = (Button) findViewById(R.id.now_where_button);
	    Button serch_button  = (Button) findViewById(R.id.serch_button);
	    now_where_button.setOnClickListener(this);
	    serch_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id){
		   case R.id.now_where_button:
			   Intent intent = new Intent(this, NowWhereSearchActivity.class);
			   startActivity(intent);
			   break;
		   case R.id.serch_button:
			   Intent intent2 = new Intent(this, FormSearchActivity.class);
			   startActivity(intent2);
			   break;
		}
		
	}

}
