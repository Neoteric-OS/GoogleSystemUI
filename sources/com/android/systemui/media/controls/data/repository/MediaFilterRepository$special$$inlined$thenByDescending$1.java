package com.android.systemui.media.controls.data.repository;

import com.android.systemui.media.controls.data.model.MediaSortKeyModel;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaFilterRepository$special$$inlined$thenByDescending$1 implements Comparator {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object $this_thenByDescending;

    public MediaFilterRepository$special$$inlined$thenByDescending$1(MediaFilterRepository$special$$inlined$compareByDescending$1 mediaFilterRepository$special$$inlined$compareByDescending$1) {
        this.$this_thenByDescending = mediaFilterRepository$special$$inlined$compareByDescending$1;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                int compare = ((MediaFilterRepository$special$$inlined$compareByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (compare != 0) {
                    return compare;
                }
                MediaSortKeyModel mediaSortKeyModel = (MediaSortKeyModel) obj2;
                Boolean bool = mediaSortKeyModel.isPlaying;
                Boolean bool2 = Boolean.TRUE;
                boolean z = false;
                Boolean valueOf = Boolean.valueOf(Intrinsics.areEqual(bool, bool2) && mediaSortKeyModel.playbackLocation == 1);
                MediaSortKeyModel mediaSortKeyModel2 = (MediaSortKeyModel) obj;
                if (Intrinsics.areEqual(mediaSortKeyModel2.isPlaying, bool2) && mediaSortKeyModel2.playbackLocation == 1) {
                    z = true;
                }
                return ComparisonsKt___ComparisonsJvmKt.compareValues(valueOf, Boolean.valueOf(z));
            case 1:
                int compare2 = ((MediaFilterRepository$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare2 != 0 ? compare2 : ComparisonsKt___ComparisonsJvmKt.compareValues(Boolean.valueOf(((MediaSortKeyModel) obj2).active), Boolean.valueOf(((MediaSortKeyModel) obj).active));
            case 2:
                int compare3 = ((MediaFilterRepository$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare3 != 0 ? compare3 : ComparisonsKt___ComparisonsJvmKt.compareValues(Boolean.valueOf(((MediaSortKeyModel) obj2).isPrioritizedRec), Boolean.valueOf(((MediaSortKeyModel) obj).isPrioritizedRec));
            case 3:
                int compare4 = ((MediaFilterRepository$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare4 != 0 ? compare4 : ComparisonsKt___ComparisonsJvmKt.compareValues(Boolean.valueOf(!((MediaSortKeyModel) obj2).isResume), Boolean.valueOf(!((MediaSortKeyModel) obj).isResume));
            case 4:
                int compare5 = ((MediaFilterRepository$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (compare5 != 0) {
                    return compare5;
                }
                return ComparisonsKt___ComparisonsJvmKt.compareValues(Boolean.valueOf(((MediaSortKeyModel) obj2).playbackLocation != 2), Boolean.valueOf(((MediaSortKeyModel) obj).playbackLocation != 2));
            case 5:
                int compare6 = ((MediaFilterRepository$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare6 != 0 ? compare6 : ComparisonsKt___ComparisonsJvmKt.compareValues(Long.valueOf(((MediaSortKeyModel) obj2).lastActive), Long.valueOf(((MediaSortKeyModel) obj).lastActive));
            case 6:
                int compare7 = ((MediaFilterRepository$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare7 != 0 ? compare7 : ComparisonsKt___ComparisonsJvmKt.compareValues(Long.valueOf(((MediaSortKeyModel) obj2).updateTime), Long.valueOf(((MediaSortKeyModel) obj).updateTime));
            default:
                int compare8 = ((MediaFilterRepository$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return compare8 != 0 ? compare8 : ComparisonsKt___ComparisonsJvmKt.compareValues(((MediaSortKeyModel) obj2).notificationKey, ((MediaSortKeyModel) obj).notificationKey);
        }
    }

    public MediaFilterRepository$special$$inlined$thenByDescending$1(MediaFilterRepository$special$$inlined$thenByDescending$1 mediaFilterRepository$special$$inlined$thenByDescending$1) {
        this.$this_thenByDescending = mediaFilterRepository$special$$inlined$thenByDescending$1;
    }

    public MediaFilterRepository$special$$inlined$thenByDescending$1(MediaFilterRepository$special$$inlined$thenByDescending$1 mediaFilterRepository$special$$inlined$thenByDescending$1, byte b) {
        this.$this_thenByDescending = mediaFilterRepository$special$$inlined$thenByDescending$1;
    }

    public MediaFilterRepository$special$$inlined$thenByDescending$1(MediaFilterRepository$special$$inlined$thenByDescending$1 mediaFilterRepository$special$$inlined$thenByDescending$1, byte b, byte b2) {
        this.$this_thenByDescending = mediaFilterRepository$special$$inlined$thenByDescending$1;
    }

    public MediaFilterRepository$special$$inlined$thenByDescending$1(MediaFilterRepository$special$$inlined$thenByDescending$1 mediaFilterRepository$special$$inlined$thenByDescending$1, byte b, boolean z) {
        this.$this_thenByDescending = mediaFilterRepository$special$$inlined$thenByDescending$1;
    }

    public MediaFilterRepository$special$$inlined$thenByDescending$1(MediaFilterRepository$special$$inlined$thenByDescending$1 mediaFilterRepository$special$$inlined$thenByDescending$1, char c) {
        this.$this_thenByDescending = mediaFilterRepository$special$$inlined$thenByDescending$1;
    }

    public MediaFilterRepository$special$$inlined$thenByDescending$1(MediaFilterRepository$special$$inlined$thenByDescending$1 mediaFilterRepository$special$$inlined$thenByDescending$1, int i) {
        this.$this_thenByDescending = mediaFilterRepository$special$$inlined$thenByDescending$1;
    }

    public MediaFilterRepository$special$$inlined$thenByDescending$1(MediaFilterRepository$special$$inlined$thenByDescending$1 mediaFilterRepository$special$$inlined$thenByDescending$1, short s) {
        this.$this_thenByDescending = mediaFilterRepository$special$$inlined$thenByDescending$1;
    }
}
