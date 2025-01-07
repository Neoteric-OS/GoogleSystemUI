package androidx.compose.foundation.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class WindowInsetsPaddingKt {
    public static final ProvidableModifierLocal ModifierLocalConsumedWindowInsets = new ProvidableModifierLocal(new Function0() { // from class: androidx.compose.foundation.layout.WindowInsetsPaddingKt$ModifierLocalConsumedWindowInsets$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new FixedIntInsets();
        }
    });

    public static final Modifier consumeWindowInsets(Modifier modifier, final WindowInsets windowInsets) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(modifier, new Function3() { // from class: androidx.compose.foundation.layout.WindowInsetsPaddingKt$consumeWindowInsets$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(788931215);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                boolean changed = composerImpl.changed(WindowInsets.this);
                WindowInsets windowInsets2 = WindowInsets.this;
                Object rememberedValue = composerImpl.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.Empty) {
                    rememberedValue = new UnionInsetsConsumingModifier(windowInsets2);
                    composerImpl.updateRememberedValue(rememberedValue);
                }
                UnionInsetsConsumingModifier unionInsetsConsumingModifier = (UnionInsetsConsumingModifier) rememberedValue;
                composerImpl.end(false);
                return unionInsetsConsumingModifier;
            }
        });
    }

    public static final Modifier onConsumedWindowInsetsChanged(Modifier modifier, final Function1 function1) {
        Function1 function12 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(modifier, new Function3() { // from class: androidx.compose.foundation.layout.WindowInsetsPaddingKt$onConsumedWindowInsetsChanged$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(-1608161351);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                boolean changed = composerImpl.changed(Function1.this);
                Function1 function13 = Function1.this;
                Object rememberedValue = composerImpl.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.Empty) {
                    rememberedValue = new ConsumedInsetsModifier(function13);
                    composerImpl.updateRememberedValue(rememberedValue);
                }
                ConsumedInsetsModifier consumedInsetsModifier = (ConsumedInsetsModifier) rememberedValue;
                composerImpl.end(false);
                return consumedInsetsModifier;
            }
        });
    }

    public static final Modifier windowInsetsPadding(Modifier modifier, final WindowInsets windowInsets) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(modifier, new Function3() { // from class: androidx.compose.foundation.layout.WindowInsetsPaddingKt$windowInsetsPadding$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(-1415685722);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                boolean changed = composerImpl.changed(WindowInsets.this);
                WindowInsets windowInsets2 = WindowInsets.this;
                Object rememberedValue = composerImpl.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.Empty) {
                    rememberedValue = new InsetsPaddingModifier(windowInsets2);
                    composerImpl.updateRememberedValue(rememberedValue);
                }
                InsetsPaddingModifier insetsPaddingModifier = (InsetsPaddingModifier) rememberedValue;
                composerImpl.end(false);
                return insetsPaddingModifier;
            }
        });
    }
}
