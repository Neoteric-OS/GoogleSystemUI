package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.graphics.ColorUtils;
import com.android.app.animation.Interpolators;
import com.android.keyguard.KeyguardConstants;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.systemui.qs.tiles.UserDetailItemView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class KeyguardUserDetailItemView extends UserDetailItemView {
    public static final boolean DEBUG = KeyguardConstants.DEBUG;
    public float mDarkAmount;
    public int mTextColor;

    public KeyguardUserDetailItemView(Context context) {
        this(context, null);
    }

    @Override // com.android.systemui.qs.tiles.UserDetailItemView
    public final int getFontSizeDimen() {
        return R.dimen.kg_user_switcher_text_size;
    }

    @Override // com.android.systemui.qs.tiles.UserDetailItemView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int currentTextColor = this.mName.getCurrentTextColor();
        this.mTextColor = currentTextColor;
        this.mName.setTextColor(ColorUtils.blendARGB(currentTextColor, this.mDarkAmount, -1));
    }

    public final void updateVisibilities(boolean z, boolean z2, boolean z3) {
        if (DEBUG) {
            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("updateVisibilities itemIsShown=", " nameIsShown=", " animate=", z, z2), z3, "KeyguardUserDetailItemView");
        }
        getBackground().setAlpha((z && z2) ? 255 : 0);
        if (!z) {
            setVisibility(8);
            setAlpha(1.0f);
            this.mName.setVisibility(z2 ? 0 : 8);
            this.mName.setAlpha(1.0f);
            return;
        }
        if (z2) {
            this.mName.setVisibility(0);
            if (z3) {
                this.mName.setAlpha(0.0f);
                this.mName.animate().alpha(1.0f).setDuration(240L).setInterpolator(Interpolators.ALPHA_IN);
            } else {
                this.mName.setAlpha(1.0f);
            }
        } else if (z3) {
            this.mName.setVisibility(0);
            this.mName.setAlpha(1.0f);
            this.mName.animate().alpha(0.0f).setDuration(240L).setInterpolator(Interpolators.ALPHA_OUT).withEndAction(new Runnable() { // from class: com.android.systemui.statusbar.policy.KeyguardUserDetailItemView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    KeyguardUserDetailItemView keyguardUserDetailItemView = KeyguardUserDetailItemView.this;
                    boolean z4 = KeyguardUserDetailItemView.DEBUG;
                    keyguardUserDetailItemView.mName.setVisibility(8);
                    keyguardUserDetailItemView.mName.setAlpha(1.0f);
                }
            });
        } else {
            this.mName.setVisibility(8);
            this.mName.setAlpha(1.0f);
        }
        setVisibility(0);
        setAlpha(1.0f);
    }

    public KeyguardUserDetailItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KeyguardUserDetailItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public KeyguardUserDetailItemView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
