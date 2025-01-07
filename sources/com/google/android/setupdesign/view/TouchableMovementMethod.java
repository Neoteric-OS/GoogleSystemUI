package com.google.android.setupdesign.view;

import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface TouchableMovementMethod {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TouchableLinkMovementMethod extends LinkMovementMethod implements TouchableMovementMethod {
        public MotionEvent lastEvent;
        public boolean lastEventResult;

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public final boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            this.lastEvent = motionEvent;
            boolean onTouchEvent = super.onTouchEvent(textView, spannable, motionEvent);
            if (motionEvent.getAction() == 0) {
                this.lastEventResult = Selection.getSelectionStart(spannable) != -1;
            } else {
                this.lastEventResult = onTouchEvent;
            }
            return onTouchEvent;
        }
    }
}
