package com.android.systemui.biometrics.ui.viewmodel;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.shared.model.BiometricModality;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptAuthState {
    public final BiometricModality authenticatedModality;
    public final long delay;
    public final boolean isAuthenticated;
    public final boolean needsUserConfirmation;
    public boolean wasConfirmed;

    public PromptAuthState(boolean z, BiometricModality biometricModality, boolean z2, long j) {
        this.isAuthenticated = z;
        this.authenticatedModality = biometricModality;
        this.needsUserConfirmation = z2;
        this.delay = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PromptAuthState)) {
            return false;
        }
        PromptAuthState promptAuthState = (PromptAuthState) obj;
        return this.isAuthenticated == promptAuthState.isAuthenticated && this.authenticatedModality == promptAuthState.authenticatedModality && this.needsUserConfirmation == promptAuthState.needsUserConfirmation && this.delay == promptAuthState.delay;
    }

    public final int hashCode() {
        return Long.hashCode(this.delay) + TransitionData$$ExternalSyntheticOutline0.m((this.authenticatedModality.hashCode() + (Boolean.hashCode(this.isAuthenticated) * 31)) * 31, 31, this.needsUserConfirmation);
    }

    public final boolean isAuthenticatedAndExplicitlyConfirmed() {
        return this.isAuthenticated && this.wasConfirmed;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PromptAuthState(isAuthenticated=");
        sb.append(this.isAuthenticated);
        sb.append(", authenticatedModality=");
        sb.append(this.authenticatedModality);
        sb.append(", needsUserConfirmation=");
        sb.append(this.needsUserConfirmation);
        sb.append(", delay=");
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.delay, ")", sb);
    }

    public /* synthetic */ PromptAuthState() {
        this(false, BiometricModality.None, false, 0L);
    }
}
