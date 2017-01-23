package com.stablematching.helper;

import java.util.List;

import com.stablematching.model.Rank;

public class RankHelper {
    public static boolean canSetRank(List<Rank> ranks, String value) {
        boolean flag = true;
        for (Rank rank : ranks) {
            String name = rank.getName();
            if (name.equals(value)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /** @param tenant
     * @param rank */
    public static void createRank(String name, Rank rank, Integer rankValue) {
        rank.setName(name);
        rank.setValue(rankValue);
    }

    public static Rank getRank(Integer one, List<Rank> ranks) {
        for (Rank rank : ranks) {
            if (rank.getValue().equals(one)) {
                return rank;
            }
        }
        return null;
    }
}
