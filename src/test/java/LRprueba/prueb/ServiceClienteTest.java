package LRprueba.prueb;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import app.controllers.ClienteRestController;
import app.dto.IngresoDTO;
import app.dto.NotificacionCargaDTO;
import app.dto.RegistroClienteDTO;
import app.entities.Calificacion;
import app.entities.Carga;
import app.entities.Cliente;
import app.entities.Cuentas;
import app.entities.Empresa;
import app.entities.Pedido;
import app.entities.Trayecto;
import app.entities.Usuario;
import app.repository.CalificacionRepository;
import app.repository.CentroAcopioRepository;
import app.repository.ClienteRepository;
import app.repository.CuentasRepository;
import app.repository.EmpresaRepository;
import app.repository.PedidoRepository;
import app.repository.TrayectoRepository;
import app.repository.UsuarioRepository;
import app.services.ServicioCliente;

@RunWith(MockitoJUnitRunner.class)
public class ServiceClienteTest {

	private MockMvc mockMvc;
	
	/** The Constant APPLICATION_JSON. */
	// Constants
	private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
	
	private Gson parser;
	
	private MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Mock
	ClienteRepository clienteRepo;

	@Mock
	UsuarioRepository usuarioRepo;
	
	@Mock	
	CuentasRepository cuentasRepo;
	
	@Mock	
	PedidoRepository pedidoRepo;
	
	@Mock	
	EmpresaRepository empresaRepo;
	
	@Mock
	TrayectoRepository trayectoRepo;
	
	@Mock
	CentroAcopioRepository centroAcopioRepo;
	
	@Mock
	CalificacionRepository calificacionRepo;
	
	@Spy
	@InjectMocks
	private ServicioCliente service;
	
	@InjectMocks
	private ClienteRestController controller;
	
	@Before
	public void configure() {
		mockMvc = standaloneSetup(controller).build();
    	MockitoAnnotations.initMocks(this);
    	parser = new GsonBuilder().create();
	}
	
