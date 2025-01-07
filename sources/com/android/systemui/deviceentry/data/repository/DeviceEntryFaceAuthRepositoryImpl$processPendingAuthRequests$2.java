package com.android.systemui.deviceentry.data.repository;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceEntryFaceAuthRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2(DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceEntryFaceAuthRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2 deviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2 = new DeviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2(this.this$0, continuation);
        deviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2.L$0 = obj;
        return deviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2) create((AuthenticationRequest) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d1, code lost:
    
        if (r10 != r0) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00e4 A[RETURN] */
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
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 1
            if (r1 == 0) goto L18
            if (r1 != r3) goto L10
            kotlin.ResultKt.throwOnFailure(r11)
            goto Le5
        L10:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L18:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            com.android.systemui.deviceentry.data.repository.AuthenticationRequest r11 = (com.android.systemui.deviceentry.data.repository.AuthenticationRequest) r11
            if (r11 == 0) goto Le5
            com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl r1 = r10.this$0
            com.android.systemui.log.FaceAuthenticationLogger r4 = r1.faceAuthLogger
            com.android.systemui.deviceentry.shared.FaceAuthUiEvent r5 = r11.uiEvent
            boolean r11 = r11.fallbackToDetection
            r4.processingRequest(r5, r11)
            java.lang.String r4 = "Authenticate was invoked"
            com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl.access$clearPendingAuthRequest(r1, r4)
            r10.label = r3
            kotlinx.coroutines.flow.StateFlowImpl r4 = r1._isAuthRunning
            java.lang.Object r6 = r4.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            com.android.systemui.log.FaceAuthenticationLogger r7 = r1.faceAuthLogger
            if (r6 == 0) goto L4b
            java.lang.String r10 = "face auth is currently running"
            r7.ignoredFaceAuthTrigger(r5, r10)
        L48:
            r10 = r2
            goto Le2
        L4b:
            kotlinx.coroutines.flow.StateFlowImpl r6 = r1.cancellationInProgress
            java.lang.Object r6 = r6.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L5f
            java.lang.String r10 = "cancellation in progress"
            r7.ignoredFaceAuthTrigger(r5, r10)
            goto L48
        L5f:
            kotlinx.coroutines.flow.ReadonlyStateFlow r6 = r1.canRunFaceAuth
            kotlinx.coroutines.flow.MutableStateFlow r6 = r6.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r6 = (kotlinx.coroutines.flow.StateFlowImpl) r6
            java.lang.Object r6 = r6.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            r8 = 0
            kotlinx.coroutines.CoroutineDispatcher r9 = r1.mainDispatcher
            if (r6 == 0) goto L7e
            com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$authenticate$2 r11 = new com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$authenticate$2
            r11.<init>(r1, r5, r8)
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r9, r11, r10)
            goto Le2
        L7e:
            kotlinx.coroutines.flow.ReadonlyStateFlow r6 = r1.canRunDetection
            kotlinx.coroutines.flow.MutableStateFlow r6 = r6.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r6 = (kotlinx.coroutines.flow.StateFlowImpl) r6
            java.lang.Object r6 = r6.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto Ldb
            if (r11 == 0) goto Ld4
            java.lang.String r11 = "face auth gating check is false, falling back to detection."
            r7.ignoredFaceAuthTrigger(r5, r11)
            boolean r11 = r1.isDetectionSupported
            if (r11 != 0) goto La8
            android.hardware.face.FaceManager r10 = r1.faceManager
            if (r10 == 0) goto La3
            java.util.List r8 = r10.getSensorPropertiesInternal()
        La3:
            r7.detectionNotSupported(r10, r8)
        La6:
            r10 = r2
            goto Ld1
        La8:
            java.lang.Object r11 = r4.getValue()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto Lc8
            java.lang.Object r10 = r4.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            android.os.CancellationSignal r11 = r1.detectCancellationSignal
            if (r11 == 0) goto Lc3
            goto Lc4
        Lc3:
            r3 = 0
        Lc4:
            r7.skippingDetection(r10, r3)
            goto La6
        Lc8:
            com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$detect$2 r11 = new com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$detect$2
            r11.<init>(r1, r5, r8)
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r9, r11, r10)
        Ld1:
            if (r10 != r0) goto L48
            goto Le2
        Ld4:
            java.lang.String r10 = "face auth gating check is false and fallback to detection is not requested"
            r7.ignoredFaceAuthTrigger(r5, r10)
            goto L48
        Ldb:
            java.lang.String r10 = "face auth & detect gating check is false"
            r7.ignoredFaceAuthTrigger(r5, r10)
            goto L48
        Le2:
            if (r10 != r0) goto Le5
            return r0
        Le5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
