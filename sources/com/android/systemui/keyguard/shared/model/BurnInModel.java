package com.android.systemui.keyguard.shared.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BurnInModel {
    public final float scale;
    public final boolean scaleClockOnly;
    public final int translationX;
    public final int translationY;

    public /* synthetic */ BurnInModel(int i, int i2, float f, int i3) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? 1.0f : f, false);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BurnInModel)) {
            return false;
        }
        BurnInModel burnInModel = (BurnInModel) obj;
        return this.translationX == burnInModel.translationX && this.translationY == burnInModel.translationY && Float.compare(this.scale, burnInModel.scale) == 0 && this.scaleClockOnly == burnInModel.scaleClockOnly;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.scaleClockOnly) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.translationY, Integer.hashCode(this.translationX) * 31, 31), this.scale, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BurnInModel(translationX=");
        sb.append(this.translationX);
        sb.append(", translationY=");
        sb.append(this.translationY);
        sb.append(", scale=");
        sb.append(this.scale);
        sb.append(", scaleClockOnly=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.scaleClockOnly, ")");
    }

    public BurnInModel(int i, int i2, float f, boolean z) {
        this.translationX = i;
        this.translationY = i2;
        this.scale = f;
        this.scaleClockOnly = z;
    }
}
