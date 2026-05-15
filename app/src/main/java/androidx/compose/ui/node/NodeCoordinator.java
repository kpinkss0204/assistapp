package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.MutableRectKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: NodeCoordinator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b&\b \u0018\u0000 ®\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0004®\u0002¯\u0002B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J$\u0010\u0095\u0001\u001a\u00020\u001e2\u0007\u0010\u0096\u0001\u001a\u00020\u00002\u0007\u0010\u0097\u0001\u001a\u00020\u000b2\u0007\u0010\u0098\u0001\u001a\u00020%H\u0002J2\u0010\u0095\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00002\b\u0010\u009a\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u009b\u0001\u001a\u00020%H\u0002ø\u0001\u0000¢\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J\u001d\u0010\u009e\u0001\u001a\u00020`2\u0006\u0010_\u001a\u00020`H\u0004ø\u0001\u0000¢\u0006\u0006\b\u009f\u0001\u0010 \u0001J'\u0010¡\u0001\u001a\u00020\u00172\b\u0010¢\u0001\u001a\u00030\u0099\u00012\u0006\u0010_\u001a\u00020`H\u0004ø\u0001\u0000¢\u0006\u0006\b£\u0001\u0010¤\u0001J\u001b\u0010¥\u0001\u001a\u00020\u001e2\u0007\u0010¦\u0001\u001a\u00020\u001c2\t\u0010§\u0001\u001a\u0004\u0018\u00010\u001dJ\u001c\u0010¨\u0001\u001a\u00020\u001e2\u0007\u0010¦\u0001\u001a\u00020\u001c2\b\u0010©\u0001\u001a\u00030ª\u0001H\u0004J\u001d\u0010«\u0001\u001a\u00020\u001e2\u0007\u0010¦\u0001\u001a\u00020\u001c2\t\u0010§\u0001\u001a\u0004\u0018\u00010\u001dH\u0002J\t\u0010¬\u0001\u001a\u00020\u001eH&J\u0018\u0010\u00ad\u0001\u001a\u00020\u00002\u0007\u0010®\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0003\b¯\u0001J*\u0010°\u0001\u001a\u00030\u0099\u00012\u0007\u0010q\u001a\u00030\u0099\u00012\t\b\u0002\u0010\u009b\u0001\u001a\u00020%H\u0016ø\u0001\u0000¢\u0006\u0006\b±\u0001\u0010²\u0001J\u001b\u0010³\u0001\u001a\u00020\u001e2\u0007\u0010´\u0001\u001a\u00020\u000b2\u0007\u0010\u0098\u0001\u001a\u00020%H\u0002J#\u0010µ\u0001\u001a\u00020%2\f\u0010¶\u0001\u001a\u0007\u0012\u0002\b\u00030·\u0001H\u0002ø\u0001\u0000¢\u0006\u0006\b¸\u0001\u0010¹\u0001J$\u0010º\u0001\u001a\u0005\u0018\u00010\u0086\u00012\f\u0010¶\u0001\u001a\u0007\u0012\u0002\b\u00030·\u0001ø\u0001\u0000¢\u0006\u0006\b»\u0001\u0010¼\u0001J\u0015\u0010½\u0001\u001a\u0005\u0018\u00010\u0086\u00012\u0007\u0010¾\u0001\u001a\u00020%H\u0002JC\u0010¿\u0001\u001a\u00020\u001e2\b\u0010À\u0001\u001a\u00030Á\u00012\b\u0010¢\u0001\u001a\u00030\u0099\u00012\b\u0010Â\u0001\u001a\u00030Ã\u00012\u0007\u0010Ä\u0001\u001a\u00020%2\u0007\u0010Å\u0001\u001a\u00020%ø\u0001\u0000¢\u0006\u0006\bÆ\u0001\u0010Ç\u0001JE\u0010È\u0001\u001a\u00020\u001e2\b\u0010À\u0001\u001a\u00030Á\u00012\b\u0010¢\u0001\u001a\u00030\u0099\u00012\b\u0010Â\u0001\u001a\u00030Ã\u00012\u0007\u0010Ä\u0001\u001a\u00020%2\u0007\u0010Å\u0001\u001a\u00020%H\u0016ø\u0001\u0000¢\u0006\u0006\bÉ\u0001\u0010Ç\u0001J\t\u0010Ê\u0001\u001a\u00020\u001eH\u0016J\u001f\u0010Ë\u0001\u001a\u00020%2\b\u0010¢\u0001\u001a\u00030\u0099\u0001H\u0004ø\u0001\u0000¢\u0006\u0006\bÌ\u0001\u0010Í\u0001J\u0007\u0010Î\u0001\u001a\u00020%J\u001c\u0010Ï\u0001\u001a\u00030Ð\u00012\u0007\u0010Ñ\u0001\u001a\u00020\u00032\u0007\u0010\u0098\u0001\u001a\u00020%H\u0016J)\u0010Ò\u0001\u001a\u00030\u0099\u00012\u0007\u0010Ñ\u0001\u001a\u00020\u00032\b\u0010Ó\u0001\u001a\u00030\u0099\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\bÔ\u0001\u0010Õ\u0001J2\u0010Ò\u0001\u001a\u00030\u0099\u00012\u0007\u0010Ñ\u0001\u001a\u00020\u00032\b\u0010Ó\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u009b\u0001\u001a\u00020%H\u0016ø\u0001\u0000¢\u0006\u0006\bÖ\u0001\u0010×\u0001J \u0010Ø\u0001\u001a\u00030\u0099\u00012\b\u0010Ù\u0001\u001a\u00030\u0099\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\bÚ\u0001\u0010 \u0001J \u0010Û\u0001\u001a\u00030\u0099\u00012\b\u0010Ù\u0001\u001a\u00030\u0099\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\bÜ\u0001\u0010 \u0001J \u0010Ý\u0001\u001a\u00030\u0099\u00012\b\u0010Ù\u0001\u001a\u00030\u0099\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\bÞ\u0001\u0010 \u0001J \u0010ß\u0001\u001a\u00030\u0099\u00012\b\u0010¢\u0001\u001a\u00030\u0099\u0001H\u0002ø\u0001\u0000¢\u0006\u0006\bà\u0001\u0010 \u0001J\u000f\u0010á\u0001\u001a\u00020\u001eH\u0000¢\u0006\u0003\bâ\u0001J\t\u0010ã\u0001\u001a\u00020\u001eH\u0016J\u0007\u0010ä\u0001\u001a\u00020\u001eJ\u001b\u0010å\u0001\u001a\u00020\u001e2\u0007\u0010æ\u0001\u001a\u00020e2\u0007\u0010ç\u0001\u001a\u00020eH\u0014J\u0007\u0010è\u0001\u001a\u00020\u001eJ\u0007\u0010é\u0001\u001a\u00020\u001eJ\u0007\u0010ê\u0001\u001a\u00020\u001eJ\u001d\u0010ë\u0001\u001a\u00020\u001e2\u0007\u0010¦\u0001\u001a\u00020\u001c2\t\u0010§\u0001\u001a\u0004\u0018\u00010\u001dH\u0016J2\u0010ì\u0001\u001a\u00030í\u00012\u0007\u0010î\u0001\u001a\u00020;2\u0010\b\u0004\u0010ï\u0001\u001a\t\u0012\u0005\u0012\u00030í\u000102H\u0084\bø\u0001\u0000¢\u0006\u0006\bð\u0001\u0010ñ\u0001JA\u0010ò\u0001\u001a\u00020\u001e2\u0006\u0010q\u001a\u00020p2\u0007\u0010\u0091\u0001\u001a\u00020\u00172\u0019\u0010E\u001a\u0015\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020\u001e\u0018\u00010B¢\u0006\u0002\bDH\u0014ø\u0001\u0000¢\u0006\u0006\bó\u0001\u0010ô\u0001J.\u0010ò\u0001\u001a\u00020\u001e2\u0006\u0010q\u001a\u00020p2\u0007\u0010\u0091\u0001\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u001dH\u0014ø\u0001\u0000¢\u0006\u0006\bó\u0001\u0010õ\u0001JK\u0010ö\u0001\u001a\u00020\u001e2\u0006\u0010q\u001a\u00020p2\u0007\u0010\u0091\u0001\u001a\u00020\u00172\u0019\u0010E\u001a\u0015\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020\u001e\u0018\u00010B¢\u0006\u0002\bD2\b\u0010!\u001a\u0004\u0018\u00010\u001dH\u0002ø\u0001\u0000¢\u0006\u0006\b÷\u0001\u0010ø\u0001JI\u0010ù\u0001\u001a\u00020\u001e2\u0006\u0010q\u001a\u00020p2\u0007\u0010\u0091\u0001\u001a\u00020\u00172\u0019\u0010E\u001a\u0015\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020\u001e\u0018\u00010B¢\u0006\u0002\bD2\b\u0010?\u001a\u0004\u0018\u00010\u001dø\u0001\u0000¢\u0006\u0006\bú\u0001\u0010ø\u0001J,\u0010û\u0001\u001a\u00020\u001e2\u0007\u0010´\u0001\u001a\u00020\u000b2\u0007\u0010\u0098\u0001\u001a\u00020%2\t\b\u0002\u0010ü\u0001\u001a\u00020%H\u0000¢\u0006\u0003\bý\u0001J\u0007\u0010þ\u0001\u001a\u00020\u001eJ\u000f\u0010ÿ\u0001\u001a\u00020\u001eH\u0010¢\u0006\u0003\b\u0080\u0002J \u0010\u0081\u0002\u001a\u00030\u0099\u00012\b\u0010\u0082\u0002\u001a\u00030\u0099\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\b\u0083\u0002\u0010 \u0001J\u0007\u0010\u0084\u0002\u001a\u00020%J*\u0010\u0085\u0002\u001a\u00030\u0099\u00012\u0007\u0010q\u001a\u00030\u0099\u00012\t\b\u0002\u0010\u009b\u0001\u001a\u00020%H\u0016ø\u0001\u0000¢\u0006\u0006\b\u0086\u0002\u0010²\u0001J\b\u0010\u0087\u0002\u001a\u00030Ð\u0001J(\u0010\u0088\u0002\u001a\u00020\u001e2\u0007\u0010Ñ\u0001\u001a\u00020\u00032\b\u0010\u0089\u0002\u001a\u00030\u008a\u0002H\u0016ø\u0001\u0000¢\u0006\u0006\b\u008b\u0002\u0010\u008c\u0002J(\u0010\u008d\u0002\u001a\u00020\u001e2\u0007\u0010\u0096\u0001\u001a\u00020\u00002\b\u0010\u0089\u0002\u001a\u00030\u008a\u0002H\u0002ø\u0001\u0000¢\u0006\u0006\b\u008e\u0002\u0010\u008f\u0002J(\u0010\u0090\u0002\u001a\u00020\u001e2\u0007\u0010\u0096\u0001\u001a\u00020\u00002\b\u0010\u0089\u0002\u001a\u00030\u008a\u0002H\u0002ø\u0001\u0000¢\u0006\u0006\b\u0091\u0002\u0010\u008f\u0002J\u001f\u0010\u0092\u0002\u001a\u00020\u001e2\b\u0010\u0089\u0002\u001a\u00030\u008a\u0002H\u0016ø\u0001\u0000¢\u0006\u0006\b\u0093\u0002\u0010\u0094\u0002J-\u0010\u0095\u0002\u001a\u00020\u001e2\u0019\u0010E\u001a\u0015\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020\u001e\u0018\u00010B¢\u0006\u0002\bD2\t\b\u0002\u0010\u0096\u0002\u001a\u00020%J\u0014\u0010\u0097\u0002\u001a\u00020\u001e2\t\b\u0002\u0010\u0098\u0002\u001a\u00020%H\u0002JF\u0010\u0099\u0002\u001a\u00020\u001e\"\u0007\b\u0000\u0010\u009a\u0002\u0018\u00012\u000f\u0010¶\u0001\u001a\n\u0012\u0005\u0012\u0003H\u009a\u00020·\u00012\u0014\u0010ï\u0001\u001a\u000f\u0012\u0005\u0012\u0003H\u009a\u0002\u0012\u0004\u0012\u00020\u001e0BH\u0086\bø\u0001\u0000¢\u0006\u0006\b\u009b\u0002\u0010\u009c\u0002J2\u0010\u0099\u0002\u001a\u00020\u001e2\u0007\u0010\u009d\u0002\u001a\u00020e2\u0007\u0010¾\u0001\u001a\u00020%2\u0014\u0010ï\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0086\u0001\u0012\u0004\u0012\u00020\u001e0BH\u0086\bJ \u0010\u009e\u0002\u001a\u00030\u0099\u00012\b\u0010\u009f\u0002\u001a\u00030\u0099\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\b \u0002\u0010 \u0001J(\u0010¡\u0002\u001a\u00020\u001e2\u0007\u0010¦\u0001\u001a\u00020\u001c2\u0013\u0010ï\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001e0BH\u0084\bJ\u001f\u0010¢\u0002\u001a\u00020%2\b\u0010¢\u0001\u001a\u00030\u0099\u0001H\u0004ø\u0001\u0000¢\u0006\u0006\b£\u0002\u0010Í\u0001JL\u0010¤\u0002\u001a\u00020\u001e*\u0005\u0018\u00010\u0086\u00012\b\u0010À\u0001\u001a\u00030Á\u00012\b\u0010¢\u0001\u001a\u00030\u0099\u00012\b\u0010Â\u0001\u001a\u00030Ã\u00012\u0007\u0010Ä\u0001\u001a\u00020%2\u0007\u0010Å\u0001\u001a\u00020%H\u0002ø\u0001\u0000¢\u0006\u0006\b¥\u0002\u0010¦\u0002JU\u0010§\u0002\u001a\u00020\u001e*\u0005\u0018\u00010\u0086\u00012\b\u0010À\u0001\u001a\u00030Á\u00012\b\u0010¢\u0001\u001a\u00030\u0099\u00012\b\u0010Â\u0001\u001a\u00030Ã\u00012\u0007\u0010Ä\u0001\u001a\u00020%2\u0007\u0010Å\u0001\u001a\u00020%2\u0007\u0010¨\u0002\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0006\b©\u0002\u0010ª\u0002JU\u0010«\u0002\u001a\u00020\u001e*\u0005\u0018\u00010\u0086\u00012\b\u0010À\u0001\u001a\u00030Á\u00012\b\u0010¢\u0001\u001a\u00030\u0099\u00012\b\u0010Â\u0001\u001a\u00030Ã\u00012\u0007\u0010Ä\u0001\u001a\u00020%2\u0007\u0010Å\u0001\u001a\u00020%2\u0007\u0010¨\u0002\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0006\b¬\u0002\u0010ª\u0002J\r\u0010\u00ad\u0002\u001a\u00020\u0000*\u00020\u0003H\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R(\u0010\u001a\u001a\u0016\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u001d\u0012\u0004\u0012\u00020\u001e0\u001bX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0019R\u001a\u0010$\u001a\u00020%X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020%X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010'\"\u0004\b,\u0010)R\u0014\u0010-\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010'R\u0014\u0010/\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010'R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020\u001e02X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00103\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010'R\u000e\u00104\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010'R\u000e\u00106\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00108\u001a\u00020%2\u0006\u00107\u001a\u00020%@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b9\u0010'R\u001a\u0010:\u001a\u00020;8@X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b<\u0010=R\"\u0010?\u001a\u0004\u0018\u00010>2\b\u00107\u001a\u0004\u0018\u00010>@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b@\u0010ARD\u0010E\u001a\u0015\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020\u001e\u0018\u00010B¢\u0006\u0002\bD2\u0019\u00107\u001a\u0015\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020\u001e\u0018\u00010B¢\u0006\u0002\bD@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u000e\u0010H\u001a\u00020IX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010N\u001a\u00020K8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR&\u0010T\u001a\u0004\u0018\u00010S2\b\u00107\u001a\u0004\u0018\u00010S@dX¦\u000e¢\u0006\f\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR$\u0010Z\u001a\u00020\t2\u0006\u0010Y\u001a\u00020\t8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u0017\u0010_\u001a\u00020`8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\ba\u0010=R\u001c\u0010b\u001a\u0010\u0012\u0004\u0012\u00020d\u0012\u0004\u0012\u00020e\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010f\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bg\u0010\u0012R\u0013\u0010h\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\bi\u0010\u0015R\u0016\u0010j\u001a\u0004\u0018\u00010k8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bl\u0010mR\u0013\u0010n\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\bo\u0010\u0015R,\u0010q\u001a\u00020p2\u0006\u00107\u001a\u00020p@TX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010u\u001a\u0004\br\u0010=\"\u0004\bs\u0010tR\u001a\u0010v\u001a\b\u0012\u0004\u0012\u00020d0w8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bx\u0010yR\u0014\u0010z\u001a\u00020\u000b8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b{\u0010|R\u000e\u0010}\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010~\u001a\u00020\u007f8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u0080\u0001\u0010=R\u0018\u0010\u0081\u0001\u001a\u00030\u0082\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0016\u0010\u0085\u0001\u001a\u00030\u0086\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R!\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0000X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001R!\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0000X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008f\u0001\u0010\u008b\u0001\"\u0006\b\u0090\u0001\u0010\u008d\u0001R(\u0010\u0091\u0001\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u0017@DX\u0086\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b\u0092\u0001\u0010\u0019\"\u0006\b\u0093\u0001\u0010\u0094\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006°\u0002"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator;", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/node/OwnerScope;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/LayoutNode;)V", "_measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "_rectCache", "Landroidx/compose/ui/geometry/MutableRect;", "alignmentLinesOwner", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "child", "getChild", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "coordinates", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "density", "", "getDensity", "()F", "drawBlock", "Lkotlin/Function2;", "Landroidx/compose/ui/graphics/Canvas;", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "", "getDrawBlock$annotations", "()V", "explicitLayer", "fontScale", "getFontScale", "forceMeasureWithLookaheadConstraints", "", "getForceMeasureWithLookaheadConstraints$ui_release", "()Z", "setForceMeasureWithLookaheadConstraints$ui_release", "(Z)V", "forcePlaceWithLookaheadOffset", "getForcePlaceWithLookaheadOffset$ui_release", "setForcePlaceWithLookaheadOffset$ui_release", "hasMeasureResult", "getHasMeasureResult", "introducesMotionFrameOfReference", "getIntroducesMotionFrameOfReference", "invalidateParentLayer", "Lkotlin/Function0;", "isAttached", "isClipping", "isValidOwnerScope", "lastLayerAlpha", "<set-?>", "lastLayerDrawingWasSkipped", "getLastLayerDrawingWasSkipped$ui_release", "lastMeasurementConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLastMeasurementConstraints-msEJaDk$ui_release", "()J", "Landroidx/compose/ui/node/OwnedLayer;", "layer", "getLayer", "()Landroidx/compose/ui/node/OwnedLayer;", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "layerBlock", "getLayerBlock", "()Lkotlin/jvm/functions/Function1;", "layerDensity", "Landroidx/compose/ui/unit/Density;", "layerLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layerPositionalProperties", "Landroidx/compose/ui/node/LayerPositionalProperties;", "layoutDirection", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/node/LookaheadDelegate;", "lookaheadDelegate", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "setLookaheadDelegate", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "value", "measureResult", "getMeasureResult$ui_release", "()Landroidx/compose/ui/layout/MeasureResult;", "setMeasureResult$ui_release", "(Landroidx/compose/ui/layout/MeasureResult;)V", "minimumTouchTargetSize", "Landroidx/compose/ui/geometry/Size;", "getMinimumTouchTargetSize-NH-jbRc", "oldAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "parent", "getParent", "parentCoordinates", "getParentCoordinates", "parentData", "", "getParentData", "()Ljava/lang/Object;", "parentLayoutCoordinates", "getParentLayoutCoordinates", "Landroidx/compose/ui/unit/IntOffset;", "position", "getPosition-nOcc-ac", "setPosition--gyyYBs", "(J)V", "J", "providedAlignmentLines", "", "getProvidedAlignmentLines", "()Ljava/util/Set;", "rectCache", "getRectCache", "()Landroidx/compose/ui/geometry/MutableRect;", "released", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "tail", "Landroidx/compose/ui/Modifier$Node;", "getTail", "()Landroidx/compose/ui/Modifier$Node;", "wrapped", "getWrapped$ui_release", "()Landroidx/compose/ui/node/NodeCoordinator;", "setWrapped$ui_release", "(Landroidx/compose/ui/node/NodeCoordinator;)V", "wrappedBy", "getWrappedBy$ui_release", "setWrappedBy$ui_release", "zIndex", "getZIndex", "setZIndex", "(F)V", "ancestorToLocal", "ancestor", "rect", "clipBounds", "Landroidx/compose/ui/geometry/Offset;", TypedValues.CycleType.S_WAVE_OFFSET, "includeMotionFrameOfReference", "ancestorToLocal-S_NoaFU", "(Landroidx/compose/ui/node/NodeCoordinator;JZ)J", "calculateMinimumTouchTargetPadding", "calculateMinimumTouchTargetPadding-E7KxVPU", "(J)J", "distanceInMinimumTouchTarget", "pointerPosition", "distanceInMinimumTouchTarget-tz77jQw", "(JJ)F", "draw", "canvas", "graphicsLayer", "drawBorder", "paint", "Landroidx/compose/ui/graphics/Paint;", "drawContainedDrawModifiers", "ensureLookaheadDelegateCreated", "findCommonAncestor", "other", "findCommonAncestor$ui_release", "fromParentPosition", "fromParentPosition-8S9VItk", "(JZ)J", "fromParentRect", "bounds", "hasNode", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "hasNode-H91voCI", "(I)Z", "head", "head-H91voCI", "(I)Landroidx/compose/ui/Modifier$Node;", "headNode", "includeTail", "hitTest", "hitTestSource", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "hitTestResult", "Landroidx/compose/ui/node/HitTestResult;", "isTouchEvent", "isInLayer", "hitTest-YqVAtuI", "(Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;ZZ)V", "hitTestChild", "hitTestChild-YqVAtuI", "invalidateLayer", "isPointerInBounds", "isPointerInBounds-k-4lQ0M", "(J)Z", "isTransparent", "localBoundingBoxOf", "Landroidx/compose/ui/geometry/Rect;", "sourceCoordinates", "localPositionOf", "relativeToSource", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "localPositionOf-S_NoaFU", "(Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "localToRoot", "relativeToLocal", "localToRoot-MK-Hz9U", "localToScreen", "localToScreen-MK-Hz9U", "localToWindow", "localToWindow-MK-Hz9U", "offsetFromEdge", "offsetFromEdge-MK-Hz9U", "onCoordinatesUsed", "onCoordinatesUsed$ui_release", "onLayoutModifierNodeChanged", "onLayoutNodeAttach", "onMeasureResultChanged", "width", "height", "onMeasured", "onPlaced", "onRelease", "performDraw", "performingMeasure", "Landroidx/compose/ui/layout/Placeable;", "constraints", "block", "performingMeasure-K40F9xA", "(JLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/layout/Placeable;", "placeAt", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "(JFLandroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "placeSelf", "placeSelf-MLgxB_4", "(JFLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "placeSelfApparentToRealOffset", "placeSelfApparentToRealOffset-MLgxB_4", "rectInParent", "clipToMinimumTouchTargetSize", "rectInParent$ui_release", "releaseLayer", "replace", "replace$ui_release", "screenToLocal", "relativeToScreen", "screenToLocal-MK-Hz9U", "shouldSharePointerInputWithSiblings", "toParentPosition", "toParentPosition-8S9VItk", "touchBoundsInRoot", "transformFrom", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "transformFrom-EL8BTi8", "(Landroidx/compose/ui/layout/LayoutCoordinates;[F)V", "transformFromAncestor", "transformFromAncestor-EL8BTi8", "(Landroidx/compose/ui/node/NodeCoordinator;[F)V", "transformToAncestor", "transformToAncestor-EL8BTi8", "transformToScreen", "transformToScreen-58bKbWc", "([F)V", "updateLayerBlock", "forceUpdateLayerParameters", "updateLayerParameters", "invokeOnLayoutChange", "visitNodes", ExifInterface.GPS_DIRECTION_TRUE, "visitNodes-aLcG6gQ", "(ILkotlin/jvm/functions/Function1;)V", "mask", "windowToLocal", "relativeToWindow", "windowToLocal-MK-Hz9U", "withPositionTranslation", "withinLayerBounds", "withinLayerBounds-k-4lQ0M", "hit", "hit-1hIXUjU", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;ZZ)V", "hitNear", "distanceFromEdge", "hitNear-JHbHoSQ", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;ZZF)V", "speculativeHit", "speculativeHit-JHbHoSQ", "toCoordinator", "Companion", "HitTestSource", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class NodeCoordinator extends LookaheadCapablePlaceable implements Measurable, LayoutCoordinates, OwnerScope {
    public static final int $stable = 0;
    public static final String ExpectAttachedLayoutCoordinates = "LayoutCoordinate operations are only valid when isAttached is true";
    public static final String UnmeasuredError = "Asking for measurement result of unmeasured layout modifier";
    private MeasureResult _measureResult;
    private MutableRect _rectCache;
    private GraphicsLayer explicitLayer;
    private boolean forceMeasureWithLookaheadConstraints;
    private boolean forcePlaceWithLookaheadOffset;
    private boolean isClipping;
    private boolean lastLayerDrawingWasSkipped;
    private OwnedLayer layer;
    private Function1<? super GraphicsLayerScope, Unit> layerBlock;
    private LayerPositionalProperties layerPositionalProperties;
    private final LayoutNode layoutNode;
    private Map<AlignmentLine, Integer> oldAlignmentLines;
    private boolean released;
    private NodeCoordinator wrapped;
    private NodeCoordinator wrappedBy;
    private float zIndex;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Function1<NodeCoordinator, Unit> onCommitAffectingLayerParams = new Function1<NodeCoordinator, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayerParams$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(NodeCoordinator nodeCoordinator) {
            invoke2(nodeCoordinator);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(NodeCoordinator nodeCoordinator) {
            if (nodeCoordinator.isValidOwnerScope()) {
                LayerPositionalProperties layerPositionalProperties = nodeCoordinator.layerPositionalProperties;
                if (layerPositionalProperties != null) {
                    NodeCoordinator.tmpLayerPositionalProperties.copyFrom(layerPositionalProperties);
                    NodeCoordinator.updateLayerParameters$default(nodeCoordinator, false, 1, null);
                    if (NodeCoordinator.tmpLayerPositionalProperties.hasSameValuesAs(layerPositionalProperties)) {
                        return;
                    }
                    LayoutNode layoutNode = nodeCoordinator.getLayoutNode();
                    LayoutNodeLayoutDelegate layoutDelegate = layoutNode.getLayoutDelegate();
                    if (layoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
                        if (layoutDelegate.getCoordinatesAccessedDuringModifierPlacement() || layoutDelegate.getCoordinatesAccessedDuringPlacement()) {
                            LayoutNode.requestRelayout$ui_release$default(layoutNode, false, 1, null);
                        }
                        layoutDelegate.getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
                    }
                    Owner owner = layoutNode.getOwner();
                    if (owner != null) {
                        owner.requestOnPositionedCallback(layoutNode);
                        return;
                    }
                    return;
                }
                NodeCoordinator.updateLayerParameters$default(nodeCoordinator, false, 1, null);
            }
        }
    };
    private static final Function1<NodeCoordinator, Unit> onCommitAffectingLayer = new Function1<NodeCoordinator, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayer$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(NodeCoordinator nodeCoordinator) {
            invoke2(nodeCoordinator);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(NodeCoordinator nodeCoordinator) {
            OwnedLayer layer = nodeCoordinator.getLayer();
            if (layer != null) {
                layer.invalidate();
            }
        }
    };
    private static final ReusableGraphicsLayerScope graphicsLayerScope = new ReusableGraphicsLayerScope();
    private static final LayerPositionalProperties tmpLayerPositionalProperties = new LayerPositionalProperties();
    private static final float[] tmpMatrix = Matrix.m4799constructorimpl$default(null, 1, null);
    private static final HitTestSource PointerInputSource = new HitTestSource() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$PointerInputSource$1
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTestChildren(LayoutNode parentLayoutNode) {
            return true;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: childHitTest-YqVAtuI, reason: not valid java name */
        public void mo6133childHitTestYqVAtuI(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
            layoutNode.m6055hitTestM_7yMNQ$ui_release(pointerPosition, hitTestResult, isTouchEvent, isInLayer);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: entityType-OLwlOKw, reason: not valid java name */
        public int mo6134entityTypeOLwlOKw() {
            return NodeKind.m6138constructorimpl(16);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v7 */
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean interceptOutOfBoundsChildEvents(Modifier.Node node) {
            int iM6138constructorimpl = NodeKind.m6138constructorimpl(16);
            MutableVector mutableVector = null;
            while (node != 0) {
                if (node instanceof PointerInputModifierNode) {
                    if (((PointerInputModifierNode) node).interceptOutOfBoundsChildEvents()) {
                        return true;
                    }
                } else if ((node.getKindSet() & iM6138constructorimpl) != 0 && (node instanceof DelegatingNode)) {
                    Modifier.Node delegate = ((DelegatingNode) node).getDelegate();
                    int i = 0;
                    node = node;
                    while (delegate != null) {
                        if ((delegate.getKindSet() & iM6138constructorimpl) != 0) {
                            i++;
                            if (i == 1) {
                                node = delegate;
                            } else {
                                if (mutableVector == null) {
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (node != 0) {
                                    if (mutableVector != null) {
                                        mutableVector.add(node);
                                    }
                                    node = 0;
                                }
                                if (mutableVector != null) {
                                    mutableVector.add(delegate);
                                }
                            }
                        }
                        delegate = delegate.getChild();
                        node = node;
                    }
                    if (i == 1) {
                    }
                }
                node = DelegatableNodeKt.pop(mutableVector);
            }
            return false;
        }
    };
    private static final HitTestSource SemanticsSource = new HitTestSource() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$SemanticsSource$1
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean interceptOutOfBoundsChildEvents(Modifier.Node node) {
            return false;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTestChildren(LayoutNode parentLayoutNode) {
            SemanticsConfiguration collapsedSemantics$ui_release = parentLayoutNode.getCollapsedSemantics$ui_release();
            boolean z = false;
            if (collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.getIsClearingSemantics()) {
                z = true;
            }
            return !z;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: childHitTest-YqVAtuI */
        public void mo6133childHitTestYqVAtuI(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
            layoutNode.m6056hitTestSemanticsM_7yMNQ$ui_release(pointerPosition, hitTestResult, isTouchEvent, isInLayer);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: entityType-OLwlOKw */
        public int mo6134entityTypeOLwlOKw() {
            return NodeKind.m6138constructorimpl(8);
        }
    };
    private Density layerDensity = getLayoutNode().getDensity();
    private LayoutDirection layerLayoutDirection = getLayoutNode().getLayoutDirection();
    private float lastLayerAlpha = 0.8f;
    private long position = IntOffset.INSTANCE.m7165getZeronOccac();
    private final Function2<Canvas, GraphicsLayer, Unit> drawBlock = new Function2<Canvas, GraphicsLayer, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$drawBlock$1
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Canvas canvas, GraphicsLayer graphicsLayer) {
            invoke2(canvas, graphicsLayer);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final Canvas canvas, final GraphicsLayer graphicsLayer) {
            if (this.this$0.getLayoutNode().isPlaced()) {
                OwnerSnapshotObserver snapshotObserver = this.this$0.getSnapshotObserver();
                NodeCoordinator nodeCoordinator = this.this$0;
                Function1 function1 = NodeCoordinator.onCommitAffectingLayer;
                final NodeCoordinator nodeCoordinator2 = this.this$0;
                snapshotObserver.observeReads$ui_release(nodeCoordinator, function1, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$drawBlock$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        nodeCoordinator2.drawContainedDrawModifiers(canvas, graphicsLayer);
                    }
                });
                this.this$0.lastLayerDrawingWasSkipped = false;
                return;
            }
            this.this$0.lastLayerDrawingWasSkipped = true;
        }
    };
    private final Function0<Unit> invalidateParentLayer = new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$invalidateParentLayer$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            NodeCoordinator wrappedBy = this.this$0.getWrappedBy();
            if (wrappedBy != null) {
                wrappedBy.invalidateLayer();
            }
        }
    };

    /* JADX INFO: compiled from: NodeCoordinator.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H&ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0005H&ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "", "childHitTest", "", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitTestResult", "Landroidx/compose/ui/node/HitTestResult;", "isTouchEvent", "", "isInLayer", "childHitTest-YqVAtuI", "(Landroidx/compose/ui/node/LayoutNode;JLandroidx/compose/ui/node/HitTestResult;ZZ)V", "entityType", "Landroidx/compose/ui/node/NodeKind;", "entityType-OLwlOKw", "()I", "interceptOutOfBoundsChildEvents", "node", "Landroidx/compose/ui/Modifier$Node;", "shouldHitTestChildren", "parentLayoutNode", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface HitTestSource {
        /* JADX INFO: renamed from: childHitTest-YqVAtuI */
        void mo6133childHitTestYqVAtuI(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer);

        /* JADX INFO: renamed from: entityType-OLwlOKw */
        int mo6134entityTypeOLwlOKw();

        boolean interceptOutOfBoundsChildEvents(Modifier.Node node);

        boolean shouldHitTestChildren(LayoutNode parentLayoutNode);
    }

    private static /* synthetic */ void getDrawBlock$annotations() {
    }

    public abstract void ensureLookaheadDelegateCreated();

    public abstract LookaheadDelegate getLookaheadDelegate();

    public abstract Modifier.Node getTail();

    protected abstract void setLookaheadDelegate(LookaheadDelegate lookaheadDelegate);

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public NodeCoordinator(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
    }

    /* JADX INFO: renamed from: getForcePlaceWithLookaheadOffset$ui_release, reason: from getter */
    public final boolean getForcePlaceWithLookaheadOffset() {
        return this.forcePlaceWithLookaheadOffset;
    }

    public final void setForcePlaceWithLookaheadOffset$ui_release(boolean z) {
        this.forcePlaceWithLookaheadOffset = z;
    }

    /* JADX INFO: renamed from: getForceMeasureWithLookaheadConstraints$ui_release, reason: from getter */
    public final boolean getForceMeasureWithLookaheadConstraints() {
        return this.forceMeasureWithLookaheadConstraints;
    }

    public final void setForceMeasureWithLookaheadConstraints$ui_release(boolean z) {
        this.forceMeasureWithLookaheadConstraints = z;
    }

    /* JADX INFO: renamed from: getWrapped$ui_release, reason: from getter */
    public final NodeCoordinator getWrapped() {
        return this.wrapped;
    }

    public final void setWrapped$ui_release(NodeCoordinator nodeCoordinator) {
        this.wrapped = nodeCoordinator;
    }

    /* JADX INFO: renamed from: getWrappedBy$ui_release, reason: from getter */
    public final NodeCoordinator getWrappedBy() {
        return this.wrappedBy;
    }

    public final void setWrappedBy$ui_release(NodeCoordinator nodeCoordinator) {
        this.wrappedBy = nodeCoordinator;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return getLayoutNode().getLayoutDirection();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return getLayoutNode().getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return getLayoutNode().getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getParent() {
        return this.wrappedBy;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LayoutCoordinates getCoordinates() {
        return this;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean getIntroducesMotionFrameOfReference() {
        return getIsPlacedUnderMotionFrameOfReference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node headNode(boolean includeTail) {
        Modifier.Node tail;
        if (getLayoutNode().getOuterCoordinator$ui_release() == this) {
            return getLayoutNode().getNodes().getHead();
        }
        if (includeTail) {
            NodeCoordinator nodeCoordinator = this.wrappedBy;
            if (nodeCoordinator == null || (tail = nodeCoordinator.getTail()) == null) {
                return null;
            }
            return tail.getChild();
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        if (nodeCoordinator2 != null) {
            return nodeCoordinator2.getTail();
        }
        return null;
    }

    public final void visitNodes(int mask, boolean includeTail, Function1<? super Modifier.Node, Unit> block) {
        Modifier.Node tail = getTail();
        if (!includeTail && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(includeTail); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & mask) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & mask) != 0) {
                block.invoke(nodeHeadNode);
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: visitNodes-aLcG6gQ, reason: not valid java name */
    public final /* synthetic */ <T> void m6131visitNodesaLcG6gQ(int type, Function1<? super T, Unit> block) {
        boolean zM6147getIncludeSelfInTraversalH91voCI = NodeKindKt.m6147getIncludeSelfInTraversalH91voCI(type);
        Modifier.Node tail = getTail();
        if (!zM6147getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM6147getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & type) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & type) != 0) {
                Modifier.Node nodePop = nodeHeadNode;
                MutableVector mutableVector = null;
                while (nodePop != null) {
                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                    if (nodePop instanceof Object) {
                        block.invoke(nodePop);
                    } else if ((nodePop.getKindSet() & type) != 0 && (nodePop instanceof DelegatingNode)) {
                        int i = 0;
                        for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild()) {
                            if ((delegate$ui_release.getKindSet() & type) != 0) {
                                i++;
                                if (i == 1) {
                                    nodePop = delegate$ui_release;
                                } else {
                                    if (mutableVector == null) {
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    if (nodePop != null) {
                                        if (mutableVector != null) {
                                            mutableVector.add(nodePop);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                            }
                        }
                        if (i == 1) {
                        }
                    }
                    nodePop = DelegatableNodeKt.pop(mutableVector);
                }
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: hasNode-H91voCI, reason: not valid java name */
    private final boolean m6110hasNodeH91voCI(int type) {
        Modifier.Node nodeHeadNode = headNode(NodeKindKt.m6147getIncludeSelfInTraversalH91voCI(type));
        return nodeHeadNode != null && DelegatableNodeKt.m6023has64DMado(nodeHeadNode, type);
    }

    /* JADX INFO: renamed from: head-H91voCI, reason: not valid java name */
    public final Modifier.Node m6124headH91voCI(int type) {
        boolean zM6147getIncludeSelfInTraversalH91voCI = NodeKindKt.m6147getIncludeSelfInTraversalH91voCI(type);
        Modifier.Node tail = getTail();
        if (!zM6147getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return null;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM6147getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & type) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & type) != 0) {
                return nodeHeadNode;
            }
            if (nodeHeadNode == tail) {
                return null;
            }
        }
        return null;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: getSize-YbymL2g */
    public final long mo5911getSizeYbymL2g() {
        return getMeasuredSize();
    }

    protected final Function1<GraphicsLayerScope, Unit> getLayerBlock() {
        return this.layerBlock;
    }

    public final boolean isTransparent() {
        if (this.layer != null && this.lastLayerAlpha <= 0.0f) {
            return true;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            return nodeCoordinator.isTransparent();
        }
        return false;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public AlignmentLinesOwner getAlignmentLinesOwner() {
        return getLayoutNode().getLayoutDelegate().getAlignmentLinesOwner$ui_release();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getChild() {
        return this.wrapped;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public void replace$ui_release() {
        GraphicsLayer graphicsLayer = this.explicitLayer;
        if (graphicsLayer != null) {
            mo5964placeAtf8xVGno(getPosition(), this.zIndex, graphicsLayer);
        } else {
            mo5904placeAtf8xVGno(getPosition(), this.zIndex, this.layerBlock);
        }
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getTail().getIsAttached();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public MeasureResult getMeasureResult$ui_release() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        throw new IllegalStateException(UnmeasuredError.toString());
    }

    public void setMeasureResult$ui_release(MeasureResult measureResult) {
        MeasureResult measureResult2 = this._measureResult;
        if (measureResult != measureResult2) {
            this._measureResult = measureResult;
            if (measureResult2 == null || measureResult.get$w() != measureResult2.get$w() || measureResult.get$h() != measureResult2.get$h()) {
                onMeasureResultChanged(measureResult.get$w(), measureResult.get$h());
            }
            Map<AlignmentLine, Integer> map = this.oldAlignmentLines;
            if (((map == null || map.isEmpty()) && measureResult.getAlignmentLines().isEmpty()) || Intrinsics.areEqual(measureResult.getAlignmentLines(), this.oldAlignmentLines)) {
                return;
            }
            getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
            LinkedHashMap linkedHashMap = this.oldAlignmentLines;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap();
                this.oldAlignmentLines = linkedHashMap;
            }
            linkedHashMap.clear();
            linkedHashMap.putAll(measureResult.getAlignmentLines());
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        LinkedHashSet linkedHashSet = null;
        for (NodeCoordinator nodeCoordinator = this; nodeCoordinator != null; nodeCoordinator = nodeCoordinator.wrapped) {
            MeasureResult measureResult = nodeCoordinator._measureResult;
            Map<AlignmentLine, Integer> alignmentLines = measureResult != null ? measureResult.getAlignmentLines() : null;
            if (alignmentLines != null && (!alignmentLines.isEmpty())) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.addAll(alignmentLines.keySet());
            }
        }
        return linkedHashSet == null ? SetsKt.emptySet() : linkedHashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6 */
    protected void onMeasureResultChanged(int width, int height) {
        NodeCoordinator nodeCoordinator;
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mo6189resizeozmzZPI(IntSizeKt.IntSize(width, height));
        } else if (getLayoutNode().isPlaced() && (nodeCoordinator = this.wrappedBy) != null) {
            nodeCoordinator.invalidateLayer();
        }
        m5965setMeasuredSizeozmzZPI(IntSizeKt.IntSize(width, height));
        if (this.layerBlock != null) {
            updateLayerParameters(false);
        }
        int iM6138constructorimpl = NodeKind.m6138constructorimpl(4);
        boolean zM6147getIncludeSelfInTraversalH91voCI = NodeKindKt.m6147getIncludeSelfInTraversalH91voCI(iM6138constructorimpl);
        Modifier.Node tail = getTail();
        if (zM6147getIncludeSelfInTraversalH91voCI || (tail = tail.getParent()) != null) {
            for (Modifier.Node nodeHeadNode = headNode(zM6147getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM6138constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
                if ((nodeHeadNode.getKindSet() & iM6138constructorimpl) != 0) {
                    Modifier.Node nodePop = nodeHeadNode;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof DrawModifierNode) {
                            ((DrawModifierNode) nodePop).onMeasureResultChanged();
                        } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                            int i = 0;
                            nodePop = nodePop;
                            while (delegate$ui_release != null) {
                                if ((delegate$ui_release.getKindSet() & iM6138constructorimpl) != 0) {
                                    i++;
                                    if (i == 1) {
                                        nodePop = delegate$ui_release;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != 0) {
                                            if (mutableVector != null) {
                                                mutableVector.add(nodePop);
                                            }
                                            nodePop = 0;
                                        }
                                        if (mutableVector != null) {
                                            mutableVector.add(delegate$ui_release);
                                        }
                                    }
                                }
                                delegate$ui_release = delegate$ui_release.getChild();
                                nodePop = nodePop;
                            }
                            if (i == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
                if (nodeHeadNode == tail) {
                    break;
                }
            }
        }
        Owner owner = getLayoutNode().getOwner();
        if (owner != null) {
            owner.onLayoutChange(getLayoutNode());
        }
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* JADX INFO: renamed from: getPosition-nOcc-ac, reason: from getter */
    public long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: setPosition--gyyYBs, reason: not valid java name */
    protected void m6129setPositiongyyYBs(long j) {
        this.position = j;
    }

    public final float getZIndex() {
        return this.zIndex;
    }

    protected final void setZIndex(float f) {
        this.zIndex = f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v4, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v8 */
    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        if (!getLayoutNode().getNodes().m6099hasH91voCI$ui_release(NodeKind.m6138constructorimpl(64))) {
            return null;
        }
        getTail();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        for (Modifier.Node tail = getLayoutNode().getNodes().getTail(); tail != null; tail = tail.getParent()) {
            if ((NodeKind.m6138constructorimpl(64) & tail.getKindSet()) != 0) {
                int iM6138constructorimpl = NodeKind.m6138constructorimpl(64);
                MutableVector mutableVector = null;
                Modifier.Node nodePop = tail;
                while (nodePop != 0) {
                    if (nodePop instanceof ParentDataModifierNode) {
                        objectRef.element = ((ParentDataModifierNode) nodePop).modifyParentData(getLayoutNode().getDensity(), objectRef.element);
                    } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                        Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                        int i = 0;
                        nodePop = nodePop;
                        while (delegate$ui_release != null) {
                            if ((delegate$ui_release.getKindSet() & iM6138constructorimpl) != 0) {
                                i++;
                                if (i == 1) {
                                    nodePop = delegate$ui_release;
                                } else {
                                    if (mutableVector == null) {
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    if (nodePop != 0) {
                                        if (mutableVector != null) {
                                            mutableVector.add(nodePop);
                                        }
                                        nodePop = 0;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                            }
                            delegate$ui_release = delegate$ui_release.getChild();
                            nodePop = nodePop;
                        }
                        if (i == 1) {
                        }
                    }
                    nodePop = DelegatableNodeKt.pop(mutableVector);
                }
            }
        }
        return objectRef.element;
    }

    public final void onCoordinatesUsed$ui_release() {
        getLayoutNode().getLayoutDelegate().onCoordinatesUsed();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui_release();
        return getLayoutNode().getOuterCoordinator$ui_release().wrappedBy;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentCoordinates() {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui_release();
        return this.wrappedBy;
    }

    protected final MutableRect getRectCache() {
        MutableRect mutableRect = this._rectCache;
        if (mutableRect != null) {
            return mutableRect;
        }
        MutableRect mutableRect2 = new MutableRect(0.0f, 0.0f, 0.0f, 0.0f);
        this._rectCache = mutableRect2;
        return mutableRect2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OwnerSnapshotObserver getSnapshotObserver() {
        return LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
    }

    /* JADX INFO: renamed from: getLastMeasurementConstraints-msEJaDk$ui_release, reason: not valid java name */
    public final long m6122getLastMeasurementConstraintsmsEJaDk$ui_release() {
        return getMeasurementConstraints();
    }

    /* JADX INFO: renamed from: performingMeasure-K40F9xA, reason: not valid java name */
    protected final Placeable m6127performingMeasureK40F9xA(long constraints, Function0<? extends Placeable> block) {
        m5966setMeasurementConstraintsBRTryo0(constraints);
        return block.invoke();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo5904placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        if (this.forcePlaceWithLookaheadOffset) {
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            m6114placeSelfMLgxB_4(lookaheadDelegate.getPosition(), zIndex, layerBlock, null);
            return;
        }
        m6114placeSelfMLgxB_4(position, zIndex, layerBlock, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo5964placeAtf8xVGno(long position, float zIndex, GraphicsLayer layer) {
        if (this.forcePlaceWithLookaheadOffset) {
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            m6114placeSelfMLgxB_4(lookaheadDelegate.getPosition(), zIndex, null, layer);
            return;
        }
        m6114placeSelfMLgxB_4(position, zIndex, null, layer);
    }

    /* JADX INFO: renamed from: placeSelf-MLgxB_4, reason: not valid java name */
    private final void m6114placeSelfMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer explicitLayer) {
        if (explicitLayer != null) {
            if (!(layerBlock == null)) {
                InlineClassHelperKt.throwIllegalArgumentException("both ways to create layers shouldn't be used together");
            }
            if (this.explicitLayer != explicitLayer) {
                this.explicitLayer = null;
                updateLayerBlock$default(this, null, false, 2, null);
                this.explicitLayer = explicitLayer;
            }
            if (this.layer == null) {
                OwnedLayer ownedLayerCreateLayer = LayoutNodeKt.requireOwner(getLayoutNode()).createLayer(this.drawBlock, this.invalidateParentLayer, explicitLayer);
                ownedLayerCreateLayer.mo6189resizeozmzZPI(getMeasuredSize());
                ownedLayerCreateLayer.mo6188movegyyYBs(position);
                this.layer = ownedLayerCreateLayer;
                getLayoutNode().setInnerLayerCoordinatorIsDirty$ui_release(true);
                this.invalidateParentLayer.invoke();
            }
        } else {
            if (this.explicitLayer != null) {
                this.explicitLayer = null;
                updateLayerBlock$default(this, null, false, 2, null);
            }
            updateLayerBlock$default(this, layerBlock, false, 2, null);
        }
        if (!IntOffset.m7154equalsimpl0(getPosition(), position)) {
            m6129setPositiongyyYBs(position);
            getLayoutNode().getLayoutDelegate().getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo6188movegyyYBs(position);
            } else {
                NodeCoordinator nodeCoordinator = this.wrappedBy;
                if (nodeCoordinator != null) {
                    nodeCoordinator.invalidateLayer();
                }
            }
            invalidateAlignmentLinesFromPositionChange(this);
            Owner owner = getLayoutNode().getOwner();
            if (owner != null) {
                owner.onLayoutChange(getLayoutNode());
            }
        }
        this.zIndex = zIndex;
        if (getIsPlacingForAlignment()) {
            return;
        }
        captureRulers$ui_release(getMeasureResult$ui_release());
    }

    public final void releaseLayer() {
        if (this.layer != null) {
            if (this.explicitLayer != null) {
                this.explicitLayer = null;
            }
            updateLayerBlock$default(this, null, false, 2, null);
            LayoutNode.requestRelayout$ui_release$default(getLayoutNode(), false, 1, null);
        }
    }

    /* JADX INFO: renamed from: placeSelfApparentToRealOffset-MLgxB_4, reason: not valid java name */
    public final void m6128placeSelfApparentToRealOffsetMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer layer) {
        m6114placeSelfMLgxB_4(IntOffset.m7159plusqkQi6aY(position, getApparentToRealOffset()), zIndex, layerBlock, layer);
    }

    public final void draw(Canvas canvas, GraphicsLayer graphicsLayer) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.drawLayer(canvas, graphicsLayer);
            return;
        }
        float fM7155getXimpl = IntOffset.m7155getXimpl(getPosition());
        float fM7156getYimpl = IntOffset.m7156getYimpl(getPosition());
        canvas.translate(fM7155getXimpl, fM7156getYimpl);
        drawContainedDrawModifiers(canvas, graphicsLayer);
        canvas.translate(-fM7155getXimpl, -fM7156getYimpl);
    }

    public void performDraw(Canvas canvas, GraphicsLayer graphicsLayer) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.draw(canvas, graphicsLayer);
        }
    }

    public static /* synthetic */ void updateLayerBlock$default(NodeCoordinator nodeCoordinator, Function1 function1, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateLayerBlock");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        nodeCoordinator.updateLayerBlock(function1, z);
    }

    public final void updateLayerBlock(Function1<? super GraphicsLayerScope, Unit> layerBlock, boolean forceUpdateLayerParameters) {
        Owner owner;
        if (!(layerBlock == null || this.explicitLayer == null)) {
            InlineClassHelperKt.throwIllegalArgumentException("layerBlock can't be provided when explicitLayer is provided");
        }
        LayoutNode layoutNode = getLayoutNode();
        boolean z = (!forceUpdateLayerParameters && this.layerBlock == layerBlock && Intrinsics.areEqual(this.layerDensity, layoutNode.getDensity()) && this.layerLayoutDirection == layoutNode.getLayoutDirection()) ? false : true;
        this.layerDensity = layoutNode.getDensity();
        this.layerLayoutDirection = layoutNode.getLayoutDirection();
        if (layoutNode.isAttached() && layerBlock != null) {
            this.layerBlock = layerBlock;
            if (this.layer != null) {
                if (z) {
                    updateLayerParameters$default(this, false, 1, null);
                    return;
                }
                return;
            }
            OwnedLayer ownedLayerCreateLayer$default = Owner.createLayer$default(LayoutNodeKt.requireOwner(layoutNode), this.drawBlock, this.invalidateParentLayer, null, 4, null);
            ownedLayerCreateLayer$default.mo6189resizeozmzZPI(getMeasuredSize());
            ownedLayerCreateLayer$default.mo6188movegyyYBs(getPosition());
            this.layer = ownedLayerCreateLayer$default;
            updateLayerParameters$default(this, false, 1, null);
            layoutNode.setInnerLayerCoordinatorIsDirty$ui_release(true);
            this.invalidateParentLayer.invoke();
            return;
        }
        this.layerBlock = null;
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.destroy();
            layoutNode.setInnerLayerCoordinatorIsDirty$ui_release(true);
            this.invalidateParentLayer.invoke();
            if (isAttached() && (owner = layoutNode.getOwner()) != null) {
                owner.onLayoutChange(layoutNode);
            }
        }
        this.layer = null;
        this.lastLayerDrawingWasSkipped = false;
    }

    static /* synthetic */ void updateLayerParameters$default(NodeCoordinator nodeCoordinator, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateLayerParameters");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        nodeCoordinator.updateLayerParameters(z);
    }

    private final void updateLayerParameters(boolean invokeOnLayoutChange) {
        Owner owner;
        if (this.explicitLayer != null) {
            return;
        }
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            final Function1<? super GraphicsLayerScope, Unit> function1 = this.layerBlock;
            if (function1 != null) {
                ReusableGraphicsLayerScope reusableGraphicsLayerScope = graphicsLayerScope;
                reusableGraphicsLayerScope.reset();
                reusableGraphicsLayerScope.setGraphicsDensity$ui_release(getLayoutNode().getDensity());
                reusableGraphicsLayerScope.setLayoutDirection$ui_release(getLayoutNode().getLayoutDirection());
                reusableGraphicsLayerScope.m4880setSizeuvyYCjk(IntSizeKt.m7209toSizeozmzZPI(mo5911getSizeYbymL2g()));
                getSnapshotObserver().observeReads$ui_release(this, onCommitAffectingLayerParams, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator.updateLayerParameters.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function1.invoke(NodeCoordinator.graphicsLayerScope);
                        NodeCoordinator.graphicsLayerScope.updateOutline$ui_release();
                    }
                });
                LayerPositionalProperties layerPositionalProperties = this.layerPositionalProperties;
                if (layerPositionalProperties == null) {
                    layerPositionalProperties = new LayerPositionalProperties();
                    this.layerPositionalProperties = layerPositionalProperties;
                }
                layerPositionalProperties.copyFrom(reusableGraphicsLayerScope);
                ownedLayer.updateLayerProperties(reusableGraphicsLayerScope);
                this.isClipping = reusableGraphicsLayerScope.getClip();
                this.lastLayerAlpha = reusableGraphicsLayerScope.getAlpha();
                if (!invokeOnLayoutChange || (owner = getLayoutNode().getOwner()) == null) {
                    return;
                }
                owner.onLayoutChange(getLayoutNode());
                return;
            }
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("updateLayerParameters requires a non-null layerBlock");
            throw new KotlinNothingValueException();
        }
        if (this.layerBlock == null) {
            return;
        }
        InlineClassHelperKt.throwIllegalStateException("null layer with a non-null layerBlock");
    }

    /* JADX INFO: renamed from: getLastLayerDrawingWasSkipped$ui_release, reason: from getter */
    public final boolean getLastLayerDrawingWasSkipped() {
        return this.lastLayerDrawingWasSkipped;
    }

    public final OwnedLayer getLayer() {
        return this.layer;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return (this.layer == null || this.released || !getLayoutNode().isAttached()) ? false : true;
    }

    /* JADX INFO: renamed from: getMinimumTouchTargetSize-NH-jbRc, reason: not valid java name */
    public final long m6123getMinimumTouchTargetSizeNHjbRc() {
        return this.layerDensity.mo658toSizeXkaWNTQ(getLayoutNode().getViewConfiguration().mo6059getMinimumTouchTargetSizeMYxV2XQ());
    }

    /* JADX INFO: renamed from: hitTest-YqVAtuI, reason: not valid java name */
    public final void m6125hitTestYqVAtuI(HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
        Modifier.Node nodeM6124headH91voCI = m6124headH91voCI(hitTestSource.mo6134entityTypeOLwlOKw());
        if (!m6132withinLayerBoundsk4lQ0M(pointerPosition)) {
            if (isTouchEvent) {
                float fM6120distanceInMinimumTouchTargettz77jQw = m6120distanceInMinimumTouchTargettz77jQw(pointerPosition, m6123getMinimumTouchTargetSizeNHjbRc());
                if (Float.isInfinite(fM6120distanceInMinimumTouchTargettz77jQw) || Float.isNaN(fM6120distanceInMinimumTouchTargettz77jQw) || !hitTestResult.isHitInMinimumTouchTargetBetter(fM6120distanceInMinimumTouchTargettz77jQw, false)) {
                    return;
                }
                m6112hitNearJHbHoSQ(nodeM6124headH91voCI, hitTestSource, pointerPosition, hitTestResult, isTouchEvent, false, fM6120distanceInMinimumTouchTargettz77jQw);
                return;
            }
            return;
        }
        if (nodeM6124headH91voCI == null) {
            mo6047hitTestChildYqVAtuI(hitTestSource, pointerPosition, hitTestResult, isTouchEvent, isInLayer);
            return;
        }
        if (m6126isPointerInBoundsk4lQ0M(pointerPosition)) {
            m6111hit1hIXUjU(nodeM6124headH91voCI, hitTestSource, pointerPosition, hitTestResult, isTouchEvent, isInLayer);
            return;
        }
        float fM6120distanceInMinimumTouchTargettz77jQw2 = !isTouchEvent ? Float.POSITIVE_INFINITY : m6120distanceInMinimumTouchTargettz77jQw(pointerPosition, m6123getMinimumTouchTargetSizeNHjbRc());
        if (!Float.isInfinite(fM6120distanceInMinimumTouchTargettz77jQw2) && !Float.isNaN(fM6120distanceInMinimumTouchTargettz77jQw2) && hitTestResult.isHitInMinimumTouchTargetBetter(fM6120distanceInMinimumTouchTargettz77jQw2, isInLayer)) {
            m6112hitNearJHbHoSQ(nodeM6124headH91voCI, hitTestSource, pointerPosition, hitTestResult, isTouchEvent, isInLayer, fM6120distanceInMinimumTouchTargettz77jQw2);
        } else {
            m6115speculativeHitJHbHoSQ(nodeM6124headH91voCI, hitTestSource, pointerPosition, hitTestResult, isTouchEvent, isInLayer, fM6120distanceInMinimumTouchTargettz77jQw2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hit-1hIXUjU, reason: not valid java name */
    public final void m6111hit1hIXUjU(final Modifier.Node node, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final boolean z, final boolean z2) {
        if (node == null) {
            mo6047hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
        } else {
            hitTestResult.hit(node, z2, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$hit$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.m6111hit1hIXUjU(NodeCoordinatorKt.m6136nextUntilhw7D004(node, hitTestSource.mo6134entityTypeOLwlOKw(), NodeKind.m6138constructorimpl(2)), hitTestSource, j, hitTestResult, z, z2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hitNear-JHbHoSQ, reason: not valid java name */
    public final void m6112hitNearJHbHoSQ(final Modifier.Node node, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final boolean z, final boolean z2, final float f) {
        if (node == null) {
            mo6047hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
        } else {
            hitTestResult.hitInMinimumTouchTarget(node, f, z2, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$hitNear$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.m6112hitNearJHbHoSQ(NodeCoordinatorKt.m6136nextUntilhw7D004(node, hitTestSource.mo6134entityTypeOLwlOKw(), NodeKind.m6138constructorimpl(2)), hitTestSource, j, hitTestResult, z, z2, f);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: speculativeHit-JHbHoSQ, reason: not valid java name */
    public final void m6115speculativeHitJHbHoSQ(final Modifier.Node node, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final boolean z, final boolean z2, final float f) {
        if (node == null) {
            mo6047hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
        } else if (!hitTestSource.interceptOutOfBoundsChildEvents(node)) {
            m6115speculativeHitJHbHoSQ(NodeCoordinatorKt.m6136nextUntilhw7D004(node, hitTestSource.mo6134entityTypeOLwlOKw(), NodeKind.m6138constructorimpl(2)), hitTestSource, j, hitTestResult, z, z2, f);
        } else {
            hitTestResult.speculativeHit(node, f, z2, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$speculativeHit$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.m6115speculativeHitJHbHoSQ(NodeCoordinatorKt.m6136nextUntilhw7D004(node, hitTestSource.mo6134entityTypeOLwlOKw(), NodeKind.m6138constructorimpl(2)), hitTestSource, j, hitTestResult, z, z2, f);
                }
            });
        }
    }

    /* JADX INFO: renamed from: hitTestChild-YqVAtuI */
    public void mo6047hitTestChildYqVAtuI(HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.m6125hitTestYqVAtuI(hitTestSource, m6109fromParentPosition8S9VItk$default(nodeCoordinator, pointerPosition, false, 2, null), hitTestResult, isTouchEvent, isInLayer);
        }
    }

    public final Rect touchBoundsInRoot() {
        if (!isAttached()) {
            return Rect.INSTANCE.getZero();
        }
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(this);
        MutableRect rectCache = getRectCache();
        long jM6119calculateMinimumTouchTargetPaddingE7KxVPU = m6119calculateMinimumTouchTargetPaddingE7KxVPU(m6123getMinimumTouchTargetSizeNHjbRc());
        rectCache.setLeft(-Size.m4396getWidthimpl(jM6119calculateMinimumTouchTargetPaddingE7KxVPU));
        rectCache.setTop(-Size.m4393getHeightimpl(jM6119calculateMinimumTouchTargetPaddingE7KxVPU));
        rectCache.setRight(getMeasuredWidth() + Size.m4396getWidthimpl(jM6119calculateMinimumTouchTargetPaddingE7KxVPU));
        rectCache.setBottom(getMeasuredHeight() + Size.m4393getHeightimpl(jM6119calculateMinimumTouchTargetPaddingE7KxVPU));
        NodeCoordinator nodeCoordinator = this;
        while (nodeCoordinator != layoutCoordinatesFindRootCoordinates) {
            nodeCoordinator.rectInParent$ui_release(rectCache, false, true);
            if (rectCache.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
            nodeCoordinator = nodeCoordinator.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator);
        }
        return MutableRectKt.toRect(rectCache);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo5917screenToLocalMKHz9U(long relativeToScreen) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        return mo5912localPositionOfR5De75A(LayoutCoordinatesKt.findRootCoordinates(this), LayoutNodeKt.requireOwner(getLayoutNode()).mo5857screenToLocalMKHz9U(relativeToScreen));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo5915localToScreenMKHz9U(long relativeToLocal) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        return LayoutNodeKt.requireOwner(getLayoutNode()).mo5856localToScreenMKHz9U(mo5914localToRootMKHz9U(relativeToLocal));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U */
    public long mo5920windowToLocalMKHz9U(long relativeToWindow) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(this);
        return mo5912localPositionOfR5De75A(layoutCoordinatesFindRootCoordinates, Offset.m4331minusMKHz9U(LayoutNodeKt.requireOwner(getLayoutNode()).mo6191calculateLocalPositionMKHz9U(relativeToWindow), LayoutCoordinatesKt.positionInRoot(layoutCoordinatesFindRootCoordinates)));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToWindow-MK-Hz9U */
    public long mo5916localToWindowMKHz9U(long relativeToLocal) {
        return LayoutNodeKt.requireOwner(getLayoutNode()).mo6192calculatePositionInWindowMKHz9U(mo5914localToRootMKHz9U(relativeToLocal));
    }

    private final NodeCoordinator toCoordinator(LayoutCoordinates layoutCoordinates) {
        NodeCoordinator coordinator;
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates = layoutCoordinates instanceof LookaheadLayoutCoordinates ? (LookaheadLayoutCoordinates) layoutCoordinates : null;
        if (lookaheadLayoutCoordinates != null && (coordinator = lookaheadLayoutCoordinates.getCoordinator()) != null) {
            return coordinator;
        }
        Intrinsics.checkNotNull(layoutCoordinates, "null cannot be cast to non-null type androidx.compose.ui.node.NodeCoordinator");
        return (NodeCoordinator) layoutCoordinates;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-R5De75A */
    public long mo5912localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        return mo5913localPositionOfS_NoaFU(sourceCoordinates, relativeToSource, true);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-S_NoaFU */
    public long mo5913localPositionOfS_NoaFU(LayoutCoordinates sourceCoordinates, long relativeToSource, boolean includeMotionFrameOfReference) {
        if (sourceCoordinates instanceof LookaheadLayoutCoordinates) {
            ((LookaheadLayoutCoordinates) sourceCoordinates).getCoordinator().onCoordinatesUsed$ui_release();
            return Offset.m4336unaryMinusF1C5BW0(sourceCoordinates.mo5913localPositionOfS_NoaFU(this, Offset.m4336unaryMinusF1C5BW0(relativeToSource), includeMotionFrameOfReference));
        }
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        while (coordinator != nodeCoordinatorFindCommonAncestor$ui_release) {
            relativeToSource = coordinator.m6130toParentPosition8S9VItk(relativeToSource, includeMotionFrameOfReference);
            coordinator = coordinator.wrappedBy;
            Intrinsics.checkNotNull(coordinator);
        }
        return m6108ancestorToLocalS_NoaFU(nodeCoordinatorFindCommonAncestor$ui_release, relativeToSource, includeMotionFrameOfReference);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformFrom-EL8BTi8 */
    public void mo5918transformFromEL8BTi8(LayoutCoordinates sourceCoordinates, float[] matrix) {
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        Matrix.m4808resetimpl(matrix);
        coordinator.m6118transformToAncestorEL8BTi8(nodeCoordinatorFindCommonAncestor$ui_release, matrix);
        m6117transformFromAncestorEL8BTi8(nodeCoordinatorFindCommonAncestor$ui_release, matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformToScreen-58bKbWc */
    public void mo5919transformToScreen58bKbWc(float[] matrix) {
        Owner ownerRequireOwner = LayoutNodeKt.requireOwner(getLayoutNode());
        m6118transformToAncestorEL8BTi8(toCoordinator(LayoutCoordinatesKt.findRootCoordinates(this)), matrix);
        ownerRequireOwner.mo5855localToScreen58bKbWc(matrix);
    }

    /* JADX INFO: renamed from: transformToAncestor-EL8BTi8, reason: not valid java name */
    private final void m6118transformToAncestorEL8BTi8(NodeCoordinator ancestor, float[] matrix) {
        NodeCoordinator nodeCoordinator = this;
        while (!Intrinsics.areEqual(nodeCoordinator, ancestor)) {
            OwnedLayer ownedLayer = nodeCoordinator.layer;
            if (ownedLayer != null) {
                ownedLayer.mo6190transform58bKbWc(matrix);
            }
            if (!IntOffset.m7154equalsimpl0(nodeCoordinator.getPosition(), IntOffset.INSTANCE.m7165getZeronOccac())) {
                float[] fArr = tmpMatrix;
                Matrix.m4808resetimpl(fArr);
                Matrix.m4819translateimpl$default(fArr, IntOffset.m7155getXimpl(r1), IntOffset.m7156getYimpl(r1), 0.0f, 4, null);
                Matrix.m4816timesAssign58bKbWc(matrix, fArr);
            }
            nodeCoordinator = nodeCoordinator.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator);
        }
    }

    /* JADX INFO: renamed from: transformFromAncestor-EL8BTi8, reason: not valid java name */
    private final void m6117transformFromAncestorEL8BTi8(NodeCoordinator ancestor, float[] matrix) {
        if (Intrinsics.areEqual(ancestor, this)) {
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        Intrinsics.checkNotNull(nodeCoordinator);
        nodeCoordinator.m6117transformFromAncestorEL8BTi8(ancestor, matrix);
        if (!IntOffset.m7154equalsimpl0(getPosition(), IntOffset.INSTANCE.m7165getZeronOccac())) {
            float[] fArr = tmpMatrix;
            Matrix.m4808resetimpl(fArr);
            Matrix.m4819translateimpl$default(fArr, -IntOffset.m7155getXimpl(getPosition()), -IntOffset.m7156getYimpl(getPosition()), 0.0f, 4, null);
            Matrix.m4816timesAssign58bKbWc(matrix, fArr);
        }
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mo6185inverseTransform58bKbWc(matrix);
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        if (!sourceCoordinates.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates " + sourceCoordinates + " is not attached!");
        }
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        MutableRect rectCache = getRectCache();
        rectCache.setLeft(0.0f);
        rectCache.setTop(0.0f);
        rectCache.setRight(IntSize.m7197getWidthimpl(sourceCoordinates.mo5911getSizeYbymL2g()));
        rectCache.setBottom(IntSize.m7196getHeightimpl(sourceCoordinates.mo5911getSizeYbymL2g()));
        NodeCoordinator nodeCoordinator = coordinator;
        while (nodeCoordinator != nodeCoordinatorFindCommonAncestor$ui_release) {
            boolean z = clipBounds;
            rectInParent$ui_release$default(nodeCoordinator, rectCache, z, false, 4, null);
            if (rectCache.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
            nodeCoordinator = nodeCoordinator.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator);
            clipBounds = z;
        }
        ancestorToLocal(nodeCoordinatorFindCommonAncestor$ui_release, rectCache, clipBounds);
        return MutableRectKt.toRect(rectCache);
    }

    /* JADX INFO: renamed from: ancestorToLocal-S_NoaFU, reason: not valid java name */
    private final long m6108ancestorToLocalS_NoaFU(NodeCoordinator ancestor, long offset, boolean includeMotionFrameOfReference) {
        if (ancestor == this) {
            return offset;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator == null || Intrinsics.areEqual(ancestor, nodeCoordinator)) {
            return m6121fromParentPosition8S9VItk(offset, includeMotionFrameOfReference);
        }
        return m6121fromParentPosition8S9VItk(nodeCoordinator.m6108ancestorToLocalS_NoaFU(ancestor, offset, includeMotionFrameOfReference), includeMotionFrameOfReference);
    }

    private final void ancestorToLocal(NodeCoordinator ancestor, MutableRect rect, boolean clipBounds) {
        if (ancestor == this) {
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.ancestorToLocal(ancestor, rect, clipBounds);
        }
        fromParentRect(rect, clipBounds);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToRoot-MK-Hz9U */
    public long mo5914localToRootMKHz9U(long relativeToLocal) {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui_release();
        long jM6116toParentPosition8S9VItk$default = relativeToLocal;
        for (NodeCoordinator nodeCoordinator = this; nodeCoordinator != null; nodeCoordinator = nodeCoordinator.wrappedBy) {
            jM6116toParentPosition8S9VItk$default = m6116toParentPosition8S9VItk$default(nodeCoordinator, jM6116toParentPosition8S9VItk$default, false, 2, null);
        }
        return jM6116toParentPosition8S9VItk$default;
    }

    protected final void withPositionTranslation(Canvas canvas, Function1<? super Canvas, Unit> block) {
        float fM7155getXimpl = IntOffset.m7155getXimpl(getPosition());
        float fM7156getYimpl = IntOffset.m7156getYimpl(getPosition());
        canvas.translate(fM7155getXimpl, fM7156getYimpl);
        block.invoke(canvas);
        canvas.translate(-fM7155getXimpl, -fM7156getYimpl);
    }

    /* JADX INFO: renamed from: toParentPosition-8S9VItk$default, reason: not valid java name */
    public static /* synthetic */ long m6116toParentPosition8S9VItk$default(NodeCoordinator nodeCoordinator, long j, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toParentPosition-8S9VItk");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return nodeCoordinator.m6130toParentPosition8S9VItk(j, z);
    }

    /* JADX INFO: renamed from: toParentPosition-8S9VItk, reason: not valid java name */
    public long m6130toParentPosition8S9VItk(long position, boolean includeMotionFrameOfReference) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            position = ownedLayer.mo6187mapOffset8S9VItk(position, false);
        }
        return (includeMotionFrameOfReference || !getIsPlacedUnderMotionFrameOfReference()) ? IntOffsetKt.m7169plusNvtHpc(position, getPosition()) : position;
    }

    /* JADX INFO: renamed from: fromParentPosition-8S9VItk$default, reason: not valid java name */
    public static /* synthetic */ long m6109fromParentPosition8S9VItk$default(NodeCoordinator nodeCoordinator, long j, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fromParentPosition-8S9VItk");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return nodeCoordinator.m6121fromParentPosition8S9VItk(j, z);
    }

    /* JADX INFO: renamed from: fromParentPosition-8S9VItk, reason: not valid java name */
    public long m6121fromParentPosition8S9VItk(long position, boolean includeMotionFrameOfReference) {
        if (includeMotionFrameOfReference || !getIsPlacedUnderMotionFrameOfReference()) {
            position = IntOffsetKt.m7167minusNvtHpc(position, getPosition());
        }
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer != null ? ownedLayer.mo6187mapOffset8S9VItk(position, true) : position;
    }

    protected final void drawBorder(Canvas canvas, Paint paint) {
        canvas.drawRect(new Rect(0.5f, 0.5f, IntSize.m7197getWidthimpl(getMeasuredSize()) - 0.5f, IntSize.m7196getHeightimpl(getMeasuredSize()) - 0.5f), paint);
    }

    public final void onLayoutNodeAttach() {
        updateLayerBlock(this.layerBlock, true);
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
        }
    }

    public final void onRelease() {
        this.released = true;
        this.invalidateParentLayer.invoke();
        releaseLayer();
    }

    public static /* synthetic */ void rectInParent$ui_release$default(NodeCoordinator nodeCoordinator, MutableRect mutableRect, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: rectInParent");
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        nodeCoordinator.rectInParent$ui_release(mutableRect, z, z2);
    }

    public final void rectInParent$ui_release(MutableRect bounds, boolean clipBounds, boolean clipToMinimumTouchTargetSize) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            if (this.isClipping) {
                if (clipToMinimumTouchTargetSize) {
                    long jM6123getMinimumTouchTargetSizeNHjbRc = m6123getMinimumTouchTargetSizeNHjbRc();
                    float fM4396getWidthimpl = Size.m4396getWidthimpl(jM6123getMinimumTouchTargetSizeNHjbRc) / 2.0f;
                    float fM4393getHeightimpl = Size.m4393getHeightimpl(jM6123getMinimumTouchTargetSizeNHjbRc) / 2.0f;
                    bounds.intersect(-fM4396getWidthimpl, -fM4393getHeightimpl, IntSize.m7197getWidthimpl(mo5911getSizeYbymL2g()) + fM4396getWidthimpl, IntSize.m7196getHeightimpl(mo5911getSizeYbymL2g()) + fM4393getHeightimpl);
                } else if (clipBounds) {
                    bounds.intersect(0.0f, 0.0f, IntSize.m7197getWidthimpl(mo5911getSizeYbymL2g()), IntSize.m7196getHeightimpl(mo5911getSizeYbymL2g()));
                }
                if (bounds.isEmpty()) {
                    return;
                }
            }
            ownedLayer.mapBounds(bounds, false);
        }
        float fM7155getXimpl = IntOffset.m7155getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() + fM7155getXimpl);
        bounds.setRight(bounds.getRight() + fM7155getXimpl);
        float fM7156getYimpl = IntOffset.m7156getYimpl(getPosition());
        bounds.setTop(bounds.getTop() + fM7156getYimpl);
        bounds.setBottom(bounds.getBottom() + fM7156getYimpl);
    }

    private final void fromParentRect(MutableRect bounds, boolean clipBounds) {
        float fM7155getXimpl = IntOffset.m7155getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() - fM7155getXimpl);
        bounds.setRight(bounds.getRight() - fM7155getXimpl);
        float fM7156getYimpl = IntOffset.m7156getYimpl(getPosition());
        bounds.setTop(bounds.getTop() - fM7156getYimpl);
        bounds.setBottom(bounds.getBottom() - fM7156getYimpl);
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mapBounds(bounds, true);
            if (this.isClipping && clipBounds) {
                bounds.intersect(0.0f, 0.0f, IntSize.m7197getWidthimpl(mo5911getSizeYbymL2g()), IntSize.m7196getHeightimpl(mo5911getSizeYbymL2g()));
                bounds.isEmpty();
            }
        }
    }

    /* JADX INFO: renamed from: withinLayerBounds-k-4lQ0M, reason: not valid java name */
    protected final boolean m6132withinLayerBoundsk4lQ0M(long pointerPosition) {
        if (!OffsetKt.m4344isFinitek4lQ0M(pointerPosition)) {
            return false;
        }
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer == null || !this.isClipping || ownedLayer.mo6186isInLayerk4lQ0M(pointerPosition);
    }

    /* JADX INFO: renamed from: isPointerInBounds-k-4lQ0M, reason: not valid java name */
    protected final boolean m6126isPointerInBoundsk4lQ0M(long pointerPosition) {
        float fM4327getXimpl = Offset.m4327getXimpl(pointerPosition);
        float fM4328getYimpl = Offset.m4328getYimpl(pointerPosition);
        return fM4327getXimpl >= 0.0f && fM4328getYimpl >= 0.0f && fM4327getXimpl < ((float) getMeasuredWidth()) && fM4328getYimpl < ((float) getMeasuredHeight());
    }

    public void invalidateLayer() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.invalidateLayer();
        }
    }

    public void onLayoutModifierNodeChanged() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
        }
    }

    public final NodeCoordinator findCommonAncestor$ui_release(NodeCoordinator other) {
        LayoutNode layoutNode = other.getLayoutNode();
        LayoutNode layoutNode2 = getLayoutNode();
        if (layoutNode == layoutNode2) {
            Modifier.Node tail = other.getTail();
            Modifier.Node tail2 = getTail();
            int iM6138constructorimpl = NodeKind.m6138constructorimpl(2);
            if (!tail2.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
            }
            for (Modifier.Node parent = tail2.getNode().getParent(); parent != null; parent = parent.getParent()) {
                if ((parent.getKindSet() & iM6138constructorimpl) != 0 && parent == tail) {
                    return other;
                }
            }
            return this;
        }
        while (layoutNode.getDepth() > layoutNode2.getDepth()) {
            layoutNode = layoutNode.getParent$ui_release();
            Intrinsics.checkNotNull(layoutNode);
        }
        while (layoutNode2.getDepth() > layoutNode.getDepth()) {
            layoutNode2 = layoutNode2.getParent$ui_release();
            Intrinsics.checkNotNull(layoutNode2);
        }
        while (layoutNode != layoutNode2) {
            layoutNode = layoutNode.getParent$ui_release();
            layoutNode2 = layoutNode2.getParent$ui_release();
            if (layoutNode == null || layoutNode2 == null) {
                throw new IllegalArgumentException("layouts are not part of the same hierarchy");
            }
        }
        if (layoutNode2 != getLayoutNode()) {
            if (layoutNode != other.getLayoutNode()) {
                return layoutNode.getInnerCoordinator$ui_release();
            }
            return other;
        }
        return this;
    }

    /* JADX INFO: renamed from: offsetFromEdge-MK-Hz9U, reason: not valid java name */
    private final long m6113offsetFromEdgeMKHz9U(long pointerPosition) {
        float fM4327getXimpl = Offset.m4327getXimpl(pointerPosition);
        float fMax = Math.max(0.0f, fM4327getXimpl < 0.0f ? -fM4327getXimpl : fM4327getXimpl - getMeasuredWidth());
        float fM4328getYimpl = Offset.m4328getYimpl(pointerPosition);
        return OffsetKt.Offset(fMax, Math.max(0.0f, fM4328getYimpl < 0.0f ? -fM4328getYimpl : fM4328getYimpl - getMeasuredHeight()));
    }

    /* JADX INFO: renamed from: calculateMinimumTouchTargetPadding-E7KxVPU, reason: not valid java name */
    protected final long m6119calculateMinimumTouchTargetPaddingE7KxVPU(long minimumTouchTargetSize) {
        return SizeKt.Size(Math.max(0.0f, (Size.m4396getWidthimpl(minimumTouchTargetSize) - getMeasuredWidth()) / 2.0f), Math.max(0.0f, (Size.m4393getHeightimpl(minimumTouchTargetSize) - getMeasuredHeight()) / 2.0f));
    }

    /* JADX INFO: renamed from: distanceInMinimumTouchTarget-tz77jQw, reason: not valid java name */
    protected final float m6120distanceInMinimumTouchTargettz77jQw(long pointerPosition, long minimumTouchTargetSize) {
        if (getMeasuredWidth() >= Size.m4396getWidthimpl(minimumTouchTargetSize) && getMeasuredHeight() >= Size.m4393getHeightimpl(minimumTouchTargetSize)) {
            return Float.POSITIVE_INFINITY;
        }
        long jM6119calculateMinimumTouchTargetPaddingE7KxVPU = m6119calculateMinimumTouchTargetPaddingE7KxVPU(minimumTouchTargetSize);
        float fM4396getWidthimpl = Size.m4396getWidthimpl(jM6119calculateMinimumTouchTargetPaddingE7KxVPU);
        float fM4393getHeightimpl = Size.m4393getHeightimpl(jM6119calculateMinimumTouchTargetPaddingE7KxVPU);
        long jM6113offsetFromEdgeMKHz9U = m6113offsetFromEdgeMKHz9U(pointerPosition);
        if ((fM4396getWidthimpl > 0.0f || fM4393getHeightimpl > 0.0f) && Offset.m4327getXimpl(jM6113offsetFromEdgeMKHz9U) <= fM4396getWidthimpl && Offset.m4328getYimpl(jM6113offsetFromEdgeMKHz9U) <= fM4393getHeightimpl) {
            return Offset.m4326getDistanceSquaredimpl(jM6113offsetFromEdgeMKHz9U);
        }
        return Float.POSITIVE_INFINITY;
    }

    /* JADX INFO: compiled from: NodeCoordinator.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\u00020\u0016X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator$Companion;", "", "()V", "ExpectAttachedLayoutCoordinates", "", "PointerInputSource", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "getPointerInputSource", "()Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "SemanticsSource", "getSemanticsSource", "UnmeasuredError", "graphicsLayerScope", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "onCommitAffectingLayer", "Lkotlin/Function1;", "Landroidx/compose/ui/node/NodeCoordinator;", "", "onCommitAffectingLayerParams", "tmpLayerPositionalProperties", "Landroidx/compose/ui/node/LayerPositionalProperties;", "tmpMatrix", "Landroidx/compose/ui/graphics/Matrix;", "[F", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HitTestSource getPointerInputSource() {
            return NodeCoordinator.PointerInputSource;
        }

        public final HitTestSource getSemanticsSource() {
            return NodeCoordinator.SemanticsSource;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v10 */
    public final void onMeasured() {
        Modifier.Node parent;
        if (m6110hasNodeH91voCI(NodeKind.m6138constructorimpl(128))) {
            Snapshot.Companion companion = Snapshot.INSTANCE;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                int iM6138constructorimpl = NodeKind.m6138constructorimpl(128);
                boolean zM6147getIncludeSelfInTraversalH91voCI = NodeKindKt.m6147getIncludeSelfInTraversalH91voCI(iM6138constructorimpl);
                if (!zM6147getIncludeSelfInTraversalH91voCI) {
                    parent = getTail().getParent();
                    if (parent == null) {
                    }
                    Unit unit = Unit.INSTANCE;
                }
                parent = getTail();
                for (Modifier.Node nodeHeadNode = headNode(zM6147getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM6138constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
                    if ((nodeHeadNode.getKindSet() & iM6138constructorimpl) != 0) {
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = nodeHeadNode;
                        while (nodePop != 0) {
                            if (nodePop instanceof LayoutAwareModifierNode) {
                                ((LayoutAwareModifierNode) nodePop).mo647onRemeasuredozmzZPI(getMeasuredSize());
                            } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate$ui_release != null) {
                                    if ((delegate$ui_release.getKindSet() & iM6138constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate$ui_release);
                                            }
                                        }
                                    }
                                    delegate$ui_release = delegate$ui_release.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    if (nodeHeadNode == parent) {
                        break;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
            } finally {
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void drawContainedDrawModifiers(Canvas canvas, GraphicsLayer graphicsLayer) {
        Modifier.Node nodeM6124headH91voCI = m6124headH91voCI(NodeKind.m6138constructorimpl(4));
        if (nodeM6124headH91voCI == null) {
            performDraw(canvas, graphicsLayer);
        } else {
            getLayoutNode().getMDrawScope$ui_release().m6065draweZhPAX0$ui_release(canvas, IntSizeKt.m7209toSizeozmzZPI(mo5911getSizeYbymL2g()), this, nodeM6124headH91voCI, graphicsLayer);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    public final void onPlaced() {
        int iM6138constructorimpl = NodeKind.m6138constructorimpl(128);
        boolean zM6147getIncludeSelfInTraversalH91voCI = NodeKindKt.m6147getIncludeSelfInTraversalH91voCI(iM6138constructorimpl);
        Modifier.Node tail = getTail();
        if (!zM6147getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM6147getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM6138constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & iM6138constructorimpl) != 0) {
                Modifier.Node nodePop = nodeHeadNode;
                MutableVector mutableVector = null;
                while (nodePop != 0) {
                    if (nodePop instanceof LayoutAwareModifierNode) {
                        ((LayoutAwareModifierNode) nodePop).onPlaced(this);
                    } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                        Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                        int i = 0;
                        nodePop = nodePop;
                        while (delegate$ui_release != null) {
                            if ((delegate$ui_release.getKindSet() & iM6138constructorimpl) != 0) {
                                i++;
                                if (i == 1) {
                                    nodePop = delegate$ui_release;
                                } else {
                                    if (mutableVector == null) {
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    if (nodePop != 0) {
                                        if (mutableVector != null) {
                                            mutableVector.add(nodePop);
                                        }
                                        nodePop = 0;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                            }
                            delegate$ui_release = delegate$ui_release.getChild();
                            nodePop = nodePop;
                        }
                        if (i == 1) {
                        }
                    }
                    nodePop = DelegatableNodeKt.pop(mutableVector);
                }
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v7 */
    public final boolean shouldSharePointerInputWithSiblings() {
        Modifier.Node nodeHeadNode = headNode(NodeKindKt.m6147getIncludeSelfInTraversalH91voCI(NodeKind.m6138constructorimpl(16)));
        if (nodeHeadNode != null && nodeHeadNode.getIsAttached()) {
            Modifier.Node node = nodeHeadNode;
            int iM6138constructorimpl = NodeKind.m6138constructorimpl(16);
            if (!node.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
            }
            Modifier.Node node2 = node.getNode();
            if ((node2.getAggregateChildKindSet() & iM6138constructorimpl) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & iM6138constructorimpl) != 0) {
                        Modifier.Node nodePop = node2;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof PointerInputModifierNode) {
                                if (((PointerInputModifierNode) nodePop).sharePointerInputWithSiblings()) {
                                    return true;
                                }
                            } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate$ui_release != null) {
                                    if ((delegate$ui_release.getKindSet() & iM6138constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate$ui_release);
                                            }
                                        }
                                    }
                                    delegate$ui_release = delegate$ui_release.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    node2 = node2.getChild();
                }
            }
        }
        return false;
    }
}
