import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Main {
	private static final char[] Price = null;
	public static DB db = new DB();
	 
	public static void main(String[] args) throws SQLException, IOException {
		db.runSql2("TRUNCATE Record;");
		processPage("http://www.shophive.com/apple/mac?p=1");
	}
 
	public static void processPage(String URL) throws SQLException, IOException{
	
		String sql = "select * from Record where Price = '"+Price+"'";
		ResultSet rs = db.runSql(sql);
		if(rs.next()){
 
		}else{
		
			sql = "INSERT INTO  `Crawler`.`Record` " + "(`Price`) VALUES " + "(?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, URL);
			stmt.execute();
 
	
			Document doc = ((Object) Jsoup.connect("http://www.shophive.com/apple/mac?p=1")).get();
 
			if(doc.text().contains("Price-Box")){
				System.out.println(Price);
			}
 
		
			}
		}
	}

