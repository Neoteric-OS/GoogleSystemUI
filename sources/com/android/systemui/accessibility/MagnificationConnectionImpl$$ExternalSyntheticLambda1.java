package com.android.systemui.accessibility;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MagnificationConnectionImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MagnificationConnectionImpl f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MagnificationConnectionImpl$$ExternalSyntheticLambda1(MagnificationConnectionImpl magnificationConnectionImpl, int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = magnificationConnectionImpl;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                MagnificationConnectionImpl magnificationConnectionImpl = this.f$0;
                MagnificationSettingsController magnificationSettingsController = (MagnificationSettingsController) magnificationConnectionImpl.mMagnification.mMagnificationSettingsSupplier.get(this.f$1);
                if (magnificationSettingsController != null) {
                    magnificationSettingsController.mContext.unregisterComponentCallbacks(magnificationSettingsController);
                    magnificationSettingsController.mWindowMagnificationSettings.hideSettingPanel(true);
                    break;
                }
                break;
            default:
                MagnificationConnectionImpl magnificationConnectionImpl2 = this.f$0;
                int i = this.f$1;
                MagnificationImpl magnificationImpl = magnificationConnectionImpl2.mMagnification;
                magnificationImpl.mHandler.removeMessages(1);
                MagnificationModeSwitch magnificationModeSwitch = (MagnificationModeSwitch) magnificationImpl.mModeSwitchesController.mSwitchSupplier.get(i);
                if (magnificationModeSwitch != null) {
                    magnificationModeSwitch.removeButton();
                    break;
                }
                break;
        }
    }
}
