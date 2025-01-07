package com.android.systemui.statusbar.phone.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface StatusBarIconController {
    static ArraySet getIconHideList(Context context, String str) {
        ArraySet arraySet = new ArraySet();
        for (String str2 : str == null ? context.getResources().getStringArray(R.array.config_statusBarIconsToExclude) : str.split(",")) {
            if (!TextUtils.isEmpty(str2)) {
                arraySet.add(str2);
            }
        }
        return arraySet;
    }
}
