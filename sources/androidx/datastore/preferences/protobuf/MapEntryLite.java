package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.PreferencesProto$Value;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MapEntryLite {
    public final Metadata metadata;
    public final PreferencesProto$Value value;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Metadata {
        public final PreferencesProto$Value defaultValue;
        public final WireFormat$FieldType keyType;
        public final WireFormat$FieldType valueType;

        public Metadata(WireFormat$FieldType wireFormat$FieldType, WireFormat$FieldType wireFormat$FieldType2, PreferencesProto$Value preferencesProto$Value) {
            this.keyType = wireFormat$FieldType;
            this.valueType = wireFormat$FieldType2;
            this.defaultValue = preferencesProto$Value;
        }
    }

    public MapEntryLite(WireFormat$FieldType wireFormat$FieldType, WireFormat$FieldType wireFormat$FieldType2, PreferencesProto$Value preferencesProto$Value) {
        this.metadata = new Metadata(wireFormat$FieldType, wireFormat$FieldType2, preferencesProto$Value);
    }
}
