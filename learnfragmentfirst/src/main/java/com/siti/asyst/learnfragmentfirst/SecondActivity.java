package com.siti.asyst.learnfragmentfirst;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.siti.asyst.learnfragmentfirst.Fragment.FourthFragment;
import com.siti.asyst.learnfragmentfirst.Fragment.InputBottomSheet;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, FourthFragment.OnSubmitButtonListener, InputBottomSheet.OnSubmitButtonListener{

    TextView namaTV, alamatTV, birthdayTV;
    Button btnInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        namaTV = findViewById(R.id.namaTV);
        alamatTV = findViewById(R.id.alamatTV);
        birthdayTV = findViewById(R.id.birthday_textview);
        btnInput = findViewById(R.id.btnInput);

        btnInput.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInput :

                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FourthFragment fourthFragment = FourthFragment.newInstance(namaTV.getText().toString(), alamatTV.getText().toString(), birthdayTV.getText().toString());

                transaction.replace(android.R.id.content, fourthFragment);
                transaction.addToBackStack(null);

                transaction.commit();
                break;
        }
    }

    @Override
    public void onSubmitButton(String name, String address, String date) {
        namaTV.setText(name);
        alamatTV.setText(address);
        birthdayTV.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.input_main_menu:

                InputBottomSheet inputBottomSheet = InputBottomSheet.newInstance(namaTV.getText().toString(), alamatTV.getText().toString(), birthdayTV.getText().toString());
                inputBottomSheet.show(getSupportFragmentManager(), "Input Bottom Sheet");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
