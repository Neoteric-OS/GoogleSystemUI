package com.android.wm.shell.bubbles;

import android.os.Looper;
import android.view.BatchedInputEventReceiver;
import android.view.Choreographer;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.MotionEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubblesNavBarInputEventReceiver extends BatchedInputEventReceiver {
    public final BubblesNavBarMotionEventHandler mMotionEventHandler;

    public BubblesNavBarInputEventReceiver(InputChannel inputChannel, Choreographer choreographer, BubblesNavBarMotionEventHandler bubblesNavBarMotionEventHandler) {
        super(inputChannel, Looper.myLooper(), choreographer);
        this.mMotionEventHandler = bubblesNavBarMotionEventHandler;
    }

    public final void onInputEvent(InputEvent inputEvent) {
        try {
            if (inputEvent instanceof MotionEvent) {
                finishInputEvent(inputEvent, this.mMotionEventHandler.onMotionEvent((MotionEvent) inputEvent));
            }
        } finally {
            finishInputEvent(inputEvent, false);
        }
    }
}
