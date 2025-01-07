package androidx.compose.foundation.selection;

import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.Role;
import com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperIndication;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SelectableKt {
    /* renamed from: selectable-O2vRcR0$default, reason: not valid java name */
    public static Modifier m145selectableO2vRcR0$default(Modifier modifier, final boolean z, MutableInteractionSource mutableInteractionSource, final ShortcutHelperIndication shortcutHelperIndication, boolean z2, final Function0 function0, int i) {
        Modifier composed;
        if ((i & 8) != 0) {
            z2 = true;
        }
        final boolean z3 = z2;
        if (shortcutHelperIndication != null) {
            composed = new SelectableElement(z, mutableInteractionSource, shortcutHelperIndication, z3, null, function0);
        } else if (shortcutHelperIndication == null) {
            composed = new SelectableElement(z, mutableInteractionSource, null, z3, null, function0);
        } else {
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            if (mutableInteractionSource != null) {
                composed = IndicationKt.indication(companion, mutableInteractionSource, shortcutHelperIndication).then(new SelectableElement(z, mutableInteractionSource, null, z3, null, function0));
            } else {
                Function3 function3 = new Function3() { // from class: androidx.compose.foundation.selection.SelectableKt$selectable-O2vRcR0$$inlined$clickableWithIndicationIfNeeded$1
                    final /* synthetic */ Role $role$inlined = null;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        ((Number) obj3).intValue();
                        ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                        composerImpl.startReplaceGroup(-1525724089);
                        OpaqueKey opaqueKey = ComposerKt.invocation;
                        Object rememberedValue = composerImpl.rememberedValue();
                        if (rememberedValue == Composer.Companion.Empty) {
                            rememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerImpl.updateRememberedValue(rememberedValue);
                        }
                        MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) rememberedValue;
                        Modifier then = IndicationKt.indication(Modifier.Companion.$$INSTANCE, mutableInteractionSource2, shortcutHelperIndication).then(new SelectableElement(z, mutableInteractionSource2, null, z3, this.$role$inlined, function0));
                        composerImpl.end(false);
                        return then;
                    }
                };
                Function1 function1 = InspectableValueKt.NoInspectorInfo;
                composed = ComposedModifierKt.composed(companion, function3);
            }
        }
        return modifier.then(composed);
    }
}
