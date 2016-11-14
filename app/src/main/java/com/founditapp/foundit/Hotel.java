package com.founditapp.foundit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Hotel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Drawable bitmap = getResources().getDrawable(R.drawable.tune_ad);
        loadPhoto(bitmap, bitmap.getMinimumWidth(), bitmap.getMinimumHeight());

        // tune
        ImageView bTune = (ImageView)findViewById(R.id.imgTune);
        assert bTune != null;
        bTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tunehotels.com/my/en")));
            }
        });

        //capsule
        ImageView bCapsule = (ImageView)findViewById(R.id.imgCapsule);
        assert bCapsule != null;
        bCapsule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://capsulecontainer.com/")));
            }
        });

        //concorde
        ImageView bConcorde = (ImageView)findViewById(R.id.imgConcorde);
        assert bConcorde != null;
        bConcorde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://sepang.concordehotelsresorts.com/")));
            }
        });

        //sama
        ImageView bSama = (ImageView)findViewById(R.id.imgSama);
        assert bSama != null;
        bSama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.samasamahotels.com/")));
            }
        });

        //sri
        ImageView bSri = (ImageView)findViewById(R.id.imgSri);
        assert bSri != null;
        bSri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.agoda.com/sri-packers-hotel-klia/hotel/kuala-lumpur-my.html")));
            }
        });

        //plaza
        ImageView bPlaza = (ImageView)findViewById(R.id.imgPlaza);
        assert bPlaza != null;
        bPlaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.booking.com/hotel/my/plaza-premium-lounge-malaysia.html")));
            }
        });
    }

    private void loadPhoto(Drawable bmp, int width, int height) {

        //ImageView tempImageView = imageView;


        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.custom_fullimage_dialog,
                (ViewGroup) findViewById(R.id.layout_root));
        ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
        image.setImageDrawable(bmp);
        imageDialog.setView(layout);
        imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        imageDialog.create();
        imageDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
