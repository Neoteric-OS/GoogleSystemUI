package androidx.appsearch.builtintypes.properties;

import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Keyword {
    public final String mAsText;
    public String mNamespace = "";
    public String mId = "";

    public Keyword(String str) {
        str.getClass();
        this.mAsText = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Keyword.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.mAsText, ((Keyword) obj).mAsText);
    }

    public final int hashCode() {
        return Objects.hashCode(this.mAsText);
    }
}
