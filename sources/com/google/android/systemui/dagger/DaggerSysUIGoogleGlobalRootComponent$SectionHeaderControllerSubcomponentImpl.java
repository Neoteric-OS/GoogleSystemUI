package com.google.android.systemui.dagger;

import com.android.systemui.statusbar.notification.collection.render.NodeController;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl {
    public final String clickIntentAction;
    public final Integer headerText;
    public final String nodeLabel;
    public final Provider sectionHeaderNodeControllerImplProvider;

    public DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, String str, Integer num, String str2) {
        this.nodeLabel = str;
        this.headerText = num;
        this.clickIntentAction = str2;
        this.sectionHeaderNodeControllerImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl$SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 4));
    }

    public final SectionHeaderNodeControllerImpl getHeaderController() {
        return (SectionHeaderNodeControllerImpl) this.sectionHeaderNodeControllerImplProvider.get();
    }

    public final NodeController getNodeController() {
        return (NodeController) this.sectionHeaderNodeControllerImplProvider.get();
    }
}
