package com.android.systemui.keyguard.domain.interactor;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.shared.model.DismissAction;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.util.kotlin.Utils;
import com.android.systemui.util.kotlin.Utils$Companion$sample$$inlined$combine$1;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardDismissActionInteractor {
    public final StateFlow dismissAction;
    public final KeyguardDismissActionInteractor$special$$inlined$map$4 executeDismissAction;
    public final KeyguardDismissActionInteractor$special$$inlined$map$4 finishedTransitionToGone;
    public final SafeFlow isOnShadeWhileUnlocked;
    public final KeyguardDismissActionInteractor$special$$inlined$map$1 onCancel;
    public final KeyguardDismissActionInteractor$special$$inlined$map$4 resetDismissAction;
    public final ReadonlyStateFlow willAnimateDismissActionOnLockscreen;

    public KeyguardDismissActionInteractor(KeyguardRepositoryImpl keyguardRepositoryImpl, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardDismissInteractor keyguardDismissInteractor, CoroutineScope coroutineScope, PowerInteractor powerInteractor, AlternateBouncerInteractor alternateBouncerInteractor) {
        final int i = 2;
        final int i2 = 3;
        final int i3 = 1;
        final int i4 = 0;
        final ReadonlyStateFlow readonlyStateFlow = keyguardRepositoryImpl.dismissAction;
        new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L44
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.DismissAction r5 = (com.android.systemui.keyguard.shared.model.DismissAction) r5
                        r5.getClass()
                        com.android.systemui.keyguard.shared.model.DismissAction$None$onCancelAction$1 r5 = com.android.systemui.keyguard.shared.model.DismissAction$None$onCancelAction$1.INSTANCE
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L44
                        return r1
                    L44:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = readonlyStateFlow.collect(new KeyguardDismissActionInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        FlowKt.stateIn(new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L44
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.DismissAction r5 = (com.android.systemui.keyguard.shared.model.DismissAction) r5
                        r5.getClass()
                        com.android.systemui.keyguard.shared.model.DismissAction$None$onCancelAction$1 r5 = com.android.systemui.keyguard.shared.model.DismissAction$None$onCancelAction$1.INSTANCE
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L44
                        return r1
                    L44:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = readonlyStateFlow.collect(new KeyguardDismissActionInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        SceneKey sceneKey = Scenes.Bouncer;
        KeyguardState keyguardState = KeyguardState.GONE;
        final Flow isFinishedIn = keyguardTransitionInteractor.isFinishedIn(keyguardState);
        final Flow flow = new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((KeyguardDismissActionInteractor$special$$inlined$map$4) isFinishedIn).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = isFinishedIn.collect(new KeyguardDismissActionInteractor$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) isFinishedIn).collect(new KeyguardDismissActionInteractor$special$$inlined$filter$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) isFinishedIn).collect(new KeyguardDismissActionInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) isFinishedIn).collect(new KeyguardDismissActionInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((Utils$Companion$sample$$inlined$combine$1) isFinishedIn).collect(new KeyguardDismissActionInteractor$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        Flow flow2 = new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = ((KeyguardDismissActionInteractor$special$$inlined$map$4) flow).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = flow.collect(new KeyguardDismissActionInteractor$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) flow).collect(new KeyguardDismissActionInteractor$special$$inlined$filter$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) flow).collect(new KeyguardDismissActionInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) flow).collect(new KeyguardDismissActionInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((Utils$Companion$sample$$inlined$combine$1) flow).collect(new KeyguardDismissActionInteractor$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        SafeFlow safeFlow = new SafeFlow(new KeyguardDismissActionInteractor$isOnShadeWhileUnlocked$3(2, null));
        final KeyguardDismissActionInteractor$special$$inlined$filter$2 keyguardDismissActionInteractor$special$$inlined$filter$2 = new KeyguardDismissActionInteractor$special$$inlined$filter$2(safeFlow, i4);
        final KeyguardDismissActionInteractor$special$$inlined$filter$2 keyguardDismissActionInteractor$special$$inlined$filter$22 = new KeyguardDismissActionInteractor$special$$inlined$filter$2(com.android.systemui.util.kotlin.FlowKt.sample(FlowKt.merge(flow2, new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((KeyguardDismissActionInteractor$special$$inlined$map$4) keyguardDismissActionInteractor$special$$inlined$filter$2).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = keyguardDismissActionInteractor$special$$inlined$filter$2.collect(new KeyguardDismissActionInteractor$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) keyguardDismissActionInteractor$special$$inlined$filter$2).collect(new KeyguardDismissActionInteractor$special$$inlined$filter$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) keyguardDismissActionInteractor$special$$inlined$filter$2).collect(new KeyguardDismissActionInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) keyguardDismissActionInteractor$special$$inlined$filter$2).collect(new KeyguardDismissActionInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((Utils$Companion$sample$$inlined$combine$1) keyguardDismissActionInteractor$special$$inlined$filter$2).collect(new KeyguardDismissActionInteractor$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, keyguardDismissInteractor.dismissKeyguardRequestWithImmediateDismissAction), readonlyStateFlow), i3);
        final int i5 = 4;
        new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i5) {
                    case 0:
                        Object collect = ((KeyguardDismissActionInteractor$special$$inlined$map$4) keyguardDismissActionInteractor$special$$inlined$filter$22).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = keyguardDismissActionInteractor$special$$inlined$filter$22.collect(new KeyguardDismissActionInteractor$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) keyguardDismissActionInteractor$special$$inlined$filter$22).collect(new KeyguardDismissActionInteractor$special$$inlined$filter$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) keyguardDismissActionInteractor$special$$inlined$filter$22).collect(new KeyguardDismissActionInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) keyguardDismissActionInteractor$special$$inlined$filter$22).collect(new KeyguardDismissActionInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((Utils$Companion$sample$$inlined$combine$1) keyguardDismissActionInteractor$special$$inlined$filter$22).collect(new KeyguardDismissActionInteractor$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(keyguardTransitionInteractor.isFinishedIn(keyguardState), keyguardTransitionInteractor.isFinishedIn(KeyguardState.PRIMARY_BOUNCER), alternateBouncerInteractor.isVisible, safeFlow, powerInteractor.isAsleep, new KeyguardDismissActionInteractor$resetDismissAction$1(null));
        final Utils$Companion$sample$$inlined$combine$1 sampleFilter = Utils.Companion.sampleFilter(new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((KeyguardDismissActionInteractor$special$$inlined$map$4) combine).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = combine.collect(new KeyguardDismissActionInteractor$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) combine).collect(new KeyguardDismissActionInteractor$special$$inlined$filter$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) combine).collect(new KeyguardDismissActionInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) combine).collect(new KeyguardDismissActionInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((Utils$Companion$sample$$inlined$combine$1) combine).collect(new KeyguardDismissActionInteractor$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, readonlyStateFlow, new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$resetDismissAction$3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(!(((DismissAction) obj) instanceof DismissAction.None));
            }
        });
        final int i6 = 5;
        new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardDismissActionInteractor$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i6) {
                    case 0:
                        Object collect = ((KeyguardDismissActionInteractor$special$$inlined$map$4) sampleFilter).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = sampleFilter.collect(new KeyguardDismissActionInteractor$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) sampleFilter).collect(new KeyguardDismissActionInteractor$special$$inlined$filter$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) sampleFilter).collect(new KeyguardDismissActionInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((KeyguardDismissActionInteractor$special$$inlined$filter$2) sampleFilter).collect(new KeyguardDismissActionInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect6 = ((Utils$Companion$sample$$inlined$combine$1) sampleFilter).collect(new KeyguardDismissActionInteractor$special$$inlined$map$7$2(flowCollector), continuation);
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
