package com.android.systemui.inputdevice.tutorial.ui.composable;

import android.view.KeyEvent;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import com.airbnb.lottie.compose.LottieAnimationKt$$ExternalSyntheticOutline0;
import com.airbnb.lottie.compose.LottieDynamicProperties;
import com.airbnb.lottie.compose.LottieDynamicPropertiesKt;
import com.airbnb.lottie.compose.LottieDynamicProperty;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.systemui.inputdevice.tutorial.ui.composable.TutorialScreenConfig;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ActionKeyTutorialScreenKt {
    public static final void ActionKeyTutorialScreen(final Function0 function0, final Function0 function02, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1892233530);
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
            BackHandlerKt.BackHandler(false, function02, composerImpl, i2 & 112, 1);
            composerImpl.startReplaceGroup(-133994219);
            composerImpl.startReplaceGroup(550520098);
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = AndroidColorSchemeKt.LocalAndroidColorScheme;
            long j = ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).primaryFixedDim;
            long j2 = ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).secondaryFixedDim;
            long j3 = ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).onSecondaryFixed;
            LottieDynamicProperties rememberLottieDynamicProperties = LottieDynamicPropertiesKt.rememberLottieDynamicProperties(new LottieDynamicProperty[]{ActionTutorialContentKt.m809rememberColorFilterPropertyRPmYEkk(".primaryFixedDim", j, composerImpl), ActionTutorialContentKt.m809rememberColorFilterPropertyRPmYEkk(".secondaryFixedDim", j2, composerImpl), ActionTutorialContentKt.m809rememberColorFilterPropertyRPmYEkk(".onSecondaryFixed", j3, composerImpl), ActionTutorialContentKt.m809rememberColorFilterPropertyRPmYEkk(".onSecondaryFixedVariant", ((AndroidColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).onSecondaryFixedVariant, composerImpl)}, composerImpl);
            composerImpl.startReplaceGroup(1635496225);
            boolean changed = composerImpl.changed(rememberLottieDynamicProperties);
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (changed || rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = new TutorialScreenConfig.Colors(j3, j2, rememberLottieDynamicProperties);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            composerImpl.end(false);
            TutorialScreenConfig tutorialScreenConfig = new TutorialScreenConfig((TutorialScreenConfig.Colors) rememberedValue, new TutorialScreenConfig.Strings(R.string.tutorial_action_key_title, R.string.tutorial_action_key_guidance, R.string.tutorial_action_key_success_title, R.string.tutorial_action_key_success_body), new TutorialScreenConfig.Animations(R.raw.action_key_edu, R.raw.action_key_success));
            Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, 1617962556);
            if (m == composer$Companion$Empty$1) {
                m = SnapshotStateKt.mutableStateOf$default(TutorialActionState.NOT_STARTED);
                composerImpl.updateRememberedValue(m);
            }
            final MutableState mutableState = (MutableState) m;
            Object m2 = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, 1617962622);
            if (m2 == composer$Companion$Empty$1) {
                m2 = new FocusRequester();
                composerImpl.updateRememberedValue(m2);
            }
            FocusRequester focusRequester = (FocusRequester) m2;
            composerImpl.end(false);
            FillElement fillElement = SizeKt.FillWholeMaxSize;
            composerImpl.startReplaceGroup(1617962743);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.ui.composable.ActionKeyTutorialScreenKt$ActionKeyTutorialScreen$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        KeyEvent keyEvent = ((androidx.compose.ui.input.key.KeyEvent) obj).nativeKeyEvent;
                        if (Key.m446equalsimpl0(Key_androidKt.Key(keyEvent.getKeyCode()), Key.MetaLeft) && KeyEventType.m447equalsimpl0(KeyEvent_androidKt.m449getTypeZmokQxo(keyEvent), 1)) {
                            MutableState.this.setValue(TutorialActionState.FINISHED);
                        }
                        return Boolean.TRUE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            composerImpl.end(false);
            Modifier focusable$default = FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(KeyInputModifierKt.onKeyEvent(fillElement, (Function1) rememberedValue2), focusRequester), false, null, 3);
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            int i3 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, focusable$default);
            ComposeUiNode.Companion.getClass();
            Function0 function03 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function03);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            ActionTutorialContentKt.ActionTutorialContent((TutorialActionState) mutableState.getValue(), function0, tutorialScreenConfig, composerImpl, ((i2 << 3) & 112) | 512);
            composerImpl.end(true);
            Unit unit = Unit.INSTANCE;
            composerImpl.startReplaceGroup(1617963168);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new ActionKeyTutorialScreenKt$ActionKeyTutorialScreen$3$1(focusRequester, null);
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            composerImpl.end(false);
            EffectsKt.LaunchedEffect(composerImpl, unit, (Function2) rememberedValue3);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.inputdevice.tutorial.ui.composable.ActionKeyTutorialScreenKt$ActionKeyTutorialScreen$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ActionKeyTutorialScreenKt.ActionKeyTutorialScreen(Function0.this, function02, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
