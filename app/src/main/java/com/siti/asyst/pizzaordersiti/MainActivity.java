package com.siti.asyst.pizzaordersiti;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.siti.asyst.pizzaordersiti.utility.Constant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener{

    EditText username;
    ImageButton close;
    Button btnSubmit;

    CheckBox cbPizzaMini, cbPizzaJumbo, cbPizzaExtra;

    RadioGroup rgPizzaMini, rgPizzaJumbo, rgPizzaExtra;
    RadioButton rbs1, rbm1, rbl1, rbs2, rbm2, rbl2,rbs3, rbm3, rbl3;
    Switch sizeUP1;
    Button order;

    String nama;
    String pizza = "";
    String selectedPizza1 = " ", selectedPizza2= " ",selectedPizza3= " ";
    String sizeUP = " ";

    ArrayList<String> listPizza = new ArrayList<String>();

    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.nama);
        close = findViewById(R.id.buttonClose);
        btnSubmit = findViewById(R.id.btnSubmit);

        cbPizzaMini = findViewById(R.id.cbPizzaMini);
        cbPizzaJumbo = findViewById(R.id.cbPizzaJumbo);
        cbPizzaExtra = findViewById(R.id.cbPizzaExtra);

        rgPizzaMini = findViewById(R.id.radiogroup1);
        rgPizzaJumbo = findViewById(R.id.radiogroup2);
        rgPizzaExtra = findViewById(R.id.radiogroup3);
        close = findViewById(R.id.buttonClose);

        ((RadioButton)findViewById(R.id.rbs1)).setChecked(true);
        ((RadioButton)findViewById(R.id.rbs2)).setChecked(true);
        ((RadioButton)findViewById(R.id.rbs3)).setChecked(true);

        ((RadioButton)findViewById(R.id.rbm1)).setChecked(true);
        ((RadioButton)findViewById(R.id.rbm2)).setChecked(true);
        ((RadioButton)findViewById(R.id.rbm3)).setChecked(true);

        ((RadioButton)findViewById(R.id.rbl1)).setChecked(true);
        ((RadioButton)findViewById(R.id.rbl2)).setChecked(true);
        ((RadioButton)findViewById(R.id.rbl3)).setChecked(true);


        rbs1 = findViewById(R.id.rbs1);
        rbs2 = findViewById(R.id.rbs2);
        rbs3 = findViewById(R.id.rbs3);

        rbm1 = findViewById(R.id.rbm1);
        rbm2 = findViewById(R.id.rbm2);
        rbm3 = findViewById(R.id.rbm3);

        rbl1 = findViewById(R.id.rbl1);
        rbl2 = findViewById(R.id.rbl2);
        rbl3 = findViewById(R.id.rbl3);
        sizeUP1 = findViewById(R.id.sizeUp);

        cbPizzaMini.setOnCheckedChangeListener(this);
        cbPizzaJumbo.setOnCheckedChangeListener(this);
        cbPizzaExtra.setOnCheckedChangeListener(this);

        rgPizzaMini.setOnCheckedChangeListener(this);
        rgPizzaJumbo.setOnCheckedChangeListener(this);
        rgPizzaExtra.setOnCheckedChangeListener(this);
        sizeUP1.setOnCheckedChangeListener(this);

        hasil = findViewById(R.id.textview);
        order = findViewById(R.id.btnOrder);

        close.setOnClickListener(this);
        order.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnSubmit:

                nama = username.getText().toString();
                if(!TextUtils.isEmpty(nama)) {
                    cbPizzaMini.setEnabled(true);
                    cbPizzaJumbo.setEnabled(true);
                    cbPizzaExtra.setEnabled(true);

                    rbs1.setEnabled(true);
                    rbm1.setEnabled(true);
                    rbl1.setEnabled(true);

                    rbs2.setEnabled(true);
                    rbm2.setEnabled(true);
                    rbl2.setEnabled(true);

                    rbs3.setEnabled(true);
                    rbm3.setEnabled(true);
                    rbl3.setEnabled(true);

                    sizeUP1.setEnabled(true);

                    order.setEnabled(true);
                }else {
                    Toast.makeText(getApplicationContext(), "Input Your Name", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.buttonClose :
                username.setText(" ");
                break;
            case R.id.btnOrder :
                nama = username.getText().toString();

               if(!TextUtils.isEmpty(nama)) {

                pizza = "";
                for(int i = 0; i<listPizza.size(); i++){
                    pizza = pizza+ " "+listPizza.get(i);
                }
//
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Confirmation")
                            .setCancelable(false)
                            .setMessage("Are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, Hasil.class);
                                    nama = username.getText().toString();
                                    String result = "Nama : " + nama + "\nPizza : " + pizza + "\nUkuran : " + selectedPizza1 + " " + selectedPizza2 + " " + selectedPizza3 + " "
                                            + "\nSize Up : " + sizeUP;
                                    intent.putExtra(Constant.KEY_RESULT, result);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();
                }else{
                   Toast.makeText(getApplicationContext(), "Input Your Name", Toast.LENGTH_SHORT).show();
               }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();

        switch (id) {
            case R.id.cbPizzaMini:
                if (isChecked) {
                    listPizza.add("Pizza Mini");
                } else {
                    listPizza.remove("Pizza Mini");
                }
                break;

            case R.id.cbPizzaJumbo:
                if (isChecked) {
                    listPizza.add("Pizza Jumbo");
                } else {
                    listPizza.remove("Pizza Jumbo");
                }
                break;

            case R.id.cbPizzaExtra:
                if (isChecked) {
                    listPizza.add("Pizza Extra Jumbo");
                } else {
                    listPizza.remove("Pizza Extra Jumbo");
                }
                break;
            case R.id.sizeUp :
                if (isChecked) {
                    sizeUP = "True";
                } else {
                    sizeUP = "False";
                }
                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbs1 :
                selectedPizza1 = "Small";
                break;
            case R.id.rbm1 :
                selectedPizza1 = "Medium";
                break;
            case R.id.rbl1 :
                selectedPizza1 = "Large";
                break;
            case R.id.rbs2 :
                selectedPizza2 = "Small";
                break;
            case R.id.rbm2 :
                selectedPizza2 = "Medium";
                break;
            case R.id.rbl2 :
                selectedPizza2 = "Large";
                break;
            case R.id.rbs3 :
                selectedPizza3 = "Small";
                break;
            case R.id.rbm3 :
                selectedPizza3 = "Medium";
                break;
            case R.id.rbl3 :
                selectedPizza3 = "Large";
                break;
        }
    }
}
