package com.android.systemui.education.ui.viewmodel;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextualEduToastViewModel extends ContextualEduContentViewModel {
    public final String message;
    public final int userId;

    public ContextualEduToastViewModel(String str, int i) {
        this.message = str;
        this.userId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContextualEduToastViewModel)) {
            return false;
        }
        ContextualEduToastViewModel contextualEduToastViewModel = (ContextualEduToastViewModel) obj;
        return Intrinsics.areEqual(this.message, contextualEduToastViewModel.message) && this.userId == contextualEduToastViewModel.userId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userId) + (this.message.hashCode() * 31);
    }

    public final String toString() {
        return "ContextualEduToastViewModel(message=" + this.message + ", userId=" + this.userId + ")";
    }
}
