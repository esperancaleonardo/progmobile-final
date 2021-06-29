package com.example.minhacaloria.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import com.example.minhacaloria.R;
import com.example.minhacaloria.database.Database;
import com.example.minhacaloria.databinding.FragmentProfileBinding;
import com.example.minhacaloria.model.Profile;
import com.example.minhacaloria.model.User;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private User logged_user;
    private Database db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        db = new Database(getContext());
        logged_user = (User) getArguments().getSerializable("logged");


        ArrayList<String> dados = new ArrayList<>(db.get_user_profile(logged_user));

        binding.inputRegisterName.setText(dados.get(0));
        binding.inputRegisterHeight.setText(dados.get(1));
        binding.inputRegisterWeight.setText(dados.get(2));
        binding.inputRegisterAge.setText(dados.get(3));


        fill_spinner(R.array.objective);
        if(dados.get(4).equals("Ganhar")){
            binding.spinnerRegisterSelectObj.setSelection(1);
        }else if(dados.get(4).equals("Manter")){
            binding.spinnerRegisterSelectObj.setSelection(2);
        }else{
            binding.spinnerRegisterSelectObj.setSelection(3);
        }

        fill_spinner(R.array.gender);
        if(dados.get(5).equals("M")){
            binding.spinnerRegisterSelectSex.setSelection(1);
        }else{
            binding.spinnerRegisterSelectSex.setSelection(2);
        }

        binding.metaAtu.setText(getString(R.string.meta, dados.get(6)));
        if(dados.get(7) == null) {
            binding.metaAnt.setText(getString(R.string.metaAnt, "----"));
        }
        else binding.metaAnt.setText(getString(R.string.metaAnt, dados.get(7)));
        binding.atualiz.setText(getString(R.string.dataAtualizacao, dados.get(8)));


        binding.btnAtualizarPerfil.setOnClickListener(v -> {
            String name     = binding.inputRegisterName.getText().toString();
            String sex      = binding.spinnerRegisterSelectSex.getSelectedItem().toString();
            String weight   = binding.inputRegisterWeight.getText().toString();
            String height   = binding.inputRegisterHeight.getText().toString();
            String age     = binding.inputRegisterAge.getText().toString();
            String objective= binding.spinnerRegisterSelectObj.getSelectedItem().toString();
            int update_id = db.get_user_id(logged_user.getEmail());
            Profile updated = new Profile(update_id, name, sex, Double.valueOf(weight), Double.valueOf(height), Integer.valueOf(age), objective);

            db.update_profile(update_id, updated, Integer.parseInt(dados.get(6)));

            ArrayList<String> dadosatt = new ArrayList<>(db.get_user_profile(logged_user));

            binding.metaAnt.setText(getString(R.string.metaAnt, dadosatt.get(7)));
            binding.metaAtu.setText(getString(R.string.meta, dadosatt.get(6)));
            binding.atualiz.setText(getString(R.string.dataAtualizacao, dadosatt.get(8)));
        });

        return binding.getRoot();
    }

    private void fill_spinner(int resource_id){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), resource_id, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(resource_id == R.array.objective){
            binding.spinnerRegisterSelectObj.setAdapter(adapter);
        }
        else if(resource_id == R.array.gender){
            binding.spinnerRegisterSelectSex.setAdapter(adapter);
        }
    }
}