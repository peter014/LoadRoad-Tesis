package LRprueba.prueb;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.configurations.Constantes;
import app.controllers.EmpresaRestController;
import app.dto.CamionDTO;
import app.dto.CamionDashDTO;
import app.dto.IngresoDTO;
import app.dto.NotificacionCargaDTO;
import app.dto.RegistroEmpresaDTO;
import app.dto.TransportadorDTO;
import app.dto.TransportadorDashDTO;
import app.entities.Calificacion;
import app.entities.Camion;
import app.entities.Carga;
import app.entities.Cliente;
import app.entities.Empresa;
import app.entities.Pedido;
import app.entities.Transportador;
import app.entities.Usuario;
import app.repository.CamionRepository;
import app.repository.EmpresaRepository;
import app.repository.PedidoRepository;
import app.repository.TransportadorRepository;
import app.repository.TrayectoRepository;
import app.repository.UsuarioRepository;
import app.services.ServicioEmpresa;

@RunWith(MockitoJUnitRunner.class)
public class ServiceEmpresaTest {

	private MockMvc mockMvc;
	
	/** The Constant APPLICATION_JSON. */
	// Constants
	private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
	
	private Gson parser;
	
	private MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	
	@Mock
	EmpresaRepository EmpresaRepo;

	@Mock
	UsuarioRepository usuarioRepo;
	
	@Mock	
	PedidoRepository pedidoRepo;
	
	@Mock
	TransportadorRepository transportadorRepo;
	
	@Mock
	CamionRepository camionRepo;
	
	@Mock
	TrayectoRepository trayectoRepo;
	
	@Spy
	@InjectMocks
	private ServicioEmpresa service;
	
	@InjectMocks
	private EmpresaRestController controller;
	
	@Before
	public void configure() {
		mockMvc = standaloneSetup(controller).build();
    	MockitoAnnotations.initMocks(this);
    	parser = new GsonBuilder().create();
	}
	
