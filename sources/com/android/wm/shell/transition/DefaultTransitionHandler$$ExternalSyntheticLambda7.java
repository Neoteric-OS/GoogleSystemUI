package com.android.wm.shell.transition;

import android.os.IBinder;
import android.view.SurfaceControl;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DefaultTransitionHandler$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DefaultTransitionHandler f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ Object f$2;
    public final /* synthetic */ Object f$3;

    public /* synthetic */ DefaultTransitionHandler$$ExternalSyntheticLambda7(DefaultTransitionHandler defaultTransitionHandler, Object obj, Object obj2, Object obj3, int i) {
        this.$r8$classId = i;
        this.f$0 = defaultTransitionHandler;
        this.f$1 = obj;
        this.f$2 = obj2;
        this.f$3 = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DefaultTransitionHandler defaultTransitionHandler = this.f$0;
                WindowThumbnail windowThumbnail = (WindowThumbnail) this.f$1;
                SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.f$2;
                DefaultTransitionHandler$$ExternalSyntheticLambda7 defaultTransitionHandler$$ExternalSyntheticLambda7 = (DefaultTransitionHandler$$ExternalSyntheticLambda7) this.f$3;
                defaultTransitionHandler.getClass();
                SurfaceControl surfaceControl = windowThumbnail.mSurfaceControl;
                if (surfaceControl != null) {
                    transaction.remove(surfaceControl);
                    transaction.apply();
                    windowThumbnail.mSurfaceControl.release();
                    windowThumbnail.mSurfaceControl = null;
                }
                defaultTransitionHandler.mTransactionPool.release(transaction);
                defaultTransitionHandler$$ExternalSyntheticLambda7.run();
                break;
            case 1:
                DefaultTransitionHandler defaultTransitionHandler2 = this.f$0;
                WindowThumbnail windowThumbnail2 = (WindowThumbnail) this.f$1;
                SurfaceControl.Transaction transaction2 = (SurfaceControl.Transaction) this.f$2;
                DefaultTransitionHandler$$ExternalSyntheticLambda7 defaultTransitionHandler$$ExternalSyntheticLambda72 = (DefaultTransitionHandler$$ExternalSyntheticLambda7) this.f$3;
                defaultTransitionHandler2.getClass();
                SurfaceControl surfaceControl2 = windowThumbnail2.mSurfaceControl;
                if (surfaceControl2 != null) {
                    transaction2.remove(surfaceControl2);
                    transaction2.apply();
                    windowThumbnail2.mSurfaceControl.release();
                    windowThumbnail2.mSurfaceControl = null;
                }
                defaultTransitionHandler2.mTransactionPool.release(transaction2);
                defaultTransitionHandler$$ExternalSyntheticLambda72.run();
                break;
            default:
                DefaultTransitionHandler defaultTransitionHandler3 = this.f$0;
                ArrayList arrayList = (ArrayList) this.f$1;
                IBinder iBinder = (IBinder) this.f$2;
                Transitions.TransitionFinishCallback transitionFinishCallback = (Transitions.TransitionFinishCallback) this.f$3;
                defaultTransitionHandler3.getClass();
                if (arrayList.isEmpty()) {
                    defaultTransitionHandler3.mAnimations.remove(iBinder);
                    transitionFinishCallback.onTransitionFinished(null);
                    break;
                }
                break;
        }
    }
}
