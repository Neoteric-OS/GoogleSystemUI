package androidx.compose.material3;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.material.ripple.AndroidRippleNode;
import androidx.compose.material.ripple.CommonRippleNode;
import androidx.compose.material.ripple.RippleNode;
import androidx.compose.material.ripple.Ripple_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DelegatingThemeAwareRippleNode$updateConfiguration$1 extends Lambda implements Function0 {
    final /* synthetic */ DelegatingThemeAwareRippleNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelegatingThemeAwareRippleNode$updateConfiguration$1(DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode) {
        super(0);
        this.this$0 = delegatingThemeAwareRippleNode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        RippleNode rippleNode;
        if (((RippleConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, RippleKt.LocalRippleConfiguration)) == null) {
            DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode = this.this$0;
            RippleNode rippleNode2 = delegatingThemeAwareRippleNode.rippleNode;
            if (rippleNode2 != null) {
                delegatingThemeAwareRippleNode.undelegate(rippleNode2);
            }
            delegatingThemeAwareRippleNode.rippleNode = null;
        } else {
            final DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode2 = this.this$0;
            if (delegatingThemeAwareRippleNode2.rippleNode == null) {
                ColorProducer colorProducer = new ColorProducer() { // from class: androidx.compose.material3.DelegatingThemeAwareRippleNode$attachNewRipple$calculateColor$1
                    @Override // androidx.compose.ui.graphics.ColorProducer
                    /* renamed from: invoke-0d7_KjU, reason: not valid java name */
                    public final long mo206invoke0d7_KjU() {
                        DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode3 = DelegatingThemeAwareRippleNode.this;
                        long mo206invoke0d7_KjU = delegatingThemeAwareRippleNode3.color.mo206invoke0d7_KjU();
                        if (mo206invoke0d7_KjU != 16) {
                            return mo206invoke0d7_KjU;
                        }
                        RippleConfiguration rippleConfiguration = (RippleConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(delegatingThemeAwareRippleNode3, RippleKt.LocalRippleConfiguration);
                        if (rippleConfiguration != null) {
                            long j = rippleConfiguration.color;
                            if (j != 16) {
                                return j;
                            }
                        }
                        return ((Color) CompositionLocalConsumerModifierNodeKt.currentValueOf(delegatingThemeAwareRippleNode3, ContentColorKt.LocalContentColor)).value;
                    }
                };
                Function0 function0 = new Function0() { // from class: androidx.compose.material3.DelegatingThemeAwareRippleNode$attachNewRipple$calculateRippleAlpha$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RippleDefaults.RippleAlpha;
                    }
                };
                TweenSpec tweenSpec = androidx.compose.material.ripple.RippleKt.DefaultTweenSpec;
                boolean z = Ripple_androidKt.IsRunningInPreview;
                InteractionSource interactionSource = delegatingThemeAwareRippleNode2.interactionSource;
                boolean z2 = delegatingThemeAwareRippleNode2.bounded;
                float f = delegatingThemeAwareRippleNode2.radius;
                if (z) {
                    rippleNode = new CommonRippleNode(interactionSource, z2, f, colorProducer, function0);
                } else {
                    AndroidRippleNode androidRippleNode = new AndroidRippleNode(interactionSource, z2, f, colorProducer, function0);
                    androidRippleNode.rippleSize = 0L;
                    rippleNode = androidRippleNode;
                }
                delegatingThemeAwareRippleNode2.delegate(rippleNode);
                delegatingThemeAwareRippleNode2.rippleNode = rippleNode;
            }
        }
        return Unit.INSTANCE;
    }
}
