package com.horizonlabs.demoprojectjava.utility;

import com.horizonlabs.demoprojectjava.R;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Rajeev Ranjan -  ABPB on 19-08-2019.
 */
public class Util {
    public static String getFormattedDouble(double d) {
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(d);
    }

    public static int getFixedBackground(int position) {
        Random rand = new Random();
        int n = rand.nextInt(2);
        switch (position % 3) {
            case 0:
                return R.drawable.circle_green;
            case 1:
                return R.drawable.circle_blue;
            case 2:
                return R.drawable.circle_red;
            default:
                return R.drawable.circle_green;
        }
    }

    public static String getShortName(String name) {
        String[] s = name.split("\\s+");
        String shortName = "";
        for (int i = 0; i < s.length; i++) {
            shortName = shortName + s[i].charAt(0);
        }
        return shortName;
    }
}
