package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.authentication.domain.interactor.AuthenticationInteractor;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.deviceentry.data.repository.DeviceEntryRepositoryImpl;
import com.android.systemui.deviceentry.shared.model.DeviceUnlockSource;
import com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.scene.domain.interactor.SceneBackInteractor;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryInteractor {
    public final AlternateBouncerInteractor alternateBouncerInteractor;
    public final CoroutineScope applicationScope;
    public final AuthenticationInteractor authenticationInteractor;
    public final Lazy canSwipeToEnter$delegate;
    public final DeviceUnlockedInteractor deviceUnlockedInteractor;
    public final DismissCallbackRegistry dismissCallbackRegistry;
    public final ReadonlyStateFlow isBypassEnabled;
    public final ReadonlyStateFlow isDeviceEntered;
    public final Lazy isLockscreenEnabled$delegate;
    public final ReadonlyStateFlow isUnlocked;
    public final DeviceEntryRepositoryImpl repository;
    public final SceneInteractor sceneInteractor;

    public DeviceEntryInteractor(CoroutineScope coroutineScope, DeviceEntryRepositoryImpl deviceEntryRepositoryImpl, AuthenticationInteractor authenticationInteractor, SceneInteractor sceneInteractor, DeviceUnlockedInteractor deviceUnlockedInteractor, AlternateBouncerInteractor alternateBouncerInteractor, DismissCallbackRegistry dismissCallbackRegistry, SceneBackInteractor sceneBackInteractor) {
        this.applicationScope = coroutineScope;
        this.repository = deviceEntryRepositoryImpl;
        this.authenticationInteractor = authenticationInteractor;
        this.deviceUnlockedInteractor = deviceUnlockedInteractor;
        final ReadonlyStateFlow readonlyStateFlow = deviceUnlockedInteractor.deviceUnlockStatus;
        final int i = 0;
        this.isUnlocked = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L45
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus r5 = (com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus) r5
                        boolean r5 = r5.isUnlocked
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L45
                        return r1
                    L45:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    default:
                        readonlyStateFlow.collect(new DeviceEntryInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(((DeviceUnlockStatus) readonlyStateFlow.getValue()).isUnlocked));
        Flow buffer$default = FlowKt.buffer$default(FlowKt.mapLatest(new DeviceEntryInteractor$isDeviceEntered$2(this, null), new DeviceEntryInteractor$special$$inlined$map$3(sceneInteractor.currentScene, 2)), -1);
        final ReadonlyStateFlow readonlyStateFlow2 = sceneBackInteractor.backStack;
        final int i2 = 1;
        this.isDeviceEntered = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(buffer$default, new DeviceEntryInteractor$special$$inlined$map$3(com.android.systemui.util.kotlin.FlowKt.pairwise(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L45
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus r5 = (com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus) r5
                        boolean r5 = r5.isUnlocked
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L45
                        return r1
                    L45:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        readonlyStateFlow2.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    default:
                        readonlyStateFlow2.collect(new DeviceEntryInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }), null), 0), new DeviceEntryInteractor$isDeviceEntered$5(3, null)), coroutineScope, SharingStarted.Companion.Eagerly, Boolean.FALSE);
        this.isLockscreenEnabled$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$isLockscreenEnabled$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$isLockscreenEnabled$2$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ DeviceEntryInteractor this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(DeviceEntryInteractor deviceEntryInteractor, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = deviceEntryInteractor;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass1(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    Unit unit = Unit.INSTANCE;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        DeviceEntryInteractor deviceEntryInteractor = this.this$0;
                        this.label = 1;
                        Object isLockscreenEnabled = deviceEntryInteractor.repository.isLockscreenEnabled(this);
                        if (isLockscreenEnabled != coroutineSingletons) {
                            isLockscreenEnabled = unit;
                        }
                        if (isLockscreenEnabled == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return unit;
                }
            }

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                DeviceEntryInteractor deviceEntryInteractor = DeviceEntryInteractor.this;
                return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AnonymousClass1(deviceEntryInteractor, null), deviceEntryInteractor.repository.isLockscreenEnabled);
            }
        });
        this.canSwipeToEnter$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$canSwipeToEnter$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$canSwipeToEnter$2$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function5 {
                /* synthetic */ Object L$0;
                /* synthetic */ boolean Z$0;
                /* synthetic */ boolean Z$1;
                /* synthetic */ boolean Z$2;
                int label;

                @Override // kotlin.jvm.functions.Function5
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    boolean booleanValue2 = ((Boolean) obj2).booleanValue();
                    boolean booleanValue3 = ((Boolean) obj4).booleanValue();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(5, (Continuation) obj5);
                    anonymousClass2.Z$0 = booleanValue;
                    anonymousClass2.Z$1 = booleanValue2;
                    anonymousClass2.L$0 = (DeviceUnlockStatus) obj3;
                    anonymousClass2.Z$2 = booleanValue3;
                    return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    DeviceUnlockSource deviceUnlockSource;
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    boolean z = this.Z$0;
                    boolean z2 = this.Z$1;
                    DeviceUnlockStatus deviceUnlockStatus = (DeviceUnlockStatus) this.L$0;
                    return Boolean.valueOf(((z && z2) || !(!deviceUnlockStatus.isUnlocked || (deviceUnlockSource = deviceUnlockStatus.deviceUnlockSource) == null || deviceUnlockSource.dismissesLockscreen)) && !this.Z$2);
                }
            }

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                DeviceEntryInteractor deviceEntryInteractor = DeviceEntryInteractor.this;
                DeviceEntryInteractor$special$$inlined$map$3 deviceEntryInteractor$special$$inlined$map$3 = new DeviceEntryInteractor$special$$inlined$map$3(deviceEntryInteractor.authenticationInteractor.authenticationMethod, 1);
                Flow flow = (Flow) deviceEntryInteractor.isLockscreenEnabled$delegate.getValue();
                DeviceEntryInteractor deviceEntryInteractor2 = DeviceEntryInteractor.this;
                return FlowKt.stateIn(FlowKt.combine(deviceEntryInteractor$special$$inlined$map$3, flow, deviceEntryInteractor2.deviceUnlockedInteractor.deviceUnlockStatus, deviceEntryInteractor2.isDeviceEntered, new AnonymousClass2(5, null)), DeviceEntryInteractor.this.applicationScope, SharingStarted.Companion.Eagerly, null);
            }
        });
        this.isBypassEnabled = deviceEntryRepositoryImpl.isBypassEnabled;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x004f, code lost:
    
        if (((com.android.systemui.authentication.shared.model.AuthenticationMethodModel) r5).isSecure == false) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isAuthenticationRequired(kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$isAuthenticationRequired$1
            if (r0 == 0) goto L13
            r0 = r5
            com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$isAuthenticationRequired$1 r0 = (com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$isAuthenticationRequired$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$isAuthenticationRequired$1 r0 = new com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor$isAuthenticationRequired$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4b
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            kotlin.ResultKt.throwOnFailure(r5)
            com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor r5 = r4.deviceUnlockedInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r5.deviceUnlockStatus
            java.lang.Object r5 = r5.getValue()
            com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus r5 = (com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus) r5
            boolean r5 = r5.isUnlocked
            if (r5 != 0) goto L52
            r0.label = r3
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r4 = r4.authenticationInteractor
            java.lang.Object r5 = r4.getAuthenticationMethod(r0)
            if (r5 != r1) goto L4b
            return r1
        L4b:
            com.android.systemui.authentication.shared.model.AuthenticationMethodModel r5 = (com.android.systemui.authentication.shared.model.AuthenticationMethodModel) r5
            boolean r4 = r5.isSecure
            if (r4 == 0) goto L52
            goto L53
        L52:
            r3 = 0
        L53:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor.isAuthenticationRequired(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
