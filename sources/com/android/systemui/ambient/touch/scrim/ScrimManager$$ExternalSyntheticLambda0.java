package com.android.systemui.ambient.touch.scrim;

import com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$scrimManagerCallback$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ScrimManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScrimManager f$0;
    public final /* synthetic */ BouncerSwipeTouchHandler$scrimManagerCallback$1 f$1;

    public /* synthetic */ ScrimManager$$ExternalSyntheticLambda0(ScrimManager scrimManager, BouncerSwipeTouchHandler$scrimManagerCallback$1 bouncerSwipeTouchHandler$scrimManagerCallback$1, int i) {
        this.$r8$classId = i;
        this.f$0 = scrimManager;
        this.f$1 = bouncerSwipeTouchHandler$scrimManagerCallback$1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ScrimManager scrimManager = this.f$0;
                scrimManager.mCallbacks.remove(this.f$1);
                break;
            default:
                ScrimManager scrimManager2 = this.f$0;
                scrimManager2.mCallbacks.add(this.f$1);
                break;
        }
    }
}
