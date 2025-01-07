package com.android.compose.theme;

import android.content.Context;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.DynamicTonalPaletteKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.material3.Typography;
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass;
import androidx.compose.material3.windowsizeclass.WindowSizeClass;
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontListFontFamily;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.window.layout.WindowMetricsCalculator;
import com.android.compose.theme.typography.TypefaceNames;
import com.android.compose.theme.typography.TypefaceTokens;
import com.android.compose.windowsizeclass.WindowSizeClassKt;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PlatformThemeKt {
    /* JADX WARN: Type inference failed for: r4v20, types: [com.android.compose.theme.PlatformThemeKt$PlatformTheme$1, kotlin.jvm.internal.Lambda] */
    public static final void PlatformTheme(boolean z, final Function2 function2, Composer composer, final int i, final int i2) {
        final boolean z2;
        int i3;
        int i4;
        int i5;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(820753581);
        if ((i & 14) == 0) {
            if ((i2 & 1) == 0) {
                z2 = z;
                if (composerImpl.changed(z2)) {
                    i5 = 4;
                    i3 = i5 | i;
                }
            } else {
                z2 = z;
            }
            i5 = 2;
            i3 = i5 | i;
        } else {
            z2 = z;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 112) == 0) {
            i3 |= composerImpl.changedInstance(function2) ? 32 : 16;
        }
        if ((i3 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            composerImpl.startDefaults();
            if ((i & 1) != 0 && !composerImpl.getDefaultsInvalid()) {
                composerImpl.skipToGroupEnd();
                int i6 = i2 & 1;
            } else if ((i2 & 1) != 0) {
                z2 = DarkThemeKt.isSystemInDarkTheme(composerImpl);
            }
            boolean z3 = z2;
            composerImpl.endDefaults();
            OpaqueKey opaqueKey = ComposerKt.invocation;
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = AndroidCompositionLocals_androidKt.LocalContext;
            Context context = (Context) composerImpl.consume(staticProvidableCompositionLocal);
            ColorScheme dynamicDarkColorScheme = z3 ? DynamicTonalPaletteKt.dynamicDarkColorScheme(context) : DynamicTonalPaletteKt.dynamicLightColorScheme(context);
            final AndroidColorScheme androidColorScheme = new AndroidColorScheme(context);
            composerImpl.startReplaceGroup(-262272317);
            boolean changed = composerImpl.changed(context);
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (changed || rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = TypefaceNames.Companion.get(context);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            TypefaceNames typefaceNames = (TypefaceNames) rememberedValue;
            composerImpl.end(false);
            composerImpl.startReplaceGroup(-262272239);
            boolean changed2 = composerImpl.changed(typefaceNames);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (changed2 || rememberedValue2 == composer$Companion$Empty$1) {
                TypefaceTokens typefaceTokens = new TypefaceTokens(typefaceNames);
                long sp = TextUnitKt.getSp(24.0d);
                long sp2 = TextUnitKt.getSp(16);
                long sp3 = TextUnitKt.getSp(0.0d);
                FontWeight fontWeight = TypefaceTokens.WeightRegular;
                long sp4 = TextUnitKt.getSp(20.0d);
                long sp5 = TextUnitKt.getSp(14);
                long sp6 = TextUnitKt.getSp(0.0d);
                long sp7 = TextUnitKt.getSp(16.0d);
                long sp8 = TextUnitKt.getSp(12);
                long sp9 = TextUnitKt.getSp(0.1d);
                long sp10 = TextUnitKt.getSp(64.0d);
                long sp11 = TextUnitKt.getSp(57);
                long sp12 = TextUnitKt.getSp(0.0d);
                long sp13 = TextUnitKt.getSp(52.0d);
                long sp14 = TextUnitKt.getSp(45);
                long sp15 = TextUnitKt.getSp(0.0d);
                long sp16 = TextUnitKt.getSp(44.0d);
                long sp17 = TextUnitKt.getSp(36);
                long sp18 = TextUnitKt.getSp(0.0d);
                long sp19 = TextUnitKt.getSp(40.0d);
                long sp20 = TextUnitKt.getSp(32);
                long sp21 = TextUnitKt.getSp(0.0d);
                long sp22 = TextUnitKt.getSp(36.0d);
                long sp23 = TextUnitKt.getSp(28);
                long sp24 = TextUnitKt.getSp(0.0d);
                long sp25 = TextUnitKt.getSp(32.0d);
                long sp26 = TextUnitKt.getSp(24);
                long sp27 = TextUnitKt.getSp(0.0d);
                long sp28 = TextUnitKt.getSp(20.0d);
                long sp29 = TextUnitKt.getSp(14);
                long sp30 = TextUnitKt.getSp(0.0d);
                FontWeight fontWeight2 = TypefaceTokens.WeightMedium;
                long sp31 = TextUnitKt.getSp(16.0d);
                long sp32 = TextUnitKt.getSp(12);
                long sp33 = TextUnitKt.getSp(0.1d);
                long sp34 = TextUnitKt.getSp(16.0d);
                long sp35 = TextUnitKt.getSp(11);
                long sp36 = TextUnitKt.getSp(0.1d);
                long sp37 = TextUnitKt.getSp(28.0d);
                long sp38 = TextUnitKt.getSp(22);
                long sp39 = TextUnitKt.getSp(0.0d);
                long sp40 = TextUnitKt.getSp(24.0d);
                long sp41 = TextUnitKt.getSp(16);
                long sp42 = TextUnitKt.getSp(0.0d);
                long sp43 = TextUnitKt.getSp(20.0d);
                long sp44 = TextUnitKt.getSp(14);
                long sp45 = TextUnitKt.getSp(0.0d);
                FontListFontFamily fontListFontFamily = typefaceTokens.plain;
                TextStyle textStyle = new TextStyle(0L, sp2, fontWeight, fontListFontFamily, sp3, 0, sp, 16645977);
                TextStyle textStyle2 = new TextStyle(0L, sp5, fontWeight, fontListFontFamily, sp6, 0, sp4, 16645977);
                TextStyle textStyle3 = new TextStyle(0L, sp8, fontWeight, fontListFontFamily, sp9, 0, sp7, 16645977);
                FontListFontFamily fontListFontFamily2 = typefaceTokens.brand;
                rememberedValue2 = new Typography(new TextStyle(0L, sp11, fontWeight, fontListFontFamily2, sp12, 0, sp10, 16645977), new TextStyle(0L, sp14, fontWeight, fontListFontFamily2, sp15, 0, sp13, 16645977), new TextStyle(0L, sp17, fontWeight, fontListFontFamily2, sp18, 0, sp16, 16645977), new TextStyle(0L, sp20, fontWeight, fontListFontFamily2, sp21, 0, sp19, 16645977), new TextStyle(0L, sp23, fontWeight, fontListFontFamily2, sp24, 0, sp22, 16645977), new TextStyle(0L, sp26, fontWeight, fontListFontFamily2, sp27, 0, sp25, 16645977), new TextStyle(0L, sp38, fontWeight, fontListFontFamily2, sp39, 0, sp37, 16645977), new TextStyle(0L, sp41, fontWeight2, fontListFontFamily, sp42, 0, sp40, 16645977), new TextStyle(0L, sp44, fontWeight2, fontListFontFamily, sp45, 0, sp43, 16645977), textStyle, textStyle2, textStyle3, new TextStyle(0L, sp29, fontWeight2, fontListFontFamily, sp30, 0, sp28, 16645977), new TextStyle(0L, sp32, fontWeight2, fontListFontFamily, sp33, 0, sp31, 16645977), new TextStyle(0L, sp35, fontWeight2, fontListFontFamily, sp36, 0, sp34, 16645977));
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            Typography typography = (Typography) rememberedValue2;
            composerImpl.end(false);
            StaticProvidableCompositionLocal staticProvidableCompositionLocal2 = WindowSizeClassKt.LocalWindowSizeClass;
            composerImpl.startReplaceGroup(-702298760);
            composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration);
            Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
            Context context2 = (Context) composerImpl.consume(staticProvidableCompositionLocal);
            WindowMetricsCalculator.Companion.getClass();
            long mo49toDpSizekrfVVM = density.mo49toDpSizekrfVVM(RectHelper_androidKt.toComposeRect(WindowMetricsCalculator.Companion.getOrCreate().computeCurrentWindowMetrics(context2)._bounds.toRect()).m321getSizeNHjbRc());
            Set set = WindowWidthSizeClass.DefaultSizeClasses;
            Set set2 = WindowHeightSizeClass.DefaultSizeClasses;
            float m672getWidthD9Ej5fM = DpSize.m672getWidthD9Ej5fM(mo49toDpSizekrfVVM);
            float f = 0;
            if (Float.compare(m672getWidthD9Ej5fM, f) < 0) {
                throw new IllegalArgumentException("Width must not be negative");
            }
            if (set.isEmpty()) {
                throw new IllegalArgumentException("Must support at least one size class");
            }
            List list = WindowWidthSizeClass.AllSizeClassList;
            int size = list.size();
            int i7 = 0;
            int i8 = 0;
            while (true) {
                if (i7 >= size) {
                    i4 = i8;
                    break;
                }
                int i9 = size;
                i4 = ((WindowWidthSizeClass) list.get(i7)).value;
                List list2 = list;
                if (set.contains(new WindowWidthSizeClass(i4))) {
                    if (Float.compare(m672getWidthD9Ej5fM, WindowWidthSizeClass.Companion.m253breakpointfhkHA5s(i4)) >= 0) {
                        break;
                    } else {
                        i8 = i4;
                    }
                }
                i7++;
                list = list2;
                size = i9;
            }
            Set set3 = WindowHeightSizeClass.DefaultSizeClasses;
            float m671getHeightD9Ej5fM = DpSize.m671getHeightD9Ej5fM(mo49toDpSizekrfVVM);
            if (Float.compare(m671getHeightD9Ej5fM, f) < 0) {
                throw new IllegalArgumentException("Width must not be negative");
            }
            if (set2.isEmpty()) {
                throw new IllegalArgumentException("Must support at least one size class");
            }
            List list3 = WindowHeightSizeClass.AllSizeClassList;
            int size2 = list3.size();
            int i10 = 2;
            int i11 = 0;
            while (true) {
                if (i11 >= size2) {
                    break;
                }
                int i12 = ((WindowHeightSizeClass) list3.get(i11)).value;
                if (set2.contains(new WindowHeightSizeClass(i12))) {
                    if (Float.compare(m671getHeightD9Ej5fM, WindowHeightSizeClass.Companion.m250breakpointsr04XMo(i12)) >= 0) {
                        i10 = i12;
                        break;
                    }
                    i10 = i12;
                }
                i11++;
            }
            final WindowSizeClass windowSizeClass = new WindowSizeClass(i4, i10);
            OpaqueKey opaqueKey2 = ComposerKt.invocation;
            composerImpl.end(false);
            MaterialThemeKt.MaterialTheme(dynamicDarkColorScheme, null, typography, ComposableLambdaKt.rememberComposableLambda(1152266073, new Function2() { // from class: com.android.compose.theme.PlatformThemeKt$PlatformTheme$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                /* JADX WARN: Type inference failed for: r0v4, types: [com.android.compose.theme.PlatformThemeKt$PlatformTheme$1$1, kotlin.jvm.internal.Lambda] */
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Composer composer2 = (Composer) obj;
                    if ((((Number) obj2).intValue() & 11) == 2) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey3 = ComposerKt.invocation;
                    ProvidedValue[] providedValueArr = {AndroidColorSchemeKt.LocalAndroidColorScheme.defaultProvidedValue$runtime_release(AndroidColorScheme.this), WindowSizeClassKt.LocalWindowSizeClass.defaultProvidedValue$runtime_release(windowSizeClass)};
                    final Function2 function22 = function2;
                    CompositionLocalKt.CompositionLocalProvider(providedValueArr, ComposableLambdaKt.rememberComposableLambda(450065433, new Function2() { // from class: com.android.compose.theme.PlatformThemeKt$PlatformTheme$1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj3, Object obj4) {
                            Composer composer3 = (Composer) obj3;
                            if ((((Number) obj4).intValue() & 11) == 2) {
                                ComposerImpl composerImpl3 = (ComposerImpl) composer3;
                                if (composerImpl3.getSkipping()) {
                                    composerImpl3.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            OpaqueKey opaqueKey4 = ComposerKt.invocation;
                            Function2.this.invoke(composer3, 0);
                            return Unit.INSTANCE;
                        }
                    }, composer2), composer2, 56);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 3072, 2);
            z2 = z3;
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.compose.theme.PlatformThemeKt$PlatformTheme$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PlatformThemeKt.PlatformTheme(z2, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
