package com.android.systemui.mediaprojection;

import android.media.projection.IMediaProjection;
import android.media.projection.IMediaProjectionManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaProjectionServiceHelper {
    public static final IMediaProjectionManager service = IMediaProjectionManager.Stub.asInterface(ServiceManager.getService("media_projection"));

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static void setReviewedConsentIfNeeded(int i, boolean z, IMediaProjection iMediaProjection) {
            if (!z || i == -1) {
                return;
            }
            try {
                MediaProjectionServiceHelper.service.setUserReviewGrantedConsentResult(i, iMediaProjection);
            } catch (RemoteException e) {
                Log.e("MediaProjectionServiceHelper", "Unable to set required consent result for token re-use", e);
            }
        }
    }
}
