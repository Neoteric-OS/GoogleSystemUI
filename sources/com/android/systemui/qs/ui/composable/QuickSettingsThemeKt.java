package com.android.systemui.qs.ui.composable;

import android.content.Context;
import android.view.ContextThemeWrapper;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class QuickSettingsThemeKt {
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.qs.ui.composable.QuickSettingsThemeKt$QuickSettingsTheme$1, kotlin.jvm.internal.Lambda] */
    public static final void QuickSettingsTheme(final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1406602596);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = AndroidCompositionLocals_androidKt.LocalContext;
            Context context = (Context) composerImpl.consume(staticProvidableCompositionLocal);
            composerImpl.startReplaceGroup(164114501);
            boolean changed = composerImpl.changed(context);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new ContextThemeWrapper(context, R.style.Theme_SystemUI_QuickSettings);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            CompositionLocalKt.CompositionLocalProvider(staticProvidableCompositionLocal.defaultProvidedValue$runtime_release((ContextThemeWrapper) rememberedValue), ComposableLambdaKt.rememberComposableLambda(-1991961124, new Function2() { // from class: com.android.systemui.qs.ui.composable.QuickSettingsThemeKt$QuickSettingsTheme$1
                {
                    super(2);
                }

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
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    Function2.this.invoke(composer2, 0);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 56);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.ui.composable.QuickSettingsThemeKt$QuickSettingsTheme$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    QuickSettingsThemeKt.QuickSettingsTheme(function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
