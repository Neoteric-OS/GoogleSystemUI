package com.android.systemui.media.controls.data.model;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaSortKeyModel {
    public final boolean active;
    public final InstanceId instanceId;
    public final Boolean isPlaying;
    public final boolean isPrioritizedRec;
    public final boolean isResume;
    public final long lastActive;
    public final String notificationKey;
    public final int playbackLocation;
    public final long updateTime;

    public MediaSortKeyModel(boolean z, Boolean bool, int i, boolean z2, boolean z3, long j, String str, long j2, InstanceId instanceId) {
        this.isPrioritizedRec = z;
        this.isPlaying = bool;
        this.playbackLocation = i;
        this.active = z2;
        this.isResume = z3;
        this.lastActive = j;
        this.notificationKey = str;
        this.updateTime = j2;
        this.instanceId = instanceId;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSortKeyModel)) {
            return false;
        }
        MediaSortKeyModel mediaSortKeyModel = (MediaSortKeyModel) obj;
        return this.isPrioritizedRec == mediaSortKeyModel.isPrioritizedRec && Intrinsics.areEqual(this.isPlaying, mediaSortKeyModel.isPlaying) && this.playbackLocation == mediaSortKeyModel.playbackLocation && this.active == mediaSortKeyModel.active && this.isResume == mediaSortKeyModel.isResume && this.lastActive == mediaSortKeyModel.lastActive && Intrinsics.areEqual(this.notificationKey, mediaSortKeyModel.notificationKey) && this.updateTime == mediaSortKeyModel.updateTime && Intrinsics.areEqual(this.instanceId, mediaSortKeyModel.instanceId);
    }

    public final int hashCode() {
        int hashCode = Boolean.hashCode(this.isPrioritizedRec) * 31;
        Boolean bool = this.isPlaying;
        int m = Scale$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.playbackLocation, (hashCode + (bool == null ? 0 : bool.hashCode())) * 31, 31), 31, this.active), 31, this.isResume), 31, this.lastActive);
        String str = this.notificationKey;
        int m2 = Scale$$ExternalSyntheticOutline0.m((m + (str == null ? 0 : str.hashCode())) * 31, 31, this.updateTime);
        InstanceId instanceId = this.instanceId;
        return m2 + (instanceId != null ? instanceId.hashCode() : 0);
    }

    public final String toString() {
        return "MediaSortKeyModel(isPrioritizedRec=" + this.isPrioritizedRec + ", isPlaying=" + this.isPlaying + ", playbackLocation=" + this.playbackLocation + ", active=" + this.active + ", isResume=" + this.isResume + ", lastActive=" + this.lastActive + ", notificationKey=" + this.notificationKey + ", updateTime=" + this.updateTime + ", instanceId=" + this.instanceId + ")";
    }
}
