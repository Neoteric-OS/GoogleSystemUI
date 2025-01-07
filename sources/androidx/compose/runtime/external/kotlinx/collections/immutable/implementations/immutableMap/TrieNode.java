package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrieNode {
    public static final TrieNode EMPTY = new TrieNode(0, 0, new Object[0], null);
    public Object[] buffer;
    public int dataMap;
    public int nodeMap;
    public final MutabilityOwnership ownedBy;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ModificationResult {
        public TrieNode node;
        public final int sizeDelta;

        public ModificationResult(TrieNode trieNode, int i) {
            this.node = trieNode;
            this.sizeDelta = i;
        }
    }

    public TrieNode(int i, int i2, Object[] objArr, MutabilityOwnership mutabilityOwnership) {
        this.dataMap = i;
        this.nodeMap = i2;
        this.ownedBy = mutabilityOwnership;
        this.buffer = objArr;
    }

    public static TrieNode makeNode(int i, Object obj, Object obj2, int i2, Object obj3, Object obj4, int i3, MutabilityOwnership mutabilityOwnership) {
        if (i3 > 30) {
            return new TrieNode(0, 0, new Object[]{obj, obj2, obj3, obj4}, mutabilityOwnership);
        }
        int indexSegment = TrieNodeKt.indexSegment(i, i3);
        int indexSegment2 = TrieNodeKt.indexSegment(i2, i3);
        if (indexSegment != indexSegment2) {
            return new TrieNode((1 << indexSegment) | (1 << indexSegment2), 0, indexSegment < indexSegment2 ? new Object[]{obj, obj2, obj3, obj4} : new Object[]{obj3, obj4, obj, obj2}, mutabilityOwnership);
        }
        return new TrieNode(0, 1 << indexSegment, new Object[]{makeNode(i, obj, obj2, i2, obj3, obj4, i3 + 5, mutabilityOwnership)}, mutabilityOwnership);
    }

    public final Object[] bufferMoveEntryToNode(int i, int i2, int i3, Object obj, Object obj2, int i4, MutabilityOwnership mutabilityOwnership) {
        Object obj3 = this.buffer[i];
        TrieNode makeNode = makeNode(obj3 != null ? obj3.hashCode() : 0, obj3, valueAtKeyIndex(i), i3, obj, obj2, i4 + 5, mutabilityOwnership);
        int nodeIndex$runtime_release = nodeIndex$runtime_release(i2);
        int i5 = nodeIndex$runtime_release + 1;
        Object[] objArr = this.buffer;
        Object[] objArr2 = new Object[objArr.length - 1];
        ArraysKt.copyInto$default(0, i, 6, objArr, objArr2);
        ArraysKt.copyInto(i, i + 2, i5, objArr, objArr2);
        objArr2[nodeIndex$runtime_release - 1] = makeNode;
        ArraysKt.copyInto(nodeIndex$runtime_release, i5, objArr.length, objArr, objArr2);
        return objArr2;
    }

    public final int calculateSize() {
        if (this.nodeMap == 0) {
            return this.buffer.length / 2;
        }
        int bitCount = Integer.bitCount(this.dataMap);
        int length = this.buffer.length;
        for (int i = bitCount * 2; i < length; i++) {
            bitCount += nodeAtIndex$runtime_release(i).calculateSize();
        }
        return bitCount;
    }

    public final boolean collisionContainsKey(Object obj) {
        IntProgression step = RangesKt.step(RangesKt.until(0, this.buffer.length));
        int i = step.first;
        int i2 = step.last;
        int i3 = step.step;
        if ((i3 > 0 && i <= i2) || (i3 < 0 && i2 <= i)) {
            while (!Intrinsics.areEqual(obj, this.buffer[i])) {
                if (i != i2) {
                    i += i3;
                }
            }
            return true;
        }
        return false;
    }

    public final boolean containsKey(int i, int i2, Object obj) {
        int indexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(indexSegment)) {
            return Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release(indexSegment)]);
        }
        if (!hasNodeAt(indexSegment)) {
            return false;
        }
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(indexSegment));
        return i2 == 30 ? nodeAtIndex$runtime_release.collisionContainsKey(obj) : nodeAtIndex$runtime_release.containsKey(i, i2 + 5, obj);
    }

    public final boolean elementsIdentityEquals(TrieNode trieNode) {
        if (this == trieNode) {
            return true;
        }
        if (this.nodeMap != trieNode.nodeMap || this.dataMap != trieNode.dataMap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != trieNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public final int entryKeyIndex$runtime_release(int i) {
        return Integer.bitCount(this.dataMap & (i - 1)) * 2;
    }

    public final Object get(int i, int i2, Object obj) {
        int indexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(indexSegment)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment);
            if (Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release])) {
                return valueAtKeyIndex(entryKeyIndex$runtime_release);
            }
            return null;
        }
        if (!hasNodeAt(indexSegment)) {
            return null;
        }
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(indexSegment));
        if (i2 != 30) {
            return nodeAtIndex$runtime_release.get(i, i2 + 5, obj);
        }
        IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length));
        int i3 = step.first;
        int i4 = step.last;
        int i5 = step.step;
        if ((i5 <= 0 || i3 > i4) && (i5 >= 0 || i4 > i3)) {
            return null;
        }
        while (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3])) {
            if (i3 == i4) {
                return null;
            }
            i3 += i5;
        }
        return nodeAtIndex$runtime_release.valueAtKeyIndex(i3);
    }

    public final boolean hasEntryAt$runtime_release(int i) {
        return (this.dataMap & i) != 0;
    }

    public final boolean hasNodeAt(int i) {
        return (this.nodeMap & i) != 0;
    }

    public final TrieNode mutableCollisionRemoveEntryAtIndex(int i, PersistentHashMapBuilder persistentHashMapBuilder) {
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.size - 1);
        persistentHashMapBuilder.operationResult = valueAtKeyIndex(i);
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        if (this.ownedBy != persistentHashMapBuilder.ownership) {
            return new TrieNode(0, 0, TrieNodeKt.access$removeEntryAtIndex(i, objArr), persistentHashMapBuilder.ownership);
        }
        this.buffer = TrieNodeKt.access$removeEntryAtIndex(i, objArr);
        return this;
    }

    public final TrieNode mutablePut(int i, Object obj, Object obj2, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode mutablePut;
        int indexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        boolean hasEntryAt$runtime_release = hasEntryAt$runtime_release(indexSegment);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        if (hasEntryAt$runtime_release) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment);
            if (!Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release])) {
                persistentHashMapBuilder.setSize(persistentHashMapBuilder.size + 1);
                MutabilityOwnership mutabilityOwnership2 = persistentHashMapBuilder.ownership;
                if (mutabilityOwnership != mutabilityOwnership2) {
                    return new TrieNode(this.dataMap ^ indexSegment, this.nodeMap | indexSegment, bufferMoveEntryToNode(entryKeyIndex$runtime_release, indexSegment, i, obj, obj2, i2, mutabilityOwnership2), mutabilityOwnership2);
                }
                this.buffer = bufferMoveEntryToNode(entryKeyIndex$runtime_release, indexSegment, i, obj, obj2, i2, mutabilityOwnership2);
                this.dataMap ^= indexSegment;
                this.nodeMap |= indexSegment;
                return this;
            }
            persistentHashMapBuilder.operationResult = valueAtKeyIndex(entryKeyIndex$runtime_release);
            if (valueAtKeyIndex(entryKeyIndex$runtime_release) == obj2) {
                return this;
            }
            if (mutabilityOwnership == persistentHashMapBuilder.ownership) {
                this.buffer[entryKeyIndex$runtime_release + 1] = obj2;
                return this;
            }
            persistentHashMapBuilder.modCount++;
            Object[] objArr = this.buffer;
            Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
            copyOf[entryKeyIndex$runtime_release + 1] = obj2;
            return new TrieNode(this.dataMap, this.nodeMap, copyOf, persistentHashMapBuilder.ownership);
        }
        if (!hasNodeAt(indexSegment)) {
            persistentHashMapBuilder.setSize(persistentHashMapBuilder.size + 1);
            MutabilityOwnership mutabilityOwnership3 = persistentHashMapBuilder.ownership;
            int entryKeyIndex$runtime_release2 = entryKeyIndex$runtime_release(indexSegment);
            if (mutabilityOwnership != mutabilityOwnership3) {
                return new TrieNode(this.dataMap | indexSegment, this.nodeMap, TrieNodeKt.access$insertEntryAtIndex(this.buffer, entryKeyIndex$runtime_release2, obj, obj2), mutabilityOwnership3);
            }
            this.buffer = TrieNodeKt.access$insertEntryAtIndex(this.buffer, entryKeyIndex$runtime_release2, obj, obj2);
            this.dataMap |= indexSegment;
            return this;
        }
        int nodeIndex$runtime_release = nodeIndex$runtime_release(indexSegment);
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        if (i2 == 30) {
            IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length));
            int i3 = step.first;
            int i4 = step.last;
            int i5 = step.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3])) {
                    if (i3 != i4) {
                        i3 += i5;
                    }
                }
                persistentHashMapBuilder.operationResult = nodeAtIndex$runtime_release.valueAtKeyIndex(i3);
                if (nodeAtIndex$runtime_release.ownedBy == persistentHashMapBuilder.ownership) {
                    nodeAtIndex$runtime_release.buffer[i3 + 1] = obj2;
                    mutablePut = nodeAtIndex$runtime_release;
                } else {
                    persistentHashMapBuilder.modCount++;
                    Object[] objArr2 = nodeAtIndex$runtime_release.buffer;
                    Object[] copyOf2 = Arrays.copyOf(objArr2, objArr2.length);
                    copyOf2[i3 + 1] = obj2;
                    mutablePut = new TrieNode(0, 0, copyOf2, persistentHashMapBuilder.ownership);
                }
            }
            persistentHashMapBuilder.setSize(persistentHashMapBuilder.size + 1);
            mutablePut = new TrieNode(0, 0, TrieNodeKt.access$insertEntryAtIndex(nodeAtIndex$runtime_release.buffer, 0, obj, obj2), persistentHashMapBuilder.ownership);
            break;
        }
        mutablePut = nodeAtIndex$runtime_release.mutablePut(i, obj, obj2, i2 + 5, persistentHashMapBuilder);
        return nodeAtIndex$runtime_release == mutablePut ? this : mutableUpdateNodeAtIndex(nodeIndex$runtime_release, mutablePut, persistentHashMapBuilder.ownership);
    }

    public final TrieNode mutablePutAll(TrieNode trieNode, int i, DeltaCounter deltaCounter, PersistentHashMapBuilder persistentHashMapBuilder) {
        Object[] objArr;
        int i2;
        int i3;
        TrieNode makeNode;
        if (this == trieNode) {
            deltaCounter.count += calculateSize();
            return this;
        }
        int i4 = 0;
        if (i > 30) {
            MutabilityOwnership mutabilityOwnership = persistentHashMapBuilder.ownership;
            int i5 = trieNode.nodeMap;
            Object[] objArr2 = this.buffer;
            Object[] copyOf = Arrays.copyOf(objArr2, objArr2.length + trieNode.buffer.length);
            int length = this.buffer.length;
            IntProgression step = RangesKt.step(RangesKt.until(0, trieNode.buffer.length));
            int i6 = step.first;
            int i7 = step.last;
            int i8 = step.step;
            if ((i8 > 0 && i6 <= i7) || (i8 < 0 && i7 <= i6)) {
                while (true) {
                    if (collisionContainsKey(trieNode.buffer[i6])) {
                        deltaCounter.count++;
                    } else {
                        Object[] objArr3 = trieNode.buffer;
                        copyOf[length] = objArr3[i6];
                        copyOf[length + 1] = objArr3[i6 + 1];
                        length += 2;
                    }
                    if (i6 == i7) {
                        break;
                    }
                    i6 += i8;
                }
            }
            return length == this.buffer.length ? this : length == trieNode.buffer.length ? trieNode : length == copyOf.length ? new TrieNode(0, 0, copyOf, mutabilityOwnership) : new TrieNode(0, 0, Arrays.copyOf(copyOf, length), mutabilityOwnership);
        }
        int i9 = this.nodeMap | trieNode.nodeMap;
        int i10 = this.dataMap;
        int i11 = trieNode.dataMap;
        int i12 = (i10 ^ i11) & (~i9);
        int i13 = i10 & i11;
        int i14 = i12;
        while (i13 != 0) {
            int lowestOneBit = Integer.lowestOneBit(i13);
            if (Intrinsics.areEqual(this.buffer[entryKeyIndex$runtime_release(lowestOneBit)], trieNode.buffer[trieNode.entryKeyIndex$runtime_release(lowestOneBit)])) {
                i14 |= lowestOneBit;
            } else {
                i9 |= lowestOneBit;
            }
            i13 ^= lowestOneBit;
        }
        if ((i9 & i14) != 0) {
            PreconditionsKt.throwIllegalStateException("Check failed.");
        }
        TrieNode trieNode2 = (Intrinsics.areEqual(this.ownedBy, persistentHashMapBuilder.ownership) && this.dataMap == i14 && this.nodeMap == i9) ? this : new TrieNode(i14, i9, new Object[Integer.bitCount(i9) + (Integer.bitCount(i14) * 2)], null);
        int i15 = i9;
        int i16 = 0;
        while (i15 != 0) {
            int lowestOneBit2 = Integer.lowestOneBit(i15);
            Object[] objArr4 = trieNode2.buffer;
            int length2 = (objArr4.length - 1) - i16;
            if (hasNodeAt(lowestOneBit2)) {
                makeNode = nodeAtIndex$runtime_release(nodeIndex$runtime_release(lowestOneBit2));
                if (trieNode.hasNodeAt(lowestOneBit2)) {
                    makeNode = makeNode.mutablePutAll(trieNode.nodeAtIndex$runtime_release(trieNode.nodeIndex$runtime_release(lowestOneBit2)), i + 5, deltaCounter, persistentHashMapBuilder);
                } else if (trieNode.hasEntryAt$runtime_release(lowestOneBit2)) {
                    int entryKeyIndex$runtime_release = trieNode.entryKeyIndex$runtime_release(lowestOneBit2);
                    Object obj = trieNode.buffer[entryKeyIndex$runtime_release];
                    Object valueAtKeyIndex = trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release);
                    int i17 = persistentHashMapBuilder.size;
                    objArr = objArr4;
                    i2 = i14;
                    i3 = lowestOneBit2;
                    makeNode = makeNode.mutablePut(obj != null ? obj.hashCode() : i4, obj, valueAtKeyIndex, i + 5, persistentHashMapBuilder);
                    if (persistentHashMapBuilder.size == i17) {
                        deltaCounter.count++;
                    }
                }
                objArr = objArr4;
                i2 = i14;
                i3 = lowestOneBit2;
            } else {
                objArr = objArr4;
                i2 = i14;
                i3 = lowestOneBit2;
                if (trieNode.hasNodeAt(i3)) {
                    makeNode = trieNode.nodeAtIndex$runtime_release(trieNode.nodeIndex$runtime_release(i3));
                    if (hasEntryAt$runtime_release(i3)) {
                        int entryKeyIndex$runtime_release2 = entryKeyIndex$runtime_release(i3);
                        Object obj2 = this.buffer[entryKeyIndex$runtime_release2];
                        int i18 = i + 5;
                        if (makeNode.containsKey(obj2 != null ? obj2.hashCode() : 0, i18, obj2)) {
                            deltaCounter.count++;
                        } else {
                            makeNode = makeNode.mutablePut(obj2 != null ? obj2.hashCode() : 0, obj2, valueAtKeyIndex(entryKeyIndex$runtime_release2), i18, persistentHashMapBuilder);
                        }
                    }
                } else {
                    int entryKeyIndex$runtime_release3 = entryKeyIndex$runtime_release(i3);
                    Object obj3 = this.buffer[entryKeyIndex$runtime_release3];
                    Object valueAtKeyIndex2 = valueAtKeyIndex(entryKeyIndex$runtime_release3);
                    int entryKeyIndex$runtime_release4 = trieNode.entryKeyIndex$runtime_release(i3);
                    Object obj4 = trieNode.buffer[entryKeyIndex$runtime_release4];
                    makeNode = makeNode(obj3 != null ? obj3.hashCode() : 0, obj3, valueAtKeyIndex2, obj4 != null ? obj4.hashCode() : 0, obj4, trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release4), i + 5, persistentHashMapBuilder.ownership);
                }
            }
            objArr[length2] = makeNode;
            i16++;
            i15 ^= i3;
            i14 = i2;
            i4 = 0;
        }
        int i19 = 0;
        while (i14 != 0) {
            int lowestOneBit3 = Integer.lowestOneBit(i14);
            int i20 = i19 * 2;
            if (trieNode.hasEntryAt$runtime_release(lowestOneBit3)) {
                int entryKeyIndex$runtime_release5 = trieNode.entryKeyIndex$runtime_release(lowestOneBit3);
                Object[] objArr5 = trieNode2.buffer;
                objArr5[i20] = trieNode.buffer[entryKeyIndex$runtime_release5];
                objArr5[i20 + 1] = trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release5);
                if (hasEntryAt$runtime_release(lowestOneBit3)) {
                    deltaCounter.count++;
                }
            } else {
                int entryKeyIndex$runtime_release6 = entryKeyIndex$runtime_release(lowestOneBit3);
                Object[] objArr6 = trieNode2.buffer;
                objArr6[i20] = this.buffer[entryKeyIndex$runtime_release6];
                objArr6[i20 + 1] = valueAtKeyIndex(entryKeyIndex$runtime_release6);
            }
            i19++;
            i14 ^= lowestOneBit3;
        }
        return elementsIdentityEquals(trieNode2) ? this : trieNode.elementsIdentityEquals(trieNode2) ? trieNode : trieNode2;
    }

    public final TrieNode mutableRemove(int i, Object obj, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode mutableRemove;
        int indexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(indexSegment)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment);
            return Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release]) ? mutableRemoveEntryAtIndex(entryKeyIndex$runtime_release, indexSegment, persistentHashMapBuilder) : this;
        }
        if (!hasNodeAt(indexSegment)) {
            return this;
        }
        int nodeIndex$runtime_release = nodeIndex$runtime_release(indexSegment);
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        if (i2 == 30) {
            IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length));
            int i3 = step.first;
            int i4 = step.last;
            int i5 = step.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3])) {
                    if (i3 != i4) {
                        i3 += i5;
                    }
                }
                mutableRemove = nodeAtIndex$runtime_release.mutableCollisionRemoveEntryAtIndex(i3, persistentHashMapBuilder);
            }
            mutableRemove = nodeAtIndex$runtime_release;
            break;
        }
        mutableRemove = nodeAtIndex$runtime_release.mutableRemove(i, obj, i2 + 5, persistentHashMapBuilder);
        return mutableReplaceNode(nodeAtIndex$runtime_release, mutableRemove, nodeIndex$runtime_release, indexSegment, persistentHashMapBuilder.ownership);
    }

    public final TrieNode mutableRemoveEntryAtIndex(int i, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.size - 1);
        persistentHashMapBuilder.operationResult = valueAtKeyIndex(i);
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        if (this.ownedBy != persistentHashMapBuilder.ownership) {
            return new TrieNode(i2 ^ this.dataMap, this.nodeMap, TrieNodeKt.access$removeEntryAtIndex(i, objArr), persistentHashMapBuilder.ownership);
        }
        this.buffer = TrieNodeKt.access$removeEntryAtIndex(i, objArr);
        this.dataMap ^= i2;
        return this;
    }

    public final TrieNode mutableReplaceNode(TrieNode trieNode, TrieNode trieNode2, int i, int i2, MutabilityOwnership mutabilityOwnership) {
        MutabilityOwnership mutabilityOwnership2 = this.ownedBy;
        if (trieNode2 != null) {
            return (mutabilityOwnership2 == mutabilityOwnership || trieNode != trieNode2) ? mutableUpdateNodeAtIndex(i, trieNode2, mutabilityOwnership) : this;
        }
        Object[] objArr = this.buffer;
        if (objArr.length == 1) {
            return null;
        }
        if (mutabilityOwnership2 != mutabilityOwnership) {
            return new TrieNode(this.dataMap, this.nodeMap ^ i2, TrieNodeKt.access$removeNodeAtIndex(i, objArr), mutabilityOwnership);
        }
        this.buffer = TrieNodeKt.access$removeNodeAtIndex(i, objArr);
        this.nodeMap ^= i2;
        return this;
    }

    public final TrieNode mutableUpdateNodeAtIndex(int i, TrieNode trieNode, MutabilityOwnership mutabilityOwnership) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1 && trieNode.buffer.length == 2 && trieNode.nodeMap == 0) {
            trieNode.dataMap = this.nodeMap;
            return trieNode;
        }
        if (this.ownedBy == mutabilityOwnership) {
            objArr[i] = trieNode;
            return this;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        copyOf[i] = trieNode;
        return new TrieNode(this.dataMap, this.nodeMap, copyOf, mutabilityOwnership);
    }

    public final TrieNode nodeAtIndex$runtime_release(int i) {
        return (TrieNode) this.buffer[i];
    }

    public final int nodeIndex$runtime_release(int i) {
        return (this.buffer.length - 1) - Integer.bitCount(this.nodeMap & (i - 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c2 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode.ModificationResult put(int r12, int r13, java.lang.Object r14, java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode.put(int, int, java.lang.Object, java.lang.Object):androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode$ModificationResult");
    }

    public final TrieNode remove(int i, int i2, Object obj) {
        TrieNode remove;
        int indexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(indexSegment)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment);
            if (!Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release])) {
                return this;
            }
            Object[] objArr = this.buffer;
            if (objArr.length == 2) {
                return null;
            }
            return new TrieNode(this.dataMap ^ indexSegment, this.nodeMap, TrieNodeKt.access$removeEntryAtIndex(entryKeyIndex$runtime_release, objArr), null);
        }
        if (!hasNodeAt(indexSegment)) {
            return this;
        }
        int nodeIndex$runtime_release = nodeIndex$runtime_release(indexSegment);
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        if (i2 == 30) {
            IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length));
            int i3 = step.first;
            int i4 = step.last;
            int i5 = step.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3])) {
                    if (i3 != i4) {
                        i3 += i5;
                    }
                }
                Object[] objArr2 = nodeAtIndex$runtime_release.buffer;
                remove = objArr2.length == 2 ? null : new TrieNode(0, 0, TrieNodeKt.access$removeEntryAtIndex(i3, objArr2), null);
            }
            remove = nodeAtIndex$runtime_release;
            break;
        }
        remove = nodeAtIndex$runtime_release.remove(i, i2 + 5, obj);
        if (remove != null) {
            return nodeAtIndex$runtime_release != remove ? updateNodeAtIndex(nodeIndex$runtime_release, indexSegment, remove) : this;
        }
        Object[] objArr3 = this.buffer;
        if (objArr3.length == 1) {
            return null;
        }
        return new TrieNode(this.dataMap, this.nodeMap ^ indexSegment, TrieNodeKt.access$removeNodeAtIndex(nodeIndex$runtime_release, objArr3), null);
    }

    public final TrieNode updateNodeAtIndex(int i, int i2, TrieNode trieNode) {
        Object[] objArr = trieNode.buffer;
        if (objArr.length != 2 || trieNode.nodeMap != 0) {
            Object[] objArr2 = this.buffer;
            Object[] copyOf = Arrays.copyOf(objArr2, objArr2.length);
            copyOf[i] = trieNode;
            return new TrieNode(this.dataMap, this.nodeMap, copyOf, null);
        }
        if (this.buffer.length == 1) {
            trieNode.dataMap = this.nodeMap;
            return trieNode;
        }
        int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i2);
        Object[] objArr3 = this.buffer;
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        Object[] copyOf2 = Arrays.copyOf(objArr3, objArr3.length + 1);
        ArraysKt.copyInto(i + 2, i + 1, objArr3.length, copyOf2, copyOf2);
        ArraysKt.copyInto(entryKeyIndex$runtime_release + 2, entryKeyIndex$runtime_release, i, copyOf2, copyOf2);
        copyOf2[entryKeyIndex$runtime_release] = obj;
        copyOf2[entryKeyIndex$runtime_release + 1] = obj2;
        return new TrieNode(this.dataMap ^ i2, this.nodeMap ^ i2, copyOf2, null);
    }

    public final Object valueAtKeyIndex(int i) {
        return this.buffer[i + 1];
    }

    public final TrieNode mutableRemove(int i, Object obj, Object obj2, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode mutableRemove;
        int indexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(indexSegment)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment);
            return (Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release]) && Intrinsics.areEqual(obj2, valueAtKeyIndex(entryKeyIndex$runtime_release))) ? mutableRemoveEntryAtIndex(entryKeyIndex$runtime_release, indexSegment, persistentHashMapBuilder) : this;
        }
        if (!hasNodeAt(indexSegment)) {
            return this;
        }
        int nodeIndex$runtime_release = nodeIndex$runtime_release(indexSegment);
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        if (i2 == 30) {
            IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length));
            int i3 = step.first;
            int i4 = step.last;
            int i5 = step.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (true) {
                    if (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3]) || !Intrinsics.areEqual(obj2, nodeAtIndex$runtime_release.valueAtKeyIndex(i3))) {
                        if (i3 == i4) {
                            break;
                        }
                        i3 += i5;
                    } else {
                        mutableRemove = nodeAtIndex$runtime_release.mutableCollisionRemoveEntryAtIndex(i3, persistentHashMapBuilder);
                        break;
                    }
                }
            }
            mutableRemove = nodeAtIndex$runtime_release;
        } else {
            mutableRemove = nodeAtIndex$runtime_release.mutableRemove(i, obj, obj2, i2 + 5, persistentHashMapBuilder);
        }
        return mutableReplaceNode(nodeAtIndex$runtime_release, mutableRemove, nodeIndex$runtime_release, indexSegment, persistentHashMapBuilder.ownership);
    }
}
