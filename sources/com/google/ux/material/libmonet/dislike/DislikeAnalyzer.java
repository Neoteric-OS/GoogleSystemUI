package com.google.ux.material.libmonet.dislike;

import com.google.ux.material.libmonet.hct.Hct;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DislikeAnalyzer {
    public static Hct fixIfDisliked(Hct hct) {
        return (((((double) Math.round(hct.hue)) > 90.0d ? 1 : (((double) Math.round(hct.hue)) == 90.0d ? 0 : -1)) >= 0 && (((double) Math.round(hct.hue)) > 111.0d ? 1 : (((double) Math.round(hct.hue)) == 111.0d ? 0 : -1)) <= 0) && ((((double) Math.round(hct.chroma)) > 16.0d ? 1 : (((double) Math.round(hct.chroma)) == 16.0d ? 0 : -1)) > 0) && (((double) Math.round(hct.tone)) < 65.0d)) ? Hct.from(hct.hue, hct.chroma, 70.0d) : hct;
    }
}
