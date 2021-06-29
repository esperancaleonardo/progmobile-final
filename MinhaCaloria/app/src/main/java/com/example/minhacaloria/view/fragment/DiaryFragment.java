package com.example.minhacaloria.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.minhacaloria.R;
import com.example.minhacaloria.database.Database;
import com.example.minhacaloria.databinding.FragmentDiaryBinding;
import com.example.minhacaloria.model.User;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiaryFragment extends Fragment {
    private FragmentDiaryBinding binding;
    private Database db;
    private User logged_user;
    private Date date;
    private int date_offset=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDiaryBinding.inflate(inflater, container, false);
        db = new Database(getContext());
        logged_user = (User) getArguments().getSerializable("logged");

        updateDay(0);



        String meta = db.get_user_target(logged_user);
        String atual = "200"; //consultar valores do dia em banco

        binding.txtMetaDiaria.setText(getString(R.string.meta_diaria, atual, meta));
        binding.progressBar.setProgress((int) ((Double.valueOf(atual)/Double.valueOf(meta))*100));
        Log.e("TARGET", meta);

        binding.previousDay.setOnClickListener(v -> {
            //voltar um dia e consultar em banco e atulizar circulo
            date_offset -=1;
            updateDay(date_offset);
        });

        binding.nextDay.setOnClickListener(v -> {
            //avançar um dia e consultar em banco e atulizar circulo
            date_offset +=1;
            updateDay(date_offset);
        });


        return binding.getRoot();
    }


    public void updateDay(int offset){
        date = new Date();

        if(offset != 0){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_YEAR, offset);
            date = cal.getTime();
        }

        Locale BRAZIL = new Locale("pt","BR");
        DateFormat df  = DateFormat.getDateInstance(DateFormat.MEDIUM, BRAZIL);
        binding.dateString.setText(getString(R.string.dow_date_diary, df.format(date)).toUpperCase(BRAZIL));
    }
}