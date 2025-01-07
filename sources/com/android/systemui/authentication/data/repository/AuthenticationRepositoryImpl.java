package com.android.systemui.authentication.data.repository;

import android.app.admin.DevicePolicyManager;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.authentication.AuthenticationModule$getSecurityMode$1;
import com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.util.time.SystemClock;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthenticationRepositoryImpl {
    public final StateFlowImpl _failedAuthenticationAttempts;
    public final StateFlowImpl _hasLockoutOccurred;
    public final CoroutineScope applicationScope;
    public final Flow authenticationMethod;
    public final CoroutineDispatcher backgroundDispatcher;
    public final SystemClock clock;
    public final DevicePolicyManager devicePolicyManager;
    public final ReadonlyStateFlow failedAuthenticationAttempts;
    public final AuthenticationModule$getSecurityMode$1 getSecurityMode;
    public final ReadonlyStateFlow hasLockoutOccurred;
    public final ReadonlyStateFlow isAutoConfirmFeatureEnabled;
    public final ReadonlyStateFlow isPatternVisible;
    public final ReadonlyStateFlow isPinEnhancedPrivacyEnabled;
    public final LockPatternUtils lockPatternUtils;
    public final UserRepositoryImpl userRepository;

    public AuthenticationRepositoryImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SystemClock systemClock, AuthenticationModule$getSecurityMode$1 authenticationModule$getSecurityMode$1, UserRepositoryImpl userRepositoryImpl, LockPatternUtils lockPatternUtils, DevicePolicyManager devicePolicyManager, BroadcastDispatcher broadcastDispatcher, MobileConnectionsRepository mobileConnectionsRepository) {
        this.applicationScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
        this.clock = systemClock;
        this.getSecurityMode = authenticationModule$getSecurityMode$1;
        this.userRepository = userRepositoryImpl;
        this.lockPatternUtils = lockPatternUtils;
        this.devicePolicyManager = devicePolicyManager;
        Boolean bool = Boolean.TRUE;
        this.isPatternVisible = refreshingFlow(bool, new AuthenticationRepositoryImpl$isPatternVisible$1(2, lockPatternUtils, LockPatternUtils.class, "isVisiblePatternEnabled", "isVisiblePatternEnabled(I)Z", 4));
        Boolean bool2 = Boolean.FALSE;
        this.isAutoConfirmFeatureEnabled = refreshingFlow(bool2, new AuthenticationRepositoryImpl$isAutoConfirmFeatureEnabled$1(2, lockPatternUtils, LockPatternUtils.class, "isAutoPinConfirmEnabled", "isAutoPinConfirmEnabled(I)Z", 4));
        final ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(userRepositoryImpl.selectedUserInfo, mobileConnectionsRepository.isAnySimSecure(), new AuthenticationRepositoryImpl$authenticationMethod$1(3, null)), new AuthenticationRepositoryImpl$special$$inlined$flatMapLatest$1(null, broadcastDispatcher));
        this.authenticationMethod = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ AuthenticationRepositoryImpl $receiver$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, AuthenticationRepositoryImpl authenticationRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$receiver$inlined = authenticationRepositoryImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:19:0x0060 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3a
                        if (r2 == r4) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L61
                    L2a:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L32:
                        java.lang.Object r6 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L55
                    L3a:
                        kotlin.ResultKt.throwOnFailure(r8)
                        java.lang.Number r7 = (java.lang.Number) r7
                        int r7 = r7.intValue()
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow
                        r0.L$0 = r8
                        r0.label = r4
                        com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl r6 = r6.$receiver$inlined
                        java.lang.Object r6 = r6.getAuthenticationMethod(r7, r0)
                        if (r6 != r1) goto L52
                        return r1
                    L52:
                        r5 = r8
                        r8 = r6
                        r6 = r5
                    L55:
                        r7 = 0
                        r0.L$0 = r7
                        r0.label = r3
                        java.lang.Object r6 = r6.emit(r8, r0)
                        if (r6 != r1) goto L61
                        return r1
                    L61:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ChannelFlowTransformLatest.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
        this.isPinEnhancedPrivacyEnabled = refreshingFlow(bool, new AuthenticationRepositoryImpl$isPinEnhancedPrivacyEnabled$1(this, null));
        this.failedAuthenticationAttempts = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(0));
        this.hasLockoutOccurred = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(bool2));
    }

    public final Object getAuthenticationMethod(int i, ContinuationImpl continuationImpl) {
        return BuildersKt.withContext(this.backgroundDispatcher, new AuthenticationRepositoryImpl$getAuthenticationMethod$3(this, i, null), continuationImpl);
    }

    public final Object getMaxFailedUnlockAttemptsForWipe(AuthenticationInteractor$special$$inlined$map$2$2.AnonymousClass1 anonymousClass1) {
        return BuildersKt.withContext(this.backgroundDispatcher, new AuthenticationRepositoryImpl$getMaxFailedUnlockAttemptsForWipe$2(this, null), anonymousClass1);
    }

    public final Object getPinLength(ContinuationImpl continuationImpl) {
        return BuildersKt.withContext(this.backgroundDispatcher, new AuthenticationRepositoryImpl$getPinLength$2(this, null), continuationImpl);
    }

    public final Object getProfileWithMinFailedUnlockAttemptsForWipe(Continuation continuation) {
        return BuildersKt.withContext(this.backgroundDispatcher, new AuthenticationRepositoryImpl$getProfileWithMinFailedUnlockAttemptsForWipe$2(this, null), continuation);
    }

    public final int getSelectedUserId() {
        return this.userRepository.getSelectedUserInfo().id;
    }

    public final ReadonlyStateFlow refreshingFlow(Object obj, Function2 function2) {
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(obj);
        BuildersKt.launch$default(this.applicationScope, null, null, new AuthenticationRepositoryImpl$refreshingFlow$1(this, MutableStateFlow, function2, null), 3);
        return new ReadonlyStateFlow(MutableStateFlow);
    }
}
