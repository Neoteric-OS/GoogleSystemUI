package androidx.compose.foundation.text.selection;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.AbsoluteAlignment;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAbsoluteAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidImageBitmap;
import androidx.compose.ui.graphics.BlendModeColorFilter;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidSelectionHandles_androidKt {
    public static final void HandlePopup(final OffsetProvider offsetProvider, final Alignment alignment, final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(476043083);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerImpl.changed(offsetProvider) : composerImpl.changedInstance(offsetProvider) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changed(alignment) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            boolean z = true;
            boolean z2 = (i2 & 112) == 32;
            if ((i2 & 14) != 4 && ((i2 & 8) == 0 || !composerImpl.changed(offsetProvider))) {
                z = false;
            }
            boolean z3 = z2 | z;
            Object rememberedValue = composerImpl.rememberedValue();
            if (z3 || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new HandlePositionProvider(alignment, offsetProvider);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            AndroidPopup_androidKt.Popup((HandlePositionProvider) rememberedValue, null, new PopupProperties(false, SecureFlagPolicy.Inherit, false), function2, composerImpl, ((i2 << 3) & 7168) | 384, 2);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$HandlePopup$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    AndroidSelectionHandles_androidKt.HandlePopup(OffsetProvider.this, alignment, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r4v24, types: [androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1, kotlin.jvm.internal.Lambda] */
    /* renamed from: SelectionHandle-pzduO1o, reason: not valid java name */
    public static final void m181SelectionHandlepzduO1o(final OffsetProvider offsetProvider, final boolean z, final ResolvedTextDirection resolvedTextDirection, final boolean z2, long j, final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        long j2;
        final boolean z3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-843755800);
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
            i3 |= composerImpl.changed(z) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerImpl.changed(resolvedTextDirection) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerImpl.changed(z2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            j2 = j;
            i3 |= ((i2 & 16) == 0 && composerImpl.changed(j2)) ? 16384 : 8192;
        } else {
            j2 = j;
        }
        if ((i2 & 32) != 0) {
            i3 |= 196608;
        } else if ((i & 196608) == 0) {
            i3 |= composerImpl.changed(modifier) ? 131072 : 65536;
        }
        if ((74899 & i3) == 74898 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            composerImpl.startDefaults();
            if ((i & 1) != 0 && !composerImpl.getDefaultsInvalid()) {
                composerImpl.skipToGroupEnd();
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                }
            } else if ((i2 & 16) != 0) {
                i3 &= -57345;
                j2 = 9205357640488583168L;
            }
            composerImpl.endDefaults();
            OpaqueKey opaqueKey = ComposerKt.invocation;
            ResolvedTextDirection resolvedTextDirection2 = ResolvedTextDirection.Rtl;
            ResolvedTextDirection resolvedTextDirection3 = ResolvedTextDirection.Ltr;
            if (z) {
                float f = SelectionHandlesKt.HandleWidth;
                z3 = (resolvedTextDirection == resolvedTextDirection3 && !z2) || (resolvedTextDirection == resolvedTextDirection2 && z2);
            } else {
                float f2 = SelectionHandlesKt.HandleWidth;
                z3 = !((resolvedTextDirection == resolvedTextDirection3 && !z2) || (resolvedTextDirection == resolvedTextDirection2 && z2));
            }
            BiasAbsoluteAlignment biasAbsoluteAlignment = z3 ? AbsoluteAlignment.TopRight : AbsoluteAlignment.TopLeft;
            int i4 = i3 & 14;
            boolean changed = (i4 == 4 || ((i3 & 8) != 0 && composerImpl.changedInstance(offsetProvider))) | ((i3 & 112) == 32) | composerImpl.changed(z3);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$semanticsModifier$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                        long mo156provideF1C5BW0 = OffsetProvider.this.mo156provideF1C5BW0();
                        ((SemanticsConfiguration) semanticsPropertyReceiver).set(SelectionHandlesKt.SelectionHandleInfoKey, new SelectionHandleInfo(z ? Handle.SelectionStart : Handle.SelectionEnd, mo156provideF1C5BW0, z3 ? SelectionHandleAnchor.Left : SelectionHandleAnchor.Right, (9223372034707292159L & mo156provideF1C5BW0) != 9205357640488583168L));
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final Modifier semantics = SemanticsModifierKt.semantics(modifier, false, (Function1) rememberedValue);
            final ViewConfiguration viewConfiguration = (ViewConfiguration) composerImpl.consume(CompositionLocalsKt.LocalViewConfiguration);
            final boolean z4 = z3;
            final long j3 = j2;
            HandlePopup(offsetProvider, biasAbsoluteAlignment, ComposableLambdaKt.rememberComposableLambda(280174801, new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                /* JADX WARN: Type inference failed for: r6v0, types: [androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1, kotlin.jvm.internal.Lambda] */
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
                    ProvidedValue defaultProvidedValue$runtime_release = CompositionLocalsKt.LocalViewConfiguration.defaultProvidedValue$runtime_release(ViewConfiguration.this);
                    final long j4 = j3;
                    final boolean z5 = z4;
                    final Modifier modifier2 = semantics;
                    final OffsetProvider offsetProvider2 = offsetProvider;
                    CompositionLocalKt.CompositionLocalProvider(defaultProvidedValue$runtime_release, ComposableLambdaKt.rememberComposableLambda(-1426434671, new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj3, Object obj4) {
                            Composer composer3 = (Composer) obj3;
                            if ((((Number) obj4).intValue() & 3) == 2) {
                                ComposerImpl composerImpl3 = (ComposerImpl) composer3;
                                if (composerImpl3.getSkipping()) {
                                    composerImpl3.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            OpaqueKey opaqueKey3 = ComposerKt.invocation;
                            long j5 = j4;
                            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
                            if (j5 != 9205357640488583168L) {
                                ComposerImpl composerImpl4 = (ComposerImpl) composer3;
                                composerImpl4.startReplaceGroup(-837649504);
                                Arrangement.Horizontal horizontal = z5 ? Arrangement.Absolute.Right : Arrangement.Absolute.Left;
                                Modifier m112requiredSizeInqDBjuR0$default = SizeKt.m112requiredSizeInqDBjuR0$default(modifier2, DpSize.m672getWidthD9Ej5fM(j4), DpSize.m671getHeightD9Ej5fM(j4), 0.0f, 0.0f, 12);
                                final OffsetProvider offsetProvider3 = offsetProvider2;
                                boolean z6 = z5;
                                RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(horizontal, Alignment.Companion.Top, composerImpl4, 0);
                                int i5 = composerImpl4.compoundKeyHash;
                                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl4.currentCompositionLocalScope();
                                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl4, m112requiredSizeInqDBjuR0$default);
                                ComposeUiNode.Companion.getClass();
                                Function0 function0 = ComposeUiNode.Companion.Constructor;
                                composerImpl4.startReusableNode();
                                if (composerImpl4.inserting) {
                                    composerImpl4.createNode(function0);
                                } else {
                                    composerImpl4.useNode();
                                }
                                Updater.m259setimpl(composerImpl4, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                                Updater.m259setimpl(composerImpl4, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                                Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                                if (composerImpl4.inserting || !Intrinsics.areEqual(composerImpl4.rememberedValue(), Integer.valueOf(i5))) {
                                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl4, i5, function2);
                                }
                                Updater.m259setimpl(composerImpl4, materializeModifier, ComposeUiNode.Companion.SetModifier);
                                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                                boolean changedInstance = composerImpl4.changedInstance(offsetProvider3);
                                Object rememberedValue2 = composerImpl4.rememberedValue();
                                if (changedInstance || rememberedValue2 == composer$Companion$Empty$1) {
                                    rememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1$1$1$1
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return Boolean.valueOf((OffsetProvider.this.mo156provideF1C5BW0() & 9223372034707292159L) != 9205357640488583168L);
                                        }
                                    };
                                    composerImpl4.updateRememberedValue(rememberedValue2);
                                }
                                AndroidSelectionHandles_androidKt.SelectionHandleIcon(companion, (Function0) rememberedValue2, z6, composerImpl4, 6);
                                composerImpl4.end(true);
                                composerImpl4.end(false);
                            } else {
                                ComposerImpl composerImpl5 = (ComposerImpl) composer3;
                                composerImpl5.startReplaceGroup(-836720496);
                                Modifier modifier3 = modifier2;
                                boolean changedInstance2 = composerImpl5.changedInstance(offsetProvider2);
                                final OffsetProvider offsetProvider4 = offsetProvider2;
                                Object rememberedValue3 = composerImpl5.rememberedValue();
                                if (changedInstance2 || rememberedValue3 == composer$Companion$Empty$1) {
                                    rememberedValue3 = new Function0() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1$2$1
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return Boolean.valueOf((OffsetProvider.this.mo156provideF1C5BW0() & 9223372034707292159L) != 9205357640488583168L);
                                        }
                                    };
                                    composerImpl5.updateRememberedValue(rememberedValue3);
                                }
                                AndroidSelectionHandles_androidKt.SelectionHandleIcon(modifier3, (Function0) rememberedValue3, z5, composerImpl5, 0);
                                composerImpl5.end(false);
                            }
                            return Unit.INSTANCE;
                        }
                    }, composer2), composer2, 56);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, i4 | 384);
        }
        final long j4 = j2;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    AndroidSelectionHandles_androidKt.m181SelectionHandlepzduO1o(OffsetProvider.this, z, resolvedTextDirection, z2, j4, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void SelectionHandleIcon(final Modifier modifier, final Function0 function0, final boolean z, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(2111672474);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changed(z) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier m114sizeVpY3zN4 = SizeKt.m114sizeVpY3zN4(modifier, SelectionHandlesKt.HandleWidth, SelectionHandlesKt.HandleHeight);
            Function3 function3 = new Function3() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Modifier modifier2 = (Modifier) obj;
                    ((Number) obj3).intValue();
                    ComposerImpl composerImpl2 = (ComposerImpl) ((Composer) obj2);
                    composerImpl2.startReplaceGroup(-196777734);
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    final long j = ((TextSelectionColors) composerImpl2.consume(TextSelectionColorsKt.LocalTextSelectionColors)).handleColor;
                    boolean changed = composerImpl2.changed(j) | composerImpl2.changed(Function0.this) | composerImpl2.changed(z);
                    final Function0 function02 = Function0.this;
                    final boolean z2 = z;
                    Object rememberedValue = composerImpl2.rememberedValue();
                    if (changed || rememberedValue == Composer.Companion.Empty) {
                        rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                CacheDrawScope cacheDrawScope = (CacheDrawScope) obj4;
                                final ImageBitmap createHandleImage = AndroidSelectionHandles_androidKt.createHandleImage(cacheDrawScope, Float.intBitsToFloat((int) (cacheDrawScope.cacheParams.mo279getSizeNHjbRc() >> 32)) / 2.0f);
                                final BlendModeColorFilter m370tintxETnrds$default = ColorFilter.Companion.m370tintxETnrds$default(j);
                                final Function0 function03 = function02;
                                final boolean z3 = z2;
                                return cacheDrawScope.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj5) {
                                        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) ((ContentDrawScope) obj5);
                                        layoutNodeDrawScope.drawContent();
                                        if (((Boolean) Function0.this.invoke()).booleanValue()) {
                                            boolean z4 = z3;
                                            CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                                            if (z4) {
                                                ImageBitmap imageBitmap = createHandleImage;
                                                ColorFilter colorFilter = m370tintxETnrds$default;
                                                long mo431getCenterF1C5BW0 = canvasDrawScope.mo431getCenterF1C5BW0();
                                                CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
                                                long m418getSizeNHjbRc = canvasDrawScope$drawContext$1.m418getSizeNHjbRc();
                                                canvasDrawScope$drawContext$1.getCanvas().save();
                                                try {
                                                    canvasDrawScope$drawContext$1.transform.m421scale0AR0LA0(-1.0f, 1.0f, mo431getCenterF1C5BW0);
                                                    canvasDrawScope.m411drawImagegbVJVH8(imageBitmap, colorFilter);
                                                } finally {
                                                    BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
                                                }
                                            } else {
                                                canvasDrawScope.m411drawImagegbVJVH8(createHandleImage, m370tintxETnrds$default);
                                            }
                                        }
                                        return Unit.INSTANCE;
                                    }
                                });
                            }
                        };
                        composerImpl2.updateRememberedValue(rememberedValue);
                    }
                    Modifier drawWithCache = DrawModifierKt.drawWithCache(modifier2, (Function1) rememberedValue);
                    composerImpl2.end(false);
                    return drawWithCache;
                }
            };
            Function1 function1 = InspectableValueKt.NoInspectorInfo;
            SpacerKt.Spacer(composerImpl, ComposedModifierKt.composed(m114sizeVpY3zN4, function3));
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandleIcon$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    AndroidSelectionHandles_androidKt.SelectionHandleIcon(Modifier.this, function0, z, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final ImageBitmap createHandleImage(CacheDrawScope cacheDrawScope, float f) {
        CanvasDrawScope canvasDrawScope;
        int ceil = ((int) Math.ceil(f)) * 2;
        AndroidImageBitmap androidImageBitmap = HandleImageCache.imageBitmap;
        AndroidCanvas androidCanvas = HandleImageCache.canvas;
        CanvasDrawScope canvasDrawScope2 = HandleImageCache.canvasDrawScope;
        if (androidImageBitmap == null || androidCanvas == null || ceil > androidImageBitmap.bitmap.getWidth() || ceil > androidImageBitmap.bitmap.getHeight()) {
            androidImageBitmap = ImageBitmapKt.m378ImageBitmapx__hDU$default(ceil, ceil, 1);
            HandleImageCache.imageBitmap = androidImageBitmap;
            androidCanvas = CanvasKt.Canvas(androidImageBitmap);
            HandleImageCache.canvas = androidCanvas;
        }
        AndroidImageBitmap androidImageBitmap2 = androidImageBitmap;
        AndroidCanvas androidCanvas2 = androidCanvas;
        if (canvasDrawScope2 == null) {
            canvasDrawScope = new CanvasDrawScope();
            HandleImageCache.canvasDrawScope = canvasDrawScope;
        } else {
            canvasDrawScope = canvasDrawScope2;
        }
        LayoutDirection layoutDirection = cacheDrawScope.cacheParams.getLayoutDirection();
        float width = androidImageBitmap2.bitmap.getWidth();
        float height = androidImageBitmap2.bitmap.getHeight();
        long floatToRawIntBits = (Float.floatToRawIntBits(height) & 4294967295L) | (Float.floatToRawIntBits(width) << 32);
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope.drawParams;
        Density density = drawParams.density;
        LayoutDirection layoutDirection2 = drawParams.layoutDirection;
        Canvas canvas = drawParams.canvas;
        long j = drawParams.size;
        drawParams.density = cacheDrawScope;
        drawParams.layoutDirection = layoutDirection;
        drawParams.canvas = androidCanvas2;
        drawParams.size = floatToRawIntBits;
        androidCanvas2.save();
        r10.mo415drawRectnJ9OG0(Color.Black, 0L, (r19 & 4) != 0 ? DrawScope.m430offsetSizePENXr5M(canvasDrawScope.mo432getSizeNHjbRc(), 0L) : canvasDrawScope.mo432getSizeNHjbRc(), (r19 & 8) != 0 ? 1.0f : 0.0f, (r19 & 32) != 0 ? null : null, (r19 & 64) != 0 ? 3 : 0);
        r10.mo415drawRectnJ9OG0(ColorKt.Color(4278190080L), 0L, (r19 & 4) != 0 ? DrawScope.m430offsetSizePENXr5M(canvasDrawScope.mo432getSizeNHjbRc(), 0L) : (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(f) & 4294967295L), (r19 & 8) != 0 ? 1.0f : 0.0f, (r19 & 32) != 0 ? null : null, (r19 & 64) != 0 ? 3 : 0);
        DrawScope.m422drawCircleVaOC9Bg$default(canvasDrawScope, ColorKt.Color(4278190080L), f, (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(f) & 4294967295L), 0.0f, 120);
        androidCanvas2.restore();
        drawParams.density = density;
        drawParams.layoutDirection = layoutDirection2;
        drawParams.canvas = canvas;
        drawParams.size = j;
        return androidImageBitmap2;
    }
}
