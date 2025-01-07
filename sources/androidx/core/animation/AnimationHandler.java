package androidx.core.animation;

import android.view.Choreographer;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimationHandler {
    public static final ThreadLocal sAnimationHandler = new ThreadLocal();
    public final ArrayList mAnimationCallbacks = new ArrayList();
    public boolean mListDirty = false;
    public final FrameCallbackProvider16 mProvider = new FrameCallbackProvider16();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface AnimationFrameCallback {
        boolean doAnimationFrame(long j);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FrameCallbackProvider16 implements Choreographer.FrameCallback {
        public FrameCallbackProvider16() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public final void doFrame(long j) {
            AnimationHandler animationHandler = AnimationHandler.this;
            long j2 = j / 1000000;
            for (int i = 0; i < animationHandler.mAnimationCallbacks.size(); i++) {
                AnimationFrameCallback animationFrameCallback = (AnimationFrameCallback) animationHandler.mAnimationCallbacks.get(i);
                if (animationFrameCallback != null) {
                    animationFrameCallback.doAnimationFrame(j2);
                }
            }
            if (animationHandler.mListDirty) {
                for (int size = animationHandler.mAnimationCallbacks.size() - 1; size >= 0; size--) {
                    if (animationHandler.mAnimationCallbacks.get(size) == null) {
                        animationHandler.mAnimationCallbacks.remove(size);
                    }
                }
                animationHandler.mListDirty = false;
            }
            if (animationHandler.mAnimationCallbacks.size() > 0) {
                FrameCallbackProvider16 frameCallbackProvider16 = animationHandler.mProvider;
                frameCallbackProvider16.getClass();
                Choreographer.getInstance().postFrameCallback(frameCallbackProvider16);
            }
        }
    }

    public static AnimationHandler getInstance() {
        ThreadLocal threadLocal = sAnimationHandler;
        if (threadLocal.get() == null) {
            threadLocal.set(new AnimationHandler());
        }
        return (AnimationHandler) threadLocal.get();
    }
}
