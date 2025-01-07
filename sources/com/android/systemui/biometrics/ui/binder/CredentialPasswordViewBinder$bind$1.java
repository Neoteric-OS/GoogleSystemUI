package com.android.systemui.biometrics.ui.binder;

import android.view.inputmethod.InputMethodManager;
import android.widget.ImeAwareEditText;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.LifecycleOwner;
import com.android.systemui.biometrics.AuthContainerView;
import com.android.systemui.biometrics.ui.CredentialPasswordView;
import com.android.systemui.biometrics.ui.CredentialView;
import com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlySharedFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CredentialPasswordViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ CredentialView.Host $host;
    final /* synthetic */ InputMethodManager $imeManager;
    final /* synthetic */ OnBackInvokedCallback $onBackInvokedCallback;
    final /* synthetic */ ImeAwareEditText $passwordField;
    final /* synthetic */ boolean $requestFocusForInput;
    final /* synthetic */ CredentialPasswordView $view;
    final /* synthetic */ CredentialViewModel $viewModel;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.ui.binder.CredentialPasswordViewBinder$bind$1$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ CredentialView.Host $host;
        final /* synthetic */ InputMethodManager $imeManager;
        final /* synthetic */ OnBackInvokedCallback $onBackInvokedCallback;
        final /* synthetic */ ImeAwareEditText $passwordField;
        final /* synthetic */ CredentialPasswordView $view;
        final /* synthetic */ CredentialViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.CredentialPasswordViewBinder$bind$1$3$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ CredentialView.Host $host;
            final /* synthetic */ InputMethodManager $imeManager;
            final /* synthetic */ ImeAwareEditText $passwordField;
            final /* synthetic */ CredentialPasswordView $view;
            final /* synthetic */ CredentialViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(CredentialViewModel credentialViewModel, InputMethodManager inputMethodManager, CredentialPasswordView credentialPasswordView, CredentialView.Host host, ImeAwareEditText imeAwareEditText, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = credentialViewModel;
                this.$imeManager = inputMethodManager;
                this.$view = credentialPasswordView;
                this.$host = host;
                this.$passwordField = imeAwareEditText;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$viewModel, this.$imeManager, this.$view, this.$host, this.$passwordField, continuation);
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
                    ReadonlySharedFlow readonlySharedFlow = this.$viewModel.validatedAttestation;
                    final InputMethodManager inputMethodManager = this.$imeManager;
                    final CredentialPasswordView credentialPasswordView = this.$view;
                    final CredentialView.Host host = this.$host;
                    final ImeAwareEditText imeAwareEditText = this.$passwordField;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.biometrics.ui.binder.CredentialPasswordViewBinder.bind.1.3.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            byte[] bArr = (byte[]) obj2;
                            if (bArr != null) {
                                inputMethodManager.hideSoftInputFromWindow(credentialPasswordView.getWindowToken(), 0);
                                AuthContainerView authContainerView = (AuthContainerView) host;
                                authContainerView.mCredentialAttestation = bArr;
                                authContainerView.animateAway(7, true);
                            } else {
                                imeAwareEditText.setText("");
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (readonlySharedFlow.$$delegate_0.collect(flowCollector, this) == coroutineSingletons) {
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

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.CredentialPasswordViewBinder$bind$1$3$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ OnBackInvokedCallback $onBackInvokedCallback;
            final /* synthetic */ OnBackInvokedDispatcher $onBackInvokedDispatcher;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(OnBackInvokedDispatcher onBackInvokedDispatcher, OnBackInvokedCallback onBackInvokedCallback, Continuation continuation) {
                super(2, continuation);
                this.$onBackInvokedDispatcher = onBackInvokedDispatcher;
                this.$onBackInvokedCallback = onBackInvokedCallback;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$onBackInvokedDispatcher, this.$onBackInvokedCallback, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                this.$onBackInvokedDispatcher.registerOnBackInvokedCallback(0, this.$onBackInvokedCallback);
                this.label = 1;
                DelayKt.awaitCancellation(this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(CredentialPasswordView credentialPasswordView, CredentialViewModel credentialViewModel, InputMethodManager inputMethodManager, CredentialView.Host host, ImeAwareEditText imeAwareEditText, OnBackInvokedCallback onBackInvokedCallback, Continuation continuation) {
            super(2, continuation);
            this.$view = credentialPasswordView;
            this.$viewModel = credentialViewModel;
            this.$imeManager = inputMethodManager;
            this.$host = host;
            this.$passwordField = imeAwareEditText;
            this.$onBackInvokedCallback = onBackInvokedCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$view, this.$viewModel, this.$imeManager, this.$host, this.$passwordField, this.$onBackInvokedCallback, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass3 anonymousClass3 = (AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass3.invokeSuspend(unit);
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
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$viewModel, this.$imeManager, this.$view, this.$host, this.$passwordField, null), 3);
            final OnBackInvokedDispatcher findOnBackInvokedDispatcher = this.$view.findOnBackInvokedDispatcher();
            if (findOnBackInvokedDispatcher != null) {
                StandaloneCoroutine launch$default = BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(findOnBackInvokedDispatcher, this.$onBackInvokedCallback, null), 3);
                final OnBackInvokedCallback onBackInvokedCallback = this.$onBackInvokedCallback;
                launch$default.invokeOnCompletion(new Function1() { // from class: com.android.systemui.biometrics.ui.binder.CredentialPasswordViewBinder.bind.1.3.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        findOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(onBackInvokedCallback);
                        return Unit.INSTANCE;
                    }
                });
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CredentialPasswordViewBinder$bind$1(CredentialViewModel credentialViewModel, ImeAwareEditText imeAwareEditText, boolean z, OnBackInvokedCallback onBackInvokedCallback, CredentialPasswordView credentialPasswordView, InputMethodManager inputMethodManager, CredentialView.Host host, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = credentialViewModel;
        this.$passwordField = imeAwareEditText;
        this.$requestFocusForInput = z;
        this.$onBackInvokedCallback = onBackInvokedCallback;
        this.$view = credentialPasswordView;
        this.$imeManager = inputMethodManager;
        this.$host = host;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CredentialPasswordViewBinder$bind$1 credentialPasswordViewBinder$bind$1 = new CredentialPasswordViewBinder$bind$1(this.$viewModel, this.$passwordField, this.$requestFocusForInput, this.$onBackInvokedCallback, this.$view, this.$imeManager, this.$host, (Continuation) obj3);
        credentialPasswordViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return credentialPasswordViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00d2 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.binder.CredentialPasswordViewBinder$bind$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
