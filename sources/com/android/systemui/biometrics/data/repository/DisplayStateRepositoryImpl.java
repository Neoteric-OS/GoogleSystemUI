package com.android.systemui.biometrics.data.repository;

import android.R;
import android.content.Context;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.util.Size;
import android.view.Display;
import android.view.DisplayInfo;
import com.android.systemui.biometrics.shared.model.DisplayRotation;
import com.android.systemui.display.data.repository.DeviceStateRepositoryImpl;
import com.android.systemui.display.data.repository.DisplayRepository;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$1;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayStateRepositoryImpl implements DisplayStateRepository {
    public final Context context;
    public final ReadonlyStateFlow currentDisplayInfo;
    public final ReadonlyStateFlow currentDisplaySize;
    public final ReadonlyStateFlow currentRotation;
    public final ReadonlyStateFlow isInRearDisplayMode;
    public final ReadonlyStateFlow isLargeScreen;
    public final boolean isReverseDefaultRotation;

    public DisplayStateRepositoryImpl(CoroutineScope coroutineScope, Context context, DeviceStateRepositoryImpl deviceStateRepositoryImpl, DisplayRepository displayRepository) {
        this.context = context;
        this.isReverseDefaultRotation = context.getResources().getBoolean(R.bool.config_setColorTransformAcceleratedPerLayer);
        final ReadonlyStateFlow readonlyStateFlow = deviceStateRepositoryImpl.state;
        final int i = 0;
        Flow flow = new Flow() { // from class: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.display.data.repository.DeviceStateRepository$DeviceState r5 = (com.android.systemui.display.data.repository.DeviceStateRepository$DeviceState) r5
                        com.android.systemui.display.data.repository.DeviceStateRepository$DeviceState r6 = com.android.systemui.display.data.repository.DeviceStateRepository$DeviceState.REAR_DISPLAY
                        if (r5 != r6) goto L3a
                        r5 = r3
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    default:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new DisplayStateRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        };
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        Boolean bool = Boolean.FALSE;
        this.isInRearDisplayMode = FlowKt.stateIn(flow, coroutineScope, startedEagerly, bool);
        final DisplayRepositoryImpl$special$$inlined$map$1 displayRepositoryImpl$special$$inlined$map$1 = ((DisplayRepositoryImpl) displayRepository).displayChangeEvent;
        final int i2 = 2;
        Flow flow2 = new Flow() { // from class: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ DisplayStateRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, DisplayStateRepositoryImpl displayStateRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = displayStateRepositoryImpl;
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1 r0 = (com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1 r0 = new com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.view.DisplayInfo r5 = (android.view.DisplayInfo) r5
                        int r5 = r5.rotation
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl r6 = r4.this$0
                        com.android.systemui.biometrics.shared.model.DisplayRotation r5 = r6.rotationToDisplayRotation(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        ((ReadonlyStateFlow) displayRepositoryImpl$special$$inlined$map$1).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    case 1:
                        ((ReadonlyStateFlow) displayRepositoryImpl$special$$inlined$map$1).collect(new DisplayStateRepositoryImpl$special$$inlined$map$5$2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((DisplayRepositoryImpl$special$$inlined$map$1) displayRepositoryImpl$special$$inlined$map$1).collect(new DisplayStateRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        };
        DisplayInfo displayInfo = new DisplayInfo();
        Display display = context.getDisplay();
        if (display != null) {
            display.getDisplayInfo(displayInfo);
        }
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(flow2, coroutineScope, startedEagerly, displayInfo);
        final int i3 = 0;
        this.currentRotation = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ DisplayStateRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, DisplayStateRepositoryImpl displayStateRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = displayStateRepositoryImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1 r0 = (com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1 r0 = new com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.view.DisplayInfo r5 = (android.view.DisplayInfo) r5
                        int r5 = r5.rotation
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl r6 = r4.this$0
                        com.android.systemui.biometrics.shared.model.DisplayRotation r5 = r6.rotationToDisplayRotation(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        ((ReadonlyStateFlow) stateIn).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    case 1:
                        ((ReadonlyStateFlow) stateIn).collect(new DisplayStateRepositoryImpl$special$$inlined$map$5$2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((DisplayRepositoryImpl$special$$inlined$map$1) stateIn).collect(new DisplayStateRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), rotationToDisplayRotation(((DisplayInfo) stateIn.getValue()).rotation));
        final int i4 = 1;
        this.currentDisplaySize = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.display.data.repository.DeviceStateRepository$DeviceState r5 = (com.android.systemui.display.data.repository.DeviceStateRepository$DeviceState) r5
                        com.android.systemui.display.data.repository.DeviceStateRepository$DeviceState r6 = com.android.systemui.display.data.repository.DeviceStateRepository$DeviceState.REAR_DISPLAY
                        if (r5 != r6) goto L3a
                        r5 = r3
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    default:
                        ((ReadonlyStateFlow) stateIn).collect(new DisplayStateRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new Size(((DisplayInfo) stateIn.getValue()).getNaturalWidth(), ((DisplayInfo) stateIn.getValue()).getNaturalHeight()));
        final int i5 = 1;
        this.isLargeScreen = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ DisplayStateRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, DisplayStateRepositoryImpl displayStateRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = displayStateRepositoryImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1 r0 = (com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1 r0 = new com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.view.DisplayInfo r5 = (android.view.DisplayInfo) r5
                        int r5 = r5.rotation
                        com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl r6 = r4.this$0
                        com.android.systemui.biometrics.shared.model.DisplayRotation r5 = r6.rotationToDisplayRotation(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i5) {
                    case 0:
                        ((ReadonlyStateFlow) stateIn).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    case 1:
                        ((ReadonlyStateFlow) stateIn).collect(new DisplayStateRepositoryImpl$special$$inlined$map$5$2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((DisplayRepositoryImpl$special$$inlined$map$1) stateIn).collect(new DisplayStateRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineScope, startedEagerly, bool);
    }

    public final DisplayRotation rotationToDisplayRotation(int i) {
        if (this.isReverseDefaultRotation) {
            i = (i + 1) % 4;
        }
        if (i == 0) {
            return DisplayRotation.ROTATION_0;
        }
        if (i == 1) {
            return DisplayRotation.ROTATION_90;
        }
        if (i == 2) {
            return DisplayRotation.ROTATION_180;
        }
        if (i == 3) {
            return DisplayRotation.ROTATION_270;
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid DisplayRotation value: "));
    }
}
