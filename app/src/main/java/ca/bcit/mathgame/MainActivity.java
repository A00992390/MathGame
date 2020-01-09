package ca.bcit.mathgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // answer button 1
    private Button ans1;
    // answer button 2
    private Button ans2;
    // answer button 3
    private Button ans3;
    // skip button
    private Button skip;
    // reset button
    private Button reset;
    // score / 5
    private int score;
    // tracking the num of question displayed
    private int numQ;
    // for notification id
    public static final int NOTIFICATION_ID = 5453;
    // question textview
    private TextView questionTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializing default
        ans1 = findViewById(R.id.answer1);
        ans2 = findViewById(R.id.answer2);
        ans3 = findViewById(R.id.answer3);
        skip = findViewById(R.id.skipBtn);
        reset = findViewById(R.id.restBtn);
        questionTv = findViewById(R.id.questionTv);
        score = 0;
        // generate question
        generateQuestion();
        numQ = 0;
        // skip btn listener
        skip.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(++numQ <= 5)
                            generateQuestion();
                    }
                }
        );
        // reset btn listener
        reset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score = 0;
                        // generate question
                        generateQuestion();
                        numQ = 0;
                    }
                }
        );
    }

    // generate random operation
    private int getOpR() {
        return new Random().nextInt(4);
    }

    // generate random operation
    private int getIntR() {
        return new Random().nextInt(101);
    }

    // generate random operation
    private int getBtnR() {
        return new Random().nextInt(3);
    }

    // generate math operation and answers
    private void generateQuestion() {
        // two ran int
        int a = getIntR();
        int b = getIntR();
        // get the question
        switch (getOpR()) {
            case 0:
                questionTv.setText(a + " + " + b );
                generateQuestion(a, b,0);
                break;
            case 1:
                while (a < b) { // must a > b
                    a = getIntR();
                    b = getIntR();
                }
                questionTv.setText(a + " - " + b );
                generateQuestion(a, b, 1);
                break;
            case 2:
                questionTv.setText(a + " * " + b );
                generateQuestion(a, b, 2);
                break;
            case 3:
                while (b == 0 || a % b != 0) {  // b cant be 0 and a % b == 0
                    a = getIntR();
                    b = getIntR();
                }
                questionTv.setText(a + " / " + b );
                generateQuestion(a, b, 3);
                break;
        }
    }

    // generate answers
    private void generateQuestion(int a, int b, int op) {
        int btnR = getBtnR();
        checkRightAnswer(btnR);
        switch (op) {
            case 0:
                // two wrong answers
                int sum2 = new Random().nextInt(a + b) + 4;
                int sum3 = new Random().nextInt(a + b) + 10;
                while (sum2 == a + b) {
                    sum2 = new Random().nextInt(a + b) + 4;
                }
                while (sum3 == a + b) {
                    sum3 = new Random().nextInt(a + b) + 10;
                }
                if(btnR == 0) {
                    ans1.setText(Integer.toString(a + b));
                    ans2.setText(Integer.toString(sum2));
                    ans3.setText(Integer.toString(sum3));
                } else if(btnR == 1) {
                    ans2.setText(Integer.toString(a + b));
                    ans1.setText(Integer.toString(sum2));
                    ans3.setText(Integer.toString(sum3));
                } else {
                    ans3.setText(Integer.toString(a + b));
                    ans1.setText(Integer.toString(sum2));
                    ans2.setText(Integer.toString(sum3));
                }
                break;
            case 1:
                int sub2 = new Random().nextInt(a - b + 2) + 4;
                int sub3 = new Random().nextInt(a - b + 2);
                while (sub2 == a - b) {
                    sub2 = new Random().nextInt(a - b + 2) + 4;
                }
                while (sub3 == a - b) {
                    sub3 = new Random().nextInt(a - b + 2);
                }
                if(btnR == 0) {
                    ans1.setText(Integer.toString(a - b));
                    ans2.setText(Integer.toString(sub2));
                    ans3.setText(Integer.toString(sub3));
                } else if(btnR == 1) {
                    ans2.setText(Integer.toString(a - b));
                    ans1.setText(Integer.toString(new Random().nextInt(a - b + 3) + 7));
                    ans3.setText(Integer.toString(new Random().nextInt(a - b + 4) - 3));
                } else {
                    ans3.setText(Integer.toString(a - b));
                    ans1.setText(Integer.toString(new Random().nextInt(a - 3 + b) + 2));
                    ans2.setText(Integer.toString(new Random().nextInt(a - b + 1) - 2));
                }
                break;
            case 2:
                int mul2 = new Random().nextInt(a * b + 4) + 6;
                int mul3 = new Random().nextInt(a * b + 8);
                while (mul2 == a * b) {
                    mul2 = new Random().nextInt(a * b + 4) + 6;
                }
                while (mul3 == a * b) {
                    mul3 = new Random().nextInt(a * b + 8);
                }
                if(btnR == 0) {
                    ans1.setText(Integer.toString(a * b));
                    ans2.setText(Integer.toString(mul2));
                    ans3.setText(Integer.toString(mul3));
                } else if(btnR == 1) {
                    ans2.setText(Integer.toString(a * b));
                    ans1.setText(Integer.toString(mul2));
                    ans3.setText(Integer.toString(mul3));
                } else {
                    ans3.setText(Integer.toString(a * b));
                    ans1.setText(Integer.toString(mul2));
                    ans2.setText(Integer.toString(mul3));
                }
                break;
            case 3:
                int div2 = new Random().nextInt(a / b + 1) + 2;
                int div3 = new Random().nextInt(a / b + 3);
                while (div2 == a / b) {
                    div2 = new Random().nextInt(a / b + 1) + 2;
                }
                while (div3 == a / b) {
                    div3 = new Random().nextInt(a / b + 3);
                }
                if(btnR == 0) {
                    ans1.setText(Integer.toString(a / b));
                    ans2.setText(Integer.toString(div2));
                    ans3.setText(Integer.toString(div3));
                } else if(btnR == 1) {
                    ans2.setText(Integer.toString(a / b));
                    ans1.setText(Integer.toString(div2));
                    ans3.setText(Integer.toString(div3));
                } else {
                    ans3.setText(Integer.toString(a / b));
                    ans1.setText(Integer.toString(div2));
                    ans2.setText(Integer.toString(div3));
                }
                break;
        }
    }

    // in app notification (Notification Manager)
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showText(final String text) {
        Intent actionIntent = new Intent(this, MainActivity.class);

        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1234")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.title))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] {0, 1000})
                .setContentIntent(actionPendingIntent)
                .setAutoCancel(true);

        NotificationChannel channel = new NotificationChannel("1234",
                "1234" + "_name",
                NotificationManager.IMPORTANCE_HIGH);

        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.createNotificationChannel(channel);

        notifManager.notify(NOTIFICATION_ID, builder.build());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkRightAnswer(int choice) {
        if(numQ == 5) {
            showText(getString(R.string.score) + " " + score + "/5");
        }
        switch (choice) {
            case 0:
                ans1.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                score++;
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                ans2.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                ans3.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                break;
            case 1:
                ans2.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                score++;
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                ans1.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                ans3.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                break;
            case 2:
                ans3.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                score++;
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                ans1.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                ans2.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(++numQ <= 5)
                                    generateQuestion();
                            }
                        }
                );
                break;
        }
    }
}
