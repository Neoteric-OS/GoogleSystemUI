package com.android.compose.animation.scene;

import androidx.compose.animation.core.AnimationSpecKt;
import com.android.compose.animation.scene.TransformationSpec;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface TransformationSpec {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final TransformationSpecImpl Empty = new TransformationSpecImpl(AnimationSpecKt.snap$default(), null, null, EmptyList.INSTANCE);
        public static final Function0 EmptyProvider = new Function0() { // from class: com.android.compose.animation.scene.TransformationSpec$Companion$EmptyProvider$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TransformationSpec.Companion.Empty;
            }
        };
    }
}
