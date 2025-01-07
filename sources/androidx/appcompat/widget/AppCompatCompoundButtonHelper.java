package androidx.appcompat.widget;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCompatCompoundButtonHelper {
    public PorterDuff.Mode mButtonTintMode = null;
    public boolean mHasButtonTint = false;
    public boolean mHasButtonTintMode = false;
    public boolean mSkipNextApply;
    public final CompoundButton mView;

    public AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    public final void applyButtonTint() {
        Drawable buttonDrawable = this.mView.getButtonDrawable();
        if (buttonDrawable != null) {
            if (this.mHasButtonTint || this.mHasButtonTintMode) {
                Drawable mutate = buttonDrawable.mutate();
                if (this.mHasButtonTint) {
                    mutate.setTintList(null);
                }
                if (this.mHasButtonTintMode) {
                    mutate.setTintMode(this.mButtonTintMode);
                }
                if (mutate.isStateful()) {
                    mutate.setState(this.mView.getDrawableState());
                }
                this.mView.setButtonDrawable(mutate);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0064 A[Catch: all -> 0x003c, TryCatch #1 {all -> 0x003c, blocks: (B:3:0x001d, B:5:0x0026, B:8:0x002e, B:9:0x005b, B:11:0x0064, B:12:0x006d, B:14:0x0076, B:21:0x003e, B:23:0x0046, B:25:0x004e), top: B:2:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0076 A[Catch: all -> 0x003c, TRY_LEAVE, TryCatch #1 {all -> 0x003c, blocks: (B:3:0x001d, B:5:0x0026, B:8:0x002e, B:9:0x005b, B:11:0x0064, B:12:0x006d, B:14:0x0076, B:21:0x003e, B:23:0x0046, B:25:0x004e), top: B:2:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void loadFromAttributes(android.util.AttributeSet r10, int r11) {
        /*
            r9 = this;
            r0 = 0
            android.widget.CompoundButton r1 = r9.mView
            android.content.Context r1 = r1.getContext()
            int[] r4 = androidx.appcompat.R$styleable.CompoundButton
            androidx.appcompat.widget.TintTypedArray r1 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r1, r10, r4, r11)
            android.widget.CompoundButton r2 = r9.mView
            android.content.Context r3 = r2.getContext()
            android.content.res.TypedArray r6 = r1.mWrapped
            java.util.WeakHashMap r5 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            r8 = 0
            r5 = r10
            r7 = r11
            androidx.core.view.ViewCompat.Api29Impl.saveAttributeDataForStyleable(r2, r3, r4, r5, r6, r7, r8)
            android.content.res.TypedArray r10 = r1.mWrapped     // Catch: java.lang.Throwable -> L3c
            r11 = 1
            boolean r10 = r10.hasValue(r11)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L3e
            android.content.res.TypedArray r10 = r1.mWrapped     // Catch: java.lang.Throwable -> L3c
            int r10 = r10.getResourceId(r11, r0)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L3e
            android.widget.CompoundButton r11 = r9.mView     // Catch: java.lang.Throwable -> L3c android.content.res.Resources.NotFoundException -> L3e
            android.content.Context r2 = r11.getContext()     // Catch: java.lang.Throwable -> L3c android.content.res.Resources.NotFoundException -> L3e
            android.graphics.drawable.Drawable r10 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r10, r2)     // Catch: java.lang.Throwable -> L3c android.content.res.Resources.NotFoundException -> L3e
            r11.setButtonDrawable(r10)     // Catch: java.lang.Throwable -> L3c android.content.res.Resources.NotFoundException -> L3e
            goto L5b
        L3c:
            r9 = move-exception
            goto L8b
        L3e:
            android.content.res.TypedArray r10 = r1.mWrapped     // Catch: java.lang.Throwable -> L3c
            boolean r10 = r10.hasValue(r0)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L5b
            android.content.res.TypedArray r10 = r1.mWrapped     // Catch: java.lang.Throwable -> L3c
            int r10 = r10.getResourceId(r0, r0)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L5b
            android.widget.CompoundButton r11 = r9.mView     // Catch: java.lang.Throwable -> L3c
            android.content.Context r0 = r11.getContext()     // Catch: java.lang.Throwable -> L3c
            android.graphics.drawable.Drawable r10 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r10, r0)     // Catch: java.lang.Throwable -> L3c
            r11.setButtonDrawable(r10)     // Catch: java.lang.Throwable -> L3c
        L5b:
            android.content.res.TypedArray r10 = r1.mWrapped     // Catch: java.lang.Throwable -> L3c
            r11 = 2
            boolean r10 = r10.hasValue(r11)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L6d
            android.widget.CompoundButton r10 = r9.mView     // Catch: java.lang.Throwable -> L3c
            android.content.res.ColorStateList r11 = r1.getColorStateList(r11)     // Catch: java.lang.Throwable -> L3c
            r10.setButtonTintList(r11)     // Catch: java.lang.Throwable -> L3c
        L6d:
            android.content.res.TypedArray r10 = r1.mWrapped     // Catch: java.lang.Throwable -> L3c
            r11 = 3
            boolean r10 = r10.hasValue(r11)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L87
            android.widget.CompoundButton r9 = r9.mView     // Catch: java.lang.Throwable -> L3c
            android.content.res.TypedArray r10 = r1.mWrapped     // Catch: java.lang.Throwable -> L3c
            r0 = -1
            int r10 = r10.getInt(r11, r0)     // Catch: java.lang.Throwable -> L3c
            r11 = 0
            android.graphics.PorterDuff$Mode r10 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r10, r11)     // Catch: java.lang.Throwable -> L3c
            r9.setButtonTintMode(r10)     // Catch: java.lang.Throwable -> L3c
        L87:
            r1.recycle()
            return
        L8b:
            r1.recycle()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCompoundButtonHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }
}
