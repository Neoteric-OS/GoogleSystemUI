package com.android.systemui.util.time;

import android.content.Context;
import android.text.format.DateFormat;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DateFormatUtil {
    public final Context mContext;
    public final UserTracker mUserTracker;

    public DateFormatUtil(Context context, UserTracker userTracker) {
        this.mContext = context;
        this.mUserTracker = userTracker;
    }

    public final boolean is24HourFormat() {
        return DateFormat.is24HourFormat(this.mContext, ((UserTrackerImpl) this.mUserTracker).getUserId());
    }
}
