package com.android.systemui.statusbar.phone;

import android.content.Context;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.model.SysUiState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUIDialogFactory {
    public final Context applicationContext;
    public final BroadcastDispatcher broadcastDispatcher;
    public final SystemUIDialogManager dialogManager;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final SysUiState sysUiState;

    public SystemUIDialogFactory(Context context, SystemUIDialogManager systemUIDialogManager, SysUiState sysUiState, BroadcastDispatcher broadcastDispatcher, DialogTransitionAnimator dialogTransitionAnimator) {
        this.applicationContext = context;
        this.dialogManager = systemUIDialogManager;
        this.sysUiState = sysUiState;
        this.broadcastDispatcher = broadcastDispatcher;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
    }
}
