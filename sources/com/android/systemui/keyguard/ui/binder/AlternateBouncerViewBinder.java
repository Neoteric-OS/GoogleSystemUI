package com.android.systemui.keyguard.ui.binder;

import android.view.View;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerDependencies;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerViewModel;
import dagger.Lazy;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AlternateBouncerViewBinder implements CoreStartable {
    public final Lazy alternateBouncerDependencies;
    public ConstraintLayout alternateBouncerView;
    public final Lazy alternateBouncerWindowViewModel;
    public final CoroutineScope applicationScope;
    public final Lazy layoutInflater;
    public final AlternateBouncerViewBinder$onAttachAddBackGestureHandler$1 onAttachAddBackGestureHandler = new View.OnAttachStateChangeListener(this) { // from class: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$onAttachAddBackGestureHandler$1
        public final AlternateBouncerViewBinder$onAttachAddBackGestureHandler$1$onBackInvokedCallback$1 onBackInvokedCallback;

        /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$onAttachAddBackGestureHandler$1$onBackInvokedCallback$1] */
        {
            this.onBackInvokedCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$onAttachAddBackGestureHandler$1$onBackInvokedCallback$1
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    AlternateBouncerViewModel alternateBouncerViewModel = ((AlternateBouncerDependencies) AlternateBouncerViewBinder.this.alternateBouncerDependencies.get()).viewModel;
                    alternateBouncerViewModel.statusBarKeyguardViewManager.hideAlternateBouncer(false);
                    alternateBouncerViewModel.dismissCallbackRegistry.notifyDismissCancelled();
                    alternateBouncerViewModel.primaryBouncerInteractor.setDismissAction(null, null);
                }
            };
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            OnBackInvokedDispatcher findOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher();
            if (findOnBackInvokedDispatcher != null) {
                findOnBackInvokedDispatcher.registerOnBackInvokedCallback(1000000, this.onBackInvokedCallback);
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            OnBackInvokedDispatcher findOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher();
            if (findOnBackInvokedDispatcher != null) {
                findOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(this.onBackInvokedCallback);
            }
        }
    };
    public final Lazy windowManager;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$onAttachAddBackGestureHandler$1] */
    public AlternateBouncerViewBinder(CoroutineScope coroutineScope, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.applicationScope = coroutineScope;
        this.alternateBouncerWindowViewModel = lazy;
        this.alternateBouncerDependencies = lazy2;
        this.windowManager = lazy3;
        this.layoutInflater = lazy4;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        CoroutineTracingKt.launch$default(this.applicationScope, null, new AlternateBouncerViewBinder$start$1(this, null), 6);
    }
}
