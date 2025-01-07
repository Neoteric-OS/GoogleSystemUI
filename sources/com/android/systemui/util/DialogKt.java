package com.android.systemui.util;

import android.R;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.window.OnBackInvokedDispatcher;
import com.android.app.animation.Interpolators;
import com.android.systemui.animation.back.BackAnimationSpec;
import com.android.systemui.animation.back.BackAnimationSpecKt$createFloatingSurfaceAnimationSpec$1;
import com.android.systemui.animation.back.BackTransformation;
import com.android.systemui.animation.back.OnBackAnimationCallbackExtensionKt;
import com.android.systemui.animation.back.OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$4;
import com.android.systemui.animation.back.ScalePivotPosition;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DialogKt {
    public static final Pair maybeForceFullscreen(final Dialog dialog) {
        dialog.create();
        final Window window = dialog.getWindow();
        Intrinsics.checkNotNull(window);
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (window.getAttributes().width == -1 && window.getAttributes().height == -1) {
            return null;
        }
        FrameLayout frameLayout = new FrameLayout(dialog.getContext());
        viewGroup.addView(frameLayout, 0, new FrameLayout.LayoutParams(-1, -1));
        final LaunchableFrameLayout launchableFrameLayout = new LaunchableFrameLayout(dialog.getContext());
        launchableFrameLayout.setBackground(viewGroup.getBackground());
        window.setBackgroundDrawableResource(R.color.transparent);
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.util.DialogKt$maybeForceFullscreen$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        launchableFrameLayout.setClickable(true);
        frameLayout.setImportantForAccessibility(2);
        launchableFrameLayout.setImportantForAccessibility(2);
        frameLayout.addView(launchableFrameLayout, new FrameLayout.LayoutParams(window.getAttributes().width, window.getAttributes().height, window.getAttributes().gravity));
        int childCount = viewGroup.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(1);
            viewGroup.removeViewAt(1);
            launchableFrameLayout.addView(childAt);
        }
        window.setLayout(-1, -1);
        View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: com.android.systemui.util.DialogKt$maybeForceFullscreen$decorViewLayoutListener$1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                if (window.getAttributes().width == -1 && window.getAttributes().height == -1) {
                    return;
                }
                ViewGroup.LayoutParams layoutParams = launchableFrameLayout.getLayoutParams();
                layoutParams.width = window.getAttributes().width;
                layoutParams.height = window.getAttributes().height;
                launchableFrameLayout.setLayoutParams(layoutParams);
                window.setLayout(-1, -1);
            }
        };
        viewGroup.addOnLayoutChangeListener(onLayoutChangeListener);
        return new Pair(launchableFrameLayout, onLayoutChangeListener);
    }

    public static final void registerAnimationOnBackInvoked(final Dialog dialog, final View view, BackAnimationSpec backAnimationSpec) {
        final OnBackInvokedDispatcher onBackInvokedDispatcher = dialog.getOnBackInvokedDispatcher();
        final OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$4 onBackAnimationCallbackFrom$default = OnBackAnimationCallbackExtensionKt.onBackAnimationCallbackFrom$default(backAnimationSpec, view.getResources().getDisplayMetrics(), new Function1() { // from class: com.android.systemui.util.DialogKt$registerAnimationOnBackInvoked$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int width;
                float height;
                BackTransformation backTransformation = (BackTransformation) obj;
                View view2 = view;
                float f = backTransformation.translateX;
                if (!Float.isInfinite(f) && !Float.isNaN(f)) {
                    view2.setTranslationX(backTransformation.translateX);
                }
                float f2 = backTransformation.translateY;
                if (!Float.isInfinite(f2) && !Float.isNaN(f2)) {
                    view2.setTranslationY(backTransformation.translateY);
                }
                ScalePivotPosition scalePivotPosition = backTransformation.scalePivotPosition;
                if (scalePivotPosition != null) {
                    int ordinal = scalePivotPosition.ordinal();
                    if (ordinal == 0) {
                        width = view2.getWidth();
                    } else {
                        if (ordinal != 1) {
                            throw new NoWhenBranchMatchedException();
                        }
                        width = view2.getWidth();
                    }
                    float f3 = width / 2.0f;
                    int ordinal2 = scalePivotPosition.ordinal();
                    if (ordinal2 == 0) {
                        height = view2.getHeight() / 2.0f;
                    } else {
                        if (ordinal2 != 1) {
                            throw new NoWhenBranchMatchedException();
                        }
                        height = view2.getHeight();
                    }
                    view2.setPivotX(f3);
                    view2.setPivotY(height);
                }
                float f4 = backTransformation.scale;
                if (!Float.isInfinite(f4) && !Float.isNaN(f4)) {
                    view2.setScaleX(backTransformation.scale);
                    view2.setScaleY(backTransformation.scale);
                }
                return Unit.INSTANCE;
            }
        }, new Function0() { // from class: com.android.systemui.util.DialogKt$registerAnimationOnBackInvoked$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                dialog.dismiss();
                return Unit.INSTANCE;
            }
        });
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.animation.back.OnBackAnimationCallbackExtensionKt$registerOnBackInvokedCallbackOnViewAttached$1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view2) {
                onBackInvokedDispatcher.registerOnBackInvokedCallback(0, onBackAnimationCallbackFrom$default);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view2) {
                view.removeOnAttachStateChangeListener(this);
                onBackInvokedDispatcher.unregisterOnBackInvokedCallback(onBackAnimationCallbackFrom$default);
            }
        });
        if (view.isAttachedToWindow()) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(0, onBackAnimationCallbackFrom$default);
        }
    }

    public static void registerAnimationOnBackInvoked$default(Dialog dialog, final View view) {
        Function0 function0 = new Function0() { // from class: com.android.systemui.util.DialogKt$registerAnimationOnBackInvoked$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return view.getResources().getDisplayMetrics();
            }
        };
        Interpolator interpolator = Interpolators.BACK_GESTURE;
        registerAnimationOnBackInvoked(dialog, view, new BackAnimationSpecKt$createFloatingSurfaceAnimationSpec$1(function0, interpolator, Interpolators.LINEAR, interpolator));
    }
}
