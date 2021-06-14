package com.capstonegroup2.backend.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MeritBank {


    public static final String DBA_CHECKING_INTEREST_RATE = "0.0005";
    public static final String PERSONAL_CHECKING_INTEREST_RATE = ".0003";
    public static final String IRA_REGULAR_INTEREST_RATE = "0.65";
    public static final String IRA_ROLLOVER_INTEREST_RATE = "0.08";
    public static final String IRA_ROTH_INTEREST_RATE = "0.08";
    public static final String SAVINGS_ACCOUNT_INTEREST_RATE = "0.01";
    public static final List<CDOffering> CDOFFERINGS = currentOfferings();
    public static final double TRANSACTION_LIMIT = 20000.00;


    private static List<CDOffering> currentOfferings() {

        List<CDOffering> offerings = new ArrayList<>();

        CDOffering offer1 = new CDOffering(1, "0.018");
        CDOffering offer2 = new CDOffering(3, "0.02");
        CDOffering offer3 = new CDOffering(5, "0.023");
        CDOffering offer4 = new CDOffering(10, "0.026");

        offerings.add(offer1);
        offerings.add(offer2);
        offerings.add(offer3);
        offerings.add(offer4);

        return offerings;
    }

    // TODO Implement End Point for Users to find best CDOffering
    public static CDOffering getBestCDOfferingByFutureValue(BigDecimal depositAmount) {

        CDOffering bestCDOffering = null;
        BigDecimal currentOfferingValue;
        BigDecimal bestOfferingValue = new BigDecimal("0");

        for (CDOffering offering : CDOFFERINGS) {
            BigDecimal currentInterestRate = new BigDecimal(offering.getInterestRate());
            currentOfferingValue = BankAccount.futureValue(depositAmount, currentInterestRate, offering.getTerm());

            if (Double.parseDouble(String.valueOf(currentOfferingValue)) >
                    Double.parseDouble(String.valueOf(bestOfferingValue))) {
                bestOfferingValue = currentOfferingValue;
                bestCDOffering = offering;
            }
        }
        return bestCDOffering;
    }


}