	@Test
	public void ingresarTest() throws Exception{
		Cliente c = new Cliente();
		c.setId(new BigDecimal(1));
		List<Cliente> l = new ArrayList<>();
		l.add(c);
		Usuario u = new Usuario();
		u.setClienteList(l);
		u.setClave("pipeli");
		Mockito.when(usuarioRepo.findUsuario("davip@hotmail")).thenReturn(u);
		IngresoDTO dto = new IngresoDTO("davip@hotmail", "pipeli");
		
		mockMvc.perform(post("/LoadRoad/Cliente/ingresar").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
	}
	
	@Test
	public void ingresarErrorTest() throws Exception{
		Cliente c = new Cliente();
		c.setId(new BigDecimal(1));
		List<Cliente> l = new ArrayList<>();
		l.add(c);
		Usuario u = new Usuario();
		u.setClienteList(l);
		u.setClave("p");
		Mockito.when(usuarioRepo.findUsuario("davip@hotmail")).thenReturn(u);
		IngresoDTO dto = new IngresoDTO("davip@hotmail", "pipeli");
		
		mockMvc.perform(post("/LoadRoad/Cliente/ingresar").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
	}
	
	@Test
	public void registroTestException() throws Exception{
		RegistroClienteDTO dto = new RegistroClienteDTO("fel@gsmail", "fet", "felps", "20111234", "10000", "gene", "Bancolom", 
				"100", null, "231");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		mockMvc.perform(post("/LoadRoad/Cliente/registro").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}
	
	@Test
	public void registroTest() throws Exception{
		Cuentas nuevaCuenta = new Cuentas("10000", "gene", "Bancolom",
				"100",format.parse( "12-02-2012"), "231", null);

		Usuario nuevoUsuario = new Usuario("felipRo@gsmail", "fe", "felips", null, null, null);
		Cliente nuevoCliente = new Cliente("20111234", null, nuevaCuenta, nuevoUsuario);
		
		Mockito.when(usuarioRepo.saveAndFlush(nuevoUsuario)).thenReturn(null);
		Mockito.when(cuentasRepo.saveAndFlush(nuevaCuenta)).thenReturn(null);
		Mockito.when(clienteRepo.saveAndFlush(nuevoCliente)).thenReturn(null);
		
		RegistroClienteDTO dto = new RegistroClienteDTO("felipRo@gsmail", "fe", "felips", "20111234", "10000", "gene", "Bancolom", 
				"100", "12-02-2012", "231");
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		mockMvc.perform(post("/LoadRoad/Cliente/registro").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", matcher));
	}



	
	@Test
	public void revisarSolicitudesVacioTest() throws Exception{
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "1");
		Matcher<List<NotificacionCargaDTO>> matcher = new IsEqual<List<NotificacionCargaDTO>>(new ArrayList<>());
		Mockito.when(clienteRepo.findOne(new BigDecimal(1))).thenReturn(null);
		sendGet("/LoadRoad/Cliente/revisarSolicitudes",params, jsonPath("$", matcher));
	}

	@Test
	public void revisarSolicitudesTest() throws Exception{
		Usuario us = new Usuario();
		us.setNombre("David");
		Empresa em = new Empresa();
		em.setUsuarioFk(us);
		Carga c = new Carga();
		List<Carga> cargas = new ArrayList<>();
		cargas.add(c);
		Pedido p = new Pedido();
		p.setCargaList(cargas);
		p.setEmpresaFk(em);
		p.setEstado(Constantes.getPendiente());
		p.setHoraPedido(new Date());
		p.setId(new BigDecimal(600));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Cliente cliente = new Cliente();
		cliente.setPedidoList(list);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		Matcher<String> matcher = new IsEqual<String>("600");
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(cliente);
		sendGet("/LoadRoad/Cliente/revisarSolicitudes",params, jsonPath("$[0].idPedido", matcher));
	}
	
	@Test
	public void getInformacionTest() throws Exception{
		Usuario u = new Usuario();
		u.setNombre("David");
		Cliente cliente = new Cliente();
		cliente.setNit("1234");
		cliente.setUsuarioFk(u);
		
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(cliente);
		Matcher<String> matcher = new IsEqual<String>("1234");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/getInformacion",params, jsonPath("$.nit", matcher));
	}

	@Test
	public void getInformacionVacioTest() throws Exception{
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(null);
		Matcher<String> matcher = new IsEqual<String>("0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/getInformacion",params, jsonPath("$.nit", matcher));
	}
	
	@Test
	public void seleccionarTest() throws Exception{
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(new Pedido());
		Mockito.when(empresaRepo.findOne(new BigDecimal(100))).thenReturn(new Empresa());
		
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Cliente/seleccionar",params, jsonPath("$", matcher));
	}
	
	@Test
	public void seleccionarFalseTest() throws Exception{
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(new Pedido());
		Mockito.when(empresaRepo.findOne(new BigDecimal(100))).thenReturn(null);
		
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		params.add("idEmpresa", "100");
		sendGet("/LoadRoad/Cliente/seleccionar",params, jsonPath("$", matcher));
	}
	
	@Test
	public void noMonitorearTest() throws Exception{
		Trayecto t = new Trayecto();
		t.setEstado('0');
		Pedido p = new Pedido();
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		Mockito.when(trayectoRepo.findByPedido(p)).thenReturn(t);
		
		Matcher<String> matcher = new IsEqual<String>("Undefined");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		sendGet("/LoadRoad/Cliente/monitorear",params, jsonPath("$.latitud", matcher));
	}
	
	@Test
	public void monitorearTest() throws Exception{
		Trayecto t = new Trayecto();
		t.setMonitoreo('1');
		t.setEstado('1');
		t.setLatitud("123");
		Pedido p = new Pedido();
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		Mockito.when(trayectoRepo.findByPedido(p)).thenReturn(t);
		
		Matcher<String> matcher = new IsEqual<String>("123");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		sendGet("/LoadRoad/Cliente/monitorear",params, jsonPath("$.latitud", matcher));
	}
	
	@Test
	public void finMonitoreoTest() throws Exception{
		Trayecto t = new Trayecto();
		Pedido p = new Pedido();
		Mockito.when(pedidoRepo.findOne(new BigDecimal(100))).thenReturn(p);
		Mockito.when(trayectoRepo.findByPedido(p)).thenReturn(t);
		
		params = new LinkedMultiValueMap<String, String>();
		params.add("idPedido", "100");
		mockMvc.perform(get("/LoadRoad/Cliente/finalizarMonitoreo").params(params)).andExpect(status().isOk());
	}
	
	@Test
	public void calificarTest() throws Exception{
		Empresa em = new Empresa();
		Calificacion cal = new Calificacion(new BigDecimal("5"),new Date(),em);
		Mockito.when(empresaRepo.findOne(new BigDecimal(100))).thenReturn(em);
		Mockito.when(calificacionRepo.saveAndFlush(cal)).thenReturn(null);
		
		
		Matcher<Boolean> matcher = new IsEqual<Boolean>(true);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "100");
		params.add("calificacion", "5");
		sendGet("/LoadRoad/Cliente/calificar", params, jsonPath("$", matcher));
	}
	
	@Test
	public void calificarFalseTest() throws Exception{
		
		Matcher<Boolean> matcher = new IsEqual<Boolean>(false);
		params = new LinkedMultiValueMap<String, String>();
		params.add("idEmpresa", "dfs");
		params.add("calificacion", "5");
		sendGet("/LoadRoad/Cliente/calificar", params, jsonPath("$", matcher));
	}
	
	@Test
	public void precioDashTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Cliente c = new Cliente();
		c.setPedidoList(list);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(c);
		Matcher<String> matcher = new IsEqual<String>((cale.get(Calendar.MONTH))+"");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/precioDash", params, jsonPath("$[0].mes", matcher));
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
		Cliente c = new Cliente();
		c.setPedidoList(list);
		
		cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(c);
		Matcher<String> matcher = new IsEqual<String>((cale.get(Calendar.MONTH))+"");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/precioDash", params, jsonPath("$[0].mes", matcher));
	}
	
	@Test
	public void ciudadesDashTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setLugarCargaCiudad("Bogotá");
		p.setLugarDescargaCiudad("Medellin");
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Cliente c = new Cliente();
		c.setPedidoList(list);
		
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(c);
		Matcher<String> matcher = new IsEqual<String>("Bogotá");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/ciudadesDash", params, jsonPath("$[0].origen", matcher));
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
		Cliente c = new Cliente();
		c.setPedidoList(list);
		
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(c);
		Matcher<String> matcher = new IsEqual<String>("Bogotá");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/ciudadesDash", params, jsonPath("$[0].origen", matcher));
	}
	
