package androidx.appsearch.safeparcel;

import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PropertyConfigParcel extends AbstractSafeParcelable {
    public final int mCardinality;
    public final int mDataType;
    public final String mDescription = "";
    public final DocumentIndexingConfigParcel mDocumentIndexingConfigParcel;
    public Integer mHashCode;
    public final IntegerIndexingConfigParcel mIntegerIndexingConfigParcel;
    public final JoinableConfigParcel mJoinableConfigParcel;
    public final String mName;
    public final String mSchemaType;
    public final StringIndexingConfigParcel mStringIndexingConfigParcel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DocumentIndexingConfigParcel extends AbstractSafeParcelable {
        public final boolean mIndexNestedProperties;
        public final List mIndexableNestedPropertiesList;

        public DocumentIndexingConfigParcel(List list, boolean z) {
            this.mIndexNestedProperties = z;
            this.mIndexableNestedPropertiesList = list;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DocumentIndexingConfigParcel)) {
                return false;
            }
            DocumentIndexingConfigParcel documentIndexingConfigParcel = (DocumentIndexingConfigParcel) obj;
            return Boolean.valueOf(this.mIndexNestedProperties).equals(Boolean.valueOf(documentIndexingConfigParcel.mIndexNestedProperties)) && Objects.equals(this.mIndexableNestedPropertiesList, documentIndexingConfigParcel.mIndexableNestedPropertiesList);
        }

        public final int hashCode() {
            return Objects.hash(Boolean.valueOf(this.mIndexNestedProperties), this.mIndexableNestedPropertiesList);
        }

        public final String toString() {
            return "{indexNestedProperties: " + this.mIndexNestedProperties + ", indexableNestedPropertiesList: " + this.mIndexableNestedPropertiesList + "}";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IntegerIndexingConfigParcel extends AbstractSafeParcelable {
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IntegerIndexingConfigParcel)) {
                return false;
            }
            Integer num = 0;
            ((IntegerIndexingConfigParcel) obj).getClass();
            return num.equals(0);
        }

        public final int hashCode() {
            Integer num = 0;
            return num.hashCode();
        }

        public final String toString() {
            return "{indexingType: 0}";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class JoinableConfigParcel extends AbstractSafeParcelable {
        public final int mJoinableValueType;

        public JoinableConfigParcel(int i) {
            this.mJoinableValueType = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof JoinableConfigParcel)) {
                return false;
            }
            if (Integer.valueOf(this.mJoinableValueType).equals(Integer.valueOf(((JoinableConfigParcel) obj).mJoinableValueType))) {
                Object obj2 = Boolean.FALSE;
                if (obj2.equals(obj2)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.mJoinableValueType), Boolean.FALSE);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("{joinableValueType: "), this.mJoinableValueType, ", deletePropagation false}");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StringIndexingConfigParcel extends AbstractSafeParcelable {
        public final int mIndexingType;
        public final int mTokenizerType;

        public StringIndexingConfigParcel(int i, int i2) {
            this.mIndexingType = i;
            this.mTokenizerType = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StringIndexingConfigParcel)) {
                return false;
            }
            StringIndexingConfigParcel stringIndexingConfigParcel = (StringIndexingConfigParcel) obj;
            return Integer.valueOf(this.mIndexingType).equals(Integer.valueOf(stringIndexingConfigParcel.mIndexingType)) && Integer.valueOf(this.mTokenizerType).equals(Integer.valueOf(stringIndexingConfigParcel.mTokenizerType));
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.mIndexingType), Integer.valueOf(this.mTokenizerType));
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("{indexingType: ");
            sb.append(this.mIndexingType);
            sb.append(", tokenizerType ");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.mTokenizerType, "}");
        }
    }

    public PropertyConfigParcel(String str, int i, int i2, String str2, StringIndexingConfigParcel stringIndexingConfigParcel, DocumentIndexingConfigParcel documentIndexingConfigParcel, IntegerIndexingConfigParcel integerIndexingConfigParcel, JoinableConfigParcel joinableConfigParcel) {
        this.mName = str;
        this.mDataType = i;
        this.mCardinality = i2;
        this.mSchemaType = str2;
        this.mStringIndexingConfigParcel = stringIndexingConfigParcel;
        this.mDocumentIndexingConfigParcel = documentIndexingConfigParcel;
        this.mIntegerIndexingConfigParcel = integerIndexingConfigParcel;
        this.mJoinableConfigParcel = joinableConfigParcel;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PropertyConfigParcel)) {
            return false;
        }
        PropertyConfigParcel propertyConfigParcel = (PropertyConfigParcel) obj;
        return Objects.equals(this.mName, propertyConfigParcel.mName) && Objects.equals(this.mDescription, propertyConfigParcel.mDescription) && Integer.valueOf(this.mDataType).equals(Integer.valueOf(propertyConfigParcel.mDataType)) && Integer.valueOf(this.mCardinality).equals(Integer.valueOf(propertyConfigParcel.mCardinality)) && Objects.equals(this.mSchemaType, propertyConfigParcel.mSchemaType) && Objects.equals(this.mStringIndexingConfigParcel, propertyConfigParcel.mStringIndexingConfigParcel) && Objects.equals(this.mDocumentIndexingConfigParcel, propertyConfigParcel.mDocumentIndexingConfigParcel) && Objects.equals(this.mIntegerIndexingConfigParcel, propertyConfigParcel.mIntegerIndexingConfigParcel) && Objects.equals(this.mJoinableConfigParcel, propertyConfigParcel.mJoinableConfigParcel);
    }

    public final int hashCode() {
        if (this.mHashCode == null) {
            this.mHashCode = Integer.valueOf(Objects.hash(this.mName, this.mDescription, Integer.valueOf(this.mDataType), Integer.valueOf(this.mCardinality), this.mSchemaType, this.mStringIndexingConfigParcel, this.mDocumentIndexingConfigParcel, this.mIntegerIndexingConfigParcel, this.mJoinableConfigParcel, null));
        }
        return this.mHashCode.intValue();
    }

    public final String toString() {
        return "{name: " + this.mName + ", description: " + this.mDescription + ", dataType: " + this.mDataType + ", cardinality: " + this.mCardinality + ", schemaType: " + this.mSchemaType + ", stringIndexingConfigParcel: " + this.mStringIndexingConfigParcel + ", documentIndexingConfigParcel: " + this.mDocumentIndexingConfigParcel + ", integerIndexingConfigParcel: " + this.mIntegerIndexingConfigParcel + ", joinableConfigParcel: " + this.mJoinableConfigParcel + ", embeddingIndexingConfigParcel: null}";
    }
}
