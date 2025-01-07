package com.android.systemui.inputdevice.tutorial.ui.composable;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.graphics.ColorKt;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.compose.AnimateLottieCompositionAsStateKt;
import com.airbnb.lottie.compose.LottieAnimatable;
import com.airbnb.lottie.compose.LottieAnimatableImpl;
import com.airbnb.lottie.compose.LottieAnimationKt;
import com.airbnb.lottie.compose.LottieCompositionResultImpl;
import com.airbnb.lottie.compose.LottieCompositionSpec;
import com.airbnb.lottie.compose.LottieDynamicProperties;
import com.airbnb.lottie.compose.LottieDynamicProperty;
import com.airbnb.lottie.compose.RememberLottieCompositionKt;
import com.airbnb.lottie.model.KeyPath;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ActionTutorialContentKt {
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c4, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r5.rememberedValue(), java.lang.Integer.valueOf(r10)) == false) goto L40;
     */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt$ActionTutorialContent$1$2, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ActionTutorialContent(final com.android.systemui.inputdevice.tutorial.ui.composable.TutorialActionState r20, kotlin.jvm.functions.Function0 r21, final com.android.systemui.inputdevice.tutorial.ui.composable.TutorialScreenConfig r22, androidx.compose.runtime.Composer r23, final int r24) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt.ActionTutorialContent(com.android.systemui.inputdevice.tutorial.ui.composable.TutorialActionState, kotlin.jvm.functions.Function0, com.android.systemui.inputdevice.tutorial.ui.composable.TutorialScreenConfig, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0076  */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt$TutorialAnimation$1$2, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TutorialAnimation(final com.android.systemui.inputdevice.tutorial.ui.composable.TutorialActionState r16, final com.android.systemui.inputdevice.tutorial.ui.composable.TutorialScreenConfig r17, androidx.compose.ui.Modifier r18, androidx.compose.runtime.Composer r19, final int r20, final int r21) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt.TutorialAnimation(com.android.systemui.inputdevice.tutorial.ui.composable.TutorialActionState, com.android.systemui.inputdevice.tutorial.ui.composable.TutorialScreenConfig, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b7  */
    /* renamed from: TutorialDescription-sW7UJKQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m808TutorialDescriptionsW7UJKQ(final int r33, final long r34, final int r36, androidx.compose.ui.Modifier r37, androidx.compose.runtime.Composer r38, final int r39, final int r40) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt.m808TutorialDescriptionsW7UJKQ(int, long, int, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void access$EducationAnimation(final int i, final LottieDynamicProperties lottieDynamicProperties, Composer composer, final int i2) {
        int i3;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-224727779);
        if ((i2 & 14) == 0) {
            i3 = (composerImpl2.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 112) == 0) {
            i3 |= composerImpl2.changed(lottieDynamicProperties) ? 32 : 16;
        }
        if ((i3 & 91) == 18 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            LottieCompositionResultImpl rememberLottieComposition = RememberLottieCompositionKt.rememberLottieComposition(new LottieCompositionSpec.RawRes(i), composerImpl2);
            final LottieAnimatable animateLottieCompositionAsState = AnimateLottieCompositionAsStateKt.animateLottieCompositionAsState((LottieComposition) rememberLottieComposition.getValue(), Integer.MAX_VALUE, composerImpl2);
            LottieComposition lottieComposition = (LottieComposition) rememberLottieComposition.getValue();
            composerImpl2.startReplaceGroup(-102527462);
            boolean changed = composerImpl2.changed(animateLottieCompositionAsState);
            Object rememberedValue = composerImpl2.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function0() { // from class: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt$EducationAnimation$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((Number) ((LottieAnimatableImpl) animateLottieCompositionAsState).getValue()).floatValue());
                    }
                };
                composerImpl2.updateRememberedValue(rememberedValue);
            }
            composerImpl2.end(false);
            composerImpl = composerImpl2;
            LottieAnimationKt.LottieAnimation(lottieComposition, (Function0) rememberedValue, null, false, false, false, null, false, lottieDynamicProperties, null, null, false, false, null, null, false, composerImpl, 134217736 | ((i3 << 21) & 234881024), 0, 65276);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt$EducationAnimation$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ActionTutorialContentKt.access$EducationAnimation(i, lottieDynamicProperties, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$FrozenSuccessAnimation(final int i, final LottieDynamicProperties lottieDynamicProperties, Composer composer, final int i2) {
        int i3;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-1130182600);
        if ((i2 & 14) == 0) {
            i3 = (composerImpl2.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 112) == 0) {
            i3 |= composerImpl2.changed(lottieDynamicProperties) ? 32 : 16;
        }
        int i4 = i3;
        if ((i4 & 91) == 18 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            composerImpl = composerImpl2;
            LottieAnimationKt.LottieAnimation((LottieComposition) RememberLottieCompositionKt.rememberLottieComposition(new LottieCompositionSpec.RawRes(i), composerImpl2).getValue(), new Function0() { // from class: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt$FrozenSuccessAnimation$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Float.valueOf(0.0f);
                }
            }, null, false, false, false, null, false, lottieDynamicProperties, null, null, false, false, null, null, false, composerImpl, 134217784 | ((i4 << 21) & 234881024), 0, 65276);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt$FrozenSuccessAnimation$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ActionTutorialContentKt.access$FrozenSuccessAnimation(i, lottieDynamicProperties, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$SuccessAnimation(final int i, final LottieDynamicProperties lottieDynamicProperties, Composer composer, final int i2) {
        int i3;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(476597368);
        if ((i2 & 14) == 0) {
            i3 = (composerImpl2.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 112) == 0) {
            i3 |= composerImpl2.changed(lottieDynamicProperties) ? 32 : 16;
        }
        if ((i3 & 91) == 18 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            LottieCompositionResultImpl rememberLottieComposition = RememberLottieCompositionKt.rememberLottieComposition(new LottieCompositionSpec.RawRes(i), composerImpl2);
            final LottieAnimatable animateLottieCompositionAsState = AnimateLottieCompositionAsStateKt.animateLottieCompositionAsState((LottieComposition) rememberLottieComposition.getValue(), 1, composerImpl2);
            LottieComposition lottieComposition = (LottieComposition) rememberLottieComposition.getValue();
            composerImpl2.startReplaceGroup(-666339327);
            boolean changed = composerImpl2.changed(animateLottieCompositionAsState);
            Object rememberedValue = composerImpl2.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function0() { // from class: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt$SuccessAnimation$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((Number) ((LottieAnimatableImpl) animateLottieCompositionAsState).getValue()).floatValue());
                    }
                };
                composerImpl2.updateRememberedValue(rememberedValue);
            }
            composerImpl2.end(false);
            composerImpl = composerImpl2;
            LottieAnimationKt.LottieAnimation(lottieComposition, (Function0) rememberedValue, null, false, false, false, null, false, lottieDynamicProperties, null, null, false, false, null, null, false, composerImpl, 134217736 | ((i3 << 21) & 234881024), 0, 65276);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt$SuccessAnimation$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ActionTutorialContentKt.access$SuccessAnimation(i, lottieDynamicProperties, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* renamed from: rememberColorFilterProperty-RPmYEkk, reason: not valid java name */
    public static final LottieDynamicProperty m809rememberColorFilterPropertyRPmYEkk(String str, long j, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(411676271);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ColorFilter colorFilter = LottieProperty.COLOR_FILTER;
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(ColorKt.m373toArgb8_81llA(j), PorterDuff.Mode.SRC_ATOP);
        String[] strArr = {"**", str, "**"};
        composerImpl.startReplaceGroup(-1788530187);
        composerImpl.startReplaceGroup(1613443961);
        boolean changed = composerImpl.changed(strArr);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (changed || rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new KeyPath((String[]) Arrays.copyOf(strArr, 3));
            composerImpl.updateRememberedValue(rememberedValue);
        }
        KeyPath keyPath = (KeyPath) rememberedValue;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(1613444012);
        boolean changed2 = composerImpl.changed(keyPath) | composerImpl.changed(colorFilter) | composerImpl.changed(porterDuffColorFilter);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new LottieDynamicProperty(colorFilter, keyPath, porterDuffColorFilter);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        LottieDynamicProperty lottieDynamicProperty = (LottieDynamicProperty) rememberedValue2;
        composerImpl.end(false);
        composerImpl.end(false);
        composerImpl.end(false);
        return lottieDynamicProperty;
    }
}
