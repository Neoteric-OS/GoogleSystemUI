package com.android.systemui.log.table;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TableChange {
    public boolean bool;
    public String columnName;
    public String columnPrefix;

    /* renamed from: int, reason: not valid java name */
    public Integer f37int;
    public boolean isInitial;
    public String str;
    public long timestamp;
    public DataType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DataType {
        public static final /* synthetic */ DataType[] $VALUES;
        public static final DataType BOOLEAN;
        public static final DataType EMPTY;
        public static final DataType INT;
        public static final DataType STRING;

        static {
            DataType dataType = new DataType("STRING", 0);
            STRING = dataType;
            DataType dataType2 = new DataType("BOOLEAN", 1);
            BOOLEAN = dataType2;
            DataType dataType3 = new DataType("INT", 2);
            INT = dataType3;
            DataType dataType4 = new DataType("EMPTY", 3);
            EMPTY = dataType4;
            DataType[] dataTypeArr = {dataType, dataType2, dataType3, dataType4};
            $VALUES = dataTypeArr;
            EnumEntriesKt.enumEntries(dataTypeArr);
        }

        public static DataType valueOf(String str) {
            return (DataType) Enum.valueOf(DataType.class, str);
        }

        public static DataType[] values() {
            return (DataType[]) $VALUES.clone();
        }
    }

    public TableChange() {
        DataType dataType = DataType.EMPTY;
        this.timestamp = 0L;
        this.columnPrefix = "";
        this.columnName = "";
        this.isInitial = false;
        this.type = dataType;
        this.bool = false;
        this.f37int = null;
        this.str = null;
        this.columnPrefix = StringsKt.take(500, "");
        this.columnName = StringsKt.take(500, this.columnName);
        String str = this.str;
        this.str = str != null ? StringsKt.take(500, str) : null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TableChange)) {
            return false;
        }
        TableChange tableChange = (TableChange) obj;
        return this.timestamp == tableChange.timestamp && Intrinsics.areEqual(this.columnPrefix, tableChange.columnPrefix) && Intrinsics.areEqual(this.columnName, tableChange.columnName) && this.isInitial == tableChange.isInitial && this.type == tableChange.type && this.bool == tableChange.bool && Intrinsics.areEqual(this.f37int, tableChange.f37int) && Intrinsics.areEqual(this.str, tableChange.str);
    }

    public final String getName() {
        return !StringsKt__StringsJVMKt.isBlank(this.columnPrefix) ? DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(this.columnPrefix, ".", this.columnName) : this.columnName;
    }

    public final String getVal() {
        Object obj;
        int ordinal = this.type.ordinal();
        if (ordinal == 0) {
            obj = this.str;
        } else if (ordinal == 1) {
            obj = Boolean.valueOf(this.bool);
        } else if (ordinal == 2) {
            obj = this.f37int;
        } else {
            if (ordinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
            obj = null;
        }
        return (this.isInitial ? "**" : "").concat(String.valueOf(obj));
    }

    public final boolean hasData() {
        return (StringsKt__StringsJVMKt.isBlank(this.columnName) || this.type == DataType.EMPTY) ? false : true;
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m((this.type.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.columnName, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.columnPrefix, Long.hashCode(this.timestamp) * 31, 31), 31), 31, this.isInitial)) * 31, 31, this.bool);
        Integer num = this.f37int;
        int hashCode = (m + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.str;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public final void reset(long j, String str, String str2, boolean z) {
        this.timestamp = j;
        this.columnPrefix = StringsKt.take(500, str);
        this.columnName = StringsKt.take(500, str2);
        this.isInitial = z;
        this.type = DataType.EMPTY;
        this.bool = false;
        this.f37int = 0;
        this.str = null;
    }

    public final String toString() {
        return "TableChange(timestamp=" + this.timestamp + ", columnPrefix=" + this.columnPrefix + ", columnName=" + this.columnName + ", isInitial=" + this.isInitial + ", type=" + this.type + ", bool=" + this.bool + ", int=" + this.f37int + ", str=" + this.str + ")";
    }
}
