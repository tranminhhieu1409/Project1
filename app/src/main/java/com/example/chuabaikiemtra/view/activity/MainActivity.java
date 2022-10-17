package com.example.chuabaikiemtra.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

import com.example.chuabaikiemtra.R;
import com.example.chuabaikiemtra.databinding.ActivityMainBinding;
import com.example.chuabaikiemtra.view.fragment.M000SplashFrg;

public class MainActivity extends BaseAct<ActivityMainBinding> {


    @Override
    public void backToPrevious() {

    }

    @Override
    protected void initViews() {
        showFargment(M000SplashFrg.TAG, null,false);

    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onBackPressed() {
      int count = getSupportFragmentManager().getBackStackEntryCount();
      if(count==0){
          askForExitApp();
          return;
      }
        super.onBackPressed();
    }

    private void askForExitApp() {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Alert");
        alert.setMessage("Colse");
        alert.setButton(AlertDialog.BUTTON_POSITIVE,"Close",(dialogInterface, i) -> {
            super.onBackPressed();
        });
        alert.setButton(AlertDialog.BUTTON_NEGATIVE ,"Don't",(dialogInterface, i) -> {

        });
        alert.show();
    }
}