package com.android.systemui.volume.panel.component.spatialaudio.ui.composable;

import androidx.compose.foundation.BasicMarqueeKt;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.common.ui.compose.IconKt;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.volume.panel.component.button.ui.viewmodel.ButtonViewModel;
import com.android.systemui.volume.panel.component.popup.ui.composable.VolumePanelPopup;
import com.android.systemui.volume.panel.component.selector.ui.composable.Item;
import com.android.systemui.volume.panel.component.selector.ui.composable.VolumePanelRadioButtonBarScopeImpl;
import com.android.systemui.volume.panel.component.selector.ui.composable.VolumePanelRadioButtonsKt;
import com.android.systemui.volume.panel.component.spatial.ui.viewmodel.SpatialAudioButtonViewModel;
import com.android.systemui.volume.panel.component.spatial.ui.viewmodel.SpatialAudioViewModel;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SpatialAudioPopup {
    public final UiEventLogger uiEventLogger;
    public final SpatialAudioViewModel viewModel;
    public final VolumePanelPopup volumePanelPopup;

    public SpatialAudioPopup(SpatialAudioViewModel spatialAudioViewModel, VolumePanelPopup volumePanelPopup, UiEventLogger uiEventLogger) {
        this.viewModel = spatialAudioViewModel;
        this.volumePanelPopup = volumePanelPopup;
        this.uiEventLogger = uiEventLogger;
    }

    public static final void access$Title(final SpatialAudioPopup spatialAudioPopup, Composer composer, final int i) {
        ComposerImpl composerImpl;
        spatialAudioPopup.getClass();
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-351769307);
        if ((i & 1) == 0 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier m26basicMarquee1Mj1MLw$default = BasicMarqueeKt.m26basicMarquee1Mj1MLw$default(Modifier.Companion.$$INSTANCE, 63);
            composerImpl = composerImpl2;
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.volume_panel_spatial_audio_title, composerImpl2), m26basicMarquee1Mj1MLw$default, 0L, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 0, false, 1, 0, null, MaterialTheme.getTypography(composerImpl2).titleMedium, composerImpl, 48, 3072, 56828);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Title$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SpatialAudioPopup.access$Title(SpatialAudioPopup.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public final void Content(final SystemUIDialog systemUIDialog, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1509869231);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        SpatialAudioViewModel spatialAudioViewModel = this.viewModel;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(spatialAudioViewModel.isAvailable, composerImpl);
        composerImpl.startReplaceGroup(-1597098557);
        if (!((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue()) {
            EffectsKt.SideEffect(new Function0() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Content$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    SystemUIDialog.this.dismiss();
                    return Unit.INSTANCE;
                }
            }, composerImpl);
            composerImpl.end(false);
            RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
            if (endRestartGroup != null) {
                endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Content$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        ((Number) obj2).intValue();
                        SpatialAudioPopup.this.Content(systemUIDialog, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                        return Unit.INSTANCE;
                    }
                };
                return;
            }
            return;
        }
        composerImpl.end(false);
        final MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(spatialAudioViewModel.spatialAudioButtons, composerImpl);
        if (((List) collectAsStateWithLifecycle2.getValue()).isEmpty()) {
            RecomposeScopeImpl endRestartGroup2 = composerImpl.endRestartGroup();
            if (endRestartGroup2 != null) {
                endRestartGroup2.block = new Function2() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Content$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        ((Number) obj2).intValue();
                        SpatialAudioPopup.this.Content(systemUIDialog, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                        return Unit.INSTANCE;
                    }
                };
                return;
            }
            return;
        }
        VolumePanelRadioButtonsKt.m892VolumePanelRadioButtonBarcjTkxnM(null, 0.0f, 0.0f, 0.0f, null, null, null, new Function1() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Content$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                VolumePanelRadioButtonBarScopeImpl volumePanelRadioButtonBarScopeImpl = (VolumePanelRadioButtonBarScopeImpl) obj;
                for (final SpatialAudioButtonViewModel spatialAudioButtonViewModel : (List) collectAsStateWithLifecycle2.getValue()) {
                    final String obj2 = spatialAudioButtonViewModel.button.label.toString();
                    ButtonViewModel buttonViewModel = spatialAudioButtonViewModel.button;
                    final SpatialAudioPopup spatialAudioPopup = this;
                    Function0 function0 = new Function0() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Content$4.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            SpatialAudioPopup.this.viewModel.setEnabled(spatialAudioButtonViewModel.model);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposableLambdaImpl composableLambdaImpl = new ComposableLambdaImpl(666734551, true, new Function3() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Content$4.2
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            Composer composer2 = (Composer) obj4;
                            if ((((Number) obj5).intValue() & 81) == 16) {
                                ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                                if (composerImpl2.getSkipping()) {
                                    composerImpl2.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            OpaqueKey opaqueKey2 = ComposerKt.invocation;
                            IconKt.m793IconFNF3uiM(SpatialAudioButtonViewModel.this.button.icon, null, 0L, composer2, 0, 6);
                            return Unit.INSTANCE;
                        }
                    });
                    ComposableLambdaImpl composableLambdaImpl2 = new ComposableLambdaImpl(2005226806, true, new Function3() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Content$4.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            Composer composer2 = (Composer) obj4;
                            if ((((Number) obj5).intValue() & 81) == 16) {
                                ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                                if (composerImpl2.getSkipping()) {
                                    composerImpl2.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            OpaqueKey opaqueKey2 = ComposerKt.invocation;
                            Modifier m26basicMarquee1Mj1MLw$default = BasicMarqueeKt.m26basicMarquee1Mj1MLw$default(Modifier.Companion.$$INSTANCE, 63);
                            TextStyle textStyle = MaterialTheme.getTypography(composer2).labelMedium;
                            ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                            TextKt.m241Text4IGK_g(obj2, m26basicMarquee1Mj1MLw$default, ((Color) composerImpl3.consume(ContentColorKt.LocalContentColor)).value, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 0, false, 2, 0, null, textStyle, composerImpl3, 48, 3072, 56824);
                            return Unit.INSTANCE;
                        }
                    });
                    boolean z = buttonViewModel.isActive;
                    if (z && volumePanelRadioButtonBarScopeImpl.selectedIndex != -1) {
                        throw new IllegalArgumentException("Only one item should be selected at a time");
                    }
                    if (z) {
                        volumePanelRadioButtonBarScopeImpl.selectedIndex = ((ArrayList) volumePanelRadioButtonBarScopeImpl.mutableItems).size();
                    }
                    volumePanelRadioButtonBarScopeImpl.mutableItems.add(new Item(function0, composableLambdaImpl, composableLambdaImpl2, obj2));
                }
                return Unit.INSTANCE;
            }
        }, composerImpl, 0, 127);
        RecomposeScopeImpl endRestartGroup3 = composerImpl.endRestartGroup();
        if (endRestartGroup3 != null) {
            endRestartGroup3.block = new Function2() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup$Content$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SpatialAudioPopup.this.Content(systemUIDialog, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
