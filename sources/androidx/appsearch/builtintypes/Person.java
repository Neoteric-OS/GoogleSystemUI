package androidx.appsearch.builtintypes;

import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class Person extends Thing {
    public final List mAdditionalNameTypes;
    public final List mAdditionalNames;
    public final List mAffiliations;
    public final List mContactPoints;
    public final String mExternalUri;
    public final String mFamilyName;
    public final String mGivenName;
    public final String mImageUri;
    public final boolean mIsBot;
    public final boolean mIsImportant;
    public final String mMiddleName;
    public final List mNotes;
    public final List mRelations;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AdditionalName {
        public final int mType;
        public final String mValue;

        public AdditionalName(int i, String str) {
            Preconditions.checkArgumentInRange("type", i, 0, 2);
            this.mType = i;
            this.mValue = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdditionalName)) {
                return false;
            }
            AdditionalName additionalName = (AdditionalName) obj;
            return this.mType == additionalName.mType && this.mValue.equals(additionalName.mValue);
        }

        public final int hashCode() {
            return (this.mType + this.mValue).hashCode();
        }
    }

    public Person(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2, String str7, String str8, String str9, String str10, String str11, boolean z, boolean z2, List list3, List list4, List list5, List list6, List list7, List list8) {
        super(str, str2, i, j, j2, str3, list, str4, str5, str6, list2);
        this.mGivenName = str7;
        this.mMiddleName = str8;
        this.mFamilyName = str9;
        this.mExternalUri = str10;
        this.mImageUri = str11;
        this.mIsImportant = z;
        this.mIsBot = z2;
        this.mNotes = Collections.unmodifiableList(list3);
        List unmodifiableList = Collections.unmodifiableList(list4);
        this.mAdditionalNameTypes = unmodifiableList;
        this.mAdditionalNames = Collections.unmodifiableList(list5);
        this.mAffiliations = Collections.unmodifiableList(list6);
        this.mRelations = Collections.unmodifiableList(list7);
        this.mContactPoints = Collections.unmodifiableList(list8);
        ArrayList arrayList = new ArrayList(unmodifiableList.size());
        for (int i2 = 0; i2 < this.mAdditionalNameTypes.size(); i2++) {
            arrayList.add(new AdditionalName(((Long) this.mAdditionalNameTypes.get(i2)).intValue(), (String) this.mAdditionalNames.get(i2)));
        }
        Collections.unmodifiableList(arrayList);
    }
}
