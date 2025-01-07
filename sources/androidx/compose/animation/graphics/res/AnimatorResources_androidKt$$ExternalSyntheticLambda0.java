package androidx.compose.animation.graphics.res;

import android.animation.TimeInterpolator;
import androidx.compose.animation.core.Easing;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AnimatorResources_androidKt$$ExternalSyntheticLambda0 implements Easing {
    public final /* synthetic */ TimeInterpolator f$0;

    public /* synthetic */ AnimatorResources_androidKt$$ExternalSyntheticLambda0(TimeInterpolator timeInterpolator) {
        this.f$0 = timeInterpolator;
    }

    @Override // androidx.compose.animation.core.Easing
    public final float transform(float f) {
        TimeInterpolator timeInterpolator = this.f$0;
        AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda3 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
        return timeInterpolator.getInterpolation(f);
    }
}
