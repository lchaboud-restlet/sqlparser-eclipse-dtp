package test;

//imports needed
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseErrorInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManagerProvider;

public class Test {

	public static void main(final String[] args) {
		// SELECT : OK
		parse("SELECT * FROM test");

		// DELETE : OK
		parse("DELETE FROM test");

		// CREATE TABLE : KO : "Unable to parse the input"
		parse("CREATE TABLE films (\n"+
				"    code        char(5) CONSTRAINT firstkey PRIMARY KEY,\n"+
				"    title       varchar(40) NOT NULL,\n"+
				"    did         integer NOT NULL,\n"+
				"    date_prod   date,\n"+
				"    kind        varchar(10),\n"+
				"    len         interval hour to minute\n"+
				")");
	}

	private static void parse(final String sql) {
		try {
			// Create an instance the Parser Manager
			// SQLQueryParserManagerProvider.getInstance().getParserManager
			// returns the best compliant SQLQueryParserManager
			// supporting the SQL dialect of the database described by the given
			// database product information. In the code below null is passed for both the database and version
			// in which case a generic parser is returned

			final SQLQueryParserManager parserManager = SQLQueryParserManagerProvider
					.getInstance().getParserManager(null, null);
			//Samples

			//Parse
			final SQLQueryParseResult parseResult = parserManager.parseQuery(sql);
			// Get the Query Model object from the result
			final QueryStatement resultObject = parseResult.getQueryStatement();
			// Get the SQL text
			final String parsedSQL = resultObject.getSQL();
			System.out.println(parsedSQL);

		} catch (final SQLParserException spe) {
			// handle the syntax error
			System.out.println(spe.getMessage());
			final List syntacticErrors = spe.getErrorInfoList();
			final Iterator itr = syntacticErrors.iterator();
			while (itr.hasNext()) {
				final SQLParseErrorInfo errorInfo = (SQLParseErrorInfo) itr.next();
				// Example usage of the SQLParseErrorInfo object
				// the error message
				final String errorMessage = errorInfo.getParserErrorMessage();
				// the line numbers of error
				final int errorLine = errorInfo.getLineNumberStart();
				final int errorColumn = errorInfo.getColumnNumberStart();
			}

		} catch (final SQLParserInternalException spie) {
			// handle the exception
			System.out.println(spie.getMessage());
		}
	}

}
