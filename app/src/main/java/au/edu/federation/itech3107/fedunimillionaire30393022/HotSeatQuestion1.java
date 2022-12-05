package au.edu.federation.itech3107.fedunimillionaire30393022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class HotSeatQuestion1 extends AppCompatActivity {

    Button btn1;
    CheckBox ck1 = null;
    CheckBox ck2 = null;
    CheckBox ck3 = null;
    CheckBox ck4 = null;
    TextView tv1 = null;
    TextView tv2 = null;
    TextView tv3 = null;
    TextView tv4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_page);

        btn1 = findViewById(R.id.BtnNext);
        btn1.setEnabled(false);

        ck1 = findViewById(R.id.checkBoxA);
        ck2 = findViewById(R.id.checkBoxB);
        ck3 = findViewById(R.id.checkBoxC);
        ck4 = findViewById(R.id.checkBoxD);
        tv1 = findViewById(R.id.question);
        tv2 = findViewById(R.id.win_money);
        tv3 = findViewById(R.id.safe_money);
        tv4 = findViewById(R.id.timer);

//        read json file - easy question
        InputStream isE = HotSeatQuestion1.this.getClass().getClassLoader().getResourceAsStream("assets/" + "questions-easy.txt");
        InputStreamReader streamReaderE = new InputStreamReader(isE);
        BufferedReader readerE = new BufferedReader(streamReaderE);
        String lineE;
        StringBuilder stringBuilderE = new StringBuilder();
        try {
            while ((lineE = readerE.readLine()) != null) {
                // stringBuilder.append(lineE);
                stringBuilderE.append(lineE);
            }
            readerE.close();
            readerE.close();
            isE.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        read json file - hard question
        InputStream isH = HotSeatQuestion1.this.getClass().getClassLoader().getResourceAsStream("assets/" + "questions-hard.txt");
        InputStreamReader streamReaderH = new InputStreamReader(isH);
        BufferedReader readerH = new BufferedReader(streamReaderH);
        String lineH;
        StringBuilder stringBuilderH = new StringBuilder();
        try {
            while ((lineH = readerH.readLine()) != null) {
                // stringBuilder.append(lineH);
                stringBuilderH.append(lineH);
            }
            readerH.close();
            readerH.close();
            isH.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        read json file - medium question
        InputStream isM = HotSeatQuestion1.this.getClass().getClassLoader().getResourceAsStream("assets/" + "questions-medium.txt");
        InputStreamReader streamReaderM = new InputStreamReader(isM);
        BufferedReader readerM = new BufferedReader(streamReaderM);
        String lineM;
        StringBuilder stringBuilderM = new StringBuilder();
        try {
            while ((lineM = readerM.readLine()) != null) {
                // stringBuilder.append(lineM);
                stringBuilderM.append(lineM);
            }
            readerM.close();
            readerM.close();
            isM.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        read json file - win money and safe money
        InputStream is_money = HotSeatQuestion1.this.getClass().getClassLoader().getResourceAsStream("assets/" + "money.json");
        InputStreamReader streamReader_money = new InputStreamReader(is_money);
        BufferedReader reader_money = new BufferedReader(streamReader_money);
        String line_money;
        StringBuilder stringBuilder_money = new StringBuilder();
        try {
            while ((line_money = reader_money.readLine()) != null) {
                stringBuilder_money.append(line_money);
            }
            reader_money.close();
            reader_money.close();
            is_money.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
//            get the length of json_array,then use random to choose a number from 0 to json_array.length
            JSONObject QA = new JSONObject(stringBuilderE.toString());
            JSONArray json_array = QA.getJSONArray("questions");


            int num = json_array.length();
            Random random = new Random();//reference:https://blog.csdn.net/A2000824/article/details/124005070
            int Q1 = random.nextInt(num);//[0,json_array.length),json begin from 0,and length begin from 1,perfect!

            JSONObject json_object1 = json_array.getJSONObject(Q1);
            JSONArray json_arrayQ1 = json_object1.getJSONArray("incorrect_answers");

            //        get current time. reference:https://blog.csdn.net/qq_45241988/article/details/103766921
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());

            tv1.setText("1. " + "(" + json_object1.getString("difficulty") + ")" + json_object1.getString("question"));
            ck1.setText("A. " + json_object1.getString("correct_answer"));
            ck2.setText("B. " + json_arrayQ1.getString(0));
            ck3.setText("C. " + json_arrayQ1.getString(1));
            ck4.setText("D. " + json_arrayQ1.getString(2));

//        timer,15s,reference:https://blog.csdn.net/xch_yang/article/details/103523972
            new CountDownTimer(15000, 1000) {

                @Override
                public void onTick(long l) {
                    tv4.setText("Warning! Only " + l / 1000 + " seconds left!");
                }

                @Override
                public void onFinish() {
                    AlertDialog textTips = new AlertDialog.Builder(HotSeatQuestion1.this)
                            .setTitle("Sorry !!")
                            .setMessage("Time out! Press OK will go back to the first page.")
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                Intent intent = new Intent(HotSeatQuestion1.this, WelcomePage.class);
                                startActivity(intent);
                                finish();
                            })
                            .setCancelable(false)
                            .create();
                    textTips.show();
                    cancel();
                }
            }.start();

            ck1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    ck1.setEnabled(false);
                    ck2.setEnabled(false);
                    ck3.setEnabled(false);
                    ck4.setEnabled(false);
                    btn1.setEnabled(true);

                    try {
                        JSONObject Money = new JSONObject(stringBuilder_money.toString());
                        JSONArray json_array_money = Money.getJSONArray("money");
                        JSONObject json_object_money = json_array_money.getJSONObject(0);
                        tv2.setText("Correct! You will win: $ " + json_object_money.getString("win_money") + " ! ");
                        tv3.setText("Now you safe money is $ " + json_object_money.getString("safe_money") + " !");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            ck2.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    AlertDialog textTips = new AlertDialog.Builder(HotSeatQuestion1.this)
                            .setTitle("Sorry !!")
                            .setMessage("Wrong! You lose the game, no safe money!")
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                Intent intent = new Intent(HotSeatQuestion1.this, FinishPage.class);
                                intent.putExtra("Win_Money","0");
                                intent.putExtra("WinTime",simpleDateFormat.format(date));
                                startActivity(intent);
                                finish();
                            })
                            .setCancelable(false)
                            .create();
                    textTips.show();
                }
            });

            ck3.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    AlertDialog textTips = new AlertDialog.Builder(HotSeatQuestion1.this)
                            .setTitle("Sorry !!")
                            .setMessage("Wrong! You lose the game, no safe money!")
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                Intent intent = new Intent(HotSeatQuestion1.this, FinishPage.class);
                                intent.putExtra("Win_Money","0");
                                intent.putExtra("WinTime",simpleDateFormat.format(date));
                                startActivity(intent);
                                finish();
                            })
                            .setCancelable(false)
                            .create();
                    textTips.show();
                }
            });

            ck4.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    AlertDialog textTips = new AlertDialog.Builder(HotSeatQuestion1.this)
                            .setTitle("Sorry !!")
                            .setMessage("Wrong! You lose the game, no safe money!")
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                Intent intent = new Intent(HotSeatQuestion1.this, FinishPage.class);
                                intent.putExtra("Win_Money","0");
                                intent.putExtra("WinTime",simpleDateFormat.format(date));
                                startActivity(intent);
                                finish();
                            })
                            .setCancelable(false)
                            .create();
                    textTips.show();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        btn1.setOnClickListener(v -> {
            Intent intent = new Intent(HotSeatQuestion1.this, HotSeatQuestion2.class);
            startActivity(intent);
//            finish();
        });
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            //do something.
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }
}