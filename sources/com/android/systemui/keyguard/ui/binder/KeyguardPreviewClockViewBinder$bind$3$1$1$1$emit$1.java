package com.android.systemui.keyguard.ui.binder;

import com.android.systemui.keyguard.ui.binder.KeyguardPreviewClockViewBinder$bind$3;
import com.android.systemui.plugins.clocks.ClockController;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardPreviewClockViewBinder$bind$3$1$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ KeyguardPreviewClockViewBinder$bind$3.AnonymousClass1.C01201.C01211 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardPreviewClockViewBinder$bind$3$1$1$1$emit$1(KeyguardPreviewClockViewBinder$bind$3.AnonymousClass1.C01201.C01211 c01211, Continuation continuation) {
        super(continuation);
        this.this$0 = c01211;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((ClockController) null, (Continuation) this);
    }
}
