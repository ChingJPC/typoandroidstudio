package com.example.typoandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    ImageView applogoImage;
    MediaPlayer Sonido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                Sonido.stop();
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea,3000);

        //animaciones

        applogoImage = findViewById(R.id.imageView);


        Animation animationionUp = AnimationUtils.loadAnimation(this, R.anim.anim_up);


        applogoImage.setAnimation(animationionUp);

    }
    @Override
    protected void onStart() {
        super.onStart();
        try {
            Sonido = MediaPlayer.create(this,R.raw.ladridoperro);
            Sonido.start();
            //Sonido.setLooping(true);
        }catch (Exception e){
            Log.e("Musica",e.getMessage());
        }
    }
}