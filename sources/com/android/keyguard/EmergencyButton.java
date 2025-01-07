package com.android.keyguard;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import com.android.internal.util.EmergencyAffordanceManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class EmergencyButton extends Button {
    public int mDownX;
    public int mDownY;
    public final EmergencyAffordanceManager mEmergencyAffordanceManager;
    public final boolean mEnableEmergencyCallWhileSimLocked;
    public boolean mLongPressWasDragged;

    /* renamed from: $r8$lambda$HEMKmkVNKF-XedRWg2uz8LyJ1f8, reason: not valid java name */
    public static boolean m750$r8$lambda$HEMKmkVNKFXedRWg2uz8LyJ1f8(EmergencyButton emergencyButton) {
        if (emergencyButton.getVisibility() != 0 || !TextUtils.equals(emergencyButton.getText(), ((Button) emergencyButton).mContext.getString(R.string.lockscreen_permanent_disabled_sim_instructions)) || emergencyButton.mLongPressWasDragged || !emergencyButton.mEmergencyAffordanceManager.needsEmergencyAffordance()) {
            return false;
        }
        emergencyButton.mEmergencyAffordanceManager.performEmergencyCall();
        return true;
    }

    public EmergencyButton(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        if (this.mEmergencyAffordanceManager.needsEmergencyAffordance()) {
            setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.keyguard.EmergencyButton$$ExternalSyntheticLambda0
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return EmergencyButton.m750$r8$lambda$HEMKmkVNKFXedRWg2uz8LyJ1f8(EmergencyButton.this);
                }
            });
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getActionMasked() == 0) {
            this.mDownX = x;
            this.mDownY = y;
            this.mLongPressWasDragged = false;
        } else {
            int abs = Math.abs(x - this.mDownX);
            int abs2 = Math.abs(y - this.mDownY);
            int scaledTouchSlop = ViewConfiguration.get(((Button) this).mContext).getScaledTouchSlop();
            if (Math.abs(abs2) > scaledTouchSlop || Math.abs(abs) > scaledTouchSlop) {
                this.mLongPressWasDragged = true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public EmergencyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableEmergencyCallWhileSimLocked = ((Button) this).mContext.getResources().getBoolean(R.bool.config_faceAuthSupportsSelfIllumination);
        this.mEmergencyAffordanceManager = new EmergencyAffordanceManager(context);
    }
}
