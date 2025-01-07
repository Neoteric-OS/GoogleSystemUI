package com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class ShareToAppChipViewModel$createShareToAppDialogDelegate$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        m873invoke();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: collision with other method in class */
    public final void m873invoke() {
        ShareToAppChipViewModel shareToAppChipViewModel = (ShareToAppChipViewModel) this.receiver;
        shareToAppChipViewModel.getClass();
        LogLevel logLevel = LogLevel.INFO;
        ShareToAppChipViewModel$stopProjectingFromDialog$2 shareToAppChipViewModel$stopProjectingFromDialog$2 = new Function1() { // from class: com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel.ShareToAppChipViewModel$stopProjectingFromDialog$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Stop sharing requested from dialog";
            }
        };
        LogBuffer logBuffer = shareToAppChipViewModel.logger;
        logBuffer.commit(logBuffer.obtain("ShareToAppVM", logLevel, shareToAppChipViewModel$stopProjectingFromDialog$2, null));
        shareToAppChipViewModel.chipTransitionHelper.onActivityStoppedFromDialog();
        shareToAppChipViewModel.mediaProjectionChipInteractor.stopProjecting();
    }
}
