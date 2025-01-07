package com.android.systemui.common.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.systemui.common.ui.view.LongPressHandlingView;
import com.android.systemui.common.ui.view.LongPressHandlingView$interactionHandler$2;
import com.android.systemui.common.ui.view.LongPressHandlingViewInteractionHandler;
import com.android.systemui.log.LongPressHandlingViewLogger;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.shade.TouchLogger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LongPressHandlingView extends View {
    public static final /* synthetic */ int $r8$clinit = 0;
    public AccessibilityNodeInfo.AccessibilityAction accessibilityHintLongPressAction;
    public final Lazy interactionHandler$delegate;
    public Listener listener;

    public /* synthetic */ LongPressHandlingView(Context context, AttributeSet attributeSet, Function0 function0, int i, LongPressHandlingViewLogger longPressHandlingViewLogger, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, function0, (i2 & 8) != 0 ? ViewConfiguration.getTouchSlop() : i, (i2 & 16) != 0 ? null : longPressHandlingViewLogger);
    }

    @Override // android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        TouchLogger.Companion.logDispatchTouch("long_press", motionEvent, dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    public final LongPressHandlingViewInteractionHandler getInteractionHandler() {
        return (LongPressHandlingViewInteractionHandler) this.interactionHandler$delegate.getValue();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        Object obj;
        Object down;
        final LongPressHandlingViewInteractionHandler interactionHandler = getInteractionHandler();
        boolean z = true;
        if (motionEvent != null) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                down = new LongPressHandlingViewInteractionHandler.MotionEventModel.Down((int) motionEvent.getX(), (int) motionEvent.getY());
            } else if (actionMasked == 1) {
                down = new LongPressHandlingViewInteractionHandler.MotionEventModel.Up(LongPressHandlingViewKt.distanceMoved(motionEvent), motionEvent.getEventTime() - motionEvent.getDownTime());
            } else if (actionMasked != 2) {
                obj = actionMasked != 3 ? LongPressHandlingViewInteractionHandler.MotionEventModel.Other.INSTANCE : LongPressHandlingViewInteractionHandler.MotionEventModel.Cancel.INSTANCE;
            } else {
                down = new LongPressHandlingViewInteractionHandler.MotionEventModel.Move(LongPressHandlingViewKt.distanceMoved(motionEvent));
            }
            obj = down;
        } else {
            obj = null;
        }
        if (!interactionHandler.isLongPressHandlingEnabled) {
            return false;
        }
        boolean z2 = obj instanceof LongPressHandlingViewInteractionHandler.MotionEventModel.Down;
        LongPressHandlingViewLogger longPressHandlingViewLogger = interactionHandler.logger;
        if (z2) {
            LongPressHandlingViewInteractionHandler.MotionEventModel.Down down2 = (LongPressHandlingViewInteractionHandler.MotionEventModel.Down) obj;
            final int i = down2.x;
            long longValue = ((Number) interactionHandler.longPressDuration.invoke()).longValue();
            if (longPressHandlingViewLogger != null) {
                longPressHandlingViewLogger.schedulingLongPress(longValue);
            }
            final int i2 = down2.y;
            interactionHandler.scheduledLongPressHandle = (DisposableHandle) ((LongPressHandlingView$interactionHandler$2.AnonymousClass1) interactionHandler.postDelayed).invoke(new Runnable() { // from class: com.android.systemui.common.ui.view.LongPressHandlingViewInteractionHandler$scheduleLongPress$1
                @Override // java.lang.Runnable
                public final void run() {
                    LongPressHandlingViewLogger longPressHandlingViewLogger2 = LongPressHandlingViewInteractionHandler.this.logger;
                    if (longPressHandlingViewLogger2 != null) {
                        LogLevel logLevel = LogLevel.DEBUG;
                        longPressHandlingViewLogger2.logBuffer.log(longPressHandlingViewLogger2.tag, logLevel, "long press event detected and dispatched", null);
                    }
                    LongPressHandlingViewInteractionHandler longPressHandlingViewInteractionHandler = LongPressHandlingViewInteractionHandler.this;
                    int i3 = i;
                    int i4 = i2;
                    if (((Boolean) ((LongPressHandlingView$interactionHandler$2.AnonymousClass2) longPressHandlingViewInteractionHandler.isAttachedToWindow).invoke()).booleanValue()) {
                        ((LongPressHandlingView$interactionHandler$2.AnonymousClass3) longPressHandlingViewInteractionHandler.onLongPressDetected).invoke(Integer.valueOf(i3), Integer.valueOf(i4));
                    }
                }
            }, Long.valueOf(longValue));
        } else {
            boolean z3 = obj instanceof LongPressHandlingViewInteractionHandler.MotionEventModel.Move;
            int i3 = interactionHandler.allowedTouchSlop;
            if (z3) {
                float f = ((LongPressHandlingViewInteractionHandler.MotionEventModel.Move) obj).distanceMoved;
                if (f > i3) {
                    if (longPressHandlingViewLogger != null) {
                        longPressHandlingViewLogger.cancelingLongPressDueToTouchSlop(i3, f);
                    }
                    DisposableHandle disposableHandle = interactionHandler.scheduledLongPressHandle;
                    if (disposableHandle != null) {
                        disposableHandle.dispose();
                    }
                }
            } else if (obj instanceof LongPressHandlingViewInteractionHandler.MotionEventModel.Up) {
                if (longPressHandlingViewLogger != null) {
                    LongPressHandlingViewInteractionHandler.MotionEventModel.Up up = (LongPressHandlingViewInteractionHandler.MotionEventModel.Up) obj;
                    longPressHandlingViewLogger.onUpEvent(up.distanceMoved, i3, up.gestureDuration);
                }
                DisposableHandle disposableHandle2 = interactionHandler.scheduledLongPressHandle;
                if (disposableHandle2 != null) {
                    disposableHandle2.dispose();
                }
                LongPressHandlingViewInteractionHandler.MotionEventModel.Up up2 = (LongPressHandlingViewInteractionHandler.MotionEventModel.Up) obj;
                if (up2.distanceMoved <= i3) {
                    if (up2.gestureDuration < ((Number) interactionHandler.longPressDuration.invoke()).longValue()) {
                        if (longPressHandlingViewLogger != null) {
                            longPressHandlingViewLogger.logBuffer.log(longPressHandlingViewLogger.tag, LogLevel.DEBUG, "Dispatching single tap instead of long press", null);
                        }
                        if (((Boolean) ((LongPressHandlingView$interactionHandler$2.AnonymousClass2) interactionHandler.isAttachedToWindow).invoke()).booleanValue()) {
                            ((LongPressHandlingView$interactionHandler$2.AnonymousClass4) interactionHandler.onSingleTapDetected).invoke();
                        }
                    }
                }
            } else if (obj instanceof LongPressHandlingViewInteractionHandler.MotionEventModel.Cancel) {
                if (longPressHandlingViewLogger != null) {
                    longPressHandlingViewLogger.logBuffer.log(longPressHandlingViewLogger.tag, LogLevel.DEBUG, "Long press may be cancelled due to MotionEventModel.Cancel", null);
                }
                DisposableHandle disposableHandle3 = interactionHandler.scheduledLongPressHandle;
                if (disposableHandle3 != null) {
                    disposableHandle3.dispose();
                }
            }
            z = false;
        }
        return z;
    }

    public LongPressHandlingView(Context context, AttributeSet attributeSet, final Function0 function0, final int i, final LongPressHandlingViewLogger longPressHandlingViewLogger) {
        super(context, attributeSet);
        setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.common.ui.view.LongPressHandlingView$setupAccessibilityDelegate$1
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                LongPressHandlingView longPressHandlingView = LongPressHandlingView.this;
                int i2 = LongPressHandlingView.$r8$clinit;
                if (!longPressHandlingView.getInteractionHandler().isLongPressHandlingEnabled || (accessibilityAction = LongPressHandlingView.this.accessibilityHintLongPressAction) == null) {
                    return;
                }
                accessibilityNodeInfo.addAction(accessibilityAction);
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
                LongPressHandlingView longPressHandlingView = LongPressHandlingView.this;
                int i3 = LongPressHandlingView.$r8$clinit;
                if (!longPressHandlingView.getInteractionHandler().isLongPressHandlingEnabled || i2 != 32) {
                    return super.performAccessibilityAction(view, i2, bundle);
                }
                LongPressHandlingView longPressHandlingView2 = view instanceof LongPressHandlingView ? (LongPressHandlingView) view : null;
                if (longPressHandlingView2 == null) {
                    return false;
                }
                LongPressHandlingView.Listener listener = LongPressHandlingView.this.listener;
                if (listener == null) {
                    return true;
                }
                listener.onLongPressDetected(longPressHandlingView2, true);
                return true;
            }
        });
        this.interactionHandler$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.common.ui.view.LongPressHandlingView$interactionHandler$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.common.ui.view.LongPressHandlingView$interactionHandler$2$1, reason: invalid class name */
            final class AnonymousClass1 extends Lambda implements Function2 {
                final /* synthetic */ LongPressHandlingView this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(LongPressHandlingView longPressHandlingView) {
                    super(2);
                    this.this$0 = longPressHandlingView;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    long longValue = ((Number) obj2).longValue();
                    final Object obj3 = new Object();
                    this.this$0.getHandler().postDelayed((Runnable) obj, obj3, longValue);
                    final LongPressHandlingView longPressHandlingView = this.this$0;
                    return new DisposableHandle() { // from class: com.android.systemui.common.ui.view.LongPressHandlingView.interactionHandler.2.1.1
                        @Override // kotlinx.coroutines.DisposableHandle
                        public final void dispose() {
                            LongPressHandlingView.this.getHandler().removeCallbacksAndMessages(obj3);
                        }
                    };
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.common.ui.view.LongPressHandlingView$interactionHandler$2$2, reason: invalid class name */
            final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0 {
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(((LongPressHandlingView) this.receiver).isAttachedToWindow());
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.common.ui.view.LongPressHandlingView$interactionHandler$2$3, reason: invalid class name */
            final class AnonymousClass3 extends Lambda implements Function2 {
                final /* synthetic */ LongPressHandlingView this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(LongPressHandlingView longPressHandlingView) {
                    super(2);
                    this.this$0 = longPressHandlingView;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj).intValue();
                    ((Number) obj2).intValue();
                    LongPressHandlingView longPressHandlingView = this.this$0;
                    LongPressHandlingView.Listener listener = longPressHandlingView.listener;
                    if (listener != null) {
                        listener.onLongPressDetected(longPressHandlingView, false);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.common.ui.view.LongPressHandlingView$interactionHandler$2$4, reason: invalid class name */
            final class AnonymousClass4 extends Lambda implements Function0 {
                final /* synthetic */ LongPressHandlingView this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(LongPressHandlingView longPressHandlingView) {
                    super(0);
                    this.this$0 = longPressHandlingView;
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    LongPressHandlingView.Listener listener = this.this$0.listener;
                    if (listener != null) {
                        listener.onSingleTapDetected();
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(LongPressHandlingView.this);
                LongPressHandlingView longPressHandlingView = LongPressHandlingView.this;
                return new LongPressHandlingViewInteractionHandler(anonymousClass1, new AnonymousClass2(0, longPressHandlingView, LongPressHandlingView.class, "isAttachedToWindow", "isAttachedToWindow()Z", 0), new AnonymousClass3(longPressHandlingView), new AnonymousClass4(longPressHandlingView), function0, i, longPressHandlingViewLogger);
            }
        });
    }

    public LongPressHandlingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, new Function0() { // from class: com.android.systemui.common.ui.view.LongPressHandlingView.1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(ViewConfiguration.getLongPressTimeout());
            }
        }, 0, null, 24, null);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onLongPressDetected(View view, boolean z);

        default void onSingleTapDetected() {
        }
    }
}
