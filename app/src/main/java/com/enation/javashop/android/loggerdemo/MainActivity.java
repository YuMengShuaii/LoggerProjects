package com.enation.javashop.android.loggerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.enation.javashop.utils.logger.Cache;
import com.enation.javashop.utils.logger.LogType;
import com.enation.javashop.utils.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.LoggerInit(this, true);
        findViewById(R.id.printD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("在苍茫美丽马勒戈壁有一群草拟吗！");
            }
        });
        findViewById(R.id.printE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("顺风吹牛逼，逆风讲道理!");
            }
        });
        findViewById(R.id.Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.clearLocal();
            }
        });
        findViewById(R.id.printI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("你知道我爱你爱的快要疯掉么!");
            }
        });
    }
}
