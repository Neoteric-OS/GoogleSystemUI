package androidx.compose.ui.node;

import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LayoutNodeKt {
    public static final Density DefaultDensity = DensityKt.Density$default();

    public static final Owner requireOwner(LayoutNode layoutNode) {
        AndroidComposeView androidComposeView = layoutNode.owner;
        if (androidComposeView != null) {
            return androidComposeView;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("LayoutNode should be attached to an owner");
        throw null;
    }
}
