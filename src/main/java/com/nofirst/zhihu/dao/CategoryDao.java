package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Category;

public interface CategoryDao {

    Category selectBySlug(String slug);
}
