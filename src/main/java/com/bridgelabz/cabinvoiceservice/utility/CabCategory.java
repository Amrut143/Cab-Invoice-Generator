package com.bridgelabz.cabinvoiceservice.utility;

public enum CabCategory {

    NORMAL(1,10.0,5.0),
    PREMIUM(2,15.0,20.0);

    private  final int COST_PER_TIME;
    private final double MINIMUM_COST_PER_KILOMETER;
    private final double MINIMUM_FARE;

    /**
     *
     * @param COST_PER_TIME
     * @param MINIMUM_COST_PER_KILOMETER
     * @param MINIMUM_FARE
     */
    CabCategory(int COST_PER_TIME, double MINIMUM_COST_PER_KILOMETER, Double MINIMUM_FARE) {
        this.COST_PER_TIME = COST_PER_TIME;
        this.MINIMUM_COST_PER_KILOMETER = MINIMUM_COST_PER_KILOMETER;
        this.MINIMUM_FARE = MINIMUM_FARE;
    }

    /**
     *
     * @param distance
     * @param time
     * @return
     */
    public double calculateFare(double distance, int time) {
        Double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }
}
