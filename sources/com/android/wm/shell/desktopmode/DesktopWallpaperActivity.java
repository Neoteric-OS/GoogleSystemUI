package com.android.wm.shell.desktopmode;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopWallpaperActivity extends Activity {
    public static final ComponentName wallpaperActivityComponent = new ComponentName("com.android.systemui", DesktopWallpaperActivity.class.getName());

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        ProtoLog.d(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopWallpaperActivity: onCreate", new Object[0]);
        super.onCreate(bundle);
        getWindow().addFlags(8);
    }
}
