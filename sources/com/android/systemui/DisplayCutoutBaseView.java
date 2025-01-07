package com.android.systemui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.IndentingPrintWriter;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.DisplayInfo;
import android.view.View;
import com.android.app.animation.Interpolators;
import com.android.systemui.RegionInterceptingFrameLayout;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DisplayCutoutBaseView extends View implements RegionInterceptingFrameLayout.RegionInterceptableView {
    public ValueAnimator cameraProtectionAnimator;
    public float cameraProtectionProgress;
    public final Path cutoutPath;
    public final DisplayInfo displayInfo;
    public Display.Mode displayMode;
    public int displayRotation;
    public String displayUniqueId;
    public final int[] location;
    public final Paint paint;
    public boolean pendingConfigChange;
    public final Path protectionPath;
    public final Path protectionPathOrig;
    public final RectF protectionRect;
    public final RectF protectionRectOrig;
    public boolean shouldDrawCutout;
    public boolean showProtection;

    public DisplayCutoutBaseView(Context context) {
        super(context);
        Resources resources = getContext().getResources();
        Display display = getContext().getDisplay();
        this.shouldDrawCutout = DisplayCutout.getFillBuiltInDisplayCutout(resources, display != null ? display.getUniqueId() : null);
        this.location = new int[2];
        this.displayInfo = new DisplayInfo();
        this.paint = new Paint();
        this.cutoutPath = new Path();
        this.protectionRect = new RectF();
        this.protectionPath = new Path();
        this.protectionRectOrig = new RectF();
        this.protectionPathOrig = new Path();
        this.cameraProtectionProgress = 0.5f;
    }

    public void drawCutoutProtection(Canvas canvas) {
        if (this.cameraProtectionProgress <= 0.5f || this.protectionRect.isEmpty()) {
            return;
        }
        float f = this.cameraProtectionProgress;
        canvas.scale(f, f, this.protectionRect.centerX(), this.protectionRect.centerY());
        canvas.drawPath(this.protectionPath, this.paint);
    }

    public void drawCutouts(Canvas canvas) {
        DisplayCutout displayCutout = this.displayInfo.displayCutout;
        if (displayCutout == null || displayCutout.getCutoutPath() == null) {
            return;
        }
        canvas.drawPath(this.cutoutPath, this.paint);
    }

    public void dump(PrintWriter printWriter) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.increaseIndent();
        asIndenting.println("DisplayCutoutBaseView:");
        asIndenting.increaseIndent();
        asIndenting.println("shouldDrawCutout=" + this.shouldDrawCutout);
        asIndenting.println("cutout=" + this.displayInfo.displayCutout);
        asIndenting.println("cameraProtectionProgress=" + this.cameraProtectionProgress);
        asIndenting.println("protectionRect=" + this.protectionRect);
        asIndenting.println("protectionRectOrig=" + this.protectionRectOrig);
        asIndenting.decreaseIndent();
        asIndenting.decreaseIndent();
    }

    public void enableShowProtection(boolean z) {
        if (this.showProtection == z) {
            return;
        }
        this.showProtection = z;
        updateProtectionBoundingPath();
        if (this.showProtection) {
            requestLayout();
        }
        ValueAnimator valueAnimator = this.cameraProtectionAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator duration = ValueAnimator.ofFloat(this.cameraProtectionProgress, this.showProtection ? 1.0f : 0.5f).setDuration(750L);
        this.cameraProtectionAnimator = duration;
        if (duration != null) {
            duration.setInterpolator(Interpolators.DECELERATE_QUINT);
        }
        ValueAnimator valueAnimator2 = this.cameraProtectionAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.DisplayCutoutBaseView$enableShowProtection$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    DisplayCutoutBaseView.this.cameraProtectionProgress = ((Float) valueAnimator3.getAnimatedValue()).floatValue();
                    DisplayCutoutBaseView.this.invalidate();
                }
            });
        }
        ValueAnimator valueAnimator3 = this.cameraProtectionAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.DisplayCutoutBaseView$enableShowProtection$2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    DisplayCutoutBaseView displayCutoutBaseView = DisplayCutoutBaseView.this;
                    displayCutoutBaseView.cameraProtectionAnimator = null;
                    if (displayCutoutBaseView.showProtection) {
                        return;
                    }
                    displayCutoutBaseView.requestLayout();
                }
            });
        }
        ValueAnimator valueAnimator4 = this.cameraProtectionAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.start();
        }
    }

    public float getPhysicalPixelDisplaySizeRatio() {
        DisplayCutout displayCutout = this.displayInfo.displayCutout;
        if (displayCutout != null) {
            return displayCutout.getCutoutPathParserInfo().getPhysicalPixelDisplaySizeRatio();
        }
        return 1.0f;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Display display = getContext().getDisplay();
        this.displayUniqueId = display != null ? display.getUniqueId() : null;
        updateCutout();
        updateProtectionBoundingPath();
        onUpdate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.shouldDrawCutout) {
            canvas.save();
            getLocationOnScreen(this.location);
            int[] iArr = this.location;
            canvas.translate(-iArr[0], -iArr[1]);
            drawCutouts(canvas);
            drawCutoutProtection(canvas);
            canvas.restore();
        }
    }

    public final void setProtection(Path path, Rect rect) {
        this.protectionPathOrig.reset();
        this.protectionPathOrig.set(path);
        this.protectionPath.reset();
        this.protectionRectOrig.setEmpty();
        this.protectionRectOrig.set(rect);
        this.protectionRect.setEmpty();
    }

    public final void updateConfiguration(String str) {
        DisplayInfo displayInfo = new DisplayInfo();
        Display display = getContext().getDisplay();
        if (display != null) {
            display.getDisplayInfo(displayInfo);
        }
        Display.Mode mode = this.displayMode;
        this.displayMode = displayInfo.getMode();
        String str2 = displayInfo.uniqueId;
        if (!Intrinsics.areEqual(this.displayUniqueId, str2)) {
            this.displayUniqueId = str2;
            this.shouldDrawCutout = DisplayCutout.getFillBuiltInDisplayCutout(getContext().getResources(), this.displayUniqueId);
            invalidate();
        }
        Display.Mode mode2 = this.displayMode;
        if (mode != null) {
            if (Integer.valueOf(mode.getPhysicalHeight()).equals(mode2 != null ? Integer.valueOf(mode2.getPhysicalHeight()) : null)) {
                if (Integer.valueOf(mode.getPhysicalWidth()).equals(mode2 != null ? Integer.valueOf(mode2.getPhysicalWidth()) : null) && Intrinsics.areEqual(this.displayInfo.displayCutout, displayInfo.displayCutout) && this.displayRotation == displayInfo.rotation) {
                    return;
                }
            }
        }
        if (Intrinsics.areEqual(str, displayInfo.uniqueId)) {
            this.displayRotation = displayInfo.rotation;
            updateCutout();
            updateProtectionBoundingPath();
            onUpdate();
        }
    }

    public void updateCutout() {
        Path cutoutPath;
        if (this.pendingConfigChange) {
            return;
        }
        this.cutoutPath.reset();
        Display display = getContext().getDisplay();
        if (display != null) {
            display.getDisplayInfo(this.displayInfo);
        }
        DisplayCutout displayCutout = this.displayInfo.displayCutout;
        if (displayCutout != null && (cutoutPath = displayCutout.getCutoutPath()) != null) {
            this.cutoutPath.set(cutoutPath);
        }
        invalidate();
    }

    public void updateProtectionBoundingPath() {
        if (this.pendingConfigChange) {
            return;
        }
        Matrix matrix = new Matrix();
        float physicalPixelDisplaySizeRatio = getPhysicalPixelDisplaySizeRatio();
        matrix.postScale(physicalPixelDisplaySizeRatio, physicalPixelDisplaySizeRatio);
        DisplayInfo displayInfo = this.displayInfo;
        int i = displayInfo.logicalWidth;
        int i2 = displayInfo.logicalHeight;
        int i3 = displayInfo.rotation;
        boolean z = i3 == 1 || i3 == 3;
        int i4 = z ? i2 : i;
        if (!z) {
            i = i2;
        }
        if (i3 != 0) {
            if (i3 == 1) {
                matrix.postRotate(270.0f);
                matrix.postTranslate(0.0f, i4);
            } else if (i3 == 2) {
                matrix.postRotate(180.0f);
                matrix.postTranslate(i4, i);
            } else {
                if (i3 != 3) {
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i3, "Unknown rotation: "));
                }
                matrix.postRotate(90.0f);
                matrix.postTranslate(i, 0.0f);
            }
        }
        if (this.protectionPathOrig.isEmpty()) {
            return;
        }
        this.protectionPath.set(this.protectionPathOrig);
        this.protectionPath.transform(matrix);
        matrix.mapRect(this.protectionRect, this.protectionRectOrig);
    }

    public static /* synthetic */ void getCameraProtectionProgress$annotations() {
    }

    public static /* synthetic */ void getDisplayInfo$annotations() {
    }

    public static /* synthetic */ void getProtectionPath$annotations() {
    }

    public static /* synthetic */ void getProtectionRect$annotations() {
    }

    public void onUpdate() {
    }
}
