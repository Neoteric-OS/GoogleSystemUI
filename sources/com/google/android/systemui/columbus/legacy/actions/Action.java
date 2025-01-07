package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Action {
    public final Context context;
    public final Set feedbackEffects;
    public boolean isAvailable = true;
    public final Set listeners = new LinkedHashSet();
    public final Handler handler = new Handler(Looper.getMainLooper());

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onActionAvailabilityChanged(Action action);
    }

    public Action(Context context, Set set) {
        this.context = context;
        this.feedbackEffects = set;
    }

    public abstract String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig();

    public void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        updateFeedbackEffects(i, detectionProperties);
        if (i == 1) {
            Log.i(getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig(), "Triggering");
            onTrigger(detectionProperties);
        }
    }

    public abstract void onTrigger(GestureSensor.DetectionProperties detectionProperties);

    public final void setAvailable(boolean z) {
        Handler handler;
        if (this.isAvailable != z) {
            this.isAvailable = z;
            Iterator it = this.listeners.iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                handler = this.handler;
                if (!hasNext) {
                    break;
                }
                final Listener listener = (Listener) it.next();
                handler.post(new Runnable() { // from class: com.google.android.systemui.columbus.legacy.actions.Action$setAvailable$1$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Action.Listener.this.onActionAvailabilityChanged(this);
                    }
                });
            }
            if (this.isAvailable) {
                return;
            }
            handler.post(new Runnable() { // from class: com.google.android.systemui.columbus.legacy.actions.Action$setAvailable$2
                @Override // java.lang.Runnable
                public final void run() {
                    Action.this.updateFeedbackEffects(0, null);
                }
            });
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void updateFeedbackEffects(int i, GestureSensor.DetectionProperties detectionProperties) {
        Set set = this.feedbackEffects;
        if (set != null) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                ((FeedbackEffect) it.next()).onGestureDetected(i, detectionProperties);
            }
        }
    }
}
