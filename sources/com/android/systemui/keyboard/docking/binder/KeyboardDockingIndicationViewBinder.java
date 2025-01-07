package com.android.systemui.keyboard.docking.binder;

import android.content.Context;
import android.view.WindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.systemui.keyboard.docking.ui.KeyboardDockingIndicationView;
import com.android.systemui.keyboard.docking.ui.viewmodel.KeyboardDockingIndicationViewModel;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardDockingIndicationViewBinder {
    public final KeyboardDockingIndicationViewBinder$drawCallback$1 drawCallback;
    public final KeyboardDockingIndicationView glowEffectView;
    public final KeyboardDockingIndicationViewBinder$stateChangedCallback$1 stateChangedCallback;
    public final KeyboardDockingIndicationViewModel viewModel;
    public final WindowManager.LayoutParams windowLayoutParams;
    public final ViewCaptureAwareWindowManager windowManager;

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.keyboard.docking.binder.KeyboardDockingIndicationViewBinder$stateChangedCallback$1] */
    public KeyboardDockingIndicationViewBinder(Context context, CoroutineScope coroutineScope, KeyboardDockingIndicationViewModel keyboardDockingIndicationViewModel, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.format = -3;
        layoutParams.type = 2009;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.setTitle("Edge glow effect");
        layoutParams.flags = 24;
        layoutParams.setTrustedOverlay();
        new KeyboardDockingIndicationView(context, null);
        new Object(this) { // from class: com.android.systemui.keyboard.docking.binder.KeyboardDockingIndicationViewBinder$drawCallback$1
            public final /* synthetic */ KeyboardDockingIndicationViewBinder this$0;
        };
        this.stateChangedCallback = new Object(this) { // from class: com.android.systemui.keyboard.docking.binder.KeyboardDockingIndicationViewBinder$stateChangedCallback$1
            public final /* synthetic */ KeyboardDockingIndicationViewBinder this$0;
        };
    }
}
