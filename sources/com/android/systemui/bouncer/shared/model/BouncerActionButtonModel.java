package com.android.systemui.bouncer.shared.model;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerActionButtonModel {
    public final String label;
    public final Lambda onClick;
    public final Function0 onLongClick;

    /* JADX WARN: Multi-variable type inference failed */
    public BouncerActionButtonModel(String str, Function0 function0, Function0 function02) {
        this.label = str;
        this.onClick = (Lambda) function0;
        this.onLongClick = function02;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BouncerActionButtonModel)) {
            return false;
        }
        BouncerActionButtonModel bouncerActionButtonModel = (BouncerActionButtonModel) obj;
        return Intrinsics.areEqual(this.label, bouncerActionButtonModel.label) && this.onClick.equals(bouncerActionButtonModel.onClick) && Intrinsics.areEqual(this.onLongClick, bouncerActionButtonModel.onLongClick);
    }

    public final int hashCode() {
        int hashCode = (this.onClick.hashCode() + (this.label.hashCode() * 31)) * 31;
        Function0 function0 = this.onLongClick;
        return hashCode + (function0 == null ? 0 : function0.hashCode());
    }

    public final String toString() {
        return "BouncerActionButtonModel(label=" + this.label + ", onClick=" + this.onClick + ", onLongClick=" + this.onLongClick + ")";
    }
}
