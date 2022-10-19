package com.joelbland.tipcalculator;

public class TipCalculator {
    private float tip;
    private float bill;
    private float guests;

    public TipCalculator( float newTip, float newBill, float newGuests) {
        setTip( newTip );
        setBill( newBill );
        setGuests( newGuests );
    }

    public float getTip( ) {
        return tip;
    }

    public float getBill( ) {
        return bill;
    }

    public float getGuests( ) {
        return guests;
    }

    public void setTip( float newTip ) {
        if( newTip > 0 )
            tip = newTip;
    }

    public void setBill( float newBill ) {
        if( newBill > 0 )
            bill = newBill;
    }

    public void setGuests( float newGuests ) {
        if( newGuests > 0 )
            guests = newGuests;
    }

    public float tipAmount( ) {
        return bill * tip;
    }

    public float totalAmount( ) {
        return bill + tipAmount( );
    }

    public float totalPerGuest( ) {
        return totalAmount() / getGuests();
    }

    public float tipPerGuest( ) {
        return tipAmount() / getGuests();
    }
}