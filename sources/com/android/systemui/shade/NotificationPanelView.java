package com.android.systemui.shade;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.shade.NotificationPanelViewController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationPanelView extends FrameLayout {
    public final Paint mAlphaPaint;
    public int mCurrentPanelAlpha;
    public boolean mDozing;
    public NotificationPanelViewController$$ExternalSyntheticLambda11 mOnConfigurationChangedListener;
    public NotificationPanelViewController$$ExternalSyntheticLambda11 mRtlChangeListener;
    public NotificationPanelViewController.TouchHandler mTouchHandler;

    public NotificationPanelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mAlphaPaint = paint;
        setWillNotDraw(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        setBackgroundColor(0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchConfigurationChanged(Configuration configuration) {
        super.dispatchConfigurationChanged(configuration);
        this.mOnConfigurationChangedListener.f$0.loadDimens();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mCurrentPanelAlpha != 255) {
            canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.mAlphaPaint);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        DispatchTouchLogger dispatchTouchLogger = TouchLogger.touchLogger;
        if (dispatchTouchLogger != null) {
            LogLevel logLevel = LogLevel.DEBUG;
            DispatchTouchLogger$logDispatchTouch$2 dispatchTouchLogger$logDispatchTouch$2 = new DispatchTouchLogger$logDispatchTouch$2(dispatchTouchLogger);
            LogBuffer logBuffer = dispatchTouchLogger.buffer;
            LogMessage obtain = logBuffer.obtain("systemui.shade.touch", logLevel, dispatchTouchLogger$logDispatchTouch$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = "NPV";
            logMessageImpl.int1 = motionEvent.getAction();
            logMessageImpl.long1 = motionEvent.getDownTime();
            logMessageImpl.bool1 = dispatchTouchEvent;
            logBuffer.commit(obtain);
        }
        return dispatchTouchEvent;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return !this.mDozing;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mTouchHandler.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        NotificationPanelViewController$$ExternalSyntheticLambda11 notificationPanelViewController$$ExternalSyntheticLambda11 = this.mRtlChangeListener;
        if (notificationPanelViewController$$ExternalSyntheticLambda11 != null) {
            NotificationPanelViewController notificationPanelViewController = notificationPanelViewController$$ExternalSyntheticLambda11.f$0;
            if (i != notificationPanelViewController.mOldLayoutDirection) {
                notificationPanelViewController.mOldLayoutDirection = i;
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }
}
