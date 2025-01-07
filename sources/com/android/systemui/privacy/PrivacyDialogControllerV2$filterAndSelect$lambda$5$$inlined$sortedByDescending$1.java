package com.android.systemui.privacy;

import com.android.systemui.privacy.PrivacyDialogV2;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyDialogControllerV2$filterAndSelect$lambda$5$$inlined$sortedByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues(Long.valueOf(((PrivacyDialogV2.PrivacyElement) obj2).lastActiveTimestamp), Long.valueOf(((PrivacyDialogV2.PrivacyElement) obj).lastActiveTimestamp));
    }
}
