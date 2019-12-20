package main.external_libraries.heinemanns_json;

public class JSONArr {
	private Object[] init;

	public JSONArr() {
		init = new Object[0];
	}

	public JSONArr(Object[] o) {
		init = o;
	}

	public JSONArr(String s) {
		try {
			JSONArr j = JSON.parseArr(s);
			init = j.toArray();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void add(int o) {
		Object[] temp = init;
		init = new Object[temp.length + 1];
		for (int i = 0; i != temp.length; i++)
			init[i] = temp[i];
		init[init.length - 1] = new Integer(o);
	}

	public void add(double o) {
		Object[] temp = init;
		init = new Object[temp.length + 1];
		for (int i = 0; i != temp.length; i++)
			init[i] = temp[i];
		init[init.length - 1] = new Double(o);
	}

	public void add(String o) {
		Object[] temp = init;
		init = new Object[temp.length + 1];
		for (int i = 0; i != temp.length; i++)
			init[i] = temp[i];
		init[init.length - 1] = o;
	}

	public void add(boolean o) {
		Object[] temp = init;
		init = new Object[temp.length + 1];
		for (int i = 0; i != temp.length; i++)
			init[i] = temp[i];
		init[init.length - 1] = new Boolean(o);
	}

	public void addNull() {
		Object[] temp = init;
		init = new Object[temp.length + 1];
		for (int i = 0; i != temp.length; i++)
			init[i] = temp[i];
		init[init.length - 1] = null;
	}

	public void add(JSONObj o) {
		Object[] temp = init;
		init = new Object[temp.length + 1];
		for (int i = 0; i != temp.length; i++)
			init[i] = temp[i];
		init[init.length - 1] = o;
	}

	public void add(JSONArr o) {
		Object[] temp = init;
		init = new Object[temp.length + 1];
		for (int i = 0; i != temp.length; i++)
			init[i] = temp[i];
		init[init.length - 1] = o;
	}

	public Object get(int i) {
		return init[i];
	}

	public int size() {
		return init.length;
	}

	public int getInt(int i) {
		return (Integer) init[i];
	}

	public double getDouble(int i) {
		return (Double) init[i];
	}

	public String getString(int i) {
		return (String) init[i];
	}

	public JSONArr getArr(int i) {
		return (JSONArr) init[i];
	}

	public JSONObj getObj(int i) {
		return (JSONObj) init[i];
	}

	public boolean getBoolean(int i) {
		return (Boolean) init[i];
	}

	public Object[] toArray() {
		return init;
	}

	public Object remove(int i) {
		Object[] temp = init;
		init = new Object[temp.length - 1];
		for (int j = 0; j != temp.length; j++) {
			if (j == i)
				continue;
			init[j] = temp[j];
		}
		return temp[i];
	}

	public String toString() {
		return JSON.stringify(this);
	}

	public static JSONArr parse(String s) throws JSONException {
		try {
			return JSON.parseArr(s);
		} catch (JSONException e) {
			throw e;
		}
	}
}