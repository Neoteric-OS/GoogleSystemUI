package com.android.systemui.keyguard.ui.preview;

import com.android.systemui.plugins.clocks.ClockController;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class KeyguardPreviewRenderer$setupKeyguardRootView$1 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return KeyguardPreviewRenderer.access$updateClockAppearance((KeyguardPreviewRenderer) this.receiver, (ClockController) obj, (Continuation) obj2);
    }
}
