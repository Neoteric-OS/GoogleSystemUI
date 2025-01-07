package com.android.keyguard;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import com.android.keyguard.PasswordTextView;
import com.android.keyguard.PasswordTextView.CharState;
import com.android.systemui.res.R$styleable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class NumPadKey extends ViewGroup implements NumPadAnimationListener {
    public static String[] sKlondike;
    public boolean mAnimationsEnabled;
    public final NumPadAnimator mAnimator;
    public final int mDigit;
    public final TextView mDigitText;
    public final TextView mKlondikeText;
    public final AnonymousClass1 mListener;
    public int mOrientation;
    public final PowerManager mPM;
    public PasswordTextView mTextView;
    public final int mTextViewResId;

    public NumPadKey(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        this.mOrientation = configuration.orientation;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setTextEntryKey(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredHeight = this.mDigitText.getMeasuredHeight();
        int measuredHeight2 = this.mKlondikeText.getMeasuredHeight();
        int height = (getHeight() / 2) - ((measuredHeight + measuredHeight2) / 2);
        int width = getWidth() / 2;
        int measuredWidth = width - (this.mDigitText.getMeasuredWidth() / 2);
        int i5 = measuredHeight + height;
        TextView textView = this.mDigitText;
        textView.layout(measuredWidth, height, textView.getMeasuredWidth() + measuredWidth, i5);
        int i6 = (int) (i5 - (measuredHeight2 * 0.35f));
        int measuredWidth2 = width - (this.mKlondikeText.getMeasuredWidth() / 2);
        TextView textView2 = this.mKlondikeText;
        textView2.layout(measuredWidth2, i6, textView2.getMeasuredWidth() + measuredWidth2, measuredHeight2 + i6);
        int i7 = i3 - i;
        int i8 = i4 - i2;
        NumPadAnimator numPadAnimator = this.mAnimator;
        if (numPadAnimator != null) {
            numPadAnimator.onLayout(i7, i8);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChildren(i, i2);
        int measuredWidth = getMeasuredWidth();
        if (this.mAnimator == null || this.mOrientation == 2) {
            measuredWidth = (int) (measuredWidth * 0.66f);
        }
        setMeasuredDimension(getMeasuredWidth(), measuredWidth);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        NumPadAnimator numPadAnimator;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            performHapticFeedback(1, 1);
            NumPadAnimator numPadAnimator2 = this.mAnimator;
            if (numPadAnimator2 != null && this.mAnimationsEnabled) {
                numPadAnimator2.mExpandAnimatorSet.cancel();
                numPadAnimator2.mContractAnimatorSet.cancel();
                numPadAnimator2.mExpandAnimatorSet.start();
            }
        } else if ((actionMasked == 1 || actionMasked == 3) && (numPadAnimator = this.mAnimator) != null && this.mAnimationsEnabled) {
            numPadAnimator.mExpandAnimatorSet.cancel();
            numPadAnimator.mContractAnimatorSet.cancel();
            numPadAnimator.mContractAnimatorSet.start();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.android.keyguard.NumPadAnimationListener
    public final void setProgress(float f) {
        NumPadAnimator numPadAnimator = this.mAnimator;
        if (numPadAnimator != null) {
            numPadAnimator.setProgress(f);
        }
    }

    public NumPadKey(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.numPadKeyStyle);
    }

    public NumPadKey(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.layout.keyguard_num_pad_key);
    }

    public NumPadKey(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.mDigit = -1;
        this.mAnimationsEnabled = true;
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.keyguard.NumPadKey.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PasswordTextView.CharState charState;
                View findViewById;
                NumPadKey numPadKey = NumPadKey.this;
                if (numPadKey.mTextView == null && numPadKey.mTextViewResId > 0 && (findViewById = numPadKey.getRootView().findViewById(NumPadKey.this.mTextViewResId)) != null && (findViewById instanceof PasswordTextView)) {
                    NumPadKey.this.mTextView = (PasswordTextView) findViewById;
                }
                PasswordTextView passwordTextView = NumPadKey.this.mTextView;
                if (passwordTextView != null && passwordTextView.isEnabled()) {
                    NumPadKey numPadKey2 = NumPadKey.this;
                    PasswordTextView passwordTextView2 = numPadKey2.mTextView;
                    char forDigit = Character.forDigit(numPadKey2.mDigit, 10);
                    CharSequence transformedText = passwordTextView2.getTransformedText();
                    String m = OpaqueKey$$ExternalSyntheticOutline0.m(new StringBuilder(), passwordTextView2.mText, forDigit);
                    passwordTextView2.mText = m;
                    int length = m.length();
                    if (length > passwordTextView2.mTextChars.size()) {
                        charState = passwordTextView2.new CharState();
                        charState.whichChar = forDigit;
                        passwordTextView2.mTextChars.add(charState);
                    } else {
                        charState = (PasswordTextView.CharState) passwordTextView2.mTextChars.get(length - 1);
                        charState.whichChar = forDigit;
                    }
                    PasswordTextView passwordTextView3 = PasswordTextView.this;
                    boolean z = passwordTextView3.mShowPassword;
                    boolean z2 = !z && (charState.dotAnimator == null || !charState.dotAnimationIsGrowing);
                    boolean z3 = z && (charState.textAnimator == null || !charState.textAnimationIsGrowing);
                    boolean z4 = charState.widthAnimator == null || !charState.widthAnimationIsGrowing;
                    if (z2) {
                        charState.startDotAppearAnimation(0L);
                    }
                    if (z3) {
                        PasswordTextView.CharState.cancelAnimator(charState.textAnimator);
                        ValueAnimator ofFloat = ValueAnimator.ofFloat(charState.currentTextSizeFactor, 1.0f);
                        charState.textAnimator = ofFloat;
                        ofFloat.addUpdateListener(charState.mTextSizeUpdater);
                        charState.textAnimator.addListener(charState.textFinishListener);
                        charState.textAnimator.setInterpolator(passwordTextView3.mAppearInterpolator);
                        charState.textAnimator.setDuration((long) ((1.0f - charState.currentTextSizeFactor) * 160.0f));
                        charState.textAnimator.start();
                        charState.textAnimationIsGrowing = true;
                        if (charState.textTranslateAnimator == null) {
                            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
                            charState.textTranslateAnimator = ofFloat2;
                            ofFloat2.addUpdateListener(charState.mTextTranslationUpdater);
                            charState.textTranslateAnimator.addListener(charState.textTranslateFinishListener);
                            charState.textTranslateAnimator.setInterpolator(passwordTextView3.mAppearInterpolator);
                            charState.textTranslateAnimator.setDuration(160L);
                            charState.textTranslateAnimator.start();
                        }
                    }
                    if (z4) {
                        PasswordTextView.CharState.cancelAnimator(charState.widthAnimator);
                        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(charState.currentWidthFactor, 1.0f);
                        charState.widthAnimator = ofFloat3;
                        ofFloat3.addUpdateListener(charState.mWidthUpdater);
                        charState.widthAnimator.addListener(charState.widthFinishListener);
                        charState.widthAnimator.setDuration((long) ((1.0f - charState.currentWidthFactor) * 160.0f));
                        charState.widthAnimator.start();
                        charState.widthAnimationIsGrowing = true;
                    }
                    if (passwordTextView3.mShowPassword) {
                        passwordTextView3.removeCallbacks(charState.dotSwapperRunnable);
                        charState.isDotSwapPending = false;
                        passwordTextView3.postDelayed(charState.dotSwapperRunnable, 1300L);
                        charState.isDotSwapPending = true;
                    }
                    if (length > 1) {
                        PasswordTextView.CharState charState2 = (PasswordTextView.CharState) passwordTextView2.mTextChars.get(length - 2);
                        if (charState2.isDotSwapPending) {
                            PasswordTextView.CharState.AnonymousClass10 anonymousClass10 = charState2.dotSwapperRunnable;
                            PasswordTextView passwordTextView4 = PasswordTextView.this;
                            passwordTextView4.removeCallbacks(anonymousClass10);
                            charState2.isDotSwapPending = false;
                            ValueAnimator valueAnimator = charState2.textAnimator;
                            if (valueAnimator != null) {
                                long duration = (valueAnimator.getDuration() - charState2.textAnimator.getCurrentPlayTime()) + 100;
                                passwordTextView4.removeCallbacks(charState2.dotSwapperRunnable);
                                charState2.isDotSwapPending = false;
                                passwordTextView4.postDelayed(charState2.dotSwapperRunnable, duration);
                                charState2.isDotSwapPending = true;
                            } else {
                                charState2.startTextDisappearAnimation(0L);
                                charState2.startDotAppearAnimation(30L);
                            }
                        }
                    }
                    PinShapeInput pinShapeInput = passwordTextView2.mPinShapeInput;
                    if (pinShapeInput != null) {
                        pinShapeInput.append();
                    }
                    passwordTextView2.onUserActivity();
                    passwordTextView2.sendAccessibilityEventTypeViewTextChanged(((StringBuilder) transformedText).length(), 0, 1, transformedText);
                }
                NumPadKey.this.mPM.userActivity(SystemClock.uptimeMillis(), false);
            }
        };
        setFocusable(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.NumPadKey, i, i2);
        try {
            int i3 = obtainStyledAttributes.getInt(0, -1);
            this.mDigit = i3;
            this.mTextViewResId = obtainStyledAttributes.getResourceId(1, 0);
            obtainStyledAttributes.recycle();
            setOnClickListener(onClickListener);
            this.mPM = (PowerManager) ((ViewGroup) this).mContext.getSystemService("power");
            ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(i2, (ViewGroup) this, true);
            TextView textView = (TextView) findViewById(R.id.digit_text);
            this.mDigitText = textView;
            textView.setText(Integer.toString(i3));
            TextView textView2 = (TextView) findViewById(R.id.klondike_text);
            this.mKlondikeText = textView2;
            if (i3 >= 0) {
                if (sKlondike == null) {
                    sKlondike = getResources().getStringArray(R.array.lockscreen_num_pad_klondike);
                }
                String[] strArr = sKlondike;
                if (strArr != null && strArr.length > i3) {
                    String str = strArr[i3];
                    if (str.length() > 0) {
                        textView2.setText(str);
                    } else if (textView2.getVisibility() != 8) {
                        textView2.setVisibility(4);
                    }
                }
            }
            setContentDescription(textView.getText().toString());
            Drawable background = getBackground();
            if (background instanceof GradientDrawable) {
                this.mAnimator = new NumPadAnimator(context, background.mutate(), R.style.NumPadKey, textView, null);
            } else {
                this.mAnimator = null;
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
