package com.google.android.systemui.assist.uihints;

import android.metrics.LogMaker;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistantSessionEvent;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TouchInsideHandler implements View.OnClickListener, View.OnTouchListener {
    public final AssistLogger mAssistLogger;
    public Runnable mFallback;
    public boolean mInGesturalMode;

    public TouchInsideHandler(final Lazy lazy, NavigationModeController navigationModeController, AssistLogger assistLogger) {
        new Handler(Looper.getMainLooper());
        this.mAssistLogger = assistLogger;
        this.mFallback = new Runnable() { // from class: com.google.android.systemui.assist.uihints.TouchInsideHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ((AssistManagerGoogle) Lazy.this.get()).hideAssist();
            }
        };
        this.mInGesturalMode = QuickStepContract.isGesturalMode(navigationModeController.addListener(new NavigationModeController.ModeChangedListener() { // from class: com.google.android.systemui.assist.uihints.TouchInsideHandler$$ExternalSyntheticLambda1
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i) {
                TouchInsideHandler.this.mInGesturalMode = QuickStepContract.isGesturalMode(i);
            }
        }));
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        onTouchInside();
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mInGesturalMode) {
            if (motionEvent.getAction() == 0) {
                onTouchInside();
            }
        } else if (motionEvent.getAction() == 1) {
            onTouchInside();
        }
        return true;
    }

    public final void onTouchInside() {
        this.mFallback.run();
        this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_USER_DISMISS);
        MetricsLogger.action(new LogMaker(1716).setType(5).setSubtype(2));
    }
}
