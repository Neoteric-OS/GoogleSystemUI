package com.android.systemui.qs.panels.ui.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.animation.scene.ContentScope;
import com.android.systemui.qs.panels.ui.viewmodel.TileGridViewModel;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TileGridKt {
    public static final void TileGrid(final ContentScope contentScope, final TileGridViewModel tileGridViewModel, Modifier modifier, final Function0 function0, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(705899080);
        if ((i2 & 2) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(tileGridViewModel.gridLayout, composerImpl);
        MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(tileGridViewModel.tileViewModels, EmptyList.INSTANCE, composerImpl, 56);
        ((GridLayout) collectAsStateWithLifecycle.getValue()).TileGrid(contentScope, (List) collectAsStateWithLifecycle2.getValue(), modifier, function0, composerImpl, (i & 14) | 64 | (i & 896) | (i & 7168));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier2 = modifier;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.TileGridKt$TileGrid$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    TileGridKt.TileGrid(ContentScope.this, tileGridViewModel, modifier2, function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
