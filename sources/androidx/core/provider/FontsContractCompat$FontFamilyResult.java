package androidx.core.provider;

import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontsContractCompat$FontFamilyResult {
    public final List mFonts;
    public final int mStatusCode;

    public FontsContractCompat$FontFamilyResult() {
        this.mStatusCode = 1;
        this.mFonts = Collections.singletonList(null);
    }

    public FontsContractCompat$FontFamilyResult(List list) {
        this.mStatusCode = 0;
        this.mFonts = list;
    }
}
