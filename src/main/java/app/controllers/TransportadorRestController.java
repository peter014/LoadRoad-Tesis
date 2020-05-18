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

import app.dto.CoordenadasDTO;
import app.dto.InfoCargaDTO;
import app.dto.InfoUsuarioDTO;
import app.dto.IngresoDTO;
import app.dto.RegistroTransportradorDTO;
import app.interfaces.ServicioTransportadorInterface;

@RestController
@RequestMapping("/LoadRoad/Transportador")
public class TransportadorRestController {

	@Autowired
	private ServicioTransportadorInterface service;

	@CrossOrigin("*")
	@RequestMapping(value = "/ingresar", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> ingreso(@RequestBody IngresoDTO ingreso) {
		return new ResponseEntity<>(service.ingresar(ingreso), HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/registro", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Boolean> registro(@RequestBody RegistroTransportradorDTO registro) {
		try {
			return new ResponseEntity<>(service.registrarse(registro), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/revisarMonitoreo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> revisarMonitoreo(@RequestParam String idTransportador,
			@RequestParam String idPedido) {
		return new ResponseEntity<>(service.revisarMonitoreo(idTransportador, idPedido), HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/guardarCoordenadas", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Boolean> guardarCoordenadas(@RequestBody CoordenadasDTO coordenadas) {
		return new ResponseEntity<>(service.recibirCoordenadas(coordenadas), HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/cambiarEstado", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> cambiarEstado(@RequestParam String idTransportador, @RequestParam String estado,
			@RequestParam String idPedido) {
		return new ResponseEntity<>(service.cambiarEstado(idTransportador, estado, idPedido), HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/detalleSolicitudes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<InfoCargaDTO>> detalleSolicitud(@RequestParam String idTransportador) {
		return new ResponseEntity<>(service.detalleSolicitud(new BigDecimal(idTransportador)), HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/getInformacion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<InfoUsuarioDTO> getUsers(@RequestParam String idTransportador) {
		return new ResponseEntity<>(service.getInfoTransportador(idTransportador), HttpStatus.OK);
	}

}
