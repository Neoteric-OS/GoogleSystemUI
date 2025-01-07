package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.notification.stack.ui.view.NotificationScrollView;
import com.android.systemui.util.kotlin.FlowDumperImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$32;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationScrollViewBinder extends FlowDumperImpl {
    public final ConfigurationState configuration;
    public final NotificationScrollView view;
    public final MutableStateFlow viewLeftOffset;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$32 viewModelFactory;

    public NotificationScrollViewBinder(DumpManager dumpManager, CoroutineDispatcher coroutineDispatcher, NotificationScrollView notificationScrollView, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$32 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$32, ConfigurationState configurationState) {
        super(dumpManager);
        dumpValue(StateFlowKt.MutableStateFlow(0), "viewLeftOffset");
    }
}
