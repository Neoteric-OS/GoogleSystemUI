package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Looper;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.AppCompatTextHelper;
import androidx.collection.LruCache;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCompatTextHelper {
    public boolean mAsyncFontPending;
    public final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    public TintInfo mDrawableBottomTint;
    public TintInfo mDrawableEndTint;
    public TintInfo mDrawableLeftTint;
    public TintInfo mDrawableRightTint;
    public TintInfo mDrawableStartTint;
    public TintInfo mDrawableTopTint;
    public Typeface mFontTypeface;
    public final TextView mView;
    public int mStyle = 0;
    public int mFontWeight = -1;
    public String mFontVariationSettings = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.appcompat.widget.AppCompatTextHelper$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public final /* synthetic */ int val$fontWeight;
        public final /* synthetic */ int val$style;
        public final /* synthetic */ WeakReference val$textViewWeak;

        public AnonymousClass1(int i, int i2, WeakReference weakReference) {
            this.val$fontWeight = i;
            this.val$style = i2;
            this.val$textViewWeak = weakReference;
        }

        public final void callbackFailAsync(final int i) {
            new Handler(Looper.getMainLooper()).post(new Runnable(i) { // from class: androidx.core.content.res.ResourcesCompat$FontCallback$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AppCompatTextHelper.AnonymousClass1.this.getClass();
                }
            });
        }

        public final void onFontRetrieved(final Typeface typeface) {
            int i = this.val$fontWeight;
            if (i != -1) {
                typeface = Typeface.create(typeface, i, (this.val$style & 2) != 0);
            }
            WeakReference weakReference = this.val$textViewWeak;
            AppCompatTextHelper appCompatTextHelper = AppCompatTextHelper.this;
            if (appCompatTextHelper.mAsyncFontPending) {
                appCompatTextHelper.mFontTypeface = typeface;
                final TextView textView = (TextView) weakReference.get();
                if (textView != null) {
                    if (textView.isAttachedToWindow()) {
                        final int i2 = appCompatTextHelper.mStyle;
                        textView.post(new Runnable() { // from class: androidx.appcompat.widget.AppCompatTextHelper.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                TextView textView2 = textView;
                                Typeface typeface2 = typeface;
                                int i3 = i2;
                                LruCache lruCache = Api26Impl.sVariationsCache;
                                String fontVariationSettings = textView2.getFontVariationSettings();
                                if (!TextUtils.isEmpty(fontVariationSettings)) {
                                    Api26Impl.setFontVariationSettings(textView2, null);
                                }
                                textView2.setTypeface(typeface2, i3);
                                if (TextUtils.isEmpty(fontVariationSettings)) {
                                    return;
                                }
                                Api26Impl.setFontVariationSettings(textView2, fontVariationSettings);
                            }
                        });
                        return;
                    }
                    int i3 = appCompatTextHelper.mStyle;
                    LruCache lruCache = Api26Impl.sVariationsCache;
                    String fontVariationSettings = textView.getFontVariationSettings();
                    if (!TextUtils.isEmpty(fontVariationSettings)) {
                        Api26Impl.setFontVariationSettings(textView, null);
                    }
                    textView.setTypeface(typeface, i3);
                    if (TextUtils.isEmpty(fontVariationSettings)) {
                        return;
                    }
                    Api26Impl.setFontVariationSettings(textView, fontVariationSettings);
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Api26Impl {
        public static Paint sPaint;
        public static final LruCache sVariationsCache = new LruCache(30);

        public static void setFontVariationSettings(TextView textView, String str) {
            if (Objects.equals(textView.getFontVariationSettings(), str)) {
                textView.setFontVariationSettings("");
            }
            textView.setFontVariationSettings(str);
        }
    }

    public AppCompatTextHelper(TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView);
    }

    public static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i) {
        ColorStateList tintList;
        synchronized (appCompatDrawableManager) {
            tintList = appCompatDrawableManager.mResourceManager.getTintList(i, context);
        }
        if (tintList == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = tintList;
        return tintInfo;
    }

    public final void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable == null || tintInfo == null) {
            return;
        }
        AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
    }

    public final void applyCompoundDrawablesTints() {
        if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
        if (this.mDrawableStartTint == null && this.mDrawableEndTint == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
        applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
        applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
    }

    public final void applyFontAndVariationSettings(boolean z) {
        Typeface typeface = this.mFontTypeface;
        if (typeface != null) {
            if (this.mFontWeight == -1) {
                this.mView.setTypeface(typeface, this.mStyle);
            } else {
                this.mView.setTypeface(typeface);
            }
        } else if (z) {
            this.mView.setTypeface(null);
        }
        String str = this.mFontVariationSettings;
        if (str != null) {
            Api26Impl.setFontVariationSettings(this.mView, str);
        }
    }

    public final void loadFromAttributes(AttributeSet attributeSet, int i) {
        boolean z;
        boolean z2;
        String str;
        int i2;
        float f;
        ColorStateList colorStateList;
        int resourceId;
        int i3;
        int resourceId2;
        Context context = this.mView.getContext();
        AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
        int[] iArr = R$styleable.AppCompatTextHelper;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i);
        TextView textView = this.mView;
        Context context2 = textView.getContext();
        TypedArray typedArray = obtainStyledAttributes.mWrapped;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api29Impl.saveAttributeDataForStyleable(textView, context2, iArr, attributeSet, typedArray, i, 0);
        int resourceId3 = obtainStyledAttributes.mWrapped.getResourceId(0, -1);
        if (obtainStyledAttributes.mWrapped.hasValue(3)) {
            this.mDrawableLeftTint = createTintInfo(context, appCompatDrawableManager, obtainStyledAttributes.mWrapped.getResourceId(3, 0));
        }
        if (obtainStyledAttributes.mWrapped.hasValue(1)) {
            this.mDrawableTopTint = createTintInfo(context, appCompatDrawableManager, obtainStyledAttributes.mWrapped.getResourceId(1, 0));
        }
        if (obtainStyledAttributes.mWrapped.hasValue(4)) {
            this.mDrawableRightTint = createTintInfo(context, appCompatDrawableManager, obtainStyledAttributes.mWrapped.getResourceId(4, 0));
        }
        if (obtainStyledAttributes.mWrapped.hasValue(2)) {
            this.mDrawableBottomTint = createTintInfo(context, appCompatDrawableManager, obtainStyledAttributes.mWrapped.getResourceId(2, 0));
        }
        if (obtainStyledAttributes.mWrapped.hasValue(5)) {
            this.mDrawableStartTint = createTintInfo(context, appCompatDrawableManager, obtainStyledAttributes.mWrapped.getResourceId(5, 0));
        }
        if (obtainStyledAttributes.mWrapped.hasValue(6)) {
            this.mDrawableEndTint = createTintInfo(context, appCompatDrawableManager, obtainStyledAttributes.mWrapped.getResourceId(6, 0));
        }
        obtainStyledAttributes.recycle();
        boolean z3 = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
        int[] iArr2 = R$styleable.TextAppearance;
        if (resourceId3 != -1) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId3, iArr2);
            TintTypedArray tintTypedArray = new TintTypedArray(context, obtainStyledAttributes2);
            if (z3 || !obtainStyledAttributes2.hasValue(14)) {
                z = false;
                z2 = false;
            } else {
                z = obtainStyledAttributes2.getBoolean(14, false);
                z2 = true;
            }
            updateTypefaceAndStyle(context, tintTypedArray);
            str = obtainStyledAttributes2.hasValue(15) ? obtainStyledAttributes2.getString(15) : null;
            tintTypedArray.recycle();
        } else {
            z = false;
            z2 = false;
            str = null;
        }
        TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, iArr2, i, 0);
        TintTypedArray tintTypedArray2 = new TintTypedArray(context, obtainStyledAttributes3);
        if (!z3 && obtainStyledAttributes3.hasValue(14)) {
            z = obtainStyledAttributes3.getBoolean(14, false);
            z2 = true;
        }
        boolean z4 = z;
        if (obtainStyledAttributes3.hasValue(15)) {
            str = obtainStyledAttributes3.getString(15);
        }
        if (obtainStyledAttributes3.hasValue(0) && obtainStyledAttributes3.getDimensionPixelSize(0, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        updateTypefaceAndStyle(context, tintTypedArray2);
        tintTypedArray2.recycle();
        if (!z3 && z2) {
            this.mView.setAllCaps(z4);
        }
        applyFontAndVariationSettings(false);
        if (str != null) {
            this.mView.setTextLocales(LocaleList.forLanguageTags(str));
        }
        AppCompatTextViewAutoSizeHelper appCompatTextViewAutoSizeHelper = this.mAutoSizeTextHelper;
        Context context3 = appCompatTextViewAutoSizeHelper.mContext;
        int[] iArr3 = R$styleable.AppCompatTextView;
        TypedArray obtainStyledAttributes4 = context3.obtainStyledAttributes(attributeSet, iArr3, i, 0);
        TextView textView2 = appCompatTextViewAutoSizeHelper.mTextView;
        ViewCompat.Api29Impl.saveAttributeDataForStyleable(textView2, textView2.getContext(), iArr3, attributeSet, obtainStyledAttributes4, i, 0);
        if (obtainStyledAttributes4.hasValue(5)) {
            appCompatTextViewAutoSizeHelper.mAutoSizeTextType = obtainStyledAttributes4.getInt(5, 0);
        }
        float dimension = obtainStyledAttributes4.hasValue(4) ? obtainStyledAttributes4.getDimension(4, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes4.hasValue(2) ? obtainStyledAttributes4.getDimension(2, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes4.hasValue(1) ? obtainStyledAttributes4.getDimension(1, -1.0f) : -1.0f;
        if (obtainStyledAttributes4.hasValue(3) && (resourceId2 = obtainStyledAttributes4.getResourceId(3, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes4.getResources().obtainTypedArray(resourceId2);
            int length = obtainTypedArray.length();
            int[] iArr4 = new int[length];
            if (length > 0) {
                for (int i4 = 0; i4 < length; i4++) {
                    iArr4[i4] = obtainTypedArray.getDimensionPixelSize(i4, -1);
                }
                int[] cleanupAutoSizePresetSizes = AppCompatTextViewAutoSizeHelper.cleanupAutoSizePresetSizes(iArr4);
                appCompatTextViewAutoSizeHelper.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes;
                boolean z5 = cleanupAutoSizePresetSizes.length > 0;
                appCompatTextViewAutoSizeHelper.mHasPresetAutoSizeValues = z5;
                if (z5) {
                    appCompatTextViewAutoSizeHelper.mAutoSizeTextType = 1;
                    appCompatTextViewAutoSizeHelper.mAutoSizeMinTextSizeInPx = cleanupAutoSizePresetSizes[0];
                    appCompatTextViewAutoSizeHelper.mAutoSizeMaxTextSizeInPx = cleanupAutoSizePresetSizes[r7 - 1];
                    appCompatTextViewAutoSizeHelper.mAutoSizeStepGranularityInPx = -1.0f;
                }
            }
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes4.recycle();
        if (!appCompatTextViewAutoSizeHelper.supportsAutoSizeText()) {
            appCompatTextViewAutoSizeHelper.mAutoSizeTextType = 0;
        } else if (appCompatTextViewAutoSizeHelper.mAutoSizeTextType == 1) {
            if (!appCompatTextViewAutoSizeHelper.mHasPresetAutoSizeValues) {
                DisplayMetrics displayMetrics = appCompatTextViewAutoSizeHelper.mContext.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    i3 = 2;
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                } else {
                    i3 = 2;
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(i3, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                if (dimension2 <= 0.0f) {
                    throw new IllegalArgumentException("Minimum auto-size text size (" + dimension2 + "px) is less or equal to (0px)");
                }
                if (dimension3 <= dimension2) {
                    throw new IllegalArgumentException("Maximum auto-size text size (" + dimension3 + "px) is less or equal to minimum auto-size text size (" + dimension2 + "px)");
                }
                if (dimension <= 0.0f) {
                    throw new IllegalArgumentException("The auto-size step granularity (" + dimension + "px) is less or equal to (0px)");
                }
                appCompatTextViewAutoSizeHelper.mAutoSizeTextType = 1;
                appCompatTextViewAutoSizeHelper.mAutoSizeMinTextSizeInPx = dimension2;
                appCompatTextViewAutoSizeHelper.mAutoSizeMaxTextSizeInPx = dimension3;
                appCompatTextViewAutoSizeHelper.mAutoSizeStepGranularityInPx = dimension;
                appCompatTextViewAutoSizeHelper.mHasPresetAutoSizeValues = false;
            }
            if (appCompatTextViewAutoSizeHelper.supportsAutoSizeText() && appCompatTextViewAutoSizeHelper.mAutoSizeTextType == 1 && (!appCompatTextViewAutoSizeHelper.mHasPresetAutoSizeValues || appCompatTextViewAutoSizeHelper.mAutoSizeTextSizesInPx.length == 0)) {
                int floor = ((int) Math.floor((appCompatTextViewAutoSizeHelper.mAutoSizeMaxTextSizeInPx - appCompatTextViewAutoSizeHelper.mAutoSizeMinTextSizeInPx) / appCompatTextViewAutoSizeHelper.mAutoSizeStepGranularityInPx)) + 1;
                int[] iArr5 = new int[floor];
                for (int i5 = 0; i5 < floor; i5++) {
                    iArr5[i5] = Math.round((i5 * appCompatTextViewAutoSizeHelper.mAutoSizeStepGranularityInPx) + appCompatTextViewAutoSizeHelper.mAutoSizeMinTextSizeInPx);
                }
                appCompatTextViewAutoSizeHelper.mAutoSizeTextSizesInPx = AppCompatTextViewAutoSizeHelper.cleanupAutoSizePresetSizes(iArr5);
            }
        }
        if (appCompatTextViewAutoSizeHelper.mAutoSizeTextType != 0) {
            int[] iArr6 = appCompatTextViewAutoSizeHelper.mAutoSizeTextSizesInPx;
            if (iArr6.length > 0) {
                TextView textView3 = this.mView;
                LruCache lruCache = Api26Impl.sVariationsCache;
                if (textView3.getAutoSizeStepGranularity() != -1.0f) {
                    this.mView.setAutoSizeTextTypeUniformWithConfiguration(Math.round(appCompatTextViewAutoSizeHelper.mAutoSizeMinTextSizeInPx), Math.round(appCompatTextViewAutoSizeHelper.mAutoSizeMaxTextSizeInPx), Math.round(appCompatTextViewAutoSizeHelper.mAutoSizeStepGranularityInPx), 0);
                } else {
                    this.mView.setAutoSizeTextTypeUniformWithPresetSizes(iArr6, 0);
                }
            }
        }
        TypedArray obtainStyledAttributes5 = context.obtainStyledAttributes(attributeSet, iArr3);
        int resourceId4 = obtainStyledAttributes5.getResourceId(8, -1);
        Drawable drawable = resourceId4 != -1 ? appCompatDrawableManager.getDrawable(resourceId4, context) : null;
        int resourceId5 = obtainStyledAttributes5.getResourceId(13, -1);
        Drawable drawable2 = resourceId5 != -1 ? appCompatDrawableManager.getDrawable(resourceId5, context) : null;
        int resourceId6 = obtainStyledAttributes5.getResourceId(9, -1);
        Drawable drawable3 = resourceId6 != -1 ? appCompatDrawableManager.getDrawable(resourceId6, context) : null;
        int resourceId7 = obtainStyledAttributes5.getResourceId(6, -1);
        Drawable drawable4 = resourceId7 != -1 ? appCompatDrawableManager.getDrawable(resourceId7, context) : null;
        int resourceId8 = obtainStyledAttributes5.getResourceId(10, -1);
        Drawable drawable5 = resourceId8 != -1 ? appCompatDrawableManager.getDrawable(resourceId8, context) : null;
        int resourceId9 = obtainStyledAttributes5.getResourceId(7, -1);
        Drawable drawable6 = resourceId9 != -1 ? appCompatDrawableManager.getDrawable(resourceId9, context) : null;
        if (drawable5 != null || drawable6 != null) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            TextView textView4 = this.mView;
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView4.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
        } else if (drawable != null || drawable2 != null || drawable3 != null || drawable4 != null) {
            Drawable[] compoundDrawablesRelative2 = this.mView.getCompoundDrawablesRelative();
            Drawable drawable7 = compoundDrawablesRelative2[0];
            if (drawable7 == null && compoundDrawablesRelative2[2] == null) {
                Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
                TextView textView5 = this.mView;
                if (drawable == null) {
                    drawable = compoundDrawables[0];
                }
                if (drawable2 == null) {
                    drawable2 = compoundDrawables[1];
                }
                if (drawable3 == null) {
                    drawable3 = compoundDrawables[2];
                }
                if (drawable4 == null) {
                    drawable4 = compoundDrawables[3];
                }
                textView5.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            } else {
                if (drawable2 == null) {
                    drawable2 = compoundDrawablesRelative2[1];
                }
                if (drawable4 == null) {
                    drawable4 = compoundDrawablesRelative2[3];
                }
                this.mView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, compoundDrawablesRelative2[2], drawable4);
            }
        }
        if (obtainStyledAttributes5.hasValue(11)) {
            if (!obtainStyledAttributes5.hasValue(11) || (resourceId = obtainStyledAttributes5.getResourceId(11, 0)) == 0 || (colorStateList = ContextCompat.getColorStateList(resourceId, context)) == null) {
                colorStateList = obtainStyledAttributes5.getColorStateList(11);
            }
            TextView textView6 = this.mView;
            textView6.getClass();
            textView6.setCompoundDrawableTintList(colorStateList);
        }
        if (obtainStyledAttributes5.hasValue(12)) {
            PorterDuff.Mode parseTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes5.getInt(12, -1), null);
            TextView textView7 = this.mView;
            textView7.getClass();
            textView7.setCompoundDrawableTintMode(parseTintMode);
        }
        int dimensionPixelSize = obtainStyledAttributes5.getDimensionPixelSize(15, -1);
        int dimensionPixelSize2 = obtainStyledAttributes5.getDimensionPixelSize(18, -1);
        if (obtainStyledAttributes5.hasValue(19)) {
            TypedValue peekValue = obtainStyledAttributes5.peekValue(19);
            if (peekValue == null || peekValue.type != 5) {
                f = obtainStyledAttributes5.getDimensionPixelSize(19, -1);
                i2 = -1;
            } else {
                int i6 = peekValue.data;
                i2 = i6 & 15;
                f = TypedValue.complexToFloat(i6);
            }
        } else {
            i2 = -1;
            f = -1.0f;
        }
        obtainStyledAttributes5.recycle();
        if (dimensionPixelSize != -1) {
            TextView textView8 = this.mView;
            if (dimensionPixelSize < 0) {
                throw new IllegalArgumentException();
            }
            textView8.setFirstBaselineToTopHeight(dimensionPixelSize);
        }
        if (dimensionPixelSize2 != -1) {
            TextView textView9 = this.mView;
            if (dimensionPixelSize2 < 0) {
                throw new IllegalArgumentException();
            }
            Paint.FontMetricsInt fontMetricsInt = textView9.getPaint().getFontMetricsInt();
            int i7 = textView9.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
            if (dimensionPixelSize2 > Math.abs(i7)) {
                textView9.setPadding(textView9.getPaddingLeft(), textView9.getPaddingTop(), textView9.getPaddingRight(), dimensionPixelSize2 - i7);
            }
        }
        if (f != -1.0f) {
            if (i2 == -1) {
                TextViewCompat.setLineHeight(this.mView, (int) f);
            } else {
                this.mView.setLineHeight(i2, f);
            }
        }
    }

    public final void onSetTextAppearance(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.TextAppearance);
        TintTypedArray tintTypedArray = new TintTypedArray(context, obtainStyledAttributes);
        if (obtainStyledAttributes.hasValue(14)) {
            this.mView.setAllCaps(obtainStyledAttributes.getBoolean(14, false));
        }
        if (obtainStyledAttributes.hasValue(0) && obtainStyledAttributes.getDimensionPixelSize(0, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        boolean updateTypefaceAndStyle = updateTypefaceAndStyle(context, tintTypedArray);
        tintTypedArray.recycle();
        applyFontAndVariationSettings(updateTypefaceAndStyle);
    }

    public final boolean updateTypefaceAndStyle(Context context, TintTypedArray tintTypedArray) {
        String string;
        this.mStyle = tintTypedArray.mWrapped.getInt(2, this.mStyle);
        int i = tintTypedArray.mWrapped.getInt(11, -1);
        this.mFontWeight = i;
        if (i != -1) {
            this.mStyle &= 2;
        }
        if (tintTypedArray.mWrapped.hasValue(13)) {
            this.mFontVariationSettings = tintTypedArray.mWrapped.getString(13);
        }
        if (!tintTypedArray.mWrapped.hasValue(10) && !tintTypedArray.mWrapped.hasValue(12)) {
            if (!tintTypedArray.mWrapped.hasValue(1)) {
                return false;
            }
            this.mAsyncFontPending = false;
            int i2 = tintTypedArray.mWrapped.getInt(1, 1);
            if (i2 == 1) {
                this.mFontTypeface = Typeface.SANS_SERIF;
            } else if (i2 == 2) {
                this.mFontTypeface = Typeface.SERIF;
            } else if (i2 == 3) {
                this.mFontTypeface = Typeface.MONOSPACE;
            }
            return true;
        }
        this.mFontTypeface = null;
        int i3 = tintTypedArray.mWrapped.hasValue(12) ? 12 : 10;
        int i4 = this.mFontWeight;
        int i5 = this.mStyle;
        if (!context.isRestricted()) {
            try {
                Typeface font = tintTypedArray.getFont(i3, this.mStyle, new AnonymousClass1(i4, i5, new WeakReference(this.mView)));
                if (font != null) {
                    if (this.mFontWeight != -1) {
                        this.mFontTypeface = Typeface.create(Typeface.create(font, 0), this.mFontWeight, (this.mStyle & 2) != 0);
                    } else {
                        this.mFontTypeface = font;
                    }
                }
                this.mAsyncFontPending = this.mFontTypeface == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.mFontTypeface == null && (string = tintTypedArray.mWrapped.getString(i3)) != null) {
            if (this.mFontWeight != -1) {
                this.mFontTypeface = Typeface.create(Typeface.create(string, 0), this.mFontWeight, (2 & this.mStyle) != 0);
            } else {
                this.mFontTypeface = Typeface.create(string, this.mStyle);
            }
        }
        return true;
    }
}
