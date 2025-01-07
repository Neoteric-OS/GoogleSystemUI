package com.android.wm.shell.freeform;

import android.os.IBinder;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class FreeformTaskTransitionHandler$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ FreeformTaskTransitionHandler f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ IBinder f$2;
    public final /* synthetic */ Transitions.TransitionFinishCallback f$3;

    public /* synthetic */ FreeformTaskTransitionHandler$$ExternalSyntheticLambda2(FreeformTaskTransitionHandler freeformTaskTransitionHandler, ArrayList arrayList, IBinder iBinder, Transitions.TransitionFinishCallback transitionFinishCallback) {
        this.f$0 = freeformTaskTransitionHandler;
        this.f$1 = arrayList;
        this.f$2 = iBinder;
        this.f$3 = transitionFinishCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final FreeformTaskTransitionHandler freeformTaskTransitionHandler = this.f$0;
        ArrayList arrayList = this.f$1;
        final IBinder iBinder = this.f$2;
        final Transitions.TransitionFinishCallback transitionFinishCallback = this.f$3;
        freeformTaskTransitionHandler.getClass();
        if (arrayList.isEmpty()) {
            ((HandlerExecutor) freeformTaskTransitionHandler.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.freeform.FreeformTaskTransitionHandler$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    FreeformTaskTransitionHandler freeformTaskTransitionHandler2 = FreeformTaskTransitionHandler.this;
                    IBinder iBinder2 = iBinder;
                    Transitions.TransitionFinishCallback transitionFinishCallback2 = transitionFinishCallback;
                    freeformTaskTransitionHandler2.mAnimations.remove(iBinder2);
                    transitionFinishCallback2.onTransitionFinished(null);
                }
            });
        }
    }
}
