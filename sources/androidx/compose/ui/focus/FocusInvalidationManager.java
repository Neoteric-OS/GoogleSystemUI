package androidx.compose.ui.focus;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FocusInvalidationManager {
    public final Function0 invalidateOwnerFocusState;
    public final Function1 onRequestApplyChangesListener;
    public final Function0 rootFocusStateFetcher;
    public final List focusTargetNodes = new ArrayList();
    public final List focusEventNodes = new ArrayList();
    public final List focusPropertiesNodes = new ArrayList();
    public final List focusTargetsWithInvalidatedFocusEvents = new ArrayList();

    public FocusInvalidationManager(Function1 function1, Function0 function0, Function0 function02) {
        this.onRequestApplyChangesListener = function1;
        this.invalidateOwnerFocusState = function0;
        this.rootFocusStateFetcher = function02;
    }

    public final boolean hasPendingInvalidation() {
        return (this.focusTargetNodes.isEmpty() && this.focusPropertiesNodes.isEmpty() && this.focusEventNodes.isEmpty()) ? false : true;
    }

    public final void scheduleInvalidation(List list, Object obj) {
        if (list.add(obj)) {
            if (((ArrayList) this.focusPropertiesNodes).size() + ((ArrayList) this.focusEventNodes).size() + ((ArrayList) this.focusTargetNodes).size() == 1) {
                this.onRequestApplyChangesListener.invoke(new FocusInvalidationManager$scheduleInvalidation$1(0, this, FocusInvalidationManager.class, "invalidateNodes", "invalidateNodes()V", 0));
            }
        }
    }
}
