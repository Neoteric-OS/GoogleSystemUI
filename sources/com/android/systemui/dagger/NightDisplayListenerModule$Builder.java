package com.android.systemui.dagger;

import android.content.Context;
import android.os.Handler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NightDisplayListenerModule$Builder {
    public final Handler mBgHandler;
    public final Context mContext;
    public int mUserId = 0;

    public NightDisplayListenerModule$Builder(Context context, Handler handler) {
        this.mContext = context;
        this.mBgHandler = handler;
    }
}
