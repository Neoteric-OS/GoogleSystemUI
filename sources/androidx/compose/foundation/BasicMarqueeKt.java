package androidx.compose.foundation;

import androidx.compose.ui.Modifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BasicMarqueeKt {
    /* renamed from: basicMarquee-1Mj1MLw$default, reason: not valid java name */
    public static Modifier m26basicMarquee1Mj1MLw$default(Modifier modifier, int i) {
        int i2 = 1;
        if ((i & 1) != 0) {
            MarqueeSpacing$Companion$$ExternalSyntheticLambda0 marqueeSpacing$Companion$$ExternalSyntheticLambda0 = MarqueeDefaults.Spacing;
            i2 = 3;
        }
        MarqueeSpacing$Companion$$ExternalSyntheticLambda0 marqueeSpacing$Companion$$ExternalSyntheticLambda02 = MarqueeDefaults.Spacing;
        return modifier.then(new MarqueeModifierElement(i2, (i & 8) != 0 ? 1200 : 200, MarqueeDefaults.Spacing, MarqueeDefaults.Velocity));
    }
}
