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
    private User logged_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        logged_user = (User) getIntent().getSerializableExtra("user_logged");

        selectFragment(new DiaryFragment(), "diary");

        binding.navSidebar.setNavigationItemSelectedListener((MenuItem item) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("logged", logged_user);

            switch (item.getItemId()){
                case R.id.configuracoes:
                    SettingsFragment settings = new SettingsFragment();
                    settings.setArguments(bundle);
                    selectFragment(settings, "settings");
                    break;
                case R.id.perfil:
                    ProfileFragment profile = new ProfileFragment();
                    profile.setArguments(bundle);
                    selectFragment(profile, "profile");
                    break;
                case R.id.diary:
                    DiaryFragment diary = new DiaryFragment();
                    diary.setArguments(bundle);
                    selectFragment(diary, "diary");
                    break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {}

    private void selectFragment(Fragment f, String tag){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_content, f, tag);
        transaction.addToBackStack("sttack");
        transaction.commit();
    }
}