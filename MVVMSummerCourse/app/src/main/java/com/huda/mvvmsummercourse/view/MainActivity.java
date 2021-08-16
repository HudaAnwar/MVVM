package com.huda.mvvmsummercourse.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.huda.mvvmsummercourse.databinding.ActivityMainBinding;
import com.huda.mvvmsummercourse.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.okBtn.setOnClickListener(v -> {
            viewModel.setUser(binding.edtName.getText().toString(),
                    binding.edtEmail.getText().toString(),
                    binding.edtAge.getText().toString());
        });
        viewModel.userMutableLiveData.observe(this, user -> {
            binding.userName.setText(user.getName());
            binding.userEmail.setText(user.getEmail());
            binding.userAge.setText(String.valueOf(user.getAge()));
        });
        viewModel.toastMsg.observe(this, msg -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });
    }
}