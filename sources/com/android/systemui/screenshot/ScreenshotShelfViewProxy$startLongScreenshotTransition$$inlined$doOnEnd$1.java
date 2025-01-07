package com.android.systemui.screenshot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.RenderEffect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.screenshot.LegacyScreenshotController;
import com.android.systemui.screenshot.ui.viewmodel.AnimationState;
import com.android.systemui.screenshot.ui.viewmodel.ScreenshotViewModel;
import com.google.android.systemui.screenshot.ThumbnailObserverGoogle;
import com.google.android.systemui.screenshot.ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1;
import com.google.android.systemui.screenshot.ThumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1;
import com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect;
import com.google.android.systemui.screenshot.surfaceeffects.revealeffect.RippleRevealEffect;
import com.google.android.systemui.screenshot.surfaceeffects.utils.MathUtils;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScreenshotShelfViewProxy this$0;

    public /* synthetic */ ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1(ScreenshotShelfViewProxy screenshotShelfViewProxy, int i) {
        this.$r8$classId = i;
        this.this$0 = screenshotShelfViewProxy;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                LegacyScreenshotController.AnonymousClass2 anonymousClass2 = this.this$0.callbacks;
                if (anonymousClass2 != null) {
                    anonymousClass2.onDismiss();
                    break;
                }
                break;
            case 1:
                LegacyScreenshotController.AnonymousClass2 anonymousClass22 = this.this$0.callbacks;
                if (anonymousClass22 != null) {
                    anonymousClass22.onUserInteraction();
                }
                ThumbnailObserverGoogle thumbnailObserverGoogle = this.this$0.thumbnailObserver;
                thumbnailObserverGoogle.getClass();
                Log.d("ThumbnailObserver", "Entrance complete");
                if (thumbnailObserverGoogle.pearlEnabled) {
                    final RippleRevealEffect rippleRevealEffect = thumbnailObserverGoogle.rippleRevealEffect;
                    if (rippleRevealEffect != null && !rippleRevealEffect.animator.isRunning()) {
                        rippleRevealEffect.animator.setDuration((long) 1000.0f);
                        rippleRevealEffect.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.systemui.screenshot.surfaceeffects.revealeffect.RippleRevealEffect$play$1
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                float currentPlayTime = valueAnimator.getCurrentPlayTime();
                                RippleRevealEffect.this.rippleRevealShader.setFloatUniform("in_time", 0.00175f * currentPlayTime);
                                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                RippleRevealEffect rippleRevealEffect2 = RippleRevealEffect.this;
                                RippleRevealEffectConfig rippleRevealEffectConfig = rippleRevealEffect2.config;
                                float f = rippleRevealEffectConfig.innerRadiusEnd;
                                float f2 = rippleRevealEffectConfig.innerRadiusStart;
                                float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f, f2, floatValue, f2);
                                float f3 = rippleRevealEffectConfig.outerRadiusEnd;
                                float f4 = rippleRevealEffectConfig.outerRadiusStart;
                                float m2 = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f3, f4, floatValue, f4);
                                rippleRevealEffect2.rippleRevealShader.setFloatUniform("in_innerRadius", m);
                                RippleRevealEffect.this.rippleRevealShader.setFloatUniform("in_outerRadius", m2);
                                RippleRevealEffectConfig rippleRevealEffectConfig2 = RippleRevealEffect.this.config;
                                float constrainedMap = MathUtils.constrainedMap(0.0f, currentPlayTime);
                                RippleRevealEffectConfig rippleRevealEffectConfig3 = RippleRevealEffect.this.config;
                                float constrainedMap2 = MathUtils.constrainedMap(130.0f, currentPlayTime);
                                int alphaComponent = ColorUtils.setAlphaComponent(RippleRevealEffect.this.config.innerColor, (int) ((255.0f * constrainedMap) + 0.0f));
                                int alphaComponent2 = ColorUtils.setAlphaComponent(RippleRevealEffect.this.config.outerColor, (int) ((255.0f * constrainedMap2) + 0.0f));
                                RippleRevealEffect.this.rippleRevealShader.setColorUniform("in_innerColor", alphaComponent);
                                RippleRevealEffect.this.rippleRevealShader.setColorUniform("in_outerColor", alphaComponent2);
                                RippleRevealEffect.this.rippleRevealShader.setFloatUniform("in_dstAlpha", Math.max(constrainedMap, constrainedMap2));
                                RippleRevealEffect.this.rippleRevealShader.setFloatUniform("in_sparkleAlpha", Math.min(constrainedMap, constrainedMap2));
                                RippleRevealEffect rippleRevealEffect3 = RippleRevealEffect.this;
                                ThumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1 thumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1 = rippleRevealEffect3.renderEffectCallback;
                                thumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1.$image.setRenderEffect(RenderEffect.createChainEffect(RenderEffect.createRuntimeShaderEffect(rippleRevealEffect3.rippleRevealShader, "in_dst"), thumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1.$blurEffect));
                            }
                        });
                        rippleRevealEffect.animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.systemui.screenshot.surfaceeffects.revealeffect.RippleRevealEffect$play$2
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationEnd(Animator animator2) {
                                ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1 thumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1 = RippleRevealEffect.this.stateChangedCallback;
                                if (thumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1 != null) {
                                    ((ImageView) thumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1.$border).setVisibility(4);
                                }
                            }
                        });
                        rippleRevealEffect.animator.start();
                        ((ImageView) rippleRevealEffect.stateChangedCallback.$border).setVisibility(0);
                    }
                    final GlowPieEffect glowPieEffect = thumbnailObserverGoogle.glowBorderEffect;
                    if (glowPieEffect != null && !glowPieEffect.mainAnimator.isRunning()) {
                        GlowPieEffect.FirstGlowPie firstGlowPie = GlowPieEffect.firstGlowPie;
                        firstGlowPie.setProgress(0.0f);
                        firstGlowPie.setTime(0.0f);
                        GlowPieEffect.SecondGlowPie secondGlowPie = GlowPieEffect.secondGlowPie;
                        secondGlowPie.setProgress(0.0f);
                        secondGlowPie.setTime(0.0f);
                        glowPieEffect.mainAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect$play$1
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                float currentPlayTime = valueAnimator.getCurrentPlayTime();
                                GlowPieEffect.BaseGlow baseGlow = GlowPieEffect.baseGlow;
                                baseGlow.time = currentPlayTime;
                                GlowPieEffect.FirstGlowPie firstGlowPie2 = GlowPieEffect.firstGlowPie;
                                firstGlowPie2.updateProgress(currentPlayTime);
                                GlowPieEffect.SecondGlowPie secondGlowPie2 = GlowPieEffect.secondGlowPie;
                                secondGlowPie2.updateProgress(currentPlayTime);
                                GlowPieShader glowPieShader = GlowPieEffect.this.glowPieShader;
                                float[] fArr = {0.0f, firstGlowPie2.angle(), secondGlowPie2.angle()};
                                glowPieShader.getClass();
                                glowPieShader.setFloatUniform("in_angles", fArr);
                                GlowPieShader glowPieShader2 = GlowPieEffect.this.glowPieShader;
                                float[] fArr2 = {0.0f, (firstGlowPie2.getProgress() * (-1.63f)) + 1.0f, (secondGlowPie2.getProgress() * (-1.63f)) + 1.0f};
                                glowPieShader2.getClass();
                                glowPieShader2.setFloatUniform("in_bottomThresholds", fArr2);
                                GlowPieShader glowPieShader3 = GlowPieEffect.this.glowPieShader;
                                float[] fArr3 = {0.0f, (firstGlowPie2.getProgress() * (-1.63f)) + 1.63f, (secondGlowPie2.getProgress() * (-1.63f)) + 1.63f};
                                glowPieShader3.getClass();
                                glowPieShader3.setFloatUniform("in_topThresholds", fArr3);
                                GlowPieShader glowPieShader4 = GlowPieEffect.this.glowPieShader;
                                float[] fArr4 = {baseGlow.alpha(), firstGlowPie2.alpha(), secondGlowPie2.alpha()};
                                glowPieShader4.getClass();
                                glowPieShader4.setFloatUniform("in_alphas", fArr4);
                                GlowPieEffect glowPieEffect2 = GlowPieEffect.this;
                                ((View) glowPieEffect2.renderEffectDrawCallback.$border).setRenderEffect(RenderEffect.createRuntimeShaderEffect(glowPieEffect2.glowPieShader, "in_dst"));
                            }
                        });
                        glowPieEffect.mainAnimator.start();
                    }
                }
                ScreenshotViewModel screenshotViewModel = this.this$0.viewModel;
                AnimationState animationState = AnimationState.ENTRANCE_COMPLETE;
                StateFlowImpl stateFlowImpl = screenshotViewModel._animationState;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, animationState);
                break;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
            case 1:
                break;
            default:
                this.this$0.thumbnailObserver.getClass();
                Log.d("ThumbnailObserver", "Entrance started");
                ScreenshotViewModel screenshotViewModel = this.this$0.viewModel;
                AnimationState animationState = AnimationState.ENTRANCE_STARTED;
                StateFlowImpl stateFlowImpl = screenshotViewModel._animationState;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, animationState);
                break;
        }
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ScreenshotShelfViewProxy$createScreenshotDropInAnimation$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ScreenshotShelfViewProxy$createScreenshotDropInAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationEnd$com$android$systemui$screenshot$ScreenshotShelfViewProxy$createScreenshotDropInAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ScreenshotShelfViewProxy$createScreenshotDropInAnimation$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ScreenshotShelfViewProxy$createScreenshotDropInAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationStart$com$android$systemui$screenshot$ScreenshotShelfViewProxy$createScreenshotDropInAnimation$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationStart$com$android$systemui$screenshot$ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1(Animator animator) {
    }
}
