package com.android.systemui.authentication.domain.interactor;

import com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthenticationInteractor {
    public final SharedFlowImpl _onAuthenticationResult;
    public final CoroutineScope applicationScope;
    public final Flow authenticationMethod;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ReadonlyStateFlow hintedPinLength;
    public final ReadonlyStateFlow isAutoConfirmEnabled;
    public final ReadonlyStateFlow isPatternVisible;
    public final ReadonlyStateFlow isPinEnhancedPrivacyEnabled;
    public final ReadonlySharedFlow onAuthenticationResult;
    public final AuthenticationRepositoryImpl repository;
    public final SelectedUserInteractor selectedUserInteractor;
    public final AuthenticationInteractor$special$$inlined$map$1 upcomingWipe;

    public AuthenticationInteractor(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, AuthenticationRepositoryImpl authenticationRepositoryImpl, SelectedUserInteractor selectedUserInteractor) {
        this.repository = authenticationRepositoryImpl;
        this.selectedUserInteractor = selectedUserInteractor;
        this.authenticationMethod = authenticationRepositoryImpl.authenticationMethod;
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(authenticationRepositoryImpl.isAutoConfirmFeatureEnabled, authenticationRepositoryImpl.hasLockoutOccurred, new AuthenticationInteractor$isAutoConfirmEnabled$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        this.isAutoConfirmEnabled = stateIn;
        final int i = 0;
        this.hintedPinLength = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ AuthenticationInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    boolean Z$0;
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

                public AnonymousClass2(FlowCollector flowCollector, AuthenticationInteractor authenticationInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = authenticationInteractor;
                }

                /* JADX WARN: Code restructure failed: missing block: B:20:0x0074, code lost:
                
                    if (r4 == 6) goto L26;
                 */
                /* JADX WARN: Removed duplicated region for block: B:19:0x006c  */
                /* JADX WARN: Removed duplicated region for block: B:23:0x0084 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:25:0x0040  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L40
                        if (r2 == r4) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L85
                    L2a:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L32:
                        boolean r7 = r0.Z$0
                        java.lang.Object r8 = r0.L$1
                        kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
                        java.lang.Object r2 = r0.L$0
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2 r2 = (com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1.AnonymousClass2) r2
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L62
                    L40:
                        kotlin.ResultKt.throwOnFailure(r9)
                        java.lang.Boolean r8 = (java.lang.Boolean) r8
                        boolean r8 = r8.booleanValue()
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r9 = r7.this$0
                        com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl r9 = r9.repository
                        r0.L$0 = r7
                        kotlinx.coroutines.flow.FlowCollector r2 = r7.$this_unsafeFlow
                        r0.L$1 = r2
                        r0.Z$0 = r8
                        r0.label = r4
                        java.lang.Object r9 = r9.getPinLength(r0)
                        if (r9 != r1) goto L5e
                        return r1
                    L5e:
                        r6 = r2
                        r2 = r7
                        r7 = r8
                        r8 = r6
                    L62:
                        r4 = r9
                        java.lang.Number r4 = (java.lang.Number) r4
                        int r4 = r4.intValue()
                        r5 = 0
                        if (r7 == 0) goto L77
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r7 = r2.this$0
                        com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl r7 = r7.repository
                        r7.getClass()
                        r7 = 6
                        if (r4 != r7) goto L77
                        goto L78
                    L77:
                        r9 = r5
                    L78:
                        r0.L$0 = r5
                        r0.L$1 = r5
                        r0.label = r3
                        java.lang.Object r7 = r8.emit(r9, r0)
                        if (r7 != r1) goto L85
                        return r1
                    L85:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        ((ReadonlyStateFlow) stateIn).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = stateIn.collect(new AuthenticationInteractor$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.isPatternVisible = authenticationRepositoryImpl.isPatternVisible;
        this.onAuthenticationResult = new ReadonlySharedFlow(SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7));
        this.isPinEnhancedPrivacyEnabled = authenticationRepositoryImpl.isPinEnhancedPrivacyEnabled;
        final ReadonlyStateFlow readonlyStateFlow = authenticationRepositoryImpl.failedAuthenticationAttempts;
        final int i2 = 1;
        new Flow() { // from class: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ AuthenticationInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    boolean Z$0;
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

                public AnonymousClass2(FlowCollector flowCollector, AuthenticationInteractor authenticationInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = authenticationInteractor;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r9 instanceof com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L40
                        if (r2 == r4) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L85
                    L2a:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L32:
                        boolean r7 = r0.Z$0
                        java.lang.Object r8 = r0.L$1
                        kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
                        java.lang.Object r2 = r0.L$0
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1$2 r2 = (com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1.AnonymousClass2) r2
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L62
                    L40:
                        kotlin.ResultKt.throwOnFailure(r9)
                        java.lang.Boolean r8 = (java.lang.Boolean) r8
                        boolean r8 = r8.booleanValue()
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r9 = r7.this$0
                        com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl r9 = r9.repository
                        r0.L$0 = r7
                        kotlinx.coroutines.flow.FlowCollector r2 = r7.$this_unsafeFlow
                        r0.L$1 = r2
                        r0.Z$0 = r8
                        r0.label = r4
                        java.lang.Object r9 = r9.getPinLength(r0)
                        if (r9 != r1) goto L5e
                        return r1
                    L5e:
                        r6 = r2
                        r2 = r7
                        r7 = r8
                        r8 = r6
                    L62:
                        r4 = r9
                        java.lang.Number r4 = (java.lang.Number) r4
                        int r4 = r4.intValue()
                        r5 = 0
                        if (r7 == 0) goto L77
                        com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r7 = r2.this$0
                        com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl r7 = r7.repository
                        r7.getClass()
                        r7 = 6
                        if (r4 != r7) goto L77
                        goto L78
                    L77:
                        r9 = r5
                    L78:
                        r0.L$0 = r5
                        r0.L$1 = r5
                        r0.label = r3
                        java.lang.Object r7 = r8.emit(r9, r0)
                        if (r7 != r1) goto L85
                        return r1
                    L85:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = readonlyStateFlow.collect(new AuthenticationInteractor$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$getWipeTarget(com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r4, kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4.getClass()
            boolean r0 = r5 instanceof com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$getWipeTarget$1
            if (r0 == 0) goto L16
            r0 = r5
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$getWipeTarget$1 r0 = (com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$getWipeTarget$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$getWipeTarget$1 r0 = new com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$getWipeTarget$1
            r0.<init>(r4, r5)
        L1b:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r4 = (com.android.systemui.authentication.domain.interactor.AuthenticationInteractor) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4
            r0.label = r3
            com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl r5 = r4.repository
            java.lang.Object r5 = r5.getProfileWithMinFailedUnlockAttemptsForWipe(r0)
            if (r5 != r1) goto L46
            goto L6b
        L46:
            java.lang.Number r5 = (java.lang.Number) r5
            int r5 = r5.intValue()
            com.android.systemui.user.domain.interactor.SelectedUserInteractor r0 = r4.selectedUserInteractor
            com.android.systemui.user.data.repository.UserRepositoryImpl r0 = r0.repository
            int r0 = r0.mainUserId
            com.android.systemui.user.domain.interactor.SelectedUserInteractor r4 = r4.selectedUserInteractor
            int r4 = r4.getSelectedUserId()
            com.android.systemui.authentication.shared.model.AuthenticationWipeModel$WipeTarget$WholeDevice r1 = com.android.systemui.authentication.shared.model.AuthenticationWipeModel.WipeTarget.WholeDevice.INSTANCE
            if (r5 != r4) goto L63
            if (r5 != r0) goto L5f
            goto L6b
        L5f:
            com.android.systemui.authentication.shared.model.AuthenticationWipeModel$WipeTarget$User r4 = com.android.systemui.authentication.shared.model.AuthenticationWipeModel.WipeTarget.User.INSTANCE
        L61:
            r1 = r4
            goto L6b
        L63:
            r4 = -10000(0xffffffffffffd8f0, float:NaN)
            if (r5 != r4) goto L68
            goto L6b
        L68:
            com.android.systemui.authentication.shared.model.AuthenticationWipeModel$WipeTarget$ManagedProfile r4 = com.android.systemui.authentication.shared.model.AuthenticationWipeModel.WipeTarget.ManagedProfile.INSTANCE
            goto L61
        L6b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor.access$getWipeTarget(com.android.systemui.authentication.domain.interactor.AuthenticationInteractor, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object getAuthenticationMethod(ContinuationImpl continuationImpl) {
        AuthenticationRepositoryImpl authenticationRepositoryImpl = this.repository;
        return authenticationRepositoryImpl.getAuthenticationMethod(authenticationRepositoryImpl.getSelectedUserId(), continuationImpl);
    }
}
