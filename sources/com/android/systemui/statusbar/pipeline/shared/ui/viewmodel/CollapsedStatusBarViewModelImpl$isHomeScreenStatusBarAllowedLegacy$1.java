package com.android.systemui.statusbar.pipeline.shared.ui.viewmodel;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CollapsedStatusBarViewModelImpl$isHomeScreenStatusBarAllowedLegacy$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        CollapsedStatusBarViewModelImpl$isHomeScreenStatusBarAllowedLegacy$1 collapsedStatusBarViewModelImpl$isHomeScreenStatusBarAllowedLegacy$1 = new CollapsedStatusBarViewModelImpl$isHomeScreenStatusBarAllowedLegacy$1(3, (Continuation) obj3);
        collapsedStatusBarViewModelImpl$isHomeScreenStatusBarAllowedLegacy$1.L$0 = (KeyguardState) obj;
        collapsedStatusBarViewModelImpl$isHomeScreenStatusBarAllowedLegacy$1.Z$0 = booleanValue;
        return collapsedStatusBarViewModelImpl$isHomeScreenStatusBarAllowedLegacy$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        KeyguardState keyguardState = (KeyguardState) this.L$0;
        return Boolean.valueOf((keyguardState == KeyguardState.GONE || keyguardState == KeyguardState.OCCLUDED) && !this.Z$0);
    }
}
