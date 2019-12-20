package main.external_libraries.heinemanns_json;

import java.util.regex.*;

/**
 * The <code>JSON</code> class holds static methods that can turn a JSON
 * string<br>
 * to a JSON array or object and a JSON array or object to a JSON string
 * 
 * 
 * @author HEINEMANN
 * 
 * @see <a href="http://www.json.org/">The official JSON website</a>
 * @see <a href="https://en.wikipedia.org/wiki/JSON>JSON - Wikipedia</a>
 * 
 */
public class JSON {
	/**
	 * private JSON()<br>
	 * Prevent anyone from instancing this class
	 */
	private JSON() {
	}

	/**
	 * <code>public static String <b>stringify</b> (JSONObj o)</code><br>
	 * this method accepts a JSON object and returns that object as a JSON string
	 * 
	 * @param o
	 *            - a JSON object
	 * @return the given JSON object as a JSON string
	 * 
	 */
	public static String stringify(JSONObj o) {
		String json = "{";
		for (int i = 0; i != o.keys().length; i++) {
			json += "\"" + o.keys()[i] + "\":"
					+ (o.toArray()[i] != null && o.toArray()[i].toString().equals(o.toArray()[i])
							? "\"" + o.toArray()[i] + "\""
							: o.toArray()[i])
					+ (i == o.keys().length - 1 ? "" : ",");
		}
		return json + "}";
	}

	/**
	 * <code>public static String <b>stringify</b> (JSONArr o)</code><br>
	 * this method accepts a JSON array and returns that object as a JSON string
	 * 
	 * @param a
	 *            - a JSON array
	 * @return the given JSON array as a JSON string
	 * 
	 */
	public static String stringify(JSONArr a) {
		String json = "[";
		for (int i = 0; i != a.size(); i++) {
			json += (a.toArray()[i] != null && a.toArray()[i].toString().equals(a.toArray()[i])
					? "\"" + a.toArray()[i] + "\""
					: a.toArray()[i]) + (i == a.toArray().length - 1 ? "" : ",");
		}
		return json + "]";
	}

	/**
	 * <code>public static Object <b>parse</b> (String j) throws JSONException</code><br>
	 * this method converts a JSON string to a JSON object or array
	 * 
	 * @param j
	 *            - a JSON string
	 * @return an object that be converted either into a JSON array or a JSON object
	 * @throws JSONException
	 *             in case that the given string isn't a legal JSON string
	 */
	public static Object parse(String j) throws JSONException {
		j = j.trim();
		if (j.charAt(0) == '{')
			return JSON.parseObj(j);
		if (j.charAt(0) == '[')
			return JSON.parseArr(j);
		throw new JSONException("Invalid JSON String");
	}

