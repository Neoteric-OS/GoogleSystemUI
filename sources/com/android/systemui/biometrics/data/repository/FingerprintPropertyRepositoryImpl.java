package com.android.systemui.biometrics.data.repository;

import android.hardware.biometrics.SensorLocationInternal;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import com.android.systemui.biometrics.shared.model.FingerprintSensorTypeKt;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.Collections;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FingerprintPropertyRepositoryImpl implements FingerprintPropertyRepository {
    public static final FingerprintSensorPropertiesInternal DEFAULT_PROPS;
    public static final FingerprintSensorPropertiesInternal UNINITIALIZED_PROPS;
    public final CoroutineDispatcher backgroundDispatcher;
    public final FingerprintManager fingerprintManager;
    public final Flow propertiesInitialized;
    public final ReadonlyStateFlow props;
    public final FingerprintPropertyRepositoryImpl$special$$inlined$map$1 sensorId;
    public final FingerprintPropertyRepositoryImpl$special$$inlined$map$1 sensorLocations;
    public final ReadonlyStateFlow sensorType;
    public final FingerprintPropertyRepositoryImpl$special$$inlined$map$1 strength;

    static {
        EmptyList emptyList = EmptyList.INSTANCE;
        SensorLocationInternal sensorLocationInternal = SensorLocationInternal.DEFAULT;
        UNINITIALIZED_PROPS = new FingerprintSensorPropertiesInternal(-2, 0, 0, emptyList, 0, false, true, Collections.singletonList(sensorLocationInternal));
        DEFAULT_PROPS = new FingerprintSensorPropertiesInternal(-1, 0, 0, emptyList, 0, false, true, Collections.singletonList(sensorLocationInternal));
    }

    public FingerprintPropertyRepositoryImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, FingerprintManager fingerprintManager) {
        this.backgroundDispatcher = coroutineDispatcher;
        this.fingerprintManager = fingerprintManager;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new FingerprintPropertyRepositoryImpl$props$1(this, null)), coroutineScope, SharingStarted.Companion.Eagerly, UNINITIALIZED_PROPS);
        this.props = stateIn;
        final FingerprintPropertyRepositoryImpl$special$$inlined$map$1 fingerprintPropertyRepositoryImpl$special$$inlined$map$1 = new FingerprintPropertyRepositoryImpl$special$$inlined$map$1(stateIn, 0);
        final FingerprintPropertyRepositoryImpl$special$$inlined$map$1 fingerprintPropertyRepositoryImpl$special$$inlined$map$12 = new FingerprintPropertyRepositoryImpl$special$$inlined$map$1(stateIn, 1);
        this.strength = fingerprintPropertyRepositoryImpl$special$$inlined$map$12;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(new FingerprintPropertyRepositoryImpl$special$$inlined$map$1(stateIn, 2), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), FingerprintSensorTypeKt.toSensorType(((FingerprintSensorPropertiesInternal) stateIn.getValue()).sensorType));
        this.sensorType = stateIn2;
        final FingerprintPropertyRepositoryImpl$special$$inlined$map$1 fingerprintPropertyRepositoryImpl$special$$inlined$map$13 = new FingerprintPropertyRepositoryImpl$special$$inlined$map$1(stateIn, 3);
        this.sensorLocations = fingerprintPropertyRepositoryImpl$special$$inlined$map$13;
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FingerprintPropertyRepositoryImpl$propertiesInitialized$2(this, null), new FingerprintPropertyRepositoryImpl$special$$inlined$map$1(stateIn, 4));
        final int i = 0;
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$12 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FingerprintPropertyRepositoryImpl$propertiesInitialized$4(this, null), new Flow() { // from class: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1 r0 = (com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1 r0 = new com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1
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
                        java.lang.Number r6 = (java.lang.Number) r6
                        r6.intValue()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$1).collect(new FingerprintPropertyRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$1).collect(new FingerprintPropertyRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
        final int i2 = 1;
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$13 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FingerprintPropertyRepositoryImpl$propertiesInitialized$6(this, null), new Flow() { // from class: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1 r0 = (com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1 r0 = new com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1
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
                        java.lang.Number r6 = (java.lang.Number) r6
                        r6.intValue()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$13).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$13).collect(new FingerprintPropertyRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$13).collect(new FingerprintPropertyRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$14 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FingerprintPropertyRepositoryImpl$propertiesInitialized$8(this, null), new FingerprintPropertyRepositoryImpl$special$$inlined$map$1(stateIn2, 5));
        final int i3 = 2;
        this.propertiesInitialized = FlowKt.distinctUntilChanged(FlowKt.combine(flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$12, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$13, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$14, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FingerprintPropertyRepositoryImpl$propertiesInitialized$10(this, null), new Flow() { // from class: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1 r0 = (com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1 r0 = new com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6$2$1
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
                        java.lang.Number r6 = (java.lang.Number) r6
                        r6.intValue()
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r3, r0)
                        if (r5 != r1) goto L44
                        return r1
                    L44:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$special$$inlined$map$6.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$12).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$12).collect(new FingerprintPropertyRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((FingerprintPropertyRepositoryImpl$special$$inlined$map$1) fingerprintPropertyRepositoryImpl$special$$inlined$map$12).collect(new FingerprintPropertyRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }), new FingerprintPropertyRepositoryImpl$propertiesInitialized$11(null)));
    }
}
