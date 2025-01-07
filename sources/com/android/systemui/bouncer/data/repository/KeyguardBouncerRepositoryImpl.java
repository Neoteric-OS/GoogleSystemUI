package com.android.systemui.bouncer.data.repository;

import android.os.Build;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBouncerRepositoryImpl {
    public final StateFlowImpl _alternateBouncerUIAvailable;
    public final StateFlowImpl _alternateBouncerVisible;
    public final StateFlowImpl _isBackButtonEnabled;
    public final StateFlowImpl _keyguardAuthenticatedBiometrics;
    public final SharedFlowImpl _keyguardAuthenticatedPrimaryAuth;
    public final StateFlowImpl _keyguardPosition;
    public final StateFlowImpl _lastShownSecurityMode;
    public final StateFlowImpl _panelExpansionAmount;
    public final StateFlowImpl _primaryBouncerDisappearAnimation;
    public final StateFlowImpl _primaryBouncerScrimmed;
    public final StateFlowImpl _primaryBouncerShow;
    public final StateFlowImpl _primaryBouncerShowingSoon;
    public final StateFlowImpl _primaryBouncerStartingToHide;
    public final StateFlowImpl _resourceUpdateRequests;
    public final StateFlowImpl _showMessage;
    public final SharedFlowImpl _userRequestedBouncerWhenAlreadyAuthenticated;
    public final ReadonlyStateFlow alternateBouncerUIAvailable;
    public final ReadonlyStateFlow alternateBouncerVisible;
    public final SystemClock clock;
    public final ReadonlyStateFlow isBackButtonEnabled;
    public final ReadonlyStateFlow keyguardAuthenticatedBiometrics;
    public final ReadonlySharedFlow keyguardAuthenticatedPrimaryAuth;
    public final ReadonlyStateFlow keyguardPosition;
    public final ReadonlyStateFlow lastShownSecurityMode;
    public final ReadonlyStateFlow panelExpansionAmount;
    public final ReadonlyStateFlow primaryBouncerScrimmed;
    public final ReadonlyStateFlow primaryBouncerShow;
    public final ReadonlyStateFlow primaryBouncerShowingSoon;
    public final ReadonlyStateFlow primaryBouncerStartingDisappearAnimation;
    public final ReadonlyStateFlow primaryBouncerStartingToHide;
    public final ReadonlyStateFlow resourceUpdateRequests;
    public final ReadonlyStateFlow showMessage;
    public final ReadonlySharedFlow userRequestedBouncerWhenAlreadyAuthenticated;

    public KeyguardBouncerRepositoryImpl(SystemClock systemClock, CoroutineScope coroutineScope, TableLogBuffer tableLogBuffer) {
        this.clock = systemClock;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._primaryBouncerShow = MutableStateFlow;
        ReadonlyStateFlow readonlyStateFlow = new ReadonlyStateFlow(MutableStateFlow);
        this.primaryBouncerShow = readonlyStateFlow;
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._primaryBouncerShowingSoon = MutableStateFlow2;
        ReadonlyStateFlow readonlyStateFlow2 = new ReadonlyStateFlow(MutableStateFlow2);
        this.primaryBouncerShowingSoon = readonlyStateFlow2;
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._primaryBouncerStartingToHide = MutableStateFlow3;
        ReadonlyStateFlow readonlyStateFlow3 = new ReadonlyStateFlow(MutableStateFlow3);
        this.primaryBouncerStartingToHide = readonlyStateFlow3;
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(null);
        this._primaryBouncerDisappearAnimation = MutableStateFlow4;
        final ReadonlyStateFlow readonlyStateFlow4 = new ReadonlyStateFlow(MutableStateFlow4);
        this.primaryBouncerStartingDisappearAnimation = readonlyStateFlow4;
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(bool);
        this._primaryBouncerScrimmed = MutableStateFlow5;
        ReadonlyStateFlow readonlyStateFlow5 = new ReadonlyStateFlow(MutableStateFlow5);
        this.primaryBouncerScrimmed = readonlyStateFlow5;
        StateFlowImpl MutableStateFlow6 = StateFlowKt.MutableStateFlow(Float.valueOf(1.0f));
        this._panelExpansionAmount = MutableStateFlow6;
        final ReadonlyStateFlow readonlyStateFlow6 = new ReadonlyStateFlow(MutableStateFlow6);
        this.panelExpansionAmount = readonlyStateFlow6;
        StateFlowImpl MutableStateFlow7 = StateFlowKt.MutableStateFlow(null);
        this._keyguardPosition = MutableStateFlow7;
        ReadonlyStateFlow readonlyStateFlow7 = new ReadonlyStateFlow(MutableStateFlow7);
        this.keyguardPosition = readonlyStateFlow7;
        StateFlowImpl MutableStateFlow8 = StateFlowKt.MutableStateFlow(null);
        this._isBackButtonEnabled = MutableStateFlow8;
        ReadonlyStateFlow readonlyStateFlow8 = new ReadonlyStateFlow(MutableStateFlow8);
        this.isBackButtonEnabled = readonlyStateFlow8;
        StateFlowImpl MutableStateFlow9 = StateFlowKt.MutableStateFlow(null);
        this._keyguardAuthenticatedBiometrics = MutableStateFlow9;
        this.keyguardAuthenticatedBiometrics = new ReadonlyStateFlow(MutableStateFlow9);
        this.keyguardAuthenticatedPrimaryAuth = new ReadonlySharedFlow(SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7));
        this.userRequestedBouncerWhenAlreadyAuthenticated = new ReadonlySharedFlow(SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7));
        StateFlowImpl MutableStateFlow10 = StateFlowKt.MutableStateFlow(null);
        this._showMessage = MutableStateFlow10;
        final ReadonlyStateFlow readonlyStateFlow9 = new ReadonlyStateFlow(MutableStateFlow10);
        this.showMessage = readonlyStateFlow9;
        StateFlowImpl MutableStateFlow11 = StateFlowKt.MutableStateFlow(KeyguardSecurityModel.SecurityMode.Invalid);
        this._lastShownSecurityMode = MutableStateFlow11;
        final ReadonlyStateFlow readonlyStateFlow10 = new ReadonlyStateFlow(MutableStateFlow11);
        this.lastShownSecurityMode = readonlyStateFlow10;
        StateFlowImpl MutableStateFlow12 = StateFlowKt.MutableStateFlow(bool);
        this._resourceUpdateRequests = MutableStateFlow12;
        ReadonlyStateFlow readonlyStateFlow11 = new ReadonlyStateFlow(MutableStateFlow12);
        this.resourceUpdateRequests = readonlyStateFlow11;
        StateFlowImpl MutableStateFlow13 = StateFlowKt.MutableStateFlow(bool);
        this._alternateBouncerVisible = MutableStateFlow13;
        ReadonlyStateFlow readonlyStateFlow12 = new ReadonlyStateFlow(MutableStateFlow13);
        this.alternateBouncerVisible = readonlyStateFlow12;
        ReadonlyStateFlow readonlyStateFlow13 = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(bool));
        if (Build.IS_DEBUGGABLE) {
            FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(DiffableKt.logDiffsForTable((Flow) readonlyStateFlow, tableLogBuffer, "", "PrimaryBouncerShow", false), new KeyguardBouncerRepositoryImpl$setUpLogging$1(2, null), 0), coroutineScope);
            FlowKt.launchIn(DiffableKt.logDiffsForTable((Flow) readonlyStateFlow2, tableLogBuffer, "", "PrimaryBouncerShowingSoon", false), coroutineScope);
            FlowKt.launchIn(DiffableKt.logDiffsForTable((Flow) readonlyStateFlow3, tableLogBuffer, "", "PrimaryBouncerStartingToHide", false), coroutineScope);
            final int i = 0;
            FlowKt.launchIn(DiffableKt.logDiffsForTable(new Flow() { // from class: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r6 instanceof com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = (com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = new com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L48
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            java.lang.Runnable r5 = (java.lang.Runnable) r5
                            if (r5 == 0) goto L38
                            r5 = r3
                            goto L39
                        L38:
                            r5 = 0
                        L39:
                            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L48
                            return r1
                        L48:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i) {
                        case 0:
                            ((ReadonlyStateFlow) readonlyStateFlow4).collect(new AnonymousClass2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 1:
                            ((ReadonlyStateFlow) readonlyStateFlow4).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$2$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 2:
                            ((ReadonlyStateFlow) readonlyStateFlow4).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$4$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 3:
                            ((ReadonlyStateFlow) readonlyStateFlow4).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$5$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        default:
                            Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) readonlyStateFlow4).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$3$2(flowCollector), continuation);
                            return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }
            }, tableLogBuffer, "", "PrimaryBouncerStartingDisappearAnimation", false), coroutineScope);
            FlowKt.launchIn(DiffableKt.logDiffsForTable((Flow) readonlyStateFlow5, tableLogBuffer, "", "PrimaryBouncerScrimmed", false), coroutineScope);
            final int i2 = 1;
            FlowKt.launchIn(DiffableKt.logDiffsForTable(new Flow() { // from class: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r6 instanceof com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = (com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = new com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L48
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            java.lang.Runnable r5 = (java.lang.Runnable) r5
                            if (r5 == 0) goto L38
                            r5 = r3
                            goto L39
                        L38:
                            r5 = 0
                        L39:
                            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L48
                            return r1
                        L48:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i2) {
                        case 0:
                            ((ReadonlyStateFlow) readonlyStateFlow6).collect(new AnonymousClass2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 1:
                            ((ReadonlyStateFlow) readonlyStateFlow6).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$2$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 2:
                            ((ReadonlyStateFlow) readonlyStateFlow6).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$4$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 3:
                            ((ReadonlyStateFlow) readonlyStateFlow6).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$5$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        default:
                            Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) readonlyStateFlow6).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$3$2(flowCollector), continuation);
                            return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }
            }, tableLogBuffer, "", "PanelExpansionAmountMillis", -1), coroutineScope);
            final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(readonlyStateFlow7);
            final int i3 = 4;
            FlowKt.launchIn(DiffableKt.logDiffsForTable(new Flow() { // from class: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r6 instanceof com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = (com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = new com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L48
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            java.lang.Runnable r5 = (java.lang.Runnable) r5
                            if (r5 == 0) goto L38
                            r5 = r3
                            goto L39
                        L38:
                            r5 = 0
                        L39:
                            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L48
                            return r1
                        L48:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i3) {
                        case 0:
                            ((ReadonlyStateFlow) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new AnonymousClass2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 1:
                            ((ReadonlyStateFlow) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$2$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 2:
                            ((ReadonlyStateFlow) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$4$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 3:
                            ((ReadonlyStateFlow) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$5$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        default:
                            Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$3$2(flowCollector), continuation);
                            return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }
            }, tableLogBuffer, "", "KeyguardPosition", -1), coroutineScope);
            FlowKt.launchIn(DiffableKt.logDiffsForTable((Flow) new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(readonlyStateFlow8), tableLogBuffer, "", "IsBackButtonEnabled", false), coroutineScope);
            final int i4 = 2;
            FlowKt.launchIn(DiffableKt.logDiffsForTable(new Flow() { // from class: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r6 instanceof com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = (com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = new com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L48
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            java.lang.Runnable r5 = (java.lang.Runnable) r5
                            if (r5 == 0) goto L38
                            r5 = r3
                            goto L39
                        L38:
                            r5 = 0
                        L39:
                            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L48
                            return r1
                        L48:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i4) {
                        case 0:
                            ((ReadonlyStateFlow) readonlyStateFlow9).collect(new AnonymousClass2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 1:
                            ((ReadonlyStateFlow) readonlyStateFlow9).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$2$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 2:
                            ((ReadonlyStateFlow) readonlyStateFlow9).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$4$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 3:
                            ((ReadonlyStateFlow) readonlyStateFlow9).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$5$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        default:
                            Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) readonlyStateFlow9).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$3$2(flowCollector), continuation);
                            return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }
            }, tableLogBuffer, "", "ShowMessage", (String) null), coroutineScope);
            FlowKt.launchIn(DiffableKt.logDiffsForTable((Flow) readonlyStateFlow11, tableLogBuffer, "", "ResourceUpdateRequests", false), coroutineScope);
            FlowKt.launchIn(DiffableKt.logDiffsForTable((Flow) readonlyStateFlow13, tableLogBuffer, "", "IsAlternateBouncerUIAvailable", false), coroutineScope);
            FlowKt.launchIn(DiffableKt.logDiffsForTable((Flow) readonlyStateFlow12, tableLogBuffer, "", "AlternateBouncerVisible", false), coroutineScope);
            final int i5 = 3;
            FlowKt.launchIn(DiffableKt.logDiffsForTable(new Flow() { // from class: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r6 instanceof com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = (com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1 r0 = new com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L48
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            java.lang.Runnable r5 = (java.lang.Runnable) r5
                            if (r5 == 0) goto L38
                            r5 = r3
                            goto L39
                        L38:
                            r5 = 0
                        L39:
                            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L48
                            return r1
                        L48:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i5) {
                        case 0:
                            ((ReadonlyStateFlow) readonlyStateFlow10).collect(new AnonymousClass2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 1:
                            ((ReadonlyStateFlow) readonlyStateFlow10).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$2$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 2:
                            ((ReadonlyStateFlow) readonlyStateFlow10).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$4$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        case 3:
                            ((ReadonlyStateFlow) readonlyStateFlow10).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$5$2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        default:
                            Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) readonlyStateFlow10).collect(new KeyguardBouncerRepositoryImpl$setUpLogging$$inlined$map$3$2(flowCollector), continuation);
                            return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }
            }, tableLogBuffer, "", "lastShownSecurityMode", (String) null), coroutineScope);
        }
    }

    public final void setAlternateVisible(boolean z) {
        StateFlowImpl stateFlowImpl = this._alternateBouncerVisible;
        if (z && !((Boolean) stateFlowImpl.getValue()).booleanValue()) {
            ((SystemClockImpl) this.clock).getClass();
            android.os.SystemClock.uptimeMillis();
        }
        AuthContainerView$$ExternalSyntheticOutline0.m(z, stateFlowImpl, null);
    }
}
