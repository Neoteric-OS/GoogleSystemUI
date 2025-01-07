package com.android.settingslib.notification;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConversationIconFactory extends BaseIconFactory {
    public final LauncherApps mLauncherApps;
    public final PackageManager mPackageManager;

    static {
        Math.sqrt(288.0d);
    }

    public ConversationIconFactory(Context context, LauncherApps launcherApps, PackageManager packageManager, int i) {
        super(context, context.getResources().getConfiguration().densityDpi, i, false);
        this.mLauncherApps = launcherApps;
        this.mPackageManager = packageManager;
        context.getResources().getColor(R.color.important_conversation, null);
    }
}
