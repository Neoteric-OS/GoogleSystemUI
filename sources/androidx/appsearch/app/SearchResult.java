package androidx.appsearch.app;

import androidx.appsearch.safeparcel.AbstractSafeParcelable;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SearchResult extends AbstractSafeParcelable {
    public final GenericDocumentParcel mDocument;
    public GenericDocument mDocumentCached;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final String mDatabaseName;
        public GenericDocument mGenericDocument;
        public final String mPackageName;
        public List mMatchInfos = new ArrayList();
        public List mInformationalRankingSignals = new ArrayList();
        public List mJoinedResults = new ArrayList();
        public boolean mBuilt = false;

        public Builder(String str, String str2) {
            str.getClass();
            this.mPackageName = str;
            str2.getClass();
            this.mDatabaseName = str2;
        }

        public final void resetIfBuilt() {
            if (this.mBuilt) {
                this.mMatchInfos = new ArrayList(this.mMatchInfos);
                this.mJoinedResults = new ArrayList(this.mJoinedResults);
                this.mInformationalRankingSignals = new ArrayList(this.mInformationalRankingSignals);
                this.mBuilt = false;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MatchInfo extends AbstractSafeParcelable {
    }

    public SearchResult(GenericDocumentParcel genericDocumentParcel, List list, String str, String str2, List list2, List list3) {
        genericDocumentParcel.getClass();
        this.mDocument = genericDocumentParcel;
        list.getClass();
        str.getClass();
        str2.getClass();
        list2.getClass();
        Collections.unmodifiableList(list2);
        if (list3 != null) {
            Collections.unmodifiableList(list3);
        } else {
            Collections.emptyList();
        }
    }
}
