package com.android.systemui.people.ui.compose;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.style.TextAlign;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PeopleScreenEmptyKt {
    public static final void ExampleTile(final int i, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-127501856);
        if (i == 0 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
            RoundedCornerShape m148RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(28);
            AndroidColorScheme.DeprecatedValues deprecatedValues = androidColorScheme.deprecated;
            SurfaceKt.m232SurfaceT9BRK9s(null, m148RoundedCornerShape0680j_4, deprecatedValues.colorSurface, deprecatedValues.textColorPrimary, 0.0f, 0.0f, null, ComposableSingletons$PeopleScreenEmptyKt.f39lambda2, composerImpl, 12582912, 113);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.people.ui.compose.PeopleScreenEmptyKt$ExampleTile$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PeopleScreenEmptyKt.ExampleTile(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void PeopleScreenEmpty(final Function0 function0, Composer composer, final int i) {
        int i2;
        Modifier weight;
        Modifier weight2;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(601596182);
        if ((i & 14) == 0) {
            i2 = (composerImpl2.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(SizeKt.FillWholeMaxSize, PeopleScreenKt.PeopleSpacePadding);
            ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.CenterHorizontally, composerImpl2, 48);
            int i3 = composerImpl2.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl2.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl2, m98padding3ABfNKs);
            ComposeUiNode.Companion.getClass();
            Function0 function02 = ComposeUiNode.Companion.Constructor;
            composerImpl2.startReusableNode();
            if (composerImpl2.inserting) {
                composerImpl2.createNode(function02);
            } else {
                composerImpl2.useNode();
            }
            Updater.m259setimpl(composerImpl2, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl2.inserting || !Intrinsics.areEqual(composerImpl2.rememberedValue(), Integer.valueOf(i3))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl2, i3, function2);
            }
            Updater.m259setimpl(composerImpl2, materializeModifier, ComposeUiNode.Companion.SetModifier);
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.select_conversation_title, composerImpl2), null, 0L, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl2).headlineSmall, composerImpl2, 0, 0, 65022);
            SpacerKt.Spacer(composerImpl2, SizeKt.m108height3ABfNKs(companion, 50));
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.no_conversations_text, composerImpl2), null, 0L, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl2).bodyLarge, composerImpl2, 0, 0, 65022);
            weight = ColumnScopeInstance.INSTANCE.weight(companion, 1.0f, true);
            SpacerKt.Spacer(composerImpl2, weight);
            ExampleTile(0, composerImpl2);
            weight2 = ColumnScopeInstance.INSTANCE.weight(companion, 1.0f, true);
            SpacerKt.Spacer(composerImpl2, weight2);
            AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl2.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
            Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion, 1.0f);
            float f = 56;
            float f2 = (true && true) ? Float.NaN : 0.0f;
            if ((2 & 1) != 0) {
                f = Float.NaN;
            }
            Modifier m107defaultMinSizeVpY3zN4 = SizeKt.m107defaultMinSizeVpY3zN4(fillMaxWidth, f2, f);
            PaddingValuesImpl paddingValuesImpl = ButtonDefaults.ContentPadding;
            AndroidColorScheme.DeprecatedValues deprecatedValues = androidColorScheme.deprecated;
            composerImpl = composerImpl2;
            ButtonKt.Button(function0, m107defaultMinSizeVpY3zN4, false, null, ButtonDefaults.m198buttonColorsro_MJ88(deprecatedValues.colorAccentPrimary, deprecatedValues.textColorOnAccent, composerImpl2, 12), null, null, null, null, ComposableSingletons$PeopleScreenEmptyKt.f38lambda1, composerImpl2, (i2 & 14) | 805306416, 492);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.people.ui.compose.PeopleScreenEmptyKt$PeopleScreenEmpty$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PeopleScreenEmptyKt.PeopleScreenEmpty(function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
