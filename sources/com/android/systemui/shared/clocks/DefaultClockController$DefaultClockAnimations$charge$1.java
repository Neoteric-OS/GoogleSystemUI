package com.android.systemui.shared.clocks;

import com.android.systemui.shared.clocks.DefaultClockController;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DefaultClockController$DefaultClockAnimations$charge$1 extends Lambda implements Function0 {
    final /* synthetic */ DefaultClockController.DefaultClockAnimations this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultClockController$DefaultClockAnimations$charge$1(DefaultClockController.DefaultClockAnimations defaultClockAnimations) {
        super(0);
        this.this$0 = defaultClockAnimations;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Boolean.valueOf(this.this$0.dozeState.isActive);
    }
}
