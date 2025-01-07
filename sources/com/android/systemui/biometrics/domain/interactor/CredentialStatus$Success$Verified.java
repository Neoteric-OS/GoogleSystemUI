package com.android.systemui.biometrics.domain.interactor;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CredentialStatus$Success$Verified implements CredentialStatus {
    public final byte[] hat;

    public CredentialStatus$Success$Verified(byte[] bArr) {
        this.hat = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CredentialStatus$Success$Verified) && Intrinsics.areEqual(this.hat, ((CredentialStatus$Success$Verified) obj).hat);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.hat);
    }

    public final String toString() {
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Verified(hat=", Arrays.toString(this.hat), ")");
    }
}
