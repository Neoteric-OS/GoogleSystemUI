package com.android.settingslib.wifi.dpp;

import android.text.TextUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class WifiDppIntentHelper {
    public static String removeFirstAndLastDoubleQuotes(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        int i = length - 1;
        int i2 = str.charAt(0) == '\"' ? 1 : 0;
        if (str.charAt(i) == '\"') {
            i = length - 2;
        }
        return str.substring(i2, i + 1);
    }
}
