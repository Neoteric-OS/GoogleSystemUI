package com.android.keyguard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthKeyguardMessageArea extends KeyguardMessageArea {
    public AuthKeyguardMessageArea(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.keyguard.KeyguardMessageArea
    public final void updateTextColor() {
        setTextColor(ColorStateList.valueOf(-1));
    }
}
