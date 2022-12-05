package au.edu.federation.itech3107.fedunimillionaire30393022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DeleteAddQuestion extends AppCompatActivity {

    TextView tv1;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_add_question);

        //        read json file - easy question
        InputStream isE = DeleteAddQuestion.this.getClass().getClassLoader().getResourceAsStream("assets/" + "questions-easy.txt");
        InputStreamReader streamReaderE = new InputStreamReader(isE);
        BufferedReader readerE = new BufferedReader(streamReaderE);
        String lineE;
        StringBuilder stringBuilderE = new StringBuilder();
        try {
            while ((lineE = readerE.readLine()) != null) {
                stringBuilderE.append(lineE);
            }
            readerE.close();
            readerE.close();
            isE.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //        read json file - medium question
        InputStream isM = DeleteAddQuestion.this.getClass().getClassLoader().getResourceAsStream("assets/" + "questions-medium.txt");
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

        //        read json file - hard question
        InputStream isH = DeleteAddQuestion.this.getClass().getClassLoader().getResourceAsStream("assets/" + "questions-hard.txt");
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


        tv1 = findViewById(R.id.List);
        try {
            JSONObject QA = new JSONObject(stringBuilderE.toString());
            JSONObject QA1 = new JSONObject(stringBuilderM.toString());
            JSONObject QA2 = new JSONObject(stringBuilderH.toString());

            JSONArray json_array = QA.getJSONArray("questions");
            JSONArray json_array1 = QA1.getJSONArray("questions");
            JSONArray json_array2 = QA2.getJSONArray("questions");

            int num = json_array.length();
            int num1 = json_array.length();
            int num2 = json_array.length();

            int i;
            int o;
            int u;

            for (i = 0; i < num; i++) {
                int a = i + 1;
                JSONObject json_object1 = json_array.getJSONObject(i);
                tv1.append(a + ".(" + json_object1.get("difficulty") + ")" + json_object1.getString("question") + "\n");
            }

            for (o = 0; o < num1; o++) {
                int b = o + 31;
                JSONObject json_object2 = json_array1.getJSONObject(o);
                tv1.append(b + ".(" + json_object2.get("difficulty") + ")" + json_object2.getString("question") + "\n");
            }

            for (u = 0; u < num2; u++) {
                int c = u + 61;
                JSONObject json_object3 = json_array2.getJSONObject(u);
                tv1.append(c + ".(" + json_object3.get("difficulty") + ")" + json_object3.getString("question") + "\n");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        btn1 = findViewById(R.id.AddEasy);
        btn2 = findViewById(R.id.AddMedium);
        btn3 = findViewById(R.id.AddHard);
        btn4 = findViewById(R.id.Delete);
        btn5 = findViewById(R.id.Back);

        btn1.setOnClickListener(view -> {
            Toast toast = Toast.makeText(DeleteAddQuestion.this, "Add to easy succeed", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        });

        btn2.setOnClickListener(view -> {
            Toast toast = Toast.makeText(DeleteAddQuestion.this, "Add to medium succeed", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        });

        btn3.setOnClickListener(view -> {
            Toast toast = Toast.makeText(DeleteAddQuestion.this, "Add to hard succeed", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        });

        btn4.setOnClickListener(view -> {
            Toast toast = Toast.makeText(DeleteAddQuestion.this, "Delete Succeed", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        });

        btn5.setOnClickListener(view -> {
            Intent intent = new Intent(DeleteAddQuestion.this, WelcomePage.class);
            startActivity(intent);
            finish();
        });
    }
}