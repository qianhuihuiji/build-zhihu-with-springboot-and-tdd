package com.nofirst.zhihu.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NotificationExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdIsNull() {
            addCriterion("notifiable_id is null");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdIsNotNull() {
            addCriterion("notifiable_id is not null");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdEqualTo(Integer value) {
            addCriterion("notifiable_id =", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdNotEqualTo(Integer value) {
            addCriterion("notifiable_id <>", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdGreaterThan(Integer value) {
            addCriterion("notifiable_id >", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("notifiable_id >=", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdLessThan(Integer value) {
            addCriterion("notifiable_id <", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdLessThanOrEqualTo(Integer value) {
            addCriterion("notifiable_id <=", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdIn(List<Integer> values) {
            addCriterion("notifiable_id in", values, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdNotIn(List<Integer> values) {
            addCriterion("notifiable_id not in", values, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdBetween(Integer value1, Integer value2) {
            addCriterion("notifiable_id between", value1, value2, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("notifiable_id not between", value1, value2, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeIsNull() {
            addCriterion("notifiable_type is null");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeIsNotNull() {
            addCriterion("notifiable_type is not null");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeEqualTo(String value) {
            addCriterion("notifiable_type =", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeNotEqualTo(String value) {
            addCriterion("notifiable_type <>", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeGreaterThan(String value) {
            addCriterion("notifiable_type >", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeGreaterThanOrEqualTo(String value) {
            addCriterion("notifiable_type >=", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeLessThan(String value) {
            addCriterion("notifiable_type <", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeLessThanOrEqualTo(String value) {
            addCriterion("notifiable_type <=", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeLike(String value) {
            addCriterion("notifiable_type like", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeNotLike(String value) {
            addCriterion("notifiable_type not like", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeIn(List<String> values) {
            addCriterion("notifiable_type in", values, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeNotIn(List<String> values) {
            addCriterion("notifiable_type not in", values, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeBetween(String value1, String value2) {
            addCriterion("notifiable_type between", value1, value2, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeNotBetween(String value1, String value2) {
            addCriterion("notifiable_type not between", value1, value2, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andReadAtIsNull() {
            addCriterion("read_at is null");
            return (Criteria) this;
        }

        public Criteria andReadAtIsNotNull() {
            addCriterion("read_at is not null");
            return (Criteria) this;
        }

        public Criteria andReadAtEqualTo(Date value) {
            addCriterion("read_at =", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtNotEqualTo(Date value) {
            addCriterion("read_at <>", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtGreaterThan(Date value) {
            addCriterion("read_at >", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtGreaterThanOrEqualTo(Date value) {
            addCriterion("read_at >=", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtLessThan(Date value) {
            addCriterion("read_at <", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtLessThanOrEqualTo(Date value) {
            addCriterion("read_at <=", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtIn(List<Date> values) {
            addCriterion("read_at in", values, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtNotIn(List<Date> values) {
            addCriterion("read_at not in", values, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtBetween(Date value1, Date value2) {
            addCriterion("read_at between", value1, value2, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtNotBetween(Date value1, Date value2) {
            addCriterion("read_at not between", value1, value2, "readAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

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

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}