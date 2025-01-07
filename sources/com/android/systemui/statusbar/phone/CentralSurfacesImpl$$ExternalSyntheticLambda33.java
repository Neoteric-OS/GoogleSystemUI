package com.android.systemui.statusbar.phone;

import com.android.systemui.shade.NotificationShadeWindowControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$$ExternalSyntheticLambda33 implements Runnable {
    public final /* synthetic */ CentralSurfacesImpl f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ CentralSurfacesImpl$$ExternalSyntheticLambda33(CentralSurfacesImpl centralSurfacesImpl, boolean z) {
        this.f$0 = centralSurfacesImpl;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CentralSurfacesImpl centralSurfacesImpl = this.f$0;
        ((NotificationShadeWindowControllerImpl) centralSurfacesImpl.mNotificationShadeWindowController).setRequestTopUi("ShellStartingWindow", this.f$1);
    }
}
