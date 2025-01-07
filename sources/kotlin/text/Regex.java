package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Regex implements Serializable {
    private Set _options;
    private final Pattern nativePattern;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Serialized implements Serializable {
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        public Serialized(String str, int i) {
            this.pattern = str;
            this.flags = i;
        }

        private final Object readResolve() {
            return new Regex(Pattern.compile(this.pattern, this.flags));
        }
    }

    public Regex(Pattern pattern) {
        this.nativePattern = pattern;
    }

    private final Object writeReplace() {
        return new Serialized(this.nativePattern.pattern(), this.nativePattern.flags());
    }

    public final MatcherMatchResult matchEntire(CharSequence charSequence) {
        Matcher matcher = this.nativePattern.matcher(charSequence);
        if (matcher.matches()) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public final boolean matches(CharSequence charSequence) {
        return this.nativePattern.matcher(charSequence).matches();
    }

    public final String replace(CharSequence charSequence, Function1 function1) {
        Matcher matcher = this.nativePattern.matcher(charSequence);
        MatcherMatchResult matcherMatchResult = !matcher.find(0) ? null : new MatcherMatchResult(matcher, charSequence);
        if (matcherMatchResult == null) {
            return charSequence.toString();
        }
        String str = (String) charSequence;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        do {
            Matcher matcher2 = matcherMatchResult.matcher;
            sb.append((CharSequence) str, i, RangesKt.until(matcher2.start(), matcher2.end()).first);
            sb.append((CharSequence) function1.invoke(matcherMatchResult));
            Matcher matcher3 = matcherMatchResult.matcher;
            i = RangesKt.until(matcher3.start(), matcher3.end()).last + 1;
            int end = matcherMatchResult.matcher.end() + (matcherMatchResult.matcher.end() != matcherMatchResult.matcher.start() ? 0 : 1);
            if (end <= ((String) matcherMatchResult.input).length()) {
                Matcher matcher4 = matcherMatchResult.matcher.pattern().matcher(matcherMatchResult.input);
                matcherMatchResult = !matcher4.find(end) ? null : new MatcherMatchResult(matcher4, matcherMatchResult.input);
            } else {
                matcherMatchResult = null;
            }
            if (i >= length) {
                break;
            }
        } while (matcherMatchResult != null);
        if (i < length) {
            sb.append((CharSequence) str, i, length);
        }
        return sb.toString();
    }

    public final List split(CharSequence charSequence) {
        String str;
        int i = 0;
        StringsKt.requireNonNegativeLimit(0);
        Matcher matcher = this.nativePattern.matcher(charSequence);
        if (!matcher.find()) {
            return Collections.singletonList(charSequence.toString());
        }
        ArrayList arrayList = new ArrayList(10);
        do {
            str = (String) charSequence;
            arrayList.add(str.subSequence(i, matcher.start()).toString());
            i = matcher.end();
        } while (matcher.find());
        arrayList.add(str.subSequence(i, str.length()).toString());
        return arrayList;
    }

    public final String toString() {
        return this.nativePattern.toString();
    }

    public Regex(String str) {
        this(Pattern.compile(str));
    }

    public final String replace(CharSequence charSequence) {
        return this.nativePattern.matcher(charSequence).replaceAll("");
    }
}
