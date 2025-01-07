package com.android.systemui.statusbar.notification.collection.notifcollection;

import com.android.app.viewcapture.data.ViewNode;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotifCollectionLoggerKt {
    public static final String cancellationReasonDebugString(int i) {
        String str;
        switch (i) {
            case -1:
                str = "REASON_NOT_CANCELED";
                break;
            case 0:
                str = "REASON_UNKNOWN";
                break;
            case 1:
                str = "REASON_CLICK";
                break;
            case 2:
                str = "REASON_CANCEL";
                break;
            case 3:
                str = "REASON_CANCEL_ALL";
                break;
            case 4:
                str = "REASON_ERROR";
                break;
            case 5:
                str = "REASON_PACKAGE_CHANGED";
                break;
            case 6:
                str = "REASON_USER_STOPPED";
                break;
            case 7:
                str = "REASON_PACKAGE_BANNED";
                break;
            case 8:
                str = "REASON_APP_CANCEL";
                break;
            case 9:
                str = "REASON_APP_CANCEL_ALL";
                break;
            case 10:
                str = "REASON_LISTENER_CANCEL";
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                str = "REASON_LISTENER_CANCEL_ALL";
                break;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                str = "REASON_GROUP_SUMMARY_CANCELED";
                break;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                str = "REASON_GROUP_OPTIMIZATION";
                break;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                str = "REASON_PACKAGE_SUSPENDED";
                break;
            case 15:
                str = "REASON_PROFILE_TURNED_OFF";
                break;
            case 16:
                str = "REASON_UNAUTOBUNDLED";
                break;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                str = "REASON_CHANNEL_BANNED";
                break;
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                str = "REASON_SNOOZED";
                break;
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                str = "REASON_TIMEOUT";
                break;
            case 20:
                str = "REASON_CHANNEL_REMOVED";
                break;
            case 21:
                str = "REASON_CLEAR_DATA";
                break;
            case 22:
                str = "REASON_ASSISTANT_CANCEL";
                break;
            default:
                str = "unknown";
                break;
        }
        return i + ":" + str;
    }
}
