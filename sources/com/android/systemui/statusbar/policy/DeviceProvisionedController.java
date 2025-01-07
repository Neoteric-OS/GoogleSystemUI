package com.android.systemui.statusbar.policy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface DeviceProvisionedController extends CallbackController {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface DeviceProvisionedListener {
        default void onUserSwitched$1() {
            onUserSetupChanged();
        }

        default void onDeviceProvisionedChanged() {
        }

        default void onUserSetupChanged() {
        }
    }
}
