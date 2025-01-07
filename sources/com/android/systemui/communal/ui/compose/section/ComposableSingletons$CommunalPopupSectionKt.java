package com.android.systemui.communal.ui.compose.section;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.outlined.TouchAppKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.PathNode;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComposableSingletons$CommunalPopupSectionKt {

    /* renamed from: lambda-1, reason: not valid java name */
    public static final ComposableLambdaImpl f25lambda1 = new ComposableLambdaImpl(-900706228, false, new Function2() { // from class: com.android.systemui.communal.ui.compose.section.ComposableSingletons$CommunalPopupSectionKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Composer composer = (Composer) obj;
            if ((((Number) obj2).intValue() & 11) == 2) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            ComposerImpl composerImpl2 = (ComposerImpl) composer;
            AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl2.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(BackgroundKt.m25backgroundbw27NRU(SizeKt.m108height3ABfNKs(companion, 56), androidColorScheme.secondary, RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(50)), 16);
            RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Center, Alignment.Companion.CenterVertically, composerImpl2, 54);
            int i = composerImpl2.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl2.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl2, m98padding3ABfNKs);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl2.startReusableNode();
            if (composerImpl2.inserting) {
                composerImpl2.createNode(function0);
            } else {
                composerImpl2.useNode();
            }
            Updater.m259setimpl(composerImpl2, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl2.inserting || !Intrinsics.areEqual(composerImpl2.rememberedValue(), Integer.valueOf(i))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i, composerImpl2, i, function2);
            }
            Updater.m259setimpl(composerImpl2, materializeModifier, ComposeUiNode.Companion.SetModifier);
            ImageVector imageVector = TouchAppKt._touchApp;
            if (imageVector == null) {
                ImageVector.Builder builder = new ImageVector.Builder("Outlined.TouchApp", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList = VectorKt.EmptyPath;
                SolidColor solidColor = new SolidColor(Color.Black);
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(18.19f, 12.44f);
                pathBuilder.lineToRelative(-3.24f, -1.62f);
                pathBuilder.curveToRelative(1.29f, -1.0f, 2.12f, -2.56f, 2.12f, -4.32f);
                pathBuilder.curveToRelative(0.0f, -3.03f, -2.47f, -5.5f, -5.5f, -5.5f);
                pathBuilder._nodes.add(new PathNode.RelativeReflectiveCurveTo(-5.5f, 2.47f, -5.5f, 5.5f));
                pathBuilder.curveToRelative(0.0f, 2.13f, 1.22f, 3.98f, 3.0f, 4.89f);
                pathBuilder.verticalLineToRelative(3.26f);
                pathBuilder.curveToRelative(-2.15f, -0.46f, -2.02f, -0.44f, -2.26f, -0.44f);
                pathBuilder.curveToRelative(-0.53f, 0.0f, -1.03f, 0.21f, -1.41f, 0.59f);
                pathBuilder.lineTo(4.0f, 16.22f);
                pathBuilder.lineToRelative(5.09f, 5.09f);
                pathBuilder.curveTo(9.52f, 21.75f, 10.12f, 22.0f, 10.74f, 22.0f);
                pathBuilder.horizontalLineToRelative(6.3f);
                pathBuilder.curveToRelative(0.98f, 0.0f, 1.81f, -0.7f, 1.97f, -1.67f);
                pathBuilder.lineToRelative(0.8f, -4.71f);
                pathBuilder.curveTo(20.03f, 14.32f, 19.38f, 13.04f, 18.19f, 12.44f);
                pathBuilder.close();
                pathBuilder.moveTo(17.84f, 15.29f);
                pathBuilder.lineTo(17.04f, 20.0f);
                pathBuilder.horizontalLineToRelative(-6.3f);
                pathBuilder.curveToRelative(-0.09f, 0.0f, -0.17f, -0.04f, -0.24f, -0.1f);
                pathBuilder.lineToRelative(-3.68f, -3.68f);
                pathBuilder.lineToRelative(4.25f, 0.89f);
                pathBuilder.verticalLineTo(6.5f);
                pathBuilder.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
                pathBuilder.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
                pathBuilder.verticalLineToRelative(6.0f);
                pathBuilder.horizontalLineToRelative(1.76f);
                pathBuilder.lineToRelative(3.46f, 1.73f);
                pathBuilder.curveTo(17.69f, 14.43f, 17.91f, 14.86f, 17.84f, 15.29f);
                pathBuilder.close();
                pathBuilder.moveTo(8.07f, 6.5f);
                pathBuilder.curveToRelative(0.0f, -1.93f, 1.57f, -3.5f, 3.5f, -3.5f);
                pathBuilder._nodes.add(new PathNode.RelativeReflectiveCurveTo(3.5f, 1.57f, 3.5f, 3.5f));
                pathBuilder.curveToRelative(0.0f, 0.95f, -0.38f, 1.81f, -1.0f, 2.44f);
                pathBuilder.verticalLineTo(6.5f);
                pathBuilder.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
                pathBuilder.curveToRelative(-1.38f, 0.0f, -2.5f, 1.12f, -2.5f, 2.5f);
                pathBuilder.verticalLineToRelative(2.44f);
                pathBuilder.curveTo(8.45f, 8.31f, 8.07f, 7.45f, 8.07f, 6.5f);
                pathBuilder.close();
                builder.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor, null, "", pathBuilder._nodes);
                imageVector = builder.build();
                TouchAppKt._touchApp = imageVector;
            }
            IconKt.m214Iconww6aTOc(imageVector, StringResources_androidKt.stringResource(R.string.popup_on_dismiss_cta_tile_text, composerImpl2), SizeKt.m113size3ABfNKs(companion, 20), androidColorScheme.onSecondary, composerImpl2, 384, 0);
            SpacerKt.Spacer(composerImpl2, SizeKt.m113size3ABfNKs(companion, 8));
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.popup_on_dismiss_cta_tile_text, composerImpl2), null, androidColorScheme.onSecondary, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl2).titleSmall, composerImpl2, 0, 0, 65530);
            composerImpl2.end(true);
            return Unit.INSTANCE;
        }
    });
}
