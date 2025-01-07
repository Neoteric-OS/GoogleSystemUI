package com.android.systemui.media.controls.ui.controller;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaPlayerData {
    public static final MediaData EMPTY;
    public static final MediaPlayerData INSTANCE = new MediaPlayerData();
    public static final MediaPlayerData$special$$inlined$thenByDescending$1 comparator = null;
    public static boolean isSwipedAway;
    public static final Map mediaData;
    public static final TreeMap mediaPlayers;
    public static boolean shouldPrioritizeSs;
    public static SmartspaceMediaData smartspaceMediaData;
    public static final LinkedHashMap visibleMediaPlayers;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaSortKey {
        public final MediaData data;
        public final boolean isSsMediaRec;
        public final boolean isSsReactivated;
        public final String key;
        public final long updateTime;

        public MediaSortKey(boolean z, MediaData mediaData, String str, long j, boolean z2) {
            this.isSsMediaRec = z;
            this.data = mediaData;
            this.key = str;
            this.updateTime = j;
            this.isSsReactivated = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaSortKey)) {
                return false;
            }
            MediaSortKey mediaSortKey = (MediaSortKey) obj;
            return this.isSsMediaRec == mediaSortKey.isSsMediaRec && Intrinsics.areEqual(this.data, mediaSortKey.data) && Intrinsics.areEqual(this.key, mediaSortKey.key) && this.updateTime == mediaSortKey.updateTime && this.isSsReactivated == mediaSortKey.isSsReactivated;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isSsReactivated) + Scale$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.key, (this.data.hashCode() + (Boolean.hashCode(this.isSsMediaRec) * 31)) * 31, 31), 31, this.updateTime);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("MediaSortKey(isSsMediaRec=");
            sb.append(this.isSsMediaRec);
            sb.append(", data=");
            sb.append(this.data);
            sb.append(", key=");
            sb.append(this.key);
            sb.append(", updateTime=");
            sb.append(this.updateTime);
            sb.append(", isSsReactivated=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isSsReactivated, ")");
        }
    }

    static {
        EmptyList emptyList = EmptyList.INSTANCE;
        EMPTY = new MediaData(-1, false, (String) null, (Icon) null, (CharSequence) null, (CharSequence) null, (Icon) null, (List) emptyList, (List) emptyList, (MediaButton) null, "INVALID", (MediaSession.Token) null, (PendingIntent) null, (MediaDeviceData) null, true, (Runnable) null, 0, (String) null, false, (Boolean) null, false, 0L, 0L, InstanceId.fakeInstanceId(-1), -1, false, (Double) null, 0, 1023345152);
        mediaPlayers = new TreeMap(new MediaPlayerData$special$$inlined$thenByDescending$1(new MediaPlayerData$special$$inlined$thenByDescending$1(new MediaPlayerData$special$$inlined$thenByDescending$1(new MediaPlayerData$special$$inlined$thenByDescending$1(new MediaPlayerData$special$$inlined$thenByDescending$1(new MediaPlayerData$special$$inlined$thenByDescending$1(new MediaPlayerData$special$$inlined$thenByDescending$1(new MediaPlayerData$special$$inlined$thenByDescending$1(new MediaPlayerData$special$$inlined$compareByDescending$1())), (byte) 0), (char) 0), 0), (short) 0), (byte) 0, false), (byte) 0, (byte) 0));
        mediaData = new LinkedHashMap();
        visibleMediaPlayers = new LinkedHashMap();
    }

    private MediaPlayerData() {
    }

    public static MediaControlPanel getMediaPlayer(String str) {
        MediaSortKey mediaSortKey = (MediaSortKey) mediaData.get(str);
        if (mediaSortKey != null) {
            return (MediaControlPanel) mediaPlayers.get(mediaSortKey);
        }
        return null;
    }

    public static int getMediaPlayerIndex(String str) {
        MediaSortKey mediaSortKey = (MediaSortKey) mediaData.get(str);
        int i = 0;
        for (Object obj : mediaPlayers.entrySet()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), mediaSortKey)) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    public static MediaControlPanel removeMediaPlayer(String str, boolean z) {
        MediaSortKey mediaSortKey = (MediaSortKey) mediaData.remove(str);
        if (mediaSortKey == null) {
            return null;
        }
        if (mediaSortKey.isSsMediaRec) {
            smartspaceMediaData = null;
        }
        if (z) {
            visibleMediaPlayers.remove(str);
        }
        return (MediaControlPanel) mediaPlayers.remove(mediaSortKey);
    }

    public static String smartspaceMediaKey() {
        for (Map.Entry entry : mediaData.entrySet()) {
            if (((MediaSortKey) entry.getValue()).isSsMediaRec) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    public final void addMediaPlayer(String str, MediaData mediaData2, MediaControlPanel mediaControlPanel, boolean z, MediaCarouselControllerLogger mediaCarouselControllerLogger) {
        MediaControlPanel removeMediaPlayer = removeMediaPlayer(str, false);
        if (removeMediaPlayer != null && !removeMediaPlayer.equals(mediaControlPanel)) {
            if (mediaCarouselControllerLogger != null) {
                mediaCarouselControllerLogger.logPotentialMemoryLeak(str);
            }
            removeMediaPlayer.onDestroy();
        }
        MediaSortKey mediaSortKey = new MediaSortKey(false, mediaData2, str, System.currentTimeMillis(), z);
        mediaData.put(str, mediaSortKey);
        mediaPlayers.put(mediaSortKey, mediaControlPanel);
        visibleMediaPlayers.put(str, mediaSortKey);
    }

    public final void addMediaRecommendation(String str, SmartspaceMediaData smartspaceMediaData2, MediaControlPanel mediaControlPanel, boolean z, MediaCarouselControllerLogger mediaCarouselControllerLogger) {
        shouldPrioritizeSs = z;
        MediaControlPanel removeMediaPlayer = removeMediaPlayer(str, false);
        if (removeMediaPlayer != null && !removeMediaPlayer.equals(mediaControlPanel)) {
            if (mediaCarouselControllerLogger != null) {
                mediaCarouselControllerLogger.logPotentialMemoryLeak(str);
            }
            removeMediaPlayer.onDestroy();
        }
        Boolean bool = Boolean.FALSE;
        MediaSortKey mediaSortKey = new MediaSortKey(true, MediaData.copy$default(EMPTY, null, null, null, null, null, null, smartspaceMediaData2.isActive, null, false, bool, 0L, 0L, null, 0, 1072676863), str, System.currentTimeMillis(), true);
        mediaData.put(str, mediaSortKey);
        mediaPlayers.put(mediaSortKey, mediaControlPanel);
        visibleMediaPlayers.put(str, mediaSortKey);
        smartspaceMediaData = smartspaceMediaData2;
    }

    public final void clear() {
        mediaData.clear();
        mediaPlayers.clear();
        visibleMediaPlayers.clear();
    }
}
