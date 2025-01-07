package com.google.android.systemui.dagger;

import com.android.systemui.ambient.touch.dagger.AmbientTouchComponent$Factory;
import com.android.systemui.complication.dagger.ComplicationComponent$Factory;
import com.android.systemui.doze.dagger.DozeComponent$Builder;
import com.android.systemui.dreams.dagger.DreamOverlayComponent$Factory;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorComponent$Factory;
import com.android.systemui.qs.dagger.QSSceneComponent$Factory;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory implements AmbientTouchComponent$Factory, DozeComponent$Builder, DreamOverlayComponent$Factory, MediaProjectionAppSelectorComponent$Factory, QSSceneComponent$Factory, ComplicationComponent$Factory, com.android.systemui.dreams.complication.dagger.ComplicationComponent$Factory {
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
    }
}
