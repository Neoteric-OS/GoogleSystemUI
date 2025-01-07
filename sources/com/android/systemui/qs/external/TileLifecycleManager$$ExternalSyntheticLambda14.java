package com.android.systemui.qs.external;

import java.util.function.Function;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TileLifecycleManager$$ExternalSyntheticLambda14 implements Function {
    public final /* synthetic */ Predicate f$0;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Boolean.valueOf(this.f$0.test((QSTileServiceWrapper) obj));
    }
}
