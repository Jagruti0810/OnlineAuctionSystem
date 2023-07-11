package com.narola.onlineauctionsystem.dao;

import com.narola.onlineauctionsystem.dto.CredentialDTO;
import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.dto.SignUpDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    public User addUserDetails(SignUpDTO signUpDTO) throws DaoException {
        User user = null;
        try {
            String query = "insert into users (user_name, mobile_no, email, address, password, verification_code, is_verified,  role_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, signUpDTO.getUsername());
            stmt.setString(2, signUpDTO.getMobileNo());
            stmt.setString(3, signUpDTO.getEmail());
            stmt.setString(4, signUpDTO.getAddress());
            stmt.setString(5, signUpDTO.getPassword());
            stmt.setString(6, signUpDTO.getVerificationCode());
            stmt.setBoolean(7, signUpDTO.isVerified());
            stmt.setInt(8, Integer.parseInt(signUpDTO.getRoleId()));
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                signUpDTO.setUserId(resultSet.getInt(1));
            }
//            user = new User();
//            user.setUserId(signUpDTO.getUserId());
//            user.setUsername(signUpDTO.getUsername());
//            user.setMobileNo(signUpDTO.getMobileNo());
//            user.setEmail(signUpDTO.getEmail());
//            user.setAddress(signUpDTO.getAddress());
//            user.setPassword(signUpDTO.getPassword());
//            user.setVerificationCode(signUpDTO.getVerificationCode());
//            user.setVerified(signUpDTO.isVerified());
//            user.setRoleId(Integer.parseInt(signUpDTO.getRoleId()));
        } catch (SQLException se) {
            throw new DaoException("Something went wrong", se);
        } catch (Exception e) {
            throw new DaoException("Something went wrong");
        }
        return user;
    }

    public User getUserCredentials(CredentialDTO credentialDTO) {
        User user = null;
        try {
            String query3 = "select * from users where email = ? AND password = ?";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query3);
            ps.setString(1, credentialDTO.getEmail());
            ps.setString(2, credentialDTO.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setMobileNo(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setAddress(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setVerificationCode(rs.getString(7));
                user.setVerified(rs.getBoolean(8));
                user.setRoleId(rs.getInt(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean checkUserVerified(Boolean isVerified, String email) throws DaoException {
        try {
            String query = "UPDATE users SET is_verified = ? where email=?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setBoolean(1, isVerified);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("oops something went wrong", e);
        } catch (Exception e) {
            throw new DaoException("oops something went wrong", e);
        }
        return true;
    }

    public User getUserById(String userId) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setMobileNo(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setAddress(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setVerificationCode(resultSet.getString(7));
                user.setVerified(resultSet.getBoolean(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public User getUseProfileDetails(int userId) {
        User user = null;
        try {
            String query3 = "select * from users where user_id = ?";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query3);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setMobileNo(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setAddress(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setVerificationCode(rs.getString(7));
                user.setVerified(rs.getBoolean(8));
                user.setRoleId(rs.getInt(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public void editUserProfile(SignUpDTO signUpDTO) {
        try {
            String sql1 = "UPDATE users SET user_name = ?, mobile_no = ?, email = ?, address = ? where user_id = ?";
            PreparedStatement ps1 = DbConnection.getInstance().getConnection().prepareStatement(sql1);
            ps1.setString(1, signUpDTO.getUsername());
            ps1.setString(2, signUpDTO.getMobileNo());
            ps1.setString(3, signUpDTO.getEmail());
            ps1.setString(4, signUpDTO.getAddress());
            ps1.setInt(5, signUpDTO.getUserId());
            ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateVerificationCode(String email, String verificationCode) {
        try {
            String sql = "UPDATE users SET verification_code = ? WHERE email = ?";
            PreparedStatement ps1 = DbConnection.getInstance().getConnection().prepareStatement(sql);
            ps1.setString(1, verificationCode);
            ps1.setString(2, email);
            ps1.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}






