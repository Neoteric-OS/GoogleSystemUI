package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.util.kotlin.WithPrev;
import com.android.systemui.util.ui.AnimatableEvent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardRootViewModel$areNotifsFullyHiddenAnimated$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ KeyguardRootViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardRootViewModel$areNotifsFullyHiddenAnimated$1(KeyguardRootViewModel keyguardRootViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = keyguardRootViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        KeyguardRootViewModel$areNotifsFullyHiddenAnimated$1 keyguardRootViewModel$areNotifsFullyHiddenAnimated$1 = new KeyguardRootViewModel$areNotifsFullyHiddenAnimated$1(this.this$0, (Continuation) obj3);
        keyguardRootViewModel$areNotifsFullyHiddenAnimated$1.L$0 = (WithPrev) obj;
        keyguardRootViewModel$areNotifsFullyHiddenAnimated$1.Z$0 = booleanValue;
        return keyguardRootViewModel$areNotifsFullyHiddenAnimated$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        WithPrev withPrev = (WithPrev) this.L$0;
        boolean z = this.Z$0;
        Boolean bool = (Boolean) withPrev.previousValue;
        boolean booleanValue = ((Boolean) withPrev.newValue).booleanValue();
        boolean z2 = false;
        if (bool != null && (z || (this.this$0.dozeParameters.getAlwaysOn() && !this.this$0.dozeParameters.getDisplayNeedsBlanking()))) {
            z2 = true;
        }
        return new AnimatableEvent(Boolean.valueOf(booleanValue), z2);
    }
}
