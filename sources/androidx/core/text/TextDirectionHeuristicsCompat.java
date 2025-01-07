package androidx.core.text;

import com.android.app.viewcapture.data.ViewNode;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicInternal LTR = new TextDirectionHeuristicInternal(null, false);
    public static final TextDirectionHeuristicInternal RTL = new TextDirectionHeuristicInternal(null, true);
    public static final TextDirectionHeuristicInternal FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, false);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FirstStrong {
        public static final FirstStrong INSTANCE = new FirstStrong();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TextDirectionHeuristicInternal {
        public final FirstStrong mAlgorithm;
        public final boolean mDefaultIsRtl;

        public TextDirectionHeuristicInternal(FirstStrong firstStrong, boolean z) {
            this.mAlgorithm = firstStrong;
            this.mDefaultIsRtl = z;
        }

        public final boolean defaultIsRtl() {
            return this.mDefaultIsRtl;
        }

        public final boolean isRtl(int i, CharSequence charSequence) {
            if (charSequence != null && i >= 0) {
                String str = (String) charSequence;
                if (str.length() - i >= 0) {
                    if (this.mAlgorithm == null) {
                        return defaultIsRtl();
                    }
                    char c = 2;
                    for (int i2 = 0; i2 < i && c == 2; i2++) {
                        byte directionality = Character.getDirectionality(str.charAt(i2));
                        TextDirectionHeuristicInternal textDirectionHeuristicInternal = TextDirectionHeuristicsCompat.LTR;
                        if (directionality != 0) {
                            if (directionality != 1 && directionality != 2) {
                                switch (directionality) {
                                    case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                                    case 15:
                                        break;
                                    case 16:
                                    case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                                        break;
                                    default:
                                        c = 2;
                                        break;
                                }
                            }
                            c = 0;
                        }
                        c = 1;
                    }
                    if (c == 0) {
                        return true;
                    }
                    if (c != 1) {
                        return defaultIsRtl();
                    }
                    return false;
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
