package com.android.systemui.keyguard.ui.view.layout.blueprints.transitions;

import android.transition.TransitionSet;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition;
import com.android.systemui.keyguard.ui.view.layout.sections.transitions.DefaultClockSteppingTransition;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IntraBlueprintTransition extends TransitionSet {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Config {
        public static final Config DEFAULT = new Config(Type.NoTransition, null, 14);
        public final boolean checkPriority;
        public final List rebuildSections;
        public final boolean terminatePrevious;
        public final Type type;

        public Config(Type type, List list, int i) {
            boolean z = (i & 2) != 0;
            boolean z2 = (i & 4) != 0;
            list = (i & 8) != 0 ? EmptyList.INSTANCE : list;
            this.type = type;
            this.checkPriority = z;
            this.terminatePrevious = z2;
            this.rebuildSections = list;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Config)) {
                return false;
            }
            Config config = (Config) obj;
            return this.type == config.type && this.checkPriority == config.checkPriority && this.terminatePrevious == config.terminatePrevious && Intrinsics.areEqual(this.rebuildSections, config.rebuildSections);
        }

        public final int hashCode() {
            return this.rebuildSections.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(this.type.hashCode() * 31, 31, this.checkPriority), 31, this.terminatePrevious);
        }

        public final String toString() {
            return "Config(type=" + this.type + ", checkPriority=" + this.checkPriority + ", terminatePrevious=" + this.terminatePrevious + ", rebuildSections=" + this.rebuildSections + ")";
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Type {
        public static final /* synthetic */ Type[] $VALUES;
        public static final Type ClockSize;
        public static final Type DefaultClockStepping;
        public static final Type DefaultTransition;
        public static final Type NoTransition;
        public static final Type SmartspaceVisibility;
        private final boolean animateNotifChanges;
        private final int priority;

        static {
            Type type = new Type(0, 100, "ClockSize", true);
            ClockSize = type;
            Type type2 = new Type(1, 99, "ClockCenter", false);
            Type type3 = new Type(2, 98, "DefaultClockStepping", false);
            DefaultClockStepping = type3;
            Type type4 = new Type(3, 2, "SmartspaceVisibility", true);
            SmartspaceVisibility = type4;
            Type type5 = new Type(4, 1, "DefaultTransition", false);
            DefaultTransition = type5;
            Type type6 = new Type(5, 0, "NoTransition", false);
            NoTransition = type6;
            Type[] typeArr = {type, type2, type3, type4, type5, type6};
            $VALUES = typeArr;
            EnumEntriesKt.enumEntries(typeArr);
        }

        public Type(int i, int i2, String str, boolean z) {
            this.priority = i2;
            this.animateNotifChanges = z;
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }

        public final boolean getAnimateNotifChanges() {
            return this.animateNotifChanges;
        }

        public final int getPriority() {
            return this.priority;
        }
    }

    public IntraBlueprintTransition(Config config, KeyguardClockViewModel keyguardClockViewModel) {
        setOrdering(0);
        Type type = config.type;
        int ordinal = type.ordinal();
        if (ordinal == 2) {
            ClockController clockController = (ClockController) ((StateFlowImpl) keyguardClockViewModel.currentClock.$$delegate_0).getValue();
            addTransition(clockController != null ? new DefaultClockSteppingTransition(clockController) : null);
            return;
        }
        if (ordinal != 5) {
            ClockSizeTransition clockSizeTransition = new ClockSizeTransition();
            clockSizeTransition.setOrdering(0);
            if (type != Type.SmartspaceVisibility) {
                clockSizeTransition.addTransition(new ClockSizeTransition.ClockFaceOutTransition(config, keyguardClockViewModel));
                clockSizeTransition.addTransition(new ClockSizeTransition.ClockFaceInTransition(config, keyguardClockViewModel));
            }
            ClockSizeTransition.SmartspaceMoveTransition smartspaceMoveTransition = new ClockSizeTransition.SmartspaceMoveTransition();
            smartspaceMoveTransition.setDuration(((Boolean) ((StateFlowImpl) keyguardClockViewModel.isLargeClockVisible.$$delegate_0).getValue()).booleanValue() ? 967L : 467L);
            smartspaceMoveTransition.setInterpolator(Interpolators.EMPHASIZED);
            smartspaceMoveTransition.addTarget(R.id.date_smartspace_view);
            smartspaceMoveTransition.addTarget(R.id.bc_smartspace_view);
            smartspaceMoveTransition.addTarget(R.id.aod_notification_icon_container);
            smartspaceMoveTransition.addTarget(R.id.status_view_media_container);
            clockSizeTransition.addTransition(smartspaceMoveTransition);
            addTransition(clockSizeTransition);
        }
    }
}
