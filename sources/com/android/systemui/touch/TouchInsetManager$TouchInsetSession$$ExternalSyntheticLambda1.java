package com.android.systemui.touch;

import com.android.systemui.touch.TouchInsetManager;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TouchInsetManager$TouchInsetSession$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TouchInsetManager.TouchInsetSession f$0;

    public /* synthetic */ TouchInsetManager$TouchInsetSession$$ExternalSyntheticLambda1(TouchInsetManager.TouchInsetSession touchInsetSession, int i) {
        this.$r8$classId = i;
        this.f$0 = touchInsetSession;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final TouchInsetManager.TouchInsetSession touchInsetSession = this.f$0;
        switch (i) {
            case 0:
                final TouchInsetManager touchInsetManager = touchInsetSession.mManager;
                touchInsetManager.mExecutor.execute(new Runnable() { // from class: com.android.systemui.touch.TouchInsetManager$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        TouchInsetManager touchInsetManager2 = TouchInsetManager.this;
                        TouchInsetManager.TouchInsetSession touchInsetSession2 = touchInsetSession;
                        touchInsetManager2.recycleRegions(touchInsetSession2);
                        touchInsetManager2.mSessionRegions.remove(touchInsetSession2);
                        touchInsetManager2.updateTouchInsets();
                    }
                });
                touchInsetSession.mTrackedViews.clear();
                break;
            default:
                touchInsetSession.getClass();
                final HashMap hashMap = new HashMap();
                if (!touchInsetSession.mTrackedViews.isEmpty()) {
                    touchInsetSession.mTrackedViews.stream().forEach(new TouchInsetManager$$ExternalSyntheticLambda0(hashMap, 3));
                    final TouchInsetManager touchInsetManager2 = touchInsetSession.mManager;
                    touchInsetManager2.mExecutor.execute(new Runnable() { // from class: com.android.systemui.touch.TouchInsetManager$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            TouchInsetManager touchInsetManager3 = TouchInsetManager.this;
                            TouchInsetManager.TouchInsetSession touchInsetSession2 = touchInsetSession;
                            HashMap hashMap2 = hashMap;
                            touchInsetManager3.recycleRegions(touchInsetSession2);
                            touchInsetManager3.mSessionRegions.put(touchInsetSession2, hashMap2);
                            touchInsetManager3.updateTouchInsets();
                        }
                    });
                    break;
                }
                break;
        }
    }
}
