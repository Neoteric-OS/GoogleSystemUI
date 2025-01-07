package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractorImpl;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractor;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl;
import com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptIconViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ PromptSelectorInteractor $promptSelectorInteractor$inlined;
    final /* synthetic */ PromptViewModel $promptViewModel$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PromptIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptIconViewModel$special$$inlined$flatMapLatest$1(PromptSelectorInteractor promptSelectorInteractor, PromptIconViewModel promptIconViewModel, PromptViewModel promptViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = promptIconViewModel;
        this.$promptSelectorInteractor$inlined = promptSelectorInteractor;
        this.$promptViewModel$inlined = promptViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PromptIconViewModel promptIconViewModel = this.this$0;
        PromptIconViewModel$special$$inlined$flatMapLatest$1 promptIconViewModel$special$$inlined$flatMapLatest$1 = new PromptIconViewModel$special$$inlined$flatMapLatest$1(this.$promptSelectorInteractor$inlined, promptIconViewModel, this.$promptViewModel$inlined, (Continuation) obj3);
        promptIconViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        promptIconViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return promptIconViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flow;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            int ordinal = ((PromptIconViewModel.AuthType) this.L$1).ordinal();
            if (ordinal == 0) {
                final PromptIconViewModel promptIconViewModel = this.this$0;
                DisplayStateInteractorImpl displayStateInteractorImpl = (DisplayStateInteractorImpl) promptIconViewModel.displayStateInteractor;
                ReadonlyStateFlow readonlyStateFlow = displayStateInteractorImpl.currentRotation;
                ReadonlyStateFlow readonlyStateFlow2 = displayStateInteractorImpl.isInRearDisplayMode;
                StateFlow stateFlow = ((PromptSelectorInteractorImpl) this.$promptSelectorInteractor$inlined).fingerprintSensorType;
                PromptViewModel promptViewModel = this.$promptViewModel$inlined;
                final Flow[] flowArr = {readonlyStateFlow, readonlyStateFlow2, stateFlow, promptViewModel.isAuthenticated, promptViewModel.isAuthenticating, promptViewModel.showingError};
                final int i2 = 0;
                flow = new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$1$3, reason: invalid class name */
                    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
                        private /* synthetic */ Object L$0;
                        /* synthetic */ Object L$1;
                        int label;
                        final /* synthetic */ PromptIconViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass3(PromptIconViewModel promptIconViewModel, Continuation continuation) {
                            super(3, continuation);
                            this.this$0 = promptIconViewModel;
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, (Continuation) obj3);
                            anonymousClass3.L$0 = (FlowCollector) obj;
                            anonymousClass3.L$1 = (Object[]) obj2;
                            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:43:0x00cb, code lost:
                        
                            if (r1 != false) goto L37;
                         */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                            /*
                                Method dump skipped, instructions count: 223
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$1.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector2, Continuation continuation) {
                        switch (i2) {
                            case 0:
                                final Flow[] flowArr2 = flowArr;
                                Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return new Object[flowArr2.length];
                                    }
                                }, new AnonymousClass3(promptIconViewModel, null), flowCollector2, flowArr2);
                                if (combineInternal != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                    break;
                                }
                                break;
                            default:
                                final Flow[] flowArr3 = flowArr;
                                Object combineInternal2 = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return new Object[flowArr3.length];
                                    }
                                }, new PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3(promptIconViewModel, null), flowCollector2, flowArr3);
                                if (combineInternal2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                    break;
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                };
            } else if (ordinal == 1) {
                flow = FlowKt.combine(FlowKt.distinctUntilChanged(this.$promptViewModel$inlined.isAuthenticated), FlowKt.distinctUntilChanged(this.$promptViewModel$inlined.isAuthenticating), FlowKt.distinctUntilChanged(this.$promptViewModel$inlined.isPendingConfirmation), FlowKt.distinctUntilChanged(this.$promptViewModel$inlined.showingError), new PromptIconViewModel$iconAsset$1$2(this.this$0, null));
            } else {
                if (ordinal != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                final PromptIconViewModel promptIconViewModel2 = this.this$0;
                DisplayStateInteractorImpl displayStateInteractorImpl2 = (DisplayStateInteractorImpl) promptIconViewModel2.displayStateInteractor;
                ReadonlyStateFlow readonlyStateFlow3 = displayStateInteractorImpl2.currentRotation;
                ReadonlyStateFlow readonlyStateFlow4 = displayStateInteractorImpl2.isInRearDisplayMode;
                StateFlow stateFlow2 = ((PromptSelectorInteractorImpl) this.$promptSelectorInteractor$inlined).fingerprintSensorType;
                PromptViewModel promptViewModel2 = this.$promptViewModel$inlined;
                final Flow[] flowArr2 = {readonlyStateFlow3, readonlyStateFlow4, stateFlow2, promptViewModel2.isAuthenticated, promptViewModel2.isAuthenticating, promptViewModel2.isPendingConfirmation, promptViewModel2.showingError};
                final int i3 = 1;
                flow = new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$1$3, reason: invalid class name */
                    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
                        private /* synthetic */ Object L$0;
                        /* synthetic */ Object L$1;
                        int label;
                        final /* synthetic */ PromptIconViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass3(PromptIconViewModel promptIconViewModel, Continuation continuation) {
                            super(3, continuation);
                            this.this$0 = promptIconViewModel;
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, (Continuation) obj3);
                            anonymousClass3.L$0 = (FlowCollector) obj;
                            anonymousClass3.L$1 = (Object[]) obj2;
                            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            /*
                                Method dump skipped, instructions count: 223
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$1.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector2, Continuation continuation) {
                        switch (i3) {
                            case 0:
                                final Flow[] flowArr22 = flowArr2;
                                Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return new Object[flowArr22.length];
                                    }
                                }, new AnonymousClass3(promptIconViewModel2, null), flowCollector2, flowArr22);
                                if (combineInternal != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                    break;
                                }
                                break;
                            default:
                                final Flow[] flowArr3 = flowArr2;
                                Object combineInternal2 = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return new Object[flowArr3.length];
                                    }
                                }, new PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3(promptIconViewModel2, null), flowCollector2, flowArr3);
                                if (combineInternal2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                    break;
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                };
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flow, this) == coroutineSingletons) {
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
