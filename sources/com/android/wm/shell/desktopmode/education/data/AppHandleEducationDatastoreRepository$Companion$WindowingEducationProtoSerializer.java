package com.android.wm.shell.desktopmode.education.data;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.core.Serializer;
import androidx.datastore.core.UncloseableOutputStream;
import com.android.framework.protobuf.InvalidProtocolBufferException;
import java.io.InputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AppHandleEducationDatastoreRepository$Companion$WindowingEducationProtoSerializer implements Serializer {
    public static final AppHandleEducationDatastoreRepository$Companion$WindowingEducationProtoSerializer INSTANCE = new AppHandleEducationDatastoreRepository$Companion$WindowingEducationProtoSerializer();
    public static final WindowingEducationProto defaultValue = WindowingEducationProto.getDefaultInstance();

    @Override // androidx.datastore.core.Serializer
    public final Object getDefaultValue() {
        return defaultValue;
    }

    @Override // androidx.datastore.core.Serializer
    public final Object readFrom(InputStream inputStream) {
        try {
            return WindowingEducationProto.parseFrom(inputStream);
        } catch (InvalidProtocolBufferException e) {
            throw new CorruptionException("Cannot read proto.", e);
        }
    }

    @Override // androidx.datastore.core.Serializer
    public final void writeTo(Object obj, UncloseableOutputStream uncloseableOutputStream) {
        ((WindowingEducationProto) obj).writeTo(uncloseableOutputStream);
    }
}
