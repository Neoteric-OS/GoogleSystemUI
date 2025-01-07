package androidx.constraintlayout.core.widgets.analyzer;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DependencyGraph {
    public ConstraintWidgetContainer mContainer;
    public ArrayList mGroups;
    public BasicMeasure.Measure mMeasure;
    public ConstraintLayout.Measurer mMeasurer;
    public boolean mNeedBuildGraph;
    public boolean mNeedRedoMeasures;
    public ArrayList mRuns;
    public ConstraintWidgetContainer mWidgetcontainer;

    public final void applyGroup(DependencyNode dependencyNode, int i, ArrayList arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun.mRunGroup == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.mWidgetcontainer;
            if (widgetRun == constraintWidgetContainer.mHorizontalRun || widgetRun == constraintWidgetContainer.mVerticalRun) {
                return;
            }
            if (runGroup == null) {
                runGroup = new RunGroup();
                runGroup.mFirstRun = null;
                runGroup.mRuns = new ArrayList();
                runGroup.mFirstRun = widgetRun;
                arrayList.add(runGroup);
            }
            widgetRun.mRunGroup = runGroup;
            runGroup.mRuns.add(widgetRun);
            DependencyNode dependencyNode2 = widgetRun.start;
            for (Dependency dependency : dependencyNode2.mDependencies) {
                if (dependency instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency, i, arrayList, runGroup);
                }
            }
            DependencyNode dependencyNode3 = widgetRun.end;
            for (Dependency dependency2 : dependencyNode3.mDependencies) {
                if (dependency2 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency2, i, arrayList, runGroup);
                }
            }
            if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.mDependencies) {
                    if (dependency3 instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency3, i, arrayList, runGroup);
                    }
                }
            }
            Iterator it = dependencyNode2.mTargets.iterator();
            while (it.hasNext()) {
                applyGroup((DependencyNode) it.next(), i, arrayList, runGroup);
            }
            Iterator it2 = dependencyNode3.mTargets.iterator();
            while (it2.hasNext()) {
                applyGroup((DependencyNode) it2.next(), i, arrayList, runGroup);
            }
            if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                Iterator it3 = ((VerticalWidgetRun) widgetRun).baseline.mTargets.iterator();
                while (it3.hasNext()) {
                    applyGroup((DependencyNode) it3.next(), i, arrayList, runGroup);
                }
            }
        }
    }

    public final void basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer) {
        int i;
        int i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        Iterator it = constraintWidgetContainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = dimensionBehaviourArr[1];
            if (constraintWidget.mVisibility == 8) {
                constraintWidget.measured = true;
            } else {
                float f = constraintWidget.mMatchConstraintPercentWidth;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (f < 1.0f && dimensionBehaviour2 == dimensionBehaviour4) {
                    constraintWidget.mMatchConstraintDefaultWidth = 2;
                }
                float f2 = constraintWidget.mMatchConstraintPercentHeight;
                if (f2 < 1.0f && dimensionBehaviour3 == dimensionBehaviour4) {
                    constraintWidget.mMatchConstraintDefaultHeight = 2;
                }
                float f3 = constraintWidget.mDimensionRatio;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (f3 > 0.0f) {
                    if (dimensionBehaviour2 == dimensionBehaviour4 && (dimensionBehaviour3 == dimensionBehaviour5 || dimensionBehaviour3 == dimensionBehaviour6)) {
                        constraintWidget.mMatchConstraintDefaultWidth = 3;
                    } else if (dimensionBehaviour3 == dimensionBehaviour4 && (dimensionBehaviour2 == dimensionBehaviour5 || dimensionBehaviour2 == dimensionBehaviour6)) {
                        constraintWidget.mMatchConstraintDefaultHeight = 3;
                    } else if (dimensionBehaviour2 == dimensionBehaviour4 && dimensionBehaviour3 == dimensionBehaviour4) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 0) {
                            constraintWidget.mMatchConstraintDefaultWidth = 3;
                        }
                        if (constraintWidget.mMatchConstraintDefaultHeight == 0) {
                            constraintWidget.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                ConstraintAnchor constraintAnchor = constraintWidget.mRight;
                ConstraintAnchor constraintAnchor2 = constraintWidget.mLeft;
                if (dimensionBehaviour2 == dimensionBehaviour4 && constraintWidget.mMatchConstraintDefaultWidth == 1 && (constraintAnchor2.mTarget == null || constraintAnchor.mTarget == null)) {
                    dimensionBehaviour2 = dimensionBehaviour5;
                }
                ConstraintAnchor constraintAnchor3 = constraintWidget.mBottom;
                ConstraintAnchor constraintAnchor4 = constraintWidget.mTop;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = (dimensionBehaviour3 == dimensionBehaviour4 && constraintWidget.mMatchConstraintDefaultHeight == 1 && (constraintAnchor4.mTarget == null || constraintAnchor3.mTarget == null)) ? dimensionBehaviour5 : dimensionBehaviour3;
                HorizontalWidgetRun horizontalWidgetRun = constraintWidget.mHorizontalRun;
                horizontalWidgetRun.mDimensionBehavior = dimensionBehaviour2;
                int i3 = constraintWidget.mMatchConstraintDefaultWidth;
                horizontalWidgetRun.matchConstraintsType = i3;
                VerticalWidgetRun verticalWidgetRun = constraintWidget.mVerticalRun;
                verticalWidgetRun.mDimensionBehavior = dimensionBehaviour7;
                Iterator it2 = it;
                int i4 = constraintWidget.mMatchConstraintDefaultHeight;
                verticalWidgetRun.matchConstraintsType = i4;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour2 == dimensionBehaviour8 || dimensionBehaviour2 == dimensionBehaviour6 || dimensionBehaviour2 == dimensionBehaviour5) && (dimensionBehaviour7 == dimensionBehaviour8 || dimensionBehaviour7 == dimensionBehaviour6 || dimensionBehaviour7 == dimensionBehaviour5)) {
                    int width = constraintWidget.getWidth();
                    if (dimensionBehaviour2 == dimensionBehaviour8) {
                        i = (constraintWidgetContainer.getWidth() - constraintAnchor2.mMargin) - constraintAnchor.mMargin;
                        dimensionBehaviour2 = dimensionBehaviour6;
                    } else {
                        i = width;
                    }
                    int height = constraintWidget.getHeight();
                    if (dimensionBehaviour7 == dimensionBehaviour8) {
                        i2 = (constraintWidgetContainer.getHeight() - constraintAnchor4.mMargin) - constraintAnchor3.mMargin;
                        dimensionBehaviour = dimensionBehaviour6;
                    } else {
                        i2 = height;
                        dimensionBehaviour = dimensionBehaviour7;
                    }
                    measure(constraintWidget, dimensionBehaviour2, i, dimensionBehaviour, i2);
                    constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                    constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                    constraintWidget.measured = true;
                } else {
                    ConstraintAnchor[] constraintAnchorArr = constraintWidget.mListAnchors;
                    if (dimensionBehaviour2 == dimensionBehaviour4 && (dimensionBehaviour7 == dimensionBehaviour5 || dimensionBehaviour7 == dimensionBehaviour6)) {
                        if (i3 == 3) {
                            if (dimensionBehaviour7 == dimensionBehaviour5) {
                                measure(constraintWidget, dimensionBehaviour5, 0, dimensionBehaviour5, 0);
                            }
                            int height2 = constraintWidget.getHeight();
                            measure(constraintWidget, dimensionBehaviour6, (int) ((height2 * constraintWidget.mDimensionRatio) + 0.5f), dimensionBehaviour6, height2);
                            constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                            constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        } else if (i3 == 1) {
                            measure(constraintWidget, dimensionBehaviour5, 0, dimensionBehaviour7, 0);
                            constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                        } else if (i3 == 2) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = constraintWidgetContainer.mListDimensionBehaviors[0];
                            if (dimensionBehaviour9 == dimensionBehaviour6 || dimensionBehaviour9 == dimensionBehaviour8) {
                                measure(constraintWidget, dimensionBehaviour6, (int) ((f * constraintWidgetContainer.getWidth()) + 0.5f), dimensionBehaviour7, constraintWidget.getHeight());
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        } else if (constraintAnchorArr[0].mTarget == null || constraintAnchorArr[1].mTarget == null) {
                            measure(constraintWidget, dimensionBehaviour5, 0, dimensionBehaviour7, 0);
                            constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                            constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        }
                    }
                    if (dimensionBehaviour7 == dimensionBehaviour4 && (dimensionBehaviour2 == dimensionBehaviour5 || dimensionBehaviour2 == dimensionBehaviour6)) {
                        if (i4 == 3) {
                            if (dimensionBehaviour2 == dimensionBehaviour5) {
                                measure(constraintWidget, dimensionBehaviour5, 0, dimensionBehaviour5, 0);
                            }
                            int width2 = constraintWidget.getWidth();
                            float f4 = constraintWidget.mDimensionRatio;
                            if (constraintWidget.mDimensionRatioSide == -1) {
                                f4 = 1.0f / f4;
                            }
                            measure(constraintWidget, dimensionBehaviour6, width2, dimensionBehaviour6, (int) ((width2 * f4) + 0.5f));
                            constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                            constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        } else if (i4 == 1) {
                            measure(constraintWidget, dimensionBehaviour2, 0, dimensionBehaviour5, 0);
                            constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                        } else if (i4 == 2) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = constraintWidgetContainer.mListDimensionBehaviors[1];
                            if (dimensionBehaviour10 == dimensionBehaviour6 || dimensionBehaviour10 == dimensionBehaviour8) {
                                measure(constraintWidget, dimensionBehaviour2, constraintWidget.getWidth(), dimensionBehaviour6, (int) ((f2 * constraintWidgetContainer.getHeight()) + 0.5f));
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        } else if (constraintAnchorArr[2].mTarget == null || constraintAnchorArr[3].mTarget == null) {
                            measure(constraintWidget, dimensionBehaviour5, 0, dimensionBehaviour7, 0);
                            constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                            constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        }
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour4 && dimensionBehaviour7 == dimensionBehaviour4) {
                        if (i3 == 1 || i4 == 1) {
                            measure(constraintWidget, dimensionBehaviour5, 0, dimensionBehaviour5, 0);
                            constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                            constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                        } else if (i4 == 2 && i3 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer.mListDimensionBehaviors;
                            if (dimensionBehaviourArr2[0] == dimensionBehaviour6 && dimensionBehaviourArr2[1] == dimensionBehaviour6) {
                                measure(constraintWidget, dimensionBehaviour6, (int) ((f * constraintWidgetContainer.getWidth()) + 0.5f), dimensionBehaviour6, (int) ((f2 * constraintWidgetContainer.getHeight()) + 0.5f));
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        }
                    }
                }
                it = it2;
            }
        }
    }

    public final void buildGraph() {
        ArrayList arrayList = this.mRuns;
        arrayList.clear();
        ConstraintWidgetContainer constraintWidgetContainer = this.mContainer;
        constraintWidgetContainer.mHorizontalRun.clear();
        constraintWidgetContainer.mVerticalRun.clear();
        arrayList.add(constraintWidgetContainer.mHorizontalRun);
        arrayList.add(constraintWidgetContainer.mVerticalRun);
        Iterator it = constraintWidgetContainer.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (constraintWidget instanceof Guideline) {
                GuidelineReference guidelineReference = new GuidelineReference(constraintWidget);
                constraintWidget.mHorizontalRun.clear();
                constraintWidget.mVerticalRun.clear();
                guidelineReference.orientation = ((Guideline) constraintWidget).mOrientation;
                arrayList.add(guidelineReference);
            } else {
                if (constraintWidget.isInHorizontalChain()) {
                    if (constraintWidget.horizontalChainRun == null) {
                        constraintWidget.horizontalChainRun = new ChainRun(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.horizontalChainRun);
                } else {
                    arrayList.add(constraintWidget.mHorizontalRun);
                }
                if (constraintWidget.isInVerticalChain()) {
                    if (constraintWidget.verticalChainRun == null) {
                        constraintWidget.verticalChainRun = new ChainRun(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.verticalChainRun);
                } else {
                    arrayList.add(constraintWidget.mVerticalRun);
                }
                if (constraintWidget instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((WidgetRun) it2.next()).clear();
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it3.next();
            if (widgetRun.mWidget != constraintWidgetContainer) {
                widgetRun.apply();
            }
        }
        this.mGroups.clear();
        ConstraintWidgetContainer constraintWidgetContainer2 = this.mWidgetcontainer;
        findGroup(constraintWidgetContainer2.mHorizontalRun, 0, this.mGroups);
        findGroup(constraintWidgetContainer2.mVerticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public final int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        int i2;
        long max;
        float f;
        DependencyGraph dependencyGraph = this;
        ConstraintWidgetContainer constraintWidgetContainer2 = constraintWidgetContainer;
        int size = dependencyGraph.mGroups.size();
        long j = 0;
        int i3 = 0;
        long j2 = 0;
        while (i3 < size) {
            WidgetRun widgetRun = ((RunGroup) dependencyGraph.mGroups.get(i3)).mFirstRun;
            if (!(widgetRun instanceof ChainRun) ? !(i != 0 ? (widgetRun instanceof VerticalWidgetRun) : (widgetRun instanceof HorizontalWidgetRun)) : ((ChainRun) widgetRun).orientation != i) {
                DependencyNode dependencyNode = (i == 0 ? constraintWidgetContainer2.mHorizontalRun : constraintWidgetContainer2.mVerticalRun).start;
                DependencyNode dependencyNode2 = (i == 0 ? constraintWidgetContainer2.mHorizontalRun : constraintWidgetContainer2.mVerticalRun).end;
                boolean contains = widgetRun.start.mTargets.contains(dependencyNode);
                DependencyNode dependencyNode3 = widgetRun.end;
                boolean contains2 = dependencyNode3.mTargets.contains(dependencyNode2);
                long wrapDimension = widgetRun.getWrapDimension();
                DependencyNode dependencyNode4 = widgetRun.start;
                if (contains && contains2) {
                    long traverseStart = RunGroup.traverseStart(dependencyNode4, j);
                    long traverseEnd = RunGroup.traverseEnd(dependencyNode3, j);
                    long j3 = traverseStart - wrapDimension;
                    int i4 = dependencyNode3.mMargin;
                    i2 = i3;
                    if (j3 >= (-i4)) {
                        j3 += i4;
                    }
                    long j4 = dependencyNode4.mMargin;
                    long j5 = ((-traverseEnd) - wrapDimension) - j4;
                    if (j5 >= j4) {
                        j5 -= j4;
                    }
                    ConstraintWidget constraintWidget = widgetRun.mWidget;
                    if (i == 0) {
                        f = constraintWidget.mHorizontalBiasPercent;
                    } else if (i == 1) {
                        f = constraintWidget.mVerticalBiasPercent;
                    } else {
                        constraintWidget.getClass();
                        f = -1.0f;
                    }
                    float f2 = f > 0.0f ? (long) ((j3 / (1.0f - f)) + (j5 / f)) : 0L;
                    max = (dependencyNode4.mMargin + ((((long) ((f2 * f) + 0.5f)) + wrapDimension) + ((long) AndroidFlingSpline$$ExternalSyntheticOutline0.m(1.0f, f, f2, 0.5f)))) - dependencyNode3.mMargin;
                } else {
                    i2 = i3;
                    max = contains ? Math.max(RunGroup.traverseStart(dependencyNode4, dependencyNode4.mMargin), dependencyNode4.mMargin + wrapDimension) : contains2 ? Math.max(-RunGroup.traverseEnd(dependencyNode3, dependencyNode3.mMargin), (-dependencyNode3.mMargin) + wrapDimension) : (widgetRun.getWrapDimension() + dependencyNode4.mMargin) - dependencyNode3.mMargin;
                }
            } else {
                max = j;
                i2 = i3;
            }
            j2 = Math.max(j2, max);
            i3 = i2 + 1;
            dependencyGraph = this;
            constraintWidgetContainer2 = constraintWidgetContainer;
            j = 0;
        }
        return (int) j2;
    }

    public final void findGroup(WidgetRun widgetRun, int i, ArrayList arrayList) {
        DependencyNode dependencyNode;
        Iterator it = widgetRun.start.mDependencies.iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            dependencyNode = widgetRun.end;
            if (!hasNext) {
                break;
            }
            Dependency dependency = (Dependency) it.next();
            if (dependency instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency, i, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency).start, i, arrayList, null);
            }
        }
        for (Dependency dependency2 : dependencyNode.mDependencies) {
            if (dependency2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency2, i, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency2).end, i, arrayList, null);
            }
        }
        if (i == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.mDependencies) {
                if (dependency3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency3, i, arrayList, null);
                }
            }
        }
    }

    public final void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i;
        measure.verticalDimension = i2;
        this.mMeasurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(measure.measuredWidth);
        constraintWidget.setHeight(measure.measuredHeight);
        constraintWidget.mHasBaseline = measure.measuredHasBaseline;
        constraintWidget.setBaselineDistance(measure.measuredBaseline);
    }

    public final void measureWidgets() {
        BaselineDimensionDependency baselineDimensionDependency;
        Iterator it = this.mWidgetcontainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (!constraintWidget.measured) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i = constraintWidget.mMatchConstraintDefaultWidth;
                int i2 = constraintWidget.mMatchConstraintDefaultHeight;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z2 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == dimensionBehaviour4 && i == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == dimensionBehaviour4 && i2 == 1)) {
                    z = true;
                }
                DimensionDependency dimensionDependency = constraintWidget.mHorizontalRun.mDimension;
                boolean z3 = dimensionDependency.resolved;
                DimensionDependency dimensionDependency2 = constraintWidget.mVerticalRun.mDimension;
                boolean z4 = dimensionDependency2.resolved;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (z3 && z4) {
                    measure(constraintWidget, dimensionBehaviour5, dimensionDependency.value, dimensionBehaviour5, dimensionDependency2.value);
                    constraintWidget.measured = true;
                } else if (z3 && z) {
                    measure(constraintWidget, dimensionBehaviour5, dimensionDependency.value, dimensionBehaviour3, dimensionDependency2.value);
                    if (dimensionBehaviour2 == dimensionBehaviour4) {
                        constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                    } else {
                        constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                        constraintWidget.measured = true;
                    }
                } else if (z4 && z2) {
                    measure(constraintWidget, dimensionBehaviour3, dimensionDependency.value, dimensionBehaviour5, dimensionDependency2.value);
                    if (dimensionBehaviour == dimensionBehaviour4) {
                        constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                    } else {
                        constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                        constraintWidget.measured = true;
                    }
                }
                if (constraintWidget.measured && (baselineDimensionDependency = constraintWidget.mVerticalRun.mBaselineDimension) != null) {
                    baselineDimensionDependency.resolve(constraintWidget.mBaselineDistance);
                }
            }
        }
    }
}
