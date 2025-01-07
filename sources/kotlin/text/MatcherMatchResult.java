package kotlin.text;

import java.util.regex.Matcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MatcherMatchResult {
    public MatcherMatchResult$groupValues$1 groupValues_;
    public final CharSequence input;
    public final Matcher matcher;

    public MatcherMatchResult(Matcher matcher, CharSequence charSequence) {
        this.matcher = matcher;
        this.input = charSequence;
    }
}
