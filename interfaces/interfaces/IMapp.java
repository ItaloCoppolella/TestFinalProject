package interfaces;

import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public interface IMapp {


	default void fromMap(Map<String, String> map) {
		for(Method m : this.getClass().getMethods()) {
			if(m.getName().startsWith("set") && m.getParameterCount() == 1) {
				String nomeProp = m.getName().substring(3,4).toLowerCase() + m.getName().substring(4);
				if(map.containsKey(nomeProp)) {
					String valore = map.get(nomeProp);
					if(valore == null) {
						continue;
					}
					try {
						String tipoParametro = m.getParameters()[0].getType().getSimpleName().toLowerCase();
						switch (tipoParametro) {
						case "string":
							m.invoke(this, valore);
							break;
						case "int":
							m.invoke(this, Integer.parseInt(valore));
							break;
						case "double":
							m.invoke(this, Double.parseDouble(valore));
							break;
						case "boolean":
							m.invoke(this, Integer.parseInt(valore) == 1 ? true : false);
							break;
						case"date":
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
						    try {
						        java.util.Date date = dateFormat.parse(valore);
						        m.invoke(this, date);
						    } catch (ParseException e) {
						        e.printStackTrace();
						        System.out.println("Errore nel parsing della data");
						    }
							break;

						default:
							System.out.println("tipo non riconosciuto");
							break;
						}
					} catch (Exception e) {
						System.out.println("controlla valori");
					}
				}
			}
		}
	}
		
	//Integer.parseInt(valore) == 1 ? true : false)
	//-------------------------------------------------------------------------------------------

	default Map<String, String> toMap() {
		Map<String, String> ris = new LinkedHashMap<String, String>();
		for(Method m : this.getClass().getMethods()) {
			if((m.getName().startsWith("get") || m.getName().startsWith("is"))
					&& !m.getName().startsWith("getClass") && 
					m.getParameterCount() == 0) {
				try {
					int i = m.getName().startsWith("get") ? 3 : 2;
					String nomeProp = m.getName().substring(i).toLowerCase();
					ris.put(nomeProp, m.invoke(this) + "");

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return ris;
	}

}
