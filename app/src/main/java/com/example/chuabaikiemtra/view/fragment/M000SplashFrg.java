package com.example.chuabaikiemtra.view.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import com.example.chuabaikiemtra.databinding.FragmentM000SplashBinding;

public class M000SplashFrg extends BaseFragment<FragmentM000SplashBinding> {
    public static final String TAG = M000SplashFrg.class.getName();

    @Override
    protected FragmentM000SplashBinding initViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentM000SplashBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {
        binding.ivBee.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_slide_out_bottom));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMainScreen();
            }
        }, 2500);
    }

    private void gotoMainScreen() {
        callBack.showFargment(M001MainFrg.TAG,null,false);
    }

    @Override
    protected void clickView(View view) {

    }
}
