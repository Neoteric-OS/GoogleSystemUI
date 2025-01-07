package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SwipeDirection {
    public static final /* synthetic */ SwipeDirection[] $VALUES;
    public static final SwipeDirection Down;
    public static final SwipeDirection End;
    public static final SwipeDirection Left;
    public static final SwipeDirection Start;
    public static final SwipeDirection Up;
    private final Function1 resolve;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Resolved {
        public static final /* synthetic */ Resolved[] $VALUES;
        public static final Resolved Down;
        public static final Resolved Left;
        public static final Resolved Right;
        public static final Resolved Up;
        private final Orientation orientation;

        static {
            Orientation orientation = Orientation.Vertical;
            Resolved resolved = new Resolved("Up", 0, orientation);
            Up = resolved;
            Resolved resolved2 = new Resolved("Down", 1, orientation);
            Down = resolved2;
            Orientation orientation2 = Orientation.Horizontal;
            Resolved resolved3 = new Resolved("Left", 2, orientation2);
            Left = resolved3;
            Resolved resolved4 = new Resolved("Right", 3, orientation2);
            Right = resolved4;
            Resolved[] resolvedArr = {resolved, resolved2, resolved3, resolved4};
            $VALUES = resolvedArr;
            EnumEntriesKt.enumEntries(resolvedArr);
        }

        public Resolved(String str, int i, Orientation orientation) {
            this.orientation = orientation;
        }

        public static Resolved valueOf(String str) {
            return (Resolved) Enum.valueOf(Resolved.class, str);
        }

        public static Resolved[] values() {
            return (Resolved[]) $VALUES.clone();
        }

        public final Orientation getOrientation() {
            return this.orientation;
        }
    }

    static {
        SwipeDirection swipeDirection = new SwipeDirection("Up", 0, new Function1() { // from class: com.android.compose.animation.scene.SwipeDirection.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Resolved.Up;
            }
        });
        Up = swipeDirection;
        SwipeDirection swipeDirection2 = new SwipeDirection("Down", 1, new Function1() { // from class: com.android.compose.animation.scene.SwipeDirection.2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Resolved.Down;
            }
        });
        Down = swipeDirection2;
        SwipeDirection swipeDirection3 = new SwipeDirection("Left", 2, new Function1() { // from class: com.android.compose.animation.scene.SwipeDirection.3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Resolved.Left;
            }
        });
        Left = swipeDirection3;
        SwipeDirection swipeDirection4 = new SwipeDirection("Right", 3, new Function1() { // from class: com.android.compose.animation.scene.SwipeDirection.4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Resolved.Right;
            }
        });
        SwipeDirection swipeDirection5 = new SwipeDirection("Start", 4, new Function1() { // from class: com.android.compose.animation.scene.SwipeDirection.5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((LayoutDirection) obj) == LayoutDirection.Ltr ? Resolved.Left : Resolved.Right;
            }
        });
        Start = swipeDirection5;
        SwipeDirection swipeDirection6 = new SwipeDirection("End", 5, new Function1() { // from class: com.android.compose.animation.scene.SwipeDirection.6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((LayoutDirection) obj) == LayoutDirection.Ltr ? Resolved.Right : Resolved.Left;
            }
        });
        End = swipeDirection6;
        SwipeDirection[] swipeDirectionArr = {swipeDirection, swipeDirection2, swipeDirection3, swipeDirection4, swipeDirection5, swipeDirection6};
        $VALUES = swipeDirectionArr;
        EnumEntriesKt.enumEntries(swipeDirectionArr);
    }

    public SwipeDirection(String str, int i, Function1 function1) {
        this.resolve = function1;
    }

    public static SwipeDirection valueOf(String str) {
        return (SwipeDirection) Enum.valueOf(SwipeDirection.class, str);
    }

    public static SwipeDirection[] values() {
        return (SwipeDirection[]) $VALUES.clone();
    }

    public final Function1 getResolve$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
        return this.resolve;
    }
}
