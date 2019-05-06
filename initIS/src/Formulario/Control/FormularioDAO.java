package Formulario.Control;

import org.json.JSONArray;

public interface FormularioDAO {
	
	public abstract JSONArray getFormularies();
	public abstract void insertFormulary(FormularioDTO newForm);
	public abstract void deleteFormulary(int n);
	public abstract void insertGame(int n);
	
}
