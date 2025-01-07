package com.android.settingslib.notification.domain.interactor;

import com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NotificationsSoundPolicyInteractor {
    public final NotificationsSoundPolicyInteractor$special$$inlined$map$1 areAlarmsAllowed;
    public final NotificationsSoundPolicyInteractor$special$$inlined$map$1 isMediaAllowed;
    public final NotificationsSoundPolicyInteractor$special$$inlined$map$1 isRingerAllowed;
    public final NotificationsSoundPolicyInteractor$special$$inlined$map$1 isSystemAllowed;
    public final ZenModeRepositoryImpl repository;

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1] */
    public NotificationsSoundPolicyInteractor(ZenModeRepositoryImpl zenModeRepositoryImpl) {
        this.repository = zenModeRepositoryImpl;
        final StateFlow consolidatedNotificationPolicy = zenModeRepositoryImpl.getConsolidatedNotificationPolicy();
        final int i = 0;
        this.areAlarmsAllowed = new Flow() { // from class: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1 r0 = (com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1 r0 = new com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1
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
                        android.app.NotificationManager$Policy r5 = (android.app.NotificationManager.Policy) r5
                        if (r5 == 0) goto L3f
                        boolean r5 = r5.allowAlarms()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = consolidatedNotificationPolicy.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = consolidatedNotificationPolicy.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = consolidatedNotificationPolicy.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect4 = consolidatedNotificationPolicy.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final StateFlow consolidatedNotificationPolicy2 = zenModeRepositoryImpl.getConsolidatedNotificationPolicy();
        final int i2 = 1;
        this.isMediaAllowed = new Flow() { // from class: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1 r0 = (com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1 r0 = new com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1
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
                        android.app.NotificationManager$Policy r5 = (android.app.NotificationManager.Policy) r5
                        if (r5 == 0) goto L3f
                        boolean r5 = r5.allowAlarms()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = consolidatedNotificationPolicy2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = consolidatedNotificationPolicy2.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = consolidatedNotificationPolicy2.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect4 = consolidatedNotificationPolicy2.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final StateFlow consolidatedNotificationPolicy3 = zenModeRepositoryImpl.getConsolidatedNotificationPolicy();
        final int i3 = 2;
        this.isSystemAllowed = new Flow() { // from class: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1 r0 = (com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1 r0 = new com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1
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
                        android.app.NotificationManager$Policy r5 = (android.app.NotificationManager.Policy) r5
                        if (r5 == 0) goto L3f
                        boolean r5 = r5.allowAlarms()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = consolidatedNotificationPolicy3.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = consolidatedNotificationPolicy3.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = consolidatedNotificationPolicy3.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect4 = consolidatedNotificationPolicy3.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final StateFlow consolidatedNotificationPolicy4 = zenModeRepositoryImpl.getConsolidatedNotificationPolicy();
        final int i4 = 3;
        this.isRingerAllowed = new Flow() { // from class: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1 r0 = (com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1 r0 = new com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1$2$1
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
                        android.app.NotificationManager$Policy r5 = (android.app.NotificationManager.Policy) r5
                        if (r5 == 0) goto L3f
                        boolean r5 = r5.allowAlarms()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = consolidatedNotificationPolicy4.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = consolidatedNotificationPolicy4.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = consolidatedNotificationPolicy4.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect4 = consolidatedNotificationPolicy4.collect(new NotificationsSoundPolicyInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
    }

    /* renamed from: isZenMuted-tLTdkI8, reason: not valid java name */
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 m758isZenMutedtLTdkI8(int i) {
        return FlowKt.combine(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1((StateFlow) this.repository.globalZenMode$delegate.getValue()), new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(this.areAlarmsAllowed), new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(this.isMediaAllowed), new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(this.isRingerAllowed), new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(this.isSystemAllowed), new NotificationsSoundPolicyInteractor$isZenMuted$1(i, null));
    }
}
