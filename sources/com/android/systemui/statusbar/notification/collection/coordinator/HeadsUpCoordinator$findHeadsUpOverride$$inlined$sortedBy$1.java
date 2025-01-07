package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeadsUpCoordinator$findHeadsUpOverride$$inlined$sortedBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues(Long.valueOf(-((HeadsUpCoordinator.PostedEntry) obj).entry.mSbn.getNotification().getWhen()), Long.valueOf(-((HeadsUpCoordinator.PostedEntry) obj2).entry.mSbn.getNotification().getWhen()));
    }
}
