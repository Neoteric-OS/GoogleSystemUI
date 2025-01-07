package com.android.compose.animation.scene;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ElementScopeImpl$contentScope$1 implements ContentScope, BoxScope {
    public final /* synthetic */ ContentScope $$delegate_0;

    public ElementScopeImpl$contentScope$1(ElementScopeImpl elementScopeImpl) {
        this.$$delegate_0 = elementScopeImpl.delegateContentScope;
    }

    @Override // com.android.compose.animation.scene.ContentScope
    public final void Element(ElementKey elementKey, Modifier modifier, Function3 function3, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(319846566);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        this.$$delegate_0.Element(elementKey, modifier, function3, composerImpl, i & 1022);
        composerImpl.end(false);
    }

    @Override // androidx.compose.foundation.layout.BoxScope
    public final Modifier align(Modifier modifier, Alignment alignment) {
        return BoxScopeInstance.INSTANCE.align(modifier, alignment);
    }

    @Override // com.android.compose.animation.scene.ContentScope
    public final Modifier element(Modifier modifier, ElementKey elementKey) {
        return this.$$delegate_0.element(modifier, elementKey);
    }

    @Override // com.android.compose.animation.scene.ContentScope
    public final Modifier horizontalNestedScrollToScene(Modifier modifier, NestedScrollBehavior nestedScrollBehavior, NestedScrollBehavior nestedScrollBehavior2, Function0 function0) {
        return this.$$delegate_0.horizontalNestedScrollToScene(modifier, nestedScrollBehavior, nestedScrollBehavior2, function0);
    }

    @Override // androidx.compose.foundation.layout.BoxScope
    public final Modifier matchParentSize(Modifier modifier) {
        return BoxScopeInstance.INSTANCE.matchParentSize(Modifier.Companion.$$INSTANCE);
    }
}
