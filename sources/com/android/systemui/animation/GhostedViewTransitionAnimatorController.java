package com.android.systemui.animation;

import android.content.ComponentName;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.GhostView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import java.util.LinkedList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class GhostedViewTransitionAnimatorController implements ActivityTransitionAnimator.Controller {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Drawable background;
    public WrappedDrawable backgroundDrawable;
    public final Lazy backgroundInsets$delegate;
    public FrameLayout backgroundView;
    public final ComponentName component;
    public GhostView ghostView;
    public final Matrix ghostViewMatrix;
    public final View ghostedView;
    public final int[] ghostedViewLocation;
    public final TransitionAnimator.State ghostedViewState;
    public final float[] initialGhostViewMatrixValues;
    public final InteractionJankMonitor interactionJankMonitor;
    public final boolean isLaunching;
    public final Integer launchCujType;
    public final Integer returnCujType;
    public int startBackgroundAlpha;
    public ViewGroup transitionContainer;
    public final int[] transitionContainerLocation;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static GradientDrawable findGradientDrawable(Drawable drawable) {
            if (drawable instanceof GradientDrawable) {
                return (GradientDrawable) drawable;
            }
            if (drawable instanceof InsetDrawable) {
                Drawable drawable2 = ((InsetDrawable) drawable).getDrawable();
                if (drawable2 != null) {
                    return findGradientDrawable(drawable2);
                }
                return null;
            }
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    GradientDrawable findGradientDrawable = findGradientDrawable(layerDrawable.getDrawable(i));
                    if (findGradientDrawable != null) {
                        return findGradientDrawable;
                    }
                }
            }
            if (drawable instanceof StateListDrawable) {
                return findGradientDrawable(((StateListDrawable) drawable).getCurrent());
            }
            return null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WrappedDrawable extends Drawable {
        public final float[] cornerRadii;
        public int currentAlpha = 255;
        public final Rect previousBounds = new Rect();
        public final float[] previousCornerRadii;
        public final Drawable wrapped;

        public WrappedDrawable(Drawable drawable) {
            this.wrapped = drawable;
            float[] fArr = new float[8];
            for (int i = 0; i < 8; i++) {
                fArr[i] = -1.0f;
            }
            this.cornerRadii = fArr;
            this.previousCornerRadii = new float[8];
        }

        public static void applyBackgroundRadii(Drawable drawable, float[] fArr) {
            if (drawable instanceof GradientDrawable) {
                ((GradientDrawable) drawable).setCornerRadii(fArr);
                return;
            }
            if (drawable instanceof InsetDrawable) {
                Drawable drawable2 = ((InsetDrawable) drawable).getDrawable();
                if (drawable2 != null) {
                    applyBackgroundRadii(drawable2, fArr);
                    return;
                }
                return;
            }
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    applyBackgroundRadii(layerDrawable.getDrawable(i), fArr);
                }
            }
        }

        @Override // android.graphics.drawable.Drawable
        public final void draw(Canvas canvas) {
            Drawable drawable;
            Drawable drawable2;
            Drawable drawable3 = this.wrapped;
            if (drawable3 == null) {
                return;
            }
            drawable3.copyBounds(this.previousBounds);
            drawable3.setAlpha(this.currentAlpha);
            drawable3.setBounds(getBounds());
            if (this.cornerRadii[0] >= 0.0f && (drawable2 = this.wrapped) != null) {
                GradientDrawable findGradientDrawable = Companion.findGradientDrawable(drawable2);
                if (findGradientDrawable != null) {
                    float[] cornerRadii = findGradientDrawable.getCornerRadii();
                    if (cornerRadii != null) {
                        System.arraycopy(cornerRadii, 0, this.previousCornerRadii, 0, (r3 & 8) != 0 ? cornerRadii.length : 6);
                    } else {
                        float cornerRadius = findGradientDrawable.getCornerRadius();
                        float[] fArr = this.previousCornerRadii;
                        fArr[0] = cornerRadius;
                        fArr[1] = cornerRadius;
                        fArr[2] = cornerRadius;
                        fArr[3] = cornerRadius;
                        fArr[4] = cornerRadius;
                        fArr[5] = cornerRadius;
                        fArr[6] = cornerRadius;
                        fArr[7] = cornerRadius;
                    }
                }
                applyBackgroundRadii(this.wrapped, this.cornerRadii);
            }
            drawable3.draw(canvas);
            drawable3.setAlpha(0);
            drawable3.setBounds(this.previousBounds);
            if (this.cornerRadii[0] < 0.0f || (drawable = this.wrapped) == null) {
                return;
            }
            applyBackgroundRadii(drawable, this.previousCornerRadii);
        }

        @Override // android.graphics.drawable.Drawable
        public final int getAlpha() {
            return this.currentAlpha;
        }

        @Override // android.graphics.drawable.Drawable
        public final int getOpacity() {
            Drawable drawable = this.wrapped;
            if (drawable == null) {
                return -2;
            }
            int alpha = drawable.getAlpha();
            drawable.setAlpha(this.currentAlpha);
            int opacity = drawable.getOpacity();
            drawable.setAlpha(alpha);
            return opacity;
        }

        @Override // android.graphics.drawable.Drawable
        public final void setAlpha(int i) {
            if (i != this.currentAlpha) {
                this.currentAlpha = i;
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public final void setColorFilter(ColorFilter colorFilter) {
            Drawable drawable = this.wrapped;
            if (drawable == null) {
                return;
            }
            drawable.setColorFilter(colorFilter);
        }
    }

    public GhostedViewTransitionAnimatorController(View view, Integer num, int i) {
        Drawable drawable = null;
        num = (i & 2) != 0 ? null : num;
        InteractionJankMonitor interactionJankMonitor = InteractionJankMonitor.getInstance();
        this.ghostedView = view;
        this.launchCujType = num;
        this.returnCujType = null;
        this.interactionJankMonitor = interactionJankMonitor;
        this.isLaunching = true;
        this.transitionContainer = (ViewGroup) view.getRootView();
        this.transitionContainerLocation = new int[2];
        float[] fArr = new float[9];
        for (int i2 = 0; i2 < 9; i2++) {
            fArr[i2] = 0.0f;
        }
        this.initialGhostViewMatrixValues = fArr;
        this.ghostViewMatrix = new Matrix();
        this.backgroundInsets$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.animation.GhostedViewTransitionAnimatorController$backgroundInsets$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Insets opticalInsets;
                Drawable drawable2 = GhostedViewTransitionAnimatorController.this.background;
                return (drawable2 == null || (opticalInsets = drawable2.getOpticalInsets()) == null) ? Insets.NONE : opticalInsets;
            }
        });
        this.startBackgroundAlpha = 255;
        this.ghostedViewLocation = new int[2];
        this.ghostedViewState = new TransitionAnimator.State(0, 0, 0, 0, 0.0f, 0.0f, 63);
        View view2 = this.ghostedView;
        if (!(view2 instanceof LaunchableView)) {
            throw new IllegalArgumentException("A GhostedViewLaunchAnimatorController was created from a View that does not implement LaunchableView. This can lead to subtle bugs where the visibility of the View we are launching from is not what we expected.");
        }
        if (view2.getBackground() == null) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(view2);
            while (true) {
                if (linkedList.isEmpty()) {
                    break;
                }
                View view3 = (View) linkedList.remove(0);
                if (view3.getBackground() != null) {
                    drawable = view3.getBackground();
                    break;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    int childCount = viewGroup.getChildCount();
                    for (int i3 = 0; i3 < childCount; i3++) {
                        linkedList.add(viewGroup.getChildAt(i3));
                    }
                }
            }
        } else {
            drawable = view2.getBackground();
        }
        this.background = drawable;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final TransitionAnimator.State createAnimatorState() {
        TransitionAnimator.State state = new TransitionAnimator.State(0, 0, 0, 0, getCurrentTopCornerRadius(), getCurrentBottomCornerRadius(), 15);
        fillGhostedViewState(state);
        return state;
    }

    public final void fillGhostedViewState(TransitionAnimator.State state) {
        View view = this.ghostedView;
        int[] iArr = this.ghostedViewLocation;
        view.getLocationOnScreen(iArr);
        Insets insets = (Insets) this.backgroundInsets$delegate.getValue();
        KeyEvent.Callback callback = this.ghostedView;
        Rect paddingForLaunchAnimation = callback instanceof LaunchableView ? ((LaunchableView) callback).getPaddingForLaunchAnimation() : new Rect();
        int i = iArr[1];
        state.top = insets.top + i + paddingForLaunchAnimation.top;
        state.bottom = ((MathKt.roundToInt(this.ghostedView.getScaleY() * this.ghostedView.getHeight()) + i) - insets.bottom) + paddingForLaunchAnimation.bottom;
        int i2 = iArr[0];
        state.left = insets.left + i2 + paddingForLaunchAnimation.left;
        state.right = ((MathKt.roundToInt(this.ghostedView.getScaleX() * this.ghostedView.getWidth()) + i2) - insets.right) + paddingForLaunchAnimation.right;
    }

    public float getCurrentBottomCornerRadius() {
        GradientDrawable findGradientDrawable;
        Drawable drawable = this.background;
        if (drawable == null || (findGradientDrawable = Companion.findGradientDrawable(drawable)) == null) {
            return 0.0f;
        }
        float[] cornerRadii = findGradientDrawable.getCornerRadii();
        return this.ghostedView.getScaleX() * (cornerRadii != null ? cornerRadii[4] : findGradientDrawable.getCornerRadius());
    }

    public float getCurrentTopCornerRadius() {
        GradientDrawable findGradientDrawable;
        Drawable drawable = this.background;
        if (drawable == null || (findGradientDrawable = Companion.findGradientDrawable(drawable)) == null) {
            return 0.0f;
        }
        float[] cornerRadii = findGradientDrawable.getCornerRadii();
        return this.ghostedView.getScaleX() * (cornerRadii != null ? cornerRadii[0] : findGradientDrawable.getCornerRadius());
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final ViewGroup getTransitionContainer() {
        return this.transitionContainer;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final boolean isLaunching() {
        return this.isLaunching;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationEnd(boolean z) {
        if (this.ghostView == null) {
            return;
        }
        Integer num = this.isLaunching ? this.launchCujType : this.returnCujType;
        if (num != null) {
            this.interactionJankMonitor.end(num.intValue());
        }
        WrappedDrawable wrappedDrawable = this.backgroundDrawable;
        Drawable drawable = wrappedDrawable != null ? wrappedDrawable.wrapped : null;
        if (drawable != null) {
            drawable.setAlpha(this.startBackgroundAlpha);
        }
        GhostView.removeGhost(this.ghostedView);
        FrameLayout frameLayout = this.backgroundView;
        if (frameLayout != null) {
            this.transitionContainer.getOverlay().remove(frameLayout);
        }
        View view = this.ghostedView;
        if (view instanceof LaunchableView) {
            ((LaunchableView) view).setShouldBlockVisibilityChanges(false);
            ((LaunchableView) this.ghostedView).onActivityLaunchAnimationEnd();
        } else {
            view.setVisibility(4);
            this.ghostedView.setVisibility(0);
            this.ghostedView.invalidate();
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        GhostView ghostView = this.ghostView;
        if (ghostView == null) {
            return;
        }
        FrameLayout frameLayout = this.backgroundView;
        Intrinsics.checkNotNull(frameLayout);
        if (!state.visible || !this.ghostedView.isAttachedToWindow()) {
            if (ghostView.getVisibility() == 0) {
                ghostView.setVisibility(4);
                this.ghostedView.setTransitionVisibility(4);
                frameLayout.setVisibility(4);
                return;
            }
            return;
        }
        if (ghostView.getVisibility() == 4) {
            ghostView.setVisibility(0);
            frameLayout.setVisibility(0);
        }
        TransitionAnimator.State state2 = this.ghostedViewState;
        fillGhostedViewState(state2);
        int i = state.left - state2.left;
        int i2 = state.right - state2.right;
        int i3 = state.top - state2.top;
        int i4 = state.bottom - state2.bottom;
        float min = Math.min(state.getWidth() / state2.getWidth(), state.getHeight() / state2.getHeight());
        if (this.ghostedView.getParent() instanceof ViewGroup) {
            GhostView.calculateMatrix(this.ghostedView, this.transitionContainer, this.ghostViewMatrix);
        }
        ViewGroup viewGroup = this.transitionContainer;
        int[] iArr = this.transitionContainerLocation;
        viewGroup.getLocationOnScreen(iArr);
        this.ghostViewMatrix.postScale(min, min, ((state2.getWidth() / 2.0f) + state2.left) - iArr[0], ((state2.getHeight() / 2.0f) + state2.top) - iArr[1]);
        this.ghostViewMatrix.postTranslate((i + i2) / 2.0f, (i3 + i4) / 2.0f);
        ghostView.setAnimationMatrix(this.ghostViewMatrix);
        Insets insets = (Insets) this.backgroundInsets$delegate.getValue();
        int i5 = state.top - insets.top;
        int i6 = state.left - insets.left;
        int i7 = state.right + insets.right;
        int i8 = state.bottom + insets.bottom;
        frameLayout.setTop(i5 - iArr[1]);
        frameLayout.setBottom(i8 - iArr[1]);
        frameLayout.setLeft(i6 - iArr[0]);
        frameLayout.setRight(i7 - iArr[0]);
        WrappedDrawable wrappedDrawable = this.backgroundDrawable;
        Intrinsics.checkNotNull(wrappedDrawable);
        if (wrappedDrawable.wrapped != null) {
            float f3 = state.topCornerRadius;
            float f4 = state.bottomCornerRadius;
            WrappedDrawable wrappedDrawable2 = this.backgroundDrawable;
            if (wrappedDrawable2 != null) {
                float[] fArr = wrappedDrawable2.cornerRadii;
                fArr[0] = f3;
                fArr[1] = f3;
                fArr[2] = f3;
                fArr[3] = f3;
                fArr[4] = f4;
                fArr[5] = f4;
                fArr[6] = f4;
                fArr[7] = f4;
                wrappedDrawable2.invalidateSelf();
            }
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationStart(boolean z) {
        Matrix matrix;
        ViewParent parent;
        if (!(this.ghostedView.getParent() instanceof ViewGroup)) {
            Log.w("GhostedViewTransitionAnimatorController", "Skipping animation as ghostedView is not attached to a ViewGroup");
            return;
        }
        FrameLayout frameLayout = new FrameLayout(this.transitionContainer.getContext());
        this.transitionContainer.getOverlay().add(frameLayout);
        this.backgroundView = frameLayout;
        Drawable drawable = this.background;
        this.startBackgroundAlpha = drawable != null ? drawable.getAlpha() : 255;
        WrappedDrawable wrappedDrawable = new WrappedDrawable(this.background);
        this.backgroundDrawable = wrappedDrawable;
        FrameLayout frameLayout2 = this.backgroundView;
        if (frameLayout2 != null) {
            frameLayout2.setBackground(wrappedDrawable);
        }
        KeyEvent.Callback callback = this.ghostedView;
        LaunchableView launchableView = callback instanceof LaunchableView ? (LaunchableView) callback : null;
        if (launchableView != null) {
            launchableView.setShouldBlockVisibilityChanges(true);
        }
        GhostView addGhost = GhostView.addGhost(this.ghostedView, this.transitionContainer);
        this.ghostView = addGhost;
        Object parent2 = (addGhost == null || (parent = addGhost.getParent()) == null) ? null : parent.getParent();
        ViewGroup viewGroup = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
        if (viewGroup != null) {
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
        GhostView ghostView = this.ghostView;
        if (ghostView == null || (matrix = ghostView.getAnimationMatrix()) == null) {
            matrix = Matrix.IDENTITY_MATRIX;
        }
        Intrinsics.checkNotNull(matrix);
        matrix.getValues(this.initialGhostViewMatrixValues);
        Integer num = this.isLaunching ? this.launchCujType : this.returnCujType;
        if (num != null) {
            this.interactionJankMonitor.begin(this.ghostedView, num.intValue());
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void setTransitionContainer(ViewGroup viewGroup) {
        this.transitionContainer = viewGroup;
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void getTransitionCookie() {
    }
}
