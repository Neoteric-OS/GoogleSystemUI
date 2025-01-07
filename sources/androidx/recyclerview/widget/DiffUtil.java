package androidx.recyclerview.widget;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DiffUtil {
    public static final AnonymousClass1 DIAGONAL_COMPARATOR = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.recyclerview.widget.DiffUtil$1, reason: invalid class name */
    public final class AnonymousClass1 implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ((Diagonal) obj).x - ((Diagonal) obj2).x;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Diagonal {
        public final int size;
        public final int x;
        public final int y;

        public Diagonal(int i, int i2, int i3) {
            this.x = i;
            this.y = i2;
            this.size = i3;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DiffResult {
        public final Callback mCallback;
        public final boolean mDetectMoves;
        public final List mDiagonals;
        public final int[] mNewItemStatuses;
        public final int mNewListSize;
        public final int[] mOldItemStatuses;
        public final int mOldListSize;

        public DiffResult(Callback callback, List list, int[] iArr, int[] iArr2) {
            int[] iArr3;
            int[] iArr4;
            Callback callback2;
            int i;
            Diagonal diagonal;
            int i2;
            this.mDiagonals = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.mCallback = callback;
            int oldListSize = callback.getOldListSize();
            this.mOldListSize = oldListSize;
            int newListSize = callback.getNewListSize();
            this.mNewListSize = newListSize;
            this.mDetectMoves = true;
            Diagonal diagonal2 = list.isEmpty() ? null : (Diagonal) ((ArrayList) list).get(0);
            if (diagonal2 == null || diagonal2.x != 0 || diagonal2.y != 0) {
                list.add(0, new Diagonal(0, 0, 0));
            }
            list.add(new Diagonal(oldListSize, newListSize, 0));
            Iterator it = list.iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                iArr3 = this.mNewItemStatuses;
                iArr4 = this.mOldItemStatuses;
                callback2 = this.mCallback;
                if (!hasNext) {
                    break;
                }
                Diagonal diagonal3 = (Diagonal) it.next();
                for (int i3 = 0; i3 < diagonal3.size; i3++) {
                    int i4 = diagonal3.x + i3;
                    int i5 = diagonal3.y + i3;
                    int i6 = callback2.areContentsTheSame(i4, i5) ? 1 : 2;
                    iArr4[i4] = (i5 << 4) | i6;
                    iArr3[i5] = (i4 << 4) | i6;
                }
            }
            if (this.mDetectMoves) {
                int i7 = 0;
                for (Diagonal diagonal4 : this.mDiagonals) {
                    while (true) {
                        i = diagonal4.x;
                        if (i7 < i) {
                            if (iArr4[i7] == 0) {
                                int size = ((ArrayList) this.mDiagonals).size();
                                int i8 = 0;
                                int i9 = 0;
                                while (true) {
                                    if (i8 < size) {
                                        diagonal = (Diagonal) ((ArrayList) this.mDiagonals).get(i8);
                                        while (true) {
                                            i2 = diagonal.y;
                                            if (i9 < i2) {
                                                if (iArr3[i9] == 0 && callback2.areItemsTheSame(i7, i9)) {
                                                    int i10 = callback2.areContentsTheSame(i7, i9) ? 8 : 4;
                                                    iArr4[i7] = (i9 << 4) | i10;
                                                    iArr3[i9] = i10 | (i7 << 4);
                                                } else {
                                                    i9++;
                                                }
                                            }
                                        }
                                    }
                                    i9 = diagonal.size + i2;
                                    i8++;
                                }
                            }
                            i7++;
                        }
                    }
                    i7 = diagonal4.size + i;
                }
            }
        }

        public static PostponedUpdate getPostponedUpdate(boolean z, Collection collection, int i) {
            PostponedUpdate postponedUpdate;
            Iterator it = ((ArrayDeque) collection).iterator();
            while (true) {
                if (!it.hasNext()) {
                    postponedUpdate = null;
                    break;
                }
                postponedUpdate = (PostponedUpdate) it.next();
                if (postponedUpdate.posInOwnerList == i && postponedUpdate.removal == z) {
                    it.remove();
                    break;
                }
            }
            while (it.hasNext()) {
                PostponedUpdate postponedUpdate2 = (PostponedUpdate) it.next();
                if (z) {
                    postponedUpdate2.currentPos--;
                } else {
                    postponedUpdate2.currentPos++;
                }
            }
            return postponedUpdate;
        }

        public final void dispatchUpdatesTo(ListUpdateCallback listUpdateCallback) {
            int[] iArr;
            Callback callback;
            int i;
            int i2;
            int i3;
            DiffResult diffResult = this;
            BatchingListUpdateCallback batchingListUpdateCallback = listUpdateCallback instanceof BatchingListUpdateCallback ? (BatchingListUpdateCallback) listUpdateCallback : new BatchingListUpdateCallback(listUpdateCallback);
            ArrayDeque arrayDeque = new ArrayDeque();
            int i4 = 1;
            int size = ((ArrayList) diffResult.mDiagonals).size() - 1;
            int i5 = diffResult.mOldListSize;
            int i6 = diffResult.mNewListSize;
            int i7 = i5;
            while (size >= 0) {
                Diagonal diagonal = (Diagonal) ((ArrayList) diffResult.mDiagonals).get(size);
                int i8 = diagonal.x;
                int i9 = diagonal.size;
                int i10 = i8 + i9;
                int i11 = diagonal.y;
                int i12 = i11 + i9;
                while (true) {
                    iArr = diffResult.mOldItemStatuses;
                    callback = diffResult.mCallback;
                    i = 0;
                    if (i7 <= i10) {
                        break;
                    }
                    i7--;
                    int i13 = iArr[i7];
                    if ((i13 & 12) != 0) {
                        int i14 = i13 >> 4;
                        PostponedUpdate postponedUpdate = getPostponedUpdate(false, arrayDeque, i14);
                        if (postponedUpdate != null) {
                            i3 = i6;
                            int i15 = (i5 - postponedUpdate.currentPos) - 1;
                            batchingListUpdateCallback.onMoved(i7, i15);
                            if ((i13 & 4) != 0) {
                                callback.getChangePayload(i7, i14);
                                batchingListUpdateCallback.onChanged(i15, 1, null);
                            }
                        } else {
                            i3 = i6;
                            arrayDeque.add(new PostponedUpdate(i7, (i5 - i7) - 1, true));
                        }
                    } else {
                        i3 = i6;
                        batchingListUpdateCallback.onRemoved(i7, i4);
                        i5--;
                    }
                    i6 = i3;
                    i4 = 1;
                }
                while (i6 > i12) {
                    i6--;
                    int i16 = diffResult.mNewItemStatuses[i6];
                    if ((i16 & 12) != 0) {
                        int i17 = i16 >> 4;
                        PostponedUpdate postponedUpdate2 = getPostponedUpdate(true, arrayDeque, i17);
                        if (postponedUpdate2 == null) {
                            arrayDeque.add(new PostponedUpdate(i6, i5 - i7, false));
                            i2 = 0;
                        } else {
                            i2 = 0;
                            batchingListUpdateCallback.onMoved((i5 - postponedUpdate2.currentPos) - 1, i7);
                            if ((i16 & 4) != 0) {
                                callback.getChangePayload(i17, i6);
                                batchingListUpdateCallback.onChanged(i7, 1, null);
                            }
                        }
                    } else {
                        i2 = i;
                        batchingListUpdateCallback.onInserted(i7, 1);
                        i5++;
                    }
                    diffResult = this;
                    i = i2;
                }
                i7 = diagonal.x;
                int i18 = i7;
                int i19 = i11;
                while (i < i9) {
                    if ((iArr[i18] & 15) == 2) {
                        callback.getChangePayload(i18, i19);
                        batchingListUpdateCallback.onChanged(i18, 1, null);
                    }
                    i18++;
                    i19++;
                    i++;
                }
                size--;
                diffResult = this;
                i4 = 1;
                i6 = i11;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PostponedUpdate {
        public int currentPos;
        public final int posInOwnerList;
        public final boolean removal;

        public PostponedUpdate(int i, int i2, boolean z) {
            this.posInOwnerList = i;
            this.currentPos = i2;
            this.removal = z;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Range {
        public int newListEnd;
        public int newListStart;
        public int oldListEnd;
        public int oldListStart;

        public final int newSize() {
            return this.newListEnd - this.newListStart;
        }

        public final int oldSize() {
            return this.oldListEnd - this.oldListStart;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Snake {
        public int endX;
        public int endY;
        public boolean reverse;
        public int startX;
        public int startY;

        public final int diagonalSize() {
            return Math.min(this.endX - this.startX, this.endY - this.startY);
        }
    }

    public static DiffResult calculateDiff(Callback callback) {
        ArrayList arrayList;
        ArrayList arrayList2;
        Range range;
        Snake snake;
        ArrayList arrayList3;
        ArrayList arrayList4;
        int i;
        Range range2;
        Range range3;
        int i2;
        int i3;
        Snake snake2;
        Snake snake3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        Range range4 = new Range();
        int i10 = 0;
        range4.oldListStart = 0;
        range4.oldListEnd = oldListSize;
        range4.newListStart = 0;
        range4.newListEnd = newListSize;
        arrayList6.add(range4);
        int i11 = oldListSize + newListSize;
        int i12 = 1;
        int i13 = (((i11 + 1) / 2) * 2) + 1;
        int[] iArr = new int[i13];
        int i14 = i13 / 2;
        int[] iArr2 = new int[i13];
        ArrayList arrayList7 = new ArrayList();
        while (!arrayList6.isEmpty()) {
            Range range5 = (Range) arrayList6.remove(arrayList6.size() - i12);
            if (range5.oldSize() >= i12 && range5.newSize() >= i12) {
                int newSize = ((range5.newSize() + range5.oldSize()) + i12) / 2;
                int i15 = i12 + i14;
                iArr[i15] = range5.oldListStart;
                iArr2[i15] = range5.oldListEnd;
                int i16 = i10;
                while (i16 < newSize) {
                    int i17 = Math.abs(range5.oldSize() - range5.newSize()) % 2 == i12 ? i12 : i10;
                    int oldSize = range5.oldSize() - range5.newSize();
                    int i18 = -i16;
                    int i19 = i18;
                    while (true) {
                        if (i19 > i16) {
                            arrayList = arrayList6;
                            i2 = i10;
                            arrayList2 = arrayList7;
                            i3 = newSize;
                            snake2 = null;
                            break;
                        }
                        if (i19 == i18 || (i19 != i16 && iArr[i19 + 1 + i14] > iArr[(i19 - 1) + i14])) {
                            i7 = iArr[i19 + 1 + i14];
                            i8 = i7;
                        } else {
                            i7 = iArr[(i19 - 1) + i14];
                            i8 = i7 + 1;
                        }
                        i3 = newSize;
                        arrayList = arrayList6;
                        int i20 = ((i8 - range5.oldListStart) + range5.newListStart) - i19;
                        int i21 = (i16 == 0 || i8 != i7) ? i20 : i20 - 1;
                        arrayList2 = arrayList7;
                        while (i8 < range5.oldListEnd && i20 < range5.newListEnd && callback.areItemsTheSame(i8, i20)) {
                            i8++;
                            i20++;
                        }
                        iArr[i19 + i14] = i8;
                        if (i17 != 0) {
                            int i22 = oldSize - i19;
                            i9 = i17;
                            if (i22 >= i18 + 1 && i22 <= i16 - 1 && iArr2[i22 + i14] <= i8) {
                                snake2 = new Snake();
                                snake2.startX = i7;
                                snake2.startY = i21;
                                snake2.endX = i8;
                                snake2.endY = i20;
                                i2 = 0;
                                snake2.reverse = false;
                                break;
                            }
                        } else {
                            i9 = i17;
                        }
                        i19 += 2;
                        i10 = 0;
                        newSize = i3;
                        arrayList6 = arrayList;
                        arrayList7 = arrayList2;
                        i17 = i9;
                    }
                    if (snake2 != null) {
                        snake = snake2;
                        range = range5;
                        break;
                    }
                    int i23 = (range5.oldSize() - range5.newSize()) % 2 == 0 ? 1 : i2;
                    int oldSize2 = range5.oldSize() - range5.newSize();
                    int i24 = i18;
                    while (true) {
                        if (i24 > i16) {
                            range = range5;
                            snake3 = null;
                            break;
                        }
                        if (i24 == i18 || (i24 != i16 && iArr2[i24 + 1 + i14] < iArr2[(i24 - 1) + i14])) {
                            i4 = iArr2[i24 + 1 + i14];
                            i5 = i4;
                        } else {
                            i4 = iArr2[(i24 - 1) + i14];
                            i5 = i4 - 1;
                        }
                        int i25 = range5.newListEnd - ((range5.oldListEnd - i5) - i24);
                        int i26 = (i16 == 0 || i5 != i4) ? i25 : i25 + 1;
                        while (i5 > range5.oldListStart && i25 > range5.newListStart) {
                            range = range5;
                            if (!callback.areItemsTheSame(i5 - 1, i25 - 1)) {
                                break;
                            }
                            i5--;
                            i25--;
                            range5 = range;
                        }
                        range = range5;
                        iArr2[i24 + i14] = i5;
                        if (i23 != 0 && (i6 = oldSize2 - i24) >= i18 && i6 <= i16 && iArr[i6 + i14] >= i5) {
                            snake3 = new Snake();
                            snake3.startX = i5;
                            snake3.startY = i25;
                            snake3.endX = i4;
                            snake3.endY = i26;
                            snake3.reverse = true;
                            break;
                        }
                        i24 += 2;
                        range5 = range;
                    }
                    if (snake3 != null) {
                        snake = snake3;
                        break;
                    }
                    i16++;
                    newSize = i3;
                    arrayList6 = arrayList;
                    arrayList7 = arrayList2;
                    range5 = range;
                    i12 = 1;
                    i10 = 0;
                }
            }
            arrayList = arrayList6;
            arrayList2 = arrayList7;
            range = range5;
            snake = null;
            if (snake != null) {
                if (snake.diagonalSize() > 0) {
                    int i27 = snake.endY;
                    int i28 = snake.startY;
                    int i29 = i27 - i28;
                    int i30 = snake.endX;
                    int i31 = snake.startX;
                    int i32 = i30 - i31;
                    arrayList5.add(i29 != i32 ? snake.reverse ? new Diagonal(i31, i28, snake.diagonalSize()) : i29 > i32 ? new Diagonal(i31, i28 + 1, snake.diagonalSize()) : new Diagonal(i31 + 1, i28, snake.diagonalSize()) : new Diagonal(i31, i28, i32));
                }
                if (arrayList2.isEmpty()) {
                    range2 = new Range();
                    arrayList4 = arrayList2;
                    range3 = range;
                    i = 1;
                } else {
                    i = 1;
                    arrayList4 = arrayList2;
                    range2 = (Range) arrayList4.remove(arrayList2.size() - 1);
                    range3 = range;
                }
                range2.oldListStart = range3.oldListStart;
                range2.newListStart = range3.newListStart;
                range2.oldListEnd = snake.startX;
                range2.newListEnd = snake.startY;
                arrayList3 = arrayList;
                arrayList3.add(range2);
                range3.oldListEnd = range3.oldListEnd;
                range3.newListEnd = range3.newListEnd;
                range3.oldListStart = snake.endX;
                range3.newListStart = snake.endY;
                arrayList3.add(range3);
            } else {
                arrayList3 = arrayList;
                arrayList4 = arrayList2;
                i = 1;
                arrayList4.add(range);
            }
            i12 = i;
            arrayList6 = arrayList3;
            arrayList7 = arrayList4;
            i10 = 0;
        }
        Collections.sort(arrayList5, DIAGONAL_COMPARATOR);
        return new DiffResult(callback, arrayList5, iArr, iArr2);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public abstract int getNewListSize();

        public abstract int getOldListSize();

        public void getChangePayload(int i, int i2) {
        }
    }
}
