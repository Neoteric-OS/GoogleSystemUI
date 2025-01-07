package com.google.android.msdl.data.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FeedbackLevel {
    public static final /* synthetic */ FeedbackLevel[] $VALUES;
    public static final FeedbackLevel DEFAULT;
    public static final FeedbackLevel EXPRESSIVE;
    public static final FeedbackLevel MINIMAL;

    static {
        FeedbackLevel feedbackLevel = new FeedbackLevel("NO_FEEDBACK", 0);
        FeedbackLevel feedbackLevel2 = new FeedbackLevel("MINIMAL", 1);
        MINIMAL = feedbackLevel2;
        FeedbackLevel feedbackLevel3 = new FeedbackLevel("DEFAULT", 2);
        DEFAULT = feedbackLevel3;
        FeedbackLevel feedbackLevel4 = new FeedbackLevel("EXPRESSIVE", 3);
        EXPRESSIVE = feedbackLevel4;
        FeedbackLevel[] feedbackLevelArr = {feedbackLevel, feedbackLevel2, feedbackLevel3, feedbackLevel4};
        $VALUES = feedbackLevelArr;
        EnumEntriesKt.enumEntries(feedbackLevelArr);
    }

    public static FeedbackLevel valueOf(String str) {
        return (FeedbackLevel) Enum.valueOf(FeedbackLevel.class, str);
    }

    public static FeedbackLevel[] values() {
        return (FeedbackLevel[]) $VALUES.clone();
    }
}
