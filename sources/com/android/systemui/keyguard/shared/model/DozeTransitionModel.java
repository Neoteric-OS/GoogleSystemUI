package com.android.systemui.keyguard.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeTransitionModel {
    public final DozeStateModel from;
    public final DozeStateModel to;

    public DozeTransitionModel(DozeStateModel dozeStateModel, DozeStateModel dozeStateModel2) {
        this.from = dozeStateModel;
        this.to = dozeStateModel2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DozeTransitionModel)) {
            return false;
        }
        DozeTransitionModel dozeTransitionModel = (DozeTransitionModel) obj;
        return this.from == dozeTransitionModel.from && this.to == dozeTransitionModel.to;
    }

    public final int hashCode() {
        return this.to.hashCode() + (this.from.hashCode() * 31);
    }

    public final String toString() {
        return "DozeTransitionModel(from=" + this.from + ", to=" + this.to + ")";
    }
}
