package com.android.settingslib.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRoute2Info;
import android.media.RouteListingPreference;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InfoMediaDevice extends MediaDevice {
    public InfoMediaDevice(Context context, MediaRoute2Info mediaRoute2Info, RouteListingPreference.Item item) {
        super(context, mediaRoute2Info, item);
        initDeviceRecord();
    }

    public int getDrawableResIdByType() {
        int type = this.mRouteInfo.getType();
        if (type == 1001) {
            return R.drawable.ic_media_display_device;
        }
        if (type == 2000) {
            return R.drawable.ic_media_group_device;
        }
        switch (type) {
            case 1004:
                return R.drawable.ic_media_tablet;
            case 1005:
                return R.drawable.ic_dock_device;
            case 1006:
                return R.drawable.ic_media_computer;
            case 1007:
                return R.drawable.ic_media_game_console;
            case 1008:
                return R.drawable.ic_media_car;
            case 1009:
                return R.drawable.ic_media_smartwatch;
            case 1010:
                return R.drawable.ic_smartphone;
            default:
                return R.drawable.ic_media_speaker_device;
        }
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final Drawable getIcon() {
        return getIconWithoutBackground();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final Drawable getIconWithoutBackground() {
        return this.mContext.getDrawable(getDrawableResIdByType());
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final String getId() {
        return this.mRouteInfo.getId();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final String getName() {
        return this.mRouteInfo.getName().toString();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final boolean isConnected() {
        return true;
    }
}
