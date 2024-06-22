package com.doo.sistemanutruco.repository.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractTemplateSqlDAO<T, K> implements DAO<T, K> {

    protected abstract String createSaveSql();
    protected abstract String createUpdateSql();
    protected abstract String createDeleteSql();
    protected abstract String createSelectSql();
    protected abstract String createSelectAllSql();
    protected abstract String createSelectBySql(String field);

    protected abstract void setEntityToPreparedStatement(T entity, PreparedStatement stmt) throws SQLException;
    protected abstract void setKeyToPreparedStatement(K key, PreparedStatement stmt) throws SQLException;
    protected abstract void setFilterToPreparedStatement(Object filter, PreparedStatement stmt) throws SQLException;
    protected abstract T getEntityFromResultSet(ResultSet rs) throws SQLException;
    protected abstract K getEntityKey(T entity);

    private String sql;

    @Override
    public K create(T entity) {
        sql = createSaveSql();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setEntityToPreparedStatement(entity, stmt);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return (K) generatedKeys.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(T entity) {
        sql = createUpdateSql();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setEntityToPreparedStatement(entity, stmt);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(K key) {
        sql = createDeleteSql();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setKeyToPreparedStatement(key, stmt);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(T entity) {
        return deleteByKey(getEntityKey(entity));
    }

    @Override
    public Optional<T> findOne(K key) {
        sql = createSelectSql();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setKeyToPreparedStatement(key, stmt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(getEntityFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        sql = createSelectAllSql();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(getEntityFromResultSet(rs));
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    @Override
    public List<T> selectBy(String field, Object value) {
        sql = createSelectBySql(field);
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setFilterToPreparedStatement(value, stmt);
            ResultSet rs = stmt.executeQuery();
            List<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(getEntityFromResultSet(rs));
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}