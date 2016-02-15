package com.gushuwang.android.deviceinfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * MainActivity
 * 
 * @author swgu
 * @datetime 2016-2-15 下午3:46:56
 */
public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";

	private ViewGroup mDeviceInfoContainer;
	private ViewGroup mProcessInfoContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		addData();
	}
	
	private void initView() {
		mDeviceInfoContainer = (ViewGroup) findViewById(R.id.device_info_container);
		mProcessInfoContainer = (ViewGroup) findViewById(R.id.app_info_container);
	}
	
	private void addData() {
		
		addDataRow("包名称：", getPackageName(), mProcessInfoContainer);
		
		// package info
		PackageManager pm = getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.toString());
		}
	}
	
	/**
	 * 增加一行信息
	 * 
	 * @param title
	 * @param info
	 * @param root
	 */
	private void addDataRow(CharSequence title, CharSequence info, ViewGroup root) {
		TextView titleView = new TextView(this);
		titleView.setText(title);
		
		TextView infoView = new TextView(this);
		infoView.setText(info);
		
		LinearLayout container = new LinearLayout(this);
		LinearLayout.LayoutParams containerLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		containerLp.setMargins(60, 0, 0, 0);
		
		container.addView(titleView);
		container.addView(infoView);
		
		root.addView(container, containerLp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
