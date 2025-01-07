package com.android.systemui.media.controls.shared.model;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaData {
    public final List actions;
    public final List actionsToShowInCompact;
    public boolean active;
    public final String app;
    public final Icon appIcon;
    public final int appUid;
    public final CharSequence artist;
    public final Icon artwork;
    public final PendingIntent clickIntent;
    public final long createdTimestampMillis;
    public final MediaDeviceData device;
    public boolean hasCheckedForResume;
    public final boolean initialized;
    public final InstanceId instanceId;
    public final boolean isClearable;
    public final boolean isExplicit;
    public final Boolean isPlaying;
    public long lastActive;
    public final String notificationKey;
    public final String packageName;
    public final int playbackLocation;
    public Runnable resumeAction;
    public final Double resumeProgress;
    public final boolean resumption;
    public final MediaButton semanticActions;
    public int smartspaceId;
    public final CharSequence song;
    public final MediaSession.Token token;
    public final int userId;

    public MediaData(int i, boolean z, String str, Icon icon, CharSequence charSequence, CharSequence charSequence2, Icon icon2, List list, List list2, MediaButton mediaButton, String str2, MediaSession.Token token, PendingIntent pendingIntent, MediaDeviceData mediaDeviceData, boolean z2, Runnable runnable, int i2, boolean z3, String str3, boolean z4, Boolean bool, boolean z5, long j, long j2, InstanceId instanceId, int i3, boolean z6, Double d, int i4) {
        this.userId = i;
        this.initialized = z;
        this.app = str;
        this.appIcon = icon;
        this.artist = charSequence;
        this.song = charSequence2;
        this.artwork = icon2;
        this.actions = list;
        this.actionsToShowInCompact = list2;
        this.semanticActions = mediaButton;
        this.packageName = str2;
        this.token = token;
        this.clickIntent = pendingIntent;
        this.device = mediaDeviceData;
        this.active = z2;
        this.resumeAction = runnable;
        this.playbackLocation = i2;
        this.resumption = z3;
        this.notificationKey = str3;
        this.hasCheckedForResume = z4;
        this.isPlaying = bool;
        this.isClearable = z5;
        this.lastActive = j;
        this.createdTimestampMillis = j2;
        this.instanceId = instanceId;
        this.appUid = i3;
        this.isExplicit = z6;
        this.resumeProgress = d;
        this.smartspaceId = i4;
    }

    public static MediaData copy$default(MediaData mediaData, EmptyList emptyList, List list, MediaButton mediaButton, String str, PendingIntent pendingIntent, MediaDeviceData mediaDeviceData, boolean z, Runnable runnable, boolean z2, Boolean bool, long j, long j2, InstanceId instanceId, int i, int i2) {
        int i3;
        boolean z3;
        String str2;
        boolean z4;
        int i4 = mediaData.userId;
        boolean z5 = mediaData.initialized;
        String str3 = mediaData.app;
        Icon icon = mediaData.appIcon;
        CharSequence charSequence = mediaData.artist;
        CharSequence charSequence2 = mediaData.song;
        Icon icon2 = mediaData.artwork;
        List list2 = (i2 & 128) != 0 ? mediaData.actions : emptyList;
        List list3 = (i2 & 256) != 0 ? mediaData.actionsToShowInCompact : list;
        MediaButton mediaButton2 = (i2 & 512) != 0 ? mediaData.semanticActions : mediaButton;
        String str4 = (i2 & 1024) != 0 ? mediaData.packageName : str;
        MediaSession.Token token = (i2 & 2048) != 0 ? mediaData.token : null;
        PendingIntent pendingIntent2 = (i2 & 4096) != 0 ? mediaData.clickIntent : pendingIntent;
        MediaDeviceData mediaDeviceData2 = (i2 & 8192) != 0 ? mediaData.device : mediaDeviceData;
        boolean z6 = (i2 & 16384) != 0 ? mediaData.active : z;
        Runnable runnable2 = (i2 & 32768) != 0 ? mediaData.resumeAction : runnable;
        int i5 = mediaData.playbackLocation;
        if ((i2 & 131072) != 0) {
            i3 = i5;
            z3 = mediaData.resumption;
        } else {
            i3 = i5;
            z3 = true;
        }
        String str5 = mediaData.notificationKey;
        if ((i2 & 524288) != 0) {
            str2 = str5;
            z4 = mediaData.hasCheckedForResume;
        } else {
            str2 = str5;
            z4 = z2;
        }
        Boolean bool2 = (1048576 & i2) != 0 ? mediaData.isPlaying : bool;
        boolean z7 = (2097152 & i2) != 0 ? mediaData.isClearable : true;
        PendingIntent pendingIntent3 = pendingIntent2;
        long j3 = (4194304 & i2) != 0 ? mediaData.lastActive : j;
        long j4 = (8388608 & i2) != 0 ? mediaData.createdTimestampMillis : j2;
        InstanceId instanceId2 = (16777216 & i2) != 0 ? mediaData.instanceId : instanceId;
        int i6 = (i2 & 33554432) != 0 ? mediaData.appUid : i;
        boolean z8 = mediaData.isExplicit;
        Double d = mediaData.resumeProgress;
        int i7 = mediaData.smartspaceId;
        mediaData.getClass();
        mediaData.getClass();
        return new MediaData(i4, z5, str3, icon, charSequence, charSequence2, icon2, list2, list3, mediaButton2, str4, token, pendingIntent3, mediaDeviceData2, z6, runnable2, i3, z3, str2, z4, bool2, z7, j3, j4, instanceId2, i6, z8, d, i7);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaData)) {
            return false;
        }
        MediaData mediaData = (MediaData) obj;
        return this.userId == mediaData.userId && this.initialized == mediaData.initialized && Intrinsics.areEqual(this.app, mediaData.app) && Intrinsics.areEqual(this.appIcon, mediaData.appIcon) && Intrinsics.areEqual(this.artist, mediaData.artist) && Intrinsics.areEqual(this.song, mediaData.song) && Intrinsics.areEqual(this.artwork, mediaData.artwork) && Intrinsics.areEqual(this.actions, mediaData.actions) && Intrinsics.areEqual(this.actionsToShowInCompact, mediaData.actionsToShowInCompact) && Intrinsics.areEqual(this.semanticActions, mediaData.semanticActions) && Intrinsics.areEqual(this.packageName, mediaData.packageName) && Intrinsics.areEqual(this.token, mediaData.token) && Intrinsics.areEqual(this.clickIntent, mediaData.clickIntent) && Intrinsics.areEqual(this.device, mediaData.device) && this.active == mediaData.active && Intrinsics.areEqual(this.resumeAction, mediaData.resumeAction) && this.playbackLocation == mediaData.playbackLocation && this.resumption == mediaData.resumption && Intrinsics.areEqual(this.notificationKey, mediaData.notificationKey) && this.hasCheckedForResume == mediaData.hasCheckedForResume && Intrinsics.areEqual(this.isPlaying, mediaData.isPlaying) && this.isClearable == mediaData.isClearable && this.lastActive == mediaData.lastActive && this.createdTimestampMillis == mediaData.createdTimestampMillis && Intrinsics.areEqual(this.instanceId, mediaData.instanceId) && this.appUid == mediaData.appUid && this.isExplicit == mediaData.isExplicit && Intrinsics.areEqual(this.resumeProgress, mediaData.resumeProgress) && this.smartspaceId == mediaData.smartspaceId;
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m(Integer.hashCode(this.userId) * 31, 31, this.initialized);
        String str = this.app;
        int hashCode = (m + (str == null ? 0 : str.hashCode())) * 31;
        Icon icon = this.appIcon;
        int hashCode2 = (hashCode + (icon == null ? 0 : icon.hashCode())) * 31;
        CharSequence charSequence = this.artist;
        int hashCode3 = (hashCode2 + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
        CharSequence charSequence2 = this.song;
        int hashCode4 = (hashCode3 + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
        Icon icon2 = this.artwork;
        int m2 = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((hashCode4 + (icon2 == null ? 0 : icon2.hashCode())) * 31, 31, this.actions), 31, this.actionsToShowInCompact);
        MediaButton mediaButton = this.semanticActions;
        int m3 = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.packageName, (m2 + (mediaButton == null ? 0 : mediaButton.hashCode())) * 31, 31);
        MediaSession.Token token = this.token;
        int hashCode5 = (m3 + (token == null ? 0 : token.hashCode())) * 31;
        PendingIntent pendingIntent = this.clickIntent;
        int hashCode6 = (hashCode5 + (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 31;
        MediaDeviceData mediaDeviceData = this.device;
        int m4 = TransitionData$$ExternalSyntheticOutline0.m((hashCode6 + (mediaDeviceData == null ? 0 : mediaDeviceData.hashCode())) * 31, 31, this.active);
        Runnable runnable = this.resumeAction;
        int m5 = TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.playbackLocation, (m4 + (runnable == null ? 0 : runnable.hashCode())) * 31, 31), 31, this.resumption);
        String str2 = this.notificationKey;
        int m6 = TransitionData$$ExternalSyntheticOutline0.m((m5 + (str2 == null ? 0 : str2.hashCode())) * 31, 31, this.hasCheckedForResume);
        Boolean bool = this.isPlaying;
        int m7 = TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.appUid, (this.instanceId.hashCode() + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((m6 + (bool == null ? 0 : bool.hashCode())) * 31, 31, this.isClearable), 31, this.lastActive), 31, this.createdTimestampMillis)) * 31, 31), 31, this.isExplicit);
        Double d = this.resumeProgress;
        return Boolean.hashCode(false) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.smartspaceId, (m7 + (d == null ? 0 : d.hashCode())) * 31, 31);
    }

    public final String toString() {
        Icon icon = this.appIcon;
        CharSequence charSequence = this.artist;
        CharSequence charSequence2 = this.song;
        return "MediaData(userId=" + this.userId + ", initialized=" + this.initialized + ", app=" + this.app + ", appIcon=" + icon + ", artist=" + ((Object) charSequence) + ", song=" + ((Object) charSequence2) + ", artwork=" + this.artwork + ", actions=" + this.actions + ", actionsToShowInCompact=" + this.actionsToShowInCompact + ", semanticActions=" + this.semanticActions + ", packageName=" + this.packageName + ", token=" + this.token + ", clickIntent=" + this.clickIntent + ", device=" + this.device + ", active=" + this.active + ", resumeAction=" + this.resumeAction + ", playbackLocation=" + this.playbackLocation + ", resumption=" + this.resumption + ", notificationKey=" + this.notificationKey + ", hasCheckedForResume=" + this.hasCheckedForResume + ", isPlaying=" + this.isPlaying + ", isClearable=" + this.isClearable + ", lastActive=" + this.lastActive + ", createdTimestampMillis=" + this.createdTimestampMillis + ", instanceId=" + this.instanceId + ", appUid=" + this.appUid + ", isExplicit=" + this.isExplicit + ", resumeProgress=" + this.resumeProgress + ", smartspaceId=" + this.smartspaceId + ", isImpressed=false)";
    }

    public MediaData(int i, boolean z, String str, Icon icon, CharSequence charSequence, CharSequence charSequence2, Icon icon2, List list, List list2, MediaButton mediaButton, String str2, MediaSession.Token token, PendingIntent pendingIntent, MediaDeviceData mediaDeviceData, boolean z2, Runnable runnable, int i2, String str3, boolean z3, Boolean bool, boolean z4, long j, long j2, InstanceId instanceId, int i3, boolean z5, Double d, int i4, int i5) {
        this((i5 & 1) != 0 ? -1 : i, (i5 & 2) != 0 ? false : z, (i5 & 4) != 0 ? null : str, (i5 & 8) != 0 ? null : icon, (i5 & 16) != 0 ? null : charSequence, (i5 & 32) != 0 ? null : charSequence2, (i5 & 64) != 0 ? null : icon2, (i5 & 128) != 0 ? EmptyList.INSTANCE : list, (i5 & 256) != 0 ? EmptyList.INSTANCE : list2, (i5 & 512) != 0 ? null : mediaButton, (i5 & 1024) != 0 ? "INVALID" : str2, (i5 & 2048) != 0 ? null : token, (i5 & 4096) != 0 ? null : pendingIntent, (i5 & 8192) != 0 ? null : mediaDeviceData, (i5 & 16384) != 0 ? true : z2, (i5 & 32768) != 0 ? null : runnable, (i5 & 65536) != 0 ? 0 : i2, (i5 & 131072) == 0, (i5 & 262144) != 0 ? null : str3, (i5 & 524288) != 0 ? false : z3, (i5 & 1048576) != 0 ? null : bool, (i5 & 2097152) == 0 ? z4 : true, (i5 & 4194304) != 0 ? 0L : j, (i5 & 8388608) == 0 ? j2 : 0L, (i5 & 16777216) != 0 ? InstanceId.fakeInstanceId(-1) : instanceId, (i5 & 33554432) != 0 ? -1 : i3, (i5 & 67108864) != 0 ? false : z5, (i5 & 134217728) != 0 ? null : d, (i5 & 268435456) == 0 ? i4 : -1);
    }
}
