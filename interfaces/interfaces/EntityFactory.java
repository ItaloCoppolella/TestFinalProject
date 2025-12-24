package interfaces;

import java.util.Map;

import entities.*;


public interface EntityFactory {
	
	static Entity make(String s) {
		Entity e = null;
		
		switch (s.toLowerCase()) {
		case "cliente":
			e = new Cliente();
			break;
		case "dipendente":
			e = new Dipendente();
			break;
		case "gita":
			e = new Gita();
			break;
		case "prenotazione":
			e = new Prenotazione();
			break;
		case "equipaggio":
			e = new Equipaggio();
			break;
		
		default:
			System.out.println("non conosco questa entità");
			break;
		}
		return e;
	}
	
	static Entity make(Map<String, String> map) {
		Entity e = null;
		
		if(map.containsKey("elemento")) {
			e = make(map.get("elemento"));
		}
		map.remove("elemento");
		if(e != null) {
			e.fromMap(map);
		}
		
		return e;
		
	}
}
