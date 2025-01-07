package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.analyzer.Grouping;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConstraintAnchor {
    public int mFinalValue;
    public boolean mHasFinalValue;
    public final ConstraintWidget mOwner;
    public SolverVariable mSolverVariable;
    public ConstraintAnchor mTarget;
    public final Type mType;
    public HashSet mDependents = null;
    public int mMargin = 0;
    public int mGoneMargin = Integer.MIN_VALUE;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Type {
        public static final /* synthetic */ Type[] $VALUES;
        public static final Type BASELINE;
        public static final Type BOTTOM;
        public static final Type CENTER;
        public static final Type CENTER_X;
        public static final Type CENTER_Y;
        public static final Type LEFT;
        public static final Type RIGHT;
        public static final Type TOP;

        /* JADX INFO: Fake field, exist only in values array */
        Type EF0;

        static {
            Type type = new Type("NONE", 0);
            Type type2 = new Type("LEFT", 1);
            LEFT = type2;
            Type type3 = new Type("TOP", 2);
            TOP = type3;
            Type type4 = new Type("RIGHT", 3);
            RIGHT = type4;
            Type type5 = new Type("BOTTOM", 4);
            BOTTOM = type5;
            Type type6 = new Type("BASELINE", 5);
            BASELINE = type6;
            Type type7 = new Type("CENTER", 6);
            CENTER = type7;
            Type type8 = new Type("CENTER_X", 7);
            CENTER_X = type8;
            Type type9 = new Type("CENTER_Y", 8);
            CENTER_Y = type9;
            $VALUES = new Type[]{type, type2, type3, type4, type5, type6, type7, type8, type9};
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.mOwner = constraintWidget;
        this.mType = type;
    }

    public final boolean connect(ConstraintAnchor constraintAnchor, int i, int i2, boolean z) {
        if (constraintAnchor == null) {
            reset();
            return true;
        }
        if (!z && !isValidConnection(constraintAnchor)) {
            return false;
        }
        this.mTarget = constraintAnchor;
        if (constraintAnchor.mDependents == null) {
            constraintAnchor.mDependents = new HashSet();
        }
        HashSet hashSet = this.mTarget.mDependents;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.mMargin = i;
        this.mGoneMargin = i2;
        return true;
    }

    public final void findDependents(int i, WidgetGroup widgetGroup, ArrayList arrayList) {
        HashSet hashSet = this.mDependents;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                Grouping.findDependents(((ConstraintAnchor) it.next()).mOwner, i, arrayList, widgetGroup);
            }
        }
    }

    public final int getFinalValue() {
        if (this.mHasFinalValue) {
            return this.mFinalValue;
        }
        return 0;
    }

    public final int getMargin() {
        ConstraintAnchor constraintAnchor;
        if (this.mOwner.mVisibility == 8) {
            return 0;
        }
        int i = this.mGoneMargin;
        return (i == Integer.MIN_VALUE || (constraintAnchor = this.mTarget) == null || constraintAnchor.mOwner.mVisibility != 8) ? this.mMargin : i;
    }

    public final ConstraintAnchor getOpposite() {
        Type type = this.mType;
        int ordinal = type.ordinal();
        ConstraintWidget constraintWidget = this.mOwner;
        switch (ordinal) {
            case 0:
            case 5:
            case 6:
            case 7:
            case 8:
                return null;
            case 1:
                return constraintWidget.mRight;
            case 2:
                return constraintWidget.mBottom;
            case 3:
                return constraintWidget.mLeft;
            case 4:
                return constraintWidget.mTop;
            default:
                throw new AssertionError(type.name());
        }
    }

    public final boolean hasCenteredDependents() {
        HashSet hashSet = this.mDependents;
        if (hashSet == null) {
            return false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((ConstraintAnchor) it.next()).getOpposite().isConnected()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isConnected() {
        return this.mTarget != null;
    }

    public final boolean isValidConnection(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type type = Type.BASELINE;
        Type type2 = this.mType;
        ConstraintWidget constraintWidget = constraintAnchor.mOwner;
        Type type3 = constraintAnchor.mType;
        if (type3 == type2) {
            return type2 != type || (constraintWidget.mHasBaseline && this.mOwner.mHasBaseline);
        }
        int ordinal = type2.ordinal();
        Type type4 = Type.RIGHT;
        Type type5 = Type.LEFT;
        Type type6 = Type.CENTER_Y;
        Type type7 = Type.CENTER_X;
        switch (ordinal) {
            case 0:
            case 7:
            case 8:
                return false;
            case 1:
            case 3:
                boolean z = type3 == type5 || type3 == type4;
                if (constraintWidget instanceof Guideline) {
                    return z || type3 == type7;
                }
                return z;
            case 2:
            case 4:
                boolean z2 = type3 == Type.TOP || type3 == Type.BOTTOM;
                if (constraintWidget instanceof Guideline) {
                    return z2 || type3 == type6;
                }
                return z2;
            case 5:
                return (type3 == type5 || type3 == type4) ? false : true;
            case 6:
                return (type3 == type || type3 == type7 || type3 == type6) ? false : true;
            default:
                throw new AssertionError(type2.name());
        }
    }

    public final void reset() {
        HashSet hashSet;
        ConstraintAnchor constraintAnchor = this.mTarget;
        if (constraintAnchor != null && (hashSet = constraintAnchor.mDependents) != null) {
            hashSet.remove(this);
            if (this.mTarget.mDependents.size() == 0) {
                this.mTarget.mDependents = null;
            }
        }
        this.mDependents = null;
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = Integer.MIN_VALUE;
        this.mHasFinalValue = false;
        this.mFinalValue = 0;
    }

    public final void resetSolverVariable() {
        SolverVariable solverVariable = this.mSolverVariable;
        if (solverVariable == null) {
            this.mSolverVariable = new SolverVariable(SolverVariable.Type.UNRESTRICTED);
        } else {
            solverVariable.reset();
        }
    }

    public final void setFinalValue(int i) {
        this.mFinalValue = i;
        this.mHasFinalValue = true;
    }

    public final String toString() {
        return this.mOwner.mDebugName + ":" + this.mType.toString();
    }

    public final void connect(ConstraintAnchor constraintAnchor, int i) {
        connect(constraintAnchor, i, Integer.MIN_VALUE, false);
    }
}
