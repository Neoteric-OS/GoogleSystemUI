package com.android.systemui.media.controls.ui.controller;

import com.android.systemui.media.controls.ui.controller.MediaPlayerData;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaPlayerData$special$$inlined$compareByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        MediaPlayerData.MediaSortKey mediaSortKey = (MediaPlayerData.MediaSortKey) obj2;
        Boolean bool = mediaSortKey.data.isPlaying;
        Boolean bool2 = Boolean.TRUE;
        boolean z = false;
        Boolean valueOf = Boolean.valueOf(Intrinsics.areEqual(bool, bool2) && mediaSortKey.data.playbackLocation == 0);
        MediaPlayerData.MediaSortKey mediaSortKey2 = (MediaPlayerData.MediaSortKey) obj;
        if (Intrinsics.areEqual(mediaSortKey2.data.isPlaying, bool2) && mediaSortKey2.data.playbackLocation == 0) {
            z = true;
        }
        return ComparisonsKt___ComparisonsJvmKt.compareValues(valueOf, Boolean.valueOf(z));
    }
}
