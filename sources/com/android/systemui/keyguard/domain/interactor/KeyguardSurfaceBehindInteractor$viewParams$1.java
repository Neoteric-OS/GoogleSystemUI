package com.android.systemui.keyguard.domain.interactor;

import android.content.Context;
import com.android.systemui.keyguard.shared.model.KeyguardSurfaceBehindModel;
import com.android.systemui.shade.data.repository.FlingInfo;
import dagger.Lazy;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardSurfaceBehindInteractor$viewParams$1 extends SuspendLambda implements Function4 {
    final /* synthetic */ Context $context;
    final /* synthetic */ Lazy $inWindowLauncherUnlockAnimationInteractor;
    final /* synthetic */ SwipeToDismissInteractor $swipeToDismissInteractor;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardSurfaceBehindInteractor$viewParams$1(Lazy lazy, Context context, SwipeToDismissInteractor swipeToDismissInteractor, Continuation continuation) {
        super(4, continuation);
        this.$inWindowLauncherUnlockAnimationInteractor = lazy;
        this.$context = context;
        this.$swipeToDismissInteractor = swipeToDismissInteractor;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        boolean booleanValue3 = ((Boolean) obj3).booleanValue();
        KeyguardSurfaceBehindInteractor$viewParams$1 keyguardSurfaceBehindInteractor$viewParams$1 = new KeyguardSurfaceBehindInteractor$viewParams$1(this.$inWindowLauncherUnlockAnimationInteractor, this.$context, this.$swipeToDismissInteractor, (Continuation) obj4);
        keyguardSurfaceBehindInteractor$viewParams$1.Z$0 = booleanValue;
        keyguardSurfaceBehindInteractor$viewParams$1.Z$1 = booleanValue2;
        keyguardSurfaceBehindInteractor$viewParams$1.Z$2 = booleanValue3;
        return keyguardSurfaceBehindInteractor$viewParams$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        if (!z) {
            return new KeyguardSurfaceBehindModel(30, z2 ? 1.0f : 0.0f);
        }
        if (z3) {
            return new KeyguardSurfaceBehindModel(30, 0.0f);
        }
        if (((InWindowLauncherUnlockAnimationInteractor) this.$inWindowLauncherUnlockAnimationInteractor.get()).isLauncherUnderneath()) {
            return new KeyguardSurfaceBehindModel(30, 1.0f);
        }
        float f = (int) (250 * this.$context.getResources().getDisplayMetrics().density);
        FlingInfo flingInfo = (FlingInfo) ((StateFlowImpl) this.$swipeToDismissInteractor.dismissFling.$$delegate_0).getValue();
        return new KeyguardSurfaceBehindModel(1.0f, 0.0f, f, flingInfo != null ? flingInfo.velocity : 0.0f);
    }
}
