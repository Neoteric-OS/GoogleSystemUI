package com.android.systemui.media.controls.data.repository;

import com.android.systemui.media.controls.data.model.MediaSortKeyModel;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaFilterRepository$special$$inlined$compareByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        MediaSortKeyModel mediaSortKeyModel = (MediaSortKeyModel) obj2;
        Boolean bool = mediaSortKeyModel.isPlaying;
        Boolean bool2 = Boolean.TRUE;
        boolean z = false;
        Boolean valueOf = Boolean.valueOf(Intrinsics.areEqual(bool, bool2) && mediaSortKeyModel.playbackLocation == 0);
        MediaSortKeyModel mediaSortKeyModel2 = (MediaSortKeyModel) obj;
        if (Intrinsics.areEqual(mediaSortKeyModel2.isPlaying, bool2) && mediaSortKeyModel2.playbackLocation == 0) {
            z = true;
        }
        return ComparisonsKt___ComparisonsJvmKt.compareValues(valueOf, Boolean.valueOf(z));
    }
}
