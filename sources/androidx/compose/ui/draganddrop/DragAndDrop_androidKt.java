package androidx.compose.ui.draganddrop;

import android.content.ClipDescription;
import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.collections.builders.MapBuilder;
import kotlin.collections.builders.SetBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DragAndDrop_androidKt {
    public static final long getPositionInRoot(DragAndDropEvent dragAndDropEvent) {
        float x = dragAndDropEvent.dragEvent.getX();
        float y = dragAndDropEvent.dragEvent.getY();
        return (Float.floatToRawIntBits(x) << 32) | (Float.floatToRawIntBits(y) & 4294967295L);
    }

    public static final Set mimeTypes(DragAndDropEvent dragAndDropEvent) {
        ClipDescription clipDescription = dragAndDropEvent.dragEvent.getClipDescription();
        if (clipDescription == null) {
            return EmptySet.INSTANCE;
        }
        SetBuilder setBuilder = new SetBuilder(new MapBuilder(clipDescription.getMimeTypeCount()));
        int mimeTypeCount = clipDescription.getMimeTypeCount();
        for (int i = 0; i < mimeTypeCount; i++) {
            setBuilder.add(clipDescription.getMimeType(i));
        }
        return setBuilder.build();
    }
}
