package com.android.systemui.media.dialog;

import android.content.Context;
import android.util.Log;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.settingslib.media.MediaDevice;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaOutputMetricLogger {
    public static final boolean DEBUG = Log.isLoggable("MediaOutputMetricLogger", 3);
    public int mConnectedBluetoothDeviceCount;
    public final Context mContext;
    public final String mPackageName;
    public int mRemoteDeviceCount;
    public MediaDevice mSourceDevice;
    public MediaDevice mTargetDevice;
    public int mWiredDeviceCount;

    public MediaOutputMetricLogger(Context context, String str) {
        this.mContext = context;
        this.mPackageName = str;
    }

    public static int getInteractionDeviceType(MediaDevice mediaDevice) {
        if (mediaDevice == null) {
            return 0;
        }
        int deviceType = mediaDevice.getDeviceType();
        if (deviceType == 1) {
            return 1;
        }
        if (deviceType == 2) {
            return 200;
        }
        if (deviceType == 3) {
            return 100;
        }
        if (deviceType == 5) {
            return 300;
        }
        if (deviceType != 6) {
            return deviceType != 7 ? 0 : 500;
        }
        return 400;
    }

    public static int getLoggingDeviceType(MediaDevice mediaDevice) {
        if (mediaDevice == null) {
            return 0;
        }
        switch (mediaDevice.getDeviceType()) {
        }
        return 0;
    }

    public final String getLoggingPackageName() {
        String str = this.mPackageName;
        if (str == null || str.isEmpty()) {
            return "";
        }
        try {
            int i = this.mContext.getPackageManager().getApplicationInfo(str, 0).flags;
            return ((i & 1) == 0 && (i & 128) == 0) ? "" : str;
        } catch (Exception unused) {
            Log.e("MediaOutputMetricLogger", str + " is invalid.");
            return "";
        }
    }

    public final void updateLoggingMediaItemCount(List list) {
        this.mRemoteDeviceCount = 0;
        this.mConnectedBluetoothDeviceCount = 0;
        this.mWiredDeviceCount = 0;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = (MediaItem) it.next();
            if (mediaItem.mMediaDeviceOptional.isPresent() && ((MediaDevice) mediaItem.mMediaDeviceOptional.get()).isConnected()) {
                int deviceType = ((MediaDevice) mediaItem.mMediaDeviceOptional.get()).getDeviceType();
                if (deviceType == 2 || deviceType == 3) {
                    this.mWiredDeviceCount++;
                } else if (deviceType == 5) {
                    this.mConnectedBluetoothDeviceCount++;
                } else if (deviceType == 6 || deviceType == 7) {
                    this.mRemoteDeviceCount++;
                }
            }
        }
        if (DEBUG) {
            StringBuilder sb = new StringBuilder("connected devices: wired: ");
            sb.append(this.mWiredDeviceCount);
            sb.append(" bluetooth: ");
            sb.append(this.mConnectedBluetoothDeviceCount);
            sb.append(" remote: ");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, this.mRemoteDeviceCount, "MediaOutputMetricLogger");
        }
    }
}
