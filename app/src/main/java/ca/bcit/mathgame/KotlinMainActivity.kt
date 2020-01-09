package ca.bcit.mathgame

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_kotlin_main.*
import java.util.*

class KotlinMainActivity : AppCompatActivity() {
    // score / 5
    private var score: Int = 0
    // tracking the num of question displayed
    private var numQ: Int = 0
    // for notification id
    val NOTIFICATION_ID = 5453

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        score = 0
        // generate question
        generateQuestion()
        numQ = 0
        // skip btn listener
        skipBtn.setOnClickListener(
                View.OnClickListener {
                    if (++numQ <= 5)
                        generateQuestion()
                }
        )
        // reset btn listener
        resetBtn.setOnClickListener(
                View.OnClickListener {
                    score = 0
                    // generate question
                    generateQuestion()
                    numQ = 0
                }
        )
    }

    // generate random operation
    private fun getOpR(): Int {
        return Random().nextInt(4)
    }

    // generate random operation
    private fun getIntR(): Int {
        return Random().nextInt(101)
    }

    // generate random operation
    private fun getBtnR(): Int {
        return Random().nextInt(3)
    }

    // generate math operation and answers
    private fun generateQuestion() {
        // two ran int
        var a = getIntR()
        var b = getIntR()
        // get the question
        when (getOpR()) {
            0 -> {
                questionTv.text = "$a + $b"
                generateQuestion(a, b, 0)
            }
            1 -> {
                while (a < b) { // must a > b
                    a = getIntR()
                    b = getIntR()
                }
                questionTv.text = "$a - $b"
                generateQuestion(a, b, 1)
            }
            2 -> {
                questionTv.text = "$a * $b"
                generateQuestion(a, b, 2)
            }
            3 -> {
                while (b == 0 || a % b != 0) {  // b cant be 0 and a % b == 0
                    a = getIntR()
                    b = getIntR()
                }
                questionTv.text = "$a / $b"
                generateQuestion(a, b, 3)
            }
        }
    }

    // generate answers
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun generateQuestion(a: Int, b: Int, op: Int) {
        val btnR = getBtnR().also {
            checkRightAnswer(it)
        }
        when (op) {
            0 -> {
                // two wrong answers
                var sum2 = Random().nextInt(a + b) + 4
                var sum3 = Random().nextInt(a + b) + 10
                while (sum2 == a + b) {
                    sum2 = Random().nextInt(a + b) + 4
                }
                while (sum3 == a + b) {
                    sum3 = Random().nextInt(a + b) + 10
                }
                when (btnR) {
                    0 -> {
                        answer1.text = (a + b).toString()
                        answer2.text = sum2.toString()
                        answer3.text = sum3.toString()
                    }
                    1 -> {
                        answer2.text = (a + b).toString()
                        answer1.text = sum2.toString()
                        answer3.text = sum3.toString()
                    }
                    else -> {
                        answer3.text = (a + b).toString()
                        answer1.text = sum2.toString()
                        answer2.text = sum3.toString()
                    }
                }
            }
            1 -> {
                var sub2 = Random().nextInt(a - b + 2) + 4
                var sub3 = Random().nextInt(a - b + 2)
                while (sub2 == a - b) {
                    sub2 = Random().nextInt(a - b + 2) + 4
                }
                while (sub3 == a - b) {
                    sub3 = Random().nextInt(a - b + 2)
                }
                when (btnR) {
                    0 -> {
                        answer1.text = (a - b).toString()
                        answer2.text = sub2.toString()
                        answer3.text = sub3.toString()
                    }
                    1 -> {
                        answer2.text = (a - b).toString()
                        answer1.text = (Random().nextInt(a - b + 3) + 7).toString()
                        answer3.text = (Random().nextInt(a - b + 4) - 3).toString()
                    }
                    else -> {
                        answer3.text = (a - b).toString()
                        answer1.text = (Random().nextInt(a - 3 + b) + 2).toString()
                        answer2.text = (Random().nextInt(a - b + 1) - 2).toString()
                    }
                }
            }
            2 -> {
                var mul2 = Random().nextInt(a * b + 4) + 6
                var mul3 = Random().nextInt(a * b + 8)
                while (mul2 == a * b) {
                    mul2 = Random().nextInt(a * b + 4) + 6
                }
                while (mul3 == a * b) {
                    mul3 = Random().nextInt(a * b + 8)
                }
                when (btnR) {
                    0 -> {
                        answer1.text = (a * b).toString()
                        answer2.text = mul2.toString()
                        answer3.text = mul3.toString()
                    }
                    1 -> {
                        answer2.text = (a * b).toString()
                        answer1.text = mul2.toString()
                        answer3.text = mul3.toString()
                    }
                    else -> {
                        answer3.text = (a * b).toString()
                        answer1.text = mul2.toString()
                        answer2.text = mul3.toString()
                    }
                }
            }
            3 -> {
                var div2 = Random().nextInt(a / b + 1) + 2
                var div3 = Random().nextInt(a / b + 3)
                while (div2 == a / b) {
                    div2 = Random().nextInt(a / b + 1) + 2
                }
                while (div3 == a / b) {
                    div3 = Random().nextInt(a / b + 3)
                }
                when (btnR) {
                    0 -> {
                        answer1.text = (a / b).toString()
                        answer2.text = div2.toString()
                        answer3.text = div3.toString()
                    }
                    1 -> {
                        answer2.text = (a / b).toString()
                        answer1.text = div2.toString()
                        answer3.text = div3.toString()
                    }
                    else -> {
                        answer3.text = (a / b).toString()
                        answer1.text = div2.toString()
                        answer2.text = div3.toString()
                    }
                }
            }
        }
    }

    // in app notification (Notification Manager)
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun showText(text: String) {
        val actionIntent = Intent(this, MainActivity::class.java)

        val actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, "1234")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.title))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(longArrayOf(0, 1000))
                .setContentIntent(actionPendingIntent)
                .setAutoCancel(true)

        val channel = NotificationChannel("1234",
                "1234" + "_name",
                NotificationManager.IMPORTANCE_HIGH)

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifManager.createNotificationChannel(channel)

        notifManager.notify(NOTIFICATION_ID, builder.build())
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun checkRightAnswer(choice: Int) {
        if (numQ == 5) {
            showText(getString(R.string.score) + " " + score + "/5")
        }
        when (choice) {
            0 -> {
                answer1.setOnClickListener(
                        View.OnClickListener {
                            score++
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
                answer2.setOnClickListener(
                        View.OnClickListener {
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
                answer3.setOnClickListener(
                        View.OnClickListener {
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
            }
            1 -> {
                answer2.setOnClickListener(
                        View.OnClickListener {
                            score++
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
                answer1.setOnClickListener(
                        View.OnClickListener {
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
                answer3.setOnClickListener(
                        View.OnClickListener {
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
            }
            2 -> {
                answer3.setOnClickListener(
                        View.OnClickListener {
                            score++
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
                answer1.setOnClickListener(
                        View.OnClickListener {
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
                answer2.setOnClickListener(
                        View.OnClickListener {
                            if (++numQ <= 5)
                                generateQuestion()
                        }
                )
            }
        }
    }

}
