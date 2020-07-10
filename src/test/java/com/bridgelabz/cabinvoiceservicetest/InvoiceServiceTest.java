package com.bridgelabz.cabinvoiceservicetest;

import com.bridgelabz.cabinvoiceservice.service.InvoiceGenerator;
import com.bridgelabz.cabinvoiceservice.utility.CabCategory;
import com.bridgelabz.cabinvoiceservice.utility.InvoiceSummary;
import com.bridgelabz.cabinvoiceservice.utility.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceGenerator normalInvoiceGenerator;
    InvoiceGenerator premiumInvoiceGenerator;

    @Before
    public void setUp() {
        normalInvoiceGenerator = new InvoiceGenerator(CabCategory.NORMAL);
        premiumInvoiceGenerator = new InvoiceGenerator(CabCategory.PREMIUM);
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = normalInvoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double totalFare = normalInvoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, totalFare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary = normalInvoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = "amp@.com";
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        normalInvoiceGenerator.addRide(userId, rides);
        InvoiceSummary summary = normalInvoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenDistanceAndTimeForPremium_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = premiumInvoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(40, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTimeForPremium_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = premiumInvoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(20, fare, 0.0);
    }

    @Test
    public void givenMultipleRidesForPremium_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary = premiumInvoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRidesForPremium_ShouldReturnInvoiceSummary() {
        String userId = "amp@.com";
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        premiumInvoiceGenerator.addRide(userId, rides);
        InvoiceSummary summary = premiumInvoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
}
