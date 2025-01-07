package androidx.constraintlayout.core.widgets;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.ArrayRow;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Barrier extends HelperWidget {
    public int mBarrierType = 0;
    public boolean mAllowsGoneWidget = true;
    public int mMargin = 0;
    public boolean mResolved = false;

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void addToSolver(LinearSystem linearSystem, boolean z) {
        boolean z2;
        int i;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        ConstraintAnchor constraintAnchor = this.mLeft;
        constraintAnchorArr[0] = constraintAnchor;
        ConstraintAnchor constraintAnchor2 = this.mTop;
        int i2 = 2;
        constraintAnchorArr[2] = constraintAnchor2;
        ConstraintAnchor constraintAnchor3 = this.mRight;
        constraintAnchorArr[1] = constraintAnchor3;
        ConstraintAnchor constraintAnchor4 = this.mBottom;
        constraintAnchorArr[3] = constraintAnchor4;
        for (ConstraintAnchor constraintAnchor5 : constraintAnchorArr) {
            constraintAnchor5.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor5);
        }
        int i3 = this.mBarrierType;
        if (i3 < 0 || i3 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor6 = constraintAnchorArr[i3];
        if (!this.mResolved) {
            allSolved();
        }
        if (this.mResolved) {
            this.mResolved = false;
            int i4 = this.mBarrierType;
            if (i4 == 0 || i4 == 1) {
                linearSystem.addEquality(constraintAnchor.mSolverVariable, this.mX);
                linearSystem.addEquality(constraintAnchor3.mSolverVariable, this.mX);
                return;
            } else {
                if (i4 == 2 || i4 == 3) {
                    linearSystem.addEquality(constraintAnchor2.mSolverVariable, this.mY);
                    linearSystem.addEquality(constraintAnchor4.mSolverVariable, this.mY);
                    return;
                }
                return;
            }
        }
        for (int i5 = 0; i5 < this.mWidgetsCount; i5++) {
            ConstraintWidget constraintWidget = this.mWidgets[i5];
            if (this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) {
                int i6 = this.mBarrierType;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (((i6 == 0 || i6 == 1) && constraintWidget.mListDimensionBehaviors[0] == dimensionBehaviour && constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget != null) || ((i6 == 2 || i6 == 3) && constraintWidget.mListDimensionBehaviors[1] == dimensionBehaviour && constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null)) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        boolean z3 = constraintAnchor.hasCenteredDependents() || constraintAnchor3.hasCenteredDependents();
        boolean z4 = constraintAnchor2.hasCenteredDependents() || constraintAnchor4.hasCenteredDependents();
        int i7 = (z2 || !(((i = this.mBarrierType) == 0 && z3) || ((i == 2 && z4) || ((i == 1 && z3) || (i == 3 && z4))))) ? 4 : 5;
        int i8 = 0;
        while (i8 < this.mWidgetsCount) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i8];
            if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.mBarrierType]);
                int i9 = this.mBarrierType;
                ConstraintAnchor constraintAnchor7 = constraintWidget2.mListAnchors[i9];
                constraintAnchor7.mSolverVariable = createObjectVariable;
                ConstraintAnchor constraintAnchor8 = constraintAnchor7.mTarget;
                int i10 = (constraintAnchor8 == null || constraintAnchor8.mOwner != this) ? 0 : constraintAnchor7.mMargin;
                if (i9 == 0 || i9 == i2) {
                    SolverVariable solverVariable = constraintAnchor6.mSolverVariable;
                    int i11 = this.mMargin - i10;
                    ArrayRow createRow = linearSystem.createRow();
                    SolverVariable createSlackVariable = linearSystem.createSlackVariable();
                    createSlackVariable.strength = 0;
                    createRow.createRowLowerThan(solverVariable, createObjectVariable, createSlackVariable, i11);
                    linearSystem.addConstraint(createRow);
                } else {
                    SolverVariable solverVariable2 = constraintAnchor6.mSolverVariable;
                    int i12 = this.mMargin + i10;
                    ArrayRow createRow2 = linearSystem.createRow();
                    SolverVariable createSlackVariable2 = linearSystem.createSlackVariable();
                    createSlackVariable2.strength = 0;
                    createRow2.createRowGreaterThan(solverVariable2, createObjectVariable, createSlackVariable2, i12);
                    linearSystem.addConstraint(createRow2);
                }
                linearSystem.addEquality(constraintAnchor6.mSolverVariable, createObjectVariable, this.mMargin + i10, i7);
            }
            i8++;
            i2 = 2;
        }
        int i13 = this.mBarrierType;
        if (i13 == 0) {
            linearSystem.addEquality(constraintAnchor3.mSolverVariable, constraintAnchor.mSolverVariable, 0, 8);
            linearSystem.addEquality(constraintAnchor.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 4);
            linearSystem.addEquality(constraintAnchor.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
            return;
        }
        if (i13 == 1) {
            linearSystem.addEquality(constraintAnchor.mSolverVariable, constraintAnchor3.mSolverVariable, 0, 8);
            linearSystem.addEquality(constraintAnchor.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 4);
            linearSystem.addEquality(constraintAnchor.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
        } else if (i13 == 2) {
            linearSystem.addEquality(constraintAnchor4.mSolverVariable, constraintAnchor2.mSolverVariable, 0, 8);
            linearSystem.addEquality(constraintAnchor2.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 4);
            linearSystem.addEquality(constraintAnchor2.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
        } else if (i13 == 3) {
            linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor4.mSolverVariable, 0, 8);
            linearSystem.addEquality(constraintAnchor2.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 4);
            linearSystem.addEquality(constraintAnchor2.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
        }
    }

    public final boolean allSolved() {
        int i;
        int i2;
        int i3;
        boolean z = true;
        int i4 = 0;
        while (true) {
            i = this.mWidgetsCount;
            if (i4 >= i) {
                break;
            }
            ConstraintWidget constraintWidget = this.mWidgets[i4];
            if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i2 = this.mBarrierType) == 0 || i2 == 1) && !constraintWidget.isResolvedHorizontally()) || (((i3 = this.mBarrierType) == 2 || i3 == 3) && !constraintWidget.isResolvedVertically()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int i5 = 0;
        boolean z2 = false;
        for (int i6 = 0; i6 < this.mWidgetsCount; i6++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i6];
            if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                ConstraintAnchor.Type type = ConstraintAnchor.Type.BOTTOM;
                ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
                ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
                ConstraintAnchor.Type type4 = ConstraintAnchor.Type.LEFT;
                if (!z2) {
                    int i7 = this.mBarrierType;
                    if (i7 == 0) {
                        i5 = constraintWidget2.getAnchor(type4).getFinalValue();
                    } else if (i7 == 1) {
                        i5 = constraintWidget2.getAnchor(type3).getFinalValue();
                    } else if (i7 == 2) {
                        i5 = constraintWidget2.getAnchor(type2).getFinalValue();
                    } else if (i7 == 3) {
                        i5 = constraintWidget2.getAnchor(type).getFinalValue();
                    }
                    z2 = true;
                }
                int i8 = this.mBarrierType;
                if (i8 == 0) {
                    i5 = Math.min(i5, constraintWidget2.getAnchor(type4).getFinalValue());
                } else if (i8 == 1) {
                    i5 = Math.max(i5, constraintWidget2.getAnchor(type3).getFinalValue());
                } else if (i8 == 2) {
                    i5 = Math.min(i5, constraintWidget2.getAnchor(type2).getFinalValue());
                } else if (i8 == 3) {
                    i5 = Math.max(i5, constraintWidget2.getAnchor(type).getFinalValue());
                }
            }
        }
        int i9 = i5 + this.mMargin;
        int i10 = this.mBarrierType;
        if (i10 == 0 || i10 == 1) {
            setFinalHorizontal(i9, i9);
        } else {
            setFinalVertical(i9, i9);
        }
        this.mResolved = true;
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean allowedInBarrier() {
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void copy(ConstraintWidget constraintWidget, HashMap hashMap) {
        super.copy(constraintWidget, hashMap);
        Barrier barrier = (Barrier) constraintWidget;
        this.mBarrierType = barrier.mBarrierType;
        this.mAllowsGoneWidget = barrier.mAllowsGoneWidget;
        this.mMargin = barrier.mMargin;
    }

    public final int getOrientation() {
        int i = this.mBarrierType;
        if (i == 0 || i == 1) {
            return 0;
        }
        return (i == 2 || i == 3) ? 1 : -1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean isResolvedHorizontally() {
        return this.mResolved;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean isResolvedVertically() {
        return this.mResolved;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final String toString() {
        String m = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("[Barrier] "), this.mDebugName, " {");
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (i > 0) {
                m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(m, ", ");
            }
            StringBuilder m2 = EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0.m(m);
            m2.append(constraintWidget.mDebugName);
            m = m2.toString();
        }
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(m, "}");
    }
}
