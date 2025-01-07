package androidx.constraintlayout.core.widgets.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class DependencyNode implements Dependency {
    public int mMargin;
    public final WidgetRun mRun;
    public int value;
    public WidgetRun updateDelegate = null;
    public boolean delegateToWidgetRun = false;
    public boolean readyToSolve = false;
    public Type mType = Type.UNKNOWN;
    public int mMarginFactor = 1;
    public DimensionDependency mMarginDependency = null;
    public boolean resolved = false;
    public final List mDependencies = new ArrayList();
    public final List mTargets = new ArrayList();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Type {
        public static final /* synthetic */ Type[] $VALUES;
        public static final Type BASELINE;
        public static final Type BOTTOM;
        public static final Type HORIZONTAL_DIMENSION;
        public static final Type LEFT;
        public static final Type RIGHT;
        public static final Type TOP;
        public static final Type UNKNOWN;
        public static final Type VERTICAL_DIMENSION;

        static {
            Type type = new Type("UNKNOWN", 0);
            UNKNOWN = type;
            Type type2 = new Type("HORIZONTAL_DIMENSION", 1);
            HORIZONTAL_DIMENSION = type2;
            Type type3 = new Type("VERTICAL_DIMENSION", 2);
            VERTICAL_DIMENSION = type3;
            Type type4 = new Type("LEFT", 3);
            LEFT = type4;
            Type type5 = new Type("RIGHT", 4);
            RIGHT = type5;
            Type type6 = new Type("TOP", 5);
            TOP = type6;
            Type type7 = new Type("BOTTOM", 6);
            BOTTOM = type7;
            Type type8 = new Type("BASELINE", 7);
            BASELINE = type8;
            $VALUES = new Type[]{type, type2, type3, type4, type5, type6, type7, type8};
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public DependencyNode(WidgetRun widgetRun) {
        this.mRun = widgetRun;
    }

    public final void addDependency(WidgetRun widgetRun) {
        this.mDependencies.add(widgetRun);
        if (this.resolved) {
            widgetRun.update(widgetRun);
        }
    }

    public final void clear() {
        this.mTargets.clear();
        this.mDependencies.clear();
        this.resolved = false;
        this.value = 0;
        this.readyToSolve = false;
        this.delegateToWidgetRun = false;
    }

    public void resolve(int i) {
        if (this.resolved) {
            return;
        }
        this.resolved = true;
        this.value = i;
        for (Dependency dependency : this.mDependencies) {
            dependency.update(dependency);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mRun.mWidget.mDebugName);
        sb.append(":");
        sb.append(this.mType);
        sb.append("(");
        sb.append(this.resolved ? Integer.valueOf(this.value) : "unresolved");
        sb.append(") <t=");
        sb.append(((ArrayList) this.mTargets).size());
        sb.append(":d=");
        sb.append(((ArrayList) this.mDependencies).size());
        sb.append(">");
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public final void update(Dependency dependency) {
        Iterator it = this.mTargets.iterator();
        while (it.hasNext()) {
            if (!((DependencyNode) it.next()).resolved) {
                return;
            }
        }
        this.readyToSolve = true;
        WidgetRun widgetRun = this.updateDelegate;
        if (widgetRun != null) {
            widgetRun.update(this);
        }
        if (this.delegateToWidgetRun) {
            this.mRun.update(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i = 0;
        for (DependencyNode dependencyNode2 : this.mTargets) {
            if (!(dependencyNode2 instanceof DimensionDependency)) {
                i++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i == 1 && dependencyNode.resolved) {
            DimensionDependency dimensionDependency = this.mMarginDependency;
            if (dimensionDependency != null) {
                if (!dimensionDependency.resolved) {
                    return;
                } else {
                    this.mMargin = this.mMarginFactor * dimensionDependency.value;
                }
            }
            resolve(dependencyNode.value + this.mMargin);
        }
        WidgetRun widgetRun2 = this.updateDelegate;
        if (widgetRun2 != null) {
            widgetRun2.update(this);
        }
    }
}
