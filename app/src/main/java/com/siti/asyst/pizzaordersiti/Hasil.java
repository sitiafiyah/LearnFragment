package com.siti.asyst.pizzaordersiti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.siti.asyst.pizzaordersiti.utility.Constant;

public class Hasil extends AppCompatActivity {

    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        resultTV = findViewById(R.id.result_textview);

        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            resultTV.setText(bundle.getString(Constant.KEY_RESULT, ""));
        }
    }
}
