package com.android.systemui.power;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.wm.shell.R;
import kotlin.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InattentiveSleepWarningView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mDismissing;
    public final Animator mFadeOutAnimator;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public final IBinder mWindowToken;

    public InattentiveSleepWarningView(Context context, Lazy lazy) {
        super(context);
        this.mWindowToken = new Binder();
        this.mWindowManager = new ViewCaptureAwareWindowManager((WindowManager) ((FrameLayout) this).mContext.getSystemService(WindowManager.class), lazy);
        LayoutInflater.from(((FrameLayout) this).mContext).inflate(R.layout.inattentive_sleep_warning, (ViewGroup) this, true);
        setFocusable(true);
        setOnKeyListener(new InattentiveSleepWarningView$$ExternalSyntheticLambda1());
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), android.R.animator.fade_out);
        this.mFadeOutAnimator = loadAnimator;
        loadAnimator.setTarget(this);
        loadAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.power.InattentiveSleepWarningView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                InattentiveSleepWarningView inattentiveSleepWarningView = InattentiveSleepWarningView.this;
                inattentiveSleepWarningView.mDismissing = false;
                inattentiveSleepWarningView.setAlpha(1.0f);
                InattentiveSleepWarningView.this.setVisibility(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                InattentiveSleepWarningView inattentiveSleepWarningView = InattentiveSleepWarningView.this;
                if (inattentiveSleepWarningView.mDismissing) {
                    inattentiveSleepWarningView.setVisibility(4);
                    inattentiveSleepWarningView.mWindowManager.removeView(inattentiveSleepWarningView);
                }
            }
        });
    }
}
