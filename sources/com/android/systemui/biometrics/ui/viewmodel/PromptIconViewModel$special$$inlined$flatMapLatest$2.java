package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptIconViewModel$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ PromptSelectorInteractor $promptSelectorInteractor$inlined;
    final /* synthetic */ PromptViewModel $promptViewModel$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PromptIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptIconViewModel$special$$inlined$flatMapLatest$2(PromptSelectorInteractor promptSelectorInteractor, PromptIconViewModel promptIconViewModel, PromptViewModel promptViewModel, Continuation continuation) {
        super(3, continuation);
        this.$promptSelectorInteractor$inlined = promptSelectorInteractor;
        this.$promptViewModel$inlined = promptViewModel;
        this.this$0 = promptIconViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PromptSelectorInteractor promptSelectorInteractor = this.$promptSelectorInteractor$inlined;
        PromptViewModel promptViewModel = this.$promptViewModel$inlined;
        PromptIconViewModel$special$$inlined$flatMapLatest$2 promptIconViewModel$special$$inlined$flatMapLatest$2 = new PromptIconViewModel$special$$inlined$flatMapLatest$2(promptSelectorInteractor, this.this$0, promptViewModel, (Continuation) obj3);
        promptIconViewModel$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        promptIconViewModel$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return promptIconViewModel$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006a A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r10.label
            r2 = 1
            if (r1 == 0) goto L15
            if (r1 != r2) goto Ld
            kotlin.ResultKt.throwOnFailure(r11)
            goto L6b
        Ld:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L15:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
            java.lang.Object r1 = r10.L$1
            com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$AuthType r1 = (com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel.AuthType) r1
            int r1 = r1.ordinal()
            r3 = 0
            if (r1 == 0) goto L47
            if (r1 == r2) goto L33
            r4 = 2
            if (r1 != r4) goto L2d
            goto L47
        L2d:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException
            r10.<init>()
            throw r10
        L33:
            com.android.systemui.biometrics.ui.viewmodel.PromptViewModel r1 = r10.$promptViewModel$inlined
            kotlinx.coroutines.flow.ReadonlyStateFlow r4 = r1.isAuthenticated
            com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$contentDescriptionId$1$2 r5 = new com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$contentDescriptionId$1$2
            com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel r6 = r10.this$0
            r5.<init>(r6, r3)
            kotlinx.coroutines.flow.ReadonlyStateFlow r3 = r1.isAuthenticating
            kotlinx.coroutines.flow.Flow r1 = r1.showingError
            kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 r1 = kotlinx.coroutines.flow.FlowKt.combine(r4, r3, r1, r5)
            goto L62
        L47:
            com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractor r1 = r10.$promptSelectorInteractor$inlined
            com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl r1 = (com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl) r1
            kotlinx.coroutines.flow.StateFlow r4 = r1.fingerprintSensorType
            com.android.systemui.biometrics.ui.viewmodel.PromptViewModel r1 = r10.$promptViewModel$inlined
            kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r1.isAuthenticated
            com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$contentDescriptionId$1$1 r9 = new com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$contentDescriptionId$1$1
            com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel r6 = r10.this$0
            r9.<init>(r6, r3)
            kotlinx.coroutines.flow.ReadonlyStateFlow r6 = r1.isAuthenticating
            com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2 r7 = r1.isPendingConfirmation
            kotlinx.coroutines.flow.Flow r8 = r1.showingError
            kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 r1 = kotlinx.coroutines.flow.FlowKt.combine(r4, r5, r6, r7, r8, r9)
        L62:
            r10.label = r2
            java.lang.Object r10 = kotlinx.coroutines.flow.FlowKt.emitAll(r11, r1, r10)
            if (r10 != r0) goto L6b
            return r0
        L6b:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$special$$inlined$flatMapLatest$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
