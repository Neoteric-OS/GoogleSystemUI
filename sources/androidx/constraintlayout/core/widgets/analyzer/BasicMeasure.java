package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BasicMeasure {
    public final ConstraintWidgetContainer mConstraintWidgetContainer;
    public final ArrayList mVariableDimensionsWidgets = new ArrayList();
    public final Measure mMeasure = new Measure();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Measure {
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.mConstraintWidgetContainer = constraintWidgetContainer;
    }

    public final boolean measure(int i, ConstraintWidget constraintWidget, ConstraintLayout.Measurer measurer) {
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviourArr[1];
        measure.horizontalDimension = constraintWidget.getWidth();
        measure.verticalDimension = constraintWidget.getHeight();
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour2 == dimensionBehaviour3;
        boolean z2 = measure.verticalBehavior == dimensionBehaviour3;
        boolean z3 = z && constraintWidget.mDimensionRatio > 0.0f;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
        int[] iArr = constraintWidget.mResolvedMatchConstraintDefault;
        if (z3 && iArr[0] == 4) {
            measure.horizontalBehavior = dimensionBehaviour4;
        }
        if (z4 && iArr[1] == 4) {
            measure.verticalBehavior = dimensionBehaviour4;
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(measure.measuredWidth);
        constraintWidget.setHeight(measure.measuredHeight);
        constraintWidget.mHasBaseline = measure.measuredHasBaseline;
        constraintWidget.setBaselineDistance(measure.measuredBaseline);
        measure.measureStrategy = 0;
        return measure.measuredNeedsSolverPass;
    }

    public final void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer, int i, int i2, int i3) {
        constraintWidgetContainer.getClass();
        int i4 = constraintWidgetContainer.mMinWidth;
        int i5 = constraintWidgetContainer.mMinHeight;
        constraintWidgetContainer.mMinWidth = 0;
        constraintWidgetContainer.mMinHeight = 0;
        constraintWidgetContainer.setWidth(i2);
        constraintWidgetContainer.setHeight(i3);
        if (i4 < 0) {
            constraintWidgetContainer.mMinWidth = 0;
        } else {
            constraintWidgetContainer.mMinWidth = i4;
        }
        if (i5 < 0) {
            constraintWidgetContainer.mMinHeight = 0;
        } else {
            constraintWidgetContainer.mMinHeight = i5;
        }
        ConstraintWidgetContainer constraintWidgetContainer2 = this.mConstraintWidgetContainer;
        constraintWidgetContainer2.mPass = i;
        constraintWidgetContainer2.layout();
    }

    public final void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer) {
        this.mVariableDimensionsWidgets.clear();
        int size = constraintWidgetContainer.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer.mChildren.get(i);
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour == dimensionBehaviour2 || dimensionBehaviourArr[1] == dimensionBehaviour2) {
                this.mVariableDimensionsWidgets.add(constraintWidget);
            }
        }
        constraintWidgetContainer.mDependencyGraph.mNeedBuildGraph = true;
    }
}
