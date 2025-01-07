package com.android.systemui.statusbar.chips.ui.viewmodel;

import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChipTransitionHelper {
    public final SharedFlowImpl activityStoppedFromDialogEvent;
    public final CoroutineScope scope;
    public final ReadonlyStateFlow wasActivityRecentlyStoppedFromDialog;

    public ChipTransitionHelper(CoroutineScope coroutineScope) {
        this.scope = coroutineScope;
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        this.activityStoppedFromDialogEvent = MutableSharedFlow$default;
        this.wasActivityRecentlyStoppedFromDialog = FlowKt.stateIn(FlowKt.transformLatest(MutableSharedFlow$default, new ChipTransitionHelper$wasActivityRecentlyStoppedFromDialog$1(3, null)), coroutineScope, SharingStarted.Companion.Lazily, Boolean.FALSE);
    }

    public final ReadonlyStateFlow createChipFlow(ReadonlyStateFlow readonlyStateFlow) {
        return FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, this.wasActivityRecentlyStoppedFromDialog, new ChipTransitionHelper$createChipFlow$1(3, null)), this.scope, SharingStarted.Companion.WhileSubscribed$default(3), new OngoingActivityChipModel.Hidden(true));
    }

    public final void onActivityStoppedFromDialog() {
        BuildersKt.launch$default(this.scope, null, null, new ChipTransitionHelper$onActivityStoppedFromDialog$1(this, null), 3);
    }
}
