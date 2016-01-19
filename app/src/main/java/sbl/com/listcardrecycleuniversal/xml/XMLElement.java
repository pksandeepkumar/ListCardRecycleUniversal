/**
 * 
 */
package sbl.com.listcardrecycleuniversal.xml;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Sandeep Kumar
 * 
 */
public class XMLElement {
	public XMLElement Parent = null;
	public ArrayList<XMLElement> Children = new ArrayList<XMLElement>();
	public HashMap<String, String> Attributes = new HashMap<String, String>();
	public String Name = null;

	public XMLElement(String name) {
		this.Name = name;
	}
	
	public void SetAttribute(String name, String value) 
	{
		this.Attributes.put(name, value);
	}

	public String getAttribute(String name) {
		if (!this.Attributes.containsKey(name)) { return null; }
		String returnStr = this.Attributes.get(name);
		if( (null != returnStr) && (returnStr.length() > 0)) { return returnStr; }
		return  null;
	}

	public void Add(XMLElement ChildElement) {
		ChildElement.Parent = this;
		Children.add(ChildElement);
	}

	public XMLElement selectSingleELementByAttribute(String AttributeName, String AttributeValue) {
		try {
			if ((null == AttributeName) || (AttributeName.length() < 1) || (null == AttributeValue)) {
				return null;
			}
			
			for (XMLElement curChild : Children) {
				if (null == curChild) { continue; }
                String curAttr = curChild.getAttribute(AttributeName);
                if ((null != curAttr) && (0 == curAttr.compareTo(AttributeValue))) {
                    return curChild;
                }
			}
		}catch(Exception e){}
		return null;
	}
	
	
	public ArrayList<XMLElement> selectELementsByAttribute(String AttributeName, String AttributeValue) {
		ArrayList<XMLElement> returnList = new ArrayList<XMLElement>();
		try {
			if ((null == AttributeName) || (AttributeName.length() < 1) || (null == AttributeValue)) {
				return returnList;
			}

			for (XMLElement curChild : Children) {
				if (null == curChild) { continue; }
                String curAttr = curChild.getAttribute(AttributeName);
                if ((null != curAttr) && (0 == curAttr.compareTo(AttributeValue))) {
						returnList.add(curChild);
                }
			}
		}catch(Exception e){}
		return returnList;
	}
	
	public XMLElement selectSingleELementByName(String Name) {
		try {
			if ((null == Name) || (Name.length() < 1)) { return null; }
			for (XMLElement curChild : Children) {
				if ((null != curChild) && (null != curChild.Name) &&
                        (0 == curChild.Name.compareToIgnoreCase(Name))) {
					return curChild;
				}
			}
		}catch(Exception e){}
		return null;
	}

	public ArrayList<XMLElement> selectELementsByName(String Name) {
		ArrayList<XMLElement> returnList = new ArrayList<XMLElement>();
		try {
			if ((null == Name) || (Name.length() < 1)) {
				return returnList;
			}
			
			for (XMLElement curChild : Children) {
				if ((null != curChild) && (null != curChild.Name)
                        && (0 == curChild.Name.compareToIgnoreCase(Name))) {
					returnList.add(curChild);
				}
			}
		}catch(Exception e){}
		return returnList;
	}
	
	
}
