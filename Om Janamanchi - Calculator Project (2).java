package com.example.myapplication;


import android.icu.number.Precision;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class OmJanamanchi extends AppCompatActivity implements View.OnClickListener
{
    Button button1;
    Button button2;
    Button button3;
    Button buttonAdd;
    Button button4;
    Button button5;
    Button button6;
    Button buttonSubtract;
    Button button7;
    Button button8;
    Button button9;
    Button buttonMultiply;
    Button buttonC;
    Button button0;
    Button buttonEquals;
    Button buttonDivide;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    String output;
    String finalOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1); button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2); button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3); button3.setOnClickListener(this);
        buttonAdd = findViewById(R.id.buttonAdd); buttonAdd.setOnClickListener(this);
        button4 = findViewById(R.id.button4); button4.setOnClickListener(this);
        button5 = findViewById(R.id.button5); button5.setOnClickListener(this);
        button6 = findViewById(R.id.button6); button6.setOnClickListener(this);
        buttonSubtract = findViewById(R.id.buttonSubtract); buttonSubtract.setOnClickListener(this);
        button7 = findViewById(R.id.button7); button7.setOnClickListener(this);
        button8 = findViewById(R.id.button8); button8.setOnClickListener(this);
        button9 = findViewById(R.id.button9); button9.setOnClickListener(this);
        buttonMultiply = findViewById(R.id.buttonMultiply); buttonMultiply.setOnClickListener(this);
        buttonC = findViewById(R.id.buttonC); buttonC.setOnClickListener(this);
        button0 = findViewById(R.id.button0); button0.setOnClickListener(this);
        buttonEquals = findViewById(R.id.buttonEquals); buttonEquals.setOnClickListener(this);
        buttonDivide = findViewById(R.id.buttonDivide); buttonDivide.setOnClickListener(this);
        textView1 = findViewById(R.id.textView1);
        output = "";
        finalOutput = "";
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId()) {
            case R.id.button1:
                output += button1.getText();
                textView1.setText(output);
                break;
            case R.id.button2:
                output += button2.getText();
                textView1.setText(output);
                break;
            case R.id.button3:
                output += button3.getText();
                textView1.setText(output);
                break;
            case R.id.buttonAdd:
                output += buttonAdd.getText();
                textView1.setText(output);
                break;
            case R.id.button4:
                output += button4.getText();
                textView1.setText(output);
                break;
            case R.id.button5:
                output += button5.getText();
                textView1.setText(output);
                break;
            case R.id.button6:
                output += button6.getText();
                textView1.setText(output);
                break;
            case R.id.buttonSubtract:
                output += buttonSubtract.getText();
                textView1.setText(output);
                break;
            case R.id.button7:
                output += button7.getText();
                textView1.setText(output);
                break;
            case R.id.button8:
                output += button8.getText();
                textView1.setText(output);
                break;
            case R.id.button9:
                output += button9.getText();
                textView1.setText(output);
                break;
            case R.id.buttonMultiply:
                output += buttonMultiply.getText();
                textView1.setText(output);
                break;
            case R.id.buttonC:
                output = "0";
                textView1.setText(output);
                output = "";
                break;
            case R.id.button0:
                output += button0.getText();
                textView1.setText(output);
                break;
            case R.id.buttonEquals:
                output = Double.toString(CalculatePemdas(view));
                finalOutput = cutDecimal(output);
                if (output.equals("Infinity"))
                    textView1.setText("Error");
                else
                    textView1.setText(finalOutput);
                break;
            case R.id.buttonDivide:
                output += buttonDivide.getText();
                textView1.setText(output);
                break;
        }
    }
    public double CalculatePemdas(View view)
    {

        StringTokenizer st = new StringTokenizer(output, "+-X÷", true);
        ArrayList<String> list = new ArrayList<String>();
        double result = 0;
        String strResult = "";
        int iIndex = 0 ;

        try
        {
            //fills equation into list and splits the tokens into elements
            while(st.hasMoreElements())
            {
                list.add(st.nextToken());
            }

            double numBefore = 0;
            double numAfter = 0;

            for(int i = 0; i < list.size(); i++)
            {
                iIndex = list.indexOf("X");

                if (iIndex < 0 )
                    iIndex = list.indexOf("÷");

                if(list.get(i).equals("X") || list.get(i).equals("÷"))
                {

                    numBefore = Double.parseDouble( list.get( i-1) );
                    numAfter = Double.parseDouble( list.get( i+1 ) );

                    if(list.get(i).equals("X"))
                    {
                        result =  (numBefore * numAfter);
                    }

                    if(list.get(i).equals("÷"))
                    {
                        result =  (numBefore / numAfter);
                    }

                    strResult = String.valueOf(result);
                    list.set(i-1,cutDecimal(strResult));
                    list.remove(i);
                    list.remove(i);
                    i=0;
                }
                else if((list.get(i).equals("+") || list.get(i).equals("-")) && iIndex < 0)
                {

                    numBefore = Double.parseDouble( list.get( i-1) );
                    numAfter = Double.parseDouble( list.get( i+1 ) );

                    if(list.get(i).equals("+"))
                    {
                        result = (numBefore + numAfter);

                    }

                    if(list.get(i).equals("-"))
                    {
                        result = (numBefore - numAfter);
                    }

                    strResult = String.valueOf(result);
                    list.set(i-1,cutDecimal(strResult));
                    list.remove(i);
                    list.remove(i);
                    i=0;
                }


            }

        }
        catch(ArithmeticException e)
        {
            textView1.setText("Error");
        }

        if (strResult.equals("Infinity"))
            return result;
        else
            return Math.round(result* 100000.0)/100000.0;
    }

    /* Simple Calculator Code:
    public double Calculate(View view)
    {

        StringTokenizer st = new StringTokenizer(output, "+-X÷", true);
        ArrayList<String> list = new ArrayList<String>();
        double result = 0;

        try
        {
            //fills equation into list and splits the tokens into elements
            while(st.hasMoreElements())
            {
                list.add(st.nextToken());
            }

            double numBefore = 0;
            double numAfter = 0;

            for(int i = 0; i < list.size(); i++)
            {

                if(list.get(i).equals("X") || list.get(i).equals("÷"))
                {

                    numBefore = Integer.parseInt( list.get( i-1) );
                    numAfter = Integer.parseInt( list.get( i+1 ) );

                    if(list.get(i).equals("*"))
                    {
                        if (result == 0)
                            result =  (numBefore * numAfter);
                        else
                            result =  (result * numAfter);
                    }

                    if(list.get(i).equals("÷"))
                    {
                        if (result == 0)
                            result =  (numBefore / numAfter);
                        else
                            result =  (result / numAfter);
                    }
                }
                else if(list.get(i).equals("+") || list.get(i).equals("-"))
                {

                    numBefore = Integer.parseInt( list.get( i-1) );
                    numAfter = Integer.parseInt( list.get( i+1 ) );

                    if(list.get(i).equals("+"))
                    {
                        if(result == 0)
                            result = (numBefore + numAfter);
                        else
                            result = result + numAfter;
                    }

                    if(list.get(i).equals("-"))
                    {
                        if(result == 0)
                            result =   (numBefore - numAfter);
                        else
                            result = result - numAfter;
                    }
                }


            }

        }
        catch(ArithmeticException e)
        {
            textView1.setText("Error");
        }

        return result;
    }*/


    public String cutDecimal(String num)
    {
        String n [] = num.split("\\.");
        if(n.length > 1)
        {
            if(n[1].equals("0"))
            {
                num = n[0];
            }
        }
        return num;
    }


}









