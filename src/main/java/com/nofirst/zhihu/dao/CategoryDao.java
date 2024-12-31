package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Category;

/**
 * The interface Category dao.
 */
public interface CategoryDao {

    /**
     * Select by slug category.
     *
     * @param slug the slug
     * @return the category
     */
    Category selectBySlug(String slug);
}
