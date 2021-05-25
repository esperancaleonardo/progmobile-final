package com.example.minhacaloria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhacaloria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new Database(getBaseContext());
        db.getReadableDatabase();

        binding.btnLoginCancel.setOnClickListener(v -> finish());

        binding.btnLoginLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(db.verify_user_data(binding.inputLoginEmail.getText().toString(), binding.inputLoginPass.getText().toString())){
                    Toast.makeText(v.getContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                else{
                    Toast.makeText(v.getContext(), "Login ou senha inválidos!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {}
}