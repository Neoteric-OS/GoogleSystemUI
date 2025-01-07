package com.android.systemui.bouncer.shared.model;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerMessageModel {
    public final Message message;
    public final Message secondaryMessage;

    public BouncerMessageModel(Message message, Message message2) {
        this.message = message;
        this.secondaryMessage = message2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BouncerMessageModel)) {
            return false;
        }
        BouncerMessageModel bouncerMessageModel = (BouncerMessageModel) obj;
        return Intrinsics.areEqual(this.message, bouncerMessageModel.message) && Intrinsics.areEqual(this.secondaryMessage, bouncerMessageModel.secondaryMessage);
    }

    public final int hashCode() {
        Message message = this.message;
        int hashCode = (message == null ? 0 : message.hashCode()) * 31;
        Message message2 = this.secondaryMessage;
        return hashCode + (message2 != null ? message2.hashCode() : 0);
    }

    public final String toString() {
        return "BouncerMessageModel(message=" + this.message + ", secondaryMessage=" + this.secondaryMessage + ")";
    }
}
