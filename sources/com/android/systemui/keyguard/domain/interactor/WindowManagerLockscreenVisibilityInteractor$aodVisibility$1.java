package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.shared.model.BiometricUnlockMode;
import com.android.systemui.keyguard.shared.model.BiometricUnlockModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class WindowManagerLockscreenVisibilityInteractor$aodVisibility$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        WindowManagerLockscreenVisibilityInteractor$aodVisibility$1 windowManagerLockscreenVisibilityInteractor$aodVisibility$1 = new WindowManagerLockscreenVisibilityInteractor$aodVisibility$1(4, (Continuation) obj4);
        windowManagerLockscreenVisibilityInteractor$aodVisibility$1.Z$0 = booleanValue;
        windowManagerLockscreenVisibilityInteractor$aodVisibility$1.Z$1 = booleanValue2;
        windowManagerLockscreenVisibilityInteractor$aodVisibility$1.L$0 = (BiometricUnlockModel) obj3;
        return windowManagerLockscreenVisibilityInteractor$aodVisibility$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z2 = this.Z$0;
        boolean z3 = this.Z$1;
        BiometricUnlockModel biometricUnlockModel = (BiometricUnlockModel) this.L$0;
        if (z2 && z3) {
            BiometricUnlockMode.Companion companion = BiometricUnlockMode.Companion;
            BiometricUnlockMode biometricUnlockMode = biometricUnlockModel.mode;
            companion.getClass();
            if (!BiometricUnlockMode.wakeAndUnlockModes.contains(biometricUnlockMode)) {
                z = true;
                return Boolean.valueOf(z);
            }
        }
        z = false;
        return Boolean.valueOf(z);
    }
}
