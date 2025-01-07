package com.android.systemui.qs.pipeline.data.repository;

import java.util.Comparator;
import kotlin.Pair;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserTileSpecRepository$Companion$reconcileTiles$$inlined$sortedBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues((Integer) ((Pair) obj).getSecond(), (Integer) ((Pair) obj2).getSecond());
    }
}
