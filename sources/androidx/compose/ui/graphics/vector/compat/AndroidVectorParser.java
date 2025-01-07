package androidx.compose.ui.graphics.vector.compat;

import android.content.res.TypedArray;
import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.vector.PathParser;
import androidx.core.content.res.TypedArrayUtils;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidVectorParser {
    public int config = 0;
    public final PathParser pathParser = new PathParser();
    public final XmlPullParser xmlParser;

    public AndroidVectorParser(XmlPullParser xmlPullParser) {
        this.xmlParser = xmlPullParser;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AndroidVectorParser)) {
            return false;
        }
        AndroidVectorParser androidVectorParser = (AndroidVectorParser) obj;
        return Intrinsics.areEqual(this.xmlParser, androidVectorParser.xmlParser) && this.config == androidVectorParser.config;
    }

    public final float getNamedFloat(TypedArray typedArray, String str, int i, float f) {
        if (TypedArrayUtils.hasAttribute(this.xmlParser, str)) {
            f = typedArray.getFloat(i, f);
        }
        updateConfig(typedArray.getChangingConfigurations());
        return f;
    }

    public final int hashCode() {
        return Integer.hashCode(this.config) + (this.xmlParser.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AndroidVectorParser(xmlParser=");
        sb.append(this.xmlParser);
        sb.append(", config=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.config, ')');
    }

    public final void updateConfig(int i) {
        this.config = i | this.config;
    }
}
