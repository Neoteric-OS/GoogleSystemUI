package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import android.util.Log;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionInfo;
import com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.domain.interactor.PowerInteractor$special$$inlined$map$1;
import com.android.systemui.util.kotlin.FlowKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TransitionInteractor {
    public final KeyguardState fromState;
    public final KeyguardInteractor keyguardInteractor;
    public final KeyguardOcclusionInteractor keyguardOcclusionInteractor;
    public final CoroutineDispatcher mainDispatcher;
    public final String name;
    public final PowerInteractor powerInteractor;
    public final KeyguardTransitionInteractor transitionInteractor;

    public TransitionInteractor(KeyguardState keyguardState, KeyguardTransitionInteractor keyguardTransitionInteractor, CoroutineDispatcher coroutineDispatcher, PowerInteractor powerInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor, KeyguardInteractor keyguardInteractor) {
        this.fromState = keyguardState;
        this.transitionInteractor = keyguardTransitionInteractor;
        this.powerInteractor = powerInteractor;
        this.keyguardOcclusionInteractor = keyguardOcclusionInteractor;
        this.keyguardInteractor = keyguardInteractor;
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        this.name = simpleName == null ? "UnknownTransitionInteractor" : simpleName;
    }

    public static Object startTransitionTo$default(TransitionInteractor transitionInteractor, KeyguardState keyguardState, ValueAnimator valueAnimator, TransitionModeOnCanceled transitionModeOnCanceled, String str, Continuation continuation, int i) {
        if ((i & 2) != 0) {
            valueAnimator = transitionInteractor.getDefaultAnimatorForTransitionsToState(keyguardState);
        }
        ValueAnimator valueAnimator2 = valueAnimator;
        if ((i & 4) != 0) {
            transitionModeOnCanceled = TransitionModeOnCanceled.LAST_VALUE;
        }
        TransitionModeOnCanceled transitionModeOnCanceled2 = transitionModeOnCanceled;
        if ((i & 8) != 0) {
            str = "";
        }
        transitionInteractor.getClass();
        keyguardState.checkValidState();
        KeyguardState keyguardState2 = ((TransitionInfo) transitionInteractor.getInternalTransitionInteractor().currentTransitionInfoInternal.getValue()).to;
        KeyguardState keyguardState3 = transitionInteractor.fromState;
        String str2 = transitionInteractor.name;
        if (keyguardState3 == keyguardState2) {
            return transitionInteractor.getTransitionRepository().startTransition(new TransitionInfo(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str2, StringsKt__StringsJVMKt.isBlank(str) ? "" : DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("(", str, ")")), keyguardState3, keyguardState, valueAnimator2, transitionModeOnCanceled2), continuation);
        }
        Log.e(str2, "Ignoring startTransition: This interactor asked to transition from " + keyguardState3 + " -> " + keyguardState + ", but we last transitioned to " + ((TransitionInfo) transitionInteractor.getInternalTransitionInteractor().currentTransitionInfoInternal.getValue()).to + ", not " + keyguardState3 + ". This should never happen - check currentTransitionInfoInternal or use filterRelevantKeyguardState before starting transitions.");
        return null;
    }

    public abstract ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState);

    public abstract InternalKeyguardTransitionInteractor getInternalTransitionInteractor();

    public abstract KeyguardTransitionRepositoryImpl getTransitionRepository();

    public final Object listenForSleepTransition(Function1 function1, SuspendLambda suspendLambda) {
        final PowerInteractor$special$$inlined$map$1 powerInteractor$special$$inlined$map$1 = this.powerInteractor.isAsleep;
        Object collect = FlowKt.sample(new TransitionInteractor$filterRelevantKeyguardState$$inlined$filter$1(new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L46
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        r6 = r5
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        boolean r6 = r6.booleanValue()
                        if (r6 == 0) goto L46
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L46
                        return r1
                    L46:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect2 = PowerInteractor$special$$inlined$map$1.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect2 == CoroutineSingletons.COROUTINE_SUSPENDED ? collect2 : Unit.INSTANCE;
            }
        }, this), this.transitionInteractor.startedKeyguardTransitionStep).collect(new TransitionInteractor$listenForSleepTransition$$inlined$map$1$2(new FlowCollector() { // from class: com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForSleepTransition$5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                TransitionInteractor transitionInteractor = TransitionInteractor.this;
                Object startTransitionTo$default = TransitionInteractor.startTransitionTo$default(transitionInteractor, (KeyguardState) ((StateFlowImpl) transitionInteractor.keyguardInteractor.asleepKeyguardState.$$delegate_0).getValue(), null, (TransitionModeOnCanceled) obj, "Sleep transition triggered", continuation, 2);
                return startTransitionTo$default == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default : Unit.INSTANCE;
            }
        }, function1), suspendLambda);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (collect != coroutineSingletons) {
            collect = unit;
        }
        return collect == coroutineSingletons ? collect : unit;
    }

    public final void listenForTransitionToCamera(CoroutineScope coroutineScope, KeyguardInteractor keyguardInteractor) {
        BuildersKt.launch$default(coroutineScope, null, null, new TransitionInteractor$listenForTransitionToCamera$1(this, keyguardInteractor, null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object maybeHandleInsecurePowerGesture(kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.android.systemui.keyguard.domain.interactor.TransitionInteractor$maybeHandleInsecurePowerGesture$1
            if (r0 == 0) goto L14
            r0 = r9
            com.android.systemui.keyguard.domain.interactor.TransitionInteractor$maybeHandleInsecurePowerGesture$1 r0 = (com.android.systemui.keyguard.domain.interactor.TransitionInteractor$maybeHandleInsecurePowerGesture$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            com.android.systemui.keyguard.domain.interactor.TransitionInteractor$maybeHandleInsecurePowerGesture$1 r0 = new com.android.systemui.keyguard.domain.interactor.TransitionInteractor$maybeHandleInsecurePowerGesture$1
            r0.<init>(r8, r9)
            goto L12
        L1a:
            java.lang.Object r9 = r6.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L39
            if (r1 == r3) goto L35
            if (r1 != r2) goto L2d
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lb6
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L35:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8f
        L39:
            kotlin.ResultKt.throwOnFailure(r9)
            com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor r9 = r8.keyguardOcclusionInteractor
            com.android.systemui.power.domain.interactor.PowerInteractor r1 = r9.powerInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r1 = r1.detailedWakefulness
            kotlinx.coroutines.flow.MutableStateFlow r1 = r1.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r1 = (kotlinx.coroutines.flow.StateFlowImpl) r1
            java.lang.Object r1 = r1.getValue()
            com.android.systemui.power.shared.model.WakefulnessModel r1 = (com.android.systemui.power.shared.model.WakefulnessModel) r1
            boolean r1 = r1.powerButtonLaunchGestureTriggered
            if (r1 == 0) goto L69
            com.android.systemui.keyguard.shared.model.KeyguardState$Companion r1 = com.android.systemui.keyguard.shared.model.KeyguardState.Companion
            com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r4 = r9.internalTransitionInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r4 = r4.currentTransitionInfoInternal
            java.lang.Object r4 = r4.getValue()
            com.android.systemui.keyguard.shared.model.TransitionInfo r4 = (com.android.systemui.keyguard.shared.model.TransitionInfo) r4
            com.android.systemui.keyguard.shared.model.KeyguardState r4 = r4.to
            r1.getClass()
            boolean r1 = com.android.systemui.keyguard.shared.model.KeyguardState.Companion.deviceIsAsleepInState(r4)
            if (r1 == 0) goto L69
            r1 = 1
            goto L6a
        L69:
            r1 = 0
        L6a:
            if (r1 == 0) goto Lb9
            com.android.systemui.keyguard.domain.interactor.KeyguardInteractor r1 = r8.keyguardInteractor
            kotlinx.coroutines.flow.MutableStateFlow r1 = r1.isKeyguardDismissible
            kotlinx.coroutines.flow.StateFlowImpl r1 = (kotlinx.coroutines.flow.StateFlowImpl) r1
            java.lang.Object r1 = r1.getValue()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L92
            com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
            r6.label = r3
            java.lang.String r5 = "Power button gesture while keyguard is dismissible"
            r7 = 6
            r3 = 0
            r4 = 0
            r1 = r8
            java.lang.Object r8 = startTransitionTo$default(r1, r2, r3, r4, r5, r6, r7)
            if (r8 != r0) goto L8f
            return r0
        L8f:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            return r8
        L92:
            kotlinx.coroutines.flow.ReadonlyStateFlow r9 = r9.occludingActivityWillDismissKeyguard
            kotlinx.coroutines.flow.MutableStateFlow r9 = r9.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r9 = (kotlinx.coroutines.flow.StateFlowImpl) r9
            java.lang.Object r9 = r9.getValue()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto Lb9
            com.android.systemui.keyguard.shared.model.KeyguardState r9 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
            r6.label = r2
            java.lang.String r5 = "Power button gesture on dismissable keyguard"
            r7 = 6
            r3 = 0
            r4 = 0
            r1 = r8
            r2 = r9
            java.lang.Object r8 = startTransitionTo$default(r1, r2, r3, r4, r5, r6, r7)
            if (r8 != r0) goto Lb6
            return r0
        Lb6:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            return r8
        Lb9:
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.TransitionInteractor.maybeHandleInsecurePowerGesture(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public abstract void start();
}
