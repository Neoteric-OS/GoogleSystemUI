package com.android.systemui.accessibility.floatingmenu;

import android.R;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DragToInteractAnimationController {
    public final ArrayMap mInteractMap;
    public final DragToInteractView mInteractView;
    public final MenuView mMenuView;
    public float mMinInteractSize;
    public float mSizePercent;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.accessibility.floatingmenu.DragToInteractAnimationController$1, reason: invalid class name */
    public final class AnonymousClass1 extends MagnetizedObject {
        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
        public final float getHeight(Object obj) {
            return ((MenuView) obj).getHeight();
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
        public final void getLocationOnScreen(Object obj, int[] iArr) {
            ((MenuView) obj).getLocationOnScreen(iArr);
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
        public final float getWidth(Object obj) {
            return ((MenuView) obj).getWidth();
        }
    }

    public DragToInteractAnimationController(DragToInteractView dragToInteractView, MenuView menuView) {
        this.mInteractView = dragToInteractView;
        dragToInteractView.setPivotX(dragToInteractView.getWidth() / 2.0f);
        dragToInteractView.setPivotY(dragToInteractView.getHeight() / 2.0f);
        this.mMenuView = menuView;
        updateResources();
        this.mInteractMap = new ArrayMap();
        dragToInteractView.interactMap.forEach(new DragToInteractAnimationController$$ExternalSyntheticLambda0(0, this));
    }

    public final void animateInteractMenu(int i, boolean z) {
        Pair pair = (Pair) this.mInteractMap.get(Integer.valueOf(i));
        if (pair == null) {
            return;
        }
        ValueAnimator valueAnimator = (ValueAnimator) pair.second;
        if (z) {
            valueAnimator.start();
        } else {
            valueAnimator.reverse();
        }
    }

    public MagnetizedObject.MagnetListener getMagnetListener(int i) {
        Pair pair = (Pair) this.mInteractMap.get(Integer.valueOf(i));
        Objects.requireNonNull(pair);
        MagnetizedObject.MagnetListener magnetListener = ((MagnetizedObject) pair.first).magnetListener;
        if (magnetListener != null) {
            return magnetListener;
        }
        return null;
    }

    public final int maybeConsumeMotionEvent(MotionEvent motionEvent) {
        for (Map.Entry entry : this.mInteractMap.entrySet()) {
            if (((MagnetizedObject) ((Pair) entry.getValue()).first).maybeConsumeMotionEvent(motionEvent)) {
                return ((Integer) entry.getKey()).intValue();
            }
        }
        return R.id.empty;
    }

    public final void showInteractView(boolean z) {
        DragToInteractView dragToInteractView = this.mInteractView;
        if (dragToInteractView != null) {
            if (!z) {
                dragToInteractView.hide();
                return;
            }
            if (dragToInteractView.isShowing) {
                return;
            }
            GradientDrawable gradientDrawable = dragToInteractView.gradientDrawable;
            if (gradientDrawable == null) {
                Log.e(DragToInteractView.TAG, "The view isn't ready. Should be called after `setup`");
            }
            if (gradientDrawable == null) {
                return;
            }
            dragToInteractView.isShowing = true;
            dragToInteractView.setVisibility(0);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(gradientDrawable, dragToInteractView.GRADIENT_ALPHA, gradientDrawable.getAlpha(), 255);
            ofInt.setDuration(dragToInteractView.INTERACT_SCRIM_FADE_MS);
            ofInt.start();
            Iterator it = dragToInteractView.interactMap.entrySet().iterator();
            while (it.hasNext()) {
                PhysicsAnimator physicsAnimator = (PhysicsAnimator) ((kotlin.Pair) ((Map.Entry) it.next()).getValue()).getSecond();
                physicsAnimator.cancel();
                physicsAnimator.spring(DynamicAnimation.TRANSLATION_Y, 0.0f, 0.0f, dragToInteractView.spring);
                physicsAnimator.start();
            }
        }
    }

    public final void updateResources() {
        MenuView menuView = this.mMenuView;
        float dimensionPixelSize = menuView.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.dismiss_circle_size);
        float dimensionPixelSize2 = menuView.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.dismiss_circle_small);
        this.mMinInteractSize = dimensionPixelSize2;
        this.mSizePercent = dimensionPixelSize2 / dimensionPixelSize;
    }
}
