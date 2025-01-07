package com.android.systemui.statusbar.policy;

import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.IndentingPrintWriter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.res.R$styleable;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.wm.shell.R;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SmartReplyView extends ViewGroup {
    public final BreakIterator mBreakIterator;
    public PriorityQueue mCandidateButtonQueueForSqueezing;
    public int mCurrentBackgroundColor;
    public boolean mCurrentColorized;
    public int mCurrentRippleColor;
    public int mCurrentStrokeColor;
    public int mCurrentTextColor;
    public final int mDefaultBackgroundColor;
    public final int mDefaultStrokeColor;
    public final int mDefaultTextColor;
    public final int mDefaultTextColorDarkBg;
    public boolean mDidHideSystemReplies;
    public final int mHeightUpperLimit;
    public long mLastDispatchDrawTime;
    public long mLastDrawChildTime;
    public long mLastMeasureTime;
    public int mMaxNumActions;
    public int mMaxSqueezeRemeasureAttempts;
    public int mMinNumSystemGeneratedReplies;
    public final double mMinStrokeContrast;
    public final int mRippleColor;
    public final int mRippleColorDarkBg;
    public boolean mSmartRepliesGeneratedByAssistant;
    public View mSmartReplyContainer;
    public final int mSpacing;
    public final int mStrokeWidth;
    public int mTotalSqueezeRemeasureAttempts;
    public static final int MEASURE_SPEC_ANY_LENGTH = View.MeasureSpec.makeMeasureSpec(0, 0);
    public static final SmartReplyView$$ExternalSyntheticLambda0 DECREASING_MEASURED_WIDTH_WITHOUT_PADDING_COMPARATOR = new SmartReplyView$$ExternalSyntheticLambda0();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class LayoutParams extends ViewGroup.LayoutParams {
        public SmartButtonType mButtonType;
        public String mNoShowReason;
        public boolean show;
        public int squeezeStatus;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.show = false;
            this.squeezeStatus = 0;
            this.mButtonType = SmartButtonType.REPLY;
            this.mNoShowReason = "new";
        }

        public boolean isShown() {
            return this.show;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SmartActions {
        public final List actions;
        public final boolean fromAssistant;

        public SmartActions(List list, boolean z) {
            this.actions = list;
            this.fromAssistant = z;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SmartButtonType {
        public static final /* synthetic */ SmartButtonType[] $VALUES;
        public static final SmartButtonType ACTION;
        public static final SmartButtonType REPLY;

        static {
            SmartButtonType smartButtonType = new SmartButtonType("REPLY", 0);
            REPLY = smartButtonType;
            SmartButtonType smartButtonType2 = new SmartButtonType("ACTION", 1);
            ACTION = smartButtonType2;
            $VALUES = new SmartButtonType[]{smartButtonType, smartButtonType2};
        }

        public static SmartButtonType valueOf(String str) {
            return (SmartButtonType) Enum.valueOf(SmartButtonType.class, str);
        }

        public static SmartButtonType[] values() {
            return (SmartButtonType[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SmartReplies {
        public final List choices;
        public final boolean fromAssistant;
        public final PendingIntent pendingIntent;
        public final RemoteInput remoteInput;

        public SmartReplies(List list, RemoteInput remoteInput, PendingIntent pendingIntent, boolean z) {
            this.choices = list;
            this.remoteInput = remoteInput;
            this.pendingIntent = pendingIntent;
            this.fromAssistant = z;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SmartSuggestionMeasures {
        public int mMaxChildHeight;
        public int mMeasuredWidth;

        public SmartSuggestionMeasures(int i, int i2) {
            this.mMeasuredWidth = i;
            this.mMaxChildHeight = i2;
        }

        public final Object clone() {
            return new SmartSuggestionMeasures(this.mMeasuredWidth, this.mMaxChildHeight);
        }
    }

    public SmartReplyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSmartRepliesGeneratedByAssistant = false;
        this.mHeightUpperLimit = NotificationUtils.getFontScaledHeight(R.dimen.smart_reply_button_max_height, ((ViewGroup) this).mContext);
        int color = context.getColor(R.color.smart_reply_button_background);
        this.mDefaultBackgroundColor = color;
        this.mDefaultTextColor = ((ViewGroup) this).mContext.getColor(R.color.smart_reply_button_text);
        this.mDefaultTextColorDarkBg = ((ViewGroup) this).mContext.getColor(R.color.smart_reply_button_text_dark_bg);
        int color2 = ((ViewGroup) this).mContext.getColor(R.color.smart_reply_button_stroke);
        this.mDefaultStrokeColor = color2;
        int color3 = ((ViewGroup) this).mContext.getColor(R.color.notification_ripple_untinted_color);
        this.mRippleColor = color3;
        this.mRippleColorDarkBg = Color.argb(Color.alpha(color3), 255, 255, 255);
        this.mMinStrokeContrast = ContrastColorUtil.calculateContrast(color2, color);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SmartReplyView, 0, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == 1) {
                i2 = obtainStyledAttributes.getDimensionPixelSize(i3, 0);
            } else if (index == 0) {
                i = obtainStyledAttributes.getDimensionPixelSize(i3, 0);
            }
        }
        obtainStyledAttributes.recycle();
        this.mStrokeWidth = i;
        this.mSpacing = i2;
        this.mBreakIterator = BreakIterator.getLineInstance();
        setBackgroundTintColor(this.mDefaultBackgroundColor, false);
        this.mCandidateButtonQueueForSqueezing = new PriorityQueue(Math.max(getChildCount(), 1), DECREASING_MEASURED_WIDTH_WITHOUT_PADDING_COMPARATOR);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.mLastDispatchDrawTime = SystemClock.elapsedRealtime();
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        if (!((LayoutParams) view.getLayoutParams()).show) {
            return false;
        }
        this.mLastDrawChildTime = SystemClock.elapsedRealtime();
        return super.drawChild(canvas, view, j);
    }

    public final void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println(this);
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.print("mMaxSqueezeRemeasureAttempts=");
        indentingPrintWriter.println(this.mMaxSqueezeRemeasureAttempts);
        indentingPrintWriter.print("mTotalSqueezeRemeasureAttempts=");
        indentingPrintWriter.println(this.mTotalSqueezeRemeasureAttempts);
        indentingPrintWriter.print("mMaxNumActions=");
        indentingPrintWriter.println(this.mMaxNumActions);
        indentingPrintWriter.print("mSmartRepliesGeneratedByAssistant=");
        indentingPrintWriter.println(this.mSmartRepliesGeneratedByAssistant);
        indentingPrintWriter.print("mMinNumSystemGeneratedReplies=");
        indentingPrintWriter.println(this.mMinNumSystemGeneratedReplies);
        indentingPrintWriter.print("mHeightUpperLimit=");
        indentingPrintWriter.println(this.mHeightUpperLimit);
        indentingPrintWriter.print("mDidHideSystemReplies=");
        indentingPrintWriter.println(this.mDidHideSystemReplies);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        indentingPrintWriter.print("lastMeasureAge (s)=");
        indentingPrintWriter.println(this.mLastMeasureTime == 0 ? Float.NaN : (elapsedRealtime - r2) / 1000.0f);
        indentingPrintWriter.print("lastDrawChildAge (s)=");
        indentingPrintWriter.println(this.mLastDrawChildTime == 0 ? Float.NaN : (elapsedRealtime - r2) / 1000.0f);
        indentingPrintWriter.print("lastDispatchDrawAge (s)=");
        indentingPrintWriter.println(this.mLastDispatchDrawTime != 0 ? (elapsedRealtime - r2) / 1000.0f : Float.NaN);
        int childCount = getChildCount();
        indentingPrintWriter.print("children: num=");
        indentingPrintWriter.println(childCount);
        indentingPrintWriter.increaseIndent();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            indentingPrintWriter.print("[");
            indentingPrintWriter.print(i);
            indentingPrintWriter.print("] type=");
            indentingPrintWriter.print(layoutParams.mButtonType);
            indentingPrintWriter.print(" squeezeStatus=");
            indentingPrintWriter.print(layoutParams.squeezeStatus);
            indentingPrintWriter.print(" show=");
            indentingPrintWriter.print(layoutParams.show);
            indentingPrintWriter.print(" noShowReason=");
            indentingPrintWriter.print(layoutParams.mNoShowReason);
            indentingPrintWriter.print(" view=");
            indentingPrintWriter.println(childAt);
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.decreaseIndent();
    }

    public final List filterActionsOrReplies(SmartButtonType smartButtonType) {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() == 0 && (childAt instanceof Button) && layoutParams.mButtonType == smartButtonType) {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = getLayoutDirection() == 1;
        int i5 = z2 ? (i3 - i) - ((ViewGroup) this).mPaddingRight : ((ViewGroup) this).mPaddingLeft;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (((LayoutParams) childAt.getLayoutParams()).show) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i7 = z2 ? i5 - measuredWidth : i5;
                childAt.layout(i7, 0, i7 + measuredWidth, measuredHeight);
                int i8 = measuredWidth + this.mSpacing;
                i5 = z2 ? i5 - i8 : i5 + i8;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:185:0x0382  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r30, int r31) {
        /*
            Method dump skipped, instructions count: 1001
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.SmartReplyView.onMeasure(int, int):void");
    }

    public final void setBackgroundTintColor(int i, boolean z) {
        if (i == this.mCurrentBackgroundColor && z == this.mCurrentColorized) {
            return;
        }
        this.mCurrentBackgroundColor = i;
        this.mCurrentColorized = z;
        boolean isColorDark = ContrastColorUtil.isColorDark(i);
        int i2 = isColorDark ? this.mDefaultTextColorDarkBg : this.mDefaultTextColor;
        int i3 = i | DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
        int ensureTextContrast = ContrastColorUtil.ensureTextContrast(i2, i3, isColorDark);
        this.mCurrentTextColor = ensureTextContrast;
        if (!z) {
            ensureTextContrast = ContrastColorUtil.ensureContrast(this.mDefaultStrokeColor, i3, isColorDark, this.mMinStrokeContrast);
        }
        this.mCurrentStrokeColor = ensureTextContrast;
        this.mCurrentRippleColor = isColorDark ? this.mRippleColorDarkBg : this.mRippleColor;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            setButtonColors((Button) getChildAt(i4));
        }
    }

    public final void setButtonColors(Button button) {
        Drawable background = button.getBackground();
        if (background instanceof RippleDrawable) {
            Drawable mutate = background.mutate();
            RippleDrawable rippleDrawable = (RippleDrawable) mutate;
            rippleDrawable.setColor(ColorStateList.valueOf(this.mCurrentRippleColor));
            Drawable drawable = rippleDrawable.getDrawable(0);
            if (drawable instanceof InsetDrawable) {
                Drawable drawable2 = ((InsetDrawable) drawable).getDrawable();
                if (drawable2 instanceof GradientDrawable) {
                    GradientDrawable gradientDrawable = (GradientDrawable) drawable2;
                    gradientDrawable.setColor(this.mCurrentBackgroundColor);
                    gradientDrawable.setStroke(this.mStrokeWidth, this.mCurrentStrokeColor);
                }
            }
            button.setBackground(mutate);
        }
        button.setTextColor(this.mCurrentTextColor);
    }

    @Override // android.view.ViewGroup
    public final LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutParams layoutParams = new LayoutParams(((ViewGroup) this).mContext, attributeSet);
        layoutParams.show = false;
        layoutParams.squeezeStatus = 0;
        layoutParams.mButtonType = SmartButtonType.REPLY;
        layoutParams.mNoShowReason = "new";
        return layoutParams;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams.width, layoutParams.height);
    }
}
