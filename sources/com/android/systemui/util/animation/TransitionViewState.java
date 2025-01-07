package com.android.systemui.util.animation;

import android.graphics.PointF;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TransitionViewState {
    public int height;
    public int measureHeight;
    public int measureWidth;
    public int width;
    public final Map widgetStates = new LinkedHashMap();
    public float alpha = 1.0f;
    public final PointF translation = new PointF();
    public final PointF contentTranslation = new PointF();

    public final TransitionViewState copy(TransitionViewState transitionViewState) {
        if (transitionViewState == null) {
            transitionViewState = new TransitionViewState();
        }
        transitionViewState.width = this.width;
        transitionViewState.height = this.height;
        transitionViewState.measureHeight = this.measureHeight;
        transitionViewState.measureWidth = this.measureWidth;
        transitionViewState.alpha = this.alpha;
        PointF pointF = transitionViewState.translation;
        PointF pointF2 = this.translation;
        pointF.set(pointF2.x, pointF2.y);
        PointF pointF3 = transitionViewState.contentTranslation;
        PointF pointF4 = this.contentTranslation;
        pointF3.set(pointF4.x, pointF4.y);
        for (Map.Entry entry : this.widgetStates.entrySet()) {
            Map map = transitionViewState.widgetStates;
            Object key = entry.getKey();
            WidgetState widgetState = (WidgetState) entry.getValue();
            float f = widgetState.x;
            float f2 = widgetState.y;
            int i = widgetState.width;
            int i2 = widgetState.height;
            int i3 = widgetState.measureWidth;
            int i4 = widgetState.measureHeight;
            float f3 = widgetState.alpha;
            float f4 = widgetState.scale;
            boolean z = widgetState.gone;
            widgetState.getClass();
            map.put(key, new WidgetState(f, f2, i, i2, i3, i4, f3, f4, z));
        }
        return transitionViewState;
    }
}
