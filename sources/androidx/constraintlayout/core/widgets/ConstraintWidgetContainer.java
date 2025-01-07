package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.DependencyGraph;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConstraintWidgetContainer extends ConstraintWidget {
    public final DependencyGraph mDependencyGraph;
    public boolean mHeightMeasuredTooSmall;
    public ChainHead[] mHorizontalChainsArray;
    public int mHorizontalChainsSize;
    public WeakReference mHorizontalWrapMax;
    public WeakReference mHorizontalWrapMin;
    public boolean mIsRtl;
    public final BasicMeasure.Measure mMeasure;
    public ConstraintLayout.Measurer mMeasurer;
    public int mOptimizationLevel;
    public int mPaddingLeft;
    public int mPaddingTop;
    public int mPass;
    public final LinearSystem mSystem;
    public ChainHead[] mVerticalChainsArray;
    public int mVerticalChainsSize;
    public WeakReference mVerticalWrapMax;
    public WeakReference mVerticalWrapMin;
    public final HashSet mWidgetsToAdd;
    public boolean mWidthMeasuredTooSmall;
    public ArrayList mChildren = new ArrayList();
    public final BasicMeasure mBasicMeasureSolver = new BasicMeasure(this);

    public ConstraintWidgetContainer() {
        DependencyGraph dependencyGraph = new DependencyGraph();
        dependencyGraph.mNeedBuildGraph = true;
        dependencyGraph.mNeedRedoMeasures = true;
        dependencyGraph.mRuns = new ArrayList();
        new ArrayList();
        dependencyGraph.mMeasurer = null;
        dependencyGraph.mMeasure = new BasicMeasure.Measure();
        dependencyGraph.mGroups = new ArrayList();
        dependencyGraph.mWidgetcontainer = this;
        dependencyGraph.mContainer = this;
        this.mDependencyGraph = dependencyGraph;
        this.mMeasurer = null;
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mOptimizationLevel = 257;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mVerticalWrapMin = null;
        this.mHorizontalWrapMin = null;
        this.mVerticalWrapMax = null;
        this.mHorizontalWrapMax = null;
        this.mWidgetsToAdd = new HashSet();
        this.mMeasure = new BasicMeasure.Measure();
    }

    public static void measure(ConstraintWidget constraintWidget, ConstraintLayout.Measurer measurer, BasicMeasure.Measure measure) {
        int i;
        int i2;
        if (measurer == null) {
            return;
        }
        if (constraintWidget.mVisibility == 8 || (constraintWidget instanceof Guideline) || (constraintWidget instanceof Barrier)) {
            measure.measuredWidth = 0;
            measure.measuredHeight = 0;
            return;
        }
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
        measure.horizontalBehavior = dimensionBehaviourArr[0];
        measure.verticalBehavior = dimensionBehaviourArr[1];
        measure.horizontalDimension = constraintWidget.getWidth();
        measure.verticalDimension = constraintWidget.getHeight();
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = 0;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z2 = measure.verticalBehavior == dimensionBehaviour2;
        boolean z3 = z && constraintWidget.mDimensionRatio > 0.0f;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (z && constraintWidget.hasDanglingDimension(0) && constraintWidget.mMatchConstraintDefaultWidth == 0 && !z3) {
            measure.horizontalBehavior = dimensionBehaviour3;
            if (z2 && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                measure.horizontalBehavior = dimensionBehaviour4;
            }
            z = false;
        }
        if (z2 && constraintWidget.hasDanglingDimension(1) && constraintWidget.mMatchConstraintDefaultHeight == 0 && !z4) {
            measure.verticalBehavior = dimensionBehaviour3;
            if (z && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                measure.verticalBehavior = dimensionBehaviour4;
            }
            z2 = false;
        }
        if (constraintWidget.isResolvedHorizontally()) {
            measure.horizontalBehavior = dimensionBehaviour4;
            z = false;
        }
        if (constraintWidget.isResolvedVertically()) {
            measure.verticalBehavior = dimensionBehaviour4;
            z2 = false;
        }
        int[] iArr = constraintWidget.mResolvedMatchConstraintDefault;
        if (z3) {
            if (iArr[0] == 4) {
                measure.horizontalBehavior = dimensionBehaviour4;
            } else if (!z2) {
                if (measure.verticalBehavior == dimensionBehaviour4) {
                    i2 = measure.verticalDimension;
                } else {
                    measure.horizontalBehavior = dimensionBehaviour3;
                    measurer.measure(constraintWidget, measure);
                    i2 = measure.measuredHeight;
                }
                measure.horizontalBehavior = dimensionBehaviour4;
                measure.horizontalDimension = (int) (constraintWidget.mDimensionRatio * i2);
            }
        }
        if (z4) {
            if (iArr[1] == 4) {
                measure.verticalBehavior = dimensionBehaviour4;
            } else if (!z) {
                if (measure.horizontalBehavior == dimensionBehaviour4) {
                    i = measure.horizontalDimension;
                } else {
                    measure.verticalBehavior = dimensionBehaviour3;
                    measurer.measure(constraintWidget, measure);
                    i = measure.measuredWidth;
                }
                measure.verticalBehavior = dimensionBehaviour4;
                if (constraintWidget.mDimensionRatioSide == -1) {
                    measure.verticalDimension = (int) (i / constraintWidget.mDimensionRatio);
                } else {
                    measure.verticalDimension = (int) (constraintWidget.mDimensionRatio * i);
                }
            }
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(measure.measuredWidth);
        constraintWidget.setHeight(measure.measuredHeight);
        constraintWidget.mHasBaseline = measure.measuredHasBaseline;
        constraintWidget.setBaselineDistance(measure.measuredBaseline);
        measure.measureStrategy = 0;
    }

    public final void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            int i2 = this.mHorizontalChainsSize + 1;
            ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
            if (i2 >= chainHeadArr.length) {
                this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
            }
            ChainHead[] chainHeadArr2 = this.mHorizontalChainsArray;
            int i3 = this.mHorizontalChainsSize;
            chainHeadArr2[i3] = new ChainHead(constraintWidget, 0, this.mIsRtl);
            this.mHorizontalChainsSize = i3 + 1;
            return;
        }
        if (i == 1) {
            int i4 = this.mVerticalChainsSize + 1;
            ChainHead[] chainHeadArr3 = this.mVerticalChainsArray;
            if (i4 >= chainHeadArr3.length) {
                this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr3, chainHeadArr3.length * 2);
            }
            ChainHead[] chainHeadArr4 = this.mVerticalChainsArray;
            int i5 = this.mVerticalChainsSize;
            chainHeadArr4[i5] = new ChainHead(constraintWidget, 1, this.mIsRtl);
            this.mVerticalChainsSize = i5 + 1;
        }
    }

    public final void addChildrenToSolver(LinearSystem linearSystem) {
        boolean optimizeFor = optimizeFor(64);
        addToSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            boolean[] zArr = constraintWidget.mIsInBarrier;
            zArr[0] = false;
            zArr[1] = false;
            if (constraintWidget instanceof Barrier) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i2);
                if (constraintWidget2 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget2;
                    for (int i3 = 0; i3 < barrier.mWidgetsCount; i3++) {
                        ConstraintWidget constraintWidget3 = barrier.mWidgets[i3];
                        if (barrier.mAllowsGoneWidget || constraintWidget3.allowedInBarrier()) {
                            int i4 = barrier.mBarrierType;
                            if (i4 == 0 || i4 == 1) {
                                constraintWidget3.mIsInBarrier[0] = true;
                            } else if (i4 == 2 || i4 == 3) {
                                constraintWidget3.mIsInBarrier[1] = true;
                            }
                        }
                    }
                }
            }
        }
        this.mWidgetsToAdd.clear();
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget4 = (ConstraintWidget) this.mChildren.get(i5);
            constraintWidget4.getClass();
            boolean z2 = constraintWidget4 instanceof VirtualLayout;
            if (z2 || (constraintWidget4 instanceof Guideline)) {
                if (z2) {
                    this.mWidgetsToAdd.add(constraintWidget4);
                } else {
                    constraintWidget4.addToSolver(linearSystem, optimizeFor);
                }
            }
        }
        while (this.mWidgetsToAdd.size() > 0) {
            int size2 = this.mWidgetsToAdd.size();
            Iterator it = this.mWidgetsToAdd.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                VirtualLayout virtualLayout = (VirtualLayout) ((ConstraintWidget) it.next());
                HashSet hashSet = this.mWidgetsToAdd;
                for (int i6 = 0; i6 < virtualLayout.mWidgetsCount; i6++) {
                    if (hashSet.contains(virtualLayout.mWidgets[i6])) {
                        virtualLayout.addToSolver(linearSystem, optimizeFor);
                        this.mWidgetsToAdd.remove(virtualLayout);
                        break;
                    }
                }
            }
            if (size2 == this.mWidgetsToAdd.size()) {
                Iterator it2 = this.mWidgetsToAdd.iterator();
                while (it2.hasNext()) {
                    ((ConstraintWidget) it2.next()).addToSolver(linearSystem, optimizeFor);
                }
                this.mWidgetsToAdd.clear();
            }
        }
        boolean z3 = LinearSystem.USE_DEPENDENCY_ORDERING;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (z3) {
            HashSet hashSet2 = new HashSet();
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget) this.mChildren.get(i7);
                constraintWidget5.getClass();
                if (!(constraintWidget5 instanceof VirtualLayout) && !(constraintWidget5 instanceof Guideline)) {
                    hashSet2.add(constraintWidget5);
                }
            }
            addChildrenToSolverByDependency(this, linearSystem, hashSet2, this.mListDimensionBehaviors[0] == dimensionBehaviour ? 0 : 1, false);
            Iterator it3 = hashSet2.iterator();
            while (it3.hasNext()) {
                ConstraintWidget constraintWidget6 = (ConstraintWidget) it3.next();
                Optimizer.checkMatchParent(this, linearSystem, constraintWidget6);
                constraintWidget6.addToSolver(linearSystem, optimizeFor);
            }
        } else {
            for (int i8 = 0; i8 < size; i8++) {
                ConstraintWidget constraintWidget7 = (ConstraintWidget) this.mChildren.get(i8);
                if (constraintWidget7 instanceof ConstraintWidgetContainer) {
                    ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget7.mListDimensionBehaviors;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = dimensionBehaviourArr[1];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    if (dimensionBehaviour2 == dimensionBehaviour) {
                        constraintWidget7.setHorizontalDimensionBehaviour(dimensionBehaviour4);
                    }
                    if (dimensionBehaviour3 == dimensionBehaviour) {
                        constraintWidget7.setVerticalDimensionBehaviour(dimensionBehaviour4);
                    }
                    constraintWidget7.addToSolver(linearSystem, optimizeFor);
                    if (dimensionBehaviour2 == dimensionBehaviour) {
                        constraintWidget7.setHorizontalDimensionBehaviour(dimensionBehaviour2);
                    }
                    if (dimensionBehaviour3 == dimensionBehaviour) {
                        constraintWidget7.setVerticalDimensionBehaviour(dimensionBehaviour3);
                    }
                } else {
                    Optimizer.checkMatchParent(this, linearSystem, constraintWidget7);
                    if (!(constraintWidget7 instanceof VirtualLayout) && !(constraintWidget7 instanceof Guideline)) {
                        constraintWidget7.addToSolver(linearSystem, optimizeFor);
                    }
                }
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, null, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, null, 1);
        }
    }

    public final boolean directMeasureWithOrientation(int i, boolean z) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        DependencyGraph dependencyGraph = this.mDependencyGraph;
        ConstraintWidgetContainer constraintWidgetContainer = dependencyGraph.mWidgetcontainer;
        boolean z3 = false;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidgetContainer.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = constraintWidgetContainer.getDimensionBehaviour(1);
        int x = constraintWidgetContainer.getX();
        int y = constraintWidgetContainer.getY();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (z && (dimensionBehaviour2 == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour3 == dimensionBehaviour)) {
            Iterator it = dependencyGraph.mRuns.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun widgetRun = (WidgetRun) it.next();
                if (widgetRun.orientation == i && !widgetRun.supportsWrapComputation()) {
                    z = false;
                    break;
                }
            }
            if (i == 0) {
                if (z && dimensionBehaviour2 == dimensionBehaviour) {
                    constraintWidgetContainer.setHorizontalDimensionBehaviour(dimensionBehaviour4);
                    constraintWidgetContainer.setWidth(dependencyGraph.computeWrap(constraintWidgetContainer, 0));
                    constraintWidgetContainer.mHorizontalRun.mDimension.resolve(constraintWidgetContainer.getWidth());
                }
            } else if (z && dimensionBehaviour3 == dimensionBehaviour) {
                constraintWidgetContainer.setVerticalDimensionBehaviour(dimensionBehaviour4);
                constraintWidgetContainer.setHeight(dependencyGraph.computeWrap(constraintWidgetContainer, 1));
                constraintWidgetContainer.mVerticalRun.mDimension.resolve(constraintWidgetContainer.getHeight());
            }
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        if (i == 0) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = constraintWidgetContainer.mListDimensionBehaviors[0];
            if (dimensionBehaviour6 == dimensionBehaviour4 || dimensionBehaviour6 == dimensionBehaviour5) {
                int width = constraintWidgetContainer.getWidth() + x;
                constraintWidgetContainer.mHorizontalRun.end.resolve(width);
                constraintWidgetContainer.mHorizontalRun.mDimension.resolve(width - x);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = constraintWidgetContainer.mListDimensionBehaviors[1];
            if (dimensionBehaviour7 == dimensionBehaviour4 || dimensionBehaviour7 == dimensionBehaviour5) {
                int height = constraintWidgetContainer.getHeight() + y;
                constraintWidgetContainer.mVerticalRun.end.resolve(height);
                constraintWidgetContainer.mVerticalRun.mDimension.resolve(height - y);
                z2 = true;
            }
            z2 = false;
        }
        dependencyGraph.measureWidgets();
        Iterator it2 = dependencyGraph.mRuns.iterator();
        while (it2.hasNext()) {
            WidgetRun widgetRun2 = (WidgetRun) it2.next();
            if (widgetRun2.orientation == i && (widgetRun2.mWidget != constraintWidgetContainer || widgetRun2.mResolved)) {
                widgetRun2.applyToWidget();
            }
        }
        Iterator it3 = dependencyGraph.mRuns.iterator();
        while (true) {
            if (!it3.hasNext()) {
                z3 = true;
                break;
            }
            WidgetRun widgetRun3 = (WidgetRun) it3.next();
            if (widgetRun3.orientation == i && (z2 || widgetRun3.mWidget != constraintWidgetContainer)) {
                if (!widgetRun3.start.resolved) {
                    break;
                }
                if (!widgetRun3.end.resolved) {
                    break;
                }
                if (!(widgetRun3 instanceof ChainRun) && !widgetRun3.mDimension.resolved) {
                    break;
                }
            }
        }
        constraintWidgetContainer.setHorizontalDimensionBehaviour(dimensionBehaviour2);
        constraintWidgetContainer.setVerticalDimensionBehaviour(dimensionBehaviour3);
        return z3;
    }

    /* JADX WARN: Removed duplicated region for block: B:194:0x0694  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x06aa A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x06b9  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x06ca  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0823  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x087b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0887 A[LOOP:13: B:272:0x0885->B:273:0x0887, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:286:0x08f0  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x090c  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x091c  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0961  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0963  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0919  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0860  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0976  */
    /* JADX WARN: Removed duplicated region for block: B:608:0x0605  */
    /* JADX WARN: Removed duplicated region for block: B:626:0x0632 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:629:0x0642  */
    /* JADX WARN: Removed duplicated region for block: B:636:0x0660  */
    /* JADX WARN: Removed duplicated region for block: B:643:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x065a  */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void layout() {
        /*
            Method dump skipped, instructions count: 2436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.layout():void");
    }

    public final boolean optimizeFor(int i) {
        return (this.mOptimizationLevel & i) == i;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingTop = 0;
        this.mChildren.clear();
        super.reset();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void resetSolverVariables(Cache cache) {
        super.resetSolverVariables(cache);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).resetSolverVariables(cache);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void updateFromRuns(boolean z, boolean z2) {
        super.updateFromRuns(z, z2);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).updateFromRuns(z, z2);
        }
    }
}
