package com.android.systemui.media.controls.ui.viewmodel;

import android.graphics.drawable.Drawable;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GutsViewModel {
    public final Drawable cancelTextBackground;
    public final CharSequence gutsText;
    public final boolean isDismissEnabled;
    public final Lambda onDismissClicked;
    public final Lambda onSettingsClicked;

    /* JADX WARN: Multi-variable type inference failed */
    public GutsViewModel(CharSequence charSequence, boolean z, Function0 function0, Drawable drawable, Function0 function02) {
        this.gutsText = charSequence;
        this.isDismissEnabled = z;
        this.onDismissClicked = (Lambda) function0;
        this.cancelTextBackground = drawable;
        this.onSettingsClicked = (Lambda) function02;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GutsViewModel)) {
            return false;
        }
        GutsViewModel gutsViewModel = (GutsViewModel) obj;
        return Intrinsics.areEqual(this.gutsText, gutsViewModel.gutsText) && this.isDismissEnabled == gutsViewModel.isDismissEnabled && this.onDismissClicked.equals(gutsViewModel.onDismissClicked) && Intrinsics.areEqual(this.cancelTextBackground, gutsViewModel.cancelTextBackground) && this.onSettingsClicked.equals(gutsViewModel.onSettingsClicked);
    }

    public final int hashCode() {
        int hashCode = (this.onDismissClicked.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(this.gutsText.hashCode() * 31, 31, this.isDismissEnabled)) * 31;
        Drawable drawable = this.cancelTextBackground;
        return this.onSettingsClicked.hashCode() + ((hashCode + (drawable == null ? 0 : drawable.hashCode())) * 31);
    }

    public final String toString() {
        CharSequence charSequence = this.gutsText;
        return "GutsViewModel(gutsText=" + ((Object) charSequence) + ", isDismissEnabled=" + this.isDismissEnabled + ", onDismissClicked=" + this.onDismissClicked + ", cancelTextBackground=" + this.cancelTextBackground + ", onSettingsClicked=" + this.onSettingsClicked + ")";
    }
}
