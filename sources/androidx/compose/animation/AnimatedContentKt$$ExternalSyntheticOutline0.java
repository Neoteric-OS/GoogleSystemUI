package androidx.compose.animation;

import androidx.compose.runtime.ComposerImpl;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract /* synthetic */ class AnimatedContentKt$$ExternalSyntheticOutline0 {
    public static void m(int i, ComposerImpl composerImpl, int i2, Function2 function2) {
        composerImpl.updateRememberedValue(Integer.valueOf(i));
        composerImpl.apply(Integer.valueOf(i2), function2);
    }
}
