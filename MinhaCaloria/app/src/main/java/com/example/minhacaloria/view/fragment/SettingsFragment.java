package com.example.minhacaloria.view.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.minhacaloria.database.Database;
import com.example.minhacaloria.databinding.FragmentSettingsBinding;
import com.example.minhacaloria.model.User;


public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private AlertDialog disconnectPopUp, alteraSenha, alteraEmail;
    private User logged_user;
    private Database db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        logged_user = (User) getArguments().getSerializable("logged");
        binding.inputSetttingsOldMail.setText(logged_user.getEmail());
        binding.inputSetttingsOldPass.setText(logged_user.getPassword());
        db = new Database(getContext());

        habilita_notificacoes(false);

        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(binding.checkBox1.isEnabled()){
                habilita_notificacoes(false);
                Toast.makeText(getContext(), "Central de notificações desabilitada!", Toast.LENGTH_SHORT).show();
            }
            else{
                habilita_notificacoes(true);
                Toast.makeText(getContext(), "Central de notificações habilitada!", Toast.LENGTH_SHORT).show();

            }
        });


        binding.btnDisconnect.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setTitle("DESCONECTAR");
            builder.setMessage("Deseja realmente sair do app?");
            builder.setPositiveButton("SIM", (dialog, which) -> {
                Intent it = new Intent(getActivity(), SettingsFragment.class);
                getActivity().finish();
                startActivity(it);
            });
            builder.setNegativeButton("CANCELAR", (dialog, which) -> disconnectPopUp.dismiss());
            disconnectPopUp = builder.create();
            disconnectPopUp.show();
        });

        binding.btnMailSettings.setOnClickListener(v -> {
            if(binding.inputSetttingsNewMail.getText().toString().equals("")){
                Toast.makeText(v.getContext(), "Digite um email novo para alterar!", Toast.LENGTH_SHORT).show();
            }
            else if(binding.inputSetttingsNewMail.getText().toString().equals(binding.inputSetttingsOldMail.getText().toString())){
                Toast.makeText(v.getContext(), "O email novo é igual o atual!", Toast.LENGTH_SHORT).show();
            }
            else{
                Integer user_id = db.get_user_id(logged_user.getEmail());
                logged_user.setEmail(binding.inputSetttingsNewMail.getText().toString());
                db.update_user(user_id, logged_user);
                binding.inputSetttingsOldMail.setText(binding.inputSetttingsNewMail.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Alteração de email");
                builder.setMessage("Email alterado com sucesso!");
                builder.setPositiveButton("OK", (dialog, which) -> alteraEmail.dismiss());
                alteraEmail = builder.create();
                alteraEmail.show();
            }
        });

        binding.btnPassSettings.setOnClickListener(v -> {
            if(binding.inputSetttingsNewPass.getText().toString().equals("")){
                Toast.makeText(v.getContext(), "Digite uma senha nova para alterar!", Toast.LENGTH_SHORT).show();
            }
            else if(binding.inputSetttingsNewPass.getText().toString().equals(binding.inputSetttingsOldPass.getText().toString())){
                Toast.makeText(v.getContext(), "O senha nova é igual a atual!", Toast.LENGTH_SHORT).show();
            }
            else{
                Integer user_id = db.get_user_id(logged_user.getEmail());
                logged_user.setPassword(binding.inputSetttingsNewPass.getText().toString());
                db.update_user(user_id, logged_user);
                binding.inputSetttingsOldPass.setText(binding.inputSetttingsNewPass.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Alteração de senha");
                builder.setMessage("Senha alterada com sucesso!");
                builder.setPositiveButton("OK", (dialog, which) -> alteraSenha.dismiss());
                alteraSenha = builder.create();
                alteraSenha.show();
            }
        });

        binding.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(binding.checkBox1.isChecked()){
                    Toast.makeText(getContext(), "Notificação 'Lembrete de Água' ativada!!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Notificação 'Lembrete de Água' desativada", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(binding.checkBox1.isChecked()){
                Toast.makeText(getContext(), "Notificação 'Lembrete de Água' ativada!!!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "Notificação 'Lembrete de Água' desativada", Toast.LENGTH_SHORT).show();
            }
        });
        binding.checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(binding.checkBox1.isChecked()){
                Toast.makeText(getContext(), "Notificação 'Lembrete de Água' ativada!!!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "Notificação 'Lembrete de Água' desativada", Toast.LENGTH_SHORT).show();
            }
        });
        binding.checkBox4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(binding.checkBox1.isChecked()){
                Toast.makeText(getContext(), "Notificação 'Lembrete de Água' ativada!!!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "Notificação 'Lembrete de Água' desativada", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    public void habilita_notificacoes(boolean flag){
        binding.checkBox1.setEnabled(flag);
        binding.checkBox2.setEnabled(flag);
        binding.checkBox3.setEnabled(flag);
        binding.checkBox4.setEnabled(flag);
        if(!flag){
            binding.checkBox1.setChecked(flag);
            binding.checkBox2.setChecked(flag);
            binding.checkBox3.setChecked(flag);
            binding.checkBox4.setChecked(flag);
        }

    }


}