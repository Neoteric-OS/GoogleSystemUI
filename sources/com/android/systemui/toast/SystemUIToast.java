package com.android.systemui.toast;

import android.R;
import android.animation.Animator;
import android.content.Context;
import android.view.View;
import com.android.systemui.plugins.ToastPlugin;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUIToast implements ToastPlugin.Toast {
    public final Context mContext;
    public int mDefaultGravity;
    public int mDefaultY;
    public final Animator mInAnimator;
    public final Animator mOutAnimator;
    public final ToastPlugin.Toast mPluginToast;
    public final CharSequence mText;
    public final View mToastView;

    /* JADX WARN: Removed duplicated region for block: B:13:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SystemUIToast(android.view.LayoutInflater r19, android.content.Context r20, java.lang.CharSequence r21, com.android.systemui.plugins.ToastPlugin.Toast r22, java.lang.String r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 788
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.toast.SystemUIToast.<init>(android.view.LayoutInflater, android.content.Context, java.lang.CharSequence, com.android.systemui.plugins.ToastPlugin$Toast, java.lang.String, int, int):void");
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final Integer getGravity() {
        if (isPluginToast()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getGravity() != null) {
                return toast.getGravity();
            }
        }
        return Integer.valueOf(this.mDefaultGravity);
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final Integer getHorizontalMargin() {
        if (isPluginToast()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getHorizontalMargin() != null) {
                return toast.getHorizontalMargin();
            }
        }
        return 0;
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final Animator getInAnimation() {
        return this.mInAnimator;
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final Animator getOutAnimation() {
        return this.mOutAnimator;
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final Integer getVerticalMargin() {
        if (isPluginToast()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getVerticalMargin() != null) {
                return toast.getVerticalMargin();
            }
        }
        return 0;
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final View getView() {
        return this.mToastView;
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final Integer getXOffset() {
        if (isPluginToast()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getXOffset() != null) {
                return toast.getXOffset();
            }
        }
        return 0;
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final Integer getYOffset() {
        if (isPluginToast()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getYOffset() != null) {
                return toast.getYOffset();
            }
        }
        return Integer.valueOf(this.mDefaultY);
    }

    public final boolean isPluginToast() {
        return this.mPluginToast != null;
    }

    @Override // com.android.systemui.plugins.ToastPlugin.Toast
    public final void onOrientationChange(int i) {
        ToastPlugin.Toast toast = this.mPluginToast;
        if (toast != null) {
            toast.onOrientationChange(i);
        }
        this.mDefaultY = this.mContext.getResources().getDimensionPixelSize(R.dimen.waterfall_display_left_edge_size);
        this.mDefaultGravity = this.mContext.getResources().getInteger(R.integer.config_unfoldTransitionHalfFoldedTimeout);
    }
}
