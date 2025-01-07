package androidx.dynamicanimation.animation;

import android.animation.ValueAnimator;
import android.util.AndroidRuntimeException;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.AnimationHandler;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DynamicAnimation {
    public static final AnonymousClass1 ALPHA;
    public static final AnonymousClass1 ROTATION;
    public static final AnonymousClass1 ROTATION_X;
    public static final AnonymousClass1 ROTATION_Y;
    public static final AnonymousClass1 SCALE_X;
    public static final AnonymousClass1 SCALE_Y;
    public static final AnonymousClass1 SCROLL_X = null;
    public static final AnonymousClass1 SCROLL_Y = null;
    public static final AnonymousClass1 TRANSLATION_X;
    public static final AnonymousClass1 TRANSLATION_Y;
    public static final AnonymousClass1 TRANSLATION_Z;
    public AnimationHandler mAnimationHandler;
    public final ArrayList mEndListeners;
    public long mLastFrameTime;
    public float mMaxValue;
    public float mMinValue;
    public float mMinVisibleChange;
    public final FloatPropertyCompat mProperty;
    public boolean mRunning;
    public boolean mStartValueIsSet;
    public final Object mTarget;
    public final ArrayList mUpdateListeners;
    public float mValue;
    public float mVelocity;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MassState {
        public float mValue;
        public float mVelocity;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnAnimationEndListener {
        void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnAnimationUpdateListener {
        void onAnimationUpdate(float f, float f2);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ViewProperty extends FloatPropertyCompat {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    /* JADX WARN: Type inference failed for: r0v5, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.dynamicanimation.animation.DynamicAnimation$1] */
    static {
        final int i = 0;
        TRANSLATION_X = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i2 = 4;
        TRANSLATION_Y = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i2) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i2) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i3 = 5;
        TRANSLATION_Z = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i3) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i3) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i4 = 6;
        SCALE_X = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i4) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i4) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i5 = 7;
        SCALE_Y = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i5) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i5) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i6 = 8;
        ROTATION = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i6) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i6) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i7 = 9;
        ROTATION_X = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i7) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i7) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i8 = 10;
        ROTATION_Y = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i8) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i8) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i9 = 1;
        ALPHA = new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i9) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i9) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i10 = 2;
        new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i10) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i10) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
        final int i11 = 3;
        new ViewProperty() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i11) {
                    case 0:
                        return ((View) obj).getTranslationX();
                    case 1:
                        return ((View) obj).getAlpha();
                    case 2:
                        return ((View) obj).getScrollX();
                    case 3:
                        return ((View) obj).getScrollY();
                    case 4:
                        return ((View) obj).getTranslationY();
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        return ViewCompat.Api21Impl.getTranslationZ((View) obj);
                    case 6:
                        return ((View) obj).getScaleX();
                    case 7:
                        return ((View) obj).getScaleY();
                    case 8:
                        return ((View) obj).getRotation();
                    case 9:
                        return ((View) obj).getRotationX();
                    default:
                        return ((View) obj).getRotationY();
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i11) {
                    case 0:
                        ((View) obj).setTranslationX(f);
                        break;
                    case 1:
                        ((View) obj).setAlpha(f);
                        break;
                    case 2:
                        ((View) obj).setScrollX((int) f);
                        break;
                    case 3:
                        ((View) obj).setScrollY((int) f);
                        break;
                    case 4:
                        ((View) obj).setTranslationY(f);
                        break;
                    case 5:
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api21Impl.setTranslationZ((View) obj, f);
                        break;
                    case 6:
                        ((View) obj).setScaleX(f);
                        break;
                    case 7:
                        ((View) obj).setScaleY(f);
                        break;
                    case 8:
                        ((View) obj).setRotation(f);
                        break;
                    case 9:
                        ((View) obj).setRotationX(f);
                        break;
                    default:
                        ((View) obj).setRotationY(f);
                        break;
                }
            }
        };
    }

    public DynamicAnimation(final FloatValueHolder floatValueHolder) {
        this.mVelocity = 0.0f;
        this.mValue = Float.MAX_VALUE;
        this.mStartValueIsSet = false;
        this.mRunning = false;
        this.mMaxValue = Float.MAX_VALUE;
        this.mMinValue = -3.4028235E38f;
        this.mLastFrameTime = 0L;
        this.mEndListeners = new ArrayList();
        this.mUpdateListeners = new ArrayList();
        this.mTarget = null;
        this.mProperty = new FloatPropertyCompat() { // from class: androidx.dynamicanimation.animation.DynamicAnimation.15
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                return FloatValueHolder.this.mValue;
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                FloatValueHolder.this.mValue = f;
            }
        };
        this.mMinVisibleChange = 1.0f;
    }

    public final void addEndListener(OnAnimationEndListener onAnimationEndListener) {
        if (this.mEndListeners.contains(onAnimationEndListener)) {
            return;
        }
        this.mEndListeners.add(onAnimationEndListener);
    }

    public final void addUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        if (this.mRunning) {
            throw new UnsupportedOperationException("Error: Update listeners must be added beforethe animation.");
        }
        if (this.mUpdateListeners.contains(onAnimationUpdateListener)) {
            return;
        }
        this.mUpdateListeners.add(onAnimationUpdateListener);
    }

    public void cancel() {
        if (!getAnimationHandler().mScheduler.isCurrentThread()) {
            throw new AndroidRuntimeException("Animations may only be canceled from the same thread as the animation handler");
        }
        if (this.mRunning) {
            endAnimationInternal(true);
        }
    }

    public final void endAnimationInternal(boolean z) {
        this.mRunning = false;
        AnimationHandler animationHandler = getAnimationHandler();
        animationHandler.mDelayedCallbackStartTime.remove(this);
        int indexOf = animationHandler.mAnimationCallbacks.indexOf(this);
        if (indexOf >= 0) {
            animationHandler.mAnimationCallbacks.set(indexOf, null);
            animationHandler.mListDirty = true;
        }
        this.mLastFrameTime = 0L;
        this.mStartValueIsSet = false;
        for (int i = 0; i < this.mEndListeners.size(); i++) {
            if (this.mEndListeners.get(i) != null) {
                ((OnAnimationEndListener) this.mEndListeners.get(i)).onAnimationEnd(this, z, this.mValue, this.mVelocity);
            }
        }
        ArrayList arrayList = this.mEndListeners;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    public final AnimationHandler getAnimationHandler() {
        AnimationHandler animationHandler = this.mAnimationHandler;
        if (animationHandler != null) {
            return animationHandler;
        }
        ThreadLocal threadLocal = AnimationHandler.sAnimatorHandler;
        if (threadLocal.get() == null) {
            threadLocal.set(new AnimationHandler(new AnimationHandler.FrameCallbackScheduler16()));
        }
        return (AnimationHandler) threadLocal.get();
    }

    public final void removeEndListener(OnAnimationEndListener onAnimationEndListener) {
        ArrayList arrayList = this.mEndListeners;
        int indexOf = arrayList.indexOf(onAnimationEndListener);
        if (indexOf >= 0) {
            arrayList.set(indexOf, null);
        }
    }

    public final void setMinimumVisibleChange(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum visible change must be positive.");
        }
        this.mMinVisibleChange = f;
        setValueThreshold(f * 0.75f);
    }

    public final void setPropertyValue(float f) {
        this.mProperty.setValue(this.mTarget, f);
        for (int i = 0; i < this.mUpdateListeners.size(); i++) {
            if (this.mUpdateListeners.get(i) != null) {
                ((OnAnimationUpdateListener) this.mUpdateListeners.get(i)).onAnimationUpdate(this.mValue, this.mVelocity);
            }
        }
        ArrayList arrayList = this.mUpdateListeners;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    public abstract void setValueThreshold(float f);

    public void start() {
        if (!getAnimationHandler().mScheduler.isCurrentThread()) {
            throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
        }
        boolean z = this.mRunning;
        if (z || z) {
            return;
        }
        this.mRunning = true;
        if (!this.mStartValueIsSet) {
            this.mValue = this.mProperty.getValue(this.mTarget);
        }
        float f = this.mValue;
        if (f > this.mMaxValue || f < this.mMinValue) {
            throw new IllegalArgumentException("Starting value need to be in between min value and max value");
        }
        AnimationHandler animationHandler = getAnimationHandler();
        if (animationHandler.mAnimationCallbacks.size() == 0) {
            animationHandler.mScheduler.postFrameCallback(animationHandler.mRunnable);
            animationHandler.mDurationScale = ValueAnimator.getDurationScale();
            if (animationHandler.mDurationScaleChangeListener == null) {
                animationHandler.mDurationScaleChangeListener = new AnimationHandler.FrameCallbackScheduler16(animationHandler);
            }
            final AnimationHandler.FrameCallbackScheduler16 frameCallbackScheduler16 = animationHandler.mDurationScaleChangeListener;
            if (((AnimationHandler$DurationScaleChangeListener33$$ExternalSyntheticLambda0) frameCallbackScheduler16.mChoreographer) == null) {
                ValueAnimator.DurationScaleChangeListener durationScaleChangeListener = new ValueAnimator.DurationScaleChangeListener() { // from class: androidx.dynamicanimation.animation.AnimationHandler$DurationScaleChangeListener33$$ExternalSyntheticLambda0
                    @Override // android.animation.ValueAnimator.DurationScaleChangeListener
                    public final void onChanged(float f2) {
                        ((AnimationHandler) AnimationHandler.FrameCallbackScheduler16.this.mLooper).mDurationScale = f2;
                    }
                };
                frameCallbackScheduler16.mChoreographer = durationScaleChangeListener;
                ValueAnimator.registerDurationScaleChangeListener(durationScaleChangeListener);
            }
        }
        if (animationHandler.mAnimationCallbacks.contains(this)) {
            return;
        }
        animationHandler.mAnimationCallbacks.add(this);
    }

    public abstract boolean updateValueAndVelocity(long j);

    public DynamicAnimation(Object obj, FloatPropertyCompat floatPropertyCompat) {
        this.mVelocity = 0.0f;
        this.mValue = Float.MAX_VALUE;
        this.mStartValueIsSet = false;
        this.mRunning = false;
        this.mMaxValue = Float.MAX_VALUE;
        this.mMinValue = -3.4028235E38f;
        this.mLastFrameTime = 0L;
        this.mEndListeners = new ArrayList();
        this.mUpdateListeners = new ArrayList();
        this.mTarget = obj;
        this.mProperty = floatPropertyCompat;
        if (floatPropertyCompat != ROTATION && floatPropertyCompat != ROTATION_X && floatPropertyCompat != ROTATION_Y) {
            if (floatPropertyCompat == ALPHA) {
                this.mMinVisibleChange = 0.00390625f;
                return;
            } else if (floatPropertyCompat != SCALE_X && floatPropertyCompat != SCALE_Y) {
                this.mMinVisibleChange = 1.0f;
                return;
            } else {
                this.mMinVisibleChange = 0.002f;
                return;
            }
        }
        this.mMinVisibleChange = 0.1f;
    }
}
