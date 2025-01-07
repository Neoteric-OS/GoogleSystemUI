package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.BlendModeColorFilter;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class VectorKt {
    public static final EmptyList EmptyPath = EmptyList.INSTANCE;

    static {
        int i = Color.$r8$clinit;
    }

    public static final List addPathNodes(String str) {
        if (str == null) {
            return EmptyPath;
        }
        PathParser pathParser = new PathParser();
        ArrayList arrayList = pathParser.nodes;
        if (arrayList == null) {
            arrayList = new ArrayList();
            pathParser.nodes = arrayList;
        } else {
            arrayList.clear();
        }
        pathParser.pathStringToNodes(str, arrayList);
        ArrayList arrayList2 = pathParser.nodes;
        return arrayList2 != null ? arrayList2 : EmptyList.INSTANCE;
    }

    public static final boolean tintableWithAlphaMask(ColorFilter colorFilter) {
        if (colorFilter instanceof BlendModeColorFilter) {
            BlendModeColorFilter blendModeColorFilter = (BlendModeColorFilter) colorFilter;
            if (BlendMode.m357equalsimpl0(blendModeColorFilter.blendMode, 5) || BlendMode.m357equalsimpl0(blendModeColorFilter.blendMode, 3)) {
                return true;
            }
        } else if (colorFilter == null) {
            return true;
        }
        return false;
    }
}
