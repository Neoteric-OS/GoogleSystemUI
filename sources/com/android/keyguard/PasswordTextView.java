package com.android.keyguard;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.android.systemui.res.R$styleable;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PasswordTextView extends FrameLayout {
    public final Interpolator mAppearInterpolator;
    public final int mCharPadding;
    public final Interpolator mDisappearInterpolator;
    public final int mDotSize;
    public int mDrawColor;
    public final Paint mDrawPaint;
    public final int mGravity;
    public boolean mIsPinHinting;
    public final PowerManager mPM;
    public PinShapeInput mPinShapeInput;
    public boolean mShowPassword;
    public String mText;
    public final ArrayList mTextChars;
    public int mTextHeightRaw;
    public boolean mUsePinShapes;
    public BasePasswordTextView$UserActivityListener mUserActivityListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CharState {
        public float currentDotSizeFactor;
        public float currentTextSizeFactor;
        public float currentWidthFactor;
        public boolean dotAnimationIsGrowing;
        public Animator dotAnimator;
        public final AnonymousClass2 dotFinishListener;
        public boolean isDotSwapPending;
        public final AnonymousClass6 mDotSizeUpdater;
        public final AnonymousClass6 mTextSizeUpdater;
        public final AnonymousClass6 mTextTranslationUpdater;
        public final AnonymousClass6 mWidthUpdater;
        public boolean textAnimationIsGrowing;
        public ValueAnimator textAnimator;
        public final AnonymousClass2 textFinishListener;
        public ValueAnimator textTranslateAnimator;
        public final AnonymousClass2 textTranslateFinishListener;
        public char whichChar;
        public boolean widthAnimationIsGrowing;
        public ValueAnimator widthAnimator;
        public final AnonymousClass2 widthFinishListener;
        public float currentTextTranslationY = 1.0f;
        public final AnonymousClass1 removeEndListener = new AnimatorListenerAdapter() { // from class: com.android.keyguard.PasswordTextView.CharState.1
            public boolean mCancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                this.mCancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                if (this.mCancelled) {
                    return;
                }
                CharState charState = CharState.this;
                PasswordTextView.this.mTextChars.remove(charState);
                CharState.cancelAnimator(CharState.this.textTranslateAnimator);
                CharState.this.textTranslateAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                this.mCancelled = false;
            }
        };
        public final AnonymousClass10 dotSwapperRunnable = new Runnable() { // from class: com.android.keyguard.PasswordTextView.CharState.10
            @Override // java.lang.Runnable
            public final void run() {
                CharState charState = CharState.this;
                charState.startTextDisappearAnimation(0L);
                charState.startDotAppearAnimation(30L);
                CharState.this.isDotSwapPending = false;
            }
        };

        /* JADX WARN: Type inference failed for: r2v10, types: [com.android.keyguard.PasswordTextView$CharState$6] */
        /* JADX WARN: Type inference failed for: r2v11, types: [com.android.keyguard.PasswordTextView$CharState$10] */
        /* JADX WARN: Type inference failed for: r2v2, types: [com.android.keyguard.PasswordTextView$CharState$1] */
        /* JADX WARN: Type inference failed for: r2v3, types: [com.android.keyguard.PasswordTextView$CharState$2] */
        /* JADX WARN: Type inference failed for: r2v4, types: [com.android.keyguard.PasswordTextView$CharState$2] */
        /* JADX WARN: Type inference failed for: r2v5, types: [com.android.keyguard.PasswordTextView$CharState$2] */
        /* JADX WARN: Type inference failed for: r2v6, types: [com.android.keyguard.PasswordTextView$CharState$2] */
        /* JADX WARN: Type inference failed for: r2v7, types: [com.android.keyguard.PasswordTextView$CharState$6] */
        /* JADX WARN: Type inference failed for: r2v8, types: [com.android.keyguard.PasswordTextView$CharState$6] */
        /* JADX WARN: Type inference failed for: r2v9, types: [com.android.keyguard.PasswordTextView$CharState$6] */
        public CharState() {
            final int i = 0;
            this.dotFinishListener = new AnimatorListenerAdapter(this) { // from class: com.android.keyguard.PasswordTextView.CharState.2
                public final /* synthetic */ CharState this$1;

                {
                    this.this$1 = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i) {
                        case 0:
                            this.this$1.dotAnimator = null;
                            break;
                        case 1:
                            this.this$1.textAnimator = null;
                            break;
                        case 2:
                            this.this$1.textTranslateAnimator = null;
                            break;
                        default:
                            this.this$1.widthAnimator = null;
                            break;
                    }
                }
            };
            final int i2 = 1;
            this.textFinishListener = new AnimatorListenerAdapter(this) { // from class: com.android.keyguard.PasswordTextView.CharState.2
                public final /* synthetic */ CharState this$1;

                {
                    this.this$1 = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i2) {
                        case 0:
                            this.this$1.dotAnimator = null;
                            break;
                        case 1:
                            this.this$1.textAnimator = null;
                            break;
                        case 2:
                            this.this$1.textTranslateAnimator = null;
                            break;
                        default:
                            this.this$1.widthAnimator = null;
                            break;
                    }
                }
            };
            final int i3 = 2;
            this.textTranslateFinishListener = new AnimatorListenerAdapter(this) { // from class: com.android.keyguard.PasswordTextView.CharState.2
                public final /* synthetic */ CharState this$1;

                {
                    this.this$1 = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i3) {
                        case 0:
                            this.this$1.dotAnimator = null;
                            break;
                        case 1:
                            this.this$1.textAnimator = null;
                            break;
                        case 2:
                            this.this$1.textTranslateAnimator = null;
                            break;
                        default:
                            this.this$1.widthAnimator = null;
                            break;
                    }
                }
            };
            final int i4 = 3;
            this.widthFinishListener = new AnimatorListenerAdapter(this) { // from class: com.android.keyguard.PasswordTextView.CharState.2
                public final /* synthetic */ CharState this$1;

                {
                    this.this$1 = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i4) {
                        case 0:
                            this.this$1.dotAnimator = null;
                            break;
                        case 1:
                            this.this$1.textAnimator = null;
                            break;
                        case 2:
                            this.this$1.textTranslateAnimator = null;
                            break;
                        default:
                            this.this$1.widthAnimator = null;
                            break;
                    }
                }
            };
            final int i5 = 0;
            this.mDotSizeUpdater = new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.PasswordTextView.CharState.6
                public final /* synthetic */ CharState this$1;

                {
                    this.this$1 = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    switch (i5) {
                        case 0:
                            this.this$1.currentDotSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                        case 1:
                            boolean isCharVisibleForA11y = this.this$1.isCharVisibleForA11y();
                            CharState charState = this.this$1;
                            float f = charState.currentTextSizeFactor;
                            charState.currentTextSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            if (isCharVisibleForA11y != this.this$1.isCharVisibleForA11y()) {
                                CharState charState2 = this.this$1;
                                charState2.currentTextSizeFactor = f;
                                CharSequence transformedText = PasswordTextView.this.getTransformedText();
                                this.this$1.currentTextSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                CharState charState3 = this.this$1;
                                int indexOf = PasswordTextView.this.mTextChars.indexOf(charState3);
                                if (indexOf >= 0) {
                                    PasswordTextView.this.sendAccessibilityEventTypeViewTextChanged(indexOf, 1, 1, transformedText);
                                }
                            }
                            PasswordTextView.this.invalidate();
                            break;
                        case 2:
                            this.this$1.currentTextTranslationY = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                        default:
                            this.this$1.currentWidthFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                    }
                }
            };
            final int i6 = 1;
            this.mTextSizeUpdater = new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.PasswordTextView.CharState.6
                public final /* synthetic */ CharState this$1;

                {
                    this.this$1 = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    switch (i6) {
                        case 0:
                            this.this$1.currentDotSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                        case 1:
                            boolean isCharVisibleForA11y = this.this$1.isCharVisibleForA11y();
                            CharState charState = this.this$1;
                            float f = charState.currentTextSizeFactor;
                            charState.currentTextSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            if (isCharVisibleForA11y != this.this$1.isCharVisibleForA11y()) {
                                CharState charState2 = this.this$1;
                                charState2.currentTextSizeFactor = f;
                                CharSequence transformedText = PasswordTextView.this.getTransformedText();
                                this.this$1.currentTextSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                CharState charState3 = this.this$1;
                                int indexOf = PasswordTextView.this.mTextChars.indexOf(charState3);
                                if (indexOf >= 0) {
                                    PasswordTextView.this.sendAccessibilityEventTypeViewTextChanged(indexOf, 1, 1, transformedText);
                                }
                            }
                            PasswordTextView.this.invalidate();
                            break;
                        case 2:
                            this.this$1.currentTextTranslationY = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                        default:
                            this.this$1.currentWidthFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                    }
                }
            };
            final int i7 = 2;
            this.mTextTranslationUpdater = new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.PasswordTextView.CharState.6
                public final /* synthetic */ CharState this$1;

                {
                    this.this$1 = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    switch (i7) {
                        case 0:
                            this.this$1.currentDotSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                        case 1:
                            boolean isCharVisibleForA11y = this.this$1.isCharVisibleForA11y();
                            CharState charState = this.this$1;
                            float f = charState.currentTextSizeFactor;
                            charState.currentTextSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            if (isCharVisibleForA11y != this.this$1.isCharVisibleForA11y()) {
                                CharState charState2 = this.this$1;
                                charState2.currentTextSizeFactor = f;
                                CharSequence transformedText = PasswordTextView.this.getTransformedText();
                                this.this$1.currentTextSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                CharState charState3 = this.this$1;
                                int indexOf = PasswordTextView.this.mTextChars.indexOf(charState3);
                                if (indexOf >= 0) {
                                    PasswordTextView.this.sendAccessibilityEventTypeViewTextChanged(indexOf, 1, 1, transformedText);
                                }
                            }
                            PasswordTextView.this.invalidate();
                            break;
                        case 2:
                            this.this$1.currentTextTranslationY = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                        default:
                            this.this$1.currentWidthFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                    }
                }
            };
            final int i8 = 3;
            this.mWidthUpdater = new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.PasswordTextView.CharState.6
                public final /* synthetic */ CharState this$1;

                {
                    this.this$1 = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    switch (i8) {
                        case 0:
                            this.this$1.currentDotSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                        case 1:
                            boolean isCharVisibleForA11y = this.this$1.isCharVisibleForA11y();
                            CharState charState = this.this$1;
                            float f = charState.currentTextSizeFactor;
                            charState.currentTextSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            if (isCharVisibleForA11y != this.this$1.isCharVisibleForA11y()) {
                                CharState charState2 = this.this$1;
                                charState2.currentTextSizeFactor = f;
                                CharSequence transformedText = PasswordTextView.this.getTransformedText();
                                this.this$1.currentTextSizeFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                CharState charState3 = this.this$1;
                                int indexOf = PasswordTextView.this.mTextChars.indexOf(charState3);
                                if (indexOf >= 0) {
                                    PasswordTextView.this.sendAccessibilityEventTypeViewTextChanged(indexOf, 1, 1, transformedText);
                                }
                            }
                            PasswordTextView.this.invalidate();
                            break;
                        case 2:
                            this.this$1.currentTextTranslationY = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                        default:
                            this.this$1.currentWidthFactor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            PasswordTextView.this.invalidate();
                            break;
                    }
                }
            };
        }

        public static void cancelAnimator(Animator animator) {
            if (animator != null) {
                animator.cancel();
            }
        }

        public final boolean isCharVisibleForA11y() {
            return this.currentTextSizeFactor > 0.0f || (this.textAnimator != null && this.textAnimationIsGrowing);
        }

        public final void startDotAppearAnimation(long j) {
            AnonymousClass2 anonymousClass2 = this.dotFinishListener;
            AnonymousClass6 anonymousClass6 = this.mDotSizeUpdater;
            cancelAnimator(this.dotAnimator);
            PasswordTextView passwordTextView = PasswordTextView.this;
            if (passwordTextView.mShowPassword) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(this.currentDotSizeFactor, 1.0f);
                ofFloat.addUpdateListener(anonymousClass6);
                ofFloat.setDuration((long) ((1.0f - this.currentDotSizeFactor) * 160.0f));
                ofFloat.addListener(anonymousClass2);
                ofFloat.setStartDelay(j);
                ofFloat.start();
                this.dotAnimator = ofFloat;
            } else {
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(this.currentDotSizeFactor, 1.5f);
                ofFloat2.addUpdateListener(anonymousClass6);
                ofFloat2.setInterpolator(passwordTextView.mAppearInterpolator);
                ofFloat2.setDuration(160L);
                ValueAnimator ofFloat3 = ValueAnimator.ofFloat(1.5f, 1.0f);
                ofFloat3.addUpdateListener(anonymousClass6);
                ofFloat3.setDuration(160L);
                ofFloat3.addListener(anonymousClass2);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(ofFloat2, ofFloat3);
                animatorSet.setStartDelay(j);
                animatorSet.start();
                this.dotAnimator = animatorSet;
            }
            this.dotAnimationIsGrowing = true;
        }

        public final void startRemoveAnimation(long j, long j2) {
            boolean z = (this.currentDotSizeFactor > 0.0f && this.dotAnimator == null) || (this.dotAnimator != null && this.dotAnimationIsGrowing);
            boolean z2 = (this.currentTextSizeFactor > 0.0f && this.textAnimator == null) || (this.textAnimator != null && this.textAnimationIsGrowing);
            boolean z3 = (this.currentWidthFactor > 0.0f && this.widthAnimator == null) || (this.widthAnimator != null && this.widthAnimationIsGrowing);
            if (z) {
                cancelAnimator(this.dotAnimator);
                ValueAnimator ofFloat = ValueAnimator.ofFloat(this.currentDotSizeFactor, 0.0f);
                ofFloat.addUpdateListener(this.mDotSizeUpdater);
                ofFloat.addListener(this.dotFinishListener);
                ofFloat.setInterpolator(PasswordTextView.this.mDisappearInterpolator);
                ofFloat.setDuration((long) (Math.min(this.currentDotSizeFactor, 1.0f) * 160.0f));
                ofFloat.setStartDelay(j);
                ofFloat.start();
                this.dotAnimator = ofFloat;
                this.dotAnimationIsGrowing = false;
            }
            if (z2) {
                startTextDisappearAnimation(j);
            }
            if (z3) {
                cancelAnimator(this.widthAnimator);
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(this.currentWidthFactor, 0.0f);
                this.widthAnimator = ofFloat2;
                ofFloat2.addUpdateListener(this.mWidthUpdater);
                this.widthAnimator.addListener(this.widthFinishListener);
                this.widthAnimator.addListener(this.removeEndListener);
                this.widthAnimator.setDuration((long) (this.currentWidthFactor * 160.0f));
                this.widthAnimator.setStartDelay(j2);
                this.widthAnimator.start();
                this.widthAnimationIsGrowing = false;
            }
        }

        public final void startTextDisappearAnimation(long j) {
            cancelAnimator(this.textAnimator);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.currentTextSizeFactor, 0.0f);
            this.textAnimator = ofFloat;
            ofFloat.addUpdateListener(this.mTextSizeUpdater);
            this.textAnimator.addListener(this.textFinishListener);
            this.textAnimator.setInterpolator(PasswordTextView.this.mDisappearInterpolator);
            this.textAnimator.setDuration((long) (this.currentTextSizeFactor * 160.0f));
            this.textAnimator.setStartDelay(j);
            this.textAnimator.start();
            this.textAnimationIsGrowing = false;
        }
    }

    public PasswordTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mText = "";
        this.mShowPassword = true;
        this.mUsePinShapes = false;
        this.mTextChars = new ArrayList();
        Paint paint = new Paint();
        this.mDrawPaint = paint;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.View);
        try {
            boolean z = obtainStyledAttributes.getBoolean(19, true);
            boolean z2 = obtainStyledAttributes.getBoolean(20, true);
            setFocusable(z);
            setFocusableInTouchMode(z2);
            obtainStyledAttributes.recycle();
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PasswordTextView);
            try {
                this.mTextHeightRaw = obtainStyledAttributes.getInt(4, 0);
                this.mGravity = obtainStyledAttributes.getInt(1, 17);
                this.mDotSize = obtainStyledAttributes.getDimensionPixelSize(3, getContext().getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.password_dot_size));
                this.mCharPadding = obtainStyledAttributes.getDimensionPixelSize(2, getContext().getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.password_char_padding));
                int color = obtainStyledAttributes.getColor(0, -1);
                this.mDrawColor = color;
                paint.setColor(color);
                obtainStyledAttributes.recycle();
                paint.setFlags(129);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTypeface(Typeface.create(context.getString(R.string.config_inCallNotificationSound), 0));
                this.mAppearInterpolator = AnimationUtils.loadInterpolator(((FrameLayout) this).mContext, R.interpolator.linear_out_slow_in);
                this.mDisappearInterpolator = AnimationUtils.loadInterpolator(((FrameLayout) this).mContext, R.interpolator.fast_out_linear_in);
                AnimationUtils.loadInterpolator(((FrameLayout) this).mContext, R.interpolator.fast_out_slow_in);
                this.mPM = (PowerManager) ((FrameLayout) this).mContext.getSystemService("power");
                setWillNotDraw(false);
            } finally {
            }
        } finally {
        }
    }

    public final Rect getCharBounds() {
        this.mDrawPaint.setTextSize(this.mTextHeightRaw * getResources().getDisplayMetrics().scaledDensity);
        Rect rect = new Rect();
        this.mDrawPaint.getTextBounds("0", 0, 1, rect);
        return rect;
    }

    public final CharSequence getTransformedText() {
        int size = this.mTextChars.size();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            CharState charState = (CharState) this.mTextChars.get(i);
            if (charState.dotAnimator == null || charState.dotAnimationIsGrowing) {
                sb.append(charState.isCharVisibleForA11y() ? charState.whichChar : (char) 8226);
            }
        }
        return sb;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        this.mTextHeightRaw = getContext().getResources().getInteger(com.android.wm.shell.R.integer.scaled_password_text_size);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float width;
        if (this.mUsePinShapes) {
            super.onDraw(canvas);
            return;
        }
        int size = this.mTextChars.size();
        Rect charBounds = getCharBounds();
        int i = charBounds.right - charBounds.left;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            CharState charState = (CharState) this.mTextChars.get(i3);
            if (i3 != 0) {
                i2 = (int) ((this.mCharPadding * charState.currentWidthFactor) + i2);
            }
            i2 = (int) ((i * charState.currentWidthFactor) + i2);
        }
        float f = i2;
        int i4 = this.mGravity;
        boolean z = true;
        if ((i4 & 7) == 3) {
            width = ((i4 & 8388608) == 0 || getLayoutDirection() != 1) ? getPaddingLeft() : (getWidth() - getPaddingRight()) - f;
        } else {
            width = (getWidth() - getPaddingRight()) - f;
            float width2 = (getWidth() / 2.0f) - (f / 2.0f);
            if (width2 > 0.0f) {
                width = width2;
            }
        }
        int size2 = this.mTextChars.size();
        Rect charBounds2 = getCharBounds();
        int i5 = charBounds2.bottom - charBounds2.top;
        float paddingTop = getPaddingTop() + (((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
        canvas.clipRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        float f2 = charBounds2.right - charBounds2.left;
        int i6 = 0;
        while (i6 < size2) {
            CharState charState2 = (CharState) this.mTextChars.get(i6);
            float f3 = charState2.currentTextSizeFactor;
            boolean z2 = f3 > 0.0f ? z : false;
            boolean z3 = charState2.currentDotSizeFactor > 0.0f ? z : false;
            float f4 = charState2.currentWidthFactor * f2;
            PasswordTextView passwordTextView = PasswordTextView.this;
            if (z2) {
                float f5 = i5;
                float f6 = (f5 * charState2.currentTextTranslationY * 0.8f) + ((f5 / 2.0f) * f3) + paddingTop;
                canvas.save();
                canvas.translate((f4 / 2.0f) + width, f6);
                float f7 = charState2.currentTextSizeFactor;
                canvas.scale(f7, f7);
                canvas.drawText(Character.toString(charState2.whichChar), 0.0f, 0.0f, passwordTextView.mDrawPaint);
                canvas.restore();
            }
            if (z3) {
                canvas.save();
                canvas.translate((f4 / 2.0f) + width, paddingTop);
                canvas.drawCircle(0.0f, 0.0f, (passwordTextView.mDotSize / 2) * charState2.currentDotSizeFactor, passwordTextView.mDrawPaint);
                canvas.restore();
            }
            width += (passwordTextView.mCharPadding * charState2.currentWidthFactor) + f4;
            i6++;
            z = true;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(EditText.class.getName());
        accessibilityEvent.setPassword(true);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(EditText.class.getName());
        accessibilityNodeInfo.setPassword(true);
        accessibilityNodeInfo.setText(getTransformedText());
        accessibilityNodeInfo.setSelected(false);
        accessibilityNodeInfo.setEditable(true);
        accessibilityNodeInfo.setInputType(16);
    }

    public final void onUserActivity() {
        this.mPM.userActivity(SystemClock.uptimeMillis(), false);
        BasePasswordTextView$UserActivityListener basePasswordTextView$UserActivityListener = this.mUserActivityListener;
        if (basePasswordTextView$UserActivityListener != null) {
            basePasswordTextView$UserActivityListener.onUserActivity();
        }
    }

    public final void sendAccessibilityEventTypeViewTextChanged(int i, int i2, int i3, CharSequence charSequence) {
        if (AccessibilityManager.getInstance(((FrameLayout) this).mContext).isEnabled()) {
            if (isFocused() || (isSelected() && isShown())) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(16);
                obtain.setFromIndex(i);
                obtain.setRemovedCount(i2);
                obtain.setAddedCount(i3);
                obtain.setBeforeText(charSequence);
                CharSequence transformedText = getTransformedText();
                if (!TextUtils.isEmpty(transformedText)) {
                    obtain.getText().add(transformedText);
                }
                obtain.setPassword(true);
                sendAccessibilityEventUnchecked(obtain);
            }
        }
    }

    public final void setIsPinHinting(boolean z) {
        PinShapeInput pinShapeInput = this.mPinShapeInput;
        if (pinShapeInput == null || this.mIsPinHinting != z) {
            this.mIsPinHinting = z;
            if (pinShapeInput != null) {
                removeView(pinShapeInput.getView());
                this.mPinShapeInput = null;
            }
            PinShapeInput pinShapeInput2 = z ? (PinShapeInput) LayoutInflater.from(((FrameLayout) this).mContext).inflate(com.android.wm.shell.R.layout.keyguard_pin_shape_hinting_view, (ViewGroup) null) : (PinShapeInput) LayoutInflater.from(((FrameLayout) this).mContext).inflate(com.android.wm.shell.R.layout.keyguard_pin_shape_non_hinting_view, (ViewGroup) null);
            this.mPinShapeInput = pinShapeInput2;
            addView(pinShapeInput2.getView());
        }
    }

    public PasswordTextView(Context context) {
        this(context, null);
    }

    public PasswordTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PasswordTextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }
}
