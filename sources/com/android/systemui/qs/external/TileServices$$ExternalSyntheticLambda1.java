package com.android.systemui.qs.external;

import java.util.Comparator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TileServices$$ExternalSyntheticLambda1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return -Integer.compare(((TileServiceManager) obj).mPriority, ((TileServiceManager) obj2).mPriority);
    }
}
