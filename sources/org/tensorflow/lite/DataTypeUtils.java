package org.tensorflow.lite;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DataTypeUtils {
    public static String toStringName(DataType dataType) {
        switch (dataType) {
            case FLOAT32:
                return "float";
            case INT32:
                return "int";
            case UINT8:
            case INT8:
                return "byte";
            case INT64:
                return "long";
            case STRING:
                return "string";
            case BOOL:
                return "bool";
            case INT16:
                return "short";
            default:
                throw new IllegalArgumentException("DataType error: DataType " + dataType + " is not supported yet");
        }
    }
}
