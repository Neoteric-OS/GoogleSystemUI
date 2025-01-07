package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.Switch;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.state.ToggleableState;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.systemui.animation.Expandable;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.panels.ui.viewmodel.AccessibilityUiState;
import com.android.systemui.qs.panels.ui.viewmodel.TileUiState;
import com.android.systemui.qs.panels.ui.viewmodel.TileUiStateKt$toUiState$2;
import com.android.systemui.qs.panels.ui.viewmodel.TileViewModel;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tileimpl.SubtitleArrayMapping;
import com.android.wm.shell.R;
import java.util.HashMap;
import java.util.function.Supplier;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TileKt {
    /* JADX WARN: Type inference failed for: r11v3, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt$Tile$3, kotlin.jvm.internal.Lambda] */
    public static final void Tile(final TileViewModel tileViewModel, final boolean z, Modifier modifier, Composer composer, int i) {
        int i2;
        String str;
        ToggleableState toggleableState;
        Supplier supplier;
        String str2;
        String obj;
        String obj2;
        TileUiState tileUiState;
        MutableState mutableState;
        Object obj3;
        boolean z2;
        TileColors tileColors;
        Object obj4;
        boolean z3;
        boolean z4;
        ComposerImpl composerImpl;
        final int i3;
        final Modifier modifier2;
        final boolean z5;
        TileColors tileColors2;
        boolean z6;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(759293097);
        if ((i & 14) == 0) {
            i2 = (composerImpl2.changed(tileViewModel) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl2.changed(z) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerImpl2.changed(modifier) ? 256 : 128;
        }
        int i4 = i2;
        if ((i4 & 731) == 146 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
            i3 = i;
            modifier2 = modifier;
            z5 = z;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(tileViewModel.state, tileViewModel.tile.getState(), composerImpl2, 72);
            composerImpl2.consume(AndroidCompositionLocals_androidKt.LocalConfiguration);
            Resources resources = ((Context) composerImpl2.consume(AndroidCompositionLocals_androidKt.LocalContext)).getResources();
            Object obj5 = (QSTile.State) collectAsStateWithLifecycle.getValue();
            composerImpl2.startReplaceGroup(1797408667);
            boolean changed = composerImpl2.changed(obj5) | composerImpl2.changed(resources);
            Object rememberedValue = composerImpl2.rememberedValue();
            Object obj6 = Composer.Companion.Empty;
            if (changed || rememberedValue == obj6) {
                final QSTile.State state = (QSTile.State) collectAsStateWithLifecycle.getValue();
                int i5 = (!Intrinsics.areEqual(state.expandedAccessibilityClassName, Switch.class.getName()) || state.handlesSecondaryClick) ? 0 : 2;
                StringBuilder sb = new StringBuilder();
                if (Role.m574equalsimpl0(i5, 2) || state.state == 0) {
                    HashMap hashMap = SubtitleArrayMapping.subtitleIdsMap;
                    str = resources.getStringArray(SubtitleArrayMapping.getSubtitleId(state.spec))[state.state];
                } else {
                    str = "";
                }
                CharSequence secondaryLabel = state.getSecondaryLabel(str);
                if (!TextUtils.isEmpty(str)) {
                    sb.append((CharSequence) str);
                }
                if (state.disabledByPolicy && state.state != 0) {
                    sb.append(", ");
                    sb.append(resources.getStringArray(SubtitleArrayMapping.getSubtitleId(state.spec))[0]);
                }
                if (!TextUtils.isEmpty(state.stateDescription)) {
                    CharSequence charSequence = state.stateDescription;
                    Intrinsics.checkNotNull(charSequence);
                    if (!StringsKt.contains$default(sb, charSequence)) {
                        sb.append(", ");
                        sb.append(state.stateDescription);
                    }
                }
                if (Role.m574equalsimpl0(i5, 2) || state.handlesSecondaryClick) {
                    toggleableState = state.state == 2 ? ToggleableState.On : ToggleableState.Off;
                } else {
                    toggleableState = null;
                }
                CharSequence charSequence2 = state.label;
                String str3 = (charSequence2 == null || (obj2 = charSequence2.toString()) == null) ? "" : obj2;
                String str4 = (secondaryLabel == null || (obj = secondaryLabel.toString()) == null) ? "" : obj;
                int i6 = state.disabledByPolicy ? 0 : state.state;
                boolean z7 = state.handlesSecondaryClick;
                if (state.icon != null) {
                    supplier = new Supplier() { // from class: com.android.systemui.qs.panels.ui.viewmodel.TileUiStateKt$toUiState$1$1
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return QSTile.State.this.icon;
                        }
                    };
                } else {
                    supplier = state.iconSupplier;
                    if (supplier == null) {
                        supplier = TileUiStateKt$toUiState$2.INSTANCE;
                    }
                }
                Supplier supplier2 = supplier;
                CharSequence charSequence3 = state.contentDescription;
                if (charSequence3 == null || (str2 = charSequence3.toString()) == null) {
                    str2 = "";
                }
                rememberedValue = new TileUiState(str3, str4, i6, z7, supplier2, new AccessibilityUiState(str2, sb.toString(), i5, toggleableState, state.disabledByPolicy ? resources.getString(R.string.accessibility_tile_disabled_by_policy_action_description) : null));
                composerImpl2.updateRememberedValue(rememberedValue);
            }
            TileUiState tileUiState2 = (TileUiState) rememberedValue;
            composerImpl2.end(false);
            float f = TileDefaults.ActiveIconCornerRadius;
            composerImpl2.startReplaceGroup(648291360);
            int i7 = tileUiState2.state;
            boolean z8 = tileUiState2.handlesSecondaryClick;
            if (i7 != 1) {
                if (i7 != 2) {
                    composerImpl2.startReplaceGroup(1093345286);
                    composerImpl2.startReplaceGroup(9550526);
                    mutableState = collectAsStateWithLifecycle;
                    obj3 = obj6;
                    tileUiState = tileUiState2;
                    tileColors2 = new TileColors(MaterialTheme.getColorScheme(composerImpl2).surface, MaterialTheme.getColorScheme(composerImpl2).surface, MaterialTheme.getColorScheme(composerImpl2).onSurface, MaterialTheme.getColorScheme(composerImpl2).onSurface, MaterialTheme.getColorScheme(composerImpl2).onSurface);
                    composerImpl2.end(false);
                    composerImpl2.end(false);
                } else {
                    tileUiState = tileUiState2;
                    mutableState = collectAsStateWithLifecycle;
                    obj3 = obj6;
                    composerImpl2.startReplaceGroup(1093345033);
                    if (z8) {
                        composerImpl2.startReplaceGroup(1093345090);
                        composerImpl2.startReplaceGroup(334753343);
                        tileColors2 = new TileColors(MaterialTheme.getColorScheme(composerImpl2).surfaceVariant, MaterialTheme.getColorScheme(composerImpl2).primary, MaterialTheme.getColorScheme(composerImpl2).onSurfaceVariant, MaterialTheme.getColorScheme(composerImpl2).onSurfaceVariant, MaterialTheme.getColorScheme(composerImpl2).onPrimary);
                        composerImpl2.end(false);
                        composerImpl2.end(false);
                        z6 = false;
                    } else {
                        composerImpl2.startReplaceGroup(1093345164);
                        composerImpl2.startReplaceGroup(-1966609684);
                        tileColors2 = new TileColors(MaterialTheme.getColorScheme(composerImpl2).primary, MaterialTheme.getColorScheme(composerImpl2).primary, MaterialTheme.getColorScheme(composerImpl2).onPrimary, MaterialTheme.getColorScheme(composerImpl2).onPrimary, MaterialTheme.getColorScheme(composerImpl2).onPrimary);
                        z6 = false;
                        composerImpl2.end(false);
                        composerImpl2.end(false);
                    }
                    composerImpl2.end(z6);
                }
                tileColors = tileColors2;
                z2 = false;
            } else {
                tileUiState = tileUiState2;
                mutableState = collectAsStateWithLifecycle;
                obj3 = obj6;
                composerImpl2.startReplaceGroup(1093345245);
                composerImpl2.startReplaceGroup(2095397799);
                TileColors tileColors3 = new TileColors(MaterialTheme.getColorScheme(composerImpl2).surfaceVariant, MaterialTheme.getColorScheme(composerImpl2).surfaceVariant, MaterialTheme.getColorScheme(composerImpl2).onSurfaceVariant, MaterialTheme.getColorScheme(composerImpl2).onSurfaceVariant, MaterialTheme.getColorScheme(composerImpl2).onSurfaceVariant);
                z2 = false;
                composerImpl2.end(false);
                composerImpl2.end(false);
                tileColors = tileColors3;
            }
            composerImpl2.end(z2);
            composerImpl2.startReplaceGroup(1038151710);
            final TileUiState tileUiState3 = tileUiState;
            RoundedCornerShape m845animateShaperAjV9yQ = TileDefaults.m845animateShaperAjV9yQ(tileUiState3.state, TileDefaults.ActiveTileCornerRadius, "QSTileIconCornerRadius", composerImpl2);
            composerImpl2.end(z2);
            long j = (z || !z8) ? tileColors.iconBackground : tileColors.background;
            composerImpl2.startReplaceGroup(1797409189);
            int i8 = i4 & 14;
            boolean z9 = i8 == 4 ? true : z2;
            Object rememberedValue2 = composerImpl2.rememberedValue();
            Object obj7 = obj3;
            if (z9 || rememberedValue2 == obj7) {
                obj4 = obj7;
                z3 = true;
                z4 = z2;
                rememberedValue2 = new TileKt$Tile$1$1(1, tileViewModel, TileViewModel.class, "onClick", "onClick(Lcom/android/systemui/animation/Expandable;)V", 0);
                composerImpl2.updateRememberedValue(rememberedValue2);
            } else {
                obj4 = obj7;
                z4 = z2;
                z3 = true;
            }
            KFunction kFunction = (KFunction) rememberedValue2;
            composerImpl2.end(z4);
            composerImpl2.startReplaceGroup(1797409226);
            boolean z10 = i8 == 4 ? z3 : z4;
            Object rememberedValue3 = composerImpl2.rememberedValue();
            if (z10 || rememberedValue3 == obj4) {
                Object tileKt$Tile$2$1 = new TileKt$Tile$2$1(1, tileViewModel, TileViewModel.class, "onLongClick", "onLongClick(Lcom/android/systemui/animation/Expandable;)V", 0);
                composerImpl2.updateRememberedValue(tileKt$Tile$2$1);
                rememberedValue3 = tileKt$Tile$2$1;
            }
            composerImpl2.end(z4);
            final TileColors tileColors4 = tileColors;
            final MutableState mutableState2 = mutableState;
            composerImpl = composerImpl2;
            i3 = i;
            modifier2 = modifier;
            z5 = z;
            m846TileContainerpzZJ40c(j, m845animateShaperAjV9yQ, z, tileUiState3, modifier, (Function1) kFunction, (Function1) ((KFunction) rememberedValue3), ComposableLambdaKt.rememberComposableLambda(1335826219, new Function4() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt$Tile$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj8, Object obj9, Object obj10, Object obj11) {
                    Icon resource;
                    BoxScope boxScope = (BoxScope) obj8;
                    final Expandable expandable = (Expandable) obj9;
                    ((Number) obj11).intValue();
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    Supplier supplier3 = TileUiState.this.icon;
                    ComposerImpl composerImpl3 = (ComposerImpl) ((Composer) obj10);
                    composerImpl3.startReplaceGroup(883311126);
                    Context context = (Context) composerImpl3.consume(AndroidCompositionLocals_androidKt.LocalContext);
                    QSTile.Icon icon = (QSTile.Icon) supplier3.get();
                    if (icon != null) {
                        resource = icon instanceof QSTileImpl.ResourceIcon ? new Icon.Resource(((QSTileImpl.ResourceIcon) icon).mResId, null) : new Icon.Loaded(icon.getDrawable(context), null);
                    } else {
                        resource = new Icon.Resource(R.drawable.ic_error_outline, null);
                    }
                    composerImpl3.end(false);
                    if (z) {
                        composerImpl3.startReplaceGroup(824019364);
                        CommonTileKt.m843SmallTileContentcf5BqRc(boxScope.align(Modifier.Companion.$$INSTANCE, Alignment.Companion.Center), resource, tileColors4.icon, false, composerImpl3, 0, 8);
                        composerImpl3.end(false);
                    } else {
                        composerImpl3.startReplaceGroup(824019556);
                        float f2 = TileDefaults.ActiveIconCornerRadius;
                        int i9 = TileUiState.this.state;
                        composerImpl3.startReplaceGroup(-1016753111);
                        RoundedCornerShape m845animateShaperAjV9yQ2 = TileDefaults.m845animateShaperAjV9yQ(i9, TileDefaults.ActiveIconCornerRadius, "QSTileCornerRadius", composerImpl3);
                        composerImpl3.end(false);
                        TileUiState tileUiState4 = TileUiState.this;
                        String str5 = tileUiState4.label;
                        boolean z11 = ((QSTile.State) mutableState2.getValue()).handlesSecondaryClick;
                        AccessibilityUiState accessibilityUiState = TileUiState.this.accessibilityUiState;
                        TileColors tileColors5 = tileColors4;
                        composerImpl3.startReplaceGroup(824019934);
                        boolean changed2 = composerImpl3.changed(mutableState2) | composerImpl3.changed(tileViewModel);
                        final TileViewModel tileViewModel2 = tileViewModel;
                        final State state2 = mutableState2;
                        Object rememberedValue4 = composerImpl3.rememberedValue();
                        if (changed2 || rememberedValue4 == Composer.Companion.Empty) {
                            rememberedValue4 = new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt$Tile$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    if (((QSTile.State) state2.getValue()).handlesSecondaryClick) {
                                        TileViewModel.this.tile.secondaryClick(null);
                                    }
                                    return Unit.INSTANCE;
                                }
                            };
                            composerImpl3.updateRememberedValue(rememberedValue4);
                        }
                        composerImpl3.end(false);
                        final TileViewModel tileViewModel3 = tileViewModel;
                        CommonTileKt.LargeTileContent(str5, tileUiState4.secondaryLabel, resource, tileColors5, accessibilityUiState, z11, m845animateShaperAjV9yQ2, (Function0) rememberedValue4, new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt$Tile$3.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                TileViewModel tileViewModel4 = TileViewModel.this;
                                tileViewModel4.tile.longClick(expandable);
                                return Unit.INSTANCE;
                            }
                        }, composerImpl3, 0, 0);
                        composerImpl3.end(false);
                    }
                    return Unit.INSTANCE;
                }
            }, composerImpl2), composerImpl, ((i4 << 3) & 896) | 12582912 | ((i4 << 6) & 57344), 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt$Tile$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj8, Object obj9) {
                    ((Number) obj9).intValue();
                    TileKt.Tile(TileViewModel.this, z5, modifier2, (Composer) obj8, RecomposeScopeImplKt.updateChangedFlags(i3 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a1  */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt$TileContainer$3, kotlin.jvm.internal.Lambda] */
    /* renamed from: TileContainer-pzZJ40c, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m846TileContainerpzZJ40c(final long r25, final androidx.compose.ui.graphics.Shape r27, final boolean r28, final com.android.systemui.qs.panels.ui.viewmodel.TileUiState r29, androidx.compose.ui.Modifier r30, kotlin.jvm.functions.Function1 r31, kotlin.jvm.functions.Function1 r32, final kotlin.jvm.functions.Function4 r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instructions count: 401
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt.m846TileContainerpzZJ40c(long, androidx.compose.ui.graphics.Shape, boolean, com.android.systemui.qs.panels.ui.viewmodel.TileUiState, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TileLazyGrid(final androidx.compose.foundation.lazy.grid.GridCells r21, androidx.compose.ui.Modifier r22, androidx.compose.foundation.lazy.grid.LazyGridState r23, androidx.compose.foundation.layout.PaddingValues r24, final kotlin.jvm.functions.Function1 r25, androidx.compose.runtime.Composer r26, final int r27, final int r28) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.ui.compose.infinitegrid.TileKt.TileLazyGrid(androidx.compose.foundation.lazy.grid.GridCells, androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.grid.LazyGridState, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }
}
