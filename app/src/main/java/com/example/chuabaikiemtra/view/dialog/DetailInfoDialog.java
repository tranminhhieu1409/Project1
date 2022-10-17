package com.example.chuabaikiemtra.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.example.chuabaikiemtra.R;
import com.example.chuabaikiemtra.databinding.ViewDetailInfoBinding;
import com.example.chuabaikiemtra.model.Animal;

public class DetailInfoDialog extends Dialog implements View.OnClickListener {
    private final ViewDetailInfoBinding binding;
    private final Animal animal;
    private final Context context;

    public DetailInfoDialog(@NonNull Context context, Animal animal) {
        super(context,R.style.Theme_Dialog);
        this.context = context;
        this.animal = animal;
        binding = ViewDetailInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();

    }

    private void initViews() {
        binding.ivBack.setOnClickListener(this);
        binding.tvTen.setText(animal.getName());
        binding.wedInfo.getSettings().setJavaScriptEnabled(true);
        binding.wedInfo.getSettings().setAllowContentAccess(true);
        binding.wedInfo.getSettings().setBuiltInZoomControls(true);
        binding.wedInfo.getSettings().setAllowFileAccess(true);
        binding.wedInfo.getSettings().setDomStorageEnabled(true);
        binding.wedInfo.getSettings().setSupportZoom(true);
        binding.wedInfo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        binding.wedInfo.loadUrl("https://vi.wikipedia.org/wiki/" + animal.getName());
    }

    @Override
    public void dismiss() {
        if(!binding.wedInfo.canGoBack()){
            super.dismiss();
            return;
        }
       binding.wedInfo.goBack();
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_fade_in));
        if(view.getId() == R.id.iv_back) {
            dismiss();
        }
    }
}
