package com.google.android.systemui.screenshot;

import android.R;
import android.util.TypedValue;
import android.view.View;
import com.android.settingslib.Utils;
import com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect;
import com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffectConfig;
import com.google.android.systemui.screenshot.surfaceeffects.revealeffect.RippleRevealEffect;
import com.google.android.systemui.screenshot.surfaceeffects.revealeffect.RippleRevealEffectConfig;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ThumbnailObserverGoogle {
    public final ThumbnailObserverGoogle$borderLayoutChangeListener$1 borderLayoutChangeListener;
    public GlowPieEffect glowBorderEffect;
    public boolean pearlEnabled;
    public RippleRevealEffect rippleRevealEffect;
    public final ThumbnailObserverGoogle$borderLayoutChangeListener$1 thumbnailLayoutChangeListener;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.systemui.screenshot.ThumbnailObserverGoogle$borderLayoutChangeListener$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.systemui.screenshot.ThumbnailObserverGoogle$borderLayoutChangeListener$1] */
    public ThumbnailObserverGoogle() {
        final int i = 1;
        this.thumbnailLayoutChangeListener = new View.OnLayoutChangeListener(this) { // from class: com.google.android.systemui.screenshot.ThumbnailObserverGoogle$borderLayoutChangeListener$1
            public final /* synthetic */ ThumbnailObserverGoogle this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                RippleRevealEffect rippleRevealEffect;
                switch (i) {
                    case 0:
                        ThumbnailObserverGoogle thumbnailObserverGoogle = this.this$0;
                        if (thumbnailObserverGoogle.pearlEnabled) {
                            GlowPieEffect glowPieEffect = thumbnailObserverGoogle.glowBorderEffect;
                            if (glowPieEffect != null) {
                                Intrinsics.checkNotNull(view);
                                glowPieEffect.glowPieShader.applyConfig(ThumbnailObserverGoogle.createGlowBorderConfig(view));
                            }
                            view.getBackground().setTint(Utils.getColorAttr(R.^attr-private.materialColorPrimaryContainer, view.getContext()).getDefaultColor());
                            break;
                        }
                        break;
                    default:
                        ThumbnailObserverGoogle thumbnailObserverGoogle2 = this.this$0;
                        if (thumbnailObserverGoogle2.pearlEnabled && (rippleRevealEffect = thumbnailObserverGoogle2.rippleRevealEffect) != null) {
                            Intrinsics.checkNotNull(view);
                            RippleRevealEffectConfig createRippleRevealConfig = ThumbnailObserverGoogle.createRippleRevealConfig(view);
                            rippleRevealEffect.config = createRippleRevealConfig;
                            rippleRevealEffect.rippleRevealShader.applyConfig(createRippleRevealConfig);
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 0;
        this.borderLayoutChangeListener = new View.OnLayoutChangeListener(this) { // from class: com.google.android.systemui.screenshot.ThumbnailObserverGoogle$borderLayoutChangeListener$1
            public final /* synthetic */ ThumbnailObserverGoogle this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i22, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                RippleRevealEffect rippleRevealEffect;
                switch (i2) {
                    case 0:
                        ThumbnailObserverGoogle thumbnailObserverGoogle = this.this$0;
                        if (thumbnailObserverGoogle.pearlEnabled) {
                            GlowPieEffect glowPieEffect = thumbnailObserverGoogle.glowBorderEffect;
                            if (glowPieEffect != null) {
                                Intrinsics.checkNotNull(view);
                                glowPieEffect.glowPieShader.applyConfig(ThumbnailObserverGoogle.createGlowBorderConfig(view));
                            }
                            view.getBackground().setTint(Utils.getColorAttr(R.^attr-private.materialColorPrimaryContainer, view.getContext()).getDefaultColor());
                            break;
                        }
                        break;
                    default:
                        ThumbnailObserverGoogle thumbnailObserverGoogle2 = this.this$0;
                        if (thumbnailObserverGoogle2.pearlEnabled && (rippleRevealEffect = thumbnailObserverGoogle2.rippleRevealEffect) != null) {
                            Intrinsics.checkNotNull(view);
                            RippleRevealEffectConfig createRippleRevealConfig = ThumbnailObserverGoogle.createRippleRevealConfig(view);
                            rippleRevealEffect.config = createRippleRevealConfig;
                            rippleRevealEffect.rippleRevealShader.applyConfig(createRippleRevealConfig);
                            break;
                        }
                        break;
                }
            }
        };
    }

    public static GlowPieEffectConfig createGlowBorderConfig(View view) {
        float width = view.getWidth();
        float height = view.getHeight();
        int defaultColor = Utils.getColorAttr(R.^attr-private.materialColorPrimaryContainer, view.getContext()).getDefaultColor();
        return new GlowPieEffectConfig(width * 0.5f, view.getHeight() * 0.5f, width, height, TypedValue.applyDimension(1, 32.0f, view.getContext().getResources().getDisplayMetrics()), new int[]{defaultColor, Utils.getColorAttr(R.^attr-private.materialColorTertiaryContainer, view.getContext()).getDefaultColor(), defaultColor});
    }

    public static RippleRevealEffectConfig createRippleRevealConfig(View view) {
        float max = Math.max(view.getWidth(), view.getHeight()) * 3.0f;
        float f = max * 0.05f;
        return new RippleRevealEffectConfig(view.getWidth() * 0.5f, view.getHeight(), f, max, f * 1.25f, max * 1.25f, view.getContext().getResources().getDisplayMetrics().density, Utils.getColorAttr(R.^attr-private.materialColorPrimaryContainer, view.getContext()).getDefaultColor(), Utils.getColorAttr(R.^attr-private.materialColorTertiaryContainer, view.getContext()).getDefaultColor());
    }
}
