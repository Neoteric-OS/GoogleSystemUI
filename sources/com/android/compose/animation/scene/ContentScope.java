package com.android.compose.animation.scene;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.android.compose.animation.scene.NestedScrollBehavior;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ContentScope {
    static Modifier horizontalNestedScrollToScene$default(ContentScope contentScope, Modifier modifier) {
        NestedScrollBehavior.Companion companion = NestedScrollBehavior.Companion;
        companion.getClass();
        NestedScrollBehavior nestedScrollBehavior = NestedScrollBehavior.Default;
        companion.getClass();
        return contentScope.horizontalNestedScrollToScene(modifier, nestedScrollBehavior, nestedScrollBehavior, new Function0() { // from class: com.android.compose.animation.scene.BaseContentScope$horizontalNestedScrollToScene$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Boolean.FALSE;
            }
        });
    }

    void Element(ElementKey elementKey, Modifier modifier, Function3 function3, Composer composer, int i);

    Modifier element(Modifier modifier, ElementKey elementKey);

    Modifier horizontalNestedScrollToScene(Modifier modifier, NestedScrollBehavior nestedScrollBehavior, NestedScrollBehavior nestedScrollBehavior2, Function0 function0);
}
