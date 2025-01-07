package com.android.systemui.inputdevice.tutorial.ui.composable;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.airbnb.lottie.compose.LottieDynamicProperties;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TutorialScreenConfig {
    public final Animations animations;
    public final Colors colors;
    public final Strings strings;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Animations {
        public final int educationResId;
        public final int successResId;

        public Animations(int i, int i2) {
            this.educationResId = i;
            this.successResId = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Animations)) {
                return false;
            }
            Animations animations = (Animations) obj;
            return this.educationResId == animations.educationResId && this.successResId == animations.successResId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.successResId) + (Integer.hashCode(this.educationResId) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Animations(educationResId=");
            sb.append(this.educationResId);
            sb.append(", successResId=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.successResId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Colors {
        public final LottieDynamicProperties animationColors;
        public final long background;
        public final long title;

        public Colors(long j, long j2, LottieDynamicProperties lottieDynamicProperties) {
            this.background = j;
            this.title = j2;
            this.animationColors = lottieDynamicProperties;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Colors)) {
                return false;
            }
            Colors colors = (Colors) obj;
            return Color.m363equalsimpl0(this.background, colors.background) && Color.m363equalsimpl0(this.title, colors.title) && Intrinsics.areEqual(this.animationColors, colors.animationColors);
        }

        public final int hashCode() {
            int i = Color.$r8$clinit;
            return this.animationColors.hashCode() + Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.background) * 31, 31, this.title);
        }

        public final String toString() {
            StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Colors(background=", Color.m369toStringimpl(this.background), ", title=", Color.m369toStringimpl(this.title), ", animationColors=");
            m.append(this.animationColors);
            m.append(")");
            return m.toString();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Strings {
        public final int bodyResId;
        public final int bodySuccessResId;
        public final int titleResId;
        public final int titleSuccessResId;

        public Strings(int i, int i2, int i3, int i4) {
            this.titleResId = i;
            this.bodyResId = i2;
            this.titleSuccessResId = i3;
            this.bodySuccessResId = i4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Strings)) {
                return false;
            }
            Strings strings = (Strings) obj;
            return this.titleResId == strings.titleResId && this.bodyResId == strings.bodyResId && this.titleSuccessResId == strings.titleSuccessResId && this.bodySuccessResId == strings.bodySuccessResId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.bodySuccessResId) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.titleSuccessResId, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.bodyResId, Integer.hashCode(this.titleResId) * 31, 31), 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Strings(titleResId=");
            sb.append(this.titleResId);
            sb.append(", bodyResId=");
            sb.append(this.bodyResId);
            sb.append(", titleSuccessResId=");
            sb.append(this.titleSuccessResId);
            sb.append(", bodySuccessResId=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.bodySuccessResId, ")");
        }
    }

    public TutorialScreenConfig(Colors colors, Strings strings, Animations animations) {
        this.colors = colors;
        this.strings = strings;
        this.animations = animations;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TutorialScreenConfig)) {
            return false;
        }
        TutorialScreenConfig tutorialScreenConfig = (TutorialScreenConfig) obj;
        return Intrinsics.areEqual(this.colors, tutorialScreenConfig.colors) && Intrinsics.areEqual(this.strings, tutorialScreenConfig.strings) && Intrinsics.areEqual(this.animations, tutorialScreenConfig.animations);
    }

    public final int hashCode() {
        return this.animations.hashCode() + ((this.strings.hashCode() + (this.colors.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "TutorialScreenConfig(colors=" + this.colors + ", strings=" + this.strings + ", animations=" + this.animations + ")";
    }
}
