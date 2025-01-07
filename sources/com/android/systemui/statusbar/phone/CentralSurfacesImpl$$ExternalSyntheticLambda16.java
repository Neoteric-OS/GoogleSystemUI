package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.data.model.StatusBarMode;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda7;
import com.android.wm.shell.bubbles.Bubbles;
import com.android.wm.shell.common.HandlerExecutor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$$ExternalSyntheticLambda16 implements Consumer {
    public final /* synthetic */ CentralSurfacesImpl f$0;
    public final /* synthetic */ StatusBarMode f$1;

    public /* synthetic */ CentralSurfacesImpl$$ExternalSyntheticLambda16(CentralSurfacesImpl centralSurfacesImpl, StatusBarMode statusBarMode) {
        this.f$0 = centralSurfacesImpl;
        this.f$1 = statusBarMode;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        CentralSurfacesImpl centralSurfacesImpl = this.f$0;
        StatusBarMode statusBarMode = this.f$1;
        Bubbles bubbles = (Bubbles) obj;
        centralSurfacesImpl.getClass();
        BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubbles;
        ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda7(bubblesImpl, (statusBarMode == StatusBarMode.LIGHTS_OUT || statusBarMode == StatusBarMode.LIGHTS_OUT_TRANSPARENT || centralSurfacesImpl.mStatusBarWindowState == 2) ? false : true, 1));
    }
}
