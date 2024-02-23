package com.PaymentDetails;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.BookingDetails.BookingDetails;

public interface Payment {
	void booking( Connection conn, BookingDetails Bookdata) throws SQLException;

}
