package com.android.systemui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.util.IntProperty;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.systemui.animation.ViewHierarchyAnimator;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator$animateViewIn$onAnimationEnd$1;
import com.android.wm.shell.R;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.EmptySet;
import kotlin.collections.MapsKt;
import kotlin.collections.builders.ListBuilder;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewHierarchyAnimator {
    public static final Interpolator DEFAULT_FADE_IN_INTERPOLATOR;
    public static final Map PROPERTIES;
    public static final Companion Companion = new Companion();
    public static final Interpolator DEFAULT_INTERPOLATOR = Interpolators.STANDARD;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    abstract class Bound {
        public static final /* synthetic */ Bound[] $VALUES;
        public static final BOTTOM BOTTOM;
        public static final LEFT LEFT;
        public static final RIGHT RIGHT;
        public static final TOP TOP;
        private final String label;
        private final int overrideTag;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class BOTTOM extends Bound {
            @Override // com.android.systemui.animation.ViewHierarchyAnimator.Bound
            public final int getValue(View view) {
                return view.getBottom();
            }

            @Override // com.android.systemui.animation.ViewHierarchyAnimator.Bound
            public final void setValue(View view, int i) {
                view.setBottom(i);
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class LEFT extends Bound {
            @Override // com.android.systemui.animation.ViewHierarchyAnimator.Bound
            public final int getValue(View view) {
                return view.getLeft();
            }

            @Override // com.android.systemui.animation.ViewHierarchyAnimator.Bound
            public final void setValue(View view, int i) {
                view.setLeft(i);
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class RIGHT extends Bound {
            @Override // com.android.systemui.animation.ViewHierarchyAnimator.Bound
            public final int getValue(View view) {
                return view.getRight();
            }

            @Override // com.android.systemui.animation.ViewHierarchyAnimator.Bound
            public final void setValue(View view, int i) {
                view.setRight(i);
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class TOP extends Bound {
            @Override // com.android.systemui.animation.ViewHierarchyAnimator.Bound
            public final int getValue(View view) {
                return view.getTop();
            }

            @Override // com.android.systemui.animation.ViewHierarchyAnimator.Bound
            public final void setValue(View view, int i) {
                view.setTop(i);
            }
        }

        static {
            LEFT left = new LEFT("LEFT", 0, R.id.tag_override_left, "left");
            LEFT = left;
            TOP top = new TOP("TOP", 1, R.id.tag_override_top, "top");
            TOP = top;
            RIGHT right = new RIGHT("RIGHT", 2, R.id.tag_override_right, "right");
            RIGHT = right;
            BOTTOM bottom = new BOTTOM("BOTTOM", 3, R.id.tag_override_bottom, "bottom");
            BOTTOM = bottom;
            Bound[] boundArr = {left, top, right, bottom};
            $VALUES = boundArr;
            EnumEntriesKt.enumEntries(boundArr);
        }

        public Bound(String str, int i, int i2, String str2) {
            this.label = str2;
            this.overrideTag = i2;
        }

        public static Bound valueOf(String str) {
            return (Bound) Enum.valueOf(Bound.class, str);
        }

        public static Bound[] values() {
            return (Bound[]) $VALUES.clone();
        }

        public final String getLabel() {
            return this.label;
        }

        public final int getOverrideTag() {
            return this.overrideTag;
        }

        public abstract int getValue(View view);

        public abstract void setValue(View view, int i);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final Integer access$getBound(View view, Bound bound) {
            Companion companion = ViewHierarchyAnimator.Companion;
            Object tag = view.getTag(bound.getOverrideTag());
            if (tag instanceof Integer) {
                return (Integer) tag;
            }
            return null;
        }

        public static void addListener(View view, ViewHierarchyAnimator$Companion$createListener$1 viewHierarchyAnimator$Companion$createListener$1, boolean z, boolean z2, Set set) {
            if (set.contains(view)) {
                return;
            }
            Object tag = view.getTag(R.id.tag_layout_listener);
            if (tag != null && (tag instanceof View.OnLayoutChangeListener)) {
                view.removeOnLayoutChangeListener((View.OnLayoutChangeListener) tag);
            }
            view.addOnLayoutChangeListener(viewHierarchyAnimator$Companion$createListener$1);
            view.setTag(R.id.tag_layout_listener, viewHierarchyAnimator$Companion$createListener$1);
            if (z2 && (view instanceof ViewGroup) && z) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    addListener(viewGroup.getChildAt(i), viewHierarchyAnimator$Companion$createListener$1, true, z2, set);
                }
            }
        }

        public static void addListener$default(Companion companion, View view, ViewHierarchyAnimator$Companion$createListener$1 viewHierarchyAnimator$Companion$createListener$1, boolean z) {
            EmptySet emptySet = EmptySet.INSTANCE;
            companion.getClass();
            addListener(view, viewHierarchyAnimator$Companion$createListener$1, z, true, emptySet);
        }

        public static boolean animateNextUpdate$default(View view, Interpolator interpolator, Set set, int i) {
            Companion companion = ViewHierarchyAnimator.Companion;
            if ((i & 2) != 0) {
                interpolator = ViewHierarchyAnimator.DEFAULT_INTERPOLATOR;
            }
            Interpolator interpolator2 = interpolator;
            boolean z = (i & 8) != 0;
            if ((i & 16) != 0) {
                set = EmptySet.INSTANCE;
            }
            if (!occupiesSpace(view.getVisibility(), view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                return false;
            }
            addListener(view, new ViewHierarchyAnimator$Companion$createListener$1(null, false, interpolator2, 500L, null), true, z, set);
            return true;
        }

        public static void createAndStartFadeInAnimator(View view, long j, long j2, Interpolator interpolator) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 1.0f);
            ofFloat.setStartDelay(j2);
            ofFloat.setDuration(j);
            ofFloat.setInterpolator(interpolator);
            ofFloat.addListener(new ViewHierarchyAnimator$Companion$animateRemoval$2(view, 1));
            Object tag = view.getTag(R.id.tag_alpha_animator);
            ObjectAnimator objectAnimator = tag instanceof ObjectAnimator ? (ObjectAnimator) tag : null;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            view.setTag(R.id.tag_alpha_animator, ofFloat);
            ofFloat.start();
        }

        public static boolean occupiesSpace(int i, int i2, int i3, int i4, int i5) {
            return (i == 8 || i2 == i4 || i3 == i5) ? false : true;
        }

        public static void recursivelyRemoveListener(View view) {
            Object tag = view.getTag(R.id.tag_layout_listener);
            if (tag != null && (tag instanceof View.OnLayoutChangeListener)) {
                view.setTag(R.id.tag_layout_listener, null);
                view.removeOnLayoutChangeListener((View.OnLayoutChangeListener) tag);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    recursivelyRemoveListener(viewGroup.getChildAt(i));
                }
            }
        }

        public static void setBound(View view, Bound bound, int i) {
            view.setTag(bound.getOverrideTag(), Integer.valueOf(i));
            bound.setValue(view, i);
        }

        public static void startAnimation(final View view, final Set set, Map map, Map map2, Interpolator interpolator, long j, final Runnable runnable) {
            ListBuilder listBuilder = new ListBuilder();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                Bound bound = (Bound) it.next();
                listBuilder.add(PropertyValuesHolder.ofInt((Property<?, Integer>) ViewHierarchyAnimator.PROPERTIES.get(bound), ((Number) MapsKt.getValue(bound, map)).intValue(), ((Number) MapsKt.getValue(bound, map2)).intValue()));
            }
            PropertyValuesHolder[] propertyValuesHolderArr = (PropertyValuesHolder[]) listBuilder.build().toArray(new PropertyValuesHolder[0]);
            Object tag = view.getTag(R.id.tag_animator);
            ObjectAnimator objectAnimator = tag instanceof ObjectAnimator ? (ObjectAnimator) tag : null;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, (PropertyValuesHolder[]) Arrays.copyOf(propertyValuesHolderArr, propertyValuesHolderArr.length));
            ofPropertyValuesHolder.setInterpolator(interpolator);
            ofPropertyValuesHolder.setDuration(j);
            ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.animation.ViewHierarchyAnimator$Companion$startAnimation$1
                public boolean cancelled;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    this.cancelled = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    Runnable runnable2;
                    view.setTag(R.id.tag_animator, null);
                    Set set2 = set;
                    View view2 = view;
                    Iterator it2 = set2.iterator();
                    while (it2.hasNext()) {
                        view2.setTag(((ViewHierarchyAnimator.Bound) it2.next()).getOverrideTag(), null);
                    }
                    if (!this.cancelled) {
                        ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                        ViewHierarchyAnimator.Companion.recursivelyRemoveListener(view);
                    }
                    if (this.cancelled || (runnable2 = runnable) == null) {
                        return;
                    }
                    runnable2.run();
                }
            });
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                Bound bound2 = (Bound) it2.next();
                Companion companion = ViewHierarchyAnimator.Companion;
                setBound(view, bound2, ((Number) MapsKt.getValue(bound2, map)).intValue());
            }
            view.setTag(R.id.tag_animator, ofPropertyValuesHolder);
            ofPropertyValuesHolder.start();
        }

        public final boolean animateRemoval(final View view, Interpolator interpolator, final ChipbarCoordinator$animateViewIn$onAnimationEnd$1 chipbarCoordinator$animateViewIn$onAnimationEnd$1) {
            DimenHolder dimenHolder;
            Hotspot hotspot = Hotspot.TOP;
            if (!occupiesSpace(view.getVisibility(), view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                return false;
            }
            final ViewGroup viewGroup = (ViewGroup) view.getParent();
            ViewHierarchyAnimator$Companion$createListener$1 viewHierarchyAnimator$Companion$createListener$1 = new ViewHierarchyAnimator$Companion$createListener$1(null, false, interpolator, 250L, null);
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (!Intrinsics.areEqual(childAt, view)) {
                    Intrinsics.checkNotNull(childAt);
                    addListener$default(this, childAt, viewHierarchyAnimator$Companion$createListener$1, false);
                }
            }
            final boolean z = viewGroup.getChildCount() > 1;
            if (z) {
                viewGroup.removeView(view);
                viewGroup.getOverlay().add(view);
            }
            Runnable runnable = new Runnable() { // from class: com.android.systemui.animation.ViewHierarchyAnimator$Companion$animateRemoval$endRunnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (z) {
                        viewGroup.getOverlay().remove(view);
                    } else {
                        viewGroup.removeView(view);
                    }
                    chipbarCoordinator$animateViewIn$onAnimationEnd$1.run();
                }
            };
            Bound.LEFT left = Bound.LEFT;
            Pair pair = new Pair(left, Integer.valueOf(view.getLeft()));
            Bound.TOP top = Bound.TOP;
            Pair pair2 = new Pair(top, Integer.valueOf(view.getTop()));
            Bound.RIGHT right = Bound.RIGHT;
            Pair pair3 = new Pair(right, Integer.valueOf(view.getRight()));
            Bound.BOTTOM bottom = Bound.BOTTOM;
            Map mapOf = MapsKt.mapOf(pair, pair2, pair3, new Pair(bottom, Integer.valueOf(view.getBottom())));
            int left2 = view.getLeft();
            int top2 = view.getTop();
            int right2 = view.getRight();
            view.getBottom();
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                dimenHolder = new DimenHolder(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            } else {
                dimenHolder = new DimenHolder(0, 0, 0, 0);
            }
            int i2 = top2 - dimenHolder.top;
            Map mapOf2 = MapsKt.mapOf(new Pair(top, Integer.valueOf(i2)), new Pair(bottom, Integer.valueOf(i2)), new Pair(left, Integer.valueOf(left2)), new Pair(right, Integer.valueOf(right2)));
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (view.getLeft() != ((Number) MapsKt.getValue(left, mapOf2)).intValue()) {
                linkedHashSet.add(left);
            }
            if (view.getTop() != ((Number) MapsKt.getValue(top, mapOf2)).intValue()) {
                linkedHashSet.add(top);
            }
            if (view.getRight() != ((Number) MapsKt.getValue(right, mapOf2)).intValue()) {
                linkedHashSet.add(right);
            }
            if (view.getBottom() != ((Number) MapsKt.getValue(bottom, mapOf2)).intValue()) {
                linkedHashSet.add(bottom);
            }
            startAnimation(view, linkedHashSet, mapOf, mapOf2, interpolator, 250L, runnable);
            ViewGroup viewGroup2 = (ViewGroup) view;
            int childCount2 = viewGroup2.getChildCount();
            for (int i3 = 0; i3 < childCount2; i3++) {
                View childAt2 = viewGroup2.getChildAt(i3);
                Bound.LEFT left3 = Bound.LEFT;
                Pair pair4 = new Pair(left3, Integer.valueOf(childAt2.getLeft()));
                Bound.TOP top3 = Bound.TOP;
                Pair pair5 = new Pair(top3, Integer.valueOf(childAt2.getTop()));
                Bound.RIGHT right3 = Bound.RIGHT;
                Pair pair6 = new Pair(right3, Integer.valueOf(childAt2.getRight()));
                Bound.BOTTOM bottom2 = Bound.BOTTOM;
                Map mapOf3 = MapsKt.mapOf(pair4, pair5, pair6, new Pair(bottom2, Integer.valueOf(childAt2.getBottom())));
                int left4 = childAt2.getLeft();
                int top4 = childAt2.getTop();
                int right4 = childAt2.getRight();
                int bottom3 = childAt2.getBottom();
                ((Number) MapsKt.getValue(right3, mapOf2)).intValue();
                ((Number) MapsKt.getValue(left3, mapOf2)).intValue();
                ((Number) MapsKt.getValue(bottom2, mapOf2)).intValue();
                ((Number) MapsKt.getValue(top3, mapOf2)).intValue();
                int i4 = (bottom3 - top4) / 2;
                Map mapOf4 = MapsKt.mapOf(new Pair(left3, Integer.valueOf(left4)), new Pair(top3, Integer.valueOf(-i4)), new Pair(right3, Integer.valueOf(right4)), new Pair(bottom2, Integer.valueOf(i4)));
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                if (childAt2.getLeft() != ((Number) MapsKt.getValue(left3, mapOf2)).intValue()) {
                    linkedHashSet2.add(left3);
                }
                if (childAt2.getTop() != ((Number) MapsKt.getValue(top3, mapOf2)).intValue()) {
                    linkedHashSet2.add(top3);
                }
                if (childAt2.getRight() != ((Number) MapsKt.getValue(right3, mapOf2)).intValue()) {
                    linkedHashSet2.add(right3);
                }
                if (childAt2.getBottom() != ((Number) MapsKt.getValue(bottom2, mapOf2)).intValue()) {
                    linkedHashSet2.add(bottom2);
                }
                startAnimation(childAt2, linkedHashSet2, mapOf3, mapOf4, interpolator, 250L, null);
            }
            final float[] fArr = new float[viewGroup2.getChildCount()];
            int childCount3 = viewGroup2.getChildCount();
            for (int i5 = 0; i5 < childCount3; i5++) {
                fArr[i5] = viewGroup2.getChildAt(i5).getAlpha();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            ofFloat.setInterpolator(Interpolators.ALPHA_OUT);
            ofFloat.setDuration(250 / 2);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.animation.ViewHierarchyAnimator$Companion$animateRemoval$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int childCount4 = ((ViewGroup) view).getChildCount();
                    for (int i6 = 0; i6 < childCount4; i6++) {
                        ((ViewGroup) view).getChildAt(i6).setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue() * fArr[i6]);
                    }
                }
            });
            ofFloat.addListener(new ViewHierarchyAnimator$Companion$animateRemoval$2(view, 0));
            ofFloat.start();
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DimenHolder {
        public final int bottom;
        public final int left;
        public final int right;
        public final int top;

        public DimenHolder(int i, int i2, int i3, int i4) {
            this.left = i;
            this.top = i2;
            this.right = i3;
            this.bottom = i4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DimenHolder)) {
                return false;
            }
            DimenHolder dimenHolder = (DimenHolder) obj;
            return this.left == dimenHolder.left && this.top == dimenHolder.top && this.right == dimenHolder.right && this.bottom == dimenHolder.bottom;
        }

        public final int hashCode() {
            return Integer.hashCode(this.bottom) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.right, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.top, Integer.hashCode(this.left) * 31, 31), 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("DimenHolder(left=");
            sb.append(this.left);
            sb.append(", top=");
            sb.append(this.top);
            sb.append(", right=");
            sb.append(this.right);
            sb.append(", bottom=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.bottom, ")");
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Hotspot {
        public static final /* synthetic */ Hotspot[] $VALUES;
        public static final Hotspot TOP;

        static {
            Hotspot hotspot = new Hotspot("CENTER", 0);
            Hotspot hotspot2 = new Hotspot("LEFT", 1);
            Hotspot hotspot3 = new Hotspot("TOP_LEFT", 2);
            Hotspot hotspot4 = new Hotspot("TOP", 3);
            TOP = hotspot4;
            Hotspot[] hotspotArr = {hotspot, hotspot2, hotspot3, hotspot4, new Hotspot("TOP_RIGHT", 4), new Hotspot("RIGHT", 5), new Hotspot("BOTTOM_RIGHT", 6), new Hotspot("BOTTOM", 7), new Hotspot("BOTTOM_LEFT", 8)};
            $VALUES = hotspotArr;
            EnumEntriesKt.enumEntries(hotspotArr);
        }

        public static Hotspot valueOf(String str) {
            return (Hotspot) Enum.valueOf(Hotspot.class, str);
        }

        public static Hotspot[] values() {
            return (Hotspot[]) $VALUES.clone();
        }
    }

    static {
        Interpolator interpolator = Interpolators.EMPHASIZED;
        DEFAULT_FADE_IN_INTERPOLATOR = Interpolators.ALPHA_IN;
        final Bound.LEFT left = Bound.LEFT;
        final String label = left.getLabel();
        Pair pair = new Pair(left, new IntProperty(label) { // from class: com.android.systemui.animation.ViewHierarchyAnimator$Companion$createViewProperty$1
            @Override // android.util.Property
            public final Integer get(Object obj) {
                View view = (View) obj;
                ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                Integer access$getBound = ViewHierarchyAnimator.Companion.access$getBound(view, ViewHierarchyAnimator.Bound.this);
                return Integer.valueOf(access$getBound != null ? access$getBound.intValue() : ViewHierarchyAnimator.Bound.this.getValue(view));
            }

            @Override // android.util.IntProperty
            public final void setValue(Object obj, int i) {
                ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                ViewHierarchyAnimator.Companion.setBound((View) obj, ViewHierarchyAnimator.Bound.this, i);
            }
        });
        final Bound.TOP top = Bound.TOP;
        final String label2 = top.getLabel();
        Pair pair2 = new Pair(top, new IntProperty(label2) { // from class: com.android.systemui.animation.ViewHierarchyAnimator$Companion$createViewProperty$1
            @Override // android.util.Property
            public final Integer get(Object obj) {
                View view = (View) obj;
                ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                Integer access$getBound = ViewHierarchyAnimator.Companion.access$getBound(view, ViewHierarchyAnimator.Bound.this);
                return Integer.valueOf(access$getBound != null ? access$getBound.intValue() : ViewHierarchyAnimator.Bound.this.getValue(view));
            }

            @Override // android.util.IntProperty
            public final void setValue(Object obj, int i) {
                ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                ViewHierarchyAnimator.Companion.setBound((View) obj, ViewHierarchyAnimator.Bound.this, i);
            }
        });
        final Bound.RIGHT right = Bound.RIGHT;
        final String label3 = right.getLabel();
        Pair pair3 = new Pair(right, new IntProperty(label3) { // from class: com.android.systemui.animation.ViewHierarchyAnimator$Companion$createViewProperty$1
            @Override // android.util.Property
            public final Integer get(Object obj) {
                View view = (View) obj;
                ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                Integer access$getBound = ViewHierarchyAnimator.Companion.access$getBound(view, ViewHierarchyAnimator.Bound.this);
                return Integer.valueOf(access$getBound != null ? access$getBound.intValue() : ViewHierarchyAnimator.Bound.this.getValue(view));
            }

            @Override // android.util.IntProperty
            public final void setValue(Object obj, int i) {
                ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                ViewHierarchyAnimator.Companion.setBound((View) obj, ViewHierarchyAnimator.Bound.this, i);
            }
        });
        final Bound.BOTTOM bottom = Bound.BOTTOM;
        final String label4 = bottom.getLabel();
        PROPERTIES = MapsKt.mapOf(pair, pair2, pair3, new Pair(bottom, new IntProperty(label4) { // from class: com.android.systemui.animation.ViewHierarchyAnimator$Companion$createViewProperty$1
            @Override // android.util.Property
            public final Integer get(Object obj) {
                View view = (View) obj;
                ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                Integer access$getBound = ViewHierarchyAnimator.Companion.access$getBound(view, ViewHierarchyAnimator.Bound.this);
                return Integer.valueOf(access$getBound != null ? access$getBound.intValue() : ViewHierarchyAnimator.Bound.this.getValue(view));
            }

            @Override // android.util.IntProperty
            public final void setValue(Object obj, int i) {
                ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                ViewHierarchyAnimator.Companion.setBound((View) obj, ViewHierarchyAnimator.Bound.this, i);
            }
        }));
    }
}
