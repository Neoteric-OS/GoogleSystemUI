package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleEducationController {
    public final Context context;
    public final SharedPreferences prefs;

    public BubbleEducationController(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(context.getPackageName(), 0);
    }
}
