package com.munyu.lucia;

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

	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
		//設定画面の初期設定
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		
		// ホタルデータの初期設定
		TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setText(sharedPref.getString("list_key", ""));
        Integer meimetu_sec = Integer.valueOf(sharedPref.getString("meimetu_sec", "2000"));
        Integer seek_sec = Integer.valueOf(sharedPref.getString("seek_sec", "1000"));
        
		// Tweenアニメーションの適用
		// 初期状態のアニメーションを表示
		ImageView img = (ImageView) findViewById(R.id.imageView3);
		Animation animation= AnimationUtils.loadAnimation(this,R.anim.lightanim);
		img.startAnimation(animation);
		
		// シークバーの初期設定
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setMax(100);
        seekBar.setProgress(50);
		TextView textView2 = (TextView) findViewById(R.id.textView2);
		if (seekBar.getProgress() == 50) {
	        textView2.setText(String.valueOf((meimetu_sec-(seek_sec))/1000.00) + "秒に1回点滅");
		} else {
	        textView2.setText(String.valueOf((meimetu_sec-(seek_sec/(50-seekBar.getProgress())))/1000.00) + "秒に1回点滅");
		}
        seekBar.setOnSeekBarChangeListener(this);
	}
	
	@Override  
	protected void onResume() {  
	    super.onResume();  
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		
		// ホタル名表示に設定の値を更新
		TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setText(sharedPref.getString("list_key", ""));
	}  
		
	//シークバーの処理関連
	
	// シークバートラッキング開始時
	public void onStartTrackingTouch(SeekBar seekBar) {
        Log.v("onStartTrackingTouch()",
            String.valueOf(seekBar.getProgress()));
    }
    // シークバートラッキング中
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
        Log.v("onProgressChanged()",
            String.valueOf(progress) + ", " + String.valueOf(fromTouch));
    }
    // シークバートラッキング終了時
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.v("onStopTrackingTouch()",
            String.valueOf(seekBar.getProgress()));
		ImageView img = (ImageView) findViewById(R.id.imageView3);
		Animation animation= AnimationUtils.loadAnimation(this,R.anim.lightanim);
		img.startAnimation(animation);
		TextView textView2 = (TextView) findViewById(R.id.textView2);
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Integer meimetu_sec = Integer.valueOf(sharedPref.getString("meimetu_sec", "2000"));
        Integer seek_sec = Integer.valueOf(sharedPref.getString("seek_sec", "1000"));
		animation.setDuration((long) ((seekBar.getProgress()*30)/1.5));
		if (seekBar.getProgress() == 50) {
	        textView2.setText(String.valueOf((meimetu_sec-(seek_sec))/1000.00) + "秒に1回点滅");
		} else {
	        textView2.setText(String.valueOf((meimetu_sec-(seek_sec/(50-seekBar.getProgress())))/1000.00) + "秒に1回点滅");
		}
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

}