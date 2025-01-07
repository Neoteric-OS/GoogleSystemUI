package com.android.systemui.volume.panel.component.volume.ui.viewmodel;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SlidersExpandableViewModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Expandable implements SlidersExpandableViewModel {
        public final boolean isExpanded;

        public Expandable(boolean z) {
            this.isExpanded = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Expandable) && this.isExpanded == ((Expandable) obj).isExpanded;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isExpanded);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("Expandable(isExpanded="), this.isExpanded, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Fixed implements SlidersExpandableViewModel {
        public static final Fixed INSTANCE = new Fixed();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Fixed);
        }

        public final int hashCode() {
            return -1830679804;
        }

        public final String toString() {
            return "Fixed";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Unavailable implements SlidersExpandableViewModel {
        public static final Unavailable INSTANCE = new Unavailable();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Unavailable);
        }

        public final int hashCode() {
            return 1187254240;
        }

        public final String toString() {
            return "Unavailable";
        }
    }
}
