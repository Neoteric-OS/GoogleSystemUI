package com.android.systemui.communal.ui.compose;

import android.view.IWindowManager;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Start$1;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.filled.AddKt;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.outlined.EditKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.TextUnitKt;
import com.android.systemui.communal.util.DensityUtils;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComposableSingletons$CommunalHubKt {

    /* renamed from: lambda-1, reason: not valid java name */
    public static final ComposableLambdaImpl f16lambda1 = new ComposableLambdaImpl(878073318, false, new Function3() { // from class: com.android.systemui.communal.ui.compose.ComposableSingletons$CommunalHubKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            if ((((Number) obj3).intValue() & 81) == 16) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.communal_widgets_disclaimer_button, composer), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composer).labelLarge, composer, 0, 0, 65534);
            return Unit.INSTANCE;
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static final ComposableLambdaImpl f17lambda2 = new ComposableLambdaImpl(1931407786, false, new Function3() { // from class: com.android.systemui.communal.ui.compose.ComposableSingletons$CommunalHubKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            if ((((Number) obj3).intValue() & 81) == 16) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            ImageVector add = AddKt.getAdd();
            String stringResource = StringResources_androidKt.stringResource(R.string.label_for_button_in_empty_state_cta, composer);
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            IconKt.m214Iconww6aTOc(add, stringResource, SizeKt.m113size3ABfNKs(companion, 24), 0L, composer, 384, 8);
            SpacerKt.Spacer(composer, SizeKt.m117width3ABfNKs(companion, ButtonDefaults.IconSpacing));
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.label_for_button_in_empty_state_cta, composer), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composer).titleSmall, composer, 0, 0, 65534);
            return Unit.INSTANCE;
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static final ComposableLambdaImpl f18lambda3 = new ComposableLambdaImpl(1260877735, false, new Function3() { // from class: com.android.systemui.communal.ui.compose.ComposableSingletons$CommunalHubKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            if ((((Number) obj3).intValue() & 81) == 16) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
            Arrangement.SpacedAligned m79spacedByD5KLDUw = Arrangement.m79spacedByD5KLDUw(ButtonDefaults.IconSpacing, Alignment.Companion.CenterHorizontally);
            BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m79spacedByD5KLDUw, vertical, composer, 48);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer);
            ComposerImpl composerImpl2 = (ComposerImpl) composer;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl2.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl2.startReusableNode();
            if (composerImpl2.inserting) {
                composerImpl2.createNode(function0);
            } else {
                composerImpl2.useNode();
            }
            Updater.m259setimpl(composer, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composer, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl2.inserting || !Intrinsics.areEqual(composerImpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl2, currentCompositeKeyHash, function2);
            }
            Updater.m259setimpl(composer, materializeModifier, ComposeUiNode.Companion.SetModifier);
            ImageVector imageVector = CloseKt._close;
            if (imageVector == null) {
                ImageVector.Builder builder = new ImageVector.Builder("Filled.Close", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList = VectorKt.EmptyPath;
                SolidColor solidColor = new SolidColor(Color.Black);
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(19.0f, 6.41f);
                pathBuilder.lineTo(17.59f, 5.0f);
                pathBuilder.lineTo(12.0f, 10.59f);
                pathBuilder.lineTo(6.41f, 5.0f);
                pathBuilder.lineTo(5.0f, 6.41f);
                pathBuilder.lineTo(10.59f, 12.0f);
                pathBuilder.lineTo(5.0f, 17.59f);
                pathBuilder.lineTo(6.41f, 19.0f);
                pathBuilder.lineTo(12.0f, 13.41f);
                pathBuilder.lineTo(17.59f, 19.0f);
                pathBuilder.lineTo(19.0f, 17.59f);
                pathBuilder.lineTo(13.41f, 12.0f);
                pathBuilder.close();
                builder.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor, null, "", pathBuilder._nodes);
                imageVector = builder.build();
                CloseKt._close = imageVector;
            }
            IconKt.m214Iconww6aTOc(imageVector, (String) null, (Modifier) null, 0L, composer, 48, 12);
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.button_to_remove_widget, composer), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            composerImpl2.end(true);
            return Unit.INSTANCE;
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static final ComposableLambdaImpl f19lambda4 = new ComposableLambdaImpl(707199469, false, new Function3() { // from class: com.android.systemui.communal.ui.compose.ComposableSingletons$CommunalHubKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            if ((((Number) obj3).intValue() & 81) == 16) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            ImageVector imageVector = CheckKt._check;
            if (imageVector == null) {
                ImageVector.Builder builder = new ImageVector.Builder("Filled.Check", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList = VectorKt.EmptyPath;
                SolidColor solidColor = new SolidColor(Color.Black);
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(9.0f, 16.17f);
                pathBuilder.lineTo(4.83f, 12.0f);
                pathBuilder.lineToRelative(-1.42f, 1.41f);
                pathBuilder.lineTo(9.0f, 19.0f);
                pathBuilder.lineTo(21.0f, 7.0f);
                pathBuilder.lineToRelative(-1.41f, -1.41f);
                pathBuilder.close();
                builder.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor, null, "", pathBuilder._nodes);
                imageVector = builder.build();
                CheckKt._check = imageVector;
            }
            IconKt.m214Iconww6aTOc(imageVector, (String) null, (Modifier) null, 0L, composer, 48, 12);
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.hub_mode_editing_exit_button_text, composer), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            return Unit.INSTANCE;
        }
    });

    /* renamed from: lambda-5, reason: not valid java name */
    public static final ComposableLambdaImpl f20lambda5 = new ComposableLambdaImpl(-1263281864, false, new Function3() { // from class: com.android.systemui.communal.ui.compose.ComposableSingletons$CommunalHubKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            if ((((Number) obj3).intValue() & 81) == 16) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.cta_tile_button_to_dismiss, composer), null, 0L, TextUnitKt.getSp(14), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 3072, 0, 131062);
            return Unit.INSTANCE;
        }
    });

    /* renamed from: lambda-6, reason: not valid java name */
    public static final ComposableLambdaImpl f21lambda6 = new ComposableLambdaImpl(-1707479242, false, new Function3() { // from class: com.android.systemui.communal.ui.compose.ComposableSingletons$CommunalHubKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            if ((((Number) obj3).intValue() & 81) == 16) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.cta_tile_button_to_open_widget_editor, composer), null, 0L, TextUnitKt.getSp(14), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 3072, 0, 131062);
            return Unit.INSTANCE;
        }
    });

    /* renamed from: lambda-7, reason: not valid java name */
    public static final ComposableLambdaImpl f22lambda7 = new ComposableLambdaImpl(-1962818462, false, new Function2() { // from class: com.android.systemui.communal.ui.compose.ComposableSingletons$CommunalHubKt$lambda-7$1
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
            ImageVector imageVector = EditKt._edit;
            if (imageVector == null) {
                ImageVector.Builder builder = new ImageVector.Builder("Outlined.Edit", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList = VectorKt.EmptyPath;
                SolidColor solidColor = new SolidColor(Color.Black);
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(14.06f, 9.02f);
                pathBuilder.lineToRelative(0.92f, 0.92f);
                pathBuilder.lineTo(5.92f, 19.0f);
                pathBuilder.lineTo(5.0f, 19.0f);
                pathBuilder.verticalLineToRelative(-0.92f);
                pathBuilder.lineToRelative(9.06f, -9.06f);
                pathBuilder.moveTo(17.66f, 3.0f);
                pathBuilder.curveToRelative(-0.25f, 0.0f, -0.51f, 0.1f, -0.7f, 0.29f);
                pathBuilder.lineToRelative(-1.83f, 1.83f);
                pathBuilder.lineToRelative(3.75f, 3.75f);
                pathBuilder.lineToRelative(1.83f, -1.83f);
                pathBuilder.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
                pathBuilder.lineToRelative(-2.34f, -2.34f);
                pathBuilder.curveToRelative(-0.2f, -0.2f, -0.45f, -0.29f, -0.71f, -0.29f);
                pathBuilder.close();
                pathBuilder.moveTo(14.06f, 6.19f);
                pathBuilder.lineTo(3.0f, 17.25f);
                pathBuilder.lineTo(3.0f, 21.0f);
                pathBuilder.horizontalLineToRelative(3.75f);
                pathBuilder.lineTo(17.81f, 9.94f);
                pathBuilder.lineToRelative(-3.75f, -3.75f);
                pathBuilder.close();
                builder.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor, null, "", pathBuilder._nodes);
                imageVector = builder.build();
                EditKt._edit = imageVector;
            }
            String stringResource = StringResources_androidKt.stringResource(R.string.edit_widget, composer);
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            IWindowManager iWindowManager = DensityUtils.windowManagerService;
            IconKt.m214Iconww6aTOc(imageVector, stringResource, PaddingKt.m98padding3ABfNKs(companion, DensityUtils.Companion.m799getAdjustedDpu2uoSUM(12)), 0L, composer, 0, 8);
            return Unit.INSTANCE;
        }
    });

    /* renamed from: lambda-8, reason: not valid java name */
    public static final ComposableLambdaImpl f23lambda8 = new ComposableLambdaImpl(-1769929298, false, new Function3() { // from class: com.android.systemui.communal.ui.compose.ComposableSingletons$CommunalHubKt$lambda-8$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            if ((((Number) obj3).intValue() & 81) == 16) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            return Unit.INSTANCE;
        }
    });
}
