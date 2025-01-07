package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.DpSize;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class IconButtonKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FilledIconButton(final int r18, final int r19, androidx.compose.foundation.interaction.MutableInteractionSource r20, androidx.compose.material3.IconButtonColors r21, androidx.compose.runtime.Composer r22, androidx.compose.ui.Modifier r23, androidx.compose.ui.graphics.Shape r24, final kotlin.jvm.functions.Function0 r25, final kotlin.jvm.functions.Function2 r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt.FilledIconButton(int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material3.IconButtonColors, androidx.compose.runtime.Composer, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void IconButton(final int r28, final int r29, androidx.compose.foundation.interaction.MutableInteractionSource r30, androidx.compose.material3.IconButtonColors r31, androidx.compose.runtime.Composer r32, androidx.compose.ui.Modifier r33, androidx.compose.ui.graphics.Shape r34, final kotlin.jvm.functions.Function0 r35, final kotlin.jvm.functions.Function2 r36, boolean r37) {
        /*
            Method dump skipped, instructions count: 662
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt.IconButton(int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material3.IconButtonColors, androidx.compose.runtime.Composer, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, boolean):void");
    }

    /* JADX WARN: Type inference failed for: r5v6, types: [androidx.compose.material3.IconButtonKt$SurfaceIconButton$2, kotlin.jvm.internal.Lambda] */
    public static final void SurfaceIconButton(final Function0 function0, final Modifier modifier, final boolean z, final Shape shape, final IconButtonColors iconButtonColors, final BorderStroke borderStroke, final MutableInteractionSource mutableInteractionSource, final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(1142501472);
        if ((i & 6) == 0) {
            i2 = (composerImpl2.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl2.changed(modifier) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl2.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerImpl2.changed(shape) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerImpl2.changed(iconButtonColors) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerImpl2.changed(borderStroke) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerImpl2.changed(mutableInteractionSource) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerImpl2.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ((4793491 & i2) == 4793490 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            int i3 = i2 & 8078;
            int i4 = i2 << 9;
            composerImpl = composerImpl2;
            SurfaceKt.m233Surfaceo_FOJdg(function0, SemanticsModifierKt.semantics(modifier, false, new Function1() { // from class: androidx.compose.material3.IconButtonKt$SurfaceIconButton$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    SemanticsPropertiesKt.m577setRolekuIjeqM((SemanticsPropertyReceiver) obj, 0);
                    return Unit.INSTANCE;
                }
            }), z, shape, z ? iconButtonColors.containerColor : iconButtonColors.disabledContainerColor, z ? iconButtonColors.contentColor : iconButtonColors.disabledContentColor, 0.0f, borderStroke, mutableInteractionSource, ComposableLambdaKt.rememberComposableLambda(524891765, new Function2() { // from class: androidx.compose.material3.IconButtonKt$SurfaceIconButton$2
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Composer composer2 = (Composer) obj;
                    if ((((Number) obj2).intValue() & 3) == 2) {
                        ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                        if (composerImpl3.getSkipping()) {
                            composerImpl3.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                    long m211smallContainerSizeNwlBFI$default = IconButtonDefaults.m211smallContainerSizeNwlBFI$default();
                    FillElement fillElement = SizeKt.FillWholeMaxWidth;
                    Modifier m114sizeVpY3zN4 = SizeKt.m114sizeVpY3zN4(companion, DpSize.m672getWidthD9Ej5fM(m211smallContainerSizeNwlBFI$default), DpSize.m671getHeightD9Ej5fM(m211smallContainerSizeNwlBFI$default));
                    BiasAlignment biasAlignment = Alignment.Companion.Center;
                    Function2 function22 = Function2.this;
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                    ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                    PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl4.currentCompositionLocalScope();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, m114sizeVpY3zN4);
                    ComposeUiNode.Companion.getClass();
                    Function0 function02 = ComposeUiNode.Companion.Constructor;
                    composerImpl4.startReusableNode();
                    if (composerImpl4.inserting) {
                        composerImpl4.createNode(function02);
                    } else {
                        composerImpl4.useNode();
                    }
                    Updater.m259setimpl(composer2, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                    Updater.m259setimpl(composer2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                    Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                    if (composerImpl4.inserting || !Intrinsics.areEqual(composerImpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl4, currentCompositeKeyHash, function23);
                    }
                    Updater.m259setimpl(composer2, materializeModifier, ComposeUiNode.Companion.SetModifier);
                    function22.invoke(composer2, 0);
                    composerImpl4.end(true);
                    return Unit.INSTANCE;
                }
            }, composerImpl2), composerImpl, i3 | (234881024 & i4) | (i4 & 1879048192), 192);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.IconButtonKt$SurfaceIconButton$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    IconButtonKt.SurfaceIconButton(Function0.this, modifier, z, shape, iconButtonColors, borderStroke, mutableInteractionSource, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
