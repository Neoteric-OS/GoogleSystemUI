package androidx.compose.material3;

import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MaterialThemeKt {
    static {
        CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.material3.MaterialThemeKt$LocalUsingExpressiveTheme$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Boolean.FALSE;
            }
        });
    }

    public static final void MaterialTheme(ColorScheme colorScheme, Shapes shapes, Typography typography, final Function2 function2, Composer composer, final int i, final int i2) {
        ColorScheme colorScheme2;
        int i3;
        Shapes shapes2;
        Typography typography2;
        Shapes shapes3;
        Typography typography3;
        final Typography typography4;
        final ColorScheme colorScheme3;
        int i4;
        int i5;
        int i6;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-2127166334);
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                colorScheme2 = colorScheme;
                if (composerImpl.changed(colorScheme2)) {
                    i6 = 4;
                    i3 = i6 | i;
                }
            } else {
                colorScheme2 = colorScheme;
            }
            i6 = 2;
            i3 = i6 | i;
        } else {
            colorScheme2 = colorScheme;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                shapes2 = shapes;
                if (composerImpl.changed(shapes2)) {
                    i5 = 32;
                    i3 |= i5;
                }
            } else {
                shapes2 = shapes;
            }
            i5 = 16;
            i3 |= i5;
        } else {
            shapes2 = shapes;
        }
        if ((i & 384) == 0) {
            if ((i2 & 4) == 0) {
                typography2 = typography;
                if (composerImpl.changed(typography2)) {
                    i4 = 256;
                    i3 |= i4;
                }
            } else {
                typography2 = typography;
            }
            i4 = 128;
            i3 |= i4;
        } else {
            typography2 = typography;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerImpl.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i3 & 1171) == 1170 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
            colorScheme3 = colorScheme2;
            typography4 = typography2;
        } else {
            composerImpl.startDefaults();
            if ((i & 1) == 0 || composerImpl.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    colorScheme2 = MaterialTheme.getColorScheme(composerImpl);
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    shapes3 = MaterialTheme.getShapes(composerImpl);
                    i3 &= -113;
                } else {
                    shapes3 = shapes2;
                }
                if ((i2 & 4) != 0) {
                    typography3 = MaterialTheme.getTypography(composerImpl);
                    i3 &= -897;
                    composerImpl.endDefaults();
                    OpaqueKey opaqueKey = ComposerKt.invocation;
                    int i7 = i3 & 14;
                    int i8 = i3 << 3;
                    MaterialTheme(colorScheme2, MaterialTheme.getMotionScheme(composerImpl), shapes3, typography3, function2, composerImpl, i7 | (i8 & 896) | (i8 & 7168) | (i8 & 57344), 0);
                    shapes2 = shapes3;
                    ColorScheme colorScheme4 = colorScheme2;
                    typography4 = typography3;
                    colorScheme3 = colorScheme4;
                }
            } else {
                composerImpl.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
                shapes3 = shapes2;
            }
            typography3 = typography2;
            composerImpl.endDefaults();
            OpaqueKey opaqueKey2 = ComposerKt.invocation;
            int i72 = i3 & 14;
            int i82 = i3 << 3;
            MaterialTheme(colorScheme2, MaterialTheme.getMotionScheme(composerImpl), shapes3, typography3, function2, composerImpl, i72 | (i82 & 896) | (i82 & 7168) | (i82 & 57344), 0);
            shapes2 = shapes3;
            ColorScheme colorScheme42 = colorScheme2;
            typography4 = typography3;
            colorScheme3 = colorScheme42;
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Shapes shapes4 = shapes2;
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.MaterialThemeKt$MaterialTheme$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    MaterialThemeKt.MaterialTheme(ColorScheme.this, shapes4, typography4, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.material3.MaterialThemeKt$MaterialTheme$2, kotlin.jvm.internal.Lambda] */
    public static final void MaterialTheme(ColorScheme colorScheme, MotionScheme motionScheme, Shapes shapes, Typography typography, final Function2 function2, Composer composer, final int i, final int i2) {
        ColorScheme colorScheme2;
        int i3;
        MotionScheme motionScheme2;
        Shapes shapes2;
        final Typography typography2;
        ColorScheme colorScheme3;
        long Color;
        final ColorScheme colorScheme4;
        int i4;
        int i5;
        int i6;
        int i7;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1317723617);
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                colorScheme2 = colorScheme;
                if (composerImpl.changed(colorScheme2)) {
                    i7 = 4;
                    i3 = i7 | i;
                }
            } else {
                colorScheme2 = colorScheme;
            }
            i7 = 2;
            i3 = i7 | i;
        } else {
            colorScheme2 = colorScheme;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                motionScheme2 = motionScheme;
                if (composerImpl.changed(motionScheme2)) {
                    i6 = 32;
                    i3 |= i6;
                }
            } else {
                motionScheme2 = motionScheme;
            }
            i6 = 16;
            i3 |= i6;
        } else {
            motionScheme2 = motionScheme;
        }
        if ((i & 384) == 0) {
            if ((i2 & 4) == 0) {
                shapes2 = shapes;
                if (composerImpl.changed(shapes2)) {
                    i5 = 256;
                    i3 |= i5;
                }
            } else {
                shapes2 = shapes;
            }
            i5 = 128;
            i3 |= i5;
        } else {
            shapes2 = shapes;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                typography2 = typography;
                if (composerImpl.changed(typography2)) {
                    i4 = 2048;
                    i3 |= i4;
                }
            } else {
                typography2 = typography;
            }
            i4 = 1024;
            i3 |= i4;
        } else {
            typography2 = typography;
        }
        if ((i2 & 16) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            i3 |= composerImpl.changedInstance(function2) ? 16384 : 8192;
        }
        if ((i3 & 9363) == 9362 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
            colorScheme4 = colorScheme2;
        } else {
            composerImpl.startDefaults();
            if ((i & 1) != 0 && !composerImpl.getDefaultsInvalid()) {
                composerImpl.skipToGroupEnd();
                colorScheme3 = colorScheme2;
            } else {
                colorScheme3 = (i2 & 1) != 0 ? MaterialTheme.getColorScheme(composerImpl) : colorScheme2;
                if ((i2 & 2) != 0) {
                    motionScheme2 = MaterialTheme.getMotionScheme(composerImpl);
                }
                if ((i2 & 4) != 0) {
                    shapes2 = MaterialTheme.getShapes(composerImpl);
                }
                if ((i2 & 8) != 0) {
                    typography2 = MaterialTheme.getTypography(composerImpl);
                }
            }
            composerImpl.endDefaults();
            OpaqueKey opaqueKey = ComposerKt.invocation;
            IndicationNodeFactory m221rippleH2RKhps$default = RippleKt.m221rippleH2RKhps$default(0.0f, false, 7);
            long j = colorScheme3.primary;
            boolean changed = composerImpl.changed(j);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                Color = ColorKt.Color(Color.m368getRedimpl(j), Color.m367getGreenimpl(j), Color.m365getBlueimpl(j), 0.4f, Color.m366getColorSpaceimpl(j));
                rememberedValue = new TextSelectionColors(j, Color);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{ColorSchemeKt.LocalColorScheme.defaultProvidedValue$runtime_release(colorScheme3), MotionSchemeKt.LocalMotionScheme.defaultProvidedValue$runtime_release(motionScheme2), IndicationKt.LocalIndication.defaultProvidedValue$runtime_release(m221rippleH2RKhps$default), ShapesKt.LocalShapes.defaultProvidedValue$runtime_release(shapes2), TextSelectionColorsKt.LocalTextSelectionColors.defaultProvidedValue$runtime_release((TextSelectionColors) rememberedValue), TypographyKt.LocalTypography.defaultProvidedValue$runtime_release(typography2)}, ComposableLambdaKt.rememberComposableLambda(-2097082079, new Function2() { // from class: androidx.compose.material3.MaterialThemeKt$MaterialTheme$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Composer composer2 = (Composer) obj;
                    if ((((Number) obj2).intValue() & 3) == 2) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    TextKt.ProvideTextStyle(Typography.this.bodyLarge, function2, composer2, 0);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 56);
            colorScheme4 = colorScheme3;
        }
        final MotionScheme motionScheme3 = motionScheme2;
        final Typography typography3 = typography2;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Shapes shapes3 = shapes2;
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.MaterialThemeKt$MaterialTheme$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    MaterialThemeKt.MaterialTheme(ColorScheme.this, motionScheme3, shapes3, typography3, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
