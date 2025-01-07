package com.android.systemui.keyboard.stickykeys.ui.viewmodel;

import com.android.systemui.keyboard.data.repository.KeyboardRepositoryImpl;
import com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepository;
import kotlin.collections.MapsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StickyKeysIndicatorViewModel {
    public final ReadonlyStateFlow indicatorContent;

    public StickyKeysIndicatorViewModel(StickyKeysRepository stickyKeysRepository, KeyboardRepositoryImpl keyboardRepositoryImpl, CoroutineScope coroutineScope) {
        this.indicatorContent = FlowKt.stateIn(FlowKt.transformLatest(FlowKt.transformLatest(keyboardRepositoryImpl.isAnyKeyboardConnected, new StickyKeysIndicatorViewModel$special$$inlined$flatMapLatest$1(null, stickyKeysRepository)), new StickyKeysIndicatorViewModel$special$$inlined$flatMapLatest$2(null, stickyKeysRepository)), coroutineScope, SharingStarted.Companion.Lazily, MapsKt.emptyMap());
    }
}
