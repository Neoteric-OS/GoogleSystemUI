package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ClipScrollableContainerKt {
    public static final Modifier HorizontalScrollableClipModifier;
    public static final float MaxSupportedElevation = 30;
    public static final Modifier VerticalScrollableClipModifier;

    static {
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        HorizontalScrollableClipModifier = ClipKt.clip(companion, new ClipScrollableContainerKt$HorizontalScrollableClipModifier$1());
        VerticalScrollableClipModifier = ClipKt.clip(companion, new ClipScrollableContainerKt$VerticalScrollableClipModifier$1());
    }
}
