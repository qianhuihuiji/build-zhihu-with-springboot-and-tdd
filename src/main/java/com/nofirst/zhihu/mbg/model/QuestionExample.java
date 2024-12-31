package com.nofirst.zhihu.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Question example.
 */
public class QuestionExample {
    /**
     * The Order by clause.
     */
    protected String orderByClause;

    /**
     * The Distinct.
     */
    protected boolean distinct;

    /**
     * The Ored criteria.
     */
    protected List<Criteria> oredCriteria;

    /**
     * Instantiates a new Question example.
     */
    public QuestionExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * Sets order by clause.
     *
     * @param orderByClause the order by clause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * Gets order by clause.
     *
     * @return the order by clause
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * Sets distinct.
     *
     * @param distinct the distinct
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * Is distinct boolean.
     *
     * @return the boolean
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * Gets ored criteria.
     *
     * @return the ored criteria
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * Or.
     *
     * @param criteria the criteria
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * Or criteria.
     *
     * @return the criteria
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * Create criteria criteria.
     *
     * @return the criteria
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * Create criteria internal criteria.
     *
     * @return the criteria
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * Clear.
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * The type Generated criteria.
     */
    protected abstract static class GeneratedCriteria {
        /**
         * The Criteria.
         */
        protected List<Criterion> criteria;

        /**
         * Instantiates a new Generated criteria.
         */
        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        /**
         * Is valid boolean.
         *
         * @return the boolean
         */
        public boolean isValid() {
            return criteria.size() > 0;
        }

        /**
         * Gets all criteria.
         *
         * @return the all criteria
         */
        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        /**
         * Gets criteria.
         *
         * @return the criteria
         */
        public List<Criterion> getCriteria() {
            return criteria;
        }

        /**
         * Add criterion.
         *
         * @param condition the condition
         */
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        /**
         * Add criterion.
         *
         * @param condition the condition
         * @param value     the value
         * @param property  the property
         */
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /**
         * Add criterion.
         *
         * @param condition the condition
         * @param value1    the value 1
         * @param value2    the value 2
         * @param property  the property
         */
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        /**
         * And id is null criteria.
         *
         * @return the criteria
         */
        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        /**
         * And id is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        /**
         * And id equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**
         * And id not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         * And id greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         * And id greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         * And id less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         * And id less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         * And id in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        /**
         * And id not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        /**
         * And id between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        /**
         * And id not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        /**
         * And user id is null criteria.
         *
         * @return the criteria
         */
        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        /**
         * And user id is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        /**
         * And user id equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        /**
         * And user id not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        /**
         * And user id greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        /**
         * And user id greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        /**
         * And user id less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        /**
         * And user id less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        /**
         * And user id in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        /**
         * And user id not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        /**
         * And user id between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        /**
         * And user id not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        /**
         * And title is null criteria.
         *
         * @return the criteria
         */
        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        /**
         * And title is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        /**
         * And title equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        /**
         * And title not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        /**
         * And title greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        /**
         * And title greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        /**
         * And title less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        /**
         * And title less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        /**
         * And title like criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        /**
         * And title not like criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        /**
         * And title in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        /**
         * And title not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        /**
         * And title between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        /**
         * And title not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        /**
         * And published at is null criteria.
         *
         * @return the criteria
         */
        public Criteria andPublishedAtIsNull() {
            addCriterion("published_at is null");
            return (Criteria) this;
        }

