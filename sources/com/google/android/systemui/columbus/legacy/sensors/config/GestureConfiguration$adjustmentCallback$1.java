package com.google.android.systemui.columbus.legacy.sensors.config;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class GestureConfiguration$adjustmentCallback$1 extends Lambda implements Function1 {
    final /* synthetic */ GestureConfiguration this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GestureConfiguration$adjustmentCallback$1(GestureConfiguration gestureConfiguration) {
        super(1);
        this.this$0 = gestureConfiguration;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        this.this$0.updateSensitivity();
        return Unit.INSTANCE;
    }
}
