package com.example.minhacaloria;

import android.content.Intent;
import android.database.sqlite.SQLiteAbortException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhacaloria.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new Database(getBaseContext());
        db.getDatabaseName();

        fill_spinner(R.array.objective);
        fill_spinner(R.array.gender);

        binding.btnRegisterCancel.setOnClickListener(v -> finish());

        binding.btnRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    coletar informações de cadastro verificar dados existente no banco e caso nao exista
                    mostrar toast de cadastro correto ou cadastro errado caso erro
                 */
                String name     = binding.inputRegisterName.getText().toString();
                String email    = binding.inputRegisterMail.getText().toString();
                String password = binding.inputRegisterPass.getText().toString();
                String sex      = binding.spinnerRegisterSelectSex.getSelectedItem().toString();
                String weight   = binding.inputRegisterWeight.getText().toString();
                String height   = binding.inputRegisterHeight.getText().toString();
                String age     = binding.inputRegisterAge.getText().toString();
                String objective= binding.spinnerRegisterSelectObj.getSelectedItem().toString();

                if(check_empty_string(name, email, password, sex, weight, height, age, objective) || (sex.equals("-")  || objective.equals("Selecione"))){
                    Toast.makeText(v.getContext(), "Verifique campos não preenchidos!", Toast.LENGTH_SHORT).show();
                }else{
                    if(db.verify_mail_registered(email)){
                        Double weight_ = Double.valueOf(weight);
                        Double height_ = Double.valueOf(height);
                        Integer age_ = Integer.valueOf(age);

                        try {
                            db.insert_user(new User(email, password));
                            Integer user_id = db.get_user_id(email);
                            db.insert_profile(new Profile(user_id, name, sex, weight_, height_, age_, objective));

                            Toast.makeText(v.getContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        catch (SQLiteAbortException e){
                            Toast.makeText(v.getContext(), "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(v.getContext(), "Já existe usuário com esse e-mail!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void fill_spinner(int resource_id){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, resource_id, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(resource_id == R.array.objective){
            binding.spinnerRegisterSelectObj.setAdapter(adapter);
        }
        else if(resource_id == R.array.gender){
            binding.spinnerRegisterSelectSex.setAdapter(adapter);
        }
    }

    private static boolean check_empty_string(String... strings){
        for (String s : strings)
            if (s == null || s.isEmpty())
                return true;
        return false;
    }
}