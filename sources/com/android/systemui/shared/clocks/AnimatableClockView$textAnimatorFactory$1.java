package com.android.systemui.shared.clocks;

import android.text.Layout;
import com.android.systemui.animation.TextAnimator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AnimatableClockView$textAnimatorFactory$1 extends Lambda implements Function2 {
    public static final AnimatableClockView$textAnimatorFactory$1 INSTANCE = new AnimatableClockView$textAnimatorFactory$1();

    public AnimatableClockView$textAnimatorFactory$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new TextAnimator((Layout) obj, (Function0) obj2);
    }
}
