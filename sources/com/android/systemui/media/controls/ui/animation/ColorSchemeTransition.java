package com.android.systemui.media.controls.ui.animation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.ImageButton;
import com.android.settingslib.Utils;
import com.android.systemui.media.controls.ui.view.GutsViewHolder;
import com.android.systemui.media.controls.ui.view.MediaViewHolder;
import com.android.systemui.monet.ColorScheme;
import com.android.systemui.monet.TonalPalette;
import com.android.systemui.surfaceeffects.loadingeffect.LoadingEffect;
import com.android.systemui.surfaceeffects.ripple.MultiRippleController;
import com.android.systemui.surfaceeffects.ripple.RippleAnimation;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseController;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseController$Companion$AnimationState;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseShader;
import com.android.wm.shell.R;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ColorSchemeTransition {
    public final AnimatingColorTransition accentPrimary;
    public final int bgColor;
    public final AnimatingColorTransition colorSeamless;
    public final AnimatingColorTransition[] colorTransitions;
    public final Context context;
    public LoadingEffect loadingEffect;
    public final MediaViewHolder mediaViewHolder;
    public final MultiRippleController multiRippleController;
    public final TurbulenceNoiseController turbulenceNoiseController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$1, reason: invalid class name */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3, AnimatingColorTransition.class, "<init>", "<init>(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return new AnimatingColorTransition(((Number) obj).intValue(), (Function1) obj2, (Function1) obj3);
        }
    }

    public ColorSchemeTransition(Context context, MediaViewHolder mediaViewHolder, MultiRippleController multiRippleController, TurbulenceNoiseController turbulenceNoiseController) {
        AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
        this.context = context;
        this.mediaViewHolder = mediaViewHolder;
        this.multiRippleController = multiRippleController;
        this.turbulenceNoiseController = turbulenceNoiseController;
        int color = context.getColor(R.color.material_dynamic_neutral20);
        this.bgColor = color;
        AnimatingColorTransition animatingColorTransition = (AnimatingColorTransition) anonymousClass1.invoke(Integer.valueOf(color), ColorSchemeTransition$surfaceColor$1.INSTANCE, new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$surfaceColor$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int intValue = ((Number) obj).intValue();
                ColorStateList valueOf = ColorStateList.valueOf(intValue);
                ColorSchemeTransition.this.mediaViewHolder.seamlessIcon.setImageTintList(valueOf);
                ColorSchemeTransition.this.mediaViewHolder.seamlessText.setTextColor(intValue);
                ColorSchemeTransition.this.mediaViewHolder.albumView.setBackgroundTintList(valueOf);
                GutsViewHolder gutsViewHolder = ColorSchemeTransition.this.mediaViewHolder.gutsViewHolder;
                gutsViewHolder.dismissText.setTextColor(intValue);
                if (!gutsViewHolder.isDismissible) {
                    gutsViewHolder.cancelText.setTextColor(intValue);
                }
                return Unit.INSTANCE;
            }
        });
        AnimatingColorTransition animatingColorTransition2 = (AnimatingColorTransition) anonymousClass1.invoke(Integer.valueOf(loadDefaultColor(android.R.attr.textColorPrimary)), ColorSchemeTransition$accentPrimary$1.INSTANCE, new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$accentPrimary$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TurbulenceNoiseShader turbulenceNoiseShader;
                int intValue = ((Number) obj).intValue();
                ColorSchemeTransition.this.mediaViewHolder.actionPlayPause.setBackgroundTintList(ColorStateList.valueOf(intValue));
                GutsViewHolder gutsViewHolder = ColorSchemeTransition.this.mediaViewHolder.gutsViewHolder;
                gutsViewHolder.getClass();
                ColorStateList valueOf = ColorStateList.valueOf(intValue);
                gutsViewHolder.settings.setImageTintList(valueOf);
                gutsViewHolder.cancelText.setBackgroundTintList(valueOf);
                gutsViewHolder.dismissText.setBackgroundTintList(valueOf);
                for (RippleAnimation rippleAnimation : ColorSchemeTransition.this.multiRippleController.multipleRippleView.ripples) {
                    rippleAnimation.config.color = intValue;
                    rippleAnimation.applyConfigToShader();
                }
                TurbulenceNoiseController turbulenceNoiseController2 = ColorSchemeTransition.this.turbulenceNoiseController;
                if (turbulenceNoiseController2.state != TurbulenceNoiseController$Companion$AnimationState.NOT_PLAYING && (turbulenceNoiseShader = turbulenceNoiseController2.turbulenceNoiseView.turbulenceNoiseShader) != null) {
                    turbulenceNoiseShader.setColorUniform("in_color", intValue);
                }
                LoadingEffect loadingEffect = ColorSchemeTransition.this.loadingEffect;
                if (loadingEffect != null) {
                    loadingEffect.turbulenceNoiseShader.setColorUniform("in_color", intValue);
                }
                return Unit.INSTANCE;
            }
        });
        this.accentPrimary = animatingColorTransition2;
        AnimatingColorTransition animatingColorTransition3 = (AnimatingColorTransition) anonymousClass1.invoke(Integer.valueOf(loadDefaultColor(android.R.attr.textColorPrimary)), ColorSchemeTransition$accentSecondary$1.INSTANCE, new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$accentSecondary$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ColorStateList valueOf = ColorStateList.valueOf(((Number) obj).intValue());
                Drawable background = ColorSchemeTransition.this.mediaViewHolder.seamlessButton.getBackground();
                RippleDrawable rippleDrawable = background instanceof RippleDrawable ? (RippleDrawable) background : null;
                if (rippleDrawable != null) {
                    rippleDrawable.setColor(valueOf);
                    rippleDrawable.setEffectColor(valueOf);
                }
                return Unit.INSTANCE;
            }
        });
        AnimatingColorTransition animatingColorTransition4 = (AnimatingColorTransition) anonymousClass1.invoke(Integer.valueOf(loadDefaultColor(android.R.attr.textColorPrimary)), new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$colorSeamless$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int i = ColorSchemeTransition.this.context.getResources().getConfiguration().uiMode & 48;
                TonalPalette tonalPalette = ((ColorScheme) obj).mAccent1;
                return Integer.valueOf(i == 32 ? tonalPalette.getS100() : tonalPalette.getS200());
            }
        }, new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$colorSeamless$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ColorSchemeTransition.this.mediaViewHolder.seamlessButton.setBackgroundTintList(ColorStateList.valueOf(((Number) obj).intValue()));
                return Unit.INSTANCE;
            }
        });
        this.colorSeamless = animatingColorTransition4;
        this.colorTransitions = new AnimatingColorTransition[]{animatingColorTransition, animatingColorTransition4, animatingColorTransition2, animatingColorTransition3, (AnimatingColorTransition) anonymousClass1.invoke(Integer.valueOf(loadDefaultColor(android.R.attr.textColorPrimary)), ColorSchemeTransition$textPrimary$1.INSTANCE, new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$textPrimary$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int intValue = ((Number) obj).intValue();
                ColorSchemeTransition.this.mediaViewHolder.titleText.setTextColor(intValue);
                ColorStateList valueOf = ColorStateList.valueOf(intValue);
                ColorSchemeTransition.this.mediaViewHolder.seekBar.getThumb().setTintList(valueOf);
                ColorSchemeTransition.this.mediaViewHolder.seekBar.setProgressTintList(valueOf);
                ColorSchemeTransition.this.mediaViewHolder.scrubbingElapsedTimeView.setTextColor(valueOf);
                ColorSchemeTransition.this.mediaViewHolder.scrubbingTotalTimeView.setTextColor(valueOf);
                MediaViewHolder mediaViewHolder2 = ColorSchemeTransition.this.mediaViewHolder;
                mediaViewHolder2.getClass();
                Iterator it = CollectionsKt__CollectionsKt.listOf(mediaViewHolder2.actionNext, mediaViewHolder2.actionPrev, mediaViewHolder2.action0, mediaViewHolder2.action1, mediaViewHolder2.action2, mediaViewHolder2.action3, mediaViewHolder2.action4).iterator();
                while (it.hasNext()) {
                    ((ImageButton) it.next()).setImageTintList(valueOf);
                }
                GutsViewHolder gutsViewHolder = ColorSchemeTransition.this.mediaViewHolder.gutsViewHolder;
                gutsViewHolder.getClass();
                ColorStateList valueOf2 = ColorStateList.valueOf(intValue);
                gutsViewHolder.gutsText.setTextColor(valueOf2);
                if (gutsViewHolder.isDismissible) {
                    gutsViewHolder.cancelText.setTextColor(valueOf2);
                }
                return Unit.INSTANCE;
            }
        }), (AnimatingColorTransition) anonymousClass1.invoke(Integer.valueOf(loadDefaultColor(android.R.attr.textColorPrimaryInverse)), ColorSchemeTransition$textPrimaryInverse$1.INSTANCE, new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$textPrimaryInverse$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ColorSchemeTransition.this.mediaViewHolder.actionPlayPause.setImageTintList(ColorStateList.valueOf(((Number) obj).intValue()));
                return Unit.INSTANCE;
            }
        }), (AnimatingColorTransition) anonymousClass1.invoke(Integer.valueOf(loadDefaultColor(android.R.attr.textColorSecondary)), ColorSchemeTransition$textSecondary$1.INSTANCE, new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$textSecondary$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ColorSchemeTransition.this.mediaViewHolder.artistText.setTextColor(((Number) obj).intValue());
                return Unit.INSTANCE;
            }
        }), (AnimatingColorTransition) anonymousClass1.invoke(Integer.valueOf(loadDefaultColor(android.R.attr.textColorTertiary)), ColorSchemeTransition$textTertiary$1.INSTANCE, new Function1() { // from class: com.android.systemui.media.controls.ui.animation.ColorSchemeTransition$textTertiary$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ColorSchemeTransition.this.mediaViewHolder.seekBar.setProgressBackgroundTintList(ColorStateList.valueOf(((Number) obj).intValue()));
                return Unit.INSTANCE;
            }
        })};
    }

    public final int loadDefaultColor(int i) {
        return Utils.getColorAttr(i, this.context).getDefaultColor();
    }
}
