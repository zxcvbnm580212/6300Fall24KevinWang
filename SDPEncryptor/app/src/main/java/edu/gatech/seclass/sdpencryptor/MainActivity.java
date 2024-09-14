package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText plainText;
    private EditText multiplierArg;
    private EditText adderArg;
    private EditText cipherText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plainText = (EditText) findViewById(R.id.plainTextID);
        multiplierArg = (EditText) findViewById(R.id.multiplierArgID);
        adderArg = (EditText) findViewById(R.id.adderArgID);
        cipherText = (EditText) findViewById(R.id.cipherTextID);
    }

    public void handleClick(View view){
        String cipherTextValue;
        String plainTextValue = plainText.getText().toString();
        String multiplierArgValue = multiplierArg.getText().toString();
        String adderArgValue = adderArg.getText().toString();

        // Invalid Plain Text
        if (plainTextValue.length() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Plain Text";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        // Invalid Multiplier Arg
        if (multiplierArgValue.length() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Multiplier Arg";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        // Invalid Adder Arg
        if (adderArgValue.length() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Adder Arg";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        cipherTextValue = encrypt(Integer.parseInt(multiplierArgValue),Integer.parseInt(adderArgValue),plainTextValue);

        cipherText.setText(cipherTextValue);
    }

    private String encrypt(int arg1, int arg2, String currentString) {
        // Invalid Multiplier Arg
        if (arg1 <= 0 || arg1 >= 62 || gcd(arg1, 62) != 1) {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Multiplier Arg";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return "";
        }

        // Invalid Adder Arg
        if (arg2 < 1 || arg2 >= 62) {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Adder Arg";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return "";
        }

        StringBuilder encryptedString = new StringBuilder();

        for (int i = 0; i < currentString.length(); i++) {
            char c = currentString.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                int x = getCharValue(c);
                int encryptedValue = (arg1 * x + arg2) % 62;
                encryptedString.append(getCharFromValue(encryptedValue));
            } else {
                encryptedString.append(c);
            }
        }

        return encryptedString.toString();
    }

    private int getCharValue(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (c - 'A') * 2;  // "A" = 0, "B" = 2, ..., "Z" = 50
        } else if (c >= 'a' && c <= 'z') {
            return (c - 'a') * 2 + 1;  // "a" = 1, "b" = 3, ..., "z" = 51
        } else if (c >= '0' && c <= '9') {
            return c - '0' + 52;  // "0" = 52, "1" = 53, ..., "9" = 61
        }
        throw new IllegalArgumentException("Illegal string");
    }

    private char getCharFromValue(int value) {
        if (value >= 0 && value <= 50 && value % 2 == 0) {
            // 大寫字母 'A' 到 'Z'
            return (char) ('A' + value / 2);
        } else if (value >= 1 && value <= 51 && value % 2 == 1) {
            // 小寫字母 'a' 到 'z'
            return (char) ('a' + (value - 1) / 2);
        } else if (value >= 52 && value <= 61) {
            // 數字 '0' 到 '9'
            return (char) ('0' + (value - 52));
        }
        throw new IllegalArgumentException("Illegal number");
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}