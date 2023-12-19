package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

public class MainActivity extends AppCompatActivity {
    EditText edtinput,edtoutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalizeViews();
    }

    private void initalizeViews() {
    edtinput=findViewById(R.id.edtin1);
    edtoutput=findViewById(R.id.edtout1);
    }

    public void buttonclicked(View view){
       Button button = (Button) view;
       String textButton = button.getText().toString();
        String oldinput=edtinput.getText().toString();
       if (textButton.equalsIgnoreCase("C")){
          int len= edtinput.length();
         String reStr=oldinput.substring(0,len-1);
         edtinput.setText(reStr);

       } else if (textButton.equalsIgnoreCase("DEL")) {
           edtinput.setText("");
           edtoutput.setText("");

       } else if (textButton.equalsIgnoreCase("X")) {
           edtinput.setText(oldinput+"*");


       } else if (textButton.equalsIgnoreCase("=")) {
           if(oldinput.trim().equalsIgnoreCase("")){
               edtinput.setText(edtoutput.getText().toString());
               edtoutput.setText("");
           }
           //calculate expression and set answer

         try {
             Expression expression = new Expression(oldinput);
             EvaluationValue res = expression.evaluate();
             edtoutput.setText(res.getStringValue());
             edtinput.setText("");
             return;


         } catch (EvaluationException e) {
             Toast.makeText(this, "Can't evaluate expression", Toast.LENGTH_SHORT).show();
         } catch (ParseException e) {
             Toast.makeText(this, "Invalid Expression", Toast.LENGTH_SHORT).show();
         }

       }else{
           edtinput.setText(oldinput+textButton);
       }



    }
}