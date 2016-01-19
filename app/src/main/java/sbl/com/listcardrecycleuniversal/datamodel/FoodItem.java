/**
 * 
 */
package sbl.com.listcardrecycleuniversal.datamodel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import sbl.com.listcardrecycleuniversal.db.Databases;
import sbl.com.listcardrecycleuniversal.utility.LOG;
import sbl.com.listcardrecycleuniversal.utility.Utility;
import sbl.com.listcardrecycleuniversal.xml.XMLElement;
import sbl.com.listcardrecycleuniversal.xml.XMLTree;

/**
 * @author Sandeep Kumar <pksandeepkumar@gmail.com>
 *
 */
public class FoodItem extends BaseDataModel {

//    <FoodItems>
//    <FoodItem id="1" name="Fish Food" rate="45" description=""
//    imageUrl="http://4.bp.blogspot.com/-DYBXwLsTEZE/Vp3KmsfsOyI/AAAAAAAACiU/G1YIunoUaLw/s1600/healthy_food.jpg"/>


    public static final String TABLE_NAME = "TableFoodItem";
	
	public static final String FOOD_ID = "id";
	public static final String NAME = "name";
	public static final String RATE = "rate";
	public static final String DESCRIPTION = "description";
    public static final String IMAGE_URL = "imageUrl";

	public int id;
	public String name;
	public float rate;
    public String description;
    public String imageUrl;


	public static final String CREATE_TABE_QUERY = "CREATE TABLE  " + TABLE_NAME 
			+ " ( " + "_id" + " INTEGER  PRIMARY KEY AUTOINCREMENT," 
			+ FOOD_ID + " INTEGER,"
			+ NAME + " varchar(200),"
			+ RATE + " INTEGER,"
			+ DESCRIPTION + " TEXT , "
            + IMAGE_URL + " TEXT );";


//    {
//        "FoodItems": {
//        "FoodItem": [
//        {
//            "_id": "1",
//                "_name": "Fish Food",
//                "_rate": "45",
//                "_description": "",
//                "_imageUrl": "http://4.bp.blogspot.com/-DYBXwLsTEZE/Vp3KmsfsOyI/" +
//                "AAAAAAAACiU/G1YIunoUaLw/s1600/healthy_food.jpg"
//        },

	public static ArrayList<FoodItem> getParsedFromJson( String jsonString) {
		ArrayList<FoodItem> lists = new ArrayList<FoodItem>();
		try {
            JSONObject object = new JSONObject(jsonString);
			JSONArray jsonArray = object.getJSONArray("FoodItems");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				FoodItem instance = new FoodItem();
				instance.id = getJsonAttributeValueInt(jsonObject, "_id");
				instance.name = getJsonAttributeValueString(jsonObject, "_name");
				instance.rate = (float) getJsonAttributeValueDouble(jsonObject, "_rate");
				instance.description = getJsonAttributeValueString(jsonObject, "_description");
                instance.imageUrl = getJsonAttributeValueString(jsonObject, "_imageUrl");
				lists.add(instance);
			}
		} catch ( Exception e){
			e.printStackTrace();
		}
		return lists;
	}

//    <FoodItems>
//    <FoodItem id="1" name="Fish Food" rate="45" description=""
//    imageUrl="http://4.bp.blogspot.com/-DYBXwLsTEZE/Vp3KmsfsOyI/AAAAAAAACiU/G1YIunoUaLw/s1600/healthy_food.jpg"/>


    public static ArrayList<FoodItem> getParsedFromXML( String xmlString) {
        ArrayList<FoodItem> lists = new ArrayList<FoodItem>();
        XMLTree tree = new XMLTree();
        tree.load(xmlString, false);
        XMLElement rootElement = tree.RootElement;
        if(rootElement == null) return lists;
        for( XMLElement element : rootElement.Children) {
            if(element == null) continue;
            FoodItem foodItem = new FoodItem();
            foodItem.id = Utility.parseInt(element.getAttribute("id"));
            foodItem.name = element.getAttribute("name");
            foodItem.rate = Utility.parseFloat(element.getAttribute("rate"));
            foodItem.description = element.getAttribute("description");
            foodItem.imageUrl = element.getAttribute("imageUrl");
        }
        return lists;
    }
	
