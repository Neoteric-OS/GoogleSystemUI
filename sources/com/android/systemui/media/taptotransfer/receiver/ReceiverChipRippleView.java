package com.android.systemui.media.taptotransfer.receiver;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import com.android.systemui.surfaceeffects.ripple.RippleView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ReceiverChipRippleView extends RippleView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean isStarted;

    public ReceiverChipRippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setupShader(RippleShader.RippleShape.CIRCLE);
        RippleShader rippleShader = this.rippleShader;
        rippleShader = rippleShader == null ? null : rippleShader;
        RippleShader.FadeParams fadeParams = rippleShader.baseRingFadeParams;
        fadeParams.fadeOutStart = 1.0f;
        fadeParams.fadeOutEnd = 1.0f;
        RippleShader.FadeParams fadeParams2 = rippleShader.centerFillFadeParams;
        fadeParams2.getClass();
        fadeParams2.fadeInEnd = 0.0f;
        fadeParams2.fadeOutStart = 1.0f;
        fadeParams2.fadeOutEnd = 1.0f;
        RippleShader rippleShader2 = this.rippleShader;
        (rippleShader2 != null ? rippleShader2 : null).setFloatUniform("in_sparkle_strength", 0.0f);
        this.isStarted = false;
    }
}