	/**
	 * <code>public static JSONObj <b>parseObj</b> (String j) throws JSONException</code><br>
	 * this method converts a JSON string to a JSON object
	 * 
	 * @param j
	 *            - a JSON string
	 * @return a JSON object
	 * @throws JSONException
	 *             in case that the given string isn't a legal JSON string
	 */
	public static JSONObj parseObj(String j) throws JSONException {
		j = j.trim().replaceFirst("\\{", "").replaceFirst("\\}$", "").replaceFirst("^_\\(\\d+\\);", "")
				.replaceFirst(";\\(\\d+\\)_$", "").replaceAll("\\.-0-\\.", ",").replaceAll("_\\(\\d+\\);", "{")
				.replaceAll(";\\(\\d+\\)_", "}").replaceAll("~\\(\\d+\\);", "[").replaceAll(";\\(\\d+\\)~", "]")
				.replaceAll("\\\\\"", "%%tagw#@%").trim();
		int flag1 = j.indexOf("\""), flag2 = j.substring(flag1 + 1).indexOf("\"") + flag1;
		while (flag1 != -1 && flag2 != -1) {
			String temp = j.substring(flag1, flag2 + 2);
			String rep = temp.replaceAll(",", "-&coma&~;").replaceAll(":", "-&dev&~;").replaceAll("\\{", "-&opend&~;")
					.replaceAll("\\}", "-&closed&~;").replaceAll("\\[", "-&opens&~;").replaceAll("\\]", "-&closes&~;")
					.replaceAll("\\t", "-&tab&~;").replaceAll("\\r?\\n", "-&line&~;").replaceAll("\\s", "-&space&~;")
					.replaceAll("\"", "&&@#tag#@");
			j = j.replace(temp, rep);
			flag1 = j.indexOf("\"");
			flag2 = j.substring(flag1 + 1).indexOf("\"") + flag1;
		}
		j = j.replaceAll("\\s", "").replaceAll("\\r?\\n", "").replaceAll("&&@#tag#@", "\"").replaceAll("%%tagw#@%",
				"\\\"");
		JSONObj obj = new JSONObj();
		if (j.isEmpty())
			return obj;
		Pattern p = Pattern.compile("\\{[^\\{\\}]*\\}");
		Matcher m = p.matcher(j);
		int flag = 0;
		while (m.find()) {
			j = j.replace(m.group(0), m.group(0).replaceFirst("^\\{", "_(" + flag + ");")
					.replaceFirst("\\}$", ";(" + flag + ")_").replaceAll(",", ".-0-."));
			m = p.matcher(j);
			flag++;
		}
		p = Pattern.compile("\\[[^\\[\\]]*\\]");
		m = p.matcher(j);
		flag = 0;
		while (m.find()) {
			j = j.replace(m.group(0), m.group(0).replaceFirst("^\\[", "~(" + flag + ");")
					.replaceFirst("\\]$", ";(" + flag + ")~").replaceAll(",", ".-0-."));
			m = p.matcher(j);
			flag++;
		}
		do {
			p = Pattern.compile("\"[^\"]*\":");
			m = p.matcher(j);
			if (!m.find())
				throw new JSONException("Invalid JSON String");
			j = j.substring(m.group(0).length() - 1, j.length()).trim();
			String key = m.group(0).replaceFirst(":$", "").trim().replaceFirst("\"", "").replaceFirst("\"$", "");
			p = Pattern.compile(":[^,]*,?");
			m = p.matcher(j);
			if (!m.find())
				throw new JSONException("Invalid JSON String");
			String valstr = m.group(0).replaceFirst(":", "").replaceFirst(",", "").trim();
			if (valstr.matches("_\\(\\d+\\);.*")) {
				obj.set(key.replaceAll("-&dtag&~;", "\\\"").replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":")
						.replaceAll("-&opend&~;", "{").replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
						.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ")
						.replaceAll("-&line&~;", "\r\n"), parseObj(valstr));
			}
			if (valstr.matches("~\\(\\d+\\);.*")) {
				obj.set(key.replaceAll("-&dtag&~;", "\\\"").replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":")
						.replaceAll("-&opend&~;", "{").replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
						.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ")
						.replaceAll("-&line&~;", "\r\n"), parseArr(valstr));
			}
			if (valstr.matches("\".*")) {
				obj.set(key.replaceAll("-&dtag&~;", "\\\"").replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":")
						.replaceAll("-&opend&~;", "{").replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
						.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ")
						.replaceAll("-&line&~;", "\r\n"),
						valstr.replaceFirst("\"", "").replaceFirst("\"$", "").replaceAll("-&dtag&~;", "\\\"")
								.replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":").replaceAll("-&opend&~;", "{")
								.replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
								.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	")
								.replaceAll("-&space&~;", " ").replaceAll("-&line&~;", "\r\n"));
			}
			if (valstr.equals("true")) {
				obj.set(key.replaceAll("-&dtag&~;", "\\\"").replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":")
						.replaceAll("-&opend&~;", "{").replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
						.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ")
						.replaceAll("-&line&~;", "\r\n"), true);
			}
			if (valstr.equals("false")) {
				obj.set(key.replaceAll("-&dtag&~;", "\\\"").replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":")
						.replaceAll("-&opend&~;", "{").replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
						.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ")
						.replaceAll("-&line&~;", "\r\n"), false);
			}
			if (valstr.equals("null")) {
				obj.setNull(key.replaceAll("-&dtag&~;", "\\\"").replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":")
						.replaceAll("-&opend&~;", "{").replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
						.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ")
						.replaceAll("-&line&~;", "\r\n"));
			}
			if (valstr.matches("-?\\d+")) {
				obj.set(key.replaceAll("-&dtag&~;", "\\\"").replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":")
						.replaceAll("-&opend&~;", "{").replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
						.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ")
						.replaceAll("-&line&~;", "\r\n"), Integer.parseInt(valstr));
			} else if (valstr.matches("-?\\d+(\\.\\d+)?(E-?\\d+)?")) {
				obj.set(key.replaceAll("-&dtag&~;", "\\\"").replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":")
						.replaceAll("-&opend&~;", "{").replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[")
						.replaceAll("-&closed&~;", "]").replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ")
						.replaceAll("-&line&~;", "\r\n"), Double.parseDouble(valstr));
			}
			j = j.substring(m.group(0).length() - 1, j.length()).trim();
		} while (j.indexOf(',') != -1);
		return obj;
	}

	/**
	 * <code>public static JSONArr <b>parseArr</b> (String j) throws JSONException</code><br>
	 * this method converts a JSON string to a JSON array
	 * 
	 * @param j
	 *            - a JSON string
	 * @return a JSON object
	 * @throws JSONException
	 *             in case that the given string isn't a legal JSON string
	 */
	public static JSONArr parseArr(String j) throws JSONException {
		j = j.trim().replaceFirst("\\[", "").replaceFirst("\\]$", "").replaceFirst("^~\\(\\d+\\);", "")
				.replaceFirst(";\\(\\d+\\)~$", "").replaceAll("\\.-0-\\.", ",").replaceAll("_\\(\\d+\\);", "{")
				.replaceAll(";\\(\\d+\\)_", "}").replaceAll("~\\(\\d+\\);", "[").replaceAll(";\\(\\d+\\)~", "]")
				.replaceAll("\\\\\"", "%%tagw#@%").trim();
		JSONArr arr = new JSONArr();
		int flag1 = j.indexOf("\""), flag2 = j.substring(flag1 + 1).indexOf("\"") + flag1;
		while (flag1 != -1 && flag2 != -1) {
			String temp = j.substring(flag1, flag2 + 2);
			String rep = temp.replaceAll(",", "-&coma&~;").replaceAll(":", "-&dev&~;").replaceAll("\\{", "-&opend&~;")
					.replaceAll("\\}", "-&closed&~;").replaceAll("\\[", "-&opens&~;").replaceAll("\\]", "-&closes&~;")
					.replaceAll("\\t", "-&tab&~;").replaceAll("\\r?\\n", "-&line&~;").replaceAll("\\s", "-&space&~;")
					.replaceAll("\"", "&&@#tag#@");
			j = j.replace(temp, rep);
			flag1 = j.indexOf("\"");
			flag2 = j.substring(flag1 + 1).indexOf("\"") + flag1;
		}
		j = j.replaceAll("\\s", "").replaceAll("\\r?\\n", "").replaceAll("&&@#tag#@", "\"").replaceAll("%%tagw#@%",
				"\\\"");
		if (j.isEmpty())
			return arr;
		Pattern p = Pattern.compile("\\{[^\\{\\}]*\\}");
		Matcher m = p.matcher(j);
		int flag = 0;
		while (m.find()) {
			j = j.replace(m.group(0), m.group(0).replaceFirst("^\\{", "_(" + flag + ");")
					.replaceFirst("\\}$", ";(" + flag + ")_").replaceAll(",", ".-0-."));
			m = p.matcher(j);
			flag++;
		}
		p = Pattern.compile("\\[[^\\[\\]]*\\]");
		m = p.matcher(j);
		flag = 0;
		while (m.find()) {
			j = j.replace(m.group(0), m.group(0).replaceFirst("^\\[", "~(" + flag + ");")
					.replaceFirst("\\]$", ";(" + flag + ")~").replaceAll(",", ".-0-."));
			m = p.matcher(j);
			flag++;
		}
		do {
			p = Pattern.compile("[^,]*,?");
			m = p.matcher(j);
			if (!m.find())
				throw new JSONException("Invalid JSON String");
			String valstr = m.group(0).replaceFirst(",$", "").trim();
			if (valstr.matches("_\\(\\d+\\);.*"))
				arr.add(parseObj(valstr));
			if (valstr.matches("~\\(\\d+\\);.*")) {
				arr.add(parseArr(valstr));
			}
			if (valstr.matches("\".*"))
				arr.add(valstr.replaceFirst("\"", "").replaceFirst("\"$", "").replaceAll("-&dtag&~;", "\\\"")
						.replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":").replaceAll("-&opend&~;", "{")
						.replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[").replaceAll("-&closed&~;", "]")
						.replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ").replaceAll("-&line&~;", "\r\n"));
			if (valstr.equals("true"))
				arr.add(true);
			if (valstr.equals("false"))
				arr.add(false);
			if (valstr.equals("null"))
				arr.addNull();
			if (valstr.matches("-?\\d+"))
				arr.add(Integer.parseInt(valstr));
			else if (valstr.matches("-?\\d+(\\.\\d+)?(E-?\\d+)?"))
				arr.add(Double.parseDouble(valstr));
			j = j.substring(m.group(0).length(), j.length()).trim();
		} while (j.indexOf(',') != -1);
		if (j.matches("_\\(\\d+\\);.*"))
			arr.add(parseObj(j));
		if (j.matches("~\\(\\d+\\);.*"))
			arr.add(parseArr(j));
		if (j.matches("\".*"))
			arr.add(j.replaceFirst("\"", "").replaceFirst("\"$", "").replaceAll("-&dtag&~;", "\\\"")
					.replaceAll("-&coma&~;", ",").replaceAll("-&dev&~;", ":").replaceAll("-&opend&~;", "{")
					.replaceAll("-&closed&~;", "}").replaceAll("-&opens&~;", "[").replaceAll("-&closed&~;", "]")
					.replaceAll("-&tab&~;", "	").replaceAll("-&space&~;", " ").replaceAll("-&line&~;", "\r\n"));
		if (j.equals("true"))
			arr.add(true);
		if (j.equals("false"))
			arr.add(false);
		if (j.equals("null"))
			arr.addNull();
		if (j.matches("-?\\d+"))
			arr.add(Integer.parseInt(j));
		else if (j.matches("-?\\d+(\\.\\d+)?(E-?\\d+)?"))
			arr.add(Double.parseDouble(j));
		return arr;
	}
}