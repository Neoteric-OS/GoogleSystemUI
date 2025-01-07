package com.android.systemui.statusbar.notification.collection;

import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShadeListBuilder$$ExternalSyntheticLambda12 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        GroupEntry groupEntry = (GroupEntry) obj;
        return groupEntry.mSummary == null && groupEntry.mUnmodifiableChildren.isEmpty();
    }
}
