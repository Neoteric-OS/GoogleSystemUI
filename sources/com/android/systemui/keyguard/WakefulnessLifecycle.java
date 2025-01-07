package com.android.systemui.keyguard;

import android.app.IWallpaperManager;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.util.time.SystemClock;
import com.android.wm.shell.R;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WakefulnessLifecycle extends Lifecycle implements Dumpable {
    public final Context mContext;
    public final DisplayMetrics mDisplayMetrics;
    public final SystemClock mSystemClock;
    public final IWallpaperManager mWallpaperManagerService;
    public int mWakefulness = 2;
    public int mLastWakeReason = 0;
    public Point mLastWakeOriginLocation = null;
    public int mLastSleepReason = 0;
    public Point mLastSleepOriginLocation = null;

    public WakefulnessLifecycle(Context context, IWallpaperManager iWallpaperManager, SystemClock systemClock, DumpManager dumpManager) {
        this.mContext = context;
        this.mDisplayMetrics = context.getResources().getDisplayMetrics();
        this.mWallpaperManagerService = iWallpaperManager;
        this.mSystemClock = systemClock;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "WakefulnessLifecycle", this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "WakefulnessLifecycle:", "  mWakefulness=");
        m.append(this.mWakefulness);
        printWriter.println(m.toString());
    }

    public final Point getPowerButtonOrigin() {
        return this.mContext.getResources().getConfiguration().orientation == 1 ? new Point(this.mDisplayMetrics.widthPixels, this.mContext.getResources().getDimensionPixelSize(R.dimen.physical_power_button_center_screen_location_y)) : new Point(this.mContext.getResources().getDimensionPixelSize(R.dimen.physical_power_button_center_screen_location_y), this.mDisplayMetrics.heightPixels);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Observer {
        default void onFinishedGoingToSleep$1() {
        }

        default void onFinishedWakingUp() {
        }

        default void onPostFinishedWakingUp() {
        }

        default void onStartedGoingToSleep() {
        }

        default void onStartedWakingUp() {
        }
    }
}
