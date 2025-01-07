package com.android.compose.animation.scene;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import com.android.compose.animation.scene.Element;
import com.android.compose.animation.scene.ElementNode;
import com.android.compose.animation.scene.content.Content;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ElementModifier extends ModifierNodeElement {
    public final Content content;
    public final List currentTransitionStates;
    public final ElementKey key;
    public final SceneTransitionLayoutImpl layoutImpl;

    public ElementModifier(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, List list, Content content, ElementKey elementKey) {
        this.layoutImpl = sceneTransitionLayoutImpl;
        this.currentTransitionStates = list;
        this.content = content;
        this.key = elementKey;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new ElementNode(this.layoutImpl, this.currentTransitionStates, this.content, this.key);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElementModifier)) {
            return false;
        }
        ElementModifier elementModifier = (ElementModifier) obj;
        return Intrinsics.areEqual(this.layoutImpl, elementModifier.layoutImpl) && Intrinsics.areEqual(this.currentTransitionStates, elementModifier.currentTransitionStates) && Intrinsics.areEqual(this.content, elementModifier.content) && Intrinsics.areEqual(this.key, elementModifier.key);
    }

    public final int hashCode() {
        return this.key.identity.hashCode() + ((this.content.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.layoutImpl.hashCode() * 31, 31, this.currentTransitionStates)) * 31);
    }

    public final String toString() {
        return "ElementModifier(layoutImpl=" + this.layoutImpl + ", currentTransitionStates=" + this.currentTransitionStates + ", content=" + this.content + ", key=" + this.key + ")";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ElementNode elementNode = (ElementNode) node;
        List list = this.currentTransitionStates;
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = elementNode.layoutImpl;
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl2 = this.layoutImpl;
        if (sceneTransitionLayoutImpl2.equals(sceneTransitionLayoutImpl)) {
            if (this.content.equals(elementNode.content)) {
                elementNode.currentTransitionStates = list;
                Element.State state = elementNode._stateInContent;
                Intrinsics.checkNotNull(state);
                state.nodes.remove(elementNode);
                Element element = elementNode._element;
                Intrinsics.checkNotNull(element);
                Element.State state2 = elementNode._stateInContent;
                Intrinsics.checkNotNull(state2);
                elementNode.key = this.key;
                elementNode.updateElementAndContentValues();
                Element.State state3 = elementNode._stateInContent;
                Intrinsics.checkNotNull(state3);
                state3.nodes.add(elementNode);
                BuildersKt.launch$default(elementNode.getCoroutineScope(), null, null, new ElementNode$addNodeToContentState$1(elementNode, null), 3);
                ElementNode.Companion.access$maybePruneMaps(sceneTransitionLayoutImpl2, element, state2);
                return;
            }
        }
        throw new IllegalStateException("Check failed.");
    }
}
