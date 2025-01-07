package com.android.systemui.keyguard.domain.interactor;

import android.util.Log;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionInfo;
import com.android.systemui.util.kotlin.Utils;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardEnabledInteractor {
    public final BiometricSettingsRepositoryImpl biometricSettingsRepository;
    public final ReadonlyStateFlow isKeyguardEnabled;
    public final KeyguardRepositoryImpl repository;
    public final KeyguardEnabledInteractor$special$$inlined$map$1 showKeyguardWhenReenabled;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardEnabledInteractor$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ InternalKeyguardTransitionInteractor $internalTransitionInteractor;
        final /* synthetic */ KeyguardDismissTransitionInteractor $keyguardDismissTransitionInteractor;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, KeyguardDismissTransitionInteractor keyguardDismissTransitionInteractor, Continuation continuation) {
            super(2, continuation);
            this.$internalTransitionInteractor = internalKeyguardTransitionInteractor;
            this.$keyguardDismissTransitionInteractor = keyguardDismissTransitionInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyguardEnabledInteractor.this.new AnonymousClass1(this.$internalTransitionInteractor, this.$keyguardDismissTransitionInteractor, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Utils.Companion companion = Utils.Companion;
                KeyguardEnabledInteractor keyguardEnabledInteractor = KeyguardEnabledInteractor.this;
                SafeFlow sample = companion.sample(new KeyguardEnabledInteractor$special$$inlined$map$1(keyguardEnabledInteractor.repository.isKeyguardEnabled, 1), keyguardEnabledInteractor.biometricSettingsRepository.isCurrentUserInLockdown, this.$internalTransitionInteractor.currentTransitionInfoInternal);
                final KeyguardDismissTransitionInteractor keyguardDismissTransitionInteractor = this.$keyguardDismissTransitionInteractor;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardEnabledInteractor.1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Triple triple = (Triple) obj2;
                        boolean booleanValue = ((Boolean) triple.component2()).booleanValue();
                        if (((TransitionInfo) triple.component3()).to != KeyguardState.GONE && !booleanValue) {
                            KeyguardDismissTransitionInteractor keyguardDismissTransitionInteractor2 = KeyguardDismissTransitionInteractor.this;
                            keyguardDismissTransitionInteractor2.getClass();
                            String str = KeyguardDismissTransitionInteractor.TAG;
                            Log.d(str, "#startDismissKeyguardTransition(reason=keyguard disabled)");
                            KeyguardState keyguardState = ((TransitionInfo) keyguardDismissTransitionInteractor2.repository.currentTransitionInfoInternal.getValue()).to;
                            int ordinal = keyguardState.ordinal();
                            if (ordinal == 1) {
                                FromDozingTransitionInteractor fromDozingTransitionInteractor = keyguardDismissTransitionInteractor2.fromDozingTransitionInteractor;
                                fromDozingTransitionInteractor.getClass();
                                BuildersKt.launch$default(fromDozingTransitionInteractor.scope, null, null, new FromDozingTransitionInteractor$dismissFromDozing$1(fromDozingTransitionInteractor, null), 3);
                            } else if (ordinal == 9) {
                                Log.i(str, "Already transitioning to GONE; ignoring startDismissKeyguardTransition.");
                            } else if (ordinal == 11) {
                                keyguardDismissTransitionInteractor2.fromOccludedTransitionInteractor.dismissFromOccluded();
                            } else if (ordinal == 4) {
                                FromAodTransitionInteractor fromAodTransitionInteractor = keyguardDismissTransitionInteractor2.fromAodTransitionInteractor;
                                fromAodTransitionInteractor.getClass();
                                CoroutineTracingKt.launch$default(fromAodTransitionInteractor.scope, null, new FromAodTransitionInteractor$dismissAod$1(fromAodTransitionInteractor, null), 6);
                            } else if (ordinal == 5) {
                                FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor = keyguardDismissTransitionInteractor2.fromAlternateBouncerTransitionInteractor;
                                fromAlternateBouncerTransitionInteractor.getClass();
                                BuildersKt.launch$default(fromAlternateBouncerTransitionInteractor.scope, null, null, new FromAlternateBouncerTransitionInteractor$dismissAlternateBouncer$1(fromAlternateBouncerTransitionInteractor, null), 3);
                            } else if (ordinal == 6) {
                                FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor = keyguardDismissTransitionInteractor2.fromPrimaryBouncerTransitionInteractor;
                                fromPrimaryBouncerTransitionInteractor.getClass();
                                BuildersKt.launch$default(fromPrimaryBouncerTransitionInteractor.scope, null, null, new FromPrimaryBouncerTransitionInteractor$dismissPrimaryBouncer$1(fromPrimaryBouncerTransitionInteractor, null), 3);
                            } else if (ordinal != 7) {
                                Log.e(str, "We don't know how to dismiss keyguard from state " + keyguardState + ".");
                            } else {
                                keyguardDismissTransitionInteractor2.fromLockscreenTransitionInteractor.dismissKeyguard();
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (sample.collect(flowCollector, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public KeyguardEnabledInteractor(CoroutineScope coroutineScope, KeyguardRepositoryImpl keyguardRepositoryImpl, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, KeyguardDismissTransitionInteractor keyguardDismissTransitionInteractor, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor) {
        this.repository = keyguardRepositoryImpl;
        this.biometricSettingsRepository = biometricSettingsRepositoryImpl;
        this.isKeyguardEnabled = keyguardRepositoryImpl.isKeyguardEnabled;
        new KeyguardEnabledInteractor$special$$inlined$map$1(Utils.Companion.sample(new KeyguardEnabledInteractor$special$$inlined$map$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(keyguardRepositoryImpl.isKeyguardEnabled, new KeyguardEnabledInteractor$showKeyguardWhenReenabled$1(2, null), 0), 2), internalKeyguardTransitionInteractor.currentTransitionInfoInternal, biometricSettingsRepositoryImpl.isCurrentUserInLockdown), 0);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(internalKeyguardTransitionInteractor, keyguardDismissTransitionInteractor, null), 3);
    }
}
