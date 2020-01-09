package ca.bcit.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button javaBtn;
    private Button kotlinBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        javaBtn = findViewById(R.id.javaBtn);

        kotlinBtn = findViewById(R.id.kotlinBtn);

        final Intent javaAct = new Intent(this, MainActivity.class);

        final Intent kotlinAct = new Intent(this, KotlinMainActivity.class);
        javaBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(javaAct);
                    }
                }
        );

        kotlinBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(kotlinAct);
                    }
                }
        );
    }

}
