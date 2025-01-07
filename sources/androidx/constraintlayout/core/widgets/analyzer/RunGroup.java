package androidx.constraintlayout.core.widgets.analyzer;

import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RunGroup {
    public WidgetRun mFirstRun;
    public ArrayList mRuns;

    public static long traverseEnd(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = ((ArrayList) dependencyNode.mDependencies).size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = (Dependency) ((ArrayList) dependencyNode.mDependencies).get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.mRun != widgetRun) {
                    j2 = Math.min(j2, traverseEnd(dependencyNode2, dependencyNode2.mMargin + j));
                }
            }
        }
        if (dependencyNode != widgetRun.end) {
            return j2;
        }
        long wrapDimension = widgetRun.getWrapDimension();
        long j3 = j - wrapDimension;
        return Math.min(Math.min(j2, traverseEnd(widgetRun.start, j3)), j3 - r8.mMargin);
    }

    public static long traverseStart(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = ((ArrayList) dependencyNode.mDependencies).size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = (Dependency) ((ArrayList) dependencyNode.mDependencies).get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.mRun != widgetRun) {
                    j2 = Math.max(j2, traverseStart(dependencyNode2, dependencyNode2.mMargin + j));
                }
            }
        }
        if (dependencyNode != widgetRun.start) {
            return j2;
        }
        long wrapDimension = widgetRun.getWrapDimension();
        long j3 = j + wrapDimension;
        return Math.max(Math.max(j2, traverseStart(widgetRun.end, j3)), j3 - r8.mMargin);
    }
}
