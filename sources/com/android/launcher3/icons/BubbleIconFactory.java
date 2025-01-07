package com.android.launcher3.icons;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BubbleIconFactory extends BaseIconFactory {
    public final BaseIconFactory mBadgeFactory;
    public final int mRingColor;
    public final int mRingWidth;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class CircularAdaptiveIcon extends AdaptiveIconDrawable {
        public final Path mPath;

        public CircularAdaptiveIcon(Drawable drawable, Drawable drawable2) {
            super(drawable, drawable2);
            this.mPath = new Path();
        }

        @Override // android.graphics.drawable.AdaptiveIconDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            int save = canvas.save();
            canvas.clipPath(getIconMask());
            Drawable background = getBackground();
            if (background != null) {
                background.draw(canvas);
            }
            Drawable foreground = getForeground();
            if (foreground != null) {
                foreground.draw(canvas);
            }
            canvas.restoreToCount(save);
        }

        @Override // android.graphics.drawable.AdaptiveIconDrawable
        public final Path getIconMask() {
            this.mPath.reset();
            Rect bounds = getBounds();
            this.mPath.addOval(bounds.left, bounds.top, bounds.right, bounds.bottom, Path.Direction.CW);
            return this.mPath;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CircularRingDrawable extends CircularAdaptiveIcon {
        public final Drawable mDr;
        public final Rect mInnerBounds;

        public CircularRingDrawable(Drawable drawable) {
            super(null, null);
            this.mInnerBounds = new Rect();
            this.mDr = drawable;
        }

        @Override // com.android.launcher3.icons.BubbleIconFactory.CircularAdaptiveIcon, android.graphics.drawable.AdaptiveIconDrawable, android.graphics.drawable.Drawable
        public final void draw(Canvas canvas) {
            int save = canvas.save();
            canvas.clipPath(getIconMask());
            canvas.drawColor(BubbleIconFactory.this.mRingColor);
            this.mInnerBounds.set(getBounds());
            Rect rect = this.mInnerBounds;
            int i = BubbleIconFactory.this.mRingWidth;
            rect.inset(i, i);
            Rect rect2 = this.mInnerBounds;
            canvas.translate(rect2.left, rect2.top);
            this.mDr.setBounds(0, 0, this.mInnerBounds.width(), this.mInnerBounds.height());
            this.mDr.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    public BubbleIconFactory(Context context, int i, int i2, int i3, int i4) {
        super(context, context.getResources().getConfiguration().densityDpi, i, false);
        this.mRingColor = i3;
        this.mRingWidth = i4;
        this.mBadgeFactory = new BaseIconFactory(context, context.getResources().getConfiguration().densityDpi, i2, false);
    }

    public static Drawable getBubbleDrawable(Context context, ShortcutInfo shortcutInfo, Icon icon) {
        if (shortcutInfo != null) {
            return ((LauncherApps) context.getSystemService(LauncherApps.class)).getShortcutIconDrawable(shortcutInfo, context.getResources().getConfiguration().densityDpi);
        }
        if (icon == null) {
            return null;
        }
        if (icon.getType() == 4 || icon.getType() == 6) {
            context.grantUriPermission(context.getPackageName(), icon.getUri(), 1);
        }
        return icon.loadDrawable(context);
    }

    public final BitmapInfo getBadgeBitmap(Drawable drawable, boolean z) {
        if (drawable instanceof AdaptiveIconDrawable) {
            AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) drawable;
            drawable = new CircularAdaptiveIcon(adaptiveIconDrawable.getBackground(), adaptiveIconDrawable.getForeground());
        }
        if (z) {
            drawable = new CircularRingDrawable(drawable);
        }
        Bitmap createIconBitmap = this.mBadgeFactory.createIconBitmap(drawable, 1.0f, 2);
        BaseIconFactory baseIconFactory = this.mBadgeFactory;
        if (baseIconFactory.mIconBitmapSize != createIconBitmap.getWidth() || baseIconFactory.mIconBitmapSize != createIconBitmap.getHeight()) {
            createIconBitmap = baseIconFactory.createIconBitmap(new BitmapDrawable(baseIconFactory.mContext.getResources(), createIconBitmap), 1.0f, 0);
        }
        return new BitmapInfo(createIconBitmap, baseIconFactory.mColorExtractor.findDominantColorByHue(createIconBitmap));
    }
}
