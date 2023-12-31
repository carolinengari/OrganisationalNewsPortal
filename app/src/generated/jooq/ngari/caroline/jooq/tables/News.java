/*
 * This file is generated by jOOQ.
 */
package ngari.caroline.jooq.tables;


import java.util.function.Function;

import ngari.caroline.jooq.DefaultSchema;
import ngari.caroline.jooq.Keys;
import ngari.caroline.jooq.tables.records.NewsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class News extends TableImpl<NewsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>news</code>
     */
    public static final News NEWS = new News();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<NewsRecord> getRecordType() {
        return NewsRecord.class;
    }

    /**
     * The column <code>news.id</code>.
     */
    public final TableField<NewsRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>news.news_type</code>.
     */
    public final TableField<NewsRecord, String> NEWS_TYPE = createField(DSL.name("news_type"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>news.department_id</code>.
     */
    public final TableField<NewsRecord, Integer> DEPARTMENT_ID = createField(DSL.name("department_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>news.user_id</code>.
     */
    public final TableField<NewsRecord, Integer> USER_ID = createField(DSL.name("user_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>news.title</code>.
     */
    public final TableField<NewsRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>news.description</code>.
     */
    public final TableField<NewsRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR, this, "");

    private News(Name alias, Table<NewsRecord> aliased) {
        this(alias, aliased, null);
    }

    private News(Name alias, Table<NewsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>news</code> table reference
     */
    public News(String alias) {
        this(DSL.name(alias), NEWS);
    }

    /**
     * Create an aliased <code>news</code> table reference
     */
    public News(Name alias) {
        this(alias, NEWS);
    }

    /**
     * Create a <code>news</code> table reference
     */
    public News() {
        this(DSL.name("news"), null);
    }

    public <O extends Record> News(Table<O> child, ForeignKey<O, NewsRecord> key) {
        super(child, key, NEWS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<NewsRecord> getPrimaryKey() {
        return Keys.NEWS__PK_NEWS;
    }

    @Override
    public News as(String alias) {
        return new News(DSL.name(alias), this);
    }

    @Override
    public News as(Name alias) {
        return new News(alias, this);
    }

    @Override
    public News as(Table<?> alias) {
        return new News(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public News rename(String name) {
        return new News(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public News rename(Name name) {
        return new News(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public News rename(Table<?> name) {
        return new News(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, Integer, Integer, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super Integer, ? super String, ? super Integer, ? super Integer, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super Integer, ? super String, ? super Integer, ? super Integer, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
