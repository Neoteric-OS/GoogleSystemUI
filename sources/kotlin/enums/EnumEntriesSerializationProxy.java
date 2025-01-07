package kotlin.enums;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EnumEntriesSerializationProxy implements Serializable {
    private static final long serialVersionUID = 0;
    private final Class c;

    public EnumEntriesSerializationProxy(Enum[] enumArr) {
        Class<?> componentType = enumArr.getClass().getComponentType();
        Intrinsics.checkNotNull(componentType);
        this.c = componentType;
    }

    private final Object readResolve() {
        return new EnumEntriesList((Enum[]) this.c.getEnumConstants());
    }
}
