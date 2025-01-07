package com.android.systemui.shared.hardware;

import android.hardware.input.InputManager;
import android.view.InputDevice;
import kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.FilteringSequence;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class InputManagerKt {
    public static final FilteringSequence getInputDeviceSequence(final InputManager inputManager) {
        int[] inputDeviceIds = inputManager.getInputDeviceIds();
        return SequencesKt.mapNotNull(inputDeviceIds.length == 0 ? EmptySequence.INSTANCE : new ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1(1, inputDeviceIds), new Function1() { // from class: com.android.systemui.shared.hardware.InputManagerKt$getInputDeviceSequence$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return inputManager.getInputDevice(((Number) obj).intValue());
            }
        });
    }

    public static final boolean hasInputDevice(InputManager inputManager, Function1 function1) {
        FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(getInputDeviceSequence(inputManager));
        while (filteringSequence$iterator$1.hasNext()) {
            if (((Boolean) function1.invoke((InputDevice) filteringSequence$iterator$1.next())).booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
