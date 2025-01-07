package com.android.wm.shell.windowdecor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Trace;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.widget.ImageView;
import android.window.InputTransferToken;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.DynamicTonalPaletteKt;
import androidx.compose.ui.graphics.ColorKt;
import com.android.wm.shell.R;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.windowdecor.WindowDecoration;
import com.android.wm.shell.windowdecor.common.DecorThemeUtil;
import java.util.function.Supplier;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ResizeVeil {
    public final Bitmap appIcon;
    public SurfaceControl backgroundSurface;
    public final Context context;
    public final ColorScheme darkColors;
    public final DecorThemeUtil decorThemeUtil;
    public Display display;
    public final DisplayController displayController;
    public int iconSize;
    public SurfaceControl iconSurface;
    public boolean isVisible;
    public final ColorScheme lightColors;
    public final ResizeVeil$onDisplaysChangedListener$1 onDisplaysChangedListener;
    public SurfaceControl parentSurface;
    public final AnonymousClass1 surfaceControlBuilderFactory;
    public final Supplier surfaceControlTransactionSupplier;
    public final AnonymousClass1 surfaceControlViewHostFactory;
    public ValueAnimator veilAnimator;
    public SurfaceControl veilSurface;
    public SurfaceControlViewHost viewHost;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.windowdecor.ResizeVeil$1, reason: invalid class name */
    public final class AnonymousClass1 implements WindowDecoration.SurfaceControlViewHostFactory {
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.wm.shell.windowdecor.ResizeVeil$onDisplaysChangedListener$1] */
    public ResizeVeil(Context context, DisplayController displayController, Bitmap bitmap, SurfaceControl surfaceControl, Supplier supplier, final ActivityManager.RunningTaskInfo runningTaskInfo) {
        this.context = context;
        this.displayController = displayController;
        this.appIcon = bitmap;
        this.parentSurface = surfaceControl;
        this.surfaceControlTransactionSupplier = supplier;
        this.decorThemeUtil = new DecorThemeUtil(context);
        this.lightColors = DynamicTonalPaletteKt.dynamicLightColorScheme(context);
        this.darkColors = DynamicTonalPaletteKt.dynamicDarkColorScheme(context);
        this.onDisplaysChangedListener = new DisplayController.OnDisplaysChangedListener() { // from class: com.android.wm.shell.windowdecor.ResizeVeil$onDisplaysChangedListener$1
            @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
            public final void onDisplayAdded(int i) {
                if (runningTaskInfo.displayId != i) {
                    return;
                }
                ResizeVeil resizeVeil = this;
                resizeVeil.displayController.removeDisplayWindowListener(this);
                resizeVeil.setupResizeVeil(runningTaskInfo);
            }
        };
        setupResizeVeil(runningTaskInfo);
    }

    public final void relayout(Rect rect, SurfaceControl.Transaction transaction) {
        float f = 2;
        PointF pointF = new PointF((rect.width() / f) - (this.iconSize / f), (rect.height() / f) - (this.iconSize / f));
        SurfaceControl surfaceControl = this.veilSurface;
        SurfaceControl surfaceControl2 = this.iconSurface;
        if (surfaceControl == null || surfaceControl2 == null) {
            return;
        }
        transaction.setWindowCrop(surfaceControl, rect.width(), rect.height()).setPosition(surfaceControl2, pointF.x, pointF.y).setPosition(this.parentSurface, rect.left, rect.top).setWindowCrop(this.parentSurface, rect.width(), rect.height());
    }

    public final void setupResizeVeil(ActivityManager.RunningTaskInfo runningTaskInfo) {
        int i = runningTaskInfo.displayId;
        DisplayController displayController = this.displayController;
        Display display = displayController.getDisplay(i);
        this.display = display;
        if (display == null) {
            displayController.addDisplayWindowListener(this.onDisplaysChangedListener);
            return;
        }
        Trace.beginSection("ResizeVeil#setupResizeVeil");
        this.veilSurface = new SurfaceControl.Builder().setName(AnnotationValue$1$$ExternalSyntheticOutline0.m(runningTaskInfo.taskId, "Resize veil of Task=")).setContainerLayer().setHidden(true).setParent(this.parentSurface).setCallsite("ResizeVeil#setupResizeVeil").build();
        this.backgroundSurface = new SurfaceControl.Builder().setName(AnnotationValue$1$$ExternalSyntheticOutline0.m(runningTaskInfo.taskId, "Resize veil background of Task=")).setColorLayer().setHidden(true).setParent(this.veilSurface).setCallsite("ResizeVeil#setupResizeVeil").build();
        this.iconSurface = new SurfaceControl.Builder().setName(AnnotationValue$1$$ExternalSyntheticOutline0.m(runningTaskInfo.taskId, "Resize veil icon of Task=")).setContainerLayer().setHidden(true).setParent(this.veilSurface).setCallsite("ResizeVeil#setupResizeVeil").build();
        this.iconSize = this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_resize_veil_icon_size);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.desktop_mode_resize_veil, (ViewGroup) null);
        ((ImageView) inflate.requireViewById(R.id.veil_application_icon)).setImageBitmap(this.appIcon);
        int i2 = this.iconSize;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i2, i2, 2, 8, -2);
        layoutParams.setTitle("Resize veil icon window of Task=" + runningTaskInfo.taskId);
        layoutParams.inputFeatures = 1;
        layoutParams.setTrustedOverlay();
        SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(this.context, this.display, new WindowlessWindowManager(runningTaskInfo.configuration, this.iconSurface, (InputTransferToken) null), "ResizeVeil");
        this.viewHost = surfaceControlViewHost;
        surfaceControlViewHost.setView(inflate, layoutParams);
        Trace.endSection();
    }

    public final void showVeil(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Rect rect, ActivityManager.RunningTaskInfo runningTaskInfo, boolean z) {
        long j;
        final int i = 0;
        final int i2 = 1;
        if (this.viewHost == null || this.isVisible) {
            transaction.apply();
            return;
        }
        this.isVisible = true;
        final SurfaceControl surfaceControl2 = this.backgroundSurface;
        final SurfaceControl surfaceControl3 = this.iconSurface;
        SurfaceControl surfaceControl4 = this.veilSurface;
        if (surfaceControl2 == null || surfaceControl3 == null || surfaceControl4 == null) {
            return;
        }
        if (!surfaceControl.equals(this.parentSurface)) {
            transaction.reparent(surfaceControl4, surfaceControl);
            this.parentSurface = surfaceControl;
        }
        int ordinal = this.decorThemeUtil.getAppTheme(runningTaskInfo).ordinal();
        if (ordinal == 0) {
            j = this.lightColors.surfaceContainer;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            j = this.darkColors.surfaceContainer;
        }
        transaction.show(surfaceControl4).setLayer(surfaceControl4, 60000).setLayer(surfaceControl3, 1).setLayer(surfaceControl2, 0).setColor(surfaceControl2, Color.valueOf(ColorKt.m373toArgb8_81llA(j)).getComponents());
        relayout(rect, transaction);
        if (!z) {
            transaction.show(surfaceControl3).show(surfaceControl2).setAlpha(surfaceControl3, 1.0f).setAlpha(surfaceControl2, 1.0f).apply();
            return;
        }
        ValueAnimator valueAnimator = this.veilAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator2 = this.veilAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        final SurfaceControl.Transaction transaction2 = (SurfaceControl.Transaction) this.surfaceControlTransactionSupplier.get();
        final SurfaceControl.Transaction transaction3 = (SurfaceControl.Transaction) this.surfaceControlTransactionSupplier.get();
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(100L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.windowdecor.ResizeVeil$showVeil$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                switch (i) {
                    case 0:
                        transaction2.setAlpha(surfaceControl2, ((Float) ofFloat.getAnimatedValue()).floatValue()).apply();
                        break;
                    default:
                        transaction2.setAlpha(surfaceControl2, ((Float) ofFloat.getAnimatedValue()).floatValue()).apply();
                        break;
                }
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.windowdecor.ResizeVeil$showVeil$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i) {
                    case 0:
                        transaction2.setAlpha(surfaceControl2, 1.0f).apply();
                        break;
                    default:
                        transaction2.setAlpha(surfaceControl2, 1.0f).apply();
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                switch (i) {
                    case 0:
                        transaction2.show(surfaceControl2).setAlpha(surfaceControl2, 0.0f).apply();
                        break;
                    default:
                        transaction2.show(surfaceControl2).setAlpha(surfaceControl2, 0.0f).apply();
                        break;
                }
            }
        });
        this.veilAnimator = ofFloat;
        final ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat2.setDuration(100L);
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.windowdecor.ResizeVeil$showVeil$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                switch (i2) {
                    case 0:
                        transaction3.setAlpha(surfaceControl3, ((Float) ofFloat2.getAnimatedValue()).floatValue()).apply();
                        break;
                    default:
                        transaction3.setAlpha(surfaceControl3, ((Float) ofFloat2.getAnimatedValue()).floatValue()).apply();
                        break;
                }
            }
        });
        ofFloat2.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.windowdecor.ResizeVeil$showVeil$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i2) {
                    case 0:
                        transaction3.setAlpha(surfaceControl3, 1.0f).apply();
                        break;
                    default:
                        transaction3.setAlpha(surfaceControl3, 1.0f).apply();
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                switch (i2) {
                    case 0:
                        transaction3.show(surfaceControl3).setAlpha(surfaceControl3, 0.0f).apply();
                        break;
                    default:
                        transaction3.show(surfaceControl3).setAlpha(surfaceControl3, 0.0f).apply();
                        break;
                }
            }
        });
        transaction.hide(surfaceControl3).hide(surfaceControl2).apply();
        ValueAnimator valueAnimator3 = this.veilAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.start();
        }
        ofFloat2.start();
    }

    public final void updateResizeVeil(Rect rect, SurfaceControl.Transaction transaction) {
        if (!this.isVisible) {
            transaction.apply();
            return;
        }
        ValueAnimator valueAnimator = this.veilAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.end();
        }
        relayout(rect, transaction);
        transaction.apply();
    }
}
