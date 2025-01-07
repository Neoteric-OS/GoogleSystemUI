package androidx.compose.ui.text.android.selection;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.android.CharSequenceCharacterIterator;
import androidx.emoji2.text.EmojiCompat;
import java.lang.Character;
import java.text.BreakIterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WordIterator {
    public final CharSequence charSequence;
    public final int end;
    public final BreakIterator iterator;
    public final int start;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static boolean isPunctuation$ui_text_release(int i) {
            int type = Character.getType(i);
            return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
        }
    }

    public WordIterator(CharSequence charSequence, int i, Locale locale) {
        this.charSequence = charSequence;
        if (charSequence.length() < 0) {
            throw new IllegalArgumentException("input start index is outside the CharSequence");
        }
        if (i < 0 || i > charSequence.length()) {
            throw new IllegalArgumentException("input end index is outside the CharSequence");
        }
        BreakIterator wordInstance = BreakIterator.getWordInstance(locale);
        this.iterator = wordInstance;
        this.start = Math.max(0, -50);
        this.end = Math.min(charSequence.length(), i + 50);
        wordInstance.setText(new CharSequenceCharacterIterator(i, charSequence));
    }

    public final void checkOffsetIsValid(int i) {
        int i2 = this.start;
        int i3 = this.end;
        if (i > i3 || i2 > i) {
            throw new IllegalArgumentException(BackEventCompat$$ExternalSyntheticOutline0.m(ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "Invalid offset: ", ". Valid range is [", " , "), i3, ']').toString());
        }
    }

    public final boolean isAfterLetterOrDigitOrEmoji(int i) {
        int i2 = this.start + 1;
        if (i > this.end || i2 > i) {
            return false;
        }
        if (Character.isLetterOrDigit(Character.codePointBefore(this.charSequence, i))) {
            return true;
        }
        int i3 = i - 1;
        if (Character.isSurrogate(this.charSequence.charAt(i3))) {
            return true;
        }
        if (!EmojiCompat.isConfigured()) {
            return false;
        }
        EmojiCompat emojiCompat = EmojiCompat.get();
        return emojiCompat.getLoadState() == 1 && emojiCompat.getEmojiStart(i3, this.charSequence) != -1;
    }

    public final boolean isAfterPunctuation(int i) {
        int i2 = this.start + 1;
        if (i > this.end || i2 > i) {
            return false;
        }
        return Companion.isPunctuation$ui_text_release(Character.codePointBefore(this.charSequence, i));
    }

    public final boolean isBoundary(int i) {
        checkOffsetIsValid(i);
        if (this.iterator.isBoundary(i) && (!isOnLetterOrDigitOrEmoji(i) || !isOnLetterOrDigitOrEmoji(i - 1) || !isOnLetterOrDigitOrEmoji(i + 1))) {
            if (i <= 0 || i >= this.charSequence.length() - 1) {
                return true;
            }
            if (!isHiraganaKatakanaBoundary(i) && !isHiraganaKatakanaBoundary(i + 1)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isHiraganaKatakanaBoundary(int i) {
        int i2 = i - 1;
        Character.UnicodeBlock of = Character.UnicodeBlock.of(this.charSequence.charAt(i2));
        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.HIRAGANA;
        return (Intrinsics.areEqual(of, unicodeBlock) && Intrinsics.areEqual(Character.UnicodeBlock.of(this.charSequence.charAt(i)), Character.UnicodeBlock.KATAKANA)) || (Intrinsics.areEqual(Character.UnicodeBlock.of(this.charSequence.charAt(i)), unicodeBlock) && Intrinsics.areEqual(Character.UnicodeBlock.of(this.charSequence.charAt(i2)), Character.UnicodeBlock.KATAKANA));
    }

    public final boolean isOnLetterOrDigitOrEmoji(int i) {
        if (i >= this.end || this.start > i) {
            return false;
        }
        if (Character.isLetterOrDigit(Character.codePointAt(this.charSequence, i)) || Character.isSurrogate(this.charSequence.charAt(i))) {
            return true;
        }
        if (!EmojiCompat.isConfigured()) {
            return false;
        }
        EmojiCompat emojiCompat = EmojiCompat.get();
        return emojiCompat.getLoadState() == 1 && emojiCompat.getEmojiStart(i, this.charSequence) != -1;
    }

    public final boolean isOnPunctuation(int i) {
        if (i >= this.end || this.start > i) {
            return false;
        }
        return Companion.isPunctuation$ui_text_release(Character.codePointAt(this.charSequence, i));
    }

    public final int nextBoundary(int i) {
        checkOffsetIsValid(i);
        int following = this.iterator.following(i);
        return (isOnLetterOrDigitOrEmoji(following + (-1)) && isOnLetterOrDigitOrEmoji(following) && !isHiraganaKatakanaBoundary(following)) ? nextBoundary(following) : following;
    }

    public final int prevBoundary(int i) {
        checkOffsetIsValid(i);
        int preceding = this.iterator.preceding(i);
        return (isOnLetterOrDigitOrEmoji(preceding) && isAfterLetterOrDigitOrEmoji(preceding) && !isHiraganaKatakanaBoundary(preceding)) ? prevBoundary(preceding) : preceding;
    }
}
