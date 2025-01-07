package com.android.systemui;

import android.os.Trace;
import android.view.View;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.decor.OverlayWindow;
import com.android.systemui.decor.ScreenDecorCommand;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ScreenDecorations$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScreenDecorations f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ ScreenDecorations$$ExternalSyntheticLambda7(ScreenDecorations screenDecorations, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = screenDecorations;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ScreenDecorations screenDecorations = this.f$0;
                View view = (View) this.f$1;
                if (screenDecorations.mOverlays != null && screenDecorations.shouldOptimizeVisibility()) {
                    Trace.beginSection("ScreenDecorations#updateOverlayWindowVisibilityIfViewExists");
                    for (OverlayWindow overlayWindow : screenDecorations.mOverlays) {
                        if (overlayWindow != null && overlayWindow.getView(view.getId()) != null) {
                            overlayWindow.rootView.setVisibility(screenDecorations.getWindowVisibility(overlayWindow, true));
                            Trace.endSection();
                            break;
                        }
                    }
                    Trace.endSection();
                    break;
                }
                break;
            case 1:
                ScreenDecorations screenDecorations2 = this.f$0;
                ScreenDecorCommand screenDecorCommand = (ScreenDecorCommand) this.f$1;
                ScreenDecorHwcLayer screenDecorHwcLayer = screenDecorations2.mScreenDecorHwcLayer;
                if (screenDecorHwcLayer != null) {
                    int intValue = screenDecorCommand.getColor().intValue();
                    if (screenDecorHwcLayer.debug && screenDecorHwcLayer.color != intValue) {
                        screenDecorHwcLayer.color = intValue;
                        screenDecorHwcLayer.paint.setColor(intValue);
                        screenDecorHwcLayer.updateColors();
                        screenDecorHwcLayer.invalidate();
                    }
                }
                ScreenDecorations.AnonymousClass4 anonymousClass4 = screenDecorations2.mColorInversionSetting;
                screenDecorations2.updateColorInversion(anonymousClass4 != null ? anonymousClass4.getValue() : 0);
                break;
            default:
                ScreenDecorations screenDecorations3 = this.f$0;
                FaceScanningOverlay faceScanningOverlay = (FaceScanningOverlay) this.f$1;
                screenDecorations3.getClass();
                Trace.beginSection("ScreenDecorations#hideOverlayRunnable");
                screenDecorations3.updateOverlayWindowVisibilityIfViewExists(faceScanningOverlay.findViewById(screenDecorations3.mFaceScanningViewId));
                Trace.endSection();
                break;
        }
    }
}
