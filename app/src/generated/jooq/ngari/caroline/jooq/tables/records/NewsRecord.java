/*
 * This file is generated by jOOQ.
 */
package ngari.caroline.jooq.tables.records;


import ngari.caroline.jooq.tables.News;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NewsRecord extends UpdatableRecordImpl<NewsRecord> implements Record6<Integer, String, Integer, Integer, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>news.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>news.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>news.news_type</code>.
     */
    public void setNewsType(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>news.news_type</code>.
     */
    public String getNewsType() {
        return (String) get(1);
    }

    /**
     * Setter for <code>news.department_id</code>.
     */
    public void setDepartmentId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>news.department_id</code>.
     */
    public Integer getDepartmentId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>news.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>news.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>news.title</code>.
     */
    public void setTitle(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>news.title</code>.
     */
    public String getTitle() {
        return (String) get(4);
    }

    /**
     * Setter for <code>news.description</code>.
     */
    public void setDescription(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>news.description</code>.
     */
    public String getDescription() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, Integer, Integer, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, Integer, Integer, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return News.NEWS.ID;
    }

    @Override
    public Field<String> field2() {
        return News.NEWS.NEWS_TYPE;
    }

    @Override
    public Field<Integer> field3() {
        return News.NEWS.DEPARTMENT_ID;
    }

    @Override
    public Field<Integer> field4() {
        return News.NEWS.USER_ID;
    }

    @Override
    public Field<String> field5() {
        return News.NEWS.TITLE;
    }

    @Override
    public Field<String> field6() {
        return News.NEWS.DESCRIPTION;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getNewsType();
    }

    @Override
    public Integer component3() {
        return getDepartmentId();
    }

    @Override
    public Integer component4() {
        return getUserId();
    }

    @Override
    public String component5() {
        return getTitle();
    }

    @Override
    public String component6() {
        return getDescription();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getNewsType();
    }

    @Override
    public Integer value3() {
        return getDepartmentId();
    }

    @Override
    public Integer value4() {
        return getUserId();
    }

    @Override
    public String value5() {
        return getTitle();
    }

    @Override
    public String value6() {
        return getDescription();
    }

    @Override
    public NewsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public NewsRecord value2(String value) {
        setNewsType(value);
        return this;
    }

    @Override
    public NewsRecord value3(Integer value) {
        setDepartmentId(value);
        return this;
    }

    @Override
    public NewsRecord value4(Integer value) {
        setUserId(value);
        return this;
    }

    @Override
    public NewsRecord value5(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public NewsRecord value6(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public NewsRecord values(Integer value1, String value2, Integer value3, Integer value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NewsRecord
     */
    public NewsRecord() {
        super(News.NEWS);
    }

    /**
     * Create a detached, initialised NewsRecord
     */
    public NewsRecord(Integer id, String newsType, Integer departmentId, Integer userId, String title, String description) {
        super(News.NEWS);

        setId(id);
        setNewsType(newsType);
        setDepartmentId(departmentId);
        setUserId(userId);
        setTitle(title);
        setDescription(description);
        resetChangedOnNotNull();
    }
}
