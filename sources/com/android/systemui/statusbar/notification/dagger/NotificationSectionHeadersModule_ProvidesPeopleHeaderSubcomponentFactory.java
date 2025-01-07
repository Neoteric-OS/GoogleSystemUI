package com.android.systemui.statusbar.notification.dagger;

import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationSectionHeadersModule_ProvidesPeopleHeaderSubcomponentFactory implements Provider {
    public static DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl providesPeopleHeaderSubcomponent(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder daggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder = (DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder) switchingProvider.get();
        daggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder.getClass();
        daggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder.nodeLabel = "people header";
        daggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder.headerText = Integer.valueOf(R.string.notification_section_header_conversations);
        daggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder.clickIntentAction = "android.settings.CONVERSATION_SETTINGS";
        return daggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder.build();
    }
}
