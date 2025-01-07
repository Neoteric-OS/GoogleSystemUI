package com.android.systemui.biometrics;

import android.view.MotionEvent;
import com.android.systemui.biometrics.UdfpsController;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6 implements Function3 {
    public final /* synthetic */ UdfpsController.UdfpsOverlayController f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6(UdfpsController.UdfpsOverlayController udfpsOverlayController, long j) {
        this.f$0 = udfpsOverlayController;
        this.f$1 = j;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        UdfpsController udfpsController = UdfpsController.this;
        boolean booleanValue = ((Boolean) obj3).booleanValue();
        return Boolean.valueOf(UdfpsController.m784$$Nest$monTouch(udfpsController, this.f$1, (MotionEvent) obj2, booleanValue));
    }
}
