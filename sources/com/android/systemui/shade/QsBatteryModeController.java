package com.android.systemui.shade;

import android.content.Context;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QsBatteryModeController {
    public final Context context;
    public float fadeInStartFraction;
    public float fadeOutCompleteFraction;
    public final StatusBarContentInsetsProvider insetsProvider;

    public QsBatteryModeController(Context context, StatusBarContentInsetsProvider statusBarContentInsetsProvider) {
        this.context = context;
        this.insetsProvider = statusBarContentInsetsProvider;
        updateResources();
    }

    public final void updateResources() {
        this.fadeInStartFraction = (this.context.getResources().getInteger(R.integer.fade_in_start_frame) - 1) / 100.0f;
        this.fadeOutCompleteFraction = (this.context.getResources().getInteger(R.integer.fade_out_complete_frame) + 1) / 100.0f;
    }
}