//	{
//	    "GradeID": 1,
//	    "Code": "COT-GR-A  ",
//	    "Name": "Cotton Grade A",
//	    "CropID": 2
//	  }
	
	public static FoodItem getAnObjectFromCursor(Cursor c) {
		FoodItem instance = null;
		if( c != null) {
			instance = new FoodItem();
            instance.id = c.getInt(c.getColumnIndex(FOOD_ID));
			instance.name = c.getString(c.getColumnIndex(NAME));
			instance.rate = c.getFloat(c.getColumnIndex(RATE));
			instance.description = c.getString(c.getColumnIndex(NAME));
			instance.imageUrl = c.getString(c.getColumnIndex(IMAGE_URL));
		} else {
		}
		
		return instance;
	}

	public static FoodItem getAnObject(Databases db, FoodItem item) {
		FoodItem gradeTemp = null;
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from "  + TABLE_NAME  + " WHERE " +
                FOOD_ID + " = " + item.id + "" ;
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if (c.moveToFirst()) {
			gradeTemp = getAnObjectFromCursor(c);
		}
		c.close();
		dbRead.close();
		return gradeTemp;
	}
	
	public static ArrayList<FoodItem> getAllObject(Databases db) {
		ArrayList<FoodItem> grades = new ArrayList<FoodItem>();
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from "  + TABLE_NAME  ;
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);

		if(c.moveToFirst()){
			do {
				FoodItem gradeTemp = getAnObjectFromCursor(c);
				grades.add(gradeTemp);
			} while (c.moveToNext());
		}
		c.close();
		dbRead.close();
		return grades;
	}
	

	public static boolean inseartOperation(Databases db, FoodItem item) {
		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "insert into " + TABLE_NAME + " (" 
					+ FOOD_ID + ","
					+ NAME + ","
					+ RATE + ","
                    + DESCRIPTION + ","
                    + IMAGE_URL + ") values ("
					+ "" +  item.id + "" + ","
                    + "'" + item.name + "'" + ","
                    + "" + item.rate + "" + ","
                    + "'" + item.description + "'" + ","
                    + "'" + item.imageUrl + "'" + ");";
		LOG.log("Query:", "Query:" + query);
		sql.execSQL(query);
		return true;
	}
	
	public static boolean updateOperation(Databases db, FoodItem item) {
		
		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "update " + TABLE_NAME + " SET " 
					+ FOOD_ID + " = " + item.id + " , "
					+ NAME + " = '" + item.name + "' , "
					+ RATE + " = " + item.rate + " , "
                    + DESCRIPTION + " = '" + item.description + "' , "
					+ IMAGE_URL + " = '" + item.imageUrl + "' "
					+ " WHERE " + FOOD_ID + " = " + item.id + "";
		LOG.log("Query:", "Query:" + query);
		sql.execSQL(query);
		
		sql.close();
		return true;
	}
	
	public static boolean deleteTable(Databases db) {
		try {
			SQLiteDatabase sql = db.getWritableDatabase();
			String query = "DELETE from " +  TABLE_NAME;
			LOG.log("Query:", "Query:" + query);
			sql.execSQL(query);
			sql.close();
			return true;
		} catch ( Exception e) {
			return false;
		}
	}
	
	public static void inseartOrUpdateOperation(Databases db, FoodItem grade) {
		if(grade == null) return;
		FoodItem gradeTemp = getAnObject(db, grade);
		if(gradeTemp == null) {
			inseartOperation(db, grade);
		} else {
			updateOperation(db, grade);
		}
	}
	
//	GradeID
//	Code
//	Name
//	CropID

}
