package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.recents.OverviewProxyRecentsImpl;
import com.android.systemui.recents.Recents;
import com.android.systemui.statusbar.CommandQueue;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SystemUIGoogleModule_ProvideRecentsFactory implements Provider {
    public static Recents provideRecents(Context context, OverviewProxyRecentsImpl overviewProxyRecentsImpl, CommandQueue commandQueue) {
        return new Recents(context, overviewProxyRecentsImpl, commandQueue);
    }
}
