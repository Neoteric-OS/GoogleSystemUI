package com.android.systemui.keyguard;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda8;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardIndication {
    public final Drawable mBackground;
    public final boolean mForceAccessibilityLiveRegionAssertive;
    public final Drawable mIcon;
    public final CharSequence mMessage;
    public final Long mMinVisibilityMillis;
    public final KeyguardIndicationController$$ExternalSyntheticLambda8 mOnClickListener;
    public final ColorStateList mTextColor;

    public KeyguardIndication(CharSequence charSequence, ColorStateList colorStateList, Drawable drawable, KeyguardIndicationController$$ExternalSyntheticLambda8 keyguardIndicationController$$ExternalSyntheticLambda8, Drawable drawable2, Long l, Boolean bool) {
        this.mMessage = charSequence;
        this.mTextColor = colorStateList;
        this.mIcon = drawable;
        this.mOnClickListener = keyguardIndicationController$$ExternalSyntheticLambda8;
        this.mBackground = drawable2;
        this.mMinVisibilityMillis = l;
        this.mForceAccessibilityLiveRegionAssertive = bool.booleanValue();
    }

    public final String toString() {
        String str;
        if (TextUtils.isEmpty(this.mMessage)) {
            str = "KeyguardIndication{";
        } else {
            str = "KeyguardIndication{mMessage=" + ((Object) this.mMessage);
        }
        if (this.mIcon != null) {
            StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, " mIcon=");
            m.append(this.mIcon);
            str = m.toString();
        }
        KeyguardIndicationController$$ExternalSyntheticLambda8 keyguardIndicationController$$ExternalSyntheticLambda8 = this.mOnClickListener;
        if (keyguardIndicationController$$ExternalSyntheticLambda8 != null) {
            str = str + " mOnClickListener=" + keyguardIndicationController$$ExternalSyntheticLambda8;
        }
        if (this.mBackground != null) {
            StringBuilder m2 = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, " mBackground=");
            m2.append(this.mBackground);
            str = m2.toString();
        }
        Long l = this.mMinVisibilityMillis;
        if (l != null) {
            str = str + " mMinVisibilityMillis=" + l;
        }
        if (this.mForceAccessibilityLiveRegionAssertive) {
            str = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "mForceAccessibilityLiveRegionAssertive");
        }
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "}");
    }
}
