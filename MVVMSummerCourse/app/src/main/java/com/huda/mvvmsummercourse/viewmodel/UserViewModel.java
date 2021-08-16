package com.huda.mvvmsummercourse.viewmodel;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huda.mvvmsummercourse.model.User;

public class UserViewModel extends ViewModel {
    public MutableLiveData<String> toastMsg = new MutableLiveData<>();
    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private User user;
    private String successMessage = "Successful entry";
    private String errorMessage = "User data not valid";

    public UserViewModel() {
        user = new User("", "", 0);
    }

    public void setUser(String name, String email, String age) {
        user.setName(name);
        user.setEmail(email);
        if (!age.isEmpty() && age != null) {
            user.setAge(Integer.parseInt(age));
        } else {
            user.setAge(0);
        }
        if (isValidEntry()) {
            userMutableLiveData.setValue(user);
            toastMsg.setValue(successMessage);
        } else {
            toastMsg.setValue(errorMessage);
        }
    }

    private boolean isValidEntry() {
        return !TextUtils.isEmpty(user.getName()) &&
                !TextUtils.isEmpty(user.getEmail()) &&
                user.getAge() > 0 &&
                Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches();
    }


}
