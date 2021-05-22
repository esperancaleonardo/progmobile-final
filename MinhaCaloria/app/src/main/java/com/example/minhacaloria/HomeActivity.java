package com.example.minhacaloria;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.minhacaloria.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        selectFragment(new DiaryFragment(), "diary");

        binding.navSidebar.setNavigationItemSelectedListener((MenuItem item) -> {
            switch (item.getItemId()){
                case R.id.configuracoes:
                    selectFragment(new SettingsFragment(), "settings");
                    break;
                case R.id.perfil:
                    selectFragment(new ProfileFragment(), "profile");
                    break;
                case R.id.diary:
                    selectFragment(new DiaryFragment(), "diary");
                    break;
            }
            return true;
        });
    }


    private void selectFragment(Fragment f, String tag){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_content, f, tag);
        transaction.addToBackStack("sttack");
        transaction.commit();
    }
}