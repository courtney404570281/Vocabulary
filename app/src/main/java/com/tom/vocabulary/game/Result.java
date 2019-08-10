package com.tom.vocabulary.game;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

public class Result {
    @ColorRes
    int color;
    @StringRes
    int result;
    boolean enable;

    public Result(@ColorRes int color, @StringRes int result) {
        this.color = color;
        this.result = result;
        enable = true;
    }

    public Result(@ColorRes int color, @StringRes int result, boolean enable) {
        this.color = color;
        this.result = result;
        this.enable = enable;
    }

    public int getColor() {
        return color;
    }

    public int getResult() {
        return result;
    }

    public boolean isEnable() {
        return enable;
    }
}
