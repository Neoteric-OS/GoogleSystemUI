package androidx.compose.ui.text.font;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontVariation$Settings {
    public final List settings;

    public FontVariation$Settings(FontVariation$Setting... fontVariation$SettingArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (fontVariation$SettingArr.length > 0) {
            FontVariation$Setting fontVariation$Setting = fontVariation$SettingArr[0];
            throw null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (list.size() != 1) {
                throw new IllegalArgumentException(OpaqueKey$$ExternalSyntheticOutline0.m(ActivityResultRegistry$$ExternalSyntheticOutline0.m("'", str, "' must be unique. Actual [ ["), CollectionsKt.joinToString$default(list, null, null, null, null, 63), ']').toString());
            }
            CollectionsKt__MutableCollectionsKt.addAll(list, arrayList);
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        this.settings = arrayList2;
        if (arrayList2.size() <= 0) {
            return;
        }
        arrayList2.get(0).getClass();
        throw new ClassCastException();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FontVariation$Settings) && Intrinsics.areEqual(this.settings, ((FontVariation$Settings) obj).settings);
    }

    public final int hashCode() {
        return this.settings.hashCode();
    }
}
