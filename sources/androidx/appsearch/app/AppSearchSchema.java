package androidx.appsearch.app;

import androidx.appsearch.exceptions.IllegalSchemaException;
import androidx.appsearch.safeparcel.AbstractSafeParcelable;
import androidx.appsearch.safeparcel.PropertyConfigParcel;
import androidx.appsearch.util.IndentingStringBuilder;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppSearchSchema extends AbstractSafeParcelable {
    public final String mDescription;
    public final List mParentTypes;
    public final List mPropertyConfigParcels;
    public final String mSchemaType;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final String mSchemaType;
        public ArrayList mPropertyConfigParcels = new ArrayList();
        public LinkedHashSet mParentTypes = new LinkedHashSet();
        public final ArraySet mPropertyNames = new ArraySet(0);
        public boolean mBuilt = false;

        public Builder(String str) {
            this.mSchemaType = str;
        }

        public final void addProperty(PropertyConfig propertyConfig) {
            if (this.mBuilt) {
                this.mPropertyConfigParcels = new ArrayList(this.mPropertyConfigParcels);
                this.mParentTypes = new LinkedHashSet(this.mParentTypes);
                this.mBuilt = false;
            }
            PropertyConfigParcel propertyConfigParcel = propertyConfig.mPropertyConfigParcel;
            ArraySet arraySet = this.mPropertyNames;
            String str = propertyConfigParcel.mName;
            if (!arraySet.add(str)) {
                throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str));
            }
            this.mPropertyConfigParcels.add(propertyConfigParcel);
        }

        public final AppSearchSchema build() {
            this.mBuilt = true;
            return new AppSearchSchema(this.mSchemaType, this.mPropertyConfigParcels, new ArrayList(this.mParentTypes));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BytesPropertyConfig extends PropertyConfig {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DocumentPropertyConfig extends PropertyConfig {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LongPropertyConfig extends PropertyConfig {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class PropertyConfig {
        public final PropertyConfigParcel mPropertyConfigParcel;

        public PropertyConfig(PropertyConfigParcel propertyConfigParcel) {
            this.mPropertyConfigParcel = propertyConfigParcel;
        }

        public final void appendPropertyConfigString(IndentingStringBuilder indentingStringBuilder) {
            List emptyList;
            indentingStringBuilder.applyIndentToString("{\n");
            indentingStringBuilder.increaseIndentLevel();
            indentingStringBuilder.applyIndentToString("name: \"");
            PropertyConfigParcel propertyConfigParcel = this.mPropertyConfigParcel;
            indentingStringBuilder.applyIndentToString(propertyConfigParcel.mName);
            indentingStringBuilder.applyIndentToString("\",\n");
            indentingStringBuilder.applyIndentToString("description: \"");
            indentingStringBuilder.applyIndentToString(propertyConfigParcel.mDescription);
            indentingStringBuilder.applyIndentToString("\",\n");
            if (this instanceof StringPropertyConfig) {
                PropertyConfigParcel propertyConfigParcel2 = ((StringPropertyConfig) this).mPropertyConfigParcel;
                PropertyConfigParcel.StringIndexingConfigParcel stringIndexingConfigParcel = propertyConfigParcel2.mStringIndexingConfigParcel;
                int i = stringIndexingConfigParcel == null ? 0 : stringIndexingConfigParcel.mIndexingType;
                if (i == 0) {
                    indentingStringBuilder.applyIndentToString("indexingType: INDEXING_TYPE_NONE,\n");
                } else if (i == 1) {
                    indentingStringBuilder.applyIndentToString("indexingType: INDEXING_TYPE_EXACT_TERMS,\n");
                } else if (i != 2) {
                    indentingStringBuilder.applyIndentToString("indexingType: INDEXING_TYPE_UNKNOWN,\n");
                } else {
                    indentingStringBuilder.applyIndentToString("indexingType: INDEXING_TYPE_PREFIXES,\n");
                }
                int i2 = stringIndexingConfigParcel == null ? 0 : stringIndexingConfigParcel.mTokenizerType;
                if (i2 == 0) {
                    indentingStringBuilder.applyIndentToString("tokenizerType: TOKENIZER_TYPE_NONE,\n");
                } else if (i2 == 1) {
                    indentingStringBuilder.applyIndentToString("tokenizerType: TOKENIZER_TYPE_PLAIN,\n");
                } else if (i2 == 2) {
                    indentingStringBuilder.applyIndentToString("tokenizerType: TOKENIZER_TYPE_VERBATIM,\n");
                } else if (i2 != 3) {
                    indentingStringBuilder.applyIndentToString("tokenizerType: TOKENIZER_TYPE_UNKNOWN,\n");
                } else {
                    indentingStringBuilder.applyIndentToString("tokenizerType: TOKENIZER_TYPE_RFC822,\n");
                }
                PropertyConfigParcel.JoinableConfigParcel joinableConfigParcel = propertyConfigParcel2.mJoinableConfigParcel;
                int i3 = joinableConfigParcel != null ? joinableConfigParcel.mJoinableValueType : 0;
                if (i3 == 0) {
                    indentingStringBuilder.applyIndentToString("joinableValueType: JOINABLE_VALUE_TYPE_NONE,\n");
                } else if (i3 != 1) {
                    indentingStringBuilder.applyIndentToString("joinableValueType: JOINABLE_VALUE_TYPE_UNKNOWN,\n");
                } else {
                    indentingStringBuilder.applyIndentToString("joinableValueType: JOINABLE_VALUE_TYPE_QUALIFIED_ID,\n");
                }
            } else if (this instanceof DocumentPropertyConfig) {
                indentingStringBuilder.applyIndentToString("shouldIndexNestedProperties: ");
                PropertyConfigParcel propertyConfigParcel3 = ((DocumentPropertyConfig) this).mPropertyConfigParcel;
                PropertyConfigParcel.DocumentIndexingConfigParcel documentIndexingConfigParcel = propertyConfigParcel3.mDocumentIndexingConfigParcel;
                indentingStringBuilder.applyIndentToString(Boolean.valueOf(documentIndexingConfigParcel != null ? documentIndexingConfigParcel.mIndexNestedProperties : false).toString());
                indentingStringBuilder.applyIndentToString(",\n");
                indentingStringBuilder.applyIndentToString("indexableNestedProperties: ");
                if (documentIndexingConfigParcel == null) {
                    emptyList = Collections.emptyList();
                } else {
                    List list = documentIndexingConfigParcel.mIndexableNestedPropertiesList;
                    emptyList = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
                }
                indentingStringBuilder.applyIndentToString(emptyList.toString());
                indentingStringBuilder.applyIndentToString(",\n");
                indentingStringBuilder.applyIndentToString("schemaType: \"");
                String str = propertyConfigParcel3.mSchemaType;
                str.getClass();
                indentingStringBuilder.applyIndentToString(str);
                indentingStringBuilder.applyIndentToString("\",\n");
            } else if (this instanceof LongPropertyConfig) {
                indentingStringBuilder.applyIndentToString("indexingType: INDEXING_TYPE_NONE,\n");
            }
            int i4 = propertyConfigParcel.mCardinality;
            if (i4 == 1) {
                indentingStringBuilder.applyIndentToString("cardinality: CARDINALITY_REPEATED,\n");
            } else if (i4 == 2) {
                indentingStringBuilder.applyIndentToString("cardinality: CARDINALITY_OPTIONAL,\n");
            } else if (i4 != 3) {
                indentingStringBuilder.applyIndentToString("cardinality: CARDINALITY_UNKNOWN,\n");
            } else {
                indentingStringBuilder.applyIndentToString("cardinality: CARDINALITY_REQUIRED,\n");
            }
            switch (propertyConfigParcel.mDataType) {
                case 1:
                    indentingStringBuilder.applyIndentToString("dataType: DATA_TYPE_STRING,\n");
                    break;
                case 2:
                    indentingStringBuilder.applyIndentToString("dataType: DATA_TYPE_LONG,\n");
                    break;
                case 3:
                    indentingStringBuilder.applyIndentToString("dataType: DATA_TYPE_DOUBLE,\n");
                    break;
                case 4:
                    indentingStringBuilder.applyIndentToString("dataType: DATA_TYPE_BOOLEAN,\n");
                    break;
                case 5:
                    indentingStringBuilder.applyIndentToString("dataType: DATA_TYPE_BYTES,\n");
                    break;
                case 6:
                    indentingStringBuilder.applyIndentToString("dataType: DATA_TYPE_DOCUMENT,\n");
                    break;
                case 7:
                    indentingStringBuilder.applyIndentToString("dataType: DATA_TYPE_EMBEDDING,\n");
                    break;
                default:
                    indentingStringBuilder.applyIndentToString("dataType: DATA_TYPE_UNKNOWN,\n");
                    break;
            }
            indentingStringBuilder.decreaseIndentLevel();
            indentingStringBuilder.applyIndentToString("}");
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PropertyConfig)) {
                return false;
            }
            return Objects.equals(this.mPropertyConfigParcel, ((PropertyConfig) obj).mPropertyConfigParcel);
        }

        public final int hashCode() {
            return this.mPropertyConfigParcel.hashCode();
        }

        public final String toString() {
            IndentingStringBuilder indentingStringBuilder = new IndentingStringBuilder();
            appendPropertyConfigString(indentingStringBuilder);
            return indentingStringBuilder.mStringBuilder.toString();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StringPropertyConfig extends PropertyConfig {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Builder {
            public final String mPropertyName;
            public int mCardinality = 2;
            public int mIndexingType = 0;
            public int mTokenizerType = 0;
            public int mJoinableValueType = 0;

            public Builder(String str) {
                this.mPropertyName = str;
            }

            public final StringPropertyConfig build() {
                if (this.mTokenizerType == 0) {
                    Preconditions.checkState("Cannot set TOKENIZER_TYPE_NONE with an indexing type other than INDEXING_TYPE_NONE.", this.mIndexingType == 0);
                } else {
                    Preconditions.checkState("Cannot set TOKENIZER_TYPE_PLAIN with INDEXING_TYPE_NONE.", this.mIndexingType != 0);
                }
                if (this.mJoinableValueType == 1) {
                    Preconditions.checkState("Cannot set JOINABLE_VALUE_TYPE_QUALIFIED_ID with CARDINALITY_REPEATED.", this.mCardinality != 1);
                }
                return new StringPropertyConfig(new PropertyConfigParcel(this.mPropertyName, 1, this.mCardinality, null, new PropertyConfigParcel.StringIndexingConfigParcel(this.mIndexingType, this.mTokenizerType), null, null, new PropertyConfigParcel.JoinableConfigParcel(this.mJoinableValueType)));
            }

            public final void setCardinality(int i) {
                Preconditions.checkArgumentInRange("cardinality", i, 1, 3);
                this.mCardinality = i;
            }

            public final void setIndexingType(int i) {
                Preconditions.checkArgumentInRange("indexingType", i, 0, 2);
                this.mIndexingType = i;
            }

            public final void setJoinableValueType(int i) {
                Preconditions.checkArgumentInRange("joinableValueType", i, 0, 1);
                this.mJoinableValueType = i;
            }

            public final void setTokenizerType(int i) {
                Preconditions.checkArgumentInRange("tokenizerType", i, 0, 3);
                this.mTokenizerType = i;
            }
        }
    }

    public AppSearchSchema(String str, List list, List list2) {
        Objects.requireNonNull(str);
        this.mSchemaType = str;
        Objects.requireNonNull(list);
        this.mPropertyConfigParcels = list;
        this.mParentTypes = list2;
        this.mDescription = "";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppSearchSchema)) {
            return false;
        }
        AppSearchSchema appSearchSchema = (AppSearchSchema) obj;
        if (this.mSchemaType.equals(appSearchSchema.mSchemaType) && this.mDescription.equals(appSearchSchema.mDescription) && Collections.unmodifiableList(this.mParentTypes).equals(Collections.unmodifiableList(appSearchSchema.mParentTypes))) {
            return getProperties().equals(appSearchSchema.getProperties());
        }
        return false;
    }

    public final List getProperties() {
        Object stringPropertyConfig;
        if (this.mPropertyConfigParcels.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(((ArrayList) this.mPropertyConfigParcels).size());
        for (int i = 0; i < ((ArrayList) this.mPropertyConfigParcels).size(); i++) {
            PropertyConfigParcel propertyConfigParcel = (PropertyConfigParcel) ((ArrayList) this.mPropertyConfigParcels).get(i);
            propertyConfigParcel.getClass();
            int i2 = propertyConfigParcel.mDataType;
            switch (i2) {
                case 1:
                    stringPropertyConfig = new StringPropertyConfig(propertyConfigParcel);
                    break;
                case 2:
                    stringPropertyConfig = new LongPropertyConfig(propertyConfigParcel);
                    break;
                case 3:
                    stringPropertyConfig = new BytesPropertyConfig(propertyConfigParcel);
                    break;
                case 4:
                    stringPropertyConfig = new BytesPropertyConfig(propertyConfigParcel);
                    break;
                case 5:
                    stringPropertyConfig = new BytesPropertyConfig(propertyConfigParcel);
                    break;
                case 6:
                    stringPropertyConfig = new DocumentPropertyConfig(propertyConfigParcel);
                    break;
                case 7:
                    stringPropertyConfig = new BytesPropertyConfig(propertyConfigParcel);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported property bundle of type " + i2 + "; contents: " + propertyConfigParcel);
            }
            arrayList.add(stringPropertyConfig);
        }
        return arrayList;
    }

    public final int hashCode() {
        return Objects.hash(this.mSchemaType, getProperties(), Collections.unmodifiableList(this.mParentTypes), this.mDescription);
    }

    public final String toString() {
        IndentingStringBuilder indentingStringBuilder = new IndentingStringBuilder();
        indentingStringBuilder.applyIndentToString("{\n");
        indentingStringBuilder.increaseIndentLevel();
        indentingStringBuilder.applyIndentToString("schemaType: \"");
        indentingStringBuilder.applyIndentToString(this.mSchemaType);
        indentingStringBuilder.applyIndentToString("\",\n");
        indentingStringBuilder.applyIndentToString("description: \"");
        indentingStringBuilder.applyIndentToString(this.mDescription);
        indentingStringBuilder.applyIndentToString("\",\n");
        indentingStringBuilder.applyIndentToString("properties: [\n");
        PropertyConfig[] propertyConfigArr = (PropertyConfig[]) getProperties().toArray(new PropertyConfig[0]);
        Arrays.sort(propertyConfigArr, new AppSearchSchema$$ExternalSyntheticLambda0());
        for (int i = 0; i < propertyConfigArr.length; i++) {
            PropertyConfig propertyConfig = propertyConfigArr[i];
            indentingStringBuilder.increaseIndentLevel();
            propertyConfig.appendPropertyConfigString(indentingStringBuilder);
            if (i != propertyConfigArr.length - 1) {
                indentingStringBuilder.applyIndentToString(",\n");
            }
            indentingStringBuilder.decreaseIndentLevel();
        }
        indentingStringBuilder.applyIndentToString("\n");
        indentingStringBuilder.applyIndentToString("]\n");
        indentingStringBuilder.decreaseIndentLevel();
        indentingStringBuilder.applyIndentToString("}");
        return indentingStringBuilder.mStringBuilder.toString();
    }
}
