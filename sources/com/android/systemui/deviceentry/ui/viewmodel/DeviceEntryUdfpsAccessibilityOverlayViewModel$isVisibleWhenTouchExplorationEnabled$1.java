package com.android.systemui.deviceentry.ui.viewmodel;

import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryForegroundViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryUdfpsAccessibilityOverlayViewModel$isVisibleWhenTouchExplorationEnabled$1 extends SuspendLambda implements Function3 {
    /* synthetic */ float F$0;
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        float floatValue = ((Number) obj2).floatValue();
        DeviceEntryUdfpsAccessibilityOverlayViewModel$isVisibleWhenTouchExplorationEnabled$1 deviceEntryUdfpsAccessibilityOverlayViewModel$isVisibleWhenTouchExplorationEnabled$1 = new DeviceEntryUdfpsAccessibilityOverlayViewModel$isVisibleWhenTouchExplorationEnabled$1(3, (Continuation) obj3);
        deviceEntryUdfpsAccessibilityOverlayViewModel$isVisibleWhenTouchExplorationEnabled$1.L$0 = (DeviceEntryForegroundViewModel.ForegroundIconViewModel) obj;
        deviceEntryUdfpsAccessibilityOverlayViewModel$isVisibleWhenTouchExplorationEnabled$1.F$0 = floatValue;
        return deviceEntryUdfpsAccessibilityOverlayViewModel$isVisibleWhenTouchExplorationEnabled$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DeviceEntryForegroundViewModel.ForegroundIconViewModel foregroundIconViewModel = (DeviceEntryForegroundViewModel.ForegroundIconViewModel) this.L$0;
        return Boolean.valueOf(foregroundIconViewModel.type == DeviceEntryIconView.IconType.FINGERPRINT && !foregroundIconViewModel.useAodVariant && this.F$0 == 1.0f);
    }
}
