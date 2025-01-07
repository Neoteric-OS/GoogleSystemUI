package com.android.systemui.qs.panels.ui.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import com.android.systemui.qs.panels.ui.compose.infinitegrid.InfiniteGridLayout;
import com.android.systemui.qs.panels.ui.viewmodel.PaginatedGridViewModel;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PaginatedGridLayout implements GridLayout {
    public final InfiniteGridLayout delegateGridLayout;
    public final PaginatedGridViewModel viewModel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Dimensions {
        public static final float FooterHeight = 48;
        public static final float InterPageSpacing = 16;
    }

    public PaginatedGridLayout(PaginatedGridViewModel paginatedGridViewModel, InfiniteGridLayout infiniteGridLayout) {
        this.viewModel = paginatedGridViewModel;
        this.delegateGridLayout = infiniteGridLayout;
    }

    @Override // com.android.systemui.qs.panels.ui.compose.GridLayout
    public final void EditTileGrid(final List list, final Modifier modifier, final Function2 function2, final Function1 function1, final Function1 function12, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(633415467);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        this.delegateGridLayout.EditTileGrid(list, modifier, function2, function1, function12, composerImpl, (i & 112) | 8 | (i & 896) | (i & 7168) | (57344 & i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.PaginatedGridLayout$EditTileGrid$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PaginatedGridLayout.this.EditTileGrid(list, modifier, function2, function1, function12, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x005c, code lost:
    
        if (r6 == androidx.compose.runtime.Composer.Companion.Empty) goto L6;
     */
    /* JADX WARN: Type inference failed for: r4v27, types: [com.android.systemui.qs.panels.ui.compose.PaginatedGridLayout$TileGrid$2$2$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.android.systemui.qs.panels.ui.compose.PaginatedGridLayout$TileGrid$2$1, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.qs.panels.ui.compose.GridLayout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void TileGrid(final com.android.compose.animation.scene.ContentScope r30, final java.util.List r31, final androidx.compose.ui.Modifier r32, final kotlin.jvm.functions.Function0 r33, androidx.compose.runtime.Composer r34, final int r35) {
        /*
            Method dump skipped, instructions count: 722
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.ui.compose.PaginatedGridLayout.TileGrid(com.android.compose.animation.scene.ContentScope, java.util.List, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }
}
