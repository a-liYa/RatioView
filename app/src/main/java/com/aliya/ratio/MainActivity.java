package com.aliya.ratio;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aliya.view.ratio.RatioRelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RatioRelativeLayout ratioLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(this);

        ratioLl = (RatioRelativeLayout) findViewById(R.id.ratio_ll);
    }

    @Override
    public void onClick(View view) {
        ratioLl.setRatio(2);
    }
}
