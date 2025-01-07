package com.android.systemui.touchpad.tutorial.ui.composable;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import com.airbnb.lottie.compose.LottieDynamicProperties;
import com.airbnb.lottie.compose.LottieDynamicPropertiesKt;
import com.airbnb.lottie.compose.LottieDynamicProperty;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt;
import com.android.systemui.inputdevice.tutorial.ui.composable.TutorialScreenConfig;
import com.android.systemui.touchpad.tutorial.ui.gesture.BackGestureMonitor;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BackGestureTutorialScreenKt {
    public static final void BackGestureTutorialScreen(final Function0 function0, final Function0 function02, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1191661884);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changedInstance(function02) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            composerImpl.startReplaceGroup(-1978158073);
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = AndroidColorSchemeKt.LocalAndroidColorScheme;
            long j = ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).onTertiary;
            long j2 = ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).onTertiaryFixed;
            long j3 = ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).onTertiaryFixedVariant;
            long j4 = ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).tertiaryFixedDim;
            LottieDynamicProperties rememberLottieDynamicProperties = LottieDynamicPropertiesKt.rememberLottieDynamicProperties(new LottieDynamicProperty[]{ActionTutorialContentKt.m809rememberColorFilterPropertyRPmYEkk(".tertiaryFixedDim", j4, composerImpl), ActionTutorialContentKt.m809rememberColorFilterPropertyRPmYEkk(".onTertiaryFixed", j2, composerImpl), ActionTutorialContentKt.m809rememberColorFilterPropertyRPmYEkk(".onTertiary", j, composerImpl), ActionTutorialContentKt.m809rememberColorFilterPropertyRPmYEkk(".onTertiaryFixedVariant", j3, composerImpl)}, composerImpl);
            composerImpl.startReplaceGroup(1007023425);
            boolean changed = composerImpl.changed(rememberLottieDynamicProperties);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new TutorialScreenConfig.Colors(j2, j4, rememberLottieDynamicProperties);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            composerImpl.end(false);
            int i3 = i2 << 6;
            GestureTutorialScreenKt.GestureTutorialScreen(new TutorialScreenConfig((TutorialScreenConfig.Colors) rememberedValue, new TutorialScreenConfig.Strings(R.string.touchpad_back_gesture_action_title, R.string.touchpad_back_gesture_guidance, R.string.touchpad_back_gesture_success_title, R.string.touchpad_back_gesture_success_body), new TutorialScreenConfig.Animations(R.raw.trackpad_back_edu, R.raw.trackpad_back_success)), new DistanceBasedGestureMonitorProvider(new Function2() { // from class: com.android.systemui.touchpad.tutorial.ui.composable.BackGestureTutorialScreenKt$BackGestureTutorialScreen$gestureMonitorProvider$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return new BackGestureMonitor(((Number) obj).intValue(), (Function1) obj2);
                }
            }), function0, function02, composerImpl, (i3 & 896) | 8 | (i3 & 7168));
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.touchpad.tutorial.ui.composable.BackGestureTutorialScreenKt$BackGestureTutorialScreen$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    BackGestureTutorialScreenKt.BackGestureTutorialScreen(Function0.this, function02, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
