package com.android.systemui.shared.animation;

import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.shared.animation.UnfoldConstantTranslateAnimator;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.EmptyList;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldConstantTranslateAnimator implements UnfoldTransitionProgressProvider.TransitionProgressListener {
    public final UnfoldTransitionProgressProvider progressProvider;
    public ViewGroup rootView;
    public float translationMax;
    public final Set viewsIdToTranslate;
    public List viewsToTranslate = EmptyList.INSTANCE;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Direction {
        public static final /* synthetic */ Direction[] $VALUES;
        public static final Direction END;
        public static final Direction START;
        private final float multiplier;

        static {
            Direction direction = new Direction(-1.0f, 0, "START");
            START = direction;
            Direction direction2 = new Direction(1.0f, 1, "END");
            END = direction2;
            Direction[] directionArr = {direction, direction2};
            $VALUES = directionArr;
            EnumEntriesKt.enumEntries(directionArr);
        }

        public Direction(float f, int i, String str) {
            this.multiplier = f;
        }

        public static Direction valueOf(String str) {
            return (Direction) Enum.valueOf(Direction.class, str);
        }

        public static Direction[] values() {
            return (Direction[]) $VALUES.clone();
        }

        public final float getMultiplier() {
            return this.multiplier;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ViewToTranslate {
        public final Direction direction;
        public final Function2 translateFunc;
        public final WeakReference view;

        public ViewToTranslate(WeakReference weakReference, Direction direction, Function2 function2) {
            this.view = weakReference;
            this.direction = direction;
            this.translateFunc = function2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ViewToTranslate)) {
                return false;
            }
            ViewToTranslate viewToTranslate = (ViewToTranslate) obj;
            return Intrinsics.areEqual(this.view, viewToTranslate.view) && this.direction == viewToTranslate.direction && Intrinsics.areEqual(this.translateFunc, viewToTranslate.translateFunc);
        }

        public final int hashCode() {
            return this.translateFunc.hashCode() + ((this.direction.hashCode() + (this.view.hashCode() * 31)) * 31);
        }

        public final String toString() {
            return "ViewToTranslate(view=" + this.view + ", direction=" + this.direction + ", translateFunc=" + this.translateFunc + ")";
        }
    }

    public UnfoldConstantTranslateAnimator(Set set, UnfoldTransitionProgressProvider unfoldTransitionProgressProvider) {
        this.viewsIdToTranslate = set;
        this.progressProvider = unfoldTransitionProgressProvider;
    }

    public final void init(ViewGroup viewGroup, float f) {
        if (this.rootView == null) {
            this.progressProvider.addCallback(this);
        }
        this.rootView = viewGroup;
        this.translationMax = f;
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionFinished() {
        translateViews$1(1.0f);
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionProgress(float f) {
        translateViews$1(f);
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionStarted() {
        final ViewGroup viewGroup = this.rootView;
        if (viewGroup == null) {
            viewGroup = null;
        }
        this.viewsToTranslate = SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(this.viewsIdToTranslate), new Function1() { // from class: com.android.systemui.shared.animation.UnfoldConstantTranslateAnimator$registerViewsForAnimation$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return (Boolean) ((UnfoldConstantTranslateAnimator.ViewIdToTranslate) obj).shouldBeAnimated.invoke();
            }
        }), new Function1() { // from class: com.android.systemui.shared.animation.UnfoldConstantTranslateAnimator$registerViewsForAnimation$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                UnfoldConstantTranslateAnimator.ViewIdToTranslate viewIdToTranslate = (UnfoldConstantTranslateAnimator.ViewIdToTranslate) obj;
                View findViewById = viewGroup.findViewById(viewIdToTranslate.viewId);
                if (findViewById != null) {
                    return new UnfoldConstantTranslateAnimator.ViewToTranslate(new WeakReference(findViewById), viewIdToTranslate.direction, viewIdToTranslate.translateFunc);
                }
                return null;
            }
        }));
    }

    public final void translateViews$1(float f) {
        float f2 = (f - 1.0f) * this.translationMax;
        ViewGroup viewGroup = this.rootView;
        if (viewGroup == null) {
            viewGroup = null;
        }
        int i = viewGroup.getLayoutDirection() == 1 ? -1 : 1;
        for (ViewToTranslate viewToTranslate : this.viewsToTranslate) {
            View view = (View) viewToTranslate.view.get();
            if (view != null) {
                viewToTranslate.translateFunc.invoke(view, Float.valueOf(viewToTranslate.direction.getMultiplier() * f2 * i));
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ViewIdToTranslate {
        public final Direction direction;
        public final Function0 shouldBeAnimated;
        public final Function2 translateFunc;
        public final int viewId;

        public ViewIdToTranslate(int i, Direction direction, Function0 function0, Function2 function2) {
            this.viewId = i;
            this.direction = direction;
            this.shouldBeAnimated = function0;
            this.translateFunc = function2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ViewIdToTranslate)) {
                return false;
            }
            ViewIdToTranslate viewIdToTranslate = (ViewIdToTranslate) obj;
            return this.viewId == viewIdToTranslate.viewId && this.direction == viewIdToTranslate.direction && Intrinsics.areEqual(this.shouldBeAnimated, viewIdToTranslate.shouldBeAnimated) && Intrinsics.areEqual(this.translateFunc, viewIdToTranslate.translateFunc);
        }

        public final int hashCode() {
            return this.translateFunc.hashCode() + ((this.shouldBeAnimated.hashCode() + ((this.direction.hashCode() + (Integer.hashCode(this.viewId) * 31)) * 31)) * 31);
        }

        public final String toString() {
            return "ViewIdToTranslate(viewId=" + this.viewId + ", direction=" + this.direction + ", shouldBeAnimated=" + this.shouldBeAnimated + ", translateFunc=" + this.translateFunc + ")";
        }

        public /* synthetic */ ViewIdToTranslate(int i, Direction direction, Function0 function0) {
            this(i, direction, function0, new Function2() { // from class: com.android.systemui.shared.animation.UnfoldConstantTranslateAnimator.ViewIdToTranslate.2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((View) obj).setTranslationX(((Number) obj2).floatValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }
}
