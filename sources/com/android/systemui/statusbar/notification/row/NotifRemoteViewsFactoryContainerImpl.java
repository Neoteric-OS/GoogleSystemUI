package com.android.systemui.statusbar.notification.row;

import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlin.collections.builders.SetBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifRemoteViewsFactoryContainerImpl {
    public final SetBuilder factories;

    public NotifRemoteViewsFactoryContainerImpl(PrecomputedTextViewFactory precomputedTextViewFactory, NotificationOptimizedLinearLayoutFactory notificationOptimizedLinearLayoutFactory, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        SetBuilder setBuilder = new SetBuilder();
        setBuilder.add(precomputedTextViewFactory);
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        setBuilder.add(notificationOptimizedLinearLayoutFactory);
        setBuilder.add(switchingProvider.get());
        this.factories = setBuilder.build();
    }
}
