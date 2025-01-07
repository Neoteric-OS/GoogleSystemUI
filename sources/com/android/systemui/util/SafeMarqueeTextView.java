package com.android.systemui.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SafeMarqueeTextView extends TextView {
    public boolean safelyIgnoreLayout;

    public SafeMarqueeTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i, 0);
    }

    @Override // android.view.View
    public final void requestLayout() {
        if (this.safelyIgnoreLayout) {
            return;
        }
        super.requestLayout();
    }

    public void startMarquee() {
        boolean z = this.safelyIgnoreLayout;
        this.safelyIgnoreLayout = getLayoutParams().width != -2;
        super.startMarquee();
        this.safelyIgnoreLayout = z;
    }

    public void stopMarquee() {
        boolean z = this.safelyIgnoreLayout;
        this.safelyIgnoreLayout = getLayoutParams().width != -2;
        super.stopMarquee();
        this.safelyIgnoreLayout = z;
    }
}
