package com.android.systemui.keyguard.data.repository;

import android.animation.ValueAnimator;
import android.os.Trace;
import android.util.Log;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionInfo;
import com.android.systemui.keyguard.shared.model.TransitionState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardTransitionRepositoryImpl {
    public final StateFlowImpl _currentTransitionInfo;
    public final MutexImpl _currentTransitionMutex;
    public final SharedFlowImpl _transitions;
    public final ReadonlyStateFlow currentTransitionInfoInternal;
    public ValueAnimator lastAnimator;
    public TransitionStep lastStep;
    public final CoroutineDispatcher mainDispatcher;
    public final Flow transitions;
    public UUID updateTransitionId;

    public KeyguardTransitionRepositoryImpl(CoroutineDispatcher coroutineDispatcher) {
        this.mainDispatcher = coroutineDispatcher;
        SharedFlowImpl MutableSharedFlow = SharedFlowKt.MutableSharedFlow(2, 20, BufferOverflow.DROP_OLDEST);
        this._transitions = MutableSharedFlow;
        this.transitions = FlowKt.distinctUntilChanged(new ReadonlySharedFlow(MutableSharedFlow));
        this.lastStep = new TransitionStep(31);
        this._currentTransitionMutex = MutexKt.Mutex$default();
        KeyguardState keyguardState = KeyguardState.OFF;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(new TransitionInfo("", keyguardState, keyguardState, null));
        this._currentTransitionInfo = MutableStateFlow;
        this.currentTransitionInfoInternal = new ReadonlyStateFlow(MutableStateFlow);
        TransitionState transitionState = TransitionState.STARTED;
        emitTransition(new TransitionStep(16), false);
    }

    public static final void access$updateTransitionInternal(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, UUID uuid, float f, TransitionState transitionState) {
        if (!Intrinsics.areEqual(keyguardTransitionRepositoryImpl.updateTransitionId, uuid)) {
            Log.e("KeyguardTransitionRepository", "Attempting to update with old/invalid transitionId: " + uuid);
        } else {
            if (transitionState == TransitionState.FINISHED || transitionState == TransitionState.CANCELED) {
                keyguardTransitionRepositoryImpl.updateTransitionId = null;
            }
            keyguardTransitionRepositoryImpl.emitTransition(TransitionStep.copy$default(keyguardTransitionRepositoryImpl.lastStep, f, transitionState, 19), true);
        }
    }

    public final void emitTransition(TransitionStep transitionStep, boolean z) {
        TransitionState transitionState = TransitionState.RUNNING;
        TransitionState transitionState2 = transitionStep.transitionState;
        if (transitionState2 != transitionState) {
            String str = z ? " (manual)" : "";
            String str2 = "Transition: " + transitionStep.from + " -> " + transitionStep.to + str;
            int hashCode = str2.hashCode();
            int ordinal = transitionState2.ordinal();
            if (ordinal == 0) {
                Trace.beginAsyncSection(str2, hashCode);
            } else if (ordinal == 2) {
                Trace.endAsyncSection(str2, hashCode);
            } else if (ordinal == 3) {
                Trace.endAsyncSection(str2, hashCode);
            }
            Log.i("KeyguardTransitionRepository", transitionState2.name() + " transition: " + transitionStep + str);
        }
        this._transitions.tryEmit(transitionStep);
        this.lastStep = transitionStep;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0093 A[PHI: r8
      0x0093: PHI (r8v9 java.lang.Object) = (r8v8 java.lang.Object), (r8v1 java.lang.Object) binds: [B:19:0x0090, B:12:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0092 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object startTransition(com.android.systemui.keyguard.shared.model.TransitionInfo r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$1 r0 = (com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$1 r0 = new com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 3
            r4 = 1
            if (r2 == 0) goto L4e
            if (r2 == r4) goto L41
            r6 = 2
            if (r2 == r6) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r8)
            goto L93
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            java.lang.Object r6 = r0.L$1
            com.android.systemui.keyguard.shared.model.TransitionInfo r6 = (com.android.systemui.keyguard.shared.model.TransitionInfo) r6
            java.lang.Object r7 = r0.L$0
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r7 = (com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L7e
        L41:
            java.lang.Object r6 = r0.L$1
            r7 = r6
            com.android.systemui.keyguard.shared.model.TransitionInfo r7 = (com.android.systemui.keyguard.shared.model.TransitionInfo) r7
            java.lang.Object r6 = r0.L$0
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r6 = (com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L78
        L4e:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.flow.StateFlowImpl r8 = r6._currentTransitionInfo
            r8.setValue(r7)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r2 = "(Internal) Setting current transition info: "
            r8.<init>(r2)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            java.lang.String r2 = "KeyguardTransitionRepository"
            android.util.Log.d(r2, r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            kotlinx.coroutines.sync.MutexImpl r8 = r6._currentTransitionMutex
            java.lang.Object r8 = r8.lock(r0)
            if (r8 != r1) goto L78
            return r1
        L78:
            r6.getClass()
            r5 = r7
            r7 = r6
            r6 = r5
        L7e:
            kotlinx.coroutines.CoroutineDispatcher r8 = r7.mainDispatcher
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$2 r2 = new com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$2
            r4 = 0
            r2.<init>(r7, r6, r4)
            r0.L$0 = r4
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r8 = com.android.app.tracing.coroutines.CoroutineTracingKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L93
            return r1
        L93:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl.startTransition(com.android.systemui.keyguard.shared.model.TransitionInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object updateTransition(java.util.UUID r11, float r12, com.android.systemui.keyguard.shared.model.TransitionState r13, kotlin.coroutines.Continuation r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$updateTransition$1
            if (r0 == 0) goto L13
            r0 = r14
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$updateTransition$1 r0 = (com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$updateTransition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$updateTransition$1 r0 = new com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$updateTransition$1
            r0.<init>(r10, r14)
        L18:
            java.lang.Object r14 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4a
            if (r2 == r4) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r14)
            goto L79
        L2a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L32:
            float r12 = r0.F$0
            java.lang.Object r10 = r0.L$2
            r13 = r10
            com.android.systemui.keyguard.shared.model.TransitionState r13 = (com.android.systemui.keyguard.shared.model.TransitionState) r13
            java.lang.Object r10 = r0.L$1
            r11 = r10
            java.util.UUID r11 = (java.util.UUID) r11
            java.lang.Object r10 = r0.L$0
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r10 = (com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl) r10
            kotlin.ResultKt.throwOnFailure(r14)
        L45:
            r5 = r10
            r6 = r11
            r7 = r12
            r8 = r13
            goto L60
        L4a:
            kotlin.ResultKt.throwOnFailure(r14)
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r13
            r0.F$0 = r12
            r0.label = r4
            kotlinx.coroutines.sync.MutexImpl r14 = r10._currentTransitionMutex
            java.lang.Object r14 = r14.lock(r0)
            if (r14 != r1) goto L45
            return r1
        L60:
            kotlinx.coroutines.CoroutineDispatcher r10 = r5.mainDispatcher
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$updateTransition$2 r11 = new com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$updateTransition$2
            r9 = 0
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)
            r12 = 0
            r0.L$0 = r12
            r0.L$1 = r12
            r0.L$2 = r12
            r0.label = r3
            java.lang.Object r10 = com.android.app.tracing.coroutines.CoroutineTracingKt.withContext(r10, r11, r0)
            if (r10 != r1) goto L79
            return r1
        L79:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl.updateTransition(java.util.UUID, float, com.android.systemui.keyguard.shared.model.TransitionState, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
