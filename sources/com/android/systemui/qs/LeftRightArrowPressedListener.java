package com.android.systemui.qs;

import android.view.KeyEvent;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LeftRightArrowPressedListener implements View.OnKeyListener, View.OnFocusChangeListener {
    public Integer lastKeyCode;
    public PageIndicator$$ExternalSyntheticLambda0 listener;

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        if (z) {
            this.lastKeyCode = null;
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        Integer num;
        if (i != 21 && i != 22) {
            return false;
        }
        if (keyEvent.getAction() == 1 && (num = this.lastKeyCode) != null && i == num.intValue()) {
            PageIndicator$$ExternalSyntheticLambda0 pageIndicator$$ExternalSyntheticLambda0 = this.listener;
            if (pageIndicator$$ExternalSyntheticLambda0 != null) {
                pageIndicator$$ExternalSyntheticLambda0.accept(Integer.valueOf(i));
            }
            this.lastKeyCode = null;
        } else if (keyEvent.getRepeatCount() == 0) {
            this.lastKeyCode = Integer.valueOf(i);
        }
        return true;
    }
}
