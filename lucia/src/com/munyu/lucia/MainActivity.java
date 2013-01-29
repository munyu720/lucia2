package com.munyu.lucia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Tweenアニメーションの適用
		// 初期状態のアニメーションを表示
		final ImageView img = (ImageView) findViewById(R.id.imageView3);
		final Animation animation= AnimationUtils.loadAnimation(this,R.anim.lightanim);
		img.startAnimation(animation);
		
		// テキストビューの初期設定
		final TextView textView = (TextView) findViewById(R.id.textView2);
		
		// シークバーの設定
		// 変更後に明滅間隔を変更
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setMax(100);
        seekBar.setProgress(50);
        // テキストビューのテキストを設定します
        textView.setText(String.valueOf((seekBar.getProgress()*30.00)/1500)+"秒に1回点滅");
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            // トラッキング開始時に呼び出されます
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.v("onStartTrackingTouch()",
                    String.valueOf(seekBar.getProgress()));
            }
            // トラッキング中に呼び出されます
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                Log.v("onProgressChanged()",
                    String.valueOf(progress) + ", " + String.valueOf(fromTouch));
            }
            // トラッキング終了時に呼び出されます
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.v("onStopTrackingTouch()",
                    String.valueOf(seekBar.getProgress()));
                animation.setDuration((long) ((seekBar.getProgress()*30)/1.5));
             // テキストビューのテキストを設定します
                textView.setText(String.valueOf((seekBar.getProgress()*30.00)/1500)+"秒に1回点滅");
                img.startAnimation(animation);
            }
            
        });
	}
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
			showMessage("Hello! Item1");
	        startActivity(new Intent(this, MyPreferences.class));
			return true;
		}
		return false;
	}
	
	protected void showMessage(String msg){
		Toast.makeText(
			this, 
			msg, Toast.LENGTH_SHORT).show();
	}
}
