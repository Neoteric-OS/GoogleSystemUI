package com.android.launcher3.icons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class BitmapInfo {
    public static final Bitmap LOW_RES_ICON = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
    public BitmapInfo badgeInfo;
    public final int color;
    public int flags;
    public final Bitmap icon;
    public Bitmap mWhiteShadowLayer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Extender {
    }

    public BitmapInfo(Bitmap bitmap, int i) {
        this.icon = bitmap;
        this.color = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void applyFlags(Context context, FastBitmapDrawable fastBitmapDrawable, int i) {
        Drawable userBadgeDrawable;
        GraphicsUtils$$ExternalSyntheticLambda0 graphicsUtils$$ExternalSyntheticLambda0 = GraphicsUtils.sOnNewBitmapRunnable;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.disabledIconAlpha});
        obtainStyledAttributes.getFloat(0, 1.0f);
        obtainStyledAttributes.recycle();
        fastBitmapDrawable.getClass();
        fastBitmapDrawable.mCreationFlags = i;
        if ((i & 2) == 0) {
            boolean z = (i & 1) != 0;
            boolean z2 = (i & 4) != 0;
            BitmapInfo bitmapInfo = this.badgeInfo;
            int i2 = z;
            if (bitmapInfo != null) {
                if (z2) {
                    i2 = (z ? 1 : 0) | 4;
                }
                userBadgeDrawable = bitmapInfo.newIcon(i2, context);
            } else {
                if (!z2) {
                    int i3 = this.flags;
                    if ((i3 & 2) != 0) {
                        userBadgeDrawable = new UserBadgeDrawable(context, R.drawable.ic_instant_app_badge, R.color.badge_tint_instant, z);
                    } else if ((i3 & 1) != 0) {
                        userBadgeDrawable = new UserBadgeDrawable(context, R.drawable.ic_work_app_badge, R.color.badge_tint_work, z);
                    } else if ((i3 & 4) != 0) {
                        userBadgeDrawable = new UserBadgeDrawable(context, R.drawable.ic_clone_app_badge, R.color.badge_tint_clone, z);
                    } else if ((i3 & 8) != 0) {
                        userBadgeDrawable = new UserBadgeDrawable(context, R.drawable.ic_private_profile_app_badge, R.color.badge_tint_private, z);
                    }
                }
                userBadgeDrawable = null;
            }
            if (userBadgeDrawable != null) {
                Drawable drawable = fastBitmapDrawable.mBadge;
                if (drawable != null) {
                    drawable.setCallback(null);
                }
                fastBitmapDrawable.mBadge = userBadgeDrawable;
                userBadgeDrawable.setCallback(fastBitmapDrawable);
                Rect bounds = fastBitmapDrawable.getBounds();
                Drawable drawable2 = fastBitmapDrawable.mBadge;
                if (drawable2 != null) {
                    int width = bounds.width();
                    float f = BaseIconFactory.LEGACY_ICON_SCALE;
                    int i4 = (int) (width * 0.444f);
                    int i5 = bounds.right;
                    int i6 = bounds.bottom;
                    drawable2.setBounds(i5 - i4, i6 - i4, i5, i6);
                }
                fastBitmapDrawable.updateFilter();
            }
        }
    }

    public FastBitmapDrawable newIcon(int i, Context context) {
        FastBitmapDrawable placeHolderIconDrawable = LOW_RES_ICON == this.icon ? new PlaceHolderIconDrawable(this, context) : new FastBitmapDrawable(this.icon, this.color);
        applyFlags(context, placeHolderIconDrawable, i);
        return placeHolderIconDrawable;
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BitmapInfo mo756clone() {
        BitmapInfo bitmapInfo = new BitmapInfo(this.icon, this.color);
        bitmapInfo.mWhiteShadowLayer = this.mWhiteShadowLayer;
        bitmapInfo.flags = this.flags;
        bitmapInfo.badgeInfo = this.badgeInfo;
        return bitmapInfo;
    }
}
