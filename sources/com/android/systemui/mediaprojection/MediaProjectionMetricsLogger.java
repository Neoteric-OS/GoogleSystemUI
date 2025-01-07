package com.android.systemui.mediaprojection;

import android.media.projection.IMediaProjectionManager;
import android.os.RemoteException;
import android.util.Log;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaProjectionMetricsLogger {
    public final IMediaProjectionManager service;

    public MediaProjectionMetricsLogger(IMediaProjectionManager iMediaProjectionManager) {
        this.service = iMediaProjectionManager;
    }

    public final void notifyPermissionRequestDisplayed(int i) {
        try {
            this.service.notifyPermissionRequestDisplayed(i);
        } catch (RemoteException e) {
            Log.e("MediaProjectionMetricsLogger", "Error notifying server of projection displayed", e);
        }
    }

    public final void notifyProjectionInitiated(int i, SessionCreationSource sessionCreationSource) {
        try {
            IMediaProjectionManager iMediaProjectionManager = this.service;
            int ordinal = sessionCreationSource.ordinal();
            int i2 = 1;
            if (ordinal != 0) {
                if (ordinal != 1) {
                    i2 = 2;
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        i2 = 0;
                    }
                } else {
                    i2 = 3;
                }
            }
            iMediaProjectionManager.notifyPermissionRequestInitiated(i, i2);
        } catch (RemoteException e) {
            Log.e("MediaProjectionMetricsLogger", "Error notifying server of projection initiated", e);
        }
    }
}
