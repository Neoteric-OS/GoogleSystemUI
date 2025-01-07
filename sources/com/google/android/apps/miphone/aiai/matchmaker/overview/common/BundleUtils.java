package com.google.android.apps.miphone.aiai.matchmaker.overview.common;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppActionSuggestion;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppIcon;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppIconType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppPackage;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$ContentGroup;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$Contents;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$SearchSuggestion;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$Selection;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$Feedback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$QuickShareInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotActionFeedback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotFeedback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOp;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpFeedback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpStatus;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.InteractionContextParcelables$InteractionContext;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ParserParcelables$ParsedViewHierarchy;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ContentRect;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$DebugInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$InteractionType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$SetupInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Stats;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BundleUtils {
    public static Bundle createClassificationsRequest(String str, String str2, int i, long j, Bundle bundle, InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext, ContentParcelables$Contents contentParcelables$Contents) {
        Bundle bundle2;
        String str3;
        Bundle bundle3;
        Iterator it;
        Iterator it2;
        Iterator it3;
        Bundle bundle4;
        String str4;
        String str5;
        String str6;
        Iterator it4;
        String str7;
        Bundle bundle5;
        ContentParcelables$Contents contentParcelables$Contents2 = contentParcelables$Contents;
        Bundle bundle6 = new Bundle();
        bundle6.putString("PackageName", str);
        bundle6.putString("ActivityName", str2);
        bundle6.putInt("TaskId", i);
        bundle6.putLong("CaptureTimestampMs", j);
        bundle6.putBundle("AssistBundle", bundle);
        Bundle bundle7 = new Bundle();
        String str8 = "id";
        bundle7.putString("id", contentParcelables$Contents2.id);
        bundle7.putLong("screenSessionId", contentParcelables$Contents2.screenSessionId);
        String str9 = "opaquePayload";
        ArrayList<? extends Parcelable> arrayList = null;
        if (contentParcelables$Contents2.contentGroups == null) {
            bundle7.putParcelableArrayList("contentGroups", null);
            bundle2 = bundle6;
            str3 = "opaquePayload";
        } else {
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(((ArrayList) contentParcelables$Contents2.contentGroups).size());
            Iterator it5 = contentParcelables$Contents2.contentGroups.iterator();
            while (it5.hasNext()) {
                ContentParcelables$ContentGroup contentParcelables$ContentGroup = (ContentParcelables$ContentGroup) it5.next();
                if (contentParcelables$ContentGroup == null) {
                    arrayList2.add(arrayList);
                    bundle3 = bundle6;
                    str4 = str8;
                    str5 = str9;
                    it = it5;
                } else {
                    Bundle bundle8 = new Bundle();
                    if (contentParcelables$ContentGroup.contentRects == null) {
                        bundle8.putParcelableArrayList("contentRects", arrayList);
                    } else {
                        ArrayList<? extends Parcelable> arrayList3 = new ArrayList<>(((ArrayList) contentParcelables$ContentGroup.contentRects).size());
                        for (SuggestParcelables$ContentRect suggestParcelables$ContentRect : contentParcelables$ContentGroup.contentRects) {
                            if (suggestParcelables$ContentRect == null) {
                                arrayList3.add(arrayList);
                            } else {
                                arrayList3.add(suggestParcelables$ContentRect.writeToBundle());
                            }
                        }
                        bundle8.putParcelableArrayList("contentRects", arrayList3);
                    }
                    if (contentParcelables$ContentGroup.selections == null) {
                        bundle8.putParcelableArrayList("selections", arrayList);
                        bundle3 = bundle6;
                        it = it5;
                    } else {
                        ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>(((ArrayList) contentParcelables$ContentGroup.selections).size());
                        Iterator it6 = contentParcelables$ContentGroup.selections.iterator();
                        while (it6.hasNext()) {
                            ContentParcelables$Selection contentParcelables$Selection = (ContentParcelables$Selection) it6.next();
                            if (contentParcelables$Selection == null) {
                                arrayList4.add(arrayList);
                                bundle4 = bundle6;
                                it2 = it5;
                                it3 = it6;
                            } else {
                                Bundle bundle9 = new Bundle();
                                it2 = it5;
                                it3 = it6;
                                if (contentParcelables$Selection.rectIndices == null) {
                                    bundle9.putIntegerArrayList("rectIndices", null);
                                    bundle4 = bundle6;
                                } else {
                                    bundle4 = bundle6;
                                    bundle9.putIntegerArrayList("rectIndices", new ArrayList<>(contentParcelables$Selection.rectIndices));
                                }
                                bundle9.putString(str8, contentParcelables$Selection.id);
                                bundle9.putBoolean("isSmartSelection", contentParcelables$Selection.isSmartSelection);
                                bundle9.putInt("suggestedPresentationMode", contentParcelables$Selection.suggestedPresentationMode);
                                bundle9.putString(str9, contentParcelables$Selection.opaquePayload);
                                SuggestParcelables$InteractionType suggestParcelables$InteractionType = contentParcelables$Selection.interactionType;
                                if (suggestParcelables$InteractionType == null) {
                                    bundle9.putBundle("interactionType", null);
                                } else {
                                    Bundle bundle10 = new Bundle();
                                    bundle10.putInt("value", suggestParcelables$InteractionType.value);
                                    bundle9.putBundle("interactionType", bundle10);
                                }
                                bundle9.putInt("contentGroupIndex", contentParcelables$Selection.contentGroupIndex);
                                arrayList4.add(bundle9);
                            }
                            it5 = it2;
                            it6 = it3;
                            bundle6 = bundle4;
                            arrayList = null;
                        }
                        bundle3 = bundle6;
                        it = it5;
                        bundle8.putParcelableArrayList("selections", arrayList4);
                    }
                    bundle8.putString("text", contentParcelables$ContentGroup.text);
                    bundle8.putInt("numLines", contentParcelables$ContentGroup.numLines);
                    if (contentParcelables$ContentGroup.searchSuggestions == null) {
                        bundle8.putParcelableArrayList("searchSuggestions", null);
                        str4 = str8;
                        str5 = str9;
                    } else {
                        ArrayList<? extends Parcelable> arrayList5 = new ArrayList<>(((ArrayList) contentParcelables$ContentGroup.searchSuggestions).size());
                        Iterator it7 = contentParcelables$ContentGroup.searchSuggestions.iterator();
                        while (it7.hasNext()) {
                            ContentParcelables$SearchSuggestion contentParcelables$SearchSuggestion = (ContentParcelables$SearchSuggestion) it7.next();
                            if (contentParcelables$SearchSuggestion == null) {
                                arrayList5.add(null);
                                str6 = str8;
                                str7 = str9;
                                it4 = it7;
                            } else {
                                Bundle bundle11 = new Bundle();
                                ContentParcelables$AppActionSuggestion contentParcelables$AppActionSuggestion = contentParcelables$SearchSuggestion.appActionSuggestion;
                                if (contentParcelables$AppActionSuggestion == null) {
                                    bundle11.putBundle("appActionSuggestion", null);
                                    str6 = str8;
                                    it4 = it7;
                                } else {
                                    Bundle bundle12 = new Bundle();
                                    str6 = str8;
                                    it4 = it7;
                                    bundle12.putString("displayText", contentParcelables$AppActionSuggestion.displayText);
                                    bundle12.putString("subtitle", contentParcelables$AppActionSuggestion.subtitle);
                                    bundle11.putBundle("appActionSuggestion", bundle12);
                                }
                                ContentParcelables$AppIcon contentParcelables$AppIcon = contentParcelables$SearchSuggestion.appIcon;
                                if (contentParcelables$AppIcon == null) {
                                    bundle11.putBundle("appIcon", null);
                                    str7 = str9;
                                } else {
                                    Bundle bundle13 = new Bundle();
                                    bundle13.putString("iconUri", contentParcelables$AppIcon.iconUri);
                                    ContentParcelables$AppPackage contentParcelables$AppPackage = contentParcelables$AppIcon.appPackage;
                                    if (contentParcelables$AppPackage == null) {
                                        str7 = str9;
                                        bundle13.putBundle("appPackage", null);
                                    } else {
                                        str7 = str9;
                                        Bundle bundle14 = new Bundle();
                                        bundle14.putString("packageName", contentParcelables$AppPackage.packageName);
                                        bundle13.putBundle("appPackage", bundle14);
                                    }
                                    ContentParcelables$AppIconType contentParcelables$AppIconType = contentParcelables$AppIcon.appIconType;
                                    if (contentParcelables$AppIconType == null) {
                                        bundle13.putBundle("appIconType", null);
                                    } else {
                                        Bundle bundle15 = new Bundle();
                                        bundle15.putInt("value", contentParcelables$AppIconType.value);
                                        bundle13.putBundle("appIconType", bundle15);
                                    }
                                    bundle11.putBundle("appIcon", bundle13);
                                }
                                ContentParcelables$AppPackage contentParcelables$AppPackage2 = contentParcelables$SearchSuggestion.executionInfo;
                                if (contentParcelables$AppPackage2 == null) {
                                    bundle11.putBundle("executionInfo", null);
                                } else {
                                    Bundle bundle16 = new Bundle();
                                    bundle16.putString("deeplinkUri", contentParcelables$AppPackage2.packageName);
                                    bundle11.putBundle("executionInfo", bundle16);
                                }
                                bundle11.putFloat("confScore", contentParcelables$SearchSuggestion.confScore);
                                arrayList5.add(bundle11);
                            }
                            str8 = str6;
                            it7 = it4;
                            str9 = str7;
                        }
                        str4 = str8;
                        str5 = str9;
                        bundle8.putParcelableArrayList("searchSuggestions", arrayList5);
                    }
                    arrayList2.add(bundle8);
                }
                it5 = it;
                str8 = str4;
                str9 = str5;
                bundle6 = bundle3;
                arrayList = null;
            }
            bundle2 = bundle6;
            str3 = str9;
            bundle7.putParcelableArrayList("contentGroups", arrayList2);
            contentParcelables$Contents2 = contentParcelables$Contents;
        }
        SuggestParcelables$Stats suggestParcelables$Stats = contentParcelables$Contents2.stats;
        if (suggestParcelables$Stats == null) {
            bundle5 = null;
            bundle7.putBundle("stats", null);
        } else {
            bundle5 = null;
            bundle7.putBundle("stats", suggestParcelables$Stats.writeToBundle());
        }
        if (contentParcelables$Contents2.debugInfo == null) {
            bundle7.putBundle("debugInfo", bundle5);
        } else {
            bundle7.putBundle("debugInfo", new Bundle());
        }
        bundle7.putString(str3, contentParcelables$Contents2.opaquePayload);
        SuggestParcelables$SetupInfo suggestParcelables$SetupInfo = contentParcelables$Contents2.setupInfo;
        if (suggestParcelables$SetupInfo == null) {
            bundle7.putBundle("setupInfo", null);
        } else {
            bundle7.putBundle("setupInfo", suggestParcelables$SetupInfo.writeToBundle());
        }
        bundle7.putString("contentUri", contentParcelables$Contents2.contentUri);
        Bundle bundle17 = bundle2;
        bundle17.putBundle("Contents", bundle7);
        bundle17.putBundle("InteractionContext", interactionContextParcelables$InteractionContext.writeToBundle());
        bundle17.putInt("Version", 4);
        bundle17.putInt("BundleTypedVersion", 3);
        return bundle17;
    }

    public static Bundle createFeedbackRequest(FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        Bundle bundle4 = null;
        if (feedbackParcelables$FeedbackBatch.feedback == null) {
            bundle3.putParcelableArrayList("feedback", null);
        } else {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(((ArrayList) feedbackParcelables$FeedbackBatch.feedback).size());
            for (FeedbackParcelables$Feedback feedbackParcelables$Feedback : feedbackParcelables$FeedbackBatch.feedback) {
                if (feedbackParcelables$Feedback == null) {
                    arrayList.add(bundle4);
                    bundle = bundle4;
                } else {
                    Bundle bundle5 = new Bundle();
                    if (feedbackParcelables$Feedback.feedback != null) {
                        bundle5.putInt("feedback#tag", 11);
                        FeedbackParcelables$ScreenshotFeedback feedbackParcelables$ScreenshotFeedback = feedbackParcelables$Feedback.feedback;
                        if (feedbackParcelables$ScreenshotFeedback == null) {
                            bundle5.putBundle("feedback", bundle4);
                        } else {
                            Bundle bundle6 = new Bundle();
                            if (feedbackParcelables$ScreenshotFeedback.screenshotFeedback instanceof FeedbackParcelables$ScreenshotOpFeedback) {
                                bundle6.putInt("screenshotFeedback#tag", 2);
                                FeedbackParcelables$ScreenshotOpFeedback feedbackParcelables$ScreenshotOpFeedback = (FeedbackParcelables$ScreenshotOpFeedback) feedbackParcelables$ScreenshotFeedback.screenshotFeedback;
                                if (feedbackParcelables$ScreenshotOpFeedback == null) {
                                    bundle6.putBundle("screenshotFeedback", bundle4);
                                } else {
                                    Bundle bundle7 = new Bundle();
                                    FeedbackParcelables$ScreenshotOp feedbackParcelables$ScreenshotOp = feedbackParcelables$ScreenshotOpFeedback.op;
                                    Bundle bundle8 = new Bundle();
                                    bundle8.putInt("value", feedbackParcelables$ScreenshotOp.value);
                                    bundle7.putBundle("op", bundle8);
                                    FeedbackParcelables$ScreenshotOpStatus feedbackParcelables$ScreenshotOpStatus = feedbackParcelables$ScreenshotOpFeedback.status;
                                    Bundle bundle9 = new Bundle();
                                    bundle9.putInt("value", feedbackParcelables$ScreenshotOpStatus.value);
                                    bundle7.putBundle("status", bundle9);
                                    bundle7.putLong("durationMs", feedbackParcelables$ScreenshotOpFeedback.durationMs);
                                    bundle6.putBundle("screenshotFeedback", bundle7);
                                }
                            }
                            if (feedbackParcelables$ScreenshotFeedback.screenshotFeedback instanceof FeedbackParcelables$ScreenshotActionFeedback) {
                                bundle6.putInt("screenshotFeedback#tag", 3);
                                FeedbackParcelables$ScreenshotActionFeedback feedbackParcelables$ScreenshotActionFeedback = (FeedbackParcelables$ScreenshotActionFeedback) feedbackParcelables$ScreenshotFeedback.screenshotFeedback;
                                if (feedbackParcelables$ScreenshotActionFeedback == null) {
                                    bundle6.putBundle("screenshotFeedback", null);
                                } else {
                                    Bundle bundle10 = new Bundle();
                                    bundle10.putString("actionType", feedbackParcelables$ScreenshotActionFeedback.actionType);
                                    bundle10.putBoolean("isSmartActions", true);
                                    FeedbackParcelables$QuickShareInfo feedbackParcelables$QuickShareInfo = feedbackParcelables$ScreenshotActionFeedback.quickShareInfo;
                                    if (feedbackParcelables$QuickShareInfo == null) {
                                        bundle10.putBundle("quickShareInfo", null);
                                    } else {
                                        Bundle bundle11 = new Bundle();
                                        bundle11.putString("contentUri", feedbackParcelables$QuickShareInfo.contentUri);
                                        bundle11.putString("targetPackageName", feedbackParcelables$QuickShareInfo.targetPackageName);
                                        bundle11.putString("targetClassName", feedbackParcelables$QuickShareInfo.targetClassName);
                                        bundle11.putString("targetShortcutId", feedbackParcelables$QuickShareInfo.targetShortcutId);
                                        bundle10.putBundle("quickShareInfo", bundle11);
                                    }
                                    bundle6.putBundle("screenshotFeedback", bundle10);
                                }
                            }
                            bundle6.putString("screenshotId", feedbackParcelables$ScreenshotFeedback.screenshotId);
                            bundle5.putBundle("feedback", bundle6);
                        }
                    }
                    bundle = null;
                    bundle5.putString("id", null);
                    bundle5.putLong("timestampMs", 0L);
                    bundle5.putBundle("suggestionAction", null);
                    bundle5.putBundle("interactionContext", null);
                    arrayList.add(bundle5);
                }
                bundle4 = bundle;
            }
            bundle3.putParcelableArrayList("feedback", arrayList);
        }
        bundle3.putLong("screenSessionId", feedbackParcelables$FeedbackBatch.screenSessionId);
        bundle3.putString("overviewSessionId", feedbackParcelables$FeedbackBatch.overviewSessionId);
        bundle2.putBundle("FeedbackBatch", bundle3);
        bundle2.putInt("Version", 4);
        bundle2.putInt("BundleTypedVersion", 6);
        return bundle2;
    }

    public static Bundle createSelectionsRequest(String str, String str2, int i, long j, InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext, Bundle bundle, ParserParcelables$ParsedViewHierarchy parserParcelables$ParsedViewHierarchy) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("PackageName", str);
        bundle2.putString("ActivityName", str2);
        bundle2.putInt("TaskId", i);
        bundle2.putLong("CaptureTimestampMs", j);
        bundle2.putBundle("InteractionContext", interactionContextParcelables$InteractionContext.writeToBundle());
        bundle2.putBundle("AssistBundle", bundle);
        if (parserParcelables$ParsedViewHierarchy == null) {
            bundle2.putBundle("ParsedViewHierarchy", null);
        } else {
            Bundle bundle3 = new Bundle();
            bundle3.putLong("acquisitionStartTime", 0L);
            bundle3.putLong("acquisitionEndTime", 0L);
            bundle3.putBoolean("isHomeActivity", false);
            bundle3.putParcelableArrayList("windows", null);
            bundle3.putBoolean("hasKnownIssues", false);
            bundle3.putString("packageName", null);
            bundle3.putString("activityClassName", null);
            bundle3.putBundle("insetsRect", null);
            bundle2.putBundle("ParsedViewHierarchy", bundle3);
        }
        bundle2.putInt("Version", 4);
        bundle2.putInt("BundleTypedVersion", 3);
        return bundle2;
    }

    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$InteractionType, java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppActionSuggestion, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppIcon, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppPackage, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v8 */
    public static ContentParcelables$Contents extractContents(Bundle bundle) {
        SuggestParcelables$DebugInfo suggestParcelables$DebugInfo;
        Bundle bundle2 = bundle.getBundle("Contents");
        if (bundle2 == null) {
            return new ContentParcelables$Contents();
        }
        ContentParcelables$Contents contentParcelables$Contents = new ContentParcelables$Contents();
        if (bundle2.containsKey("id")) {
            contentParcelables$Contents.id = bundle2.getString("id");
        }
        if (bundle2.containsKey("screenSessionId")) {
            contentParcelables$Contents.screenSessionId = bundle2.getLong("screenSessionId");
        }
        ?? r6 = 0;
        if (bundle2.containsKey("contentGroups")) {
            ArrayList parcelableArrayList = bundle2.getParcelableArrayList("contentGroups");
            if (parcelableArrayList == null) {
                contentParcelables$Contents.contentGroups = null;
            } else {
                contentParcelables$Contents.contentGroups = new ArrayList(parcelableArrayList.size());
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle3 = (Bundle) it.next();
                    if (bundle3 == null) {
                        contentParcelables$Contents.contentGroups.add(r6);
                    } else {
                        List list = contentParcelables$Contents.contentGroups;
                        ContentParcelables$ContentGroup contentParcelables$ContentGroup = new ContentParcelables$ContentGroup();
                        if (bundle3.containsKey("contentRects")) {
                            ArrayList parcelableArrayList2 = bundle3.getParcelableArrayList("contentRects");
                            if (parcelableArrayList2 == null) {
                                contentParcelables$ContentGroup.contentRects = r6;
                            } else {
                                contentParcelables$ContentGroup.contentRects = new ArrayList(parcelableArrayList2.size());
                                Iterator it2 = parcelableArrayList2.iterator();
                                while (it2.hasNext()) {
                                    Bundle bundle4 = (Bundle) it2.next();
                                    if (bundle4 == null) {
                                        contentParcelables$ContentGroup.contentRects.add(r6);
                                    } else {
                                        contentParcelables$ContentGroup.contentRects.add(SuggestParcelables$ContentRect.create(bundle4));
                                    }
                                }
                            }
                        }
                        if (bundle3.containsKey("selections")) {
                            ArrayList parcelableArrayList3 = bundle3.getParcelableArrayList("selections");
                            if (parcelableArrayList3 == null) {
                                contentParcelables$ContentGroup.selections = r6;
                            } else {
                                contentParcelables$ContentGroup.selections = new ArrayList(parcelableArrayList3.size());
                                Iterator it3 = parcelableArrayList3.iterator();
                                while (it3.hasNext()) {
                                    Bundle bundle5 = (Bundle) it3.next();
                                    if (bundle5 == null) {
                                        contentParcelables$ContentGroup.selections.add(r6);
                                    } else {
                                        List list2 = contentParcelables$ContentGroup.selections;
                                        ContentParcelables$Selection contentParcelables$Selection = new ContentParcelables$Selection();
                                        if (bundle5.containsKey("rectIndices")) {
                                            contentParcelables$Selection.rectIndices = bundle5.getIntegerArrayList("rectIndices");
                                        }
                                        if (bundle5.containsKey("id")) {
                                            contentParcelables$Selection.id = bundle5.getString("id");
                                        }
                                        if (bundle5.containsKey("isSmartSelection")) {
                                            contentParcelables$Selection.isSmartSelection = bundle5.getBoolean("isSmartSelection");
                                        }
                                        if (bundle5.containsKey("suggestedPresentationMode")) {
                                            contentParcelables$Selection.suggestedPresentationMode = bundle5.getInt("suggestedPresentationMode");
                                        }
                                        if (bundle5.containsKey("opaquePayload")) {
                                            contentParcelables$Selection.opaquePayload = bundle5.getString("opaquePayload");
                                        }
                                        if (bundle5.containsKey("interactionType")) {
                                            Bundle bundle6 = bundle5.getBundle("interactionType");
                                            if (bundle6 == null) {
                                                contentParcelables$Selection.interactionType = r6;
                                            } else {
                                                contentParcelables$Selection.interactionType = SuggestParcelables$InteractionType.create(bundle6);
                                            }
                                        }
                                        if (bundle5.containsKey("contentGroupIndex")) {
                                            contentParcelables$Selection.contentGroupIndex = bundle5.getInt("contentGroupIndex");
                                        }
                                        list2.add(contentParcelables$Selection);
                                    }
                                }
                            }
                        }
                        if (bundle3.containsKey("text")) {
                            contentParcelables$ContentGroup.text = bundle3.getString("text");
                        }
                        if (bundle3.containsKey("numLines")) {
                            contentParcelables$ContentGroup.numLines = bundle3.getInt("numLines");
                        }
                        if (bundle3.containsKey("searchSuggestions")) {
                            ArrayList parcelableArrayList4 = bundle3.getParcelableArrayList("searchSuggestions");
                            if (parcelableArrayList4 == null) {
                                contentParcelables$ContentGroup.searchSuggestions = r6;
                            } else {
                                contentParcelables$ContentGroup.searchSuggestions = new ArrayList(parcelableArrayList4.size());
                                Iterator it4 = parcelableArrayList4.iterator();
                                while (it4.hasNext()) {
                                    Bundle bundle7 = (Bundle) it4.next();
                                    if (bundle7 == null) {
                                        contentParcelables$ContentGroup.searchSuggestions.add(r6);
                                    } else {
                                        List list3 = contentParcelables$ContentGroup.searchSuggestions;
                                        ContentParcelables$SearchSuggestion contentParcelables$SearchSuggestion = new ContentParcelables$SearchSuggestion();
                                        if (bundle7.containsKey("appActionSuggestion")) {
                                            Bundle bundle8 = bundle7.getBundle("appActionSuggestion");
                                            if (bundle8 == null) {
                                                contentParcelables$SearchSuggestion.appActionSuggestion = r6;
                                            } else {
                                                ContentParcelables$AppActionSuggestion contentParcelables$AppActionSuggestion = new ContentParcelables$AppActionSuggestion();
                                                if (bundle8.containsKey("displayText")) {
                                                    contentParcelables$AppActionSuggestion.displayText = bundle8.getString("displayText");
                                                }
                                                if (bundle8.containsKey("subtitle")) {
                                                    contentParcelables$AppActionSuggestion.subtitle = bundle8.getString("subtitle");
                                                }
                                                contentParcelables$SearchSuggestion.appActionSuggestion = contentParcelables$AppActionSuggestion;
                                            }
                                        }
                                        if (bundle7.containsKey("appIcon")) {
                                            Bundle bundle9 = bundle7.getBundle("appIcon");
                                            if (bundle9 == null) {
                                                contentParcelables$SearchSuggestion.appIcon = r6;
                                            } else {
                                                ContentParcelables$AppIcon contentParcelables$AppIcon = new ContentParcelables$AppIcon();
                                                if (bundle9.containsKey("iconUri")) {
                                                    contentParcelables$AppIcon.iconUri = bundle9.getString("iconUri");
                                                }
                                                if (bundle9.containsKey("appPackage")) {
                                                    Bundle bundle10 = bundle9.getBundle("appPackage");
                                                    if (bundle10 == null) {
                                                        contentParcelables$AppIcon.appPackage = r6;
                                                    } else {
                                                        ContentParcelables$AppPackage contentParcelables$AppPackage = new ContentParcelables$AppPackage();
                                                        if (bundle10.containsKey("packageName")) {
                                                            contentParcelables$AppPackage.packageName = bundle10.getString("packageName");
                                                        }
                                                        contentParcelables$AppIcon.appPackage = contentParcelables$AppPackage;
                                                    }
                                                }
                                                if (bundle9.containsKey("appIconType")) {
                                                    Bundle bundle11 = bundle9.getBundle("appIconType");
                                                    if (bundle11 == null) {
                                                        contentParcelables$AppIcon.appIconType = null;
                                                    } else {
                                                        int i = bundle11.getInt("value");
                                                        contentParcelables$AppIcon.appIconType = i == 0 ? ContentParcelables$AppIconType.UNDEFINED : i == 1 ? ContentParcelables$AppIconType.URI : i == 2 ? ContentParcelables$AppIconType.DRAWABLE : null;
                                                    }
                                                }
                                                contentParcelables$SearchSuggestion.appIcon = contentParcelables$AppIcon;
                                            }
                                        }
                                        if (bundle7.containsKey("executionInfo")) {
                                            Bundle bundle12 = bundle7.getBundle("executionInfo");
                                            if (bundle12 == null) {
                                                contentParcelables$SearchSuggestion.executionInfo = null;
                                            } else {
                                                ContentParcelables$AppPackage contentParcelables$AppPackage2 = new ContentParcelables$AppPackage();
                                                if (bundle12.containsKey("deeplinkUri")) {
                                                    contentParcelables$AppPackage2.packageName = bundle12.getString("deeplinkUri");
                                                }
                                                contentParcelables$SearchSuggestion.executionInfo = contentParcelables$AppPackage2;
                                            }
                                        }
                                        if (bundle7.containsKey("confScore")) {
                                            contentParcelables$SearchSuggestion.confScore = bundle7.getFloat("confScore");
                                        }
                                        list3.add(contentParcelables$SearchSuggestion);
                                        r6 = 0;
                                    }
                                }
                            }
                        }
                        list.add(contentParcelables$ContentGroup);
                        r6 = 0;
                    }
                }
            }
        }
        if (bundle2.containsKey("stats")) {
            Bundle bundle13 = bundle2.getBundle("stats");
            if (bundle13 == null) {
                suggestParcelables$DebugInfo = null;
                contentParcelables$Contents.stats = null;
            } else {
                suggestParcelables$DebugInfo = null;
                contentParcelables$Contents.stats = SuggestParcelables$Stats.create(bundle13);
            }
        } else {
            suggestParcelables$DebugInfo = null;
        }
        if (bundle2.containsKey("debugInfo")) {
            if (bundle2.getBundle("debugInfo") == null) {
                contentParcelables$Contents.debugInfo = suggestParcelables$DebugInfo;
            } else {
                contentParcelables$Contents.debugInfo = new SuggestParcelables$DebugInfo();
            }
        }
        if (bundle2.containsKey("opaquePayload")) {
            contentParcelables$Contents.opaquePayload = bundle2.getString("opaquePayload");
        }
        if (bundle2.containsKey("setupInfo")) {
            Bundle bundle14 = bundle2.getBundle("setupInfo");
            if (bundle14 == null) {
                contentParcelables$Contents.setupInfo = null;
            } else {
                contentParcelables$Contents.setupInfo = SuggestParcelables$SetupInfo.create(bundle14);
            }
        }
        if (bundle2.containsKey("contentUri")) {
            contentParcelables$Contents.contentUri = bundle2.getString("contentUri");
        }
        return contentParcelables$Contents;
    }
}
