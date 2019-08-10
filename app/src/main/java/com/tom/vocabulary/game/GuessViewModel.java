package com.tom.vocabulary.game;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuessViewModel extends ViewModel {
    MutableLiveData<String> secret;
    MutableLiveData<String> result;

    public GuessViewModel() {
        secret = new MutableLiveData<>();
        result = new MutableLiveData<>();
    }

    public MutableLiveData<String> getSecret() {
        return secret;
    }

    public void startGame(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(list.get(i));
        }
        secret.setValue(sb.toString());
    }

}
