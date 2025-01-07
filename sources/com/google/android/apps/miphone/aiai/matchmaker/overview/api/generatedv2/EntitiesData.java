package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class EntitiesData implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    private final Map bitmapMap;
    private final SuggestParcelables$Entities entities;
    private final Map pendingIntentMap;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.EntitiesData$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return EntitiesData.read(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new EntitiesData[i];
        }
    }

    private EntitiesData(SuggestParcelables$Entities suggestParcelables$Entities, Map map, Map map2) {
        this.entities = suggestParcelables$Entities;
        this.bitmapMap = map;
        this.pendingIntentMap = map2;
    }

    public static EntitiesData create(SuggestParcelables$Entities suggestParcelables$Entities) {
        return new EntitiesData(suggestParcelables$Entities, new HashMap(), new HashMap());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v41, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Action, java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r7v42 */
    /* JADX WARN: Type inference failed for: r7v45 */
    public static EntitiesData read(Parcel parcel) {
        SuggestParcelables$DebugInfo suggestParcelables$DebugInfo;
        Bundle readBundle = parcel.readBundle();
        SuggestParcelables$Entities suggestParcelables$Entities = new SuggestParcelables$Entities();
        if (readBundle.containsKey("id")) {
            suggestParcelables$Entities.id = readBundle.getString("id");
        }
        if (readBundle.containsKey("success")) {
            suggestParcelables$Entities.success = readBundle.getBoolean("success");
        }
        ?? r7 = 0;
        if (readBundle.containsKey("entities")) {
            ArrayList parcelableArrayList = readBundle.getParcelableArrayList("entities");
            if (parcelableArrayList == null) {
                suggestParcelables$Entities.entities = null;
            } else {
                suggestParcelables$Entities.entities = new ArrayList(parcelableArrayList.size());
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle = (Bundle) it.next();
                    if (bundle == null) {
                        suggestParcelables$Entities.entities.add(r7);
                    } else {
                        List list = suggestParcelables$Entities.entities;
                        SuggestParcelables$Entity suggestParcelables$Entity = new SuggestParcelables$Entity();
                        if (bundle.containsKey("id")) {
                            suggestParcelables$Entity.id = bundle.getString("id");
                        }
                        if (bundle.containsKey("actions")) {
                            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("actions");
                            if (parcelableArrayList2 == null) {
                                suggestParcelables$Entity.actions = r7;
                            } else {
                                suggestParcelables$Entity.actions = new ArrayList(parcelableArrayList2.size());
                                Iterator it2 = parcelableArrayList2.iterator();
                                while (it2.hasNext()) {
                                    Bundle bundle2 = (Bundle) it2.next();
                                    if (bundle2 == null) {
                                        suggestParcelables$Entity.actions.add(r7);
                                    } else {
                                        List list2 = suggestParcelables$Entity.actions;
                                        SuggestParcelables$ActionGroup suggestParcelables$ActionGroup = new SuggestParcelables$ActionGroup();
                                        if (bundle2.containsKey("id")) {
                                            suggestParcelables$ActionGroup.id = bundle2.getString("id");
                                        }
                                        if (bundle2.containsKey("displayName")) {
                                            suggestParcelables$ActionGroup.displayName = bundle2.getString("displayName");
                                        }
                                        if (bundle2.containsKey("mainAction")) {
                                            Bundle bundle3 = bundle2.getBundle("mainAction");
                                            if (bundle3 == null) {
                                                suggestParcelables$ActionGroup.mainAction = r7;
                                            } else {
                                                suggestParcelables$ActionGroup.mainAction = SuggestParcelables$Action.create(bundle3);
                                            }
                                        }
                                        if (bundle2.containsKey("alternateActions")) {
                                            ArrayList parcelableArrayList3 = bundle2.getParcelableArrayList("alternateActions");
                                            if (parcelableArrayList3 == null) {
                                                suggestParcelables$ActionGroup.alternateActions = r7;
                                            } else {
                                                suggestParcelables$ActionGroup.alternateActions = new ArrayList(parcelableArrayList3.size());
                                                Iterator it3 = parcelableArrayList3.iterator();
                                                while (it3.hasNext()) {
                                                    Bundle bundle4 = (Bundle) it3.next();
                                                    if (bundle4 == null) {
                                                        suggestParcelables$ActionGroup.alternateActions.add(null);
                                                    } else {
                                                        suggestParcelables$ActionGroup.alternateActions.add(SuggestParcelables$Action.create(bundle4));
                                                    }
                                                }
                                            }
                                        }
                                        if (bundle2.containsKey("isHiddenAction")) {
                                            suggestParcelables$ActionGroup.isHiddenAction = bundle2.getBoolean("isHiddenAction");
                                        }
                                        if (bundle2.containsKey("opaquePayload")) {
                                            suggestParcelables$ActionGroup.opaquePayload = bundle2.getString("opaquePayload");
                                        }
                                        list2.add(suggestParcelables$ActionGroup);
                                        r7 = 0;
                                    }
                                }
                            }
                        }
                        if (bundle.containsKey("entitySpans")) {
                            ArrayList parcelableArrayList4 = bundle.getParcelableArrayList("entitySpans");
                            if (parcelableArrayList4 == null) {
                                suggestParcelables$Entity.entitySpans = null;
                            } else {
                                suggestParcelables$Entity.entitySpans = new ArrayList(parcelableArrayList4.size());
                                Iterator it4 = parcelableArrayList4.iterator();
                                while (it4.hasNext()) {
                                    Bundle bundle5 = (Bundle) it4.next();
                                    if (bundle5 == null) {
                                        suggestParcelables$Entity.entitySpans.add(null);
                                    } else {
                                        List list3 = suggestParcelables$Entity.entitySpans;
                                        SuggestParcelables$EntitySpan suggestParcelables$EntitySpan = new SuggestParcelables$EntitySpan();
                                        if (bundle5.containsKey("rects")) {
                                            ArrayList parcelableArrayList5 = bundle5.getParcelableArrayList("rects");
                                            if (parcelableArrayList5 == null) {
                                                suggestParcelables$EntitySpan.rects = null;
                                            } else {
                                                suggestParcelables$EntitySpan.rects = new ArrayList(parcelableArrayList5.size());
                                                Iterator it5 = parcelableArrayList5.iterator();
                                                while (it5.hasNext()) {
                                                    Bundle bundle6 = (Bundle) it5.next();
                                                    if (bundle6 == null) {
                                                        suggestParcelables$EntitySpan.rects.add(null);
                                                    } else {
                                                        suggestParcelables$EntitySpan.rects.add(SuggestParcelables$ContentRect.create(bundle6));
                                                    }
                                                }
                                            }
                                        }
                                        if (bundle5.containsKey("selectionId")) {
                                            suggestParcelables$EntitySpan.selectionId = bundle5.getString("selectionId");
                                        }
                                        if (bundle5.containsKey("rectIndices")) {
                                            suggestParcelables$EntitySpan.rectIndices = bundle5.getIntegerArrayList("rectIndices");
                                        }
                                        list3.add(suggestParcelables$EntitySpan);
                                    }
                                }
                            }
                        }
                        if (bundle.containsKey("searchQueryHint")) {
                            suggestParcelables$Entity.searchQueryHint = bundle.getString("searchQueryHint");
                        }
                        if (bundle.containsKey("annotationTypeName")) {
                            suggestParcelables$Entity.annotationTypeName = bundle.getString("annotationTypeName");
                        }
                        if (bundle.containsKey("annotationSourceName")) {
                            suggestParcelables$Entity.annotationSourceName = bundle.getString("annotationSourceName");
                        }
                        if (bundle.containsKey("verticalTypeName")) {
                            suggestParcelables$Entity.verticalTypeName = bundle.getString("verticalTypeName");
                        }
                        if (bundle.containsKey("annotationScore")) {
                            suggestParcelables$Entity.annotationScore = bundle.getFloat("annotationScore");
                        }
                        if (bundle.containsKey("contentGroupIndex")) {
                            suggestParcelables$Entity.contentGroupIndex = bundle.getInt("contentGroupIndex");
                        }
                        if (bundle.containsKey("selectionIndex")) {
                            suggestParcelables$Entity.selectionIndex = bundle.getInt("selectionIndex");
                        }
                        if (bundle.containsKey("isSmartSelection")) {
                            suggestParcelables$Entity.isSmartSelection = bundle.getBoolean("isSmartSelection");
                        }
                        if (bundle.containsKey("suggestedPresentationMode")) {
                            suggestParcelables$Entity.suggestedPresentationMode = bundle.getInt("suggestedPresentationMode");
                        }
                        if (bundle.containsKey("numWords")) {
                            suggestParcelables$Entity.numWords = bundle.getInt("numWords");
                        }
                        if (bundle.containsKey("startIndex")) {
                            suggestParcelables$Entity.startIndex = bundle.getInt("startIndex");
                        }
                        if (bundle.containsKey("endIndex")) {
                            suggestParcelables$Entity.endIndex = bundle.getInt("endIndex");
                        }
                        if (bundle.containsKey("opaquePayload")) {
                            suggestParcelables$Entity.opaquePayload = bundle.getString("opaquePayload");
                        }
                        if (bundle.containsKey("interactionType")) {
                            Bundle bundle7 = bundle.getBundle("interactionType");
                            if (bundle7 == null) {
                                suggestParcelables$Entity.interactionType = null;
                            } else {
                                suggestParcelables$Entity.interactionType = SuggestParcelables$InteractionType.create(bundle7);
                            }
                        }
                        if (bundle.containsKey("shouldStartForResult")) {
                            suggestParcelables$Entity.shouldStartForResult = bundle.getBoolean("shouldStartForResult");
                        }
                        if (bundle.containsKey("kgCollections")) {
                            suggestParcelables$Entity.kgCollections = bundle.getStringArrayList("kgCollections");
                        }
                        list.add(suggestParcelables$Entity);
                        r7 = 0;
                    }
                }
            }
        }
        if (readBundle.containsKey("stats")) {
            Bundle bundle8 = readBundle.getBundle("stats");
            if (bundle8 == null) {
                suggestParcelables$DebugInfo = null;
                suggestParcelables$Entities.stats = null;
            } else {
                suggestParcelables$DebugInfo = null;
                suggestParcelables$Entities.stats = SuggestParcelables$Stats.create(bundle8);
            }
        } else {
            suggestParcelables$DebugInfo = null;
        }
        if (readBundle.containsKey("debugInfo")) {
            if (readBundle.getBundle("debugInfo") == null) {
                suggestParcelables$Entities.debugInfo = suggestParcelables$DebugInfo;
            } else {
                suggestParcelables$Entities.debugInfo = new SuggestParcelables$DebugInfo();
            }
        }
        if (readBundle.containsKey("extrasInfo")) {
            Bundle bundle9 = readBundle.getBundle("extrasInfo");
            if (bundle9 == null) {
                suggestParcelables$Entities.extrasInfo = null;
            } else {
                SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo = new SuggestParcelables$ExtrasInfo();
                if (bundle9.containsKey("containsPendingIntents")) {
                    suggestParcelables$ExtrasInfo.containsPendingIntents = bundle9.getBoolean("containsPendingIntents");
                }
                if (bundle9.containsKey("containsBitmaps")) {
                    suggestParcelables$ExtrasInfo.containsBitmaps = bundle9.getBoolean("containsBitmaps");
                }
                suggestParcelables$Entities.extrasInfo = suggestParcelables$ExtrasInfo;
            }
        }
        if (readBundle.containsKey("opaquePayload")) {
            suggestParcelables$Entities.opaquePayload = readBundle.getString("opaquePayload");
        }
        if (readBundle.containsKey("setupInfo")) {
            Bundle bundle10 = readBundle.getBundle("setupInfo");
            if (bundle10 == null) {
                suggestParcelables$Entities.setupInfo = null;
            } else {
                suggestParcelables$Entities.setupInfo = SuggestParcelables$SetupInfo.create(bundle10);
            }
        }
        HashMap hashMap = new HashMap();
        SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo2 = suggestParcelables$Entities.extrasInfo;
        if (suggestParcelables$ExtrasInfo2 != null && suggestParcelables$ExtrasInfo2.containsBitmaps) {
            parcel.readMap(hashMap, Bitmap.class.getClassLoader());
        }
        HashMap hashMap2 = new HashMap();
        SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo3 = suggestParcelables$Entities.extrasInfo;
        if (suggestParcelables$ExtrasInfo3 != null && suggestParcelables$ExtrasInfo3.containsPendingIntents) {
            parcel.readMap(hashMap2, PendingIntent.class.getClassLoader());
        }
        return create(suggestParcelables$Entities, hashMap, hashMap2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SuggestParcelables$Entities entities() {
        return this.entities;
    }

    public Bitmap getBitmap(String str) {
        return (Bitmap) this.bitmapMap.get(str);
    }

    public Map getBitmapMap() {
        return this.bitmapMap;
    }

    public PendingIntent getPendingIntent(String str) {
        return (PendingIntent) this.pendingIntentMap.get(str);
    }

    public Map getPendingIntentMap() {
        return this.pendingIntentMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        String str;
        Iterator it;
        Iterator it2;
        String str2;
        Iterator it3;
        ArrayList<? extends Parcelable> arrayList;
        Iterator it4;
        Iterator it5;
        Iterator it6;
        Bundle bundle;
        SuggestParcelables$Entities suggestParcelables$Entities = this.entities;
        suggestParcelables$Entities.getClass();
        Bundle bundle2 = new Bundle();
        String str3 = "id";
        bundle2.putString("id", suggestParcelables$Entities.id);
        bundle2.putBoolean("success", suggestParcelables$Entities.success);
        ArrayList<? extends Parcelable> arrayList2 = null;
        if (suggestParcelables$Entities.entities == null) {
            bundle2.putParcelableArrayList("entities", null);
        } else {
            ArrayList<? extends Parcelable> arrayList3 = new ArrayList<>(((ArrayList) suggestParcelables$Entities.entities).size());
            Iterator it7 = suggestParcelables$Entities.entities.iterator();
            while (it7.hasNext()) {
                SuggestParcelables$Entity suggestParcelables$Entity = (SuggestParcelables$Entity) it7.next();
                if (suggestParcelables$Entity == null) {
                    arrayList3.add(arrayList2);
                    str = str3;
                    it = it7;
                } else {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString(str3, suggestParcelables$Entity.id);
                    if (suggestParcelables$Entity.actions == null) {
                        bundle3.putParcelableArrayList("actions", arrayList2);
                        str = str3;
                        it = it7;
                    } else {
                        ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>(((ArrayList) suggestParcelables$Entity.actions).size());
                        Iterator it8 = suggestParcelables$Entity.actions.iterator();
                        while (it8.hasNext()) {
                            SuggestParcelables$ActionGroup suggestParcelables$ActionGroup = (SuggestParcelables$ActionGroup) it8.next();
                            if (suggestParcelables$ActionGroup == null) {
                                arrayList4.add(arrayList2);
                                str2 = str3;
                                it2 = it7;
                                it3 = it8;
                            } else {
                                Bundle bundle4 = new Bundle();
                                it2 = it7;
                                bundle4.putString(str3, suggestParcelables$ActionGroup.id);
                                str2 = str3;
                                bundle4.putString("displayName", suggestParcelables$ActionGroup.displayName);
                                SuggestParcelables$Action suggestParcelables$Action = suggestParcelables$ActionGroup.mainAction;
                                if (suggestParcelables$Action == null) {
                                    it3 = it8;
                                    arrayList = null;
                                    bundle4.putBundle("mainAction", null);
                                } else {
                                    it3 = it8;
                                    arrayList = null;
                                    bundle4.putBundle("mainAction", suggestParcelables$Action.writeToBundle());
                                }
                                if (suggestParcelables$ActionGroup.alternateActions == null) {
                                    bundle4.putParcelableArrayList("alternateActions", arrayList);
                                } else {
                                    ArrayList<? extends Parcelable> arrayList5 = new ArrayList<>(((ArrayList) suggestParcelables$ActionGroup.alternateActions).size());
                                    Iterator it9 = suggestParcelables$ActionGroup.alternateActions.iterator();
                                    while (it9.hasNext()) {
                                        SuggestParcelables$Action suggestParcelables$Action2 = (SuggestParcelables$Action) it9.next();
                                        if (suggestParcelables$Action2 == null) {
                                            it4 = it9;
                                            arrayList5.add(null);
                                        } else {
                                            it4 = it9;
                                            arrayList5.add(suggestParcelables$Action2.writeToBundle());
                                        }
                                        it9 = it4;
                                    }
                                    bundle4.putParcelableArrayList("alternateActions", arrayList5);
                                }
                                bundle4.putBoolean("isHiddenAction", suggestParcelables$ActionGroup.isHiddenAction);
                                bundle4.putString("opaquePayload", suggestParcelables$ActionGroup.opaquePayload);
                                arrayList4.add(bundle4);
                            }
                            it7 = it2;
                            str3 = str2;
                            it8 = it3;
                            arrayList2 = null;
                        }
                        str = str3;
                        it = it7;
                        bundle3.putParcelableArrayList("actions", arrayList4);
                    }
                    if (suggestParcelables$Entity.entitySpans == null) {
                        bundle3.putParcelableArrayList("entitySpans", null);
                    } else {
                        ArrayList<? extends Parcelable> arrayList6 = new ArrayList<>(((ArrayList) suggestParcelables$Entity.entitySpans).size());
                        Iterator it10 = suggestParcelables$Entity.entitySpans.iterator();
                        while (it10.hasNext()) {
                            SuggestParcelables$EntitySpan suggestParcelables$EntitySpan = (SuggestParcelables$EntitySpan) it10.next();
                            if (suggestParcelables$EntitySpan == null) {
                                arrayList6.add(null);
                                it5 = it10;
                            } else {
                                Bundle bundle5 = new Bundle();
                                it5 = it10;
                                if (suggestParcelables$EntitySpan.rects == null) {
                                    bundle5.putParcelableArrayList("rects", null);
                                } else {
                                    ArrayList<? extends Parcelable> arrayList7 = new ArrayList<>(((ArrayList) suggestParcelables$EntitySpan.rects).size());
                                    Iterator it11 = suggestParcelables$EntitySpan.rects.iterator();
                                    while (it11.hasNext()) {
                                        SuggestParcelables$ContentRect suggestParcelables$ContentRect = (SuggestParcelables$ContentRect) it11.next();
                                        if (suggestParcelables$ContentRect == null) {
                                            it6 = it11;
                                            arrayList7.add(null);
                                        } else {
                                            it6 = it11;
                                            arrayList7.add(suggestParcelables$ContentRect.writeToBundle());
                                        }
                                        it11 = it6;
                                    }
                                    bundle5.putParcelableArrayList("rects", arrayList7);
                                }
                                bundle5.putString("selectionId", suggestParcelables$EntitySpan.selectionId);
                                if (suggestParcelables$EntitySpan.rectIndices == null) {
                                    bundle5.putIntegerArrayList("rectIndices", null);
                                } else {
                                    bundle5.putIntegerArrayList("rectIndices", new ArrayList<>(suggestParcelables$EntitySpan.rectIndices));
                                }
                                arrayList6.add(bundle5);
                            }
                            it10 = it5;
                        }
                        bundle3.putParcelableArrayList("entitySpans", arrayList6);
                    }
                    bundle3.putString("searchQueryHint", suggestParcelables$Entity.searchQueryHint);
                    bundle3.putString("annotationTypeName", suggestParcelables$Entity.annotationTypeName);
                    bundle3.putString("annotationSourceName", suggestParcelables$Entity.annotationSourceName);
                    bundle3.putString("verticalTypeName", suggestParcelables$Entity.verticalTypeName);
                    bundle3.putFloat("annotationScore", suggestParcelables$Entity.annotationScore);
                    bundle3.putInt("contentGroupIndex", suggestParcelables$Entity.contentGroupIndex);
                    bundle3.putInt("selectionIndex", suggestParcelables$Entity.selectionIndex);
                    bundle3.putBoolean("isSmartSelection", suggestParcelables$Entity.isSmartSelection);
                    bundle3.putInt("suggestedPresentationMode", suggestParcelables$Entity.suggestedPresentationMode);
                    bundle3.putInt("numWords", suggestParcelables$Entity.numWords);
                    bundle3.putInt("startIndex", suggestParcelables$Entity.startIndex);
                    bundle3.putInt("endIndex", suggestParcelables$Entity.endIndex);
                    bundle3.putString("opaquePayload", suggestParcelables$Entity.opaquePayload);
                    SuggestParcelables$InteractionType suggestParcelables$InteractionType = suggestParcelables$Entity.interactionType;
                    if (suggestParcelables$InteractionType == null) {
                        bundle3.putBundle("interactionType", null);
                    } else {
                        Bundle bundle6 = new Bundle();
                        bundle6.putInt("value", suggestParcelables$InteractionType.value);
                        bundle3.putBundle("interactionType", bundle6);
                    }
                    bundle3.putBoolean("shouldStartForResult", suggestParcelables$Entity.shouldStartForResult);
                    if (suggestParcelables$Entity.kgCollections == null) {
                        bundle3.putStringArrayList("kgCollections", null);
                    } else {
                        bundle3.putStringArrayList("kgCollections", new ArrayList<>(suggestParcelables$Entity.kgCollections));
                    }
                    arrayList3.add(bundle3);
                }
                it7 = it;
                str3 = str;
                arrayList2 = null;
            }
            bundle2.putParcelableArrayList("entities", arrayList3);
        }
        SuggestParcelables$Stats suggestParcelables$Stats = suggestParcelables$Entities.stats;
        if (suggestParcelables$Stats == null) {
            bundle = null;
            bundle2.putBundle("stats", null);
        } else {
            bundle = null;
            bundle2.putBundle("stats", suggestParcelables$Stats.writeToBundle());
        }
        if (suggestParcelables$Entities.debugInfo == null) {
            bundle2.putBundle("debugInfo", bundle);
        } else {
            bundle2.putBundle("debugInfo", new Bundle());
        }
        SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo = suggestParcelables$Entities.extrasInfo;
        if (suggestParcelables$ExtrasInfo == null) {
            bundle2.putBundle("extrasInfo", bundle);
        } else {
            Bundle bundle7 = new Bundle();
            bundle7.putBoolean("containsPendingIntents", suggestParcelables$ExtrasInfo.containsPendingIntents);
            bundle7.putBoolean("containsBitmaps", suggestParcelables$ExtrasInfo.containsBitmaps);
            bundle2.putBundle("extrasInfo", bundle7);
        }
        bundle2.putString("opaquePayload", suggestParcelables$Entities.opaquePayload);
        SuggestParcelables$SetupInfo suggestParcelables$SetupInfo = suggestParcelables$Entities.setupInfo;
        if (suggestParcelables$SetupInfo == null) {
            bundle2.putBundle("setupInfo", null);
        } else {
            bundle2.putBundle("setupInfo", suggestParcelables$SetupInfo.writeToBundle());
        }
        bundle2.writeToParcel(parcel, 0);
        SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo2 = this.entities.extrasInfo;
        if (suggestParcelables$ExtrasInfo2 != null) {
            if (suggestParcelables$ExtrasInfo2.containsBitmaps) {
                parcel.writeMap(this.bitmapMap);
            }
            if (this.entities.extrasInfo.containsPendingIntents) {
                parcel.writeMap(this.pendingIntentMap);
            }
        }
    }

    public static EntitiesData create(SuggestParcelables$Entities suggestParcelables$Entities, Map map, Map map2) {
        return new EntitiesData(suggestParcelables$Entities, map, map2);
    }
}
