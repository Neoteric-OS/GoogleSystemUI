package com.android.compose.animation.scene.content;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import com.android.compose.animation.scene.ContentScope;
import com.android.compose.animation.scene.ElementKey;
import com.android.compose.animation.scene.ElementKt;
import com.android.compose.animation.scene.MovableElementKt;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl;
import com.android.compose.animation.scene.NestedScrollBehavior;
import com.android.compose.animation.scene.NestedScrollToSceneKt;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContentScopeImpl implements ContentScope {
    public final Content content;
    public final SceneTransitionLayoutImpl layoutImpl;
    public final MutableSceneTransitionLayoutStateImpl layoutState;

    public ContentScopeImpl(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Content content) {
        this.layoutImpl = sceneTransitionLayoutImpl;
        this.content = content;
        MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = sceneTransitionLayoutImpl.state;
    }

    @Override // com.android.compose.animation.scene.ContentScope
    public final void Element(final ElementKey elementKey, final Modifier modifier, final Function3 function3, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-928964390);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(elementKey) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(modifier) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerImpl.changedInstance(function3) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerImpl.changed(this) ? 2048 : 1024;
        }
        if ((i2 & 5851) == 1170 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            MovableElementKt.Element(this.layoutImpl, this.content, elementKey, modifier, function3, composerImpl, (i2 << 6) & 65408);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.compose.animation.scene.content.ContentScopeImpl$Element$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ContentScopeImpl.this.Element(elementKey, modifier, function3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    @Override // com.android.compose.animation.scene.ContentScope
    public final Modifier element(Modifier modifier, ElementKey elementKey) {
        return ElementKt.element(modifier, this.layoutImpl, this.content, elementKey);
    }

    @Override // com.android.compose.animation.scene.ContentScope
    public final Modifier horizontalNestedScrollToScene(Modifier modifier, NestedScrollBehavior nestedScrollBehavior, NestedScrollBehavior nestedScrollBehavior2, Function0 function0) {
        return NestedScrollToSceneKt.nestedScrollToScene(modifier, this.layoutImpl, Orientation.Horizontal, nestedScrollBehavior, nestedScrollBehavior2, function0);
    }
}
