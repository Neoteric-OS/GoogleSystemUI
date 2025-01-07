package com.android.systemui.people;

import android.R;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import androidx.core.graphics.drawable.RoundedBitmapDrawable21;
import com.android.settingslib.Utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PeopleStoryIconFactory implements AutoCloseable {
    public final int mAccentColor;
    public final Context mContext;
    public final float mDensity;
    public final int mIconBitmapSize;
    public final float mIconSize;
    public final int mImportantConversationColor;
    public final PackageManager mPackageManager;

    public PeopleStoryIconFactory(Context context, PackageManager packageManager, int i) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, R.style.Theme.DeviceDefault.DayNight);
        this.mContext = contextThemeWrapper;
        float f = i;
        this.mIconBitmapSize = (int) (contextThemeWrapper.getResources().getDisplayMetrics().density * f);
        float f2 = contextThemeWrapper.getResources().getDisplayMetrics().density;
        this.mDensity = f2;
        this.mIconSize = f2 * f;
        this.mPackageManager = packageManager;
        this.mImportantConversationColor = contextThemeWrapper.getColor(com.android.wm.shell.R.color.important_conversation);
        this.mAccentColor = Utils.getColorAttr(R.^attr-private.colorAccentPrimaryVariant, contextThemeWrapper).getDefaultColor();
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PeopleStoryIconDrawable extends Drawable {
        public RoundedBitmapDrawable21 mAvatar;
        public Drawable mBadgeIcon;
        public float mDensity;
        public float mFullIconSize;
        public int mIconSize;
        public Paint mPriorityRingPaint;
        public boolean mShowImportantRing;
        public boolean mShowStoryRing;
        public Paint mStoryPaint;

        @Override // android.graphics.drawable.Drawable
        public final void draw(Canvas canvas) {
            int i;
            Rect bounds = getBounds();
            float min = Math.min(bounds.height(), bounds.width()) / this.mFullIconSize;
            int i2 = (int) (this.mDensity * 2.0f);
            float f = i2;
            this.mPriorityRingPaint.setStrokeWidth(f);
            this.mStoryPaint.setStrokeWidth(f);
            int i3 = (int) (this.mFullIconSize * min);
            int i4 = i3 - (i2 * 2);
            if (this.mAvatar != null) {
                int i5 = i4 + i2;
                if (this.mShowStoryRing) {
                    float f2 = i3 / 2;
                    canvas.drawCircle(f2, f2, (i4 - i2) / 2, this.mStoryPaint);
                    int i6 = i2 + i2;
                    i = i2 + i6;
                    i5 -= i6;
                } else {
                    i = i2;
                }
                this.mAvatar.setBounds(i, i, i5, i5);
                this.mAvatar.draw(canvas);
            } else {
                Log.w("PeopleStoryIconFactory", "Null avatar icon");
            }
            int min2 = Math.min((int) (this.mDensity * 40.0f), (int) (i4 / 2.4d));
            if (this.mBadgeIcon == null) {
                Log.w("PeopleStoryIconFactory", "Null badge icon");
                return;
            }
            int i7 = i3 - min2;
            if (this.mShowImportantRing) {
                float f3 = (min2 / 2) + i7;
                canvas.drawCircle(f3, f3, (min2 - i2) / 2, this.mPriorityRingPaint);
                i7 += i2;
                i3 -= i2;
            }
            this.mBadgeIcon.setBounds(i7, i7, i3, i3);
            this.mBadgeIcon.draw(canvas);
        }

        @Override // android.graphics.drawable.Drawable
        public final int getIntrinsicHeight() {
            return this.mIconSize;
        }

        @Override // android.graphics.drawable.Drawable
        public final int getIntrinsicWidth() {
            return this.mIconSize;
        }

        @Override // android.graphics.drawable.Drawable
        public final int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public final void setColorFilter(ColorFilter colorFilter) {
            RoundedBitmapDrawable21 roundedBitmapDrawable21 = this.mAvatar;
            if (roundedBitmapDrawable21 != null) {
                roundedBitmapDrawable21.setColorFilter(colorFilter);
            }
            Drawable drawable = this.mBadgeIcon;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }

        @Override // android.graphics.drawable.Drawable
        public final void setAlpha(int i) {
        }
    }
}
