package com.android.systemui.communal.domain.interactor;

import com.android.systemui.communal.domain.model.CommunalContentModel;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalInteractor$ongoingContent$1$invokeSuspend$$inlined$sortByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues(Long.valueOf(((CommunalContentModel.Ongoing) obj2).getCreatedTimestampMillis()), Long.valueOf(((CommunalContentModel.Ongoing) obj).getCreatedTimestampMillis()));
    }
}
