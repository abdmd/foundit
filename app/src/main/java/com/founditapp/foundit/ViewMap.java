package com.founditapp.foundit;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ViewMap extends AppCompatActivity {

    ImageView mImageView;
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Any implementation of ImageView can be used!
        mImageView = (ImageView) findViewById(R.id.floorPlan);

        // Set the Drawable displayed
        final Drawable bitmapArr = getResources().getDrawable(R.drawable.arrivalhall_new);
        final Drawable bitmapDep = getResources().getDrawable(R.drawable.departurehall_new);
        final Drawable bitmapTrans = getResources().getDrawable(R.drawable.transport_layout);

        mImageView.setImageDrawable(bitmapArr);

        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        // (not needed unless you are going to change the drawable later)
        mAttacher = new PhotoViewAttacher(mImageView);

        // navigate to arrival
        ImageView bArr = (ImageView)findViewById(R.id.btnArrival);
        assert bArr != null;
        assert mImageView != null;
        bArr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.setImageDrawable(bitmapArr);
                mAttacher.update();
            }
        });

        // navigate to departure
        ImageView bDep= (ImageView)findViewById(R.id.btnDeparture);
        assert bDep != null;
        assert mImageView != null;
        bDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mImageView.setImageDrawable(bitmapDep);
                mAttacher.update();
            }
        });

        // navigate to transport
        ImageView bTrans= (ImageView)findViewById(R.id.btnTransport);
        assert bTrans != null;
        assert mImageView != null;
        bTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mImageView.setImageDrawable(bitmapTrans);
                mAttacher.update();
            }
        });
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
