package fiture.quiamco.com.homefiture.Exercises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fiture.quiamco.com.homefiture.R;

public class day3moreheavy2 extends AppCompatActivity {
    private Button Next,Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day3moreheavy2);

        Next=(Button)findViewById (R.id.button73);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(day3moreheavy2.this, day3moreheavy3.class);
                startActivity(intent);
                finish();
            }
        });
        Back=(Button)findViewById(R.id.button74);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(day3moreheavy2.this, day3moreheavy2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
