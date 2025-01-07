package com.android.systemui.deviceentry.data.repository;

import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthenticationRequest {
    public final boolean fallbackToDetection;
    public final FaceAuthUiEvent uiEvent;

    public AuthenticationRequest(FaceAuthUiEvent faceAuthUiEvent, boolean z) {
        this.uiEvent = faceAuthUiEvent;
        this.fallbackToDetection = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthenticationRequest)) {
            return false;
        }
        AuthenticationRequest authenticationRequest = (AuthenticationRequest) obj;
        return this.uiEvent == authenticationRequest.uiEvent && this.fallbackToDetection == authenticationRequest.fallbackToDetection;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.fallbackToDetection) + (this.uiEvent.hashCode() * 31);
    }

    public final String toString() {
        return "AuthenticationRequest(uiEvent=" + this.uiEvent + ", fallbackToDetection=" + this.fallbackToDetection + ")";
    }
}
