package com.android.systemui.accessibility;

import android.os.RemoteException;
import android.util.Log;
import android.view.accessibility.IMagnificationConnectionCallback;
import com.android.systemui.accessibility.MagnificationImpl;
import com.android.systemui.accessibility.MagnificationSettingsController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MagnificationImpl$4$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MagnificationSettingsController.Callback f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MagnificationImpl$4$$ExternalSyntheticLambda2(MagnificationSettingsController.Callback callback, int i, int i2, int i3) {
        this.$r8$classId = i3;
        this.f$0 = callback;
        this.f$1 = i;
        this.f$2 = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IMagnificationConnectionCallback iMagnificationConnectionCallback;
        switch (this.$r8$classId) {
            case 0:
                MagnificationImpl.AnonymousClass3 anonymousClass3 = (MagnificationImpl.AnonymousClass3) this.f$0;
                int i = this.f$1;
                int i2 = this.f$2;
                MagnificationImpl magnificationImpl = MagnificationImpl.this;
                if (((WindowMagnificationController) magnificationImpl.mWindowMagnificationControllerSupplier.get(i)).isActivated() ^ (i2 == 2)) {
                    MagnificationSettingsController magnificationSettingsController = (MagnificationSettingsController) magnificationImpl.mMagnificationSettingsSupplier.get(i);
                    if (magnificationSettingsController != null) {
                        magnificationSettingsController.mContext.unregisterComponentCallbacks(magnificationSettingsController);
                        magnificationSettingsController.mWindowMagnificationSettings.hideSettingPanel(true);
                    }
                    MagnificationConnectionImpl magnificationConnectionImpl = magnificationImpl.mMagnificationConnectionImpl;
                    if (magnificationConnectionImpl != null && (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) != null) {
                        try {
                            iMagnificationConnectionCallback.onChangeMagnificationMode(i, i2);
                            break;
                        } catch (RemoteException e) {
                            Log.e("WindowMagnificationConnectionImpl", "Failed to inform changing magnification mode", e);
                            return;
                        }
                    }
                }
                break;
            case 1:
                MagnificationImpl.AnonymousClass3 anonymousClass32 = (MagnificationImpl.AnonymousClass3) this.f$0;
                int i3 = this.f$1;
                int i4 = this.f$2;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) MagnificationImpl.this.mWindowMagnificationControllerSupplier.get(i3);
                if (windowMagnificationController != null && windowMagnificationController.mMagnificationSizeScaleOptions.contains(i4)) {
                    windowMagnificationController.mSettingsButtonIndex = i4;
                    int magnificationWindowSizeFromIndex = windowMagnificationController.getMagnificationWindowSizeFromIndex(i4);
                    windowMagnificationController.setWindowSizeAndCenter(Float.NaN, Float.NaN, magnificationWindowSizeFromIndex, magnificationWindowSizeFromIndex);
                    break;
                }
                break;
            default:
                MagnificationImpl.AnonymousClass3 anonymousClass33 = (MagnificationImpl.AnonymousClass3) this.f$0;
                int i5 = this.f$1;
                int i6 = this.f$2;
                MagnificationSettingsController magnificationSettingsController2 = (MagnificationSettingsController) MagnificationImpl.this.mMagnificationSettingsSupplier.get(i5);
                if (magnificationSettingsController2 != null) {
                    magnificationSettingsController2.mWindowMagnificationSettings.updateSelectedButton(i6);
                    break;
                }
                break;
        }
    }
}
