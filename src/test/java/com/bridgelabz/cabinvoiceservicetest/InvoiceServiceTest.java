package com.bridgelabz.cabinvoiceservicetest;

import com.bridgelabz.cabinvoiceservice.InvoiceGenerator;
import com.bridgelabz.cabinvoiceservice.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceGenerator invoiceGenerator;

    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double totalFare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, totalFare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        double fare = invoiceGenerator.calculateFare(rides);
        Assert.assertEquals(30, fare, 0.0);
    }
}
