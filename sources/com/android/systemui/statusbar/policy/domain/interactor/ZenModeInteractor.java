package com.android.systemui.statusbar.policy.domain.interactor;

import android.content.Context;
import com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl;
import com.android.settingslib.notification.modes.ZenIconLoader;
import com.android.systemui.shared.notifications.data.repository.NotificationSettingsRepository;
import com.android.systemui.statusbar.policy.data.repository.DeviceProvisioningRepositoryImpl;
import com.android.systemui.statusbar.policy.data.repository.UserSetupRepositoryImpl;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ZenModeInteractor {
    public final Flow activeModes;
    public final Flow areNotificationsHiddenInShade;
    public final Context context;
    public final DeviceProvisioningRepositoryImpl deviceProvisioningRepository;
    public final ZenIconLoader iconLoader;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isZenAvailable;
    public final Flow mainActiveMode;
    public final Flow modes;
    public final NotificationSettingsRepository notificationSettingsRepository;
    public final ZenModeRepositoryImpl zenModeRepository;

    public ZenModeInteractor(Context context, ZenModeRepositoryImpl zenModeRepositoryImpl, NotificationSettingsRepository notificationSettingsRepository, CoroutineDispatcher coroutineDispatcher, ZenIconLoader zenIconLoader, DeviceProvisioningRepositoryImpl deviceProvisioningRepositoryImpl, UserSetupRepositoryImpl userSetupRepositoryImpl) {
        this.context = context;
        this.zenModeRepository = zenModeRepositoryImpl;
        this.notificationSettingsRepository = notificationSettingsRepository;
        this.iconLoader = zenIconLoader;
        new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(deviceProvisioningRepositoryImpl.isDeviceProvisioned, userSetupRepositoryImpl.isUserSetUp, new ZenModeInteractor$isZenAvailable$1(3, null));
        final StateFlow stateFlow = (StateFlow) zenModeRepositoryImpl.globalZenMode$delegate.getValue();
        final int i = 0;
        this.areNotificationsHiddenInShade = FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L58
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Integer r5 = (java.lang.Integer) r5
                        r6 = 0
                        if (r5 == 0) goto L3c
                        int r5 = r5.intValue()
                        goto L3d
                    L3c:
                        r5 = r6
                    L3d:
                        if (r5 == 0) goto L49
                        if (r5 == r3) goto L48
                        r2 = 2
                        if (r5 == r2) goto L48
                        r2 = 3
                        if (r5 == r2) goto L48
                        goto L49
                    L48:
                        r6 = r3
                    L49:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L58
                        return r1
                    L58:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((StateFlow) stateFlow).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = stateFlow.collect(new ZenModeInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }), zenModeRepositoryImpl.getConsolidatedNotificationPolicy(), new ZenModeInteractor$areNotificationsHiddenInShade$1(3, null)));
        final Flow flow = (Flow) zenModeRepositoryImpl.modes$delegate.getValue();
        this.modes = flow;
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$dndMode$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                throw new IllegalStateException("New code path not supported when android.app.modes_ui is disabled.");
            }
        });
        final Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.flowOn(new Flow() { // from class: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ZenModeInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ZenModeInteractor zenModeInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = zenModeInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:19:0x005c A[RETURN] */
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
                        boolean r0 = r8 instanceof com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2$2$1
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
                        goto L5d
                    L2a:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L32:
                        java.lang.Object r6 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L51
                    L3a:
                        kotlin.ResultKt.throwOnFailure(r8)
                        java.util.List r7 = (java.util.List) r7
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow
                        r0.L$0 = r8
                        r0.label = r4
                        com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor r6 = r6.this$0
                        java.lang.Object r6 = r6.buildActiveZenModes(r7, r0)
                        if (r6 != r1) goto L4e
                        return r1
                    L4e:
                        r5 = r8
                        r8 = r6
                        r6 = r5
                    L51:
                        r7 = 0
                        r0.L$0 = r7
                        r0.label = r3
                        java.lang.Object r6 = r6.emit(r8, r0)
                        if (r6 != r1) goto L5d
                        return r1
                    L5d:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineDispatcher));
        this.activeModes = distinctUntilChanged;
        final int i2 = 1;
        FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L58
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Integer r5 = (java.lang.Integer) r5
                        r6 = 0
                        if (r5 == 0) goto L3c
                        int r5 = r5.intValue()
                        goto L3d
                    L3c:
                        r5 = r6
                    L3d:
                        if (r5 == 0) goto L49
                        if (r5 == r3) goto L48
                        r2 = 2
                        if (r5 == r2) goto L48
                        r2 = 3
                        if (r5 == r2) goto L48
                        goto L49
                    L48:
                        r6 = r3
                    L49:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L58
                        return r1
                    L58:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((StateFlow) distinctUntilChanged).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = distinctUntilChanged.collect(new ZenModeInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object buildActiveZenModes(java.util.List r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$buildActiveZenModes$1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$buildActiveZenModes$1 r0 = (com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$buildActiveZenModes$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$buildActiveZenModes$1 r0 = new com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$buildActiveZenModes$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = ""
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 != r4) goto L31
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r0.L$0
            java.util.List r7 = (java.util.List) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L81
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r7 = r7.iterator()
        L45:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L5c
            java.lang.Object r2 = r7.next()
            r5 = r2
            com.android.settingslib.notification.modes.ZenMode r5 = (com.android.settingslib.notification.modes.ZenMode) r5
            boolean r5 = r5.isActive()
            if (r5 == 0) goto L45
            r8.add(r2)
            goto L45
        L5c:
            java.util.Comparator r7 = com.android.settingslib.notification.modes.ZenMode.PRIORITIZING_COMPARATOR
            java.util.List r7 = kotlin.collections.CollectionsKt.sortedWith(r8, r7)
            java.lang.Object r8 = kotlin.collections.CollectionsKt.firstOrNull(r7)
            com.android.settingslib.notification.modes.ZenMode r8 = (com.android.settingslib.notification.modes.ZenMode) r8
            if (r8 == 0) goto L89
            android.app.AutomaticZenRule r2 = r8.mRule
            java.lang.String r2 = r2.getName()
            if (r2 != 0) goto L73
            r2 = r3
        L73:
            r0.L$0 = r7
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r8 = r6.getModeIcon(r8, r0)
            if (r8 != r1) goto L80
            return r1
        L80:
            r6 = r2
        L81:
            com.android.settingslib.notification.modes.ZenIcon r8 = (com.android.settingslib.notification.modes.ZenIcon) r8
            com.android.systemui.statusbar.policy.domain.model.ZenModeInfo r0 = new com.android.systemui.statusbar.policy.domain.model.ZenModeInfo
            r0.<init>(r6, r8)
            goto L8a
        L89:
            r0 = 0
        L8a:
            java.util.ArrayList r6 = new java.util.ArrayList
            r8 = 10
            int r8 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r7, r8)
            r6.<init>(r8)
            java.util.Iterator r7 = r7.iterator()
        L99:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto Lb2
            java.lang.Object r8 = r7.next()
            com.android.settingslib.notification.modes.ZenMode r8 = (com.android.settingslib.notification.modes.ZenMode) r8
            android.app.AutomaticZenRule r8 = r8.mRule
            java.lang.String r8 = r8.getName()
            if (r8 != 0) goto Lae
            r8 = r3
        Lae:
            r6.add(r8)
            goto L99
        Lb2:
            com.android.systemui.statusbar.policy.domain.model.ActiveZenModes r7 = new com.android.systemui.statusbar.policy.domain.model.ActiveZenModes
            r7.<init>(r6, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor.buildActiveZenModes(java.util.List, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getModeIcon(final com.android.settingslib.notification.modes.ZenMode r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$getModeIcon$1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$getModeIcon$1 r0 = (com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$getModeIcon$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$getModeIcon$1 r0 = new com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor$getModeIcon$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L30
            if (r2 != r3) goto L28
            kotlin.ResultKt.throwOnFailure(r8)
            goto Lce
        L28:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L30:
            kotlin.ResultKt.throwOnFailure(r8)
            android.content.Context r8 = r6.context
            com.android.settingslib.notification.modes.ZenIconLoader r6 = r6.iconLoader
            r6.getClass()
            boolean r2 = r7.isManualDnd()
            if (r2 == 0) goto L43
            com.android.settingslib.notification.modes.ZenIcon$Key r2 = com.android.settingslib.notification.modes.ZenIconKeys.MANUAL_DND
            goto L96
        L43:
            android.app.AutomaticZenRule r2 = r7.mRule
            int r2 = r2.getIconResId()
            if (r2 == 0) goto L76
            android.app.AutomaticZenRule r2 = r7.mRule
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r4 = "android"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L64
            android.app.AutomaticZenRule r2 = r7.mRule
            int r2 = r2.getIconResId()
            com.android.settingslib.notification.modes.ZenIcon$Key r2 = com.android.settingslib.notification.modes.ZenIcon.Key.forSystemResource(r2)
            goto L96
        L64:
            com.android.settingslib.notification.modes.ZenIcon$Key r2 = new com.android.settingslib.notification.modes.ZenIcon$Key
            android.app.AutomaticZenRule r4 = r7.mRule
            java.lang.String r4 = r4.getPackageName()
            android.app.AutomaticZenRule r5 = r7.mRule
            int r5 = r5.getIconResId()
            r2.<init>(r4, r5)
            goto L96
        L76:
            com.android.settingslib.notification.modes.ZenMode$Kind r2 = r7.mKind
            com.android.settingslib.notification.modes.ZenMode$Kind r4 = com.android.settingslib.notification.modes.ZenMode.Kind.IMPLICIT
            if (r2 != r4) goto L7f
            com.android.settingslib.notification.modes.ZenIcon$Key r2 = com.android.settingslib.notification.modes.ZenIconKeys.IMPLICIT_MODE_DEFAULT
            goto L96
        L7f:
            android.app.AutomaticZenRule r2 = r7.mRule
            int r2 = r2.getType()
            com.google.common.collect.ImmutableMap r4 = com.android.settingslib.notification.modes.ZenIconKeys.TYPE_DEFAULTS
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r2 = r4.get(r2)
            if (r2 == 0) goto L92
            goto L94
        L92:
            com.android.settingslib.notification.modes.ZenIcon$Key r2 = com.android.settingslib.notification.modes.ZenIconKeys.FOR_UNEXPECTED_TYPE
        L94:
            com.android.settingslib.notification.modes.ZenIcon$Key r2 = (com.android.settingslib.notification.modes.ZenIcon.Key) r2
        L96:
            com.google.common.util.concurrent.ListenableFuture r4 = r6.loadIcon(r8, r2, r3)
            int r5 = com.google.common.util.concurrent.FluentFuture.$r8$clinit
            boolean r5 = r4 instanceof com.google.common.util.concurrent.FluentFuture
            if (r5 == 0) goto La3
            com.google.common.util.concurrent.FluentFuture r4 = (com.google.common.util.concurrent.FluentFuture) r4
            goto La9
        La3:
            com.google.common.util.concurrent.ForwardingFluentFuture r5 = new com.google.common.util.concurrent.ForwardingFluentFuture
            r5.<init>(r4)
            r4 = r5
        La9:
            com.android.settingslib.notification.modes.ZenIconLoader$$ExternalSyntheticLambda0 r5 = new com.android.settingslib.notification.modes.ZenIconLoader$$ExternalSyntheticLambda0
            r5.<init>()
            int r7 = com.google.common.util.concurrent.AbstractTransformFuture.$r8$clinit
            com.google.common.util.concurrent.ListeningExecutorService r6 = r6.mBackgroundExecutor
            r6.getClass()
            com.google.common.util.concurrent.AbstractTransformFuture$AsyncTransformFuture r7 = new com.google.common.util.concurrent.AbstractTransformFuture$AsyncTransformFuture
            r7.<init>()
            r7.inputFuture = r4
            r7.function = r5
            java.util.concurrent.Executor r6 = com.google.common.util.concurrent.MoreExecutors.rejectionPropagatingExecutor(r6, r7)
            r4.addListener(r7, r6)
            r0.label = r3
            java.lang.Object r8 = androidx.concurrent.futures.ListenableFutureKt.await(r7, r0)
            if (r8 != r1) goto Lce
            return r1
        Lce:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor.getModeIcon(com.android.settingslib.notification.modes.ZenMode, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final int getZenDuration() {
        return ((Number) ((StateFlowImpl) this.notificationSettingsRepository.zenDuration.$$delegate_0).getValue()).intValue();
    }
}
