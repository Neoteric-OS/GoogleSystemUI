package com.android.settingslib.media;

import android.content.Context;
import android.os.SystemProperties;
import android.util.SparseIntArray;
import com.android.wm.shell.R;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class DeviceIconUtil {
    public static final SparseIntArray AUDIO_DEVICE_TO_MEDIA_ROUTE_TYPE;
    public final Context mContext;
    public final boolean mIsTablet;
    public final boolean mIsTv;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        AUDIO_DEVICE_TO_MEDIA_ROUTE_TYPE = sparseIntArray;
        sparseIntArray.put(11, 11);
        sparseIntArray.put(22, 22);
        sparseIntArray.put(12, 12);
        sparseIntArray.put(13, 13);
        sparseIntArray.put(9, 9);
        sparseIntArray.put(10, 10);
        sparseIntArray.put(29, 29);
        sparseIntArray.put(3, 3);
        sparseIntArray.put(4, 4);
        sparseIntArray.put(2, 2);
    }

    public DeviceIconUtil(Context context) {
        Objects.requireNonNull(context);
        this.mContext = context;
        context.getPackageManager().hasSystemFeature("android.software.leanback");
        this.mIsTv = false;
        this.mIsTablet = Arrays.asList(SystemProperties.get("ro.build.characteristics").split(",")).contains("tablet");
    }

    public final int getIconResIdFromMediaRouteType(int i) {
        if (!this.mIsTv) {
            int i2 = this.mIsTablet ? R.drawable.ic_media_tablet : R.drawable.ic_smartphone;
            if (i == 3 || i == 4 || i == 22) {
                return R.drawable.ic_headphone;
            }
            if (i != 29) {
                switch (i) {
                }
                return R.drawable.ic_headphone;
            }
            return R.drawable.ic_external_display;
        }
        if (i != 2) {
            if (i == 3 || i == 4) {
                return R.drawable.ic_wired_device;
            }
            if (i == 22) {
                return R.drawable.ic_headphone;
            }
            if (i != 29) {
                switch (i) {
                }
                return R.drawable.ic_headphone;
            }
            return R.drawable.ic_hdmi;
        }
        return R.drawable.ic_tv;
        return R.drawable.ic_dock_device;
    }

    public DeviceIconUtil(boolean z) {
        this.mContext = null;
        this.mIsTv = z;
        this.mIsTablet = false;
    }
}
