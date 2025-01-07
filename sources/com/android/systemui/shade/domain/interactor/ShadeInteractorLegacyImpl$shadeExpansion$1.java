package com.android.systemui.shade.domain.interactor;

import com.android.systemui.keyguard.shared.model.StatusBarState;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ShadeInteractorLegacyImpl$shadeExpansion$1 extends SuspendLambda implements Function6 {
    /* synthetic */ float F$0;
    /* synthetic */ float F$1;
    /* synthetic */ float F$2;
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    public ShadeInteractorLegacyImpl$shadeExpansion$1(Continuation continuation) {
        super(6, continuation);
    }

    @Override // kotlin.jvm.functions.Function6
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        float floatValue = ((Number) obj).floatValue();
        float floatValue2 = ((Number) obj3).floatValue();
        float floatValue3 = ((Number) obj4).floatValue();
        boolean booleanValue = ((Boolean) obj5).booleanValue();
        ShadeInteractorLegacyImpl$shadeExpansion$1 shadeInteractorLegacyImpl$shadeExpansion$1 = new ShadeInteractorLegacyImpl$shadeExpansion$1((Continuation) obj6);
        shadeInteractorLegacyImpl$shadeExpansion$1.F$0 = floatValue;
        shadeInteractorLegacyImpl$shadeExpansion$1.L$0 = (StatusBarState) obj2;
        shadeInteractorLegacyImpl$shadeExpansion$1.F$1 = floatValue2;
        shadeInteractorLegacyImpl$shadeExpansion$1.F$2 = floatValue3;
        shadeInteractorLegacyImpl$shadeExpansion$1.Z$0 = booleanValue;
        return shadeInteractorLegacyImpl$shadeExpansion$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        float f = this.F$0;
        StatusBarState statusBarState = (StatusBarState) this.L$0;
        float f2 = this.F$1;
        float f3 = this.F$2;
        boolean z = this.Z$0;
        int ordinal = statusBarState.ordinal();
        if (ordinal == 0) {
            f = (z || f3 <= 0.0f) ? f2 : 1.0f - f3;
        } else if (ordinal != 1) {
            if (ordinal != 2) {
                throw new NoWhenBranchMatchedException();
            }
            f = 1.0f;
        }
        return new Float(f);
    }
}
