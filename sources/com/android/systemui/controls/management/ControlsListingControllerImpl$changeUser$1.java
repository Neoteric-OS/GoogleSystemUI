package com.android.systemui.controls.management;

import android.content.Context;
import android.os.UserHandle;
import com.android.settingslib.applications.ServiceListing;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsListingControllerImpl$changeUser$1 implements Runnable {
    public final /* synthetic */ Object $newUser;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ControlsListingControllerImpl this$0;

    public /* synthetic */ ControlsListingControllerImpl$changeUser$1(ControlsListingControllerImpl controlsListingControllerImpl, Object obj, int i) {
        this.$r8$classId = i;
        this.this$0 = controlsListingControllerImpl;
        this.$newUser = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                if (this.this$0.userChangeInProgress.decrementAndGet() == 0) {
                    this.this$0.currentUserId = ((UserHandle) this.$newUser).getIdentifier();
                    Context createContextAsUser = this.this$0.context.createContextAsUser((UserHandle) this.$newUser, 0);
                    ControlsListingControllerImpl controlsListingControllerImpl = this.this$0;
                    controlsListingControllerImpl.serviceListing = (ServiceListing) controlsListingControllerImpl.serviceListingBuilder.invoke(createContextAsUser);
                    ControlsListingControllerImpl controlsListingControllerImpl2 = this.this$0;
                    controlsListingControllerImpl2.serviceListing.mCallbacks.add(controlsListingControllerImpl2.serviceListingCallback);
                    this.this$0.serviceListing.setListening(true);
                    this.this$0.serviceListing.reload();
                    break;
                }
                break;
            default:
                if (this.this$0.userChangeInProgress.get() <= 0) {
                    this.this$0.updateServices((List) this.$newUser);
                    break;
                }
                break;
        }
    }
}
