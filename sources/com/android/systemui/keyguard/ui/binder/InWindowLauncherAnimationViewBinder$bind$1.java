package com.android.systemui.keyguard.ui.binder;

import android.graphics.Rect;
import android.util.Log;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.ui.view.InWindowLauncherUnlockAnimationManager;
import com.android.systemui.keyguard.ui.view.InWindowLauncherUnlockAnimationManagerKt;
import com.android.systemui.keyguard.ui.viewmodel.InWindowLauncherAnimationViewModel;
import com.android.systemui.shared.system.smartspace.ILauncherUnlockAnimationController$Stub$Proxy;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class InWindowLauncherAnimationViewBinder$bind$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ InWindowLauncherUnlockAnimationManager $inWindowLauncherUnlockAnimationManager;
    final /* synthetic */ InWindowLauncherAnimationViewModel $viewModel;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.InWindowLauncherAnimationViewBinder$bind$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ InWindowLauncherUnlockAnimationManager $inWindowLauncherUnlockAnimationManager;
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass1(InWindowLauncherUnlockAnimationManager inWindowLauncherUnlockAnimationManager, int i) {
            this.$r8$classId = i;
            this.$inWindowLauncherUnlockAnimationManager = inWindowLauncherUnlockAnimationManager;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            Unit unit = Unit.INSTANCE;
            InWindowLauncherUnlockAnimationManager inWindowLauncherUnlockAnimationManager = this.$inWindowLauncherUnlockAnimationManager;
            switch (this.$r8$classId) {
                case 0:
                    if (!((Boolean) obj).booleanValue()) {
                        boolean z = inWindowLauncherUnlockAnimationManager.preparedForUnlock && !((Boolean) ((StateFlowImpl) inWindowLauncherUnlockAnimationManager.interactor.startedUnlockAnimation.$$delegate_0).getValue()).booleanValue();
                        Float f = inWindowLauncherUnlockAnimationManager.manualUnlockAmount;
                        boolean z2 = (f == null || Intrinsics.areEqual(f, 1.0f)) ? false : true;
                        if (z) {
                            Log.e(InWindowLauncherUnlockAnimationManagerKt.TAG, "Called prepareForUnlock(), but not playUnlockAnimation(). Failing-safe by calling setUnlockAmount(1f)");
                            inWindowLauncherUnlockAnimationManager.preparedForUnlock = false;
                            ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy = inWindowLauncherUnlockAnimationManager.launcherAnimationController;
                            if (iLauncherUnlockAnimationController$Stub$Proxy != null) {
                                inWindowLauncherUnlockAnimationManager.manualUnlockAmount = Float.valueOf(1.0f);
                                iLauncherUnlockAnimationController$Stub$Proxy.setUnlockAmount(true);
                            }
                        } else if (z2) {
                            Log.e(InWindowLauncherUnlockAnimationManagerKt.TAG, "Unlock has ended, but manual unlock amount != 1f. Failing-safe by calling setUnlockAmount(1f)");
                            inWindowLauncherUnlockAnimationManager.preparedForUnlock = false;
                            ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy2 = inWindowLauncherUnlockAnimationManager.launcherAnimationController;
                            if (iLauncherUnlockAnimationController$Stub$Proxy2 != null) {
                                inWindowLauncherUnlockAnimationManager.manualUnlockAmount = Float.valueOf(1.0f);
                                iLauncherUnlockAnimationController$Stub$Proxy2.setUnlockAmount(true);
                            }
                        }
                        inWindowLauncherUnlockAnimationManager.manualUnlockAmount = null;
                        break;
                    } else {
                        ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy3 = inWindowLauncherUnlockAnimationManager.launcherAnimationController;
                        if (iLauncherUnlockAnimationController$Stub$Proxy3 != null && !inWindowLauncherUnlockAnimationManager.preparedForUnlock) {
                            inWindowLauncherUnlockAnimationManager.preparedForUnlock = true;
                            inWindowLauncherUnlockAnimationManager.manualUnlockAmount = null;
                            iLauncherUnlockAnimationController$Stub$Proxy3.prepareForUnlock(false, 0, new Rect());
                            break;
                        }
                    }
                    break;
                default:
                    if (!((Boolean) obj).booleanValue()) {
                        StateFlowImpl stateFlowImpl = inWindowLauncherUnlockAnimationManager.interactor.repository.startedUnlockAnimation;
                        Boolean bool = Boolean.FALSE;
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, bool);
                        break;
                    } else {
                        int i = InWindowLauncherUnlockAnimationManager.$r8$clinit;
                        if (inWindowLauncherUnlockAnimationManager.preparedForUnlock) {
                            ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy4 = inWindowLauncherUnlockAnimationManager.launcherAnimationController;
                            if (iLauncherUnlockAnimationController$Stub$Proxy4 != null) {
                                iLauncherUnlockAnimationController$Stub$Proxy4.playUnlockAnimation(633L, 100L);
                                StateFlowImpl stateFlowImpl2 = inWindowLauncherUnlockAnimationManager.interactor.repository.startedUnlockAnimation;
                                Boolean bool2 = Boolean.TRUE;
                                stateFlowImpl2.getClass();
                                stateFlowImpl2.updateState(null, bool2);
                            }
                        } else {
                            Log.e(InWindowLauncherUnlockAnimationManagerKt.TAG, "Attempted to call playUnlockAnimation() before prepareToUnlock().");
                        }
                        inWindowLauncherUnlockAnimationManager.preparedForUnlock = false;
                        break;
                    }
            }
            return unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InWindowLauncherAnimationViewBinder$bind$1(InWindowLauncherAnimationViewModel inWindowLauncherAnimationViewModel, InWindowLauncherUnlockAnimationManager inWindowLauncherUnlockAnimationManager, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = inWindowLauncherAnimationViewModel;
        this.$inWindowLauncherUnlockAnimationManager = inWindowLauncherUnlockAnimationManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new InWindowLauncherAnimationViewBinder$bind$1(this.$viewModel, this.$inWindowLauncherUnlockAnimationManager, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((InWindowLauncherAnimationViewBinder$bind$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
        }
        ResultKt.throwOnFailure(obj);
        ReadonlyStateFlow readonlyStateFlow = this.$viewModel.shouldPrepareForInWindowAnimation;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$inWindowLauncherUnlockAnimationManager, 0);
        this.label = 1;
        ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(anonymousClass1, this);
        return coroutineSingletons;
    }
}
