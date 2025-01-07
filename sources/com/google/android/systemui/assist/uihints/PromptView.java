package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class PromptView extends TextView {
    public final DecelerateInterpolator mDecelerateInterpolator;
    public boolean mEnabled;
    public String mHandleString;
    public boolean mHasDarkBackground;
    public final Configuration mLastConfig;
    public int mLastInvocationType;
    public final float mRiseDistance;
    public String mSqueezeString;
    public final int mTextColorDark;
    public final int mTextColorLight;

    public PromptView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mHandleString = getResources().getString(R.string.handle_invocation_prompt);
        this.mSqueezeString = getResources().getString(R.string.squeeze_invocation_prompt);
        int updateFrom = this.mLastConfig.updateFrom(configuration);
        boolean z = (updateFrom & 4096) != 0;
        boolean z2 = (updateFrom & 1073741824) != 0;
        if (z || z2) {
            setTextSize(0, ((TextView) this).mContext.getResources().getDimension(R.dimen.transcription_text_size));
            updateViewHeight();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        updateViewHeight();
    }

    public final void updateViewHeight() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = (int) (((TextView) this).mContext.getResources().getDimension(R.dimen.transcription_text_size) + getResources().getDimension(R.dimen.assist_prompt_start_height) + this.mRiseDistance);
        }
        requestLayout();
    }

    public PromptView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PromptView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PromptView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDecelerateInterpolator = new DecelerateInterpolator(2.0f);
        this.mLastConfig = new Configuration();
        this.mHasDarkBackground = false;
        this.mEnabled = false;
        this.mLastInvocationType = 0;
        int color = getContext().getColor(R.color.transcription_text_dark);
        this.mTextColorDark = color;
        int color2 = getContext().getColor(R.color.transcription_text_light);
        this.mTextColorLight = color2;
        this.mRiseDistance = getResources().getDimension(R.dimen.assist_prompt_rise_distance);
        this.mHandleString = getResources().getString(R.string.handle_invocation_prompt);
        this.mSqueezeString = getResources().getString(R.string.squeeze_invocation_prompt);
        boolean z = this.mHasDarkBackground;
        boolean z2 = !z;
        if (z2 != z) {
            setTextColor(z ? color2 : color);
            this.mHasDarkBackground = z2;
        }
    }
}
