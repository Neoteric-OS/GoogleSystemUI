package com.android.systemui.keyguard.ui.binder;

import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.ui.view.SideFpsProgressBar;
import com.android.systemui.keyguard.ui.viewmodel.SideFpsProgressBarViewModel;
import com.android.systemui.log.SideFpsLogger;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SideFpsProgressBarViewBinder implements CoreStartable {
    public final SideFpsLogger logger;
    public final SideFpsProgressBar view;
    public final SideFpsProgressBarViewModel viewModel;

    public SideFpsProgressBarViewBinder(SideFpsProgressBarViewModel sideFpsProgressBarViewModel, SideFpsProgressBar sideFpsProgressBar, CoroutineScope coroutineScope, SideFpsLogger sideFpsLogger, CommandRegistry commandRegistry) {
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