	@Test
	public void ingresarTest() throws Exception{
		Empresa e = new Empresa();
		e.setId(new BigDecimal(1));
		List<Empresa> l = new ArrayList<>();
		l.add(e);
		Usuario u = new Usuario();
		u.setEmpresaList(l);
		u.setClave("pipeli");
		Mockito.when(usuarioRepo.findUsuario("davip@hotmail")).thenReturn(u);
		IngresoDTO dto = new IngresoDTO("davip@hotmail", "pipeli");
		
		mockMvc.perform(post("/LoadRoad/Empresa/ingresar").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
	}
	
	@Test
	public void ingresarErrorTest() throws Exception{
		Empresa e = new Empresa();
		e.setId(new BigDecimal(1));
		List<Empresa> l = new ArrayList<>();
		l.add(e);
		Usuario u = new Usuario();
		u.setEmpresaList(l);
		u.setClave("p");
		Mockito.when(usuarioRepo.findUsuario("davip@hotmail")).thenReturn(u);
		IngresoDTO dto = new IngresoDTO("davip@hotmail", "pipeli");
		
		mockMvc.perform(post("/LoadRoad/Empresa/ingresar").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
	}
	
	@Test
	public void registroTestException() throws Exception{
		RegistroEmpresaDTO dto = new RegistroEmpresaDTO("fel@gsmail", "fet", "felps", "20111234", "gene", null, "100", 
				"100", "Bancolom");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		mockMvc.perform(post("/LoadRoad/Empresa/registro").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}
	
	@Test
	public void registroTest() throws Exception{
		Usuario nuevoUsuario = new Usuario("felipRo@gsmail", "fe", "felips", null, null, null);
		Empresa nuevaEmpresa = new Empresa("20111234", "gene", new BigDecimal("1000"), new BigDecimal("100"),"123","Bancolom",nuevoUsuario,new BigDecimal("0"));
		
		Mockito.when(usuarioRepo.saveAndFlush(nuevoUsuario)).thenReturn(null);
		Mockito.when(EmpresaRepo.saveAndFlush(nuevaEmpresa)).thenReturn(null);
		
		RegistroEmpresaDTO dto = new RegistroEmpresaDTO("fel@gsmail", "fet", "felps", "20111234", "gene", "10000", "100", 
				"100", "Bancolom");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		mockMvc.perform(post("/LoadRoad/Empresa/registro").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}

	@Test
	public void revisarSolicitudesVacioTest() throws Exception{
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "1");
		Matcher<List<NotificacionCargaDTO>> matcher = new IsEqual<List<NotificacionCargaDTO>>(new ArrayList<>());
		Empresa e = new Empresa();
		e.setPedidoList(new ArrayList<>());
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(1))).thenReturn(e);
		sendGet("/LoadRoad/Empresa/revisarSolicitudes",params, jsonPath("$", matcher));
	}

	@Test
	public void revisarSolicitudesTest() throws Exception{
		Usuario us = new Usuario();
		us.setNombre("David");
		Cliente cl = new Cliente();
		cl.setUsuarioFk(us);
		Carga c = new Carga();
		List<Carga> cargas = new ArrayList<>();
		cargas.add(c);
		Pedido p = new Pedido();
		p.setCargaList(cargas);
		p.setClienteFk(cl);
		p.setEstado(Constantes.getPendiente());
		p.setHoraPedido(new Date());
		p.setId(new BigDecimal(600));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Empresa Empresa = new Empresa();
		Empresa.setPedidoList(list);
		
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		Matcher<String> matcher = new IsEqual<String>("600");
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(Empresa);
		sendGet("/LoadRoad/Empresa/revisarSolicitudes",params, jsonPath("$[0].idPedido", matcher));
	}
	
	@Test
	public void getInformacionTest() throws Exception{
		Usuario u = new Usuario();
		u.setNombre("David");
		Empresa Empresa = new Empresa();
		Empresa.setNit("1234");
		Empresa.setUsuarioFk(u);
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(Empresa);
		Matcher<String> matcher = new IsEqual<String>("1234");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/getInformacion",params, jsonPath("$.nit", matcher));
	}

	@Test
	public void getInformacionVacioTest() throws Exception{
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(null);
		Matcher<String> matcher = new IsEqual<String>("0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/getInformacion",params, jsonPath("$.nit", matcher));
	}
	
	@Test
	public void detalleSolicitudTest() throws Exception{
		Camion cam = new Camion();
		cam.setAlto(new BigDecimal(2));
		cam.setAncho(new BigDecimal(2));
		cam.setLargo(new BigDecimal(2));
		cam.setPeso(new BigDecimal(2));
		cam.setEstado(Constantes.getDisponible());
		cam.setCiudad("Bogota");
		List<Camion> list = new ArrayList<>();
		list.add(cam);
		Empresa e = new Empresa();
		e.setCamionList(list);
		
		Carga c = new Carga();
		c.setAlto(new BigDecimal(1));
		c.setAncho(new BigDecimal(1));
		c.setLargo(new BigDecimal(1));
		c.setPeso(new BigDecimal(1));
		c.setValor(new BigDecimal(1));
		List<Carga> cargas = new ArrayList<>();
		cargas.add(c);
		Pedido p = new Pedido();
		p.setEmpresaFk(e); 
		p.setCargaList(cargas);
		p.setEstado(Constantes.getPendiente());
		p.setHoraPedido(new Date());
		p.setId(new BigDecimal(600));
		p.setAuxiliarCarga((short) 2);
		p.setAuxiliarDescarga((short) 2);
		p.setFechaHoraLlegada(new Date());
		p.setFechaHoraSalida(new Date());
		p.setHoraPedido(new Date());
		p.setLugarCargaCiudad("Bogota");
		
		Usuario us = new Usuario();
		us.setNombre("Dav");
		Cliente cli = new Cliente(new BigDecimal(1));
		cli.setUsuarioFk(us);
		p.setClienteFk(cli);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		Matcher<String> matcher = new IsEqual<String>("1");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		sendGet("/LoadRoad/Empresa/detalleSolicitud",params, jsonPath("$.peso", matcher));
	}
	
	@Test
	public void detalleSolicitudNoCamionTest() throws Exception{
		Camion cam = new Camion();
		cam.setPeso(new BigDecimal(0));
		cam.setEstado(Constantes.getDisponible());
		cam.setCiudad("Bogota");
		List<Camion> list = new ArrayList<>();
		list.add(cam);
		Empresa e = new Empresa();
		e.setCamionList(list);
		
		Carga c = new Carga();
		c.setAlto(new BigDecimal(1));
		c.setAncho(new BigDecimal(1));
		c.setLargo(new BigDecimal(1));
		c.setPeso(new BigDecimal(1));
		c.setValor(new BigDecimal(1));
		List<Carga> cargas = new ArrayList<>();
		cargas.add(c);
		Pedido p = new Pedido();
		p.setEmpresaFk(e); 
		p.setCargaList(cargas);
		p.setEstado(Constantes.getPendiente());
		p.setHoraPedido(new Date());
		p.setId(new BigDecimal(600));
		p.setAuxiliarCarga((short) 2);
		p.setAuxiliarDescarga((short) 2);
		p.setFechaHoraLlegada(new Date());
		p.setFechaHoraSalida(new Date());
		p.setHoraPedido(new Date());
		p.setLugarCargaCiudad("Bogota");
		
		Usuario us = new Usuario();
		us.setNombre("Dav");
		Cliente cli = new Cliente(new BigDecimal(1));
		cli.setUsuarioFk(us);
		p.setClienteFk(cli);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		Matcher<String> matcher = new IsEqual<String>(null);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		sendGet("/LoadRoad/Empresa/detalleSolicitud",params, jsonPath("$.peso", matcher));
	}
	
	@Test
	public void getCamionesTest() throws Exception{
		Pedido p = new Pedido(new BigDecimal(100));
		p.setLugarCargaCiudad("Bogota");
		Camion cam = new Camion();
		cam.setAlto(new BigDecimal(2));
		cam.setAncho(new BigDecimal(2));
		cam.setLargo(new BigDecimal(2));
		cam.setPeso(new BigDecimal(2));
		cam.setEstado(Constantes.getDisponible());
		cam.setCiudad("Bogota");
		cam.setId(new BigDecimal(1));
		List<Camion> list = new ArrayList<>();
		list.add(cam);
		Empresa em = new Empresa();
		em.setCamionList(list);
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		params.add("idEmpresa", "100");
		Matcher<String> matcher = new IsEqual<String>("2");
		sendGet("/LoadRoad/Empresa/getCamiones",params, jsonPath("$[0].peso", matcher));
	}
	@Test
	public void getCamionesVacioTest() throws Exception{
		Pedido p = new Pedido(new BigDecimal(100));
		p.setLugarCargaCiudad("Bogota");
		Camion cam = new Camion(new BigDecimal(1));
		cam.setEstado(Constantes.getOcupado());
		cam.setCiudad("Bogota");

		List<Camion> list = new ArrayList<>();
		list.add(cam);
		Empresa em = new Empresa();
		em.setCamionList(list);
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		params.add("idEmpresa", "100");
		Matcher<List<CamionDTO>> matcher = new IsEqual<List<CamionDTO>>(new ArrayList<>());
		sendGet("/LoadRoad/Empresa/getCamiones",params, jsonPath("$", matcher));
	}
	
	@Test
	public void getTransportadoresTest() throws Exception{
		Pedido p = new Pedido(new BigDecimal(100));
		p.setLugarCargaCiudad("Bogota");
		
		Usuario us = new Usuario("us", "cla", "David", null, null, null);
		Transportador trans = new Transportador(new BigDecimal(1));
		us.setId(new BigDecimal(1));
		trans.setCedula("123");
		trans.setEstado(Constantes.getDisponible());
		trans.setCiudad("Bogota");
		trans.setUsuarioFk(us);
		List<Transportador> list = new ArrayList<>();
		list.add(trans);
		Empresa em = new Empresa();
		em.setTransportadorList(list);
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		params.add("idEmpresa", "100");
		Matcher<String> matcher = new IsEqual<String>("123");
		sendGet("/LoadRoad/Empresa/getTransportadores",params, jsonPath("$[0].cedula", matcher));
	}
	
	@Test
	public void getTransportadoresVacioTest() throws Exception{
		Transportador trans = new Transportador(new BigDecimal(1));
		trans.setEstado(Constantes.getEnmarcha());
		List<Transportador> list = new ArrayList<>();
		list.add(trans);
		Empresa em = new Empresa();
		em.setTransportadorList(list);
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(new Pedido());
		
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		params.add("idEmpresa", "100");
		Matcher<List<TransportadorDTO>> matcher = new IsEqual<List<TransportadorDTO>>(new ArrayList<>());
		sendGet("/LoadRoad/Empresa/getTransportadores",params, jsonPath("$", matcher));
	}
	
	@Test
	public void rechazarPedidoTest() throws Exception{
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(new Pedido());
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		sendGet("/LoadRoad/Empresa/rechazarPedido",params, jsonPath("$", matcher));
	}
	
	@Test
	public void rechazarPedidoMalTest() throws Exception{
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(null);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		sendGet("/LoadRoad/Empresa/rechazarPedido",params, jsonPath("$", matcher));
	}
	
	@Test
	public void aceptarPedidoTest() throws Exception{
		Pedido p = new Pedido();
		p.setLugarCargaCord("213.2421,124.25");
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(100))).thenReturn(new Transportador());
		Mockito.when(camionRepo.findOne(new BigDecimal(100))).thenReturn(new Camion());
		
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		params.add("idTransportador", "100");
		params.add("idCamion", "100");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		sendGet("/LoadRoad/Empresa/aceptarPedido",params, jsonPath("$", matcher));
	}
	
	@Test
	public void aceptarPedidoMalTest() throws Exception{
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(null);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(100))).thenReturn(new Transportador());
		Mockito.when(camionRepo.findOne(new BigDecimal(100))).thenReturn(new Camion());
		
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		params.add("idTransportador", "100");
		params.add("idCamion", "100");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		sendGet("/LoadRoad/Empresa/aceptarPedido",params, jsonPath("$", matcher));
	}
	
	@Test
	public void precioDashTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Empresa em = new Empresa();
		em.setPedidoList(list);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>((cale.get(Calendar.MONTH))+"");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/precioDash", params, jsonPath("$[0].mes", matcher));
	}

	@Test
	public void precioDashMonthsTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		cale.set(cale.get(Calendar.YEAR), cale.get(Calendar.MONTH)+1, cale.get(Calendar.DAY_OF_WEEK)); 
		p = new Pedido();
		p.setHoraPedido(cale.getTime());
		p.setPrecio(new BigDecimal(50));
		list.add(p);
		Empresa em = new Empresa();
		em.setPedidoList(list);
		
		cale.setTime(new Date());
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>((cale.get(Calendar.MONTH))+"");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/precioDash", params, jsonPath("$[0].mes", matcher));
	}
	
	@Test
	public void ciudadesDashTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setLugarCargaCiudad("Bogotá");
		p.setLugarDescargaCiudad("Medellin");
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Empresa em = new Empresa();
		em.setPedidoList(list);
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>("Bogotá");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/ciudadesDash", params, jsonPath("$[0].origen", matcher));
	}
	
	@Test
	public void ciudadesDash2CiudadesTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setLugarCargaCiudad("Bogotá");
		p.setLugarDescargaCiudad("Medellin");
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		p = new Pedido();
		p.setLugarCargaCiudad("Bogotá");
		p.setLugarDescargaCiudad("Cali");
		list.add(p);
		Empresa em = new Empresa();
		em.setPedidoList(list);
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>("Bogotá");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/ciudadesDash", params, jsonPath("$[0].origen", matcher));
	}

	@Test
	public void camionesDashTest() throws Exception{
		
		Camion cam = new Camion("123 fsd","Mazda","3","2000","6 Ejes",new BigDecimal(0),new BigDecimal(0),
				new BigDecimal(0),new BigDecimal(0),Constantes.getDisponible(),Constantes.getCiudades()[0]);
		List<Camion> list = new ArrayList<>();
		list.add(cam);
		Empresa em = new Empresa();
		em.setCamionList(list);
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>(Constantes.getCiudades()[0]);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/camionesDash", params, jsonPath("$[0].ciudad", matcher));
	}
	
	@Test
	public void camionesDashNullTest() throws Exception{
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(null);
		Matcher<List<CamionDashDTO>> matcher = new IsEqual<List<CamionDashDTO>>(new ArrayList<>());
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/camionesDash", params, jsonPath("$", matcher));
	}
	
	@Test
	public void transportadoresDashTest() throws Exception{
		Usuario us = new Usuario();
		us.setNombre("David");
		Transportador tr = new Transportador("12354", "1014",Constantes.getDisponible(),Constantes.getCiudades()[0], us);
		List<Transportador> list = new ArrayList<>();
		list.add(tr);
		Empresa em = new Empresa();
		em.setTransportadorList(list);
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>(Constantes.getCiudades()[0]);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/transportadoresDash", params, jsonPath("$[0].ciudad", matcher));
	}
	
	@Test
	public void transportadoresDashNullTest() throws Exception{
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(null);
		Matcher<List<TransportadorDashDTO>> matcher = new IsEqual<List<TransportadorDashDTO>>(new ArrayList<>());
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/transportadoresDash", params, jsonPath("$", matcher));
	}
	
	@Test
	public void gananciasAnioTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Empresa em = new Empresa();
		em.setPedidoList(list);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>("50.0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/gananciasAnio", params, jsonPath("$.valor", matcher));
	}
	
	@Test
	public void gananciasMesTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Empresa em = new Empresa();
		em.setPedidoList(list);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>("50.0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/gananciasMes", params, jsonPath("$.valor", matcher));
	}
	
	@Test
	public void gananciasDiaTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Empresa em = new Empresa();
		em.setPedidoList(list);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<String> matcher = new IsEqual<String>("50.0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/gananciasDia", params, jsonPath("$.valor", matcher));
	}
	
	@Test
	public void dashCalificacionTest() throws Exception{
		
		Calificacion cal = new Calificacion(new BigDecimal(5),new Date(),null);
		List<Calificacion> list = new ArrayList<>();
		list.add(cal);
		Empresa em = new Empresa();
		em.setCalificacion(list);
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<Double> matcher = new IsEqual<Double>(5.0);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/dashCalificacion", params, jsonPath("$[0].calificacion", matcher));
	}
	
	@Test
	public void dashCalificacionMonthsTest() throws Exception{
		
		Calificacion cali = new Calificacion(new BigDecimal(5),new Date(),null);
		List<Calificacion> list = new ArrayList<>();
		list.add(cali);
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.YEAR, Calendar.MONTH+1, Calendar.DATE);
		cali = new Calificacion(new BigDecimal(5),cale.getTime(),null);
		list.add(cali);
		Empresa em = new Empresa();
		em.setCalificacion(list);
		
		Mockito.when(EmpresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Matcher<Double> matcher = new IsEqual<Double>(5.0);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Empresa/dashCalificacion", params, jsonPath("$[0].calificacion", matcher));
	}
	
	private void sendGet(String url, MultiValueMap<String, String> params, ResultMatcher expected) throws Exception{
		mockMvc.perform(get(url).params(params)).andExpect(status().isOk()).andExpect(expected);
	}
}
