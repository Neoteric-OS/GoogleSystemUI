package com.android.wm.shell.windowdecor;

import android.net.Uri;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DesktopModeWindowDecoration$$ExternalSyntheticLambda6 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DesktopModeWindowDecoration f$0;

    public /* synthetic */ DesktopModeWindowDecoration$$ExternalSyntheticLambda6(DesktopModeWindowDecoration desktopModeWindowDecoration, int i) {
        this.$r8$classId = i;
        this.f$0 = desktopModeWindowDecoration;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                DesktopModeWindowDecoration desktopModeWindowDecoration = this.f$0;
                desktopModeWindowDecoration.mOpenInBrowserClickListener.accept((Uri) obj);
                desktopModeWindowDecoration.mHandler.removeCallbacks(desktopModeWindowDecoration.mCapturedLinkExpiredRunnable);
                return Unit.INSTANCE;
            default:
                DesktopModeWindowDecoration desktopModeWindowDecoration2 = this.f$0;
                desktopModeWindowDecoration2.getClass();
                desktopModeWindowDecoration2.mIsMaximizeMenuHovered = ((Boolean) obj).booleanValue();
                desktopModeWindowDecoration2.onMaximizeHoverStateChanged();
                return null;
        }
    }
}
