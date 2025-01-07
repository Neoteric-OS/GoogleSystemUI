package androidx.appsearch.builtintypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class Thing {
    public final List mAlternateNames;
    public final long mCreationTimestampMillis;
    public final String mDescription;
    public final int mDocumentScore;
    public final long mDocumentTtlMillis;
    public final String mId;
    public final String mImage;
    public final String mName;
    public final String mNamespace;
    public final List mPotentialActions;
    public final String mUrl;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends BuilderImpl {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class BuilderImpl {
        public long mCreationTimestampMillis;
        public String mDescription;
        public int mDocumentScore;
        public long mDocumentTtlMillis;
        public final String mId;
        public String mImage;
        public String mName;
        public final String mNamespace;
        public String mUrl;
        public List mAlternateNames = new ArrayList();
        public List mPotentialActions = new ArrayList();
        public boolean mBuilt = false;

        public BuilderImpl(String str, String str2) {
            str.getClass();
            this.mNamespace = str;
            str2.getClass();
            this.mId = str2;
            this.mCreationTimestampMillis = -1L;
        }

        public final void resetIfBuilt() {
            if (this.mBuilt) {
                this.mAlternateNames = new ArrayList(this.mAlternateNames);
                this.mPotentialActions = new ArrayList(this.mPotentialActions);
                this.mBuilt = false;
            }
        }

        public final void setAlternateNames(List list) {
            resetIfBuilt();
            resetIfBuilt();
            this.mAlternateNames.clear();
            if (list != null) {
                this.mAlternateNames.addAll(list);
            }
        }

        public final void setPotentialActions(List list) {
            resetIfBuilt();
            resetIfBuilt();
            this.mPotentialActions.clear();
            if (list != null) {
                this.mPotentialActions.addAll(list);
            }
        }
    }

    public Thing(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2) {
        str.getClass();
        this.mNamespace = str;
        str2.getClass();
        this.mId = str2;
        this.mDocumentScore = i;
        this.mCreationTimestampMillis = j;
        this.mDocumentTtlMillis = j2;
        this.mName = str3;
        if (list == null) {
            this.mAlternateNames = Collections.emptyList();
        } else {
            this.mAlternateNames = Collections.unmodifiableList(list);
        }
        this.mDescription = str4;
        this.mImage = str5;
        this.mUrl = str6;
        if (list2 == null) {
            this.mPotentialActions = Collections.emptyList();
        } else {
            this.mPotentialActions = Collections.unmodifiableList(list2);
        }
    }
}
