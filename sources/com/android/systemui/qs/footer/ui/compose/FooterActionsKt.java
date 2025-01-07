package com.android.systemui.qs.footer.ui.compose;

import android.content.Context;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.AppBarKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.FlowExtKt;
import com.airbnb.lottie.compose.LottieAnimationKt$$ExternalSyntheticOutline0;
import com.android.compose.modifiers.FadingBackgroundKt;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.compose.theme.ColorKt;
import com.android.systemui.animation.Expandable;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.compose.modifiers.SysuiTestTagKt;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsButtonViewModel;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsForegroundServicesButtonViewModel;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsSecurityButtonViewModel;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsViewModel;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class FooterActionsKt {
    /* JADX WARN: Type inference failed for: r1v13, types: [com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$4$1, kotlin.jvm.internal.Lambda] */
    public static final void FooterActions(final FooterActionsViewModel footerActionsViewModel, final LifecycleOwner lifecycleOwner, Modifier modifier, Composer composer, final int i, final int i2) {
        MutableState mutableState;
        Object obj;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(548205006);
        int i3 = i2 & 4;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(footerActionsViewModel.alpha, composerImpl);
        Object collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(footerActionsViewModel.backgroundAlpha, composerImpl);
        composerImpl.startReplaceGroup(-922484594);
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj2 = Composer.Companion.Empty;
        if (rememberedValue == obj2) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState2 = (MutableState) rememberedValue;
        Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, -922484491);
        if (m == obj2) {
            m = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(m);
        }
        final MutableState mutableState3 = (MutableState) m;
        Object m2 = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, -922484372);
        if (m2 == obj2) {
            m2 = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(m2);
        }
        final MutableState mutableState4 = (MutableState) m2;
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(new Object[]{context, lifecycleOwner, footerActionsViewModel, footerActionsViewModel.security, footerActionsViewModel.foregroundServices, footerActionsViewModel.userSwitcher}, new FooterActionsKt$FooterActions$1(lifecycleOwner, footerActionsViewModel, context, mutableState2, mutableState3, mutableState4, null), composerImpl);
        long colorAttr = ColorKt.colorAttr(R.attr.underSurface, composerImpl);
        long j = ((AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).onSurface;
        float dimensionResource = PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_corner_radius, composerImpl);
        composerImpl.startReplaceGroup(-922483294);
        boolean changed = composerImpl.changed(colorAttr) | composerImpl.changed(collectAsStateWithLifecycle2) | composerImpl.changed(dimensionResource);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == obj2) {
            mutableState = mutableState2;
            obj = obj2;
            rememberedValue2 = FadingBackgroundKt.m741backgroundRPmYEkk(companion, colorAttr, new FooterActionsKt$FooterActions$backgroundModifier$1$1(collectAsStateWithLifecycle2, State.class, "value", "getValue()Ljava/lang/Object;", 0), RoundedCornerShapeKt.m150RoundedCornerShapea9UjIt4$default(12, dimensionResource, dimensionResource));
            composerImpl.updateRememberedValue(rememberedValue2);
        } else {
            obj = obj2;
            mutableState = mutableState2;
        }
        Modifier modifier3 = (Modifier) rememberedValue2;
        composerImpl.end(false);
        float dimensionResource2 = PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_content_horizontal_padding, composerImpl);
        Modifier fillMaxWidth = SizeKt.fillMaxWidth(modifier2, 1.0f);
        composerImpl.startReplaceGroup(-922482786);
        boolean changed2 = composerImpl.changed(collectAsStateWithLifecycle);
        Object rememberedValue3 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue3 == obj) {
            rememberedValue3 = new Function1() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    ((ReusableGraphicsLayerScope) ((GraphicsLayerScope) obj3)).setAlpha(((Number) collectAsStateWithLifecycle.getValue()).floatValue());
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue3);
        }
        composerImpl.end(false);
        Modifier layout = LayoutModifierKt.layout(PaddingKt.m101paddingqDBjuR0(GraphicsLayerModifierKt.graphicsLayer(fillMaxWidth, (Function1) rememberedValue3).then(modifier3), dimensionResource2, PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_footer_actions_top_padding, composerImpl), dimensionResource2, PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_footer_actions_bottom_padding, composerImpl)), new Function3() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$3
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj3, Object obj4, Object obj5) {
                MeasureResult layout$1;
                MeasureScope measureScope = (MeasureScope) obj3;
                Measurable measurable = (Measurable) obj4;
                long j2 = ((Constraints) obj5).value;
                final int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(4);
                int i4 = mo45roundToPx0680j_4 * 2;
                final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints.m651getHasBoundedWidthimpl(j2) ? Constraints.m648copyZbe2FdA$default(j2, 0, Constraints.m655getMaxWidthimpl(j2) + i4, 0, 0, 13) : j2);
                layout$1 = measureScope.layout$1(ConstraintsKt.m665constrainWidthK40F9xA(j2, mo479measureBRTryo0.width - i4), ConstraintsKt.m664constrainHeightK40F9xA(j2, mo479measureBRTryo0.height), MapsKt.emptyMap(), new Function1() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$3.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj6) {
                        ((Placeable.PlacementScope) obj6).place(Placeable.this, -mo45roundToPx0680j_4, 0, 0.0f);
                        return Unit.INSTANCE;
                    }
                });
                return layout$1;
            }
        });
        RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Start, Alignment.Companion.CenterVertically, composerImpl, 48);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, layout);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        final MutableState mutableState5 = mutableState;
        CompositionLocalKt.CompositionLocalProvider(AppBarKt$$ExternalSyntheticOutline0.m(j, ContentColorKt.LocalContentColor), ComposableLambdaKt.rememberComposableLambda(-2122823950, new Function2() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$4$1
            final /* synthetic */ RowScope $this_Row = RowScopeInstance.INSTANCE;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj3, Object obj4) {
                Composer composer2 = (Composer) obj3;
                if ((((Number) obj4).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                composerImpl3.startReplaceGroup(-78889285);
                FooterActionsSecurityButtonViewModel footerActionsSecurityButtonViewModel = (FooterActionsSecurityButtonViewModel) mutableState5.getValue();
                Modifier.Companion companion2 = Modifier.Companion.$$INSTANCE;
                if (footerActionsSecurityButtonViewModel == null && ((FooterActionsForegroundServicesButtonViewModel) mutableState3.getValue()) == null) {
                    SpacerKt.Spacer(composerImpl3, this.$this_Row.weight(companion2, 1.0f, true));
                }
                composerImpl3.end(false);
                FooterActionsSecurityButtonViewModel footerActionsSecurityButtonViewModel2 = (FooterActionsSecurityButtonViewModel) mutableState5.getValue();
                composerImpl3.startReplaceGroup(-78889150);
                if (footerActionsSecurityButtonViewModel2 != null) {
                    FooterActionsKt.access$SecurityButton(footerActionsSecurityButtonViewModel2, this.$this_Row.weight(companion2, 1.0f, true), composerImpl3, 0, 0);
                }
                composerImpl3.end(false);
                FooterActionsForegroundServicesButtonViewModel footerActionsForegroundServicesButtonViewModel = (FooterActionsForegroundServicesButtonViewModel) mutableState3.getValue();
                composerImpl3.startReplaceGroup(-78889070);
                if (footerActionsForegroundServicesButtonViewModel != null) {
                    FooterActionsKt.access$ForegroundServicesButton(this.$this_Row, footerActionsForegroundServicesButtonViewModel, composerImpl3, 0);
                }
                composerImpl3.end(false);
                FooterActionsButtonViewModel footerActionsButtonViewModel = (FooterActionsButtonViewModel) mutableState4.getValue();
                composerImpl3.startReplaceGroup(-78889007);
                if (footerActionsButtonViewModel != null) {
                    FooterActionsKt.access$IconButton(footerActionsButtonViewModel, SysuiTestTagKt.sysuiResTag(companion2, "multi_user_switch"), composerImpl3, 0, 0);
                }
                composerImpl3.end(false);
                FooterActionsKt.access$IconButton(FooterActionsViewModel.this.settings, SysuiTestTagKt.sysuiResTag(companion2, "settings_button_container"), composerImpl3, 0, 0);
                FooterActionsButtonViewModel footerActionsButtonViewModel2 = FooterActionsViewModel.this.power;
                if (footerActionsButtonViewModel2 != null) {
                    FooterActionsKt.access$IconButton(footerActionsButtonViewModel2, SysuiTestTagKt.sysuiResTag(companion2, "pm_lite"), composerImpl3, 0, 0);
                }
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 56);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    ((Number) obj4).intValue();
                    FooterActionsKt.FooterActions(FooterActionsViewModel.this, lifecycleOwner, modifier4, (Composer) obj3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008c  */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.android.systemui.qs.footer.ui.compose.FooterActionsKt$NumberButton$1, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NumberButton(final int r19, final boolean r20, final kotlin.jvm.functions.Function1 r21, androidx.compose.ui.Modifier r22, androidx.compose.runtime.Composer r23, final int r24, final int r25) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.footer.ui.compose.FooterActionsKt.NumberButton(int, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARN: Type inference failed for: r4v10, types: [com.android.systemui.qs.footer.ui.compose.FooterActionsKt$TextButton$1, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextButton(final com.android.systemui.common.shared.model.Icon r21, final java.lang.String r22, final boolean r23, final kotlin.jvm.functions.Function1 r24, androidx.compose.ui.Modifier r25, androidx.compose.runtime.Composer r26, final int r27, final int r28) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.footer.ui.compose.FooterActionsKt.TextButton(com.android.systemui.common.shared.model.Icon, java.lang.String, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void access$ForegroundServicesButton(final RowScope rowScope, final FooterActionsForegroundServicesButtonViewModel footerActionsForegroundServicesButtonViewModel, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(942581812);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(rowScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(footerActionsForegroundServicesButtonViewModel) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            boolean z = footerActionsForegroundServicesButtonViewModel.displayText;
            Function1 function1 = footerActionsForegroundServicesButtonViewModel.onClick;
            if (z) {
                composerImpl.startReplaceGroup(-1797158);
                TextButton(new Icon.Resource(R.drawable.ic_info_outline, null), footerActionsForegroundServicesButtonViewModel.text, footerActionsForegroundServicesButtonViewModel.hasNewChanges, function1, rowScope.weight(Modifier.Companion.$$INSTANCE, 1.0f, true), composerImpl, 0, 0);
                composerImpl.end(false);
            } else {
                composerImpl.startReplaceGroup(-1796893);
                NumberButton(footerActionsForegroundServicesButtonViewModel.foregroundServicesCount, footerActionsForegroundServicesButtonViewModel.hasNewChanges, function1, null, composerImpl, 0, 8);
                composerImpl.end(false);
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$ForegroundServicesButton$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    FooterActionsKt.access$ForegroundServicesButton(RowScope.this, footerActionsForegroundServicesButtonViewModel, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Type inference failed for: r11v0, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.FunctionReferenceImpl] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.android.systemui.qs.footer.ui.compose.FooterActionsKt$IconButton$1, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$IconButton(final com.android.systemui.qs.footer.ui.viewmodel.FooterActionsButtonViewModel r19, androidx.compose.ui.Modifier r20, androidx.compose.runtime.Composer r21, final int r22, final int r23) {
        /*
            r0 = r19
            r1 = r22
            r2 = r23
            r3 = 2
            r15 = r21
            androidx.compose.runtime.ComposerImpl r15 = (androidx.compose.runtime.ComposerImpl) r15
            r4 = -1608080050(0xffffffffa026a54e, float:-1.4115442E-19)
            r15.startRestartGroup(r4)
            r4 = r2 & 1
            if (r4 == 0) goto L18
            r4 = r1 | 6
            goto L28
        L18:
            r4 = r1 & 14
            if (r4 != 0) goto L27
            boolean r4 = r15.changed(r0)
            if (r4 == 0) goto L24
            r4 = 4
            goto L25
        L24:
            r4 = r3
        L25:
            r4 = r4 | r1
            goto L28
        L27:
            r4 = r1
        L28:
            r3 = r3 & r2
            if (r3 == 0) goto L30
            r4 = r4 | 48
        L2d:
            r5 = r20
            goto L42
        L30:
            r5 = r1 & 112(0x70, float:1.57E-43)
            if (r5 != 0) goto L2d
            r5 = r20
            boolean r6 = r15.changed(r5)
            if (r6 == 0) goto L3f
            r6 = 32
            goto L41
        L3f:
            r6 = 16
        L41:
            r4 = r4 | r6
        L42:
            r6 = r4 & 91
            r7 = 18
            if (r6 != r7) goto L56
            boolean r6 = r15.getSkipping()
            if (r6 != 0) goto L4f
            goto L56
        L4f:
            r15.skipToGroupEnd()
            r3 = r5
            r18 = r15
            goto L8f
        L56:
            if (r3 == 0) goto L5b
            androidx.compose.ui.Modifier$Companion r3 = androidx.compose.ui.Modifier.Companion.$$INSTANCE
            goto L5c
        L5b:
            r3 = r5
        L5c:
            androidx.compose.runtime.OpaqueKey r5 = androidx.compose.runtime.ComposerKt.invocation
            int r5 = r0.backgroundColor
            long r5 = com.android.compose.theme.ColorKt.colorAttr(r5, r15)
            androidx.compose.foundation.shape.RoundedCornerShape r7 = androidx.compose.foundation.shape.RoundedCornerShapeKt.CircleShape
            com.android.systemui.qs.footer.ui.compose.FooterActionsKt$IconButton$1 r8 = new com.android.systemui.qs.footer.ui.compose.FooterActionsKt$IconButton$1
            r8.<init>()
            r9 = 1064898050(0x3f790e02, float:0.97287)
            androidx.compose.runtime.internal.ComposableLambdaImpl r13 = androidx.compose.runtime.internal.ComposableLambdaKt.rememberComposableLambda(r9, r8, r15)
            int r4 = r4 << 3
            r4 = r4 & 896(0x380, float:1.256E-42)
            r8 = 12582912(0xc00000, float:1.7632415E-38)
            r16 = r4 | r8
            kotlin.jvm.internal.FunctionReferenceImpl r11 = r0.onClick
            r10 = 0
            r17 = 88
            r8 = 0
            r12 = 0
            r4 = r5
            r6 = r7
            r7 = r3
            r14 = r15
            r18 = r15
            r15 = r16
            r16 = r17
            com.android.compose.animation.ExpandableKt.m724ExpandableQIcBpto(r4, r6, r7, r8, r10, r11, r12, r13, r14, r15, r16)
        L8f:
            androidx.compose.runtime.RecomposeScopeImpl r4 = r18.endRestartGroup()
            if (r4 == 0) goto L9c
            com.android.systemui.qs.footer.ui.compose.FooterActionsKt$IconButton$2 r5 = new com.android.systemui.qs.footer.ui.compose.FooterActionsKt$IconButton$2
            r5.<init>()
            r4.block = r5
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.footer.ui.compose.FooterActionsKt.access$IconButton(com.android.systemui.qs.footer.ui.viewmodel.FooterActionsButtonViewModel, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void access$NewChangesDot(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1339346955);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i3 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.Companion.$$INSTANCE;
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            final String stringResource = StringResources_androidKt.stringResource(R.string.fgs_dot_content_description, composerImpl);
            final long j = ((AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).tertiary;
            Modifier m113size3ABfNKs = SizeKt.m113size3ABfNKs(modifier, 12);
            composerImpl.startReplaceGroup(1174050000);
            boolean changed = composerImpl.changed(stringResource);
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (changed || rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = new Function1() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$NewChangesDot$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        SemanticsPropertiesKt.setContentDescription((SemanticsPropertyReceiver) obj, stringResource);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            Modifier semantics = SemanticsModifierKt.semantics(m113size3ABfNKs, false, (Function1) rememberedValue);
            composerImpl.startReplaceGroup(1174050050);
            boolean changed2 = composerImpl.changed(j);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (changed2 || rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new Function1() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$NewChangesDot$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        DrawScope.m422drawCircleVaOC9Bg$default((DrawScope) obj, j, 0.0f, 0L, 0.0f, 126);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            composerImpl.end(false);
            CanvasKt.Canvas(semantics, (Function1) rememberedValue2, composerImpl, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$NewChangesDot$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    FooterActionsKt.access$NewChangesDot(Modifier.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$SecurityButton(final FooterActionsSecurityButtonViewModel footerActionsSecurityButtonViewModel, final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Function1 function1;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(47825543);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (composerImpl.changed(footerActionsSecurityButtonViewModel) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = 2 & i2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 112) == 0) {
            i3 |= composerImpl.changed(modifier) ? 32 : 16;
        }
        if ((i3 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.Companion.$$INSTANCE;
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            final Function2 function2 = footerActionsSecurityButtonViewModel.onClick;
            composerImpl.startReplaceGroup(508634277);
            if (function2 == null) {
                function1 = null;
            } else {
                final Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
                function1 = new Function1() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$SecurityButton$onClick$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Function2.this.invoke(context, (Expandable) obj);
                        return Unit.INSTANCE;
                    }
                };
            }
            composerImpl.end(false);
            TextButton(footerActionsSecurityButtonViewModel.icon, footerActionsSecurityButtonViewModel.text, false, function1, modifier, composerImpl, ((i3 << 9) & 57344) | 384, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$SecurityButton$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    FooterActionsKt.access$SecurityButton(FooterActionsSecurityButtonViewModel.this, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
