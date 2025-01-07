package com.google.android.systemui.elmyra.actions;

import com.google.android.systemui.elmyra.ElmyraService;
import com.google.android.systemui.elmyra.feedback.FeedbackEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Action {
    public final List mFeedbackEffects;
    public final Executor mHandler;
    public ElmyraService.AnonymousClass1 mListener;

    public Action(Executor executor, List list) {
        ArrayList arrayList = new ArrayList();
        this.mFeedbackEffects = arrayList;
        this.mHandler = executor;
        if (list != null) {
            arrayList.addAll(list);
        }
    }

    public abstract boolean isAvailable();

    public final void notifyListener() {
        if (this.mListener != null) {
            final int i = 0;
            this.mHandler.execute(new Runnable(this) { // from class: com.google.android.systemui.elmyra.actions.Action$$ExternalSyntheticLambda0
                public final /* synthetic */ Action f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i2 = i;
                    Action action = this.f$0;
                    switch (i2) {
                        case 0:
                            ElmyraService.AnonymousClass1 anonymousClass1 = action.mListener;
                            if (anonymousClass1 != null) {
                                ElmyraService.this.updateSensorListener$1();
                                break;
                            }
                            break;
                        default:
                            action.updateFeedbackEffects(0, 0.0f);
                            break;
                    }
                }
            });
        }
        if (isAvailable()) {
            return;
        }
        final int i2 = 1;
        this.mHandler.execute(new Runnable(this) { // from class: com.google.android.systemui.elmyra.actions.Action$$ExternalSyntheticLambda0
            public final /* synthetic */ Action f$0;

            {
                this.f$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i22 = i2;
                Action action = this.f$0;
                switch (i22) {
                    case 0:
                        ElmyraService.AnonymousClass1 anonymousClass1 = action.mListener;
                        if (anonymousClass1 != null) {
                            ElmyraService.this.updateSensorListener$1();
                            break;
                        }
                        break;
                    default:
                        action.updateFeedbackEffects(0, 0.0f);
                        break;
                }
            }
        });
    }

    public abstract void onTrigger(GestureSensor.DetectionProperties detectionProperties);

    public String toString() {
        return getClass().getSimpleName();
    }

    public void triggerFeedbackEffects(GestureSensor.DetectionProperties detectionProperties) {
        if (isAvailable()) {
            for (int i = 0; i < ((ArrayList) this.mFeedbackEffects).size(); i++) {
                ((FeedbackEffect) ((ArrayList) this.mFeedbackEffects).get(i)).onResolve(detectionProperties);
            }
        }
    }

    public void updateFeedbackEffects(int i, float f) {
        int i2 = 0;
        if (f == 0.0f || i == 0) {
            while (i2 < ((ArrayList) this.mFeedbackEffects).size()) {
                ((FeedbackEffect) ((ArrayList) this.mFeedbackEffects).get(i2)).onRelease();
                i2++;
            }
        } else if (isAvailable()) {
            while (i2 < ((ArrayList) this.mFeedbackEffects).size()) {
                ((FeedbackEffect) ((ArrayList) this.mFeedbackEffects).get(i2)).onProgress(i, f);
                i2++;
            }
        }
    }

    public void onProgress(int i, float f) {
    }
}
