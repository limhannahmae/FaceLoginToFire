package fiture.quiamco.com.homefiture.Exercises;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rilixtech.materialfancybutton.MaterialFancyButton;

import fiture.quiamco.com.homefiture.NavDrawer;
import fiture.quiamco.com.homefiture.R;
import fiture.quiamco.com.homefiture.models.User;
import pl.droidsonroids.gif.GifTextView;

public class day5heavy extends AppCompatActivity {

    GifTextView img;
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
    private SharedPreferences sharedPreferences;
    private String id;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference userRef;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day5heavy);

        img = (GifTextView) findViewById(R.id.gifTextView);
        sharedPreferences = getApplicationContext().getSharedPreferences("FitureUser", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("userKey", "");
        Log.d("idOy",id);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("dailyChallenge");
        userRef = database.getReference("UserFiture");
        Bundle inBundle = getIntent().getExtras();
        user = (User) inBundle.getSerializable("user");
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

                myCountDownTimer = new day5heavy.MyCountDownTimer(10000, 1000);

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
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Instructions:");
                //set dialog message
                alertDialogBuilder
                        .setMessage("1.)Lie face down on a hyperextension bench, tucking your ankles securely under the footpads. " +
                                "2.) Adjust the upper pad if possible so your upper thighs lie flat across the wide pad, leaving enough " +
                                "room for you to bend at the waist without any restriction. " +
                                "3.) With your body straight, cross your arms in front of you my preference " +
                                "or behind your head. This will be your starting position. " +
                                "4.)Repeat for the recommended amount of repetitions.  ")
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
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(id)){
                            Log.d("idOy","naaID");
                            final DatabaseReference matchesRefTemp = dataSnapshot.getRef().child(id);
                            matchesRefTemp.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild("6")){
                                        Log.d("idOy","naaJOD");
                                        final DatabaseReference ref0 = dataSnapshot.getRef().child("10");

                                        ref0.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                String status = dataSnapshot.child("status").getValue().toString();
                                                if(status.equalsIgnoreCase("unlock4")){
                                                    ref0.child("status").setValue("done");
                                                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            if(dataSnapshot.hasChild(id)){
                                                                final DatabaseReference refUserID = dataSnapshot.getRef().child(id);
                                                                refUserID.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        int userPoints = Integer.parseInt(dataSnapshot.child("userPoints").getValue().toString());
                                                                        userPoints+=30;
                                                                        Toast.makeText(day5heavy.this, "Congratulations, you received 30 points!", Toast.LENGTH_LONG).show();
                                                                        refUserID.child("userPoints").setValue(userPoints);
                                                                        user.setUserPoints(String.valueOf(userPoints));
                                                                        Intent proc = new Intent(day5heavy.this, NavDrawer.class);
                                                                        Bundle bundle = new Bundle();
                                                                        bundle.putSerializable("user", user);
                                                                        proc.putExtras(bundle);
                                                                        startActivity(proc);
                                                                        finish();
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(DatabaseError databaseError) {

                                                                    }
                                                                });
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    });
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//                Intent step = new Intent(exercise1mostheavy.this, exercise2mostheavy.class);
//                startActivity(step);

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
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(id)){
                        Log.d("idOy","naaID");
                        final DatabaseReference matchesRefTemp = dataSnapshot.getRef().child(id);
                        matchesRefTemp.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild("6")){
                                    Log.d("idOy","naaJOD");
                                    final DatabaseReference ref0 = dataSnapshot.getRef().child("10");

                                    ref0.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            String status = dataSnapshot.child("status").getValue().toString();
                                            if(status.equalsIgnoreCase("unlock4")){
                                                ref0.child("status").setValue("done");
                                                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if(dataSnapshot.hasChild(id)){
                                                            final DatabaseReference refUserID = dataSnapshot.getRef().child(id);
                                                            refUserID.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    int userPoints = Integer.parseInt(dataSnapshot.child("userPoints").getValue().toString());
                                                                    userPoints+=30;
                                                                    Toast.makeText(day5heavy.this, "Congratulations, you received 30 points!", Toast.LENGTH_LONG).show();
                                                                    refUserID.child("userPoints").setValue(userPoints);
                                                                    user.setUserPoints(String.valueOf(userPoints));
                                                                    Intent proc = new Intent(day5heavy.this, NavDrawer.class);
                                                                    Bundle bundle = new Bundle();
                                                                    bundle.putSerializable("user", user);
                                                                    proc.putExtras(bundle);
                                                                    startActivity(proc);
                                                                    finish();
                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }


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
            if (mins == 0 && secs == 10) {
                finish.setVisibility(View.VISIBLE);
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

            }
        }

    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // to do
        finish();
    }
}
