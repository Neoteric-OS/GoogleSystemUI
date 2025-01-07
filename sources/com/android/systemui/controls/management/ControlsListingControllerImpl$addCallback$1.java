package com.android.systemui.controls.management;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.controls.management.ControlsListingController;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsListingControllerImpl$addCallback$1 implements Runnable {
    public final /* synthetic */ ControlsListingController.ControlsListingCallback $listener;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ControlsListingControllerImpl this$0;

    public /* synthetic */ ControlsListingControllerImpl$addCallback$1(ControlsListingControllerImpl controlsListingControllerImpl, ControlsListingController.ControlsListingCallback controlsListingCallback, int i) {
        this.$r8$classId = i;
        this.this$0 = controlsListingControllerImpl;
        this.$listener = controlsListingCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                if (this.this$0.userChangeInProgress.get() <= 0) {
                    List currentServices = this.this$0.getCurrentServices();
                    ExifInterface$$ExternalSyntheticOutline0.m("Subscribing callback, service count: ", "ControlsListingControllerImpl", ((ArrayList) currentServices).size());
                    this.this$0.callbacks.add(this.$listener);
                    this.$listener.onServicesUpdated(currentServices);
                    break;
                } else {
                    this.this$0.addCallback(this.$listener);
                    break;
                }
            default:
                Log.d("ControlsListingControllerImpl", "Unsubscribing callback");
                this.this$0.callbacks.remove(this.$listener);
                break;
        }
    }
}
