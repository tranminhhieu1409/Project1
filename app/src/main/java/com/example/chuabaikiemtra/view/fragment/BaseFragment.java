package com.example.chuabaikiemtra.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.chuabaikiemtra.view.OnMainCallBack;

public abstract class BaseFragment<B extends ViewBinding>
        extends Fragment implements View.OnClickListener {
    protected Context context;
    protected B binding;
    protected OnMainCallBack callBack;
    protected Object mData;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater, container);
        initViews();
        return binding.getRoot();
    }

    protected abstract B initViewBinding( @NonNull LayoutInflater inflater,
                                          @NonNull ViewGroup container);

    protected abstract void initViews();

    public final void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);
    }

    protected abstract void clickView(View view);
    public void setData(Object data) {
        mData = data;
    }
}
