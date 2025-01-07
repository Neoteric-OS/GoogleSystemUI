package com.android.systemui.statusbar.pipeline.wifi.ui.model;

import android.R;
import android.content.Context;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.settingslib.AccessibilityContentDescriptions;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.log.table.Diffable;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.connectivity.WifiIcons;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface WifiIcon extends Diffable {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Hidden implements WifiIcon {
        public static final Hidden INSTANCE = new Hidden();

        public final String toString() {
            return "hidden";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Visible implements WifiIcon {
        public final ContentDescription.Loaded contentDescription;
        public final Icon.Resource icon;

        public Visible(int i, ContentDescription.Loaded loaded) {
            this.contentDescription = loaded;
            this.icon = new Icon.Resource(i, loaded);
        }

        public final String toString() {
            return String.valueOf(this.contentDescription.description);
        }
    }

    @Override // com.android.systemui.log.table.Diffable
    default void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
        if (Intrinsics.areEqual(((WifiIcon) obj).toString(), toString())) {
            return;
        }
        tableRowLoggerImpl.logChange("icon", toString());
    }

    @Override // com.android.systemui.log.table.Diffable
    default void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
        tableRowLoggerImpl.logChange("icon", toString());
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = null;

        public static WifiIcon fromModel(WifiNetworkModel wifiNetworkModel, Context context, boolean z) {
            Visible visible;
            int i;
            boolean z2 = wifiNetworkModel instanceof WifiNetworkModel.Unavailable;
            Hidden hidden = Hidden.INSTANCE;
            if (z2 || (wifiNetworkModel instanceof WifiNetworkModel.Invalid) || (wifiNetworkModel instanceof WifiNetworkModel.CarrierMerged)) {
                return hidden;
            }
            if (wifiNetworkModel instanceof WifiNetworkModel.Inactive) {
                visible = new Visible(R.drawable.ic_volume, new ContentDescription.Loaded(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(context.getString(com.android.wm.shell.R.string.accessibility_no_wifi), ",", context.getString(com.android.wm.shell.R.string.data_connection_no_internet))));
            } else {
                if (!(wifiNetworkModel instanceof WifiNetworkModel.Active)) {
                    throw new NoWhenBranchMatchedException();
                }
                WifiNetworkModel.Active active = (WifiNetworkModel.Active) wifiNetworkModel;
                if (z) {
                    WifiNetworkModel.HotspotDeviceType hotspotDeviceType = WifiNetworkModel.HotspotDeviceType.NONE;
                    WifiNetworkModel.HotspotDeviceType hotspotDeviceType2 = active.hotspotDeviceType;
                    if (hotspotDeviceType2 != hotspotDeviceType) {
                        switch (hotspotDeviceType2.ordinal()) {
                            case 0:
                                throw new IllegalStateException("NONE checked earlier");
                            case 1:
                            case 2:
                            case 7:
                                i = com.android.wm.shell.R.drawable.ic_hotspot_phone;
                                break;
                            case 3:
                                i = com.android.wm.shell.R.drawable.ic_hotspot_tablet;
                                break;
                            case 4:
                                i = com.android.wm.shell.R.drawable.ic_hotspot_laptop;
                                break;
                            case 5:
                                i = com.android.wm.shell.R.drawable.ic_hotspot_watch;
                                break;
                            case 6:
                                i = com.android.wm.shell.R.drawable.ic_hotspot_auto;
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        return new Visible(i, new ContentDescription.Loaded(context.getString(com.android.wm.shell.R.string.accessibility_wifi_other_device)));
                    }
                }
                int[] iArr = AccessibilityContentDescriptions.WIFI_CONNECTION_STRENGTH;
                int i2 = active.level;
                String string = context.getString(iArr[i2]);
                visible = active.isValidated ? new Visible(WifiIcons.WIFI_FULL_ICONS[i2], new ContentDescription.Loaded(string)) : new Visible(WifiIcons.WIFI_NO_INTERNET_ICONS[i2], new ContentDescription.Loaded(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(string, ",", context.getString(com.android.wm.shell.R.string.data_connection_no_internet))));
            }
            return visible;
        }

        public static /* synthetic */ void getNO_INTERNET$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
        }
    }
}
