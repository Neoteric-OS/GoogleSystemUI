package com.android.systemui.statusbar.notification.data.repository;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveNotificationsStore {
    public final Map groups;
    public final Map individuals;
    public final Map rankingsMap;
    public final List renderList;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final Map groups = new LinkedHashMap();
        public final Map individuals = new LinkedHashMap();
        public final List renderList = new ArrayList();
        public Map rankingsMap = MapsKt.emptyMap();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Key {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Group extends Key {
            public final String key;

            public Group(String str) {
                this.key = str;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Group) && Intrinsics.areEqual(this.key, ((Group) obj).key);
            }

            public final int hashCode() {
                return this.key.hashCode();
            }

            public final String toString() {
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("Group(key="), this.key, ")");
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Individual extends Key {
            public final String key;

            public Individual(String str) {
                this.key = str;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Individual) && Intrinsics.areEqual(this.key, ((Individual) obj).key);
            }

            public final int hashCode() {
                return this.key.hashCode();
            }

            public final String toString() {
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("Individual(key="), this.key, ")");
            }
        }
    }

    public ActiveNotificationsStore(Map map, Map map2, List list, Map map3) {
        this.groups = map;
        this.individuals = map2;
        this.renderList = list;
        this.rankingsMap = map3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActiveNotificationsStore)) {
            return false;
        }
        ActiveNotificationsStore activeNotificationsStore = (ActiveNotificationsStore) obj;
        return Intrinsics.areEqual(this.groups, activeNotificationsStore.groups) && Intrinsics.areEqual(this.individuals, activeNotificationsStore.individuals) && Intrinsics.areEqual(this.renderList, activeNotificationsStore.renderList) && Intrinsics.areEqual(this.rankingsMap, activeNotificationsStore.rankingsMap);
    }

    public final int hashCode() {
        return this.rankingsMap.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((this.individuals.hashCode() + (this.groups.hashCode() * 31)) * 31, 31, this.renderList);
    }

    public final String toString() {
        return "ActiveNotificationsStore(groups=" + this.groups + ", individuals=" + this.individuals + ", renderList=" + this.renderList + ", rankingsMap=" + this.rankingsMap + ")";
    }
}
