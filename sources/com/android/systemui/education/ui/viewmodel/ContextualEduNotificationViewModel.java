package com.android.systemui.education.ui.viewmodel;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextualEduNotificationViewModel extends ContextualEduContentViewModel {
    public final String message;
    public final String title;
    public final int userId;

    public ContextualEduNotificationViewModel(String str, String str2, int i) {
        this.title = str;
        this.message = str2;
        this.userId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContextualEduNotificationViewModel)) {
            return false;
        }
        ContextualEduNotificationViewModel contextualEduNotificationViewModel = (ContextualEduNotificationViewModel) obj;
        return Intrinsics.areEqual(this.title, contextualEduNotificationViewModel.title) && Intrinsics.areEqual(this.message, contextualEduNotificationViewModel.message) && this.userId == contextualEduNotificationViewModel.userId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userId) + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.message, this.title.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ContextualEduNotificationViewModel(title=");
        sb.append(this.title);
        sb.append(", message=");
        sb.append(this.message);
        sb.append(", userId=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.userId, ")");
    }
}
