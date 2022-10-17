package com.example.chuabaikiemtra.view.fragment;

import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.chuabaikiemtra.App;
import com.example.chuabaikiemtra.R;
import com.example.chuabaikiemtra.databinding.FragmentM001MainBinding;
import com.example.chuabaikiemtra.model.Animal;
import com.example.chuabaikiemtra.view.dialog.MiniGameDialog;

import java.util.Locale;

public class M001MainFrg extends BaseFragment<FragmentM001MainBinding> {
    public static final String TAG = M001MainFrg.class.getName();
    private TextToSpeech tts;

    @Override
    protected FragmentM001MainBinding initViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentM001MainBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {
        initData();
        initAnimalView();
        binding.btGame.setOnClickListener(this);
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });
    }

    private void initAnimalView() {
        for (int i = 0; i <= App.getInstance().getStorage().listAnimal.size() - 3; i += 3) {
            Animal animal1 = App.getInstance().getStorage().listAnimal.get(i);
            Animal animal2 = App.getInstance().getStorage().listAnimal.get(i + 1);
            Animal animal3 = App.getInstance().getStorage().listAnimal.get(i + 2);
            View v1 = LayoutInflater.from(context).inflate(R.layout.item_animal, null);
            View v2 = LayoutInflater.from(context).inflate(R.layout.item_animal, null);
            View v3 = LayoutInflater.from(context).inflate(R.layout.item_animal, null);
            ImageView ivanimal1 = v1.findViewById(R.id.iv_animal);
            ImageView ivanimal2 = v2.findViewById(R.id.iv_animal);
            ImageView ivanimal3 = v3.findViewById(R.id.iv_animal);
            ivanimal1.setImageResource(animal1.getIdPhoto());
            ivanimal2.setImageResource(animal2.getIdPhoto());
            ivanimal3.setImageResource(animal3.getIdPhoto());
            ivanimal1.setTag(animal1);
            ivanimal2.setTag(animal2);
            ivanimal3.setTag(animal3);
            ivanimal1.setOnClickListener(this);
            ivanimal2.setOnClickListener(this);
            ivanimal3.setOnClickListener(this);
            TableRow tableRow = new TableRow(context);
            tableRow.setGravity(Gravity.CENTER);
            tableRow.addView(v1, new TableRow.LayoutParams(320, 450));
            tableRow.addView(v2, new TableRow.LayoutParams(320, 450));
            tableRow.addView(v3, new TableRow.LayoutParams(320, 450));
            binding.lnAnimal.addView(tableRow, new LinearLayoutCompat.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
    }


    private void initData() {
        App.getInstance().getStorage().listAnimal.clear();
        App.getInstance().getStorage().listAnimal.add(new Animal( R.drawable.ic_bee,R.raw.bee, "Bee"));
        App.getInstance().getStorage().listAnimal.add(new Animal( R.drawable.ic_lion,R.raw.lion, "Lion"));
        App.getInstance().getStorage().listAnimal.add(new Animal( R.drawable.ic_mouse,R.raw.mouse, "Mouse"));
        App.getInstance().getStorage().listAnimal.add(new Animal(R.drawable.ic_owl,R.raw.owl, "Owl"));
        App.getInstance().getStorage().listAnimal.add(new Animal( R.drawable.ic_panda, R.raw.panda,"Panda"));
        App.getInstance().getStorage().listAnimal.add(new Animal( R.drawable.ic_pig, R.raw.pig,"Pig"));
        App.getInstance().getStorage().listAnimal.add(new Animal( R.drawable.ic_rabbit, R.raw.rabbit,"Rabbit"));
        App.getInstance().getStorage().listAnimal.add(new Animal( R.drawable.ic_snake,R.raw.snake, "Snake"));
        App.getInstance().getStorage().listAnimal.add(new Animal( R.drawable.ic_tiger, R.raw.tiger,"Tiger"));

    }

    @Override
    protected void clickView(View view) {
        if(view.getId()==R.id.bt_game ){
            showMiNiGame();
            return;
        }
        Animal tag = (Animal) view.getTag();
        goToDetailScreen(tag);
    }

    private void showMiNiGame() {
        MiniGameDialog miniGame = new MiniGameDialog(context,App.getInstance().getStorage().listAnimal);
        miniGame.show();
    }

    private void goToDetailScreen(Animal animal) {
        tts.speak(animal.getName(), TextToSpeech.QUEUE_FLUSH, null);
        callBack.showFargment(M002DetailFrg.TAG, animal, true);
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
