package com.android.systemui.keyguard.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.util.wrapper.LottieViewWrapper;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UdfpsLottieViewWrapper extends LottieViewWrapper {
    /* JADX WARN: Multi-variable type inference failed */
    public UdfpsLottieViewWrapper(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
    }

    public /* synthetic */ UdfpsLottieViewWrapper(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public UdfpsLottieViewWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
