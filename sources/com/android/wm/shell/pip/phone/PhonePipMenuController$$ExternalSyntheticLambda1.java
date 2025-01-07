package com.android.wm.shell.pip.phone;

import com.android.wm.shell.common.pip.PipUiEventLogger;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PhonePipMenuController$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int f$0;

    public /* synthetic */ PhonePipMenuController$$ExternalSyntheticLambda1(int i) {
        this.f$0 = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.f$0;
        PipTouchHandler pipTouchHandler = PipTouchHandler.this;
        pipTouchHandler.mMenuState = i;
        pipTouchHandler.updateMovementBounds();
        pipTouchHandler.onRegistrationChanged(i == 0);
        PipUiEventLogger pipUiEventLogger = pipTouchHandler.mPipUiEventLogger;
        if (i == 0) {
            pipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_HIDE_MENU);
        } else if (i == 1) {
            pipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_SHOW_MENU);
        }
    }
}
