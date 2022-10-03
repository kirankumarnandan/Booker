package com.api.pojo;

public class BookingDTO
{
    private com.api.pojo.BookingDetailsDTO booking;

    private String bookingid;

    public com.api.pojo.BookingDetailsDTO getBooking ()
    {
        return booking;
    }

    public void setBooking ( com.api.pojo.BookingDetailsDTO booking)
    {
        this.booking = booking;
    }

    public String getBookingid ()
    {
        return bookingid;
    }

    public void setBookingid (String bookingid)
    {
        this.bookingid = bookingid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [booking = "+booking+", bookingid = "+bookingid+"]";
    }
}