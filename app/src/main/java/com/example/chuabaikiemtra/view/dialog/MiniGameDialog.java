package com.example.chuabaikiemtra.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.chuabaikiemtra.R;
import com.example.chuabaikiemtra.databinding.ViewMiniGameBinding;
import com.example.chuabaikiemtra.model.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MiniGameDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = MiniGameDialog.class.getName();
    private final List<Animal> animalList;
    private final Context context;
    private final ViewMiniGameBinding binding;
    private int score = 0;
    private int index = 0;
    private Animal animal;

    public MiniGameDialog(@NonNull Context context, List<Animal> animalList) {
        super(context, R.style.Theme_Dialog);
        this.context = context;
        this.animalList = new ArrayList<>(animalList);
        Collections.shuffle(this.animalList);
        binding = ViewMiniGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();

    }

    private void initViews() {
        binding.ivBack.setOnClickListener(this);
        binding.ivCard.setOnClickListener(this);
        binding.tvCard.setOnClickListener(this);
        binding.tvA.setOnClickListener(this);
        binding.tvB.setOnClickListener(this);
        initCard();
    }

    private void initCard() {
        animal = animalList.get(index);
        List<Animal> tmpList = new ArrayList<>(animalList);
        tmpList.remove(animal);
        Collections.shuffle(tmpList);
        String txtA;
        String txtB;
        if(new Random().nextBoolean()) {
            txtA = "A:" + animal.getName();
            txtB = "B:" + tmpList.get(0).getName();

        } else {
            txtA = "B:" + animal.getName();
            txtB = "A:" + tmpList.get(0).getName();
        }
        binding.tvA.setText(txtA);
        binding.tvB.setText(txtB);

        String textA = binding.tvA.getText().toString();
        String textB = binding.tvB.getText().toString();

        int lenA = textA.length();
        int lenB = textB.length();
        String max = lenA > lenB ? textA : textB;
        Rect bounds = new Rect();
        Paint textPaint = binding.tvA.getPaint();
        textPaint.getTextBounds(max, 0, max.length(), bounds);
        int width = bounds.width();

        binding.tvA.setWidth(width + 100);
        binding.tvB.setWidth(width + 100);

    }


    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_fade_in));
        if(view.getId() == R.id.iv_back) {
            dismiss();
        } else if(view.getId() == R.id.iv_card || view.getId() == R.id.tv_card) {
            binding.frCard.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_fade_in));
            showCardAnimail();
        } else if(view.getId() == R.id.tv_a || view.getId() == R.id.tv_b) {
            checkAnswer(((TextView) view).getText().toString());
        }
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer(String ans) {
        if(ans.replace("A:", "")
                .replace("B:", "")
                .equals(animal.getName())) {
            score++;
            binding.tvScore.setText("Score:" + score);
            index++;
            if(index >= animalList.size()) {
                index = 0;
            }
            initCard();
        } else {
            Toast.makeText(context, "Wrong answer :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void showCardAnimail() {
        Toast toast = new Toast(context);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        imageView.setImageResource(animal.getIdPhoto());
        toast.setView(imageView);

        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 10);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

}
