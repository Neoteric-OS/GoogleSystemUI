package com.android.systemui.bouncer.shared.model;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Message {
    public Map formatterArgs;
    public final String message;
    public final Integer messageResId;

    public Message(int i, Integer num, String str) {
        str = (i & 1) != 0 ? null : str;
        num = (i & 2) != 0 ? null : num;
        this.message = str;
        this.messageResId = num;
        this.formatterArgs = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        return Intrinsics.areEqual(this.message, message.message) && Intrinsics.areEqual(this.messageResId, message.messageResId) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual(this.formatterArgs, message.formatterArgs);
    }

    public final int hashCode() {
        String str = this.message;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.messageResId;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 961;
        Map map = this.formatterArgs;
        return Boolean.hashCode(false) + ((hashCode2 + (map == null ? 0 : map.hashCode())) * 31);
    }

    public final String toString() {
        return "Message(message=" + this.message + ", messageResId=" + this.messageResId + ", colorState=null, formatterArgs=" + this.formatterArgs + ", animate=false)";
    }
}
