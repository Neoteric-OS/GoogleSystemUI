package com.android.systemui.dreams;

import com.android.systemui.dreams.DreamOverlayStateController;
import java.lang.ref.WeakReference;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DreamOverlayStateController$$ExternalSyntheticLambda7 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ DreamOverlayStateController$$ExternalSyntheticLambda7(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                return ((WeakReference) obj).get() == ((DreamOverlayStateController.Callback) obj2);
            default:
                DreamOverlayStateController dreamOverlayStateController = (DreamOverlayStateController) obj2;
                if (obj != null) {
                    throw new ClassCastException();
                }
                dreamOverlayStateController.getClass();
                throw null;
        }
    }
}
