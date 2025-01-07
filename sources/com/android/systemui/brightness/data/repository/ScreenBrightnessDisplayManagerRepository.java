package com.android.systemui.brightness.data.repository;

import android.hardware.display.DisplayManager;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.brightness.shared.model.LinearBrightness;
import com.android.systemui.brightness.shared.model.LinearBrightnessKt;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScreenBrightnessDisplayManagerRepository {
    public final BufferedChannel apiQueue = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6);
    public final CoroutineContext backgroundContext;
    public final ReadonlyStateFlow brightnessInfo;
    public final int displayId;
    public final DisplayManager displayManager;
    public final ReadonlyStateFlow linearBrightness;
    public final LogBuffer logBuffer;
    public final ReadonlyStateFlow maxLinearBrightness;
    public final ReadonlyStateFlow minLinearBrightness;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        Object L$0;
        Object L$1;
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ScreenBrightnessDisplayManagerRepository.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x00a9  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00c7  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0045 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00ac  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0093  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0080  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x007e  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0061 -> B:6:0x0064). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r9.label
                r2 = 0
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L2a
                if (r1 == r4) goto L21
                if (r1 != r3) goto L19
                java.lang.Object r1 = r9.L$1
                com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$SetBrightnessMethod r1 = (com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.SetBrightnessMethod) r1
                java.lang.Object r5 = r9.L$0
                kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r5 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r5
                kotlin.ResultKt.throwOnFailure(r10)
                goto L64
            L19:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L21:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r1
                kotlin.ResultKt.throwOnFailure(r10)
            L28:
                r5 = r1
                goto L46
            L2a:
                kotlin.ResultKt.throwOnFailure(r10)
                com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository r10 = com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.this
                kotlinx.coroutines.channels.BufferedChannel r10 = r10.apiQueue
                r10.getClass()
                kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = new kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator
                r1.<init>()
            L39:
                r9.L$0 = r1
                r9.L$1 = r2
                r9.label = r4
                java.lang.Object r10 = r1.hasNext(r9)
                if (r10 != r0) goto L28
                return r0
            L46:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 == 0) goto Lc7
                java.lang.Object r10 = r5.next()
                r1 = r10
                com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$SetBrightnessMethod r1 = (com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.SetBrightnessMethod) r1
                com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository r10 = com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.this
                r9.L$0 = r5
                r9.L$1 = r1
                r9.label = r3
                java.lang.Object r10 = r10.getMinMaxLinearBrightness(r9)
                if (r10 != r0) goto L64
                return r0
            L64:
                kotlin.Pair r10 = (kotlin.Pair) r10
                float r6 = r1.mo785getValuefoLk1o()
                java.lang.Object r7 = r10.getFirst()
                com.android.systemui.brightness.shared.model.LinearBrightness r7 = (com.android.systemui.brightness.shared.model.LinearBrightness) r7
                float r7 = r7.floatValue
                java.lang.Object r10 = r10.getSecond()
                com.android.systemui.brightness.shared.model.LinearBrightness r10 = (com.android.systemui.brightness.shared.model.LinearBrightness) r10
                float r10 = r10.floatValue
                int r8 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
                if (r8 >= 0) goto L80
                r6 = r7
                goto L85
            L80:
                int r7 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
                if (r7 <= 0) goto L85
                r6 = r10
            L85:
                boolean r10 = r1 instanceof com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.SetBrightnessMethod.Temporary
                if (r10 == 0) goto L93
                com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository r10 = com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.this
                android.hardware.display.DisplayManager r7 = r10.displayManager
                int r10 = r10.displayId
                r7.setTemporaryBrightness(r10, r6)
                goto La0
            L93:
                boolean r10 = r1 instanceof com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.SetBrightnessMethod.Permanent
                if (r10 == 0) goto La0
                com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository r10 = com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.this
                android.hardware.display.DisplayManager r7 = r10.displayManager
                int r10 = r10.displayId
                r7.setBrightness(r10, r6)
            La0:
                com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository r10 = com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.this
                boolean r1 = r1 instanceof com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.SetBrightnessMethod.Permanent
                r10.getClass()
                if (r1 == 0) goto Lac
                com.android.systemui.log.core.LogLevel r1 = com.android.systemui.log.core.LogLevel.DEBUG
                goto Lae
            Lac:
                com.android.systemui.log.core.LogLevel r1 = com.android.systemui.log.core.LogLevel.VERBOSE
            Lae:
                com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2 r7 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2
                    static {
                        /*
                            com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2 r0 = new com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2) com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2.INSTANCE com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2.<clinit>():void");
                    }

                    {
                        /*
                            r1 = this;
                            r0 = 1
                            r1.<init>(r0)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2.<init>():void");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final java.lang.Object invoke(java.lang.Object r1) {
                        /*
                            r0 = this;
                            com.android.systemui.log.core.LogMessage r1 = (com.android.systemui.log.core.LogMessage) r1
                            java.lang.String r0 = r1.getStr1()
                            java.lang.String r1 = "Change requested: "
                            java.lang.String r0 = androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0.m(r1, r0)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$logBrightnessChange$2.invoke(java.lang.Object):java.lang.Object");
                    }
                }
                java.lang.String r8 = "BrightnessChange"
                com.android.systemui.log.LogBuffer r10 = r10.logBuffer
                com.android.systemui.log.core.LogMessage r1 = r10.obtain(r8, r1, r7, r2)
                java.lang.String r6 = com.android.systemui.brightness.shared.model.LinearBrightnessKt.formatBrightness(r6)
                r7 = r1
                com.android.systemui.log.LogMessageImpl r7 = (com.android.systemui.log.LogMessageImpl) r7
                r7.str1 = r6
                r10.commit(r1)
                r1 = r5
                goto L39
            Lc7:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SetBrightnessMethod {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Permanent implements SetBrightnessMethod {
            public final float value;

            public /* synthetic */ Permanent(float f) {
                this.value = f;
            }

            public final boolean equals(Object obj) {
                if (obj instanceof Permanent) {
                    return Float.compare(this.value, ((Permanent) obj).value) == 0;
                }
                return false;
            }

            @Override // com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.SetBrightnessMethod
            /* renamed from: getValue--foLk1o */
            public final float mo785getValuefoLk1o() {
                return this.value;
            }

            public final int hashCode() {
                return Float.hashCode(this.value);
            }

            public final String toString() {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Permanent(value=", LinearBrightness.m791toStringimpl(this.value), ")");
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Temporary implements SetBrightnessMethod {
            public final float value;

            public /* synthetic */ Temporary(float f) {
                this.value = f;
            }

            public final boolean equals(Object obj) {
                if (obj instanceof Temporary) {
                    return Float.compare(this.value, ((Temporary) obj).value) == 0;
                }
                return false;
            }

            @Override // com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.SetBrightnessMethod
            /* renamed from: getValue--foLk1o */
            public final float mo785getValuefoLk1o() {
                return this.value;
            }

            public final int hashCode() {
                return Float.hashCode(this.value);
            }

            public final String toString() {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Temporary(value=", LinearBrightness.m791toStringimpl(this.value), ")");
            }
        }

        /* renamed from: getValue--foLk1o, reason: not valid java name */
        float mo785getValuefoLk1o();
    }

    public ScreenBrightnessDisplayManagerRepository(int i, DisplayManager displayManager, LogBuffer logBuffer, TableLogBuffer tableLogBuffer, CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        this.displayId = i;
        this.displayManager = displayManager;
        this.logBuffer = logBuffer;
        this.backgroundContext = coroutineContext;
        BuildersKt.launch$default(coroutineScope, coroutineContext, null, new AnonymousClass1(null), 2);
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new ScreenBrightnessDisplayManagerRepository$brightnessInfo$2(2, null), FlowConflatedKt.conflatedCallbackFlow(new ScreenBrightnessDisplayManagerRepository$brightnessInfo$1(this, null)));
        ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowKt.flowOn(new Flow() { // from class: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ScreenBrightnessDisplayManagerRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, ScreenBrightnessDisplayManagerRepository screenBrightnessDisplayManagerRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = screenBrightnessDisplayManagerRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:19:0x0064 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1$2$1 r0 = (com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1$2$1 r0 = new com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3b
                        if (r2 == r5) goto L33
                        if (r2 != r4) goto L2b
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L65
                    L2b:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L33:
                        java.lang.Object r6 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5a
                    L3b:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlin.Unit r7 = (kotlin.Unit) r7
                        kotlinx.coroutines.flow.FlowCollector r7 = r6.$this_unsafeFlow
                        r0.L$0 = r7
                        r0.label = r5
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository r6 = r6.this$0
                        r6.getClass()
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$brightnessInfoValue$2 r8 = new com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$brightnessInfoValue$2
                        r8.<init>(r6, r3)
                        kotlin.coroutines.CoroutineContext r6 = r6.backgroundContext
                        java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r6, r8, r0)
                        if (r8 != r1) goto L59
                        return r1
                    L59:
                        r6 = r7
                    L5a:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r6 = r6.emit(r8, r0)
                        if (r6 != r1) goto L65
                        return r1
                    L65:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineContext), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(1), null);
        this.brightnessInfo = stateIn;
        final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(stateIn);
        final int i2 = 0;
        this.minLinearBrightness = FlowKt.stateIn(LinearBrightnessKt.m792logDiffForTableCVGC8U(new Flow() { // from class: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1 r0 = (com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1 r0 = new com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L46
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.hardware.display.BrightnessInfo r5 = (android.hardware.display.BrightnessInfo) r5
                        float r5 = r5.brightnessMinimum
                        com.android.systemui.brightness.shared.model.LinearBrightness r6 = new com.android.systemui.brightness.shared.model.LinearBrightness
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L46
                        return r1
                    L46:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.collect(new ScreenBrightnessDisplayManagerRepository$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.collect(new ScreenBrightnessDisplayManagerRepository$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, tableLogBuffer, "min"), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new LinearBrightness(0.0f));
        final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$12 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(stateIn);
        final int i3 = 1;
        this.maxLinearBrightness = FlowKt.stateIn(LinearBrightnessKt.m792logDiffForTableCVGC8U(new Flow() { // from class: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1 r0 = (com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1 r0 = new com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L46
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.hardware.display.BrightnessInfo r5 = (android.hardware.display.BrightnessInfo) r5
                        float r5 = r5.brightnessMinimum
                        com.android.systemui.brightness.shared.model.LinearBrightness r6 = new com.android.systemui.brightness.shared.model.LinearBrightness
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L46
                        return r1
                    L46:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$12.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$12.collect(new ScreenBrightnessDisplayManagerRepository$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$12.collect(new ScreenBrightnessDisplayManagerRepository$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, tableLogBuffer, "max"), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new LinearBrightness(1.0f));
        final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$13 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(stateIn);
        final int i4 = 2;
        this.linearBrightness = FlowKt.stateIn(LinearBrightnessKt.m792logDiffForTableCVGC8U(new Flow() { // from class: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1 r0 = (com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1 r0 = new com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L46
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.hardware.display.BrightnessInfo r5 = (android.hardware.display.BrightnessInfo) r5
                        float r5 = r5.brightnessMinimum
                        com.android.systemui.brightness.shared.model.LinearBrightness r6 = new com.android.systemui.brightness.shared.model.LinearBrightness
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L46
                        return r1
                    L46:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$13.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$13.collect(new ScreenBrightnessDisplayManagerRepository$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$13.collect(new ScreenBrightnessDisplayManagerRepository$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, tableLogBuffer, "brightness"), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new LinearBrightness(0.0f));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getMinMaxLinearBrightness(kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$getMinMaxLinearBrightness$1
            if (r0 == 0) goto L13
            r0 = r5
            com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$getMinMaxLinearBrightness$1 r0 = (com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$getMinMaxLinearBrightness$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$getMinMaxLinearBrightness$1 r0 = new com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$getMinMaxLinearBrightness$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r5)
            goto L51
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r4.brightnessInfo
            kotlinx.coroutines.flow.MutableStateFlow r5 = r5.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r5 = (kotlinx.coroutines.flow.StateFlowImpl) r5
            java.lang.Object r5 = r5.getValue()
            android.hardware.display.BrightnessInfo r5 = (android.hardware.display.BrightnessInfo) r5
            if (r5 != 0) goto L53
            r0.label = r3
            com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$brightnessInfoValue$2 r5 = new com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository$brightnessInfoValue$2
            r2 = 0
            r5.<init>(r4, r2)
            kotlin.coroutines.CoroutineContext r4 = r4.backgroundContext
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r0)
            if (r5 != r1) goto L51
            return r1
        L51:
            android.hardware.display.BrightnessInfo r5 = (android.hardware.display.BrightnessInfo) r5
        L53:
            if (r5 == 0) goto L58
            float r4 = r5.brightnessMinimum
            goto L59
        L58:
            r4 = 0
        L59:
            if (r5 == 0) goto L5e
            float r5 = r5.brightnessMaximum
            goto L60
        L5e:
            r5 = 1065353216(0x3f800000, float:1.0)
        L60:
            com.android.systemui.brightness.shared.model.LinearBrightness r0 = new com.android.systemui.brightness.shared.model.LinearBrightness
            r0.<init>(r4)
            com.android.systemui.brightness.shared.model.LinearBrightness r4 = new com.android.systemui.brightness.shared.model.LinearBrightness
            r4.<init>(r5)
            kotlin.Pair r5 = new kotlin.Pair
            r5.<init>(r0, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.data.repository.ScreenBrightnessDisplayManagerRepository.getMinMaxLinearBrightness(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
