package com.android.systemui.touch;

import android.graphics.Region;
import android.util.Log;
import android.view.View;
import com.android.systemui.touch.TouchInsetManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TouchInsetManager {
    public final Executor mExecutor;
    public final HashMap mSessionRegions = new HashMap();
    public final HashMap mLastAffectedSurfaces = new HashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TouchInsetSession {
        public final AnonymousClass1 mAttachListener;
        public final Executor mExecutor;
        public final TouchInsetManager mManager;
        public final TouchInsetManager$TouchInsetSession$$ExternalSyntheticLambda0 mOnLayoutChangeListener;
        public final HashSet mTrackedViews;

        public TouchInsetSession(TouchInsetManager touchInsetManager, Executor executor) {
            new View.OnLayoutChangeListener() { // from class: com.android.systemui.touch.TouchInsetManager$TouchInsetSession$$ExternalSyntheticLambda0
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    TouchInsetManager.TouchInsetSession.this.updateTouchRegions();
                }
            };
            new View.OnAttachStateChangeListener() { // from class: com.android.systemui.touch.TouchInsetManager.TouchInsetSession.1
                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewAttachedToWindow(View view) {
                    TouchInsetSession.this.updateTouchRegions();
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewDetachedFromWindow(View view) {
                    TouchInsetSession.this.updateTouchRegions();
                }
            };
            this.mManager = touchInsetManager;
            this.mTrackedViews = new HashSet();
            this.mExecutor = executor;
        }

        public final void updateTouchRegions() {
            this.mExecutor.execute(new TouchInsetManager$TouchInsetSession$$ExternalSyntheticLambda1(this, 1));
        }
    }

    public TouchInsetManager(Executor executor) {
        this.mExecutor = executor;
    }

    public final void recycleRegions(TouchInsetSession touchInsetSession) {
        if (this.mSessionRegions.containsKey(touchInsetSession)) {
            Iterator it = ((HashMap) this.mSessionRegions.get(touchInsetSession)).values().iterator();
            while (it.hasNext()) {
                ((Region) it.next()).recycle();
            }
        } else {
            Log.w("TouchInsetManager", "Removing a session with no regions:" + touchInsetSession);
        }
    }

    public final void updateTouchInsets() {
        HashMap hashMap = new HashMap();
        this.mSessionRegions.values().stream().forEach(new TouchInsetManager$$ExternalSyntheticLambda0(hashMap, 0));
        hashMap.entrySet().stream().forEach(new TouchInsetManager$$ExternalSyntheticLambda1());
        this.mLastAffectedSurfaces.entrySet().forEach(new TouchInsetManager$$ExternalSyntheticLambda0(hashMap, 1));
        this.mLastAffectedSurfaces.clear();
        this.mLastAffectedSurfaces.putAll(hashMap);
    }
}
