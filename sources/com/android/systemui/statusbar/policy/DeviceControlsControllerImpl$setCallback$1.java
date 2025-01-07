package com.android.systemui.statusbar.policy;

import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.management.ControlsListingControllerImpl;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceControlsControllerImpl$setCallback$1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DeviceControlsControllerImpl this$0;

    public /* synthetic */ DeviceControlsControllerImpl$setCallback$1(DeviceControlsControllerImpl deviceControlsControllerImpl, int i) {
        this.$r8$classId = i;
        this.this$0 = deviceControlsControllerImpl;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ((ControlsListingControllerImpl) ((ControlsListingController) obj)).addCallback((ControlsListingController.ControlsListingCallback) this.this$0.listingCallback);
                break;
            case 1:
                if (!Favorites.getAllStructures().isEmpty()) {
                    this.this$0.position = 3;
                    this.this$0.fireControlsUpdate();
                    break;
                }
                break;
            case 2:
                ((ControlsListingControllerImpl) ((ControlsListingController) obj)).removeCallback(this.this$0.listingCallback);
                break;
            default:
                ((ControlsListingControllerImpl) ((ControlsListingController) obj)).removeCallback(this.this$0.listingCallback);
                break;
        }
    }
}
