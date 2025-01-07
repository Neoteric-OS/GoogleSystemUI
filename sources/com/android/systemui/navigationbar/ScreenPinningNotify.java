package com.android.systemui.navigationbar;

import android.content.Context;
import android.os.SystemClock;
import android.util.Slog;
import android.widget.Toast;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScreenPinningNotify {
    public final Context mContext;
    public long mLastShowToastTime;
    public Toast mLastToast;

    public ScreenPinningNotify(Context context) {
        this.mContext = context;
    }

    public final Toast makeAllUserToastAndShow(int i) {
        Context context = this.mContext;
        Toast makeText = Toast.makeText(context, context.getString(i), 1);
        makeText.show();
        return makeText;
    }

    public final void showEscapeToast(boolean z, boolean z2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.mLastShowToastTime < 1000) {
            Slog.i("ScreenPinningNotify", "Ignore toast since it is requested in very short interval.");
            return;
        }
        Toast toast = this.mLastToast;
        if (toast != null) {
            toast.cancel();
        }
        this.mLastToast = makeAllUserToastAndShow(z ? R.string.screen_pinning_toast_gesture_nav : z2 ? R.string.screen_pinning_toast : R.string.screen_pinning_toast_recents_invisible);
        this.mLastShowToastTime = elapsedRealtime;
    }
}
