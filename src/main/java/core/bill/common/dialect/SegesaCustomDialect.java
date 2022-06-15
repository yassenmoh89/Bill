package core.bill.common.dialect;

import org.hibernate.dialect.SQLServerDialect;

import java.sql.Types;

public class SegesaCustomDialect  extends SQLServerDialect {

	public SegesaCustomDialect() {
		 super();
		 registerColumnType(Types.BIGINT, "bigint" );   // Overwrite SQL Server datatype BIGINT
	}
}
