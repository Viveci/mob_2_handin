package com.example.user.achtung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    private Button play;
    private Button host;
    private Button score;
    private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        play = (Button) findViewById(R.id.play);
        host = (Button) findViewById(R.id.host);
        score = (Button) findViewById(R.id.score);
        settings = (Button) findViewById(R.id.settings);

        play.setOnClickListener(this);
        host.setOnClickListener(this);
        score.setOnClickListener(this);
        settings.setOnClickListener(this);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){

            case R.id.play:
                Toast.makeText(this,"play",Toast.LENGTH_SHORT).show();
                i = new Intent(this, Play.class);
                startActivity(i);
                break;
            case R.id.host:
                Toast.makeText(this,"host",Toast.LENGTH_SHORT).show();
                i = new Intent(this, Host.class);
                startActivity(i);
                break;
            case R.id.score:
                Toast.makeText(this,"score",Toast.LENGTH_SHORT).show();
                i = new Intent(this, Score.class);
                startActivity(i);
                break;
            case R.id.settings:
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
                i = new Intent(this, SetPanel.class);
                startActivity(i);
                break;
        }

    }

}
