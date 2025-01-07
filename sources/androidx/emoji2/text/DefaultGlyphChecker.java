package androidx.emoji2.text;

import android.text.TextPaint;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultGlyphChecker {
    public static final ThreadLocal sStringBuilder = new ThreadLocal();
    public final TextPaint mTextPaint;

    public DefaultGlyphChecker() {
        TextPaint textPaint = new TextPaint();
        this.mTextPaint = textPaint;
        textPaint.setTextSize(10.0f);
    }
}
