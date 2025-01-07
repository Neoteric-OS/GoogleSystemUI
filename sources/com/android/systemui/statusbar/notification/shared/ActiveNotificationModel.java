package com.android.systemui.statusbar.notification.shared;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.StatusBarIconView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveNotificationModel extends ActiveNotificationEntryModel {
    public final Icon aodIcon;
    public final int bucket;
    public final CallType callType;
    public final PendingIntent contentIntent;
    public final String groupKey;
    public final Integer instanceId;
    public final boolean isAmbient;
    public final boolean isGroupSummary;
    public final boolean isLastMessageFromReply;
    public final boolean isPulsing;
    public final boolean isRowDismissed;
    public final boolean isSilent;
    public final boolean isSuppressedFromStatusBar;
    public final String key;
    public final String packageName;
    public final Icon shelfIcon;
    public final StatusBarIconView statusBarChipIconView;
    public final Icon statusBarIcon;
    public final int uid;
    public final long whenTime;

    public ActiveNotificationModel(String str, String str2, long j, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Icon icon, Icon icon2, Icon icon3, StatusBarIconView statusBarIconView, int i, String str3, PendingIntent pendingIntent, Integer num, boolean z7, int i2, CallType callType) {
        this.key = str;
        this.groupKey = str2;
        this.whenTime = j;
        this.isAmbient = z;
        this.isRowDismissed = z2;
        this.isSilent = z3;
        this.isLastMessageFromReply = z4;
        this.isSuppressedFromStatusBar = z5;
        this.isPulsing = z6;
        this.aodIcon = icon;
        this.shelfIcon = icon2;
        this.statusBarIcon = icon3;
        this.statusBarChipIconView = statusBarIconView;
        this.uid = i;
        this.packageName = str3;
        this.contentIntent = pendingIntent;
        this.instanceId = num;
        this.isGroupSummary = z7;
        this.bucket = i2;
        this.callType = callType;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActiveNotificationModel)) {
            return false;
        }
        ActiveNotificationModel activeNotificationModel = (ActiveNotificationModel) obj;
        return Intrinsics.areEqual(this.key, activeNotificationModel.key) && Intrinsics.areEqual(this.groupKey, activeNotificationModel.groupKey) && this.whenTime == activeNotificationModel.whenTime && this.isAmbient == activeNotificationModel.isAmbient && this.isRowDismissed == activeNotificationModel.isRowDismissed && this.isSilent == activeNotificationModel.isSilent && this.isLastMessageFromReply == activeNotificationModel.isLastMessageFromReply && this.isSuppressedFromStatusBar == activeNotificationModel.isSuppressedFromStatusBar && this.isPulsing == activeNotificationModel.isPulsing && Intrinsics.areEqual(this.aodIcon, activeNotificationModel.aodIcon) && Intrinsics.areEqual(this.shelfIcon, activeNotificationModel.shelfIcon) && Intrinsics.areEqual(this.statusBarIcon, activeNotificationModel.statusBarIcon) && Intrinsics.areEqual(this.statusBarChipIconView, activeNotificationModel.statusBarChipIconView) && this.uid == activeNotificationModel.uid && this.packageName.equals(activeNotificationModel.packageName) && Intrinsics.areEqual(this.contentIntent, activeNotificationModel.contentIntent) && Intrinsics.areEqual(this.instanceId, activeNotificationModel.instanceId) && this.isGroupSummary == activeNotificationModel.isGroupSummary && this.bucket == activeNotificationModel.bucket && this.callType == activeNotificationModel.callType;
    }

    public final int hashCode() {
        int hashCode = this.key.hashCode() * 31;
        String str = this.groupKey;
        int m = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m((hashCode + (str == null ? 0 : str.hashCode())) * 31, 31, this.whenTime), 31, this.isAmbient), 31, this.isRowDismissed), 31, this.isSilent), 31, this.isLastMessageFromReply), 31, this.isSuppressedFromStatusBar), 31, this.isPulsing);
        Icon icon = this.aodIcon;
        int hashCode2 = (m + (icon == null ? 0 : icon.hashCode())) * 31;
        Icon icon2 = this.shelfIcon;
        int hashCode3 = (hashCode2 + (icon2 == null ? 0 : icon2.hashCode())) * 31;
        Icon icon3 = this.statusBarIcon;
        int hashCode4 = (hashCode3 + (icon3 == null ? 0 : icon3.hashCode())) * 31;
        StatusBarIconView statusBarIconView = this.statusBarChipIconView;
        int m2 = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.packageName, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.uid, (hashCode4 + (statusBarIconView == null ? 0 : statusBarIconView.hashCode())) * 31, 31), 31);
        PendingIntent pendingIntent = this.contentIntent;
        int hashCode5 = (m2 + (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 31;
        Integer num = this.instanceId;
        return this.callType.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.bucket, TransitionData$$ExternalSyntheticOutline0.m((hashCode5 + (num != null ? num.hashCode() : 0)) * 31, 31, this.isGroupSummary), 31);
    }

    public final String toString() {
        return "ActiveNotificationModel(key=" + this.key + ", groupKey=" + this.groupKey + ", whenTime=" + this.whenTime + ", isAmbient=" + this.isAmbient + ", isRowDismissed=" + this.isRowDismissed + ", isSilent=" + this.isSilent + ", isLastMessageFromReply=" + this.isLastMessageFromReply + ", isSuppressedFromStatusBar=" + this.isSuppressedFromStatusBar + ", isPulsing=" + this.isPulsing + ", aodIcon=" + this.aodIcon + ", shelfIcon=" + this.shelfIcon + ", statusBarIcon=" + this.statusBarIcon + ", statusBarChipIconView=" + this.statusBarChipIconView + ", uid=" + this.uid + ", packageName=" + this.packageName + ", contentIntent=" + this.contentIntent + ", instanceId=" + this.instanceId + ", isGroupSummary=" + this.isGroupSummary + ", bucket=" + this.bucket + ", callType=" + this.callType + ")";
    }
}
