package com.android.compose.animation.scene;

import androidx.compose.animation.core.SpringSpec;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SceneTransitionsBuilderImpl {
    public final SpringSpec defaultSwipeSpec = SceneTransitions.DefaultSwipeSpec;
    public final DefaultInterruptionHandler interruptionHandler = DefaultInterruptionHandler.INSTANCE;
    public final ProgressConverter$Companion$tanh$1 defaultOverscrollProgressConverter = ProgressConverter$Companion.Default;
    public final List transitionSpecs = new ArrayList();
    public final List transitionOverscrollSpecs = new ArrayList();

    public static final TransformationSpecImpl access$transition$transformationSpec(Function1 function1) {
        TransitionBuilderImpl transitionBuilderImpl = new TransitionBuilderImpl();
        function1.invoke(transitionBuilderImpl);
        return new TransformationSpecImpl(transitionBuilderImpl.spec, transitionBuilderImpl.swipeSpec, transitionBuilderImpl.distance, transitionBuilderImpl.transformations);
    }

    public static void to$default(SceneTransitionsBuilderImpl sceneTransitionsBuilderImpl, ContentKey contentKey, TransitionKey transitionKey, Function1 function1, int i) {
        if ((i & 2) != 0) {
            transitionKey = null;
        }
        sceneTransitionsBuilderImpl.transition(null, contentKey, transitionKey, null, function1);
    }

    public final TransitionSpecImpl transition(ContentKey contentKey, ContentKey contentKey2, TransitionKey transitionKey, final Function1 function1, final Function1 function12) {
        TransitionSpecImpl transitionSpecImpl = new TransitionSpecImpl(transitionKey, contentKey, contentKey2, null, function1 != null ? new Function0() { // from class: com.android.compose.animation.scene.SceneTransitionsBuilderImpl$transition$reversePreviewTransformationSpec$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SceneTransitionsBuilderImpl.access$transition$transformationSpec(Function1.this);
            }
        } : null, new Function0() { // from class: com.android.compose.animation.scene.SceneTransitionsBuilderImpl$transition$transformationSpec$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SceneTransitionsBuilderImpl.access$transition$transformationSpec(Function1.this);
            }
        });
        this.transitionSpecs.add(transitionSpecImpl);
        return transitionSpecImpl;
    }
}
