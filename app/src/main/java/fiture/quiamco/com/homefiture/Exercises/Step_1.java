package fiture.quiamco.com.homefiture.Exercises;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rilixtech.materialfancybutton.MaterialFancyButton;

import fiture.quiamco.com.homefiture.R;
import pl.droidsonroids.gif.GifTextView;

public class Step_1 extends AppCompatActivity {
    GifTextView img;
    //    String url ="http://agile-gorge-65786.herokuapp.com/gallery/images/59d85efb89fe4day2.jpg  ";
    final Context context = this;
    private MaterialFancyButton startButton;
    private MaterialFancyButton pauseButton;

    ProgressBar progressBar;
    MaterialFancyButton start_timer, stop_timer;
    CountDownTimer myCountDownTimer;

    private TextView timerValue;

    private long startTime = 0L;

    private Handler customHandler = new Handler();
    private int points = 10;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    MaterialFancyButton finish;
    MaterialFancyButton inst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_1);

        img = (GifTextView) findViewById(R.id.gifTextView);

        inst = (MaterialFancyButton) findViewById(R.id.btnInstruction);
//        loadImage();

        finish = (MaterialFancyButton) findViewById(R.id.btnFinish);

        timerValue = (TextView) findViewById(R.id.timerValue);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        start_timer = (MaterialFancyButton) findViewById(R.id.btnStart);
        stop_timer = (MaterialFancyButton) findViewById(R.id.btnStop);
        start_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCountDownTimer = new MyCountDownTimer(10000, 1000);

                myCountDownTimer.start();


            }
        });
        stop_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCountDownTimer.cancel();

            }
        });

        inst.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Instructions:");
                //set dialog message
                alertDialogBuilder
                        .setMessage("1.) Position your body on an incline bench on a 30-45 degree angle. " +
                                "Grab a barbell with an overhand grip that's shoulder-width apart and hold it above your chest. " +
                                "Extend arms upward, locking out elbows." +
                                "2.) Lower the bar straight down in a slow, controlled movement to your chest. " +
                                "Pause, then press the bar in a straight line back up to the starting position.")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
//                                Step_1.this.finish();
                            }
                        });


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }




        });

        finish.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent step = new Intent(Step_1.this, Step_2.class);
                startActivity(step);

            }
        });
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int progress = (int) (millisUntilFinished / 1000);

            progressBar.setProgress(progressBar.getMax() - progress);
        }

        @Override
        public void onFinish() {
            finish();
        }
    }


//        //startButton = (MaterialFancyButton) findViewById(R.id.btnStart);
//        finish.setVisibility(View.VISIBLE);
//
//        finish.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                    Intent step = new Intent(Step_1.this, Step_2.class);
//                    step.putExtra("score", points);
//                    startActivity(step);
//
//            }
//       });

//        inst.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view){
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//                //set title
//                alertDialogBuilder.setTitle("Instructions:");
//                //set dialog message
//                alertDialogBuilder
//                        .setMessage("1.) Position your body on an incline bench on a 30-45 degree angle. " +
//                                "Grab a barbell with an overhand grip that's shoulder-width apart and hold it above your chest. " +
//                                "Extend arms upward, locking out elbows." +
//                                "2.) Lower the bar straight down in a slow, controlled movement to your chest. " +
//                                "Pause, then press the bar in a straight line back up to the starting position.")
//                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int id) {
//                                // if this button is clicked, close
//                                // current activity
////                                Step_1.this.finish();
//                            }
//                        });
//
//
//                // create alert dialog
//                AlertDialog alertDialog = alertDialogBuilder.create();
//
//                // show it
//                alertDialog.show();
//            }
//
//
//
//
//        });

//        startButton = findViewById(R.id.)
//        startButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                startTime = SystemClock.uptimeMillis();
//                customHandler.postDelayed(updateTimerThread, 0);
//
//            }
//        });
//        pauseButton = (MaterialFancyButton) findViewById(R.id.btnPause);
//
//        pauseButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//
//                timeSwapBuff += timeInMilliseconds;
//                customHandler.removeCallbacks(updateTimerThread);
//
//            }
//        });

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
            if(mins == 0 && secs == 10){
            finish.setVisibility(View.VISIBLE);
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

            }
        }

    };
//    public void loadImage() {
//        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>(){
//            @Override
//            public void onResponse(Bitmap b) {
//                img.setImageBitmap(b);
//            }
//        }, 0, 0, null,
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        Toast.makeText(Step_1.this, "Some error occurred!!", Toast.LENGTH_LONG).show();
//                    }
//                });
//        RequestQueue rQueue = Volley.newRequestQueue(Step_1.this);
//        rQueue.add(request);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // to do
        finish();
    }

}