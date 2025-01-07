package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.google.android.material.R$styleable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ThemeEnforcement {
    public static final int[] APPCOMPAT_CHECK_ATTRS = {R.attr.colorPrimary};
    public static final int[] MATERIAL_CHECK_ATTRS = {R.attr.colorPrimaryVariant};

    public static void checkCompatibleTheme(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ThemeEnforcement, i, i2);
        boolean z = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        if (z) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(R.attr.isMaterialTheme, typedValue, true) || (typedValue.type == 18 && typedValue.data == 0)) {
                checkTheme(context, MATERIAL_CHECK_ATTRS, "Theme.MaterialComponents");
            }
        }
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, "Theme.AppCompat");
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
    
        if (r1.getResourceId(0, -1) != (-1)) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void checkTextAppearance(android.content.Context r5, android.util.AttributeSet r6, int[] r7, int r8, int r9, int... r10) {
        /*
            r0 = 1
            int[] r1 = com.google.android.material.R$styleable.ThemeEnforcement
            android.content.res.TypedArray r1 = r5.obtainStyledAttributes(r6, r1, r8, r9)
            r2 = 2
            r3 = 0
            boolean r2 = r1.getBoolean(r2, r3)
            if (r2 != 0) goto L13
            r1.recycle()
            return
        L13:
            int r2 = r10.length
            r4 = -1
            if (r2 != 0) goto L20
            int r5 = r1.getResourceId(r3, r4)
            if (r5 == r4) goto L1e
            goto L39
        L1e:
            r0 = r3
            goto L39
        L20:
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7, r8, r9)
            int r6 = r10.length
            r7 = r3
        L26:
            if (r7 >= r6) goto L36
            r8 = r10[r7]
            int r8 = r5.getResourceId(r8, r4)
            if (r8 != r4) goto L34
            r5.recycle()
            goto L1e
        L34:
            int r7 = r7 + r0
            goto L26
        L36:
            r5.recycle()
        L39:
            r1.recycle()
            if (r0 == 0) goto L3f
            return
        L3f:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant)."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.internal.ThemeEnforcement.checkTextAppearance(android.content.Context, android.util.AttributeSet, int[], int, int, int[]):void");
    }

    public static void checkTheme(Context context, int[] iArr, String str) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        for (int i = 0; i < iArr.length; i++) {
            if (!obtainStyledAttributes.hasValue(i)) {
                obtainStyledAttributes.recycle();
                throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("The style on this component requires your app theme to be ", str, " (or a descendant)."));
            }
        }
        obtainStyledAttributes.recycle();
    }
}
