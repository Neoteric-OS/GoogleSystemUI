package com.android.systemui.keyguard.ui.viewmodel;

import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import com.android.systemui.accessibility.domain.interactor.AccessibilityInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntrySourceInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryUdfpsInteractor;
import com.android.systemui.keyguard.domain.interactor.BurnInInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition;
import com.android.systemui.shade.domain.interactor.BaseShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryIconViewModel {
    public final ChannelFlowTransformLatest accessibilityDelegateHint;
    public final AccessibilityInteractor accessibilityInteractor;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 alphaMultiplierFromShadeExpansion;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 animatedBurnInOffsets;
    public final Flow burnInOffsets;
    public final DeviceEntrySourceInteractor deviceEntrySourceInteractor;
    public final ReadonlyStateFlow deviceEntryViewAlpha;
    public final MutableSharedFlow dozeAmount;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 iconType;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isInteractive;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isLongPressEnabled;
    public final ReadonlyStateFlow isUdfpsSupported;
    public final ChannelFlowTransformLatest isUnlocked;
    public final Flow isVisible;
    public final KeyguardInteractor keyguardInteractor;
    public final Lazy keyguardViewController;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 nonAnimatedBurnInOffsets;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 qsProgress;
    public final CoroutineScope scope;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 shadeExpansion;
    public final DeviceEntryIconViewModel$special$$inlined$map$1 showingAlternateBouncer;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 transitionAlpha;
    public final ReadonlyStateFlow udfpsLocation;
    public final ReadonlyStateFlow useBackgroundProtection;
    public final IntEvaluator intEvaluator = new IntEvaluator();
    public final FloatEvaluator floatEvaluator = new FloatEvaluator();

    /* JADX WARN: Type inference failed for: r9v2, types: [com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1] */
    public DeviceEntryIconViewModel(Set set, BurnInInteractor burnInInteractor, ShadeInteractor shadeInteractor, DeviceEntryUdfpsInteractor deviceEntryUdfpsInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardInteractor keyguardInteractor, Lazy lazy, DeviceEntryInteractor deviceEntryInteractor, DeviceEntrySourceInteractor deviceEntrySourceInteractor, AccessibilityInteractor accessibilityInteractor, CoroutineScope coroutineScope) {
        this.keyguardInteractor = keyguardInteractor;
        this.keyguardViewController = lazy;
        this.deviceEntrySourceInteractor = deviceEntrySourceInteractor;
        this.accessibilityInteractor = accessibilityInteractor;
        this.scope = coroutineScope;
        this.isUdfpsSupported = deviceEntryUdfpsInteractor.isUdfpsSupported;
        this.udfpsLocation = FlowKt.stateIn(deviceEntryUdfpsInteractor.udfpsLocation, coroutineScope, SharingStarted.Companion.Eagerly, null);
        final ReadonlyStateFlow readonlyStateFlow = keyguardTransitionInteractor.startedKeyguardTransitionStep;
        final int i = 0;
        this.showingAlternateBouncer = new Flow() { // from class: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.TransitionStep r5 = (com.android.systemui.keyguard.shared.model.TransitionStep) r5
                        com.android.systemui.keyguard.shared.model.KeyguardState r5 = r5.to
                        com.android.systemui.keyguard.shared.model.KeyguardState r6 = com.android.systemui.keyguard.shared.model.KeyguardState.ALTERNATE_BOUNCER
                        if (r5 != r6) goto L3c
                        r5 = r3
                        goto L3d
                    L3c:
                        r5 = 0
                    L3d:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    default:
                        readonlyStateFlow.collect(new DeviceEntryIconViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        BaseShadeInteractor baseShadeInteractor = ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor;
        this.qsProgress = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DeviceEntryIconViewModel$qsProgress$1(2, null), baseShadeInteractor.getQsExpansion());
        this.shadeExpansion = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DeviceEntryIconViewModel$shadeExpansion$1(2, null), baseShadeInteractor.getShadeExpansion());
        Set set2 = set;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10));
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            arrayList.add(((DeviceEntryIconTransition) it.next()).getDeviceEntryParentViewAlpha());
        }
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DeviceEntryIconViewModel$transitionAlpha$2(this, keyguardTransitionInteractor, null), FlowKt.shareIn(FlowKt.merge(arrayList), this.scope, SharingStarted.Companion.WhileSubscribed$default(3), 0));
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$12 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DeviceEntryIconViewModel$alphaMultiplierFromShadeExpansion$2(2, null), FlowKt.combine(this.showingAlternateBouncer, this.shadeExpansion, this.qsProgress, new DeviceEntryIconViewModel$alphaMultiplierFromShadeExpansion$1(4, null)));
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(burnInInteractor.deviceEntryIconXOffset, burnInInteractor.deviceEntryIconYOffset, burnInInteractor.udfpsProgress, new DeviceEntryIconViewModel$nonAnimatedBurnInOffsets$1(4, null));
        this.nonAnimatedBurnInOffsets = combine;
        this.animatedBurnInOffsets = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(combine, keyguardTransitionInteractor.getTransitionValueFlow(KeyguardState.AOD), new DeviceEntryIconViewModel$animatedBurnInOffsets$1(this, null));
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$12, new DeviceEntryIconViewModel$deviceEntryViewAlpha$1(3, null)), this.scope, SharingStarted.Companion.WhileSubscribed$default(3), Float.valueOf(0.0f));
        this.deviceEntryViewAlpha = stateIn;
        this.useBackgroundProtection = this.isUdfpsSupported;
        this.burnInOffsets = FlowKt.distinctUntilChanged(FlowKt.transformLatest(deviceEntryUdfpsInteractor.isUdfpsEnrolledAndEnabled, new DeviceEntryIconViewModel$special$$inlined$flatMapLatest$1(null, keyguardTransitionInteractor, shadeInteractor, this)));
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(deviceEntryUdfpsInteractor.isListeningForUdfps, FlowKt.transformLatest(this.keyguardInteractor.isKeyguardDismissible, new DeviceEntryIconViewModel$special$$inlined$flatMapLatest$2(3, null)), new DeviceEntryIconViewModel$iconType$1(3, null));
        this.iconType = flowKt__ZipKt$combine$$inlined$unsafeFlow$1;
        final int i2 = 1;
        this.isVisible = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.TransitionStep r5 = (com.android.systemui.keyguard.shared.model.TransitionStep) r5
                        com.android.systemui.keyguard.shared.model.KeyguardState r5 = r5.to
                        com.android.systemui.keyguard.shared.model.KeyguardState r6 = com.android.systemui.keyguard.shared.model.KeyguardState.ALTERNATE_BOUNCER
                        if (r5 != r6) goto L3c
                        r5 = r3
                        goto L3d
                    L3c:
                        r5 = 0
                    L3d:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new DeviceEntryIconViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        });
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$12 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, this.isUdfpsSupported, new DeviceEntryIconViewModel$isInteractive$1(3, null));
        this.accessibilityDelegateHint = FlowKt.transformLatest(this.accessibilityInteractor.isEnabled, new DeviceEntryIconViewModel$special$$inlined$flatMapLatest$3(this, null));
        this.isLongPressEnabled = flowKt__ZipKt$combine$$inlined$unsafeFlow$12;
    }

    public final Object onUserInteraction(SuspendLambda suspendLambda) {
        ((StatusBarKeyguardViewManager) this.keyguardViewController.get()).showPrimaryBouncer(true);
        SharedFlowImpl sharedFlowImpl = this.deviceEntrySourceInteractor.attemptEnterDeviceFromDeviceEntryIcon;
        Unit unit = Unit.INSTANCE;
        Object emit = sharedFlowImpl.emit(unit, suspendLambda);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (emit != coroutineSingletons) {
            emit = unit;
        }
        return emit == coroutineSingletons ? emit : unit;
    }
}
