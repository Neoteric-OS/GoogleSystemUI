package com.android.systemui.keyguard.data.repository;

import android.app.trust.TrustManager;
import com.android.keyguard.logging.TrustRepositoryLogger;
import com.android.systemui.keyguard.shared.model.TrustModel;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StartedWhileSubscribed;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrustRepositoryImpl {
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 isCurrentUserActiveUnlockRunning;
    public final ReadonlyStateFlow isCurrentUserTrustUsuallyManaged;
    public final TrustRepositoryLogger logger;
    public final ReadonlySharedFlow trust;
    public final TrustManager trustManager;
    public final UserRepositoryImpl userRepository;
    public final Map latestTrustModelForUser = new LinkedHashMap();
    public final Map activeUnlockRunningForUser = new LinkedHashMap();
    public final Map trustManagedForUser = new LinkedHashMap();

    public TrustRepositoryImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, UserRepositoryImpl userRepositoryImpl, TrustManager trustManager, TrustRepositoryLogger trustRepositoryLogger) {
        this.applicationScope = coroutineScope;
        this.userRepository = userRepositoryImpl;
        this.trustManager = trustManager;
        this.logger = trustRepositoryLogger;
        FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowConflatedKt.conflatedCallbackFlow(new TrustRepositoryImpl$trust$1(this, null)), new TrustRepositoryImpl$trust$2(this, null), 0);
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlySharedFlow shareIn = FlowKt.shareIn(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, coroutineScope, startedEagerly, 1);
        this.trust = shareIn;
        this.isCurrentUserActiveUnlockRunning = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new TrustRepositoryImpl$isCurrentUserActiveUnlockRunning$5(this, null), new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new TrustRepositoryImpl$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(shareIn, userRepositoryImpl.selectedUserInfo, TrustRepositoryImpl$isCurrentUserActiveUnlockRunning$2.INSTANCE), this, 0)), new TrustRepositoryImpl$isCurrentUserActiveUnlockRunning$4(this, null), 0));
        this.isCurrentUserTrustUsuallyManaged = FlowKt.stateIn(FlowKt.transformLatest(userRepositoryImpl.selectedUserInfo, new TrustRepositoryImpl$special$$inlined$flatMapLatest$1(this, null)), coroutineScope, startedEagerly, Boolean.FALSE);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4] */
    public final TrustRepositoryImpl$special$$inlined$map$4 getTrustAgentRequestingToDismissKeyguard() {
        final Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new TrustRepositoryImpl$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.trust, this.userRepository.selectedUserInfo, TrustRepositoryImpl$trustAgentRequestingToDismissKeyguard$2.INSTANCE), this, 2));
        final int i = 1;
        final Flow flow = new Flow() { // from class: com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2$1 r0 = (com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2$1 r0 = new com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L42
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.TrustModel r5 = (com.android.systemui.keyguard.shared.model.TrustModel) r5
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L42
                        return r1
                    L42:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((TrustRepositoryImpl$special$$inlined$map$4) distinctUntilChanged).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = distinctUntilChanged.collect(new TrustRepositoryImpl$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final int i2 = 0;
        return new Flow() { // from class: com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2$1 r0 = (com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2$1 r0 = new com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L42
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.TrustModel r5 = (com.android.systemui.keyguard.shared.model.TrustModel) r5
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L42
                        return r1
                    L42:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((TrustRepositoryImpl$special$$inlined$map$4) flow).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = flow.collect(new TrustRepositoryImpl$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
    }

    public final ReadonlyStateFlow isCurrentUserTrustManaged() {
        return FlowKt.stateIn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new TrustRepositoryImpl$isCurrentUserTrustManaged$5(2, null), new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new TrustRepositoryImpl$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.trust, this.userRepository.selectedUserInfo, TrustRepositoryImpl$isCurrentUserTrustManaged$2.INSTANCE), this, 1)), new TrustRepositoryImpl$isCurrentUserTrustManaged$4(this, null), 0)), this.applicationScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
    }

    public final ReadonlyStateFlow isCurrentUserTrusted() {
        UserRepositoryImpl userRepositoryImpl = this.userRepository;
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new TrustRepositoryImpl$isCurrentUserTrusted$5(2, null), new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new TrustRepositoryImpl$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.trust, userRepositoryImpl.selectedUserInfo, TrustRepositoryImpl$isCurrentUserTrusted$2.INSTANCE), this, 3)), new TrustRepositoryImpl$isCurrentUserTrusted$4(this, null), 0));
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        TrustModel trustModel = (TrustModel) this.latestTrustModelForUser.get(Integer.valueOf(userRepositoryImpl.getSelectedUserInfo().id));
        return FlowKt.stateIn(flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, this.applicationScope, WhileSubscribed$default, Boolean.valueOf(trustModel != null ? trustModel.isTrusted : false));
    }
}
