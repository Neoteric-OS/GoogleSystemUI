package com.android.systemui.bouncer.ui.binder;

import com.android.systemui.bouncer.ui.BouncerViewModule$Companion$bouncerDialogFactory$1;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$58;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComposeBouncerDependencies {
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$58 bouncerContainerViewModelFactory;
    public final BouncerViewModule$Companion$bouncerDialogFactory$1 dialogFactory;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4 viewModelFactory;

    public ComposeBouncerDependencies(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4, BouncerViewModule$Companion$bouncerDialogFactory$1 bouncerViewModule$Companion$bouncerDialogFactory$1, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$58 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$58) {
        this.viewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4;
        this.dialogFactory = bouncerViewModule$Companion$bouncerDialogFactory$1;
        this.bouncerContainerViewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$58;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComposeBouncerDependencies)) {
            return false;
        }
        ComposeBouncerDependencies composeBouncerDependencies = (ComposeBouncerDependencies) obj;
        return Intrinsics.areEqual(this.viewModelFactory, composeBouncerDependencies.viewModelFactory) && Intrinsics.areEqual(this.dialogFactory, composeBouncerDependencies.dialogFactory) && Intrinsics.areEqual(this.bouncerContainerViewModelFactory, composeBouncerDependencies.bouncerContainerViewModelFactory);
    }

    public final int hashCode() {
        return hashCode() + ((hashCode() + (hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "ComposeBouncerDependencies(viewModelFactory=" + this.viewModelFactory + ", dialogFactory=" + this.dialogFactory + ", bouncerContainerViewModelFactory=" + this.bouncerContainerViewModelFactory + ")";
    }
}
