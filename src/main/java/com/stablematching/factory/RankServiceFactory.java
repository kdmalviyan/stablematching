package com.stablematching.factory;

import com.stablematching.constants.Constant;
import com.stablematching.services.RankOneService;
import com.stablematching.services.RankService;
import com.stablematching.services.RankThreeService;
import com.stablematching.services.RankTwoService;

public class RankServiceFactory {

    /** @param id
     * @return */
    public static RankService getRankService(Integer id) {
        RankService rankService = null;
        if (id.equals(Constant.NUMBERS.ONE)) {
            rankService = new RankOneService();
        } else if (id.equals(Constant.NUMBERS.TWO)) {
            rankService = new RankTwoService();
        } else if (id.equals(Constant.NUMBERS.THREE)) {
            rankService = new RankThreeService();
        }
        return rankService;
    }
}
