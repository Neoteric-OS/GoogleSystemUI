package com.android.systemui.keyguard.ui.composable.section;

import android.content.Context;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import com.android.compose.animation.scene.ContentScope;
import com.android.compose.animation.scene.ElementKey;
import com.android.compose.animation.scene.ElementScopeImpl;
import com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder;
import com.android.systemui.keyguard.ui.view.KeyguardIndicationArea;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BottomAreaSection {
    public final KeyguardIndicationAreaViewModel indicationAreaViewModel;
    public final KeyguardIndicationController indicationController;
    public final KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder;
    public final KeyguardQuickAffordancesCombinedViewModel viewModel;

    public BottomAreaSection(KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel, KeyguardIndicationController keyguardIndicationController, KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder) {
        this.indicationController = keyguardIndicationController;
        this.indicationAreaViewModel = keyguardIndicationAreaViewModel;
    }

    public static final void access$IndicationArea(final BottomAreaSection bottomAreaSection, final KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, final KeyguardIndicationController keyguardIndicationController, Modifier modifier, Composer composer, final int i, final int i2) {
        bottomAreaSection.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1804781866);
        if ((i2 & 4) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(967122564);
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState = (MutableState) rememberedValue;
        composerImpl.end(false);
        final DisposableHandle disposableHandle = (DisposableHandle) mutableState.component1();
        final Function1 component2 = mutableState.component2();
        AndroidView_androidKt.AndroidView(new Function1() { // from class: com.android.systemui.keyguard.ui.composable.section.BottomAreaSection$IndicationArea$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyguardIndicationArea keyguardIndicationArea = new KeyguardIndicationArea((Context) obj);
                Function1.this.invoke(KeyguardIndicationAreaBinder.bind(keyguardIndicationArea, keyguardIndicationAreaViewModel, keyguardIndicationController));
                return keyguardIndicationArea;
            }
        }, SizeKt.fillMaxWidth(modifier, 1.0f), null, new Function1() { // from class: com.android.systemui.keyguard.ui.composable.section.BottomAreaSection$IndicationArea$5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                DisposableHandle disposableHandle2 = DisposableHandle.this;
                if (disposableHandle2 != null) {
                    disposableHandle2.dispose();
                }
                return Unit.INSTANCE;
            }
        }, null, composerImpl, 0, 20);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier2 = modifier;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyguard.ui.composable.section.BottomAreaSection$IndicationArea$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    BottomAreaSection.access$IndicationArea(BottomAreaSection.this, keyguardIndicationAreaViewModel, keyguardIndicationController, modifier2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.android.systemui.keyguard.ui.composable.section.BottomAreaSection$IndicationArea$1, kotlin.jvm.internal.Lambda] */
    public final void IndicationArea(final ContentScope contentScope, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(400972694);
        if ((i2 & 1) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ElementKey elementKey = BottomAreaSectionKt.IndicationAreaElementKey;
        composerImpl.startReplaceGroup(2014498161);
        Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, PrimitiveResources_androidKt.dimensionResource(R.dimen.keyguard_indication_margin_bottom, composerImpl), 7);
        composerImpl.end(false);
        contentScope.Element(elementKey, m102paddingqDBjuR0$default, ComposableLambdaKt.rememberComposableLambda(893716127, new Function3() { // from class: com.android.systemui.keyguard.ui.composable.section.BottomAreaSection$IndicationArea$1
            {
                super(3);
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.keyguard.ui.composable.section.BottomAreaSection$IndicationArea$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ElementScopeImpl elementScopeImpl = (ElementScopeImpl) obj;
                Composer composer2 = (Composer) obj2;
                int intValue = ((Number) obj3).intValue();
                if ((intValue & 14) == 0) {
                    intValue |= ((ComposerImpl) composer2).changed(elementScopeImpl) ? 4 : 2;
                }
                if ((intValue & 91) == 18) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                final BottomAreaSection bottomAreaSection = BottomAreaSection.this;
                elementScopeImpl.content(ComposableLambdaKt.rememberComposableLambda(-1389015676, new Function3() { // from class: com.android.systemui.keyguard.ui.composable.section.BottomAreaSection$IndicationArea$1.1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        Composer composer3 = (Composer) obj5;
                        if ((((Number) obj6).intValue() & 81) == 16) {
                            ComposerImpl composerImpl3 = (ComposerImpl) composer3;
                            if (composerImpl3.getSkipping()) {
                                composerImpl3.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey3 = ComposerKt.invocation;
                        BottomAreaSection bottomAreaSection2 = BottomAreaSection.this;
                        BottomAreaSection.access$IndicationArea(bottomAreaSection2, bottomAreaSection2.indicationAreaViewModel, bottomAreaSection2.indicationController, null, composer3, 4168, 4);
                        return Unit.INSTANCE;
                    }
                }, composer2), composer2, ((intValue << 3) & 112) | 6);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, ((i << 9) & 7168) | 390);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier2 = modifier;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyguard.ui.composable.section.BottomAreaSection$IndicationArea$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    BottomAreaSection.this.IndicationArea(contentScope, modifier2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
