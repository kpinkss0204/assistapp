package androidx.camera.extensions.internal.compat.quirk;

import android.os.Build;
import android.util.Pair;
import androidx.camera.core.impl.Quirk;
import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ImageAnalysisUnavailableQuirk implements Quirk {
    private static final Set<Pair<String, String>> KNOWN_DEVICES = new HashSet(Arrays.asList(Pair.create("samsung", "dm3q"), Pair.create("samsung", "q2q"), Pair.create("samsung", "a52sxq"), Pair.create("samsung", "b0q")));
    private final Set<Pair<String, Integer>> mUnavailableCombinations;

    ImageAnalysisUnavailableQuirk() {
        HashSet hashSet = new HashSet();
        this.mUnavailableCombinations = hashSet;
        if (Build.BRAND.equalsIgnoreCase("SAMSUNG") && Build.DEVICE.equalsIgnoreCase("dm3q")) {
            hashSet.addAll(Arrays.asList(Pair.create("1", 1), Pair.create("1", 4), Pair.create(ExifInterface.GPS_MEASUREMENT_3D, 1), Pair.create(ExifInterface.GPS_MEASUREMENT_3D, 4)));
            return;
        }
        if (Build.BRAND.equalsIgnoreCase("SAMSUNG") && Build.DEVICE.equalsIgnoreCase("q2q")) {
            hashSet.addAll(Arrays.asList(Pair.create("0", 1), Pair.create("0", 4)));
            return;
        }
        if (Build.BRAND.equalsIgnoreCase("SAMSUNG") && Build.DEVICE.equalsIgnoreCase("a52sxq")) {
            hashSet.addAll(Arrays.asList(Pair.create("0", 1), Pair.create("0", 4)));
        } else if (Build.BRAND.equalsIgnoreCase("SAMSUNG") && Build.DEVICE.equalsIgnoreCase("b0q")) {
            hashSet.addAll(Arrays.asList(Pair.create(ExifInterface.GPS_MEASUREMENT_3D, 1), Pair.create(ExifInterface.GPS_MEASUREMENT_3D, 4)));
        }
    }

    static boolean load() {
        return KNOWN_DEVICES.contains(Pair.create(Build.BRAND.toLowerCase(Locale.US), Build.DEVICE.toLowerCase(Locale.US)));
    }

    public boolean isUnavailable(String str, int i) {
        return this.mUnavailableCombinations.contains(Pair.create(str, Integer.valueOf(i)));
    }
}
