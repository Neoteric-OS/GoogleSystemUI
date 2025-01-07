package com.android.systemui.controls.management;

import com.android.systemui.statusbar.policy.CallbackController;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ControlsListingController extends CallbackController {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ControlsListingCallback {
        void onServicesUpdated(List list);
    }
}
