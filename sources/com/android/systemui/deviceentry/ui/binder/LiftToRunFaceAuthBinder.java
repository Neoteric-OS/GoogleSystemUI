package com.android.systemui.deviceentry.ui.binder;

import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import com.android.keyguard.ActiveUnlockConfig;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.CoreStartable;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.util.Assert;
import com.android.systemui.util.sensors.AsyncSensorManager;
import java.io.PrintWriter;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LiftToRunFaceAuthBinder implements CoreStartable {
    public final AsyncSensorManager asyncSensorManager;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 bouncerShowing;
    public final DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor;
    public final StateFlowImpl isListening;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 listenForPickupSensor;
    public final LiftToRunFaceAuthBinder$listener$1 listener;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 onAwakeKeyguard;
    public final PackageManager packageManager;
    public Sensor pickupSensor;
    public final CoroutineScope scope;
    public final LiftToRunFaceAuthBinder$special$$inlined$map$1 stoppedListening;

    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$listener$1] */
    public LiftToRunFaceAuthBinder(CoroutineScope coroutineScope, PackageManager packageManager, AsyncSensorManager asyncSensorManager, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardInteractor keyguardInteractor, PrimaryBouncerInteractor primaryBouncerInteractor, AlternateBouncerInteractor alternateBouncerInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, PowerInteractor powerInteractor) {
        this.scope = coroutineScope;
        this.packageManager = packageManager;
        this.asyncSensorManager = asyncSensorManager;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.deviceEntryFaceAuthInteractor = deviceEntryFaceAuthInteractor;
        final StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.isListening = MutableStateFlow;
        final int i = 1;
        final Flow flow = new Flow() { // from class: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((LiftToRunFaceAuthBinder$special$$inlined$map$1) MutableStateFlow).collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    default:
                        ((StateFlowImpl) MutableStateFlow).collect(new LiftToRunFaceAuthBinder$special$$inlined$filterNot$1$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        };
        final int i2 = 0;
        this.listenForPickupSensor = FlowKt.combine(new Flow() { // from class: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((LiftToRunFaceAuthBinder$special$$inlined$map$1) flow).collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    default:
                        ((StateFlowImpl) flow).collect(new LiftToRunFaceAuthBinder$special$$inlined$filterNot$1$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        }, new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(primaryBouncerInteractor.isShowing, alternateBouncerInteractor.isVisible, new LiftToRunFaceAuthBinder$bouncerShowing$1(3, null)), new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(powerInteractor.isInteractive, keyguardInteractor.isKeyguardVisible, new LiftToRunFaceAuthBinder$onAwakeKeyguard$1(3, null)), new LiftToRunFaceAuthBinder$listenForPickupSensor$1(this, null));
        this.listener = new TriggerEventListener() { // from class: com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder$listener$1
            @Override // android.hardware.TriggerEventListener
            public final void onTrigger(TriggerEvent triggerEvent) {
                Assert.isMainThread();
                LiftToRunFaceAuthBinder.this.deviceEntryFaceAuthInteractor.onDeviceLifted();
                LiftToRunFaceAuthBinder.this.keyguardUpdateMonitor.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.WAKE, "KeyguardLiftController");
                StateFlowImpl stateFlowImpl = LiftToRunFaceAuthBinder.this.isListening;
                Boolean bool = Boolean.FALSE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
            }
        };
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("LiftToRunFaceAuthBinder:");
        printWriter.println("  pickupSensor: " + this.pickupSensor);
        printWriter.println("  isListening: " + this.isListening.getValue());
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.packageManager.hasSystemFeature("android.hardware.biometrics.face")) {
            this.pickupSensor = this.asyncSensorManager.getDefaultSensor(25);
            BuildersKt.launch$default(this.scope, null, null, new LiftToRunFaceAuthBinder$init$1(this, null), 3);
        }
    }
}
