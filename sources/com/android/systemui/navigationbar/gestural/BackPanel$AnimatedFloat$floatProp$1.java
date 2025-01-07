package com.android.systemui.navigationbar.gestural;

import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.android.systemui.navigationbar.gestural.BackPanel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackPanel$AnimatedFloat$floatProp$1 extends FloatPropertyCompat {
    @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
    public final float getValue(Object obj) {
        return ((BackPanel.AnimatedFloat) obj).pos;
    }

    @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
    public final void setValue(Object obj, float f) {
        BackPanel.AnimatedFloat animatedFloat = (BackPanel.AnimatedFloat) obj;
        if (animatedFloat.pos == f) {
            return;
        }
        animatedFloat.pos = f;
        BackPanel.this.invalidate();
    }
}
