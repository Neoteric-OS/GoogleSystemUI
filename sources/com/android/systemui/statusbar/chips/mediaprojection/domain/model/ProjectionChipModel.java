package com.android.systemui.statusbar.chips.mediaprojection.domain.model;

import com.android.systemui.mediaprojection.data.model.MediaProjectionState;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ProjectionChipModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotProjecting extends ProjectionChipModel {
        public static final NotProjecting INSTANCE = new NotProjecting();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof NotProjecting);
        }

        public final int hashCode() {
            return 1648514747;
        }

        public final String toString() {
            return "NotProjecting";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Projecting extends ProjectionChipModel {
        public final MediaProjectionState.Projecting projectionState;
        public final Type type;

        public Projecting(Type type, MediaProjectionState.Projecting projecting) {
            this.type = type;
            this.projectionState = projecting;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Projecting)) {
                return false;
            }
            Projecting projecting = (Projecting) obj;
            return this.type == projecting.type && Intrinsics.areEqual(this.projectionState, projecting.projectionState);
        }

        public final int hashCode() {
            return this.projectionState.hashCode() + (this.type.hashCode() * 31);
        }

        public final String toString() {
            return "Projecting(type=" + this.type + ", projectionState=" + this.projectionState + ")";
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Type {
        public static final /* synthetic */ Type[] $VALUES;
        public static final Type CAST_TO_OTHER_DEVICE;
        public static final Type SHARE_TO_APP;

        static {
            Type type = new Type("SHARE_TO_APP", 0);
            SHARE_TO_APP = type;
            Type type2 = new Type("CAST_TO_OTHER_DEVICE", 1);
            CAST_TO_OTHER_DEVICE = type2;
            Type[] typeArr = {type, type2};
            $VALUES = typeArr;
            EnumEntriesKt.enumEntries(typeArr);
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }
}
