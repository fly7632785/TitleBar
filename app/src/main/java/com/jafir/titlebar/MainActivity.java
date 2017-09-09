package com.jafir.titlebar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jafir.TitleBar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TitleBar titleBar1 = (TitleBar) findViewById(R.id.title_1);
        TitleBar titleBar2 = (TitleBar) findViewById(R.id.title_2);
        TitleBar titleBar3 = (TitleBar) findViewById(R.id.title_3);
        TitleBar titleBar4 = (TitleBar) findViewById(R.id.title_4);
        TitleBar titleBar5 = (TitleBar) findViewById(R.id.title_5);
        TitleBar titleBar6 = (TitleBar) findViewById(R.id.title_6);


        titleBar2.setTextColor(Color.CYAN);
        titleBar2.setImgPadding(20);
        titleBar2.setTextPadding(10);
        titleBar2.getLeftImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"click",Toast.LENGTH_SHORT).show();
            }
        });

        titleBar6.setTextPadding(5);
        titleBar6.setBackgroundColor(Color.GREEN);
        titleBar6.getLeftImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"left click",Toast.LENGTH_SHORT).show();
            }
        });
        titleBar6.getCenterText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"center click",Toast.LENGTH_SHORT).show();
            }
        });
        titleBar6.getRightImg1().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"right click",Toast.LENGTH_SHORT).show();
            }
        });

        titleBar6.getRightText().setText("hi~");
        titleBar6.setTextColor(Color.BLACK);




    }
}
