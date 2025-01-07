package androidx.compose.ui.draganddrop;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DragAndDropTarget {
    boolean onDrop(DragAndDropEvent dragAndDropEvent);

    default void onEnded(DragAndDropEvent dragAndDropEvent) {
    }

    default void onEntered(DragAndDropEvent dragAndDropEvent) {
    }

    default void onExited(DragAndDropEvent dragAndDropEvent) {
    }

    default void onMoved(DragAndDropEvent dragAndDropEvent) {
    }

    default void onStarted(DragAndDropEvent dragAndDropEvent) {
    }
}
