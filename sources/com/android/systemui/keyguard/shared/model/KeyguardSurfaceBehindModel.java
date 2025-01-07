package com.android.systemui.keyguard.shared.model;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSurfaceBehindModel {
    public final float alpha;
    public final float animateFromAlpha;
    public final float animateFromTranslationY;
    public final float startVelocity;

    public KeyguardSurfaceBehindModel(float f, float f2, float f3, float f4) {
        this.alpha = f;
        this.animateFromAlpha = f2;
        this.animateFromTranslationY = f3;
        this.startVelocity = f4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyguardSurfaceBehindModel)) {
            return false;
        }
        KeyguardSurfaceBehindModel keyguardSurfaceBehindModel = (KeyguardSurfaceBehindModel) obj;
        return Float.compare(this.alpha, keyguardSurfaceBehindModel.alpha) == 0 && Float.compare(this.animateFromAlpha, keyguardSurfaceBehindModel.animateFromAlpha) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(this.animateFromTranslationY, keyguardSurfaceBehindModel.animateFromTranslationY) == 0 && Float.compare(this.startVelocity, keyguardSurfaceBehindModel.startVelocity) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.startVelocity) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.alpha) * 31, this.animateFromAlpha, 31), 0.0f, 31), this.animateFromTranslationY, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("KeyguardSurfaceBehindModel(alpha=");
        sb.append(this.alpha);
        sb.append(", animateFromAlpha=");
        sb.append(this.animateFromAlpha);
        sb.append(", translationY=0.0, animateFromTranslationY=");
        sb.append(this.animateFromTranslationY);
        sb.append(", startVelocity=");
        return DpCornerSize$$ExternalSyntheticOutline0.m(sb, this.startVelocity, ")");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ KeyguardSurfaceBehindModel(int r1, float r2) {
        /*
            r0 = this;
            r1 = r1 & 1
            if (r1 == 0) goto L6
            r2 = 1065353216(0x3f800000, float:1.0)
        L6:
            r1 = 0
            r0.<init>(r2, r2, r1, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.shared.model.KeyguardSurfaceBehindModel.<init>(int, float):void");
    }
}
