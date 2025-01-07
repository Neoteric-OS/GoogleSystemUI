package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShapeData {
    public boolean closed;
    public final List curves;
    public PointF initialPoint;

    public ShapeData(PointF pointF, boolean z, List list) {
        this.initialPoint = pointF;
        this.closed = z;
        this.curves = new ArrayList(list);
    }

    public final void setInitialPoint(float f, float f2) {
        if (this.initialPoint == null) {
            this.initialPoint = new PointF();
        }
        this.initialPoint.set(f, f2);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ShapeData{numCurves=");
        sb.append(this.curves.size());
        sb.append("closed=");
        return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.closed, '}');
    }

    public ShapeData() {
        this.curves = new ArrayList();
    }
}
