package com.android.systemui.communal.ui.compose;

import androidx.compose.foundation.lazy.grid.LazyGridState;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DragAndDropTargetState {
    public final float autoScrollThreshold;
    public final ContentListState contentListState;
    public final long contentOffset;
    public Integer placeHolderIndex;
    public final ContextScope scope;
    public final LazyGridState state;
    public final CommunalContentModel.WidgetPlaceholder placeHolder = new CommunalContentModel.WidgetPlaceholder();
    public final BufferedChannel scrollChannel = ChannelKt.Channel$default(0, null, null, 7);

    public DragAndDropTargetState(LazyGridState lazyGridState, long j, ContentListState contentListState, float f, ContextScope contextScope) {
        this.state = lazyGridState;
        this.contentOffset = j;
        this.contentListState = contentListState;
        this.autoScrollThreshold = f;
        this.scope = contextScope;
    }
}
