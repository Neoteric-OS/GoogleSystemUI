package com.android.systemui;

import android.os.Trace;
import com.android.systemui.ScreenDecorations;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ScreenDecorations$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScreenDecorations f$0;

    public /* synthetic */ ScreenDecorations$$ExternalSyntheticLambda2(ScreenDecorations screenDecorations, int i) {
        this.$r8$classId = i;
        this.f$0 = screenDecorations;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ScreenDecorations screenDecorations = this.f$0;
        switch (i) {
            case 0:
                screenDecorations.getClass();
                Trace.beginSection("ScreenDecorations#onConfigurationChanged");
                screenDecorations.mContext.getDisplay().getDisplayInfo(screenDecorations.mDisplayInfo);
                if (!ScreenDecorations.displaySizeChanged(screenDecorations.mDisplaySize, screenDecorations.mDisplayInfo)) {
                    int i2 = screenDecorations.mRotation;
                    screenDecorations.mPendingConfigChange = false;
                    screenDecorations.updateConfiguration();
                    int i3 = screenDecorations.mRotation;
                    if (i2 != i3) {
                        screenDecorations.mLogger.logRotationChanged(i2, i3);
                    }
                    screenDecorations.setupDecorations();
                    if (screenDecorations.mOverlays != null) {
                        screenDecorations.updateLayoutParams();
                    }
                    Trace.endSection();
                    break;
                } else {
                    screenDecorations.mPendingManualConfigUpdate = true;
                    break;
                }
            case 1:
                screenDecorations.removeAllOverlays();
                screenDecorations.removeHwcOverlay();
                screenDecorations.setupDecorations();
                break;
            case 2:
                int i4 = screenDecorations.mFaceScanningViewId;
                if (screenDecorations.getOverlayView(i4) != null) {
                    screenDecorations.updateOverlayProviderViews(new Integer[]{Integer.valueOf(i4)});
                    break;
                } else {
                    screenDecorations.setupDecorations();
                    break;
                }
            case 3:
                screenDecorations.startOnScreenDecorationsThread();
                break;
            default:
                screenDecorations.removeAllOverlays();
                screenDecorations.removeHwcOverlay();
                screenDecorations.startOnScreenDecorationsThread();
                ScreenDecorations.AnonymousClass4 anonymousClass4 = screenDecorations.mColorInversionSetting;
                screenDecorations.updateColorInversion(anonymousClass4 != null ? anonymousClass4.getValue() : 0);
                break;
        }
    }
}
