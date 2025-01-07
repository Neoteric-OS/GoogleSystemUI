package com.android.systemui.qs.tileimpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChevronImageView extends ImageView {
    public ChevronImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final boolean resolveLayoutDirection() {
        int layoutDirection = getLayoutDirection();
        boolean resolveLayoutDirection = super.resolveLayoutDirection();
        if (resolveLayoutDirection && getLayoutDirection() != layoutDirection) {
            onRtlPropertiesChanged(getLayoutDirection());
        }
        return resolveLayoutDirection;
    }
}
