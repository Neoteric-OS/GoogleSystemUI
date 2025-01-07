package com.android.systemui.qs.external;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomTileStatePersisterImpl {
    public final SharedPreferences sharedPreferences;

    public CustomTileStatePersisterImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences("custom_tiles_state", 0);
    }
}
