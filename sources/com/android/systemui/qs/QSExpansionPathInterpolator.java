package com.android.systemui.qs;

import com.android.systemui.qs.PathInterpolatorBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSExpansionPathInterpolator {
    public PathInterpolatorBuilder pathInterpolatorBuilder;

    public final PathInterpolatorBuilder.PathInterpolator getYInterpolator() {
        PathInterpolatorBuilder pathInterpolatorBuilder = this.pathInterpolatorBuilder;
        pathInterpolatorBuilder.getClass();
        return new PathInterpolatorBuilder.PathInterpolator(pathInterpolatorBuilder.mDist, pathInterpolatorBuilder.mY);
    }
}
