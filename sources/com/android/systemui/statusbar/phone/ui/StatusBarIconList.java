package com.android.systemui.statusbar.phone.ui;

import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarIconList {
    public final ArrayList mSlots;
    public final List mViewOnlySlots;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Slot {
        public StatusBarIconHolder mHolder = null;
        public final String mName;
        public ArrayList mSubSlots;

        public Slot(String str) {
            this.mName = str;
        }

        public void clear() {
            this.mHolder = null;
            if (this.mSubSlots != null) {
                this.mSubSlots = null;
            }
        }

        public final StatusBarIconHolder getHolderForTag(int i) {
            if (i == 0) {
                return this.mHolder;
            }
            ArrayList arrayList = this.mSubSlots;
            if (arrayList == null) {
                return null;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                StatusBarIconHolder statusBarIconHolder = (StatusBarIconHolder) it.next();
                if (statusBarIconHolder.tag == i) {
                    return statusBarIconHolder;
                }
            }
            return null;
        }

        public final List getHolderListInViewOrder() {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = this.mSubSlots;
            if (arrayList2 != null) {
                for (int size = arrayList2.size() - 1; size >= 0; size--) {
                    arrayList.add((StatusBarIconHolder) this.mSubSlots.get(size));
                }
            }
            StatusBarIconHolder statusBarIconHolder = this.mHolder;
            if (statusBarIconHolder != null) {
                arrayList.add(statusBarIconHolder);
            }
            return arrayList;
        }

        public final int getIndexForTag(int i) {
            for (int i2 = 0; i2 < this.mSubSlots.size(); i2++) {
                if (((StatusBarIconHolder) this.mSubSlots.get(i2)).tag == i) {
                    return i2;
                }
            }
            return -1;
        }

        public final boolean hasIconsInSlot() {
            if (this.mHolder != null) {
                return true;
            }
            ArrayList arrayList = this.mSubSlots;
            return arrayList != null && arrayList.size() > 0;
        }

        public final String toString() {
            String str;
            StatusBarIconHolder statusBarIconHolder = this.mHolder;
            if (this.mSubSlots == null) {
                str = "";
            } else {
                str = "| " + this.mSubSlots.size() + " subSlots: " + ((String) this.mSubSlots.stream().map(new StatusBarIconList$Slot$$ExternalSyntheticLambda0()).collect(Collectors.joining("|")));
            }
            return String.format("(%s) holder=%s %s", this.mName, statusBarIconHolder, str);
        }
    }

    public StatusBarIconList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        this.mSlots = arrayList;
        this.mViewOnlySlots = Collections.unmodifiableList(arrayList);
        for (String str : strArr) {
            this.mSlots.add(new Slot(str));
        }
    }

    public final int findOrInsertSlot(String str) {
        int size = this.mSlots.size();
        for (int i = 0; i < size; i++) {
            if (((Slot) this.mSlots.get(i)).mName.equals(str)) {
                return i;
            }
        }
        this.mSlots.add(0, new Slot(str));
        return 0;
    }

    public final StatusBarIconHolder getIconHolder(int i, String str) {
        return ((Slot) this.mSlots.get(findOrInsertSlot(str))).getHolderForTag(i);
    }

    public final int getViewIndex(int i, String str) {
        int findOrInsertSlot = findOrInsertSlot(str);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= findOrInsertSlot) {
                break;
            }
            Slot slot = (Slot) this.mSlots.get(i3);
            if (slot.hasIconsInSlot()) {
                int i5 = slot.mHolder == null ? 0 : 1;
                ArrayList arrayList = slot.mSubSlots;
                if (arrayList != null) {
                    i5 += arrayList.size();
                }
                i4 += i5;
            }
            i3++;
        }
        Slot slot2 = (Slot) this.mSlots.get(findOrInsertSlot);
        ArrayList arrayList2 = slot2.mSubSlots;
        if (arrayList2 != null) {
            i2 = arrayList2.size();
            if (i != 0) {
                i2 = (i2 - slot2.getIndexForTag(i)) - 1;
            }
        }
        return i4 + i2;
    }
}
