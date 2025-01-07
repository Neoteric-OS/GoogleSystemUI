package com.android.systemui.statusbar.notification.row.ui.viewmodel;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SingleLineViewModel {
    public final CharSequence contentText;
    public final ConversationData conversationData;
    public final CharSequence titleText;

    public SingleLineViewModel(CharSequence charSequence, CharSequence charSequence2, ConversationData conversationData) {
        this.titleText = charSequence;
        this.contentText = charSequence2;
        this.conversationData = conversationData;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SingleLineViewModel)) {
            return false;
        }
        SingleLineViewModel singleLineViewModel = (SingleLineViewModel) obj;
        return Intrinsics.areEqual(this.titleText, singleLineViewModel.titleText) && Intrinsics.areEqual(this.contentText, singleLineViewModel.contentText) && Intrinsics.areEqual(this.conversationData, singleLineViewModel.conversationData);
    }

    public final int hashCode() {
        CharSequence charSequence = this.titleText;
        int hashCode = (charSequence == null ? 0 : charSequence.hashCode()) * 31;
        CharSequence charSequence2 = this.contentText;
        int hashCode2 = (hashCode + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
        ConversationData conversationData = this.conversationData;
        return hashCode2 + (conversationData != null ? conversationData.hashCode() : 0);
    }

    public final String toString() {
        return "SingleLineViewModel(titleText=" + ((Object) this.titleText) + ", contentText=" + ((Object) this.contentText) + ", conversationData=" + this.conversationData + ")";
    }
}
