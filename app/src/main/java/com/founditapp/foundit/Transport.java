package com.founditapp.foundit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Transport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Drawable bitmap = getResources().getDrawable(R.drawable.skybus_ad);
        loadPhoto(bitmap, bitmap.getMinimumWidth(), bitmap.getMinimumHeight());

        Spinner spinner = (Spinner) findViewById(R.id.spinnerArea);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.area_array, android.R.layout.simple_spinner_item);
         // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        TextView tFare = (TextView) findViewById(R.id.tFare);
        String selectedText = parent.getItemAtPosition(pos).toString();
        // display fare based on spinner selection
        if (selectedText.equalsIgnoreCase("Petaling Jaya"))
            tFare.setText("Fare: RM 64");
        if (selectedText.equalsIgnoreCase("Subang Jaya"))
            tFare.setText("Fare: RM 65");
        if (selectedText.equalsIgnoreCase("Kuala Lumpur"))
            tFare.setText("Fare: RM 74");
        if (selectedText.equalsIgnoreCase("Johor"))
            tFare.setText("Fare: RM 404");
        if (selectedText.equalsIgnoreCase("Perak"))
            tFare.setText("Fare: RM 204");
        if (selectedText.equalsIgnoreCase("Ampang"))
            tFare.setText("Fare: RM 74");
        if (selectedText.equalsIgnoreCase("Klang"))
            tFare.setText("Fare: RM 74");
        if (selectedText.equalsIgnoreCase("Cheras"))
            tFare.setText("Fare: RM 73");
        if (selectedText.equalsIgnoreCase("Melaka"))
            tFare.setText("Fare: RM 145");
        if (selectedText.equalsIgnoreCase("Nilai"))
            tFare.setText("Fare: RM 42");
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
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
