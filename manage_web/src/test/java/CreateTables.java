
import java.util.List;

import org.junit.Test;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.jdbc.JdbcTemplate;
import com.jhcz.base.util.StringHelper;

public class CreateTables
{
	
	JdbcTemplate jdbc = new JdbcTemplate();
	
	String tableName = "T_B_JOB";
	
	String newTableName = "t_ws_business_job";
	
	String fc = "FC_"; //varchar2 目前仅定义这种
	
	String fn = "FN_"; //	number
	
	String fclob = "FCLOB_"; //clob
	
	@Test
	public void changeField()
	{
		createTable(newTableName);
		String sql = "select t1.*, t2.comments from (select column_id,column_name, data_type, data_length,data_precision from user_tab_columns where table_name = upper(?)) t1 left join (select column_name,comments from user_col_comments where table_name = upper(?)) t2 on t1.column_name = t2.column_name order by t1.column_id";
		List<Object> dataList = jdbc.query(sql, new Object[] { tableName, tableName });
		System.out.println(dataList.size());
		int i = 0;
		for (Object object : dataList)
		{
			i++;
			DataRow row = (DataRow) object;
			String fieldName = row.getString("column_name");
			String dataType = row.getString("data_type");
			
			fieldName = getNewFieldName(fieldName, dataType);
			String dataLength = row.getString("data_length");
			String dataPrecision = row.getString("data_precision");
			if (StringHelper.isNotEmpty(dataPrecision))
			{
				dataLength = dataPrecision;
			}
			if (i == 17)
			{
				System.out.println("fasd");
			}
			String comments = row.getString("comments");
			System.out.println(row);
			addColumn(newTableName, fieldName, dataType, dataLength);
			addComments(newTableName, fieldName, comments);
		}
		deleteColumn(newTableName);
	}
	
	public void createTable(String newTableName)
	{
		String sql = "create table " + newTableName + "(" + newTableName + " number(2))";
		jdbc.update(sql);
	}
	
	public void deleteColumn(String newTableName)
	{
		String sql = "alter table " + newTableName + " drop column " + newTableName;
		jdbc.update(sql);
	}
	
	private void addComments(String tableName, String fieldName, String comments)
	{
		String sql = "comment on column " + tableName + "." + fieldName + " is '" + comments + "'";
		jdbc.update(sql);
	}
	
	public void addColumn(String tableName, String fieldName, String dataType, String dataLength)
	{
		String sql;
		if (dataType.equals("CLOB"))
		{
			sql = "alter table " + tableName + " add (" + fieldName + " " + dataType + ")";
			jdbc.update(sql);
		}
		else if (dataType.equals("NUMBER") || dataType.equals("VARCHAR2"))
		{
			sql = "alter table " + tableName + " add (" + fieldName + " " + dataType + "(" + dataLength + "))";
			jdbc.update(sql);
		}
		else if (dataType.equals("DATE"))
		{
			sql = "alter table " + tableName + " add (" + fieldName + " VARCHAR2(20))";
			jdbc.update(sql);
		}
	}
	
	public String getNewFieldName(String fieldName, String dataType)
	{
		if (dataType.equals("NUMBER"))
		{
			fieldName = fn + fieldName;
		}
		if (dataType.equals("VARCHAR2"))
		{
			fieldName = fc + fieldName;
		}
		if (dataType.equals("CLOB"))
		{
			fieldName = fclob + fieldName;
		}
		if (dataType.equals("DATE"))
		{
			fieldName = fc + fieldName;
		}
		
		return fieldName;
	}
}
