package com.android.systemui.communal.widgets;

import android.content.Context;
import com.android.systemui.plugins.ActivityStarter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EditWidgetsActivityStarterImpl {
    public final ActivityStarter activityStarter;
    public final Context applicationContext;

    public EditWidgetsActivityStarterImpl(Context context, ActivityStarter activityStarter) {
        this.applicationContext = context;
        this.activityStarter = activityStarter;
    }
}
