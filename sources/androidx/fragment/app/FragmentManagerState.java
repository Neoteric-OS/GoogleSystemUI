package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public ArrayList mActive;
    public ArrayList mAdded;
    public BackStackRecordState[] mBackStack;
    public int mBackStackIndex;
    public ArrayList mBackStackStateKeys;
    public ArrayList mBackStackStates;
    public ArrayList mLaunchedFragments;
    public String mPrimaryNavActiveWho;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.fragment.app.FragmentManagerState$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            FragmentManagerState fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.mPrimaryNavActiveWho = null;
            fragmentManagerState.mBackStackStateKeys = new ArrayList();
            fragmentManagerState.mBackStackStates = new ArrayList();
            fragmentManagerState.mActive = parcel.createStringArrayList();
            fragmentManagerState.mAdded = parcel.createStringArrayList();
            fragmentManagerState.mBackStack = (BackStackRecordState[]) parcel.createTypedArray(BackStackRecordState.CREATOR);
            fragmentManagerState.mBackStackIndex = parcel.readInt();
            fragmentManagerState.mPrimaryNavActiveWho = parcel.readString();
            fragmentManagerState.mBackStackStateKeys = parcel.createStringArrayList();
            fragmentManagerState.mBackStackStates = parcel.createTypedArrayList(BackStackState.CREATOR);
            fragmentManagerState.mLaunchedFragments = parcel.createTypedArrayList(FragmentManager$LaunchedFragmentInfo.CREATOR);
            return fragmentManagerState;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new FragmentManagerState[i];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.mActive);
        parcel.writeStringList(this.mAdded);
        parcel.writeTypedArray(this.mBackStack, i);
        parcel.writeInt(this.mBackStackIndex);
        parcel.writeString(this.mPrimaryNavActiveWho);
        parcel.writeStringList(this.mBackStackStateKeys);
        parcel.writeTypedList(this.mBackStackStates);
        parcel.writeTypedList(this.mLaunchedFragments);
    }
}
