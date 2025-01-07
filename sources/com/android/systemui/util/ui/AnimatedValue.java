package com.android.systemui.util.ui;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface AnimatedValue {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Animating implements AnimatedValue {
        public final Lambda onStopAnimating;
        public final Object value;

        /* JADX WARN: Multi-variable type inference failed */
        public Animating(Object obj, Function0 function0) {
            this.value = obj;
            this.onStopAnimating = (Lambda) function0;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Animating)) {
                return false;
            }
            Animating animating = (Animating) obj;
            return Intrinsics.areEqual(this.value, animating.value) && this.onStopAnimating.equals(animating.onStopAnimating);
        }

        public final int hashCode() {
            Object obj = this.value;
            return this.onStopAnimating.hashCode() + ((obj == null ? 0 : obj.hashCode()) * 31);
        }

        public final String toString() {
            return "Animating(value=" + this.value + ", onStopAnimating=" + this.onStopAnimating + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotAnimating implements AnimatedValue {
        public final Object value;

        public NotAnimating(Object obj) {
            this.value = obj;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof NotAnimating) && Intrinsics.areEqual(this.value, ((NotAnimating) obj).value);
        }

        public final int hashCode() {
            Object obj = this.value;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        public final String toString() {
            return "NotAnimating(value=" + this.value + ")";
        }
    }
}
