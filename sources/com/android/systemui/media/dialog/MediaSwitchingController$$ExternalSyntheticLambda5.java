package com.android.systemui.media.dialog;

import android.media.MediaRoute2Info;
import android.util.Log;
import com.android.settingslib.media.InfoMediaManager;
import com.android.settingslib.media.MediaDevice;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaSwitchingController$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ MediaSwitchingController f$0;
    public final /* synthetic */ MediaDevice f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaSwitchingController$$ExternalSyntheticLambda5(MediaSwitchingController mediaSwitchingController, MediaDevice mediaDevice, int i) {
        this.f$0 = mediaSwitchingController;
        this.f$1 = mediaDevice;
        this.f$2 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSwitchingController mediaSwitchingController = this.f$0;
        MediaDevice mediaDevice = this.f$1;
        int i = this.f$2;
        InfoMediaManager infoMediaManager = mediaSwitchingController.mLocalMediaManager.mInfoMediaManager;
        infoMediaManager.getClass();
        MediaRoute2Info mediaRoute2Info = mediaDevice.mRouteInfo;
        if (mediaRoute2Info == null) {
            Log.w("InfoMediaManager", "Unable to set volume. RouteInfo is empty");
        } else {
            infoMediaManager.setRouteVolume(mediaRoute2Info, i);
        }
    }
}
