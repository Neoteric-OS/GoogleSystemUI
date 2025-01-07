package androidx.room.util;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.builders.MapBuilder;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__IndentKt;
import kotlin.text.StringsKt__StringsKt$splitToSequence$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TableInfo {
    public final Map columns;
    public final Set foreignKeys;
    public final Set indices;
    public final String name;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Column {
        public final int affinity;
        public final int createdFrom;
        public final String defaultValue;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        public Column(String str, String str2, boolean z, int i, String str3, int i2) {
            this.name = str;
            this.type = str2;
            this.notNull = z;
            this.primaryKeyPosition = i;
            this.defaultValue = str3;
            this.createdFrom = i2;
            String upperCase = str2.toUpperCase(Locale.ROOT);
            this.affinity = StringsKt.contains$default(upperCase, "INT") ? 3 : (StringsKt.contains$default(upperCase, "CHAR") || StringsKt.contains$default(upperCase, "CLOB") || StringsKt.contains$default(upperCase, "TEXT")) ? 2 : StringsKt.contains$default(upperCase, "BLOB") ? 5 : (StringsKt.contains$default(upperCase, "REAL") || StringsKt.contains$default(upperCase, "FLOA") || StringsKt.contains$default(upperCase, "DOUB")) ? 4 : 1;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Column) {
                Column column = (Column) obj;
                if (this.primaryKeyPosition == column.primaryKeyPosition && Intrinsics.areEqual(this.name, column.name) && this.notNull == column.notNull) {
                    int i = column.createdFrom;
                    String str = column.defaultValue;
                    String str2 = this.defaultValue;
                    int i2 = this.createdFrom;
                    if ((i2 != 1 || i != 2 || str2 == null || TableInfoKt.defaultValueEqualsCommon(str2, str)) && ((i2 != 2 || i != 1 || str == null || TableInfoKt.defaultValueEqualsCommon(str, str2)) && ((i2 == 0 || i2 != i || (str2 == null ? str == null : TableInfoKt.defaultValueEqualsCommon(str2, str))) && this.affinity == column.affinity))) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final int hashCode() {
            return (((((this.name.hashCode() * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public final String toString() {
            String joinToString$default;
            StringBuilder sb = new StringBuilder("\n            |Column {\n            |   name = '");
            sb.append(this.name);
            sb.append("',\n            |   type = '");
            sb.append(this.type);
            sb.append("',\n            |   affinity = '");
            sb.append(this.affinity);
            sb.append("',\n            |   notNull = '");
            sb.append(this.notNull);
            sb.append("',\n            |   primaryKeyPosition = '");
            sb.append(this.primaryKeyPosition);
            sb.append("',\n            |   defaultValue = '");
            String str = this.defaultValue;
            if (str == null) {
                str = "undefined";
            }
            sb.append(str);
            sb.append("'\n            |}\n        ");
            joinToString$default = SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(r2, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(StringsKt__IndentKt.trimMargin$default(sb.toString()))), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            return joinToString$default;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ForeignKey {
        public final List columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String str, String str2, String str3, List list, List list2) {
            this.referenceTable = str;
            this.onDelete = str2;
            this.onUpdate = str3;
            this.columnNames = list;
            this.referenceColumnNames = list2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ForeignKey) {
                ForeignKey foreignKey = (ForeignKey) obj;
                if (Intrinsics.areEqual(this.referenceTable, foreignKey.referenceTable) && Intrinsics.areEqual(this.onDelete, foreignKey.onDelete) && Intrinsics.areEqual(this.onUpdate, foreignKey.onUpdate) && Intrinsics.areEqual(this.columnNames, foreignKey.columnNames)) {
                    return Intrinsics.areEqual(this.referenceColumnNames, foreignKey.referenceColumnNames);
                }
            }
            return false;
        }

        public final int hashCode() {
            return this.referenceColumnNames.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.onUpdate, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.onDelete, this.referenceTable.hashCode() * 31, 31), 31), 31, this.columnNames);
        }

        public final String toString() {
            String joinToString$default;
            StringBuilder sb = new StringBuilder("\n            |ForeignKey {\n            |   referenceTable = '");
            sb.append(this.referenceTable);
            sb.append("',\n            |   onDelete = '");
            sb.append(this.onDelete);
            sb.append("',\n            |   onUpdate = '");
            sb.append(this.onUpdate);
            sb.append("',\n            |   columnNames = {");
            SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(r1, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(CollectionsKt.joinToString$default(CollectionsKt.sorted(this.columnNames), ",", null, null, null, 62))), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default("},", new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1("},")), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            Unit unit = Unit.INSTANCE;
            sb.append(unit);
            sb.append("\n            |   referenceColumnNames = {");
            SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(r8, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(CollectionsKt.joinToString$default(CollectionsKt.sorted(this.referenceColumnNames), ",", null, null, null, 62))), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(" }", new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(" }")), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            sb.append(unit);
            sb.append("\n            |}\n        ");
            joinToString$default = SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(r8, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(StringsKt__IndentKt.trimMargin$default(sb.toString()))), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            return joinToString$default;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Index {
        public final List columns;
        public final String name;
        public final List orders;
        public final boolean unique;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [java.util.Collection, java.util.List] */
        /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.util.ArrayList] */
        public Index(String str, boolean z, List list, List list2) {
            this.name = str;
            this.unique = z;
            this.columns = list;
            this.orders = list2;
            if (list2.isEmpty()) {
                int size = list.size();
                list2 = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    list2.add("ASC");
                }
            }
            this.orders = list2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Index) {
                Index index = (Index) obj;
                if (this.unique == index.unique && Intrinsics.areEqual(this.columns, index.columns) && Intrinsics.areEqual(this.orders, index.orders)) {
                    String str = this.name;
                    boolean startsWith = str.startsWith("index_");
                    String str2 = index.name;
                    return startsWith ? str2.startsWith("index_") : str.equals(str2);
                }
            }
            return false;
        }

        public final int hashCode() {
            String str = this.name;
            return this.orders.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((((str.startsWith("index_") ? "index_".hashCode() : str.hashCode()) * 31) + (this.unique ? 1 : 0)) * 31, 31, this.columns);
        }

        public final String toString() {
            String joinToString$default;
            StringBuilder sb = new StringBuilder("\n            |Index {\n            |   name = '");
            sb.append(this.name);
            sb.append("',\n            |   unique = '");
            sb.append(this.unique);
            sb.append("',\n            |   columns = {");
            SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(r1, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(CollectionsKt.joinToString$default(this.columns, ",", null, null, null, 62))), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default("},", new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1("},")), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            Unit unit = Unit.INSTANCE;
            sb.append(unit);
            sb.append("\n            |   orders = {");
            SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(r9, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(CollectionsKt.joinToString$default(this.orders, ",", null, null, null, 62))), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(" }", new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(" }")), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            sb.append(unit);
            sb.append("\n            |}\n        ");
            joinToString$default = SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(r9, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(StringsKt__IndentKt.trimMargin$default(sb.toString()))), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
                final /* synthetic */ String $indent = "    ";

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2 = (String) obj;
                    return StringsKt__StringsJVMKt.isBlank(str2) ? str2.length() < this.$indent.length() ? this.$indent : str2 : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str2);
                }
            }), "\n", null, 62);
            return joinToString$default;
        }
    }

    public TableInfo(String str, Map map, Set set, Set set2) {
        this.name = str;
        this.columns = map;
        this.foreignKeys = set;
        this.indices = set2;
    }

    /* JADX WARN: Finally extract failed */
    public static final TableInfo read(SQLiteConnection sQLiteConnection, String str) {
        Map build;
        SetBuilder setBuilder;
        SQLiteStatement prepare = sQLiteConnection.prepare("PRAGMA table_info(`" + str + "`)");
        try {
            long j = 0;
            if (prepare.step()) {
                int columnIndexOf = SQLiteStatementUtil.columnIndexOf(prepare, "name");
                int columnIndexOf2 = SQLiteStatementUtil.columnIndexOf(prepare, "type");
                int columnIndexOf3 = SQLiteStatementUtil.columnIndexOf(prepare, "notnull");
                int columnIndexOf4 = SQLiteStatementUtil.columnIndexOf(prepare, "pk");
                int columnIndexOf5 = SQLiteStatementUtil.columnIndexOf(prepare, "dflt_value");
                MapBuilder mapBuilder = new MapBuilder();
                do {
                    String text = prepare.getText(columnIndexOf);
                    mapBuilder.put(text, new Column(text, prepare.getText(columnIndexOf2), prepare.getLong(columnIndexOf3) != 0, (int) prepare.getLong(columnIndexOf4), prepare.isNull(columnIndexOf5) ? null : prepare.getText(columnIndexOf5), 2));
                } while (prepare.step());
                build = mapBuilder.build();
            } else {
                build = MapsKt.emptyMap();
            }
            prepare.close();
            prepare = sQLiteConnection.prepare("PRAGMA foreign_key_list(`" + str + "`)");
            try {
                int columnIndexOf6 = SQLiteStatementUtil.columnIndexOf(prepare, "id");
                int columnIndexOf7 = SQLiteStatementUtil.columnIndexOf(prepare, "seq");
                int columnIndexOf8 = SQLiteStatementUtil.columnIndexOf(prepare, "table");
                int columnIndexOf9 = SQLiteStatementUtil.columnIndexOf(prepare, "on_delete");
                int columnIndexOf10 = SQLiteStatementUtil.columnIndexOf(prepare, "on_update");
                List readForeignKeyFieldMappings = SchemaInfoUtilKt.readForeignKeyFieldMappings(prepare);
                prepare.reset();
                SetBuilder setBuilder2 = new SetBuilder();
                while (prepare.step()) {
                    if (prepare.getLong(columnIndexOf7) == j) {
                        int i = (int) prepare.getLong(columnIndexOf6);
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        int i2 = columnIndexOf6;
                        ArrayList<ForeignKeyWithSequence> arrayList3 = new ArrayList();
                        for (Object obj : readForeignKeyFieldMappings) {
                            int i3 = columnIndexOf7;
                            List list = readForeignKeyFieldMappings;
                            if (((ForeignKeyWithSequence) obj).id == i) {
                                arrayList3.add(obj);
                            }
                            columnIndexOf7 = i3;
                            readForeignKeyFieldMappings = list;
                        }
                        int i4 = columnIndexOf7;
                        List list2 = readForeignKeyFieldMappings;
                        for (ForeignKeyWithSequence foreignKeyWithSequence : arrayList3) {
                            arrayList.add(foreignKeyWithSequence.from);
                            arrayList2.add(foreignKeyWithSequence.to);
                        }
                        setBuilder2.add(new ForeignKey(prepare.getText(columnIndexOf8), prepare.getText(columnIndexOf9), prepare.getText(columnIndexOf10), arrayList, arrayList2));
                        columnIndexOf6 = i2;
                        columnIndexOf7 = i4;
                        readForeignKeyFieldMappings = list2;
                        j = 0;
                    }
                }
                SetBuilder build2 = setBuilder2.build();
                prepare.close();
                prepare = sQLiteConnection.prepare("PRAGMA index_list(`" + str + "`)");
                try {
                    int columnIndexOf11 = SQLiteStatementUtil.columnIndexOf(prepare, "name");
                    int columnIndexOf12 = SQLiteStatementUtil.columnIndexOf(prepare, "origin");
                    int columnIndexOf13 = SQLiteStatementUtil.columnIndexOf(prepare, "unique");
                    if (columnIndexOf11 != -1 && columnIndexOf12 != -1 && columnIndexOf13 != -1) {
                        SetBuilder setBuilder3 = new SetBuilder();
                        while (prepare.step()) {
                            if ("c".equals(prepare.getText(columnIndexOf12))) {
                                Index readIndex = SchemaInfoUtilKt.readIndex(sQLiteConnection, prepare.getText(columnIndexOf11), prepare.getLong(columnIndexOf13) == 1);
                                if (readIndex != null) {
                                    setBuilder3.add(readIndex);
                                }
                            }
                        }
                        setBuilder = setBuilder3.build();
                        return new TableInfo(str, build, build2, setBuilder);
                    }
                    prepare.close();
                    setBuilder = null;
                    return new TableInfo(str, build, build2, setBuilder);
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } finally {
            prepare.close();
        }
    }

    public final boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (obj instanceof TableInfo) {
            TableInfo tableInfo = (TableInfo) obj;
            if (Intrinsics.areEqual(this.name, tableInfo.name) && Intrinsics.areEqual(this.columns, tableInfo.columns) && Intrinsics.areEqual(this.foreignKeys, tableInfo.foreignKeys)) {
                Set set2 = this.indices;
                if (set2 == null || (set = tableInfo.indices) == null) {
                    return true;
                }
                return Intrinsics.areEqual(set2, set);
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.foreignKeys.hashCode() + ((this.columns.hashCode() + (this.name.hashCode() * 31)) * 31);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x004b, code lost:
    
        if (r4 == null) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "\n            |TableInfo {\n            |    name = '"
            r0.<init>(r1)
            java.lang.String r1 = r4.name
            r0.append(r1)
            java.lang.String r1 = "',\n            |    columns = {"
            r0.append(r1)
            java.util.Map r1 = r4.columns
            java.util.Collection r1 = r1.values()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            androidx.room.util.TableInfoKt$toStringCommon$$inlined$sortedBy$1 r2 = new androidx.room.util.TableInfoKt$toStringCommon$$inlined$sortedBy$1
            r3 = 0
            r2.<init>()
            java.util.List r1 = kotlin.collections.CollectionsKt.sortedWith(r1, r2)
            java.lang.String r1 = androidx.room.util.TableInfoKt.formatString(r1)
            r0.append(r1)
            java.lang.String r1 = "\n            |    foreignKeys = {"
            r0.append(r1)
            java.util.Set r1 = r4.foreignKeys
            java.lang.String r1 = androidx.room.util.TableInfoKt.formatString(r1)
            r0.append(r1)
            java.lang.String r1 = "\n            |    indices = {"
            r0.append(r1)
            java.util.Set r4 = r4.indices
            if (r4 == 0) goto L4d
            androidx.room.util.TableInfoKt$toStringCommon$$inlined$sortedBy$1 r1 = new androidx.room.util.TableInfoKt$toStringCommon$$inlined$sortedBy$1
            r2 = 1
            r1.<init>()
            java.util.List r4 = kotlin.collections.CollectionsKt.sortedWith(r4, r1)
            if (r4 != 0) goto L4f
        L4d:
            kotlin.collections.EmptyList r4 = kotlin.collections.EmptyList.INSTANCE
        L4f:
            java.lang.String r4 = androidx.room.util.TableInfoKt.formatString(r4)
            r0.append(r4)
            java.lang.String r4 = "\n            |}\n        "
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = kotlin.text.StringsKt__IndentKt.trimMargin$default(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.TableInfo.toString():java.lang.String");
    }
}
