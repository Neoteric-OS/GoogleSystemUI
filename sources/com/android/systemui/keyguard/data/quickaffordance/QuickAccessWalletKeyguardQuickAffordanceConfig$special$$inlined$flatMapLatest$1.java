package com.android.systemui.keyguard.data.quickaffordance;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class QuickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ QuickAccessWalletKeyguardQuickAffordanceConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QuickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1(QuickAccessWalletKeyguardQuickAffordanceConfig quickAccessWalletKeyguardQuickAffordanceConfig, Continuation continuation) {
        super(3, continuation);
        this.this$0 = quickAccessWalletKeyguardQuickAffordanceConfig;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        QuickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1 quickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1 = new QuickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        quickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        quickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return quickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00a4 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r10.label
            com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$LockScreenState$Hidden r2 = com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig.LockScreenState.Hidden.INSTANCE
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 0
            r5 = 1
            r6 = 2
            if (r1 == 0) goto L2e
            if (r1 == r5) goto L1e
            if (r1 != r6) goto L16
            kotlin.ResultKt.throwOnFailure(r11)
            goto La4
        L16:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L1e:
            java.lang.Object r1 = r10.L$2
            com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig r1 = (com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig) r1
            java.lang.Object r5 = r10.L$1
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            java.lang.Object r7 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5b
        L2e:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            r7 = r11
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            java.lang.Object r11 = r10.L$1
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            if (r11 != 0) goto L3d
            goto L8a
        L3d:
            com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig r1 = r10.this$0
            r10.L$0 = r7
            r10.L$1 = r11
            r10.L$2 = r1
            r10.label = r5
            r1.getClass()
            com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig$isWalletAvailable$2 r5 = new com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig$isWalletAvailable$2
            r5.<init>(r1, r4)
            kotlinx.coroutines.CoroutineDispatcher r8 = r1.backgroundDispatcher
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r8, r5, r10)
            if (r5 != r0) goto L58
            return r0
        L58:
            r9 = r5
            r5 = r11
            r11 = r9
        L5b:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            boolean r5 = r5.booleanValue()
            com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig r8 = r10.this$0
            com.android.systemui.wallet.controller.QuickAccessWalletController r8 = r8.walletController
            android.service.quickaccesswallet.QuickAccessWalletClient r8 = r8.mQuickAccessWalletClient
            android.graphics.drawable.Drawable r8 = r8.getTileIcon()
            r1.getClass()
            if (r11 == 0) goto L8a
            if (r5 == 0) goto L8a
            if (r8 == 0) goto L8a
            com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$LockScreenState$Visible r2 = new com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$LockScreenState$Visible
            com.android.systemui.common.shared.model.Icon$Loaded r11 = new com.android.systemui.common.shared.model.Icon$Loaded
            com.android.systemui.common.shared.model.ContentDescription$Resource r1 = new com.android.systemui.common.shared.model.ContentDescription$Resource
            r5 = 2131951875(0x7f130103, float:1.9540177E38)
            r1.<init>(r5)
            r11.<init>(r8, r1)
            r2.<init>(r11)
        L8a:
            r10.L$0 = r4
            r10.L$1 = r4
            r10.L$2 = r4
            r10.label = r6
            kotlinx.coroutines.flow.FlowKt.ensureActive(r7)
            java.lang.Object r10 = r7.emit(r2, r10)
            if (r10 != r0) goto L9c
            goto L9d
        L9c:
            r10 = r3
        L9d:
            if (r10 != r0) goto La0
            goto La1
        La0:
            r10 = r3
        La1:
            if (r10 != r0) goto La4
            return r0
        La4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
