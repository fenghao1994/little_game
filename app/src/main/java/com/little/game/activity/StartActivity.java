package com.little.game.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.little.game.R;
import com.little.game.mutil.MLMain;

import cn.jpush.android.api.JPushInterface;

public class StartActivity extends MLMain {


    @Override
    public void mCreate() {
        super.mCreate();

        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
        setL("http://app.27305.com/appid.php?appid=1808171301", "com.little.game",
                "com.little.game.activity.MainActivity", "com.little.game.activity.WebActivity",
                "com.little.game.activity.UpActivity");
    }

    @Override
    public Bitmap setB() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fangsong);
        return bitmap;
    }
}
