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

import app.dto.CalificacionDashDTO;
import app.dto.CamionDTO;
import app.dto.CamionDashDTO;
import app.dto.CiudadesDashDTO;
import app.dto.GananciaDTO;
import app.dto.InfoCargaDTO;
import app.dto.InfoUsuarioDTO;
import app.dto.IngresoDTO;
import app.dto.NotificacionCargaDTO;
import app.dto.PrecioDashDTO;
import app.dto.RegistroEmpresaDTO;
import app.dto.TransportadorDTO;
import app.dto.TransportadorDashDTO;
import app.interfaces.ServicioEmpresaInterface;

@RestController
@RequestMapping("/LoadRoad/Empresa")
public class EmpresaRestController {

	@Autowired
    private ServicioEmpresaInterface service;
	
	@CrossOrigin("*")
	@RequestMapping(value = "/ingresar", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> ingreso(@RequestBody IngresoDTO ingreso) {
		return new ResponseEntity<>(service.ingresar(ingreso),HttpStatus.OK);
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/registro", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Boolean> registro(@RequestBody RegistroEmpresaDTO registro) {
		try{
			if(service.registrarse(registro)){
				return new ResponseEntity<>(true,HttpStatus.OK);
			}
		}catch(Exception e){
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
		return new ResponseEntity<>(false,HttpStatus.OK);
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/detalleSolicitud", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<InfoCargaDTO> detalleSolicitud(@RequestParam String idPedido){
		return new ResponseEntity<>(service.detalleSolicitud(new BigDecimal(idPedido)),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/revisarSolicitudes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<NotificacionCargaDTO>> revisarSolicitudes(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.revisarSolicitudes(new BigDecimal(idEmpresa)),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/getCamiones", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CamionDTO>> getCamiones(@RequestParam String idEmpresa,@RequestParam String idPedido){
		return new ResponseEntity<>(service.getCamiones(new BigDecimal(idEmpresa),new BigDecimal(idPedido)),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/getTransportadores", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<TransportadorDTO>> getTransportadores(@RequestParam String idEmpresa,@RequestParam String idPedido){
		return new ResponseEntity<>(service.getTransportadores(new BigDecimal(idEmpresa),new BigDecimal(idPedido)),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/getInformacion", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<InfoUsuarioDTO> getUsers(@RequestParam String idEmpresa) {
		return new ResponseEntity<>(service.getInfoEmpresa(idEmpresa),HttpStatus.OK);
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/rechazarPedido", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> rechazarPedido(@RequestParam String idPedido) {
		return new ResponseEntity<>(service.rechazarPedido(idPedido),HttpStatus.OK);
    }
	
	@CrossOrigin("*")
	@RequestMapping(value = "/aceptarPedido", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> aceptarPedido(@RequestParam String idPedido,@RequestParam String idTransportador,
    		@RequestParam String idCamion) {
		try{
			return new ResponseEntity<>(service.aceptarPedido(idPedido,idTransportador,idCamion),HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e);
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
    }
	
	
	@CrossOrigin("*")
	@RequestMapping(value = "/dashCalificacion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CalificacionDashDTO>> dashCalificacion(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.dashCalificacion(idEmpresa),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/precioDash", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PrecioDashDTO>> precioDash(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.precioDash(idEmpresa),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/ciudadesDash", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CiudadesDashDTO>> ciudadesDash(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.ciudadesDash(idEmpresa),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/camionesDash", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CamionDashDTO>> camionesDash(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.camionesDash(idEmpresa),HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/transportadoresDash", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<TransportadorDashDTO>> transportadoresDash(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.transportadoresDash(idEmpresa),HttpStatus.OK);
	}
	@CrossOrigin("*")
	@RequestMapping(value = "/gananciasAnio", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GananciaDTO> gananciasAño(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.gananciasAño(idEmpresa),HttpStatus.OK);
	}	
	
	@CrossOrigin("*")
	@RequestMapping(value = "/gananciasMes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GananciaDTO> gananciasMes(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.gananciasMes(idEmpresa),HttpStatus.OK);
	}	
	
	@CrossOrigin("*")
	@RequestMapping(value = "/gananciasDia", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GananciaDTO> gananciasDia(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.gananciasDia(idEmpresa),HttpStatus.OK);
	}	
	
	@CrossOrigin("*")
	@RequestMapping(value = "/todosPedidos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<InfoCargaDTO>> todosPedidos(@RequestParam String idEmpresa){
		return new ResponseEntity<>(service.todosPedidos(new BigDecimal(idEmpresa)),HttpStatus.OK);
	}
}
