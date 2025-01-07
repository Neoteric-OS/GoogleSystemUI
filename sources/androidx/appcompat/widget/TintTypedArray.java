package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextHelper;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.ResourcesCompat$FontCallback$$ExternalSyntheticLambda0;
import androidx.core.graphics.TypefaceCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TintTypedArray {
    public final Context mContext;
    public TypedValue mTypedValue;
    public final TypedArray mWrapped;

    public TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i, 0));
    }

    public final ColorStateList getColorStateList(int i) {
        int resourceId;
        ColorStateList colorStateList;
        return (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0 || (colorStateList = ContextCompat.getColorStateList(resourceId, this.mContext)) == null) ? this.mWrapped.getColorStateList(i) : colorStateList;
    }

    public final Drawable getDrawable(int i) {
        int resourceId;
        return (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0) ? this.mWrapped.getDrawable(i) : AppCompatResources.getDrawable(resourceId, this.mContext);
    }

    public final Drawable getDrawableIfKnown(int i) {
        int resourceId;
        Drawable drawable;
        if (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0) {
            return null;
        }
        AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
        Context context = this.mContext;
        synchronized (appCompatDrawableManager) {
            drawable = appCompatDrawableManager.mResourceManager.getDrawable(context, resourceId, true);
        }
        return drawable;
    }

    public final Typeface getFont(int i, int i2, AppCompatTextHelper.AnonymousClass1 anonymousClass1) {
        int resourceId = this.mWrapped.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        Context context = this.mContext;
        TypedValue typedValue = this.mTypedValue;
        ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
        if (context.isRestricted()) {
            return null;
        }
        Resources resources = context.getResources();
        resources.getValue(resourceId, typedValue, true);
        CharSequence charSequence = typedValue.string;
        if (charSequence == null) {
            throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(resourceId) + "\" (" + Integer.toHexString(resourceId) + ") is not a Font: " + typedValue);
        }
        String charSequence2 = charSequence.toString();
        if (!charSequence2.startsWith("res/")) {
            anonymousClass1.callbackFailAsync(-3);
            return null;
        }
        Typeface typeface = (Typeface) TypefaceCompat.sTypefaceCache.get(TypefaceCompat.createResourceUid(resources, resourceId, charSequence2, typedValue.assetCookie, i2));
        if (typeface != null) {
            new Handler(Looper.getMainLooper()).post(new ResourcesCompat$FontCallback$$ExternalSyntheticLambda0(anonymousClass1, typeface));
            return typeface;
        }
        try {
            if (!charSequence2.toLowerCase().endsWith(".xml")) {
                Typeface createFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(resources, resourceId, charSequence2, typedValue.assetCookie, i2);
                if (createFromResourcesFontFile != null) {
                    new Handler(Looper.getMainLooper()).post(new ResourcesCompat$FontCallback$$ExternalSyntheticLambda0(anonymousClass1, createFromResourcesFontFile));
                } else {
                    anonymousClass1.callbackFailAsync(-3);
                }
                return createFromResourcesFontFile;
            }
            FontResourcesParserCompat.FamilyResourceEntry parse = FontResourcesParserCompat.parse(resources.getXml(resourceId), resources);
            if (parse != null) {
                return TypefaceCompat.createFromResourcesFamilyXml(context, parse, resources, resourceId, charSequence2, typedValue.assetCookie, i2, anonymousClass1, true);
            }
            Log.e("ResourcesCompat", "Failed to find font-family tag");
            anonymousClass1.callbackFailAsync(-3);
            return null;
        } catch (IOException e) {
            Log.e("ResourcesCompat", "Failed to read xml resource ".concat(charSequence2), e);
            anonymousClass1.callbackFailAsync(-3);
            return null;
        } catch (XmlPullParserException e2) {
            Log.e("ResourcesCompat", "Failed to parse xml resource ".concat(charSequence2), e2);
            anonymousClass1.callbackFailAsync(-3);
            return null;
        }
    }

    public final void recycle() {
        this.mWrapped.recycle();
    }
}
