package androidx.appsearch.builtintypes;

import androidx.appsearch.builtintypes.Thing;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ContactPoint extends Thing {
    public final List mAddresses;
    public final List mEmails;
    public final String mLabel;
    public final List mTelephones;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends Thing.BuilderImpl {
        public List mAddresses;
        public List mEmails;
        public List mTelephones;
    }

    public ContactPoint(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2, String str7, List list3, List list4, List list5) {
        super(str, str2, i, j, j2, str3, list, str4, str5, str6, list2);
        this.mLabel = str7;
        this.mAddresses = Collections.unmodifiableList(list3);
        this.mEmails = Collections.unmodifiableList(list4);
        this.mTelephones = Collections.unmodifiableList(list5);
    }
}
