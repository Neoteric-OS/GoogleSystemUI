package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.view.Choreographer;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimationHandler {
    public static final ThreadLocal sAnimatorHandler = new ThreadLocal();
    public FrameCallbackScheduler16 mDurationScaleChangeListener;
    public final FrameCallbackScheduler mScheduler;
    public final SimpleArrayMap mDelayedCallbackStartTime = new SimpleArrayMap(0);
    public final ArrayList mAnimationCallbacks = new ArrayList();
    public final AnimationCallbackDispatcher mCallbackDispatcher = new AnimationCallbackDispatcher();
    public final AnimationHandler$$ExternalSyntheticLambda0 mRunnable = new AnimationHandler$$ExternalSyntheticLambda0(this);
    public boolean mListDirty = false;
    public float mDurationScale = 1.0f;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationCallbackDispatcher {
        public AnimationCallbackDispatcher() {
        }
    }

    public AnimationHandler(FrameCallbackScheduler frameCallbackScheduler) {
        this.mScheduler = frameCallbackScheduler;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FrameCallbackScheduler16 implements FrameCallbackScheduler {
        public Object mChoreographer;
        public final Object mLooper;

        public FrameCallbackScheduler16() {
            this.mChoreographer = Choreographer.getInstance();
            this.mLooper = Looper.myLooper();
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public boolean isCurrentThread() {
            return Thread.currentThread() == ((Looper) this.mLooper).getThread();
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public void postFrameCallback(final AnimationHandler$$ExternalSyntheticLambda0 animationHandler$$ExternalSyntheticLambda0) {
            ((Choreographer) this.mChoreographer).postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.dynamicanimation.animation.AnimationHandler$FrameCallbackScheduler16$$ExternalSyntheticLambda0
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j) {
                    AnimationHandler$$ExternalSyntheticLambda0.this.run();
                }
            });
        }

        public FrameCallbackScheduler16(AnimationHandler animationHandler) {
            this.mLooper = animationHandler;
        }
    }
}
