package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.shared.model.BiometricModality;
import com.android.systemui.biometrics.ui.viewmodel.PromptMessage;
import com.android.systemui.biometrics.ui.viewmodel.PromptViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptViewModel$showTemporaryError$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $authenticateAfterError;
    final /* synthetic */ BiometricModality $failedModality;
    final /* synthetic */ boolean $hapticFeedback;
    final /* synthetic */ String $message;
    final /* synthetic */ String $messageAfterError;
    final /* synthetic */ Function2 $suppressIf;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PromptViewModel this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$showTemporaryError$3$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $authenticateAfterError;
        final /* synthetic */ String $messageAfterError;
        int label;
        final /* synthetic */ PromptViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, PromptViewModel promptViewModel, String str, Continuation continuation) {
            super(2, continuation);
            this.$authenticateAfterError = z;
            this.this$0 = promptViewModel;
            this.$messageAfterError = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$authenticateAfterError, this.this$0, this.$messageAfterError, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(2000L, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return unit;
                }
                ResultKt.throwOnFailure(obj);
            }
            if (this.$authenticateAfterError) {
                PromptViewModel.showAuthenticating$default(this.this$0, this.$messageAfterError, 2);
            } else {
                PromptViewModel promptViewModel = this.this$0;
                String str = this.$messageAfterError;
                this.label = 2;
                promptViewModel.showHelp(str);
                if (unit == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            return unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptViewModel$showTemporaryError$3(PromptViewModel promptViewModel, boolean z, BiometricModality biometricModality, Function2 function2, String str, boolean z2, String str2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = promptViewModel;
        this.$hapticFeedback = z;
        this.$failedModality = biometricModality;
        this.$suppressIf = function2;
        this.$message = str;
        this.$authenticateAfterError = z2;
        this.$messageAfterError = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        PromptViewModel$showTemporaryError$3 promptViewModel$showTemporaryError$3 = new PromptViewModel$showTemporaryError$3(this.this$0, this.$hapticFeedback, this.$failedModality, this.$suppressIf, this.$message, this.$authenticateAfterError, this.$messageAfterError, continuation);
        promptViewModel$showTemporaryError$3.L$0 = obj;
        return promptViewModel$showTemporaryError$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        PromptViewModel$showTemporaryError$3 promptViewModel$showTemporaryError$3 = (PromptViewModel$showTemporaryError$3) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        promptViewModel$showTemporaryError$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        boolean z = ((PromptAuthState) this.this$0._isAuthenticated.getValue()).isAuthenticated;
        Unit unit = Unit.INSTANCE;
        if (z) {
            if (((PromptAuthState) this.this$0._isAuthenticated.getValue()).needsUserConfirmation && this.$hapticFeedback) {
                PromptViewModel promptViewModel = this.this$0;
                promptViewModel.getClass();
                PromptViewModel.HapticsToPlay.HapticConstant hapticConstant = new PromptViewModel.HapticsToPlay.HapticConstant(10005);
                StateFlowImpl stateFlowImpl = promptViewModel._hapticsToPlay;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, hapticConstant);
            }
            return unit;
        }
        AuthContainerView$$ExternalSyntheticOutline0.m(this.$failedModality == BiometricModality.Face, this.this$0._canTryAgainNow, null);
        boolean booleanValue = ((Boolean) this.$suppressIf.invoke(this.this$0._message.getValue(), this.this$0.history)).booleanValue();
        PromptHistoryImpl promptHistoryImpl = this.this$0.history;
        BiometricModality biometricModality = this.$failedModality;
        promptHistoryImpl.getClass();
        if (biometricModality != BiometricModality.None) {
            promptHistoryImpl.failures.add(biometricModality);
        }
        if (booleanValue) {
            return unit;
        }
        StateFlowImpl stateFlowImpl2 = this.this$0._isAuthenticating;
        Boolean bool = Boolean.FALSE;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool);
        StateFlowImpl stateFlowImpl3 = this.this$0._isAuthenticated;
        PromptAuthState promptAuthState = new PromptAuthState();
        stateFlowImpl3.getClass();
        stateFlowImpl3.updateState(null, promptAuthState);
        StateFlowImpl stateFlowImpl4 = this.this$0._forceMediumSize;
        Boolean bool2 = Boolean.TRUE;
        stateFlowImpl4.getClass();
        stateFlowImpl4.updateState(null, bool2);
        StateFlowImpl stateFlowImpl5 = this.this$0._message;
        PromptMessage.Error error = new PromptMessage.Error(this.$message);
        stateFlowImpl5.getClass();
        stateFlowImpl5.updateState(null, error);
        if (this.$hapticFeedback) {
            PromptViewModel promptViewModel2 = this.this$0;
            promptViewModel2.getClass();
            PromptViewModel.HapticsToPlay.HapticConstant hapticConstant2 = new PromptViewModel.HapticsToPlay.HapticConstant(10005);
            StateFlowImpl stateFlowImpl6 = promptViewModel2._hapticsToPlay;
            stateFlowImpl6.getClass();
            stateFlowImpl6.updateState(null, hapticConstant2);
        }
        StandaloneCoroutine standaloneCoroutine = this.this$0.messageJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        PromptViewModel promptViewModel3 = this.this$0;
        promptViewModel3.messageJob = BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$authenticateAfterError, promptViewModel3, this.$messageAfterError, null), 3);
        return unit;
    }
}
