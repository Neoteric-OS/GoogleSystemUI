package com.android.systemui.dreams.complication;

import android.util.Log;
import androidx.emoji2.text.FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1;
import com.android.systemui.ambient.touch.TouchHandler;
import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.complication.ComplicationLayoutEngine;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import java.util.ArrayDeque;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HideComplicationTouchHandler implements TouchHandler {
    public static final boolean DEBUG = Log.isLoggable("HideComplicationHandler", 3);
    public final FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1 mHiddenCallback;
    public final AnonymousClass1 mHideComplications;
    public final DreamOverlayStateController mOverlayStateController;
    public final AnonymousClass1 mRestoreComplications;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final ComplicationLayoutEngine mVisibilityController;

    public HideComplicationTouchHandler(ComplicationLayoutEngine complicationLayoutEngine, int i, int i2, TouchInsetManager touchInsetManager, StatusBarKeyguardViewManager statusBarKeyguardViewManager, DelayableExecutor delayableExecutor, DreamOverlayStateController dreamOverlayStateController) {
        new ArrayDeque();
        final int i3 = 0;
        new Runnable(this) { // from class: com.android.systemui.dreams.complication.HideComplicationTouchHandler.1
            public final /* synthetic */ HideComplicationTouchHandler this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i3) {
                    case 0:
                        Log.d("HideComplicationHandler", "Restoring complications...");
                        CrossFadeHelper.fadeIn(this.this$0.mVisibilityController.mLayout, r0.mFadeInDuration, 0);
                        this.this$0.getClass();
                        break;
                    default:
                        if (!this.this$0.mOverlayStateController.containsState(8)) {
                            Log.d("HideComplicationHandler", "Hiding complications...");
                            CrossFadeHelper.fadeOut(this.this$0.mVisibilityController.mLayout, r0.mFadeOutDuration, (Runnable) null);
                            HideComplicationTouchHandler hideComplicationTouchHandler = this.this$0;
                            hideComplicationTouchHandler.getClass();
                            hideComplicationTouchHandler.getClass();
                            break;
                        }
                        break;
                }
            }
        };
        final int i4 = 1;
        new Runnable(this) { // from class: com.android.systemui.dreams.complication.HideComplicationTouchHandler.1
            public final /* synthetic */ HideComplicationTouchHandler this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i4) {
                    case 0:
                        Log.d("HideComplicationHandler", "Restoring complications...");
                        CrossFadeHelper.fadeIn(this.this$0.mVisibilityController.mLayout, r0.mFadeInDuration, 0);
                        this.this$0.getClass();
                        break;
                    default:
                        if (!this.this$0.mOverlayStateController.containsState(8)) {
                            Log.d("HideComplicationHandler", "Hiding complications...");
                            CrossFadeHelper.fadeOut(this.this$0.mVisibilityController.mLayout, r0.mFadeOutDuration, (Runnable) null);
                            HideComplicationTouchHandler hideComplicationTouchHandler = this.this$0;
                            hideComplicationTouchHandler.getClass();
                            hideComplicationTouchHandler.getClass();
                            break;
                        }
                        break;
                }
            }
        };
        this.mVisibilityController = complicationLayoutEngine;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mOverlayStateController = dreamOverlayStateController;
    }

    @Override // com.android.systemui.ambient.touch.TouchHandler
    public final void onSessionStart(TouchMonitor.TouchSessionImpl touchSessionImpl) {
        boolean z = DEBUG;
        if (z) {
            Log.d("HideComplicationHandler", "onSessionStart");
        }
        boolean isBouncerShowing = this.mStatusBarKeyguardViewManager.isBouncerShowing();
        if (z) {
            Log.d("HideComplicationHandler", "not fading. Active session count: " + touchSessionImpl.mTouchMonitor.mActiveTouchSessions.size() + ". Bouncer showing: " + isBouncerShowing);
        }
        touchSessionImpl.pop();
    }
}
