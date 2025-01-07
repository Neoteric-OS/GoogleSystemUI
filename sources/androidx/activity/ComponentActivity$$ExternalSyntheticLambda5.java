package androidx.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ComponentActivity$$ExternalSyntheticLambda5 implements LifecycleEventObserver {
    public final /* synthetic */ OnBackPressedDispatcher f$0;
    public final /* synthetic */ ComponentActivity f$1;

    public /* synthetic */ ComponentActivity$$ExternalSyntheticLambda5(ComponentActivity componentActivity, OnBackPressedDispatcher onBackPressedDispatcher) {
        this.f$0 = onBackPressedDispatcher;
        this.f$1 = componentActivity;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            this.f$0.setOnBackInvokedDispatcher(this.f$1.getOnBackInvokedDispatcher());
        }
    }
}
