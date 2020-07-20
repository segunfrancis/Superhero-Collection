/*
 * Copyright (C) 2014 Bruce Lee <bruceinpeking#gmail.com>
 * Everyone is permitted to copy and distribute verbatim or modified copies of this license document,
 * and changing it is allowed as long as the name is changed.
 */

package com.project.segunfrancis.customprogress;

import android.content.res.Resources;

/**
 * Created by SegunFrancis
 */
public class Utils {
    private Utils() {
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
