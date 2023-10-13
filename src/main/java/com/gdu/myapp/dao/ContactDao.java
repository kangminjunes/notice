package com.gdu.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.myapp.dto.ContactDto;

@Repository
public class ContactDao {

  @Autowired
  private JdbcConnection jdbcConnection;

  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;

  // Retrieve the list
  public List<ContactDto> selectList() {
    List<ContactDto> list = new ArrayList<>();
    try {
      con = jdbcConnection.getConnection();
      String sql = "SELECT NOTICE_NO, GUBUN, TITLE, CONTENT, CREATED_AT FROM NOTICE_T ORDER BY NOTICE_NO ASC";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        ContactDto contactDto = new ContactDto();
        contactDto.setNotice_no(rs.getInt("NOTICE_NO"));
        contactDto.setGubun(rs.getInt("GUBUN"));
        contactDto.setTitle(rs.getString("TITLE"));
        contactDto.setContent(rs.getString("CONTENT"));
        list.add(contactDto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, rs);
    }
    return list;
  }

  // Insert 
  public int insert(ContactDto contactDto) {
    int insertCount = 0;
    try {
      con = jdbcConnection.getConnection();
      String sql = "INSERT INTO NOTICE_T(NOTICE_NO, GUBUN, TITLE, CONTENT) VALUES(NOTICE_SEQ.NEXTVAL, ?, ?, ?)";
      ps = con.prepareStatement(sql);
      ps.setInt(1, contactDto.getGubun());
      ps.setString(2, contactDto.getTitle());
      ps.setString(3, contactDto.getContent());
      insertCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, null);
    }
    return insertCount;
  }

  // Update 
  public int update(ContactDto contactDto) {
    int updateCount = 0;
    try {
      con = jdbcConnection.getConnection();
      String sql = "UPDATE NOTICE_T SET GUBUN = ?, TITLE = ?, CONTENT = ? WHERE NOTICE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, contactDto.getGubun());
      ps.setString(2, contactDto.getTitle());
      ps.setString(3, contactDto.getContent());
      ps.setInt(4, contactDto.getNotice_no());
      updateCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, null);
    }
    return updateCount;
  }

  // Delete
  public int delete(int notice_no) {
    int deleteCount = 0;
    try {
      con = jdbcConnection.getConnection();
      String sql = "DELETE FROM NOTICE_T WHERE NOTICE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, notice_no);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, null);
    }
    return deleteCount;
  }

  // Retrieve
  public ContactDto selectContactByNo(int notice_no) {
    ContactDto contactDto = null;
    try {
      con = jdbcConnection.getConnection();
      String sql = "SELECT NOTICE_NO ,GUBUN, TITLE, CONTENT, CREATED_AT FROM NOTICE_T WHERE NOTICE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, notice_no);
      rs = ps.executeQuery();
      if (rs.next()) {
        contactDto = new ContactDto();
        contactDto.setNotice_no(rs.getInt("NOTICE_NO"));
        contactDto.setGubun(rs.getInt("GUBUN"));
        contactDto.setTitle(rs.getString("TITLE"));
        contactDto.setContent(rs.getString("CONTENT"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcConnection.close(con, ps, rs);
    }
    return contactDto;
  }
}
