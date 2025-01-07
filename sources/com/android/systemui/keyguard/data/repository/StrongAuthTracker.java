package com.android.systemui.keyguard.data.repository;

import android.content.Context;
import android.util.Log;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.keyguard.shared.model.AuthenticationFlags;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StrongAuthTracker extends LockPatternUtils.StrongAuthTracker {
    public final StateFlowImpl _authFlags;
    public final StateFlowImpl _nonStrongBiometricAllowed;
    public final ChannelFlowTransformLatest currentUserAuthFlags;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isNonStrongBiometricAllowed;
    public final StrongAuthTracker$special$$inlined$map$2 isStrongBiometricAllowed;
    public final UserRepositoryImpl userRepository;

    /* JADX WARN: Type inference failed for: r1v6, types: [com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2, kotlinx.coroutines.flow.Flow] */
    public StrongAuthTracker(UserRepositoryImpl userRepositoryImpl, Context context) {
        super(context);
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new StrongAuthTracker$special$$inlined$map$1(userRepositoryImpl.selectedUserInfo, 0));
        this._authFlags = StateFlowKt.MutableStateFlow(new AuthenticationFlags(userRepositoryImpl.getSelectedUserInfo().id, getStrongAuthForUser(userRepositoryImpl.getSelectedUserInfo().id)));
        this._nonStrongBiometricAllowed = StateFlowKt.MutableStateFlow(new Pair(Integer.valueOf(userRepositoryImpl.getSelectedUserInfo().id), Boolean.valueOf(isNonStrongBiometricAllowedAfterIdleTimeout(userRepositoryImpl.getSelectedUserInfo().id))));
        final ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(distinctUntilChanged, new StrongAuthTracker$special$$inlined$flatMapLatest$1(null, this));
        this.currentUserAuthFlags = transformLatest;
        ?? r1 = new Flow() { // from class: com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ StrongAuthTracker this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, StrongAuthTracker strongAuthTracker) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = strongAuthTracker;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2$2$1 r0 = (com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2$2$1 r0 = new com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.AuthenticationFlags r5 = (com.android.systemui.keyguard.shared.model.AuthenticationFlags) r5
                        int r5 = r5.userId
                        com.android.systemui.keyguard.data.repository.StrongAuthTracker r6 = r4.this$0
                        boolean r5 = r6.isBiometricAllowedForUser(r3, r5)
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.StrongAuthTracker$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ChannelFlowTransformLatest.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        this.isStrongBiometricAllowed = r1;
        this.isNonStrongBiometricAllowed = BiometricSettingsRepositoryKt.access$and(FlowKt.transformLatest(distinctUntilChanged, new StrongAuthTracker$special$$inlined$flatMapLatest$2(null, this)), r1);
    }

    public final void onIsNonStrongBiometricAllowedChanged(int i) {
        boolean isNonStrongBiometricAllowedAfterIdleTimeout = isNonStrongBiometricAllowedAfterIdleTimeout(i);
        StateFlowImpl stateFlowImpl = this._nonStrongBiometricAllowed;
        Pair pair = new Pair(Integer.valueOf(i), Boolean.valueOf(isNonStrongBiometricAllowedAfterIdleTimeout));
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, pair);
        Log.d("BiometricsRepositoryImpl", "onIsNonStrongBiometricAllowedChanged for userId: " + i + ", " + isNonStrongBiometricAllowedAfterIdleTimeout);
    }

    public final void onStrongAuthRequiredChanged(int i) {
        int strongAuthForUser = getStrongAuthForUser(i);
        StateFlowImpl stateFlowImpl = this._authFlags;
        AuthenticationFlags authenticationFlags = new AuthenticationFlags(i, strongAuthForUser);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, authenticationFlags);
        Log.d("BiometricsRepositoryImpl", "onStrongAuthRequiredChanged for userId: " + i + ", flag value: " + strongAuthForUser);
    }
}
