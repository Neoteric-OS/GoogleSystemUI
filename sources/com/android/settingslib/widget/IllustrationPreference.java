package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.R$styleable;
import com.android.wm.shell.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class IllustrationPreference extends Preference {
    public final AnonymousClass1 mAnimationCallback;
    public final AnonymousClass2 mAnimationCallbackCompat;
    public final boolean mCacheComposition;
    public final int mImageResId;
    public final boolean mLottieDynamicColor;
    public final int mMaxHeight;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.widget.IllustrationPreference$1, reason: invalid class name */
    public final class AnonymousClass1 extends Animatable2.AnimationCallback {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.graphics.drawable.Animatable2.AnimationCallback
        public final void onAnimationEnd(Drawable drawable) {
            ((Animatable) drawable).start();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.widget.IllustrationPreference$2, reason: invalid class name */
    public final class AnonymousClass2 extends Animatable2Compat.AnimationCallback {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
        public final void onAnimationEnd(Drawable drawable) {
            ((Animatable) drawable).start();
        }
    }

    public IllustrationPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxHeight = -1;
        this.mCacheComposition = true;
        this.mAnimationCallback = new AnonymousClass1();
        this.mAnimationCallbackCompat = new AnonymousClass2();
        this.mLayoutResId = R.layout.illustration_preference;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.LottieAnimationView, 0, 0);
            this.mImageResId = obtainStyledAttributes.getResourceId(14, 0);
            this.mCacheComposition = obtainStyledAttributes.getBoolean(2, true);
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, com.android.settingslib.widget.preference.illustration.R$styleable.IllustrationPreference, 0, 0);
            this.mLottieDynamicColor = obtainStyledAttributes2.getBoolean(0, false);
            obtainStyledAttributes2.recycle();
        }
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        InputStream openRawResource;
        super.onBindViewHolder(preferenceViewHolder);
        FrameLayout frameLayout = (FrameLayout) preferenceViewHolder.findViewById(R.id.illustration_frame);
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(R.id.background_view);
        FrameLayout frameLayout2 = (FrameLayout) preferenceViewHolder.findViewById(R.id.middleground_layout);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) preferenceViewHolder.findViewById(R.id.lottie_view);
        if (lottieAnimationView != null && !TextUtils.isEmpty(null)) {
            lottieAnimationView.setContentDescription(null);
            lottieAnimationView.setImportantForAccessibility(1);
            ((View) frameLayout.getParent()).setImportantForAccessibility(1);
        }
        int i = this.mContext.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.mContext.getResources().getDisplayMetrics().heightPixels;
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (i >= i2) {
            i = i2;
        }
        layoutParams.width = i;
        frameLayout.setLayoutParams(layoutParams);
        lottieAnimationView.setCacheComposition(this.mCacheComposition);
        if (this.mImageResId > 0) {
            try {
                openRawResource = lottieAnimationView.getResources().openRawResource(this.mImageResId);
                try {
                } finally {
                }
            } catch (IOException e) {
                Log.w("IllustrationPreference", "Unable to open Lottie raw resource", e);
            }
            if (openRawResource.read() == -1) {
                lottieAnimationView.setVisibility(8);
                frameLayout.setVisibility(8);
                openRawResource.close();
            } else {
                openRawResource.close();
                lottieAnimationView.setVisibility(0);
                frameLayout.setVisibility(0);
                Object drawable = lottieAnimationView.getDrawable();
                if (drawable instanceof Animatable) {
                    if (drawable instanceof Animatable2) {
                        ((Animatable2) drawable).clearAnimationCallbacks();
                    } else if (drawable instanceof Animatable2Compat) {
                        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = (AnimatedVectorDrawableCompat) ((Animatable2Compat) drawable);
                        Drawable drawable2 = animatedVectorDrawableCompat.mDelegateDrawable;
                        if (drawable2 != null) {
                            ((AnimatedVectorDrawable) drawable2).clearAnimationCallbacks();
                        } else {
                            AnimatedVectorDrawableCompat.AnonymousClass2 anonymousClass2 = animatedVectorDrawableCompat.mAnimatorListener;
                            if (anonymousClass2 != null) {
                                animatedVectorDrawableCompat.mAnimatedVectorState.mAnimatorSet.removeListener(anonymousClass2);
                                animatedVectorDrawableCompat.mAnimatorListener = null;
                            }
                            ArrayList arrayList = animatedVectorDrawableCompat.mAnimationCallbacks;
                            if (arrayList != null) {
                                arrayList.clear();
                            }
                        }
                    }
                    ((Animatable) drawable).stop();
                }
                lottieAnimationView.cancelAnimation();
                lottieAnimationView.setImageResource(this.mImageResId);
                Object drawable3 = lottieAnimationView.getDrawable();
                if (drawable3 == null) {
                    final int i3 = this.mImageResId;
                    lottieAnimationView.setFailureListener(new LottieListener() { // from class: com.android.settingslib.widget.IllustrationPreference$$ExternalSyntheticLambda0
                        @Override // com.airbnb.lottie.LottieListener
                        public final void onResult(Object obj) {
                            Log.w("IllustrationPreference", "Invalid illustration resource id: " + i3, (Throwable) obj);
                        }
                    });
                    lottieAnimationView.setAnimation(i3);
                    lottieAnimationView.setRepeatCount(-1);
                    lottieAnimationView.playAnimation();
                } else if (drawable3 instanceof Animatable) {
                    if (drawable3 instanceof Animatable2) {
                        ((Animatable2) drawable3).registerAnimationCallback(this.mAnimationCallback);
                    } else if (drawable3 instanceof Animatable2Compat) {
                        ((AnimatedVectorDrawableCompat) ((Animatable2Compat) drawable3)).registerAnimationCallback(this.mAnimationCallbackCompat);
                    } else if (drawable3 instanceof AnimationDrawable) {
                        ((AnimationDrawable) drawable3).setOneShot(false);
                    }
                    ((Animatable) drawable3).start();
                }
            }
        }
        if (this.mMaxHeight != -1) {
            Resources resources = imageView.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.settingslib_illustration_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.settingslib_illustration_height);
            int min = Math.min(this.mMaxHeight, dimensionPixelSize2);
            imageView.setMaxHeight(min);
            lottieAnimationView.setMaxHeight(min);
            lottieAnimationView.setMaxWidth((int) (min * (dimensionPixelSize / dimensionPixelSize2)));
        }
        frameLayout2.removeAllViews();
        frameLayout2.setVisibility(8);
        if (this.mLottieDynamicColor) {
            LottieColorUtils.applyDynamicColors(this.mContext, lottieAnimationView);
        }
    }
}
