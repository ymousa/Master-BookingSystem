package Helpers;

import Datenobjekte.Booking;
import Datenobjekte.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper {
    Connection connection= null;
    String databaseName= "databaseName=Reservierungssystem;";
    String user= "user=Youhanna;";
    String password= "password=Telekom";

    private final String SELECT_SQL_BOOKING = "Select * from Booking ";
    private final String SELECT_SQL_USER = "Select * from Login_User ";
    private final String SELECT_SQL_PASSWORD = "Select * from Password ";
    private final String SELECT_SQL_ROOM = "Select * from Room ";
    private final String INSERT_SQL_BOOKING_VALUE = "Insert into Booking Values (?, ?, ?, ?, ?, ?, ?, ?) ";
    private final String INSERT_SQL_USER_VALUE = "Insert into Login_User Values (?, ?, ?, ?, ?) ";
    private final String INSERT_SQL_PASSWORD_VALUE = "Insert into Password Values (?, ?) ";
    private final String SELECT_SQL_JOIN_USER_ON_PASSWORD = "select * from Login_User u join Password  p on u.PasswordID= p.PasswordID ";
    private final String SELECT_SQL_JOIN_BOOKING_ON_USER_ON_ROOM =
            "select b.BookingID, u.Username, r.RoomNr, b.BookingDate,  b.BookingName, b.BookingTime, b.Duration, b.Notes " +
            "from Booking b join Login_User u " +
            "on b.userID= u.UserID   " +
            "join  Room r " +
            "on r.RoomID= b.RoomID ";
    private final String DELETE_SQL_BOOKING= "Delete from Booking where BookingID= ";



    public DatabaseHelper(){
        try {
            connection= DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;" +
                    databaseName+ user+ password);
            System.out.println("Connected to Microsoft SQL Server");
        } catch (SQLException throwables) {
            System.out.println("Oops, there's an error: ");
            throwables.printStackTrace();
        }
    }

    //execute SQL statement and return a Resulset
    public ResultSet executeSQL(String sql){
        PreparedStatement ps= null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet resultSet= ps.executeQuery();
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error by executing");
        }
        return null;

    }

    //only executing a SQL statement no returns
    public void updateSQL(String sql){
        Statement statement= null;
        try {
            statement = connection.createStatement();
            int rowsAffected    = statement.executeUpdate(sql);
            System.out.println("update success");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error by executing");
        }
    }

    public int getSpecificID(String sql, String whereStatement, String columnIndex){
        int specificID;
        try {
            ResultSet rs= executeSQL(sql+whereStatement);
            while(rs.next()){
                return specificID= rs.getInt(columnIndex);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int getLastID(String sql) {
        ResultSet rs = executeSQL(sql);
        int index = 0;
        try {
            while (rs.next()) {
                if (rs.getInt(1) > index) {
                    index = rs.getInt(1);
                }
            }
            rs.close();
            return index+1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public HashMap<String, User> readUsers(){
        HashMap<String, User> allUsers= new HashMap<String, User>();
        try {
            ResultSet rs= executeSQL(SELECT_SQL_JOIN_USER_ON_PASSWORD);
            while(rs.next()){
                allUsers.put(rs.getString("Username"),
                        new User(rs.getString("Firstname"), rs.getString("Lastname"),
                                rs.getString("Username"), rs.getString("Password")));
            }
            return allUsers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Booking> readBookings(){
        ArrayList<Booking> allBookings= new ArrayList<Booking>();
        try {
            PreparedStatement ps= connection.prepareStatement(SELECT_SQL_JOIN_BOOKING_ON_USER_ON_ROOM);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                allBookings.add(new Booking(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getDate(4).toLocalDate(), rs.getString(5),
                        rs.getTime(6).toLocalTime(), rs.getInt(7), rs.getString(8)));
            }
            return allBookings;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void insertUserValue(int UserID, String username, int passwordID, String firstname, String lastname) {
        PreparedStatement prep= null;
        try {
            prep = connection.prepareStatement(INSERT_SQL_USER_VALUE);
            prep.setInt(1, UserID);
            prep.setString(2, username);
            prep.setInt(3, passwordID);
            prep.setString(4, firstname);
            prep.setString(5, lastname);

            prep.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                prep.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private void insertPasswordValue(int passwordID, String password) {
        PreparedStatement prep= null;
        try {
            prep = connection.prepareStatement(INSERT_SQL_PASSWORD_VALUE);
            prep.setInt(1, passwordID);
            prep.setString(2, password);
            prep.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                prep.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private void insertBookingValue(int bookingID, int userID, int roomID, Date bookingDate,
                                    Time bookingTime, String bookingName, int duration, String notes) {
        PreparedStatement prep= null;
        try {
            prep = connection.prepareStatement(INSERT_SQL_BOOKING_VALUE);
            prep.setInt(1, bookingID);
            prep.setInt(2, userID);
            prep.setInt(3, roomID);
            prep.setDate(4, bookingDate);
            prep.setTime(5, bookingTime);
            prep.setString(6, bookingName);
            prep.setInt(7, duration);
            prep.setString(8, notes);
            prep.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                prep.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void deleteBookingFromSQL(Booking selectedBooking){
        updateSQL(DELETE_SQL_BOOKING+ selectedBooking.getiBookingNr());
    }

    public void writeUser(User newUser){
        int indexUser = getLastID(SELECT_SQL_USER);
        int indexPassword= getLastID(SELECT_SQL_PASSWORD);

        insertPasswordValue(indexPassword, newUser.getsPass());
        insertUserValue(indexUser, newUser.getsUsername(), indexPassword, newUser.getsFirstName(), newUser.getsLastName());

    }

    public void writeBooking(Booking newBooking){
        int indexBooking= getLastID(SELECT_SQL_BOOKING);
        int indexUser= getSpecificID(SELECT_SQL_USER, String.format(" where Username= '%s'", newBooking.getsUser()), "UserID" );
        int indexRoom= getSpecificID(SELECT_SQL_ROOM, " where roomNr= "+ newBooking.getsRoomNr(), "RoomID");

        insertBookingValue(indexBooking, indexUser, indexRoom,
                newBooking.localDateTimeToDate(newBooking.getlBookingDateAndTime()),
                newBooking.localDateTimeToTime(newBooking.getlBookingDateAndTime()),
                newBooking.getsBookingName(), newBooking.getiDuration(),
                newBooking.getsNots());

    }

}
