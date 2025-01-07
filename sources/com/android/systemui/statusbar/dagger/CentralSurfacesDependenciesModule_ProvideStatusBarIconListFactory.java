package com.android.systemui.statusbar.dagger;

import android.R;
import android.content.Context;
import com.android.systemui.statusbar.phone.ui.StatusBarIconList;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CentralSurfacesDependenciesModule_ProvideStatusBarIconListFactory implements Provider {
    public static StatusBarIconList provideStatusBarIconList(Context context) {
        return new StatusBarIconList(context.getResources().getStringArray(R.array.config_system_condition_providers));
    }
}
