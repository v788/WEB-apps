package salary.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import salary.servlet.DuplicateNameException;

@WebServlet("/dataaccessbean")
public class DataAccessBean extends HttpServlet {

	//DataSourceの設定
	private static DataSource ds = null;
	private static final String JNDI_NAME = "java:comp/env/jdbc/ssjdb";

	private static DataSource getDataSource() throws NamingException {
		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup(JNDI_NAME);
		}
		return ds;
	}

	//全アルバイト従業員の氏名、時給、交通費 ,特別時給の全件表示オブジェクトのコレクション
	public Collection<EmployeeInfo> findallemployeeInfo() throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM employee";
			Collection<EmployeeInfo> employeeList = new ArrayList<EmployeeInfo>();
			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				EmployeeInfo employeeInfo = new EmployeeInfo();
				employeeInfo.setNo(rs.getString("id"));
				employeeInfo.setName(rs.getString("name"));
				employeeInfo.setHourlywage(rs.getString("hourlywage"));
				employeeInfo.setCarfare(rs.getString("carfare"));
				employeeInfo.setSphourly(rs.getString("sphourly"));

				employeeList.add(employeeInfo);
			}
			return employeeList;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	//時給割増率の全件表示オブジェクトのコレクション
	public Collection<ExtrasettingInfo> extrasettingInfo() throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM extrasetting";
			Collection<ExtrasettingInfo> extrasettingList = new ArrayList<ExtrasettingInfo>();
			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ExtrasettingInfo extrasettingInfo = new ExtrasettingInfo();

				extrasettingInfo.setId(rs.getString("id"));
				extrasettingInfo.setMidnight(rs.getString("midnight"));
				extrasettingInfo.setHoliday(rs.getString("holiday"));
				extrasettingInfo.setOvertime(rs.getString("overtime"));

				extrasettingList.add(extrasettingInfo);
			}
			return extrasettingList;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	//従業員の新規登録フォーム
	public void registEmployeeInfo(EmployeeInfo employeeInfo)
			throws SQLException, DuplicateNameException {
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO employee (name,hourlywage, carfare, sphourly ) VALUES (?,?,?,?)";
			String sqlForCheck = "SELECT name FROM employee WHERE name = ?";
			conn = getDataSource().getConnection();
			ps1 = conn.prepareStatement(sqlForCheck);
			ps1.setString(1, employeeInfo.getName());
			rs = ps1.executeQuery();
			if (rs.next()) {
				throw new DuplicateNameException();
			}

			ps2 = conn.prepareStatement(sql);
			ps2.setString(1, employeeInfo.getName());
			ps2.setString(2, employeeInfo.getHourlywage());
			ps2.setString(3, employeeInfo.getCarfare());
			ps2.setString(4, employeeInfo.getSphourly());

			ps2.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (ps2 != null) {
				ps2.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	//従業員データの変更更新メソッド
	public void updateEmployeeInfo(EmployeeInfo employeeInfo)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "UPDATE employee SET name = ?,hourlywage = ?, carfare = ?, sphourly = ? WHERE id=?";
			conn = getDataSource().getConnection();

			ps = conn.prepareStatement(sql);
			ps.setString(1, employeeInfo.getName());
			ps.setString(2, employeeInfo.getHourlywage());
			ps.setString(3, employeeInfo.getCarfare());
			ps.setString(4, employeeInfo.getSphourly());
			ps.setString(5, employeeInfo.getNo());

			ps.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	//時給割増率の変更オブジェクト
	//従業員データの変更更新メソッド
	public void updateExtrasettingInfo(ExtrasettingInfo extrasettingInfo)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "UPDATE extrasetting SET midnight = ?,holiday = ?, overtime = ? WHERE id=1";
			conn = getDataSource().getConnection();

			ps = conn.prepareStatement(sql);
			ps.setString(1, extrasettingInfo.getMidnight());
			ps.setString(2, extrasettingInfo.getHoliday());
			ps.setString(3, extrasettingInfo.getOvertime());

			ps.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	//従業員データ削除メソッド
	public void deleteEmployeeInfo(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "DELETE FROM employee WHERE id = ?";
			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	//従業員の出勤・時給入力フォーム表示処理
	public Collection<EmployeeInfo> findallLaborCostInfo() throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT employee.id, employee.name, employee.hourlywage, employee.carfare, employee.sphourly, "
					+ "extrasetting.midnight, extrasetting.holiday, extrasetting.overtime from employee, extrasetting";
			Collection<EmployeeInfo> employeeList = new ArrayList<EmployeeInfo>();
			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				EmployeeInfo employeeInfo = new EmployeeInfo();
				employeeInfo.setNo(rs.getString("id"));
				employeeInfo.setName(rs.getString("name"));
				employeeInfo.setHourlywage(rs.getString("hourlywage"));
				employeeInfo.setCarfare(rs.getString("carfare"));
				employeeInfo.setSphourly(rs.getString("sphourly"));
				employeeInfo.setMidnight(rs.getString("midnight"));
				employeeInfo.setHoliday(rs.getString("holiday"));
				employeeInfo.setOvertime(rs.getString("overtime"));

				employeeList.add(employeeInfo);
			}
			return employeeList;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}


	//当月間総人件費表示オブジェクト
	public int thisMonthLaborCostInfo() throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int thisMomth;
		try {
			String sql = "SELECT SUM(total_cost) FROM labor_cost "
					+ "WHERE DATE_FORMAT(date, '%Y%m') = DATE_FORMAT(NOW(), '%Y%m')";
			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			thisMomth = rs.getInt("SUM(total_cost)");
			return thisMomth;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

	//従業員全出勤リスト(今月分)表示オブジェクト
	public Collection<LaborcostInfo> attendanceList() throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT labor_cost.id, labor_cost.date, employee.name, labor_cost.hourly, labor_cost.attend, labor_cost.begin, labor_cost.finish, "
					+ "labor_cost.rest, labor_cost.late, labor_cost.total_work,labor_cost.OverTimeWork, labor_cost.fare, labor_cost.total_cost "
					+ "FROM labor_cost INNER JOIN employee ON labor_cost.name_id = employee.id WHERE DATE_FORMAT(date, '%Y%m') = DATE_FORMAT(NOW(), '%Y%m') ORDER BY labor_cost.id DESC";
			Collection<LaborcostInfo> alllaborcostInfoList = new ArrayList<LaborcostInfo>();
			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				LaborcostInfo laborcostInfo = new LaborcostInfo();
				laborcostInfo.setId(rs.getString("id"));
				laborcostInfo.setDate(rs.getString("date"));
				laborcostInfo.setName(rs.getString("name"));
				laborcostInfo.setHourly(rs.getString("hourly"));
				laborcostInfo.setBegin(rs.getString("begin"));
				laborcostInfo.setFinish(rs.getString("finish"));
				laborcostInfo.setRest(rs.getString("rest"));
				laborcostInfo.setLate(rs.getString("late"));
				laborcostInfo.setTotal_work(rs.getString("total_work"));
				laborcostInfo.setOverTimeWork(rs.getString("OverTimeWork"));
				laborcostInfo.setFare(rs.getString("fare"));
				laborcostInfo.setTotal_cost(rs.getString("total_cost"));
				laborcostInfo.setAttend(rs.getString("attend"));

				alllaborcostInfoList.add(laborcostInfo);
			}
			return alllaborcostInfoList;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}


}