package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SpringSpec;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionSchemeKt$standardMotionScheme$1 implements MotionScheme {
    public final SpringSpec fastEffectsSpec() {
        return AnimationSpecKt.spring$default(1.0f, 3800.0f, null, 4);
    }
}
