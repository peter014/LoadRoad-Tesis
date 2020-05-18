package app.dto;

import java.util.List;

public class ListDTO {

	private List<UsuarioDTO> list;

	
	
	public ListDTO(List<UsuarioDTO> list) {
		super();
		this.list = list;
	}

	public List<UsuarioDTO> getList() {
		return list;
	}

	public void setList(List<UsuarioDTO> list) {
		this.list = list;
	}
	
	
	
}
