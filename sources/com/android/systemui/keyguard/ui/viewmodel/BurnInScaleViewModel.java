package com.android.systemui.keyguard.ui.viewmodel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BurnInScaleViewModel {
    public final float scale;
    public final boolean scaleClockOnly;

    public BurnInScaleViewModel(float f, boolean z) {
        this.scale = f;
        this.scaleClockOnly = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BurnInScaleViewModel)) {
            return false;
        }
        BurnInScaleViewModel burnInScaleViewModel = (BurnInScaleViewModel) obj;
        return Float.compare(this.scale, burnInScaleViewModel.scale) == 0 && this.scaleClockOnly == burnInScaleViewModel.scaleClockOnly;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.scaleClockOnly) + (Float.hashCode(this.scale) * 31);
    }

    public final String toString() {
        return "BurnInScaleViewModel(scale=" + this.scale + ", scaleClockOnly=" + this.scaleClockOnly + ")";
    }
}
