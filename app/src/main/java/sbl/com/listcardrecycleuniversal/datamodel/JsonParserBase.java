/**
 * 
 */
package sbl.com.listcardrecycleuniversal.datamodel;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Sandeep Kumar <pksandeepkumar@gmail.com>
 *
 */
public class JsonParserBase{
	
	public static final String TAG = "JsonParser";
	
	public static String getJsonAttributeValueString(JSONObject jsonObject,
			String attibute) {
		String value = "";
		try {
			value = jsonObject.getString(attibute);
		} catch (JSONException e) {
			value = "";
			e.printStackTrace();
		}
		return value;
	}

	public static JSONObject getJsonAttributeValueObject(JSONObject jsonObject,
			String attibute) {
		JSONObject object = null;
		try {
			object = jsonObject.getJSONObject(attibute);
		} catch (JSONException e) {
			object = null;
			e.printStackTrace();
		}
		return object;
	}
	
	public static int getJsonAttributeValueInt(JSONObject jsonObject,
			String attibute) {
		int value = 0;
		try {
			value = jsonObject.getInt(attibute);
		} catch (JSONException e) {
			value = 0;
			e.printStackTrace();
		}
		return value;
	}

	public static double getJsonAttributeValueDouble(JSONObject jsonObject,
			String attibute) {
		double value = 0;
		try {
			value = jsonObject.getDouble(attibute);
		} catch (JSONException e) {
			value = 0;
			e.printStackTrace();
		}
		return value;
	}

	public static boolean getJsonAttributeValueBoolean(JSONObject jsonObject,
			String attibute) {
		boolean value = false;
		try {
			value = jsonObject.getBoolean(attibute);
		} catch (JSONException e) {
			value = false;
			e.printStackTrace();
		}
		return value;
	}
	
	public static int getJsonAttributeValueIntforBoolean(JSONObject jsonObject,
			String attibute) {
		int val;
		boolean value = false;
		try {
			value = jsonObject.getBoolean(attibute);
		} catch (JSONException e) {
			value = false;
			e.printStackTrace();
		}
		if(value==true)
			val=1;
		else
			val=0;
		return val;
	}

}
