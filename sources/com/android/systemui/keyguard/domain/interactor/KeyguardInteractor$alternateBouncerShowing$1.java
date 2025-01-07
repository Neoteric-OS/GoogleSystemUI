package com.android.systemui.keyguard.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.DelayKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardInteractor$alternateBouncerShowing$1 extends SuspendLambda implements Function3 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        KeyguardInteractor$alternateBouncerShowing$1 keyguardInteractor$alternateBouncerShowing$1 = new KeyguardInteractor$alternateBouncerShowing$1(3, (Continuation) obj3);
        keyguardInteractor$alternateBouncerShowing$1.Z$0 = booleanValue;
        keyguardInteractor$alternateBouncerShowing$1.Z$1 = booleanValue2;
        return keyguardInteractor$alternateBouncerShowing$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        boolean z2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            z = this.Z$0;
            if (this.Z$1) {
                this.Z$0 = z;
                this.label = 1;
                if (DelayKt.delay(600L, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                z2 = z;
            }
            return Boolean.valueOf(z);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        z2 = this.Z$0;
        ResultKt.throwOnFailure(obj);
        z = z2;
        return Boolean.valueOf(z);
    }
}
