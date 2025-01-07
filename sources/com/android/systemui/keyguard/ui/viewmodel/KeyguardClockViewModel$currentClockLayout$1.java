package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.plugins.clocks.ClockConfig;
import com.android.systemui.plugins.clocks.ClockController;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardClockViewModel$currentClockLayout$1 extends SuspendLambda implements Function5 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;

    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        boolean booleanValue3 = ((Boolean) obj3).booleanValue();
        KeyguardClockViewModel$currentClockLayout$1 keyguardClockViewModel$currentClockLayout$1 = new KeyguardClockViewModel$currentClockLayout$1(5, (Continuation) obj5);
        keyguardClockViewModel$currentClockLayout$1.Z$0 = booleanValue;
        keyguardClockViewModel$currentClockLayout$1.Z$1 = booleanValue2;
        keyguardClockViewModel$currentClockLayout$1.Z$2 = booleanValue3;
        keyguardClockViewModel$currentClockLayout$1.L$0 = (ClockController) obj4;
        return keyguardClockViewModel$currentClockLayout$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ClockConfig config;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        ClockController clockController = (ClockController) this.L$0;
        return (clockController == null || (config = clockController.getConfig()) == null || !config.getUseCustomClockScene()) ? (z3 && z2) ? KeyguardClockViewModel.ClockLayout.LARGE_CLOCK : (z3 && z) ? KeyguardClockViewModel.ClockLayout.SPLIT_SHADE_LARGE_CLOCK : z3 ? KeyguardClockViewModel.ClockLayout.SPLIT_SHADE_SMALL_CLOCK : z ? KeyguardClockViewModel.ClockLayout.LARGE_CLOCK : KeyguardClockViewModel.ClockLayout.SMALL_CLOCK : (z3 && z2) ? KeyguardClockViewModel.ClockLayout.WEATHER_LARGE_CLOCK : (z3 && z) ? KeyguardClockViewModel.ClockLayout.SPLIT_SHADE_WEATHER_LARGE_CLOCK : z3 ? KeyguardClockViewModel.ClockLayout.SPLIT_SHADE_SMALL_CLOCK : z ? KeyguardClockViewModel.ClockLayout.WEATHER_LARGE_CLOCK : KeyguardClockViewModel.ClockLayout.SMALL_CLOCK;
    }
}
