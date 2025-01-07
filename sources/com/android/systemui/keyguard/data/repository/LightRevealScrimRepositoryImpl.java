package com.android.systemui.keyguard.data.repository;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import androidx.core.animation.ValueAnimator;
import com.android.keyguard.logging.ScrimLogger;
import com.android.systemui.power.data.repository.PowerRepositoryImpl;
import com.android.systemui.statusbar.CircleReveal;
import com.android.systemui.statusbar.PowerButtonReveal;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LightRevealScrimRepositoryImpl implements LightRevealScrimRepository {
    public static final String TAG;
    public final Context context;
    public final LightRevealScrimRepositoryImpl$special$$inlined$map$1 faceRevealEffect;
    public final LightRevealScrimRepositoryImpl$special$$inlined$map$1 fingerprintRevealEffect;
    public final ChannelFlowTransformLatest nonBiometricRevealEffect;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 powerButtonRevealEffect;
    public final CallbackFlowBuilder revealAmount;
    public final ValueAnimator revealAmountAnimator;
    public final Flow revealEffect;
    public final ScrimLogger scrimLogger;
    public final LightRevealScrimRepositoryImpl$special$$inlined$map$1 tapRevealEffect;
    public Boolean willBeOrIsRevealed;

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(LightRevealScrimRepository.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        TAG = simpleName;
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1] */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1] */
    /* JADX WARN: Type inference failed for: r4v7, types: [com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1] */
    public LightRevealScrimRepositoryImpl(KeyguardRepositoryImpl keyguardRepositoryImpl, Context context, PowerRepositoryImpl powerRepositoryImpl, ScrimLogger scrimLogger) {
        final int i = 2;
        this.context = context;
        this.scrimLogger = scrimLogger;
        this.powerButtonRevealEffect = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new PowerButtonReveal(context.getResources().getDimensionPixelSize(R.dimen.physical_power_button_center_screen_location_y)));
        final ReadonlyStateFlow readonlyStateFlow = keyguardRepositoryImpl.lastDozeTapToWakePosition;
        final int i2 = 0;
        this.tapRevealEffect = new Flow() { // from class: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ LightRevealScrimRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = lightRevealScrimRepositoryImpl;
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1
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
                        android.graphics.Point r5 = (android.graphics.Point) r5
                        if (r5 == 0) goto L3d
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl r6 = r4.this$0
                        com.android.systemui.statusbar.CircleReveal r5 = com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl.access$constructCircleRevealFromPoint(r6, r5)
                        goto L3f
                    L3d:
                        com.android.systemui.statusbar.LiftReveal r5 = com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryKt.DEFAULT_REVEAL_EFFECT
                    L3f:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((StateFlow) readonlyStateFlow).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((StateFlow) readonlyStateFlow).collect(new LightRevealScrimRepositoryImpl$special$$inlined$map$3$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = readonlyStateFlow.collect(new LightRevealScrimRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final Flow flow = keyguardRepositoryImpl.fingerprintSensorLocation;
        this.fingerprintRevealEffect = new Flow() { // from class: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ LightRevealScrimRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = lightRevealScrimRepositoryImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1
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
                        android.graphics.Point r5 = (android.graphics.Point) r5
                        if (r5 == 0) goto L3d
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl r6 = r4.this$0
                        com.android.systemui.statusbar.CircleReveal r5 = com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl.access$constructCircleRevealFromPoint(r6, r5)
                        goto L3f
                    L3d:
                        com.android.systemui.statusbar.LiftReveal r5 = com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryKt.DEFAULT_REVEAL_EFFECT
                    L3f:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((StateFlow) flow).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((StateFlow) flow).collect(new LightRevealScrimRepositoryImpl$special$$inlined$map$3$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = flow.collect(new LightRevealScrimRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final StateFlow stateFlow = keyguardRepositoryImpl.faceSensorLocation;
        final int i3 = 1;
        this.faceRevealEffect = new Flow() { // from class: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ LightRevealScrimRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = lightRevealScrimRepositoryImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1$2$1
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
                        android.graphics.Point r5 = (android.graphics.Point) r5
                        if (r5 == 0) goto L3d
                        com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl r6 = r4.this$0
                        com.android.systemui.statusbar.CircleReveal r5 = com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl.access$constructCircleRevealFromPoint(r6, r5)
                        goto L3f
                    L3d:
                        com.android.systemui.statusbar.LiftReveal r5 = com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryKt.DEFAULT_REVEAL_EFFECT
                    L3f:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((StateFlow) stateFlow).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((StateFlow) stateFlow).collect(new LightRevealScrimRepositoryImpl$special$$inlined$map$3$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = stateFlow.collect(new LightRevealScrimRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        this.nonBiometricRevealEffect = FlowKt.transformLatest(powerRepositoryImpl.wakefulness, new LightRevealScrimRepositoryImpl$special$$inlined$flatMapLatest$1(this, null));
        this.revealAmountAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.revealAmount = FlowKt.callbackFlow(new LightRevealScrimRepositoryImpl$revealAmount$1(this, null));
        this.revealEffect = FlowKt.distinctUntilChanged(FlowKt.transformLatest(keyguardRepositoryImpl.biometricUnlockState, new LightRevealScrimRepositoryImpl$special$$inlined$flatMapLatest$2(this, null)));
    }

    public static final CircleReveal access$constructCircleRevealFromPoint(LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl, Point point) {
        Display display = lightRevealScrimRepositoryImpl.context.getDisplay();
        if (display == null) {
            throw new IllegalStateException("Required value was null.");
        }
        int i = point.x;
        return new CircleReveal(i, point.y, Math.max(Math.max(i, display.getWidth() - point.x), Math.max(point.y, display.getHeight() - point.y)));
    }
}
