package com.android.wm.shell.transition;

import android.graphics.Rect;
import android.util.ArrayMap;
import android.util.RotationUtils;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.WindowContainerToken;
import com.android.wm.shell.shared.CounterRotator;
import com.android.wm.shell.shared.TransitionUtil;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CounterRotatorHelper {
    public int mLastRotationDelta;
    public final ArrayMap mRotatorMap = new ArrayMap();
    public final Rect mLastDisplayBounds = new Rect();

    public final void cleanUp(SurfaceControl.Transaction transaction) {
        for (int size = this.mRotatorMap.size() - 1; size >= 0; size--) {
            SurfaceControl surfaceControl = ((CounterRotator) this.mRotatorMap.valueAt(size)).mSurface;
            if (surfaceControl != null) {
                transaction.remove(surfaceControl);
            }
        }
        this.mRotatorMap.clear();
        this.mLastRotationDelta = 0;
    }

    public final void handleClosingChanges(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, TransitionInfo.Change change) {
        int i;
        TransitionInfo transitionInfo2 = transitionInfo;
        int deltaRotation = RotationUtils.deltaRotation(change.getStartRotation(), change.getEndRotation());
        Rect endAbsBounds = change.getEndAbsBounds();
        int width = endAbsBounds.width();
        int height = endAbsBounds.height();
        this.mLastRotationDelta = deltaRotation;
        this.mLastDisplayBounds.set(endAbsBounds);
        List changes = transitionInfo.getChanges();
        int size = changes.size();
        int i2 = size - 1;
        while (i2 >= 0) {
            TransitionInfo.Change change2 = (TransitionInfo.Change) changes.get(i2);
            WindowContainerToken parent = change2.getParent();
            if (TransitionUtil.isClosingType(change2.getMode()) && TransitionInfo.isIndependent(change2, transitionInfo2) && parent != null) {
                CounterRotator counterRotator = (CounterRotator) this.mRotatorMap.get(parent);
                if (counterRotator == null) {
                    CounterRotator counterRotator2 = new CounterRotator();
                    i = deltaRotation;
                    counterRotator2.setup(transaction, transitionInfo2.getChange(parent).getLeash(), deltaRotation, width, height);
                    SurfaceControl surfaceControl = counterRotator2.mSurface;
                    if (surfaceControl != null) {
                        transaction.setLayer(surfaceControl, (change2.getFlags() & 2) == 0 ? size - i2 : -1);
                    }
                    this.mRotatorMap.put(parent, counterRotator2);
                    counterRotator = counterRotator2;
                } else {
                    i = deltaRotation;
                }
                SurfaceControl leash = change2.getLeash();
                SurfaceControl surfaceControl2 = counterRotator.mSurface;
                if (surfaceControl2 != null) {
                    transaction.reparent(leash, surfaceControl2);
                }
            } else {
                i = deltaRotation;
            }
            i2--;
            transitionInfo2 = transitionInfo;
            deltaRotation = i;
        }
    }
}
