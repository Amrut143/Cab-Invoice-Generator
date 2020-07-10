package com.bridgelabz.cabinvoiceservice.service;

import com.bridgelabz.cabinvoiceservice.utility.CabCategory;
import com.bridgelabz.cabinvoiceservice.utility.InvoiceSummary;
import com.bridgelabz.cabinvoiceservice.utility.Ride;
import com.bridgelabz.cabinvoiceservice.utility.RideRepository;

public class InvoiceGenerator {

    private static final int COST_PER_TIME_FOR_NORMAL = 1;
    private static final int COST_PER_TIME_FOR_PREMIUM = 2;
    private static final double MINIMUM_COST_PER_KILOMETER_NORMAL = 10;
    private static final double MINIMUM_FARE_NORMAL = 5.0;
    private static final double MINIMUM_COST_PER_KILOMETER_PREMIUM = 15;
    private static final double MINIMUM_FARE_PREMIUM = 20.0;
    private CabCategory cabCategory;

    RideRepository rideRepository;

    public InvoiceGenerator(CabCategory cabCategory) {
        this.rideRepository = new RideRepository();
        this.cabCategory = cabCategory;
    }

    /**
     * Function to calculate total fare
     * @param distance
     * @param time
     * @return
     */
    public double calculateFare(double distance, int time) {

        if (CabCategory.NORMAL.equals(this.cabCategory)) {
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER_NORMAL + time * COST_PER_TIME_FOR_NORMAL;
            if (totalFare < MINIMUM_FARE_NORMAL)
                return MINIMUM_FARE_NORMAL;
            return totalFare;


        }
        if (CabCategory.PREMIUM.equals(this.cabCategory)) {
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER_PREMIUM + time * COST_PER_TIME_FOR_PREMIUM;
            if (totalFare < MINIMUM_FARE_PREMIUM)
                return MINIMUM_FARE_PREMIUM;
            return totalFare;
        }
        return 0.0;
    }

    /**
     *
     * @param rides
     * @return
     */
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return  new InvoiceSummary(rides.length, totalFare);
    }

    /**
     *
     * @param userId
     * @param rides
     */
    public void addRide(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    /**
     *
     * @param userId
     * @return
     */
    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
