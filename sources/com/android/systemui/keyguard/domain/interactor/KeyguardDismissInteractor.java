package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.data.repository.TrustRepositoryImpl;
import com.android.systemui.keyguard.data.repository.TrustRepositoryImpl$special$$inlined$map$4;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.kotlin.FlowKt;
import com.android.systemui.util.kotlin.Utils;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharedFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardDismissInteractor {
    public final KeyguardDismissInteractor$special$$inlined$map$1 biometricAuthenticatedRequestDismissKeyguard;
    public final DismissCallbackRegistry dismissCallbackRegistry;
    public final SafeFlow dismissKeyguardRequest;
    public final KeyguardDismissInteractor$special$$inlined$map$1 dismissKeyguardRequestWithImmediateDismissAction;
    public final KeyguardDismissInteractor$special$$inlined$map$1 dismissKeyguardRequestWithoutImmediateDismissAction;
    public final SharedFlow keyguardDone;
    public final KeyguardRepositoryImpl keyguardRepository;
    public final CoroutineDispatcher mainDispatcher;
    public final KeyguardDismissInteractor$special$$inlined$map$1 onTrustGrantedRequestDismissKeyguard;
    public final KeyguardDismissInteractor$special$$inlined$map$1 primaryAuthenticated;
    public final PrimaryBouncerInteractor primaryBouncerInteractor;
    public final SelectedUserInteractor selectedUserInteractor;
    public final KeyguardDismissInteractor$special$$inlined$map$1 userRequestedBouncerWhenAlreadyAuthenticated;

    /* JADX WARN: Type inference failed for: r3v6, types: [com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1] */
    public KeyguardDismissInteractor(CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, KeyguardRepositoryImpl keyguardRepositoryImpl, PrimaryBouncerInteractor primaryBouncerInteractor, SelectedUserInteractor selectedUserInteractor, DismissCallbackRegistry dismissCallbackRegistry, TrustRepositoryImpl trustRepositoryImpl, AlternateBouncerInteractor alternateBouncerInteractor, PowerInteractor powerInteractor) {
        this.selectedUserInteractor = selectedUserInteractor;
        final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = primaryBouncerInteractor.keyguardAuthenticatedBiometrics;
        final int i = 0;
        Flow flow = new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        r4 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r4) goto L29
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L44
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        r6.getClass()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((KeyguardDismissInteractor$special$$inlined$filter$1) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardDismissInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((KeyguardDismissInteractor$special$$inlined$filter$2) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardDismissInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissInteractor$special$$inlined$filter$2) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardDismissInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissInteractor$special$$inlined$filter$1) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardDismissInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((KeyguardDismissInteractor$special$$inlined$filter$1) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardDismissInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        TrustRepositoryImpl$special$$inlined$map$4 trustAgentRequestingToDismissKeyguard = trustRepositoryImpl.getTrustAgentRequestingToDismissKeyguard();
        KeyguardDismissInteractor$onTrustGrantedRequestDismissKeyguard$2 keyguardDismissInteractor$onTrustGrantedRequestDismissKeyguard$2 = KeyguardDismissInteractor$onTrustGrantedRequestDismissKeyguard$2.INSTANCE;
        final KeyguardDismissInteractor$special$$inlined$filter$1 keyguardDismissInteractor$special$$inlined$filter$1 = new KeyguardDismissInteractor$special$$inlined$filter$1(FlowKt.sample(trustAgentRequestingToDismissKeyguard, kotlinx.coroutines.flow.FlowKt.combine(primaryBouncerInteractor.isShowing, alternateBouncerInteractor.isVisible, powerInteractor.isInteractive, keyguardDismissInteractor$onTrustGrantedRequestDismissKeyguard$2), new KeyguardDismissInteractor$onTrustGrantedRequestDismissKeyguard$3(3, Utils.Companion, Utils.Companion.class, "toQuad", "toQuad(Ljava/lang/Object;Lkotlin/Triple;)Lcom/android/systemui/util/kotlin/Quad;", 4)), 0);
        final int i2 = 1;
        Flow flow2 = new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        r4 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r4) goto L29
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L44
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        r6.getClass()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) keyguardDismissInteractor$special$$inlined$filter$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$1).collect(new KeyguardDismissInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$1).collect(new KeyguardDismissInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$1).collect(new KeyguardDismissInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$1).collect(new KeyguardDismissInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$1).collect(new KeyguardDismissInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final KeyguardDismissInteractor$special$$inlined$filter$2 keyguardDismissInteractor$special$$inlined$filter$2 = new KeyguardDismissInteractor$special$$inlined$filter$2(primaryBouncerInteractor.keyguardAuthenticatedPrimaryAuth, this, 0);
        final int i3 = 2;
        Flow flow3 = new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        r4 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r4) goto L29
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L44
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        r6.getClass()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) keyguardDismissInteractor$special$$inlined$filter$2).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$2).collect(new KeyguardDismissInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$2).collect(new KeyguardDismissInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$2).collect(new KeyguardDismissInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$2).collect(new KeyguardDismissInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$2).collect(new KeyguardDismissInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final KeyguardDismissInteractor$special$$inlined$filter$2 keyguardDismissInteractor$special$$inlined$filter$22 = new KeyguardDismissInteractor$special$$inlined$filter$2(primaryBouncerInteractor.userRequestedBouncerWhenAlreadyAuthenticated, this, 1);
        final int i4 = 3;
        Flow flow4 = new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        r4 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r4) goto L29
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L44
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        r6.getClass()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) keyguardDismissInteractor$special$$inlined$filter$22).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$22).collect(new KeyguardDismissInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$22).collect(new KeyguardDismissInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$22).collect(new KeyguardDismissInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$22).collect(new KeyguardDismissInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$22).collect(new KeyguardDismissInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        ReadonlySharedFlow readonlySharedFlow = keyguardRepositoryImpl.keyguardDone;
        SafeFlow sample = FlowKt.sample(kotlinx.coroutines.flow.FlowKt.merge(flow, flow2, flow3, flow4), keyguardRepositoryImpl.dismissAction);
        final KeyguardDismissInteractor$special$$inlined$filter$1 keyguardDismissInteractor$special$$inlined$filter$12 = new KeyguardDismissInteractor$special$$inlined$filter$1(sample, 1);
        final int i5 = 4;
        this.dismissKeyguardRequestWithImmediateDismissAction = new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        r4 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r4) goto L29
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L44
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        r6.getClass()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i5) {
                    case 0:
                        Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) keyguardDismissInteractor$special$$inlined$filter$12).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$12).collect(new KeyguardDismissInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$12).collect(new KeyguardDismissInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$12).collect(new KeyguardDismissInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$12).collect(new KeyguardDismissInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$12).collect(new KeyguardDismissInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final KeyguardDismissInteractor$special$$inlined$filter$1 keyguardDismissInteractor$special$$inlined$filter$13 = new KeyguardDismissInteractor$special$$inlined$filter$1(sample, 2);
        final int i6 = 5;
        new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        r4 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r4) goto L29
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L44
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        r6.getClass()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i6) {
                    case 0:
                        Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) keyguardDismissInteractor$special$$inlined$filter$13).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$13).collect(new KeyguardDismissInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$13).collect(new KeyguardDismissInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissInteractor$special$$inlined$filter$2) keyguardDismissInteractor$special$$inlined$filter$13).collect(new KeyguardDismissInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$13).collect(new KeyguardDismissInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((KeyguardDismissInteractor$special$$inlined$filter$1) keyguardDismissInteractor$special$$inlined$filter$13).collect(new KeyguardDismissInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
    }
}
