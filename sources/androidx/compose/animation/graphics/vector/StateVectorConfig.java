package androidx.compose.animation.graphics.vector;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.vector.VectorConfig;
import androidx.compose.ui.graphics.vector.VectorProperty;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StateVectorConfig implements VectorConfig {
    public State fillAlphaState;
    public State fillColorState;
    public State pathDataState;
    public State pivotXState;
    public State pivotYState;
    public State rotationState;
    public State scaleXState;
    public State scaleYState;
    public State strokeAlphaState;
    public State strokeColorState;
    public State strokeWidthState;
    public State translateXState;
    public State translateYState;
    public State trimPathEndState;
    public State trimPathOffsetState;
    public State trimPathStartState;

    @Override // androidx.compose.ui.graphics.vector.VectorConfig
    public final Object getOrDefault(VectorProperty vectorProperty, Object obj) {
        List list;
        if (vectorProperty instanceof VectorProperty.Rotation) {
            State state = this.rotationState;
            return state != null ? Float.valueOf(((Number) state.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.PivotX) {
            State state2 = this.pivotXState;
            return state2 != null ? Float.valueOf(((Number) state2.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.PivotY) {
            State state3 = this.pivotYState;
            return state3 != null ? Float.valueOf(((Number) state3.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.ScaleX) {
            State state4 = this.scaleXState;
            return state4 != null ? Float.valueOf(((Number) state4.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.ScaleY) {
            State state5 = this.scaleYState;
            return state5 != null ? Float.valueOf(((Number) state5.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.TranslateX) {
            State state6 = this.translateXState;
            return state6 != null ? Float.valueOf(((Number) state6.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.TranslateY) {
            State state7 = this.translateYState;
            return state7 != null ? Float.valueOf(((Number) state7.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.PathData) {
            State state8 = this.pathDataState;
            return (state8 == null || (list = (List) state8.getValue()) == null) ? obj : list;
        }
        if (vectorProperty instanceof VectorProperty.Fill) {
            State state9 = this.fillColorState;
            return state9 != null ? new SolidColor(((Color) state9.getValue()).value) : obj;
        }
        if (vectorProperty instanceof VectorProperty.FillAlpha) {
            State state10 = this.fillAlphaState;
            return state10 != null ? Float.valueOf(((Number) state10.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.Stroke) {
            State state11 = this.strokeColorState;
            return state11 != null ? new SolidColor(((Color) state11.getValue()).value) : obj;
        }
        if (vectorProperty instanceof VectorProperty.StrokeLineWidth) {
            State state12 = this.strokeWidthState;
            return state12 != null ? Float.valueOf(((Number) state12.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.StrokeAlpha) {
            State state13 = this.strokeAlphaState;
            return state13 != null ? Float.valueOf(((Number) state13.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.TrimPathStart) {
            State state14 = this.trimPathStartState;
            return state14 != null ? Float.valueOf(((Number) state14.getValue()).floatValue()) : obj;
        }
        if (vectorProperty instanceof VectorProperty.TrimPathEnd) {
            State state15 = this.trimPathEndState;
            return state15 != null ? Float.valueOf(((Number) state15.getValue()).floatValue()) : obj;
        }
        if (!(vectorProperty instanceof VectorProperty.TrimPathOffset)) {
            throw new NoWhenBranchMatchedException();
        }
        State state16 = this.trimPathOffsetState;
        return state16 != null ? Float.valueOf(((Number) state16.getValue()).floatValue()) : obj;
    }
}
