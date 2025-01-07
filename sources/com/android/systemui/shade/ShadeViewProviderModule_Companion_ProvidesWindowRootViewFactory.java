package com.android.systemui.shade;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.wm.shell.R;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ShadeViewProviderModule_Companion_ProvidesWindowRootViewFactory implements Provider {
    public static NotificationShadeWindowView providesWindowRootView(LayoutInflater layoutInflater) {
        NotificationShadeWindowView notificationShadeWindowView = (NotificationShadeWindowView) layoutInflater.inflate(R.layout.super_notification_shade, (ViewGroup) null);
        if (notificationShadeWindowView != null) {
            return notificationShadeWindowView;
        }
        throw new IllegalStateException("Window root view could not be properly inflated");
    }
}
