package com.android.systemui.display.domain.interactor;

import android.companion.virtual.VirtualDeviceManager;
import com.android.systemui.display.data.repository.DeviceStateRepositoryImpl;
import com.android.systemui.display.data.repository.DisplayRepository;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$2;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectedDisplayInteractorImpl {
    public final Flow concurrentDisplaysInProgress;
    public final ConnectedDisplayInteractorImpl$special$$inlined$map$2 connectedDisplayAddition;
    public final Flow connectedDisplayState;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 pendingDisplay;
    public final VirtualDeviceManager virtualDeviceManager;

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2] */
    public ConnectedDisplayInteractorImpl(VirtualDeviceManager virtualDeviceManager, KeyguardRepositoryImpl keyguardRepositoryImpl, DisplayRepository displayRepository, DeviceStateRepositoryImpl deviceStateRepositoryImpl, CoroutineDispatcher coroutineDispatcher) {
        this.virtualDeviceManager = virtualDeviceManager;
        DisplayRepositoryImpl displayRepositoryImpl = (DisplayRepositoryImpl) displayRepository;
        final ReadonlyStateFlow readonlyStateFlow = displayRepositoryImpl.displays;
        final int i = 0;
        this.connectedDisplayState = FlowKt.distinctUntilChanged(FlowKt.flowOn(new Flow() { // from class: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ConnectedDisplayInteractorImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ConnectedDisplayInteractorImpl connectedDisplayInteractorImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = connectedDisplayInteractorImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto Lce
                    L28:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r10)
                        throw r9
                    L30:
                        kotlin.ResultKt.throwOnFailure(r11)
                        java.util.Set r10 = (java.util.Set) r10
                        java.lang.Iterable r10 = (java.lang.Iterable) r10
                        java.util.ArrayList r11 = new java.util.ArrayList
                        r11.<init>()
                        java.util.Iterator r2 = r10.iterator()
                    L40:
                        boolean r4 = r2.hasNext()
                        r5 = 2
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl r6 = r9.this$0
                        if (r4 == 0) goto L5d
                        java.lang.Object r4 = r2.next()
                        r7 = r4
                        android.view.Display r7 = (android.view.Display) r7
                        r6.getClass()
                        int r6 = r7.getType()
                        if (r6 != r5) goto L40
                        r11.add(r4)
                        goto L40
                    L5d:
                        java.util.ArrayList r2 = new java.util.ArrayList
                        r2.<init>()
                        java.util.Iterator r4 = r11.iterator()
                    L66:
                        boolean r7 = r4.hasNext()
                        if (r7 == 0) goto L81
                        java.lang.Object r7 = r4.next()
                        r8 = r7
                        android.view.Display r8 = (android.view.Display) r8
                        r6.getClass()
                        int r8 = r8.getFlags()
                        r8 = r8 & r5
                        if (r8 == 0) goto L66
                        r2.add(r7)
                        goto L66
                    L81:
                        java.util.ArrayList r4 = new java.util.ArrayList
                        r4.<init>()
                        java.util.Iterator r10 = r10.iterator()
                    L8a:
                        boolean r5 = r10.hasNext()
                        if (r5 == 0) goto La9
                        java.lang.Object r5 = r10.next()
                        r7 = r5
                        android.view.Display r7 = (android.view.Display) r7
                        android.companion.virtual.VirtualDeviceManager r8 = r6.virtualDeviceManager
                        if (r8 == 0) goto L8a
                        int r7 = r7.getDisplayId()
                        boolean r7 = r8.isVirtualDeviceOwnedMirrorDisplay(r7)
                        if (r7 == 0) goto L8a
                        r4.add(r5)
                        goto L8a
                    La9:
                        boolean r10 = r11.isEmpty()
                        if (r10 == 0) goto Lb8
                        boolean r10 = r4.isEmpty()
                        if (r10 == 0) goto Lb8
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State r10 = com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State.DISCONNECTED
                        goto Lc3
                    Lb8:
                        boolean r10 = r2.isEmpty()
                        if (r10 != 0) goto Lc1
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State r10 = com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State.CONNECTED_SECURE
                        goto Lc3
                    Lc1:
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State r10 = com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State.CONNECTED
                    Lc3:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r9 = r9.$this_unsafeFlow
                        java.lang.Object r9 = r9.emit(r10, r0)
                        if (r9 != r1) goto Lce
                        return r1
                    Lce:
                        kotlin.Unit r9 = kotlin.Unit.INSTANCE
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((DisplayRepositoryImpl$special$$inlined$map$2) readonlyStateFlow).collect(new ConnectedDisplayInteractorImpl$special$$inlined$filter$1$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineDispatcher));
        final DisplayRepositoryImpl$special$$inlined$map$2 displayRepositoryImpl$special$$inlined$map$2 = displayRepositoryImpl.displayAdditionEvent;
        final int i2 = 1;
        final Flow flowOn = FlowKt.flowOn(new Flow() { // from class: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ConnectedDisplayInteractorImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ConnectedDisplayInteractorImpl connectedDisplayInteractorImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = connectedDisplayInteractorImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r11 instanceof com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto Lce
                    L28:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r10)
                        throw r9
                    L30:
                        kotlin.ResultKt.throwOnFailure(r11)
                        java.util.Set r10 = (java.util.Set) r10
                        java.lang.Iterable r10 = (java.lang.Iterable) r10
                        java.util.ArrayList r11 = new java.util.ArrayList
                        r11.<init>()
                        java.util.Iterator r2 = r10.iterator()
                    L40:
                        boolean r4 = r2.hasNext()
                        r5 = 2
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl r6 = r9.this$0
                        if (r4 == 0) goto L5d
                        java.lang.Object r4 = r2.next()
                        r7 = r4
                        android.view.Display r7 = (android.view.Display) r7
                        r6.getClass()
                        int r6 = r7.getType()
                        if (r6 != r5) goto L40
                        r11.add(r4)
                        goto L40
                    L5d:
                        java.util.ArrayList r2 = new java.util.ArrayList
                        r2.<init>()
                        java.util.Iterator r4 = r11.iterator()
                    L66:
                        boolean r7 = r4.hasNext()
                        if (r7 == 0) goto L81
                        java.lang.Object r7 = r4.next()
                        r8 = r7
                        android.view.Display r8 = (android.view.Display) r8
                        r6.getClass()
                        int r8 = r8.getFlags()
                        r8 = r8 & r5
                        if (r8 == 0) goto L66
                        r2.add(r7)
                        goto L66
                    L81:
                        java.util.ArrayList r4 = new java.util.ArrayList
                        r4.<init>()
                        java.util.Iterator r10 = r10.iterator()
                    L8a:
                        boolean r5 = r10.hasNext()
                        if (r5 == 0) goto La9
                        java.lang.Object r5 = r10.next()
                        r7 = r5
                        android.view.Display r7 = (android.view.Display) r7
                        android.companion.virtual.VirtualDeviceManager r8 = r6.virtualDeviceManager
                        if (r8 == 0) goto L8a
                        int r7 = r7.getDisplayId()
                        boolean r7 = r8.isVirtualDeviceOwnedMirrorDisplay(r7)
                        if (r7 == 0) goto L8a
                        r4.add(r5)
                        goto L8a
                    La9:
                        boolean r10 = r11.isEmpty()
                        if (r10 == 0) goto Lb8
                        boolean r10 = r4.isEmpty()
                        if (r10 == 0) goto Lb8
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State r10 = com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State.DISCONNECTED
                        goto Lc3
                    Lb8:
                        boolean r10 = r2.isEmpty()
                        if (r10 != 0) goto Lc1
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State r10 = com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State.CONNECTED_SECURE
                        goto Lc3
                    Lc1:
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State r10 = com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State.CONNECTED
                    Lc3:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r9 = r9.$this_unsafeFlow
                        java.lang.Object r9 = r9.emit(r10, r0)
                        if (r9 != r1) goto Lce
                        return r1
                    Lce:
                        kotlin.Unit r9 = kotlin.Unit.INSTANCE
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        ((ReadonlyStateFlow) displayRepositoryImpl$special$$inlined$map$2).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((DisplayRepositoryImpl$special$$inlined$map$2) displayRepositoryImpl$special$$inlined$map$2).collect(new ConnectedDisplayInteractorImpl$special$$inlined$filter$1$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineDispatcher);
        final int i3 = 0;
        this.connectedDisplayAddition = new Flow() { // from class: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2$1
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
                        goto L41
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        android.view.Display r6 = (android.view.Display) r6
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L41
                        return r1
                    L41:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = flowOn.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((StateFlow) flowOn).collect(new ConnectedDisplayInteractorImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        this.pendingDisplay = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(displayRepositoryImpl.pendingDisplay, keyguardRepositoryImpl.isKeyguardShowing, new ConnectedDisplayInteractorImpl$pendingDisplay$1(this, null));
        final ReadonlyStateFlow readonlyStateFlow2 = deviceStateRepositoryImpl.state;
        final int i4 = 1;
        this.concurrentDisplaysInProgress = FlowKt.flowOn(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2$2$1
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
                        goto L41
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        android.view.Display r6 = (android.view.Display) r6
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L41
                        return r1
                    L41:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = readonlyStateFlow2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((StateFlow) readonlyStateFlow2).collect(new ConnectedDisplayInteractorImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }), coroutineDispatcher);
    }
}
