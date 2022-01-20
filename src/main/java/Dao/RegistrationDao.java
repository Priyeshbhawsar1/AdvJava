package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Registration;

import Bean.Party;
import Bean.Login;

import Connection.DBConnection;

public class RegistrationDao {
    public static int incrementId() {
        int pk = 0;
        Connection conn;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select Max(partyId) as pk from adv.Party");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pk + 1;

    }

    public static boolean saverUser(Party party, Login login)
            throws SQLException, ClassNotFoundException {
        boolean f = false;
        int partyId = incrementId();
        try {
            Connection con = DBConnection.getConnection();

            String qr = "insert into adv.Party " + "(partyId,firstName,lastName,city,zip,state,country,phone)" + "values(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(qr);
            ps.setInt(1, partyId);
            ps.setString(2, party.getFirstName());
            ps.setString(3, party.getLastName());
            ps.setString(4, party.getCity());
            ps.setInt(5, party.getZip());
            ps.setString(6, party.getState());
            ps.setString(7, party.getCountry());
            ps.setString(8, party.getPhone());


            ps.executeUpdate();
            ps.close();

            String qr1 = "insert into adv.UserLogin " + "(userLoginId,password,partyId)" + "values(?,?,?)";
            PreparedStatement ps1 = con.prepareStatement(qr1);
            ps1.setString(1, login.getEmail());
            ps1.setString(2, login.getPassword());
            ps1.setInt(3, partyId);

            ps1.executeUpdate();
            ps1.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
