package com.android.systemui.media.taptotransfer.receiver;

import android.graphics.drawable.Drawable;
import android.media.MediaRoute2Info;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import com.android.systemui.temporarydisplay.TemporaryViewInfo;
import com.android.systemui.temporarydisplay.ViewPriority;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChipReceiverInfo extends TemporaryViewInfo {
    public final Drawable appIconDrawableOverride;
    public final CharSequence appNameOverride;
    public final String id;
    public final InstanceId instanceId;
    public final ViewPriority priority;
    public final MediaRoute2Info routeInfo;
    public final String wakeReason;
    public final String windowTitle;

    public ChipReceiverInfo(MediaRoute2Info mediaRoute2Info, Drawable drawable, CharSequence charSequence, String str, InstanceId instanceId) {
        ViewPriority viewPriority = ViewPriority.NORMAL;
        this.routeInfo = mediaRoute2Info;
        this.appIconDrawableOverride = drawable;
        this.appNameOverride = charSequence;
        this.windowTitle = "Media Transfer Chip View (Receiver)";
        this.wakeReason = "MEDIA_TRANSFER_ACTIVATED_RECEIVER";
        this.id = str;
        this.priority = viewPriority;
        this.instanceId = instanceId;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChipReceiverInfo)) {
            return false;
        }
        ChipReceiverInfo chipReceiverInfo = (ChipReceiverInfo) obj;
        return Intrinsics.areEqual(this.routeInfo, chipReceiverInfo.routeInfo) && Intrinsics.areEqual(this.appIconDrawableOverride, chipReceiverInfo.appIconDrawableOverride) && Intrinsics.areEqual(this.appNameOverride, chipReceiverInfo.appNameOverride) && Intrinsics.areEqual(this.windowTitle, chipReceiverInfo.windowTitle) && Intrinsics.areEqual(this.wakeReason, chipReceiverInfo.wakeReason) && Intrinsics.areEqual(this.id, chipReceiverInfo.id) && this.priority == chipReceiverInfo.priority && Intrinsics.areEqual(this.instanceId, chipReceiverInfo.instanceId);
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewInfo
    public final String getId() {
        return this.id;
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewInfo
    public final InstanceId getInstanceId() {
        return this.instanceId;
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewInfo
    public final ViewPriority getPriority() {
        return this.priority;
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewInfo
    public final String getWakeReason() {
        return this.wakeReason;
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewInfo
    public final String getWindowTitle() {
        return this.windowTitle;
    }

    public final int hashCode() {
        int hashCode = this.routeInfo.hashCode() * 31;
        Drawable drawable = this.appIconDrawableOverride;
        int hashCode2 = (hashCode + (drawable == null ? 0 : drawable.hashCode())) * 31;
        CharSequence charSequence = this.appNameOverride;
        return this.instanceId.hashCode() + ((this.priority.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.id, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.wakeReason, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.windowTitle, (hashCode2 + (charSequence != null ? charSequence.hashCode() : 0)) * 31, 31), 31), 31)) * 31);
    }

    public final String toString() {
        MediaRoute2Info mediaRoute2Info = this.routeInfo;
        Drawable drawable = this.appIconDrawableOverride;
        CharSequence charSequence = this.appNameOverride;
        return "ChipReceiverInfo(routeInfo=" + mediaRoute2Info + ", appIconDrawableOverride=" + drawable + ", appNameOverride=" + ((Object) charSequence) + ", windowTitle=" + this.windowTitle + ", wakeReason=" + this.wakeReason + ", id=" + this.id + ", priority=" + this.priority + ", instanceId=" + this.instanceId + ")";
    }
}
