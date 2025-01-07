package com.airbnb.lottie.model;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyPath {
    public static final KeyPath COMPOSITION = new KeyPath("COMPOSITION");
    public final List keys;
    public KeyPathElement resolvedElement;

    public KeyPath(String... strArr) {
        this.keys = Arrays.asList(strArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || KeyPath.class != obj.getClass()) {
            return false;
        }
        KeyPath keyPath = (KeyPath) obj;
        if (!this.keys.equals(keyPath.keys)) {
            return false;
        }
        KeyPathElement keyPathElement = this.resolvedElement;
        return keyPathElement != null ? keyPathElement.equals(keyPath.resolvedElement) : keyPath.resolvedElement == null;
    }

    public final boolean fullyResolvesTo(int i, String str) {
        if (i >= this.keys.size()) {
            return false;
        }
        boolean z = i == this.keys.size() - 1;
        String str2 = (String) this.keys.get(i);
        if (!str2.equals("**")) {
            boolean z2 = str2.equals(str) || str2.equals("*");
            if (!z) {
                if (i != this.keys.size() - 2) {
                    return false;
                }
                List list = this.keys;
                if (!((String) list.get(list.size() - 1)).equals("**")) {
                    return false;
                }
            }
            return z2;
        }
        if (z || !((String) this.keys.get(i + 1)).equals(str)) {
            if (z) {
                return true;
            }
            int i2 = i + 1;
            if (i2 < this.keys.size() - 1) {
                return false;
            }
            return ((String) this.keys.get(i2)).equals(str);
        }
        if (i != this.keys.size() - 2) {
            if (i != this.keys.size() - 3) {
                return false;
            }
            List list2 = this.keys;
            if (!((String) list2.get(list2.size() - 1)).equals("**")) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int hashCode = this.keys.hashCode() * 31;
        KeyPathElement keyPathElement = this.resolvedElement;
        return hashCode + (keyPathElement != null ? keyPathElement.hashCode() : 0);
    }

    public final int incrementDepthBy(int i, String str) {
        if ("__container".equals(str)) {
            return 0;
        }
        if (((String) this.keys.get(i)).equals("**")) {
            return (i != this.keys.size() - 1 && ((String) this.keys.get(i + 1)).equals(str)) ? 2 : 0;
        }
        return 1;
    }

    public final boolean matches(int i, String str) {
        if ("__container".equals(str)) {
            return true;
        }
        if (i >= this.keys.size()) {
            return false;
        }
        return ((String) this.keys.get(i)).equals(str) || ((String) this.keys.get(i)).equals("**") || ((String) this.keys.get(i)).equals("*");
    }

    public final boolean propagateToChildren(int i, String str) {
        return "__container".equals(str) || i < this.keys.size() - 1 || ((String) this.keys.get(i)).equals("**");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("KeyPath{keys=");
        sb.append(this.keys);
        sb.append(",resolved=");
        return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.resolvedElement != null, '}');
    }

    public KeyPath(KeyPath keyPath) {
        this.keys = new ArrayList(keyPath.keys);
        this.resolvedElement = keyPath.resolvedElement;
    }
}
