package com.munyu.lucia;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Tweenアニメーションの適用
		ImageView img = (ImageView) findViewById(R.id.imageView3);
		Animation animation= AnimationUtils.loadAnimation(this,R.anim.lightanim);
		img.startAnimation(animation);

	}
}
