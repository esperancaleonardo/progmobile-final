package com.example.minhacaloria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhacaloria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLoginCancel.setOnClickListener(v -> finish());

        binding.btnLoginLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                    coletar informações de login no banco e caso estiver certo
                    mostrar toast de login correto ou senha errada caso erro
                 */

                Toast.makeText(v.getContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });
    }
}