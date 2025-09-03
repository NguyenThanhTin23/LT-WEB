package JDBC.service.impl;

import java.io.File;
import java.util.List;

import JDBC.dao.CategoryDAO;
import JDBC.dao.impl.CategoryDAOImpl;
import JDBC.model.Category;
import JDBC.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    CategoryDAO categoryDao = new CategoryDAOImpl();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryDao.get(id);
    }

    @Override
    public void edit(Category newCategory) {
        Category oldCategory = categoryDao.get(newCategory.getId());
        if (oldCategory != null) {
            oldCategory.setName(newCategory.getName());

            if (newCategory.getIcon() != null) {
                // Xóa ảnh cũ
                String fileName = oldCategory.getIcon();
                final String dir = "D:\\upload";
                File file = new File(dir + "\\category\\" + fileName);
                if (file.exists()) {
                    file.delete();
                }
                oldCategory.setIcon(newCategory.getIcon());
            }

            categoryDao.edit(oldCategory);
        }
    }

    @Override
    public Category get(String name) {
        return categoryDao.get(name); // DAO phải có method get(String name)
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public List<Category> search(String catename) {
        return categoryDao.search(catename);
    }
}
