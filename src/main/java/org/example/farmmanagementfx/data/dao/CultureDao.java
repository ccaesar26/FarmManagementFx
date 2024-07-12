package org.example.farmmanagementfx.data.dao;

import org.example.farmmanagementfx.data.util.DbUtil;
import org.example.farmmanagementfx.data.entity.Culture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CultureDao {

    public static List<Culture> getCultures() {
        var connection = DbUtil.getConnection();

        var cultures = new ArrayList<Culture>();
        var call = "{ call get_all_cultures() }";

        try (var statement = connection.prepareCall(call)) {
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var culture = getCultureFromResultSet(resultSet);
                cultures.add(culture);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }

        return cultures;
    }

    public static Culture getCultureById(int cultureId) {
        var connection = DbUtil.getConnection();

        var call = "{ call get_culture_by_id(?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, cultureId);

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getCultureFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }

        return null;
    }

    public static Culture insertCulture(Culture toInsert) throws SQLException {
        var connection = DbUtil.getConnection();

        var call = "{ ? = call insert_culture(?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.setString(2, toInsert.name().getValue());

            statement.execute();

            int generatedId = statement.getInt(1);
            toInsert.setId(generatedId);

            return toInsert;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            DbUtil.closeConnection(connection);
        }
    }


    public static void updateCulture(Culture toUpdate) {
        var connection = DbUtil.getConnection();

        var call = "{ call update_culture(?, ?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, toUpdate.id().getValue());
            statement.setString(2, toUpdate.name().getValue());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    public static void deleteCulture(Culture toDelete) {
        var connection = DbUtil.getConnection();

        var call = "{ call delete_culture(?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, toDelete.id().getValue());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    private static Culture getCultureFromResultSet(ResultSet resultSet) throws SQLException {
        var id = resultSet.getInt("culture_id");
        var name = resultSet.getString("name");
        var isActive = resultSet.getBoolean("is_active");

        return new Culture(id, name, isActive);
    }
}
