package com.android.compose.animation.scene;

import androidx.compose.ui.unit.LayoutDirection;
import com.android.compose.animation.scene.SwipeSource;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Edge implements SwipeSource {
    public static final /* synthetic */ Edge[] $VALUES;
    public static final Edge Bottom = null;
    public static final Edge End;
    public static final Edge Start = null;
    public static final Edge Top = null;
    private final Function1 resolveEdge;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Resolved implements SwipeSource.Resolved {
        public static final /* synthetic */ Resolved[] $VALUES;
        public static final Resolved Bottom;
        public static final Resolved Left;
        public static final Resolved Right;
        public static final Resolved Top;

        static {
            Resolved resolved = new Resolved("Left", 0);
            Left = resolved;
            Resolved resolved2 = new Resolved("Right", 1);
            Right = resolved2;
            Resolved resolved3 = new Resolved("Top", 2);
            Top = resolved3;
            Resolved resolved4 = new Resolved("Bottom", 3);
            Bottom = resolved4;
            Resolved[] resolvedArr = {resolved, resolved2, resolved3, resolved4};
            $VALUES = resolvedArr;
            EnumEntriesKt.enumEntries(resolvedArr);
        }

        public static Resolved valueOf(String str) {
            return (Resolved) Enum.valueOf(Resolved.class, str);
        }

        public static Resolved[] values() {
            return (Resolved[]) $VALUES.clone();
        }
    }

    static {
        Edge edge = new Edge("Top", 0, new Function1() { // from class: com.android.compose.animation.scene.Edge.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Resolved.Top;
            }
        });
        Edge edge2 = new Edge("Bottom", 1, new Function1() { // from class: com.android.compose.animation.scene.Edge.2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Resolved.Bottom;
            }
        });
        Edge edge3 = new Edge("Left", 2, new Function1() { // from class: com.android.compose.animation.scene.Edge.3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Resolved.Left;
            }
        });
        Edge edge4 = new Edge("Right", 3, new Function1() { // from class: com.android.compose.animation.scene.Edge.4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Resolved.Right;
            }
        });
        Edge edge5 = new Edge("Start", 4, new Function1() { // from class: com.android.compose.animation.scene.Edge.5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((LayoutDirection) obj) == LayoutDirection.Ltr ? Resolved.Left : Resolved.Right;
            }
        });
        Edge edge6 = new Edge("End", 5, new Function1() { // from class: com.android.compose.animation.scene.Edge.6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((LayoutDirection) obj) == LayoutDirection.Ltr ? Resolved.Right : Resolved.Left;
            }
        });
        End = edge6;
        Edge[] edgeArr = {edge, edge2, edge3, edge4, edge5, edge6};
        $VALUES = edgeArr;
        EnumEntriesKt.enumEntries(edgeArr);
    }

    public Edge(String str, int i, Function1 function1) {
        this.resolveEdge = function1;
    }

    public static Edge valueOf(String str) {
        return (Edge) Enum.valueOf(Edge.class, str);
    }

    public static Edge[] values() {
        return (Edge[]) $VALUES.clone();
    }

    @Override // com.android.compose.animation.scene.SwipeSource
    public final Resolved resolve(LayoutDirection layoutDirection) {
        return (Resolved) this.resolveEdge.invoke(layoutDirection);
    }
}
