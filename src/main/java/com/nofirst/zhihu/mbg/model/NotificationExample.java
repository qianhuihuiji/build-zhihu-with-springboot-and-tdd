package com.nofirst.zhihu.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Notification example.
 */
public class NotificationExample {
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
     * Instantiates a new Notification example.
     */
    public NotificationExample() {
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
         * And type is null criteria.
         *
         * @return the criteria
         */
        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        /**
         * And type is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        /**
         * And type equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        /**
         * And type not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        /**
         * And type greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        /**
         * And type greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        /**
         * And type less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        /**
         * And type less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        /**
         * And type like criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        /**
         * And type not like criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        /**
         * And type in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        /**
         * And type not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        /**
         * And type between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        /**
         * And type not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        /**
         * And notifiable id is null criteria.
         *
         * @return the criteria
         */
        public Criteria andNotifiableIdIsNull() {
            addCriterion("notifiable_id is null");
            return (Criteria) this;
        }

        /**
         * And notifiable id is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andNotifiableIdIsNotNull() {
            addCriterion("notifiable_id is not null");
            return (Criteria) this;
        }

        /**
         * And notifiable id equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableIdEqualTo(Integer value) {
            addCriterion("notifiable_id =", value, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableIdNotEqualTo(Integer value) {
            addCriterion("notifiable_id <>", value, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableIdGreaterThan(Integer value) {
            addCriterion("notifiable_id >", value, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("notifiable_id >=", value, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableIdLessThan(Integer value) {
            addCriterion("notifiable_id <", value, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableIdLessThanOrEqualTo(Integer value) {
            addCriterion("notifiable_id <=", value, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andNotifiableIdIn(List<Integer> values) {
            addCriterion("notifiable_id in", values, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andNotifiableIdNotIn(List<Integer> values) {
            addCriterion("notifiable_id not in", values, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andNotifiableIdBetween(Integer value1, Integer value2) {
            addCriterion("notifiable_id between", value1, value2, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable id not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andNotifiableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("notifiable_id not between", value1, value2, "notifiableId");
            return (Criteria) this;
        }

        /**
         * And notifiable type is null criteria.
         *
         * @return the criteria
         */
        public Criteria andNotifiableTypeIsNull() {
            addCriterion("notifiable_type is null");
            return (Criteria) this;
        }

        /**
         * And notifiable type is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andNotifiableTypeIsNotNull() {
            addCriterion("notifiable_type is not null");
            return (Criteria) this;
        }

        /**
         * And notifiable type equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableTypeEqualTo(String value) {
            addCriterion("notifiable_type =", value, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableTypeNotEqualTo(String value) {
            addCriterion("notifiable_type <>", value, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableTypeGreaterThan(String value) {
            addCriterion("notifiable_type >", value, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableTypeGreaterThanOrEqualTo(String value) {
            addCriterion("notifiable_type >=", value, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableTypeLessThan(String value) {
            addCriterion("notifiable_type <", value, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableTypeLessThanOrEqualTo(String value) {
            addCriterion("notifiable_type <=", value, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type like criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableTypeLike(String value) {
            addCriterion("notifiable_type like", value, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type not like criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andNotifiableTypeNotLike(String value) {
            addCriterion("notifiable_type not like", value, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andNotifiableTypeIn(List<String> values) {
            addCriterion("notifiable_type in", values, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andNotifiableTypeNotIn(List<String> values) {
            addCriterion("notifiable_type not in", values, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andNotifiableTypeBetween(String value1, String value2) {
            addCriterion("notifiable_type between", value1, value2, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And notifiable type not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andNotifiableTypeNotBetween(String value1, String value2) {
            addCriterion("notifiable_type not between", value1, value2, "notifiableType");
            return (Criteria) this;
        }

        /**
         * And read at is null criteria.
         *
         * @return the criteria
         */
        public Criteria andReadAtIsNull() {
            addCriterion("read_at is null");
            return (Criteria) this;
        }

        /**
         * And read at is not null criteria.
         *
         * @return the criteria
         */
        public Criteria andReadAtIsNotNull() {
            addCriterion("read_at is not null");
            return (Criteria) this;
        }

        /**
         * And read at equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andReadAtEqualTo(Date value) {
            addCriterion("read_at =", value, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at not equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andReadAtNotEqualTo(Date value) {
            addCriterion("read_at <>", value, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at greater than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andReadAtGreaterThan(Date value) {
            addCriterion("read_at >", value, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at greater than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andReadAtGreaterThanOrEqualTo(Date value) {
            addCriterion("read_at >=", value, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at less than criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andReadAtLessThan(Date value) {
            addCriterion("read_at <", value, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at less than or equal to criteria.
         *
         * @param value the value
         * @return the criteria
         */
        public Criteria andReadAtLessThanOrEqualTo(Date value) {
            addCriterion("read_at <=", value, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andReadAtIn(List<Date> values) {
            addCriterion("read_at in", values, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at not in criteria.
         *
         * @param values the values
         * @return the criteria
         */
        public Criteria andReadAtNotIn(List<Date> values) {
            addCriterion("read_at not in", values, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andReadAtBetween(Date value1, Date value2) {
            addCriterion("read_at between", value1, value2, "readAt");
            return (Criteria) this;
        }

        /**
         * And read at not between criteria.
         *
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the criteria
         */
        public Criteria andReadAtNotBetween(Date value1, Date value2) {
            addCriterion("read_at not between", value1, value2, "readAt");
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