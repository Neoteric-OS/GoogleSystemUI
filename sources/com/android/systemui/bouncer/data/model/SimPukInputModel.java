package com.android.systemui.bouncer.data.model;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SimPukInputModel {
    public final String enteredSimPin;
    public final String enteredSimPuk;

    public SimPukInputModel(String str, String str2) {
        this.enteredSimPuk = str;
        this.enteredSimPin = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimPukInputModel)) {
            return false;
        }
        SimPukInputModel simPukInputModel = (SimPukInputModel) obj;
        return Intrinsics.areEqual(this.enteredSimPuk, simPukInputModel.enteredSimPuk) && Intrinsics.areEqual(this.enteredSimPin, simPukInputModel.enteredSimPin);
    }

    public final int hashCode() {
        String str = this.enteredSimPuk;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.enteredSimPin;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SimPukInputModel(enteredSimPuk=");
        sb.append(this.enteredSimPuk);
        sb.append(", enteredSimPin=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.enteredSimPin, ")");
    }
}
