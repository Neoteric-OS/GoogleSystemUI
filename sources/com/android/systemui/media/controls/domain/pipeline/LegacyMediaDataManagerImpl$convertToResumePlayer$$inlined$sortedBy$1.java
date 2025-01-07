package com.android.systemui.media.controls.domain.pipeline;

import com.android.systemui.media.controls.shared.model.MediaData;
import java.util.Comparator;
import kotlin.Pair;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LegacyMediaDataManagerImpl$convertToResumePlayer$$inlined$sortedBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Pair pair = (Pair) obj;
        Long valueOf = Long.valueOf(((MediaData) pair.component2()).lastActive);
        Pair pair2 = (Pair) obj2;
        return ComparisonsKt___ComparisonsJvmKt.compareValues(valueOf, Long.valueOf(((MediaData) pair2.component2()).lastActive));
    }
}
