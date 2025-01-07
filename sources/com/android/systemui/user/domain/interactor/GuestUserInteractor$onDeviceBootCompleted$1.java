package com.android.systemui.user.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class GuestUserInteractor$onDeviceBootCompleted$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ GuestUserInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GuestUserInteractor$onDeviceBootCompleted$1(GuestUserInteractor guestUserInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = guestUserInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GuestUserInteractor$onDeviceBootCompleted$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((GuestUserInteractor$onDeviceBootCompleted$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L29
            if (r1 == r5) goto L25
            if (r1 == r4) goto L1d
            if (r1 != r3) goto L15
            kotlin.ResultKt.throwOnFailure(r7)
            goto L7b
        L15:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L1d:
            java.lang.Object r1 = r6.L$0
            com.android.systemui.user.domain.interactor.GuestUserInteractor r1 = (com.android.systemui.user.domain.interactor.GuestUserInteractor) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L65
        L25:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L3f
        L29:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.user.domain.interactor.GuestUserInteractor r7 = r6.this$0
            boolean r7 = r7.isDeviceAllowedToAddGuest()
            if (r7 == 0) goto L40
            com.android.systemui.user.domain.interactor.GuestUserInteractor r7 = r6.this$0
            r6.label = r5
            java.lang.Object r6 = r7.guaranteePresent(r6)
            if (r6 != r0) goto L3f
            return r0
        L3f:
            return r2
        L40:
            com.android.systemui.user.domain.interactor.GuestUserInteractor r7 = r6.this$0
            r6.L$0 = r7
            r6.label = r4
            kotlinx.coroutines.CancellableContinuationImpl r1 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r6)
            r1.<init>(r5, r4)
            r1.initCancellability()
            com.android.systemui.user.domain.interactor.GuestUserInteractor$onDeviceBootCompleted$1$1$callback$1 r4 = new com.android.systemui.user.domain.interactor.GuestUserInteractor$onDeviceBootCompleted$1$1$callback$1
            r4.<init>()
            com.android.systemui.statusbar.policy.DeviceProvisionedController r7 = r7.deviceProvisionedController
            com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl r7 = (com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl) r7
            r7.addCallback(r4)
            java.lang.Object r7 = r1.getResult()
            if (r7 != r0) goto L65
            return r0
        L65:
            com.android.systemui.user.domain.interactor.GuestUserInteractor r7 = r6.this$0
            boolean r7 = r7.isDeviceAllowedToAddGuest()
            if (r7 == 0) goto L7b
            com.android.systemui.user.domain.interactor.GuestUserInteractor r7 = r6.this$0
            r1 = 0
            r6.L$0 = r1
            r6.label = r3
            java.lang.Object r6 = r7.guaranteePresent(r6)
            if (r6 != r0) goto L7b
            return r0
        L7b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.GuestUserInteractor$onDeviceBootCompleted$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
