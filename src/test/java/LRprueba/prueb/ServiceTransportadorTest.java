package LRprueba.prueb;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import app.controllers.TransportadorRestController;
import app.dto.CoordenadasDTO;
import app.dto.InfoCargaDTO;
import app.dto.IngresoDTO;
import app.dto.RegistroTransportradorDTO;
import app.entities.Carga;
import app.entities.Cliente;
import app.entities.Empresa;
import app.entities.Pedido;
import app.entities.Transportador;
import app.entities.Trayecto;
import app.entities.Usuario;
import app.repository.CamionRepository;
import app.repository.EmpresaRepository;
import app.repository.PedidoRepository;
import app.repository.TransportadorRepository;
import app.repository.TrayectoRepository;
import app.repository.UsuarioRepository;
import app.services.ServicioTransportador;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTransportadorTest {

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
	private ServicioTransportador service;
	
	@InjectMocks
	private TransportadorRestController controller;
	
	@Before
	public void configure() {
		mockMvc = standaloneSetup(controller).build();
    	MockitoAnnotations.initMocks(this);
    	parser = new GsonBuilder().create();
	}
	
	@Test
	public void ingresarTest() throws Exception{
		Transportador t = new Transportador();
		t.setId(new BigDecimal(1));
		List<Transportador> l = new ArrayList<>();
		l.add(t);
		Usuario u = new Usuario();
		u.setTransportadorList(l);
		u.setClave("pipeli");
		Mockito.when(usuarioRepo.findUsuario("davip@hotmail")).thenReturn(u);
		IngresoDTO dto = new IngresoDTO("davip@hotmail", "pipeli");
		
		mockMvc.perform(post("/LoadRoad/Transportador/ingresar").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
	}
	
	@Test
	public void ingresarErrorTest() throws Exception{
		Transportador t = new Transportador();
		t.setId(new BigDecimal(1));
		List<Transportador> l = new ArrayList<>();
		l.add(t);
		Usuario u = new Usuario();
		u.setTransportadorList(l);
		u.setClave("p");
		Mockito.when(usuarioRepo.findUsuario("davip@hotmail")).thenReturn(u);
		IngresoDTO dto = new IngresoDTO("davip@hotmail", "pipeli");
		
		mockMvc.perform(post("/LoadRoad/Transportador/ingresar").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
	}
	
	@Test
	public void ingresarUsuarioEquivocadoTest() throws Exception{
		Empresa e = new Empresa();
		e.setId(new BigDecimal(1));
		List<Empresa> l = new ArrayList<>();
		l.add(e);
		Usuario u = new Usuario();
		u.setEmpresaList(l);
		u.setTransportadorList(new ArrayList<>());
		u.setClave("p");
		Mockito.when(usuarioRepo.findUsuario("davip@hotmail")).thenReturn(u);
		IngresoDTO dto = new IngresoDTO("davip@hotmail", "pipeli");
		
		mockMvc.perform(post("/LoadRoad/Transportador/ingresar").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
	}
	
	@Test
	public void registroTestNull() throws Exception{
		RegistroTransportradorDTO dto = new RegistroTransportradorDTO("fel@gsmail", "fet", "felps", "20111234", "1234", "trans S.A", "Bogota");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		mockMvc.perform(post("/LoadRoad/Transportador/registro").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}
	
	@Test
	public void registroTest() throws Exception{
		Empresa nuevaEmpresa = new Empresa("20111234", "gene", new BigDecimal("1000"), new BigDecimal("100"),"123","Bancolom",null,new BigDecimal("10"));
		List<Empresa> list= new ArrayList<>();
		list.add(nuevaEmpresa);
		Usuario nuevoUsuario = new Usuario("fel@gsmail", "fet", "felips", null, list, null);
		Transportador trans = new Transportador("20111234", "1234", nuevoUsuario, nuevaEmpresa);
		Mockito.when(usuarioRepo.findUsuarioByName("trans S.A")).thenReturn(nuevoUsuario);
		Mockito.when(usuarioRepo.saveAndFlush(nuevoUsuario)).thenReturn(nuevoUsuario);
		Mockito.when(transportadorRepo.saveAndFlush(trans)).thenReturn(null);
		
		RegistroTransportradorDTO dto = new RegistroTransportradorDTO("fel@gsmail", "fet", "felps", "20111234", "1234", "trans S.A", "Bogota");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		mockMvc.perform(post("/LoadRoad/Transportador/registro").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}

	@Test
	public void registroTestException() throws Exception{

		Usuario nuevoUsuario = new Usuario("fel@gsmail", "fet", "felips", null, null, null);
		Mockito.when(usuarioRepo.findUsuarioByName("trans S.A")).thenReturn(nuevoUsuario);
		
		RegistroTransportradorDTO dto = new RegistroTransportradorDTO("fel@gsmail", "fet", "felps", "20111234", "1234", "trans S.A", "Bogota");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		mockMvc.perform(post("/LoadRoad/Transportador/registro").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}
	
	@Test
	public void revisarMonitoreoTest() throws Exception{
		Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Trayecto tra = new Trayecto(null,null,'0','1',null,pe,tr);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(tra);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "1");
		params.add("idPedido", "1");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		sendGet("/LoadRoad/Transportador/revisarMonitoreo",params, jsonPath("$", matcher));
	}
	
	@Test
	public void revisarMonitoreoFalseTest() throws Exception{
		Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Trayecto tra = new Trayecto(null,null,'0','0',null,pe,tr);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(tra);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "1");
		params.add("idPedido", "1");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		sendGet("/LoadRoad/Transportador/revisarMonitoreo",params, jsonPath("$", matcher));
	}
	
	@Test
	public void revisarMonitoreoExceptionTest() throws Exception{
		Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Trayecto tra = new Trayecto(null,null,'0',null,null,pe,tr);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(tra);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "1");
		params.add("idPedido", "1");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		sendGet("/LoadRoad/Transportador/revisarMonitoreo",params, jsonPath("$", matcher));
	}
	
	@Test
	public void guardarCoordenadasTest() throws Exception{
		Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Trayecto tra = new Trayecto(null,null,'0','1',null,pe,tr);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(tra);
		Mockito.when(trayectoRepo.saveAndFlush(tra)).thenReturn(null);
		CoordenadasDTO dto = new CoordenadasDTO("12", "32", "1", "1");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		mockMvc.perform(post("/LoadRoad/Transportador/guardarCoordenadas").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}
	
	@Test
	public void guardarCoordenadasFalseTest() throws Exception{
		Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Trayecto tra = new Trayecto(null,null,'0','0',null,pe,tr);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(tra);
		CoordenadasDTO dto = new CoordenadasDTO("12", "32", "1", "1");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		mockMvc.perform(post("/LoadRoad/Transportador/guardarCoordenadas").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}
	
	@Test
	public void guardarCoordenadasNullTest() throws Exception{
		Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(null);
		CoordenadasDTO dto = new CoordenadasDTO("12", "32", "1", "1");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		mockMvc.perform(post("/LoadRoad/Transportador/guardarCoordenadas").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}
	
	@Test
	public void cambiarEstadoMarchaTest() throws Exception{
		Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Trayecto tra = new Trayecto(null,null,'0','0',null,pe,tr);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(tra);
		
		pe.setEstado("EnMarcha");
		tra.setEstado('1');
		Mockito.when(pedidoRepo.saveAndFlush(pe)).thenReturn(null);
		Mockito.when(trayectoRepo.saveAndFlush(tra)).thenReturn(null);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "1");
		params.add("idPedido", "1");
		params.add("estado", "EnMarcha");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		sendGet("/LoadRoad/Transportador/cambiarEstado",params, jsonPath("$", matcher));
	}
	
	@Test
	public void cambiarEstadoTest() throws Exception{
		Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Trayecto tra = new Trayecto(null,null,'0','0',null,pe,tr);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(tra);
		
		pe.setEstado("ProblemasVia");
		tra.setEstado('0');
		Mockito.when(pedidoRepo.saveAndFlush(pe)).thenReturn(null);
		Mockito.when(trayectoRepo.saveAndFlush(tra)).thenReturn(null);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "1");
		params.add("idPedido", "1");
		params.add("estado", "ProblemasVia");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		sendGet("/LoadRoad/Transportador/cambiarEstado",params, jsonPath("$", matcher));
	}
	
	@Test
	public void cambiarEstadoExceptionTest() throws Exception{
		/*Pedido pe = new Pedido();
		Transportador tr = new Transportador();
		Trayecto tra = new Trayecto(null,null,'0','0',null,pe,tr);
		Mockito.when(pedidoRepo.findOne(new BigDecimal(1))).thenReturn(pe);
		Mockito.when(transportadorRepo.findOne(new BigDecimal(1))).thenReturn(tr);
		Mockito.when(trayectoRepo.findByTransportadorPedido(tr,pe)).thenReturn(tra);*/
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "as");
		params.add("idPedido", "1");
		params.add("estado", "ProblemasVia");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		sendGet("/LoadRoad/Transportador/cambiarEstado",params, jsonPath("$", matcher));
	}
	
	@Test
	public void getInformacionTest() throws Exception{
		Usuario u = new Usuario();
		u.setNombre("David");
		Transportador tr = new Transportador();
		tr.setCedula("1234");
		tr.setUsuarioFk(u);
		
		Mockito.when(transportadorRepo.findOne(new BigDecimal(100))).thenReturn(tr);
		Matcher<String> matcher = new IsEqual<String>("1234");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "100");
		sendGet("/LoadRoad/Transportador/getInformacion",params, jsonPath("$.nit", matcher));
	}

	@Test
	public void getInformacionVacioTest() throws Exception{
		Mockito.when(transportadorRepo.findOne(new BigDecimal(100))).thenReturn(null);
		Matcher<String> matcher = new IsEqual<String>("0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "100");
		sendGet("/LoadRoad/Transportador/getInformacion",params, jsonPath("$.nit", matcher));
	}
	
	@Test
	public void detalleSolicitudTest() throws Exception{
		Carga c = new Carga();
		c.setAlto(new BigDecimal(1));
		c.setAncho(new BigDecimal(1));
		c.setLargo(new BigDecimal(1));
		c.setPeso(new BigDecimal(1));
		c.setValor(new BigDecimal(1));
		List<Carga> cargas = new ArrayList<>();
		cargas.add(c);
		Pedido p = new Pedido();
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
		
		Trayecto tra = new Trayecto(null, null, '0', '0', null, p, null);
		List<Trayecto> list = new ArrayList<>();
		list.add(tra);	
		Transportador tr = new Transportador("123", "657", null, null);
		tr.setTrayectoList(list);
		
		Usuario us = new Usuario();
		us.setNombre("Dav");
		Cliente cli = new Cliente(new BigDecimal(1));
		cli.setUsuarioFk(us);
		p.setClienteFk(cli);
		
		Mockito.when(transportadorRepo.findOne(new BigDecimal(100))).thenReturn(tr);
		Matcher<String> matcher = new IsEqual<String>("1");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "100");
		sendGet("/LoadRoad/Transportador/detalleSolicitudes",params, jsonPath("$[0].peso", matcher));
	}
	
	@Test
	public void detalleSolicitudFalloTest() throws Exception{
		
		Mockito.when(transportadorRepo.findOne(new BigDecimal(100))).thenReturn(null);
		Matcher<List<InfoCargaDTO>> matcher = new IsEqual<List<InfoCargaDTO>>(new ArrayList<>());
		params = new LinkedMultiValueMap<String, String>();
		params.add("idTransportador", "100");
		sendGet("/LoadRoad/Transportador/detalleSolicitudes",params, jsonPath("$", matcher));
	}
	
	private void sendGet(String url, MultiValueMap<String, String> params, ResultMatcher expected) throws Exception{
		mockMvc.perform(get(url).params(params)).andExpect(status().isOk()).andExpect(expected);
	}
}
