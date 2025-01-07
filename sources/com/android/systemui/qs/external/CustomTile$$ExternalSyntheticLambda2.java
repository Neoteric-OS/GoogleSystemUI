package com.android.systemui.qs.external;

import android.os.RemoteException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CustomTile$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CustomTile f$0;

    public /* synthetic */ CustomTile$$ExternalSyntheticLambda2(CustomTile customTile, int i) {
        this.$r8$classId = i;
        this.f$0 = customTile;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        CustomTile customTile = this.f$0;
        switch (i) {
            case 0:
                customTile.getClass();
                try {
                    customTile.mService.onUnlockComplete();
                    break;
                } catch (RemoteException unused) {
                    return;
                }
            default:
                customTile.updateDefaultTileAndIcon();
                break;
        }
    }
}
