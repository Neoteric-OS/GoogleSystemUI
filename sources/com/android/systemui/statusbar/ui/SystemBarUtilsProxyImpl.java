package com.android.systemui.statusbar.ui;

import android.content.Context;
import android.view.DisplayCutout;
import com.android.internal.policy.SystemBarUtils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemBarUtilsProxyImpl {
    public final Context context;

    public SystemBarUtilsProxyImpl(Context context) {
        this.context = context;
    }

    public final int getStatusBarHeaderHeightKeyguard() {
        DisplayCutout cutout = this.context.getDisplay().getCutout();
        return Math.max(SystemBarUtils.getStatusBarHeight(this.context), this.context.getResources().getDimensionPixelSize(R.dimen.status_bar_header_height_keyguard) + (cutout == null ? 0 : cutout.getWaterfallInsets().top));
    }
}
