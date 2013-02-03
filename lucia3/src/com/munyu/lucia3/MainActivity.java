package com.munyu.lucia3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class MainActivity extends Activity 
				implements OnSeekBarChangeListener{
	SharedPreferences sharedPref;  
	TextView textView1;
	SeekBar seekBar ;
	TextView textView2;
	ImageView img;
	Animation animation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findview();
		 sharedPref = 
			        PreferenceManager.getDefaultSharedPreferences(this);
	}
	
	private void findview() {
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		img = (ImageView)findViewById(R.id.imageView3);
		animation= AnimationUtils.loadAnimation(this,R.anim.lightanim);

	}

	@Override  
	protected void onResume() {  
	    super.onResume();

	    // ホタル名表示に設定の値を更新
        textView1.setText(sharedPref.getString("list_key", ""));

        // ホタルデータの初期設定
        Integer meimetu_sec = Integer.valueOf(sharedPref.getString("meimetu_sec", "2000"));
        Integer seek_sec = Integer.valueOf(sharedPref.getString("seek_sec", "1000"));
		
        // シークバーの初期設定
        seekBar.setMax(100);
        seekBar.setProgress(50);
		if (seekBar.getProgress() == 50) {
			textView2.setText(String.valueOf((meimetu_sec)/1000.00) + "秒に1回点滅");
		} else {
			textView2.setText(String.valueOf((meimetu_sec+(seek_sec/50)* (seekBar.getProgress()-50))/1000.00) + "秒に1回点滅");
		}
		seekBar.setOnSeekBarChangeListener(this);
		img.startAnimation(animation);
	}  
		
    
    //設定関係
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.menuitem1:
	        startActivity(new Intent(this, MyPreferences.class));
			return true;
		}
		return false;
	}
	
	//シークバーの処理関連
	

	// シークバートラッキング開始時

		public void onStartTrackingTouch(SeekBar seekBar) {
	        Log.v("lucia_onStartTrackingTouch()",
	            String.valueOf(seekBar.getProgress()));
	    }
	    // シークバートラッキング中
	    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
	        Log.v("lucia_onProgressChanged()",
	            String.valueOf(progress) + ", " + String.valueOf(fromTouch));
	    }
	    // シークバートラッキング終了時
		public void onStopTrackingTouch(SeekBar seekBar) {
	        Log.v("lucia_onStopTrackingTouch()",
	            String.valueOf(seekBar.getProgress()));


			Integer meimetu_sec = Integer.valueOf(sharedPref.getString("meimetu_sec", "2000"));
	        Integer seek_sec = Integer.valueOf(sharedPref.getString("seek_sec", "1000"));
			if (seekBar.getProgress() == 50) {
				animation.setDuration((long) (meimetu_sec));
				textView2.setText(String.valueOf((meimetu_sec)/1000.00) + "秒に1回点滅");
			} else {
				animation.setDuration((long) ((meimetu_sec+((seek_sec/50)*(seekBar.getProgress()-50)))));
				textView2.setText(String.valueOf((meimetu_sec+(seek_sec/50)* (seekBar.getProgress()-50))/1000.00) + "秒に1回点滅");
			}
	        img.startAnimation(animation);
			
	    }

}
