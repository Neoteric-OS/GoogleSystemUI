package com.android.settingslib.widget;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AdaptiveIcon extends LayerDrawable {
    public final AdaptiveConstantState mAdaptiveConstantState;
    int mBackgroundColor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class AdaptiveConstantState extends Drawable.ConstantState {
        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            Drawable[] drawableArr = new Drawable[2];
            throw null;
        }
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.mAdaptiveConstantState;
    }

    public final void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        getDrawable(0).setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("Setting background color "), this.mBackgroundColor, "AdaptiveHomepageIcon");
        this.mAdaptiveConstantState.getClass();
    }
}
