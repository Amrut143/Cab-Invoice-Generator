package com.bridgelabz.cabinvoiceservice.service;

import com.bridgelabz.cabinvoiceservice.utility.InvoiceSummary;
import com.bridgelabz.cabinvoiceservice.utility.Ride;
import com.bridgelabz.cabinvoiceservice.utility.RideRepository;

public class InvoiceGenerator {
    private static final double MINIMUM_COST_PER_KM = 10;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5;

    RideRepository rideRepository;

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    /**
     * Function to calculate total fare
     * @param distance
     * @param time
     * @return
     */
    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
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
