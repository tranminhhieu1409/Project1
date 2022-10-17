package com.example.chuabaikiemtra.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.example.chuabaikiemtra.R;
import com.example.chuabaikiemtra.view.OnMainCallBack;
import com.example.chuabaikiemtra.view.fragment.BaseFragment;

import java.lang.reflect.Constructor;

public abstract class BaseAct<T extends ViewBinding>
        extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract void initViews();
    protected abstract T initViewBinding();

    @Override
    public void onClick(View view) {

    }
    protected final void notify(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    protected final void nootify(int msg){Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();}

    @Override
    public void showFargment(String tag, Object data, boolean isBack) {
        try {
            Class<?>clazz = Class.forName(tag);
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?> frg = (BaseFragment<?>)cons.newInstance();
            frg.setData(data);
            frg.setCallBack(this);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.ln_main,frg,tag);
            if(isBack){
                trans.addToBackStack(null);
            }
            trans.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
