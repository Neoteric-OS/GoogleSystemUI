package com.android.keyguard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.internal.graphics.ColorUtils;
import com.android.settingslib.Utils;
import com.android.systemui.Dumpable;
import com.android.wm.shell.R;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LockIconView extends FrameLayout implements Dumpable {
    public boolean mAod;
    public final ImageView mBgView;
    public float mDozeAmount;
    public int mIconType;
    public final ImageView mLockIcon;
    public final Point mLockIconCenter;
    public int mLockIconColor;
    public boolean mUseBackground;

    public LockIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLockIconCenter = new Point(0, 0);
        this.mUseBackground = false;
        this.mDozeAmount = 0.0f;
        new RectF();
        ImageView imageView = new ImageView(context, attributeSet);
        this.mBgView = imageView;
        imageView.setId(R.id.lock_icon_bg);
        this.mBgView.setImageDrawable(context.getDrawable(R.drawable.fingerprint_bg));
        this.mBgView.setVisibility(4);
        addView(this.mBgView);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mBgView.getLayoutParams();
        layoutParams.height = -1;
        layoutParams.width = -1;
        this.mBgView.setLayoutParams(layoutParams);
        ImageView imageView2 = new ImageView(context, attributeSet);
        this.mLockIcon = imageView2;
        imageView2.setId(R.id.lock_icon);
        this.mLockIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.mLockIcon.setImageDrawable(context.getDrawable(R.drawable.super_lock_icon));
        addView(this.mLockIcon);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mLockIcon.getLayoutParams();
        layoutParams2.height = -1;
        layoutParams2.width = -1;
        layoutParams2.gravity = 17;
        this.mLockIcon.setLayoutParams(layoutParams2);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "Lock Icon View Parameters:", "    Center in px (x, y)= (");
        m.append(this.mLockIconCenter.x);
        m.append(", ");
        m.append(this.mLockIconCenter.y);
        m.append(")");
        printWriter.println(m.toString());
        printWriter.println("    Radius in pixels: 0.0");
        printWriter.println("    Drawable padding: 0");
        int i = this.mIconType;
        printWriter.println("    mIconType=".concat(i != -1 ? i != 0 ? i != 1 ? i != 2 ? "invalid" : "unlock" : "fingerprint" : "lock" : "none"));
        printWriter.println("    mAod=" + this.mAod);
        printWriter.println("Lock Icon View actual measurements:");
        printWriter.println("    topLeft= (" + getX() + ", " + getY() + ")");
        StringBuilder sb = new StringBuilder("    width=");
        sb.append(getWidth());
        sb.append(" height=");
        sb.append(getHeight());
        printWriter.println(sb.toString());
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final void updateColorAndBackgroundVisibility() {
        if (!this.mUseBackground || this.mLockIcon.getDrawable() == null) {
            this.mLockIconColor = ColorUtils.blendARGB(Utils.getColorAttrDefaultColor(R.attr.wallpaperTextColorAccent, 0, getContext()), -1, this.mDozeAmount);
            this.mBgView.setVisibility(8);
        } else {
            this.mLockIconColor = ColorUtils.blendARGB(Utils.getColorAttrDefaultColor(android.R.attr.textColorPrimary, 0, getContext()), -1, this.mDozeAmount);
            this.mBgView.setImageTintList(ColorStateList.valueOf(Utils.getColorAttrDefaultColor(android.R.^attr-private.colorSurface, 0, getContext())));
            this.mBgView.setAlpha(1.0f - this.mDozeAmount);
            this.mBgView.setVisibility(0);
        }
        this.mLockIcon.setImageTintList(ColorStateList.valueOf(this.mLockIconColor));
    }

    public final void updateIcon(int i, boolean z) {
        int[] iArr;
        this.mIconType = i;
        this.mAod = z;
        ImageView imageView = this.mLockIcon;
        if (i == -1) {
            iArr = new int[0];
        } else {
            int[] iArr2 = new int[2];
            if (i == 0) {
                iArr2[0] = 16842916;
            } else if (i == 1) {
                iArr2[0] = 16842917;
            } else if (i == 2) {
                iArr2[0] = 16842918;
            }
            if (z) {
                iArr2[1] = 16842915;
            } else {
                iArr2[1] = -16842915;
            }
            iArr = iArr2;
        }
        imageView.setImageState(iArr, true);
    }
}
