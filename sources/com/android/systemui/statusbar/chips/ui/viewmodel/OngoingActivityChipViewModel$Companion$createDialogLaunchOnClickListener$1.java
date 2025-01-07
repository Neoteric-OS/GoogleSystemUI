package com.android.systemui.statusbar.chips.ui.viewmodel;

import android.view.View;
import com.android.systemui.animation.DialogCuj;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.chips.ui.view.ChipBackgroundContainer;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OngoingActivityChipViewModel$Companion$createDialogLaunchOnClickListener$1 implements View.OnClickListener {
    public final /* synthetic */ DialogCuj $cuj;
    public final /* synthetic */ SystemUIDialog.Delegate $dialogDelegate;
    public final /* synthetic */ DialogTransitionAnimator $dialogTransitionAnimator;
    public final /* synthetic */ LogBuffer $logger;
    public final /* synthetic */ String $tag;

    public OngoingActivityChipViewModel$Companion$createDialogLaunchOnClickListener$1(LogBuffer logBuffer, String str, SystemUIDialog.Delegate delegate, DialogTransitionAnimator dialogTransitionAnimator, DialogCuj dialogCuj) {
        this.$logger = logBuffer;
        this.$tag = str;
        this.$dialogDelegate = delegate;
        this.$dialogTransitionAnimator = dialogTransitionAnimator;
        this.$cuj = dialogCuj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        LogBuffer logBuffer = this.$logger;
        logBuffer.commit(logBuffer.obtain(this.$tag, LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipViewModel$Companion$createDialogLaunchOnClickListener$1.2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Chip clicked";
            }
        }, null));
        SystemUIDialog createDialog = this.$dialogDelegate.createDialog();
        ChipBackgroundContainer chipBackgroundContainer = (ChipBackgroundContainer) view.requireViewById(R.id.ongoing_activity_chip_background);
        DialogTransitionAnimator dialogTransitionAnimator = this.$dialogTransitionAnimator;
        DialogCuj dialogCuj = this.$cuj;
        TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
        dialogTransitionAnimator.showFromView(createDialog, chipBackgroundContainer, dialogCuj, false);
    }
}
