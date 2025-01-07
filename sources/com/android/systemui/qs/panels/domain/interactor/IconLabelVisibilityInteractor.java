package com.android.systemui.qs.panels.domain.interactor;

import com.android.systemui.log.LogBuffer;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconLabelVisibilityInteractor {
    public final LogBuffer logBuffer;
    public final ReadonlyStateFlow showLabels;

    public IconLabelVisibilityInteractor(QSPreferencesInteractor qSPreferencesInteractor, LogBuffer logBuffer, CoroutineScope coroutineScope) {
        this.logBuffer = logBuffer;
        FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(qSPreferencesInteractor.showLabels, new IconLabelVisibilityInteractor$showLabels$1(this, null), 0), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
    }
}
