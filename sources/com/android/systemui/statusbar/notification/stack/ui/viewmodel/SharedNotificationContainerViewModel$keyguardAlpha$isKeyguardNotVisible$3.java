package com.android.systemui.statusbar.notification.stack.ui.viewmodel;

import com.android.systemui.keyguard.shared.model.StatusBarState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SharedNotificationContainerViewModel$keyguardAlpha$isKeyguardNotVisible$3 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        SharedNotificationContainerViewModel$keyguardAlpha$isKeyguardNotVisible$3 sharedNotificationContainerViewModel$keyguardAlpha$isKeyguardNotVisible$3 = new SharedNotificationContainerViewModel$keyguardAlpha$isKeyguardNotVisible$3(3, (Continuation) obj3);
        sharedNotificationContainerViewModel$keyguardAlpha$isKeyguardNotVisible$3.Z$0 = booleanValue;
        sharedNotificationContainerViewModel$keyguardAlpha$isKeyguardNotVisible$3.L$0 = (StatusBarState) obj2;
        return sharedNotificationContainerViewModel$keyguardAlpha$isKeyguardNotVisible$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Boolean.valueOf(this.Z$0 && ((StatusBarState) this.L$0) == StatusBarState.SHADE);
    }
}
