package JDBC.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import JDBC.DBConnection;
import JDBC.dao.*;
import JDBC.model.Category;

public class CategoryDAOImpl extends DBConnection implements CategoryDAO {

    @Override
    public void insert(Category category) {
        String sql = "INSERT INTO category(cate_name, icons) VALUES (?, ?)";
        try (Connection con = super.getConnectionW();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getIcon());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category get(int id) {
        String sql = "SELECT * FROM category WHERE cate_id = ?";
        try (Connection con = super.getConnectionW();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // truyền id vào câu lệnh SQL

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // nếu tìm thấy
                    Category category = new Category();
                    category.setId(rs.getInt("cate_id"));
                    category.setName(rs.getString("cate_name"));
                    category.setIcon(rs.getString("icons"));
                    return category;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // nếu không tìm thấy
    }

    @Override
    public List<Category> search(String keyword) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE cate_name LIKE ?";
        try (Connection con = super.getConnectionW();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%"); // LIKE '%keyword%'
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("cate_id"));
                    category.setName(rs.getString("cate_name"));
                    category.setIcon(rs.getString("icons"));
                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void edit(Category category) {
        String sql = "UPDATE category SET cate_name = ?, icons = ? WHERE cate_id = ?";
        try (Connection con = super.getConnectionW();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM category WHERE cate_id = ?";
        try (Connection con = super.getConnectionW();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try (Connection con = super.getConnectionW();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("cate_id"));
                category.setName(rs.getString("cate_name"));
                category.setIcon(rs.getString("icons"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

	@Override
	public Category get(String name) {
		// TODO Auto-generated method stub
		return null;
	}


}
