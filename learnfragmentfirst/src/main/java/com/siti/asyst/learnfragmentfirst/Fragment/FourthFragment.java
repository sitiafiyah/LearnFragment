package com.siti.asyst.learnfragmentfirst.Fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.siti.asyst.learnfragmentfirst.R;
import com.siti.asyst.learnfragmentfirst.utility.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    public interface OnSubmitButtonListener{
        void onSubmitButton(String name, String address, String date);
    }

    EditText nameET, addressET;
    Button submit_btn;

    ImageView dateIV;
    TextView birthdayTV;

    OnSubmitButtonListener listener;
    DatePickerDialog datePickerDialog;

    public FourthFragment() {
        // Required empty public constructor
    }

    public static FourthFragment newInstance(String name, String address, String date){
        FourthFragment fourthFragment = new FourthFragment();

        Bundle bundle = new Bundle();
        bundle.putString("nama", name);
        bundle.putString("alamat",address);
        bundle.putString("date", date);

        fourthFragment.setArguments(bundle);
        return fourthFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_fourth, container, false);

        nameET = v.findViewById(R.id.edNama);
        addressET = v.findViewById(R.id.edAlamat);
        dateIV = v.findViewById(R.id.date_imageview);
        birthdayTV = v.findViewById(R.id.date_textview);

        submit_btn = v.findViewById(R.id.submit_btn);

        if(getArguments() != null){
            //buat Ngambil Datanya
            nameET.setText(getArguments().getString("nama", ""));
            addressET.setText(getArguments().getString("alamat", ""));
            birthdayTV.setText(getArguments().getString("date", ""));
        }

        Calendar calendar = Calendar.getInstance();
        String selectedDate = birthdayTV.getText().toString();

        int year = 2000;
        int month = 0;
        int day = 1;

        if (!selectedDate.equalsIgnoreCase("")){
            calendar.setTime(DateUtils.dateToString("dd MMMM yyyy", selectedDate));

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        submit_btn.setOnClickListener(this);
        dateIV.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_btn :
                listener.onSubmitButton(nameET.getText().toString(), addressET.getText().toString(), birthdayTV.getText().toString());
                getActivity().getSupportFragmentManager().popBackStack();
                break;

            case R.id.date_imageview :

                datePickerDialog.show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+ " "+(month+1)+" "+year;
        birthdayTV.setText(DateUtils.formatDate("dd MM yyyy", "dd MMMM yyyy", date));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnSubmitButtonListener){
            listener = (OnSubmitButtonListener)context;
        }else{
            throw new RuntimeException(context.toString() + "Activity harus implement OnSubmitButtonListener");
        }

    }

}
