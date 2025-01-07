package com.android.wm.shell.startingsurface;

import android.content.Context;
import android.hardware.display.DisplayManager;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda0;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.startingsurface.StartingSurfaceDrawer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AbsSplashWindowCreator {
    public final Context mContext;
    public final DisplayManager mDisplayManager;
    public final ShellExecutor mSplashScreenExecutor;
    public final SplashscreenContentDrawer mSplashscreenContentDrawer;
    public final StartingSurfaceDrawer.StartingWindowRecordManager mStartingWindowRecordManager;
    public CentralSurfacesImpl$$ExternalSyntheticLambda0 mSysuiProxy;

    public AbsSplashWindowCreator(SplashscreenContentDrawer splashscreenContentDrawer, Context context, ShellExecutor shellExecutor, DisplayManager displayManager, StartingSurfaceDrawer.StartingWindowRecordManager startingWindowRecordManager) {
        this.mSplashscreenContentDrawer = splashscreenContentDrawer;
        this.mContext = context;
        this.mSplashScreenExecutor = shellExecutor;
        this.mDisplayManager = displayManager;
        this.mStartingWindowRecordManager = startingWindowRecordManager;
    }
}
