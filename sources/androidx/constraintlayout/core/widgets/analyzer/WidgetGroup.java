package androidx.constraintlayout.core.widgets.analyzer;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.widgets.Chain;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WidgetGroup {
    public static int sCount;
    public int mId;
    public int mMoveTo;
    public int mOrientation;
    public ArrayList mResults;
    public ArrayList mWidgets;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MeasureResult {
    }

    public final void cleanup(ArrayList arrayList) {
        int size = this.mWidgets.size();
        if (this.mMoveTo != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                WidgetGroup widgetGroup = (WidgetGroup) arrayList.get(i);
                if (this.mMoveTo == widgetGroup.mId) {
                    moveTo(this.mOrientation, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final int measureWrap(LinearSystem linearSystem, int i) {
        int objectVariableValue;
        int objectVariableValue2;
        if (this.mWidgets.size() == 0) {
            return 0;
        }
        ArrayList arrayList = this.mWidgets;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) ((ConstraintWidget) arrayList.get(0)).mParent;
        linearSystem.reset();
        constraintWidgetContainer.addToSolver(linearSystem, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ((ConstraintWidget) arrayList.get(i2)).addToSolver(linearSystem, false);
        }
        if (i == 0 && constraintWidgetContainer.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 0);
        }
        if (i == 1 && constraintWidgetContainer.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 1);
        }
        try {
            linearSystem.minimize();
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + Arrays.toString(e.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
        this.mResults = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i3);
            MeasureResult measureResult = new MeasureResult();
            new WeakReference(constraintWidget);
            LinearSystem.getObjectVariableValue(constraintWidget.mLeft);
            LinearSystem.getObjectVariableValue(constraintWidget.mTop);
            LinearSystem.getObjectVariableValue(constraintWidget.mRight);
            LinearSystem.getObjectVariableValue(constraintWidget.mBottom);
            LinearSystem.getObjectVariableValue(constraintWidget.mBaseline);
            this.mResults.add(measureResult);
        }
        if (i == 0) {
            objectVariableValue = LinearSystem.getObjectVariableValue(constraintWidgetContainer.mLeft);
            objectVariableValue2 = LinearSystem.getObjectVariableValue(constraintWidgetContainer.mRight);
            linearSystem.reset();
        } else {
            objectVariableValue = LinearSystem.getObjectVariableValue(constraintWidgetContainer.mTop);
            objectVariableValue2 = LinearSystem.getObjectVariableValue(constraintWidgetContainer.mBottom);
            linearSystem.reset();
        }
        return objectVariableValue2 - objectVariableValue;
    }

    public final void moveTo(int i, WidgetGroup widgetGroup) {
        Iterator it = this.mWidgets.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (!widgetGroup.mWidgets.contains(constraintWidget)) {
                widgetGroup.mWidgets.add(constraintWidget);
            }
            int i2 = widgetGroup.mId;
            if (i == 0) {
                constraintWidget.horizontalGroup = i2;
            } else {
                constraintWidget.verticalGroup = i2;
            }
        }
        this.mMoveTo = widgetGroup.mId;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.mOrientation;
        sb.append(i == 0 ? "Horizontal" : i == 1 ? "Vertical" : i == 2 ? "Both" : "Unknown");
        sb.append(" [");
        String m = PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.mId, "] <");
        Iterator it = this.mWidgets.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            StringBuilder m2 = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(m, " ");
            m2.append(constraintWidget.mDebugName);
            m = m2.toString();
        }
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(m, " >");
    }
}
