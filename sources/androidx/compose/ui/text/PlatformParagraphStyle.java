package androidx.compose.ui.text;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PlatformParagraphStyle {
    public static final PlatformParagraphStyle Default = new PlatformParagraphStyle(false);
    public final boolean includeFontPadding;

    public PlatformParagraphStyle() {
        this.includeFontPadding = false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlatformParagraphStyle) {
            return this.includeFontPadding == ((PlatformParagraphStyle) obj).includeFontPadding;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(0) + (Boolean.hashCode(this.includeFontPadding) * 31);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("PlatformParagraphStyle(includeFontPadding="), this.includeFontPadding, ", emojiSupportMatch=EmojiSupportMatch.Default)");
    }

    public PlatformParagraphStyle(boolean z) {
        this.includeFontPadding = z;
    }
}
