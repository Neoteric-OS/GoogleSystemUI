package androidx.compose.foundation;

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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ClickableKt {
    /* renamed from: clickable-O2vRcR0, reason: not valid java name */
    public static final Modifier m30clickableO2vRcR0(Modifier modifier, MutableInteractionSource mutableInteractionSource, final Indication indication, final boolean z, final String str, final Role role, final Function0 function0) {
        Modifier composed;
        if (indication instanceof IndicationNodeFactory) {
            composed = new ClickableElement(mutableInteractionSource, (IndicationNodeFactory) indication, z, str, role, function0);
        } else if (indication == null) {
            composed = new ClickableElement(mutableInteractionSource, null, z, str, role, function0);
        } else {
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            if (mutableInteractionSource != null) {
                composed = IndicationKt.indication(companion, mutableInteractionSource, indication).then(new ClickableElement(mutableInteractionSource, null, z, str, role, function0));
            } else {
                Function3 function3 = new Function3() { // from class: androidx.compose.foundation.ClickableKt$clickable-O2vRcR0$$inlined$clickableWithIndicationIfNeeded$1
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
                        Modifier then = IndicationKt.indication(Modifier.Companion.$$INSTANCE, mutableInteractionSource2, Indication.this).then(new ClickableElement(mutableInteractionSource2, null, z, str, role, function0));
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

    /* renamed from: clickable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m31clickableO2vRcR0$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, Role role, Function0 function0, int i) {
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            role = null;
        }
        return m30clickableO2vRcR0(modifier, mutableInteractionSource, indicationNodeFactory, z2, null, role, function0);
    }

    /* renamed from: clickable-XHw0xAI$default, reason: not valid java name */
    public static Modifier m32clickableXHw0xAI$default(Modifier modifier, final boolean z, final String str, final Function0 function0, int i) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(modifier, new Function3() { // from class: androidx.compose.foundation.ClickableKt$clickable$2
            final /* synthetic */ Role $role = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                MutableInteractionSource mutableInteractionSource;
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(-756081143);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                Indication indication = (Indication) composerImpl.consume(IndicationKt.LocalIndication);
                if (indication instanceof IndicationNodeFactory) {
                    mutableInteractionSource = null;
                } else {
                    Object rememberedValue = composerImpl.rememberedValue();
                    if (rememberedValue == Composer.Companion.Empty) {
                        rememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerImpl.updateRememberedValue(rememberedValue);
                    }
                    mutableInteractionSource = (MutableInteractionSource) rememberedValue;
                }
                Modifier m30clickableO2vRcR0 = ClickableKt.m30clickableO2vRcR0(Modifier.Companion.$$INSTANCE, mutableInteractionSource, indication, z, str, this.$role, function0);
                composerImpl.end(false);
                return m30clickableO2vRcR0;
            }
        });
    }

    /* renamed from: combinedClickable-auXiCPI, reason: not valid java name */
    public static final Modifier m33combinedClickableauXiCPI(Modifier modifier, MutableInteractionSource mutableInteractionSource, final Indication indication, final boolean z, final String str, final Role role, final String str2, final Function0 function0, final Function0 function02, final boolean z2, final Function0 function03) {
        Modifier composed;
        if (indication instanceof IndicationNodeFactory) {
            composed = new CombinedClickableElement((IndicationNodeFactory) indication, mutableInteractionSource, role, str, str2, function03, function0, function02, z, z2);
        } else if (indication == null) {
            composed = new CombinedClickableElement(null, mutableInteractionSource, role, str, str2, function03, function0, function02, z, z2);
        } else {
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            if (mutableInteractionSource != null) {
                composed = IndicationKt.indication(companion, mutableInteractionSource, indication).then(new CombinedClickableElement(null, mutableInteractionSource, role, str, str2, function03, function0, function02, z, z2));
            } else {
                Function3 function3 = new Function3() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-auXiCPI$$inlined$clickableWithIndicationIfNeeded$1
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
                        Modifier indication2 = IndicationKt.indication(Modifier.Companion.$$INSTANCE, mutableInteractionSource2, Indication.this);
                        boolean z3 = z;
                        String str3 = str;
                        Modifier then = indication2.then(new CombinedClickableElement(null, mutableInteractionSource2, role, str3, str2, function03, function0, function02, z3, z2));
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

    /* renamed from: combinedClickable-f5TDLPQ$default, reason: not valid java name */
    public static Modifier m35combinedClickablef5TDLPQ$default(Modifier modifier, final String str, final String str2, final Function0 function0, final Function0 function02, int i) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(modifier, new Function3() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$2
            final /* synthetic */ boolean $enabled = true;
            final /* synthetic */ Role $role = null;
            final /* synthetic */ Function0 $onDoubleClick = null;
            final /* synthetic */ boolean $hapticFeedbackEnabled = true;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                MutableInteractionSource mutableInteractionSource;
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(-1534186401);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                Indication indication = (Indication) composerImpl.consume(IndicationKt.LocalIndication);
                if (indication instanceof IndicationNodeFactory) {
                    mutableInteractionSource = null;
                } else {
                    Object rememberedValue = composerImpl.rememberedValue();
                    if (rememberedValue == Composer.Companion.Empty) {
                        rememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerImpl.updateRememberedValue(rememberedValue);
                    }
                    mutableInteractionSource = (MutableInteractionSource) rememberedValue;
                }
                Modifier m33combinedClickableauXiCPI = ClickableKt.m33combinedClickableauXiCPI(Modifier.Companion.$$INSTANCE, mutableInteractionSource, indication, this.$enabled, str, this.$role, str2, function0, this.$onDoubleClick, this.$hapticFeedbackEnabled, function02);
                composerImpl.end(false);
                return m33combinedClickableauXiCPI;
            }
        });
    }
}
