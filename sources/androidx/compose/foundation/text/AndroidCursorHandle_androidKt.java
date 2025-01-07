package androidx.compose.foundation.text;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.SelectionHandleAnchor;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
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
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.BlendModeColorFilter;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScopeKt$asDrawTransform$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.DpSize;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidCursorHandle_androidKt {
    public static final float CursorHandleHeight;
    public static final float CursorHandleWidth;

    static {
        float f = 25;
        CursorHandleHeight = f;
        CursorHandleWidth = (f * 2.0f) / 2.4142137f;
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.compose.foundation.text.AndroidCursorHandle_androidKt$CursorHandle$1, kotlin.jvm.internal.Lambda] */
    /* renamed from: CursorHandle-USBMPiE, reason: not valid java name */
    public static final void m151CursorHandleUSBMPiE(final OffsetProvider offsetProvider, final Modifier modifier, long j, Composer composer, final int i, final int i2) {
        int i3;
        final long j2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1776202187);
        boolean z = true;
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerImpl.changed(offsetProvider) : composerImpl.changedInstance(offsetProvider) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerImpl.changed(modifier) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            j2 = j;
            i3 |= ((i2 & 4) == 0 && composerImpl.changed(j)) ? 256 : 128;
        } else {
            j2 = j;
        }
        if ((i3 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            composerImpl.startDefaults();
            if ((i & 1) != 0 && !composerImpl.getDefaultsInvalid()) {
                composerImpl.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
            } else if ((i2 & 4) != 0) {
                i3 &= -897;
                j2 = 9205357640488583168L;
            }
            composerImpl.endDefaults();
            OpaqueKey opaqueKey = ComposerKt.invocation;
            int i4 = i3 & 14;
            if (i4 != 4 && ((i3 & 8) == 0 || !composerImpl.changedInstance(offsetProvider))) {
                z = false;
            }
            Object rememberedValue = composerImpl.rememberedValue();
            if (z || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$CursorHandle$finalModifier$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((SemanticsConfiguration) ((SemanticsPropertyReceiver) obj)).set(SelectionHandlesKt.SelectionHandleInfoKey, new SelectionHandleInfo(Handle.Cursor, OffsetProvider.this.mo156provideF1C5BW0(), SelectionHandleAnchor.Middle, true));
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final Modifier semantics = SemanticsModifierKt.semantics(modifier, false, (Function1) rememberedValue);
            AndroidSelectionHandles_androidKt.HandlePopup(offsetProvider, Alignment.Companion.TopCenter, ComposableLambdaKt.rememberComposableLambda(-1653527038, new Function2() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$CursorHandle$1
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
                    if (j2 != 9205357640488583168L) {
                        ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                        composerImpl3.startReplaceGroup(1828931592);
                        Modifier m112requiredSizeInqDBjuR0$default = SizeKt.m112requiredSizeInqDBjuR0$default(semantics, DpSize.m672getWidthD9Ej5fM(j2), DpSize.m671getHeightD9Ej5fM(j2), 0.0f, 0.0f, 12);
                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopCenter, false);
                        int i5 = composerImpl3.compoundKeyHash;
                        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl3, m112requiredSizeInqDBjuR0$default);
                        ComposeUiNode.Companion.getClass();
                        Function0 function0 = ComposeUiNode.Companion.Constructor;
                        composerImpl3.startReusableNode();
                        if (composerImpl3.inserting) {
                            composerImpl3.createNode(function0);
                        } else {
                            composerImpl3.useNode();
                        }
                        Updater.m259setimpl(composerImpl3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                        Updater.m259setimpl(composerImpl3, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                        if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(i5))) {
                            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl3, i5, function2);
                        }
                        Updater.m259setimpl(composerImpl3, materializeModifier, ComposeUiNode.Companion.SetModifier);
                        AndroidCursorHandle_androidKt.access$DefaultCursorHandle(null, composerImpl3, 0, 1);
                        composerImpl3.end(true);
                        composerImpl3.end(false);
                    } else {
                        ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                        composerImpl4.startReplaceGroup(1829298756);
                        AndroidCursorHandle_androidKt.access$DefaultCursorHandle(semantics, composerImpl4, 0, 0);
                        composerImpl4.end(false);
                    }
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, i4 | 432);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final long j3 = j2;
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$CursorHandle$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    AndroidCursorHandle_androidKt.m151CursorHandleUSBMPiE(OffsetProvider.this, modifier, j3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$DefaultCursorHandle(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(694251107);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i3 & 3) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.Companion.$$INSTANCE;
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier m114sizeVpY3zN4 = SizeKt.m114sizeVpY3zN4(modifier, CursorHandleWidth, CursorHandleHeight);
            AndroidCursorHandle_androidKt$drawCursorHandle$1 androidCursorHandle_androidKt$drawCursorHandle$1 = new Function3() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$drawCursorHandle$1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Modifier modifier2 = (Modifier) obj;
                    ((Number) obj3).intValue();
                    ComposerImpl composerImpl2 = (ComposerImpl) ((Composer) obj2);
                    composerImpl2.startReplaceGroup(-2126899193);
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    final long j = ((TextSelectionColors) composerImpl2.consume(TextSelectionColorsKt.LocalTextSelectionColors)).handleColor;
                    Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                    boolean changed = composerImpl2.changed(j);
                    Object rememberedValue = composerImpl2.rememberedValue();
                    if (changed || rememberedValue == Composer.Companion.Empty) {
                        rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$drawCursorHandle$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                CacheDrawScope cacheDrawScope = (CacheDrawScope) obj4;
                                final float intBitsToFloat = Float.intBitsToFloat((int) (cacheDrawScope.cacheParams.mo279getSizeNHjbRc() >> 32)) / 2.0f;
                                final ImageBitmap createHandleImage = AndroidSelectionHandles_androidKt.createHandleImage(cacheDrawScope, intBitsToFloat);
                                final BlendModeColorFilter m370tintxETnrds$default = ColorFilter.Companion.m370tintxETnrds$default(j);
                                return cacheDrawScope.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$drawCursorHandle$1$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj5) {
                                        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) ((ContentDrawScope) obj5);
                                        layoutNodeDrawScope.drawContent();
                                        float f = intBitsToFloat;
                                        ImageBitmap imageBitmap = createHandleImage;
                                        ColorFilter colorFilter = m370tintxETnrds$default;
                                        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                                        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
                                        long m418getSizeNHjbRc = canvasDrawScope$drawContext$1.m418getSizeNHjbRc();
                                        canvasDrawScope$drawContext$1.getCanvas().save();
                                        try {
                                            CanvasDrawScopeKt$asDrawTransform$1 canvasDrawScopeKt$asDrawTransform$1 = canvasDrawScope$drawContext$1.transform;
                                            canvasDrawScopeKt$asDrawTransform$1.translate(f, 0.0f);
                                            Canvas canvas = canvasDrawScopeKt$asDrawTransform$1.$this_asDrawTransform.getCanvas();
                                            int i5 = (int) 0;
                                            canvas.translate(Float.intBitsToFloat(i5), Float.intBitsToFloat(i5));
                                            canvas.rotate();
                                            canvas.translate(-Float.intBitsToFloat(i5), -Float.intBitsToFloat(i5));
                                            canvasDrawScope.m411drawImagegbVJVH8(imageBitmap, colorFilter);
                                            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
                                            return Unit.INSTANCE;
                                        } catch (Throwable th) {
                                            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
                                            throw th;
                                        }
                                    }
                                });
                            }
                        };
                        composerImpl2.updateRememberedValue(rememberedValue);
                    }
                    Modifier then = modifier2.then(DrawModifierKt.drawWithCache(companion, (Function1) rememberedValue));
                    composerImpl2.end(false);
                    return then;
                }
            };
            Function1 function1 = InspectableValueKt.NoInspectorInfo;
            SpacerKt.Spacer(composerImpl, ComposedModifierKt.composed(m114sizeVpY3zN4, androidCursorHandle_androidKt$drawCursorHandle$1));
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$DefaultCursorHandle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    AndroidCursorHandle_androidKt.access$DefaultCursorHandle(Modifier.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
