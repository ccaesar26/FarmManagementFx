package org.example.farmmanagementfx.data.dao;

import org.example.farmmanagementfx.data.entity.Farmer;
import org.example.farmmanagementfx.data.util.DateUtil;
import org.example.farmmanagementfx.data.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FarmerDao {

    public static List<Farmer> getFarmers() {
        var connection = DbUtil.getConnection();

        var farmers = new ArrayList<Farmer>();
        var call = "{ call get_all_farmers() }";

        try (var statement = connection.prepareCall(call)) {
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var farmer = getFarmerFromResultSet(resultSet);
                farmers.add(farmer);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }

        return farmers;
    }

    public static Farmer getFarmerById(int farmerId) {
        var connection = DbUtil.getConnection();

        var call = "{ call get_farmer_by_id(?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, farmerId);

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getFarmerFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }

        return null;
    }

    public static Farmer insertFarmer(Farmer toInsert) throws SQLException {
        var connection = DbUtil.getConnection();

        var call = "{ ? = call insert_farmer(?, ?, ?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.setString(2, toInsert.getName());
            statement.setInt(3, toInsert.getAssignedCulture().getId());
            statement.setDate(4, DateUtil.convertToSqlDate((toInsert.getEmploymentDate())));

            statement.execute();

            var insertedId = statement.getInt(1);
            toInsert.setId(insertedId);

            return toInsert;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw e;
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    public static void updateFarmer(Farmer toUpdate) {
        var connection = DbUtil.getConnection();

        var call = "{ call update_farmer(?, ?, ?, ?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, toUpdate.getId());
            statement.setString(2, toUpdate.getName());
            statement.setInt(3, toUpdate.getAssignedCulture().getId());
            statement.setDate(4, DateUtil.convertToSqlDate(toUpdate.getEmploymentDate()));

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    public static void deleteFarmer(Farmer toDelete) {
        var connection = DbUtil.getConnection();

        var call = "{ call delete_farmer(?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, toDelete.getId());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    private static Farmer getFarmerFromResultSet(ResultSet resultSet) throws SQLException {
        var id = resultSet.getInt("farmer_id");
        var name = resultSet.getString("name");
        var assignedCultureId = resultSet.getInt("assigned_culture_id");
        var employmentDate = DateUtil.convertToDate(resultSet.getDate("employment_date"));
        var isActive = resultSet.getBoolean("is_active");

        var assignedCulture = CultureDao.getCultureById(assignedCultureId);

        return new Farmer(
                id,
                name,
                assignedCulture,
                employmentDate,
                isActive
        );
    }
}
