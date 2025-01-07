package com.android.settingslib.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRoute2Info;
import android.media.NearbyDevice;
import android.media.RouteListingPreference;
import android.text.TextUtils;
import android.util.Log;
import com.android.app.viewcapture.data.ViewNode;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaDevice implements Comparable {
    public int mConnectedRecord;
    public final Context mContext;
    public final RouteListingPreference.Item mItem;
    public int mRangeZone = 0;
    public final MediaRoute2Info mRouteInfo;
    public int mState;
    int mType;

    public MediaDevice(Context context, MediaRoute2Info mediaRoute2Info, RouteListingPreference.Item item) {
        this.mContext = context;
        this.mRouteInfo = mediaRoute2Info;
        this.mItem = item;
        if (mediaRoute2Info == null) {
            this.mType = 5;
            return;
        }
        int type = mediaRoute2Info.getType();
        if (type == 2) {
            this.mType = 1;
            return;
        }
        if (type == 3 || type == 4) {
            this.mType = 3;
            return;
        }
        if (type != 22) {
            if (type != 23 && type != 26) {
                if (type != 29) {
                    if (type == 1003) {
                        this.mType = 8;
                        return;
                    }
                    if (type == 2000) {
                        this.mType = 7;
                        return;
                    }
                    switch (type) {
                        case 8:
                            break;
                        case 9:
                        case 10:
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                            break;
                        default:
                            this.mType = 6;
                            break;
                    }
                    return;
                }
            }
            this.mType = 5;
            return;
        }
        this.mType = 2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        String str;
        MediaDevice mediaDevice = (MediaDevice) obj;
        if (mediaDevice == null) {
            return -1;
        }
        if (isConnected() ^ mediaDevice.isConnected()) {
            if (isConnected()) {
                return -1;
            }
        } else {
            if (this.mState == 4) {
                return -1;
            }
            if (mediaDevice.mState != 4) {
                int i = this.mType;
                int i2 = mediaDevice.mType;
                if (i == i2) {
                    if (isMutingExpectedDevice()) {
                        return -1;
                    }
                    if (!mediaDevice.isMutingExpectedDevice()) {
                        if (isFastPairDevice()) {
                            return -1;
                        }
                        if (!mediaDevice.isFastPairDevice()) {
                            if (isCarKitDevice()) {
                                return -1;
                            }
                            if (!mediaDevice.isCarKitDevice()) {
                                if (NearbyDevice.compareRangeZones(this.mRangeZone, mediaDevice.mRangeZone) != 0) {
                                    return NearbyDevice.compareRangeZones(this.mRangeZone, mediaDevice.mRangeZone);
                                }
                                ConnectionRecordManager connectionRecordManager = ConnectionRecordManager.getInstance();
                                synchronized (connectionRecordManager) {
                                    str = connectionRecordManager.mLastSelectedDevice;
                                }
                                if (TextUtils.equals(str, getId())) {
                                    return -1;
                                }
                                if (!TextUtils.equals(str, mediaDevice.getId())) {
                                    int i3 = this.mConnectedRecord;
                                    int i4 = mediaDevice.mConnectedRecord;
                                    return (i3 == i4 || (i4 <= 0 && i3 <= 0)) ? getName().compareToIgnoreCase(mediaDevice.getName()) : i4 - i3;
                                }
                            }
                        }
                    }
                } else if (i < i2) {
                    return -1;
                }
            }
        }
        return 1;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof MediaDevice) {
            return ((MediaDevice) obj).getId().equals(getId());
        }
        return false;
    }

    public int getCurrentVolume() {
        MediaRoute2Info mediaRoute2Info = this.mRouteInfo;
        if (mediaRoute2Info != null) {
            return mediaRoute2Info.getVolume();
        }
        Log.w("MediaDevice", "Unable to get current volume. RouteInfo is empty");
        return 0;
    }

    public final int getDeviceType() {
        return this.mType;
    }

    public abstract Drawable getIcon();

    public abstract Drawable getIconWithoutBackground();

    public abstract String getId();

    public abstract String getName();

    public int getSelectionBehavior() {
        RouteListingPreference.Item item = this.mItem;
        if (item != null) {
            return item.getSelectionBehavior();
        }
        return 1;
    }

    public final String getSubtextString() {
        RouteListingPreference.Item item = this.mItem;
        if (item == null) {
            return null;
        }
        Context context = this.mContext;
        int subText = item.getSubText();
        if (subText == 10000) {
            return (String) item.getCustomSubtextMessage();
        }
        switch (subText) {
            case 1:
                return context.getString(R.string.media_output_status_unknown_error);
            case 2:
                return context.getString(R.string.media_output_status_require_premium);
            case 3:
                return context.getString(R.string.media_output_status_not_support_downloads);
            case 4:
                return context.getString(R.string.media_output_status_try_after_ad);
            case 5:
                return context.getString(R.string.media_output_status_device_in_low_power_mode);
            case 6:
                return context.getString(R.string.media_output_status_unauthorized);
            case 7:
                return context.getString(R.string.media_output_status_track_unsupported);
            default:
                return "";
        }
    }

    public final boolean hasOngoingSession() {
        RouteListingPreference.Item item = this.mItem;
        return (item == null || (item.getFlags() & 1) == 0) ? false : true;
    }

    public final void initDeviceRecord() {
        int i;
        ConnectionRecordManager connectionRecordManager = ConnectionRecordManager.getInstance();
        Context context = this.mContext;
        synchronized (connectionRecordManager) {
            connectionRecordManager.mLastSelectedDevice = context.getSharedPreferences("seamless_transfer_record", 0).getString("last_selected_device", null);
        }
        ConnectionRecordManager connectionRecordManager2 = ConnectionRecordManager.getInstance();
        Context context2 = this.mContext;
        String id = getId();
        synchronized (connectionRecordManager2) {
            i = context2.getSharedPreferences("seamless_transfer_record", 0).getInt(id, 0);
        }
        this.mConnectedRecord = i;
    }

    public boolean isCarKitDevice() {
        return false;
    }

    public abstract boolean isConnected();

    public boolean isFastPairDevice() {
        return false;
    }

    public final boolean isHostForOngoingSession() {
        RouteListingPreference.Item item = this.mItem;
        int flags = item != null ? item.getFlags() : 0;
        return ((flags & 1) == 0 || (flags & 2) == 0) ? false : true;
    }

    public boolean isMutingExpectedDevice() {
        return false;
    }

    public final boolean isSuggestedDevice() {
        RouteListingPreference.Item item = this.mItem;
        return (item == null || (item.getFlags() & 4) == 0) ? false : true;
    }
}
