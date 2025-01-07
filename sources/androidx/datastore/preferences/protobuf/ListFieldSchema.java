package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.Internal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ListFieldSchema {
    public static final ListFieldSchemaFull FULL_INSTANCE = new ListFieldSchemaFull();
    public static final ListFieldSchemaLite LITE_INSTANCE = new ListFieldSchemaLite();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ListFieldSchemaFull extends ListFieldSchema {
        public static final Class UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.emptyList()).getClass();

        @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
        public final void makeImmutableListAt(long j, Object obj) {
            Object unmodifiableList;
            List list = (List) UnsafeUtil.getObject(j, obj);
            if (list instanceof LazyStringList) {
                unmodifiableList = ((LazyStringList) list).getUnmodifiableView();
            } else if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass())) {
                return;
            } else {
                unmodifiableList = Collections.unmodifiableList(list);
            }
            UnsafeUtil.putObject(j, obj, unmodifiableList);
        }

        @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
        public final void mergeListsAt(long j, Object obj, Object obj2) {
            List list = (List) UnsafeUtil.getObject(j, obj2);
            List mutableListAt = mutableListAt(obj, j, list.size());
            int size = mutableListAt.size();
            int size2 = list.size();
            if (size > 0 && size2 > 0) {
                mutableListAt.addAll(list);
            }
            if (size > 0) {
                list = mutableListAt;
            }
            UnsafeUtil.putObject(j, obj, list);
        }

        @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
        public final List mutableListAt(long j, Object obj) {
            return mutableListAt(obj, j, 10);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static List mutableListAt(Object obj, long j, int i) {
            LazyStringArrayList lazyStringArrayList;
            List list = (List) UnsafeUtil.getObject(j, obj);
            if (list.isEmpty()) {
                List lazyStringArrayList2 = list instanceof LazyStringList ? new LazyStringArrayList(i) : new ArrayList(i);
                UnsafeUtil.putObject(j, obj, lazyStringArrayList2);
                return lazyStringArrayList2;
            }
            if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass())) {
                ArrayList arrayList = new ArrayList(list.size() + i);
                arrayList.addAll(list);
                UnsafeUtil.putObject(j, obj, arrayList);
                lazyStringArrayList = arrayList;
            } else {
                if (!(list instanceof UnmodifiableLazyStringList)) {
                    return list;
                }
                LazyStringArrayList lazyStringArrayList3 = new LazyStringArrayList(list.size() + i);
                lazyStringArrayList3.addAll((UnmodifiableLazyStringList) list);
                UnsafeUtil.putObject(j, obj, lazyStringArrayList3);
                lazyStringArrayList = lazyStringArrayList3;
            }
            return lazyStringArrayList;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ListFieldSchemaLite extends ListFieldSchema {
        @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
        public final void makeImmutableListAt(long j, Object obj) {
            ((AbstractProtobufList) ((Internal.ProtobufList) UnsafeUtil.getObject(j, obj))).isMutable = false;
        }

        @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
        public final void mergeListsAt(long j, Object obj, Object obj2) {
            Internal.ProtobufList protobufList = (Internal.ProtobufList) UnsafeUtil.getObject(j, obj);
            Internal.ProtobufList protobufList2 = (Internal.ProtobufList) UnsafeUtil.getObject(j, obj2);
            int size = protobufList.size();
            int size2 = protobufList2.size();
            if (size > 0 && size2 > 0) {
                if (!((AbstractProtobufList) protobufList).isMutable) {
                    protobufList = protobufList.mutableCopyWithCapacity(size2 + size);
                }
                protobufList.addAll(protobufList2);
            }
            if (size > 0) {
                protobufList2 = protobufList;
            }
            UnsafeUtil.putObject(j, obj, protobufList2);
        }

        @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
        public final List mutableListAt(long j, Object obj) {
            Internal.ProtobufList protobufList = (Internal.ProtobufList) UnsafeUtil.getObject(j, obj);
            if (((AbstractProtobufList) protobufList).isMutable) {
                return protobufList;
            }
            int size = protobufList.size();
            Internal.ProtobufList mutableCopyWithCapacity = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
            UnsafeUtil.putObject(j, obj, mutableCopyWithCapacity);
            return mutableCopyWithCapacity;
        }
    }

    public abstract void makeImmutableListAt(long j, Object obj);

    public abstract void mergeListsAt(long j, Object obj, Object obj2);

    public abstract List mutableListAt(long j, Object obj);
}
