package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class VirtualLayout extends HelperWidget {
    public int mPaddingTop = 0;
    public int mPaddingBottom = 0;
    public int mPaddingStart = 0;
    public int mPaddingEnd = 0;
    public int mResolvedPaddingLeft = 0;
    public int mResolvedPaddingRight = 0;
    public boolean mNeedsCallFromSolver = false;
    public int mMeasuredWidth = 0;
    public int mMeasuredHeight = 0;
    public final BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    public ConstraintLayout.Measurer mMeasurer = null;

    public abstract void measure(int i, int i2, int i3, int i4);

    public final void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        ConstraintLayout.Measurer measurer;
        ConstraintWidget constraintWidget2;
        while (true) {
            measurer = this.mMeasurer;
            if (measurer != null || (constraintWidget2 = this.mParent) == null) {
                break;
            } else {
                this.mMeasurer = ((ConstraintWidgetContainer) constraintWidget2).mMeasurer;
            }
        }
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i;
        measure.verticalDimension = i2;
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(measure.measuredWidth);
        constraintWidget.setHeight(measure.measuredHeight);
        constraintWidget.mHasBaseline = measure.measuredHasBaseline;
        constraintWidget.setBaselineDistance(measure.measuredBaseline);
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget
    public final void updateConstraints() {
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (constraintWidget != null) {
                constraintWidget.mInVirtualLayout = true;
            }
        }
    }
}
