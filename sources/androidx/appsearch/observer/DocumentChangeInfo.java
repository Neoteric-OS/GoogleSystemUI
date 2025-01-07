package androidx.appsearch.observer;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DocumentChangeInfo {
    public final Set mChangedDocumentIds;
    public final String mDatabase;
    public final String mNamespace;
    public final String mPackageName;
    public final String mSchemaName;

    public DocumentChangeInfo(String str, String str2, String str3, String str4, Set set) {
        str.getClass();
        this.mPackageName = str;
        str2.getClass();
        this.mDatabase = str2;
        str3.getClass();
        this.mNamespace = str3;
        str4.getClass();
        this.mSchemaName = str4;
        set.getClass();
        this.mChangedDocumentIds = Collections.unmodifiableSet(set);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentChangeInfo)) {
            return false;
        }
        DocumentChangeInfo documentChangeInfo = (DocumentChangeInfo) obj;
        return this.mPackageName.equals(documentChangeInfo.mPackageName) && this.mDatabase.equals(documentChangeInfo.mDatabase) && this.mNamespace.equals(documentChangeInfo.mNamespace) && this.mSchemaName.equals(documentChangeInfo.mSchemaName) && this.mChangedDocumentIds.equals(documentChangeInfo.mChangedDocumentIds);
    }

    public final int hashCode() {
        return Objects.hash(this.mPackageName, this.mDatabase, this.mNamespace, this.mSchemaName, this.mChangedDocumentIds);
    }

    public final String toString() {
        return "DocumentChangeInfo{packageName='" + this.mPackageName + "', database='" + this.mDatabase + "', namespace='" + this.mNamespace + "', schemaName='" + this.mSchemaName + "', changedDocumentIds='" + this.mChangedDocumentIds + "'}";
    }
}
