package com.android.systemui.qs.tiles.viewmodel;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.Switch;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import java.util.Collections;
import java.util.Set;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileState {
    public final ActivationState activationState;
    public final CharSequence contentDescription;
    public final String expandedAccessibilityClassName;
    public final Lambda icon;
    public final Integer iconRes;
    public final CharSequence label;
    public final CharSequence secondaryLabel;
    public final SideViewIcon sideViewIcon;
    public final CharSequence stateDescription;
    public final Set supportedActions;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ActivationState {
        public static final /* synthetic */ ActivationState[] $VALUES;
        public static final ActivationState ACTIVE;
        public static final Companion Companion;
        public static final ActivationState INACTIVE;
        public static final ActivationState UNAVAILABLE;
        private final int legacyState;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Companion {
        }

        static {
            ActivationState activationState = new ActivationState("UNAVAILABLE", 0, 0);
            UNAVAILABLE = activationState;
            ActivationState activationState2 = new ActivationState("ACTIVE", 1, 2);
            ACTIVE = activationState2;
            ActivationState activationState3 = new ActivationState("INACTIVE", 2, 1);
            INACTIVE = activationState3;
            ActivationState[] activationStateArr = {activationState, activationState2, activationState3};
            $VALUES = activationStateArr;
            EnumEntriesKt.enumEntries(activationStateArr);
            Companion = new Companion();
        }

        public ActivationState(String str, int i, int i2) {
            this.legacyState = i2;
        }

        public static ActivationState valueOf(String str) {
            return (ActivationState) Enum.valueOf(ActivationState.class, str);
        }

        public static ActivationState[] values() {
            return (ActivationState[]) $VALUES.clone();
        }

        public final int getLegacyState() {
            return this.legacyState;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public ActivationState activationState;
        public CharSequence contentDescription;
        public ClassReference expandedAccessibilityClass;
        public Lambda icon;
        public Integer iconRes;
        public CharSequence label;
        public CharSequence secondaryLabel;
        public SideViewIcon sideViewIcon;
        public CharSequence stateDescription;
        public Set supportedActions;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EnabledState {
        public static final /* synthetic */ EnabledState[] $VALUES;
        public static final EnabledState DISABLED = null;
        public static final EnabledState ENABLED;

        static {
            EnabledState enabledState = new EnabledState("ENABLED", 0);
            ENABLED = enabledState;
            EnabledState[] enabledStateArr = {enabledState, new EnabledState("DISABLED", 1)};
            $VALUES = enabledStateArr;
            EnumEntriesKt.enumEntries(enabledStateArr);
        }

        public static EnabledState valueOf(String str) {
            return (EnabledState) Enum.valueOf(EnabledState.class, str);
        }

        public static EnabledState[] values() {
            return (EnabledState[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SideViewIcon {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Chevron implements SideViewIcon {
            public static final Chevron INSTANCE = new Chevron();

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof Chevron);
            }

            public final int hashCode() {
                return -531710673;
            }

            public final String toString() {
                return "Chevron";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class None implements SideViewIcon {
            public static final None INSTANCE = new None();

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof None);
            }

            public final int hashCode() {
                return 630195396;
            }

            public final String toString() {
                return "None";
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UserAction {
        public static final /* synthetic */ UserAction[] $VALUES;
        public static final UserAction CLICK;
        public static final UserAction LONG_CLICK;
        public static final UserAction TOGGLE_CLICK;

        static {
            UserAction userAction = new UserAction("CLICK", 0);
            CLICK = userAction;
            UserAction userAction2 = new UserAction("TOGGLE_CLICK", 1);
            TOGGLE_CLICK = userAction2;
            UserAction userAction3 = new UserAction("LONG_CLICK", 2);
            LONG_CLICK = userAction3;
            UserAction[] userActionArr = {userAction, userAction2, userAction3};
            $VALUES = userActionArr;
            EnumEntriesKt.enumEntries(userActionArr);
        }

        public static UserAction valueOf(String str) {
            return (UserAction) Enum.valueOf(UserAction.class, str);
        }

        public static UserAction[] values() {
            return (UserAction[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public QSTileState(Function0 function0, Integer num, CharSequence charSequence, ActivationState activationState, CharSequence charSequence2, Set set, CharSequence charSequence3, CharSequence charSequence4, SideViewIcon sideViewIcon, String str) {
        EnabledState enabledState = EnabledState.ENABLED;
        this.icon = (Lambda) function0;
        this.iconRes = num;
        this.label = charSequence;
        this.activationState = activationState;
        this.secondaryLabel = charSequence2;
        this.supportedActions = set;
        this.contentDescription = charSequence3;
        this.stateDescription = charSequence4;
        this.sideViewIcon = sideViewIcon;
        this.expandedAccessibilityClassName = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QSTileState)) {
            return false;
        }
        QSTileState qSTileState = (QSTileState) obj;
        if (!Intrinsics.areEqual(this.icon, qSTileState.icon) || !Intrinsics.areEqual(this.iconRes, qSTileState.iconRes) || !Intrinsics.areEqual(this.label, qSTileState.label) || this.activationState != qSTileState.activationState || !Intrinsics.areEqual(this.secondaryLabel, qSTileState.secondaryLabel) || !Intrinsics.areEqual(this.supportedActions, qSTileState.supportedActions) || !Intrinsics.areEqual(this.contentDescription, qSTileState.contentDescription) || !Intrinsics.areEqual(this.stateDescription, qSTileState.stateDescription) || !Intrinsics.areEqual(this.sideViewIcon, qSTileState.sideViewIcon)) {
            return false;
        }
        EnabledState enabledState = EnabledState.ENABLED;
        return Intrinsics.areEqual(this.expandedAccessibilityClassName, qSTileState.expandedAccessibilityClassName);
    }

    public final int hashCode() {
        int hashCode = this.icon.hashCode() * 31;
        Integer num = this.iconRes;
        int hashCode2 = (this.activationState.hashCode() + ((this.label.hashCode() + ((hashCode + (num == null ? 0 : num.hashCode())) * 31)) * 31)) * 31;
        CharSequence charSequence = this.secondaryLabel;
        int hashCode3 = (this.supportedActions.hashCode() + ((hashCode2 + (charSequence == null ? 0 : charSequence.hashCode())) * 31)) * 31;
        CharSequence charSequence2 = this.contentDescription;
        int hashCode4 = (hashCode3 + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
        CharSequence charSequence3 = this.stateDescription;
        int hashCode5 = (EnabledState.ENABLED.hashCode() + ((this.sideViewIcon.hashCode() + ((hashCode4 + (charSequence3 == null ? 0 : charSequence3.hashCode())) * 31)) * 31)) * 31;
        String str = this.expandedAccessibilityClassName;
        return hashCode5 + (str != null ? str.hashCode() : 0);
    }

    public final String toString() {
        CharSequence charSequence = this.label;
        CharSequence charSequence2 = this.secondaryLabel;
        Set set = this.supportedActions;
        CharSequence charSequence3 = this.contentDescription;
        CharSequence charSequence4 = this.stateDescription;
        StringBuilder sb = new StringBuilder("QSTileState(icon=");
        sb.append(this.icon);
        sb.append(", iconRes=");
        sb.append(this.iconRes);
        sb.append(", label=");
        sb.append((Object) charSequence);
        sb.append(", activationState=");
        sb.append(this.activationState);
        sb.append(", secondaryLabel=");
        sb.append((Object) charSequence2);
        sb.append(", supportedActions=");
        sb.append(set);
        sb.append(", contentDescription=");
        sb.append((Object) charSequence3);
        sb.append(", stateDescription=");
        sb.append((Object) charSequence4);
        sb.append(", sideViewIcon=");
        sb.append(this.sideViewIcon);
        sb.append(", enabledState=");
        sb.append(EnabledState.ENABLED);
        sb.append(", expandedAccessibilityClassName=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.expandedAccessibilityClassName, ")");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static QSTileState build(Resources resources, Resources.Theme theme, QSTileUIConfig qSTileUIConfig, Function1 function1) {
            final Drawable drawable = resources.getDrawable(qSTileUIConfig.getIconRes(), theme);
            return build(new Function0() { // from class: com.android.systemui.qs.tiles.viewmodel.QSTileState$Companion$build$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new Icon.Loaded(drawable, null);
                }
            }, resources.getString(qSTileUIConfig.getLabelRes()), function1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
        public static QSTileState build(Function0 function0, CharSequence charSequence, Function1 function1) {
            Builder builder = new Builder();
            builder.icon = (Lambda) function0;
            builder.label = charSequence;
            builder.activationState = ActivationState.INACTIVE;
            builder.supportedActions = Collections.singleton(UserAction.CLICK);
            builder.sideViewIcon = SideViewIcon.None.INSTANCE;
            EnabledState enabledState = EnabledState.ENABLED;
            builder.expandedAccessibilityClass = Reflection.getOrCreateKotlinClass(Switch.class);
            function1.invoke(builder);
            ?? r2 = builder.icon;
            Integer num = builder.iconRes;
            CharSequence charSequence2 = builder.label;
            ActivationState activationState = builder.activationState;
            CharSequence charSequence3 = builder.secondaryLabel;
            Set set = builder.supportedActions;
            CharSequence charSequence4 = builder.contentDescription;
            CharSequence charSequence5 = builder.stateDescription;
            SideViewIcon sideViewIcon = builder.sideViewIcon;
            ClassReference classReference = builder.expandedAccessibilityClass;
            return new QSTileState(r2, num, charSequence2, activationState, charSequence3, set, charSequence4, charSequence5, sideViewIcon, classReference != null ? classReference.getQualifiedName() : null);
        }
    }
}
