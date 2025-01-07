package com.android.systemui.display.ui.viewmodel;

import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl;
import com.android.systemui.display.ui.view.MirroringConfirmationDialogDelegate;
import com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectingDisplayViewModel implements CoreStartable {
    public final CoroutineDispatcher bgDispatcher;
    public final MirroringConfirmationDialogDelegate.Factory bottomSheetFactory;
    public final ConnectedDisplayInteractorImpl connectedDisplayInteractor;
    public final Context context;
    public SystemUIBottomSheetDialog dialog;
    public final CoroutineScope scope;

    public ConnectingDisplayViewModel(Context context, ConnectedDisplayInteractorImpl connectedDisplayInteractorImpl, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, MirroringConfirmationDialogDelegate.Factory factory) {
        this.context = context;
        this.connectedDisplayInteractor = connectedDisplayInteractorImpl;
        this.scope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        this.bottomSheetFactory = factory;
    }

    public final void dismissDialog$1() {
        SystemUIBottomSheetDialog systemUIBottomSheetDialog = this.dialog;
        if (systemUIBottomSheetDialog != null) {
            systemUIBottomSheetDialog.dismiss();
        }
        this.dialog = null;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        ConnectedDisplayInteractorImpl connectedDisplayInteractorImpl = this.connectedDisplayInteractor;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = connectedDisplayInteractorImpl.pendingDisplay;
        Flow flow = connectedDisplayInteractorImpl.concurrentDisplaysInProgress;
        int i = Duration.$r8$clinit;
        FlowKt.launchIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(FlowKt.debounce(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, DelayKt.m1785toDelayMillisLRDsOJo(DurationKt.toDuration(200, DurationUnit.MILLISECONDS))), flow, new ConnectingDisplayViewModel$start$1(this, null)), this.scope);
    }
}
