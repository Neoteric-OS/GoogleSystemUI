package com.android.settingslib.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRoute2Info;
import android.media.RouteListingPreference;
import android.os.SystemProperties;
import com.android.app.viewcapture.data.ViewNode;
import com.android.wm.shell.R;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PhoneMediaDevice extends MediaDevice {
    public final DeviceIconUtil mDeviceIconUtil;

    public PhoneMediaDevice(Context context, MediaRoute2Info mediaRoute2Info, RouteListingPreference.Item item) {
        super(context, mediaRoute2Info, item);
        this.mDeviceIconUtil = new DeviceIconUtil(context);
        initDeviceRecord();
    }

    public static String getMediaTransferThisDeviceName(Context context) {
        context.getPackageManager().hasSystemFeature("android.software.leanback");
        return Arrays.asList(SystemProperties.get("ro.build.characteristics").split(",")).contains("tablet") ? context.getString(R.string.media_transfer_this_device_name_tablet) : context.getString(R.string.media_transfer_this_device_name);
    }

    public int getDrawableResId() {
        return this.mDeviceIconUtil.getIconResIdFromMediaRouteType(this.mRouteInfo.getType());
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final Drawable getIcon() {
        return getIconWithoutBackground();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final Drawable getIconWithoutBackground() {
        return this.mContext.getDrawable(getDrawableResId());
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final String getId() {
        return this.mRouteInfo.getId();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final String getName() {
        String mediaTransferThisDeviceName;
        Context context = this.mContext;
        MediaRoute2Info mediaRoute2Info = this.mRouteInfo;
        context.getPackageManager().hasSystemFeature("android.software.leanback");
        int type = mediaRoute2Info.getType();
        if (type == 2) {
            mediaTransferThisDeviceName = getMediaTransferThisDeviceName(context);
        } else if (type == 3 || type == 4) {
            mediaTransferThisDeviceName = context.getString(R.string.media_transfer_wired_headphone_name);
        } else {
            if (type != 22) {
                if (type != 29) {
                    switch (type) {
                        case 9:
                            mediaTransferThisDeviceName = context.getString(R.string.media_transfer_external_device_name);
                            break;
                        case 10:
                            break;
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            break;
                        case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                            mediaTransferThisDeviceName = context.getString(R.string.media_transfer_dock_speaker_device_name);
                            break;
                        default:
                            mediaTransferThisDeviceName = context.getString(R.string.media_transfer_default_device_name);
                            break;
                    }
                }
                mediaTransferThisDeviceName = context.getString(R.string.media_transfer_external_device_name);
            }
            mediaTransferThisDeviceName = context.getString(R.string.media_transfer_wired_headphone_name);
        }
        return mediaTransferThisDeviceName.toString();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final int getSelectionBehavior() {
        return 1;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final boolean isConnected() {
        return true;
    }
}
