package androidx.appsearch.app;

import android.os.Bundle;
import androidx.appsearch.safeparcel.AbstractSafeParcelable;
import androidx.appsearch.util.BundleUtil;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SearchSpec extends AbstractSafeParcelable {
    public final String mAdvancedRankingExpression;
    public final List mEnabledFeatures;
    public final List mInformationalRankingExpressions;
    public final List mNamespaces;
    public final List mPackageNames;
    public final Bundle mProjectionTypePropertyMasks;
    public final int mResultCountPerPage;
    public final List mSchemas;
    public final List mSearchEmbeddings;
    public final int mSnippetCountPerProperty;
    public final Bundle mTypePropertyFilters;
    public final Bundle mTypePropertyWeightsField;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public boolean mBuilt;
        public List mInformationalRankingExpressions;
        public List mNamespaces;
        public List mPackageNames;
        public Bundle mProjectionTypePropertyMasks;
        public int mResultCountPerPage;
        public List mSchemas;
        public List mSearchEmbeddings;
        public Bundle mTypePropertyFilters;
        public Bundle mTypePropertyWeights;

        public final void addFilterDocumentClasses(Class... clsArr) {
            resetIfBuilt();
            List asList = Arrays.asList(clsArr);
            asList.getClass();
            resetIfBuilt();
            ArrayList arrayList = new ArrayList(asList.size());
            DocumentClassFactoryRegistry documentClassFactoryRegistry = DocumentClassFactoryRegistry.getInstance();
            Iterator it = asList.iterator();
            while (it.hasNext()) {
                arrayList.add(documentClassFactoryRegistry.getOrCreateFactory((Class) it.next()).getSchemaName());
            }
            resetIfBuilt();
            this.mSchemas.addAll(arrayList);
        }

        public final void resetIfBuilt() {
            if (this.mBuilt) {
                this.mSchemas = new ArrayList(this.mSchemas);
                this.mTypePropertyFilters = BundleUtil.deepCopy(this.mTypePropertyFilters);
                this.mNamespaces = new ArrayList(this.mNamespaces);
                this.mPackageNames = new ArrayList(this.mPackageNames);
                this.mProjectionTypePropertyMasks = BundleUtil.deepCopy(this.mProjectionTypePropertyMasks);
                this.mTypePropertyWeights = BundleUtil.deepCopy(this.mTypePropertyWeights);
                this.mSearchEmbeddings = new ArrayList(this.mSearchEmbeddings);
                this.mInformationalRankingExpressions = new ArrayList(this.mInformationalRankingExpressions);
                this.mBuilt = false;
            }
        }
    }

    public SearchSpec(List list, List list2, Bundle bundle, List list3, int i, Bundle bundle2, Bundle bundle3, List list4, List list5, List list6) {
        list.getClass();
        this.mSchemas = Collections.unmodifiableList(list);
        list2.getClass();
        this.mNamespaces = Collections.unmodifiableList(list2);
        bundle.getClass();
        this.mTypePropertyFilters = bundle;
        list3.getClass();
        this.mPackageNames = Collections.unmodifiableList(list3);
        this.mResultCountPerPage = i;
        this.mSnippetCountPerProperty = 10000;
        bundle2.getClass();
        this.mProjectionTypePropertyMasks = bundle2;
        bundle3.getClass();
        this.mTypePropertyWeightsField = bundle3;
        this.mAdvancedRankingExpression = "";
        this.mEnabledFeatures = Collections.unmodifiableList(list4);
        if (list5 != null) {
            this.mSearchEmbeddings = Collections.unmodifiableList(list5);
        } else {
            this.mSearchEmbeddings = Collections.emptyList();
        }
        if (list6 != null) {
            this.mInformationalRankingExpressions = Collections.unmodifiableList(list6);
        } else {
            this.mInformationalRankingExpressions = Collections.emptyList();
        }
    }

    public final ArrayMap getFilterProperties() {
        Set<String> keySet = this.mTypePropertyFilters.keySet();
        ArrayMap arrayMap = new ArrayMap(keySet.size());
        for (String str : keySet) {
            ArrayList<String> stringArrayList = this.mTypePropertyFilters.getStringArrayList(str);
            stringArrayList.getClass();
            arrayMap.put(str, stringArrayList);
        }
        return arrayMap;
    }

    public final ArrayMap getPropertyWeights() {
        Set<String> keySet = this.mTypePropertyWeightsField.keySet();
        ArrayMap arrayMap = new ArrayMap(keySet.size());
        for (String str : keySet) {
            Bundle bundle = this.mTypePropertyWeightsField.getBundle(str);
            if (bundle != null) {
                Set<String> keySet2 = bundle.keySet();
                ArrayMap arrayMap2 = new ArrayMap(keySet2.size());
                for (String str2 : keySet2) {
                    arrayMap2.put(str2, Double.valueOf(bundle.getDouble(str2)));
                }
                arrayMap.put(str, arrayMap2);
            }
        }
        return arrayMap;
    }
}
