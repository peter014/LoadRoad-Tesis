package app.dto;

import java.math.BigDecimal;
import java.util.List;

public class EmpresasPedidoDTO {

	private BigDecimal idPedido;
	private List<EmpresasDTO> empresas;
	
	public EmpresasPedidoDTO(BigDecimal idPedido, List<EmpresasDTO> empresas) {
		super();
		this.idPedido = idPedido;
		this.empresas = empresas;
	}
	public BigDecimal getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(BigDecimal idPedido) {
		this.idPedido = idPedido;
	}
	public List<EmpresasDTO> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<EmpresasDTO> empresas) {
		this.empresas = empresas;
	}
	
	
}
