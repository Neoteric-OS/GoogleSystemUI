package com.google.android.systemui.statusbar.phone;

import com.google.android.systemui.statusbar.phone.WallpaperNotifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WallpaperNotifier$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ WallpaperNotifier$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((WallpaperNotifier) obj).checkNotificationBroadcastSupport();
                break;
            default:
                ((WallpaperNotifier.AnonymousClass2) obj).this$0.checkNotificationBroadcastSupport();
                break;
        }
    }
}
