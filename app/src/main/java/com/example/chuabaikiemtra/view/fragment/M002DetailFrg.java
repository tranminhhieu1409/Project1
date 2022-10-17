package com.example.chuabaikiemtra.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.chuabaikiemtra.App;
import com.example.chuabaikiemtra.R;
import com.example.chuabaikiemtra.databinding.FragmentM002MainBinding;
import com.example.chuabaikiemtra.model.Animal;
import com.example.chuabaikiemtra.view.dialog.DetailInfoDialog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class M002DetailFrg extends BaseFragment<FragmentM002MainBinding> {
    public static final String TAG = M002DetailFrg.class.getName();
    private int index = 0;

    

    @Override
    protected FragmentM002MainBinding initViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentM002MainBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {
        Animal animal = (Animal) mData;
        index = getListAnimal().indexOf(animal);
        updateUI(animal);
        binding.ivNext.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
        binding.ivPlay.setOnClickListener(this);
        binding.ivTimKiem.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void clickView(View view) {
       switch (view.getId()){
           case R.id.iv_back:
               index--;
               if(index<0)index = getListAnimal().size()-1;
               updateUI(getListAnimal().get(index));
               break;
           case R.id.iv_next:
               index++;
               if(index>getListAnimal().size()-1)index = 0;
               updateUI(getListAnimal().get(index));
               break;
           case R.id.iv_play:
               Animal animal = getListAnimal().get(index);
               MediaPlayer.create(context, animal.getIdSound()).start();
               break;
           case R.id.iv_timKiem:
              // searchImage(getListAnimal().get(index).getName());
               showInfoDialog(getListAnimal().get(index));
               break;
       }
    }

    private void showInfoDialog(Animal animal) {
        DetailInfoDialog dialog = new DetailInfoDialog(context, animal);
        dialog.show();
    }

    private void searchImage(String name) {
        try {
            String word  = URLEncoder.encode(name, "UTF-8");
            Uri uri = Uri.parse("https://www.google.com/search?hl=en&q="+word);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

    }

    private void updateUI(Animal animal) {
        binding.tvTen.setText(animal.getName());
        binding.ivAnimal.setImageResource(animal.getIdPhoto());
    }

    private ArrayList<Animal> getListAnimal() {

        return App.getInstance().getStorage().listAnimal;
    }
}
