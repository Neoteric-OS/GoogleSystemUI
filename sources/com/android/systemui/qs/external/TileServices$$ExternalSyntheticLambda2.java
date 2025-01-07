package com.android.systemui.qs.external;

import android.content.ComponentName;
import android.util.Log;
import com.android.systemui.qs.external.TileServices;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TileServices$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ TileServices$$ExternalSyntheticLambda2(TileServices.AnonymousClass2 anonymousClass2, ComponentName componentName) {
        this.f$0 = anonymousClass2;
        this.f$1 = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CustomTileInterface customTileInterface;
        switch (this.$r8$classId) {
            case 0:
                TileServices tileServices = (TileServices) this.f$0;
                String str = (String) this.f$1;
                StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) tileServices.mStatusBarIconController;
                statusBarIconControllerImpl.getClass();
                if (!str.endsWith("__external")) {
                    str = str.concat("__external");
                }
                statusBarIconControllerImpl.removeAllIconsForSlot(str, false);
                return;
            default:
                TileServices.AnonymousClass2 anonymousClass2 = (TileServices.AnonymousClass2) this.f$0;
                ComponentName componentName = (ComponentName) this.f$1;
                TileServices tileServices2 = TileServices.this;
                synchronized (tileServices2.mServices) {
                    try {
                        int userId = ((UserTrackerImpl) tileServices2.mUserTracker).getUserId();
                        synchronized (tileServices2.mServices) {
                            customTileInterface = (CustomTileInterface) tileServices2.mTiles.get(userId, componentName);
                        }
                        if (customTileInterface == null) {
                            Log.d("TileServices", "Couldn't find tile for " + componentName + "(" + userId + ")");
                            return;
                        }
                        TileServiceManager tileServiceManager = (TileServiceManager) tileServices2.mServices.get(customTileInterface);
                        if (tileServiceManager == null) {
                            Log.e("TileServices", "No TileServiceManager found in requestListening for tile " + customTileInterface.getTileSpec());
                            return;
                        } else {
                            if (tileServiceManager.mStateManager.isActiveTile()) {
                                tileServiceManager.setBindRequested(true);
                                tileServiceManager.mListeningFromRequest.set(true);
                                tileServiceManager.mStateManager.onStartListening();
                                return;
                            }
                            return;
                        }
                    } finally {
                    }
                }
        }
    }

    public /* synthetic */ TileServices$$ExternalSyntheticLambda2(TileServices tileServices, String str) {
        this.f$0 = tileServices;
        this.f$1 = str;
    }
}
