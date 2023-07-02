package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView screen;
    private int op1 = 0;
    private int op2 = 0;
    private Ops operator = null;
    private boolean isOp1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);

        Button btnEqual = (Button) findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compute();
            }
        });
    }

    public void refreshOperations(View v){
        op1 = 0;
        op2 = 0;
        isOp1 = true;
        updateDisplay();
    }

    public void compute() {

        if (!isOp1) {
            if (operator.equals(Ops.PLUS)){
                op1 = op1 + op2;
            }
            else if (operator.equals(Ops.MOINS)){
                op1 = op1 - op2;
            }
            else if (operator.equals(Ops.FOIS)){
                op1 = op1 * op2;
            }
            else if (operator.equals(Ops.DIV)) {
                op1 = op1 / op2;
            }

            op2 = 0;
            isOp1 = true;
            updateDisplay();
        }
    }

    public void setOperator(View v) {

        if (v.getId() == R.id.btnPlus){
            operator=Ops.PLUS;
        } else if (v.getId() == R.id.btnMinus) {
            operator=Ops.MOINS;
        } else if (v.getId() == R.id.btnMultiplication) {
            operator=Ops.FOIS;
        } else if (v.getId() == R.id.btnDivision) {
            operator=Ops.DIV;
        }else {
            Toast.makeText(this, "Opérateur non reconnu",Toast.LENGTH_LONG);
            return;
        }
        isOp1=false;
        updateDisplay();
    }

    public void addNumber(View v){
        try {
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOp1) {
                op1 = op1 * 10 + val;
                updateDisplay();
            } else {
                op2 = op2 * 10 + val;
                updateDisplay();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }


    private void updateDisplay() {
        int v = op1;
        if (!isOp1) {
            v = op2;
        }
        screen.setText(String.format("%9d", v));
    }
    public enum Ops {
        PLUS,
        MOINS,
        FOIS,
        DIV;
    }


}