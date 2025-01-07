package com.android.systemui.statusbar.chips.ui.model;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.chips.ui.model.ColorsModel;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class OngoingActivityChipModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ChipIcon {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class FullColorAppIcon implements ChipIcon {
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class SingleColorIcon implements ChipIcon {
            public final Icon.Resource impl;

            public SingleColorIcon(Icon.Resource resource) {
                this.impl = resource;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof SingleColorIcon) && this.impl.equals(((SingleColorIcon) obj).impl);
            }

            public final int hashCode() {
                return this.impl.hashCode();
            }

            public final String toString() {
                return "SingleColorIcon(impl=" + this.impl + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class StatusBarView implements ChipIcon {
            public final StatusBarIconView impl;

            public StatusBarView(StatusBarIconView statusBarIconView) {
                this.impl = statusBarIconView;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof StatusBarView) && Intrinsics.areEqual(this.impl, ((StatusBarView) obj).impl);
            }

            public final int hashCode() {
                return this.impl.hashCode();
            }

            public final String toString() {
                return "StatusBarView(impl=" + this.impl + ")";
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Hidden extends OngoingActivityChipModel {
        public final String logName;
        public final boolean shouldAnimate;

        public Hidden(boolean z) {
            this.shouldAnimate = z;
            this.logName = "Hidden(anim=" + z + ")";
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Hidden) && this.shouldAnimate == ((Hidden) obj).shouldAnimate;
        }

        @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel
        public final String getLogName() {
            return this.logName;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.shouldAnimate);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("Hidden(shouldAnimate="), this.shouldAnimate, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Shown extends OngoingActivityChipModel {
        public final ChipIcon icon;
        public final View.OnClickListener onClickListener;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Countdown extends Shown {
            public final long secondsUntilStarted;

            public Countdown(long j) {
                super(null, null);
                this.secondsUntilStarted = j;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Countdown)) {
                    return false;
                }
                Countdown countdown = (Countdown) obj;
                countdown.getClass();
                ColorsModel.Red red = ColorsModel.Red.INSTANCE;
                return red.equals(red) && this.secondsUntilStarted == countdown.secondsUntilStarted;
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown
            public final ColorsModel getColors() {
                return ColorsModel.Red.INSTANCE;
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel
            public final String getLogName() {
                return "Shown.Countdown";
            }

            public final int hashCode() {
                return Long.hashCode(this.secondsUntilStarted) + 806066800;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("Countdown(colors=");
                sb.append(ColorsModel.Red.INSTANCE);
                sb.append(", secondsUntilStarted=");
                return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.secondsUntilStarted, ")", sb);
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class IconOnly extends Shown {
            public final ColorsModel colors;
            public final ChipIcon icon;
            public final View.OnClickListener onClickListener;

            public IconOnly(ChipIcon chipIcon, ColorsModel colorsModel, View.OnClickListener onClickListener) {
                super(chipIcon, onClickListener);
                this.icon = chipIcon;
                this.colors = colorsModel;
                this.onClickListener = onClickListener;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof IconOnly)) {
                    return false;
                }
                IconOnly iconOnly = (IconOnly) obj;
                return Intrinsics.areEqual(this.icon, iconOnly.icon) && Intrinsics.areEqual(this.colors, iconOnly.colors) && Intrinsics.areEqual(this.onClickListener, iconOnly.onClickListener);
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown
            public final ColorsModel getColors() {
                return this.colors;
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown
            public final ChipIcon getIcon() {
                return this.icon;
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel
            public final String getLogName() {
                return "Shown.Icon";
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown
            public final View.OnClickListener getOnClickListener() {
                return this.onClickListener;
            }

            public final int hashCode() {
                int hashCode = (this.colors.hashCode() + (this.icon.hashCode() * 31)) * 31;
                View.OnClickListener onClickListener = this.onClickListener;
                return hashCode + (onClickListener == null ? 0 : onClickListener.hashCode());
            }

            public final String toString() {
                return "IconOnly(icon=" + this.icon + ", colors=" + this.colors + ", onClickListener=" + this.onClickListener + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Timer extends Shown {
            public final ColorsModel colors;
            public final ChipIcon icon;
            public final View.OnClickListener onClickListener;
            public final long startTimeMs;

            public Timer(ChipIcon chipIcon, ColorsModel colorsModel, long j, View.OnClickListener onClickListener) {
                super(chipIcon, onClickListener);
                this.icon = chipIcon;
                this.colors = colorsModel;
                this.startTimeMs = j;
                this.onClickListener = onClickListener;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Timer)) {
                    return false;
                }
                Timer timer = (Timer) obj;
                return Intrinsics.areEqual(this.icon, timer.icon) && Intrinsics.areEqual(this.colors, timer.colors) && this.startTimeMs == timer.startTimeMs && Intrinsics.areEqual(this.onClickListener, timer.onClickListener);
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown
            public final ColorsModel getColors() {
                return this.colors;
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown
            public final ChipIcon getIcon() {
                return this.icon;
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel
            public final String getLogName() {
                return "Shown.Timer";
            }

            @Override // com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown
            public final View.OnClickListener getOnClickListener() {
                return this.onClickListener;
            }

            public final int hashCode() {
                int m = Scale$$ExternalSyntheticOutline0.m((this.colors.hashCode() + (this.icon.hashCode() * 31)) * 31, 31, this.startTimeMs);
                View.OnClickListener onClickListener = this.onClickListener;
                return m + (onClickListener == null ? 0 : onClickListener.hashCode());
            }

            public final String toString() {
                return "Timer(icon=" + this.icon + ", colors=" + this.colors + ", startTimeMs=" + this.startTimeMs + ", onClickListener=" + this.onClickListener + ")";
            }
        }

        public Shown(ChipIcon chipIcon, View.OnClickListener onClickListener) {
            this.icon = chipIcon;
            this.onClickListener = onClickListener;
        }

        public abstract ColorsModel getColors();

        public ChipIcon getIcon() {
            return this.icon;
        }

        public View.OnClickListener getOnClickListener() {
            return this.onClickListener;
        }
    }

    public abstract String getLogName();
}
