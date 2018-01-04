package com.enation.javashop.android.loggerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.enation.javashop.utils.logger.LogType;
import com.enation.javashop.utils.logger.Logger;
import com.enation.javashop.utils.logger.LoggerFactory;
import com.enation.javashop.utils.logger.LoggerInterceptor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoggerInterceptor interceptor = (new LoggerInterceptor() {
            @Override
            public void intercept(@LogType.Control int type, String tag, String content) {
                Log.e("1111","1111");
            }
        });
        final Logger logger = LoggerFactory.create(this)
                .diskCache()
                .setTag("JAVASHOP_TEST")
                .addInterceptor(interceptor)
                .build();

        findViewById(R.id.printD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.d("在苍茫美丽马勒戈壁有一群草拟吗！");
            }
        });
        findViewById(R.id.printE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.e("顺风吹牛逼，逆风讲道理!");
            }
        });
        findViewById(R.id.Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.clearLocal();
            }
        });
        findViewById(R.id.printI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.i("你知道我爱你爱的快要疯掉么!");
            }
        });
    }
}
