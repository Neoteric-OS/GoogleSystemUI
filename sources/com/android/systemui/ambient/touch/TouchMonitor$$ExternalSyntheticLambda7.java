package com.android.systemui.ambient.touch;

import android.graphics.Region;
import android.os.RemoteException;
import android.view.ISystemGestureExclusionListener;
import com.android.systemui.ambient.touch.TouchMonitor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class TouchMonitor$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TouchMonitor f$0;

    public /* synthetic */ TouchMonitor$$ExternalSyntheticLambda7(TouchMonitor touchMonitor, int i) {
        this.$r8$classId = i;
        this.f$0 = touchMonitor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final TouchMonitor touchMonitor = this.f$0;
        switch (i) {
            case 0:
                touchMonitor.getClass();
                try {
                    TouchMonitor.AnonymousClass2 anonymousClass2 = touchMonitor.mGestureExclusionListener;
                    if (anonymousClass2 != null) {
                        touchMonitor.mWindowManagerService.unregisterSystemGestureExclusionListener(anonymousClass2, touchMonitor.mDisplayId);
                        touchMonitor.mGestureExclusionListener = null;
                        break;
                    }
                } catch (RemoteException e) {
                    touchMonitor.mLogger.e("unregisterSystemGestureExclusionListener: failed", e);
                    return;
                }
                break;
            case 1:
                touchMonitor.mActiveTouchSessions.forEach(new TouchMonitor$$ExternalSyntheticLambda2(1));
                touchMonitor.mActiveTouchSessions.clear();
                break;
            default:
                touchMonitor.getClass();
                try {
                    ISystemGestureExclusionListener iSystemGestureExclusionListener = new ISystemGestureExclusionListener.Stub() { // from class: com.android.systemui.ambient.touch.TouchMonitor.2
                        public final void onSystemGestureExclusionChanged(int i2, Region region, Region region2) {
                            TouchMonitor.this.mExclusionRect = region.getBounds();
                        }
                    };
                    touchMonitor.mGestureExclusionListener = iSystemGestureExclusionListener;
                    touchMonitor.mWindowManagerService.registerSystemGestureExclusionListener(iSystemGestureExclusionListener, touchMonitor.mDisplayId);
                    break;
                } catch (RemoteException e2) {
                    touchMonitor.mLogger.e("Failed to register gesture exclusion listener", e2);
                }
        }
    }
}
