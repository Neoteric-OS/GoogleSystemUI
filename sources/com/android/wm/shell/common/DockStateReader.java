package com.android.wm.shell.common;

import android.content.Context;
import android.content.IntentFilter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DockStateReader {
    public static final IntentFilter DOCK_INTENT_FILTER = new IntentFilter("android.intent.action.DOCK_EVENT");
    public final Context mContext;

    public DockStateReader(Context context) {
        this.mContext = context;
    }
}