        /**
         * And published at is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andPublishedAtIsNotNull() {
            addCriterion("published_at is not null");
            return (Criteria) this;
        }

        /**
         * And published at equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andPublishedAtEqualTo(Date value) {
            addCriterion("published_at =", value, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andPublishedAtNotEqualTo(Date value) {
            addCriterion("published_at <>", value, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andPublishedAtGreaterThan(Date value) {
            addCriterion("published_at >", value, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andPublishedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("published_at >=", value, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andPublishedAtLessThan(Date value) {
            addCriterion("published_at <", value, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andPublishedAtLessThanOrEqualTo(Date value) {
            addCriterion("published_at <=", value, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andPublishedAtIn(List<Date> values) {
            addCriterion("published_at in", values, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andPublishedAtNotIn(List<Date> values) {
            addCriterion("published_at not in", values, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andPublishedAtBetween(Date value1, Date value2) {
            addCriterion("published_at between", value1, value2, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And published at not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andPublishedAtNotBetween(Date value1, Date value2) {
            addCriterion("published_at not between", value1, value2, "publishedAt");
            return (Criteria) this;
        }

        /**
         * And best answer id is null criteria.
         *
         * @return the criteria
         */
        public Criteria andBestAnswerIdIsNull() {
            addCriterion("best_answer_id is null");
            return (Criteria) this;
        }

        /**
         * And best answer id is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andBestAnswerIdIsNotNull() {
            addCriterion("best_answer_id is not null");
            return (Criteria) this;
        }

        /**
         * And best answer id equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andBestAnswerIdEqualTo(Integer value) {
            addCriterion("best_answer_id =", value, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andBestAnswerIdNotEqualTo(Integer value) {
            addCriterion("best_answer_id <>", value, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andBestAnswerIdGreaterThan(Integer value) {
            addCriterion("best_answer_id >", value, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andBestAnswerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("best_answer_id >=", value, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andBestAnswerIdLessThan(Integer value) {
            addCriterion("best_answer_id <", value, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andBestAnswerIdLessThanOrEqualTo(Integer value) {
            addCriterion("best_answer_id <=", value, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andBestAnswerIdIn(List<Integer> values) {
            addCriterion("best_answer_id in", values, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andBestAnswerIdNotIn(List<Integer> values) {
            addCriterion("best_answer_id not in", values, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andBestAnswerIdBetween(Integer value1, Integer value2) {
            addCriterion("best_answer_id between", value1, value2, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And best answer id not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andBestAnswerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("best_answer_id not between", value1, value2, "bestAnswerId");
            return (Criteria) this;
        }

        /**
         * And category id is null criteria.
         *
         * @return the criteria
         */
        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        /**
         * And category id is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        /**
         * And category id equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        /**
         * And category id not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        /**
         * And answers count is null criteria.
         *
         * @return the criteria
         */
        public Criteria andAnswersCountIsNull() {
            addCriterion("answers_count is null");
            return (Criteria) this;
        }

        /**
         * And answers count is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andAnswersCountIsNotNull() {
            addCriterion("answers_count is not null");
            return (Criteria) this;
        }

        /**
         * And answers count equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andAnswersCountEqualTo(Integer value) {
            addCriterion("answers_count =", value, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andAnswersCountNotEqualTo(Integer value) {
            addCriterion("answers_count <>", value, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andAnswersCountGreaterThan(Integer value) {
            addCriterion("answers_count >", value, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andAnswersCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("answers_count >=", value, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andAnswersCountLessThan(Integer value) {
            addCriterion("answers_count <", value, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andAnswersCountLessThanOrEqualTo(Integer value) {
            addCriterion("answers_count <=", value, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andAnswersCountIn(List<Integer> values) {
            addCriterion("answers_count in", values, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andAnswersCountNotIn(List<Integer> values) {
            addCriterion("answers_count not in", values, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andAnswersCountBetween(Integer value1, Integer value2) {
            addCriterion("answers_count between", value1, value2, "answersCount");
            return (Criteria) this;
        }

        /**
         * And answers count not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andAnswersCountNotBetween(Integer value1, Integer value2) {
            addCriterion("answers_count not between", value1, value2, "answersCount");
            return (Criteria) this;
        }

        /**
         * And slug is null criteria.
         *
         * @return the criteria
         */
        public Criteria andSlugIsNull() {
            addCriterion("slug is null");
            return (Criteria) this;
        }

