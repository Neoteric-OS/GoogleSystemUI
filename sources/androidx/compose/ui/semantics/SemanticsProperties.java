package androidx.compose.ui.semantics;

import androidx.compose.animation.AnimatedVisibilityScope$animateEnterExit$$inlined$debugInspectorInfo$1$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SemanticsProperties {
    public static final SemanticsProperties INSTANCE = null;
    public static final SemanticsPropertyKey ContentDescription = SemanticsPropertiesKt.AccessibilityKey("ContentDescription", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$ContentDescription$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            List list = (List) obj;
            List list2 = (List) obj2;
            if (list == null) {
                return list2;
            }
            ArrayList arrayList = new ArrayList(list);
            arrayList.addAll(list2);
            return arrayList;
        }
    });
    public static final SemanticsPropertyKey StateDescription = SemanticsPropertiesKt.AccessibilityKey("StateDescription");
    public static final SemanticsPropertyKey ProgressBarRangeInfo = SemanticsPropertiesKt.AccessibilityKey("ProgressBarRangeInfo");
    public static final SemanticsPropertyKey PaneTitle = SemanticsPropertiesKt.AccessibilityKey("PaneTitle", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$PaneTitle$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            throw new IllegalStateException("merge function called on unmergeable property PaneTitle.");
        }
    });
    public static final SemanticsPropertyKey SelectableGroup = SemanticsPropertiesKt.AccessibilityKey("SelectableGroup");
    public static final SemanticsPropertyKey CollectionInfo = SemanticsPropertiesKt.AccessibilityKey("CollectionInfo");
    public static final SemanticsPropertyKey CollectionItemInfo = SemanticsPropertiesKt.AccessibilityKey("CollectionItemInfo");
    public static final SemanticsPropertyKey Heading = SemanticsPropertiesKt.AccessibilityKey("Heading");
    public static final SemanticsPropertyKey Disabled = SemanticsPropertiesKt.AccessibilityKey("Disabled");
    public static final SemanticsPropertyKey LiveRegion = SemanticsPropertiesKt.AccessibilityKey("LiveRegion");
    public static final SemanticsPropertyKey Focused = SemanticsPropertiesKt.AccessibilityKey("Focused");
    public static final SemanticsPropertyKey IsContainer = SemanticsPropertiesKt.AccessibilityKey("IsContainer");
    public static final SemanticsPropertyKey IsTraversalGroup = new SemanticsPropertyKey("IsTraversalGroup");
    public static final SemanticsPropertyKey InvisibleToUser = new SemanticsPropertyKey("InvisibleToUser", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$InvisibleToUser$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return (Unit) obj;
        }
    });
    public static final SemanticsPropertyKey ContentDataType = new SemanticsPropertyKey("ContentDataType", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$ContentDataType$1
        @Override // kotlin.jvm.functions.Function2
        public final /* synthetic */ Object invoke(Object obj, Object obj2) {
            if (obj == null) {
                throw AnimatedVisibilityScope$animateEnterExit$$inlined$debugInspectorInfo$1$$ExternalSyntheticOutline0.m(obj2);
            }
            throw new ClassCastException();
        }
    });
    public static final SemanticsPropertyKey TraversalIndex = new SemanticsPropertyKey("TraversalIndex", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$TraversalIndex$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Float f = (Float) obj;
            ((Number) obj2).floatValue();
            return f;
        }
    });
    public static final SemanticsPropertyKey HorizontalScrollAxisRange = SemanticsPropertiesKt.AccessibilityKey("HorizontalScrollAxisRange");
    public static final SemanticsPropertyKey VerticalScrollAxisRange = SemanticsPropertiesKt.AccessibilityKey("VerticalScrollAxisRange");
    public static final SemanticsPropertyKey IsPopup = SemanticsPropertiesKt.AccessibilityKey("IsPopup", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$IsPopup$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            throw new IllegalStateException("merge function called on unmergeable property IsPopup. A popup should not be a child of a clickable/focusable node.");
        }
    });
    public static final SemanticsPropertyKey IsDialog = SemanticsPropertiesKt.AccessibilityKey("IsDialog", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$IsDialog$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            throw new IllegalStateException("merge function called on unmergeable property IsDialog. A dialog should not be a child of a clickable/focusable node.");
        }
    });
    public static final SemanticsPropertyKey Role = SemanticsPropertiesKt.AccessibilityKey("Role", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$Role$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Role role = (Role) obj;
            int i = ((Role) obj2).value;
            return role;
        }
    });
    public static final SemanticsPropertyKey TestTag = new SemanticsPropertyKey("TestTag", false, new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$TestTag$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return (String) obj;
        }
    });
    public static final SemanticsPropertyKey LinkTestMarker = new SemanticsPropertyKey("LinkTestMarker", false, new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$LinkTestMarker$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return (Unit) obj;
        }
    });
    public static final SemanticsPropertyKey Text = SemanticsPropertiesKt.AccessibilityKey("Text", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$Text$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            List list = (List) obj;
            List list2 = (List) obj2;
            if (list == null) {
                return list2;
            }
            ArrayList arrayList = new ArrayList(list);
            arrayList.addAll(list2);
            return arrayList;
        }
    });
    public static final SemanticsPropertyKey TextSubstitution = new SemanticsPropertyKey("TextSubstitution");
    public static final SemanticsPropertyKey IsShowingTextSubstitution = new SemanticsPropertyKey("IsShowingTextSubstitution");
    public static final SemanticsPropertyKey EditableText = SemanticsPropertiesKt.AccessibilityKey("EditableText");
    public static final SemanticsPropertyKey TextSelectionRange = SemanticsPropertiesKt.AccessibilityKey("TextSelectionRange");
    public static final SemanticsPropertyKey ImeAction = SemanticsPropertiesKt.AccessibilityKey("ImeAction");
    public static final SemanticsPropertyKey Selected = SemanticsPropertiesKt.AccessibilityKey("Selected");
    public static final SemanticsPropertyKey ToggleableState = SemanticsPropertiesKt.AccessibilityKey("ToggleableState");
    public static final SemanticsPropertyKey Password = SemanticsPropertiesKt.AccessibilityKey("Password");
    public static final SemanticsPropertyKey Error = SemanticsPropertiesKt.AccessibilityKey("Error");
    public static final SemanticsPropertyKey IndexForKey = new SemanticsPropertyKey("IndexForKey");
    public static final SemanticsPropertyKey IsEditable = new SemanticsPropertyKey("IsEditable");
    public static final SemanticsPropertyKey MaxTextLength = new SemanticsPropertyKey("MaxTextLength");
}
