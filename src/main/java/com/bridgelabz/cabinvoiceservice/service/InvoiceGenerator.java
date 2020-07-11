package com.bridgelabz.cabinvoiceservice.service;

import com.bridgelabz.cabinvoiceservice.utility.CabCategory;
import com.bridgelabz.cabinvoiceservice.model.InvoiceSummary;
import com.bridgelabz.cabinvoiceservice.model.Ride;
import com.bridgelabz.cabinvoiceservice.utility.RideRepository;

public class InvoiceGenerator {

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

        return cabCategory.calculateFare(distance,time);
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
