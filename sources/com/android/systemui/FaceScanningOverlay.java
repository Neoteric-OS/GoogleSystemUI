package com.android.systemui;

import android.R;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.IndentingPrintWriter;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.android.app.animation.Interpolators;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.settingslib.Utils;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.log.ScreenDecorationsLogger;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceScanningOverlay extends ScreenDecorations.DisplayCutoutView {
    public static final Companion Companion = null;
    public final AuthController authController;
    public ValueAnimator cameraProtectionAnimator;
    public int cameraProtectionColor;
    public int faceScanningAnimColor;
    public ScreenDecorations$$ExternalSyntheticLambda7 hideOverlayRunnable;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final ScreenDecorationsLogger logger;
    public AnimatorSet rimAnimator;
    public final Paint rimPaint;
    public float rimProgress;
    public final RectF rimRect;
    public boolean showScanningAnim;
    public final StatusBarStateController statusBarStateController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final void access$scalePath(Path path, float f) {
            Companion companion = FaceScanningOverlay.Companion;
            Matrix matrix = new Matrix();
            RectF rectF = new RectF();
            path.computeBounds(rectF, true);
            matrix.setScale(f, f, rectF.centerX(), rectF.centerY());
            path.transform(matrix);
        }
    }

    public FaceScanningOverlay(Context context, int i, StatusBarStateController statusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, ScreenDecorationsLogger screenDecorationsLogger, AuthController authController) {
        super(i, context);
        this.statusBarStateController = statusBarStateController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.logger = screenDecorationsLogger;
        this.authController = authController;
        this.rimPaint = new Paint();
        this.rimProgress = 0.5f;
        this.rimRect = new RectF();
        this.cameraProtectionColor = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
        this.faceScanningAnimColor = Utils.getColorAttrDefaultColor(R.^attr-private.materialColorPrimaryFixed, 0, context);
        setVisibility(4);
    }

    public final ValueAnimator createRimDisappearAnimator(float f, long j, TimeInterpolator timeInterpolator) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.rimProgress, f);
        ofFloat.setDuration(j);
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.addUpdateListener(new FaceScanningOverlay$enableShowProtection$1$1(this, 2));
        ofFloat.addListener(new FaceScanningOverlay$enableShowProtection$1$2(this, 1));
        return ofFloat;
    }

    @Override // com.android.systemui.DisplayCutoutBaseView
    public final void drawCutoutProtection(Canvas canvas) {
        if (this.protectionRect.isEmpty()) {
            return;
        }
        if (this.rimProgress > 0.5f) {
            Path path = new Path(this.protectionPath);
            Companion.access$scalePath(path, this.rimProgress);
            this.rimPaint.setStyle(Paint.Style.FILL);
            int alpha = this.rimPaint.getAlpha();
            this.rimPaint.setColor(ColorUtils.blendARGB(this.faceScanningAnimColor, this.statusBarStateController.getDozeAmount(), -1));
            this.rimPaint.setAlpha(alpha);
            canvas.drawPath(path, this.rimPaint);
        }
        if (this.cameraProtectionProgress > 0.5f) {
            Path path2 = new Path(this.protectionPath);
            Companion.access$scalePath(path2, this.cameraProtectionProgress);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(this.cameraProtectionColor);
            canvas.drawPath(path2, this.paint);
        }
    }

    @Override // com.android.systemui.DisplayCutoutBaseView
    public final void dump(PrintWriter printWriter) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.increaseIndent();
        asIndenting.println("FaceScanningOverlay:");
        super.dump(asIndenting);
        asIndenting.println("rimProgress=" + this.rimProgress);
        asIndenting.println("rimRect=" + this.rimRect);
        asIndenting.println("this=" + this);
        asIndenting.decreaseIndent();
    }

    @Override // com.android.systemui.DisplayCutoutBaseView
    public final void enableShowProtection(boolean z) {
        long j;
        AnimatorSet animatorSet;
        int i = 3;
        int i2 = 2;
        int i3 = 0;
        boolean z2 = this.keyguardUpdateMonitor.isFaceDetectionRunning() || this.authController.isShowing();
        boolean isFaceAuthenticated = this.keyguardUpdateMonitor.getIsFaceAuthenticated();
        boolean z3 = z2 && z;
        if (z3 == this.showScanningAnim) {
            return;
        }
        this.logger.cameraProtectionShownOrHidden(z3, this.keyguardUpdateMonitor.isFaceDetectionRunning(), this.authController.isShowing(), isFaceAuthenticated, z, this.showScanningAnim);
        this.showScanningAnim = z3;
        updateProtectionBoundingPath();
        if (this.showScanningAnim) {
            setVisibility(0);
            requestLayout();
        }
        ValueAnimator valueAnimator = this.cameraProtectionAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.cameraProtectionProgress, z3 ? 1.0f : 0.5f);
        ofFloat.setStartDelay(this.showScanningAnim ? 0L : isFaceAuthenticated ? 400L : 200L);
        if (this.showScanningAnim) {
            j = 250;
        } else {
            j = isFaceAuthenticated ? 500L : 300L;
        }
        ofFloat.setDuration(j);
        ofFloat.setInterpolator(this.showScanningAnim ? Interpolators.STANDARD_ACCELERATE : isFaceAuthenticated ? Interpolators.STANDARD : Interpolators.STANDARD_DECELERATE);
        ofFloat.addUpdateListener(new FaceScanningOverlay$enableShowProtection$1$1(this, 0));
        ofFloat.addListener(new FaceScanningOverlay$enableShowProtection$1$2(this, i3));
        this.cameraProtectionAnimator = ofFloat;
        AnimatorSet animatorSet2 = this.rimAnimator;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        if (this.showScanningAnim) {
            animatorSet = new AnimatorSet();
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 1.125f);
            ofFloat2.setDuration(250L);
            ofFloat2.setInterpolator(Interpolators.STANDARD_DECELERATE);
            ofFloat2.addUpdateListener(new FaceScanningOverlay$enableShowProtection$1$1(this, 1));
            animatorSet.playSequentially(this.cameraProtectionAnimator, ofFloat2);
        } else if (isFaceAuthenticated) {
            AnimatorSet animatorSet3 = new AnimatorSet();
            ValueAnimator ofInt = ValueAnimator.ofInt(255, 0);
            ofInt.setDuration(400L);
            ofInt.setInterpolator(Interpolators.LINEAR);
            ofInt.addUpdateListener(new FaceScanningOverlay$enableShowProtection$1$1(this, 3));
            ofInt.addListener(new FaceScanningOverlay$enableShowProtection$1$2(this, i2));
            animatorSet3.playTogether(createRimDisappearAnimator(1.25f, 400L, Interpolators.STANDARD_DECELERATE), ofInt);
            AnimatorSet animatorSet4 = new AnimatorSet();
            animatorSet4.playTogether(animatorSet3, this.cameraProtectionAnimator);
            animatorSet = animatorSet4;
        } else {
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(createRimDisappearAnimator(1.0f, 200L, Interpolators.STANDARD), this.cameraProtectionAnimator);
        }
        this.rimAnimator = animatorSet;
        animatorSet.addListener(new FaceScanningOverlay$enableShowProtection$1$2(this, i));
        AnimatorSet animatorSet5 = this.rimAnimator;
        if (animatorSet5 != null) {
            animatorSet5.start();
        }
    }

    @Override // com.android.systemui.ScreenDecorations.DisplayCutoutView, android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.mBounds.isEmpty()) {
            super.onMeasure(i, i2);
            return;
        }
        if (!this.showScanningAnim) {
            setMeasuredDimension(View.resolveSizeAndState(this.mBoundingRect.width(), i, 0), View.resolveSizeAndState(this.mBoundingRect.height(), i2, 0));
            return;
        }
        this.mTotalBounds.set(this.mBoundingRect);
        Rect rect = this.mTotalBounds;
        RectF rectF = this.rimRect;
        rect.union((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        int resolveSizeAndState = View.resolveSizeAndState(this.mTotalBounds.width(), i, 0);
        int resolveSizeAndState2 = View.resolveSizeAndState(this.mTotalBounds.height(), i2, 0);
        this.logger.boundingRect(this.rimRect, "onMeasure: Face scanning animation");
        ScreenDecorationsLogger screenDecorationsLogger = this.logger;
        Rect rect2 = this.mBoundingRect;
        screenDecorationsLogger.getClass();
        screenDecorationsLogger.boundingRect(new RectF(rect2), "onMeasure: Display cutout view bounding rect");
        ScreenDecorationsLogger screenDecorationsLogger2 = this.logger;
        Rect rect3 = this.mTotalBounds;
        screenDecorationsLogger2.getClass();
        screenDecorationsLogger2.boundingRect(new RectF(rect3), "onMeasure: TotalBounds");
        this.logger.onMeasureDimensions(i, i2, resolveSizeAndState, resolveSizeAndState2);
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    @Override // com.android.systemui.ScreenDecorations.DisplayCutoutView
    public final void setColor$1(int i) {
        this.cameraProtectionColor = i;
        invalidate();
    }

    @Override // com.android.systemui.DisplayCutoutBaseView
    public final void updateProtectionBoundingPath() {
        super.updateProtectionBoundingPath();
        this.rimRect.set(this.protectionRect);
        this.rimRect.scale(this.rimProgress);
    }
}
