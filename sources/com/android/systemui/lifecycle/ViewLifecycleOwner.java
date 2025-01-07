package com.android.systemui.lifecycle;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewLifecycleOwner implements LifecycleOwner {
    public final View view;
    public final ViewLifecycleOwner$windowVisibleListener$1 windowVisibleListener = new ViewTreeObserver.OnWindowVisibilityChangeListener() { // from class: com.android.systemui.lifecycle.ViewLifecycleOwner$windowVisibleListener$1
        @Override // android.view.ViewTreeObserver.OnWindowVisibilityChangeListener
        public final void onWindowVisibilityChanged(int i) {
            ViewLifecycleOwner.this.updateState();
        }
    };
    public final ViewLifecycleOwner$windowFocusListener$1 windowFocusListener = new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: com.android.systemui.lifecycle.ViewLifecycleOwner$windowFocusListener$1
        @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
        public final void onWindowFocusChanged(boolean z) {
            ViewLifecycleOwner.this.updateState();
        }
    };
    public final LifecycleRegistry registry = new LifecycleRegistry(this);

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.lifecycle.ViewLifecycleOwner$windowVisibleListener$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.lifecycle.ViewLifecycleOwner$windowFocusListener$1] */
    public ViewLifecycleOwner(View view) {
        this.view = view;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.registry;
    }

    public final void onCreate() {
        this.registry.setCurrentState(Lifecycle.State.CREATED);
        this.view.getViewTreeObserver().addOnWindowVisibilityChangeListener(this.windowVisibleListener);
        this.view.getViewTreeObserver().addOnWindowFocusChangeListener(this.windowFocusListener);
        updateState();
    }

    public final void onDestroy() {
        this.view.getViewTreeObserver().removeOnWindowVisibilityChangeListener(this.windowVisibleListener);
        this.view.getViewTreeObserver().removeOnWindowFocusChangeListener(this.windowFocusListener);
        this.registry.setCurrentState(Lifecycle.State.DESTROYED);
    }

    public final void updateState() {
        this.registry.setCurrentState(this.view.getWindowVisibility() != 0 ? Lifecycle.State.CREATED : !this.view.hasWindowFocus() ? Lifecycle.State.STARTED : Lifecycle.State.RESUMED);
    }
}
