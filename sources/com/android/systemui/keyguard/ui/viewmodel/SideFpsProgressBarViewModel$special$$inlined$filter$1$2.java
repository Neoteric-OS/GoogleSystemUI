package com.android.systemui.keyguard.ui.viewmodel;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SideFpsProgressBarViewModel$special$$inlined$filter$1$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel$special$$inlined$filter$1$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SideFpsProgressBarViewModel$special$$inlined$filter$1$2.this.emit(null, this);
        }
    }

    public SideFpsProgressBarViewModel$special$$inlined$filter$1$2(FlowCollector flowCollector) {
        this.$this_unsafeFlow = flowCollector;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004d, code lost:
    
        if (r7.authenticationReason.equals(com.android.systemui.biometrics.shared.model.AuthenticationReason.BiometricPromptAuthentication.INSTANCE) == false) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel$special$$inlined$filter$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel$special$$inlined$filter$1$2$1 r0 = (com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel$special$$inlined$filter$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel$special$$inlined$filter$1$2$1 r0 = new com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel$special$$inlined$filter$1$2$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5a
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
            com.android.systemui.keyguard.shared.model.FingerprintAuthenticationStatus r7 = (com.android.systemui.keyguard.shared.model.FingerprintAuthenticationStatus) r7
            boolean r2 = r7 instanceof com.android.systemui.keyguard.shared.model.AcquiredFingerprintAuthenticationStatus
            if (r2 == 0) goto L4f
            com.android.systemui.keyguard.shared.model.AcquiredFingerprintAuthenticationStatus r7 = (com.android.systemui.keyguard.shared.model.AcquiredFingerprintAuthenticationStatus) r7
            com.android.systemui.biometrics.shared.model.AuthenticationReason r2 = r7.authenticationReason
            com.android.systemui.biometrics.shared.model.AuthenticationReason$DeviceEntryAuthentication r4 = com.android.systemui.biometrics.shared.model.AuthenticationReason.DeviceEntryAuthentication.INSTANCE
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L4f
            com.android.systemui.biometrics.shared.model.AuthenticationReason r7 = r7.authenticationReason
            com.android.systemui.biometrics.shared.model.AuthenticationReason$BiometricPromptAuthentication r2 = com.android.systemui.biometrics.shared.model.AuthenticationReason.BiometricPromptAuthentication.INSTANCE
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L5a
        L4f:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
            java.lang.Object r5 = r5.emit(r6, r0)
            if (r5 != r1) goto L5a
            return r1
        L5a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel$special$$inlined$filter$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
