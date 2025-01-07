package com.android.systemui.privacy;

import com.android.systemui.privacy.PrivacyDialog;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyDialogController$filterAndSelect$lambda$6$$inlined$sortedByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues(Long.valueOf(((PrivacyDialog.PrivacyElement) obj2).lastActiveTimestamp), Long.valueOf(((PrivacyDialog.PrivacyElement) obj).lastActiveTimestamp));
    }
}
