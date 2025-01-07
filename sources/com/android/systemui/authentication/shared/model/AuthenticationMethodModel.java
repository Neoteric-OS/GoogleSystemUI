package com.android.systemui.authentication.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AuthenticationMethodModel {
    public final boolean isSecure;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class None extends AuthenticationMethodModel {
        public static final None INSTANCE = new None(false);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof None);
        }

        public final int hashCode() {
            return -42893011;
        }

        public final String toString() {
            return "None";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Password extends AuthenticationMethodModel {
        public static final Password INSTANCE = new Password(true);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Password);
        }

        public final int hashCode() {
            return -302806512;
        }

        public final String toString() {
            return "Password";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Pattern extends AuthenticationMethodModel {
        public static final Pattern INSTANCE = new Pattern(true);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Pattern);
        }

        public final int hashCode() {
            return -840115845;
        }

        public final String toString() {
            return "Pattern";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Pin extends AuthenticationMethodModel {
        public static final Pin INSTANCE = new Pin(true);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Pin);
        }

        public final int hashCode() {
            return 829902080;
        }

        public final String toString() {
            return "Pin";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Sim extends AuthenticationMethodModel {
        public static final Sim INSTANCE = new Sim(true);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Sim);
        }

        public final int hashCode() {
            return 829904962;
        }

        public final String toString() {
            return "Sim";
        }
    }

    public AuthenticationMethodModel(boolean z) {
        this.isSecure = z;
    }
}
