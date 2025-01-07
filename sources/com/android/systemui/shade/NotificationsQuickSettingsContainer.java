package com.android.systemui.shade;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.statusbar.notification.AboveShelfObserver;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationsQuickSettingsContainer extends ConstraintLayout implements FragmentHostManager.FragmentListener, AboveShelfObserver.HasViewAboveShelfChangedListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Rect mBoundingBoxRect;
    public NotificationsQSContainerController$onViewAttached$1 mConfigurationChangedListener;
    public final ArrayList mDrawingOrderedChildren;
    public final Comparator mIndexComparator;
    public Consumer mInsetsChangedListener;
    public View mKeyguardStatusBar;
    public int mLastQSPaddingBottom;
    public final ArrayList mLayoutDrawingOrder;
    public View mQSContainer;
    public Consumer mQSFragmentAttachedListener;
    public QS mQs;
    public View mQsFrame;
    public View mStackScroller;
    public final Rect mUpperRect;

    public NotificationsQuickSettingsContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDrawingOrderedChildren = new ArrayList();
        this.mLayoutDrawingOrder = new ArrayList();
        this.mIndexComparator = Comparator.comparingInt(new ToIntFunction() { // from class: com.android.systemui.shade.NotificationsQuickSettingsContainer$$ExternalSyntheticLambda0
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return NotificationsQuickSettingsContainer.this.indexOfChild((View) obj);
            }
        });
        this.mInsetsChangedListener = new NotificationsQuickSettingsContainer$$ExternalSyntheticLambda1(0);
        this.mQSFragmentAttachedListener = new NotificationsQuickSettingsContainer$$ExternalSyntheticLambda1(1);
        this.mUpperRect = new Rect();
        this.mBoundingBoxRect = new Rect();
        ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
        int i = constraintWidgetContainer.mOptimizationLevel | 64;
        this.mOptimizationLevel = i;
        constraintWidgetContainer.mOptimizationLevel = i;
        LinearSystem.USE_DEPENDENCY_ORDERING = constraintWidgetContainer.optimizeFor(512);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        this.mDrawingOrderedChildren.clear();
        this.mLayoutDrawingOrder.clear();
        if (this.mKeyguardStatusBar.getVisibility() == 0) {
            this.mDrawingOrderedChildren.add(this.mKeyguardStatusBar);
            this.mLayoutDrawingOrder.add(this.mKeyguardStatusBar);
        }
        if (this.mQsFrame.getVisibility() == 0) {
            this.mDrawingOrderedChildren.add(this.mQsFrame);
            this.mLayoutDrawingOrder.add(this.mQsFrame);
        }
        if (this.mStackScroller.getVisibility() == 0) {
            this.mDrawingOrderedChildren.add(this.mStackScroller);
            this.mLayoutDrawingOrder.add(this.mStackScroller);
        }
        this.mLayoutDrawingOrder.sort(this.mIndexComparator);
        super.dispatchDraw(canvas);
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
            logMessageImpl.str1 = "NotificationsQuickSettingsContainer";
            logMessageImpl.int1 = motionEvent.getAction();
            logMessageImpl.long1 = motionEvent.getDownTime();
            logMessageImpl.bool1 = dispatchTouchEvent;
            logBuffer.commit(obtain);
        }
        return dispatchTouchEvent;
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.mInsetsChangedListener.accept(windowInsets);
        return windowInsets;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        NotificationsQSContainerController$onViewAttached$1 notificationsQSContainerController$onViewAttached$1 = this.mConfigurationChangedListener;
        if (notificationsQSContainerController$onViewAttached$1 != null) {
            notificationsQSContainerController$onViewAttached$1.accept(configuration);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mQsFrame = findViewById(R.id.qs_frame);
        this.mKeyguardStatusBar = findViewById(R.id.keyguard_header);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.systemui.fragments.FragmentHostManager.FragmentListener
    public final void onFragmentViewCreated(Fragment fragment) {
        QS qs = (QS) fragment;
        this.mQs = qs;
        this.mQSFragmentAttachedListener.accept(qs);
        View findViewById = this.mQs.getView().findViewById(R.id.quick_settings_container);
        this.mQSContainer = findViewById;
        int i = this.mLastQSPaddingBottom;
        this.mLastQSPaddingBottom = i;
        if (findViewById != null) {
            findViewById.setPadding(findViewById.getPaddingLeft(), this.mQSContainer.getPaddingTop(), this.mQSContainer.getPaddingRight(), i);
        }
    }
}
