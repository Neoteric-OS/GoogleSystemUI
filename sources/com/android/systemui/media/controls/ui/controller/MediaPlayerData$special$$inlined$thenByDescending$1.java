package com.android.systemui.media.controls.ui.controller;

import com.android.systemui.media.controls.ui.controller.MediaPlayerData;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaPlayerData$special$$inlined$thenByDescending$1 implements Comparator {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object $this_thenByDescending;

    public MediaPlayerData$special$$inlined$thenByDescending$1(MediaPlayerData$special$$inlined$compareByDescending$1 mediaPlayerData$special$$inlined$compareByDescending$1) {
        this.$this_thenByDescending = mediaPlayerData$special$$inlined$compareByDescending$1;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                int compare = ((MediaPlayerData$special$$inlined$compareByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (compare != 0) {
                    return compare;
                }
                MediaPlayerData.MediaSortKey mediaSortKey = (MediaPlayerData.MediaSortKey) obj2;
                Boolean bool = mediaSortKey.data.isPlaying;
                Boolean bool2 = Boolean.TRUE;
                boolean z = false;
                Boolean valueOf = Boolean.valueOf(Intrinsics.areEqual(bool, bool2) && mediaSortKey.data.playbackLocation == 1);
                MediaPlayerData.MediaSortKey mediaSortKey2 = (MediaPlayerData.MediaSortKey) obj;
                if (Intrinsics.areEqual(mediaSortKey2.data.isPlaying, bool2) && mediaSortKey2.data.playbackLocation == 1) {
                    z = true;
                }
                return ComparisonsKt___ComparisonsJvmKt.compareValues(valueOf, Boolean.valueOf(z));
            case 1:
                int compare2 = ((MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare2 != 0 ? compare2 : ComparisonsKt___ComparisonsJvmKt.compareValues(Boolean.valueOf(((MediaPlayerData.MediaSortKey) obj2).data.active), Boolean.valueOf(((MediaPlayerData.MediaSortKey) obj).data.active));
            case 2:
                int compare3 = ((MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (compare3 != 0) {
                    return compare3;
                }
                MediaPlayerData.INSTANCE.getClass();
                return ComparisonsKt___ComparisonsJvmKt.compareValues(Boolean.valueOf(MediaPlayerData.shouldPrioritizeSs == ((MediaPlayerData.MediaSortKey) obj2).isSsMediaRec), Boolean.valueOf(MediaPlayerData.shouldPrioritizeSs == ((MediaPlayerData.MediaSortKey) obj).isSsMediaRec));
            case 3:
                int compare4 = ((MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare4 != 0 ? compare4 : ComparisonsKt___ComparisonsJvmKt.compareValues(Boolean.valueOf(!((MediaPlayerData.MediaSortKey) obj2).data.resumption), Boolean.valueOf(!((MediaPlayerData.MediaSortKey) obj).data.resumption));
            case 4:
                int compare5 = ((MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (compare5 != 0) {
                    return compare5;
                }
                return ComparisonsKt___ComparisonsJvmKt.compareValues(Boolean.valueOf(((MediaPlayerData.MediaSortKey) obj2).data.playbackLocation != 2), Boolean.valueOf(((MediaPlayerData.MediaSortKey) obj).data.playbackLocation != 2));
            case 5:
                int compare6 = ((MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare6 != 0 ? compare6 : ComparisonsKt___ComparisonsJvmKt.compareValues(Long.valueOf(((MediaPlayerData.MediaSortKey) obj2).data.lastActive), Long.valueOf(((MediaPlayerData.MediaSortKey) obj).data.lastActive));
            case 6:
                int compare7 = ((MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare7 != 0 ? compare7 : ComparisonsKt___ComparisonsJvmKt.compareValues(Long.valueOf(((MediaPlayerData.MediaSortKey) obj2).updateTime), Long.valueOf(((MediaPlayerData.MediaSortKey) obj).updateTime));
            default:
                int compare8 = ((MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare8 != 0 ? compare8 : ComparisonsKt___ComparisonsJvmKt.compareValues(((MediaPlayerData.MediaSortKey) obj2).data.notificationKey, ((MediaPlayerData.MediaSortKey) obj).data.notificationKey);
        }
    }

    public MediaPlayerData$special$$inlined$thenByDescending$1(MediaPlayerData$special$$inlined$thenByDescending$1 mediaPlayerData$special$$inlined$thenByDescending$1) {
        this.$this_thenByDescending = mediaPlayerData$special$$inlined$thenByDescending$1;
    }

    public MediaPlayerData$special$$inlined$thenByDescending$1(MediaPlayerData$special$$inlined$thenByDescending$1 mediaPlayerData$special$$inlined$thenByDescending$1, byte b) {
        this.$this_thenByDescending = mediaPlayerData$special$$inlined$thenByDescending$1;
    }

    public MediaPlayerData$special$$inlined$thenByDescending$1(MediaPlayerData$special$$inlined$thenByDescending$1 mediaPlayerData$special$$inlined$thenByDescending$1, byte b, byte b2) {
        this.$this_thenByDescending = mediaPlayerData$special$$inlined$thenByDescending$1;
    }

    public MediaPlayerData$special$$inlined$thenByDescending$1(MediaPlayerData$special$$inlined$thenByDescending$1 mediaPlayerData$special$$inlined$thenByDescending$1, byte b, boolean z) {
        this.$this_thenByDescending = mediaPlayerData$special$$inlined$thenByDescending$1;
    }

    public MediaPlayerData$special$$inlined$thenByDescending$1(MediaPlayerData$special$$inlined$thenByDescending$1 mediaPlayerData$special$$inlined$thenByDescending$1, char c) {
        this.$this_thenByDescending = mediaPlayerData$special$$inlined$thenByDescending$1;
    }

    public MediaPlayerData$special$$inlined$thenByDescending$1(MediaPlayerData$special$$inlined$thenByDescending$1 mediaPlayerData$special$$inlined$thenByDescending$1, int i) {
        this.$this_thenByDescending = mediaPlayerData$special$$inlined$thenByDescending$1;
    }

    public MediaPlayerData$special$$inlined$thenByDescending$1(MediaPlayerData$special$$inlined$thenByDescending$1 mediaPlayerData$special$$inlined$thenByDescending$1, short s) {
        this.$this_thenByDescending = mediaPlayerData$special$$inlined$thenByDescending$1;
    }
}
