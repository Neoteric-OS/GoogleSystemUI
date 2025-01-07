package com.android.wm.shell.sysui;

import android.os.Build;
import android.os.SystemClock;
import android.util.Pair;
import android.view.SurfaceControl;
import com.android.internal.protolog.ProtoLog;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShellInit {
    public boolean mHasInitialized;
    public final ArrayList mInitCallbacks = new ArrayList();

    public ShellInit() {
        ProtoLog.init(ShellProtoLogGroup.values());
    }

    public final void addInitCallback(Runnable runnable, Object obj) {
        if (this.mHasInitialized) {
            if (Build.isDebuggable()) {
                throw new IllegalArgumentException("Can not add callback after init");
            }
            return;
        }
        String simpleName = obj.getClass().getSimpleName();
        this.mInitCallbacks.add(new Pair(simpleName, runnable));
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_INIT_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_INIT, -776239364395549896L, 0, simpleName);
        }
    }

    public void init() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_INIT_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_INIT, 6164850266728838211L, 1, Long.valueOf(this.mInitCallbacks.size()));
        }
        SurfaceControl.setDebugUsageAfterRelease(true);
        for (int i = 0; i < this.mInitCallbacks.size(); i++) {
            Pair pair = (Pair) this.mInitCallbacks.get(i);
            long uptimeMillis = SystemClock.uptimeMillis();
            ((Runnable) pair.second).run();
            long uptimeMillis2 = SystemClock.uptimeMillis();
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_INIT_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_INIT, -8211191618626308882L, 4, String.valueOf(pair.first), Long.valueOf(uptimeMillis2 - uptimeMillis));
            }
        }
        this.mInitCallbacks.clear();
        this.mHasInitialized = true;
    }
}
