package com.google.android.material.motion;

import android.view.View;
import android.window.OnBackInvokedCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaterialBackOrchestrator {
    public final Api34BackCallbackDelegate backCallbackDelegate = new Api34BackCallbackDelegate();
    public final MaterialBackHandler backHandler;
    public final View view;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Api34BackCallbackDelegate {
        public OnBackInvokedCallback onBackInvokedCallback;
    }

    public MaterialBackOrchestrator(MaterialBackHandler materialBackHandler, View view) {
        this.backHandler = materialBackHandler;
        this.view = view;
    }
}
