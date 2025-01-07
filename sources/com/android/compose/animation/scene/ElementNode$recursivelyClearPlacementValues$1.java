package com.android.compose.animation.scene;

import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNode$Companion$TraverseDescendantsAction;
import com.android.compose.animation.scene.Element;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ElementNode$recursivelyClearPlacementValues$1 extends Lambda implements Function1 {
    public static final ElementNode$recursivelyClearPlacementValues$1 INSTANCE = new ElementNode$recursivelyClearPlacementValues$1();

    public ElementNode$recursivelyClearPlacementValues$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Element.State state = ((ElementNode) ((TraversableNode) obj))._stateInContent;
        if (state != null) {
            state.lastOffset = 9205357640488583168L;
            state.lastScale = Scale.Unspecified;
            int i = Element.$r8$clinit;
            state.lastAlpha = Float.MAX_VALUE;
        }
        return TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal;
    }
}
