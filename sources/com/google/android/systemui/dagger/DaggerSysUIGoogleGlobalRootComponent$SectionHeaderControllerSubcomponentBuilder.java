package com.google.android.systemui.dagger;

import dagger.internal.Preconditions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder {
    public String clickIntentAction;
    public Integer headerText;
    public String nodeLabel;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
    }

    public final DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl build() {
        Preconditions.checkBuilderRequirement(String.class, this.nodeLabel);
        Preconditions.checkBuilderRequirement(Integer.class, this.headerText);
        Preconditions.checkBuilderRequirement(String.class, this.clickIntentAction);
        return new DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl(this.sysUIGoogleGlobalRootComponentImpl, this.sysUIGoogleSysUIComponentImpl, this.nodeLabel, this.headerText, this.clickIntentAction);
    }
}
