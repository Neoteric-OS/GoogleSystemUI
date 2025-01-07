package androidx.compose.ui.semantics;

import kotlin.Function;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SemanticsActions {
    public static final SemanticsPropertyKey ClearTextSubstitution;
    public static final SemanticsPropertyKey Collapse;
    public static final SemanticsPropertyKey CopyText;
    public static final SemanticsPropertyKey CustomActions;
    public static final SemanticsPropertyKey CutText;
    public static final SemanticsPropertyKey Dismiss;
    public static final SemanticsPropertyKey Expand;
    public static final SemanticsPropertyKey GetScrollViewportLength;
    public static final SemanticsPropertyKey GetTextLayoutResult;
    public static final SemanticsPropertyKey InsertTextAtCursor;
    public static final SemanticsPropertyKey OnClick;
    public static final SemanticsPropertyKey OnImeAction;
    public static final SemanticsPropertyKey OnLongClick;
    public static final SemanticsPropertyKey PageDown;
    public static final SemanticsPropertyKey PageLeft;
    public static final SemanticsPropertyKey PageRight;
    public static final SemanticsPropertyKey PageUp;
    public static final SemanticsPropertyKey PasteText;
    public static final SemanticsPropertyKey RequestFocus;
    public static final SemanticsPropertyKey ScrollBy;
    public static final SemanticsPropertyKey ScrollByOffset;
    public static final SemanticsPropertyKey ScrollToIndex;
    public static final SemanticsPropertyKey SetProgress;
    public static final SemanticsPropertyKey SetSelection;
    public static final SemanticsPropertyKey SetText;
    public static final SemanticsPropertyKey SetTextSubstitution;
    public static final SemanticsPropertyKey ShowTextSubstitution;

    static {
        SemanticsPropertiesKt$ActionPropertyKey$1 semanticsPropertiesKt$ActionPropertyKey$1 = new Function2() { // from class: androidx.compose.ui.semantics.SemanticsPropertiesKt$ActionPropertyKey$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                String str;
                Function function;
                AccessibilityAction accessibilityAction = (AccessibilityAction) obj;
                AccessibilityAction accessibilityAction2 = (AccessibilityAction) obj2;
                if (accessibilityAction == null || (str = accessibilityAction.label) == null) {
                    str = accessibilityAction2.label;
                }
                if (accessibilityAction == null || (function = accessibilityAction.action) == null) {
                    function = accessibilityAction2.action;
                }
                return new AccessibilityAction(str, function);
            }
        };
        GetTextLayoutResult = SemanticsPropertiesKt.AccessibilityKey("GetTextLayoutResult", semanticsPropertiesKt$ActionPropertyKey$1);
        OnClick = SemanticsPropertiesKt.AccessibilityKey("OnClick", semanticsPropertiesKt$ActionPropertyKey$1);
        OnLongClick = SemanticsPropertiesKt.AccessibilityKey("OnLongClick", semanticsPropertiesKt$ActionPropertyKey$1);
        ScrollBy = SemanticsPropertiesKt.AccessibilityKey("ScrollBy", semanticsPropertiesKt$ActionPropertyKey$1);
        ScrollByOffset = new SemanticsPropertyKey("ScrollByOffset");
        ScrollToIndex = SemanticsPropertiesKt.AccessibilityKey("ScrollToIndex", semanticsPropertiesKt$ActionPropertyKey$1);
        SetProgress = SemanticsPropertiesKt.AccessibilityKey("SetProgress", semanticsPropertiesKt$ActionPropertyKey$1);
        SetSelection = SemanticsPropertiesKt.AccessibilityKey("SetSelection", semanticsPropertiesKt$ActionPropertyKey$1);
        SetText = SemanticsPropertiesKt.AccessibilityKey("SetText", semanticsPropertiesKt$ActionPropertyKey$1);
        SetTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("SetTextSubstitution", semanticsPropertiesKt$ActionPropertyKey$1);
        ShowTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("ShowTextSubstitution", semanticsPropertiesKt$ActionPropertyKey$1);
        ClearTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("ClearTextSubstitution", semanticsPropertiesKt$ActionPropertyKey$1);
        InsertTextAtCursor = SemanticsPropertiesKt.AccessibilityKey("InsertTextAtCursor", semanticsPropertiesKt$ActionPropertyKey$1);
        OnImeAction = SemanticsPropertiesKt.AccessibilityKey("PerformImeAction", semanticsPropertiesKt$ActionPropertyKey$1);
        CopyText = SemanticsPropertiesKt.AccessibilityKey("CopyText", semanticsPropertiesKt$ActionPropertyKey$1);
        CutText = SemanticsPropertiesKt.AccessibilityKey("CutText", semanticsPropertiesKt$ActionPropertyKey$1);
        PasteText = SemanticsPropertiesKt.AccessibilityKey("PasteText", semanticsPropertiesKt$ActionPropertyKey$1);
        Expand = SemanticsPropertiesKt.AccessibilityKey("Expand", semanticsPropertiesKt$ActionPropertyKey$1);
        Collapse = SemanticsPropertiesKt.AccessibilityKey("Collapse", semanticsPropertiesKt$ActionPropertyKey$1);
        Dismiss = SemanticsPropertiesKt.AccessibilityKey("Dismiss", semanticsPropertiesKt$ActionPropertyKey$1);
        RequestFocus = SemanticsPropertiesKt.AccessibilityKey("RequestFocus", semanticsPropertiesKt$ActionPropertyKey$1);
        CustomActions = SemanticsPropertiesKt.AccessibilityKey("CustomActions");
        PageUp = SemanticsPropertiesKt.AccessibilityKey("PageUp", semanticsPropertiesKt$ActionPropertyKey$1);
        PageLeft = SemanticsPropertiesKt.AccessibilityKey("PageLeft", semanticsPropertiesKt$ActionPropertyKey$1);
        PageDown = SemanticsPropertiesKt.AccessibilityKey("PageDown", semanticsPropertiesKt$ActionPropertyKey$1);
        PageRight = SemanticsPropertiesKt.AccessibilityKey("PageRight", semanticsPropertiesKt$ActionPropertyKey$1);
        GetScrollViewportLength = SemanticsPropertiesKt.AccessibilityKey("GetScrollViewportLength", semanticsPropertiesKt$ActionPropertyKey$1);
    }
}
