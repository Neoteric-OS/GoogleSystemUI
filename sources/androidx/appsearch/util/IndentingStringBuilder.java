package androidx.appsearch.util;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IndentingStringBuilder {
    public final StringBuilder mStringBuilder = new StringBuilder();
    public boolean mIndentNext = false;
    public int mIndentLevel = 0;

    public final void applyIndentToString(String str) {
        int indexOf = str.indexOf("\n");
        StringBuilder sb = this.mStringBuilder;
        if (indexOf == 0) {
            sb.append("\n");
            this.mIndentNext = true;
            if (str.length() > 1) {
                applyIndentToString(str.substring(indexOf + 1));
                return;
            }
            return;
        }
        if (indexOf >= 1) {
            applyIndentToString(str.substring(0, indexOf));
            sb.append("\n");
            this.mIndentNext = true;
            int i = indexOf + 1;
            if (str.length() > i) {
                applyIndentToString(str.substring(i));
                return;
            }
            return;
        }
        if (this.mIndentNext) {
            for (int i2 = 0; i2 < this.mIndentLevel; i2++) {
                sb.append("  ");
            }
            this.mIndentNext = false;
        }
        sb.append(str);
    }

    public final void decreaseIndentLevel() {
        int i = this.mIndentLevel;
        if (i == 0) {
            throw new IllegalStateException("Cannot set indent level below 0.");
        }
        this.mIndentLevel = i - 1;
    }

    public final void increaseIndentLevel() {
        this.mIndentLevel++;
    }

    public final String toString() {
        return this.mStringBuilder.toString();
    }
}
