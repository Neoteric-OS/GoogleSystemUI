package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Grouping {
    public static WidgetGroup findDependents(ConstraintWidget constraintWidget, int i, ArrayList arrayList, WidgetGroup widgetGroup) {
        int i2;
        int i3 = i == 0 ? constraintWidget.horizontalGroup : constraintWidget.verticalGroup;
        if (i3 != -1 && (widgetGroup == null || i3 != widgetGroup.mId)) {
            int i4 = 0;
            while (true) {
                if (i4 >= arrayList.size()) {
                    break;
                }
                WidgetGroup widgetGroup2 = (WidgetGroup) arrayList.get(i4);
                if (widgetGroup2.mId == i3) {
                    if (widgetGroup != null) {
                        widgetGroup.moveTo(i, widgetGroup2);
                        arrayList.remove(widgetGroup);
                    }
                    widgetGroup = widgetGroup2;
                } else {
                    i4++;
                }
            }
        } else if (i3 != -1) {
            return widgetGroup;
        }
        if (widgetGroup == null) {
            if (constraintWidget instanceof HelperWidget) {
                HelperWidget helperWidget = (HelperWidget) constraintWidget;
                int i5 = 0;
                while (true) {
                    if (i5 >= helperWidget.mWidgetsCount) {
                        i2 = -1;
                        break;
                    }
                    ConstraintWidget constraintWidget2 = helperWidget.mWidgets[i5];
                    if ((i == 0 && (i2 = constraintWidget2.horizontalGroup) != -1) || (i == 1 && (i2 = constraintWidget2.verticalGroup) != -1)) {
                        break;
                    }
                    i5++;
                }
                if (i2 != -1) {
                    int i6 = 0;
                    while (true) {
                        if (i6 >= arrayList.size()) {
                            break;
                        }
                        WidgetGroup widgetGroup3 = (WidgetGroup) arrayList.get(i6);
                        if (widgetGroup3.mId == i2) {
                            widgetGroup = widgetGroup3;
                            break;
                        }
                        i6++;
                    }
                }
            }
            if (widgetGroup == null) {
                widgetGroup = new WidgetGroup();
                widgetGroup.mWidgets = new ArrayList();
                widgetGroup.mResults = null;
                widgetGroup.mMoveTo = -1;
                int i7 = WidgetGroup.sCount;
                WidgetGroup.sCount = i7 + 1;
                widgetGroup.mId = i7;
                widgetGroup.mOrientation = i;
            }
            arrayList.add(widgetGroup);
        }
        if (!widgetGroup.mWidgets.contains(constraintWidget)) {
            widgetGroup.mWidgets.add(constraintWidget);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                guideline.mAnchor.findDependents(guideline.mOrientation == 0 ? 1 : 0, widgetGroup, arrayList);
            }
            int i8 = widgetGroup.mId;
            if (i == 0) {
                constraintWidget.horizontalGroup = i8;
                constraintWidget.mLeft.findDependents(i, widgetGroup, arrayList);
                constraintWidget.mRight.findDependents(i, widgetGroup, arrayList);
            } else {
                constraintWidget.verticalGroup = i8;
                constraintWidget.mTop.findDependents(i, widgetGroup, arrayList);
                constraintWidget.mBaseline.findDependents(i, widgetGroup, arrayList);
                constraintWidget.mBottom.findDependents(i, widgetGroup, arrayList);
            }
            constraintWidget.mCenter.findDependents(i, widgetGroup, arrayList);
        }
        return widgetGroup;
    }

    public static boolean validInGroup(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        return (dimensionBehaviour3 == dimensionBehaviour5 || dimensionBehaviour3 == dimensionBehaviour7 || (dimensionBehaviour3 == dimensionBehaviour6 && dimensionBehaviour != dimensionBehaviour7)) || (dimensionBehaviour4 == dimensionBehaviour5 || dimensionBehaviour4 == dimensionBehaviour7 || (dimensionBehaviour4 == dimensionBehaviour6 && dimensionBehaviour2 != dimensionBehaviour7));
    }
}
