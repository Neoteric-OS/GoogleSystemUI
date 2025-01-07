package com.android.systemui.bouncer.domain.interactor;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.authentication.domain.interactor.AuthenticationInteractor;
import com.android.systemui.bouncer.data.repository.BouncerRepository;
import com.android.systemui.classifier.domain.interactor.FalsingInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.domain.interactor.SceneBackInteractor;
import com.android.systemui.scene.domain.interactor.SceneBackInteractor$special$$inlined$map$1;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerInteractor {
    public final SharedFlowImpl _onImeHiddenByUser;
    public final SharedFlowImpl _onIncorrectBouncerInput;
    public final CoroutineScope applicationScope;
    public final AuthenticationInteractor authenticationInteractor;
    public final DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor;
    public final BouncerInteractor$special$$inlined$map$1 dismissDestination;
    public final FalsingInteractor falsingInteractor;
    public final ReadonlyStateFlow hintedPinLength;
    public final ReadonlyStateFlow isAutoConfirmEnabled;
    public final ReadonlyStateFlow isPatternVisible;
    public final ReadonlyStateFlow isPinEnhancedPrivacyEnabled;
    public final SharedFlowImpl onImeHiddenByUser;
    public final SharedFlowImpl onIncorrectBouncerInput;
    public final BouncerInteractor$special$$inlined$map$1 onLockoutStarted;
    public final PowerInteractor powerInteractor;
    public final BouncerRepository repository;
    public final SessionTracker sessionTracker;
    public final UiEventLogger uiEventLogger;

    public BouncerInteractor(CoroutineScope coroutineScope, BouncerRepository bouncerRepository, AuthenticationInteractor authenticationInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, FalsingInteractor falsingInteractor, PowerInteractor powerInteractor, UiEventLogger uiEventLogger, SessionTracker sessionTracker, SceneBackInteractor sceneBackInteractor) {
        this.authenticationInteractor = authenticationInteractor;
        SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        ReadonlyStateFlow readonlyStateFlow = authenticationInteractor.isAutoConfirmEnabled;
        ReadonlyStateFlow readonlyStateFlow2 = authenticationInteractor.hintedPinLength;
        ReadonlyStateFlow readonlyStateFlow3 = authenticationInteractor.isPatternVisible;
        ReadonlyStateFlow readonlyStateFlow4 = authenticationInteractor.isPinEnhancedPrivacyEnabled;
        SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        final BouncerInteractor$special$$inlined$filter$1 bouncerInteractor$special$$inlined$filter$1 = new BouncerInteractor$special$$inlined$filter$1(authenticationInteractor.onAuthenticationResult, this);
        final int i = 0;
        new Flow() { // from class: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((BouncerInteractor$special$$inlined$filter$1) bouncerInteractor$special$$inlined$filter$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((SceneBackInteractor$special$$inlined$map$1) bouncerInteractor$special$$inlined$filter$1).collect(new BouncerInteractor$special$$inlined$filter$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((BouncerInteractor$special$$inlined$map$1) bouncerInteractor$special$$inlined$filter$1).collect(new BouncerInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final SceneBackInteractor$special$$inlined$map$1 sceneBackInteractor$special$$inlined$map$1 = sceneBackInteractor.backScene;
        final int i2 = 1;
        final Flow flow = new Flow() { // from class: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((BouncerInteractor$special$$inlined$filter$1) sceneBackInteractor$special$$inlined$map$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((SceneBackInteractor$special$$inlined$map$1) sceneBackInteractor$special$$inlined$map$1).collect(new BouncerInteractor$special$$inlined$filter$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((BouncerInteractor$special$$inlined$map$1) sceneBackInteractor$special$$inlined$map$1).collect(new BouncerInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final int i3 = 2;
        new Flow() { // from class: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.domain.interactor.BouncerInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((BouncerInteractor$special$$inlined$filter$1) flow).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((SceneBackInteractor$special$$inlined$map$1) flow).collect(new BouncerInteractor$special$$inlined$filter$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((BouncerInteractor$special$$inlined$map$1) flow).collect(new BouncerInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
    }
}
