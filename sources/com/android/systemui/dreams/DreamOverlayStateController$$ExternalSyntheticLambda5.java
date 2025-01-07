package com.android.systemui.dreams;

import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.util.reference.WeakReferenceFactoryImpl;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DreamOverlayStateController$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DreamOverlayStateController f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ DreamOverlayStateController$$ExternalSyntheticLambda5(DreamOverlayStateController dreamOverlayStateController, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = dreamOverlayStateController;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DreamOverlayStateController dreamOverlayStateController = this.f$0;
                DreamOverlayStateController.Callback callback = (DreamOverlayStateController.Callback) this.f$1;
                dreamOverlayStateController.getClass();
                Objects.requireNonNull(callback, "Callback must not be null. b/128895449");
                if (!dreamOverlayStateController.mCallbacks.stream().anyMatch(new DreamOverlayStateController$$ExternalSyntheticLambda7(0, callback))) {
                    ArrayList arrayList = dreamOverlayStateController.mCallbacks;
                    ((WeakReferenceFactoryImpl) dreamOverlayStateController.mWeakReferenceFactory).getClass();
                    arrayList.add(new WeakReference(callback));
                    if (!dreamOverlayStateController.mComplications.isEmpty()) {
                        callback.onComplicationsChanged();
                        break;
                    }
                }
                break;
            case 1:
                DreamOverlayStateController dreamOverlayStateController2 = this.f$0;
                DreamOverlayStateController.Callback callback2 = (DreamOverlayStateController.Callback) this.f$1;
                dreamOverlayStateController2.getClass();
                Objects.requireNonNull(callback2, "Callback must not be null. b/128895449");
                Iterator it = dreamOverlayStateController2.mCallbacks.iterator();
                while (it.hasNext()) {
                    DreamOverlayStateController.Callback callback3 = (DreamOverlayStateController.Callback) ((WeakReference) it.next()).get();
                    if (callback3 == null || callback3 == callback2) {
                        it.remove();
                    }
                }
                break;
            default:
                this.f$0.notifyCallbacksLocked((Consumer) this.f$1);
                break;
        }
    }
}
