package com.founditapp.foundit;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FlightSchedule extends AppCompatActivity {

    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private TextView barcodeInfo;
    private static final int CAMERA_FACING_FRONT = 1;
    private static final int CAMERA_FACING_BACK = 0;
    private Handler handler;
    private Runnable runnable;
    private TextView tvDay, tvHour, tvMinute, tvSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_schedule);
        Toolbar toolbarFlight = (Toolbar) findViewById(R.id.toolbarFlight);

        final TextView tvFlight = (TextView) findViewById(R.id.tvFlight);
        final TextView tvDate = (TextView) findViewById(R.id.tvDate);
        final TextView tvTime = (TextView) findViewById(R.id.tvTime);
        final TextView tvDestination = (TextView) findViewById(R.id.tvDestination);
        final TextView tvGate = (TextView) findViewById(R.id.tvGate);

        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMinute);
        tvSecond = (TextView) findViewById(R.id.tvSecond);

        setSupportActionBar(toolbarFlight);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
        tvDay.setTypeface(tf);
        tvHour.setTypeface(tf);
        tvMinute.setTypeface(tf);
        tvSecond.setTypeface(tf);

        // start countdown
        /*** QR Code detection ***/
        cameraView = (SurfaceView)findViewById(R.id.camera_view);
        barcodeInfo = (TextView)findViewById(R.id.tvFlight);

        barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .setFacing(CAMERA_FACING_FRONT)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (barcodes.valueAt(0).displayValue.contains("10001")) {
                                tvFlight.setText("UL103");
                                tvDate.setText("21 May 2016");
                                tvTime.setText("22:30");
                                tvDestination.setText("COLOMBO");
                                tvGate.setText("L-12");
                                countDownStart(21,22,30,00);

                            }

                            if (barcodes.valueAt(0).displayValue.contains("10002"))
                            {
                                //result of second barcode
                                tvFlight.setText("EK112");
                                tvDate.setText("22 May 2016");
                                tvTime.setText("7:30");
                                tvDestination.setText("DUBAI");
                                tvGate.setText("K-4");
                                countDownStart(22, 7, 30, 00);

                            }

                            if (barcodes.valueAt(0).displayValue.contains("10003"))
                            {
                                //result of second barcode
                                tvFlight.setText("RX102");
                                tvDate.setText("22 May 2016");
                                tvTime.setText("16:00");
                                tvDestination.setText("CHANGI");
                                tvGate.setText("P-6");
                                countDownStart(22, 16, 00, 00);

                            }

                            if (barcodes.valueAt(0).displayValue.contains("10004"))
                            {
                                //result of second barcode
                                tvFlight.setText("AK72");
                                tvDate.setText("21 May 2016");
                                tvTime.setText("20:55");
                                tvDestination.setText("MALDIVES");
                                tvGate.setText("L-11");
                                countDownStart(21, 20, 55, 00);
                            }
                        }
                    });

                }
            }
        });
    }

    public void countDownStart(final int day, final int hour, final int minute, final int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minute);
        cal.set(Calendar.SECOND,second);
        cal.set(Calendar.MILLISECOND,0);
        cal.set(2016, Calendar.MAY, day);

        final Date d = cal.getTime();

        // avoid multiple handlers, remove any running threads
        if (handler == null)
            handler = new Handler();
        else
            handler.removeCallbacks(runnable);

        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    // Here Set your Event Date
                    Date futureDate = d;
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        tvDay.setText("" + String.format("%02d", days));
                        tvHour.setText("" + String.format("%02d", hours));
                        tvMinute.setText("" + String.format("%02d", minutes));
                        tvSecond.setText("" + String.format("%02d", seconds));
                    } else {
                        // linearLayout1.setVisibility(View.VISIBLE);
                        // linearLayout2.setVisibility(View.GONE);
                        // tvEvent.setText("Android Event Start");
                        tvDay.setText("0");
                        tvHour.setText("0");
                        tvMinute.setText("0");
                        tvSecond.setText("0");
                        handler.removeCallbacks(runnable);
                        // handler.removeMessages(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
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
    protected void onDestroy() {
        super.onDestroy();
        cameraSource.release();
        barcodeDetector.release();
    }
}
