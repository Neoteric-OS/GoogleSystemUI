package com.android.systemui.keyboard.stickykeys.ui;

import androidx.activity.ComponentDialog;
import com.android.systemui.keyboard.stickykeys.StickyKeysLogger;
import com.android.systemui.keyboard.stickykeys.ui.viewmodel.StickyKeysIndicatorViewModel;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StickyKeysIndicatorCoordinator {
    public final CoroutineScope applicationScope;
    public ComponentDialog dialog;
    public final StickyKeyDialogFactory stickyKeyDialogFactory;
    public final StickyKeysLogger stickyKeysLogger;
    public final StickyKeysIndicatorViewModel viewModel;

    public StickyKeysIndicatorCoordinator(CoroutineScope coroutineScope, StickyKeyDialogFactory stickyKeyDialogFactory, StickyKeysIndicatorViewModel stickyKeysIndicatorViewModel, StickyKeysLogger stickyKeysLogger) {
        this.applicationScope = coroutineScope;
        this.stickyKeyDialogFactory = stickyKeyDialogFactory;
        this.viewModel = stickyKeysIndicatorViewModel;
        this.stickyKeysLogger = stickyKeysLogger;
    }

    public final void startListening() {
        BuildersKt.launch$default(this.applicationScope, null, null, new StickyKeysIndicatorCoordinator$startListening$1(this, null), 3);
    }
}
