package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryIconViewModel$burnInOffsets$1$3 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KeyguardState.values().length];
            try {
                KeyguardState.Companion companion = KeyguardState.Companion;
                iArr[5] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                KeyguardState.Companion companion2 = KeyguardState.Companion;
                iArr[7] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        DeviceEntryIconViewModel$burnInOffsets$1$3 deviceEntryIconViewModel$burnInOffsets$1$3 = new DeviceEntryIconViewModel$burnInOffsets$1$3(4, (Continuation) obj4);
        deviceEntryIconViewModel$burnInOffsets$1$3.L$0 = (Pair) obj;
        deviceEntryIconViewModel$burnInOffsets$1$3.L$1 = (BurnInOffsets) obj2;
        deviceEntryIconViewModel$burnInOffsets$1$3.L$2 = (BurnInOffsets) obj3;
        return deviceEntryIconViewModel$burnInOffsets$1$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Pair pair = (Pair) this.L$0;
        BurnInOffsets burnInOffsets = (BurnInOffsets) this.L$1;
        BurnInOffsets burnInOffsets2 = (BurnInOffsets) this.L$2;
        TransitionStep transitionStep = (TransitionStep) pair.component1();
        boolean booleanValue = ((Boolean) pair.component2()).booleanValue();
        KeyguardState keyguardState = transitionStep.to;
        KeyguardState keyguardState2 = KeyguardState.AOD;
        KeyguardState keyguardState3 = transitionStep.from;
        if (keyguardState != keyguardState2) {
            return keyguardState3 == keyguardState2 ? WhenMappings.$EnumSwitchMapping$0[keyguardState.ordinal()] == 2 ? burnInOffsets : new BurnInOffsets(0, 0.0f, 0) : new BurnInOffsets(0, 0.0f, 0);
        }
        int ordinal = keyguardState3.ordinal();
        return ordinal != 5 ? (ordinal == 7 && !booleanValue) ? burnInOffsets : burnInOffsets2 : burnInOffsets;
    }
}
