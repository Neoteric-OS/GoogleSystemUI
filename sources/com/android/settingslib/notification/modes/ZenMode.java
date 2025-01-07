package com.android.settingslib.notification.modes;

import android.app.AutomaticZenRule;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZenMode implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    public static final AnonymousClass1 PRIORITIZED_TYPE_COMPARATOR = null;
    public static final Comparator PRIORITIZING_COMPARATOR;
    public final String mId;
    public final Kind mKind;
    public final AutomaticZenRule mRule;
    public final Status mStatus;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.notification.modes.ZenMode$1, reason: invalid class name */
    public final class AnonymousClass1 implements Comparator {
        public static final ImmutableList PRIORITIZED_TYPES = ImmutableList.construct(3, 4);

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            Integer num = (Integer) obj;
            Integer num2 = (Integer) obj2;
            ImmutableList immutableList = PRIORITIZED_TYPES;
            if (immutableList.contains(num) && immutableList.contains(num2)) {
                return immutableList.indexOf(num) - immutableList.indexOf(num2);
            }
            if (immutableList.contains(num)) {
                return -1;
            }
            return immutableList.contains(num2) ? 1 : 0;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.notification.modes.ZenMode$2, reason: invalid class name */
    public final class AnonymousClass2 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            AutomaticZenRule automaticZenRule = (AutomaticZenRule) parcel.readParcelable(AutomaticZenRule.class.getClassLoader(), AutomaticZenRule.class);
            automaticZenRule.getClass();
            return new ZenMode(readString, automaticZenRule, Kind.valueOf(parcel.readString()), Status.valueOf(parcel.readString()));
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new ZenMode[i];
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Kind {
        public static final /* synthetic */ Kind[] $VALUES;
        public static final Kind IMPLICIT;
        public static final Kind MANUAL_DND;
        public static final Kind NORMAL;

        static {
            Kind kind = new Kind("NORMAL", 0);
            NORMAL = kind;
            Kind kind2 = new Kind("MANUAL_DND", 1);
            MANUAL_DND = kind2;
            Kind kind3 = new Kind("IMPLICIT", 2);
            IMPLICIT = kind3;
            $VALUES = new Kind[]{kind, kind2, kind3};
        }

        public static Kind valueOf(String str) {
            return (Kind) Enum.valueOf(Kind.class, str);
        }

        public static Kind[] values() {
            return (Kind[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Status {
        public static final /* synthetic */ Status[] $VALUES;
        public static final Status DISABLED_BY_OTHER;
        public static final Status DISABLED_BY_USER;
        public static final Status ENABLED;
        public static final Status ENABLED_AND_ACTIVE;

        static {
            Status status = new Status("ENABLED", 0);
            ENABLED = status;
            Status status2 = new Status("ENABLED_AND_ACTIVE", 1);
            ENABLED_AND_ACTIVE = status2;
            Status status3 = new Status("DISABLED_BY_USER", 2);
            DISABLED_BY_USER = status3;
            Status status4 = new Status("DISABLED_BY_OTHER", 3);
            DISABLED_BY_OTHER = status4;
            $VALUES = new Status[]{status, status2, status3, status4};
        }

        public static Status valueOf(String str) {
            return (Status) Enum.valueOf(Status.class, str);
        }

        public static Status[] values() {
            return (Status[]) $VALUES.clone();
        }
    }

    static {
        final int i = 0;
        final int i2 = 1;
        Comparator thenComparing = Comparator.comparing(new Function() { // from class: com.android.settingslib.notification.modes.ZenMode$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ZenMode zenMode = (ZenMode) obj;
                switch (i) {
                    case 0:
                        return Boolean.valueOf(zenMode.isManualDnd());
                    case 1:
                        return Integer.valueOf(zenMode.mRule.getType());
                    default:
                        String name = zenMode.mRule.getName();
                        return name == null ? "" : name;
                }
            }
        }).reversed().thenComparing(new Function() { // from class: com.android.settingslib.notification.modes.ZenMode$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ZenMode zenMode = (ZenMode) obj;
                switch (i2) {
                    case 0:
                        return Boolean.valueOf(zenMode.isManualDnd());
                    case 1:
                        return Integer.valueOf(zenMode.mRule.getType());
                    default:
                        String name = zenMode.mRule.getName();
                        return name == null ? "" : name;
                }
            }
        }, new AnonymousClass1());
        final int i3 = 2;
        PRIORITIZING_COMPARATOR = thenComparing.thenComparing(new Function() { // from class: com.android.settingslib.notification.modes.ZenMode$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ZenMode zenMode = (ZenMode) obj;
                switch (i3) {
                    case 0:
                        return Boolean.valueOf(zenMode.isManualDnd());
                    case 1:
                        return Integer.valueOf(zenMode.mRule.getType());
                    default:
                        String name = zenMode.mRule.getName();
                        return name == null ? "" : name;
                }
            }
        });
        CREATOR = new AnonymousClass2();
    }

    public ZenMode(String str, AutomaticZenRule automaticZenRule, Kind kind, Status status) {
        this.mId = str;
        this.mRule = automaticZenRule;
        this.mKind = kind;
        this.mStatus = status;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ZenMode) {
            ZenMode zenMode = (ZenMode) obj;
            if (this.mId.equals(zenMode.mId) && this.mRule.equals(zenMode.mRule) && this.mKind.equals(zenMode.mKind) && this.mStatus.equals(zenMode.mStatus)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.mId, this.mRule, this.mKind, this.mStatus);
    }

    public final boolean isActive() {
        return this.mStatus == Status.ENABLED_AND_ACTIVE;
    }

    public final boolean isManualDnd() {
        return this.mKind == Kind.MANUAL_DND;
    }

    public final String toString() {
        return this.mId + " (" + this.mKind + ", " + this.mStatus + ") -> " + this.mRule;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeParcelable(this.mRule, 0);
        parcel.writeString(this.mKind.name());
        parcel.writeString(this.mStatus.name());
    }
}
