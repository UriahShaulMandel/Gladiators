package main.external_libraries.heinemanns_json;

public class JSONObj {
	private Object[] init;
	private String[] keys;

	public JSONObj() {
		init = new Object[0];
		keys = new String[0];
	}

	public JSONObj(Object[] o, String[] s) {
		init = o;
		keys = s;
	}

	public JSONObj(String s) {
		try {
			JSONObj j = JSON.parseObj(s);
			init = j.toArray();
			keys = j.keys();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void set(String s, int o) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				init[i] = new Integer(o);
				return;
			}
		}
		Object[] tempo = init;
		String[] temps = keys;
		keys = new String[temps.length + 1];
		init = new Object[tempo.length + 1];
		for (int i = 0; i != tempo.length; i++) {
			keys[i] = temps[i];
			init[i] = tempo[i];
		}
		init[init.length - 1] = new Integer(o);
		keys[keys.length - 1] = s;
	}

	public void set(String s, double o) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				init[i] = new Double(o);
				return;
			}
		}
		Object[] tempo = init;
		String[] temps = keys;
		keys = new String[temps.length + 1];
		init = new Object[tempo.length + 1];
		for (int i = 0; i != tempo.length; i++) {
			keys[i] = temps[i];
			init[i] = tempo[i];
		}
		init[init.length - 1] = new Double(o);
		keys[keys.length - 1] = s;
	}

	public void set(String s, boolean o) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				init[i] = new Boolean(o);
				return;
			}
		}
		Object[] tempo = init;
		String[] temps = keys;
		keys = new String[temps.length + 1];
		init = new Object[tempo.length + 1];
		for (int i = 0; i != tempo.length; i++) {
			keys[i] = temps[i];
			init[i] = tempo[i];
		}
		init[init.length - 1] = new Boolean(o);
		keys[keys.length - 1] = s;
	}

	public void set(String s, String o) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				init[i] = o;
				return;
			}
		}
		Object[] tempo = init;
		String[] temps = keys;
		keys = new String[temps.length + 1];
		init = new Object[tempo.length + 1];
		for (int i = 0; i != tempo.length; i++) {
			keys[i] = temps[i];
			init[i] = tempo[i];
		}
		init[init.length - 1] = o;
		keys[keys.length - 1] = s;
	}

	public void setNull(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				init[i] = null;
				return;
			}
		}
		Object[] tempo = init;
		String[] temps = keys;
		keys = new String[temps.length + 1];
		init = new Object[tempo.length + 1];
		for (int i = 0; i != tempo.length; i++) {
			keys[i] = temps[i];
			init[i] = tempo[i];
		}
		init[init.length - 1] = null;
		keys[keys.length - 1] = s;
	}

	public void set(String s, JSONArr o) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				init[i] = o;
				return;
			}
		}
		Object[] tempo = init;
		String[] temps = keys;
		keys = new String[temps.length + 1];
		init = new Object[tempo.length + 1];
		for (int i = 0; i != tempo.length; i++) {
			keys[i] = temps[i];
			init[i] = tempo[i];
		}
		init[init.length - 1] = o;
		keys[keys.length - 1] = s;
	}

	public void set(String s, JSONObj o) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				init[i] = o;
				return;
			}
		}
		Object[] tempo = init;
		String[] temps = keys;
		keys = new String[temps.length + 1];
		init = new Object[tempo.length + 1];
		for (int i = 0; i != tempo.length; i++) {
			keys[i] = temps[i];
			init[i] = tempo[i];
		}
		init[init.length - 1] = o;
		keys[keys.length - 1] = s;
	}

	public Object get(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				return init[i];
			}
		}
		return null;
	}

	public Object delete(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				Object[] tempo = init;
				String[] temps = keys;
				init = new Object[tempo.length - 1];
				keys = new String[temps.length - 1];
				for (int j = 0; j != tempo.length; j++) {
					if (j == i)
						continue;
					keys[j] = temps[j];
					init[j] = tempo[j];
				}
				return tempo[i];
			}
		}
		return null;
	}

	public String[] keys() {
		return keys;
	}

	public Object[] toArray() {
		return init;
	}

	public String getString(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				return (String) init[i];
			}
		}
		return null;
	}

	public int getInt(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				return (Integer) init[i];
			}
		}
		return 0;
	}

	public double getDouble(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				return (Double) init[i];
			}
		}
		return 0;
	}

	public boolean getBoolean(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				return (Boolean) init[i];
			}
		}
		return false;
	}

	public JSONObj getObj(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				return (JSONObj) init[i];
			}
		}
		return null;
	}

	public JSONArr getArr(String s) {
		for (int i = 0; i != keys.length; i++) {
			if (s.equals(keys[i])) {
				return (JSONArr) init[i];
			}
		}
		return null;
	}

	public String toString() {
		return JSON.stringify(this);
	}

	public static JSONObj parse(String s) throws JSONException {
		try {
			return JSON.parseObj(s);
		} catch (JSONException e) {
			throw e;
		}
	}
}