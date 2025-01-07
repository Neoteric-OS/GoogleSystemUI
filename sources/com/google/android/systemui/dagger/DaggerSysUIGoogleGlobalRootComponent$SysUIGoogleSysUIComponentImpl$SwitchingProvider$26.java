package com.google.android.systemui.dagger;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotifLayoutInflaterFactory;
import com.android.systemui.statusbar.notification.row.NotifRemoteViewsFactoryContainerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final NotifLayoutInflaterFactory provide(ExpandableNotificationRow expandableNotificationRow, int i) {
        return new NotifLayoutInflaterFactory(expandableNotificationRow, i, (NotifRemoteViewsFactoryContainerImpl) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) this.this$0.wMComponentImpl).provideNotifRemoteViewsFactoryContainerProvider.get());
    }
}
