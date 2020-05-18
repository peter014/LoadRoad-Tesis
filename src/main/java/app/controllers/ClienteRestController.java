package app.controllers;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dto.CiudadesDashDTO;
import app.dto.CoordenadasDTO;
import app.dto.EmpresasPedidoDTO;
import app.dto.GananciaDTO;
import app.dto.InfoCargaDTO;
import app.dto.InfoUsuarioDTO;
import app.dto.IngresoDTO;
import app.dto.NotificacionCargaDTO;
import app.dto.PrecioDashDTO;
import app.dto.RegistroClienteDTO;
import app.interfaces.ServicioClienteInterface;


@RestController
@RequestMapping("/LoadRoad/Cliente")
public class ClienteRestController 
{
	@Autowired
    private ServicioClienteInterface service;
	
	@CrossOrigin("*")
	@RequestMapping(value = "/getInformacion", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<InfoUsuarioDTO> getUsers(@RequestParam String idCliente) {
		return new ResponseEntity<>(service.getInfoCliente(idCliente),HttpStatus.OK);
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/ingresar", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> ingreso(@RequestBody IngresoDTO ingreso) {
		return new ResponseEntity<>(service.ingresar(ingreso),HttpStatus.OK);
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/registro", method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public ResponseEntity<Boolean> registro(@RequestBody RegistroClienteDTO registro) {
		try{
			return new ResponseEntity<>(service.registrarse(registro),HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/buscar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmpresasPedidoDTO> buscarEmpresa(@RequestBody InfoCargaDTO buscar) {
		return new ResponseEntity<>(service.buscarEmpresa(buscar),HttpStatus.OK);
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/seleccionar", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> seleccionarEmpresa(@RequestParam String idPedido, @RequestParam String idEmpresa) {
		return new ResponseEntity<>(service.seleccionarEmpresa(idPedido, idEmpresa),HttpStatus.OK);
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/revisarSolicitudes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<NotificacionCargaDTO>> revisarSolicitudes(@RequestParam String idCliente){
		return new ResponseEntity<>(service.revisarSolicitudes(new BigDecimal(idCliente)),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/todosPedidos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<InfoCargaDTO>> todosPedidos(@RequestParam String idCliente){
		return new ResponseEntity<>(service.todosPedidos(new BigDecimal(idCliente)),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/monitorear", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CoordenadasDTO> monitorear(@RequestParam String idPedido){
		return new ResponseEntity<>(service.monitorear(new BigDecimal(idPedido)),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/finalizarMonitoreo", method = RequestMethod.GET, produces = "application/json")
	public void finalizarMonitoreo(@RequestParam String idPedido){
		service.finMonitoreo(new BigDecimal(idPedido));
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/calificar", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> calificar(@RequestParam String idEmpresa, @RequestParam String calificacion){
		return new ResponseEntity<>(service.calificar(idEmpresa,calificacion),HttpStatus.OK);
	}	
	@CrossOrigin("*")
	@RequestMapping(value = "/precioDash", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PrecioDashDTO>> precioDash(@RequestParam String idCliente){
		return new ResponseEntity<>(service.precioDash(idCliente),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/ciudadesDash", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CiudadesDashDTO>> ciudadesDash(@RequestParam String idCliente){
		return new ResponseEntity<>(service.ciudadesDash(idCliente),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/gastosAnio", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GananciaDTO> gastosAño(@RequestParam String idCliente){
		return new ResponseEntity<>(service.gastosAño(idCliente),HttpStatus.OK);
	}	
	
	@CrossOrigin("*")
	@RequestMapping(value = "/gastosMes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GananciaDTO> gastosMes(@RequestParam String idCliente){
		return new ResponseEntity<>(service.gastosMes(idCliente),HttpStatus.OK);
	}	
	
	@CrossOrigin("*")
	@RequestMapping(value = "/gastosDia", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GananciaDTO> gastosDia(@RequestParam String idCliente){
		return new ResponseEntity<>(service.gastosDia(idCliente),HttpStatus.OK);
	}	
}