	@Test
	public void todosPedidosTest() throws Exception{
		Usuario us = new Usuario();
		us.setNombre("Empresa");
		Empresa em = new Empresa(new BigDecimal(100));
		em.setUsuarioFk(us);
		em.setCalificacion(new ArrayList<>());
		Cliente c = new Cliente(new BigDecimal(100));
		Carga ca = new Carga("", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 
				new BigDecimal(0), "", "", null);
		List<Carga> cargas = new ArrayList<>();
		cargas.add(ca);
		Pedido p = new Pedido(new Date(),new Date(),"123,421","124,421","calle 12 #23-12","calle 12 #23-12","Bogotá",
				"Medellin","Si","Aceptado",new BigDecimal(0),(short)0,(short)0, c,new Date());
		p.setCargaList(cargas);
		p.setEmpresaFk(em);
		p.setId(new BigDecimal(100));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		
		c.setPedidoList(list);
		
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(c);
		Matcher<String> matcher = new IsEqual<String>("Bogotá");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/todosPedidos", params, jsonPath("$[0].ciudadOrigen", matcher));
	}
	
	@Test
	public void gananciasAnioTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Cliente cl = new Cliente();
		cl.setPedidoList(list);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(cl);
		Matcher<String> matcher = new IsEqual<String>("50.0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/gastosAnio", params, jsonPath("$.valor", matcher));
	}
	
	@Test
	public void gananciasMesTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Cliente cl = new Cliente();
		cl.setPedidoList(list);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(cl);
		Matcher<String> matcher = new IsEqual<String>("50.0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/gastosMes", params, jsonPath("$.valor", matcher));
	}
	
	@Test
	public void gananciasDiaTest() throws Exception{
		
		Pedido p = new Pedido();
		p.setHoraPedido(new Date());
		p.setPrecio(new BigDecimal(50));
		List<Pedido> list = new ArrayList<>();
		list.add(p);
		Cliente cl = new Cliente();
		cl.setPedidoList(list);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		Mockito.when(clienteRepo.findOne(new BigDecimal(100))).thenReturn(cl);
		Matcher<String> matcher = new IsEqual<String>("50.0");
		params = new LinkedMultiValueMap<String, String>();
		params.add("idCliente", "100");
		sendGet("/LoadRoad/Cliente/gastosDia", params, jsonPath("$.valor", matcher));
	}
	/*@Test
	public void buscarEmpresa() throws Exception{

		Transportador tr = new Transportador();
		tr.setEstado(Constantes.getDisponible());
		tr.setCiudad("Bogota");
		List<Transportador> transList = new ArrayList<>();
		transList.add(tr);
		
		Camion cam = new Camion("213 dsf", "chevrolet", "duster", "2000", "6 ejes", new BigDecimal("50"),new BigDecimal("50"), 
				new BigDecimal("50"), new BigDecimal("50"), "Disponible", "Bogota"); 
		
		Empresa em = new Empresa();
		em.setTransportadorList(transList);
		CentroAcopio c = new CentroAcopio();
		c.setEmpresaFk(em);
		List<CentroAcopio> centros = new ArrayList<>();
		centros.add(c);
		
		Mockito.when(centroAcopioRepo.findCentroCiudad("Bogota")).thenReturn(centros);
		Mockito.when(clienteRepo.findOne(new BigDecimal("550"))).thenReturn(null);
		
		
		InfoCargaDTO dto = new InfoCargaDTO("caja", "perecedero", "34", "12", "10", "30", "Calle 34 #90-23", "Carrera 23 #40-13",
				"4.752316, -74.050504",	"10.988896, -74.960112", "Bogota", "Medellin", "1", "0", "2016/05/08 10:59", 
				"2017/06/08 20:40", "carga", "No", "43200", "550", null, null, null, null,"6 ejes","1",""); 
		mockMvc.perform(post("/LoadRoad/Cliente/ingresar").contentType(APPLICATION_JSON).content(parser.toJson(dto)))
		.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
	}*/
	
	private void sendGet(String url, MultiValueMap<String, String> params, ResultMatcher expected) throws Exception{
		mockMvc.perform(get(url).params(params)).andExpect(status().isOk()).andExpect(expected);
	}
	


}
