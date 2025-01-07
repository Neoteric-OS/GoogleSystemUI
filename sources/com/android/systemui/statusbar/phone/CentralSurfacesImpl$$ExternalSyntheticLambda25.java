package com.android.systemui.statusbar.phone;

import android.util.ArraySet;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$$ExternalSyntheticLambda25 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ CentralSurfacesImpl$$ExternalSyntheticLambda25(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((ShadeController) obj).makeExpandedInvisible();
                break;
            default:
                CentralSurfacesImpl.AnonymousClass3 anonymousClass3 = (CentralSurfacesImpl.AnonymousClass3) obj;
                CentralSurfacesImpl.AnonymousClass3 anonymousClass32 = (CentralSurfacesImpl.AnonymousClass3) anonymousClass3.this$0;
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) ((CentralSurfacesImpl) anonymousClass32.this$0).mNotificationShadeWindowController;
                notificationShadeWindowControllerImpl.mListener = new CentralSurfacesImpl$$ExternalSyntheticLambda0(anonymousClass3);
                notificationShadeWindowControllerImpl.setForcePluginOpen(anonymousClass3, ((ArraySet) anonymousClass32.mOverlays).size() != 0);
                break;
        }
    }
}
