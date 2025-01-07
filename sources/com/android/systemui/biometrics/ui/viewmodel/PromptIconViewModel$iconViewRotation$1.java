package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.shared.model.DisplayRotation;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptIconViewModel$iconViewRotation$1 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PromptIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptIconViewModel$iconViewRotation$1(PromptIconViewModel promptIconViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = promptIconViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int intValue = ((Number) obj).intValue();
        PromptIconViewModel$iconViewRotation$1 promptIconViewModel$iconViewRotation$1 = new PromptIconViewModel$iconViewRotation$1(this.this$0, (Continuation) obj3);
        promptIconViewModel$iconViewRotation$1.I$0 = intValue;
        promptIconViewModel$iconViewRotation$1.L$0 = (DisplayRotation) obj2;
        return promptIconViewModel$iconViewRotation$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int ordinal;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        int i = this.I$0;
        DisplayRotation displayRotation = (DisplayRotation) this.L$0;
        boolean contains = this.this$0.assetsReusedAcrossRotations.contains(Integer.valueOf(i));
        float f = 0.0f;
        if (contains && (ordinal = displayRotation.ordinal()) != 0) {
            if (ordinal == 1) {
                f = 270.0f;
            } else if (ordinal == 2) {
                f = 180.0f;
            } else {
                if (ordinal != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                f = 90.0f;
            }
        }
        return new Float(f);
    }
}
