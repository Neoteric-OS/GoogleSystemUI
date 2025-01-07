package com.android.systemui.keyboard.shortcut.domain.interactor;

import com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperStateRepository;
import com.android.systemui.model.SysUiState;
import com.android.systemui.settings.DisplayTracker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperStateInteractor {
    public final CoroutineScope backgroundScope;
    public final DisplayTracker displayTracker;
    public final ShortcutHelperStateRepository repository;
    public final SysUiState sysUiState;

    public ShortcutHelperStateInteractor(DisplayTracker displayTracker, CoroutineScope coroutineScope, SysUiState sysUiState, ShortcutHelperStateRepository shortcutHelperStateRepository) {
        this.displayTracker = displayTracker;
        this.backgroundScope = coroutineScope;
        this.sysUiState = sysUiState;
        this.repository = shortcutHelperStateRepository;
    }

    public final void setSysUiStateFlagEnabled(boolean z) {
        BuildersKt.launch$default(this.backgroundScope, null, null, new ShortcutHelperStateInteractor$setSysUiStateFlagEnabled$1(this, z, null), 3);
    }
}
