package com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class ScreenRecordChipViewModel$createDelegate$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        m872invoke();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: collision with other method in class */
    public final void m872invoke() {
        ScreenRecordChipViewModel screenRecordChipViewModel = (ScreenRecordChipViewModel) this.receiver;
        screenRecordChipViewModel.getClass();
        LogLevel logLevel = LogLevel.INFO;
        ScreenRecordChipViewModel$stopRecordingFromDialog$2 screenRecordChipViewModel$stopRecordingFromDialog$2 = new Function1() { // from class: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$stopRecordingFromDialog$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Stop recording requested from dialog";
            }
        };
        LogBuffer logBuffer = screenRecordChipViewModel.logger;
        logBuffer.commit(logBuffer.obtain("ScreenRecordVM", logLevel, screenRecordChipViewModel$stopRecordingFromDialog$2, null));
        screenRecordChipViewModel.chipTransitionHelper.onActivityStoppedFromDialog();
        screenRecordChipViewModel.shareToAppChipViewModel.chipTransitionHelper.onActivityStoppedFromDialog();
        screenRecordChipViewModel.interactor.stopRecording();
    }
}
