package com.android.compose.animation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewParent;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.ComposeView;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import com.android.systemui.animation.TransitionAnimator;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ExpandableKt {
    /* renamed from: AnimatedContentInOverlay-mxx1QeM, reason: not valid java name */
    public static final void m723AnimatedContentInOverlaymxx1QeM(final long j, final long j2, final State state, final ViewGroupOverlay viewGroupOverlay, final ExpandableControllerImpl expandableControllerImpl, final Function3 function3, final View view, final Function1 function1, final Density density, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(536668615);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        CompositionContext rememberCompositionContext = ComposablesKt.rememberCompositionContext(composerImpl);
        Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        composerImpl.startReplaceGroup(1360026613);
        boolean changed = composerImpl.changed(context) | ((((i & 234881024) ^ 100663296) > 67108864 && composerImpl.changed(density)) || (i & 100663296) == 67108864);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            final float intBitsToFloat = Float.intBitsToFloat((int) (j2 >> 32));
            final float intBitsToFloat2 = Float.intBitsToFloat((int) (4294967295L & j2));
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            long mo49toDpSizekrfVVM = density.mo49toDpSizekrfVVM(j2);
            FillElement fillElement = SizeKt.FillWholeMaxWidth;
            final Modifier drawWithContent = DrawModifierKt.drawWithContent(SizeKt.m111requiredSizeVpY3zN4(companion, DpSize.m672getWidthD9Ej5fM(mo49toDpSizekrfVVM), DpSize.m671getHeightD9Ej5fM(mo49toDpSizekrfVVM)), new Function1() { // from class: com.android.compose.animation.ExpandableKt$AnimatedContentInOverlay$composeViewInOverlay$1$contentModifier$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ContentDrawScope contentDrawScope = (ContentDrawScope) obj;
                    if (((TransitionAnimator.State) State.this.getValue()) != null) {
                        float min = Math.min(intBitsToFloat == 0.0f ? 1.0f : r0.getWidth() / intBitsToFloat, intBitsToFloat2 != 0.0f ? r0.getHeight() / intBitsToFloat2 : 1.0f);
                        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
                        long mo431getCenterF1C5BW0 = layoutNodeDrawScope.canvasDrawScope.mo431getCenterF1C5BW0();
                        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = layoutNodeDrawScope.canvasDrawScope.drawContext;
                        long m418getSizeNHjbRc = canvasDrawScope$drawContext$1.m418getSizeNHjbRc();
                        canvasDrawScope$drawContext$1.getCanvas().save();
                        try {
                            canvasDrawScope$drawContext$1.transform.m421scale0AR0LA0(min, min, mo431getCenterF1C5BW0);
                            layoutNodeDrawScope.drawContent();
                        } finally {
                            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
                        }
                    }
                    return Unit.INSTANCE;
                }
            });
            ComposeView composeView = new ComposeView(context, null, 0, 6, null);
            composeView.setContent$1(new ComposableLambdaImpl(-1116407689, true, new Function2() { // from class: com.android.compose.animation.ExpandableKt$AnimatedContentInOverlay$composeViewInOverlay$1$composeView$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    FillElement fillElement2 = SizeKt.FillWholeMaxSize;
                    final State state2 = State.this;
                    final long j3 = j;
                    final ExpandableControllerImpl expandableControllerImpl2 = expandableControllerImpl;
                    Modifier drawWithContent2 = DrawModifierKt.drawWithContent(fillElement2, new Function1() { // from class: com.android.compose.animation.ExpandableKt$AnimatedContentInOverlay$composeViewInOverlay$1$composeView$1$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            ContentDrawScope contentDrawScope = (ContentDrawScope) obj3;
                            TransitionAnimator.State state3 = (TransitionAnimator.State) State.this.getValue();
                            if (state3 != null && state3.visible) {
                                long j4 = j3;
                                BorderStroke borderStroke = expandableControllerImpl2.borderStroke;
                                float f = state3.topCornerRadius;
                                float f2 = state3.bottomCornerRadius;
                                if (f == f2) {
                                    long floatToRawIntBits = (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(f) & 4294967295L);
                                    DrawScope.m429drawRoundRectuAw5IA$default(contentDrawScope, j4, 0L, 0L, floatToRawIntBits, null, 0.0f, 246);
                                    if (borderStroke != null) {
                                        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
                                        float mo51toPx0680j_4 = layoutNodeDrawScope.mo51toPx0680j_4(borderStroke.width);
                                        Stroke stroke = new Stroke(mo51toPx0680j_4, 0.0f, 0, 0, 30);
                                        long floatToRawIntBits2 = (Float.floatToRawIntBits(r8) << 32) | (Float.floatToRawIntBits(r8) & 4294967295L);
                                        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                                        float intBitsToFloat3 = Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() >> 32)) - mo51toPx0680j_4;
                                        float intBitsToFloat4 = Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() & 4294967295L)) - mo51toPx0680j_4;
                                        DrawScope.m428drawRoundRectZuiqVtQ$default(layoutNodeDrawScope, borderStroke.brush, floatToRawIntBits2, (Float.floatToRawIntBits(intBitsToFloat3) << 32) | (Float.floatToRawIntBits(intBitsToFloat4) & 4294967295L), ExpandableKt.m725shrinkKibmq7A(mo51toPx0680j_4 / 2, floatToRawIntBits), 0.0f, stroke, 208);
                                    }
                                } else {
                                    RoundedCornerShape RoundedCornerShape = RoundedCornerShapeKt.RoundedCornerShape(f, f, f2, f2);
                                    LayoutNodeDrawScope layoutNodeDrawScope2 = (LayoutNodeDrawScope) contentDrawScope;
                                    Outline mo37createOutlinePq9zytI = RoundedCornerShape.mo37createOutlinePq9zytI(layoutNodeDrawScope2.canvasDrawScope.mo432getSizeNHjbRc(), layoutNodeDrawScope2.getLayoutDirection(), contentDrawScope);
                                    OutlineKt.m387drawOutlinewDX37Ww$default(contentDrawScope, mo37createOutlinePq9zytI, j4);
                                    if (borderStroke != null) {
                                        float mo51toPx0680j_42 = layoutNodeDrawScope2.mo51toPx0680j_4(borderStroke.width);
                                        AndroidPath Path = AndroidPath_androidKt.Path();
                                        RoundRect roundRect = ((Outline.Rounded) mo37createOutlinePq9zytI).roundRect;
                                        Path.addRoundRect$default(Path, roundRect);
                                        Path Path2 = AndroidPath_androidKt.Path();
                                        Path.addRoundRect$default(Path2, new RoundRect(mo51toPx0680j_42, mo51toPx0680j_42, roundRect.getWidth() - mo51toPx0680j_42, roundRect.getHeight() - mo51toPx0680j_42, ExpandableKt.m725shrinkKibmq7A(mo51toPx0680j_42, roundRect.topLeftCornerRadius), ExpandableKt.m725shrinkKibmq7A(mo51toPx0680j_42, roundRect.topRightCornerRadius), ExpandableKt.m725shrinkKibmq7A(mo51toPx0680j_42, roundRect.bottomRightCornerRadius), ExpandableKt.m725shrinkKibmq7A(mo51toPx0680j_42, roundRect.bottomLeftCornerRadius)));
                                        Path.m353opN5in7k0(Path, Path2, 0);
                                        DrawScope.m425drawPathGBMwjPU$default(contentDrawScope, Path, borderStroke.brush, 0.0f, null, 60);
                                    }
                                }
                                ((LayoutNodeDrawScope) contentDrawScope).drawContent();
                            }
                            return Unit.INSTANCE;
                        }
                    });
                    BiasAlignment biasAlignment = Alignment.Companion.Center;
                    Modifier modifier = drawWithContent;
                    Function3 function32 = function3;
                    ExpandableControllerImpl expandableControllerImpl3 = expandableControllerImpl;
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                    ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                    PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, drawWithContent2);
                    ComposeUiNode.Companion.getClass();
                    Function0 function0 = ComposeUiNode.Companion.Constructor;
                    composerImpl3.startReusableNode();
                    if (composerImpl3.inserting) {
                        composerImpl3.createNode(function0);
                    } else {
                        composerImpl3.useNode();
                    }
                    Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
                    Updater.m259setimpl(composer2, maybeCachedBoxMeasurePolicy, function2);
                    Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
                    Updater.m259setimpl(composer2, currentCompositionLocalScope, function22);
                    Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                    if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function23);
                    }
                    Function2 function24 = ComposeUiNode.Companion.SetModifier;
                    Updater.m259setimpl(composer2, materializeModifier, function24);
                    MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                    PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl3.currentCompositionLocalScope();
                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifier);
                    composerImpl3.startReusableNode();
                    if (composerImpl3.inserting) {
                        composerImpl3.createNode(function0);
                    } else {
                        composerImpl3.useNode();
                    }
                    Updater.m259setimpl(composer2, maybeCachedBoxMeasurePolicy2, function2);
                    Updater.m259setimpl(composer2, currentCompositionLocalScope2, function22);
                    if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash2, composerImpl3, currentCompositeKeyHash2, function23);
                    }
                    Updater.m259setimpl(composer2, materializeModifier2, function24);
                    function32.invoke(expandableControllerImpl3.expandable, composer2, 8);
                    composerImpl3.end(true);
                    composerImpl3.end(true);
                    return Unit.INSTANCE;
                }
            }));
            View view2 = new View(context);
            viewGroupOverlay.add(view2);
            ViewParent parent = view2.getParent();
            while (parent.getParent() != null) {
                parent = parent.getParent();
            }
            viewGroupOverlay.remove(view2);
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.setTag(R.id.view_tree_lifecycle_owner, ViewTreeLifecycleOwner.get(view));
            viewGroup.setTag(R.id.view_tree_view_model_store_owner, ViewTreeViewModelStoreOwner.get(view));
            viewGroup.setTag(R.id.view_tree_saved_state_registry_owner, ViewTreeSavedStateRegistryOwner.get(view));
            composeView.setParentCompositionContext(rememberCompositionContext);
            composerImpl.updateRememberedValue(composeView);
            rememberedValue = composeView;
        }
        final ComposeView composeView2 = (ComposeView) rememberedValue;
        composerImpl.end(false);
        Function1 function12 = new Function1() { // from class: com.android.compose.animation.ExpandableKt$AnimatedContentInOverlay$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                viewGroupOverlay.add(composeView2);
                TransitionAnimator.State state2 = (TransitionAnimator.State) state.getValue();
                if (state2 == null) {
                    throw new IllegalStateException("AnimatedContentInOverlay shouldn't be composed with null animatorState.");
                }
                ExpandableKt.measureAndLayoutComposeViewInOverlay(composeView2, state2);
                function1.invoke(composeView2);
                final ComposeView composeView3 = composeView2;
                final ViewGroupOverlay viewGroupOverlay2 = viewGroupOverlay;
                final Function1 function13 = function1;
                return new DisposableEffectResult() { // from class: com.android.compose.animation.ExpandableKt$AnimatedContentInOverlay$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public final void dispose() {
                        ComposeView composeView4 = ComposeView.this;
                        composeView4.disposeComposition();
                        viewGroupOverlay2.remove(composeView4);
                        function13.invoke(null);
                    }
                };
            }
        };
        int i2 = ComposeView.$r8$clinit;
        EffectsKt.DisposableEffect(viewGroupOverlay, composeView2, function12, composerImpl);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.compose.animation.ExpandableKt$AnimatedContentInOverlay$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ExpandableKt.m723AnimatedContentInOverlaymxx1QeM(j, j2, state, viewGroupOverlay, expandableControllerImpl, function3, view, function1, density, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00de A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Expandable(final com.android.compose.animation.ExpandableController r25, androidx.compose.ui.Modifier r26, kotlin.jvm.functions.Function1 r27, androidx.compose.foundation.interaction.MutableInteractionSource r28, final kotlin.jvm.functions.Function3 r29, androidx.compose.runtime.Composer r30, final int r31, final int r32) {
        /*
            Method dump skipped, instructions count: 805
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.ExpandableKt.Expandable(com.android.compose.animation.ExpandableController, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x022b  */
    /* renamed from: Expandable-QIcBpto, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m724ExpandableQIcBpto(final long r38, final androidx.compose.ui.graphics.Shape r40, androidx.compose.ui.Modifier r41, long r42, androidx.compose.foundation.BorderStroke r44, kotlin.jvm.functions.Function1 r45, androidx.compose.foundation.interaction.MutableInteractionSource r46, final kotlin.jvm.functions.Function3 r47, androidx.compose.runtime.Composer r48, final int r49, final int r50) {
        /*
            Method dump skipped, instructions count: 830
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.ExpandableKt.m724ExpandableQIcBpto(long, androidx.compose.ui.graphics.Shape, androidx.compose.ui.Modifier, long, androidx.compose.foundation.BorderStroke, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void measureAndLayoutComposeViewInOverlay(View view, TransitionAnimator.State state) {
        view.measure(View.MeasureSpec.makeSafeMeasureSpec(state.getWidth(), 1073741824), View.MeasureSpec.makeSafeMeasureSpec(state.getHeight(), 1073741824));
        int[] locationOnScreen = ((ViewGroup) view.getParent()).getLocationOnScreen();
        int i = locationOnScreen[0];
        int i2 = locationOnScreen[1];
        view.layout(state.left - i, state.top - i2, state.right - i, state.bottom - i2);
    }

    /* renamed from: shrink-Kibmq7A, reason: not valid java name */
    public static final long m725shrinkKibmq7A(float f, long j) {
        float max = Math.max(0.0f, Float.intBitsToFloat((int) (j >> 32)) - f);
        float max2 = Math.max(0.0f, Float.intBitsToFloat((int) (j & 4294967295L)) - f);
        return (Float.floatToRawIntBits(max) << 32) | (Float.floatToRawIntBits(max2) & 4294967295L);
    }
}
