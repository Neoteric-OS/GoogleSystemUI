package androidx.appsearch.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PropertyPath implements Iterable {
    public final List mPathList;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PathSegment {
        public final int mPropertyIndex;
        public final String mPropertyName;

        public PathSegment(String str, int i) {
            str.getClass();
            this.mPropertyName = str;
            this.mPropertyIndex = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof PathSegment)) {
                return false;
            }
            PathSegment pathSegment = (PathSegment) obj;
            return this.mPropertyIndex == pathSegment.mPropertyIndex && this.mPropertyName.equals(pathSegment.mPropertyName);
        }

        public final int hashCode() {
            return Objects.hash(this.mPropertyName, Integer.valueOf(this.mPropertyIndex));
        }

        public final String toString() {
            String str = this.mPropertyName;
            int i = this.mPropertyIndex;
            if (i == -1) {
                return str;
            }
            return str + "[" + i + "]";
        }
    }

    public PropertyPath(String str) {
        str.getClass();
        this.mPathList = new ArrayList();
        try {
            recursivePathScan(str);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + ": " + str);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof PropertyPath)) {
            return Objects.equals(this.mPathList, ((PropertyPath) obj).mPathList);
        }
        return false;
    }

    public final int hashCode() {
        List list = this.mPathList;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.mPathList.iterator();
    }

    public final void recursivePathScan(String str) {
        boolean z;
        String substring;
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt == ']') {
                throw new IllegalArgumentException("Malformed path (no starting '[')");
            }
            if (charAt == '[' || charAt == '.') {
                z = charAt == '[';
                if (i != 0 || str.isEmpty()) {
                    throw new IllegalArgumentException("Malformed path (blank property name)");
                }
                if (i == -1) {
                    this.mPathList.add(new PathSegment(str, -1));
                    return;
                }
                if (z) {
                    String substring2 = str.substring(0, i);
                    int indexOf = str.indexOf(93, i);
                    if (indexOf == -1) {
                        throw new IllegalArgumentException("Malformed path (no ending ']')");
                    }
                    int i2 = indexOf + 1;
                    if (i2 < str.length() && str.charAt(i2) != '.') {
                        throw new IllegalArgumentException("Malformed path (']' not followed by '.'): ".concat(str));
                    }
                    String substring3 = str.substring(i + 1, indexOf);
                    try {
                        int parseInt = Integer.parseInt(substring3);
                        if (parseInt < 0) {
                            throw new IllegalArgumentException("Malformed path (path index less than 0)");
                        }
                        this.mPathList.add(new PathSegment(substring2, parseInt));
                        substring = i2 < str.length() ? str.substring(indexOf + 2) : null;
                        if (substring == null) {
                            return;
                        }
                    } catch (NumberFormatException unused) {
                        throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Malformed path (\"", substring3, "\" as path index)"));
                    }
                } else {
                    String substring4 = str.substring(0, i);
                    substring = str.substring(i + 1);
                    this.mPathList.add(new PathSegment(substring4, -1));
                }
                recursivePathScan(substring);
                return;
            }
            i++;
        }
        z = false;
        i = -1;
        if (i != 0) {
        }
        throw new IllegalArgumentException("Malformed path (blank property name)");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ((ArrayList) this.mPathList).size(); i++) {
            sb.append(((PathSegment) ((ArrayList) this.mPathList).get(i)).toString());
            if (i < ((ArrayList) this.mPathList).size() - 1) {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}
