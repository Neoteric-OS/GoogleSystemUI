package com.android.systemui.shade;

import com.android.systemui.plugins.qs.QS;
import com.android.systemui.shade.QuickSettingsControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class QuickSettingsControllerImpl$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ QuickSettingsControllerImpl$$ExternalSyntheticLambda3(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                QuickSettingsControllerImpl quickSettingsControllerImpl = (QuickSettingsControllerImpl) obj;
                if (!quickSettingsControllerImpl.mSplitShadeEnabled) {
                    quickSettingsControllerImpl.onExpansionStarted();
                    if (!quickSettingsControllerImpl.getExpanded()) {
                        if (quickSettingsControllerImpl.isExpansionEnabled()) {
                            quickSettingsControllerImpl.mLockscreenGestureLogger.write(195, 0, 0);
                            quickSettingsControllerImpl.flingQs(0.0f, 0, null, true);
                            break;
                        }
                    } else {
                        quickSettingsControllerImpl.flingQs(0.0f, 1, null, true);
                        break;
                    }
                }
                break;
            case 1:
                QS qs = ((QuickSettingsControllerImpl) obj).mQs;
                if (qs != null) {
                    qs.animateHeaderSlidingOut();
                    break;
                }
                break;
            default:
                QuickSettingsControllerImpl quickSettingsControllerImpl2 = ((QuickSettingsControllerImpl.NsslOverscrollTopChangedListener) obj).this$0;
                quickSettingsControllerImpl2.mStackScrollerOverscrolling = false;
                QS qs2 = quickSettingsControllerImpl2.mQs;
                if (qs2 != null) {
                    qs2.setOverscrolling(false);
                }
                quickSettingsControllerImpl2.updateQsState$1();
                break;
        }
    }
}
