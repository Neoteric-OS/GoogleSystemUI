package com.android.systemui.media.controls.ui.controller;

import com.android.systemui.media.controls.ui.controller.MediaHostStatesManager;
import com.android.systemui.media.controls.ui.view.MediaHostState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaViewController$stateCallback$1 implements MediaHostStatesManager.Callback {
    public final /* synthetic */ MediaViewController this$0;

    public MediaViewController$stateCallback$1(MediaViewController mediaViewController) {
        this.this$0 = mediaViewController;
    }

    @Override // com.android.systemui.media.controls.ui.controller.MediaHostStatesManager.Callback
    public final void onHostStateChanged(int i, MediaHostState mediaHostState) {
        MediaViewController mediaViewController = this.this$0;
        int i2 = mediaViewController.currentEndLocation;
        if (i == i2 || i == mediaViewController.currentStartLocation) {
            mediaViewController.setCurrentState(mediaViewController.currentStartLocation, i2, mediaViewController.currentTransitionProgress, false, false);
        }
    }
}
