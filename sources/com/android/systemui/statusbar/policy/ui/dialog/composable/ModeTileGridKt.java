package com.android.systemui.statusbar.policy.ui.dialog.composable;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.foundation.lazy.grid.LazyGridDslKt;
import androidx.compose.foundation.lazy.grid.LazyGridIntervalContent;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.systemui.statusbar.policy.ui.dialog.viewmodel.ModeTileViewModel;
import com.android.systemui.statusbar.policy.ui.dialog.viewmodel.ModesDialogViewModel;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ModeTileGridKt {
    public static final void ModeTileGrid(final ModesDialogViewModel modesDialogViewModel, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(708429859);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(modesDialogViewModel.tiles, EmptyList.INSTANCE, composerImpl, 56);
        GridCells.Fixed fixed = new GridCells.Fixed(1);
        Modifier m110heightInVpY3zN4$default = SizeKt.m110heightInVpY3zN4$default(SizeKt.fillMaxWidth(Modifier.Companion.$$INSTANCE, 1.0f), 0.0f, 320, 1);
        float f = 8;
        Arrangement.SpacedAligned m78spacedBy0680j_4 = Arrangement.m78spacedBy0680j_4(f);
        Arrangement.SpacedAligned m78spacedBy0680j_42 = Arrangement.m78spacedBy0680j_4(f);
        composerImpl.startReplaceGroup(-1764029361);
        boolean changed = composerImpl.changed(collectAsStateWithLifecycle);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function1() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileGridKt$ModeTileGrid$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    int size = ((List) collectAsStateWithLifecycle.getValue()).size();
                    final State state = collectAsStateWithLifecycle;
                    ((LazyGridIntervalContent) ((LazyGridScope) obj)).items(size, new Function1() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileGridKt$ModeTileGrid$1$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return ((ModeTileViewModel) ((List) State.this.getValue()).get(((Number) obj2).intValue())).id;
                        }
                    }, null, new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridScope$items$1
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                            ((Number) obj2).intValue();
                            return null;
                        }
                    }, new ComposableLambdaImpl(1432844558, true, new Function4() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileGridKt$ModeTileGrid$1$1.2
                        {
                            super(4);
                        }

                        @Override // kotlin.jvm.functions.Function4
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            int intValue = ((Number) obj3).intValue();
                            Composer composer2 = (Composer) obj4;
                            int intValue2 = ((Number) obj5).intValue();
                            if ((intValue2 & 112) == 0) {
                                intValue2 |= ((ComposerImpl) composer2).changed(intValue) ? 32 : 16;
                            }
                            if ((intValue2 & 721) == 144) {
                                ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                                if (composerImpl2.getSkipping()) {
                                    composerImpl2.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            OpaqueKey opaqueKey2 = ComposerKt.invocation;
                            ModeTileKt.ModeTile((ModeTileViewModel) ((List) State.this.getValue()).get(intValue), composer2, 0);
                            return Unit.INSTANCE;
                        }
                    }));
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        LazyGridDslKt.LazyVerticalGrid(1769520, 412, null, m78spacedBy0680j_42, m78spacedBy0680j_4, null, fixed, null, composerImpl, m110heightInVpY3zN4$default, (Function1) rememberedValue, false, false);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileGridKt$ModeTileGrid$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ModeTileGridKt.ModeTileGrid(ModesDialogViewModel.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
