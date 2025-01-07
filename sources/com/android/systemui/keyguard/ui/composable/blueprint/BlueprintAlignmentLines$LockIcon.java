package com.android.systemui.keyguard.ui.composable.blueprint;

import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BlueprintAlignmentLines$LockIcon {
    public static final VerticalAlignmentLine Left = new VerticalAlignmentLine(new Function2() { // from class: com.android.systemui.keyguard.ui.composable.blueprint.BlueprintAlignmentLines$LockIcon$Left$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(Math.min(((Number) obj).intValue(), ((Number) obj2).intValue()));
        }
    });
    public static final HorizontalAlignmentLine Top = new HorizontalAlignmentLine(new Function2() { // from class: com.android.systemui.keyguard.ui.composable.blueprint.BlueprintAlignmentLines$LockIcon$Top$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(Math.min(((Number) obj).intValue(), ((Number) obj2).intValue()));
        }
    });
    public static final VerticalAlignmentLine Right = new VerticalAlignmentLine(new Function2() { // from class: com.android.systemui.keyguard.ui.composable.blueprint.BlueprintAlignmentLines$LockIcon$Right$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(Math.max(((Number) obj).intValue(), ((Number) obj2).intValue()));
        }
    });
    public static final HorizontalAlignmentLine Bottom = new HorizontalAlignmentLine(new Function2() { // from class: com.android.systemui.keyguard.ui.composable.blueprint.BlueprintAlignmentLines$LockIcon$Bottom$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(Math.max(((Number) obj).intValue(), ((Number) obj2).intValue()));
        }
    });
}
