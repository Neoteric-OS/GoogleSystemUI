package com.android.systemui.accessibility;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import com.android.systemui.accessibility.AccessibilityLogger;
import com.android.systemui.accessibility.MagnificationImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MagnificationImpl$4$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MagnificationImpl.AnonymousClass3 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ MagnificationImpl$4$$ExternalSyntheticLambda0(MagnificationImpl.AnonymousClass3 anonymousClass3, int i, boolean z, int i2) {
        this.$r8$classId = i2;
        this.f$0 = anonymousClass3;
        this.f$1 = i;
        this.f$2 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                MagnificationImpl.AnonymousClass3 anonymousClass3 = this.f$0;
                int i = this.f$1;
                boolean z = this.f$2;
                MagnificationImpl magnificationImpl = MagnificationImpl.this;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) magnificationImpl.mWindowMagnificationControllerSupplier.get(i);
                if (windowMagnificationController != null) {
                    boolean isActivated = windowMagnificationController.isActivated();
                    if (isActivated && windowMagnificationController.isActivated()) {
                        windowMagnificationController.mSettingsPanelVisibility = z;
                        windowMagnificationController.mDragView.setBackground(windowMagnificationController.mContext.getResources().getDrawable(z ? R.drawable.accessibility_window_magnification_drag_handle_background_change_inset : R.drawable.accessibility_window_magnification_drag_handle_background_inset));
                        windowMagnificationController.mDragView.setColorFilter(new PorterDuffColorFilter(windowMagnificationController.mContext.getColor(z ? R.color.magnification_border_color : R.color.magnification_drag_handle_stroke), PorterDuff.Mode.SRC_ATOP));
                    }
                    AccessibilityLogger accessibilityLogger = magnificationImpl.mA11yLogger;
                    if (!z) {
                        accessibilityLogger.uiEventLogger.log(AccessibilityLogger.MagnificationSettingsEvent.MAGNIFICATION_SETTINGS_PANEL_CLOSED);
                        break;
                    } else {
                        accessibilityLogger.uiEventLogger.logWithPosition(AccessibilityLogger.MagnificationSettingsEvent.MAGNIFICATION_SETTINGS_PANEL_OPENED, 0, (String) null, isActivated ? 2 : 1);
                        break;
                    }
                }
                break;
            default:
                MagnificationImpl.AnonymousClass3 anonymousClass32 = this.f$0;
                int i2 = this.f$1;
                boolean z2 = this.f$2;
                WindowMagnificationController windowMagnificationController2 = (WindowMagnificationController) MagnificationImpl.this.mWindowMagnificationControllerSupplier.get(i2);
                if (windowMagnificationController2 != null) {
                    windowMagnificationController2.mAllowDiagonalScrolling = z2;
                    break;
                }
                break;
        }
    }
}
