package com.example.fran.recuerdame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by yatan on 7/14/16.
 */
public class ActivityInfo extends AppCompatActivity {
    TextView titulo;
    TextView anyo;

    @Override
    protected void onCreate(Bundle saveBundle){
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_info);

        titulo = (TextView) findViewById(R.id.info_titulo);
        anyo = (TextView) findViewById(R.id.info_anyo);

        Intent in = getIntent();
        String sTitle = in.getStringExtra("titulo");
        String sAnyo = in.getStringExtra("anyo");

        titulo.setText(sTitle);
        anyo.setText(sAnyo);
    }

}