        /**
         * And slug is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andSlugIsNotNull() {
            addCriterion("slug is not null");
            return (Criteria) this;
        }

        /**
         * And slug equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andSlugEqualTo(String value) {
            addCriterion("slug =", value, "slug");
            return (Criteria) this;
        }

        /**
         * And slug not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andSlugNotEqualTo(String value) {
            addCriterion("slug <>", value, "slug");
            return (Criteria) this;
        }

        /**
         * And slug greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andSlugGreaterThan(String value) {
            addCriterion("slug >", value, "slug");
            return (Criteria) this;
        }

        /**
         * And slug greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andSlugGreaterThanOrEqualTo(String value) {
            addCriterion("slug >=", value, "slug");
            return (Criteria) this;
        }

        /**
         * And slug less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andSlugLessThan(String value) {
            addCriterion("slug <", value, "slug");
            return (Criteria) this;
        }

        /**
         * And slug less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andSlugLessThanOrEqualTo(String value) {
            addCriterion("slug <=", value, "slug");
            return (Criteria) this;
        }

        /**
         * And slug like criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andSlugLike(String value) {
            addCriterion("slug like", value, "slug");
            return (Criteria) this;
        }

        /**
         * And slug not like criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andSlugNotLike(String value) {
            addCriterion("slug not like", value, "slug");
            return (Criteria) this;
        }

        /**
         * And slug in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andSlugIn(List<String> values) {
            addCriterion("slug in", values, "slug");
            return (Criteria) this;
        }

        /**
         * And slug not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andSlugNotIn(List<String> values) {
            addCriterion("slug not in", values, "slug");
            return (Criteria) this;
        }

        /**
         * And slug between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andSlugBetween(String value1, String value2) {
            addCriterion("slug between", value1, value2, "slug");
            return (Criteria) this;
        }

        /**
         * And slug not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andSlugNotBetween(String value1, String value2) {
            addCriterion("slug not between", value1, value2, "slug");
            return (Criteria) this;
        }

        /**
         * And created at is null criteria.
         *
         * @return the criteria
         */
        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        /**
         * And created at is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        /**
         * And created at equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        /**
         * And created at not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        /**
         * And updated at is null criteria.
         *
         * @return the criteria
         */
        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        /**
         * And updated at is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        /**
         * And updated at equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        /**
         * And updated at not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }
    }

    /**
     * The type Criteria.
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * Instantiates a new Criteria.
         */
        protected Criteria() {
            super();
        }
    }

    /**
     * The type Criterion.
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        /**
         * Gets condition.
         *
         * @return the condition
         */
        public String getCondition() {
            return condition;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public Object getValue() {
            return value;
        }

        /**
         * Gets second value.
         *
         * @return the second value
         */
        public Object getSecondValue() {
            return secondValue;
        }

        /**
         * Is no value boolean.
         *
         * @return the boolean
         */
        public boolean isNoValue() {
            return noValue;
        }

        /**
         * Is single value boolean.
         *
         * @return the boolean
         */
        public boolean isSingleValue() {
            return singleValue;
        }

        /**
         * Is between value boolean.
         *
         * @return the boolean
         */
        public boolean isBetweenValue() {
            return betweenValue;
        }

        /**
         * Is list value boolean.
         *
         * @return the boolean
         */
        public boolean isListValue() {
            return listValue;
        }

        /**
         * Gets type handler.
         *
         * @return the type handler
         */
        public String getTypeHandler() {
            return typeHandler;
        }

        /**
         * Instantiates a new Criterion.
         *
         * @param condition the condition
         */
        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        /**
         * Instantiates a new Criterion.
         *
         * @param condition   the condition
         * @param value       the value
         * @param typeHandler the type handler
         */
        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        /**
         * Instantiates a new Criterion.
         *
         * @param condition the condition
         * @param value     the value
         */
        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        /**
         * Instantiates a new Criterion.
         *
         * @param condition   the condition
         * @param value       the value
         * @param secondValue the second value
         * @param typeHandler the type handler
         */
        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        /**
         * Instantiates a new Criterion.
         *
         * @param condition   the condition
         * @param value       the value
         * @param secondValue the second value
         */
        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}