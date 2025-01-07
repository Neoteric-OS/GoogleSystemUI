package com.android.systemui.toast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.INotificationManager;
import android.app.ITransientNotificationCallback;
import android.content.Context;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;
import android.view.accessibility.IAccessibilityManager;
import android.widget.ToastPresenter;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.util.Objects;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ToastUI implements CoreStartable, ConfigurationController.ConfigurationListener, CommandQueue.Callbacks {
    public ITransientNotificationCallback mCallback;
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public final IAccessibilityManager mIAccessibilityManager;
    public final INotificationManager mNotificationManager;
    public int mOrientation = 1;
    public ToastPresenter mPresenter;
    SystemUIToast mToast;
    public final ToastFactory mToastFactory;
    public final ToastLogger mToastLogger;
    ToastOutAnimatorListener mToastOutAnimatorListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ToastOutAnimatorListener extends AnimatorListenerAdapter {
        public final Animator mAnimator;
        public final ITransientNotificationCallback mPrevCallback;
        public final ToastPresenter mPrevPresenter;
        public ToastUI$$ExternalSyntheticLambda0 mShowNextToastRunnable;

        public ToastOutAnimatorListener(ToastPresenter toastPresenter, ITransientNotificationCallback iTransientNotificationCallback, ToastUI$$ExternalSyntheticLambda0 toastUI$$ExternalSyntheticLambda0, Animator animator) {
            this.mPrevPresenter = toastPresenter;
            this.mPrevCallback = iTransientNotificationCallback;
            this.mShowNextToastRunnable = toastUI$$ExternalSyntheticLambda0;
            this.mAnimator = animator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            this.mPrevPresenter.hide(this.mPrevCallback);
            ToastUI$$ExternalSyntheticLambda0 toastUI$$ExternalSyntheticLambda0 = this.mShowNextToastRunnable;
            if (toastUI$$ExternalSyntheticLambda0 != null) {
                toastUI$$ExternalSyntheticLambda0.run();
            }
            this.mAnimator.removeListener(this);
            this.mShowNextToastRunnable = null;
            ToastUI.this.mToastOutAnimatorListener = null;
        }
    }

    public ToastUI(Context context, CommandQueue commandQueue, INotificationManager iNotificationManager, IAccessibilityManager iAccessibilityManager, ToastFactory toastFactory, ToastLogger toastLogger) {
        this.mContext = context;
        this.mCommandQueue = commandQueue;
        this.mNotificationManager = iNotificationManager;
        this.mIAccessibilityManager = iAccessibilityManager;
        this.mToastFactory = toastFactory;
        this.mToastLogger = toastLogger;
    }

    public final void hideCurrentToast(ToastUI$$ExternalSyntheticLambda0 toastUI$$ExternalSyntheticLambda0) {
        Animator animator = this.mToast.mOutAnimator;
        if (animator != null) {
            ToastOutAnimatorListener toastOutAnimatorListener = new ToastOutAnimatorListener(this.mPresenter, this.mCallback, toastUI$$ExternalSyntheticLambda0, animator);
            this.mToastOutAnimatorListener = toastOutAnimatorListener;
            animator.addListener(toastOutAnimatorListener);
            animator.start();
        } else {
            this.mPresenter.hide(this.mCallback);
            if (toastUI$$ExternalSyntheticLambda0 != null) {
                toastUI$$ExternalSyntheticLambda0.run();
            }
        }
        this.mToast = null;
        this.mPresenter = null;
        this.mCallback = null;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void hideToast(String str, IBinder iBinder) {
        ToastPresenter toastPresenter = this.mPresenter;
        if (toastPresenter == null || !Objects.equals(toastPresenter.getPackageName(), str) || !Objects.equals(this.mPresenter.getToken(), iBinder)) {
            Log.w("ToastUI", "Attempt to hide non-current toast from package " + str);
            return;
        }
        String iBinder2 = iBinder.toString();
        ToastLogger toastLogger = this.mToastLogger;
        toastLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        ToastLogger$logOnHideToast$2 toastLogger$logOnHideToast$2 = new Function1() { // from class: com.android.systemui.toast.ToastLogger$logOnHideToast$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return MotionLayout$$ExternalSyntheticOutline0.m("[", logMessage.getStr2(), "] Hide toast for [", logMessage.getStr1(), "]");
            }
        };
        LogBuffer logBuffer = toastLogger.buffer;
        LogMessage obtain = logBuffer.obtain("ToastLog", logLevel, toastLogger$logOnHideToast$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.str2 = iBinder2;
        logBuffer.commit(obtain);
        hideCurrentToast(null);
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        int i = configuration.orientation;
        if (i != this.mOrientation) {
            this.mOrientation = i;
            SystemUIToast systemUIToast = this.mToast;
            if (systemUIToast != null) {
                String charSequence = systemUIToast.mText.toString();
                boolean z = this.mOrientation == 1;
                ToastLogger toastLogger = this.mToastLogger;
                toastLogger.getClass();
                LogLevel logLevel = LogLevel.DEBUG;
                ToastLogger$logOrientationChange$2 toastLogger$logOrientationChange$2 = new Function1() { // from class: com.android.systemui.toast.ToastLogger$logOrientationChange$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return "Orientation change for toast. msg='" + logMessage.getStr1() + "' isPortrait=" + logMessage.getBool1();
                    }
                };
                LogBuffer logBuffer = toastLogger.buffer;
                LogMessage obtain = logBuffer.obtain("ToastLog", logLevel, toastLogger$logOrientationChange$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = charSequence;
                logMessageImpl.bool1 = z;
                logBuffer.commit(obtain);
                this.mToast.onOrientationChange(this.mOrientation);
                this.mPresenter.updateLayoutParams(this.mToast.getXOffset().intValue(), this.mToast.getYOffset().intValue(), this.mToast.getHorizontalMargin().intValue(), this.mToast.getVerticalMargin().intValue(), this.mToast.getGravity().intValue());
            }
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showToast(int i, String str, IBinder iBinder, CharSequence charSequence, IBinder iBinder2, int i2, ITransientNotificationCallback iTransientNotificationCallback, int i3) {
        ToastUI$$ExternalSyntheticLambda0 toastUI$$ExternalSyntheticLambda0 = new ToastUI$$ExternalSyntheticLambda0(this, i, i3, str, iBinder, charSequence, iTransientNotificationCallback, iBinder2, i2);
        ToastOutAnimatorListener toastOutAnimatorListener = this.mToastOutAnimatorListener;
        if (toastOutAnimatorListener != null) {
            toastOutAnimatorListener.mShowNextToastRunnable = toastUI$$ExternalSyntheticLambda0;
        } else if (this.mPresenter != null) {
            hideCurrentToast(toastUI$$ExternalSyntheticLambda0);
        } else {
            toastUI$$ExternalSyntheticLambda0.run();
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
    }
}
